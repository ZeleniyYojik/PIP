$(document).ready(function () {
    var $chb = $('input.chb');
    $chb.click(function () {
        $chb.prop('checked', false);
        $(this).prop('checked', true);
    });
});

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

$(document).ready(function () {
    document.getElementById("graph").onclick = function (e) {
        var x = e.offsetX == undefined ? e.layerX : e.offsetX - 100;
        var y = e.offsetY == undefined ? e.layerY : e.offsetY;
        if (y <= 100)
            y = 100 - y;
        else
            y = -y + 100;
        var rad = parseFloat(document.getElementById("hiddenR").value);

        if (!isNaN(rad)) {
            var k = rad * 1.25;
            var l = document.img.width / 2;
            y = y * k / l;
            x = x * k / l;
        } else {
            alert("Set R first!");
            return;
        }
        window.location = "../lab8/main?XSelector=" + x + "&YSelector=" + y + "&RSelector=" + rad;
    }
});