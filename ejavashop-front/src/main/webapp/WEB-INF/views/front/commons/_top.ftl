<#import "/front/commons/_macro_controller.ftl" as cont/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<title>大袜网商城</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
	
	<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		
	<link rel="stylesheet" type="text/css" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'/>
	
	<!-- 采用CDN方式引入字体图标，优点:访问速度快 -->
	<link href="//cdn.bootcss.com/font-awesome/3.0.2/css/font-awesome-ie7.min.css" rel="stylesheet">
	<link href="//cdn.bootcss.com/font-awesome/3.0.2/css/font-awesome.min.css" rel="stylesheet"> 
	
	<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/user.css"/>
	<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/tzm_base.css">
	<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
	<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/func.js"></script>
	<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js"></script>
	<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/checkvalue.js"></script>
	<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
	
	<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/fwc_qqkefu.js"></script>
    <link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc_qqku.css">
	
	<style type='text/css' rel="stylesheet">
	</style>
	<script type="text/javascript">
		var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
	</script>
  </head>
	<body class='wp1200'>
	<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
			<div class='wrapper'>
			<div class='container'>
				<ul class='collect lh'>
					<li class='fore1'>
					<!-- 
						<a href='javascript:void(0)' onclick="AddFavorite(window.location,document.title);return false;">收藏</a>
						 -->
						 <span style="border: 0px;">欢迎光临大袜网</span>
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
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html">我的订单</a>
						</li>
				   	 
				   	<#else>
						<li class='fore3 ld app-ff menu' style="width: 90px;">
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/login.html' class='login'>你好,请登录</a>
						</li>
						<li class="fore3 ld app-ff menu">
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
						<a href="javascript:void(0);">手机商城</a>
					</li>
					 -->
					<li class='fore4 ld menu' id='custom-server'>
						<span></span>
						<a href='javascript:void(0);' id="tzm_star_listener"><i class="icon icon-star-empty icon-lg"></i>我的收藏</a>
					</li>
					<li class='fore5 ld menu' id='site-nav'>
						<span></span>
						<a href='javascript:void(0);' id="tzm_qrcode_listener"><i class="icon icon-qrcode icon-lg"></i>关注我们</a>
						<!-- <b></b> -->
						<img style="position: absolute;top: 65%;left:-65%;display: none;" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/wccode_open.png" alt="关注我们" />
					</li>
					<li class='fore5 ld menu' id='site-nav'>
						<span></span>
						<!-- <a href='${(R.EJS_URL_RESOURCES)!}/store/step1.html'>商家入驻</a> ${(R.EJS_URL_RESOURCES)!}/register.html-->
						<a href='javascript:void(0);' id="tzm_phone_listener"><i class="icon icon-phone icon-lg"></i>联系客服</a>
						<!-- <b></b> -->
						<img style="position: absolute;top: 65%;left:-50%;display: none;" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/tel_open.png" alt="关注我们" />
					</li>
				</ul>
			</div>
		</div>
		<!-- 华丽的分隔线 -->
		<div class='top-head'>
			<div class='container' id='HeardTop'>
				<div class='ld' id='logo-img'>
					<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" target="_blank">
						<img alt="" src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/dawawang.png" >
					</a>
				</div>
				<div class='seach-box index-saeach-box'>
					<div class='i-search ld'>
						<ul class="hide-box" style="display: none">
						</ul>
						<div class='form'>
							<span style="position: absolute;top: 6px;left:440px;color: white;z-index:10px;cursor: pointer;" onclick="javascript:$('#search_form').submit();" class="icon icon-search icon-2x""></span>
							<form action="${(domainUrlUtil.EJS_URL_RESOURCES)!}/search.html" method="get" id="search_form">
								<input type='text' id='keyword' name="keyword" value="${(keyword)!''}" class='text' autocomplete="off" style='color:rgb(153,153,153);'>
								<input type='submit' value='' class='button'>
							</form>
						</div>
					</div>
					<div id='Hotwords'>
						<strong>热门搜索：</strong>
						<div id="keywordIDs"></div>
					</div>
				</div>
				<div class='settleup'>
					<dl>
						<!-- 如果没有商品这里显示0 -->
						<div class='addcart-goods-num'>0</div>
						
						<dt class='ld first-dt'>
							<s class="icon icon-shopping-cart icon-2x" style="color:#27CDF2"></s>
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cart/detail.html' style="font-size: 13px;color: #666;">
							进货单
							</a>
							 <b></b>
						</dt>
						<dd  id="priviewMycart">
						</dd>
					</dl>
				</div>
				<div class='settleup_persion'>
					<span class="icon icon-user icon-2x"></span>&#12288;<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/index.html" style="font-size: 13px;">会员中心</a>
				</div>
			</div>
		</div>
<script type="text/javascript">
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
	
$("#tzm_star_listener").click(function(){
	//未登录不能打开我的收藏 /member/collectproduct.html
	if (!isUserLogin()) {
		window.location.href=domain+"/login.html";
		//弹出框登录 形式出现异常 暂时无法实现 
//		showid('ui-dialog');
	 	return;
	}
	window.location.href=domain+"/member/collectproduct.html";
})
</script>		