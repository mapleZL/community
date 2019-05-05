<!Doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		
		<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'>
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/tzm_login.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/tzm_base.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/register.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
		<script type="text/javascript">
			var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
			$(function(){
				//中文提示
				jQuery("#form").validate({
					errorPlacement : function(error, element) {
						var obj = $(".tip").find('p').addClass('error');
						error.appendTo(obj);
					},
			        rules : {
			            "name":{required:true},
			            "password":{required:true},
			            "verifyCode":{required:true}
			        },
			        messages:{
			        	"name":{required:"请输入账号"},
			        	"password":{required:"请输入密码"},
			        	"verifyCode":{required:"请输验证码"}
			        }
			    }); 
				//提交登录信息
				$("#loginButton").click(function(){
					var bool = $("#form").valid()
					if(bool){
						$(".btn-primary").attr("disabled","disabled");
						var params = $("#form").serialize();
						$.ajax({
							type:"POST",
							url:domain+"/dologin.html",
							dataType:"json",
							async : false,
							data : params,
							success:function(data){
								if(data.success){
									window.location=domain+"/member/order.html?orderState=1";
									/*console.dir(data);
									if(data.total==0){
										window.location=domain+"/product/10.html?productId="+ 10;
									}else{
								    	window.location=domain+"/member/order.html?orderState=1";
								    }*/
								}else{
									jAlert(data.message);
									//刷新验证码
									refreshCode();
									$(".btn-primary").removeAttr("disabled");
								}
							},
							error:function(){
								jAlert("异常，请重试！");
								$(".btn-primary").removeAttr("disabled");
							}
						});
					}
				});
				//监听回车事件  暂时不起作用，待查【仝照美】
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
	<body style="background: url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/bg_login.png) no-repeat center;background-position:0 80px;">
		<div style="margin:0 auto; width:790px;margin-bottom:635px;">
			<div style="margin-top:10px;">
				<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>
					<img alt="大袜网" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/dawawang.png'>
				</a>
			</div>
			<div class="login_bg_div" >
				<div style="float:left; margin-top:100px;">
					<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/qr_code.png'>
				</div>
				<div class="login_form_div">
					<div class="welcom_login_tip">欢迎登录</div>
					<div class="login_form_input">
						<form class="form-horizontal" id="form" role="form">
						   <div class="form-group">
						      <label for="name" class="form-label">&#12288;用&nbsp;户&nbsp;名：&#12288;</label>
						      <div class="login-box">
						         <input type="text" class="form-control" id="name"  name="name"  placeholder="请输入手机号码">
						      </div>
						   </div>
						   <div class="form-group" style="margin-top: 20px;">
						      <label for="password" class="form-label">&#12288;密&nbsp;&#12288;&nbsp;码：&#12288;</label>
						      <div class="login-box">
						         <input type="password" class="form-control" id="password"  name="password"  placeholder="请输入密码">
						      </div>
						   </div>
						   
						   <div class="form-group" style="margin-top: 20px;">
								<img alt="验证码"
									style="cursor: pointer;height: 33px;float: left;margin: 3px 2px auto;"
									src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html"
									id="code_img" onclick="refreshCode();" />
						      <div class="login-box">
						         <input type="text" class="form-control" name="verifyCode"  placeholder="请输入验证码">
						      </div>
						   </div>
						   
						   <div class="tip">
				      			<p></p>
				      		</div>
						   <div class="form-group">
						      <div class="submit_css">
						         <a  id="loginButton" class="btn btn-primary">立即登录</a>
						      </div>
						   </div>
						</form>
					</div>
					<div class="login_other_url">
						<span>
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/forgetpassword.html">忘记登录密码?</a>
						</span>
						<span style="margin-left: 170px;">
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html">免费注册</a>
						</span>
					</div>
				</div>
			</div>
		</div>
		<#include "/front/commons/_endbig.ftl" />