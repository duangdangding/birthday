function requestFullScreen(element) {
    var element=document.documentElement;
    var requestMethod = element.requestFullScreen || //W3C
        element.webkitRequestFullScreen || //Chrome等
        element.mozRequestFullScreen || //FireFox
        element.msRequestFullScreen; //IE11
    if (requestMethod) {
        requestMethod.call(element);
    } else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
        var wscript = new ActiveXObject("WScript.Shell");
        if (wscript !== null) {
            wscript.SendKeys("{F11}");
        }
    }
}

/**
 * min - max 之间的随机数
 * @param min
 * @param max
 * @returns {*}
 */
function ranNUm(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
}
/* 鼠标拖尾小心心 */
document.onmousemove = function(event){
    var body = document.querySelector("body");
    var heart = document.createElement("span");
    heart.classList.add("hertspan");
    var x = event.offsetX;
    // var y = event.offsetY;
    var y = event.clientY;
    heart.style.left = x + "px";
    heart.style.top = y + "px";
    var size =Math.random()*25;
    heart.style.width = size + "px";
    heart.style.height = size + "px";
    body.appendChild(heart);
    setTimeout(function(){
        heart.remove();
    },2000);
}