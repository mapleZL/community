//库存价格设置js
function setStock(allstocks) {
	var stock_ = 0;
	$.each(allstocks, function(i, e) {
		stock_ += Number(e);
	});
	$("#productStock").numberbox('setValue', stock_);
}

function setPprice(allpprice) {
	$("#mallPcPrice").numberbox('setValue', Math.min.apply(null, allpprice));
}

function setMprice(allmprice) {
	$("#malMobilePrice").numberbox('setValue', Math.min.apply(null, allmprice));
}

function submitdata() {
	var goodsinfo = new Array();
	$.each($("#normDiv tr[name='goodstr']"), function(idx, e) {
		var this_ = $(this);
		var data_ = new Array();
		$.each(this_.find("td :input"), function(i2, e2) {
			var val_ = $(e2).val();
			data_.push(val_ ? val_ : 0);
		});
		goodsinfo.push(data_);
	});
	var gif = goodsinfo.join("!@|@!");
	$("#goodsinfo").val(gif);
//	if ($("#addForm").form('validate')) {
		$("#addForm").submit();
//	}
}

function statisticsStock() {
	var allstocks = new Array();
	$("input[id^='inventory_details_stock_']").each(function(idx, e) {
		allstocks.push(illegalNumber($(e)));
	});
	setStock(allstocks);
}

function statisticsPprice() {
	var allspprice = new Array();
	$("input[id^='inventory_details_pprice_']").each(function(idx, e) {
		allspprice.push(illegalNumber($(e), protectedPrice));
	});
	setPprice(allspprice);
}

function statisticsMprice() {
	var allmprice = new Array();
	$("input[id^='inventory_details_mprice_']").each(function(idx, e) {
		allmprice.push(illegalNumber($(e), protectedPrice));
	});
	setMprice(allmprice);
}

function illegalNumber(o, p) {
	var val = $(o).val();
	var zer_ = p ? p : 0;
	if (isNaN(val) || Number(val) < zer_) {
		var pri_ = p ? p : 1;
		$(o).val(pri_);
		if (p) {
			$.messager.show({
				title : '提示',
				msg : '价格不能低于保护价',
				showType : 'show'
			});
			return p;
		} else
			return 1;
	} else
		return val;
}

$(function() {
	
	$('#name2').tooltip({
		position : 'right',
		content : '<span>您可以修改促销信息</span>',
		onShow : function() {
			$(this).tooltip('tip').css({
				backgroundColor : 'rgb(255,255,204)',
				borderColor : 'rgb(204,153,51)'
			});
		}
	});

	$("#back").click(function() {
		window.location.href = domain + '/seller/product/' + from;
	});
	// 启用规格，初始化
	if (isNorm == 2) {
		statisticsStock();
		statisticsPprice();
		statisticsMprice();
	} else {
		$("#mallPcPrice").live('blur', function() {
			illegalNumber($(this), protectedPrice);
		});
		$("#malMobilePrice").live('blur', function() {
			illegalNumber($(this), protectedPrice);
		});
		$("#productStock").live('blur', function() {
			illegalNumber($(this));
		});
	}

	// 库存改变
	$("input[id^='inventory_details_stock_']").live('blur', function() {
		statisticsStock();
	});

	$("input[id^='inventory_details_pprice_']").live('blur', function() {
		statisticsPprice();
	});

	$("input[id^='inventory_details_mprice_']").live('blur', function() {
		statisticsMprice();
	});

});
