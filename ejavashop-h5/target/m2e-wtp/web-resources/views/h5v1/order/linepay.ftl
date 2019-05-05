<#include "/h5v1/commons/_head.ftl" />
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>
  <h1 class="am-header-title">支付成功</h1>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
	<dl class="mt10 success overflow">
    	<dt class="fl"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/pay_success.png"></dt>
        <dd class="fl">
        	<p>支付成功！</p>
            <em class="em3">您可在“<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html" style="color: red;">订单中心</a>”查看您的订单！</em>
        </dd>
    </dl>
    <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" class="stop1 mt30 swap radius">继续逛逛</a>
</article>
<!--内容end-->    
</body>


<#include "/h5v1/commons/_footer.ftl" />
<#include "/h5v1/commons/_statistic.ftl" />

