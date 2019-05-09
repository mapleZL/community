<#include "/admin/commons/_detailheader.ftl" /> 
<script>
$(function(){
	<#if message??>
		$.messager.alert('提示','${(message)!}');
	</#if>
	$("#cacheIndex1").click(function(){
		$.messager.confirm('提示', '确定刷新商品属性缓存吗？', function(r){
			window.open("http://www.dawawang.com/cache/updateProductAttrCache?typeId=1");
		});
	});
	$("#cacheIndex2").click(function(){
		$.messager.confirm('提示', '确定刷新商品属性缓存吗？', function(r){
			window.open("http://m.dawawang.com/cache/updateProductAttrCache?typeId=1");
		});
	});
})
</script>
	
<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
				刷新商品属性缓存
			<span class="s-poar">
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>说明
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
						本页面刷新商品属性缓存使用。<br/>
						建议在必要时才使用此功能。
						</label>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="cacheIndex1" class="btn" value="刷新pc缓存" />
				<input type="button" id="cacheIndex2" class="btn" value="刷新H5缓存" />
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
