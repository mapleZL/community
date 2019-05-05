function arrow(obj,gender){
	var _value=$(obj).text();
	$("#sex_value").text(_value);
	$('#gender').val(gender);
}

$(function(){
	$("#personSubmit").click(function(){
		var realNameVal = $("#realName").val();
		var mobile=$('#mobile').val();
		var qq=$('#QQ').val();
		var qqt=/^[1-9][0-9]{4,14}$/;  
		var wecatt=/^[a-zA-Z\d_]{5,20}$/;
		var eml=/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
		var wechatNum=$('#wechatNum').val();
		var email=$("#email").val();
		if(realNameVal==""){
			alert_("真实姓名不能为空");
			//$.dialog('alert','提示',"真实姓名不能为空",2000,function(){ $("#personSubmit").removeAttr("disabled"); });
			return;
		}
		if(!isMobile(mobile)){
			alert_('请输入正确的手机号码');
			return;
		}
		if(!qqt.test(qq)){
			//alert(qq);
			alert_('请输入正确的QQ号码');
			return;
		}
//		if(!wecatt.test(wechatNum)){
//			alert_('请输入正确的微信号码');
//			return;
//		}
		if(!eml.test(email)){
			
			alert_('请输入正确的邮箱地址');
			return;
		}
		
	
		var params = $('#personalfileForm').serialize();
		$.ajax({
			type:"POST",
			url:domain+"/member/saveinfo.html",
			dataType:"json",
			async : false,
			data : params,
			success:function(data){
				if(data.success){
					alert_("保存成功", this, function() {
						location.href = domain + "/member/index.html";
					});
				}else{
					alert_(data.message);
				}
			}
		});
	});
}); 