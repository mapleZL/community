<#include "/front/commons/_headbig.ftl" />
<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css'>
		
		<!--  成功加入进货单 -->
		<div class='container'>
			<div class='cart-sucess'>
				<div id='CartSucess'>
					<div class='success-b'>
						<h3>商品已成功加入进货单</h3>
					</div>
					<div id='initCart_next_go'>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cart/detail.html' class='btn-1' >去进货单结算</a>
						<span class='ml10'>您还可以&nbsp;&nbsp;<a href='javascript:history.back();' class='ftx-05'>继续购物</a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- end -->
 <#include "/front/commons/_endbig.ftl" />