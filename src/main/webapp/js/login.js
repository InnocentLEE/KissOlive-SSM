$(function() {
	//轮播自动播放
	$('myCarousel').carousel({
		//自动3秒播放
		interval: 1000,
	});

	// 设置导航箭头垂直居中，
	$('.carousel-control').css('line-height',
		$('.carousel-inner img').height() + 'px');
	$(window).resize(
		function() {
			var $height = $('.carousel-inner img').eq(0).height() ||
				$('.carousel-inner img').eq(1).height() ||
				$('.carousel-inner img').eq(2).height();
			$('.carousel-control').css('line-height',
				$height + 'px');
		});
});
$("#li_login").click(function() {
	$('#loginModal').modal('show');
});

/*
 * 登录滑动验证码验证结果判断
 */
/*$(function() {
	var slider = new SliderUnlock("#slider", {
		successLabelTip: "验证通过"
	}, function() {
		//alert("验证成功,即将跳转至商城首页");
		//window.location.href="http://localhost:8080/KissOlive/page/user/user_home.jsp"
		//document.getElementByIdx("btn").disabled=true;
		$("#submit").attr("disabled", false);
	});
	slider.init();
})*/

$('#mpanel2').codeVerify({
	type: 1,
	width: '200px',
	height: '50px',
	fontSize: '30px',
	codeLength: 6,
	btnId: 'check-btn',
	ready: function() {},
	success: function() {

		$('#myModal').modal('hide');

	},
	error: function() {
		alert('验证码不匹配！');
	}
});
