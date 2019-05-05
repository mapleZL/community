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
					<a href='javascript:void(0)'>重置支付密码</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />

				<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>重置支付密码</h3>
				<div class=''id='pageTips'></div>
				<form class='pwform' id='editPasswordform'>
					<dl class='dl-setpw'>
						<dt class='dt_item'>
							<label>注册手机：</label>
						</dt>
						<dd class='dd_item p-item'>
							<input type="text" name="mob" class='txt' id="mob"  value="${member.name}" disabled="disabled"/>
							<input type='button'  class='btn btn-danger' onclick="getmobVerify(this)"
								style="padding: 1px 5px;margin-top: 5px;margin-left: 10px;" 
								value='获取验证码'>
						</dd>
					</dl>
					<dl class='dl-setpw'>
						<dt class='dt_item'>
							<label>手机验证码：</label>
						</dt>
						<dd class='dd_item p-item'>
							<input type="text" name="mobVerify" id="mobVerify" class='txt'/>
							<em class='em-errMes'></em>
						</dd>
					</dl>
					<dl class='dl-setpw'>
						<dt class='dt_item'>
							<span style='color:red'>*&nbsp;</span><label>新密码：</label>
						</dt>
						<dd class='dd_item p-item'>
							<input type='password' class='txt' id='txt-newpw' name='newPwd'>
							<em class='em-errMes'></em>
						</dd>
					</dl>
					<dl class='dl-setpw'>
						<dt class='dt_item'>
							<span style='color:red'>*&nbsp;</span><label>确认密码：</label>
						</dt>
						<dd class='dd_item p-item'>
							<input type='password' class='txt' id='txt-repw' name='confirmPwd' >
							<em class='em-errMes'></em>
						</dd>
					</dl> 
					<input type='button'  class='btn btn-danger bt_submit' id='buttonSubmit' name='button' value='确认提交'>
				</form>
			</div>
			<!-- end -->
		</div>
	<script type="text/javascript">
	function getmobVerify(this_){
		var obj = $(this_);
		var mob = $("#mob").val();
		if(typeof(obj.attr("disabled"))=="undefined"){
			if($("#editPasswordform").valid()){
				$.ajax({
					type : 'get',
					url : domain + '/mobVerify.html?mob=' + mob,
					success : function(e) {
						if (e.success) {
							//添加规则
							$("#mobVerify").rules("add", {  
								required : true,  
				                messages : {  
				                	required : "请输入手机验证码"  
				                }  
				            }); 
							$("#txt-newpw").rules("add", {  
								required : true,  
				                messages : {  
				                	required : "请输入新密码"  
				                }  
				            }); 
							$("#txt-repw").rules("add", {  
								required : true,  
								equalTo:"#txt-newpw",
				                messages : {  
				                	required : "请输入确认密码",
				                	equalTo : "两次密码输入不一致",
				                }  
				            }); 
							
							var time = 120;
							obj.attr("disabled", true);
							obj.val(time + "秒后重新获取");
							time--;
							intervalId = setInterval(function() {
								obj.val(time + "秒后重新获取");
								if (time == 0) {
									clearInterval(intervalId);
									obj.removeAttr("disabled");
									obj.val("获取验证码");
									
									$("#mobVerify").rules("remove")
									$("#txt-newpw").rules("remove")
									$("#txt-repw").rules("remove")
								}
								time--;
							}, 1000);
						} else {
							jAlert(e.message);
						}
						
					}
				});
			}
			
		}
	}
	
		$(function(){
			//控制左侧菜单选中
			$("#balance").addClass("currnet_page");
			
			//校验
			jQuery("#editPasswordform").validate({
				errorPlacement : function(error, element) {
					var obj = element.siblings(".em-errMes").css('display', 'block');
					error.appendTo(obj);
				}
		    }); 
		
		
			$(".bt_submit").click(function(){
				if($("#editPasswordform").valid()){
					$(".bt_submit").attr("disabled","disabled");
					var params = $('#editPasswordform').serialize();
					$.ajax({
						type:"POST",
						url:domain+"/member/resetbalancepassword.html",
						dataType:"json",
						async : false,
						data : params,
						success:function(data){
							if(data.success){
								jAlert('密码重置成功', '提示',function(){
									window.location.href=domain+"/member/balance.html"
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
		});
		
		
	</script>
	
<#include "/front/commons/_endbig.ftl" />
