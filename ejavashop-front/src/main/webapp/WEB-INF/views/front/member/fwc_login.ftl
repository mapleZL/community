<!Doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'>
		<!-- 采用CDN方式引入字体图标，优点:访问速度快 -->
		<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/font-awesome/css/font-awesome.min.css">
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
		//end
		$(function(){
			//给立即登入绑定事件
			$("#loginButton").click(function(){
				var bool = $("#form").valid();
				if(bool){
					var params = $("#form").serialize();
					$.ajax({
						type:"POST",
						url:domain+"/dologin.html",
						dataType:"json",
						async : false,
						data : params,
						success:function(data){
							if(data.success){
								var boolRealnametrue = (data.data.realName==null) || (data.data.realName=="");
								var boolRealnamefalse = (data.data.realName!=null) && (data.data.realName!="");
								if(boolRealnametrue){
									window.location.href=domain+"/member/info.html";
								}
								if(boolRealnamefalse){
									window.location.href=domain+"/member/order.html";
								}
							}else{
								jAlert(data.message);
								//刷新验证码
								refreshCode();
							}
						},
						error:function(){
							jAlert("异常，请重试！");
						}
					});
				}
			})
			
			//监听回车事件  不起作用
			function keyDownSubmit(e){
				var theEvent = e || window.event;
				var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
				if(code == 13){
					$("#loginButton").click();
				}
			}
			document.onkeydown = keyDownSubmit;
			
		})
		</script>
	</head>
    <body>		
    
    
    	<div class="login-top">
    	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html">
  			  <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/dawawang.png" height="600" width="1920" alt="">
    	</a>
					<h1>用户登录</h1>
    		</div>
    			
			<div class="sigin-mian">
				<div class="sigin-bg">
				<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/fwc/signin_bg.jpg" height="600" width="1920" alt="">
				</div>
			</div>
			<div class="sigin-side">
					
					<div class="insigin-side">
							<p>您尚未登录</p>
						</div>
					<div class="sigin-enter">
					<form id="form" role="form">
						<span class="greet">欢迎登录</span>

						<div class="name">
							<span >用户名</span>
						<input type="text" name="name" id="name" placeholder="请输入手机号码"  required="required">
						</div>
						<div class="password name">
							<span >密&#12288;码</span>
						<input type="password" id="password" name="password" placeholder="请输入密码" required="required"> 
						</div>
						<div class="verify name">
							<span style="float:left;">验证码</span>
							
						<input type="text" name="verifyCode" placeholder="请输入验证码" required="required">
						<img src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html" id="code_img" style="cursor: pointer;" height="27" width="73" alt="" onclick="refreshCode();">
							
						</div>
						<a class="chongzhi" id="loginButton">立即登录</a>
						<div class="forget-password">
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/forgetpassword.html" class="forget-left">忘记登录密码？</a>
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html" class="forget-right">免费注册</a>
						</div>
					</form>
					</div>
					
						
			</div>
		<#include "/front/commons/_endbig.ftl" />
