$(".input_box input").focus(function(){
	$(this).parent(".input_box").css("border","1px solid #ff7e18");
});
$(".input_box input").blur(function(){
	$(this).parent(".input_box").css("border","1px solid #eee");
});

var msgcode;
var intervalId;

$(function(){
	$('#memberType1').click(function (){
		$('#memberType2').removeClass("cur");
	    $('#memberType1').addClass("cur");
		$('#memberType').val(1);
	});
	
	$('#memberType2').click(function (){
		$('#memberType1').removeClass("cur");
		$('#memberType2').addClass("cur");
		$('#memberType').val(2);
	});
	
	$('#goOrdersCenter').click(function (){
		window.location.href=domain+"/index.html";
	});
	
	$("#getSMSVerify").click(function(){
		var obj = $(this);
		var mob = $("#userName").val();
		var verifycode = $("#verifyCode").val();
		
		if(!verifycode){
			$("#errLabel").html("请输入验证码");
			return;
		}
		if(!mob){
			$("#errLabel").html("请输入手机号后获取");
			return;
		}
		if (!isMobile(mob)) {
			$("#errLabel").html("请输入正确的手机号码");
			return;
		}
		$.ajax({
			type:'get',
			url: domain + '/sendVerifySMS.html?mob=' + mob + '&verifycode=' + verifycode,
			success:function(e){
				if(e.success){
					msgcode = e.data;
				} else{
					alert_(e.message);		
					clearInterval(intervalId);
					obj.removeAttr("disabled");
//					$('#showMessage').text("获取短信验证码");
					obj.text("获取验证码");
				}
			}
		});
		
		var time = 120;
		obj.attr("disabled",true);
//		$('#showMessage').text(time+"秒后重新获取");
		obj.text(time+"秒");
		time -- ;
		
		intervalId = setInterval(function(){
//			$('#showMessage').text(time+"秒后重新获取");
			obj.text(time+"秒");
			time -- ;
			if (time == 0) {
				clearInterval(intervalId);
				obj.removeAttr("disabled");
//				$('#showMessage').text(time+"获取短信验证码");
				obj.text("获取验证码");
			}
		}, 1000);  
	});
	
	$("#registerBtn").click(function() {
		var userName = $("#userName").val();
		if (userName == null || userName == "") {
			$("#errLabel").html("请输入手机号");
			return;
		}
		var verifyCode = $("#verifyCode").val();
		if (verifyCode == null || verifyCode == "") {
			$("#errLabel").html("请输入验证码");
			return;
		}
		var smsCode = $("#smsCode").val();
		if (smsCode == null || smsCode == "") {
			$("#errLabel").html("请输入手机验证码");
			return;
		}

		if(msgcode != smsCode){
			$("#errLabel").html("手机验证码错误");
			return;
		}
		var password = $("#password").val();
		if (password == null || password == "") {
			$("#errLabel").html("请输入密码");
			return;
		}
		if (password.length < 6 || password.length > 20) {
			$("#errLabel").html("请输入6-20位密码");
			return false;
		}
		var repassword = $("#repassword").val();
		if (repassword != password) {
			$("#errLabel").html("两次输入密码不一致");
			return false;
		}
		$(this).attr("disabled","disabled");
		var params = $('#registerForm').serialize();
		$.ajax({
			type:"POST",
			url:domain + "/doregister.html",
			dataType:"json",
			async : false,
			data : params,
			success:function(data){
				if (data.success) {
				   if (data.backUrl.trim() !="" ) {
						window.location = domain + "/storeregister/apply.html";
				   }
				   else {
					    alert_("注册成功！<br/>您可以进入个人资料，完善您的信息", function(t) {
					    	window.location = domain + "/member/index.html";
						});
					}
				}
				else {
					alert_(data.message);
					//刷新验证码
					refreshCode();
					$("#registerBtn").removeAttr("disabled");
				}
			},
			error:function(){
				alert_("服务异常，请稍后重试！");
				$("#registerBtn").removeAttr("disabled");
			}
		});
	});
	
});

//刷新验证码
function refreshCode(){
	jQuery("#code_img").attr("src", domain + "/verify.html?d"+new Date().getTime());
}
	