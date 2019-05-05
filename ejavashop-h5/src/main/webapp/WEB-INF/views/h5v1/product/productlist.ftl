<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%}
.am-gallery{padding:0}
.am-gallery-default .am-gallery-1 a img{width:100%;height:100%;max-height:160px;}
.am-gallery-default>li{ border:1px solid #f9f9f9; box-shadow:0 1px 1px 0 #ebebeb; margin-right:2% ; margin-top:3%}
.am-gallery-default>li.last{ margin-right:0}
.am-gallery-title,.am-gallery-desc{padding:0 0.5rem}
.am-gallery-item{ margin-bottom:0.8rem}
.am-gallery-overlay .am-gallery-title{background-color: rgba(51,51,51,.2);}

.product_list li {width:50%; float:left; padding:5px;}
.product_list li div {border:1px solid #f9f9f9; box-shadow:0 1px 1px 0 #ebebeb; width:100%; padding:5px; float:left; position: relative}
.product_list img {width:100%; height:100%;}
.product_list h3 {font-size:1.4rem; color:#fff; background-color:rgba(51,51,51,.2); text-indent:5px; float:left; 
    height:30px; line-height: 30px; width:100%; margin-top:-30px; position: relative;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
.product_list li div label {padding:0; margin:0; float:left; position: relative }
.product_list li div label h5 {opacity: 0.8; font-size:1.4rem;  background-color:#ff3c23; text-align:center; color:#fff; text-indent:5px;
    height:30px; line-height: 30px; width:100%; position: absolute; }
.product_list span {padding-top:10px; padding-left:1rem; float:left}
.product_list span em {color:#ff3c23; padding-right:1rem; font-size:1.6rem; }
.product_list del {text-decoration:line-through;}
.product_list p {display:none}
.product_list h4 {display:none}

.product_list2 li {width:100%; float:left; padding: 10px 5px 10px 10px; border-bottom:1px solid #e8e8e8;}
/*.product_list2 li div {border:1px solid #f9f9f9; box-shadow:0 1px 1px 0 #ebebeb; width:100%; padding:5px; float:left}*/
.product_list2 li div label {padding:0; margin:0; width:33.33%;float:left; position: relative }
.product_list2 li div label img {width:100%;float:left;}
.product_list2 li div label h5 {opacity: 0.8; width:100%; height:30px; line-height: 30px; position:absolute; background-color:#ff3c23; text-align:center; color:#fff}
.product_list2 h3, .product_list2 h4,.product_list2 span {padding-left:10px;float:left;}
.product_list2 h3, .product_list2 h4 {width:66%;}
.product_list2 h3 { overflow: hidden;display: -webkit-box; -webkit-line-clamp: 2;-webkit-box-orient: vertical; word-break: break-all; padding-right:0.5rem}
.product_list2 span {width:33%; padding-top:22px; float:left}
.product_list2 span em {color:#ff3c23; padding-right:1rem; font-size:1.6rem; }
.product_list2 del {text-decoration:line-through;}
.product_list2 p {margin-top:15px;}
</style>
<body style="background:#fff">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>
  <h1 class="am-header-title">
  	  <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/search-index.html">
      	  <input type="text" class="em3 text search_product" placeholder="搜索商品">
      </a>
  </h1>
  <div class="am-header-right am-header-nav">
      <a href="javascript:void(0)" id="changeshow"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_small.png" style="height:25px"></a>
  </div>
</header>

<!--头部end-->

<!--内容-->
<div class="divcons"></div>
<div class="clear"></div>
<div class="ico tp2 screen overflow changepic" style="overflow:hidden; position:fixed; width:100%; left:0; z-index:999; top:49px">
	<a href="javascript:void(0)" class="resetprice"  <#if sort &gt; 6> style="color:red"<#else>style="color:black" </#if> 
		<#if sort??>
			<#if sort == 0 || sort == 1 || sort == 2 || sort==3 || sort == 4 || sort == 8>
				data-bysort="7"
			<#else>
				data-bysort="8"
			</#if>
		</#if>>
	<em class="jiage" <#if sort &gt; 6>style="color:red"<#else>style="color:black" </#if>>库存</em>
	<span class="pri1" <#if sort &gt; 6> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_01.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao1.png"></em>
        </span>
        <span class="pri1" id="tupian1" <#if sort == 1 || sort == 2 || sort == 4 || sort == 0 || sort == 3 || sort ==8> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao1.png"></em>
        </span>
        <span class="pri1" id="tupian2" <#if sort == 3 || sort == 0 || sort == 4 || sort == 7> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_01.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_02.png"></em>
        </span>
	</a>
    <a href="javascript:void(0)" class="cur overflow resetprice" 
    		    <#if sort??>
					<#if sort == 0 || sort == 1 || sort == 2 || sort == 4 || sort == 7 || sort == 8>
						data-bysort="3"
					<#else>
						data-bysort="4"
					</#if>
				</#if>>
    	<em class="jiage" <#if sort &gt; 0 && sort &lt; 7>style="color:red"<#else>style="color:black" </#if>>价格</em>
        <span class="pri1" <#if sort &gt; 0 && sort &lt; 7> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_01.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao1.png"></em>
        </span>
        <span class="pri1" id="tupian1" <#if sort == 1 || sort == 2 || sort == 4 || sort == 0 || sort == 7 || sort == 8> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao1.png"></em>
        </span>
        <span class="pri1" id="tupian2" <#if sort == 3 || sort == 0 || sort == 7 || sort == 8> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_01.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_02.png"></em>
        </span>
     </a>
    <a href="javascript:void(0)" class="menu">筛选<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_scree.png"></i></a>
</div>
<!--侧边栏-->
<nav data-am-widget="menu" class="am-menu  am-menu-offcanvas1"  data-am-menu-offcanvas style="padding:0"> 
    <a href="javascript:  void(0)" class="am-menu-toggle" style="width:33.3%; position:fixed;right:0; z-index:999; top:49px">    
    </a>
    <div class="am-offcanvas">
      <div class="am-offcanvas-bar am-offcanvas-bar-flip" style="background:#eee;">	
      	<div class="ico public"><h3 class="em2 f16">筛选</h3></div>
        <!--开始-->
         <div class="mt10 public ico">
         	<div class="overflow">
            	<h3 class="em2 f16 fl">商品品牌</h3>
                <a onclick="zhanKai(this);" class="all_btn fr drop">全部</a>
            </div>
            <div class="overflow all_list product" >
   	   	      <#if productBrands??>
			    <#list productBrands as productBrand>
			  		<#if productBrand_index == 3><div class="all_hide" style="display:none"></#if>
            		<a data-id="${(productBrand.id)!}" <#if (productBrand_index+1) % 3 == 0> class="last"</#if>>${(productBrand.name)!""}</a>
			  	 	<#if productBrands?size == (productBrand_index+1)></div></#if>
   	   	   	  	</#list>
			  </#if>
            </div>
         </div>
		<!--结束	-->
		<span>
		<#if productTypeAttrVOsAll?? && (productTypeAttrVOsAll?size>0)>
			<#list productTypeAttrVOsAll as productTypeAttrVO>
			   	<#if productTypeAttrVO.isChoice == 0>
			         <div class="mt10 public ico">
			         	<div class="overflow">
			            	<h3 class="em2 f16 fl">${(productTypeAttrVO.name)!""}</h3>
			                <a onclick="zhanKai(this);" class="all_btn fr drop" data-id="-${(productTypeAttrVO.id)!}_${(value_index)!}">全部</a>
			            </div>
			            <div class="overflow all_list product3">
			   	   	   	   <#if productTypeAttrVO.values??>
						  	 <#list productTypeAttrVO.values as value>
						  	 	<#if value_index == 3><div class="all_hide" style="display:none"></#if>
									<a data-id="-${(productTypeAttrVO.id)!}_${(value_index)!}" <#if (value_index+1) % 3 == 0> class="last"</#if>>${(value)!""}</a>
						  	 	<#if productTypeAttrVO.values?size == (value_index+1)></div></#if>
			   	   	   	     </#list>
					       </#if>
			            </div>
			         </div>
				<#else>
			         <div class="mt10 public ico">
			         	<div class="overflow">
			            	<h3 class="em2 f16 fl">${(productTypeAttrVO.name)!""}</h3>
			                <a onclick="zhanKai(this);" class="all_btn fr drop" data-id="-${(productTypeAttrVO.id)!}_${(value_index)!}">全部</a>
			            </div>
			            <div class="overflow all_list product3">
			   	   	   	   <#if productTypeAttrVO.values??>
						  	 <#list productTypeAttrVO.values as value>
						  	 	<#if value_index == 3><div class="all_hide" style="display:none"></#if>
									<a data-id="-${(productTypeAttrVO.choiceAll)!}" data-replaceid="-${(productTypeAttrVO.id)!}_${(value_index)!}" <#if (value_index+1) % 3 == 0> class="last"</#if>>${(value)!""}</a>
						  	 	<#if productTypeAttrVO.values?size == (value_index+1)></div></#if>
			   	   	   	     </#list>
					       </#if>
			            </div>
			         </div>
		         </#if>
			</#list>
		</#if>
		</span>
		

        <div class="tp4" style="bottom:0">
        <#--
        	<a href="javascript:void(0)" class="reset_btn">重选</a>
            <a href="javascript:void(0)" class="sure_btn">确定</a>
        -->
        </div>
      </div>
      
    </div>
    
</nav>
<!--end-->
<article>
<div class="clear"></div>
	 <div style="margin-top:50px;">
	    <ul id="show_product" class="product_list2">
	     </ul>
     </div>
     <div id="product_list_more_json">
     	<a href="javascript:void(0);" class="more1">查看更多<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/more1.png"></i></a>
     </div>
     <input type="hidden"  name="changestatus" id="changestatus" value="1" />
     <input type="hidden"  name="pricepaixu" id="pricepaixu" value="1" />
     <input type="hidden"  name="list_page" id="list_page" value="0" />
     <input type="hidden"  name="moren_url" id="moren_url" value="${(urlPath)!}" />
     <div id="product_list_more_json_wait" class="more1" style="display:none;">请稍等，正在努力加载中...</div>
	 <div id="product_list_more_json_no" class="more1" style="display:none;">已展示全部商品</div>
	 <div class="da_box" id="no_product" style="display:none">
		<div class="da_img">
	    	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_noproduct.png">
	    </div>
	    <p>暂时没有相关商品</p>
	 </div>
</article>
<!--内容end-->

<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/productList.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
<script>
	var urlPath = "${(urlPath)!}";
</script>
</body>
</html>
