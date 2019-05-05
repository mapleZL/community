<#include "/seller/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/flash";
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

	function addProduct(obj){
		$('#goodsDialog').dialog('open');
		$('#gd_dataGrid').datagrid('unselectAll');
		
		var stageIdHidden = $(obj).siblings(".stageIdHidden").val();
		$("#currStageId").val(stageIdHidden);
	}
	
	function saveProduct(obj){
		
		var productId = $(obj).siblings("#productId").val();
		var price = $(obj).siblings("#price").val();
		var stock = $(obj).siblings("#stock").val();
		var stageId = $(obj).siblings("#stageId").val();
		
		if (productId == null || productId == "") {
			$.messager.alert('提示', "请选择商品");
			return false;
		}
		if(isNaN(price)){
			$.messager.alert('提示', "价格必须是数字");
			return false;
		}
		if(isNaN(stock)){
			$.messager.alert('提示', "库存必须是正整数");
			return false;
		}
		
		var params = "productId=" + productId;
		params += "&price=" + price;
		params += "&stock=" + stock;
		params += "&stageId=" + stageId;
		params += "&actFlashSaleId=" + $("#id").val();

        $.messager.confirm('提示', '确定保存该条申请信息吗？', function(r){
            if (r){
                $.messager.progress({text:"提交中..."});
                $.ajax({
                    type:"POST",
                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/flash/saveproduct",
                    dataType: "json",
                    data: params,
                    cache:false,
                    success:function(data, textStatus){
                        if (data.success) {
                            $.messager.alert('提示', "保存成功！");
                        }else{
                            $.messager.alert('提示', data.message);
                        	//删除当前行
                            $(obj).parent('.p-item').remove();
                        }
                        $.messager.progress('close');
                    }
                });
            }
        });
	}

	function deleteProduct(obj) {
		var productId = $(obj).siblings("#productId").val();
		var stageId = $(obj).siblings("#stageId").val();
		
		if (productId == null || productId == "") {
			$.messager.alert('提示', "请选择商品");
			return false;
		}
		
		var params = "productId=" + productId;
		params += "&stageId=" + stageId;

        $.messager.confirm('提示', '确定删除该条申请信息吗？', function(r){
            if (r){
                $.messager.progress({text:"提交中..."});
                $.ajax({
                    type:"POST",
                    url: "${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/flash/applydelete",
                    dataType: "json",
                    data: params,
                    cache:false,
                    success:function(data, textStatus){
                        if (data.success) {
                        	$(obj).parent('.p-item').remove();
                            $.messager.alert('提示', "删除成功！");
                        }else{
                            $.messager.alert('提示', data.message);
                        }
                        $.messager.progress('close');
                    }
                });
            }
        });
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">活动商品申请<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/flash">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(actFlashSale.id)!''}">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动名称：</label>
							<label>${(actFlashSale.actFlashSaleName)!''}</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动日期：</label>
							<label>${(actFlashSale.actDate?string('yyyy-MM-dd'))!''}</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动状态：</label>
							<@cont.select disabled="disabled" id="status" value="${(actFlashSale.status)!''}" codeDiv="FLASH_SALE_STATUS" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用渠道：</label>
							<@cont.select disabled="disabled" id="channel" value="${(actFlashSale.channel)!''}" codeDiv="CHANNEL" style="width:100px" mode="1"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">PC图片：</label>
							<#if actFlashSale.pcImage?? >
								<img alt="图片" style="width: 192px;height: 45px;"
												src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(actFlashSale.pcImage)!''}">
							</#if>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								用于PC端活动页展示。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">移动图片：</label>
							<#if actFlashSale.mobileImage?? >
								<img alt="图片" style="width: 144px;height: 70px;"
												src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(actFlashSale.mobileImage)!''}">
							</#if>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								用于移动端活动页展示。
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">作废原因：</label>
							<label>${(actFlashSale.auditOpinion)!''}</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">申请规则：</label>
							<label>${(actFlashSale.auditRule)!''}</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">活动描述：</label>
							<label>${(actFlashSale.remark)!''}</label>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>抢购阶段：</label> 
							<label>请根据自己配货信息申请活动商品，同阶段如果添加重复商品会覆盖之前的设置</label>
						</p>
					</div>
					<input type="hidden" value="" id="currStageId"/>
					<#if stageList??>
						<#list  stageList as stage>
							<div class="fluidbox addItemT">
								<p class="p12 p-item">
									<label class="lab-item">${(stage.startTime)!''}点 ~ ${(stage.endTime)!''}点：</label>
									<#if actFlashSale.status?? && actFlashSale.status == 2 >
									<input class="inputs" type="button" value="添加商品" onclick="addProduct(this)"/>
									</#if>
									<label class="">${(stage.remark)!''}</label>
									<input type="hidden" value="${stage.id}" id="hd_stateId_${stage_index}" class="stageIdHidden"/>
								</p>
								<#if stage.productList?? && stage.productList?size &gt; 0 >
									<#if actFlashSale.status?? && actFlashSale.status == 2 >
										<#list stage.productList as stageProduct>
										<p class="p12 p-item">
											<label class="lab-item">&nbsp;</label>
											<input class="inputs" type="button" value="保存" onclick="saveProduct(this)"/>
											<input class="inputs" type="button" value="删除" onclick="deleteProduct(this)"/>
											<input type="hidden" id="productId" name="productId" value="${(stageProduct.productId)!''}">
											<input type="hidden" id="stageId" name="stageId" value="${(stageProduct.actFlashSaleStageId)!''}">
											商品：<input class="easyui-validatebox txt w280" type="text" id="productName" name="productName" value="${(stageProduct.product.name1)!''}" data-options="required:true,validType:'length[0,255]'" disabled="true">
											价格：<input class="easyui-numberbox w80" type="text" id="price" name="price" value="${(stageProduct.price)!''}" data-options="required:true,precision:2" >
											原价：<input class="easyui-numberbox w80" type="text" id="pcPrice" name="pcPrice" value="${(stageProduct.product.mallPcPrice)!''}" data-options="required:true,precision:2" disabled="true">
											库存：<input class="easyui-numberbox w80" type="text" id="stock" name="stock" value="${(stageProduct.stock)!''}" data-options="required:true,min:1" >
										</p>
										</#list>
									<#else>
										<#list stage.productList as stageProduct>
										<p class="p12 p-item">
											<label class="lab-item">&nbsp;</label>
											商品：<input class="easyui-validatebox txt w280" type="text" id="productName" name="productName" value="${(stageProduct.product.name1)!''}" disabled="true">
											价格：<input class="easyui-numberbox w80" type="text" id="price" name="price" value="${(stageProduct.price)!''}" data-options="required:true,precision:2" disabled="true">
											原价：<input class="easyui-numberbox w80" type="text" id="pcPrice" name="pcPrice" value="${(stageProduct.product.mallPcPrice)!''}" data-options="required:true,precision:2" disabled="true">
											库存：<input class="easyui-numberbox w80" type="text" id="stock" name="stock" value="${(stageProduct.stock)!''}" disabled="true">
											销量：<input class="easyui-numberbox w80" type="text" id="actualSales" name="actualSales" value="${(stageProduct.actualSales)!''}" disabled="true">
										</p>
										<br/>
										<p class="p12 p-item">
											<label class="lab-item">&nbsp;</label>
											状态：<@cont.select disabled="disabled" id="status" value="${(stageProduct.status)!''}" codeDiv="FLASH_PRODUCT_STATUS" style="width:100px" mode="1"/>
											审核意见：<input class="easyui-validatebox w280" type="text" id="auditOpinion" name="auditOpinion" value="${(stageProduct.auditOpinion)!''}" disabled="true">
										</p>
										</#list>
									</#if>
								</#if>
								<div id="stage${stage.id}"></div>
							</div>
						</#list>
					</#if>
					<br/>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
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