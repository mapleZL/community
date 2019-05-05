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

  <h1 class="am-header-title">找回密码</h1>
</header>
<div class="clear"></div>
<div class="head1">
	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/logo_solgan.png">
</div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
	<form id="forgetform">
	<div class="login_box">
    	<div class="input_box">
        	<input type="text" placeholder="请输入手机号" class="fl" id="mobile" name="mobile" style="width:100%">
            <span><#--<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_right1.png">--></span>
        </div>
        <div class="input_box">
        	<input type="number" placeholder="请输入验证码" class="fl" id="verifyCode" name="verifyCode" >
            <a class="yz em3" href="javascript:void(0);"><img style="cursor:pointer;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html" 
			  	    		id="code_img" onclick="refreshCode();" width="63" height="25" /></a>
        </div>
         <div class="overflow yz_box">
         	<div class="overflow">
        	<input type="text" placeholder="短信验证码" class="fl yz_text" id="mobVerfiy" name="mobVerfiy">
               <a class="fr yz_btn" href="javascript:void(0);" onclick="getmobVerify(this)">获取验证码</a>
            </div>
            <p class="em3 f12" id ="showMsg"></p>
        </div>
      	<label id="errLabel" style="color:red"></label>
    	<a href="javascript:void(0);" class="stop2 mt30 radius public3" id='forgetPasswordBtn'>密码重置</a>
       
    </div>
    </form>
</article>
<!--内容end-->    
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/forgetPassword.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
</html>
