<#include "/h5v1/commons/_head.ftl" />
<body>
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>
  <h1 class="am-header-title">订单中心</h1>
</header>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
	<div class="overflow order_sort ico">
		<a href="###" data-state="">全部</a>
        <a href="###" data-state="1">待支付</a>
        <a href="###" data-state="3">待发货</a>
        <a href="###" data-state="4">待收货</a>
        <a href="###" data-state="5">已完成</a>
    </div>
    
    <input type="hidden" id="ordersCount" value="${ordersCount!'0'}"/>
    <input type="hidden" id="pageIndex" value="0"/>
    <input type="hidden" id="pageSize" value="${pageSize!'5'}"/>
    <input type="hidden" id="orderState" value="${orderState!''}"/>
	<div class="" id="ordersListDiv" style="margin-bottom: 10px;margin-top:100px;">
		<#include "/h5v1/member/order/orderlistmore.ftl" />
    </div>
    
    <#if ordersCount &gt; 0 >
	    <div id="addMoreOrderDiv" style="display:none">
	   		<a href="javaScript:;" onClick="addMoreOrder()">
				<div class="more1">查看更多 <i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/more1.png"></i></div>
			</a>
		</div>
		<div id="waitOrderDiv" style="display:none">
	   		<a href="javaScript:;" onClick="addMoreOrder()">
				<div class="more1" id="product_list_more_json_wait">请稍等，正在努力加载中... </div>
			</a>
		</div>
		<div class="more1" id = "noMoreOrderDiv" style="display:none">已展示全部记录</div>
	<#else>
	 <div class="da_box" id="no_product">
		<div class="da_img">
	    	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_noproduct.png">
	    </div>
	    <p>您暂时没有相关订单</p>
	 </div>
	</#if>
<article>
<!-- 内容end -->
<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/orderList.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />

</body>
</html>
