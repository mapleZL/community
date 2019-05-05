<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>大袜网商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"></link>
		<link  rel="stylesheet" type="text/css" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'></link>
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/base.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/index.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/func.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/checkvalue.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.lazyload.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/slider.js"></script>
		
		<style type='text/css' rel="stylesheet">
		</style>
		<script type="text/javascript">
			var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
		</script>
	</head>
	<body class='wp1200'>
			<div class='wrapper'>
			<div class='container'>
				<ul class='collect lh'>
					<li class='fore1'>
						<a href='javascript:void(0)' onclick="AddFavorite(window.location,document.title);return false;">收藏</a>
					</li>
				</ul>
				<ul class='shortcut-right lh'>
					<#if Session.memberSession??>
				   		<#assign user = Session.memberSession.member>
				   </#if>
				   <#if user??>
				   		<li class='fore1' id='loginbar'>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=1' target="_blank" class='login'>${(user.name)!''}</a>&nbsp;&nbsp;
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/logout.html'  onclick="logout()" class='regist'>退出</a>
						</li>
						<li class='fore2 ld'>
							<span></span>
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html" target="_blank">我的订单</a>
						</li>
				   	 
				   	<#else>
						<li class='fore1' id='loginbar'>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/login.html' class='login'>你好，请登录</a>&nbsp;&nbsp;
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html' class='regist'>免费注册</a>
						</li>
				   </#if>
					
					<li class='fore2-1 ld ff-vip'stle='padding-left:12px;'>
						<span></span>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/index.html'>会员中心</a>
					</li>
					<li class='fore3 ld app-ff menu'>
						<span></span>
						<a href=''>手机商城</a>
					</li>
					<li class='fore4 ld menu' id='custom-server'>
						<span></span>
						客户服务
					</li>
					<li class='fore5 ld menu' id='site-nav'>
						<span></span>
							网站导航
						<!-- <b></b> -->
					</li>
					<li class='fore5 ld menu' id='site-nav'>
						<span></span>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/step1.html'>商家入驻</a>
						<!-- <b></b> -->
					</li>
				</ul>
			</div>
		</div>
		<div class='top-head'>
			<div class='container' id='HeardTop'>
				<div class='ld' id='logo-img'>
					<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" target="_blank">
						<img alt="" src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/dawawang.png" >
					</a>
				</div>
				<div class='seach-box index-saeach-box'>
					<div class='i-search ld'>
						<div class='form'>
							<form action="${(domainUrlUtil.EJS_URL_RESOURCES)!}/search.html" method="get">
								<input type='text' id='keyword' name="keyword" value="${(keyword)!''}" class='text' autocomplete="off" style='color:rgb(153,153,153);'>
								<input type='submit' value='搜索' class='button'>
							</form>
						</div>
					</div>
					<div id='Hotwords'>
						<strong>热门搜索：</strong>
						<div id="keywordIDs"></div>
					</div>
				</div>
				<div class='settleup'>
					<dl class>
						<!-- 如果没有商品这里显示0 -->
						<div class='addcart-goods-num'>0</div>
						<!--  -->
						<dt class='ld first-dt'>
							<s></s> <a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cart/detail.html'
								target="_blank">我的进货单</a> <b></b>
						</dt>
						<dd  id="priviewMycart">
						</dd>
					</dl>
				</div>
			</div>
		</div>
		<!--商品分类 -->
		<div id='NavSort'>
			<div class='container'>
				<div class='all-category'>
					<div class='dts'>
						<a href='###' target='_blank'>全部商品分类</a>
					</div>
					<div class='sec_attr'>
						<ul class='dd-inner '>
						<#if typeList??> 
							<#list typeList as type>
								<li class='odd' data-index='${type_index+1}'>
									<h3><a href="javascript:void(0)"><i></i>${type.name!""}</a></h3>
									<i>&gt;</i>
								</li>
							</#list> 
						</#if>
						</ul>
						<div class='dorpdown-layer'>
							<!-- 一级分类 -->
							<#list typeList as type>
							<div class='item-sub' id='index${type_index+1}'>
								<div class='subitems'>
									<!-- 二级分类  -->    
									<dl class='fore-dl'>
										<#list type.childList as type2> 
										<dd>
											<#if type_index == 0>
											<a href='0-1-0-0-0-0-${(type2.id)!}-0-0-0.html'><i>${type2.name!""}</i></a>
											<#else>
											<a href='0-1-0-0-0-0-0-0-0-0-${(type2.id)!}_${(type_index)!}.html'><i>${type2.name!""}</i></a>
											</#if>
										</dd>
										</#list>
									</dl>
									<!-- 二级分类  end -->
								</div>
							</div>
							</#list> 
							<!-- 一级分类 end -->
						</div>
					</div>
				</div>
				<ul class='site-menu'>
					<li class='fore1'>
						<li class='fore1'>
							<a href='#' target="_blank">首页</a>
						</li>
						<li class='fore1'>
							<a href='#' target="_blank">品牌袜</a>
						</li>
						<li class='fore1'>
							<a href='#' target="_blank">裸袜</a>
						</li>
				</ul>
			</div>
		</div>
 	
	
 
	<!-- end -->
	<!-- 轮播图 -->
	<div id="banner_tabs" class="i-flexslider">
        <ul class="slides">
        <#if bannerList?? && bannerList?size &gt; 0 >
			<#list bannerList as banner>
			<li>
                <a title="" target="_blank" href="${(banner.linkUrl)!''}">
                    <img width="1920" height="457" alt="" style="background: url(${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(banner.image)!''}) no-repeat center;">
                </a>
            </li>
			</#list>
		</#if>

        </ul>
        <ul class="flex-direction-nav">
            <li><a class="flex-prev" href="javascript:;">Previous</a></li>
            <li><a class="flex-next" href="javascript:;">Next</a></li>
        </ul>
        <ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
	        <#if bannerList?? && bannerList?size &gt; 0 >
				<#list bannerList as banner>
				<li><a>${(banner_index + 1)!1}</a></li>
				</#list>
			</#if>
        </ol>
        
    </div>
	<!-- end -->
	<!-- 今日推荐 -->
	<#if todayList?? && todayList?size &gt; 0 >
	<div class='container'>
		<div class='gusess-like'>
			<div id='guessyou'>
				<div class='guess-title'>
					<h2>今日推荐</h2>
				</div>
				<div class='guess-box'>
					<div class='guess-spacer'></div>
					<ul>
						<#list todayList as recommend>
							<li>
								<div class='p-img'>
									<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(recommend.product.id)!0}.html' target='_blank'>
										<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(recommend.product.masterImg)!""}' title='${(recommend.product.name1)!""}'>
									</a>
								</div>
								<div class='p-info'>
									<div class='p-name'>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(recommend.product.id)!0}.html'
											 target='_blank' title='${(recommend.product.name1)!""}'>${(recommend.product.name1)!""}</a>
									</div>
									<div class='p-price'>
										<i>¥</i>
										${(recommend.product.mallPcPrice)!"0.00"}
									</div>
								</div>
							</li>
						</#list>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</#if>
	<!-- end -->
	<!-- 热销商品 -->
	<#if hotList?? && hotList?size &gt; 0 >
	<div class='container'>
		<div class='gusess-like'>
			<div id='guessyou'>
				<div class='guess-title'>
					<h2>热销商品</h2>
				</div>
				<div class='guess-box'>
					<div class='guess-spacer'></div>
					<ul>
						<#list hotList as recommend>
							<li>
								<div class='p-img'>
									<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(recommend.product.id)!0}.html' target='_blank'>
										<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(recommend.product.masterImg)!""}' title='${(recommend.product.name1)!""}'>
									</a>
								</div>
								<div class='p-info'>
									<div class='p-name'>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(recommend.product.id)!0}.html'
											 target='_blank' title='${(recommend.product.name1)!""}'>${(recommend.product.name1)!""}</a>
									</div>
									<div class='p-price'>
										<i>¥</i>
										${(recommend.product.mallPcPrice)!"0.00"}
									</div>
								</div>
							</li>
						</#list>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</#if>
	<!-- end -->

	<!-- 楼层 -->
	<!-- 广告 -->
	<#if floorList?? && floorList?size &gt; 0>
	<#list floorList as floor>
		<#if floor?? && floor.advImage?? && floor.advImage != "">
			<div class='container'>
				<div class='floor-banner'>
				  <a href='${(floor.advLinkUrl)!"#"}' target='_blank'>
						<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(floor.advImage)!""}' width='1210' height='100'/>
				  </a>
				</div>
			</div>
			<br />
		</#if>
		<div class='container'>
			<div class='lazy-fn'>
				<div class='floor-box'>
					<div class='lazy-clothes'>
						<h2>
							<span class='floor-title'>${floor_index+1}F</span>
							${floor.name!""}
						</h2>
						<ul class='tab'>
							<#if floor.classList?? && floor.classList?size &gt; 0>
								<#list floor.classList as fc>
									<li class='tab-items <#if fc_index==0>tab-selected</#if>'>
										<a href='javascript:void(0);'>${fc.name!""}</a>
									</li>
								</#list>
							</#if>
						</ul>
					</div>
					<div class='lazy-mc'>
						<div class='lazy-left'>
							<div class='lazy-inner'>
								<div class='left-box'>
									<a href='${(floor.masterLinkUrl)!""}' target='blank'>
										<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(floor.masterImage)!""}" width="329" height="400"/>
									</a>
								</div>
								<div class='brand-logo'>
								   <div class="brand-logo-in">
									<ul>
										<#if floor.patchList?? && floor.patchList?size &gt; 0>
										<#list floor.patchList as patch>
											<li>
												<a href='${(patch.linkUrl)!""}' target="_blank">
													${(patch.title)!""}
												</a>
											</li>
										</#list>
										</#if>
									</ul>
									</div>
								</div>
							</div>
						</div>
						
						<#if floor.classList?? && floor.classList?size &gt; 0>
						<#list floor.classList as fc>
							<div class='lazy-main <#if fc_index &gt; 0>hide</#if>'>
								<ul class='p-list'>
									<#if fc.dataList?? && fc.dataList?size &gt; 0>
									<#list fc.dataList as data >
										<#if data.dataType == 1 >
											<li>
												<div class='p-img'>
													<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(data.product.id)!0}.html' 
														target='_blank' title='${(data.product.name1)!""}'>
														<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(data.product.masterImg)!""}' width="185" height="185">
													</a>
												</div>
												<div class='p-name'>
													<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(data.product.id)!0}.html' 
														target='_blank'  title='${(data.product.name1)!""}'>
														${(data.product.name1)!""}
													</a>
												</div>
												<div class='p-price'>
													<span>￥</span><span>${(data.product.mallPcPrice)!''}</span>
												</div>
											</li>
										<#elseif data.dataType == 2 >
											<li>
												<div class='add-p-img'>
													<a href='${(data.linkUrl)!""}' target='_blank' title='${(data.title)!""}'>
														<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(data.image)!""}' >
													</a>
												</div>
											</li>
										</#if>
									</#list>
									</#if>
								</ul>
							</div>
						</#list>
						</#if>
					</div>
				</div>
				<!--  end-->
			</div>
		</div>
		<br />
		<br />
	</#list>
	</#if>
	<!-- end -->

	<!-- footer -->
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/index.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js"></script>
		<script>
			$(function(){
				// 初始化登录状态
				loginInfoInit();
				// 刷新进货单
				refreshMycart();
			});
			
			// 异步加载用户登录信息
			function loginInfoInit() {
				$.ajax({
					type:"POST",
					url:domain+"/getloginuser.html",
					success:function(data){
						if(data.success){
							if (data.data != null && data.data.name != null) {
								// 移除未登录时显示的链接
								$("#loginbar").remove();
								// 构造登录信息
								var loginInfoHtml = "";
								loginInfoHtml += ("<li class='fore1' id='loginbar'>");
								loginInfoHtml += ("	<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=1' target='_blank' class='login'>" + data.data.name + "</a>&nbsp;&nbsp;");
								loginInfoHtml += ("	<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/logout.html'  onclick='logout()' class='regist'>退出</a>");
								loginInfoHtml += ("</li>");
								loginInfoHtml += ("<li class='fore2 ld'>");
								loginInfoHtml += ("	<span></span>");
								loginInfoHtml += ("	<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html' target='_blank'>我的订单</a>");
								loginInfoHtml += ("</li>");
								// 显示登录信息
								$(".shortcut-right").prepend(loginInfoHtml);
							} else {
							}
						}else{
						}
					},
					error:function(){
					}
				});
			}
		</script>
		<#include "/front/commons/_endbig.ftl" />
	</body>
</html>