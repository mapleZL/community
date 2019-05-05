$(function(){
	//初始化用户名和密码
	loginInit();
	
	$("#loginBtn").click(function(){
		var userName = $("#userName").val();
		if (userName == null || userName == "") {
			$(".tiperror").html("请输入用户名");
			return false;
		}
		var password = $("#password").val();
		if (password == null || password == "") {
			$(".tiperror").html("请输入密码");
			return false;
		}
		var verifyCode = $("#verifyCode").val();
		if (verifyCode == null || verifyCode == "") {
			$(".tiperror").html("请输入验证码");
			return false;
		}
		$(".tiperror").html("");
		$("#loginBtn").attr("disabled","disabled");
		var params = $('#loginForm').serialize();
		var toUrl = $('#toUrl').val();
		$.ajax({
			type:"POST",
			url:domain+"/dologin.html",
			dataType:"json",
			async : false,
			data : params,
			success:function(data){
				if(data.success){
					// 跳转到TODO
					if (toUrl != null && toUrl != "") {
						window.location=toUrl;
					} else {
						//借用json中tatal字段存储该登录用户的购买信息
						var orderCount = data.total;
						//若该用户第一次登录尚未购买，跳转进入1分钱商品信息
						//if(orderCount==0){
						//	window.location=domain+"/product/10.html?productId="+ 10;
						//}else{
						
						saveMemberInfo(userName,password);
						
						window.location=domain+"/index.html";
						return false;	
						//}
					}
				}else{
					$(".tiperror").html(data.message);
					//刷新验证码
					refreshCode();
					$("#loginBtn").removeAttr("disabled");
					return false;
				}
			},
			error:function(){
				$(".tiperror").html("异常，请重试！");
				$("#loginBtn").removeAttr("disabled");
				return false;
			}
		});
	});
	
});

function loginInit() {
	//判断本地数据库中是否已存在用户名密码
	websqlOpenDB();
	//获取唯一值
	websqlGetAllData("MEMBER");
}

function saveMemberInfo(name,password){
	//查询数据库是否存在该用户
	websqlGetAData("MEMBER",name,password);
}

//刷新验证码
function refreshCode(){
	jQuery("#code_img").attr("src", domain + "/verify.html?d"+new Date().getTime());
}

$(".input_box input").focus(function(){
	$(this).parent(".input_box").css("border","1px solid #ff7e18");
})
$(".input_box input").blur(function(){
	$(this).parent(".input_box").css("border","1px solid #eee");
})