<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
.ect-radio1{ margin-top:4.5rem; width:10%}
.ect-radio1 input[type=radio],.ect-radio1 input[type=checkbox]{display: none;}
input[type=radio]{margin:4px 0 0;margin-top: 1px\9;line-height: normal;}
input[type=radio]{-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;padding: 0;}
input[type="radio"]{box-sizing: border-box;padding: 0;}
.ect-radio1 input:checked+label i, .ect-checkbox input:checked+label i{color:rgb(255, 0, 0);background:url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_click.png) center center  no-repeat;background-size:100%;border:0;width:18px;height:18px;line-height:18px}
.ect-radio1 label i, .ect-checkbox label i{display: block;float:left;margin-top: 0.1em;background:url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_nor.png) center center  no-repeat;background-size:100%;width:18px;height:18px; line-height:18px}
.am-g .am-g{margin-right:-0.4rem}
.am-intro-bd{padding:10px 0; padding-left:10px}
.add_btn{margin-right:0.3rem; padding:5px;}
.newtitle1{
    overflow: hidden;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    word-break: break-all;
    padding-right: 0.5rem;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style>
<body>
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">快速下单</h1>
</header>
<div class="divcons"></div>
<!--头部end-->
<!--搜索-->
<div class="clear"></div>
<form action="${(domainUrlUtil.EJS_URL_RESOURCES)!}/eosearch.html" method="post" id="searchForm">
	<div class="overflow order_search" style="padding:1rem 1.5rem; position:fixed; width:100%; z-index:100">
		<div class="input_box2 fl">
	        <input type="text" placeholder="请输入商品编码" class="text2" name="sku" id="sku" value="${sku!''}" >
	        <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/eosearch.html" class="fr"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/search1.png"></a>
	    </div>
	    <a href="javascript:void(0);" class="select_btn fr search">查询</a>
	</div>
</form>
<!--搜索end-->
<!--内容-->
<div class="clear"></div>
<div class="divcons"></div>
<#if pglist?? && pglist?size &gt; 0>
	<#list pglist as pg>
		<form action="" method="POST"  name="cartForm">
			<input  type="hidden" name="productId" value="${(pg.productId)!}">
			<input  type="hidden" name="productGoodId" value="${(pg.id)!}">
			<input  type="hidden" name="sellerId" value="${(pg.sellerId)!0}">
			<div data-am-widget="intro" class="am-intro am-cf am-intro-default overflow">
			    <div class="am-g am-intro-bd ico mb10">
			    	<!--左边-->
			    	<#--
			        <div class="ect-radio1 fl ect" style="width:8%;">
			            <input name="msg_type" type="checkbox" id="msg-type${(pg.sku)!}" value="1">
			            <label for="msg-type${(pg.sku)!}"><i></i></label>
			         </div>
			         -->
			        <!--左边end-->
			        <!--右边-->
			        <div class="am-g am-intro-bd fr" style="width:95%; margin-right:0.5rem !important; padding-left:0 !important">
			            <div class="am-intro-left am-u-sm-4 cart_img" style="padding-left:0">
			            	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(pg.productId)!}.html" >
			               	 <img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(pg.images)!}"/>
			                </a>
			            </div>
			            <div class="am-intro-right am-u-sm-8" style="padding-right:1rem">
			            	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(pg.productId)!}.html" >
				               	<h3 class="newtitle1" maxlength="30">${(pg.productName)!}</h3>
				                <p class="f12 em0">${(pg.normName)!}&nbsp;&nbsp;&nbsp;${(pg.sku)!}</p>
				                <p class="f12 em0">(一手=${(pg.barCodePL)!''}双:&nbsp;库存${(pg.productStock)!}双)</p>
			                </a>
			                <em class="price1">¥${(pg.mallMobilePrice)!}</em><br/>
			                <div class="spin overflow">
				           		<!--加减部分-->
				                <div class="input-group fl overflow"> 
			                         <span class="input-group-addon fl" onclick="change_goods_number(&#39;1&#39;,${(pg.id)!})">-</span>
			                         <input type="hidden" id="back_number${(pg.id)!}" value="${(pg.productStock)!}">
			                         <input type="hidden" id="barCodePL${(pg.id)!}" value="${(pg.barCodePL)!}">
			                         <input  type="hidden" name="number" value="<#if pg.productStock?number lt pg.barCodePL?number>0<#else>${(pg.barCodePL)!}</#if>" id= "number${(pg.id)!}">
			                         <input type="number" class="form-num form-contro fl" name="shou" id="goods_number${(pg.id)!}" autocomplete="off" value="<#if pg.productStock?number lt pg.barCodePL?number>0<#else>1</#if>" onfocus="back_goods_number(${(pg.id)!})" onblur="check_goods_number(${(pg.id)!})">
			                         <span class="input-group-addon fl" onclick="change_goods_number(&#39;3&#39;,${(pg.id)!})">+</span> <em>手</em>
				                </div>
				                <!--加减部分end-->
				                <a class="add_btn fr" onclick='add2cart(this,${(pg.id)!})'>加入进货单</a>
			            </div>
			            </div>
			         </div>
			     <!--右边end-->
			    </div>
			</div>
		</form>
	</#list>
</#if>
<div id="product_list_more"></div>

<#if pglist?? && pglist?size==pagesize>
<div id="product_list_more_json"  class="more1">查看更多<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/more1.png"></i></div>
<div id="product_list_more_json_wait" class="more1" style="display:none;">请稍等，正在努力加载中...</div>
<div id="product_list_more_json_no" class="more1" style="display:none;">已展示全部商品</div>
<#elseif sku?? && sku!="">
<div id="product_list_more_json_no" class="more1">已展示全部商品</div>
</#if>
<input type="hidden"  name="list_page" id="list_page" value="1" />
<input type="hidden" name="pagesize" id="pagesize" value="${(pagesize)!}" />
<!--内容end-->

<!--底部-->
<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/eosearch.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
<!--底部end-->
<!--end--> 
</body>
</html>
