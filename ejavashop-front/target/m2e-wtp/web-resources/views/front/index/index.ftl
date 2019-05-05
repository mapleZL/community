<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>大袜网-做袜子生意 就上大袜网</title>
		<meta name="Keywords" content="大袜网,袜子品牌,袜子厂家,袜子批发,袜子加盟">
		<meta name="Description" content="大袜网是全品类袜子采购、袜子批发、袜子加工的一站式服务平台，做袜子生意，就上大袜网。">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"></link>
		<meta name="baidu-site-verification" content="rnyMiv2JC1">
		
		<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		
		<link  rel="stylesheet" type="text/css" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css?version=v20080101'/>
		<!-- 采用CDN方式引入字体图标，优点:访问速度快 -->
		<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/font-awesome/css/font-awesome.min.css?version=v20080101">		
        <link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/fwc-css/fwc_huodong.css?version=v20080101">
		
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/tzm_base.css?version=v20080101">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/index.css?version=v200801013">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/jquery.alerts.css?version=v20080101"/>
        <link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc_qqku.css?version=v20080101">
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js?version=v20080101'></script>
        <script type="text/javascript">
			var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
			var msgcode;
			var intervalId;
			if(/AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE|HTC/.test(navigator.userAgent))){
			if(window.location.href.indexOf("?mobile")<0){
				try{
					var url = location.href;
				    var sp = url.split("?");
				    var mobileUrl = "${(domainUrlUtil.EJS_H5_URL)!}"+ sp[1];
				    var mobileUrl1 = "${(domainUrlUtil.EJS_H5_URL)!}";
					if(/iPad|Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){
						if (sp.length > 1){
							window.location = mobileUrl;
						}else{
							window.location = mobileUrl1;
						}
					}else{
						if (sp.length > 1){
							window.location = mobileUrl;
						}else{
							window.location = mobileUrl1;
						}
					}
				}catch(e){}
			}
		}
			
		var sUserAgent = navigator.userAgent;
		if (sUserAgent.indexOf('Android') > -1 
				|| sUserAgent.indexOf('iPhone') > -1 
				|| sUserAgent.indexOf('iPad') > -1 
				|| sUserAgent.indexOf('iPod') > -1 
				|| sUserAgent.indexOf('Symbian') > -1) {
            	location.href = 'http://m.dawawang.com';           
		}   
		</script>
	</head>
	<body class='wp1200'>
			<div class='wrapper'style="background:#F5f5f5;box-shadow:0 0 1px 0 #eeeee;color:#999999;">
			<div class='container'>
				<ul class='collect lh'>
					<li class='fore1'>
					<!-- 
						<a href='javascript:void(0)' onclick="AddFavorite(window.location,document.title);return false;">收藏</a>
						 -->
						 <span style="border: 0px;color: #727272;">
						 欢迎光临大袜网
						 </span>
						 
						
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
						<li class='fore2 ld' id="dingdanspan">
							<span></span>
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html">我的订单</a>
						</li>
				   	 
				   	<#else>
						<li class='fore3 ld app-ff menu loginhide' style="width: 86px;">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/login.html' class='login'>你好,请登录</a>
						</li>
						<li class="fore3 ld app-ff menu registhide" style="width: 72px;">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html' class='regist'>立即注册</a>
							<span></span>
						</li>
						
				   </#if>
					<!-- 
					<li class='fore2-1 ld ff-vip'stle='padding-left:12px;'>
						<span></span>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/index.html'><i class="fa fa-user fa-fw"></i>会员中心</a>
					</li>
					<li class='fore3 ld app-ff menu'>
						<span></span>
						<a href=''>手机商城</a>
					</li>
					 -->
					<li class='fore4 ld menu' id='custom-server'>
						<span></span>
						<a href='javascript:void(0);' id="tzm_star_listener"><i class="fa fa-star fa-lg"></i>我的收藏</a>
					</li>
					<li class='fore5 ld menu' id='site-nav'>
						<span></span>
						<a href='javascript:void(0);' id="tzm_qrcode_listener"><i class="fa fa-qrcode fa-lg"></i>关注我们</a>
						<!-- <b></b> -->
						<img style="position: absolute;top: 65%;left:-65%;display: none;" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/wccode_open.png" alt="关注我们" />
					</li>
					<li class='fore5 ld menu' id='site-nav'>
						<span></span>
						<!-- <a href='${(R.EJS_URL_RESOURCES)!}/store/step1.html'>商家入驻</a> ${(R.EJS_URL_RESOURCES)!}/register.html-->
						<a href='javascript:void(0);' id="tzm_phone_listener"><i class="fa fa-phone fa-lg"></i>联系客服</a>
						<!-- <b></b> -->
						<img style="position: absolute;top: 65%;left:-50%;display: none;" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/tel_open.png" alt="关注我们" />
					</li>
				</ul>
			</div>
		</div>
		<!-- 顶部广告条start -->
		<div class="top-box" style="display:block;">
		</div>
		<!-- 顶部广告条end -->
		<div class='container' id='HeardTop'>
			<div class='ld' id='logo-img'>
				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" target="_blank">
					<img alt="" src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/dawawang.gif" >
				</a>
			</div>
			<div class='seach-box index-saeach-box'>
				<div class='i-search ld'>
					<ul class="hide-box" style="display: none">
					</ul>
					<div class='form'>
						<span style="position: absolute;top: 5px;left:440px;color: white;z-index:10px;cursor: pointer;" onclick="javascript:$('#search_form').submit();" class="fa fa-search fa-2x""></span>
						<form action="${(domainUrlUtil.EJS_URL_RESOURCES)!}/search.html" method="get" id="search_form">
							<input type='text' id='keyword' name="keyword" value="${(keyword)!''}" class='text' autocomplete="off" style='color:rgb(153,153,153);' placeholder="输入关键词搜索商品"/>
							<input type='submit' value='' class='button'>
						</form>
					</div>
				</div>
				<div id='Hotwords'>
					<strong>热门搜索：</strong>
					<div id="keywordIDs"></div>
				</div>
			</div>
			<div class="cartandusercenter">
				<div class='settleup'>
					<dl>
						<!-- 如果没有商品这里显示0 -->
						<div class='addcart-goods-num'>0</div>
						<dt class='ld first-dt'>
							<s class="fa fa-shopping-cart fa-2x" ></s>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cart/detail.html' style="font-size: 13px;">
								进货单
							</a>
							 <b></b>
						</dt>
						<dd id="priviewMycart">
						</dd>
					</dl>
				</div>
				<div class='settleup_persion'>
					<span class="fa fa-user fa-2x" style=""></span>&#12288;<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/index.html" style="font-size: 13px;">会员中心</a>
				</div>
			</div>
		</div>
		<!--商品分类 -->
		<div id='NavSort'>
			<div class='container'>
				<div class='all-category'>
					<div class='dts'>
						<a href='javascript:void(0);' style="cursor: default;font-size: 16px;color:#fff;" target='_blank'>商品分类</a>
					</div>
					<div class='sec_attr' >
						<ul class='dd-inner'>
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
							<div class='item-sub' id='index${type_index+1}' >
								<div class='subitems' >
									<!-- 二级分类  -->    
									<dl class='fore-dl' style="overflow-x:hidden;overflow-y:auto;">
										<#list type.childList as type2> 
										<dd>
											<#if type_index == 0>
											<a href='0-1-0-0-0-0-${(type2.id)!}-0-0-0.html'><i>${type2.name!""}</i></a>
											<#else>
											<a href='0-1-0-0-0-0-0-0-0-0-${(type2.id)!}_${(type2_index)!}.html'><i>${type2.name!""}</i></a>
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
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0.html' target="_blank">所有商品</a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-xppw.html' target="_blank">品牌袜</a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-8-0-0-0.html' target="_blank">裸袜</a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/eosearch.html' target="_blank">快速下单<span class="fa fa-search" style="color: #27CDF2;"></span></a>
						</li>
						<li class='fore1'>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-999M.html' target="_blank">新品上线</a>
						</li>
						<#--
						<li class='fore1'>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/custom-made.html' target="_blank">采购定制</a>
						</li>
						
						<li class="fore1">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-777M.html' target="_blank">九折疯抢<span class="fa fa-fire" style="color: red;"></span></a>
						</li>
						-->
						<li class="fore1">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/search.html?keyword=短袜' target="_blank">短袜</a>
						</li>
						<!-- 5IX/CCUMI专场 -->
						<#--
						<li class="fore1">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-wadrt.html' target="_blank">
								<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/5IX.png" alt="5IX/CCUMI专场" style="padding-top:10px;" onmouseover="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/5IX_click.png'" onmouseout="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/5IX.png'"/>
							</a>
						</li>
						-->
				</ul>
				<#--
				<div class="activity_nav" style="margin-top: -34px; float: right; margin-right: 10px;">
					<a href='/0-1-0-0-0-0-0-0-0-0-777M.html' class="activity_link" target="_blank">
						<img  class="activity_img" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/entrance.png" height="70" width="220"/>
					</a>
				</div>
				-->
			</div>
		</div>
 				
				<ul class="list"></ul>
 
	<!-- end -->
	<!-- 轮播图 -->
	<div id="banner_tabs" class="i-flexslider">
        <ul class="slides" >
        <#if bannerList?? && bannerList?size &gt; 0 >
			<#list bannerList as banner>
			<li>
			<!--这是原来a标签的href  sixtymoney.html-->
                <a title="" target="_blank" href="${(banner.linkUrl)!''}">
                    <img width="1920" height="500" src="" style="background: url(${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(banner.image)!''}) no-repeat center;">
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
        	<div class="date-page" ">
				<#include "/front/index/fwc_activity.ftl" />        			
        	</div>
    </div>
	<!-- 楼层 -->
	<!-- 广告 -->
	<#if floorList?? && floorList?size &gt; 0>
	<#list floorList as floor>
	<div class="snow_background">
	<div class='container ___floor' data-id="${floor.id}" data-title="${floor.name}">
		<div class='gusess-like'>
			<div id='guessyou'>
				<div class='container'>
				<div class='floor-banner'>
				  <a href='${(floor.advLinkUrl)!"#"}' target='_blank'>
						<img data-original='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(floor.advImage)!""}' width='1198'/>
				  </a>
				</div>
			</div>
			<br />
						<#if floor.classList?? && floor.classList?size &gt; 0>
						<#list floor.classList as fc>
				<div class='guess-box'>
					<div class='guess-spacer'></div>
					<ul>
									<#if fc.dataList?? && fc.dataList?size &gt; 0>
									<#list fc.dataList as data >
							<li style="padding-top: 10px;margin-bottom: 20px;">
								<div class='p-img'>
									<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(data.product.id)!0}.html' target='_blank'>
										<img data-original='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(data.product.masterImg)!""}' title='${(data.product.name1)!""}'>
									</a>
								</div>
								<div class='p-info'>
									<div class='p-name'>
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(data.product.id)!0}.html'
											 target='_blank' title='${(data.product.name1)!""}'>${(data.product.name1)!""}</a>
											 <#if data.product.sellerId == 1>
											<span style="vertical-align:top;display:none;width:16px;height:16px;BACKGROUND: url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/redpacket.png') no-repeat center 50%;"></span>
											<#else>
											<span></span>
											</#if>
									</div>
									<div class='p-price'>
										<i>¥</i>
										${(data.product.mallPcPrice)!"0.00"}
										<#if data.product.marketPrice ?? && data.product.marketPrice &gt; data.product.mallPcPrice>
											<span class="tzm_span">
												<i>¥</i>
												${(data.product.marketPrice)!"0.00"}
											</span>
											<#else>
											<span class="tzm_span"></span>
										</#if>
									</div>
								</div>
							</li>
									</#list>
									</#if>
					</ul>
				</div>
						</#list>
						</#if>
			</div>
		</div>
	</div>
	</div>
	</#list>
	</#if>
	<div id="shipin" class="activity_nav" style="display:none">
		<a class="activity_link" href="javascript:;" target="_blank">
		    <img class="activity_img" src="" />
		</a>
	    <span style="position:absolute;right:-141px;top:0px;width: 60px;height: 60px;cursor:pointer" onclick="javascript:$('#shipin').css('display','none');$('#mask').css('display','none');"></span>
    </div>
	<div class="mask" id="mask" style="display:none"></div>
	<!-- end -->
	
	<!-- footer -->
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap.min.js?version=v20080101'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.min.js?version=v20080101'></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/func.js?version=v20080101"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/checkvalue.js?version=v20080101"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.lazyload.min.js?version=v20080101"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.alerts.js?version=v20080101"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/slider.js?version=v20080101"></script>
		
        <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/fwc_qqkefu.js?version=v20080101"></script>
        <!--活动公告-->
        <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/fwc/jquery.SuperSlide.2.1.1.js?version=v20080101"></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/index.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/loucengdaohang.js"></script>
		<script type="text/javascript">
		/* 活动公告 */
		$(function(){
			var pageState = sessionStorage.getItem('pageState');
			if(pageState != 1){
				$.ajax({
					url:domain+"/popup.html?type=1",
					success:function(data){
						if(data.success) {
							sessionStorage.setItem('pageState', 1);
							if (data.data == null) return;
							var tanChu = data.data;
							var turl = tanChu[0].linkUrl;
							var timg = "${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}" + tanChu[0].image;
							$(".activity_link").attr('href', turl); 
							$(".activity_img").attr('src', timg); 
							$(".activity_nav").show();
							$(".mask").show();
						}
					},
					error:function(){
					}
				});
			}
			
			//加载顶部广告
			$.ajax({
					url:domain+"/popup.html?type=2",
					success:function(data){
						if(data.success) {
							if (data.data == null) return;
							var tanChu = data.data;
							if (tanChu.length > 0) {
								var htmlStr ="";
								for (var i = 0; i < tanChu.length; i++) {
									var turl = tanChu[i].linkUrl;
									var timg =  "${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + tanChu[i].image;
									var name = tanChu[i].title;
									if (i == 0) {
										htmlStr = htmlStr + "<div class='top-banner first-banner' style='background:#ef1828;'>";
									} else if (i == 1) {
										htmlStr = htmlStr + "<div class='top-banner second-banner' style='background:#db0000;'>";
									} else {
										htmlStr = htmlStr + "<div class='top-banner third-banner' style='background:#ff9b19;'>";
									}
									
									htmlStr = htmlStr + "<div class='w1190 marA'>"
									+ "<em class='cloes-banner fr'></em>"
									+ "<a href= '" + turl + "' title='' target='_blank'>"
									+ "<img height='100' width='1190' src='" + timg+ "' alt='" + name + "' />"
									+ "</a>"
									+ "</div>"
									+ "</div>";
								}
								$('.top-box').append(htmlStr);
							} else {
								$('.top-box').hide();
							}
						}
					},
					error:function(){
					}
				});
			
			
			$(".slideTxtBox").slide({
					effect:"fade",
					delayTime:"1000",
			});
			// 初始化登录状态
			loginInfoInit();
			// 刷新进货单
			refreshMycart();
			/* 广告条JS切换效果代码 */
			setInterval(banner_timer,5000);
			$(".top-banner .cloes-banner").click(function(){
				$(".top-box").animate({height:0},300);
			});
			/* 切换效果end */
			
			//我的收藏 绑定监听事件 tzm_star_listener
			$("#tzm_star_listener").mouseover(function(){
				$("#tzm_star_listener i").removeClass("icon icon-star-empty icon-lg");
				$("#tzm_star_listener i").addClass("icon icon-star icon-lg");
			}).mouseout(function(){
				$("#tzm_star_listener i").removeClass("icon icon-star icon-lg");
				$("#tzm_star_listener i").addClass("icon icon-star-empty icon-lg");
			})
			//关注我们  绑定监听事件 tzm_qrcode_listener
			$("#tzm_qrcode_listener").mouseover(function(){
				$("#tzm_qrcode_listener").next().css({"display":"block"});
			}).mouseout(function(){
				$("#tzm_qrcode_listener").next().css({"display":"none"});
			})
			//联系客服  绑定监听事件 tzm_phone_listener
			$("#tzm_phone_listener").mouseover(function(){
				$("#tzm_phone_listener").next().css({"display":"block"});
			}).mouseout(function(){
				$("#tzm_phone_listener").next().css({"display":"none"});
			})
			
			$("#tzm_star_listener").click(function(){
				//未登录不能打开我的收藏 /member/collectproduct.html
				if (!isUserLogin()) {
					window.location.href=domain+"/login.html";
					//弹出框登录 形式出现异常 暂时无法实现 
			//		showid('ui-dialog');
				 	return;
				}
				window.location.href=domain+"/member/collectproduct.html";
			});
		});
			
		var i=1;
		function banner_timer(){
			i++;
			if (i%2==0){
				$(".top-banner").eq(0).css("display","block").animate({opacity:"1"},1000);
				$(".top-banner").eq(1).animate({opacity:"0"},1000).css("display","none");
			}
			else if (i%2==1) {
				$(".top-banner").eq(1).css("display","block").animate({opacity:"1"},1000);
				$(".top-banner").eq(0).animate({opacity:"0"},1000).css("display","none");
			}
			else if (i%2==2) {
				$(".top-banner").eq(2).css("display","block").animate({opacity:"1"},1000);
				$(".top-banner").eq(1).animate({opacity:"0"},1000).css("display","none");
			}
		}
		
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
							$("#dingdanspan").remove();
							// 构造登录信息
							var loginInfoHtml = "";
							loginInfoHtml += ("<li class='fore1' id='loginbar'>");
							loginInfoHtml += ("	<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=1' target='_blank' class='login'>" + data.data.name + "</a>&nbsp;&nbsp;");
							loginInfoHtml += ("	<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/logout.html'  onclick='logout()' class='regist'>退出</a>");
							loginInfoHtml += ("</li>");
							loginInfoHtml += ("<li class='fore2 ld'>");
							loginInfoHtml += ("	<span></span>");
							loginInfoHtml += ("	<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html'>我的订单</a>");
							loginInfoHtml += ("</li>");
							// 显示登录信息
							$(".shortcut-right").prepend(loginInfoHtml);
							//隐藏操作
							$(".shortcut-right .loginhide").css("display","none");
							$(".shortcut-right .registhide").css("display","none");
						} else {
						}
					}else{
					}
				},
				error:function(){
				}
			});
		}
			
		/**
			我的收藏
		*/
		function isUserLogin() {
			var isLogin = false;
			$.ajax({
				type : "POST",
				url : domain + "/isuserlogin.html",
				async : false,
				success : function(data) {
					if (data.success) {
						if (data.data) {
							isLogin = true;
						} else {
							isLogin = false;
						}
					} else {
						isLogin = false;
					}
				},
				error : function() {
					isLogin = false;
				}
			});
			return isLogin;
		}
	
		</script>
		<#include "/front/commons/_endbig.ftl" />
	</body>
</html>

