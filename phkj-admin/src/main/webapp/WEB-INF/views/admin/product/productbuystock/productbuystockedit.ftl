<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">

$(function(){
	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/productbuystock";
	});
	
	$("#add").click(function(){
		if($("#addForm").form('validate')){
			$.messager.progress({
				text : "提交中..."
			});
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/productbuystock/doEdit")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	var productStock = "${(productBuyStock.productStock)!0}";
		var inputstock = "${(productBuyStock.stock)!''}";
		if (inputstock && productStock
				&& Number(inputstock) > Number(productStock)) {
			$(".tooltip").css("left",
					$("#stock").offset().left + $("#stock").width()).show();
		} else {
			$(".tooltip").hide();
		}
	});
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			编辑下单限制(单sku)<span class="s-poar"><a class="a-back"
				href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/productbuystock">返回</a></span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm" enctype="multipart/form-data"> <input type="hidden"
				name="id" value="${(productBuyStock.id)!}" />
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">商品名称：</label>
							${(productBuyStock.productName)!""}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">SKU：</label>
							${(productBuyStock.sku)!""}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">规格：</label>
							${(productBuyStock.normName)!""}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>库存最低值：</label>
							<input class="easyui-numberbox txt w280" type="text" id="stock"
								name="stock" value="${(productBuyStock.stock)!''}"
								data-options="min:1,max:${(productBuyStock.productStock)!999999},required:true">
						</p>
						<div class="tooltip tooltip-right"
							style="display: block; color: #C93; background-color: #FFC; border-color: #DBB46F;">
							<div class="tooltip-content">该规则库存最低值大于货品库存，系统已调整为货品最大库存</div>
							<div class="tooltip-arrow-outer"
								style="border-right-color: rgb(204, 153, 51);"></div>
							<div class="tooltip-arrow"
								style="border-right-color: rgb(255, 255, 204);"></div>
						</div>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label> <label> <font
								style="color: #808080"> 当SKU库存低于这个数值的时候，开始启用限制 </font>
							</label>
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>注册会员：</label> <input
								class="easyui-numberbox txt w280" id="grade1" name="grade1"
								value="${(productBuyStock.grade1)!''}"
								data-options="min:0,max:1,precision:2,required:true">
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label> <label> <font
								style="color: #808080"> 注册会员可以购买的比例（0到1之间） </font>
							</label>
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>铜牌会员：</label> <input
								class="easyui-numberbox txt w280" id="grade2" name="grade2"
								value="${(productBuyStock.grade2)!''}"
								data-options="min:0,max:1,precision:2,required:true">
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label> <label> <font
								style="color: #808080"> 铜牌会员可以购买的比例（0到1之间） </font>
							</label>
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>银牌会员：</label> <input
								class="easyui-numberbox txt w280" id="grade3" name="grade3"
								value="${(productBuyStock.grade3)!''}"
								data-options="min:0,max:1,precision:2,required:true">
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label> <label> <font
								style="color: #808080"> 银牌会员可以购买的比例（0到1之间） </font>
							</label>
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>金牌会员：</label> <input
								class="easyui-numberbox txt w280" id="grade4" name="grade4"
								value="${(productBuyStock.grade4)!''}"
								data-options="min:0,max:1,precision:2,required:true">
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label> <label> <font
								style="color: #808080"> 金牌会员可以购买的比例（0到1之间） </font>
							</label>
						</p>
					</div>
					<br />

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>钻石会员：</label> <input
								class="easyui-numberbox txt w280" id="grade5" name="grade5"
								value="${(productBuyStock.grade5)!''}"
								data-options="min:0,max:1,precision:2,required:true">
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label> <label> <font
								style="color: #808080"> 钻石会员可以购买的比例（0到1之间） </font>
							</label>
						</p>
					</div>
					<br />
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="提交" /> <input
					type="button" id="back" class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />
