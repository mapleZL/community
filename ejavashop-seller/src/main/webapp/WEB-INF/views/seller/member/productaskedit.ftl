<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/member/productask"/>

<script language="javascript">
$(function() {

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/member/productask";
	});
	
	$("#edit").click(function(){
		var replyContent = $("#replyContent").val();
		if (replyContent == null || replyContent == "") {
			$.messager.alert('提示','请输入回复内容！');
			return;
		}
		
		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/seller/member/productask/doreply")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">用户咨询<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/member/productask">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(productAsk.id)!''}"/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">用户名称：</label>
							<label>${(productAsk.userName)!''}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">商家名称：</label>
							<label>${(productAsk.sellerName)!''}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">商家名称：</label>
							<label>${(productAsk.askContent)!''}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">商家回复：</label>
							<textarea name="replyContent" rows="4" cols="60" id="replyContent" class="{maxlength:255}">${(productAsk.replyContent)!''}</textarea>
						</p>
					</div>
				</dd>
			</dl>
			</@form.form>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="edit" class="btn" value="回复" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			
		</div>
	</div>
</div>



<#include "/seller/commons/_detailfooter.ftl" />