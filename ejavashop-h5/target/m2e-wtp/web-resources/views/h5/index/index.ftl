<#include "/h5/commons/_head.ftl" />
<style>
.cover {
position:fixed; top: 0px; right:0px; 
bottom:0px;filter: alpha(opacity=60); 
background-color: #777;
z-index: 1002; left: 0px; 
opacity:0.5; 
-moz-opacity:0.5;
}
.tzm_span{
			width:38px;
			height:19px;
			/* border:1px solid white; */
			color: black;
			text-decoration: line-through;
			font-size: 12px;
			}
</style>
<body class="bgf2">
    <!-- 搜索框 -->
    <#--
	<div class="fixedtop-sh" style="display: none;">
    			<a class="logo" href="javascript:;">
    			<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon/logo_h5.png" width="50">
    			<p>大袜网</p>
    			</a>
				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/search-index.html">	
					<div class="search">					
						<div class="button"></div>
						<div class="clear"></div>
		    			<div class="search_in">
		    				<p>搜索您想要的商品</p>
		    				<#if memberSession?? && memberSession.member??>
				   	  	     <div class="user" id="loginTopDiv">
				   	  	     	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/logout.html" >
				   	  	     	退出
				   	  	     	</a>
				   	  	     </div>
				   	  	     <#else>
					   	  	     <div class="user" id="loginTopDiv">
					   	  	     	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/login.html" >
					   	  	     	登录
					   	  	     	</a>
					   	  	     </div>
				   	  	     </#if>
		    			</div>
					</div>
				</a>
		</div>
		-->
		<!-- 新的搜索框 add by tzm -->
		<header id="index_header">
			<div class="index_top">
				<a href="#" class="index_logo" style="margin-top:12px;">
					<img alt="" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon/logo-solgan.png">
				</a>	
				<form class="search_form">
					<div class="index_serach">
						<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/search-index.html">
							<div class="index_serach_box">
								<input class="search_txt"  type="text" placeholder="搜索您想要的商品"/>
								<span class="icon_search">
								</span>
							</div>
						</a>
					</div>
				</form>
				<div class="index-search-login" style="margin-top:12px;">
					<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/login.html" >
	   	  	     		登录
	   	  	     	</a>
				</div>
			</div>
		</header>
	<!-- lunbo -->
	<div style="margin-top:40px;" id="banner_lunbotu">
		<#if banners?? && banners?size &gt; 0 >
		<div class="swiper-container" id="i-swiper-container">
		    <div class="swiper-wrapper">
		    	<#list banners as banner>
		    	<div class="swiper-slide">
		    		<a href="${(banner.linkUrl)!''}" class="block">
		    			<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${(banner.image)!''}">
		    		</a>
		    	</div>
		        </#list>
		    </div>
		    <div class="swiper-pagination"></div>
		</div>
		</#if>
	</div>

	<!-- 导航菜单 -->
	<div class="i-menu-box padt_b10 mar-bt" style="border: 0px solid red;">
		<div>
				<ul class="sub_nav clearfix">
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-xppw.html">品牌袜</a></li>
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-8-0-0-0.html">裸袜</a></li>
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/eosearch.html">快速下单</a></li>
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-xppw.html">热销商品</a></li>
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-999M.html">新品上线</a></li>
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-158_5.html">丝袜专区</a></li>
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-155_6.html">羊毛袜</a></li>
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-12-0-0-0.html">功能袜</a></li>
		</ul>
		</div>
	</div>

	 <!-- 列表 -->
	<div  style="padding:0 5px;" >
		<#if floors?? && floors?size &gt; 0 >
		<#list floors as floor>
			<section class="product pro_tmh">
			<div class="pro_tit pro_tmh_tit">
				<h2>${(floor.name)!''}</h2>
				<a href="${(floor.link)!''}" class="tips">更多</a>
				<#--
				<#if floor.name=="袜冰价">
				<a href="0-1-0-0-0-0-0-0-0-0-wamm.html" class="tips">更多</a>
				<#elseif floor.name=="新品推荐">
				<a href="0-1-0-0-0-0-0-0-0-0-999M.html" class="tips">更多</a>
				<#elseif floor.name=="羊毛袜大促">
				<a href="0-1-0-0-0-0-0-0-0-155_6.html" class="tips">更多</a>
				<#elseif floor.name=="5IX/CCUMI">
				<a href="0-1-0-0-0-0-0-0-0-0-wadrt.html" class="tips">更多</a>
				<#elseif floor.name=="热销商品">
				<a href="0-1-0-0-0-0-0-0-0-0-xppw.html" class="tips">更多</a>
				<#elseif floor.name=="红包专区">
				<a href="0-1-0-0-0-0-0-0-0-0-wadrt.html" class="tips">更多</a>
				</#if>
				-->
			</div>
		</section>

				
				<ul class="i-list-ul clear">
					<#if floor.datas?? && floor.datas?size &gt; 0 >
					<#list floor.datas as data >
					<#--
						<#if data_index &lt; 4 >
					-->
						<!-- 如果是商品 -->
						<#if data.dataType?? && data.dataType == 1 >
							<li>
								<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/product/${(data.product.id)!0}.html" class="block">
									<div class="i-list-img"><img  data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${(data.product.masterImg)!''}" width="189" height="189"></div>
									<div class="product_name">${(data.product.name1)!""}</div>
								</a>
								<div class="i-list-price">
									￥${(data.product.malMobilePrice)?string("0.00")!"0.00"}
									<#if data.product.marketPrice ?? && data.product.marketPrice != data.product.malMobilePrice>
											<span class="tzm_span">
												<i>¥</i>
												${(data.product.marketPrice)!"0.00"}
											</span>
											<#else>
											<span class="tzm_span"></span>
									</#if>
								</div>
							</li>
						<#elseif data.dataType?? && data.dataType == 2>
							<li>
								<a href="${(data.linkUrl)!''}" class="block">
									<div class="i-list-img"><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${(data.image)!''}" width="189" height="189"></div>
									<div class="product_name">${(data.title)!""}</div>
								</a>
							</li>
						</#if>
						<#--</#if>-->
					</#list>
					</#if>
				</ul>
				
			</div>
		</#list>
		</#if>
		<!-- <div class="text-center font14">点击继续加载 <i class="fa fa-angle-double-down"></i></div> -->
	</div>
	
<!-- 弹出图片 -->
	<div style="display:none; position:absolute;border: 0px solid red;top: 20%;left: 0;z-index: 1003;" id="popforActive">
		<a href="" class="activity_link">
		    <img src="" class="activity_img"/>
		</a>
	    <span style="position:absolute;right:0px;top:0px;border: 0px solid blue;width: 60px;height: 60px;" onclick="javascript:$('#popforActive').css('display','none');$('body').css('overflow','auto');$('body').css('position','static');$('#cover').css('display','none');"></span>
	</div>
	<div id="cover" class="cover" style="display:none;"></div>
<div style="color: #686868;height: 50px;line-height: 50px;text-align: center;">
	已经到底啦,更多正品好袜子,就在大袜网 ~
</div>
<!-- 浮动按钮：返回顶部 start-->
<!--<a id="scroll-qq" href="mqqwpa://im/chat?chat_type=wpa&uin=2853812862&version=1&src_type=web&web_src=dawawang.com">&nbsp;</a>-->
<a id="scroll-phone" href="tel:4008456002">&nbsp;</a>
<div id="scroll-top" style="display: none;"></div>
<!-- end -->
<#include "/h5/commons/_footer.ftl" />
<#include "/h5/commons/_statistic.ftl" />
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/swiper/swiper-3.2.7.min.js"></script>
<script>
	$(document).ready(function () {
		var mySwiper = new Swiper ('.swiper-container', {
			autoplay: 4000,
			loop: true,
			// 如果需要分页器
			pagination: '.swiper-pagination',
		});
	  
		var pageState = sessionStorage.getItem('pageState');
		if(pageState != 1){
			$.ajax({
				url:domain+"/popup.html",
				success:function(data){
					if(data.success) {
						sessionStorage.setItem('pageState', 1);
						if (data.data == null) return;
						var turl = data.data.linkUrl;
						var timg = "${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}" + data.data.image;
						$(".activity_link").attr('href', turl); 
						$(".activity_img").attr('src', timg); 
						$("#popforActive").show();
						$(".cover").show();
					}
				},
				error:function(){
				}
			});
		}
	  
		//初始化登录状态
		loginInfoInit();
		
        $("img").lazyload({
            effect:'fadeIn'
        });
		
	});
	
	//异步加载用户登录信息
	function loginInfoInit() {
		$.ajax({
			type:"POST",
			url:domain+"/getloginuser.html",
			success:function(data){
				if(data.success){
					if (data.data != null && data.data.name != null) {
						// 移除未登录时显示的链接
						$("#loginTopDiv").empty();
						// 构造顶部登录信息
						var loginInfoTopHtml = "";
						loginInfoTopHtml += ("<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!''}/logout.html' class='block'>退出</a>");
						$(".index-search-login").html(loginInfoTopHtml);
					} else {
					}
				}else{
				}
			},
			error:function(){
			}
		});
	}

	//返回头部
	$(window).scroll(function () {
		var oScroll = $("#scroll-top").length;
		var $scrollTop = parseInt($(document).scrollTop());
		if (oScroll === 1) {
			if ($scrollTop >= 10) {
				$("#scroll-top").fadeIn(300);
			} else {
				$("#scroll-top").fadeOut(300);
			}
			$("#scroll-top, .go-top").click(function () {
				$('html,body').stop().animate({ scrollTop: '1px' }, 500);
			});
		}
	});
</script>
</body>
</html>