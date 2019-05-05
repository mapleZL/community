
			<#if lookLogList??>
			<#list lookLogList as lookLog>
				<#if lookLog.product?? >
				<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(lookLog.product.id)!0}.html' class="block">
					<dl class="flex list-dl pos_relative">
						<dt><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${lookLog.product.masterImg}" alt="" width="80" height="80"></dt>
						<dd class="padl flex-2">
						<div class="product_name">${(lookLog.product.name1)!''}</div>
						<div class="product-desript">
						   <p class="clr53">￥<font>${(lookLog.product.malMobilePrice)?string("0.00")!''}</font></p>
						</div>
						</dd>
					</dl>
				</a>
				</#if>
  			</#list>
  			</#if>
