<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/productPackage"/>

<style>
	.p-item label:not(.lab-item){
		margin-left: 10px;
	}
	
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
		
	});
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			<#if obj??> 编辑套餐设定 <#else> 新增套餐设定</#if><span class="s-poar"> <a
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
							<label class="lab-item"><font class="red">*</font>套餐名称： </label>
							<input type="text" id="name" name="name"
								class="txt w200 easyui-validatebox" missingMessage="请输入套餐名称"
								data-options="required:true,validType:'length[2,200]'"
								value="${(obj.name)!''}" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>加工价格： </label>
							<input type="text" id="price" name="price"
								class="txt w200 easyui-numberbox" missingMessage="请输入加工价格"
								data-options="min:0.01,max:99999,precision:2,required:true"
								value="${(obj.price)!''}" />
						</p>
					</div>
					<div class="fluidbox">
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>状态： </label>
							<@cont.select id="state" mode="-1"
								codeDiv="PRO_PACKAGE_STATE" value="${(obj.state)!''}" name="state" class="txt w200 easyui-validatebox"
								style="width:200px;"/>
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>包装方式： </label>
							<@cont.select id="packingType" mode="-1"
								codeDiv="PRO_PACKAGE_TYPE" value="${(obj.packingType)!''}" name="packingType" class="txt w200 easyui-validatebox"
								style="width:200px;"/>	
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>包装单位： </label>
							<@cont.select id="unitType" mode="-1"
								codeDiv="PRO_PACKAGE_UNIT" value="${(obj.unitType)!''}" name="unitType" class="txt w200 easyui-validatebox"
								style="width:200px;"/>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item" style="width:100%">
							<label class="lab-item">其它： </label>
							<label>
								<input type="checkbox" id="isHook" name="isHook" <#if (obj.isHook)?? && obj.isHook==1>checked</#if> value="1" />
								钩子
							</label>
							<label>
								<input type="checkbox" id="isGlue" name="isGlue" <#if (obj.isGlue)?? && obj.isGlue==1>checked</#if> value="1" />
								不干胶
							</label>
							<label>
								<input type="checkbox" id="isLiner" name="isLiner" <#if (obj.isLiner)?? && obj.isLiner==1>checked</#if> value="1" />
								衬板
							</label>
							<label>
								<input type="checkbox" id="isBag" name="isBag" <#if (obj.isBag)?? && obj.isBag==1>checked</#if> value="1" />
								中包袋
							</label>
							<label>
								<input type="checkbox" id="isLabel" name="isLabel" <#if (obj.isLabel)?? && obj.isLabel==1>checked</#if> value="1" />
								防伪标
							</label>
							<label>
								<input type="checkbox" id="isGirdle" name="isGirdle" <#if (obj.isGirdle)?? && obj.isGirdle==1>checked</#if> value="1" />
								腰封
							</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item" style="width:100%">
							<label class="lab-item">描述： </label>
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
                               	推荐上传100*100像素的套餐主题图片
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
							<li>设置二次加工套餐</li>
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

	<#include "/admin/commons/_detailfooter.ftl" />