<#include "/h5v1/commons/_head.ftl" />
<style>
.am-gallery-default .am-gallery-item img {max-width:60px; margin:1rem auto;}
.img{height:70px; line-height:70px}
.am-gallery-title{text-align:center}
</style>
<body style="background:#fff">
<!--头部-->
<div class="overflow public header" style="background:#ff7e18">
    <div class="logo fl">
        	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/logo.png">
    </div>
    <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/search-index.html">
    	<input type="text" placeholder="搜索商品" class="search_text fl" style="opacity:1">
    </a>
    <a class="index-search-login login fr">登录</a>
</div>
<!--头部end-->

<!--内容-->
<div class="divcons"></div>
<div class="clear"></div>
<div class="overflow">
	<div class="fl con_left">      
		<#if typeList??> 
			<#list typeList as cate1>
			    	<a href="javascript:void(0);" <#if cate1_index == 0>class="cur"</#if> data-cate-name="${cate1.name}">
						<#if cate1.name?replace("、","")?length gt 4>
							${cate1.name?replace("、","")?substring(0,4)}
						<#else>
							${cate1.name}
						</#if>
					</a>
			</#list> 
		</#if>  
    </div>
    
  	<#list typeList as cate1>
	<div class="fl con_right">
    	 <ul data-am-widget="gallery" data-parent-cate-name="${cate1.name}" <#if cate1_index != 0>style="display:none"</#if> class="typechild am-gallery am-avg-sm-3 am-avg-md-3 am-avg-lg-4 am-gallery-default product" >
		        <#list cate1.childList as cate2>
  					<li class="am-gallery-item">
		                <div>
							<#if cate1_index == 0>
								<a href="0-1-0-0-0-0-${(cate2.id)!}-0-0-0.html" class="">
			                    	<#if cate1_index == 0>
				                    	<div class="img">
				                         <img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(cate2.imgUrl)!}"/>
				                      	</div>
				                    </#if>
			                        <h3 class="am-gallery-title em3">${(cate2.name)!'' }</h3>
			                    </a>
							<#else>
								<a href="0-1-0-0-0-0-0-0-0-0-${(cate2.id)!}_${(cate2_index)!}.html" class="">
				                    	<div class="img">
				                         <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/cate_${(cate2.id)!}_${(cate2_index)!}.jpg"/>
				                      	</div>
			                        <h3 class="am-gallery-title em3">${(cate2.name)!'' }</h3>
			                    </a>
							</#if>
		                </div>
		              </li>
				</#list>
          </ul>
    </div>
			</#list>
</div>
<!--内容end-->
<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/productCate.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
</html>
