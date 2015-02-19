function rawConfig() {

    var rawSocket = new SockJS("/rawSocket");

    rawSocket.onopen = function() {
        console.log('open');
        rawSocket.send('test');
    };
    rawSocket.onmessage = function(e) {
        console.log('message', e.data);
    };
    rawSocket.onclose = function() {
        console.log('close');
    };

    rawSocket.send('test');

    rawSocket.close();

}