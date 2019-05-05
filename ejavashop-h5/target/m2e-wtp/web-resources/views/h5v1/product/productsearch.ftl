<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%}
.am-intro-left a {position: relative}
.am-intro-left h5 {opacity: 0.8; width:100%; height:30px; line-height: 30px; position:absolute; background-color:#ff3c23; text-align:center; color:#fff}
</style>
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
 <form action="${(domainUrlUtil.EJS_URL_RESOURCES)!}/search.html" method="get" onkeydown="if(event.keyCode==13)return false;" >
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/drop.png" alt=""/>
      </a>
  </div>
  <h1 class="am-header-title">
       <input type="text" class="em3 text" id="keyword" value="${keyword!''}" placeholder="请输入关键词">
  </h1>
   <div class="am-header-right am-header-nav searchproduct">
      <a href="javascript:void(0)" class="" style="font-size:1.4rem; color:#fff">搜索</a>
  </div>
  
 </form>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--热门搜索-->
<div id="seqencing" style="margin-top:48px;<#if producListVOs?? && producListVOs?size &gt; 0> <#else>display:none;</#if>">
   <div class="ico tp2 screen overflow changepic" style="overflow:hidden; position:fixed; width:100%; left:0; z-index:999; top:49px">
    <a href="javascript:void(0)" class="moren" data-bysort="0" id="moren" <#if sort==0> style="color:red"<#else>style="color:black" </#if> >默认</a>
    <a href="javascript:void(0)" class="cur overflow resetprice" 
    		    <#if sort??>
					<#if sort == 0 || sort == 4 || sort == 5 || sort == 6>
						data-bysort="3"
					<#else>
						data-bysort="4"
					</#if>
				</#if>>
    	<em class="jiage" <#if sort &gt; 0 && sort &lt; 5>style="color:red"<#else>style="color:black" </#if>>价格</em>
        <span class="pri1" <#if sort &gt; 0 && sort &lt; 5> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_01.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao1.png"></em>
        </span>
        <span class="pri1" id="tupian1" <#if sort == 4 || sort == 5 || sort == 6 || sort == 0> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao1.png"></em>
        </span>
        <span class="pri1" id="tupian2" <#if sort == 3 || sort == 5 || sort == 6 || sort == 0> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_01.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_02.png"></em>
        </span>
     </a>
     <a href="javascript:void(0)" class="cur overflow" 
    		    <#if sort??>
					<#if sort == 0 || sort == 6 || sort == 4 || sort == 3>
						data-bysort="5"
					<#else>
						data-bysort="6"
					</#if>
				</#if>>
    	<em class="jiage" <#if sort &gt; 4 >style="color:red"<#else>style="color:black" </#if>>库存</em>
        <span class="pri1" <#if sort &gt; 4> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_01.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao1.png"></em>
        </span>
        <span class="pri1" <#if sort == 6 || sort == 4 || sort == 3 || sort == 0> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/sanjiao1.png"></em>
        </span>
        <span class="pri1" <#if sort == 5 || sort == 4 || sort == 3 || sort == 0> style="display:none"</#if>>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_01.png" width="100%"></em>
            <em><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/down_02.png"></em>
        </span>
     </a>
	</div>
</div>
<div class="clear"></div>
<div class="tp2 public" <#if keywordNumber=1>style="display:none;"</#if>>
	<h3 class="em3 f16">热门搜索</h3>
    <div class="overflow hot_search">
    <#assign i = 1>
    <#if keywords??> 
		<#list keywords as keyword>
			<#if keyword?? && keyword=="羊毛袜">
    	  		<a href="0-1-0-0-0-0-0-0-0-0-155_6.html"><span <#if i%3==0>class="last" </#if>>${(keyword)!''}</span></a>
			<#else>
    	  		<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/search.html?keyword=${(keyword)!''}&bysort=0" ><span <#if i%3==0>class="last" </#if>>${(keyword)!''}</span></a>
			</#if>
			<#assign i = i+1>
	  	</#list> 
	</#if>
    </div>
	
</div>
<!--热门搜索end-->
<!--搜索历史-->
<#--
<div class="clear"></div>
<div class="tp2 public">
	<div class="overflow">
		<h3 class="em3 f16 fl">搜索历史</h3>
        <a class="delete fr" data-am-modal="{target: '#my-confirm'}"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/delete.png"></a>
    </div>
    <div class="overflow hot_search">
    	<span>多么多色</span>
        <span>圣诞冬</span>
        <span class="last">冬季保暖袜</span>
        <span>一体裤</span>
        <span>手套</span>
        <span class="last">婴儿、童袜</span>
        <span>品牌袜</span>
        <span>中筒袜</span>
        <span class="last">袋装袜</span>
    </div>
	
</div>
-->
<!--搜索历史end-->
<div id="orignaldata">
<#if producListVOs??>
	<#list producListVOs as producListVO>
		<div data-am-widget="intro" class="am-intro am-cf am-intro-default ico tp2">
	         <div class="am-g am-intro-bd">
	            <div class="am-intro-left am-u-sm-4">
	            	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(producListVO.id)!0}.html">
						<#if producListVO.productStock &lt;= 0><h5>补货中</h5></#if>
	            		<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}/${(producListVO.masterImg)!0}"/>
	            	</a>
	            </div>
	            <div class="am-intro-right am-u-sm-8">
	            	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(producListVO.id)!0}.html">
	                    <h3 class="title1"><#noescape>${(producListVO.name1)!""}</#noescape></h3>
	                    <p class="f12 em3">${(producListVO.name2)!""}</p>
		                <div class="overflow mt5">
		                    <em class="price2 mt5">¥${(producListVO.malMobilePrice)!""}</em>
		                    <a class="add_btn fr">查看详情</a>
		                </div>
	                </a>
	            </div>
	         </div>
	     </div>
	</#list>
</#if>
</div>
<div id="product_list_more"></div>
<#if producListVOs?? && producListVOs?size==pagesize>
	<div id="product_list_more_json">
		<a href="javascript:void(0);" class="more1">查看更多<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/more1.png"></i></a>
	</div>
	<div id="product_list_more_json_wait" class="more1" style="display:none;">请稍等，正在努力加载中...</div>
	<div id="product_list_more_json_no">
		<a class="more1">已展示全部商品</a>
	</div>
<#else>
	<#if producListVOs??>
		<div class="da_box" id="no_product" <#if producListVOs?size &gt; 0> style="display:none" </#if>>
	    	<div class="da_img">
	        	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_search_nothing.png">
	        </div>
	        <p>没有找到您搜索的商品</p>
	    </div>
	<#else>
		<div class="da_box" id="no_product" style="display:none">
	    	<div class="da_img">
	        	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/icon_search_nothing.png">
	        </div>
	        <p>没有找到您搜索的商品</p>
	    </div>
	</#if>
</#if>

<input type="hidden"  name="list_page" id="list_page" value="1" />
<input type="hidden"  name="sort" id="sort" value="${sort!'0'}"/>
<input type="hidden" name="pagesize" id="pagesize" value="${(pagesize)!}" />
<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/productSearch.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
