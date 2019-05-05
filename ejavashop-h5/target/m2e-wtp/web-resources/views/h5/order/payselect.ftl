<#include "/h5/commons/_head.ftl" />
<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
<script type="text/javascript" src="http://tool.keleyi.com/ip/visitoriphost/"></script> 

<body class="bgf2">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/member/order.html">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">收银台</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
   
   	<@form.form action="" id="payForm" name="payForm" method="GET">
		<input type="hidden" name="paySessionstr" value="${paySessionstr!''}">
		<input type="hidden" name="relationOrderSn" id="relationOrderSn" value="${relationOrderSn!''}">
		<input type="hidden" name="fromType" id="fromType" value="${fromType!''}">
		<input type="hidden" name="localIp" id="localIp"/>
		<!-- <input type="hidden" name="payType"  value=""> --><!-- 支付方式 -->
		<div>
	         <div class="p-o-infor">
	           <p>
	           <#if fromType?? && fromType == 2>
					订单号：  
				<#else>
					订单提交成功，请您尽快付款！    订单号：  
				</#if>
				<#if relationOrderSn?? >
					<font>${relationOrderSn}</font>
				</#if>
				
	            <!-- 订单提交成功，请您尽快付款！<br>
	            订单号：<font>123456789</font> -->
	            </p>
	            <!-- 获取本机外网ip用的 用于微信支付 -->
	            <p id="keleyivisitorip"  style="display:none;"></p>
	            <p class="font12 pad-top">请您在提交订单后<font class="clr53">3小时内</font>完成支付,否则订单会自动取消</p>
	         </div>
	         
	        
	         <ul class="p-o-method">
	            <li class="flex flex-pack-justify font16">
	               <div>选择支付方式</div>
	               <div class="clr53">${(payAmount)?string('0.00')!'' }元</div>
	            </li>
	            <#--<li class="flex flex-pack-justify">
	               <div>中金支付</div>
	               <div><input id="optionsRadios" name="optionsRadios" type="radio" value="cfcapay" class="p-radio"></div>
	            </li>
	            <li class="flex flex-pack-justify">
	               <div>网银在线</div>
	               <div><input id="optionsRadios" name="optionsRadios" type="radio" value="chinapay" class="p-radio"></div>
	            </li>
	            -->
	            <li class="flex flex-pack-justify">
	               <div>支付宝</div>
	               <div><input id="optionsRadios" name="optionsRadios" type="radio" value="alipay" class="p-radio"></div>
	            </li>
								
	            <li class="flex flex-pack-justify" id = "weixin">
	               <div>微信支付</div>
	               <div><input id="wxpay" name="optionsRadios" type="radio" value="wxpay" class="p-radio" checked="checked"></div>
	            </li>
	            <#--
	            <li class="flex flex-pack-justify">
	               <div>翼支付</div>
	               <div><input id="bestpay" name="optionsRadios" type="radio" value="bestpay" class="p-radio"></div>
	            </li>
	            -->
	         </ul>
	         <input type="hidden" name= "bankCardNo">
	   
	    	<div class="add-balance pad10">
	            <p>
	            	<input type="hidden" id="sumPayPriceHidden" value="${(payAmount)?string('0.00')!'' }"/>
	            	<input type='checkbox' id='selectOrderBalance' name="selectOrderBalance" 
	            		autocomplete="off" <#if orderSuccessVO?? && orderSuccessVO.isBanlancePay> checked="checked"</#if>>
	            	&nbsp;使用余额(账户当前余额${(member.balance)?string('0.00')!'0.00' }元)</p>
	            <p class="mar_top">
	            	支付密码：
	            	<input class="form-control add-form-control" type='password' 
	            		id='balancePassword' name="balancePassword" disabled <#if orderSuccessVO?? && orderSuccessVO.isBanlancePay> value="123456" </#if>>
	            </p>
	         </div>
	         <div class="pad10"><button type="submit" class="btn btn-block btn-login" id='PayButtom'>立即支付</button></div>
	    </div>
	</@form.form>
	<!-- 主体结束 -->

	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />
 <script type="text/javascript">

$(function(){
	//选中余额checkbox 
	$("#selectOrderBalance").click(function(){
		// 如果余额小于等于0 那么不允许选中
		<#if member??&&member.balance??>
			<#if member.balance<=0 >
				<#if !memberCredit?? || memberCredit.surplus <= 0 >
					$(this).prop("checked", false);
					return;
				</#if>
			</#if>
		</#if>
		
		if($(this).prop("checked")){
			$("#balancePassword").removeAttr("disabled");

			//订单金额
			var orderTotalPrice= $("#sumPayPriceHidden").val();
			var usedBalanceHidden = $("#usedBalanceHidden").val();
			var usedCreditHidden = $("#usedCreditHidden").val();
			if (usedBalanceHidden == null) {
				usedBalanceHidden = 0;
			}
			if (usedCreditHidden == null) {
				usedCreditHidden = 0;
			}
			
			// 余额
			var balance = parseFloat('${(member.balance)!'0.00'}');
			var userblanance = balance;

			var creditAmount = 0;
			<#if memberCredit?? && memberCredit.surplus &gt;0 >
			var creditTotal = Number('${(memberCredit.surplus)!0}');
			//余额+可赊账额度 （如果赊账已结清，则余额为0）
			var balanceable = creditTotal.toFixed(2);
			//只有余额小于订单金额且可支付余额大于等于订单金额时才会使用赊账
			if(Number(balance) < Number(orderTotalPrice) && balanceable >= Number(orderTotalPrice)){
				$(this).parent().
					after("<span style='color: red;'>您的账户可赊账<b>${memberCredit.surplus}</b>元，使用余额支付后，您的账户会产生欠款，请在规定时间内还清欠款。</span>");
				//赊账金额
				creditAmount = Number(orderTotalPrice);
			} else if(balanceable < Number(orderTotalPrice)){
				$(this).parent().siblings('span').remove();
				$(this).parent().after("<span style='color: red;'>您的账户余额（包含赊账）总额为<b>"+balanceable+
					"</b>元，其中余额欠款<b>"+userblanance+
					"</b>元，可赊账<b>${memberCredit.surplus}</b>元，不足以支付当前订单，请充值或还清欠款。</span>");
				$(this).prop("checked", false);
				$("#balancePassword").attr("disabled","disabled");
				return;
			}
			</#if>
		} else{
			$(this).parent().siblings('span').remove();
			$("#balancePassword").attr("disabled","disabled");
		}
	});
	
	$("#PayButtom").click(function(){
		<#if orderSuccessVO?? && orderSuccessVO.isBanlancePay>
		<#else>
			var balancePwd = $("#balancePassword").val();
	  		if($("#selectOrderBalance").prop("checked")){
	  			if(balancePwd == null || balancePwd == ""){
	  				// alert("密码不能为空");
	  				$.dialog('alert','提示','密码不能为空',2000);
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
		var ip = document.getElementById("keleyivisitorip").innerHTML;
		$('#localIp').val(ip);
		//去支付
		$("#payForm").attr("action", "${(domainUrlUtil.EJS_URL_RESOURCES)!}/payindex.html")
			 .attr("method", "GET")
			 .submit();
	});
});
		


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
				   		// alert("支付密码输错超过6次,请用其他方式支付");
				   		$.dialog('alert','提示','支付密码输错超过6次,请用其他方式支付',2000);
						$(".toggle-title").click();
						return false;
				   	}
					if(!correct){
						// alert("支付密码不正确，您最多还可以输入"+(6-errcount)+"次");
				   		$.dialog('alert','提示',"支付密码不正确，您最多还可以输入"+(6-errcount)+"次",2000);
						return false;
					}
				}else {
					// alert(data.message);
					$.dialog('alert','提示',data.message,2000);
					return false;
				}
			},
			error : function() {
				// alert("验证密码失败！");
				$.dialog('alert','提示','验证密码失败！',2000);
			}
		});
		return correct;
	}
</script>

</body>
</html>