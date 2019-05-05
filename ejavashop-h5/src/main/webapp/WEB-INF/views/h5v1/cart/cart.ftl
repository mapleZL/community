<#include "/h5v1/commons/_head.ftl" />
<style>
.ect-radio1{ margin-top:4.5rem; width:10%}
.ect-radio1 input[type=radio],.ect-radio1 input[type=checkbox]{display: none;}
input[type=radio]{margin:4px 0 0;margin-top: 1px\9;line-height: normal;}
input[type=radio]{-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;padding: 0;}
input[type="radio"]{box-sizing: border-box;padding: 0;}
.ect-radio1 input:checked+label i, .ect-checkbox input:checked+label i{color:rgb(255, 0, 0);background:url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_click.png) center center  no-repeat;background-size:100%;border:0;width:18px;height:18px;line-height:18px}
.ect-radio1 label i, .ect-checkbox label i{display: block;float:left;margin-top: 0.1em;background:url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_nor.png) center center  no-repeat;background-size:100%;width:18px;height:18px; line-height:18px}
.am-intro-bd{padding:10px 0}
.delete{margin-right:1rem}
</style>
<body style="background-color:#f8f8f8;">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">进货单</h1>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<div id="cartListDiv" style="margin-bottom: 60px;">
</div>
<input type="hidden" id="removeCartId" >
<div id="loading">
	<div class="more1">请稍等，正在努力加载中...</div>
</div>
<!--内容end-->

<!--底部-->

<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/cart.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
<!--底部end-->
<!--end--> 
<!--弹框-->

<!--弹框end-->
</body>
</html>
