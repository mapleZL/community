<!Doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		
		<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'>
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/user.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/base.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/register.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/member/login.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
		<script type="text/javascript">
			var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
		</script>
	</head>
	<body>
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
		</div>
		<div class='nc-login-layout'>
			<div class='nc-signup'>
				<div class='signup-title'>
					<h3>用户登录</h3>
				</div>
				<div class='signup-content'>
					<form class="form-horizontal" id='form'>
					  	<div class="form-group">
						    <label for="inputUsernamel3" class="form-label">登录帐号：</label>
						    <div class="login-box">
						      	<input type="text" class="form-control" id="inputUsername3" name='name'>
						      	<div class='tip'>
							      			<p></p>
							      		</div>
						    </div>
					  	</div>
					  	
					  	<div class="form-group">
						    <label for="inputPassword3" class="form-label">密码：</label>
						    <div class="login-box">
						      	<input type="password" class="form-control" id="inputPassword3" name='password'>
						      	<div class='tip'>
							      			<p></p>
							      		</div>
						    </div>
					  	</div>
					  
					  	<div class='lg-rember'>
					  		<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/forgetpassword.html' class='forget-password'>忘记密码？</a>
					  	</div>
					  	<div class='form-group mb0'>
					  		<div style='margin-left:83px;'>
					  			<a href='javascript:void(0)' id="loginButton" class='btn btn-danger'  >登录</a>
					  		</div>
					  	</div>
					  	<div class='form-group'>
					  		<div class='inside-box'>
					  			<span style='line-height:30px;'>还不是本站会员？立即</span>
					  		</div>
					  		<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html' class='regists' style='padding:3px 10px;'>注册</a>
					  	</div>
					  	
					</form>
				</div>
			</div>
			<div class='nc-login-bottom'></div>
			<div class='nc-login-left'></div>
		</div>
		<#include "/front/commons/_endbig.ftl" />