<#include "/h5/commons/_head.ftl" />

<link rel="stylesheet"
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/SecKill.css">

<body>
	<!-- S 头部 -->
	<header id="header" class="headerbox">
		<div class="flex flex-align-center head-bar">
			<div class="flex-1 text-left">
				<span class="fa fa-angle-left"></span>
			</div>
			<div class="flex-2 text-center">掌上秒杀</div>
			<div class="flex-1 text-right" id="fa-bars">
				
			</div>
		</div>
		<#include "/h5/commons/_hidden_menu.ftl" />
	</header>
	<!-- E 头部 -->

	<!-- S 秒杀时间段导航 -->
	<nav class="topfixed">
		<div class="category-time">
			<ul class="product-ul">
				<#if allstage??>
   				<#list allstage as actFlashSaleStages>
				<li <#if actFlashSaleStageNow??&&actFlashSaleStages.id==actFlashSaleStageNow.id>class="curent"</#if>>
					<p class="tm">${(actFlashSaleStages.startTime)!}:00</p>
					<p>
						<#if actFlashSaleStageNow??&&actFlashSaleStages.id==actFlashSaleStageNow.id>
							秒杀中
						<#else>
							<#assign isover = false>
							<#list stageListOver as slover>
								<#if slover.id == actFlashSaleStages.id>
									<#assign isover = true>
								</#if>
							</#list>
							<#if isover>
								已结束
							<#else>
								即将开场
							</#if>
						</#if>
					</p>
				</li>
				</#list>
				</#if>
			</ul>
		</div>
	</nav>
	<!-- E 秒杀时间段导航 -->

	<!-- S 主体 -->
	<main class="sk-category" id="wrapper"> <!-- one -->
	<#if allstage??>
   	<#list allstage as actFlashSaleStages>
   	
   	<!-- 已结束声明 -->
	<#assign stageover = false>
	<#assign unbegin = false>
   	<#list stageListOver as slover>
		<#if slover.id == actFlashSaleStages.id>
			<#assign stageover = true>
		<#else>
			<#if actFlashSaleStageNow.id!=actFlashSaleStages.id>
				<#assign unbegin = true>
			</#if>
		</#if>
	</#list>
	
	<div class="section <#if actFlashSaleStageNow?? && actFlashSaleStageNow.id==actFlashSaleStages.id>active<#elseif !actFlashSaleStageNow??&&actFlashSaleStages_index==0>active</#if>">
		<!-- S 广告图 -->
		<#if actFlashSale??>
		<div class="ad-img">
			<a href="javascript:;">
				 <img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(actFlashSale.pcImage)!''}" alt="" />   
			</a>
		</div>
		</#if>
		<!-- E 广告图 -->

		<!-- S 倒计时 -->
		<div class="timer">
			<span>秒杀中 先下单先得哦</span>
			<div class="time-item">
				距结束 
				<strong class="hour_show">00</strong> : 
				<strong class="minute_show">00</strong> : 
				<strong class="second_show">00</strong>
			</div>
		</div>
		<!-- E 倒计时 -->

		<!-- S 商品 -->
		<div class="item-producct">
			<ul class="item-ul">
				<#if actFlashSaleStages.productList??>
	        	<#list actFlashSaleStages.productList as saleProduct>
	        	<#if saleProduct.product??>
				<li class="item-list">
					<a href="<#if stageover || unbegin>javascript:;<#else>${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1</#if>" class="list-a">
						<div class="list-pic">
							<div class="a-img">
								<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(saleProduct.product.masterImg)!}"
									alt="">
							</div>
							<em class="supor"></em>
						</div>
						<p class="list-tit">${(saleProduct.product.name1)!""}</p>
						<p class="list-price">
							<i class="doller">￥</i>${(saleProduct.price)!""}
						</p>
						<div class="skill-price">
							<p class="g-price-odd">
								<del>￥${(saleProduct.product.marketPrice)!""}</del>
							</p>
							<div class="skill-lod">
								<span class="sale-count">${(saleProduct.actualSales)!""}件已秒杀完</span>
							</div>
						</div>
					</a> 
					<a href="<#if stageover || unbegin>javascript:;<#else>${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(saleProduct.product.id)!0}.html?type=1</#if>" 
						class="btn-slid <#if stageover || unbegin>sk-disalbed<#else>bg-red</#if>"><span>
						<#if stageover>已结束<#elseif unbegin>敬请期待<#else>去秒杀</#if>
						</span>
					</a>
					<#if stageover>
					<div class="seconds-kill-over"><p>已结束</p></div>
					<#elseif unbegin>
					<div class="seconds-kill-over"><p>敬请期待</p></div>
					</#if>
				</li>
        		</#if>
				</#list>
        		</#if>
			</ul>
		</div>
		<!-- E 商品 -->
	</div>
	</#list>
	<#else>
		<div class="noactive">${errorinfo!'没有活动'}</div>
	</#if>
	</main>
	<!-- E 主体 -->

	<script
		src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
	<script
		src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/index.js"></script>
	<#if allstage??>
	<script
		src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/SecKill.js"></script>
	<script>
	var myScroll;
		$(function() {

			// 窗体滚动让nav定位
			$(window).scroll(function() {
				var SBTop = ($('body').scrollTop());
				if (SBTop > $('.headerbox').height()) {
					$('.topfixed').css({
						'position' : 'fixed'
					});
					$('.sk-category').css({
						'padding-top' : '50px'
					});
				} else {
					$('.topfixed').css({
						'position' : 'static'
					});
					$('.sk-category').css({
						'padding-top' : '0px'
					});
				}
			})

			//鼠标点击该变字体样式 
			$('.product-ul > li').click(function() {
				$(this).css({
					'color' : '#F23030'
				}).siblings().css({
					'color' : '#333'
				});
				$(this).find('.tm').css({
					'font-size' : '23px'
				}).parent().siblings().find('.tm').css({
					'font-size' : '17px',
					'font-weight' : 'bold'
				});
				var liIndex = $(this).index();
				switchli(liIndex);
			});

			// 切换商品页面 tab切换
			// curentDivIndex 当前li的索引
			function switchli(curentDivIndex) {
				$('.sk-category > .section').eq(curentDivIndex).show()
						.addClass('active').siblings().hide().removeClass(
								'active');
			}

			// 倒计时
			var intDiff = parseInt('${(countTime)!0}');//倒计时总秒数量
			function timer(intDiff) {
				window.setInterval(function() {
					var day = 0, hour = 0, minute = 0, second = 0;//时间默认值       
					if (intDiff > 0) {
						day = Math.floor(intDiff / (60 * 60 * 24));
						hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
						minute = Math.floor(intDiff / 60) - (day * 24 * 60)
								- (hour * 60);
						second = Math.floor(intDiff) - (day * 24 * 60 * 60)
								- (hour * 60 * 60) - (minute * 60);
					}
					if (hour <= 9)
						hour = '0' + hour;
					if (minute <= 9)
						minute = '0' + minute;
					if (second <= 9)
						second = '0' + second;
					// $('.day_show').html(day+"天");
					$('.hour_show').html('<s id="h"></s>' + hour);
					$('.minute_show').html('<s></s>' + minute);
					$('.second_show').html('<s></s>' + second);
					intDiff--;
				}, 1000);
			}

			timer(intDiff);
		})
	</script>
	</#if>
</body>
</html>