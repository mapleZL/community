$(function(){
	
	if ($("#list_page").val() == 0)
		moreList();
	
	$("#product_list_more_json").click(function(){
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
	
	var list_page = $("#list_page").val();
	list_page++;
	$("#list_page").val(list_page);
	
	var urljson = domain + "/member/balanceJson.html?pageNum=" + list_page;
	
	var listJsonHtml = "";
    $("#product_list_more_json").hide();
    $("#product_list_more_json_wait").show();
	$("#product_list_more_json_no").hide();
	$.ajax({
        type:"get",
        url: urljson,
        dataType: "json",
        cache:false,
        success:function(data){
            if (data.success) {
                $.each(data.data, function(i, info){
                	listJsonHtml += "<div class='mt10 ico state_box'>"+
        							"<div class='public overflow'>"+
        							"<span class='fl'>" + info.createTime + "</span>"+
        							"<span class='em4 fr'>余额：¥ " + info.money + "</span>"+
        					        "</div><div class='public state'>";
			        if(info.state == 1 || info.state == 2 || info.state == 5) {
                		listJsonHtml += "<p>余额增加</p>";
                	} else {
                		listJsonHtml += "<p>余额减少</p>";
                	}
                	listJsonHtml += "<p>明细："+info.remark+"</p></div></div>";
                })
                $("#balance_more").append(listJsonHtml);

				if (data.total == 0) {
                    $("#product_list_more_json").hide();
                    $("#product_list_more_json_wait").hide();
                	$("#product_list_more_json_no").show();
				}
				else if (data.total == 1000) {
					$("#product_list_more").append("<div style='background:#e9faff;border-top:1px solid #e5e5e5;border-bottom:1px solid #e5e5e5;width: 410px;height:86px;margin: 5px 0 0 0;'><div style='float:left;margin:20px 24px'><img lazyLoadSrc='" + domainImg + "/img/icon_collapse.png'></div><div style='font-family:MicrosoftYaHei;font-size:18px;color:#888888;text-align:left;padding-top: 21px;'>目前没有余额支付历史记录。</div><div style='font-family:MicrosoftYaHei;font-size: 12px;color:#888888;text-align:left;padding-top: 10px;'>或许您可以在商品列表中筛选到满意的商品。</div></div>");
					$("#no_product").show();
                    $("#product_list_more_json").hide();
                    $("#product_list_more_json_wait").hide();
				}
				else {
                    $("#product_list_more_json").show();
                    $("#product_list_more_json_wait").hide();
                	$("#product_list_more_json_no").hide();
                }
            }
        },
        complete : function(XMLHttpRequest,status){
			isLoading = false;
        }
    });
}

