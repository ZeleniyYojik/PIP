var timerId = setTimeout(function tick() {
    document.getElementById("clockForm:clockTimerBtn").click();
    timerId = setTimeout(tick, 6000);
}, 6000);