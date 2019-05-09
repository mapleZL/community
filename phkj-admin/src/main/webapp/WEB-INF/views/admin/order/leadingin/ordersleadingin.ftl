<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/leadingin"/>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			订单导入 
			<span class="s-poar"> 
				<a class="a-back" href="history.back()">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" action="${currentBaseUrl}/" name="addForm"> 
			<p class="p-item p-btn">
				文件上传：<input type = "file" name = "leadxls" id = "leadxls"><br/>
			</p>
			<p class="p-item p-btn">
				 <input type="button" id="leadin" class="btn" value="导入" />
				 <input type="button" id="back" class="btn" value="返回" />
			</p>
			<div id="sx_three">
				<p>
					<font size="5"><b>导入失败订单号:</b></font><div id="errorOrderSn" style="word-wrap: break-word; "></div>
				</p>
			</div>
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">您可以在此修导入特定模板的订单以便保存至本平台</label>
					</div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							导入前请确保EXCEL格式是否正确 </label>
					</div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							此功能只针对其它商城的订单进行导入 </label>
					</div>
				</dd>
			</dl>
			</@form.form>
		</div>
	</div>
</div>

<script language="javascript">

	$(function() {
	
		$("#sx_three").hide();
		
		$('#leadin').click(function(){
			var el = $("#leadxls").val();
			if(el == null || el == ""){
				$.messager.alert("提示","尚未选择文件,请检查！");
				return;
			}
			if(el.indexOf(".xls")<0){
				$.messager.alert("提示","文件格式不正确，仅支持xls类型！");
				return;
			}
			$('#addForm').ajaxSubmit({
			     url: '${currentBaseUrl}/leadinginxls?excelurl='+el,
			     type: 'post',
			     success: function (data) {
			     	 var mesc = data.message;
				     if(mesc == "订单导入成功！"){
				     	$.messager.alert("提示",data.message);
				     }else{
				     	$("#sx_three").show();
				     	$("#errorOrderSn").html(mesc);
				     	$.messager.alert("提示","存在导入失败订单，具体参考页面导入失败订单号，检查对应sku是否匹配");
				     }
			     }
			     });
		});
	});

</script>
<div id="labeldialog"></div>
<#include "/admin/commons/_detailfooter.ftl" />
