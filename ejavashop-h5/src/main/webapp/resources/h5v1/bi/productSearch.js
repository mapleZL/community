$(function(){
	pageIsHistory();
	$("#product_list_more_json_no").hide();
	$(".more1").click(function(){
		getMoreList();
	});
	
	$(".searchproduct a").click(function(){
		var keyword = $("#keyword").val();
		if(keyword == null || keyword == ""){
			alert_("请输入关键字");
			return;
		}
		window.location.href = domain + "/search.html?keyword="+keyword+"&bysort=0";
	});
	
	$(".changepic a").click(function() {
		var bysort = $(this).data("bysort");
		var keyword = $("#keyword").val();
		window.location.href = domain + "/search.html?keyword="+keyword+"&bysort="+bysort;
	});
	
	$("#keyword").keydown(function(e) {
		if (e.keyCode == 13) {
			var keyword = $("#keyword").val();
			if (keyword == "") {
				alert_("请输入您要查询的关键字！");
				return;
			}
			window.location.href = domain + "/search.html?keyword="+keyword+"&bysort=0";
		}
	});

	$(window).scroll(function() {
		if ($(document).scrollTop() >= ($(document).height() - $(window).height())) {
			getMoreList();
		}
	});
});

function getMoreList(){
	if (!$("#product_list_more_json_no").is(":hidden")) 
		return;
	if (isLoading) return;
	var list_page = $("#list_page").val();
	list_page++;
	$("#list_page").val(list_page);
	var urljson = domain + "/searchJson.html?keyword=" + $("#keyword").val() + "&pageNum=" + list_page+"&sort=" +$("#sort").val();
	$("#product_list_more_json").hide();
	$("#product_list_more_json_wait").show();
	var listJsonHtml = "";
	$.ajax({
        type:"get",
        url: urljson,
        dataType: "json",
        cache:false,
        success:function(data){
            if (data.success) {
                $.each(data.rows, function(i, product){
                	var listBool = $("#imgOrlistViewid").hasClass("fa fa-list-ul");
                    	listJsonHtml += "<div data-am-widget='intro' class='am-intro am-cf am-intro-default ico tp2'><div class='am-g am-intro-bd'>";
                    	listJsonHtml += "<div class='am-intro-left am-u-sm-4'>";
                    	listJsonHtml +=	"<a href='" + domain + "/product/"+ product.id +".html'>";
                    	if (product.productStock <= 0) listJsonHtml += '<h5>补货中</h5>';
                    	listJsonHtml += "<img src='" + domainImg + product.masterImg + "'/></a></div>";
                    	listJsonHtml +=	"<div class='am-intro-right am-u-sm-8'>";
                    	listJsonHtml += "<a href='" + domain + "/product/"+ product.id +".html'>";
                    	listJsonHtml += "<h3 class='title1'>"+ product.name1 +"</h3>";
                    	listJsonHtml += "<p class='f12 em3'>"+ product.name2 +"</p></a>";
                    	listJsonHtml += "<div class='overflow mt5'>";
                    	listJsonHtml += "<em class='price2 mt5'>¥"+ product.malMobilePrice +"</em>";
                    	listJsonHtml += "<a href='" + domain + "/product/" + product.id +".html' class='add_btn fr'>查看详情</a>";
                    	listJsonHtml += "</div></div></div></div></div>";
                });
                $("#product_list_more").append(listJsonHtml);
                var ssVal = sessionStorage.getItem("sessionKey2");
				if (ssVal == null) ssVal = "";
                if(list_page == 2){
                	sessionStorage.removeItem("sessionKey2");
                	ssVal += $("#orignaldata").html();
                }
				sessionStorage.setItem("sessionKey2", ssVal + listJsonHtml);
                var pagesize = parseInt($("#pagesize").val());
                if ((data.total) == pagesize) {
                    $("#product_list_more_json").show();
                    $("#product_list_more_json_no").hide();
                    $("#product_list_more_json_wait").hide();
                    $("#no_product").hide();
                } else {
                    $("#product_list_more_json").hide();
                    $("#product_list_more_json_no").show();
                    $("#product_list_more_json_wait").hide();
                    $("#no_product").show();
                }
            }
        },
        complete : function(XMLHttpRequest,status){
			isLoading = false;
        }
    });
}

function pageIsHistory() {
	var list_page = $("#list_page").val();
	if (list_page == 1){
	    sessionStorage.removeItem("sessionKey2");
	}else {
		var ssVal1 = sessionStorage.getItem("sessionKey2");
		$("#product_list_more").append(ssVal1);
	}
	
}