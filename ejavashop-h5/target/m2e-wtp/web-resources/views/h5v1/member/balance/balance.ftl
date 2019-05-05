<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
</style>
<body style="background:#eee">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">我的余额</h1>
</header>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
	<ul class="wallet mt10 ico" style="margin-top:50px;">
        <li><a href=""><em class="fl">当前余额</em><span class="fr">¥ ${(member.balance)!"0"}</span></a></li>
		<#if member?? && member.balancePwd?? && member.balancePwd != ''>
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/editbalancepassword.html" class="wallet_a">修改支付密码</a></li>
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/resetbalancepassword.html" class="wallet_a">重置支付密码</a></li>
			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/balance.html?type=list" class="wallet_a">余额支付历史</a></li>
		<#else>
        	<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/setbalancepassword.html"  class="wallet_a">设置支付密码</a></li>
		</#if>
    </ul>
<#include "/h5v1/commons/_footer.ftl" />
</body>
</html>
