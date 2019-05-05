<#include "/h5/commons/_head.ftl" />
<body class="bgff">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/member/order.html">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">下单成功</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
   
	<div class="errorbox">
	     <div class="arrow-fl"></div>
         <h3 class='ftc-02 text-center' style="color: black;"><i class="fa fa-smile-o"></i>&nbsp;感谢您，订单提交成功！</h3>
         <div class="padt_b10 borbt text-center" style="color: black;">请到&nbsp;<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html' class="btn s-btn">订单中心</a>&nbsp;查看详情。</div>
			<#if ordervo??>
				<#if (ordervo.ordersList?size>1)>
				<h2 class="mar_top">亲爱的用户，由于您购买的商品分属不同的商家，此订单被拆分为${(ordervo.ordersList?size) }个订单进行结算及配送。</h2>
				</#if>
		   </#if>
		<#if ordervo??>
		<#list ordervo.ordersList as order>
			<div class='mcs mar_top' id='msop_detail'>
				<ul class='list-order'>
					<li class='li-st'>
						<div class='li-for1'>
							订单号：
							<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=${(order.id)!''}'>${(order.orderSn)!'' }</a>
						</div>
						<div class='li-for2'>
							${(order.paymentName)!'' }：
							<strong class='ftc-01'>${(order.moneyOrder - order.moneyIntegral)?string('0.00')!'' }元</strong>
							
							<#if order.paymentCode == "OFFSEND" && order_index == 0 > 
								<div style="font-size: 20px; line-height: 40px;margin-top: 10px;margin-bottom: 20px;">
								<p><b>小麦（浙江）网络科技有限公司</b></p>
								<p>华夏银行：<b><br>1325 2000 0004 55303</b></p>
								<p>中国光大银行杭州滨江支行：<b><br>7966 0188 0000 59222</b></p>
								<p>联系电话：0575-89007153</p>
								</div>
								<h4 class="text-danger" style="color: rgb(255,153,0);">说明:</h4>
								<ol style="margin-left: 15px;margin-top: 15px;">
									  <li>①.选择线下打款时，请备注订单单号，用于保证订单及时核销，订单单号务必填写正确</li>
									  <li>②.订单单号需填写在电汇凭证的【汇款用途】、【附言】、【摘要】等栏内（因不同银行备注不同，最好在所有可填写备注的地方均填写）</li>
									  <li>③.一个订单单号对应一个订单和金额，请勿多转账或少转账</li>
								  <li>④.收到款后本平台将会第一时间确认并处理您的订单</li>
								  <li>⑤.查询付款情况请致电0575-89007153</li>
								</ol>
							</#if>
						</div>
					</li>
				</ul>
			</div>
		  </#list>
		 </#if>	
    </div>
	<!-- 主体结束 -->

	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />
 <script type="text/javascript">
$(function(){
	
});

</script>

</body>
</html>