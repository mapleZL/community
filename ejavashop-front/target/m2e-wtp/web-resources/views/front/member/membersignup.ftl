<!Doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<script type="text/javascript">
			var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
			var msgcode;
			var intervalId;
			if(/AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE|HTC/.test(navigator.userAgent))){
			if(window.location.href.indexOf("?mobile")<0){
				try{
					var url = location.href;
				    var sp = url.split("?");
				    var mobileUrl = "${(domainUrlUtil.EJS_H5_URL)!}/register.html?"+ sp[1];
				    var mobileUrl1 = "${(domainUrlUtil.EJS_H5_URL)!}/register.html";
					if(/iPad|Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){
						if (sp.length > 1){
							window.location = mobileUrl;
						}else{
							window.location = mobileUrl1;
						}
					}else{
						if (sp.length > 1){
							window.location = mobileUrl;
						}else{
							window.location = mobileUrl1;
						}
					}
				}catch(e){}
			}
		}
		</script>
		
		<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		
		<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'>
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/user.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/base.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/register.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>

		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/member/members.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
	</head>
	<body style="overflow: hidden;">
	<input type="hidden" name="saleCode" value="${saleCode}"  id="saleCode"/>
    <input type="hidden" name="inviterId" value="${inviterId}"  id="inviterId"/>
		<div class='head-layout'>
			<div class='container'>
				<div class='header-wrap'>
					<div class='snlogo'>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>
							<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/dawawang.png'>
						</a>
					</div>
				</div>
				<span>用户注册</span>
			</div>
		</div>
		<div class="body-layout">
			<div class='row'>
				<div class="col-sm-6">
					<div class="signup-content">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary active" id="label_cgs">
								<input type="radio" name="options" id="options1" >我是采购商
							</label>
							<label class="btn btn-primary" id="label_gys">
								<input type="radio" name="options" id="options2" >我是供应商
							</label>
						</div>
						<!--华丽的分隔线  -->
					<ul id="myTab" class="nav nav-tabs">
						<li class="active">
							<a href="#home" data-toggle="tab">W3Cschool Home </a>
						</li>
						<li>
							<a href="#ios" data-toggle="tab">iOS</a>
						</li>
					</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="home">
							<form class="form-horizontal forms-group" id='form' method="POST">
								  <div class='reg-switch-wrap'>
									<div class="form-group reg-email reg-switch " >
									    <label for="inputEmail3" class="form-label">手机号：</label>
									    <div class="login-box">
									      	<input type="text" class="form-control" id="userName" name='name' placeholder="请输入手机号">
									      	<div class='tip signup-message-email'>
								      			<p></p>
								      		</div>
									    </div>
								  	</div>
								  	<div class="form-group">
									    <label for="inputPasswordl3" class="form-label"> 验证码：</label>
									    <div class="code-box">
									      	<input type="text" class="form-control" id="verifyCode" name="verifyCode" style="width: 80px;">
									      	<div class='tip signup-message-code'>
									      		<p></p>
									      	</div>
									    </div>
									    <div class='fl' style='margin-left:25px;'>
									    	<img style="cursor:pointer;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html" id="code_img" onclick="refreshCode();" width="59" height="27" />
									    	 <a href='javascript:void(0);' onclick="refreshCode();">看不清，换一张</a>
									    </div>
								  	</div>
								</div>
							  	<div class="form-group">
							  		<label for="inputPasswordl3" class="form-label">短信验证码：</label>
							  		 <div class="login-box" style="width: 207px;">
										<input id="smsCode" name="smsCode" class="form-control" style="width: 80px;display: inline;" placeholder="">
											<button type="button" class="btn btn-default" id="getSMSVerify">获取短信验证码</button>
										<div class='tip signup-message-smscode'>
								      		<p></p>
								      	</div>
									</div>
								</div>
							  	<div class="form-group">
								    <label for="inputPasswordl3" class="form-label">设置密码：</label>
								    <div class="login-box">
								      	<input type="password" class="form-control" id="password" name='password'>
								      	<div class='tip signup-message-password'>
								      		<p></p>
								      	</div>
								    </div>
							  	</div>
							  	<div class="form-group">
								    <label for="inputPasswordl3" class="form-label">确认密码：</label>
								    <div class="login-box">
								      	<input type="password" class="form-control" id="repassword" name='repassword'>
								      	<div class='tip signup-message-confirmpsw'>
								      		<p></p>
								      	</div>
								    </div>
							  	</div>
							  	<div class='form-group'>
							  		<div class='ml70'>
								  		<div class="checkbox">
								            <label>
								               <input type="checkbox"> 我已阅读并同意<a href="./service_protocol.html">&lt;&lt;大袜网用户服务协议&gt;&gt;</a>
								            </label>
								         </div>
									  	<!-- <input type='checkbox' class='agree' name="acceptProtocol" id="acceptProtocol">
									  	<span class='protocol'>阅读并同意<a href='./service_protocol.html'>服务协议</a></span> -->
									</div>
								</div>
							  	<div class='form-group'>
							  		<div class='ml70'>
							  			<a href='javascript:void(0)' id="signupButton"  class='btn btn-danger'>立即注册</a>
							  		</div>
							  	</div>
							</form>
						</div>
						<div class="tab-pane fade" id="ios">
							<form class="form-horizontal forms-group" id='form' method="POST">
								  <div class='reg-switch-wrap'>
									<div class="form-group reg-email reg-switch " >
									    <label for="inputEmail3" class="form-label">手机号：</label>
									    <div class="login-box">
									      	<input type="text" class="form-control" id="userName" name='name' placeholder="请输入手机号">
									      	<div class='tip signup-message-email'>
								      			<p></p>
								      		</div>
									    </div>
								  	</div>
								  	<div class="form-group">
									    <label for="inputPasswordl3" class="form-label"> 验证码：</label>
									    <div class="code-box">
									      	<input type="text" class="form-control" id="verifyCode" name="verifyCode">
									      	<div class='tip signup-message-code'>
									      		<p></p>
									      	</div>
									    </div>
									    <div class='fl' style='margin-left:2px;'>
									    	<img style="cursor:pointer;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html" id="code_img" onclick="refreshCode();" width="59" height="27" />
									    	 <a href='javascript:void(0);' onclick="refreshCode();">看不清，换一张</a>
									    </div>
								  	</div>
								</div>
							  	<div class="form-group">
								    <div class="sms-verify">
								      	<button type="button" class="btn btn-default" id="getSMSVerify">
								      		获取短信验证码</button>
								      	<div class='tip signup-message-sms'>
								      		<p></p>
								      	</div>
								    </div>
							  	</div>
							  	<div class="form-group" style="position: relative;">
							  		<label for="inputPasswordl3" class="form-label">短信验证码：</label>
							  		 <div class="login-box">
										<input id="smsCode" name="smsCode" class="form-control"
											placeholder="请输入短信验证码">
										<div class='tip signup-message-smscode'>
								      		<p></p>
								      	</div>
									</div>
								</div>
							  	<div class="form-group">
								    <label for="inputPasswordl3" class="form-label">设置密码：</label>
								    <div class="login-box">
								      	<input type="password" class="form-control" id="password" name='password'>
								      	<div class='tip signup-message-password'>
								      		<p></p>
								      	</div>
								    </div>
							  	</div>
							  	<div class="form-group">
								    <label for="inputPasswordl3" class="form-label">确认密码：</label>
								    <div class="login-box">
								      	<input type="password" class="form-control" id="repassword" name='repassword'>
								      	<div class='tip signup-message-confirmpsw'>
								      		<p></p>
								      	</div>
								    </div>
							  	</div>
							  	<div class='form-group'>
							  		<div class='ml70'>
							  			<a href='javascript:void(0)' id="signupButton"  class='btn btn-danger'>立即注册</a>
							  		</div>
							  	</div>
							  	<div class='form-group'>
							  		<div class='ml70'>
									  	<input type='checkbox' class='agree' name="acceptProtocol" id="acceptProtocol">
									  	<span class='protocol'>我已阅读并同意<a href='./service_protocol.html'>服务协议</a></span>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class='apply-agreement-content'>
					<p>
						使用本公司服务所须遵守的条款和条件。 <br> <br> 1.用户资格 <br>
						本公司的服务仅向适用法律下能够签订具有法律约束力的合同的个人提供并仅由其使用。在不限制前述规定的前提下，本公司的服务不向18周岁以下或被临时或无限期中止的用户提供。如您不合资格，请勿使用本公司的服务。此外，您的账户（包括信用评价）和用户名不得向其他方转让或出售。另外，本公司保留根据其意愿中止或终止您的账户的权利。
						<br> <br> 2.您的资料（包括但不限于所添加的任何商品）不得： <br>
						*具有欺诈性、虚假、不准确或具误导性； <br>
						*侵犯任何第三方著作权、专利权、商标权、商业秘密或其他专有权利或发表权或隐私权； <br>
						*违反任何适用的法律或法规（包括但不限于有关出口管制、消费者保护、不正当竞争、刑法、反歧视或贸易惯例/公平贸易法律的法律或法规）；
						<br> *有侮辱或者诽谤他人，侵害他人合法权益的内容； <br>
						*有淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的内容； <br>
						*包含可能破坏、改变、删除、不利影响、秘密截取、未经授权而接触或征用任何系统、数据或个人资料的任何病毒、特洛依木马、蠕虫、定时炸弹、删除蝇、复活节彩蛋、间谍软件或其他电脑程序；
						<br> <br> 3.违约 <br>
						如发生以下情形，本公司可能限制您的活动、立即删除您的商品、向本公司社区发出有关您的行为的警告、发出警告通知、暂时中止、无限期地中止或终止您的用户资格及拒绝向您提供服务：
						<br> (a)您违反本协议或纳入本协议的文件； <br> (b)本公司无法核证或验证您向本公司提供的任何资料；
						<br> (c)本公司相信您的行为可能对您、本公司用户或本公司造成损失或法律责任。 <br> <br>
						4.责任限制 <br>
						本公司、本公司的关联公司和相关实体或本公司的供应商在任何情况下均不就因本公司的网站、本公司的服务或本协议而产生或与之有关的利润损失或任何特别、间接或后果性的损害（无论以何种方式产生，包括疏忽）承担任何责任。您同意您就您自身行为之合法性单独承担责任。您同意，本公司和本公司的所有关联公司和相关实体对本公司用户的行为的合法性及产生的任何结果不承担责任。
						<br> <br> 5.无代理关系 <br>
						用户和本公司是独立的合同方，本协议无意建立也没有创立任何代理、合伙、合营、雇员与雇主或特许经营关系。本公司也不对任何用户及其网上交易行为做出明示或默许的推荐、承诺或担保。
						<br> <br> 6.一般规定 <br>
						本协议在所有方面均受中华人民共和国法律管辖。本协议的规定是可分割的，如本协议任何规定被裁定为无效或不可执行，该规定可被删除而其余条款应予以执行。
					</p>
				</div>
			</div>
				<div class='nc-signup'>
				<!-- 
					<div class='signup-title'>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/login.html'>已有账号，请登录</a>
					</div>
					 -->
					<div class='signup-content'>
						<!-- <form class="form-horizontal forms-group" id='form'  action="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/doregister.html" method="POST"> -->
					</div>
				</div>
				<div class='nc-login-bottom'></div>
				<div class='nc-login-left'></div>
			</div>
			</div>
			<script type="text/javascript">
				$("#label_cgs").click(function(){
					//激活采购商注册界面
					$('#myTab li:eq(0) a').tab('show')
			      });
				$("#label_gys").click(function(){
					//激活供应商注册界面
					$('#myTab li:eq(1) a').tab('show')
			      });
			</script>
			<#include "/front/commons/_endbig.ftl" />