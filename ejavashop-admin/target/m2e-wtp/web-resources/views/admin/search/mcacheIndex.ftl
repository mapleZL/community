<#include "/admin/commons/_detailheader.ftl" /> 
<script>
$(function(){
	<#if message??>
		$.messager.alert('提示','${(message)!}');
	</#if>
	$("#add").click(function(){
		$.messager.confirm('提示', '确定索引要初始化吗？', function(r){
			if (r){
				//window.open("m.dawawang.com/cacheIndex.html");
				$.ajax({
					type : "GET",
					url :  "m.dawawang.com/cache/doIndexCache.html",
					dataType : "json",
					success : function(data) {
						if(data.success){
							alert(data.message);
						}
					}
				});
			}
		});
	});
})
</script>
	
<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
				H5初始化
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
						本次是H5首页进行静态化。<br/>
						建议在必要时才使用此功能。
						</label>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="初始化" />
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
