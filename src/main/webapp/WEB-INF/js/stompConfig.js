function stompInit() {

    $('#consolidateBtn').on('click', function () {
        var socket = new SockJS('/socket');
        var stompClient = Stomp.over(socket);

        stompClient.connect({}, function(frame) {

            console.log('Connected ' + frame);

            stompClient.send("/app/consolidate", {}, {});

            stompClient.subscribe("/user/broker/consolidationResult", function(message) {
                var $el = $('<span>').text(message.body);
                var $p = $('<p>');
                $el.appendTo($('#consolidationResult'));
                $p.appendTo($('#consolidationResult'));
            });

            stompClient.subscribe("/broker/broadcast", function(message) {
                $('#broadcasts').empty();
                var $el = $('<span>').text(message.body).css('color', 'red');
                var $p = $('<p>');
                $el.appendTo($('#broadcasts'));
                $p.appendTo($('#broadcasts'));
            });

        }, function(error) {
            console.log("STOMP protocol error " + error);
        });
    });
}
