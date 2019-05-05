$(function() {
	
	var message = $("#message").val();
	if (message != "") {
		 alert_(message);
	}
	
	$(".stop1").click(function() {
		window.location.href=domain+"/member/newaddress.html?isFromOrder=" + $("#isFromOrder").val() + "&orderType=" + $("#orderType").val() + "&actInfo=" + $("#actInfo").val();
	});
	
	var _index = $("input[name='msg_type']").index($("input[name='msg_type']:checked"));
	$("input[name='msg_type']").click(function() {
		$("input[name='msg_type']:eq(" + _index + ")").attr("checked", "true");
		confirm_("是否确定设定该地址为默认？", this, function(t) {
			window.location.href=domain+"/member/setdefaultaddress.html?id="+t.val();
		});
	});
	
	$(".address-edit").click(function() {
		var addressId = $(this).data("address-id");
		window.location.href=domain+"/member/editaddress.html?id="+addressId;
	});
	
	$('.deleteAdd').click(function () {
		var id = $(this).data("add-id");
		$("#deleteId").val(id);
		confirm_("是否确定删除该地址？", this, function(t) {
			deleteAddress(t, $("#deleteId").val());
		});
	});
});

function deleteAddress(obj, id) {
	$.ajax({
		type : "POST",
		url :  domain+"/member/deleteaddress.html",
		data : {id:id},
		dataType : "json",
		success : function(data) {
			if(data.success){
				remove_($(obj).parent().parent().parent());
			}else {
				 alert_(data.message);
			}
		}
	});
}

function choseAddress(id) {
	var orderType = $("#orderType").val();
	var actInfo = $("#actInfo").val();
	if (orderType == "2") 
		window.location.href = domain + "/order/flashsale-" + actInfo + ".html?addressId=" + id;
	else if (orderType == "3")
		window.location.href = domain + "/order/tuan-" + actInfo + ".html?addressId=" + id;
	else
		window.location.href = domain + "/order/info.html?addressId=" + id;
}