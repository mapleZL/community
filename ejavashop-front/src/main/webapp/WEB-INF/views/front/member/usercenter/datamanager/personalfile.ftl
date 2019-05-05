<#include "/front/commons/_headbig.ftl" />
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/My97DatePicker/WdatePicker.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
		<div class='container w'>
			<div class='breadcrumb'>
				<strong class='business-strong'>
					<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>首页</a>
				</strong>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>大袜网</a>
				</span>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>我的个人资料</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />

			<!-- 右侧主要内容 -->
			<div class='wrapper_main'>
				<h3>我的个人资料</h3>
				<ul class="list-group ul-group">
					  <li class="list-group-item">
					  	<span class='s-infotitle'>生日：${(member.birthday?string("yyyy-MM-dd"))!''}</span>
					  </li>
					  <li class="list-group-item">
					  	<span class='s-infotitle'>邮箱：${(member.email)!''}</span>
					  </li>
					  <li class="list-group-item">
					  	<span class='s-infotitle'>电话：${(member.phone)!''}<span>
					  </li>
				</ul>
				<ul class="list-group">
					  <li class="list-group-item">
					  	<span class='s-infotitle'>性别：<span id="gendershow"> 
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
					  		 </span>  </span>
					  </li>
					  <li class="list-group-item">
					  	<span class='s-infotitle'>QQ：${(member.qq)!''}</span>
					  </li>
					  <li class="list-group-item">
					  	<span class='s-infotitle'>手机：${(member.mobile)!''}<span>
					  </li>
				</ul>
				<form class='myinfo' id='personalfileForm'>
					<dl class='dl_col1'>
						<dt>
							<label>用户名：</label>
						</dt>
						<dd>${(member.name)!''} </dd>
						<dt>			
							<label>生日：</label>
						</dt>
						<dd class='p-item'>
							 <input id="birthday" name="birthday"  maxlength="10" class="input-medium Wdate"   
              							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});" value="${(member.birthday?string("yyyy-MM-dd"))!''}"/>
						</dd>
						<dt>
							<label>性别：</label>
						</dt>
						<dd class='p-item'>
							<@cont.radio id="q_gender" name="gender" value="${(member.gender)!''}" codeDiv="GENDER" />
						</dd>
						<dt>
							<label>电话：</label>
						</dt>
						<dd class='p-item'>
							<input type='text' value="${(member.phone)!''}" name='phone' maxlength="15" class='txt txt-err'>
							<em class='em-errMes'></em>
						</dd>
						<!-- mobile -->
						<dt class='p-item'>
							<label for='verifyPhone'>手机 
									<#if member??>
										<#if (member.isSmsVerify)=1>（已绑定）：
										<#elseif (member.isSmsVerify)=0>（未绑定）：
										</#if>
									<#else>未绑定：
									</#if> 
							</label>
						</dt>
						<dd class='p-item'>
							<input type='text' name="mobile" value="${(member.mobile)!''}" class='txt txt-err' maxlength="11" id='curMobile'>
							<#if member??>
								<#if (member.isSmsVerify)=1>
									<input type='button' class='veributton btn btn-default bt_red' id="unSmsVerif" value='解除绑定'>
								<#elseif (member.isSmsVerify)=0>
									<input type='button' class='veributton btn btn-default bt_red' id="sendSmsVerif" value='获取验证码'>
								</#if>
							</#if> 
							<em class='em-errMes' id="mobile-error"></em>
						</dd>
						<!-- end -->
						<#if member??>
							<#if (member.isSmsVerify)=0>
								<dt>
									<label>请输入验证码：</label>
								</dt>
								<dd class='p-item'>
									<input type='text' name='smsVerifyCode' maxlength="6" id='smsVerifyCode' class='txt txt-err verifyCode'>
									<input type='button' class='btn btn-primary bt_blue' id='bindMobileButton' value='绑定手机'>
									<em class='em-errMes'></em>
								</dd>
							</#if>
						</#if> 
						<dt>
							<label>邮箱
									<#if member??>
										<#if (member.isEmailVerify)=1>（已验证）：
										<#elseif (member.isEmailVerify)=0>（未验证）：
										</#if>
									<#else>未验证：
									</#if> 
									
							</label>
						</dt>
						<dd>
							<p class='p-item'>
									
								<input id='curEmail1' type='text' maxlength="60" name="email" class='txt txt-err' value='${(member.email)!''}' >
								<#if member??>
									<#if (member.isEmailVerify)=0>
										<input id='sendEmailVerify'  class='veributton btn btn-default bt_red' type='button' value='发送验证邮件'>
									<#elseif (member.isEmailVerify)=1>
										<input id='unEmailVerify'  class='veributton btn btn-default bt_red' type='button' value='解除绑定'>
									</#if>
								</#if> 
								
								<em class='em-errMes'></em>                                      
							</p>
						</dd>
						<dt>
							<label>QQ：</label>
						</dt>
						<dd class='p-item'>
							<input type='text' value='${(member.qq)!''}' name='qq' class='txt' maxlength="15">
						</dd>
						<dt></dt>
						<dd class='p-item'>
							<input type='button' value='确认提交' class='bt_submit'>
						</dd>
					</dl>
				</form>
			</div>

			<!-- end -->
		</div>
	<script type="text/javascript">
		$(function(){
			//控制左侧菜单选中
			$("#personalfile").addClass("currnet_page");
			
			//校验
			jQuery("#personalfileForm").validate({
				errorPlacement : function(error, element) {
					var obj = element.siblings(".em-errMes").css('display', 'block');
					error.appendTo(obj);
				},
		        rules : {
		            "phone":{required:false},
		            "mobile":{required:false,isMobile:true},
		            "smsVerifyCode":{required:false},
		            "email":{required:false,isEmail:true}
		        },
		        messages:{
		        	"phone":{required:"请输入电话"},
		            "mobile":{required:"请输入手机号"},
		            "smsVerifyCode":{required:"请输入验证码"},
		            "email":{required:"请输入邮箱"}
		        }
		    }); 
		
		
			$(".bt_submit").click(function(){
				if($("#personalfileForm").valid()){
					$(".bt_submit").attr("disabled","disabled");
					var params = $('#personalfileForm').serialize();
					$.ajax({
						type:"POST",
						url:domain+"/member/saveinfo.html",
						dataType:"json",
						async : false,
						data : params,
						success:function(data){
							if(data.success){
								//jAlert("保存成功");
								//重新加载数据
								//window.location.href=domain+"/member/info.html";
								jAlert('保存成功', '提示',function(){
									window.location.href=domain+"/member/info.html"
								});
							}else{
								jAlert(data.message);
								$(".bt_submit").removeAttr("disabled");
							}
						},
						error:function(){
							jAlert("异常，请重试！");
							$(".bt_submit").removeAttr("disabled");
						}
					});
				}
				
			});
			
			$("#sendSmsVerif").click(function(){
				var curMobile = $("#curMobile").val();
				if (curMobile == null || curMobile == "") {
					// $("#mobile-error").html("请输入手机号码！");
					// $("#mobile-error").css('display', 'block');
					jAlert("请输入手机号码！");
					return;
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
							jAlert("验证码发送成功，请查收短信！");
						}else{
							jAlert(data.message);
							$("#sendSmsVerif").removeAttr("disabled");
						}
					},
					error:function(){
						$("#sendSmsVerif").removeAttr("disabled");
					}
				});
			});
			
			$("#bindMobileButton").click(function(){
				var smsVerifyCode = $("#smsVerifyCode").val();
				if (smsVerifyCode == null || smsVerifyCode == "") {
					jAlert("请输入验证码！");
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
							//jAlert("绑定成功！");
							//重新加载数据
							//window.location.href=domain+"/member/info.html";
							
							jAlert('绑定成功！', '提示',function(){
								window.location.href=domain+"/member/info.html"
							});
						}else{
							jAlert(data.message);
							$("#bindMobileButton").removeAttr("disabled");
						}
					},
					error:function(){
						$("#bindMobileButton").removeAttr("disabled");
					}
				});
			});
			
			$("#unSmsVerif").click(function(){
				if(confirm("确定要解除绑定吗？")){
					$("#unSmsVerif").attr("disabled","disabled");
					$.ajax({
						type:"POST",
						url:domain+"/member/unsmsverif.html",
						dataType:"json",
						async : false,
						data : {},
						success:function(data){
							if(data.success){
								//jAlert("解除绑定成功！");
								//重新加载数据
								//window.location.href=domain+"/member/info.html";
								
								jAlert('解除绑定成功！', '提示',function(){
									window.location.href=domain+"/member/info.html"
								});
							}else{
								jAlert(data.message);
								$("#unSmsVerif").removeAttr("disabled");
							}
						},
						error:function(){
							$("#unSmsVerif").removeAttr("disabled");
						}
					});
				}
			});
			
			$("#sendEmailVerify").click(function(){
				var curEmail1 = $("#curEmail1").val();
				if (curEmail1 == null || curEmail1 == "") {
					// $("#mobile-error").html("请输入手机号码！");
					// $("#mobile-error").css('display', 'block');
					jAlert("请输入邮箱地址！");
					return;
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
							jAlert("验证码发送成功，请查收邮件！");
						}else{
							jAlert(data.message);
							$("#sendEmailVerify").removeAttr("disabled");
						}
					},
					error:function(){
						$("#sendEmailVerify").removeAttr("disabled");
					}
				});
			});
			
			$("#unEmailVerify").click(function(){
				if(confirm("确定要解除绑定吗？")){
					$("#unEmailVerify").attr("disabled","disabled");
					$.ajax({
						type:"POST",
						url:domain+"/member/unemailverif.html",
						dataType:"json",
						async : false,
						data : {},
						success:function(data){
							if(data.success){
								//jAlert("解除绑定成功！");
								//重新加载数据
								//window.location.href=domain+"/member/info.html";
								
								jAlert('解除绑定成功！', '提示',function(){
									window.location.href=domain+"/member/info.html"
								});
							}else{
								jAlert(data.message);
								$("#unEmailVerify").removeAttr("disabled");
							}
						},
						error:function(){
							$("#unEmailVerify").removeAttr("disabled");
						}
					});
				}
			});
			
			
		});
		
		
	</script>
	
<#include "/front/commons/_endbig.ftl" />
