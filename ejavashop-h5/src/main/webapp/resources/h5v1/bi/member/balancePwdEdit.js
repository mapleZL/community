$(function(){
	$(".mt30").click(function(){
		var txtoldpw = $("#txt-oldpw").val();
		var txtnewpw = $("#txt-newpw").val();
		var txtrepw = $("#txt-repw").val();

		if (txtoldpw == null || txtoldpw == "") {
			$("#errLabel").html("<i class='fa fa-warning'></i> 请输入原密码");
			return false;
		}
		if (txtoldpw.length < 6 || txtoldpw.length > 20) {
			$("#errLabel").html("<i class='fa fa-warning'></i> 请输入6-20位原密码");
			return false;
		}
		if (txtnewpw == null || txtnewpw == "") {
			$("#errLabel").html("<i class='fa fa-warning'></i> 请填写新密码");
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
			url:domain+"/member/updatebalancepassword.html",
			dataType:"json",
			data : params,
			success:function(data){
				if(data.success){
					alert_("支付密码修改成功", this, function() {
						location.href = domain + "/member/balance.html";
					});
				}else{
					alert_(data.message);
				}
			}
		});
	});
});