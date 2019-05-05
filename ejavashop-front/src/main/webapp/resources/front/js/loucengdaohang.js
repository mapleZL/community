$(function() {
	var listHtml = "";
	$(".___floor").each(function(i) {
		listHtml += '<li class="current">' + (i + 1) + 'F<div>' + $(this).data("title") + '</div></li>';
	});
	listHtml += '<li class="current">返回顶部<div>返回顶部</div></li>';
	
	$(".list").html(listHtml);
	$(window).scroll(function(){
		// 获得窗口滚动上去的距离
		var ling = $(document).scrollTop();
	});

	$(".list li").click(function() {
		var clickI = $(this).index();
		if ((clickI + 1) == $(".list li").length) {
			$('html, body').animate({'scrollTop': 0}, 500);
			return;
		}
		$(".___floor").each(function(i) {
			if (clickI == i) {
				$('.list li').eq(i).addClass('current').siblings('li').removeClass('current');
				$('html,body').stop().animate({
					'scrollTop':$(this).offset().top
				}, 500,function(){
					$(window).scroll(testList);
				});
				return;
			}
		});
	});
	$(window).scroll(testList);
	
	var $aLi=$('.guess-box ul li');
	$aLi.hover(function() {
		$(this).stop().animate({
			'top': -10
		}, 300);
	}, function() {
		$(this).stop().animate({
			'top': 0
		}, 300);
	});
	
	var $aLi = $('#guessyou .p-img img');
	$aLi.hover(function() {
		$(this).stop().fadeTo('normal', 0.7);
		$(this).siblings('li').stop().fadeTo('normal', 1);
	}, function() {
		$aLi.stop().fadeTo('normal', 1);
	});
});


//检测楼层功能封装
function testList(event) {
	var sT = $(this).scrollTop();
	var isShow = false; 
	$(".___floor").each(function(i) {
		var topSize = $(this).offset().top;
		if(sT >= (topSize - 100)){
			$('.list').show();
			isShow = true;
			$('.list li').eq(i).addClass('current').siblings('li').removeClass('current');
		}
	});
	if (!isShow) {
		$('.list').hide();
		isShow = false;
	}
}