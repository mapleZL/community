$(function() {
	var area1 = new LArea();
	area1.init({
	'trigger': '#demo1', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
	'valueTo': '#value1', //选择完毕后id属性输出到该位置
	'keys': {
	    id: 'id',
	    name: 'name'
	}, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
	'type': 1, //数据源类型
	'data': LAreaData //数据源
	});

	area1.value=[12,129,667];//控制初始位置，注意：该方法并不会影响到input的value

	var message = $("#message").val();
	if (message != "") {
		 alert_(message);
	}
	
    $(".stop1").click(function() {
    	$("#errLabel").html("");
    	// 校验
		var memberName = $("#memberName").val();
		if (memberName == null || memberName == "") {
			$("#errLabel").html("请输入收货人姓名");
			return false;
		}
		if (memberName.length > 20) {
			$("#errLabel").html("收货人姓名不能超过20字符");
			return false;
		}
		
		var mobile = $("#mobile").val();
		if (mobile == null || mobile == "") {
			$("#errLabel").html("请输入收货人手机号码");
			return false;
		}
		if(!isMobile(mobile)){
//			alert(mobile);
			$("#errLabel").html('请输入正确的收货人手机号码');
			return false;
		}
		var addall = 	$('#value1').val();
		$("#addAll").val($('#demo1').val());
		var regions = addall.split(",");
		if(regions.length > 0){
			$('#provinceId').val(regions[0]);
			$('#cityId').val(regions[1]);
			$('#areaId').val(regions[2]);
		} 
		if($('#provinceId').val() == '' || $('#cityId').val == '' || $('#areaId').val == ''){
			$("#errLabel").html("请选择省市区");
			return false;
		}
		
		var addressInfo = $("#addressInfo").val();
		if (addressInfo == null || addressInfo == "") {
			$("#errLabel").html("请输入详细地址");
			return false;
		}
		if (addressInfo.length > 50) {
			$("#errLabel").html("详细地址不能超过50字符");
			return false;
		}
		var zipCode=$("#zipCode").val();
		//邮编正则
		var Zcode=/^\d{6}$/;
		if(zipCode == null || zipCode == ''){
			$("#zipCode").val('000000');
		}else{
			if(!Zcode.test(zipCode)){
				$("#errLabel").html("请输入正确的邮编");
				return false;
			}
		}
		if($("input[type='checkbox']").is(':checked')){
			$('#state').val(1);
		}
		$("#errLabel").html("");
		$.ajax({
			type:"POST",
			url:domain+"/member/saveaddress.html",
			dataType:"json",
			data : $('#addresform').serialize(),
			success:function(data) {
				if (data.success) {
					var isFromOrder = $("#isFromOrder").val();
					var orderType = $("#orderType").val();
					var actInfo = $("#actInfo").val();
					if (isFromOrder == "1") {
						if (orderType == "2") {
							window.location.href=domain+"/order/flashsale-" + actInfo + ".html?addressId="+data.data;
						} else if (orderType == "3") {
							window.location.href=domain+"/order/tuan-" + actInfo + ".html?addressId="+data.data;
						} else if (orderType == "4") {
							window.location.href=domain+"/order/bidding-" + actInfo + ".html?addressId="+data.data;
						} else {
							window.location.href=domain+"/order/info.html?addressId="+data.data;
						}
					} else {
						window.location.href=domain+"/member/address.html";  
					}
				}else{
					 alert_(data.message);
				}
			}
		});
    });
});