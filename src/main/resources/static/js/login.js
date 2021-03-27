$('#login-button').click(function (event) {
	var userName = document.getElementById("userName").value.trim();
	if (userName != "") {
		let userLen = userName.length;
		if (userLen > 20) {
			userName = userName.substring(0,20);
		}
		$.post("/findBannad",{words:userName},function (data) {
			if (data.result.bannad === 1) {
				layer.ready(function(){
					layer.msg('请文明入场');
				});
				return false;
			}
		})
		$.post("/login",{username:userName},function (res){
			console.log('登录返回结果~'+JSON.stringify(res));
			event.preventDefault();
			$('.form').fadeOut(500);
			$('.wrapper').addClass('form-success');
			requestFullScreen();
			$('#welcomuu').fadeIn(500);
			$("#welcomuu").html("欢迎***" + userName + "***")
			setTimeout(function () {
				location.href = "/toindex";
			}, 2000);
		});
		$('.form').fadeIn(1500);
	} 
});
$("#userName").keydown(function (event) {
	var code = event.keyCode;
	if (code == 13) {
		$('#login-button').click();
	}
})
