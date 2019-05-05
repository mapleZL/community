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
	href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc/fwc_h5register.css">
<script
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js"></script>
<script
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.hDialog.js"></script>
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
				<div class="head-login">注册</div>
			</div>
			<div class="head-logo"></div>
			<h1>大袜网</h1>
		</div>
	</header>
	<!-- 头部 end-->
	<div class="s-container">
		<div class=" top-button name">
			<button id="caigou">我是采购商</button>
			<button id="gongying">我是供应商</button>
		</div>
		<div class="pad10">

			<div class="tiperror" style="text-align: center;color: red;"></div>
			<form id="registerForm" onsubmit="return false;">
				<input type="hidden" name="saleCode" value="${saleCode}"
					id="saleCode" /> <input type="hidden" name="inviterId"
					value="${inviterId}" id="inviterId" /> <input type="hidden"
					id="memberType" name='memberType' value="2">
				<div class="form-group">
					<input type="text" class="form-control" id="userName"
						name="userName" placeholder="请输入手机号">
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
				<div class="form-group">
					<input type="text" class="form-control" id="smsCode" name="smsCode"
						placeholder="短信验证码" style="width:60%;">
					<button class="yanzhenma" id="getSMSVerify">获取验证码</button>
				</div>

				<div class="form-group" style="position:relative;">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="请设置6~20位登录密码" maxlength="20" minlength ="6">

				</div>
				<div class="form-group" style="position:relative;">
					<input type="password" class="form-control" id="repassword"
						name="repassword" placeholder="请再次确认您的密码" maxlength="20" minlength ="6">

				</div>
				<div class="btn-block">
					<button type="submit" class="btn  btn-login" id="registerBtn">注册</button>
				</div>
			</form>

		</div>
	</div>
</body>
</html>
<script type="text/javascript">
				var leftbgc=document.getElementById('caigou')
				var rightbgc=document.getElementById('gongying')

				rightbgc.onclick=function  (argument) {
					//供应商memberType为1
					$("#memberType").val(1);
					
					rightbgc.style.backgroundColor='#00a0e9';
					rightbgc.style.color='#fff';

					leftbgc.style.backgroundColor='#fff';
					leftbgc.style.color='#000';
				}
					leftbgc.onclick=function  (argument) {
					//采购商memberType为2
					$("#memberType").val(2);
					
					leftbgc.style.backgroundColor='#00a0e9';
					leftbgc.style.color='#fff';

					rightbgc.style.backgroundColor='#fff';
					rightbgc.style.color='#000';

				}
									
	//end
	var msgcode;
	var intervalId;
	
	$(function(){
		$("#getSMSVerify").click(function(){
			var obj = $(this);
			var mob = $("#userName").val();
			var verifycode = $("#verifyCode").val();
			
			if(!verifycode){
				$(".tiperror").html("请输入验证码");
				return;
			}
			if(!mob){
				$(".tiperror").html("请输入手机号后获取");
				return;
			}
			if (!isMobile(mob)) {
				$(".tiperror").html("请输入正确的手机号码");
				return;
			}
			
			$.ajax({
				type:'get',
				url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/sendVerifySMS.html?mob='+
						mob+'&verifycode='+verifycode,
				success:function(e){
					if(e.success){
						msgcode = e.data;
					} else{
						$(".tiperror").html(e.message);		
						clearInterval(intervalId);
						obj.removeAttr("disabled");
						obj.text("获取短信验证码");
					}
				}
			});
			
			var time = 120;
			obj.attr("disabled",true);
			obj.text(time+"秒后重新获取");
			time -- ;
			
			intervalId = setInterval(function(){
				obj.text(time+"秒后重新获取");
				time -- ;
				if(time==0){
					clearInterval(intervalId);
					obj.removeAttr("disabled");
					obj.text("获取短信验证码");
				}
			}, 1000);  
		});
		
		$("#registerBtn").click(function(){
			var verifyCode = $("#verifyCode").val();
			if (verifyCode == null || verifyCode == "") {
				$(".tiperror").html("请输入验证码");
				return;
			}
			var userName = $("#userName").val();
			if (userName == null || userName == "") {
				$(".tiperror").html("请输入手机号");
				return;
			}
			var smsCode = $("#smsCode").val();
			if (smsCode == null || smsCode == "") {
				$(".tiperror").html("请输入手机验证码");
				return;
			}

			if(msgcode != smsCode){
				$(".tiperror").html("手机验证码错误");
				return;
			}
			var password = $("#password").val();
			if (password == null || password == "") {
				$(".tiperror").html("请输入密码");
				return;
			}
			if (password.length < 6 || password.length > 20) {
				$(".tiperror").html("请输入6-20位密码");
				return false;
			}
			var repassword = $("#repassword").val();
			if (repassword != password) {
				$(".tiperror").html("两次输入密码不一致");
				return false;
			}
			$("#registerBtn").attr("disabled","disabled");
			var params = $('#registerForm').serialize();
			$.ajax({
				type:"POST",
				url:domain+"/doregister.html",
				dataType:"json",
				async : false,
				data : params,
				success:function(data){
					if(data.success){
					   if(data.backUrl.trim() !="" ){
							window.location.href = domain + data.backUrl.trim();
						}else{
							// 跳转到用户中心
							window.location.href = domain;//+"/member/index.html";
						/* 
						var msg_ = "您已注册成功，注册有礼，大袜哥已偷偷将红包塞给您，快去查看吧。";
						window.location.href=domain+"/member/coupon/list.html";
						
				       $.dialog('confirm','提示',msg_,0,function(){
						    window.location.href=domain+"/member/coupon/list.html";
							$.closeDialog();
				       }); 
				       */
						}
					}else{
						$(".tiperror").html(data.message);
						//刷新验证码
						refreshCode();
						$("#registerBtn").removeAttr("disabled");
					}
				},
				error:function(){
					$(".tiperror").html("服务异常，请稍后重试！");
					$("#registerBtn").removeAttr("disabled");
				}
			});
		});
		
	});
	
	//刷新验证码
	function refreshCode(){
		jQuery("#code_img").attr("src","${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html?d"+new Date().getTime());
	}
</script>