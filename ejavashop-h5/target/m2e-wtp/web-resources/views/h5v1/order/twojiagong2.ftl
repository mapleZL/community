<#include "/h5v1/commons/_head.ftl" />
<body>
<header data-am-widget="header" class="header am-header am-header-default tp2">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">二次加工</h1>
</header>
	<div class="divcons"></div>
	<article>
		<div class="mt10 ico public order_success">
	    	<h3 style="text-align:center; font-size:16px; font-weight:bold; padding-bottom:10px">订单提交成功，请等待客服联系！</h3>
	        <p class="em3">二次加工订单需要客服与您联系！确认标牌和相关辅料后才能在“我的订单”内支付货单金额。</p>
	        <p class="em3">（如需自供商标，请跟客服说明确认）</p>
	        <p>订单号：<em class="em4">${relationOrderSn}</em></p>
	    </div>
	    	<a class="stop1 radius public3 connect-customer">联系客服</a>
	    	<a class="stop1 radius public3 order-center">订单中心</a>
    </article>
<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<script>
	$(".order-center").click(function() {
		location.href = "/member/order.html";
	});
	
	$(".connect-customer").click(function() {
		location.href = "tel:4008456002";
	});
</script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
