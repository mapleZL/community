function back_goods_number(id) {
    var back_number = document.getElementById('back_number' + id).value;
    var barCodePL = parseInt($('#barCodePL' + id).val());
    document.getElementById('goods_number' + id).value = Math.floor(back_number / barCodePL);
}
function check_goods_number(id) {
    var goods_number = parseInt($('#goods_number' + id).val());
    var back_number = parseInt($('#back_number' + id).val());
    var barCodePL = parseInt($('#barCodePL' + id).val());
    if (goods_number < 0 || goods_number * barCodePL > back_number) {
        back_goods_number(id);
    } else {
        $('#goods_number' + id).val(goods_number);
    }
}

function change_goods_number(type, id) {
    var goods_number = parseInt($('#goods_number' + id).val());
    var back_number = parseInt($('#back_number' + id).val());
    var barCodePL = parseInt($('#barCodePL' + id).val());
    //if(type != 2){back_goods_number(id);}
    if (type == 1) {
        goods_number--;
    }
    if (type == 3) {
        goods_number++;
    }
    if (goods_number < 0) {
        goods_number = 0;
    }
    if (goods_number * barCodePL > back_number) {
        goods_number = Math.floor(back_number / barCodePL);
        alert_("库存不足！");
    }
    $('#goods_number' + id).val(goods_number);
    $('#number' + id).val(goods_number * barCodePL);
    //if(goods_number <=0 ){goods_number=1;}
    // if(!/^[0-9]*$/.test(goods_number)){goods_number = document.getElementById('back_number'+id).value;}
    //document.getElementById('goods_number'+id).value = goods_number;
    //$.post('/b2c/index.php?m=default&c=flow&a=ajax_update_cart&u=0', {
    //		rec_id : id,goods_number : goods_number
    //}, function(data) {
    //	change_goods_number_response(data,id);
    //}, 'json');
}
// 处理返回信息并显示
function change_goods_number_response(result, id) {
    if (result.error == 0) {
        var rec_id = result.rec_id;
        $("#goods_number_" + rec_id).val(result.goods_number);
        document.getElementById('total_number').innerHTML = result.total_number; //更新数量
        document.getElementById('goods_subtotal').innerHTML = result.total_desc; //更新小计
        if (document.getElementById('ECS_CARTINFO')) {
            //更新购物车数量
            document.getElementById('ECS_CARTINFO').innerHTML = result.cart_info;
        }
    } else if (result.message != '') {
        alert(result.message);
        var goods_number = document.getElementById('back_number' + id).value;
        document.getElementById('goods_number' + id).value = goods_number;
    }
}

function add2cart(obj, id) {
    //未登录不能添加进货单
    if (!isUserLogin()) {
        window.location.href = domain + "/login.html";
        return;
    }
    var number = $('#number' + id).val();
    if (number <= 0) {
        alert_("请选择袜子的数量");
    }
    var form_ = $(obj).closest("form[name='cartForm']");
    $.ajax({
        type: "POST",
        url: domain + "/cart/addtocart.html",
        data: form_.serialize(),
        dataType: "json",
        success: function(data) {
            if (data.success) {
                window.location.href = domain + "/cart/detail.html";
            } else {
                alert_(data.message);
            }
        },
        error: function() {
            alert_("请稍后再试！");
        }
    });
}

$(function() {
    $('.search').click(function() {
        $('#searchForm').submit();
    });

    $("#product_list_more_json").click(function() {
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
    var urljson = domain + "/eosearchJson.html?sku=" + $("#sku").val() + "&pageNum=" + list_page;
    var listJsonHtml = "";
    $("#product_list_more_json_wait").show();
    $("#product_list_more_json").hide();
    $("#product_list_more_json_no").hide();
    $.ajax({
        type: "get",
        url: urljson,
        dataType: "json",
        cache: false,
        success: function(data) {
            if (data.success) {
                $.each(data.rows,
                function(i, pg) {
                    listJsonHtml += "<form action='' method='POST'  name='cartForm'>";
                    listJsonHtml += "<input  type='hidden' name='productId' value='" + pg.productId + "'>";
                    listJsonHtml += "<input  type='hidden' name='productGoodId' value='" + pg.id + "'>";
                    listJsonHtml += "<input  type='hidden' name='sellerId' value='" + pg.sellerId + "'>";
                    listJsonHtml += "<input type='hidden' id='barCodePL" + pg.id + "' value='" + pg.barCodePL + "'>";
                    listJsonHtml += "<input  type='hidden' name='number' value=";
                    if (pg.productStock < parseInt(pg.barCodePL)) {
                        listJsonHtml += "'0'";
                    } else {
                        listJsonHtml += "'" + pg.barCodePL + "'";
                    }
                    listJsonHtml += "id= 'number" + pg.id + "'>";
                    listJsonHtml += "<div data-am-widget='intro' class='am-intro am-cf am-intro-default overflow'>";
                    listJsonHtml += "<div class='am-g am-intro-bd ico mb10'>";
                    //listJsonHtml += "<div class='ect-radio1 fl ect' style='width:8%;'>";
                    //listJsonHtml += "<input name='msg_type' type='checkbox' id='msg-type2' value='1'>";
                    //listJsonHtml += "<label for='msg-type2'><i></i></label>";
                    //listJsonHtml += "</div>";
                    listJsonHtml += "<div class='am-g am-intro-bd fr' style='width:95%;margin-right:0.5rem !important; padding-left:0 !important'>";
                    listJsonHtml += "<div class='am-intro-left am-u-sm-4 cart_img' style='padding-left:0'>";
                    listJsonHtml += "<a href='" + domain + "/product/" + pg.productId + ".html' >";
                    listJsonHtml += "<img src='" + domainImg + pg.images + "'/>";
                    listJsonHtml += "</a>";
                    listJsonHtml += "</div>";
                    listJsonHtml += "<div class='am-intro-right am-u-sm-8' style='padding-right:1rem'>";
                    listJsonHtml += "<a href='" + domain + "/product/" + pg.productId + ".html' >";
                    listJsonHtml += "<h3 class='newtitle1'>" + pg.productName + "</h3>";
                    listJsonHtml += "<p class='f12 em0'>" + pg.normName + "&nbsp;&nbsp;&nbsp;" + pg.sku + "</p>";
                    listJsonHtml += "<p class='f12 em0'>(一手=" + pg.barCodePL + "双:&nbsp库存" + pg.productStock + "双)</p>";
                    listJsonHtml += "</a>";
                    listJsonHtml += "<em class='price1'>¥" + pg.mallMobilePrice + "</em>";
                    listJsonHtml += "<div class='spin overflow'>";
                    listJsonHtml += "<div class='input-group fl overflow'> ";
                    listJsonHtml += "<span class='input-group-addon fl' onclick='change_goods_number(&#39;1&#39;," + pg.id + ")'>-</span>";
                    listJsonHtml += "<input type='hidden' id='back_number" + pg.id + "' value='" + pg.productStock + "'>";
                    listJsonHtml += "<input type='text' class='form-num form-contro fl' name='shou' id='goods_number" + pg.id + "' autocomplete='off' value=";
                    if (pg.productStock < parseInt(pg.barCodePL)) {
                        listJsonHtml += "'0'";
                    } else {
                        listJsonHtml += "'1'";
                    }
                    listJsonHtml += "onfocus='back_goods_number(" + pg.id + ")' onblur='check_goods_number(" + pg.id + ")'>";
                    listJsonHtml += "<span class='input-group-addon fl' onclick='change_goods_number(&#39;3&#39;," + pg.id + ")'>+</span><em>手</em> ";
                    listJsonHtml += "</div>";
                    listJsonHtml += "<a class='add_btn fr' onclick='add2cart(this," + pg.id + ")'>加入进货单</a>";
                    listJsonHtml += "</div>";
                    listJsonHtml += "</div>";
                    listJsonHtml += "</div>";
                    listJsonHtml += "</div>";
                    listJsonHtml += "</div>";
                    listJsonHtml += "</form>";
                });
                $("#product_list_more").append(listJsonHtml);
                var pagesize = parseInt($("#pagesize").val());
                if ((data.total) == pagesize) {
                    $("#product_list_more_json").show();
                    $("#product_list_more_json_no").hide();
                    $("#product_list_more_json_wait").hide();
                } else {
                    $("#product_list_more_json").hide();
                    $("#product_list_more_json_no").show();
                    $("#product_list_more_json_wait").hide();
                }
            }
        },
        complete : function(XMLHttpRequest,status){
			isLoading = false;
        }
    });
}