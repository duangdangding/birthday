$('#login-button').click(function (event) {
	var userName = document.getElementById("userName").value.trim();
	if (userName != "") {
		let userLen = userName.length;
		if (userLen > 20) {
			userName = userName.substring(0,20);
		}
		$.post("/login",{username:userName},function (res){
			event.preventDefault();
			$('.form').fadeOut(500);
			$('.wrapper').addClass('form-success');
			// requestFullScreen();
			$('#welcomuu').fadeIn(500);
			$("#welcomuu").html("欢迎***" + userName + "***")
			setTimeout(function () {
				location.href = "/toindex";
			}, 2000);
		});
	} 
});

