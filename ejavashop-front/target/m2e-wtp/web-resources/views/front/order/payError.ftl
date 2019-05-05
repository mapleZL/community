<#include "/front/commons/_top.ftl" />
<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css'>
		${(result)!''}
		<div class='container'>
			<div class='cart-sucess'>
				<div id='CartSucess'>
					<div class='error-b'>
						支付失败，请返回<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html'>订单中心</a>重新下单！
					</div>
				</div>
			</div>
		</div>
		
		<!-- end -->
 <#include "/front/commons/_endbig.ftl" />