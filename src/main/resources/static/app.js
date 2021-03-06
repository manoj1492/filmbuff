var stompClient = null;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  } else {
    $("#conversation").hide();
  }
  $("#questions").html("");
}

function connect() {
  var socket = new SockJS("/websocket");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log("Connected: " + frame);
    stompClient.subscribe("/topic/quiz", function (question) {
      showQuestions(JSON.parse(question.body));
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

/* function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
} */

function showQuestions(question) {
  $("#questions").empty();
  console.log(questions);
  $("#questions").append("<tr><td>");
  $("#questions").append(question.statement);
  $("#questions").append("</td></tr>");
  for (i in question.answerList) {
    $("#questions").append("<tr><td>");
    $("#questions").append(question.answerList[i].content);
    $("#questions").append("</td></tr>");
  }
}

$(function () {
  $("form").on("submit", function (e) {
    e.preventDefault();
  });
  $("#connect").click(function () {
    connect();
  });
  $("#disconnect").click(function () {
    disconnect();
  });
  /* $( "#send" ).click(function() { sendName(); }); */
});
