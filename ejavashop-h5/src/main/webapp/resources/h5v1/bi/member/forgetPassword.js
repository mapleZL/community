function getmobVerify(this_){
	var obj = $(this_);
	var mob = $("#mobile").val();
	if (mob == null || mob == "") {
		$("#errLabel").html("请输入注册的手机号");
		return false;
	}
	if (!isMobile(mob)) {
		$("#errLabel").html("请输入正确的手机号");
		return false;
	}
	$("#errLabel").html();
	$.ajax({
		type : 'get',
		url : domain + '/mobVerify.html?mob=' + mob,
		success : function(e) {
			if (e.success) {
				var time = 120;
				obj.attr("disabled", true);
				obj.text(time + "秒");
				time--;
				intervalId = setInterval(function() {
					obj.text(time + "秒");
					if (time == 0) {
						clearInterval(intervalId);
						obj.removeAttr("disabled");
						obj.text("获取验证码");
					}
					time--;
				}, 1000);
			} else {
				alert(e.message);
			}
			
		}
	});
}

$(function(){
	$("#forgetPasswordBtn").click(function(){
		var mobile = $("#mobile").val();
		var mobVerfiy = $("#mobVerfiy").val();
		var verifyCode = $("#verifyCode").val();
		
		if (mobile == null || mobile == "") {
			$("#errLabel").html("请输入手机号");
			return false;
		}
		//if (!isMobile(mobile)) {
		//	$("#errLabel").html("请输入正确的手机号");
		//	return false;
		//}
		if (mobVerfiy == null || mobVerfiy == "") {
			$("#errLabel").html("请输入手机验证码");
			return false;
		}
		if (verifyCode == null || verifyCode == "") {
			$("#errLabel").html("请输入验证码");
			return false;
		}

		$("#errLabel").html();
		$("#forgetPasswordBtn").attr("disabled","disabled");
		var params = $('#forgetform').serialize();
		$.ajax({
			type:"POST",
			url:domain+"/doforgetpassword.html",
			dataType:"json",
			data : params,
			success:function(data){
				if(data.success){
				    alert_("密码重置成功，请查收您的短信！", this, function(t) {
						window.location = domain+"/login.html";
					});
				}
				else {
					$("#errLabel").html(data.message);
					refreshCode();//刷新验证码
					$("#forgetPasswordBtn").removeAttr("disabled");
					return false;
				}
			}
		});
	});


});

//刷新验证码
function refreshCode(){
	jQuery("#code_img").attr("src", domain + "/verify.html?d"+new Date().getTime());
}