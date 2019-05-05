<#include "/h5v1/commons/_head.ftl" />
<html>
<head>
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
</style>
</head>
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">设置密码</h1>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
<form id="addPasswordform">
	<ul class="bank1 ico">
           <li><label>设置密码</label><input type="password" class="text7" id='txt-newpw' name='newPwd' placeholder="请输入6~20位的字母和数字组合"></li>
           <li><label>确认密码</label><input type="password" class="text7" id='txt-repw' name='password' placeholder="请再次输入新密码"></li>
    </ul>
    <a class="stop1 mt30">保存</a>
</form>
</article>
<#include "/h5v1/commons/_footer.ftl" />
<!--内容end--> 
<script>
	$(function(){
		$(".mt30").click(function(){
			var txtnewpw = $("#txt-newpw").val();
			var txtrepw = $("#txt-repw").val();

			if (txtnewpw == null || txtnewpw == "") {
				alert_("请填写新密码");
				//webToast("请填写新密码","middle",1000);
				return false;
			}
			if (txtnewpw.length < 6 || txtnewpw.length > 20) {
				alert_("请输入6-20位新密码");
				//webToast("请输入6-20位新密码","middle",1000);
				return false;
			}
			if (txtrepw == null || txtrepw == "") {
				alert_("请输入确认密码");
				//webToast("请输入确认密码","middle",1000);
				return false;
			}
			if (txtrepw.length < 6 || txtrepw.length > 20) {
				alert_("请输入6-20位确认密码");
				//webToast("请输入6-20位确认密码","middle",1000);
				return false;
			}
			if (txtnewpw != txtrepw) {
				alert_("确认密码不对");
				//webToast("确认密码不对","middle",1000);
				return false;
			}
			var params = $('#addPasswordform').serialize();
			$.ajax({
				type:"POST",
				url:domain+"/member/savebalancepassword.html",
				dataType:"json",
				data : params,
				success:function(data){
					if(data.success){
						alert_("密码修改成功");
						//重新加载数据
					    // webToast("密码修改成功","middle",1000);
						//$.dialog('alert','提示','密码修改成功',2000,function(){ window.location.href=domain+"/member/balance.html"; });
					}else{
						alert_(data.message);
						//webToast(data.message,"middle",1000);
						//$("#errLabel").html(data.message);
						//$("#buttonSubmit").removeAttr("disabled");
					}
				}
			});
		});
	});
</script>
<!--弹框-->
<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm" style="top:30%">
  <div class="am-modal-dialog" style="border-radius:5px;">
  	<h3 class="em2" style="padding:10px; text-align:left">密码修改成功</h3>
    <div class="model_box em1 tp4" style="padding:1rem 0">
     请妥善保管你的账户和密码
    </div>
    <div class="am-modal-footer" style="padding:10px 0">
      <span class="am-modal-btn em2" data-am-modal-cancel>确定</span>
    </div>
  </div>
</div>
<!--弹框end-->    
</body>
</html>
