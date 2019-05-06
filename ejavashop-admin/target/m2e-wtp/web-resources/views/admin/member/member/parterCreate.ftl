<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
$(function(){
	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member";
	});
	$("#add").click(function(){
		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member/parter/create")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});
});
</script>
<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">创建合伙人<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member">返回</a></span></h2>
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			     <input type="hidden" name = "memberId" value = "${member.id}"/>
				<dl class="dl-group">
					<dt class="dt-group"><span class="s-icon"></span>合伙人信息</dt>
					<dd class="dd-group">
						<div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item"><font class="red">*</font>合伙人：</label>
								<input class="easyui-validatebox txt w180" type="text" id="memberName" name="name" value="${(member.name)!''}" readonly="readonly"/>
							</p>
					   </div>
					   <br/>
					   <div class="fluidbox">
							<p class="p12 p-item">
								<label class="lab-item"><font class="red">*</font>合伙编号：</label>
								<input class="easyui-validatebox txt w180" type="text" id="signNo" name="signNo"  data-options="required:true"/>
							</p>
					   </div>
					   <br/>
					   <div class="fluidbox">
						<label class="lab-item"><font class="red">*</font>合同有效期：</label>
						<input type="text" id="startTime" name="startTime"
								class="txt w180 easyui-validatebox" missingMessage="开始时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'});"
								value="${(parterSign.startTime?string('yyyy-MM-dd'))!''}" readonly="readonly">
						~
						<input type="text" id="endTime" name="endTime"
								class="txt w180 easyui-validatebox" missingMessage="结束时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'});"
								value="${(parterSign.endTime?string('yyyy-MM-dd'))!''}" readonly="readonly">
					</div>
					<br/>
					
					</dd>
				</dl>
				<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="新增" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
		<#if message ? exists><input type="hidden" value = "${message}"  id= "message"/></#if>
	</div>
</div>
<script>
    if($('#message').val().length > 0){
    	alert($('#message').val());
    }
</script>
<#include "/admin/commons/_detailfooter.ftl" />
