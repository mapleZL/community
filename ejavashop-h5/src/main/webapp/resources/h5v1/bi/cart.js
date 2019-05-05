function goorderinfo() {
    var t_id = $("#productId_T").val();
    var p_id = $("#productId_P").val();
    if (t_id == 10 && p_id == 0) {
        alert_("很抱歉，进货单中存在一分钱体验支付商品，\n该商品不能参与正常订单的购买流程，请单独购买1分钱体验商品。");
        return;
    }
    
	// 提交订单按钮屏蔽，避免重复提交
	$(".cart_submit").attr("disabled","disabled");
	// 提交loading
 	$('body').append("<div class='purchase-loading'><div class='loading-2'></div></div>");
    var submitable = true;
    var tFlag;
    $(".oder-list").each(function() {
    	if (!submitable) return false;
        var prolist = $(this).find("dl.cart-ul");
        prolist.each(function(idx_, this_) {
            //先做购买数校验
            var inputnumber = $(this).find(".quantity");
            var inputstock_ = Number(inputnumber.val());
            var maxStock_ = Number($(this).find("input[type='hidden'][name='maxStock']").val());
            var pname_ = $(this).find(".product_name h3").html().trim();
            var norm_ = $(this).find(".product_name p").html().trim();
            var productId = Number($(this).find("input[type='hidden'][name='productId']").val());
			var productGoodsId = Number($(this).find("input[type='hidden'][name='productGoodsId']").val());
			//进行限购校验  add by lushuai 2017-02-16
			tFlag = checklimitations(productId,inputstock_,productGoodsId);
			if(!tFlag){
				submitable = false;
				$(".purchase-loading").remove();
			}
//            var cartId_ = $(this).find("input[type='hidden'][name='cartId']").val();
            if (inputstock_ > maxStock_) {
                submitable = false;
                var msg_ = "很抱歉，<br/>商品【" + pname_ + "(" + norm_ + ")】目前允许您最多只能购买" + maxStock_ + "双，系统将会修改您下单的数量，是否继续提交？";
                $(".purchase-loading").remove();
                confirm_(msg_, this, function(t) {
                    var cartId_ = t.find("input[type='hidden'][name='cartId']").val();
                	maxStock_ = Number(t.find("input[type='hidden'][name='maxStock']").val());
                    inputnumber.val(maxStock_);
                    if (maxStock_ == 0) {
                    	deleteCart(cartId_, t.children(".cart_delete"));
                    }
                    //更新进货单某某件商品的数量
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        async: false,
                        url: domain + "/cart/updateCartById.html",
                        data: {
                            id: cartId_,
                            count: maxStock_
                        },
                        success: function(data) {
                        	$(".cart_submit").removeAttr("disabled");
                        	getNewCartInfo();
                        },
                        error: function() {
                            alert_('数据加载失败');
                        }
                    });

                });
                return false;
            }
        });
    });
    if (submitable) {
        $(".purchase-loading").remove();
        location.href = domain + "/order/info.html";
    }
}

$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: domain + "/cart/tomydetail.html",
        dataType: "html",
        success: function(data) {
            //加载数据
            $("#loading").hide();
            $("#cartListDiv").append(data);
        	$("[lazyLoadSrc]").YdxLazyLoad({});
        }
    });

	$(document).on("click", ".cart_delete", function() {
	    //var cartId = $(this).data("cartid");
	    confirm_("您确定要删除该商品吗？", this, function(t) {
	        deleteCart($("#removeCartId").val(), t);
	    });
	});
	
	$(document).on("click", ".cart_submit", function() {
	    goorderinfo();
	});
});

function checklimitations(productId,num,productGoodsId){
	var tFlag = true;
	$.ajax({
		type : "GET",
		url :  domain+"/cart/checklimitations.html",
		data : {
			productId:productId, 
			productGoodsId:productGoodsId,
			number:num
		},
		async:false,
		dataType : "json",
		success : function(data) {
			var msg = data.message;
			if(msg != '' && msg != null){
				alert_(msg);
				tFlag = false;
			}
		},
		error : function() {
			alert_("校验限购商品数据失败！");
			tFlag = false;
		}
	});
	return tFlag; 
}

function cartminus(obj) {
    var numberObj = $(obj).parent().children("#number");
    var stockNums = parseInt($(obj).parent().children("#stockNums").val());
    var number = parseInt(numberObj.val(), 10);
    var cartId = $(obj).parent().children("#cartId").val();
    $("#removeCartId").val(cartId);
    if (number <= stockNums) {
        //deleteCart(cartId);
        confirm_("您确定要删除该商品吗？", this, function(t) {
	        deleteCart($("#removeCartId").val(), $(obj));
	    });
    } else {
        //			number -= 10;
        number -= stockNums;
    }
    numberObj.val(number);
    updateSingle(cartId, number);
    getNewCartInfo();
}

function cartplus(obj) {
    var numberObj = $(obj).parent().children("#number");
    var stockNums = parseInt($(obj).parent().children("#stockNums").val());
    var number = parseInt(numberObj.val(), 10) + stockNums;
    var productStock = parseInt($(obj).parent().children("#productStock").val());
    if (number <= productStock) {
        numberObj.val(number);
        var cartId = $(obj).parent().children("#cartId").val();
        updateSingle(cartId, number);
        getNewCartInfo();
    } else {
        //			number += 10; 库存不足
        //number += stockNums;
        alert_("库存不足");
        return;
    }
}

// 数量输入框失去焦点
function modify(obj) {
    var number = parseInt($(obj).val(), 10);
    var stockNums = parseInt($(obj).parent().children("#stockNums").val());
    var productStock = $(obj).parent().children("#productStock").val();
    if (number == null || parseInt(number) < stockNums) {
        //			number = 10;
        number = stockNums;
    } else {
        if (number > parseInt(productStock)) {
            number = parseInt(productStock);
        }
    }

    //只允许10的倍数
    var val_ = Number($(obj).val());
    if (val_ % stockNums != 0) {
        var mod = val_ % stockNums;
        var floorVal = Number(val_ - mod);
        var remainder_ = Number(stockNums - mod);
        //四舍五入
        if (mod >= (stockNums / 2)) {
            number = val_ + remainder_;
        } else {
            number = floorVal;
        }
    }
    $(obj).val(number);

    var cartId = $(obj).parent().children("#cartId").val();
    updateSingle(cartId, number);
    getNewCartInfo();
}

//更新进货单某某件商品的数量
function updateSingle(id, count) {
    $.ajax({
        type: "POST",
        url: domain + "/cart/updateCartById.html",
        data: {
            id: id,
            count: count
        },
        dataType: "json",
        async: false,
        success: function(data) {},
        error: function() {
            alert_('数据加载失败');
        }
    });
}

// 异步加载进货单信息
function getNewCartInfo() {
    $.ajax({
        type: "POST",
        url: domain + "/cart/getcartinfo.html?rd=" + Math.random(),
        async: false,
        success: function(data) {
            $("#cartListDiv").empty();
            $("#cartListDiv").append(data);

            $(".cartAmountFont").html("￥" + $("#cartAmount").val());
            $(".totalNumberFont").html("(" + $("#totalCheckedNumber").val() + ")");
            var checkedNum = parseInt($("#totalCheckedNumber").val());
            var totalNum = parseInt($("#totalNumber").val());
            if (checkedNum == totalNum) {
                $("#checkAllFoot").checked = true;
            }
        	$("[lazyLoadSrc]").YdxLazyLoad({});
        }
    });
}

function deleteThisDeatil(cartId) {
    $("#removeCartId").val(cartId);
}

function deleteCart(cartId, obj) {
    $.ajax({
        type: "GET",
        url: domain + "/cart/deleteCartById.html",
        data: {
            id: cartId
        },
        dataType: "json",
        success: function(data) {
            if (data.success) {
                remove_(obj.parent().parent().parent().parent().parent());
                //延时触发，增加删除效果
                setTimeout(getNewCartInfo(),1000);
            } else {
                alert_(data.message);
            }

            //$("img").lazyload({
            //    effect:'fadeIn'
            //});
        }
    });
}

//选中
function checkedChange(id) {
    var checked = 0;
    if ($('#' + id).is(':checked')) {
        checked = 1;
    }
    //var id = $(obj).val();
    $.ajax({
        type: "GET",
        url: domain + "/cart/cartchecked.html",
        data: {
            id: id,
            checked: checked
        },
        dataType: "json",
        success: function(data) {
            if (data.success) {
                //重新加载单品信息
                getNewCartInfo();
            } else {
                alert_(data.message);
            }
        },
        error: function() {
            alert_('数据加载失败');
        }
    });
}

//全部选中
function checkedChangeAll(obj) {
    var checked = 0;
    if ($(obj).prop('checked')) {
        checked = 1;
    }
    $.ajax({
        type: "GET",
        url: domain + "/cart/cartcheckedall.html",
        data: {
            checked: checked
        },
        dataType: "json",
        success: function(data) {
            if (data.success) {
                //重新加载单品信息
                getNewCartInfo();
            } else {
                alert_(data.message);
            }
        },
        error: function() {
            alert_('数据加载失败');
        }
    });
}