<#include "/seller/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/operate/sellerqq"/>

<style>
.dl-group p img {
	max-width: 120px;
	float: left;
}

.formbox-a .lab-item {
	float: left;
	width: 120px;
	text-align: right;
	margin-right: 3px;
	display: inline;
	padding-top: 5px;
}
</style>

<script language="javascript">
	$(function() {

		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
		});

		$("#add").click(function() {
			if ($('#addForm').form('validate')) {
				$('#addForm').submit();
			}
		});

	})

	function closeWin() {
		$('#newstypeWin').window('close');
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			<#if obj??> 编辑客服QQ <#else> 新增客服QQ </#if> <span class="s-poar">
				<a class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm" enctype="multipart/form-data"
			action="${currentBaseUrl}/doAdd"> <input type="hidden"
				value="${(obj.id)!''}" name="id"> <input type="hidden"
				value="${(obj.image)!''}" name="image">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>QQ: </label> <input
								type="text" id="qq" name="qq" value="${(obj.qq)!''}"
								class="txt w200 easyui-numberbox" missingMessage="请输入qq"
								data-options="required:true" />
						</p>
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>客服名称: </label> <input
								type="text" id="name" name="name" value="${(obj.name)!''}"
								class="txt w200 easyui-validatebox" missingMessage="请输入客服名称"
								data-options="required:true" />
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red">*</font>状态: </label>
							<@cont.select id="state" mode="-1" codeDiv="SELLER_QQ_STATE"
							name="state" class="txt" value="${(obj.state)!''}" style="width:200px;"/>
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
						<label class="lab-item" style="width: 100%; text-align: left;">
							请添加客服QQ,填写完毕后点击提交 </label>
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
</div>

<#include "/seller/commons/_detailfooter.ftl" />
