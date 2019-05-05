<#import "/h5/commons/_macro_controller.ftl" as cont/>
<#include "/h5/commons/_head.ftl" />
<body class="bgf2">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/index.html">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
		 </div>
   	  	 <div class="flex-2 text-center">个人资料</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
	
	<div class="s-container">
	  <div>

		  <div class="user-infor bgff mar-bt">
		  	 <div class="pad10 flex flex-pack-justify bor-b2">
		  	 	<div>生日</div>
		  	 	<div>${(member.birthday?string("yyyy-MM-dd"))!''}</div>
		  	 </div>
		  	 <div class="pad10 flex flex-pack-justify bor-b2">
		  	 	<div>性别</div>
		  	 	<div>
		  	 		<#if member??>
		  			<#if  member.gender??>
		  				<#assign gender = member.gender>
			  				<#if gender==0>保密
			  				<#elseif gender==1>男
			  				<#elseif gender==2>女
			  				<#else>
		  				</#if>
		  		    </#if>
		  		  	</#if>
				</div>
		  	 </div>
		  	 <div class="pad10 flex flex-pack-justify bor-b2">
		  	 	<div>邮箱</div>
		  	 	<div>${(member.email)!''}</div>
		  	 </div>
		  	 <div class="pad10 flex flex-pack-justify bor-b2">
		  	 	<div>QQ</div>
		  	 	<div>${(member.qq)!''}</div>
		  	 </div>
		  	 <div class="pad10 flex flex-pack-justify bor-b2">
		  	 	<div>电话</div>
		  	 	<div>${(member.phone)!''}</div>
		  	 </div>
		  	 <div class="pad10 flex flex-pack-justify bor-b2">
		  	 	<div>手机</div>
		  	 	<div>${(member.mobile)!''}</div>
		  	 </div>
		  </div>

		  <div class="user-infor bgff mar-bt" style="margin-top:30px;">
		    <form id='personalfileForm'>
		  	 <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">用户名:</div>
                <div class="flex-2">${(member.name)!''}</div>
		  	 </div>
		  	 <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">生日:</div>
                <div class="flex-2"><input type="text" id="birthday" name="birthday" value="${(member.birthday?string('yyyy-MM-dd'))!''}" placeholder="1985-4-18"></div>
		  	 </div>
		  	 <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">性别:</div>
		  	 	<div class="flex-2">
		  	 		<@cont.radio id="q_gender" name="gender" value="${(member.gender)!''}" codeDiv="GENDER" />
		  	 	  <!-- <span><input type="radio">保密</span>
		  	 	  <span><input type="radio">男</span>
		  	 	  <span><input type="radio">女</span> -->
		  	 	</div>
		  	 </div>
		  	 <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">电话:</div>
		  	 	<div class="flex-2"><input type="tel" id='phone' name='phone' value="${(member.phone)!''}" placeholder=""></div>
		  	 </div>
		  	 <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">手机:
		  	 		<br>
		  	 			<#if member??>
							<#if (member.isSmsVerify)=1>(已绑定)
							<#elseif (member.isSmsVerify)=0>(未绑定)
							</#if>
						<#else>(未绑定)
						</#if> 
				</div>
		  	 	<div class="flex-1">
		  	 	  <input type="tel" id="curMobile" name="mobile" value="${(member.mobile)!''}"  placeholder="请输入手机号码">
		  	 	</div>
		  	 	<div class="flex-1 text-right">
			 	  <#if member??>
					  <#if (member.isSmsVerify)=1>
						  <button class="btn" type="button" id="unSmsVerif">解除绑定</button>
					  <#elseif (member.isSmsVerify)=0>
						  <button class="btn" type="button" id="sendSmsVerif">获取短信验证码</button>
					  </#if>
				  </#if> 
		  	 	</div>
		  	 </div>
		  	 <#if member??>
				<#if (member.isSmsVerify)=0>
					<div class="pad10 flex bor-b2 input-line">
				  	 	<div class="u-label">验证码:</div>
				  	 	<div class="flex-1"><input type="text" name='smsVerifyCode' maxlength="6" id='smsVerifyCode'  placeholder="请输入验证码"></div>
				  	 	<div class="flex-1 text-right"><button class="btn btn-primary" type="button" id='bindMobileButton'>绑定手机</button></div>
				  	</div>
				</#if>
			 </#if> 
		  	 <!-- <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">验证码:</div>
		  	 	<div class="flex-1"><input type="text" name="text"  placeholder="请输入验证码"></div>
		  	 	<div class="flex-1 text-right"><button class="btn btn-primary" type="button">绑定手机</button></div>
		  	 </div> -->
		  	 
		  	 <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">邮箱:
		  	 		<br>
		  	 		<#if member??>
						<#if (member.isEmailVerify)=1>(已验证)
						<#elseif (member.isEmailVerify)=0>(未验证)
						</#if>
					<#else>(未验证)
					</#if>
		  	 	</div>
		  	 	<div class="flex-2">
		  	 	  <input type="tel" id='curEmail1' name="email" value="${(member.email)!''}" placeholder="请输入邮箱">
		  	 	</div>
		  	 	<div class=" text-right">
			  	 	<#if member??>
						<#if (member.isEmailVerify)=0>
							<button class="btn" type="button" id='sendEmailVerify'>发送验证邮件</button>
						<#elseif (member.isEmailVerify)=1>
							<button class="btn" type="button" id='unEmailVerify'>解除绑定</button>
						</#if>
					</#if>
		  	 	</div>
		  	 </div>
		  	 <div class="pad10 flex bor-b2 input-line">
		  	 	<div class="u-label">QQ:</div>
		  	 	<div class="flex-2"><input type="number" id='qq' name='qq' value="${(member.qq)!''}" placeholder="请输入QQ"></div>
		  	 </div>
		  	 
             <div style="padding:10px 0 0 10px;"><font id="errLabel" class="font12 clr53"></font></div>
		  	 <div class="text-center padt_b10" style="margin-bottom: 40px;">
		  	 	 <button class="btn btn-login" type="submit" style="padding:6px 12px;" id="personSubmit">确认提交</button>
		  	 </div>
		  	 
		  	</form>
		  </div>
		
	  </div>
    </div>
	<!-- 主体结束 -->

	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />


 <!--日历-->
<link href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/selectdate/mobiscroll.css" rel="stylesheet" type="text/css">
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/selectdate/mobiscroll_002.js" type="text/javascript"></script>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/selectdate/mobiscroll.js" type="text/javascript"></script>   
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/selectdate/mobiscroll_003.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		/*日历*/
		var currYear = (new Date()).getFullYear();
		var opt={};
		opt.date = {preset : 'date'};
		opt.datetime = {preset : 'datetime'};
		opt.time = {preset : 'time'};
		opt.default = {
			theme: 'android-ics', //皮肤样式
			display: 'modal', //显示方式 
			mode: 'scroller', //日期选择模式
			dateFormat: 'yyyy-mm-dd',
			lang: 'zh',
			showNow: false,
			startYear: currYear - 50, //开始年份
			endYear: currYear + 10 //结束年份
		};
		$("#birthday").mobiscroll($.extend(opt['date'], opt['default']));				

		$("#personSubmit").click(function(){
			// 校验
			var phone = $("#phone").val();
			if (phone != null && phone.length > 0) {
				if (!isPhone(phone)) {
					$("#errLabel").html("<i class='fa fa-warning'></i> 请输入正确的电话号码，如：010-88888888");
					return false;
				}
			}
			
			var curMobile = $("#curMobile").val();
			if (curMobile != null && curMobile.length > 0) {
				if (!isMobile(curMobile)) {
					$("#errLabel").html("<i class='fa fa-warning'></i> 请输入正确的手机号码");
					return false;
				}
			}
			
			var curEmail1 = $("#curEmail1").val();
			if (curEmail1 != null && curEmail1.length > 0) {
				if (!isEmail(curEmail1)) {
					$("#errLabel").html("<i class='fa fa-warning'></i> 请输入正确的邮箱地址");
					return false;
				}
			}
			
			var phone = $("#phone").val();
			if (phone != null && phone.length > 0) {
				if (!isPhone(phone)) {
					$("#errLabel").html("<i class='fa fa-warning'></i> 请输入正确的电话号码");
					return false;
				}
			}
			
			$("#personSubmit").attr("disabled","disabled");
			var params = $('#personalfileForm').serialize();
			$.ajax({
				type:"POST",
				url:domain+"/member/saveinfo.html",
				dataType:"json",
				async : false,
				data : params,
				success:function(data){
					if(data.success){
						// alert("保存成功");
						//重新加载数据
						// window.location.href=domain+"/member/info.html";
						$.dialog('alert','提示','保存成功',2000,function(){ window.location.href=domain+"/member/info.html"; });
					}else{
						// alert(data.message);
						// $("#personSubmit").removeAttr("disabled");
						$.dialog('alert','提示',data.message,2000,function(){ $("#personSubmit").removeAttr("disabled"); });
					}
				}
			});
		});
		
		$("#sendSmsVerif").click(function(){
			var curMobile = $("#curMobile").val();
			if (curMobile == null || curMobile == "") {
				// alert("请输入手机号码！");
				$.dialog('alert','提示','请输入手机号码！',2000);
				return;
			}
			if (!isMobile(curMobile)) {
				$("#errLabel").html("请输入正确的手机号码");
				return false;
			}
			$("#sendSmsVerif").attr("disabled","disabled");
			$.ajax({
				type:"POST",
				url:domain+"/member/sendsmsverif.html",
				dataType:"json",
				async : false,
				data : {mobile:curMobile},
				success:function(data){
					if(data.success){
						// alert("验证码发送成功，请查收短信！");
						$.dialog('alert','提示','验证码发送成功，请查收短信！',2000);
					}else{
						// alert(data.message);
						// $("#sendSmsVerif").removeAttr("disabled");
						$.dialog('alert','提示',data.message,2000,function(){ $("#sendSmsVerif").removeAttr("disabled"); });
					}
				}
			});
		});
		
		$("#bindMobileButton").click(function(){
			var smsVerifyCode = $("#smsVerifyCode").val();
			if (smsVerifyCode == null || smsVerifyCode == "") {
				// alert("请输入验证码！");
				$.dialog('alert','提示','请输入验证码！',2000);
				return;
			}
			$("#bindMobileButton").attr("disabled","disabled");
			$.ajax({
				type:"POST",
				url:domain+"/member/smsverif.html",
				dataType:"json",
				async : false,
				data : {verif:smsVerifyCode},
				success:function(data){
					if(data.success){
						// alert("绑定成功！");
						// 重新加载数据
						// window.location.href=domain+"/member/info.html";
						$.dialog('alert','提示','绑定成功！',2000,function(){ window.location.href=domain+"/member/info.html"; });
					}else{
						// alert(data.message);
						// $("#bindMobileButton").removeAttr("disabled");
						$.dialog('alert','提示',data.message,2000,function(){ $("#bindMobileButton").removeAttr("disabled"); });
					}
				}
			});
		});
		
		$("#unSmsVerif").click(function(){
			/* if(confirm("确定要解除绑定吗？")){
				$("#unSmsVerif").attr("disabled","disabled");
				$.ajax({
					type:"POST",
					url:domain+"/member/unsmsverif.html",
					dataType:"json",
					async : false,
					data : {},
					success:function(data){
						if(data.success){
							// alert("解除绑定成功！");
							// 重新加载数据
							// window.location.href=domain+"/member/info.html";
							$.dialog('alert','提示','解除绑定成功！',2000,function(){ window.location.href=domain+"/member/info.html"; });
						}else{
							// alert(data.message);
							// $("#unSmsVerif").removeAttr("disabled");
							$.dialog('alert','提示',data.message,2000,function(){ $("#unSmsVerif").removeAttr("disabled"); });
						}
					}
				});
			} */
			$.dialog('confirm','提示','确定要解除绑定吗？',0,function(){
				$("#unSmsVerif").attr("disabled","disabled");
				$.ajax({
					type:"POST",
					url:domain+"/member/unsmsverif.html",
					dataType:"json",
					async : false,
					data : {},
					success:function(data){
						if(data.success){
							// alert("解除绑定成功！");
							// 重新加载数据
							$.dialog('alert','提示','解除绑定成功！',2000,function(){ window.location.href=domain+"/member/info.html"; });
						}else{
							// alert(data.message);
							$.dialog('alert','提示',data.message,2000,function(){ $("#unSmsVerif").removeAttr("disabled"); });
						}
					}
				});
			});
		});
		
		$("#sendEmailVerify").click(function(){
			var curEmail1 = $("#curEmail1").val();
			if (curEmail1 == null || curEmail1 == "") {
				// alert("请输入邮箱地址！");
				$.dialog('alert','提示','请输入邮箱地址！',2000);
				return;
			}
			if (!isEmail(curEmail1)) {
				$("#errLabel").html("请输入正确的邮箱地址");
				return false;
			}
			$("#sendEmailVerify").attr("disabled","disabled");
			$.ajax({
				type:"POST",
				url:domain+"/member/sendemailverif.html",
				dataType:"json",
				async : false,
				data : {email:curEmail1},
				success:function(data){
					if(data.success){
						// alert("验证码发送成功，请查收邮件！");
						$.dialog('alert','提示','验证码发送成功，请查收邮件！',2000);
					}else{
						// alert(data.message);
						// $("#sendEmailVerify").removeAttr("disabled");
						$.dialog('alert','提示',data.message,2000,function(){ $("#sendEmailVerify").removeAttr("disabled"); });
					}
				},
				error:function(){
					$("#sendEmailVerify").removeAttr("disabled");
				}
			});
		});
		
		$("#unEmailVerify").click(function(){
			/* if(confirm("确定要解除绑定吗？")){
				$("#unEmailVerify").attr("disabled","disabled");
				$.ajax({
					type:"POST",
					url:domain+"/member/unemailverif.html",
					dataType:"json",
					async : false,
					data : {},
					success:function(data){
						if(data.success){
							// alert("解除绑定成功！");
							//重新加载数据
							// window.location.href=domain+"/member/info.html";
							$.dialog('alert','提示','解除绑定成功！',2000,function(){ window.location.href=domain+"/member/info.html"; });
						}else{
							// alert(data.message);
							// $("#unEmailVerify").removeAttr("disabled");
							$.dialog('alert','提示',data.message,2000,function(){ $("#unEmailVerify").removeAttr("disabled"); });
						}
					},
					error:function(){
						$("#unEmailVerify").removeAttr("disabled");
					}
				});
			} */
			$.dialog('confirm','提示','确定要解除绑定吗？',0,function(){
				$("#unEmailVerify").attr("disabled","disabled");
				$.ajax({
					type:"POST",
					url:domain+"/member/unemailverif.html",
					dataType:"json",
					async : false,
					data : {},
					success:function(data){
						if(data.success){
							// alert("解除绑定成功！");
							//重新加载数据
							// window.location.href=domain+"/member/info.html";
							$.dialog('alert','提示','解除绑定成功！',2000,function(){ window.location.href=domain+"/member/info.html"; });
						}else{
							// alert(data.message);
							// $("#unEmailVerify").removeAttr("disabled");
							$.dialog('alert','提示',data.message,2000,function(){ $("#unEmailVerify").removeAttr("disabled"); });
						}
					},
					error:function(){
						$("#unEmailVerify").removeAttr("disabled");
					}
				});
			});
		});
	});
	
</script>


</body>
</html>