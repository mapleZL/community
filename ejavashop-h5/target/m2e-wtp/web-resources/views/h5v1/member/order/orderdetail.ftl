<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
</style>
<body style="background:#fff">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">订单详情</h1>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
<div style="background:#eee; padding-top:1rem; ">
	<div class="ico public order_info">
    	<p class="em2">订单信息</p>
        <p>订单编号：<#if order.tradeNo ? exists>${(order.tradeNo)!''}<#else>${(order.orderSn)!''}</#if></p>
        <p>订单金额：￥${(order.moneyOrder)!'' }</p>
        <p>订单日期：${(order.createTime)?string("yyyy-MM-dd")!'' }</p>
        <p>订单备注：${(order.remark)!''}</p>
    </div>
    <div class="tp2 public addr mt10 ico">
        <p class="em2"><em>收货人：${(order.name)!'' }</em><em>${commUtil.hideMiddleStr(order.mobile,3,4)!''}</em></p>
        <p>${(order.addressAll)!''}&nbsp;${(order.addressInfo)!''}</p>
    </div>
    
    
    <#if (order.orderProductList)??>
		<#list (order.orderProductList) as op>
		 <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(op.productId)!0}.html">
			<div class="public ico mt10 public">
		        <div data-am-widget="intro" class="am-intro am-cf am-intro-default default">
		             <div class="am-g am-intro-bd">
		                <div class="am-intro-left am-u-sm-4" style="padding-left:0">
		                	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(op.productId)!0}.html">
				                <#if (op.product)?? && op.product.isNorm == 2 && (op.productGoods)?? && (op.productGoods.images!='')>
				                	<img optype="goods" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productGoods.images!''}">
								<#else>
									<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productLeadLittle!''}">
								</#if>
							</a>
						</div>
		                <div class="am-intro-right am-u-sm-8">
		                    <a href="javascript:void(0);">
		                        <h3 class="title1 em2">${(op.productName)!''}</h3>
		                        <p class="f12 em3">${(op.specInfo)!''}</p>
		                        <p class="f12 em3">加工方式：${(op.packageName)!'裸袜'}; 加工费：${(op.packageFee)!'0.00'}</p>
		                        
		                    </a>
		                    <div class="overflow">
		                        <p class="f12 em3 fl">¥ ${(op.moneyPrice)!''} ×${(op.number)!''}</p>
		                        <em class="price2 fr">小计：¥ ${(op.moneyAmount)!''}</em>
		                    </div>
		                </div>
		             </div>
		         </div>
		     </div>
		 </a>
		</#list>
		</#if>
     
     <div class="mt10 ico public order_info">
    	<p class="em2">订单信息(<em class="em3">${(order.paymentName)!'' }</em>)</p>
        <p class="overflow"><em class="em0 fl">商品金额</em><em class="em0 fr">￥${(order.moneyProduct)?string("0.00")!'0.00' }</em></p>
        <p class="overflow"><em class="em0 fl">运费</em><em class="em0 fr">+ ￥${(order.moneyLogistics)!'0.00'}</em></p>
        <p class="overflow"><em class="em0 fl">套餐服务费</em><em class="em0 fr">+  ￥${(order.servicePrice)?string('0.00')!'' }</em></p>
        <p class="overflow"><em class="em0 fl">优惠金额</em><em class="em0 fr">- ￥${(order.moneyDiscount)?string("0.00")!'0.00' }</em></p>
        <p class="overflow"><em class="em0 fl">积分支付</em><em class="em0 fr">- ￥${(order.moneyIntegral)?string("0.00")!'0.00' }</em></p>
        <p class="overflow"><em class="em0 fl">余额支付</em><em class="em0 fr">- ￥${(order.moneyPaidBalance)?string("0.00")!'0.00' }</em></p>
        <p class="overflow"><em class="em0 fl">现金支付</em><em class="em0 fr">- ￥${(order.moneyPaidReality)?string("0.00")!'0.00' }</em></p>
        <p class="overflow"><em class="em0 fl">订单金额</em><em class="em0 fr">￥${(order.moneyOrder)?string("0.00")!'0.00' }</em></p>
    </div>
    <div class="overflow f16" style="background:url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/back2.png) top center no-repeat #fff; background-size:100% 100%; height:50px; line-height:50px; padding:0 1rem">
    	<em class="em2 fl">实付款</em>
        <em class="em4 fr">￥${(order.moneyPaidReality + order.moneyPaidBalance)?string('0.00')!'' }</em>
    </div>
</div>
<div class="mt10 public order_info">
    <p class="em2 f16">配送信息</p>
    <p>配送方式：${(order.logisticsName)!""}</p>
    <p>快递单号：${(order.logisticsNumber)!""}</p>
    <p>发货时间：<#if order.outTime?? >${(order.outTime)?string("yyyy-MM-dd HH:mm:ss")!""}</#if></p>
    <!--时间轴-->
    <div class="about4">
        <div class="about4_main">
            <div class="line"></div>
            <ul>
            	<#if orderLogList?? && orderLogList?size &gt; 0 >
				 <#list orderLogList as orderLog >
					 <li>
	                    <p>${orderLog.createTime?string("yyyy-MM-dd HH:mm:ss") }</p>
	                     <p>${orderLog.content}</p>
	                </li>
				 </#list>
			  </#if>
            </ul>
        </div>
    </div>
    <!--end-->
</div>

<div class="clear"></div>
<div class="more mt10">
    <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/more.png">
</div>
</article>
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript">
$(document).ready(function(e) {
	var h = $(".about4_main ul li:first-child").height()/2;<!--第一个li高度的一半-->
	var h1 = $(".about4_main ul li:last-child").height()/2;<!--最后一个li高度的一半-->
	$(".line").css("top",h);
	$(".line").height($(".about4_main").height()-h1-h);
});
</script>
<#include "/h5v1/commons/_statistic.ftl" />
<!--内容end-->

</body>
</html>
