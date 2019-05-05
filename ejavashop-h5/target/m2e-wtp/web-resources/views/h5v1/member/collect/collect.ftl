<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
</style>
<body>
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">收藏的商品</h1>
</header>
<!--头部end-->
<div class="divcons"></div>
<div class="clear"></div>
<!--内容-->
<input type="hidden" id="pageSize" value="${pageSize!'5'}"/>
<input type="hidden" id="productCount" value="${productCount!'0'}"/>
<input type="hidden" id="productPageIndex" value="0"/>
<div id="productDiv">
</div>
<div id="addMoreProductDiv" style="display:none;">
	<a href="javascript:void(0);" class="more1">查看更多<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/more1.png"></i></a>
</div>
<div id="product_list_more_json_wait" class="more1" <#if productCount == 0 >style="display:none;"</#if>>请稍等，正在努力加载中...</div>
<#if productCount == 0 >
	 <div class="da_box" id="no_product">
		<div class="da_img">
	    	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_noproduct.png">
	    </div>
	    <p>您暂时没有收藏商品</p>
	 </div>
<#else>
<div id="product_list_more_json_no" class="more1" style="display:none;">已展示全部收藏商品</div>
</#if>
<div class="clear"></div>
<!--内容end-->  
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/collect.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
  
</body>
</html>
