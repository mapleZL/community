<!Doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<!-- 采用CDN方式引入字体图标，优点:访问速度快 -->
		<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/font-awesome/css/font-awesome.min.css">
		<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'>
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwz_login.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/tzm_base.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.method.js"></script>
		
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/fwc_qqkefu.js"></script>
        <link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc_qqku.css">
        
		<script type="text/javascript">
		var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
		</script>
	</head>
    <body>
    			<div class="login-top">
    
  			<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html">
  			 	 <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/dawawang.png" height="600" width="1920" alt="">
		    </a>
					<h1>找回密码</h1>
    		</div>
    
			<div class="sigin-mian">
				<div class="sigin-bg">
				<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/fwc/signin_bg.jpg" height="600" width="1920" alt="">
				</div>
			</div>
			<div class="sigin-side">
					
					<div class="insigin-side">
							<p>验证后可重置密码</p>
						</div>
					<div class="sigin-enter">
					<form id="forgetform">
						<span class="greet">找回密码</span>

						<div class="name">
							<span >用户名</span>
						<input type="text" name="name" id="mob" placeholder="请输入手机号码" required="required" oninvalid="setCustomValidity('Custom Message')">
						</div>
						<div class="password name">
							<span >短信验证</span>
						<input type="text" name="mobVerfiy" id="mobVerfiy"  placeholder="请输入手机验证码" required="required">
						<a class="huoqu" onclick="getmobVerify(this)">获取验证码</a>
						</div>
						<div class="verify name">
							<span style="float:left;">验证码</span>
							
						<input type="text" placeholder="请输入验证码" name="verifyCode" id="verifyCode" required="required">
						<img src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html" style="cursor: pointer;" height="27" width="73" alt="" id="code_img" onclick="refreshCode();">
							
						</div>
						<a class="chongzhi" id="forgetPasswordBtn">重置密码</a>
						<div class="forget-password">
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/login.html" class="forget-left">已有账号，立即登录</a>
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html" class="forget-right">免费注册</a>
						</div>
					</form>
					</div>
					
						
			</div>
<script type="text/javascript">
	//添加校验规则
	jQuery("#forgetform").validate({
		rules:{
			name:{
				isMobile:true
			}
		}
	})
	//end

	function getmobVerify(this_){
		var obj = $(this_);
		var textBool = obj.text().indexOf("获取验证码")>=0;
		var mob = $("#mob").val();
		var validator = $("#forgetform").validate();
		var mobBool = validator.element("#mob");
		if(mobBool && textBool){
			$.ajax({
				type : 'get',
				url : domain + '/mobVerify.html?mob=' + mob,
				success : function(e) {
					if (e.success) {
						var time = 120;
						obj.text(time + "秒后重新获取");
						time--;
						intervalId = setInterval(function() {
							obj.text(time + "秒后重新获取");
							if (time == 0) {
								clearInterval(intervalId);
								obj.text("获取验证码");
							}
							time--;
						}, 1000);
					} else {
						jAlert(e.message);
					}
					
				}
			});
		}
	}
	//end
	$(function() {
		$("#forgetPasswordBtn").click(function() {
	
			if ($("#forgetform").valid()) {
				var params = $('#forgetform').serialize();
				$.ajax({
					type : "POST",
					url : domain + "/doforgetpassword.html",
					dataType : "json",
					async : false,
					data : params,
					success : function(data) {
						if (data.success) {
							jAlert(data.data, '提示', function() {
								window.location = domain + "/login.html"
							});
						} else {
							jAlert(data.message);
							refreshCode();//刷新验证码
						}
					}
				});
			}
	
		});
	});
</script>
		<#include "/front/commons/_endbig.ftl" />