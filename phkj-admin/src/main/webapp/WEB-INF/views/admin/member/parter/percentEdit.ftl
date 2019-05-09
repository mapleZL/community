<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function(){
		$('#saveRuleRes1').click(function() {
			$("#roleResForm1").form('submit', {
				url : "${currentBaseUrl}/updateParterPercent",
				success : function(e) {
					$(this).attr('disabled',false);
					$.messager.progress('close');
					$.messager.show({
						title : '提示',
						msg : e,
						showType : 'show'
					});
					closeWin();
				}
			});
		});
		
	});

	function closeWin(){
		$("#percentEdit,window.parent.document").window("close");
	}
</script>

<form id="roleResForm1" method="post"  class="form-contbox">
     <input type="hidden" name="parterPercentId"  value="${ParterPercent.id}"/>
	 <dl class="dl-group">
		<dt class="dt-group"><span class="s-icon"></span>佣金信息</dt>
		<dd class="dd-group">
		<div style="margin-top: 5px;"class="fluidbox">
			<p class="p12 p-item">
				<label class="lab-item">合伙人: </label><span id="memberNameSpan">${parterSign.memberName!}</span>
			</p>
		</div>
		<br/>
		<div class="fluidbox">
			<p class="p12 p-item">
				<label class="lab-item">佣金类型: 
				<#if ParterPercent.percentType == 'self' >
					<input type="radio"  name="percentType" value="self" id="self" checked="checked"/>合伙人佣金
				<#else>
					<input type="radio"  name="percentType" value="recommond" id="recommond" checked="checked"/>推荐合伙人佣金
				</#if>
			    </label>
			</p>
		</div>
		<br/>
		<div class="fluidbox">
			<p class="p12 p-item">
				<label class="lab-item">袜类型: 
				<#if ParterPercent.type == 'brand' >
					<input type="radio"  name="waType" value="brand" id="brand" checked="checked"/>品牌袜
				<#else>
					<input type="radio"  name="waType" value="luowa" id="luowa" checked="checked"/>裸&nbsp;袜
				</#if>
			    </label>
			</p>
		</div>
		<br/>
	    <div class="fluidbox">
			<label class="lab-item">起止时间：</label>
			<input type="text" id="startTime" name="startTime"
					class="txt w100 easyui-validatebox" missingMessage="开始时间必填"
					data-options="required:true"
					 readonly="readonly"
					 value="${(ParterPercent.startTime?string('yyyy-MM-dd'))!''}"
					 />
					<!--onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${(parterSign.startTime?string('yyyy-MM-dd'))!''}'});"  -->
			~
			<input type="text" id="endTime" name="endTime"
					class="txt w100 easyui-validatebox" missingMessage="结束时间必填"
					data-options="required:true"
					readonly="readonly"
					value = "${(ParterPercent.endTime?string('yyyy-MM-dd'))!''}";
					/>
					<!--onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'${(parterSign.endTime?string('yyyy-MM-dd'))!''}'});"  -->
	   </div>
	   <br/>
	   <div class="fluidbox">
			<label class="lab-item">佣金比例：</label>
			<input type="text" name = "percent" class="txt w100 easyui-validatebox"  data-options="required:true"  value="${ParterPercent.percent}"/>
	   </div>
	   <br/>
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