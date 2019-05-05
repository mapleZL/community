$("a[data-id]").click(function(){
	$(this).addClass('cur');
});
function zhanKai(obj){
    var zhan = 1;
	if ($(obj).parent().siblings('.all_list').find('.all_hide').css("display") == "none") {
		$(obj).parent().siblings('.all_list').find('.all_hide').css("display","block");
        $(obj).addClass("up").removeClass("drop");
		flag=2;
	} 
	else {
	    $(obj).parent().siblings('.all_list').find('.all_hide').css('display','none');
		$(obj).addClass("drop").removeClass("up");
        zhan = 1;
     }
}

$(function(){
	if(!pageIsHistory()) {
		getMoreList();
	}
	$(".moren").click(function() {
		var bysort = $(this).data("bysort");
//		alert(bysort);
		//filterSort(bysort);
		var moren_url = sessionStorage.getItem("moren_url");
		self.location= domain + "/" + moren_url + ".html";
	});
	
	var count = sessionStorage.getItem("count");
	if(count == null || count == 'undefined' ||count == ''){
		count = 1;
		sessionStorage.setItem("moren_url" ,$("#moren_url").val());
	}else{
		count += 1;
	}
	sessionStorage.setItem("count", count);
	
	
    $("#changeshow").click(function() {
        if ($("#show_product").hasClass("product_list2")) {
            $("#show_product").removeClass("product_list2").addClass("product_list");
            $(this).children(':first').attr("src", domainStatic + "/img/icon_big.png");
        }
        else {
            $("#show_product").removeClass("product_list").addClass("product_list2");
            $(this).children(':first').attr("src", domainStatic + "/img/icon_small.png");
        }
    });
	
	var urlsath = location.href;
	var currentBrandId = urlsath.split("-")[6];
	$(".product a").each(function() {
		var val = $(this).data("id");
		if (currentBrandId == val) {
			$(this).addClass("cur");
			if ($(this).parent().hasClass("product")) {
				$(this).siblings("div").show();
				$(this).parent().prev().children("a").removeClass("drop").addClass("up");
			}
			else {
				$(this).parent().show();
				$(this).parent().parent().prev().children("a").removeClass("drop").addClass("up");
			}
			return false;
		}
	});
	
	var apaths = urlsath.substring(0, urlsath.length - 5).split("-");
	$(".product3 a").each(function() {
		var val = $(this).data("replaceid");
		for (var i = 9; i < apaths.length; i ++) {
			if ("-" + apaths[i] == val) {
				$(this).addClass("cur");
				if ($(this).parent().hasClass("product3")) {
					$(this).siblings("div").show();
					$(this).parent().prev().children("a").removeClass("drop").addClass("up");
				}
				else {
					$(this).parent().show();
					$(this).parent().parent().prev().children("a").removeClass("drop").addClass("up");
				}
				break;
			}
		}
	});
	
	$(".resetprice").click(function(){
		var bysort = $(this).data("bysort");
//		alert(bysort);
		filterSort(bysort);
	});
	
	$(".changepic").click(function(){
	    var sta = $("#pricepaixu").val();
    	$(".pri1").hide();
	    if(sta == 1){
	    	$("#tupian1").show();
	    	$("#pricepaixu").val(2);
	    }else{
	    	$("#tupian2").show();
	    	$("#pricepaixu").val(1);
	    } 
	});
	
	$(".more1").click(function(){
		getMoreList();
	});
	
	$(".product a").click(function(){
		var productBrandId = $(this).data("id");
		if(productBrandId != null && productBrandId != ""){
			filterBrand(productBrandId);
		}
	});
	
	$(".product2 a").click(function(){
		var productBrandId = $(this).data("id");
		if(productBrandId != null && productBrandId != ""){
			filterWhereAllNew(productBrandId);
		}
	});
	
	$(".product3 a").click(function(){
		var productBrandId = $(this).data("id");
		var productReplace = $(this).data("replaceid");
		//alert(productBrandId+"      "+productReplace);
		if (productReplace != "" && productReplace != undefined) {
			filterWhereAllChange(productBrandId,productReplace);
		}
		else {
			filterWhereAllNew(productBrandId);
		}
	});
	
	$(window).scroll(function() {
		if ($(document).scrollTop() >= ($(document).height() - $(window).height())) {
			getMoreList();
		}
	});
});

function pageIsHistory() {
	var list_page = $("#list_page").val();
	if (list_page == 0){
	    sessionStorage.removeItem("sessionKey1");
	    return false;
	}else {
	    var changeStatus = sessionStorage.getItem("changeStatus");
		var ssVal1 = sessionStorage.getItem("sessionKey1");
		$("#show_product").append(ssVal1);
		$("[lazyLoadSrc]").YdxLazyLoad({});
		return true;
	}
}


function filterBrand(brand) {
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 6) {
	    	url += brand;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location = domain + "/" + url + ".html";
}

function filterWhereAllNew(filter) {
	self.location = domain + "/" + urlPath + filter + ".html";
}

function filterWhereAllChange(delfilter, changefilter) {
	var url = urlPath.replace(delfilter, "");
	url = url + changefilter;
	self.location = domain + "/" + url + ".html";
}

function filterSort(sort) {
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 3) {
	    	url += sort;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location = domain + "/" + url + ".html";
}

var sessionKey = "producListSession";

function getMoreList() {
	if (!$("#product_list_more_json_no").is(":hidden")) 
		return;
	if (isLoading) return;
	isLoading = true;
	$("#loading").show();		
	var list_page = $("#list_page").val();
	list_page++;
	$("#list_page").val(list_page);
	var urlPaths = urlPath.split("-");
	var url = "listjson-";
	for(var i=1; i<urlPaths.length; i++) {
	    if(i == 2) {
	    	url += list_page;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	var urljson = domain + "/" + url + ".html";
	
	var listJsonHtml = "";
    $("#product_list_more_json_wait").show();
	$("#product_list_more_json").hide();
	$("#product_list_more_json_no").hide();            
	$.ajax({
        type:"get",
        url: urljson,
        dataType: "json",
        cache:false,
        success:function(data){
            if (data.success) {
                $.each(data.data, function(i, product){
                	listJsonHtml += '<li><div><a href="' + domain + '/product/' + product.id + '.html"><label>';
                	if (product.productStock <= 0) listJsonHtml += '<h5>补货中</h5>';
					listJsonHtml += '<img lazyLoadSrc="' + domainImg + product.masterImg  + '"/></label><h3>' + product.name1 + '</h3>';
					listJsonHtml += '<h4 class="f12 em3">'+ product.name2 +'</h4>';
					listJsonHtml += '<span><em class="f16">¥ ' + product.malMobilePrice + '</em>';
            		if(product.marketPrice !=null && product.marketPrice > product.malMobilePrice){
            			listJsonHtml += '<del class="em3 f12">¥ ' + product.marketPrice + '</del>';
            		}
					listJsonHtml += '</span><p class="add_btn fr">查看详情</p></a></div></li>';
                });
                
                $("#show_product").append(listJsonHtml);
                var ssVal = sessionStorage.getItem("sessionKey1");
				if (ssVal == null) ssVal = "";
				sessionStorage.setItem("sessionKey1", ssVal + listJsonHtml);
				if (data.total == 0) {
					$("#product_list_more_json").show();
					$("#product_list_more_json_no").hide();
					$("#product_list_more_json_wait").hide();
					$("#no_product").hide();
				}
				else if (data.total == 2) {
					$("#product_list_more").append("<div style='background:#e9faff;border-top:1px solid #e5e5e5;border-bottom:1px solid #e5e5e5;width: 410px;height:86px;margin: 5px 0 0 0;'><div style='float:left;margin:20px 24px'><img lazyLoadSrc='" + domainImg + "/img/icon_collapse.png'></div><div style='font-family:MicrosoftYaHei;font-size:18px;color:#888888;text-align:left;padding-top: 21px;'>没有找到相关商品。</div><div style='font-family:MicrosoftYaHei;font-size: 12px;color:#888888;text-align:left;padding-top: 10px;'>或许您可以在商品列表中筛选到满意的商品。</div></div>");
					$("#no_product").show();
					$("#product_list_more_json_wait").hide();
					$("#product_list_more_json").hide();
				}else {
					$("#product_list_more_json").hide();
					$("#product_list_more_json_no").show();
					$("#product_list_more_json_wait").hide();
					$("#no_product").hide();
				}
				$("[lazyLoadSrc]").YdxLazyLoad({});
            }
        },
        complete : function(XMLHttpRequest,status){
			isLoading = false;
        }
    });
    $("#loading").hide();
}