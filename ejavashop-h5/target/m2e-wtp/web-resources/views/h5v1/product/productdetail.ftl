<#include "/h5v1/commons/_head.ftl" />
<style>
.am-nav-tabs>li.am-active>a, .am-nav-tabs>li.am-active>a:focus, .am-nav-tabs>li.am-active>a:hover{color:#ff3c23; border:0; border-bottom:2px solid #ff3c23; background:#f8f8f8; width:50%; margin:0 auto}
.am-nav-tabs>li{width:50%; text-align:center}
.am-slider-default .am-direction-nav .am-prev{left:-30px}
.am-slider-default .am-direction-nav .am-next{right:-30px}
::-webkit-scrollbar{display: none;}
</style>
<body >
   <!-- 头部 -->
	<header data-am-widget="header" class="am-header am-header-default tp2 header">
	  <div class="am-header-left am-header-nav">
	      <a href="javascript:void(0);">
	          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
	      </a>
	  </div>
	
	  <h1 class="am-header-title">商品详情</h1>
	</header>
   <!-- 头部 end-->
	<div class="divcons"></div>
	<div class="clear"></div>
	<div data-am-widget="slider" class="am-slider am-slider-a1" data-am-slider='{"directionNav":false}'>
	  <ul class="am-slides">
	  	<#if productLeadPicList?? && productLeadPicList?size &gt; 0 >
			<#list productLeadPicList as pic >
				<li><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${pic!''}"></li>
			</#list>
		</#if>
	  </ul>
	  
	</div>
	<div id="actInfoDiv"></div>
	
		<!-- 详情列表 -->
		<#if product?? && product.state?? && product.state == 6>
			<div class="ico public price">
				<p>${(product.name1)!''}</p>
			    <div class="overflow">
			    	<#if product.priceStatus == 3>
			    		<input type="hidden" name="priceStatus" id="priceStatus" value="3" />
			    		<div class="fl mr10">
				            <span>¥<em id="product_price1">${(product.scatteredPrice)?string('0.00')!'999999'}</em></span>
				            <div class="em3 f12">散价(10*n)${(product.unit)!''}</div>
				        </div>
				        <div class="fl mr10">
				            <span>¥<em id="product_price2">${(product.mallPcPrice)?string('0.00')!'999999'}</em></span>
				            <!--  
				            <#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != product.mallPcPrice>
								&nbsp;&nbsp;<span>¥${(goods.mallOriginalPrice)?string('0.00')!'999999'}</span>
							</#if>
							-->
				            <div class="em3 f12">单色整箱价(<em id="info_package_number_zheng">${(product.fullContainerQuantity)}</em>*n)${(product.unit)!''}</div>
				        </div>
					<#elseif product.priceStatus == 2 && (productPrice)??>
						<input type="hidden" name="priceStatus" id="priceStatus" value="2" />
						<div class="fl mr10 price_num">
				            <span>¥<em id="product_price1">${(productPrice.price1)?string('0.00')!'999999'}</em></span>
				            <p class="em3 f12">${(productPrice.price1S)!''}-<em id="productPrice_price1E">${(productPrice.price1E)!''}</em>${(product.unit)!''}</p>
				        </div>
				        <div class="fl mr10 price_num">
				            <span>¥<em id="product_price2">${(productPrice.price2)?string('0.00')!'999999'}</em></span>
				            <p class="em3 f12">${(productPrice.price2S)!''}-<em id="productPrice_price2E">${(productPrice.price2E)!''}</em>${(product.unit)!''}</p>
				        </div>
				        <div class="fl mr10 price_num">
				            <span>¥<em id="product_price3">${(productPrice.price3)?string('0.00')!'999999'}</em></span>
				            <p class="em3 f12">≥${(productPrice.price3S)!''}${(product.unit)!''}</p>
				        </div>
					<#elseif product.priceStatus == 1>
						<input type="hidden" name="priceStatus" id="priceStatus" value="1" />
						<span>¥<em id="product_price1">${(goods.mallMobilePrice)?string('0.00')!'999999'}</em></span>
		    			<del>${(goods.mallOriginalPrice)!""}</del>
					</#if>
			    </div>
			</div>
			<!--banner end-->
		
			<!--选项卡-->
			<div class="clear"></div>
			<div class="ico mt10">
				<div class="am-tabs" data-am-tabs>
					<ul class="am-tabs-nav am-nav am-nav-tabs" style="background:#f8f8f8">
						<li class="am-active"><a href="#tab1">商品</a></li>
						<li><a href="#tab2">详情</a></li>
					</ul>
				<div class="am-tabs-bd mt10">
					<div class="am-tab-panel am-fade am-in am-active" id="tab1">
						<div class="img wap">
							<p><#noescape> ${(product.description)!''}</#noescape></p>
			      			<#-- <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/img1.jpg"> -->
			      		</div>
			      	</div>
<#--				</div>-->
				<div class="am-tab-panel am-fade" id="tab2" style="padding-top:0">
					<div class="wap">
						<table cellspacing="0" class="parame">
							<tr>
								<td class="td1">商品名称</td>
								<td class="td2">${(product.name1)!''}</td>
							</tr>
							<tr>
								<td class="td1">上架时间</td>
								<td class="td2">${(product.upTime?string("yyyy-MM-dd HH:mm:ss"))!'' }</td>
							</tr>
							<#if productAttr?? && productAttr?size &gt; 0>
								<#list productAttr as attr>
									<tr>
										<td class="td1">${(attr.name)!''}</td>
										<td class="td2">${(attr.value)!''}</td>
									</tr>
								</#list>
							</#if>
						</table>
					</div>
				</div>   
			</div>
			<!--选项卡end-->

				
	        <!-- 底部固定菜单 -->
	        <div class="clear"></div>
			<div style="height:70px;"></div>
			<div class="foot1 overflow">
			    <div class="fl ic">
			  		<a id="collect" onClick="collectProduct()" <#if statisticsVO?? && statisticsVO.collectedProduct>style='color:ff7e18'</#if> >
			            <#if statisticsVO?? && statisticsVO.collectedProduct>
			            	<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/star.jpg"></i>
		        			<p>已收藏</p>
		        		<#else>
		        			<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/ic1.jpg"></i>
		        			<p>收藏</p>
		        		</#if>
			        </a>
			        <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/cart/detail.html" >
			        	<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/ic2.jpg"></i>
			            <p>进货单</p><font class="cart-num">${(cartNumber)!0}</font>
			        </a>
			        <a href="#">
			        <#if product.productBrandId?? && product.productBrandId==8 >
			        	<a href="javascript:;" id="skuAllSelectforLuowa">
			        		<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/ic3.jpg"></i>
			        		<p>一键清仓</p>
			        	</a>
					<#else>
			        	<a href="javascript:;" id="skuAllSelectforPPW" >
			        		<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/ic3.jpg"></i>
			        		<p>一键清仓</p>
			        	</a>
					</#if> 
			        </a>
			 	</div>
					<a class="fl join" data-am-modal="{target: '#my-actions'}" id="addToCart" href="javascript:;">加入进货单</a>
				 	<a class="fl buy" data-am-modal="{target: '#my-actions'}" id="buyNow" href="javascript:;">立即购买</a>
			</div>

	       <!--  弹出层 -->
	       
        <#else>
			<div class="mar-bt" id="mmmm">
		        <h3 style="padding:20px 0; background:#fdf5f5; text-align:center;"><strong>即将上架，敬请期待！</strong></h3>
		    </div>
		</#if>
	<div class="go-top" style="z-index:999;position:fixed; right:10px; bottom:70px">
		<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/top.png" style="max-width:40px; background:none">
	</div>
		<!-- footer -->
    <input type="hidden" value="${(product.state)!''}" id="tzm_productState"/>
    <input type="hidden" value="${(product.upTime)?string('yyyy-MM-dd HH:mm:ss')}" id="tzm_productUpTime"/>
    <input type="hidden" value="${(productId)!0}" id="productId"/>
    <input type="hidden" value="${product.unit}" id="productUnit"/>
    <input type="hidden" value="${(seller.id)!0}" id="productSellerId"/> 
    <input type="hidden" value="${noomids!0}" id="productNormId"/>
    <input type="hidden" value="${(seller.sellerName)!''}" id="productSellerName"/>
    <input type="hidden" id="isCollectProduct" value="${(statisticsVO.collectedProduct)?string('true','false')!'false'}"/>
	<!-- 主体结束 -->
	<#if product.productBrandId?? && product.productBrandId==8 >
		<#include "luowapop.ftl" />
	<#else>
		<#include "ppwpop.ftl" />
	</#if>		
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/changeImg.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquey-bigic.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.ellipsis.min.js'></script>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.hDialog.js"></script>    
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/swiper/swiper-3.2.7.min.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/alertPopShow.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/productDetail.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/pl.js"></script>
</body>




