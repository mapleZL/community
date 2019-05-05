
$(function(){
	$("[lazyLoadSrc]").YdxLazyLoad({
		onShow: function () {
			$(this).parent().next().hide()
		}
	});
	
	sessionStorage.removeItem("count");
	var pageState = sessionStorage.getItem('pageState');
	if (pageState == null) {
		$.ajax({
			url:domain + "/popup.html?type=1",
			success:function(data) {
				sessionStorage.setItem('pageState', 1);
				if (data.data == null) return;
				var turl = data.data.linkUrl;
				var timg = domainImg + "/" + data.data.image;
				$(".popforActive a").attr('href', turl); 
				$(".popforActive img").attr('src', timg); 
				$(".popforActive").show();
				$(".cover").show();
				$(".popforActive span").click(function() {
					$(this).parent().hide();
					$(".cover").hide();	
				});
			},
			error:function() {
			}
		});
	}
  
	//初始化登录状态
	loginInfoInit();
	
	$(".go-top").click(function () {
		$('html,body').stop().animate({ scrollTop: '1px' }, 500);
	});
});