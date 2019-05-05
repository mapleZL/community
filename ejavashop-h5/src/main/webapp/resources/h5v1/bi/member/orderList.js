
var sessionKey = "orderListSession";

function pageIsHistory() {
	var list_page = $("#pageIndex").val();
	if (list_page == 0) {
		sessionStorage.removeItem(sessionKey);
		return false;
	}
	else {
		var ssVal = sessionStorage.getItem(sessionKey);
		$("#ordersListDiv").append(ssVal);
		$("[lazyLoadSrc]").YdxLazyLoad({});
		return true;
	}
}

function currTab() {
	var currState = $("#orderState").val();
	$("a[data-state]").removeClass("cur");
	$("a[data-state]").each(function() {
		if (currState == $(this).data("state")) {
			$(this).addClass("cur");
			return false;
		}
	});
}

$(function(){
	currTab();
	
	if (!pageIsHistory()) {
		addMoreOrder();
	}
	//确认收货
	$(document).on("click",".order_confirm", function() {
		confirm_("您确定已经收到商品了吗？", this, function(t) {
			goodsReceipt(t);
		});
	});
	//确认取消
	$(document).on("click",".order_delete", function() {
		confirm_("确定要取消订单吗？", this, function(t) {
			cancalOrder(t);
		});
	});
	//确认删除
	$(document).on("click",".order_cancel", function() {
		confirm_("确定要取消订单吗？", this, function(t) {
			deleteOrder(t);
		});
	});
	
	$("a[data-state]").click(function(){
		var state = $(this).data("state");
		location.href = domain + "/member/order.html?orderState=" + state;
	});
	
	$(window).scroll(function() {
		if ($(document).scrollTop() >= ($(document).height() - $(window).height())) {
			addMoreOrder();
		}
	});
});

// 加载更多
function addMoreOrder() {
	if (!$("#noMoreOrderDiv").is(":hidden")) 
		return;
	if (isLoading) return;
	isLoading = true;
	$("#addMoreOrderDiv").hide();
	$("#waitOrderDiv").show();
	$("#noMoreOrderDiv").hide();
	// 总数
	var ordersCount = parseInt($("#ordersCount").val());
	// 当前加载的页数
	var pageIndex = parseInt($("#pageIndex").val());
	// 每页加载数量
	var pageSize = parseInt($("#pageSize").val());
	pageIndex += 1;
	var moreUrl = domain + "/member/moreorder.html";
	var currUrl = location.href;
	cu = currUrl.split("?");
	if (cu.length > 1) 
		moreUrl = moreUrl;// + "?" + cu[1];
	$.ajax({
		type:"GET",
		url : moreUrl,
		dataType:"html",
		data:{orderState:$("#orderState").val(),pageIndex:pageIndex},
		success:function(data){
			//加载数据
			$("#ordersListDiv").append(data);

			var ssVal = sessionStorage.getItem(sessionKey);
			if (ssVal == null) ssVal = "";
            sessionStorage.setItem(sessionKey, ssVal + data);
			// 页码加1
			$("#pageIndex").val(pageIndex);
			if (ordersCount <= (pageSize * (pageIndex))) {
				$("#addMoreOrderDiv").hide();
				$("#noMoreOrderDiv").show();
			}
			else {
				$("#addMoreOrderDiv").show();
			}
			$("#waitOrderDiv").hide();
			$("[lazyLoadSrc]").YdxLazyLoad({});
		},
        complete : function(XMLHttpRequest,status){
			isLoading = false;
        }
	});
}

//取消订单
function cancalOrder(obj){
	var tradeNo = obj.data("order-tradeno");
		$.ajax({
			type : "GET",
			url :  domain+"/member/cancalorder.html",
			data : {tradeNo:tradeNo},
			dataType : "json",
			success : function(data) {
				if(data.success){
					remove_(obj.parent().parent());
				}else {
					 alert_(data.message);
					 //$.dialog('alert','提示',data.message,2000);
				}
			}
	});
}

//删除订单
function deleteOrder(obj){
	var orderId = obj.data("order-orderid");
		$.ajax({
			type : "GET",
			url :  domain+"/member/deleteorder.html",
			data : {orderId:orderId},
			dataType : "json",
			success : function(data) {
				if(data.success){
					remove_(obj.parent().parent());
				}else {
					 alert_(data.message);
					 //$.dialog('alert','提示',data.message,2000);
				}
			}
	});
}

//确认收货
function goodsReceipt(obj){
	var ordersId = obj.data("order-id");
	$.ajax({
		type : "GET",
		url :  domain+"/member/goodreceive.html",
		data : {ordersId:ordersId},
		dataType : "json",
		success : function(data) {
			if(data.success){
				// 修改显示状态
				remove_(obj.parent().parent());
			}else {
				 alert_(data.message);
				 //$.dialog('alert','提示',data.message,2000);
			}
		}
	});
}