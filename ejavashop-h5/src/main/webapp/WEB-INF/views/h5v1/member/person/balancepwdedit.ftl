<#include "/h5v1/commons/_head.ftl" />
<html>
<head>
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
</style>
</head>
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">修改支付密码</h1>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
<form id="editPasswordform">
	<ul class="bank1 ico">
           <li><label>旧密码</label><input type="password" class="text7" id='txt-oldpw' name='oldPwd'  placeholder="请输入当前密码"></li>
           <li><label>新密码</label><input type="password" class="text7" id='txt-newpw' name='newPwd' placeholder="请输入6~20位的字母和数字组合"></li>
           <li><label>确认新密码</label><input type="password" class="text7" id='txt-repw' name='confirmPwd' placeholder="请再次输入新密码"></li>
    </ul>
	<div style="padding:10px 0 0 10px;"><font id="errLabel" class="font12 clr53" style="color:red"></font></div>
    <a class="stop1 mt30">确认修改</a>
</form>
</article>
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/balancePwdEdit.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />  
</body>
</html>
