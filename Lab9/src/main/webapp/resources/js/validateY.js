function validateY() {
    var y_val = document.getElementById("mainForm:Y").value;
    y_val = y_val.replace(",", ".");
    var y_valid = !((y_val == "") || !(!isNaN(parseFloat(y_val)) && isFinite(y_val)) || (y_val > 3) || (y_val < -5));
    if (y_valid) {
        document.getElementById("mainForm:submit").disabled = false;
        document.getElementById("mainForm:Y").style.borderColor = "#77d5f7";
        document.getElementById("mainForm:submit").style.background = "#0078ae";
    } else {
        document.getElementById("mainForm:submit").disabled = true;
        document.getElementById("mainForm:submit").style.background = "#EEEEEE";
        document.getElementById("mainForm:Y").style.borderColor = "red";
    }
}