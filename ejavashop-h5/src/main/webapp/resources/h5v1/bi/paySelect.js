$(function() {
	var ua = navigator.userAgent.toLowerCase();
	   //是否是微信浏览器
	if(ua.match(/MicroMessenger/i) == 'micromessenger') {
		$('.weixin_pay').show();
	}
	
	$("a[data-payway]").click(function() {
		$(this).addClass('on').siblings().removeClass('on');
		var payWay = $(this).data("payway");
		$("#optionsRadios").val($(this).data("payway"));
		if (payWay == "balance") {
			countPayResult();
			$("#selectOrderBalance").val("on");
			$("#balancePassword").focus();
		}
		else {
			$("#selectOrderBalance").val("");
			if ($(".confirmPay").hasClass("sub")) {
				$(".confirmPay").addClass("stop1 radius public3");
				$(".confirmPay").attr("disabled","");
				$(".confirmPay").removeClass("sub");	
			}
		}
	});
	
	$(".confirm-pay, .balance-pay").click(function(){
		var pay_action = $("#optionsRadios").val();
		if(pay_action == ""){
			alert_("未选择支付方式");
			return;
		}
		if ($("#selectOrderBalance").val() == "on") {
			var balancePwd = $("#balancePassword").val();
			if (balancePwd == null || balancePwd == undefined || balancePwd == "") {
				alert_("密码不能为空");
  				$("#balancePassword").focus();
  				return false;
  			}
  			//验证支付密码
  			if (!checkBalancePwd(balancePwd)) {
  				$("#balancePassword").val("");
  				$("#balancePassword").focus();
  				return false;
  			}
		}
		$("#payForm").attr("action", domain + "/payindex.html").attr("method", "GET").submit();
	});
});

function countPayResult() {
	//订单金额
	var orderTotalPrice= $("#sumPayPriceHidden").val();
	// 余额
	var userblanance = parseFloat($("#memberBalance").val());
	
	var creditTotal = parseFloat($("#memberCredit").val());
	//余额+可赊账额度 （如果赊账已结清，则余额为0）
	var balanceable = creditTotal.toFixed(2);
	//只有余额小于订单金额且可支付余额大于等于订单金额时才会使用赊账
	if (Number(userblanance) < Number(orderTotalPrice) && balanceable >= Number(orderTotalPrice)) {
		$(".after_balance").html("您的账户可赊账<b>" + creditTotal + "</b>元，使用余额支付后，您的账户会产生欠款，请在规定时间内还清欠款。");
		//赊账金额
		creditAmount = Number(orderTotalPrice);
	} 
	else if (balanceable < Number(orderTotalPrice)) {
		$(".after_balance").html("您的账户余额（包含赊账）总额为<b>" + balanceable + "</b>元，其中余额欠款<b>" + userblanance + "</b>元，可赊账<b>" + creditTotal + "</b>元，不足以支付当前订单，请充值或还清欠款。</span>");
		$(this).prop("checked", false);
		$("#balancePassword").attr("disabled","disabled");
		$(".confirmPay").removeClass("stop1 radius public3");
		$(".confirmPay").addClass("sub");
		$("#selectOrderBalance").val("");
		$("a[data-payway='balance']").removeClass("on");
		$("#optionsRadios").val("");
		return;
	}
}

//验证支付密码
function checkBalancePwd(balancePwd) {
	var correct = false;
	$.ajax({
		type : "GET",
		url :  domain + "/order/checkbalancepwd.html",
		data : {balancePwd : balancePwd},
		dataType : "json",
		async:false,
		success : function(data) {
			if (data.success) {
				correct = data.data.correct;
				var errcount = parseInt(data.data.pwdErrCount);
			   	if (errcount >= 6) {
			   		alert_('支付密码输错超过6次,请用其他方式支付');
					$(".toggle-title").click();
					return false;
			   	}
				if (!correct) {
					alert_("支付密码不正确，您最多还可以输入" + (6-errcount) + "次", function() {
						$('.cd-user-modal').addClass('is-visible');
					});
					return false;
				}
			}
			else {
				alert_(data.message);
				return false;
			}
		},
		error : function() {
			alert_("验证密码失败！");
		}
	});
	return correct;
}