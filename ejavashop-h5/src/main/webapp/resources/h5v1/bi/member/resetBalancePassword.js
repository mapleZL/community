$(function (){
	$(".mt30").click(function(){
		var txtnewpw = $("#txt-newpw").val();
		var txtrepw = $("#txt-repw").val();
		if (txtnewpw == null || txtnewpw == "") {
			$("#errLabel").html("<i class='fa fa-warning'></i> 请输入新密码");
			return false;
		}
		if (txtnewpw.length < 6 || txtnewpw.length > 20) {
			$("#errLabel").html("<i class='fa fa-warning'></i> 请输入6-20位新密码");
			return false;
		}
		if (txtrepw == null || txtrepw == "") {
			$("#errLabel").html("<i class='fa fa-warning'></i> 请输入确认密码");
			return false;
		}
		if (txtrepw.length < 6 || txtrepw.length > 20) {
			$("#errLabel").html("<i class='fa fa-warning'></i> 请输入6-20位确认密码");
			return false;
		}
		if (txtnewpw != txtrepw) {
			$("#errLabel").html("<i class='fa fa-warning'></i> 确认密码不正确");
			return false;
		}
		$("#errLabel").html("");
		var params = $('#editPasswordform').serialize();
		$.ajax({
			type:"POST",
			url:domain+"/member/resetbalancepassword.html",
			dataType:"json",
			data : params,
			success:function(data){
				if(data.success){
					alert_("支付密码重置成功", this, function() {
						location.href = domain + "/member/balance.html";
					});
				}else{
					alert_(data.message);
				}
			}
		});
	});
});

function getmobVerify(this_){
	var obj = $('.yz');
	var mob = $("#mob").val();
		$.ajax({
			type : 'get',
			url : domain + '/mobVerify.html?mob=' + mob,
			success : function(e) {
				if (e.success) {
					var time = 120;
					//obj.attr("disabled", true);
					obj.text(time + "秒后重新获取");
					time--;
					intervalId = setInterval(function() {
						obj.text(time + "秒后重新获取");
						if (time == 0) {
							clearInterval(intervalId);
							//obj.removeAttr("disabled");
							obj.text("重新获取");
						}
						time--;
					}, 1000);
				} else {
					alert_(e.message);
				}
			}
		});
}