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
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc/fwc_h5find.css">
<script
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js"></script>
<script type="text/javascript">
	var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
</script>
</head>
<body>
	<header>
		<div class="login-head">
			<div class="login-indead">
				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/login.html"> <span
					class="left-back"></span>
				</a>
				<div class="head-login">找回密码</div>
			</div>
			<div class="head-logo"></div>
			<h1>大袜网</h1>
		</div>
	</header>
	<!-- 头部 end-->
	<div class="s-container">
		<div class="pad10">
			<p>您可以通过绑定的手机找回密码</p>
			<div class="tiperror"></div>
			<form id="loginForm" onSubmit="return false;">
				<input type="hidden" id="toUrl" name="toUrl" value="${(toUrl)!''}" />
				<div class="form-group">
					<input type="text" class="form-control" id="mobile" name="mobile"
						placeholder="请输入注册的手机号码">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="mobVerfiy"
						name="mobVerfiy" placeholder="短信验证码" style="width:60%;">
					<button class="yanzhenma" onclick="getmobVerify(this)">获取验证码</button>
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
					<label id="errLabel" style="color:red"></label>
					<button type="submit" class="btn  btn-login" id='forgetPasswordBtn'>重置密码</button>
				</div>
			</form>

		</div>
	</div>
	<script type="text/javascript">
		function getmobVerify(this_) {
			var obj = $(this_);
			var mob = $("#mobile").val();
			if (mob == null || mob == "") {
				$("#errLabel").html("请输入注册的手机号");
				return false;
			}
			if (!isMobile(mob)) {
				$("#errLabel").html("请输入正确的手机号");
				return false;
			}
			obj.attr("disabled", true);
			$.ajax({
				type : 'get',
				url : domain + '/mobVerify.html?mob=' + mob,
				success : function(e) {
					if (e.success) {
						var time = 120;
						obj.attr("disabled", true);
						obj.val(time + "秒后重新获取");
						time--;
						intervalId = setInterval(function() {
							obj.text(time + "秒后重新获取");
							if (time == 0) {
								clearInterval(intervalId);
								obj.removeAttr("disabled");
								obj.text("获取验证码");
							}
							time--;
						}, 1000);
					} else {
						// 					jAlert(e.message);
						$.dialog('alert', '提示', e.message);
					}
				}
			});
		}

		$(function() {
			$("#forgetPasswordBtn").click(function() {
				var mobile = $("#mobile").val();
				var mobVerfiy = $("#mobVerfiy").val();
				var verifyCode = $("#verifyCode").val();

				if (mobile == null || mobile == "") {
					$("#errLabel").html("请输入手机号");
					return false;
				}
				if (!isMobile(mobile)) {
					$("#errLabel").html("请输入正确的手机号");
					return false;
				}
				if (mobVerfiy == null || mobVerfiy == "") {
					$("#errLabel").html("请输入手机验证码");
					return false;
				}
				if (verifyCode == null || verifyCode == "") {
					$("#errLabel").html("请输入验证码");
					return false;
				}

				$("#forgetPasswordBtn").attr("disabled", "disabled");
				var params = $('#loginForm').serialize();
				$.ajax({
					type : "POST",
					url : domain + "/doforgetpassword.html",
					dataType : "json",
					data : params,
					success : function(data) {
						if (data.success) {
							alert("密码重置成功，请查收您的短信！");
							window.location = domain + "/login.html";
							/* $.dialog('alert','提示',data.data,2000,function(){ 
								window.location=domain+"/login.html"; 
							}); */
						} else {
							$("#errLabel").html(data.message);
							refreshCode();//刷新验证码
							$("#forgetPasswordBtn").removeAttr("disabled");
							return false;
						}
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