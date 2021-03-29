$('#randomname').click(function (event) {
	$.post("/getRandName",function (res){
		$("#userName").val(res.result);
	});
})
$('#login-button').click(function (event) {
	var userName = document.getElementById("userName").value.trim();
	if (userName != "") {
		let userLen = userName.length;
		if (userLen > 20) {
			userName = userName.substring(0,20);
		}
		// $.ajaxSettings.async = false;
		$.post("/findBannad",{words:userName},function (data) {
			if (data.result.bannad === 1) {
				layer.msg('请文明入场~');
				return false;
			}
			var xingzuo = $("#xingzuo").val();
			$.post("/login",{username:userName,userXz:xingzuo},function (res){
				event.preventDefault();
				$('.form').fadeOut(100);
				$('.wrapper').addClass('form-success');
				requestFullScreen();
				$('#welcomuu').fadeIn(500);
				$("#welcomuu").html("欢迎***" + userName + "***")
				setTimeout(function () {
					location.href = "/toindex";
				}, 2000);
			});
		})
		// $.ajaxSettings.async = true;
		$('.form').fadeIn(1500);
	}
});
$("#userName").keydown(function (event) {
	var code = event.keyCode;
	if (code == 13) {
		$('#login-button').click();
	}
})
