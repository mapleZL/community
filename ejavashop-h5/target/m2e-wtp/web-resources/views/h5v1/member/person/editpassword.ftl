<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
</style>
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 ">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">修改密码</h1>
</header>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
	<form id="editPasswordform">
		<ul class="bank1 ico">
	           <li><label>用户名</label><input type="text" class="text7" id="text0" value="${(member.name)!""}" readonly="readonly"></li>
	           <li><label>旧密码</label><input type="password" class="text7" id="text1"  name='oldPwd'  placeholder="请输入当前密码"></li>
	           <li><label>新密码</label><input type="password" class="text7" id="text2" name='newPwd'  placeholder="请输入6~20位的字母和数字组合"></li>
	           <li><label>确认新密码</label><input type="password" class="text7" id="text3" name='confirmPwd'  placeholder="请再次输入新密码"></li>
	    </ul>
	    <div style="padding:10px 0 0 10px;"><font id="errLabel" class="font12 clr53" style="color:red"></font></div>
	    <a class="stop1 mt30"  id='buttonSubmit'>确认修改</a>
    </form>
</article>
<!--内容end--> 
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/editPassword.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />  
</body>
</html>
