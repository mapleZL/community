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

  <h1 class="am-header-title">合伙人日志</h1>
</header>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
	<ul class="wallet mt10 ico" style="margin-top:50px;">
		<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotal.html?type=1" class="wallet_a">累计销售汇总表</a></li>
		<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotal.html?type=2" class="wallet_a">年度汇总表</a></li>
		<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotal.html?type=3" class="wallet_a">分类销售汇总表</a></li>
		<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotal.html?type=4" class="wallet_a">合伙人提点统计表</a></li>
		<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/panerTotal.html?type=5" class="wallet_a">合伙人推荐合伙人</a></li>
    </ul>
<#include "/h5v1/commons/_footer.ftl" />
</body>
</html>
