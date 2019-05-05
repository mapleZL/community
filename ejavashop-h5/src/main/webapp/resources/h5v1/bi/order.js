var sessionCartId = "cartId";
var sessionExpress = "expressId";
var sessionPayWay = "payWay";
var sessionPayBill = "payBill";
var sessionSendArea = "sendArea";
var sessionRedId = "redId";

$("a[data-id]").click(function(){
	$(this).addClass('cur').siblings().removeClass('cur');
});
$(".detail").click(function(){
    var zhan = 1;
	if ($(".hide").css("display") == "none") {
		$(".hide").css("display","block");
		$(".detail").addClass("up");
	    $(".detail").removeClass("drop");
		zhan=2;
    } 
	else {
	   $(".hide").css('display','none');
       $(".detail").addClass("drop");
	   $(".detail").removeClass("up");
        zhan = 1;
     }
})
	
$(document).on("click",".address_choose", function() {
	window.location.href = domain+"/member/address.html?isFromOrder=1";
});

$(function(){

	getHistoryTransfer();
	$(".transportArea a").click(function() {
		var cartId = $(this).data("cartid");
		sessionStorage.setItem(sessionCartId, cartId);
		/*
			$(".transportArea li").removeClass("am-active");
			$(this).parent().addClass("am-active");
		*/
		countExpressFee(cartId);
	});
	
	$(".express a").click(function() {
		$(this).addClass('cur').siblings().removeClass('cur');
		ajaxExpressFee($(this).data("express"));
	});
	
	//选择配送区域
	$("a[data-area]").click(function() {
		var areaId = $(this).data("area");
		$("#transArea").val(areaId);
		sessionStorage.setItem(sessionSendArea, areaId);
		changeTransportFee();
		$(".ownsendareadiv").eq(0).html($(this).html());
		$("#my-actions").modal('close');
	});
	
	//选择红包
	$("a[data-red]").click(function() {
		var couponSn = $(this).data("red");
		//记录使用的红包的商家ID
		if(couponSn != ""){
			$("#useCouponSellerIds").val(0);
		}
		checkCouponSn(couponSn);
		sessionStorage.setItem(sessionRedId, redId);
		$(".red_show").eq(0).html($(this).html());
		$("#my-redlist").modal('close');
	});
	
	//支付方式选择
	$(".pay_way a").click(function(){
		var payValue = $(this).data("id");
		putPayWay(payValue);
		sessionStorage.setItem(sessionPayWay, payValue);
	});
	
	$(".need_bill a").click(function(){
		var remark = $(this).data("id");
		sessionStorage.setItem(sessionPayBill, remark);
		putNeedBill(remark);
	});
	
	$("#submitCart").click(function() {
		if ($("#totalCartAmount").val() == 0) {
	    	alert_('请不要重复提交订单！');
	    	return;
		}
		var transId = $("#transId").val();
		var paymentcode = $("#paymentCode").val();
		var paymenrname = $("#paymentName").val();
		if(transId == 5){
			var sendArea = $("#transArea").val();
			if(sendArea == 0 && transId !=8 && transId !=6 && transId !=7){
				alert_("请选择配送区域");
				return;
			}else{
				//送货上门时判断地址是否为浙江地区
  		    	var selectaddressAll = $("#tzm_addressAll").val();
  		    	if(selectaddressAll.indexOf("浙江")<0){
	  		    	alert_('送货上门只针对浙江的特定区域。<br/>请重新选择收货地址;<br/>或者更改配送方式。');
	  		    	$('#sendArea').focus();
	  		    	return;
  		    	}
				$("#transId").val(transId+""+sendArea);
			}
		}
		else if(transId == 0){
			alert_("请在页首选择配送方式");
			return;
		}
		// 判断收货地址是否存在
		var addressId = $("#addressId").val();
		if(addressId == null || addressId == "" || addressId == 0){
			alert_('请添加或选择收货地址');
			return false;
		}
		if(transId == 6 || transId == 7){
			var realName = $("#realName").val();
			if(realName == ""){
				alert_('尊敬的用户,即将跳转个人资料<br/>请完善您的真实姓名,谢谢');
				setTimeout(dealAction(),5000);  
				return false;
			}
		}
		//判断订单类型.
		var servicePrice = $("#servicePrice").val();
		var ordersType;
		if(servicePrice > 0){
			ordersType = 5;
			$("#ordersTypeName").val(5);
		}else if(servicePrice = 0){
			ordersType = 1;
			$("#ordersTypeName").val(1);
		}
		
		var  tzm_ttfee = $("input[name='tzm_ttfee']");
		var tzm_ttfee_bool = false;
		tzm_ttfee.each(function(i){
			if(parseFloat($(this).val()) > 0 ){//如果有套餐费，则此订单存在二次加工
				tzm_ttfee_bool = true;
			}
		})
		if(tzm_ttfee_bool){
			//设置为1代表次订单为二次加工订单，后台做特殊处理
			$("#tzm_twoJG").val("1");
		}
		var actionUrl = domain + "/order/ordercommit.html";
		var param = $("#orderForm").serialize();
 		// 提交订单按钮屏蔽，避免重复提交
 		$(this).attr("disabled", "disabled");
 		// 提交loading
	 	$('body').append("<div id='submit_loading' class='purchase-loading'><div class='loading-cont'></div></div>");
	 	sessionStorage.removeItem(sessionCartId);
	 	sessionStorage.removeItem(sessionExpress);
	 	sessionStorage.removeItem(sessionPayWay);
	 	sessionStorage.removeItem(sessionPayBill);
	 	sessionStorage.removeItem(sessionSendArea);
	 	sessionStorage.removeItem(sessionRedId);
		$.ajax({
			type : "POST",
			dataType : "json",
			url : actionUrl,
			data : param,
			async:false,
			success : function(result) {
				if (result == null) {
					errorSubmit();
					return false;
				}
				if (result.success) {
					var data = result.data;
					var paySessionstr = data.paySessionstr;
					var goJumpPayfor = data.goJumpPayfor;
					var relationOrderSn = data.relationOrderSn;
					var payAmount = data.payAmount;
					
					 //跳转到成功页面
					 if (goJumpPayfor) {
						successUrl = domain+"/order/pay.html";
						newurl = successUrl + "?relationOrderSn=" + relationOrderSn + "&paySessionstr="+paySessionstr+"&rid=" + Math.random();
						window.setTimeout('window.location.href=newurl;', 450);
						return;
					} else {
						successUrl = domain+"/order/success.html";
						window.location.href = successUrl+"?relationOrderSn="+relationOrderSn+"&rd="+Math.random();
						return;
					}
				}
				else {
					$("input[name='CSRFToken']").val(result.csrfToken);
					if (result.message != null) {
						$("#submit_loading").remove();
						// alert(result.message);
							alert_(result.message);
						return;
					} else {
						errorSubmit();
					}
				}
			},
			error : function(error) {
				$("#submitCart").removeAttr("disabled");
				$("#submit_loading").remove();
 				alert_('亲爱的用户请不要频繁点击, 请稍后重试...');
			}
		});
		
	});
});

function checkCouponSn(couponSn){
	var currType = $("#currType").val();
	var sellerId = 0
	var orderAmount =  $("#finalAmount").val();
    // 校验优惠券可用性
	 $.ajax({
		type : "GET",
		url :  domain+"/order/getmarketcoupon.html",
		data : {
			orderAmount:orderAmount,
			couponType:currType,
			couponSn:couponSn,
			servicePrice:$("#servicePrice").val(),
			sellerId:sellerId
		},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				$("#couponPassword0").val(data.data.password);
				$("#couponSn0").val(data.data.couponSn);
				calculateCouponValue(sellerId, data.data.couponValue,currType);
            }
		}
	});
}

function calculateCouponValue(sellerId,newCouponValue,type){
	// 订单金额
	var sumPayPriceHidden= $("#finalAmount").val();
	//判断 如果 自配送则运费不加
	var boolFlag = $("#transId").val();
	var logisticsFeeAmount;
	if(boolFlag==5 && type !=1){
		logisticsFeeAmount = $("#logisticsFeeAmount").val();
		sumPayPriceHidden = parseFloat(sumPayPriceHidden)-parseFloat(logisticsFeeAmount)
	}
	//end
	// 如果修改使用的优惠券，则需要重新计算订单应付金额
	var couponValueOld = $("#couponValue" + sellerId).val();
	// 已使用红包的金额和
	var couponValueSum = $("#couponValueSum").val();
	if (couponValueOld == null) {
		couponValueOld = 0;
	}
	if (couponValueSum == null) {
		couponValueSum = 0;
	}
	
	// 新的订单金额=原订单金额+被替换的优惠券金额-新使用的优惠券金额
	sumPayPriceHidden = parseFloat(sumPayPriceHidden) + parseFloat(couponValueOld) - parseFloat(newCouponValue);
	sumPayPriceHidden = sumPayPriceHidden.toFixed(2);
	// 新的优惠券金额和=原优惠券金额和+被替换的优惠券金额-新使用的优惠券金额
	couponValueSum = parseFloat(couponValueSum) + parseFloat(couponValueOld) - parseFloat(newCouponValue);
	couponValueSum = couponValueSum.toFixed(2);
	$("#finalamountFont").html("合计：¥ " + sumPayPriceHidden);
	$("#heji").html("¥" + sumPayPriceHidden);
	$("#shouldPay").html("￥" + sumPayPriceHidden);
	$("#couponReduce").html("-  ¥" + parseFloat(newCouponValue).toFixed(2));
	$("#finalAmount").val(sumPayPriceHidden);
	// 记录该优惠券的抵扣金额
	$("#couponValue" + sellerId).val(newCouponValue);
	// 记录新的金额和
	$("#couponValueSum").val(couponValueSum);
	// 记录新的订单金额
	$("#sumPayPriceHidden").val(sumPayPriceHidden);
	
	// 修改显示金额
	$("#sumPayPriceId").html("￥"+sumPayPriceHidden);
	$("#payPriceId").html("￥"+sumPayPriceHidden);
	// 把负数改成正数显示
	couponValueSum = parseFloat(Math.abs(couponValueSum)).toFixed(2);
	$("#couponPriceId").html(couponValueSum);
}

function errorSubmit() {
	// 更新token值
	$("#submitCart").removeAttr("disabled");
	$("#submit_loading").remove();
	alert_('亲爱的用户请不要频繁点击, 请稍后重试...');
	return;
}

function getHistoryTransfer() {
	//选择物流
	if (sessionStorage.getItem(sessionCartId) != null) {
		var cartId = sessionStorage.getItem(sessionCartId);
		$("a[data-cartid]").each(function(i) {
			if ($(this).data("cartid") == cartId) {
				$(this).parent().addClass('am-active').siblings().removeClass('am-active');
				$(".am-tab-panel").removeClass("am-active am-in");
				$(".am-tab-panel").eq(i).addClass("am-active am-in");
				countExpressFee(cartId);
				if (cartId == 5) {
					//配送地区
					if (sessionStorage.getItem(sessionSendArea) != null) {
						var sendArea = sessionStorage.getItem(sessionSendArea);
						$("a[data-area]").each(function(i) {
							if ($(this).data("area") == sendArea) {
								$("#transArea").val(sendArea);
								changeTransportFee();
								$(".ownsendareadiv").eq(0).html($(this).html());
								return false;
							}
						});
					}
				}
				else if (cartId == 0) {
					//运送方式
					if (sessionStorage.getItem(sessionExpress) != null) {
						var expressId = sessionStorage.getItem(sessionExpress);
						$(".express a").each(function(i) {
							if ($(this).data("express") == expressId) {
								$(this).addClass('cur').siblings().removeClass('cur');
								ajaxExpressFee(expressId);
								return false;
							}
						});
					}
				}
				return false;
			}
		});
	}
	//支付方式
	if (sessionStorage.getItem(sessionPayWay) != null) {
		var payWay = sessionStorage.getItem(sessionPayWay);
		$(".pay_way a").each(function(i) {
			if ($(this).data("id") == payWay) {
				$(this).addClass('cur').siblings().removeClass('cur');
				putPayWay(payWay);
				return false;
			}
		});
	}
	//订单备注
	if (sessionStorage.getItem(sessionPayBill) != null) {
		var payBill = sessionStorage.getItem(sessionPayBill);
		$(".need_bill a").each(function(i) {
			if ($(this).data("id") == payBill) {
				$(this).addClass('cur').siblings().removeClass('cur');
				putNeedBill(payBill);
				return false;
			}
		});
	}
	//是否选择红包
	if (sessionStorage.getItem(sessionRedId) != null) {
		var redId = sessionStorage.getItem(sessionRedId);
		$("a[data-red]").each(function(i) {
			if ($(this).data("red") == redId) {
				$("#redId").val(redId);
				$(".red_show").eq(0).html($(this).html());
				return false;
			}
		});
	}
}

function dealAction(){
 	window.location.href=domain+'/member/info.html';
 }
 
//计算运费
function ajaxExpressFee(transType) {
	sessionStorage.setItem(sessionExpress, transType);
	$("#transId").val(transType);
	var addressId = $("#addressId").val();
	//使用Ajax异步调用计算运费
	$.ajax({
		type : "POST",
		url :  domain+"/order/calculateTransFee.html",
		data : {addressId:addressId,transportType:transType},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				$("#shipfee").html(" + ￥" + data.data.logisticsFeeAmount.toFixed(2));
				$("#finalamountFont").html("合计：¥ " + data.data.finalAmount.toFixed(2));
				$("#shouldPay").html("￥" + data.data.finalAmount.toFixed(2));
			}
		}
	});
}

function countExpressFee(cartId) {
	$("#shipfee").html(" + ￥0.00");
	var finalAmount = parseFloat($("#finalAmount").val());
	var logisticsFeeAmount = parseFloat($("#logisticsFeeAmount").val());
	$("#finalamountFont").html("合计：¥ " + (finalAmount - logisticsFeeAmount).toFixed(2));
 	$("#transId").val(cartId);
 	$("transArea").val(0);
}

//设置支付方式
function putPayWay(payValue) {
	if (payValue == 1) {
		$("#paymentName").val("在线支付");
		$("#paymentCode").val("ONLINE");
	}
	else if (payValue == 2) {
		$("#paymentName").val("线下打款");
		$("#paymentCode").val("OFFLINE");
	}
	else if (payValue == 3) {
		$("#paymentName").val("余额支付");
		$("#paymentCode").val("BALANCE");
	}
}

//
function putNeedBill(remark) {
	if (remark == 1) {
		$("#remark").val("需要发货单");
	}
	else if(remark == 2) {
		$("#remark").val("不需要发货单");
	}
}

//配送地区费用重新计算
function changeTransportFee() {
	var sendArea = $("#transArea").val();
	var totalCartAmount = $('#totalCartAmount').val();
	var box_amount = parseFloat(totalCartAmount/500).toFixed(2);
	var arr_am = box_amount.split('.');
	var t = parseFloat(arr_am[0]);
	var transportFee = 0.00;
	if(arr_am[1]>0){
		t += 1;
	}
	//一区运费计算
	if(sendArea==2){
		transportFee = t*4;
		if(transportFee<20){
			transportFee = 20;
		}
		if(totalCartAmount >=1000){
			transportFee = 0.00;
		}
	}
	//二区运费计算
	if(sendArea==3){
		transportFee = t*4.5;
		if(transportFee < 180){
			transportFee = 180;
		}
		if(totalCartAmount >=5000){
			transportFee = 0.00;
		}
	}
	//三区运费计算
	if(sendArea==4){
		transportFee = t*5.5;
		if(transportFee < 330){
			transportFee = 330;
		}
		if(totalCartAmount >=2000){
			transportFee = 0.00;
		}
	}
	//四区运费计算
	if(sendArea==5){
		transportFee = t*5;
		if(transportFee < 250){
			transportFee = 250;
		}
		if(totalCartAmount >=2000){
			transportFee = 0.00;
		}
	}
	//动态计算运费
	var finalAmount = parseFloat($("#finalAmount").val());
	var logisticsFeeAmount = parseFloat($("#logisticsFeeAmount").val());
	var total_money = (finalAmount - logisticsFeeAmount + +transportFee).toFixed(2);
	$("#shipfee").html(" + ￥"+transportFee);
	$("#finalamountFont").html("合计：¥ " + total_money);
	$("#shouldPay").html("￥" + total_money);
	$("#heji").html("￥" + total_money);
}