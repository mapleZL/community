
$(function(){
	
	jQuery("#form").validate({
		errorPlacement : function(error, element) {
			var obj = element.siblings(".tip").css('display', 'block')
					.find('p').addClass('error');
			error.appendTo(obj);
		},
        rules : {
            "name":{required:true},
            "password":{required:true},
            "verifyCode":{required:true}
        },
        messages:{
        	"name":{required:"请输入账号"},
        	"password":{required:"请输入密码"},
        	"verifyCode":{required:"请输验证码"}
        }
    }); 
	
	/**
	 * 提交登录信息
	 */
	$("#loginButton").click(function(){

		if ($("#form").valid()) {
			$(".btn-danger").attr("disabled","disabled");
			var params = $('#form').serialize();
			$.ajax({
				type:"POST",
				url:domain+"/dologin.html",
				dataType:"json",
				async : false,
				data : params,
				success:function(data){
					if(data.success){
						window.location=domain+"/member/order.html";
					}else{
						jAlert(data.message);
						//刷新验证码
						refreshCode();
						$(".btn-danger").removeAttr("disabled");
					}
				},
				error:function(){
					jAlert("异常，请重试！");
					$(".btn-danger").removeAttr("disabled");
				}
			});
		}
		
	});
	
});	
