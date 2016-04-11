<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <title>ПИП Лаба №8</title>
</head>
<body>
<script>
    $(document).ready(function () {
        var $chb = $('input.chb');
        $chb.click(function () {
            $chb.prop('checked', false);
            $(this).prop('checked', true);
        });
    });
</script>
<div class="wrapper">
    <header>
        <p>Пашнин Александр Денисович</p>
        <p>Группа P3217</p>
        <p>Вариант 1727</p>
    </header>
    <div class="content">
        <div class="inputs">
            <form method="GET" action="/lab8/main">
                <p>X value:
                    <input type="checkbox" name="XSelector" class="chb" value="-2" checked>-2
                    <input type="checkbox" name="XSelector" class="chb" value="-1.5">-1.5
                    <input type="checkbox" name="XSelector" class="chb" value="-1">-1
                    <input type="checkbox" name="XSelector" class="chb" value="-0.5">-0.5
                    <input type="checkbox" name="XSelector" class="chb" value="0">0
                    <input type="checkbox" name="XSelector" class="chb" value="0.5">0.5
                    <input type="checkbox" name="XSelector" class="chb" value="1">1
                    <input type="checkbox" name="XSelector" class="chb" value="1.5">1.5
                    <input type="checkbox" name="XSelector" class="chb" value="2">2
                <p>Y value {-3..5}:
                    <input type="text" id="textY" name="YSelector" placeholder="-3..5" onchange="validateForm()"
                           onkeydown="javascript:if(13==event.keyCode){return false;}" required>
                </p>
                <p id="rad">Select R:
                    <button type="button" name="RSelector" class="radBtn" onclick="rSelected(this)" value="1">1</button>
                    <button type="button" name="RSelector" class="radBtn" onclick="rSelected(this)" value="2">2</button>
                    <button type="button" name="RSelector" class="radBtn" onclick="rSelected(this)" value="3">3</button>
                    <button type="button" name="RSelector" class="radBtn" onclick="rSelected(this)" value="4">4</button>
                    <button type="button" name="RSelector" class="radBtn" onclick="rSelected(this)" value="5">5</button>
                </p>
                <input type="hidden" id="hiddenR" name="RSelector"></input>
                <button type="submit" id="ok" disabled="true">OK</button>
            </form>
        </div>
        <div class="area">
            <p><img name='img' src='http://i.imgur.com/NYTtEHF.png' id="graph" style="position: relative;"/></p>
        </div>
    </div>
</div>
</body>
</html>
<script>
    function validateForm() {
        var y_val = document.getElementById("textY").value;
        y_val = y_val.replace(",", ".");
        var r_val = document.getElementById("hiddenR").value;
        var y_valid = !((y_val == "") || !(!isNaN(parseFloat(y_val)) && isFinite(y_val)) || (y_val > 5) || (y_val < -3));
        var r_valid = !isNaN(parseFloat(r_val));
        if (y_valid && r_valid) {
            document.getElementById("ok").disabled = false;
            document.getElementById("textY").style.borderColor = "";
            document.getElementById("rad").style.borderStyle = "";
            document.getElementById("rad").style.borderWidth = "";
            document.getElementById("rad").style.borderColor = "";
        } else {
            document.getElementById("ok").disabled = true;
            if (!y_valid) {
                document.getElementById("textY").style.borderColor = "red";
            }
            if (!r_valid) {
                document.getElementById("rad").style.borderStyle = "solid";
                document.getElementById("rad").style.borderWidth = "1px";
                document.getElementById("rad").style.borderColor = "red";
            }
        }
    }

    function rSelected(btn) {
        document.getElementById("hiddenR").value = btn.value;
        var elems = document.getElementsByClassName("radBtn");
        for (var i = 0; i < elems.length; i++) {
            elems[i].style.backgroundColor = "";
        }
        btn.style.backgroundColor = "#4CAF50";
        validateForm();
    }
</script>
<script>
    document.getElementById('graph').onclick = function (e) {
        var x = e.offsetX == undefined ? e.layerX : e.offsetX - 100;
        var y = e.offsetY == undefined ? e.layerY : e.offsetY;
        if (y <= 100)
            y = 100 - y;
        else
            y = -y + 100;
        var radios = document.getElementsByTagName('input');
        var r;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].type === 'radio' && radios[i].checked && radios[i].name === 'R') {
                r = radios[i].value;
            }
        }
        if (!isNaN(r)) {
            var k = r * 1.25;
            var l = document.img.width / 2;
            y = y * k / l;
            x = x * k / l;
        } else {
            alert("Set R first!");
            return;
        }
        window.location = "../lab8-1.0?XSelector=" + x + "&YSelector=" + y + "&RSelector=" + r;
    }
</script>
<style>
    .content {
        margin-top: 50px;
    }

    .inputs {
        width: 50%;
        float: left;
        padding-top: 50px;
    }

    .wrapper {
        margin: auto;
        width: 980px;
    }

    .area {
        width: 50%;
        float: right;
        padding: auto;
        text-align: center;
    }

    .area::before {
        content: "Область:";
        display: block;
    }

    header > p {
        font-family: sans-serif;
        width: 300px;
        padding: 5px;
        text-align: center;
        margin: auto;
        color: #004dff;

    }

    form {
        font-family: sans-serif;
    }

    header > p:first-child {
        font-size: 25px !important;
    }

    header > p:nth-child(2) {
        font-size: 20px;
    }

    header > p:nth-child(3) {
        font-size: 18px;
    }

    form > p > input, button, select {
        margin-left: 10px;
    }

    form > p > button {
        width: 30px;
        height: 30px;
    }
</style>
