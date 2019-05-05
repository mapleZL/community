<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function(){
		$('#saveRuleRes1').click(function() {
			$("#roleResForm1").form('submit', {
				url : "${currentBaseUrl}/saveMakeRecord",
				success : function(data) {
						$(this).attr('disabled',false);
						$.messager.progress('close');
						$.messager.show({
							title : '提示',
							msg : data.message,
							showType : 'show'
						});
						closeWin();
				}
			});
		});
		
	});
	function closeWin(){
		$("#makeRecord,window.parent.document").window("close");
	}
</script>

<form id="roleResForm1" method="post"  class="form-contbox">
	<input type="hidden"  name="memberId" value="${memberId}"/>     
	 <dl class="dl-group">
		<dt class="dt-group"><span class="s-icon"></span>合伙人列表</dt>
		<#--
		<div style="margin-top: 5px;"class="fluidbox">
			<p class="p12 p-item">
				<label class="lab-item">用户: </label><span id="memberNameSpan">${parterSign.memberRealName!}</span>
			</p>
		</div>
		<dl class="dl-group">
		-->
		<dd class="dd-group">
		<#if parterSignList ??>
		<#list parterSignList as parterSign>
		<div class="fluidbox">
			<p class="p12 p-item">
				<label class="lab-item">推荐人：
			    <input type="radio" name="parterMemberId" value="${parterSign.memberId}"  id="memberId"/>
			    <#if parterSign.memberRealName ? exists>
			    	${parterSign.memberRealName!''}
			    <#else>
			    	${parterSign.memberName!''}
			    </#if>
			    </label>
			</p>
		</div>
	   <#--</dl>-->
		</#list>
		</#if>
		</dd>
		</dl>
		<div>
			<p class="p-item p-btn">
				<a id="saveRuleRes1" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-delete"
					onclick="closeWin();">关闭</a>
			</p>
		</div>
</form>