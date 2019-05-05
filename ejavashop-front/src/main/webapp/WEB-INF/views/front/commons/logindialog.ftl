<link rel="stylesheet" type="text/css" 
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/dialoglogin.css">
<script type="text/javascript"
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/layer_pop.js"></script>
<script>
	$(function() {
		$('#loginclose').on('click', function() {
			closeLayer('ui-dialog')
		});

		jQuery("#dialogLoginForm").validate(
				{
					errorPlacement : function(error, element) {
						var obj = element.addClass('error');
						error.appendTo(obj);
					},
					rules : {
						"name" : {
							required : true
						},
						"password" : {
							required : true,
							passwordLength : true
						},
						"verifyCode":{
							required:true
						}
					},
					messages : {
						"name" : {
							required : "请输入用户名"
						},
						"password" : {
							required : "请输入密码"
						},
						"verifyCode":{
							required:"请输验证码"
						}
					}
				});

		$("#dialogLoginButton").click(function() {

			if ($("#dialogLoginForm").valid()) {
				$(".btn-danger").attr("disabled", "disabled");
				var params = $('#dialogLoginForm').serialize();
				$.ajax({
					type : "POST",
					url : domain + "/dodialoglogin.html",
					dataType : "json",
					async : false,
					data : params,
					success : function(data) {
						if (data.success) {
							//jAlert("登录成功！");
							closeLayer('ui-dialog');
							location.reload();
						} else {
							jAlert(data.message);
							refreshCode();
							$(".btn-danger").removeAttr("disabled");
						}
					},
					error : function() {
						jAlert("异常，请重试！");
						$(".btn-danger").removeAttr("disabled");
					}
				});
			}
		});
	});

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

<!-- 弹层 -->
<div class="ui-dialog" id="ui-dialog">
	<h2>
		<div class="fl">您尚未登录</div>
		<div class="fr">
			<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/dialog.png"
				alt="" id="loginclose">
		</div>
	</h2>
	<div style="border: 0px solid red;">
		<span
			style="display: inline-block; font-size: 18px; font-weight: bold; margin-bottom: 5px; padding-left: 180px; padding-top: 5px;">用户登录</span>
		<form id="dialogLoginForm" class="form-horizontal"
			style="padding-left: 30px;">
			<div class="form-group" style="margin: 0 0 0 0;">
				<label for="inputUsernamel3">登录帐号：</label>
				<div class="login-box" style="display: inline;">
					<input type="text" name="name" id="inputUsername3"
						class="form-control" style="width: 300px;">
<!-- 					<div class="tip" style="color: red;"> -->
<!-- 						<p></p> -->
<!-- 					</div> -->
				</div>
			</div>
			<br />
			<div class="form-group" style="margin: 0 0 0 0;">
				<label for="inputPassword3">密&#12288;&#12288;码：</label>
				<div class="login-box" style="display: inline;">
					<input type="password" name="password" id="inputPassword3"
						class="form-control" style="width: 300px;">
<!-- 					<div class="tip" style="color: red;"> -->
<!-- 						<p></p> -->
<!-- 					</div> -->
				</div>
			</div>
			<br />
			
			<div class="form-group" style="margin: 0 0 0 0;">
				<label>验证码&#12288;：</label>
				<div class="login-box" style="float: left;">
					<input type="text" name="verifyCode"
						class="form-control" style="width: 220px;">
<!-- 					<div class="tip" style="color: red;"> -->
<!-- 						<p></p> -->
<!-- 					</div> -->
				</div>
				<img alt="验证码"
					style="cursor: pointer;float: left;margin: 3px 5px;"
					src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html"
					id="code_img" onclick="refreshCode();" />
			</div>

			<div class="lg-tip clearfix"
				style="padding-left: 124px; padding-top: 10px;">
				<a class="forget-password"
					href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/forgetpassword.html">忘记密码？</a>
			</div>
			<div class="form-group" style="margin: 0 0 0 0;">
				<div style="margin-left: 83px;">
					<a class="btn btn-danger" id="dialogLoginButton"
						href="javascript:void(0)" style="width: 210px;">登录</a>
				</div>
			</div>
			<div class="form-group" style="margin: 0 0 0 0;">
				<div class="inside-box" style="padding-left: 125px;">
					<span style="line-height: 30px;">还不是本站会员？</span> <a
						style="padding: 0 0 0 0;" class="regists"
						href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html">立即注册</a>
				</div>
			</div>
		</form>
	</div>
</div>
