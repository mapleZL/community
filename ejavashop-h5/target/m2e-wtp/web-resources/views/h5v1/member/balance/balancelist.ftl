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

  <h1 class="am-header-title">余额支付历史</h1>
</header>
<!--头部end-->

<!--内容-->
<div class="divcons"></div>
<div class="clear"></div>
<article>
    <div id="balance_more"></div>
     <input type="hidden"  name="list_page" id="list_page" value="0" />
     <div id="product_list_more_json">
     	<a href="javascript:void(0);" class="more1">查看更多<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/more1.png"></i></a>
     </div>
     <div id="product_list_more_json_wait" class="more1" style="display:none;">请稍等，正在努力加载中...</div>
	 <div id="product_list_more_json_no" class="more1" style="display:none;">已展示全部支付历史</div>
	 <div class="da_box" id="no_product" style="display:none">
		<div class="da_img">
	    	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_noproduct.png">
	    </div>
	    <p>暂时没有相关支付历史</p>
	 </div>
</article>
<!--内容end-->   
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/balanceList.js"></script>
<#include "/h5v1/commons/_statistic.ftl" /> 
</body>
</html>
