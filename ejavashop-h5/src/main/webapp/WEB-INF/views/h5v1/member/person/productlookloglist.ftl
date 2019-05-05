			<#if lookLogList??>
				<#list lookLogList as lookLog>
					<#if lookLog.product?? >
						<div data-am-widget="intro" class="am-intro am-cf am-intro-default ico tp2 mt10 every-li">
					         <div class="am-g am-intro-bd">
					            <div class="am-intro-left am-u-sm-4"><a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(lookLog.product.id)!0}.html' ><img lazyLoadSrc="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${lookLog.product.masterImg}" /></a></div>
					            <div class="am-intro-right am-u-sm-8">
					            	<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(lookLog.product.id)!0}.html' >
					                    <h3 class="title1">${(lookLog.product.name1)!''}</h3>
					                    <p class="f12 em3">${(lookLog.product.name2)!''}</p>
					                </a>
					                <div class="overflow mt5">
					                    <em class="price2 mt5">${(lookLog.product.malMobilePrice)?string("0.00")!''}</em>
					                    <a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(lookLog.product.id)!0}.html' class="add_btn fr">查看详情</a>
					                    <a href="javascript:void(0);" class = "add_btn fr deletelog" data-order-productid="${(lookLog.product.id)!0}">删除</a>
					                </div>
					            </div>
					         </div>
					         <#--
					         	<div class="tp2 btn">删除</div>
					         -->
					     </div>
					</#if>
	  			</#list>
  			</#if>
