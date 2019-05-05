<#include "/front/commons/_top.ftl" />
<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css'>
<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/payfor.css'>
		<div class='w w1 header container'>
			<div class='ld' id='logo' style="display: none;">
				<a href='' target='_blank' class='link1'>
					<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/logo.jpg'>
				</a>
			</div>	
			
		</div>
	
	<form id="payForm" method="GET" >
		<!-- 支付 -->
		<div id='PayforBox'>
			<div class='container w'>
				<!-- 订单详情 -->
				<div class='order-information'>
					<div class='p-left'>
						<h3 class='p-title'>
								订单号：${(ordersSn)!}
						</h3>
						<p class='p-tips'>请您在提交订单后<span class='font-red'>3小时内</span>完成支付，否则订单会自动取消</p>
					</div>
					<div class='p-right'>
						<div class='pay-price'>
							<em>应付金额</em>
							<strong>${(showWeiXinMoney)!'' }</strong>
							<em>元</em>
						</div>
					</div>
					
					<br>
					
					<div class='clr'></div>
					
				</div>
				
				<!-- end -->
				<!-- 支付方式 -->
				<div class='payment'>
					<div>
						<span>
						<span style="font-size: 20px">微信支付</span>
						&nbsp;&nbsp;&nbsp;&nbsp;  请扫描下方二维码在微信进行付款
					</div>
					</div>
					<div style="margin-left: 100px">
						<img src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/wx/createTDCode.html?codeUrl=${(codeUrl)!}" width="298px">
					</div>
					<div style="margin-left: 450px;margin-top: -300px;">
						<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/phone-bg.png' >
					</div>
					<div style="margin-left: 100px;margin-top: -120px;background-color:#ff5d5b; width:300px">
                         <img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/saoma.png' width="298px" height="80px" >
                    </div>	
				</div>
			</div>
		</div>
		
	</form>
		<!-- end -->
		
<script type="text/javascript">
		$(document).ready(function () {
		    setInterval("ajaxstatus()", 3000);    
		});
		var orderNo = "${ordersSn}";
		var url = "${(domainUrlUtil.EJS_URL_RESOURCES)!}";
		function ajaxstatus() {
	        $.ajax({
	            url: url+"/wx/returnUrl.html",
	            type: "GET",
	            dataType:"json",
	            async : false,
	            cache:false,
	            data: {orderNo:orderNo},
	            success: function (data, textStatus) {
	                if (data.data) {
	                	window.location.href = url+"/wx/returnUrlSuccess.html"; 
	                }
	            }
	        });
		  }
</script>
	
<#include "/front/commons/_endbig.ftl" />