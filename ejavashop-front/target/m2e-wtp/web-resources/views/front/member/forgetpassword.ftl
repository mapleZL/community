<!Doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	
	<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
	
	<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'>
	<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/user-bak.css">
	<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/tzm_base.css">
	<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/register.css">
	<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/member/members.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
	<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
	<script type="text/javascript">
		var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
	</script>
</head>
<body style="background: url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/bg_login.png) no-repeat center;background-position:0 80px;">
	<#--
		<img
			style="position: absolute; top: 15%; left: 0%; z-index: -1; width: 100%;"
			alt="大袜网"
			src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/bg_login.png">
	-->
	<div class='head-layout'>
		<div class='container'>
			<div class='header-wrap'>
				<div class='snlogo'>
					<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'> <img
						src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/dawawang.png'>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class='nc-login-layout container' style="margin-bottom: 342px;">
		<div style="float:left; margin-top:100px;margin-left: 250px;">
			<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/qr_code.png'>
		</div>
		<div class='nc-signup'
			style="margin-right: 190px; margin-top:50px; margin-left: 750px;">
			<div class='signup-title'>
				<h3>忘记密码</h3>
			</div>
			<div class='signup-content'>
				<form class="form-horizontal" id='forgetform'>
					<div class="form-group">
						<label class="form-label" for="inputUsernamel3">手机号：</label>
						<div class="login-box">
							<input type="text" name="name" id="mob"
								class="form-control">
							<div class="tip signup-message-email">
								<p></p>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="form-label" for="inputUsernamel3">手机验证码：</label>
						<div class="fl" style="width: 115px;">
							<input type="text" name="mobVerfiy" id="mobVerfiy"
								class="form-control">
							<div class="tip signup-message-mobVerfiy">
								<p></p>
							</div>
						</div>
						<div style="margin-left: 2px;" class="fl" onclick="getmobVerify(this)">
							<a href="javascript:void(0)" class="btn btn-default">获取验证码</a>
						</div>
					</div>
					<div class="form-group">
						<label class="form-label" for="inputPasswordl3"> 验证码：</label>
						<div class="fl">
							<input type="text" name="verifyCode" id="verifyCode"
								class="form-control" style="width: 64px;">
							<div class="tip signup-message-code">
								<p></p>
							</div>
						</div>
						<div style="margin-left: 2px;" class="fl">
							<img style="cursor: pointer;"
								src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html"
								id="code_img" onclick="refreshCode();" width="59" height="27" />
							<a href='javascript:void(0);' onclick="refreshCode();">看不清，换一张</a>
						</div>

					</div>
					<div class='form-group'>
						<label for="inputPasswordl3" class="form-label"> &nbsp;</label>
						<!-- <div class='col-sm-4 col-md-offset-3'> -->
						<div class=''>
							<!-- <a href='javascript:void(0)' id="forgetPasswordBtn" class='btn btn-danger2'>重置密码</a> -->
							<a href="javascript:void(0)" id="forgetPasswordBtn"
								class="btn btn-default">重置密码</a> <span
								style="padding-left: 50px;"> <a
								href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/login.html">立即登录</a>
								<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html">免费注册</a>
							</span>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class='nc-login-bottom' style="display: none;"></div>
		<div class='nc-login-left'></div>
	</div>
	<script type="text/javascript">
		function getmobVerify(this_){
			var obj = $(this_).find("a");
			var mob = $("#mob").val();
			if(typeof(obj.attr("disabled"))=="undefined"){
				if($("#forgetform").valid()){
					$.ajax({
						type : 'get',
						url : domain + '/mobVerify.html?mob=' + mob,
						success : function(e) {
							if (e.success) {
								//添加规则
								$("#mobVerfiy").rules("add", {  
									required : true,  
					                messages : {  
					                	required : "请输入手机验证码"  
					                }  
					            }); 
								$("#verifyCode").rules("add", {  
									required : true,  
					                messages : {  
					                	required : "请输入验证码"  
					                }  
					            }); 
								
								var time = 120;
								obj.attr("disabled", true);
								obj.text(time + "秒后重新获取");
								time--;
								intervalId = setInterval(function() {
									obj.text(time + "秒后重新获取");
									if (time == 0) {
										clearInterval(intervalId);
										obj.removeAttr("disabled");
										obj.text("获取验证码");
										
										$("#mobVerfiy").rules("remove")
										$("#verifyCode").rules("remove")
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
	
		$(function() {

			jQuery("#forgetform").validate(
					{
						errorPlacement : function(error, element) {
							var obj = element.siblings(".tip").css('display',
									'block').find('p').addClass('error');
							error.appendTo(obj);
						},
						rules : {
							"name" : {
								required : true,
								isMobile : true,
							}
						},
						messages : {
							"name" : {
								required : "请输入手机号",
								isMobile : "请输入正确的手机号"
							}
						}
					});

			$("#forgetPasswordBtn").click(function() {

				if ($("#forgetform").valid()) {
					$(".btn-danger").attr("disabled", "disabled");
					var params = $('#forgetform').serialize();
					$.ajax({
						type : "POST",
						url : domain + "/doforgetpassword.html",
						dataType : "json",
						async : false,
						data : params,
						success : function(data) {
							if (data.success) {
								jAlert(data.data, '提示', function() {
									window.location = domain + "/login.html"
								});
							} else {
								jAlert(data.message);
								refreshCode();//刷新验证码
								$(".btn-danger").removeAttr("disabled");
							}
						}/* ,
										error:function(){
											jAlert("异常，请重试！");
											$(".btn-danger").removeAttr("disabled");
										} */
					});
				}

			});
		});
	</script>
	<#include "/front/commons/_endbig.ftl" />