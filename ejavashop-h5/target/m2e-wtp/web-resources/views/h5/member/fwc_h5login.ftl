<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title></title>
<link rel="stylesheet" type="text/css"
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc/base.css">
<link rel="stylesheet" type="text/css"
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc/fwc_h5login.css">
<script
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
</script>
</head>
<body>
	<header>
		<div class="login-head">
			<div class="login-indead">

				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html"> <span
					class="left-back"></span>
				</a>
				<div class="head-login">登录</div>
			</div>
			<div class="head-logo"></div>
			<h1>大袜网</h1>
		</div>

	</header>
	<!-- 头部 end-->

	<div class="s-container">
		<div class="pad10">
			<div class="tiperror"
				style="border: 0px solid red;text-align: center;color: red;"></div>
			<form id="loginForm" onSubmit="return false;">
				<input type="hidden" id="toUrl" name="toUrl" value="${(toUrl)!''}" />
				<div class="form-group">
					<input type="text" class="form-control" id="userName"
						name="userName" placeholder="请输入用户名">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="请输入密码">
				</div>
				<div class="form-group" style="position:relative;">
					<input type="text" class="form-control" id="verifyCode"
						name="verifyCode" placeholder="请输入验证码" maxlength="6"> <span
						class="captcha-img"> <img
						src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html"
						id="code_img" onclick="refreshCode();" height="27" width="73"
						alt="">
					</span>

				</div>
				<div class="btn-block">
					<button type="submit" class="btn  btn-login" id="loginBtn">登录</button>
				</div>
			</form>
			<div class="flex" style="width: 100%;height: 100%;margin-top: 5%;">
				<div class="login-option" style="float: left;width: 50%;">
					<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html"
						style="text-decoration: underline;">免费注册</a>
				</div>
				<div class="flex-forget" style="float: left;margin: 0;width: 50%;">
					<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/forgetpassword.html"
						style="text-decoration: underline;">忘记密码</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$("#loginBtn")
					.click(
							function() {
								var userName = $("#userName").val();
								if (userName == null || userName == "") {
									$(".tiperror").html("请输入用户名");
									return false;
								}
								var password = $("#password").val();
								if (password == null || password == "") {
									$(".tiperror").html("请输入密码");
									return false;
								}
								var verifyCode = $("#verifyCode").val();
								if (verifyCode == null || verifyCode == "") {
									$(".tiperror").html("请输入验证码");
									return false;
								}
								$("#loginBtn").attr("disabled", "disabled");
								var params = $('#loginForm').serialize();
								var toUrl = $('#toUrl').val();
								$
										.ajax({
											type : "POST",
											url : domain + "/dologin.html",
											dataType : "json",
											async : false,
											data : params,
											success : function(data) {
												if (data.success) {
													// 跳转到TODO
													var boolRealnametrue = (data.data.realName == null)
															|| (data.data.realName == "");
													var boolRealnamefalse = (data.data.realName != null)
															&& (data.data.realName != "");
													if (toUrl != null
															&& toUrl != "") {
														window.location = toUrl;
													} else if (data.success
															&& boolRealnametrue) {
														window.location = domain
																+ "/member/info.html";
													} else {
														//借用json中tatal字段存储该登录用户的购买信息
														var orderCount = data.total;
														//若该用户第一次登录尚未购买，跳转进入1分钱商品信息
														//if(orderCount==0){
														//	window.location=domain+"/product/10.html?productId="+ 10;
														//}else{
														//window.location = domain + "/member/order.html";
														//默认跳转首页，如果用户进货单有数据，由会有图标打醒用户
														window.location = domain + "/index.html";
														//}
													}
												} else {
													$(".tiperror").html(
															data.message);
													//刷新验证码
													refreshCode();
													$("#loginBtn").removeAttr(
															"disabled");
													return false;
												}
											},
											error : function() {
												$(".tiperror").html("异常，请重试！");
												$("#loginBtn").removeAttr(
														"disabled");
												return false;
											}
										});
							});

		});

		//刷新验证码
		function refreshCode() {
			jQuery("#code_img").attr(
					"src",
					"${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html?d"
							+ new Date().getTime());
		}
	</script>

</body>
</html>