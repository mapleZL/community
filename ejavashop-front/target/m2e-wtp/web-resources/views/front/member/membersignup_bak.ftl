<!Doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<script type="text/javascript">
			var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
			var msgcode;
			var intervalId;
		  if(/AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE|HTC/.test(navigator.userAgent))){
			if(window.location.href.indexOf("?mobile")<0){
				try{
					var url = location.href;
				    var sp = url.split("?");
				    var mobileUrl = "${(domainUrlUtil.EJS_H5_URL)!}/register.html?"+ sp[1];
				    var mobileUrl1 = "${(domainUrlUtil.EJS_H5_URL)!}/register.html";
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
		</script>
		<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		
		<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'>
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/user-bak.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/tzm_base.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/register.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>

		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/member/members.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
	</head>
	<body style="background: url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/bg_login.png) no-repeat center;background-position:0 80px;">
		<div class='head-layout'>
			<div class='container'>
				<div class='header-wrap'>
					<div class='snlogo'>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>
							<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/dawawang.png'>
						</a>
					</div>
				</div>
			</div>
			<div class='nc-login-layout container'>
				<div class="signup-left">
					<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/qr_code.png'>
				</div>
				<div class='nc-signup'>
					<div class='signup-title'>
						<h3>用户注册<span  style="float:right;font-size:12px;margin-right:30px;"><a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/login.html'>已有账号，请登录</a></span></h3>
					</div>
					<div class='signup-content'>
						<!-- <form class="form-horizontal forms-group" id='form'  action="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/doregister.html" method="POST"> -->
						<form class="form-horizontal forms-group" id='form' method="POST">
						<input type="hidden" name="saleCode" value="${saleCode}"  id="saleCode"/>
						<input type="hidden" name="inviterId" value="${inviterId}"  id="inviterId"/>
							     <div class="form-group">
								    <div class="memberType-div">	
									    <label for="inputEmail3" class="form-label" style="display: block;margin-top: -6px;">用户类别：</label>
								      	<input type="radio"  id="memberType1" checked="checked;" name='memberType' value="2" style="height:14px;vertical-align:top;"/>
								      	<label for="memberType1" style="vertical-align:bottom;cursor: pointer;">我是采购商</label>
								      	<span style="margin-right:50px;"></span>
								      	<input type="radio"  id="memberType" name='memberType'  value="1" style="height:14px;vertical-align:top;"/>
								      	<label for="memberType" style="vertical-align:bottom;cursor: pointer;">我是供应商</label>

								      	<div class='tip signup-message-email'>
							      			<p></p>
							      		</div>
								    </div>
							  	</div>
							  <div class='reg-switch-wrap'>
								<div class="form-group reg-email reg-switch " >
								    <label for="inputEmail3" class="form-label">手机号：</label>
								    <div class="login-box">
								      	<input type="text" class="form-control" id="userName" name='name'>
								      	<div class='tip signup-message-email'>
							      			<p></p>
							      		</div>
								    </div>
							  	</div>
							  	<div class="form-group">
								    <label for="inputPasswordl3" class="form-label"> 验证码：</label>
								    <div class="code-box">
								      	<input type="text" class="form-control" id="verifyCode" name="verifyCode">
								      	<div class='tip signup-message-code'>
								      		<p></p>
								      	</div>
								    </div>
								    <div class='fl' style='margin-left:2px;'>
								    	<img style="cursor:pointer;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html" id="code_img" onclick="refreshCode();" width="59" height="27" />
								    	 <a href='javascript:void(0);' onclick="refreshCode();">看不清，换一张</a>
								    </div>
							  	</div>
							</div>
						  	<div class="form-group">
							    <div class="sms-verify">
							      	<button type="button" class="btn btn-default" id="getSMSVerify">
							      		获取短信验证码</button>
							      	<div class='tip signup-message-sms'>
							      		<p></p>
							      	</div>
							    </div>
						  	</div>
						  	<div class="form-group" style="position: relative;">
						  		<label for="inputPasswordl3" class="form-label">短信验证码：</label>
						  		 <div class="login-box">
									<input id="smsCode" name="smsCode" class="form-control"
										placeholder="请输入短信验证码">
									<div class='tip signup-message-smscode'>
							      		<p></p>
							      	</div>
								</div>
							</div>
						  	<div class="form-group">
							    <label for="inputPasswordl3" class="form-label">设置密码：</label>
							    <div class="login-box">
							      	<input type="password" class="form-control" id="password" name='password'>
							      	<div class='tip signup-message-password'>
							      		<p></p>
							      	</div>
							    </div>
						  	</div>
						  	<div class="form-group">
							    <label for="inputPasswordl3" class="form-label">确认密码：</label>
							    <div class="login-box">
							      	<input type="password" class="form-control" id="repassword" name='repassword'>
							      	<div class='tip signup-message-confirmpsw'>
							      		<p></p>
							      	</div>
							    </div>
						  	</div>
						  	<div class='form-group'>
						  		<div class='ml70'>
						  			<a href='javascript:void(0)' id="signupButton"  class='btn btn-danger'>立即注册</a>
						  		</div>
						  	</div>
						  	<div class='form-group'>
						  		<div class='ml70'>
								  	<input type='checkbox' class='agree' name="acceptProtocol" id="acceptProtocol">
								  	<span class='protocol'>我已阅读并同意<a href='./service_protocol.html'>服务协议</a></span>
								</div>
							</div>
						  	
						</form>
					</div>
				</div>
				<div class='nc-login-bottom'></div>
				<div class='nc-login-left'></div>
			</div>
			</div>
			<#include "/front/commons/_endbig.ftl" />