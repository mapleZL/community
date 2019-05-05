<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
</style>
<body>
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>
  <h1 class="am-header-title">支付结果</h1>
</header>>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
  	<div class="da_box">
	<#if state=='fail'>
	<div class="da_img">
		<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_error.png">
	</div>
        <p>&nbsp;</p>
        <p>支付失败，请返回&nbsp;<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html' style="color: red;">订单中心</a>&nbsp;重新下单！</p>
        <#else>
	<div class="da_img">
		<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_success.png">
	</div>
        <p>&nbsp;</p>
        </#if>
        <p>${(info)!''}，请返回&nbsp;<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html' style="color: red;">订单中心</a>&nbsp;查看订单情况！</p>
        <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" class="sub_btn">返回首页</a>
    </div>
    
</article>
<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<#include "/h5v1/commons/_statistic.ftl" />

</body>
</html>