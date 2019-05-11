<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/repair/member";
	});
	$("#add").click(function(){
		
		if($("#addForm").form('validate')){

			var prefix = $("#userName").val();
			if (!userName) {
				$.messager.alert('提示','维修人员姓名必填');
				return;
			}
			
			var schedulingDay = $("#schedulingDay").val();
			if (!schedulingDay) {
				$.messager.alert('提示','排班规则必填');
				return;
			}
			
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/repair/member/create")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">添加维修人员<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/repair/member">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>维修人员基本信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>维修人员姓名：</label>
							<input class="easyui-validatebox txt w280" type="text" id="userName" name="userName" value="${(repaireMember.userName)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>排班规则：</label>
							<select id="schedulingDay" name="schedulingDay" level="0" class="txt w290">
								<option value="0">请选择</option>
								<option value="1">周一</option>
								<option value="2">周二</option>
								<option value="3">周三</option>
								<option value="4">周四</option>
								<option value="5">周五</option>
								<option value="6">周六</option>
								<option value="7">周日</option>
							</select>
						</p>
					</div>
					<br/>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="新增" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />