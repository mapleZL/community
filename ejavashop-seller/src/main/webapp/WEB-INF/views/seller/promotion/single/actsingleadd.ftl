<#include "/seller/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){

	$("#productNum").val(0);

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/single";
	});
	$("#add").click(function(){
		
		if($("#addForm").form('validate')){

			var type = $("#type").val();
			if (type == null || type == "") {
				$.messager.alert('提示','请选择活动类型。');
				return;
			}
			
			var channel = $("#channel").val();
			if (channel == null || channel == "") {
				$.messager.alert('提示','请选择活动应用渠道。');
				return;
			}
			
			var num = parseInt($("#productNum").val());
	    	if (num <= 0) {
	    		$.messager.alert('提示', '请选择参加活动的商品。');
				return;
	    	}
	    	
	    	if(num>1000){
	    		$.messager.alert('提示', '同一个活动最多选择1000个商品。');
				return;
	    	}
	    	
	    	// 减免固定金额时检查价格
	    	var discount = parseFloat($("#discount").val());
	    	if (type == 1) {
		    	var name1 = "";
		    	$.each($("input[id='mallPcPrice']"), function(index, item){
		    		// 通用渠道检查商家价和移动端价
		    		if (channel == 1) {
		    			if (parseFloat($(item).val()) <= discount) {
			    			name1 = $(item).siblings("#productName").val();
			    			return false;
			    		}
			    		if (parseFloat($(item).siblings("#malMobilePrice").val()) <= discount) {
			    			name1 = $(item).siblings("#productName").val();
			    			return false;
			    		}
		    		} else if (channel == 2) {
		    			// PC渠道只检查商城价
		    			if (parseFloat($(item).val()) <= discount) {
			    			name1 = $(item).siblings("#productName").val();
			    			return false;
			    		}
		    		} else if (channel == 3) {
		    			// 移动端渠道只检查移动端价
			    		if (parseFloat($(item).siblings("#malMobilePrice").val()) <= discount) {
			    			name1 = $(item).siblings("#productName").val();
			    			return false;
			    		}
		    		}
		    	});
		    	
		    	if (name1 != "") {
		    		$.messager.alert('提示', '商品【' + name1 + '】的价格已低于优惠金额，请重新选择商品（参加活动的商品价格必须大于优惠金额）。');
					return;
		    	}
	    	} else {
	    		// 折扣时检查折扣值，在0到1之间
	    		if (discount <= 0 || discount >= 1) {
	    			$.messager.alert('提示', '折扣值必须在0到1之间。');
					return;
	    		}
	    	}
			
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/single/create")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	$('#pro').click(function(){
		$('#goodsDialog').dialog('open');
		$('#gd_dataGrid').datagrid('unselectAll');
	});
	
	$(document).on('click','.a-del-proItem',function () {
		$(this).parent('.addItemT').remove();
		var num = parseInt($("#productNum").val()) - 1;
		$("#productNum").val(num);
		return false;
	});
	
	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">添加单品立减<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/single">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="actSingleName" name="actSingleName" value="${(actSingle.actSingleName)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动类型：</label>
							<@cont.select id="type" value="${(actSingle.type)!''}" codeDiv="ACT_SINGLE_TYPE" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>优惠额/折扣：</label>
							<input class="easyui-numberbox txt w280" type="text" id="discount" name="discount" value="${(actSingle.discount)!''}" data-options="required:true,precision:2" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								活动类型为减免金额时为金额（如10为减免10元），折扣类型时为折扣（如0.90为打九折）。
								</font>
							</label>
						</p>
					</div>
					<br/>
					
					<!-- 用于计算数量 -->
					<input type="hidden" id="productNum" name="productNum" value="0">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动商品：</label> 
							<input type="button" value="选择商品" id="pro"/>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								一个订单满减活动最多添加1000个商品。
								</font>
							</label>
						</p>
					</div>
					<#if productList??>
						<#list  productList as product>
							<div class="fluidbox addItemT">
									<label class="lab-item">&nbsp;</label>
									<input type="hidden" id="ids" name="ids" value="${(product.id)!''}" />
									商品名称：
									<input type="text" id="productName" name="productName" readonly="readonly" style="background:#eee;color:#777;" value="${(product.name1)!''}" class="txt w250" />
									&nbsp;&nbsp;商城价：
									<input type="text" id="mallPcPrice" name="mallPcPrice" readonly="readonly" style="background:#eee;color:#777;" value="${(product.mallPcPrice)!''}" class="txt w50" />
									&nbsp;&nbsp;移动端价：
									<input type="text" id="malMobilePrice" name="malMobilePrice" readonly="readonly" style="background:#eee;color:#777;" value="${(product.malMobilePrice)!''}" class="txt w50" />
									<a class="a-del-proItem" href="#"><b> —删除</b></a>
							</div>
						</#list>
					</#if>
					<div class="pro-addItem-box"></div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动时间：</label>
							
							<input type="text" id="startTime" name="startTime"
								class="txt w200 easyui-validatebox" missingMessage="开始时间必填"
								data-options="required:true"
								onclick="WdatePicker({startDate:'%y-%M-{%d+1} 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"
								value="${(actSingle.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						~
						<input type="text" id="endTime" name="endTime"
								class="txt w200 easyui-validatebox" missingMessage="结束时间必填"
								data-options="required:true"
								onclick="WdatePicker({startDate:'%y-%M-{%d+1} 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});"
								value="${(actSingle.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用渠道：</label>
							<@cont.select id="channel" value="${(actSingle.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动描述：</label>
							<textarea name="remark" rows="4" cols="60" id="remark" class="{maxlength:255}" >${(actSingle.remark)!''}</textarea>
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

<div style="display: none">
<#include "goodsDialog.ftl"/>
</div>

<#include "/seller/commons/_detailfooter.ftl" />