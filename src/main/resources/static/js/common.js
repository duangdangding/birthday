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