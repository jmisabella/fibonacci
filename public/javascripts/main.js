var webSocket;
var fibNs;

function head(ns) {
  return ns[0];
}

function tail(ns) {
  return ns.slice(1);
}

function init() {
  var host = location.origin.replace(/^https/, 'wss').replace(/^http/, 'ws'); 
  webSocket = new WebSocket(`${host}/ws`);
  webSocket.onopen = onOpen;
  webSocket.onclose = onClose;
  webSocket.onmessage = onMessage;
  webSocket.onerror = onError;
  $("#message-input").focus();
}

function onOpen(event) {
  consoleLog("CONNECTED");
}

function onClose(event) {
  consoleLog("DISCONNECTED");
  consoleLog("Re-initializing a new fresh connection so server will be available for next action");
  init();
}

function onError(event) {
  consoleLog("ERROR: " + event.data);
  consoleLog("ERROR: " + JSON.stringify(event));
}

function onMessage(event) {
  console.log(event.data);
  let receivedData = JSON.parse(event.data);
  // console.log("New Data: ", receivedData);
  fibNs = receivedData.body.numbers;
  // console.log("FIB SEQ: " + fibNs); 
  // for (i = 0; i < fibNs.length; i++) {
  //   console.log("FIB NUMBER: " + fibNs[i]);
  // }
}

function consoleLog(message) {
  console.log("New message: ", message);
}

window.addEventListener("load", init, false);

$("#send-button").click(function (e) {
  console.log("Sending ...");
  getFibSequence();  
});

// 1. create a json version of the message.
// 2. send the message to the server.
function getFibSequence() {
  // create the message as json
  var messageInput = "fibonacciSequence=1200;";
  // var messageInput = "fibonacciSequence=200;";
  let jsonMessage = {
    message: messageInput
  };
  // send our json message to the server
  sendToServer(jsonMessage);
}

var spiralPhase = 2;
// var previousSpiralPhase = 1;
var backgroundPhase = 0;
var canvas = document.getElementById("can");
var ctx = canvas.getContext("2d");
var color = "#000";
var p1 = 0; 
var p2 = 0; 

// Assume ctx is canvas 2D Context and ready to render to
var width = ctx.canvas.width;
var height = ctx.canvas.height;

var center = {
    x: width / 2,
    y: height / 2
};

canvas.addEventListener('click', function() {
  spiralPhase = (spiralPhase + 1) % 4;
  // ctx.clearRect(0, 0, $("#can").width, $("#can").height);
  backgroundPhase = random(0, 4);
  $("#can").removeClass("background-0");
  $("#can").removeClass("background-1");
  $("#can").removeClass("background-2");
  $("#can").removeClass("background-3");
  $("#can").removeClass("background-4");
  $("#can").addClass("background-" + backgroundPhase);
  ctx.clearRect(0, 0, width, height);
  ctx.beginPath();
  color;
  switch (backgroundPhase) {
    case 0:
      color = "#16067f";
      break;
    case 1:
      color = "#4000ff";
      break;
    case 2:
      color = "#5affde";
      break;
    case 3:
      color = "#FFCB05";
      break;
    case 4:
      color = "#FFCB05";
      break;
    case "0":
      color = "#16067f";
      break;
    case "1":
      color = "#4000ff";
      break;
    case "2":
      color = "#5affde";
      break;
    case "3":
      color = "#FFCB05";
      break;
    case "4":
      color = "#FFCB05";
      break;
    default:
      color = "#000";
      break;
  }
  drawStroke(getSpiral(p1, p2, getDistance({x:0,y:0},center)), center, color);
});

function random(min = 0, max = Number.MAX_SAFE_INTEGER) {
  return Math.floor(Math.random() * (max - min) + min);
} 

canvas.addEventListener('mousemove', function() {
    var mouse = {};

    mouse.x = event.offsetX;
    mouse.y = event.offsetY;
    if(mouse.x === undefined){ // if firefox
        mouse.x = event.clientX;
        mouse.y = event.clientY;
    }

    // Substract offset (so it's centered at 0,0)
    mouse.x -= center.x;
    mouse.y -= center.y;

    drawFibonacciSpiral({x:0, y:0}, mouse);
});

var drawFibonacciSpiral = function(p1, p2){
    ctx.clearRect(0, 0, width, height);

    // Draw coord axis -> center viewport at 0,0
    // drawStroke([{x:0, y:-center.y}, {x:0, y:center.y}], center, "gray");
    // drawStroke([{x:-center.x, y:0}, {x:center.x, y:0}], center,"gray");

    // Draw spiral -> center viewport at 0,0
    // drawStroke(getSpiral(p1, p2, getDistance({x:0,y:0},center)), center, "Red");
    // drawStroke(getSpiral(p1, p2, getDistance({x:0,y:0},center)), center);
    drawStroke(getSpiral(p1, p2, getDistance({x:0,y:0},center)), center);
};

var getDistance = function(p1, p2){
    return Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
};

var getAngle = function(p1, p2){
    return Math.atan2(p2.y-p1.y, p2.x-p1.x);
};

var drawStroke = function(points, offset, strokeColor){
    // Default value
    offset = offset || {x:0,y:0}; // Offset to center on screen
    // strokeColor = strokeColor || "black";

    ctx.strokeStyle = strokeColor;
    ctx.beginPath();
    var p = points[0];
    ctx.moveTo(offset.x + p.x, offset.y + p.y);
    for(var i = 1; i < points.length; i++){
        p = points[i];
        ctx.lineTo(offset.x + p.x, offset.y + p.y);
    }
    ctx.stroke();  // draw it all
};

var FibonacciGenerator = function(){
    var thisFibonacci = this;

    thisFibonacci.getDiscrete = function(n){
        if (fibNs == null || fibNs.length == 0) {
          getFibSequence();
        }
        if (n >= fibNs.length) {
          n = 0;
        }
        return fibNs[n];
    };

    thisFibonacci.getNumber = function(n){
        var floor = Math.floor(n);
        var ceil = Math.ceil(n);

        if (Math.floor(n) == n){
            return thisFibonacci.getDiscrete(n);
        }

        var a = Math.pow(n - floor, 1.15);

        var fibFloor = thisFibonacci.getDiscrete(floor);
        var fibCeil = thisFibonacci.getDiscrete(ceil);

        return fibFloor + a * (fibCeil - fibFloor);
    };
    return thisFibonacci;
};

var getSpiral = function(pA, pB, maxRadius){
    // 1 step = 1/4 turn or 90º

    var precision = 500; // Lines to draw in each 1/4 turn
    var stepB = 4; // Steps to get to point B

    var angleToPointB = getAngle(pA,pB); // Angle between pA and pB
    var distToPointB = getDistance(pA,pB); // Distance between pA and pB

    var fibonacci = new FibonacciGenerator();

    // Find scale so that the last point of the curve is at distance to pB
    var radiusB = fibonacci.getNumber(stepB);
    var scale = distToPointB / radiusB;

    // Find angle offset so that last point of the curve is at angle to pB
    var angleOffset = angleToPointB - stepB * Math.PI / 2;

    var path = [];

    var i, step , radius, angle, p;

    // Start at the center
    i = step = radius = angle = 0;

    // Continue drawing until reaching maximum radius
    while (radius * scale <= maxRadius){
        p = {
            x: scale * radius * Math.cos(angle + angleOffset) + pA.x,
            y: scale * radius * Math.sin(angle + angleOffset) + pA.y
        };
        path.push(p);
        
        // JMI start 
        var phase = spiralPhase; 
        q = ''
        switch(phase.toString()) {
          case '0':
            q = {
                x: (scale+1) * (radius+1) * Math.cos(angle + angleOffset) + pA.x,
                y: (scale+1) * (radius+1) * Math.sin(angle + angleOffset) + pA.y
            };
            break;
          case '1':
            q = {
                x: (scale+1) * (radius-1) * Math.cos(angle + angleOffset) + pA.x,
                y: (scale-1) * (radius+1) * Math.sin(angle + angleOffset) + pA.y
            };
            break;
          case '2':
            q = {
                x: (scale+1) * (radius+1) * Math.cos(angle + angleOffset) + pA.x,
                y: (scale-1) * (radius-1) * Math.sin(angle + angleOffset) + pA.y
            };
            break;
          case '3':
            q = {
                x: (scale-1) * (radius+1) * Math.cos(angle + angleOffset) + pA.x,
                y: (scale+1) * (radius-1) * Math.sin(angle + angleOffset) + pA.y
            };
            break;
          default:
            q = {
                x: (scale+1) * (radius+1) * Math.cos(angle + angleOffset) + pA.x,
                y: (scale+1) * (radius+1) * Math.sin(angle + angleOffset) + pA.y
            };
            break;
        } 
        path.push(q);
        // JMI end

        i++; // Next point
        step = i / precision; // 1/4 turns at point    
        radius = fibonacci.getNumber(step); // Radius of Fibonacci spiral
        angle = step * Math.PI / 2; // Radians at point
    }

    return path;
};

/////////////////////////////

// send the data to the server using the WebSocket
function sendToServer(jsonMessage) {
  if(webSocket.readyState == WebSocket.OPEN) {
    consoleLog("SENT: " + jsonMessage.message);
    webSocket.send(JSON.stringify(jsonMessage));
  } else {
    consoleLog("Could not send data. Websocket is not open.");
  }

}
