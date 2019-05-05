<#include "/seller/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/full";
	});
	$("#add").click(function(){
		
		if($("#addForm").form('validate')){

			var firstFull = $("#firstFull").val();
			var secondFull = $("#secondFull").val();
			var thirdFull = $("#thirdFull").val();
			var firstDiscount = $("#firstDiscount").val();
			var secondDiscount = $("#secondDiscount").val();
			var thirdDiscount = $("#thirdDiscount").val();

			// 满额减免额比较
			if (parseFloat(firstFull) <= parseFloat(firstDiscount)) {
				$.messager.alert('提示','满额必须大于减免额。');
				return;
			}
			if (secondFull != null && secondFull != "" && parseFloat(secondFull) > 0) {
				if (parseFloat(secondFull) <= parseFloat(firstFull)) {
					$.messager.alert('提示','第二档满额必须大于第一档满额。');
					return;
				}
				if (secondDiscount == null || secondDiscount == "" || parseFloat(secondDiscount) <= 0) {
					$.messager.alert('提示','设定满额必须同时设定减免额。');
					return;
				}
				if (parseFloat(secondFull) <= parseFloat(secondDiscount)) {
					$.messager.alert('提示','满额必须大于减免额。');
					return;
				}
				if (parseFloat(secondDiscount) <= parseFloat(firstDiscount)) {
					$.messager.alert('提示','第二档减免额必须大于第一档减免额。');
					return;
				}
			}
			if (thirdFull != null && thirdFull != "" && parseFloat(thirdFull) > 0) {
				if (secondFull == null || secondFull == "" || parseFloat(secondFull) <= 0) {
					$.messager.alert('提示','如果设定第三档则必须设定第二档。');
					return;
				}
				if (parseFloat(thirdFull) <= parseFloat(secondFull)) {
					$.messager.alert('提示','第三档满额必须大于第二档满额。');
					return;
				}
				if (thirdDiscount == null || thirdDiscount == "" || parseFloat(thirdDiscount) <= 0) {
					$.messager.alert('提示','设定满额必须同时设定减免额。');
					return;
				}
				if (parseFloat(thirdFull) <= parseFloat(thirdDiscount)) {
					$.messager.alert('提示','满额必须大于减免额。');
					return;
				}
				if (parseFloat(thirdDiscount) <= parseFloat(secondDiscount)) {
					$.messager.alert('提示','第三档减免额必须大于第二档减免额。');
					return;
				}
			}
			
			var channel = $("#channel").val();
			if (channel == null || channel == "") {
				$.messager.alert('提示','请选择活动应用渠道。');
				return;
			}
			
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/full/create")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">添加订单满减<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/full">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>满减活动名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="actFullName" name="actFullName" value="${(actFull.actFullName)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>第一档：</label>
							满
							<input class="easyui-numberbox txt w100" type="text" id="firstFull" name="firstFull" value="${(actFull.firstFull)!''}" data-options="required:true,precision:2,min:1" >
							减
							<input class="easyui-numberbox txt w100" type="text" id="firstDiscount" name="firstDiscount" value="${(actFull.firstDiscount)!''}" data-options="required:true,precision:2" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								满额与订单减去单品立减金额后计算出的金额相比较，如满1000减50，如果订单总额减去单品立减优惠金额后大于100，则满足规则。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">第二档：</label>
							满
							<input class="easyui-numberbox txt w100" type="text" id="secondFull" name="secondFull" value="${(actFull.secondFull)!''}" data-options="precision:2" >
							减
							<input class="easyui-numberbox txt w100" type="text" id="secondDiscount" name="secondDiscount" value="${(actFull.secondDiscount)!''}" data-options="precision:2" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								第二、三档可为空，为空或设定为0，表示不设定该档次，不能跨档次设定（如设定一三档次不设定二档次）。
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								第二档满额必须比第一档满额金额大；同理第三档满额必须比第二档满额金额大。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">第三档：</label>
							满
							<input class="easyui-numberbox txt w100" type="text" id="thirdFull" name="thirdFull" value="${(actFull.thirdFull)!''}" data-options="precision:2" >
							减
							<input class="easyui-numberbox txt w100" type="text" id="thirdDiscount" name="thirdDiscount" value="${(actFull.thirdDiscount)!''}" data-options="precision:2" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动时间：</label>
							
							<input type="text" id="startTime" name="startTime"
								class="txt w200 easyui-validatebox" missingMessage="开始时间必填"
								data-options="required:true"
								onclick="WdatePicker({startDate:'%y-%M-{%d+1} 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"
								value="${(actFull.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						~
						<input type="text" id="endTime" name="endTime"
								class="txt w200 easyui-validatebox" missingMessage="结束时间必填"
								data-options="required:true"
								onclick="WdatePicker({startDate:'%y-%M-{%d+1} 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});"
								value="${(actFull.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用渠道：</label>
							<@cont.select id="channel" value="${(actFull.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动描述：</label>
							<textarea name="remark" rows="4" cols="60" id="remark" class="{maxlength:255}" >${(actFull.remark)!''}</textarea>
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



<#include "/seller/commons/_detailfooter.ftl" />