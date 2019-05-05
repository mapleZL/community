//我的商城
$(".head-user-menu").mouseover(function() {
	$(".head-user-menu dd").css("display", "block");
	$(".head-user-menu dt").css("border-bottom", '0');
	$(".head-user-menu dt b").css({
		"top" : "7px",
		"border-style" : "dashed dashed solid",
		"border-color" : "transparent transparent #ccc"
	});
}).mouseout(function() {
	$(".head-user-menu dd").css("display", "none");
	$(".head-user-menu dt").css("border-bottom", '1px solid #efefef');
	$(".head-user-menu dt b").css({
		"top" : "9px",
		"border-style" : "dashed dashed solid",
		"border-color" : " #CCC transparent transparent"
	});
});
// 

// 首页商品分类
var obj = $(".odd:eq(0)");
$(".dd-inner .odd").mouseover(
		function() {
			$(".odd").eq($(this).index()).addClass("hover").siblings()
					.removeClass("hover");
			var obj = $(this);
			var index = $(this).data('index');
			$(".item-sub").css("display", "none");
			$("#index" + index).css("display", "block");
			$(this).parent().siblings().css("display", "block");

			$(".item-sub").hover(
					function() {
						$(this).css("display", "block").parent().css("display",
								"block");
					},
					function() {
						$(this).css("display", "none").parent().css("display",
								"none");
						$(".odd").removeClass("hover");
					});
		}).mouseout(function() {
	$(".odd").eq($(this).index()).removeClass("hover");
	$(this).parent().siblings().css("display", "none");
	$(".item-sub").hover(function() {
		$(".odd").eq($(this).index()).addClass("hover");
	}, function() {
		$(".odd").removeClass("hover")
	});
});


$(function() {
   //首页轮播
	var bannerSlider = new Slider($('#banner_tabs'), {
        time: 4000,
        delay: 400,
        event: 'hover',
        auto: true,
        mode: 'fade',
        controller: $('#bannerCtrl'),
        activeControllerCls: 'active'
    });
    $('#banner_tabs .flex-prev').click(function() {
        bannerSlider.prev();
    });
    $('#banner_tabs .flex-next').click(function() {
        bannerSlider.next();
    });
    
	// setTimeout("takeCount()", 1000);
	// 首页Tab标签卡滑门切换
	$(".tabs-nav > li > h3").bind(
			'mouseover',
			(function(e) {
				if (e.target == this) {
					var tabs = $(this).parent().parent().children("li");
					var panels = $(this).parent().parent().parent().children(
							".tabs-panel");
					var index = $.inArray(this, $(this).parent().parent().find(
							"h3"));
					if (panels.eq(index)[0]) {
						tabs.removeClass("tabs-selected").eq(index).addClass(
								"tabs-selected");
						panels.addClass("tabs-hide").eq(index).removeClass(
								"tabs-hide");
					}
				}
			}));
});

// 首页楼层标签的切换
var interval;
$(document).ready(function() {
	var $tab_li = $('.tab li');
	$tab_li.hover(function() {
		$(this).addClass('tab-selected').siblings().removeClass(
				'tab-selected');
		var index = $tab_li.index(this);
		$('.lazy-mc .lazy-main').eq(index).removeClass("hide")
				.siblings(".lazy-main").addClass("hide");
	});
	
	//interval = setInterval('change_image()',2000)

				
/*	$(".p-img img").lazyload({
		effect : "fadeIn",
		failurelimit : 10
	}); */
});


