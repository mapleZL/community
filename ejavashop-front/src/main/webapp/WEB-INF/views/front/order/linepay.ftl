<#include "/front/commons/_top.ftl" />
<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css'>
		<#--
		<div class='container'>
			<div class='cart-sucess'>
				<div id='CartSucess'>
					<div class='success-b'>
						正在为您跳转支付页面，请等待......
					</div>
				</div>
			</div>
		</div>
	<#noescape>${(data)!'' }</#noescape>
	
	-->
		${(result)!''}
		<div class='container'>
			<div class='cart-sucess'>
				<div id='CartSucess'>
					<div class='success-b'>
						支付成功，请到<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=1'>订单中心</a>查看您的订单！
					</div>
				</div>
			</div>
		</div>
		
		<!-- end -->
 <#include "/front/commons/_endbig.ftl" />