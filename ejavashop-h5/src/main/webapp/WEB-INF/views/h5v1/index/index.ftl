<#include "/h5v1/commons/_head.ftl" />
<style>
.am-gallery{padding:0}
.am-gallery-default .am-gallery-1 a img{width:100%;height:100%;max-height:160px;}
.am-gallery-default>li{ border:1px solid #f9f9f9; box-shadow:0 1px 1px 0 #ebebeb; margin-right:2% ; margin-top:3%}
.am-gallery-default>li.last{ margin-right:0}
.am-gallery-title,.am-gallery-desc{padding:0 0.5rem}
.am-gallery-item{ margin-bottom:0.8rem}
.am-gallery-overlay .am-gallery-title{background-color: rgba(51,51,51,.2);}
.popforActive {display:none; position:fixed;border: 0px solid red;top: 25%;left: 5%; z-index: 1003;}
.popforActive img {width:95%}
.popforActive span {position:absolute;right:3%;top:0px;border: 0px solid blue;width: 60px;height: 60px;}
</style>
<body>
<!--头部-->
<div class="head overflow public">
	<div class="logo fl">
    	<img src="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/logo.png">
    </div>
    <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/search-index.html">
    	<input type="text" placeholder="搜索商品" class="search_text fl" >
    </a>
  	<a class="index-search-login login fr">登录</a>
</div>
<div id="banner_box" class="box_swipe overflow">
    <div data-am-widget="slider" class="am-slider am-slider-a1" data-am-slider='{"directionindexNav":false}'>
        <ul class="am-slides">
		    <#if banners?? && banners?size &gt; 0 >
			    <#list banners as banner>
			    	<li>
		    		  <a href="${(banner.linkUrl)!''}" class="block">
		    			<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${(banner.image)!''}">
		    		  </a>
			    	</li>
			    </#list>
			</#if>
        </ul>
    </div>
</div>
<!--头部end-->

<!--图标-->
<div class="overflow"></div>
<div class="ico">
    <ul class="categoryLiIcon">
         <li>
           <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/0-1-0-0-0-0-0-0-0-0.html">
               <img lazyLoadSrc="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon1.png"/>
               <em>所有商品</em>
           </a>
         </li>
         <li>
          <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-xppw.html">
               <img lazyLoadSrc="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon2.png"/>
               <em>品牌袜区</em>
          </a>
         </li>
         <li>
          <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-8-0-0-0.html">
               <img lazyLoadSrc="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon3.png"/>
               <em>裸袜专区</em>
          </a>
         </li>
         <li>
          <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-xppw.html">
               <img lazyLoadSrc="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon4.png"/>
               <em>热销商品</em>
          </a>
         </li>
         <li>
           <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-999M.html">
               <img lazyLoadSrc="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon5.png"/>
               <em>新品上线</em>
           </a>
         </li>
         <li>
          <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-p1003.html">
               <img lazyLoadSrc="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon6.png"/>
               <em>线下专区</em>
          </a>
         </li>
          <li>
           <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/0-1-0-0-0-0-0-0-0-0-p1002.html">
               <img lazyLoadSrc="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon7.png"/>
               <em>短袜专区</em>
           </a>
         </li>
         <li>
          <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/eosearch.html">
               <img lazyLoadSrc="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon8.png"/>
               <em>快速下单</em>
          </a>
         </li>
         
     </ul>
</div>
<!--end-->

<!--天天特价-->
<div class="clear"></div>
<div class="mt10 ico">
    <#if floors?? && floors?size &gt; 0 >
    <#assign floorPic = 0>
		<#list floors as floor>
			<#assign floorPic = floorPic+1>
			<a href="${(floor.link)!''}" class="title">
				<#--
				<img src="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/title${floorPic+1}.png">
				-->
				<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(floor.image)!''}">
			</a>
		    <div class="public2">
 				<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default am-gallery-overlay">
					<#if floor.datas?? && floor.datas?size &gt; 0 >
					<#assign i = 1>
					<#list floor.datas as data >
						<!-- 如果是商品 -->
						<#if data.dataType?? && data.dataType == 1 >
							<li <#if i%2 == 0>class="last"</#if>>
							  <div class="am-gallery-item am-gallery-1 ico box">
								<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/product/${(data.product.id)!0}.html">
									<#--<img lazyLoadSrc="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${freeMarkerUtil.handleImagePath(data.product.masterImg, 'little')}" width="189" height="189"/>-->
                                    <img lazyLoadSrc="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${data.product.masterImg}" width="189" height="189"/>
									<h3 class="am-gallery-title em2">${(data.product.name1)!""}</h3>
								</a>
								</div>
								<p class="price_list">
								  <em class="f16">
									￥${(data.product.malMobilePrice)?string("0.00")!"0.00"}
								  </em>
									<#if data.product.marketPrice ?? && data.product.marketPrice &gt; data.product.malMobilePrice>
											<del class="em3 f12">
												<i>¥</i>
												${(data.product.marketPrice)!"0.00"}
											</del>
									</#if>
								</p>
								<#assign i = i+1>
							</li>
						<#elseif data.dataType?? && data.dataType == 2>
							<li class="last">
								<div class="am-gallery-item am-gallery-1 ico box">
									<a href="${(data.linkUrl)!''}">
										<img lazyLoadSrc="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${(data.image)!''}" width="189" height="189"/>
										<h3 class="am-gallery-title em2">${(data.title)!""}</h3>
									</a>
								</div>
							</li>
						</#if>
					</#list>
					</#if>
				</ul>
			</div>
		</#list>
		</#if>
</div>
<!--天天特价end-->
<div class="clear"></div>
<div class="more">
	<img src="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/more.png">
</div>
<!--悬浮框-->
<div class="hot_line">
	<div class="hot_img">
	  	<a href="tel:4008456002">
    		<img src="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon_phone1.png">
    	</a>
    </div>
    <#--QQ暂时隐藏
	    <div class="hot_img">
	    	<img src="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon_phone2.png">
	    </div>
    -->
    <div class="hot_img go-top">
    	<img src="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/h5v1/img/icon_phone3.png">
    </div>
</div>
<!--悬浮框end-->
<!-- 弹出图片 -->
<div class="popforActive">
	<a href="">
	    <img src=""/>
	</a>
    <span></span>
</div>
<div class="cover"></div>
<!-- 弹出图片  end-->
<#include "/h5v1/commons/_nav.ftl" />
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/index.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
</html>
