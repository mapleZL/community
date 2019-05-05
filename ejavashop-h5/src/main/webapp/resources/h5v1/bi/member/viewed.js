$(function(){

	if (!pageIsHistory()) {
		addMoreLog();
	}
	
	$('#showMore').click(function (){
		addMoreLog();
	});
	
	$(window).scroll(function() {
		if ($(document).scrollTop() >= ($(document).height() - $(window).height())) {
			addMoreLog();
		}
	});
	
	//$('.every-li').touchWipe({itemDelete: '.btn'});
	//删除浏览记录
	$(document).on("click",".deletelog", function() {
			deleteProductId(this);
	});
	
});

//删除浏览记录
function deleteProductId(t){
	var productId = $(t).data("order-productid")
		$.ajax({
			type : "GET",
			url :  domain+"/member/deleteproductlog.html",
			data : {productId:productId},
			dataType : "json",
			success : function(data) {
				if(data.success){
					remove_($(t).parent().parent().parent().parent());
				}else {
					 alert_(data.message);
					 //$.dialog('alert','提示',data.message,2000);
				}
			}
	});
}

// 加载更多
function addMoreLog() {
	if (!$("#product_list_more_json_no").is(":hidden")) 
		return;
	if (isLoading) return;
	isLoading = true;
	// 总数
	var logCount = parseInt($("#logCount").val());
	// 当前加载的页数
	var pageIndex = parseInt($("#pageIndex").val());
	// 每页加载数量
	var pageSize = parseInt($("#pageSize").val());
	// 如果总数量小于等于已经加载的数量，则表示没有更多，隐藏加载更多按钮，显示没有更多
	$("#addMoreLogDiv").hide();
	$("#product_list_more_json_no").hide();
	$("#product_list_more_json_wait").show();
	$.ajax({
		type:"GET",
		url:domain + "/member/moreviewed.html",
		dataType:"html",
		data:{pageIndex:pageIndex+1},
		success:function(data){
			//加载数据
			$("#lookLogListDiv").append(data);
            var ssVal = sessionStorage.getItem("sessionKey1");
			if (ssVal == null) ssVal = "";
			sessionStorage.setItem("sessionKey1", ssVal + data);
			$("[lazyLoadSrc]").YdxLazyLoad({});
			// 页码加1
			$("#pageIndex").val(pageIndex + 1)
			if (logCount <= (pageSize * (pageIndex+1))) {
				$("#addMoreLogDiv").hide();
				$("#product_list_more_json_no").show();
				$("#product_list_more_json_wait").hide();
				return;
			}
			else {
				$("#addMoreLogDiv").show();
				$("#product_list_more_json_no").hide();
				$("#product_list_more_json_wait").hide();
			}
		},
        complete : function(XMLHttpRequest,status){
			isLoading = false;
        }
	});
}

function pageIsHistory() {
	var list_page = $("#pageIndex").val();
	if (list_page == 0){
	    sessionStorage.removeItem("sessionKey1");
	    return false;
	}else {
		var ssVal1 = sessionStorage.getItem("sessionKey1");
		$("#lookLogListDiv").append(ssVal1);
		$("[lazyLoadSrc]").YdxLazyLoad({});
		return true;
	}
}
