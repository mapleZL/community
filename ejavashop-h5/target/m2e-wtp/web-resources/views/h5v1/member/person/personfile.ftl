<#include "/h5v1/commons/_head.ftl" />
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">个人资料</h1>
</header>
<!--头部end-->

<!--内容-->
<div class="divcons"></div>
<div class="clear"></div>
<article>

<form id= "personalfileForm">
	<ul class="wallet mt10 ico">
        <li>
        	<em class="fl">用户名</em>
        	<input type="text" id="name" name="name" class="em0 fr name_text" placeholder="${(member.name)!''}" value="${(member.name)!''}">
        </li>
        <li>
        	<em class="fl">真实姓名</em>
        	<input type="text" id="realName" name="realName" class="em0 fr name_text" placeholder="${(member.realName)!''}" value="${(member.realName)!''}">
        </li>
        <li>
        	<em class="fl">手机号码</em>
        	<input type="number" id="mobile" name="mobile" class="em0 fr name_text" placeholder="${(member.mobile)!''}" value="${(member.mobile)!''}">
        </li>
    </ul>
    <ul class="wallet mt10 ico">
        <li>
        	<a class="wallet_a" data-am-modal="{target: '#sex-confirm'}"><em class="fl">性别</em><span class="fr em0" style="padding-right:2rem" id="sex_value">
        	<#if member.gender ?? && member.gender ? exists && member.gender == 1>男<#elseif member.gender ?? && member.gender ? exists && member.gender == 2>女<#else>请选择性别</#if>
        	</span></a>
        </li>
        <li>
        	<em class="fl">QQ</em>
        	<input type="number" id="QQ" name="QQ" class="em0 fr name_text" placeholder="${(member.qq)!''}" value="${(member.qq)!''}">
        </li>
        <li>
        	<em class="fl">微信</em><input type="text" id="wechatNum" name="wechatNum" class="em0 fr name_text" placeholder="${(member.wechatNum)!''}" value="${(member.wechatNum)!''}">
        </li>
        <li>
        	<em class="fl">邮箱</em><input type="text" id="email" name="email" class="em0 fr name_text" placeholder="${(member.email)!''}" value="${(member.email)!''}">
        </li>
    </ul>
    <a class="stop1 mt30" id="personSubmit">保存</a>
    <input type="hidden" id="gender" name="gender" value="1"> 
    </form>
</article>
<!--内容end-->  
<!--弹框-->
<div class="am-modal am-modal-confirm" tabindex="-1" id="sex-confirm" style="top:30%">
  <div class="am-modal-dialog sex_box" style="border-radius:5px;">
  	<span class="em2 tp2" style="padding:10px; text-align:left">性别选择</span>
    <span class="em2 tp2" style="padding:10px; text-align:left" data-am-modal-close onClick="arrow(this,1)">男</span>
    <span class="em2 tp2" style="padding:10px; text-align:left" data-am-modal-close onClick="arrow(this,2)">女</span> 
  </div>
</div>
<!--弹框end-->
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/personfile.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
</html>
