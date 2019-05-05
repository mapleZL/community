$(function(){
	// 总数
	//var sellerCount = parseInt($("#sellerCount").val());
	// 当前加载的页数
	//var sellerPageIndex = parseInt($("#sellerPageIndex").val());
	// 如果总数量小于等于已经加载的数量，则表示没有更多，隐藏加载更多按钮，显示没有更多
	//if (sellerCount <= (pageSize * sellerPageIndex)) {
	//	$("#addMoreSellerDiv").hide();
	//	$("#noMoreSellerDiv").show();
	//}
	
	// 取消关注商品
	$('.cancle').live('click', function (){
		confirm_("确定取消收藏？", this, function(t){
			var productId = t.data("goods-id");
			cancleCollect(productId,t);
		});
	});

	if(!pageIsHistory()) {
		moreList();
	}
	
	// 加载更多收藏商品
	$('.more1').click(function (){
		moreList();
	});
	
	$(window).scroll(function() {
		if ($(document).scrollTop() >= ($(document).height() - $(window).height())) {
			moreList();
		}
	});
});

function moreList() {
	if (!$("#product_list_more_json_no").is(":hidden")) 
		return;
	if (isLoading) return;
	isLoading = true;
	var productCount = parseInt($("#productCount").val());
	// 当前加载的页数
	var productPageIndex = parseInt($("#productPageIndex").val());
	// 每页加载数量
	var pageSize = parseInt($("#pageSize").val());
	$("#addMoreProductDiv").hide();
	$("#product_list_more_json_wait").show();
	$.ajax({
		type:"GET",
		url: domain + "/member/morecollectproduct.html",
		dataType:"html",
		data:{pageIndex:productPageIndex+1},
		success:function(data){
			//加载数据
			$("#productDiv").append(data);
			$("[lazyLoadSrc]").YdxLazyLoad({});
			
            var ssVal = sessionStorage.getItem("sessionKey1");
			if (ssVal == null) ssVal = "";
			sessionStorage.setItem("sessionKey1", ssVal + data);
			// 页码加1
			$("#productPageIndex").val(productPageIndex + 1);
			if (productCount <= (pageSize * (productPageIndex+1))) {
				$("#addMoreProductDiv").hide();
				$("#product_list_more_json_no").show();
				$("#product_list_more_json_wait").hide();
			}
			else {
				$("#addMoreProductDiv").show();
				$("#product_list_more_json_no").hide();
				$("#product_list_more_json_wait").hide();
			}
		},
        complete : function(XMLHttpRequest,status){
			isLoading = false;
        }
	});
}

function cancleCollect(productId,obj){
	obj.parent().parent().parent().parent().fadeTo("slow", 0.01, function(){//fade
		$(this).slideUp("slow", function() {//slide up
			$(this).remove();//then remove from the DOM
			sessionStorage.setItem("sessionKey1", $("#productDiv").html());
		});
	});
	if (true) return;
	$.ajax({
		type:"GET",
		url:domain+"/member/cancelcollectproduct.html",
		dataType:"json",
		async : false,
		data : {productId:productId},
		success:function(data){
			if(data.success){
				obj.parent().parent().parent().parent().fadeTo("slow", 0.01, function(){//fade
					$(this).slideUp("slow", function() {//slide up
						$(this).remove();//then remove from the DOM
						sessionStorage.setItem("sessionKey1", $("#productDiv").html());
					});
				});
			}else{
				alert_(data.message);
			}
		}
	});
}

function pageIsHistory() {
	var list_page = $("#productPageIndex").val();
	if (list_page == 0){
	    sessionStorage.removeItem("sessionKey1");
	    return false;
	}else {
		var ssVal1 = sessionStorage.getItem("sessionKey1");
		$("#productDiv").append(ssVal1);
		$("[lazyLoadSrc]").YdxLazyLoad({});

		var productCount = parseInt($("#productCount").val());
		var productPageIndex = parseInt($("#productPageIndex").val());
		var pageSize = parseInt($("#pageSize").val());
		if (productCount <= (pageSize * (productPageIndex))) {
			$("#addMoreProductDiv").hide();
			$("#product_list_more_json_no").show();
			$("#product_list_more_json_wait").hide();
		}
		return true;
	}
}