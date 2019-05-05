<#include "/seller/commons/_detailheader.ftl" />

<script language="javascript">

$(function(){

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/coupon";
	});

	$("#export").click(function(){
		var exportNum = $("#exportNum").val();
		if (exportNum == null || exportNum == "" || exportNum == 0) {
			$.messager.alert('提示', "请填写导出数量。");
			return;
		}
		$.messager.confirm('提示', '确定导出' + exportNum + '张红包吗？', function(r){
            if (r){
            	$.fileDownload('${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/coupon/doexport',{data:"id=${(coupon.id)!''}&exportNum=" + exportNum});
            }
        });
    });
	
	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">导出红包<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/coupon">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>红包名称：</label>
							<input disabled class="easyui-validatebox txt w280" type="text" id="couponName" name="couponName" value="${(coupon.couponName)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>红包前缀：</label>
							<input disabled class="easyui-validatebox txt w280" type="text" id="prefix" name="prefix" value="${(coupon.prefix)!''}" data-options="required:true,validType:'length[4,4]'" >
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
							<input disabled class="easyui-numberbox txt w280" type="text" id="couponValue" name="couponValue" value="${(coupon.couponValue)!''}" data-options="required:true,precision:2" >
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
							<input disabled class="easyui-numberbox txt w280" type="text" id="minAmount" name="minAmount" value="${(coupon.minAmount)!''}" data-options="required:true,precision:2" >
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
							<input disabled class="easyui-validatebox txt w200" type="text" id="sendStartTime" name="sendStartTime" value="${(coupon.sendStartTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
							~
							<input disabled class="easyui-validatebox txt w200" type="text" id="sendEndTime" name="sendEndTime" value="${(coupon.sendEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>使用时间：</label>
							<input disabled class="easyui-validatebox txt w200" type="text" id="useStartTime" name="useStartTime" value="${(coupon.useStartTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
							~
							<input disabled class="easyui-validatebox txt w200" type="text" id="useEndTime" name="useEndTime" value="${(coupon.useEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}" data-options="required:true,validType:'length[0,100]'" >
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
							<input disabled class="easyui-numberbox txt w280" type="text" id="personLimitNum" name="personLimitNum" value="${(coupon.personLimitNum)!''}" data-options="required:true" >
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
							<input disabled class="easyui-numberbox txt w280" type="text" id="totalLimitNum" name="totalLimitNum" value="${(coupon.totalLimitNum)!''}" data-options="required:true,min:1" >
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
							<label class="lab-item"><font class="red">*</font>已发放数量：</label>
							<input disabled class="easyui-numberbox txt w280" type="text" id="receivedNum" name="receivedNum" value="${(coupon.receivedNum)!''}" data-options="required:true,min:1" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>红包类型：</label>
							<@cont.select disabled="disabled" id="type" value="${(coupon.type)!''}" codeDiv="COUPON_TYPE" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用渠道：</label>
							<@cont.select disabled="disabled" id="channel" value="${(coupon.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">红包描述：</label>
							<textarea disabled name="remark" rows="4" cols="60" id="remark" class="{maxlength:255}" >${(coupon.remark)!''}</textarea>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>导出数量：</label>
							<input class="easyui-numberbox txt w280" type="text" id="exportNum" name="exportNum" value="" data-options="required:true,min:1,max:1000" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								导出数量，一次至少导出1张，最多导出1000张，总导出数量不能超过红包设定的总数量。
								</font>
							</label>
						</p>
					</div>
					<br/>
					
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="export" class="btn" value="导出" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/seller/commons/_detailfooter.ftl" />