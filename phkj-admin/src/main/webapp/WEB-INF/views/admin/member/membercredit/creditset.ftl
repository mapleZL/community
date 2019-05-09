<div class="wrapper">
	<#if added??>
	<div style="font-size: 16px;margin: 80px;">该会员已设置赊账信息，请至
		<a style="color:blue;" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/memberCredit?memberId=${memberId!}">赊账管理</a> 页面查看
	</div>
	<#else>
	<div class="formbox-a">
		<div class="form-contbox">
			<form id="creditAddform">
				<input type="hidden" value="${memberId!}" name="memberId">
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>账期额度： </label> <input
							type="text" id="quota" name="quota"
							class="txt w200 easyui-numberbox" missingMessage="请输入账期额度"
							data-options="min:0.01,max:99999999,precision:2,required:true" />
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">&nbsp;</label> <font
							style="color: #808080">用户在账期内最大可赊账额度，保留两位小数</font>
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item"><font class="red">*</font>账期周期：
						</label> <input type="text" id="cycle" name="cycle"
							class="txt w200 easyui-numberbox" missingMessage="请输入账期周期（单位：天）"
							data-options="min:1,max:365,precision:0,required:true" />
					</p>
				</div>
				<div class="fluidbox">
					<p class="p12 p-item">
						<label class="lab-item">&nbsp;</label> <font
							style="color: #808080">请输入账期周期（单位：天），系统将自动计算到账日期</font>
					</p>
				</div>
				<p class="p-item p-btn">
					<input type="button" class="btn" value="提交" onclick="submitCreditset(this);"/> 
					<input type="button" class="btn" value="取消"
						onclick="$('#creditsetDialog').dialog('close')" />
				</p>
			</form>
		</div>
	</div>
	</#if>
</div>

<script>
	function submitCreditset(obj){
		var this_ = $(obj);
		var isValid = $("#creditAddform").form('validate');
		if (isValid) {
			this_.attr("disabled",true);
			this_.val("提交中...");
			$.ajax({
				type:"POST",
				url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/memberCredit/doAdd",
				dataType: "json",
				data: $('#creditAddform').serialize(),
				cache:false,
				success:function(data){
					$.messager.progress('close')
					if (data.success) {
						$.messager.show({
							title : '提示',
							msg : "设置成功",
							showType : 'show'
						});
						$('#creditsetDialog').dialog('close');
					} else {
						this_.removeAttr("disabled");
						this_.val("提交");
						$.messager.alert("提示",data.message);
					}
				}
			});
		}
	}
</script>