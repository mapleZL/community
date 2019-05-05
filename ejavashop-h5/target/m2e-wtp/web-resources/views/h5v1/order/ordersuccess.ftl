<#include "/h5v1/commons/_head.ftl" />
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>
  <h1 class="am-header-title">提交成功</h1>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
	<#if ordervo??>
		<#if (ordervo.ordersList?size>1)>
			<h2 class="mar_top">亲爱的用户，由于您购买的商品分属不同的商家，此订单被拆分为${(ordervo.ordersList?size) }个订单进行结算及配送。</h2>
		</#if>
	</#if>
	
	<div class="mt10 ico public order_success">
	    <h3 class="f16">订单提交成功，请您尽快打款！</h3>
	    <p class="em3">请您在3小时内完成线下付款，否则订单将自动取消。</p>
	 <#if ordervo??>
		<#list ordervo.ordersList as order>
	        <p>订单号：<em class="em4">${(order.orderSn)!'' }</em></p>
	        <p>线下打款：<em class="em4">¥ ${(order.moneyOrder - order.moneyIntegral)?string('0.00')!'' }</em></p>
    	</#list>
    </#if>
    </div>
    <h3 class="wap em2 pay_title">收款方</h3>
    <h3 class="public ico em2">小麦（浙江）网络科技有限公司</h3>
    <h3 class="wap em2 pay_title1">可打款银行</h3>
    <dl class="tp2 tp4 ico public bank overflow">
    	<dt class="fl"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/bank_huaxia.png"></dt>
        <dd class="fl">
        	<p>华夏银行</p>
            <p class="em2">1325  2000  0004  55303</p>
        </dd>
    </dl>
    <dl class="tp2 tp4 ico public bank overflow mt10">
    	<dt class="fl"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/bank_guangda.png"></dt>
        <dd class="fl">
        	<p>中国光大银行杭州滨江支行</p>
            <p class="em2">1325  2000  0004  55303</p>
        </dd>
    </dl>
     <h3 class="wap em2 pay_title2">说明</h3>
     <div class="ico public">
     	<p class="mt5">1：选择线下打款时，请备注订单单号，用于保证订单及时核销，订单单号务必填写正确。</p>
        <p class="mt5">2：订单单号需填写在电汇凭证的【汇款用途】、【附言】、【摘要】等栏内（因不同银行备注不同，最好在所有可填写备注的地方均填写）。</p>
        <p class="mt5">3：一个订单单号对应一个订单和金额，请勿多转账或少转账。</p>
        <p class="mt5">4：收到款后本平台将会第一时间确认并处理您的订单。</p>
        <p class="mt5">5：查询付款情况请致电0575-89007153。</p>
     </div>
</article>
<!--内容end-->    
</body>
<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<#include "/h5v1/commons/_statistic.ftl" />
