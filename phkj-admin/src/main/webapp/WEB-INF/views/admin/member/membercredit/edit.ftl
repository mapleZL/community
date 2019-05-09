<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/memberCredit"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<style>
	.panel-fit{
		height: 100%;
		margin: 0;
		padding: 0;
		border: 0;
	    overflow: inherit;
	}
</style>

<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.EJS_URL_RESOURCES}";
	
	$(function(){
	   	$("#back").click(function(){
		   location.href="${currentBaseUrl}";
	   	});
	   
		$("#add").click(function() {
			var this_ = $(this);
			if($("#addForm").form('validate')){
				this_.attr("disabled",true);
				$.messager.progress({
					text : "提交中..."
				});
				$.ajax({
					type:"POST",
					url: "${currentBaseUrl}/doAdd",
					dataType: "json",
					data: $('#addForm').serialize(),
					cache:false,
					success:function(data){
						$.messager.progress('close')
						if (data.success) {
							$.messager.show({
    							title : '提示',
    							msg : "<#if obj??>修改成功<#else>新增成功</#if>",
    							showType : 'show'
    						});
							setTimeout(function(){
								location.href="${currentBaseUrl}";
							},2000);
						} else {
							this_.removeAttr("disabled");
							$.messager.alert("提示",data.message);
						}
					}
				});
			}
		});
	});
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			<#if obj??> 编辑赊账管理 <#else> 新增赊账管理</#if><span class="s-poar"> <a
				class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm"
			action="${currentBaseUrl}/doAdd"> <input type="hidden"
				value="${(obj.id)!}" name="id">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>账期额度： </label>
							<input type="text" id="quota" name="quota"
								class="txt w200 easyui-numberbox" missingMessage="请输入账期额度"
								data-options="min:0.01,max:99999999,precision:2,required:true"
								value="${(obj.quota)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>已使用额度： </label>
							<input type="text" id="used" name="used"
								class="txt w200 easyui-numberbox" missingMessage="请输入已使用额度"
								data-options="min:0,max:99999999,precision:2,required:true"
								value="${(obj.used)!'0'}" <#if !obj??>readonly</#if>/>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>剩余额度： </label>
							<input type="text" id="surplus" name="surplus"
								class="txt w200 easyui-numberbox" missingMessage="请输入剩余额度"
								data-options="min:0.01,max:99999999,precision:2,required:true"
								value="${(obj.surplus)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>到期日： </label>
							 <input type="text" id="expireDate" name="expireDate"
								 class="txt w200 easyui-validatebox" missingMessage="请输入到期日"
								 data-options="required:true"
                              	 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-M%-%d'})" 
                               	 value="${((obj.expireDate)?string('yyyy-MM-dd'))!''}"/>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>账期周期（天）： </label>
							<input type="text" id="cycle" name="cycle"
								class="txt w200 easyui-numberbox" missingMessage="请输入账期周期（单位：天）"
								data-options="min:1,max:365,precision:0,required:true"
								value="${(obj.cycle)!''}" />
						</p>
					</div>

				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<ul style="margin-left: 30px;">
							<li>设置赊账管理</li>
						</ul>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="提交" /> <input
					type="button" id="back" class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>

	<#include "/admin/commons/_detailfooter.ftl" />