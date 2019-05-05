<#include "/front/commons/_top.ftl" />
<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css?a=12'>
<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/payfor.css?a=12'>
	
	<form id="payForm" method="GET" >
		<input type="hidden" name="paySessionstr" value="${paySessionstr!''}">
		<input type="hidden" name="relationOrderSn" id="relationOrderSn" value="${relationOrderSn!''}">
		<input type="hidden" name="fromType" id="fromType" value="${fromType!''}">
		<input type="hidden" name="payType" id="payType" value="${payType!''}">
		
		<!-- 记录一个hidden值方便计算 -->
		<input type="hidden" id="sumPayPriceHidden" value="${payAmount!0}"/>
		<input type="hidden" id="usedBalanceHidden" />
		<input type="hidden" id="usedCreditHidden" />
									
		<!-- <input type="hidden" name="payType"  value=""> --><!-- 支付方式 -->
	
		<!-- 支付 -->
		<div id='PayforBox'>
			<div class='container w'>
				<!-- 订单详情 -->
				<div class='order-information'>
					<div class='p-left'>
						<h3 class='p-title'>
							<#if fromType?? && fromType == 2>
								订单号：  
							<#else>
								订单提交成功，请您尽快付款！    订单号：  
							</#if>
							<#if relationOrderSn?? >
									${relationOrderSn}
							</#if>
						</h3>
						<p class='p-tips'>请您在提交订单后<span class='font-red'>3小时内</span>完成支付，否则订单会自动取消</p>
					</div>
					<div class='p-right'>
						<div class='pay-price'>
							<em>应付金额</em>
							<strong>${(payAmount)!'' }</strong>
							<em>元</em>
						</div>
						<div class='pay-price discountPrice' style="color: red;display:none"></div>
					</div>
					
					<br>
					<div class='p-left'>
						<div class='form'>
							<label id='canUsedBalanceId'><input type='checkbox' id='selectOrderBalance' name="selectOrderBalance" autocomplete="off" <#if orderSuccessVO?? && orderSuccessVO.isBanlancePay> checked="checked"</#if>>
							使用余额（账户当前余额：${(member.balance)!'0.00' }元）</label>
							支付密码：<input type='password' id='balancePassword' name="balancePassword" disabled <#if orderSuccessVO?? && orderSuccessVO.isBanlancePay> value="123456" </#if>>
						</div>
					</div>
					
					<div class='clr'></div>
					
				</div>
				
				<!-- end -->
				<!-- 支付方式 -->
				<div class='payment'>
					<div class='paybox'>
						<div class='p-wrap'>
							<ul>
								<#--
								<li>
									<input type="radio" name="optionsRadios" id="optionsRadios4" value="cfcapay" checked="checked">
									<label for="optionsRadios4">
									<div class='img-pay'>
										<img width="130" height="40" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/cfca.png'>
									</div>
									</label>
								</li>
								-->
							    <li style="width:260px">
									<input type="radio" name="optionsRadios" id="optionsRadios5" value="bestpay">
									<label for="optionsRadios5">
									<div class='img-pay '>
										<img height="60" id="bestpayactive" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/bestpay.png'>
									</div>
									</label>
								</li>
								<li>
									<input type="radio" name="optionsRadios" id="optionsRadios1" value="alipay" checked="checked"> 
									<label for="optionsRadios1">
									<div class='img-pay'>
										<img width="130" height="45" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/l142.png'>
									</div>
									</label>
								</li>
								<li>
									<input type="radio" name="optionsRadios" id="optionsRadios3" value="weixin">
									<label for="optionsRadios3">
										<div class='img-pay'>
											<img width="130" height="40" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/weixin_logo.png'>
										</div>
									</label>
								</li>
								<li>
									<input type="radio" name="optionsRadios" id="optionsRadios2" value="chinapay">
									<label for="optionsRadios2">
									<div class='img-pay'>
										<img width="130" height="40" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/chinapay.png'>
									</div>
									</label>
								</li>
							</ul>
						</div>
					</div>
					<!-- <div class='paybox bt0'>
						<div class='p-wrap'>
							<ul>
								<li>
									 <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2"> 
									<div class='img-pay'>
										<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/l144.png'>
									</div>
								</li>
								<li>
									<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
									<div class='img-pay'>
										<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/payafter.jpg'>
									</div> 
								</li>
							</ul>
						</div>
					</div> -->
					<div class='payment-column'>&nbsp;</div>
					<div class='pv-button'>
						<!-- 在线支付--> 
						<input type='button' value='立即支付'  class='PayforSubmit' id='PayButtom'> 
					</div>
				</div>
			</div>
		</div>
		
	</form>
		<!-- end -->
		<!-- footer -->
<script type="text/javascript">

$(function(){
	
	isActiveTime();

	//选中余额checkbox 
	$("#selectOrderBalance").click(function(){
		
		//如果余额小于等于0 那么不允许选中
		<#if member??&&member.balance??>
		<#if member.balance<=0>
			<#if !memberCredit?? || memberCredit.surplus <= 0 >
				$(this).prop("checked", false);
			</#if>
		</#if>
		</#if>

		//订单金额
		var orderTotalPrice= $("#sumPayPriceHidden").val();
		var usedBalanceHidden = $("#usedBalanceHidden").val();
		var usedCreditHidden = $("#usedCreditHidden").val();
		
		if($(this).prop("checked")){
			// 计算账户余额及订单金额  订单金额 = 订单金额-优惠 -使用余额
			// 1、订单金额小于或等于余额 则余额使用显示订单金额 ，
			// 2、订单金额大于余额，则余额使用显示余额金额 
			// 余额
			var balance = parseFloat('${(member.balance)!'0.00'}');
			var userblanance = balance;
			balance = balance >= 0?balance:0;

			var creditAmount = 0;
			<#if memberCredit?? && memberCredit.surplus &gt;0 >
			var creditTotal = Number('${(memberCredit.surplus)!0}');
			//余额+可赊账额度 （如果赊账已结清，则余额为0）
			var balanceable =creditTotal;// Number(userblanance) + creditTotal;
			if(userblanance >0){
				//用户还有余额
				balanceable = balanceable+Number(userblanance);
			}
			//只有余额小于订单金额且可支付余额大于等于订单金额时才会使用赊账
			if(Number(balance) < Number(orderTotalPrice) && balanceable >= Number(orderTotalPrice)){
				$(this).parent().next().after("<br/><span style='color: red;'>您的账户可赊账<b>${memberCredit.surplus}</b>元，使用余额支付后，您的账户会产生欠款，请在规定时间内还清欠款。</span>");
				//赊账金额
				creditAmount = Number(orderTotalPrice);
				
				//赊账金额大于赊账总额
				//赊账金额 ＝ 赊账总额
// 				if(creditAmount > creditTotal){
// 					creditAmount = creditTotal;
// 				}
				$("#creditPriceListDiv").show();
				$("#creditPay").html(creditAmount);
				$("#usedCreditHidden").val(creditAmount);
			} else if(balanceable < Number(orderTotalPrice)){
				$(this).parent().siblings("span").remove();
				$(this).parent().next().after("<span style='color: red;'>您的账户余额（包含赊账）总额为<b>"+balanceable+
					"</b>元，其中余额欠款<b>"+userblanance+"</b>元，赊账剩余额度<b>${memberCredit.surplus}</b>元，不足以支付当前订单，请充值或还清欠款。</span>");
				$(this).prop("checked", false);
				$("#balance_pwd_div").hide();
				return;
			}
			</#if>
			// 如果从下单跳转并且选择使用余额，则密码不让输入
			<#if orderSuccessVO?? && orderSuccessVO.isBanlancePay>
				$("#balancePassword").attr("disabled","disabled");
			<#else>
				$("#balancePassword").removeAttr("disabled");
			</#if>
		}else{
			$(this).parent().siblings("span").remove();
			$(this).parent().siblings("br").remove();
			<#if orderSuccessVO?? && orderSuccessVO.isBanlancePay>
				$("#balancePassword").attr("disabled","disabled");
			<#else>
				$("#balancePassword").attr("disabled","disabled");
				$("#balancePassword").val("");
			</#if>
		}
	});
	
	$("#PayButtom").click(function(){
		<#if orderSuccessVO?? && orderSuccessVO.isBanlancePay>
		<#else>
			var balancePwd = $("#balancePassword").val();
	  		if($("#selectOrderBalance").prop("checked")){
	  			if(isEmpty(balancePwd)){
	  				jAlert("密码不能为空");
	  				$("#balancePassword").focus();
	  				return false;
	  			}
	  			//验证支付密码
	  			var checkpwd = checkBalancePwd(balancePwd);
	  			if(!checkpwd){
	  				return false;
	  			}
	  		}
		</#if>
		
		// 支付提交
		//$("#payForm").attr("action", "${(domainUrlUtil.EJS_URL_RESOURCES)!}/order/dopay.html")
		$("#payForm").attr("action", "${(domainUrlUtil.EJS_URL_RESOURCES)!}/payindex.html")
			 .attr("method", "GET")
			 .submit();
	});
});


function isActiveTime() {
	$.ajax({
		type : "GET",
		url :  domain+"/isActiveTime.html",
		success : function(data) {
			if (data == "true") {
				$("#bestpayactive").attr("src", "${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/bestpay2.png");
				var price = parseFloat(${(payAmount)!'0' });
				price = parseFloat(price * 0.997).toFixed(2);
				$(".discountPrice").html("<em>通过翼支付支付，优惠金额为：</em><strong>" + price + "</strong><em>元</em>");
				$(".discountPrice").show();
			}
		}
	});
}

	//验证支付密码
	function checkBalancePwd(balancePwd){
		var correct = false;
		$.ajax({
			type : "GET",
			url :  domain+"/order/checkbalancepwd.html",
			data : {balancePwd:balancePwd},
			dataType : "json",
			async:false,
			success : function(data) {
				if(data.success){
					correct = data.data.correct;
					var errcount = parseInt(data.data.pwdErrCount);
				   	if(errcount>=6){
				   		jAlert("支付密码输错超过6次,请用其他方式支付");
						$(".toggle-title").click();
						return false;
				   	}
					if(!correct){
						jAlert("支付密码不正确，您最多还可以输入"+(6-errcount)+"次");
						return false;
					}
				}else {
					jAlert(data.message);
					return false;
				}
			},
			error : function() {
				jAlert("验证密码失败！");
			}
		});
		return correct;
	}
</script>
	
<#include "/front/commons/_endbig.ftl" />