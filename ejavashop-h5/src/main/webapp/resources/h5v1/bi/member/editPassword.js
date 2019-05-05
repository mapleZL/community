/*
$("#text1").click(function(){
	alert_("请设置旧密码");
});
$("#text2").click(function(){
	alert_("请填写新密码");
});
$("#text3").click(function(){
	alert_("新密码不一致");
});
*/
$(function(){
	$("#buttonSubmit").click(function(){
		var txtoldpw = $("#text1").val();
		var txtnewpw = $("#text2").val();
		var txtrepw = $("#text3").val();

		if (txtoldpw == null || txtoldpw == "") {
			$("#errLabel").html("<i class='fa fa-warning'></i> 请输入原密码");
			return false;
		}
		if (txtoldpw.length < 6 || txtoldpw.length > 20) {
			$("#errLabel").html("<i class='fa fa-warning'></i> 请输入6-20位原密码");
			return false;
		}
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
		if (txtnewpw == txtoldpw) {
			$("#errLabel").html("<i class='fa fa-warning'></i> 旧密码和新密码一致 ");
			return false;
		}
		$("#errLabel").html("");
		//$("#buttonSubmit").attr("disabled","disabled");
		var params = $('#editPasswordform').serialize();
		$.ajax({
			type:"POST",
			url:domain+"/member/updatepassword.html",
			dataType:"json",
			async : false,
			data : params,
			success:function(data){
				if(data.success){
					//重新加载数据
					alert_("密码修改成功，请妥善保管您的账户和密码", this, function() {
						location.href = domain + "/member/index.html";
					});
				}else{
					alert_(data.message);
					//$.dialog('alert','提示',data.message,2000,function(){ $("#buttonSubmit").removeAttr("disabled"); });
					return;
				}
			},
			error:function(){
				alert_("服务异常");
				//$("#buttonSubmit").removeAttr("disabled");
			}
		});
	});
});

function goMemberIndex(){
	window.location.href=domain+"/member/index.html";
}