<article>
		<#if productList?? && productList?size &gt; 0 >
			<#list productList as product >
				<div data-am-widget="intro" class="am-intro am-cf am-intro-default ico tp2">
		         <div class="am-g am-intro-bd">
		            <div class="am-intro-left am-u-sm-4">
		            	<a href="javascript:void(0);"<#if product?? && product.productState == 6> href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(product.productId)!0}.html"<#else>href = 'javascript:void(0)'</#if> > 
		            	    <img lazyLoadSrc="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.productLeadLittle}" alt="" width="80" height="80" <#if product?? && product.productState == 7>style="opacity:0.5;"</#if>>
		            	</a>
		            </div>
		            <div class="am-intro-right am-u-sm-8">
		            	<a <#if product?? && product.productState == 6> href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(product.productId)!0}.html" <#else>href = 'javascript:void(0)' style="color:#D6E3E9;"</#if>>
		                    <h3 class="title1">${(product.productName)!''}</h3>
		                    <p class="f12 em3">火爆热卖</p>
		                </a>
		                <div class="overflow mt5">
		                    <em class="price2 mt5" <#if product?? && product.productState == 7>style="color:#D6E3E9;"</#if>>¥${(product.mallMobilePrice)?string('0.00')!'0.00'}</em>
		                    <a class="add_btn fr cancle" href="javascript:void(0);" data-goods-id = "${product.productId}">取消收藏</a>
		                </div>
		            </div>
		         </div>
		     </div>
			</#list>
		</#if>
</article>