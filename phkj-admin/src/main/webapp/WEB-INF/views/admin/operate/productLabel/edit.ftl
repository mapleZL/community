<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/productLabel"/>

<style>
	.panel-fit{
		height: 100%;
		margin: 0;
		padding: 0;
		border: 0;
	    overflow: inherit;
	}
</style>
<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.EJS_URL_RESOURCES}";
	
	$(function(){
		$("#back").click(function(){
			location.href="${currentBaseUrl}";
		});
		
		$("#add").click(function() {
			var isValid = $("#addForm").form('validate');
			if (isValid) {
				$.messager.progress({
					text : "提交中..."
				});
				$('#addForm').submit();
			}
		});
		
		$("#sel_member").change(function(){
			if($(this).val()==1){
				$('#memberDialog').dialog('open');
			}
		});
		
	});
	
	function memberCallBack(data) {
		var name = data.name;
		$("#memberId").val(data.id);
		$("#memberinfo").html("已选择客户："+name);
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			<#if obj??> 编辑标签管理 <#else> 新增标签管理</#if><span class="s-poar"> <a
				class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm" enctype="multipart/form-data"
			action="${currentBaseUrl}/doAdd"> <input type="hidden"
				value="${(obj.id)!}" name="id">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>标签名称： </label>
							<input type="text" id="name" name="name"
								class="txt w200 easyui-validatebox" missingMessage="请输入标签名称"
								data-options="required:true,validType:'length[2,200]'"
								value="${(obj.name)!''}" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>状态： </label>
							<@cont.select id="state" mode="-1"
							codeDiv="PRODUCT_LABEL_STATE" value="${(obj.state)!''}" name="state" class="txt w200 easyui-validatebox"
							style="width:200px;"/>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>所属客户</label>
							<#assign membervalue = 0>
							<#if (obj.memberId)??&&obj.memberId!=0>
							<#assign membervalue = 1>
							</#if>
							<@cont.select id="sel_member" mode="-1"
								codeDiv="PRODUCT_LABEL_MEMBER" value="${membervalue}" 
								class="txt w200 easyui-validatebox"/>
							<input type="hidden" name="memberId" id="memberId" value="${(obj.memberId)!''}" />
							<span id="memberinfo"><#if membervalue!=0>已选择客户：${(obj.memberName)!'数据错误'}</#if></span>
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>（条码）SKU： </label>
							<input type="text" id="sku" name="sku"
								class="txt w200 easyui-validatebox" missingMessage="请输入sku"
								data-options="required:true,validType:'length[2,20]'"
								value="${(obj.sku)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>毛重： </label>
							<input type="text" id="grossWeight" name="grossWeight"
								class="txt w200 easyui-numberbox" missingMessage="请输入毛重"
								data-options="min:0.01,max:99999,precision:2,required:true"
								value="${(obj.grossWeight)!''}" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>净重： </label>
							<input type="text" id="netWeight" name="netWeight"
								class="txt w200 easyui-numberbox" missingMessage="请输入净重"
								data-options="min:0.01,max:99999,precision:2,required:true"
								value="${(obj.netWeight)!''}" />
						</p>
					</div>
					<div class="fluidbox" style="width: 49%;float: left;">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                请填写毛重信息，单位为克（g），保留两位小数
                            </font>
                        </p>
                    </div>
                    <div class="fluidbox" style="width: 50%;">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                 请填写净重信息，单位为克（g），保留两位小数
                            </font>
                        </p>
                    </div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>规格： </label>
							<input type="text" id="norms" name="norms"
								class="txt w200 easyui-validatebox" missingMessage="请输入规格"
								data-options="required:true,validType:'length[1,50]'"
								value="${(obj.norms)!''}" />
						</p>
						
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>成本价： </label>
							<input type="text" id="costPrice" name="costPrice"
								class="txt w200 easyui-numberbox" missingMessage="请输入成本价"
								data-options="min:0.00,max:99999,precision:2,required:true"
								value="${(obj.costPrice)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>市场价： </label>
							<input type="text" id="marketPrice" name="marketPrice"
								class="txt w200 easyui-numberbox" missingMessage="请输入市场价"
								data-options="min:0.00,max:99999,precision:2,required:true"
								value="${(obj.marketPrice)!''}" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>库存： </label>
							<input type="text" id="stock" name="stock"
								class="txt w200 easyui-numberbox" missingMessage="请输入库存"
								data-options="min:1,max:9999999,precision:0,required:true"
								value="${(obj.stock)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>品牌： </label>
							<input type="text" id="brand" name="brand"
								class="txt w200 easyui-validatebox" missingMessage="请输入品牌"
								data-options="required:true,validType:'length[2,20]'"
								value="${(obj.brand)!''}" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item" style="width: 100%">
							<label class="lab-item">说明： </label>
							<textarea id="describe" name="describe" 
								cols="91">${(obj.describe)!''}</textarea>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">图片： </label>
							<input type="file" id="imagepic" name="imagepic"
								class="txt w240 easyui-validatebox" />
							<#if obj??> <img alt="" style="max-width: 300px;margin-left: 140px;"
								src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(obj.image)!''}">
							</#if> 
						</p>
					</div>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                请上传标签图片，推荐上传100*100像素的套餐主题图片
                            </font>
                        </p>
                    </div>
				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<ul style="margin-left: 30px;">
							<li>设置标签管理</li>
						</ul>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="提交" /> <input
					type="button" id="back" class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>
	
<#include "memberdialog.ftl" />
<#include "/admin/commons/_detailfooter.ftl" />