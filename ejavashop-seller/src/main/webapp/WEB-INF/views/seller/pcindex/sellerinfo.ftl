<#include "/seller/commons/_detailheader.ftl" />

<script language="javascript">
$(function(){
	
	$("#edit").click(function(){
		
		var detail = UM.getEditor('myEditor').getContent();
        $('#detail').val(detail);//商品描述信息
        
		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/seller/pcindex/sellerinfo/update")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	<#if pcSellerIndex?? && (pcSellerIndex.detail)??>
	    UM.getEditor('myEditor').setContent(<#noescape>'${(pcSellerIndex.detail)}'</#noescape>, true);
	</#if>

	<#if message??>$.messager.progress('close');alert('${message}');</#if>
})
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">首页信息编辑</h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
                    <input type="hidden" id="id" name="id" value="${(pcSellerIndex.id)!''}">
                    
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">logo图片：</label>
							<input type="file" id="imageFile" name="imageFile"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox"/>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								图片最佳像素：宽160，高80
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<img alt="图片" style="width: 160px;height: 80px;"
											src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(pcSellerIndex.logo)!''}">
						</p>
						<input type="hidden" id="logo" name="logo" value="${(pcSellerIndex.logo)!''}">
					</div>
					<br/>
                    
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">店铺公告：</label>
							<textarea name="notice" rows="4" cols="60" id="notice" class="{maxlength:100}" >${(pcSellerIndex.notice)!''}</textarea>
						</p>
					</div>
					<br/>
                    
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">店铺介绍: </label>
                        	<div style="padding-left: 140px;padding-top: 2px;"><#include "desc.ftl"/></div>
                        	<input type="hidden" id="detail" name="detail" value="${(pcSellerIndex.detail)!''}"/>
                        </p>
                    </div>
                    <br/>
				</dd>
			</dl>
			
			<!-- <dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>帮助</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label></label>
						</p>
					</div>
				</dd>
			</dl> -->
			
			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="edit" class="btn" value="确定"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/seller/commons/_detailfooter.ftl" />