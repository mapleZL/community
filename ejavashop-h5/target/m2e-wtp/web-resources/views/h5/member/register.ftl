<#include "/h5/commons/_head.ftl" />
<body style="background: #f2f2f2;">
	<!-- 头部 -->
	<header>
		<div class="flex flex-align-center login-header">
			<div class="padlr10 text-left">
				<a href="javascript:history.back(-1);"> <span
					class="fa fa-angle-left"></span>
				</a>
			</div>
			<div class="flex-2 text-center">注册11</div>
		</div>

	</header>
	<!-- 头部 end-->
	<div class="s-container">
		<div class="pad10">
			<div class="tiperror"></div>
			<form id="registerForm">
				<div class="form-group flex">
					<div style="padding-right: 5px;width: 270px;">
						<input class="form-control" id="userName" name="userName" placeholder="请输入手机号">
    <input type="hidden" name="saleCode" value="${saleCode}"  id="saleCode"/>
	<input type="hidden" name="inviterId" value="${inviterId}"  id="inviterId"/>
					</div>
					<div>
						<button type="button" class="btn btn-default" id="getSMSVerify"
							style="background: #bdbdbd;">此处为修改地方</button>
					</div>
				</div>
				<div class="form-group" style="position: relative;">
					<input type="text" class="form-control" id="verifyCode" name="verifyCode" placeholder="请输入验证码" maxlength="6"> 
					<span class="captcha-img" style="right: 10px; left: auto;">
						<img style="cursor: pointer;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html" id="code_img" onclick="refreshCode();" width="63" height="25" />
					</span>
				</div>
				<div class="form-group" style="position: relative;">
					<input id="smsCode" name="smsCode" class="form-control"
						placeholder="请输入短信验证码">
				</div>
				<div class="form-group" style="position: relative;">
					<input type="password" id="password" name="password"
						 class="form-control" placeholder="请设置6-20位登录密码">
				</div>
				<div class="form-group" style="position: relative;">
					<input type="password" id="repassword" name="repassword"
						 class="form-control" placeholder="确认您的密码">
				</div>
				<div class="form-group b_form-group">
				<label><font class="clr53">*</font>会员类型：</label>
				<lable>我是采购商</lable>&nbsp;&nbsp;<input type="radio" id="memberType" name="memberType" value="2" checked="checked"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<lable>我是供应商</lable>&nbsp;&nbsp;<input type="radio" id="memberType" name="memberType" value="1"/>
				<p class="clr53 font12"></p>
			</div>
				<button type="button" id="registerBtn"
					class="btn btn-block btn-login">这里是注册按钮</button>
			</form>

		</div>
	</div>
	<!-- 主体结束 -->

	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" /> 
	<#include "/h5/commons/_statistic.ftl" />

	<script type="text/javascript">
	var msgcode;
	var intervalId;
	
	$(function(){
		$("#getSMSVerify").click(function(){
			var pattern = /^1[34578]\d{9}$/;
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
			if (!pattern.test(mob)) {
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
			alert("触发");
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
							window.location = domain+"/storeregister/apply.html";
						}else{
						// 跳转到用户中心
						//window.location=domain+"/member/index.html";
						var msg_ = "您已注册成功，注册有礼，大袜哥已偷偷将红包塞给您，快去查看吧。";
				       $.dialog('confirm','提示',msg_,0,function(){
						    window.location.href=domain+"/member/coupon/list.html";
							$.closeDialog();
				       });
				      // window.location.href = domain + "/product/10.html";
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



</body>
</html>