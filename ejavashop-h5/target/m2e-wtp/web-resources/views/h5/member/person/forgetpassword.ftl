<#include "/h5/commons/_head.ftl" />
<body class="bgf2">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
		 </div>
   	  	 <div class="flex-2 text-center">忘记密码</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
	
	<div class="s-container">
	  <div>

		  <div class="user-infor user-infor2 bgff mar-bt">
		    <form id="forgetform">
		  	 <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">手机号码:</div>
                <div class="flex-2">
                	<input type="text" class="form-control" id="mobile" name="mobile" placeholder="请输入注册的手机号码">
                </div>
		  	 </div>
		  	 <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">手机验证码:</div>
                <div class="flex-2">
                	<input type="text" class="form-control" id="mobVerfiy" 
                		name="mobVerfiy" placeholder="手机验证码" style="width: 165px;float: left;">
		  	 		<button class="btn btn-login" type="button" 
		  	 			style="padding:6px 12px;" onclick="getmobVerify(this)">获取验证码</button>
                </div>
		  	 </div>
		  	 <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">验证码:</div>
                <div class="flex-2"  style="position:relative;">
                	<input type="text" class="form-control" id="verifyCode" name="verifyCode" 
                		placeholder="请输入验证码" maxlength="6" style="width:50%">
			  	    <span class="captcha-img">
			  	    	<img style="cursor:pointer;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html" 
			  	    		id="code_img" onclick="refreshCode();" width="63" height="25" />
			  	    </span>
                </div>
                
		  	 </div>
		  	 <br>
             <label id="errLabel" style="color:red"></label>
		  	 
		  	 <div class="text-center padt_b10">
		  	 	 <button class="btn btn-login" type="button" style="padding:6px 12px;" 
		  	 	 	id='forgetPasswordBtn'>重置密码</button>
		  	 </div>
		  	 
		  	</form>
		  </div>

	  </div>
    </div>
	<!-- 主体结束 -->

	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

<script type="text/javascript">
	function getmobVerify(this_){
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
							obj.val("获取验证码");
						}
						time--;
					}, 1000);
				} else {
// 					jAlert(e.message);
					$.dialog('alert','提示',e.message);
				}
				
			}
		});
	}

	$(function(){
		$("#forgetPasswordBtn").click(function(){
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

			$("#forgetPasswordBtn").attr("disabled","disabled");
			var params = $('#forgetform').serialize();
			$.ajax({
				type:"POST",
				url:domain+"/doforgetpassword.html",
				dataType:"json",
				data : params,
				success:function(data){
					if(data.success){
						// alert("密码重置成功，请查收您的短信！");
						$.dialog('alert','提示',data.data,2000,function(){ 
							window.location=domain+"/login.html"; 
						});
					}else{
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
	function refreshCode(){
		jQuery("#code_img").attr("src","${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html?d"+new Date().getTime());
	}
</script>

</body>
</html>