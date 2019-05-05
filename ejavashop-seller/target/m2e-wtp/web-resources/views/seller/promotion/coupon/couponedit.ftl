<#include "/seller/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/coupon";
	});
	$("#edit").click(function(){

		if($("#addForm").form('validate')){

			var checkAz = /^[A-Za-z]+$/;
			var prefix = $("#prefix").val();
			if (!checkAz.test(prefix)) {
				$.messager.alert('提示','红包前缀只能输入英文字母。');
				return;
			}
			
			var type = $("#type").val();
			if (type == null || type == "") {
				$.messager.alert('提示','请选择活动类型。');
				return;
			}
			
			var channel = $("#channel").val();
			if (channel == null || channel == "") {
				$.messager.alert('提示','请选择红包应用渠道。');
				return;
			}

			var couponValue = $("#couponValue").val();
			var minAmount = $("#minAmount").val();
			if (parseFloat(minAmount) <= parseFloat(couponValue)) {
				$.messager.alert('提示','适用最低订单金额必须大于红包金额。');
				return;
			}
			
			var channel = $("#channel").val();
			if (channel == null || channel == "") {
				$.messager.alert('提示','请选择活动应用渠道。');
				return;
			}
			
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/coupon/update")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">编辑红包<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/coupon">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(coupon.id)!''}">
					<input type="hidden" id="status" name="status" value="${(coupon.status)!''}">
					<input type="hidden" id="receivedNum" name="receivedNum" value="${(coupon.receivedNum)!''}">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>红包名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="couponName" name="couponName" value="${(coupon.couponName)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>红包前缀：</label>
							<input class="easyui-validatebox txt w280" type="text" id="prefix" name="prefix" value="${(coupon.prefix)!''}" data-options="required:true,validType:'length[4,4]'" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								用于生成红包序列号，固定4位长度，只能是A-Z的英文字母组成。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>红包面值：</label>
							<input class="easyui-numberbox txt w280" type="text" id="couponValue" name="couponValue" value="${(coupon.couponValue)!''}" data-options="required:true,precision:2" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								红包抵用现金金额。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>适用的最低订单金额：</label>
							<input class="easyui-numberbox txt w280" type="text" id="minAmount" name="minAmount" value="${(coupon.minAmount)!''}" data-options="required:true,precision:2" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								红包适用的订单最低金额，该金额是在订单扣除单品立减活动、订单满减活动后的订单金额。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>发放时间：</label>
							<input type="text" id="sendStartTime" name="sendStartTime"
								class="txt w200 easyui-validatebox" missingMessage="开始时间必填"
								data-options="required:true"
								onclick="WdatePicker({startDate:'%y-%M-{%d+1} 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'sendEndTime\')}'});"
								value="${(coupon.sendStartTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
							~
							<input type="text" id="sendEndTime" name="sendEndTime"
									class="txt w200 easyui-validatebox" missingMessage="结束时间必填"
									data-options="required:true"
									onclick="WdatePicker({startDate:'%y-%M-{%d+1} 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'sendStartTime\')}'});"
									value="${(coupon.sendEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								如果红包是线下发放类型，则该时间为店铺可以导出红包号码的时间。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>使用时间：</label>
							<input type="text" id="useStartTime" name="useStartTime"
								class="txt w200 easyui-validatebox" missingMessage="开始时间必填"
								data-options="required:true"
								onclick="WdatePicker({startDate:'%y-%M-{%d+1} 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'sendStartTime\')}',maxDate:'#F{$dp.$D(\'useEndTime\')}'});"
								value="${(coupon.useStartTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
							~
							<input type="text" id="useEndTime" name="useEndTime"
									class="txt w200 easyui-validatebox" missingMessage="结束时间必填"
									data-options="required:true"
									onclick="WdatePicker({startDate:'%y-%M-{%d+1} 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'useStartTime\')}'});"
									value="${(coupon.useEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								使用开始时间开始必须比发放开始时间晚，使用结束时间必须比发放结束时间晚。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>会员限制领取数量：</label>
							<input class="easyui-numberbox txt w280" type="text" id="personLimitNum" 
								name="personLimitNum" value="${(coupon.personLimitNum)!''}" 
								data-options="required:true,min:0,max:99999,precision:0" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								每个会员限制领取的数量，0为不限。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>总数量：</label>
							<input class="easyui-numberbox txt w280" type="text" 
							id="totalLimitNum" name="totalLimitNum" 
							value="${(coupon.totalLimitNum)!''}" 
							data-options="required:true,min:1,max:99999,precision:0" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								总共预计发放的总数量，至少1张。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>红包类型：</label>
							<@cont.select id="type" value="${(coupon.type)!''}" codeDiv="COUPON_TYPE" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用渠道：</label>
							<@cont.select id="channel" value="${(coupon.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动描述：</label>
							<textarea name="remark" rows="4" cols="60" id="remark" class="{maxlength:255}" >${(coupon.remark)!''}</textarea>
						</p>
					</div>
					<br/>
					
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="edit" class="btn" value="修改" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/seller/commons/_detailfooter.ftl" />