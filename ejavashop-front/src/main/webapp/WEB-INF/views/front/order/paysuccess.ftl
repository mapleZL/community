<#include "/front/commons/_top.ftl" />
<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css'>
		
		<!--  成功加入进货单 -->
		<div class='container'>
			<div class='cart-sucess'>
				<div id='CartSucess'>
					<div class='success-b'>
						<h3>支付成功  </h3>
						
						<#if orderSn??>
						订单号为 : ${orderSn}
						</#if>
					</div>
				</div>
			</div>
		</div>
		<!-- end -->
 <#include "/front/commons/_endbig.ftl" />