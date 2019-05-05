<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
.ect-radio1{ margin-top:4.5rem; width:10%}
.ect-radio1 input[type=radio],.ect-radio1 input[type=checkbox]{display: none;}
input[type=radio]{margin:4px 0 0;margin-top: 1px\9;line-height: normal;}
input[type=radio]{-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;padding: 0;}
input[type="radio"]{box-sizing: border-box;padding: 0;}
.ect-radio1 input:checked+label i, .ect-checkbox input:checked+label i{color:rgb(255, 0, 0);background: url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_click.png) center center  no-repeat;background-size:100%;border:0;}
.ect-radio1 label i, .ect-checkbox label i{width:1.2em;height:1.2em;display: block;float:left;margin-top: 0.1em;border-radius:100%;background:rgba(255,255,255,0);border:1px solid rgb(187, 187,187);}
.am-intro-bd{padding:10px 0}
.btn{ position: absolute; top: 0; right: -80px; text-align: center; background: #e91e1e; color: #fff; width: 80px ;height: 130px;}
</style>
<body>
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">浏览历史</h1>
</header>
<!--头部end-->

<!--内容-->
<div class="divcons"></div>
<div class="clear"></div>
<article>
	<input type="hidden" id="logCount" value="${logCount!'0'}"/>
	<input type="hidden" id="pageIndex" value="0"/>
	<input type="hidden" id="pageSize" value="${pageSize!'5'}"/>
	<div id="lookLogListDiv"></div>
	<div id="addMoreLogDiv">
		<a href="javascript:void(0);" class="more1" id="showMore">查看更多<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/more1.png"></i></a>
	</div>
	<div id="product_list_more_json_wait" class="more1">请稍等，正在努力加载中...</div>
	<div id="product_list_more_json_no" class="more1" style="display:none;">已展示全部浏览历史</div>
</article>
<!--内容end-->    
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/viewed.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/touchWipe.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
</html>
