package models.modules

import models.behaviors.fibonacci.{ Fibonacci, FibonacciByList }

case object Fib extends FibonacciByList {
  def formattedResultJson(length: Int): String = {
    val seq: List[BigInt] = Fib.fibonacciSequence(length)
    val json = seq.mkString("""{ "numbers": [""", ",", "]}")
    json 
  }
}
