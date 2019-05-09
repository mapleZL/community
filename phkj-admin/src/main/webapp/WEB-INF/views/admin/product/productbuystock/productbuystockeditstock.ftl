<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/onSale";
	});
	$("#add").click(function(){
		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/productbuystock/create")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
	
})


//全选、取消全选的事件
function selectAll(){
	if ($("#SelectAll").attr("checked")) {
		$(":checkbox").attr("checked", true);
	} else {
		$(":checkbox").attr("checked", false);
	}
}
//子复选框的事件
function setSelectAll(){
	//当没有选中某个子复选框时，SelectAll取消选中
	if (!$("#subcheck").checked) {
		$("#SelectAll").attr("checked", false);
	}
	var chsub = $("input[type='checkbox'][id='subcheck']").length; //获取subcheck的个数
	var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
	if (checkedsub == chsub) {
		$("#SelectAll").attr("checked", true);
	}
}

<#if exists?? && exists == true>
	var time = Number(4);
	var interval_ = setInterval(function(){
		$("#seconds").html(time);
		
		if(time == 0){
			clearInterval(interval_);
			window.location.href = "${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/onSale";
		}
		time --;
	},1000);
</#if>
</script>

<div class="wrapper">
	<div class="formbox-a">
		<#if exists?? && exists == true>
		<div style="padding: 30px;font-size: 15px;text-align: center;">
			该商品已存在库存设定<span style="font-size: 11px;color: red;margin-left: 10px;">
			<i id="seconds">5</i>秒后返回</span>
		</div>
		<#else>
		<h2 class="h2-title">设置下单限制<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/onSale">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<input type="hidden" name="productId" value="${(product.id)!}" />
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商品名称：</label>
							${(product.name1)!""}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>库存最低值：</label>
							<input class="easyui-numberbox txt w280" type="text" id="stock" name="stock" value="${(productBuyStock.stock)!''}" data-options="min:1,max:99999999,required:true" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								当SKU库存低于这个数值的时候，开始启用限制
								</font>
							</label>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>是否使用: </label>
							<@cont.radio id="state" value="${(productBuyStock.state)!''}" codeDiv="USE_YN" />
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>注册会员：</label>
							<input class="easyui-numberbox txt w280" id="grade1" name="grade1" value="${(productBuyStock.grade1)!''}" data-options="min:0,max:1,precision:2,required:true" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								注册会员可以购买的比例（0到1之间）
								</font>
							</label>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>铜牌会员：</label>
							<input class="easyui-numberbox txt w280" id="grade2" name="grade2" value="${(productBuyStock.grade2)!''}" data-options="min:0,max:1,precision:2,required:true" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								铜牌会员可以购买的比例（0到1之间）
								</font>
							</label>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>银牌会员：</label>
							<input class="easyui-numberbox txt w280" id="grade3" name="grade3" value="${(productBuyStock.grade3)!''}" data-options="min:0,max:1,precision:2,required:true" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								银牌会员可以购买的比例（0到1之间）
								</font>
							</label>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>金牌会员：</label>
							<input class="easyui-numberbox txt w280" id="grade4" name="grade4" value="${(productBuyStock.grade4)!''}" data-options="min:0,max:1,precision:2,required:true" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								金牌会员可以购买的比例（0到1之间）
								</font>
							</label>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>钻石会员：</label>
							<input class="easyui-numberbox txt w280" id="grade5" name="grade5" value="${(productBuyStock.grade5)!''}" data-options="min:0,max:1,precision:2,required:true" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								钻石会员可以购买的比例（0到1之间）
								</font>
							</label>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<label class="lab-item"><font class="red">*</font>选择SKU：</label>
						<table width="80%" border="0" cellspacing="0" cellpadding="0"
								style="margin-left: 30px" id="optable">
								<tbody>
									<tr style="background: #CCC; border: 1px solid #e2e1e1; font-size: 12px;">
										<td width="10%" style="padding: 6px;"><input type="checkbox" id="SelectAll" onclick="selectAll();"/></td>
										<td width="20%" style="padding: 6px;">商品sku</td>
										<td width="70%" style="padding: 6px;">商品规格</td>
									</tr>
										<#if product?? && product.goodsList??>
											<#list product.goodsList as goods>
											  <tr>
												<td width="10%" style="padding: 6px;"><input name="goodsIdAll" type="checkbox" id="subcheck" value="${(goods.id)}" onclick="setSelectAll();"/></td>
												<td width="20%" style="padding: 6px;">${(goods.sku)!""}</td>
												<td width="70%" style="padding: 6px;">${(goods.normName)!""}</td>
											   </tr>
											</#list>
										</#if>
								</tbody>
							</table>
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
		</#if>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />