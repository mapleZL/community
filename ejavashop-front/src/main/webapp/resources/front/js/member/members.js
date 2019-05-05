
$(function(){
	//服务协议默认勾选
	$("#acceptProtocol").attr("checked","checked");
	
	/**
	 * 增加手机号校验
	 */
	jQuery.validator.addMethod('isMobile', function(value, element) {
        return this.optional(element) || auth_phone(value);
    },"<font color='red'>请输入正确的手机号</font>");

	jQuery.validator.addMethod('passwordLength', function(value, element) {
	    return this.optional(element) || auth_password(value);
	},"<font color='red'>密码长度在6～20字符之间</font>");
	
	/**
	 * 增加邮箱校验
	 */
	jQuery.validator.addMethod('isEmail', function(value, element) {
        return this.optional(element) || auth_email(value);
    },"<font color='red'>请输入正确的邮箱地址</font>");

	/**
	 * 增加用户名重复校验
	 */
	jQuery.validator.addMethod('nameIsNotExist', function(value, element) {
        return this.optional(element) || nameIsNotExist(value);
    },"<font color='red'>此用户名已存在</font>");

	
	function auth_email(value){
		// 验证邮箱
		var flag = false;
		var re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(re.test(value)==false){
		}else{
		   flag = true;
		}
		return flag;
	}
	
	function auth_password(value){
		var flag = false;
		var password = value.replace(/' '/g, '');
		if (password.length < 6 || password.length > 20) {
		} else {
			flag = true;
		}
		return flag;
	}


	//验证手机号码
	function auth_phone(phone){
	    var re = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
	    return re.test(phone);
	} 
	
	//验证用户名是否已存在
	function nameIsNotExist(value){
		var nameIsNotExist = false;
		$.ajax({
			type:"GET",
			url:domain+"/nameIsExist.html",
			dataType:"json",
			async : false,
			data : {name:value},
			success:function(data){
				if(data.success){
					nameIsNotExist = true;
				} else {
					jAlert(data.message);
				}
			}
		});
		
		return nameIsNotExist;
	}

	
	jQuery("#form").validate({
        rules : {
            "name":{required:true,nameIsNotExist:true,isMobile:true},//验证邮箱
            "password":{required:true,passwordLength:true},
            "repassword":{required:true,equalTo:"#password"},
            "verifyCode":{required:true},
            "acceptProtocol":{required:true},
            "smsCode":{required:true}
        },
        messages:{
        	"name":{required:"<font color='red'>请输入手机号</font>"},
        	"password":{required:"<font color='red'>请输入密码</font>"},
        	"repassword":{required:"<font color='red'>请输入确认密码</font>",equalTo:"<font color='red'>两次密码不一致</font>"},
        	"verifyCode":{required:"<font color='red'>请输验证码</font>"},
        	"acceptProtocol":{required:"<font color='red'>请接受服务条款</font>"},
        	"smsCode":{required:"<font color='red'>请输入手机验证码</font>"}
        }
    }); 
	
	/**
	 * 提交注册信息
	 */
	$("#signupButton").click(function(){
		if ($("#form").valid()) {
			var params = $('#form').serialize();
			$.ajax({
				type : "POST",
				url : domain + "/doregister.html",
				dataType : "json",
				async : false,
				data : params,
				success : function(data) {
					if (data.success) {
						var BackUrl= "/store/step2.html";
						//console.dir(data.backUrl);
						if(data.backUrl.trim() !="" ){
							jAlert('验证通过！请完善企业相关资料', '提示', function() {
								window.location.href = domain + BackUrl;
							});
							//window.location = domain + BackUrl;
						}else{
							jAlert('注册成功！请先完善您的个人资料', '提示', function() {
								window.location.href = domain + "/member/info.html";//+"/member/index.html";window.location.href = domain + "/member/info.html";
							});
						/*jAlert('注册成功！注册有礼，大袜哥已将红包偷偷塞给您。快去查看吧', '提示', function() {
							window.location = domain + '/member/coupon-use.html';
						});*/
					   }
					} else {
						jAlert(data.message);
						refreshCode();// 刷新验证码
						$(".btn-danger").removeAttr("disabled");
					}
				},
				error : function() {
					jAlert("异常，请重试！");
					$(".btn-danger").removeAttr("disabled");
				}
			});
		}
		
	});
});	


	


