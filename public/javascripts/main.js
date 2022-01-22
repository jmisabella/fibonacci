var webSocket;
var messageInput;

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
  console.log("New Data: ", receivedData);
  // get the text from the "body" field of the json we
  // receive from the server.
  appendServerMessageToView("Server", receivedData.body);
}

function appendClientMessageToView(title, message) {
  $("#message-content").append("<span>" + title + ": " + message + "<br /></span>");
}

function appendServerMessageToView(title, message) {
  $("#message-content").append("<span>" + title + ": " + message + "<br /><br /></span>");
}

function consoleLog(message) {
  console.log("New message: ", message);
}

window.addEventListener("load", init, false);

$("#send-button").click(function (e) {
  console.log("Sending ...");
  getMessageAndSendToServer();
  // put focus back in the textarea
  $("#message-input").focus();
});

// send the message when the user presses the <enter> key while in the textarea
$(window).on("keydown", function (e) {
  if (e.which == 13) {
    getMessageAndSendToServer();
    return false;
  }
});

// there’s a lot going on here:
// 1. get our message from the textarea.
// 2. append that message to our view/div.
// 3. create a json version of the message.
// 4. send the message to the server.
function getMessageAndSendToServer() {

  // get the text from the textarea
  messageInput = $("#message-input").val();

  // clear the textarea
  $("#message-input").val("");

  // if the trimmed message was blank, return now
  if ($.trim(messageInput) == "") {
    return false;
  }

  // add the message to the view/div
  appendClientMessageToView("Me", messageInput);

  // create the message as json
  let jsonMessage = {
    message: messageInput
  };

  // send our json message to the server
  sendToServer(jsonMessage);
}



/////////////////////////////
function fibNumbers() {
    return [0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]
}

var canvas = document.getElementById("can");
var ctx = canvas.getContext("2d");

// Assume ctx is canvas 2D Context and ready to render to
var width = ctx.canvas.width;
var height = ctx.canvas.height;

var center = {
    x: width / 2,
    y: height / 2
};

canvas.addEventListener('mousemove', function(){
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
    drawStroke([{x:0, y:-center.y}, {x:0, y:center.y}], center, "gray");
    drawStroke([{x:-center.x, y:0}, {x:center.x, y:0}], center,"gray");

    // Draw spiral -> center viewport at 0,0
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
    strokeColor = strokeColor || "black";

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

    // Start with 0 1 2... instead of the real sequence 0 1 1 2...
    thisFibonacci.array = [0, 1, 2];

    thisFibonacci.getDiscrete = function(n){

        // If the Fibonacci number is not in the array, calculate it
        while (n >= thisFibonacci.array.length){
            var length = thisFibonacci.array.length;
            var nextFibonacci = thisFibonacci.array[length - 1] + thisFibonacci.array[length - 2];
            thisFibonacci.array.push(nextFibonacci);
        }

        return thisFibonacci.array[n];
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

  // var c = document.getElementById("canv");
  // var ctx = c.getContext("2d");
  
  // var grd = ctx.createLinearGradient(0, 0, 200, 0);
  // grd.addColorStop(0, "red");
  // grd.addColorStop(1, "white");
  // // Fill with gradient
  // ctx.fillStyle = grd;
  // ctx.fillRect(10, 10, 150, 80);

  // ctx.moveTo(0, 0);
  // ctx.lineTo(200, 100);
  // ctx.stroke();


  // ctx.beginPath();
  // ctx.arc(95, 50, 40, 0, 2 * Math.PI);
  // ctx.stroke();

}
