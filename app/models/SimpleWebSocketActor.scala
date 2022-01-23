package models

import models.modules.Fib
import akka.actor._
import play.api.libs.json._
import play.api.libs.json.Json

object SimpleWebSocketActor {
  // DOCS: Props is a ActorRef configuration object, that is immutable, so it is 
  // thread-safe and fully sharable. Used when creating new actors through 
  // ActorSystem.actorOf and ActorContext.actorOf.
  def props(clientActorRef: ActorRef) = Props(new SimpleWebSocketActor(clientActorRef))
}

class SimpleWebSocketActor(clientActorRef: ActorRef) extends Actor {
  val logger = play.api.Logger(getClass)

  private def parseArgs(rawMsg: String): Map[String, String] = rawMsg.split(";").map(kv => kv.split("=").head -> kv.split("=").tail.head).toMap

  def receive = {
    case jsValue: JsValue =>
      logger.info(s"JS-VALUE: $jsValue")
      val clientMessage = getMessage(jsValue)

      val args: Map[String, String] = parseArgs(clientMessage);
      val result: String = args.keySet match {
        case s if (s == Set("fibonacciSequence")) => Fib.formattedResultJson(args("fibonacciSequence").toInt)
        case u => throw new IllegalArgumentException(s"From args [$clientMessage], unexpected arg key combination [$u]")
      }

      val json: JsValue = Json.parse(s"""{"body": $result}""")
      println("JSON: " + json);
      clientActorRef ! (json)
  }

  // parse the "message" field from the json the client sends us
  def getMessage(json: JsValue): String = (json \ "message").as[String]

}
