<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h1>WebSockets Test</h1>
<button id="restCall">restCall</button>
<div id="restResult"></div>
<button id="consolidateBtn">Consolidate</button>
<div id="consolidationResult"></div>

<div id="broadcasts">

</div>


<div id="special"></div>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../res/stomp.min.js"></script>
<script src="../res/sockjs.min.js"></script>
<script src="../js/rawConfig.js"></script>
<script src="../js/stompConfig.js"></script>

<script type="text/javascript">
    //rawConfig();
    stompInit();

    $('#restCall').on('click', function () {
        $.get("/restCall", function (data) {
            $('#restResult').text(JSON.stringify(data));
        });
    })

</script>

</body>
</html>