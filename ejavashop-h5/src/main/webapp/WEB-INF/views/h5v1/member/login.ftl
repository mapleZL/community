<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
</style>
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default ">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">登录</h1>
</header>
<div class="clear"></div>
<div class="head1">
	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/logo_solgan.png">
</div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
	<form id="loginForm" onSubmit="return false;">
	<input type="hidden" id="toUrl" name="toUrl" value="${(toUrl)!''}"/>
	<div class="login_box">
    	<div class="input_box">
        	<input type="text"  id="userName" name="userName" placeholder="请输入用户名" class="fl" style="width:100%">
            <span></span>
        </div>
        <div class="input_box">
        	<input type="password" id="password" name="password" placeholder="请输入密码" class="fl" style="width:100%">
            <span></span>
        </div>
        <div class="input_box">
        	<input type="number" id="verifyCode" name="verifyCode" placeholder="请输入验证码" class="fl">
            <a class="yz em3" href="#"><img style="cursor:pointer;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html" id="code_img" onclick="refreshCode();" width="63" height="25" /></a>
        </div>
      	<label class="tiperror" style="color:red"></label>
    	<a class="stop2 mt30 radius public3" id="loginBtn" href="javascript:void(0);">登录</a>
        <div class="overflow mt20">
        	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/register.html" class="fl em3">立即注册</a>
            <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/forgetpassword.html" class="fr em3">忘记密码？</a>
        </div>
    </div>
   </form>
</article>
<!--内容end-->
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/login.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
</html>
