<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){


	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/banner";
	});
	$("#edit").click(function(){

		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/banner/update")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})
	function hideSortedId(){
		$('#sortedId').hide();
	}
	function showSortedId(){
		$('#sortedId').show();
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">编辑首页轮播图<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/banner">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					<input type="hidden" id="id" name="id" value="${(pcIndexBanner.id)!''}">
					<input type="hidden" id="status" name="status" value="${(pcIndexBanner.status)!''}">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>标题：</label>
							<input class="easyui-validatebox txt w280" type="text" id="title" name="title" value="${(pcIndexBanner.title)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>链接地址：</label>
							<input class="easyui-validatebox txt w280" type="text" id="linkUrl" name="linkUrl" value="${(pcIndexBanner.linkUrl)!''}" data-options="required:true,validType:'length[0,100]'" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								请填写完整的地址
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>类型：</label>
							<#if pcIndexBanner.type == '0'>
								轮播图：<input  type="radio" id="type0" name="type" value="0" checked="checked"  onclick="javascript:showSortedId()"/>
								弹出层：<input  type="radio" id="type1" name="type" value="1"  onclick="javascript:hideSortedId()"/>
								顶层广告：<input  type="radio" id="type2" name="type" value="2"  onclick="javascript:showSortedId()"/>
							<#elseif pcIndexBanner.type == '1'>
								轮播图：<input  type="radio" id="type1" name="type" value="0"  onclick="javascript:showSortedId()"/>
								弹出层：<input  type="radio" id="type1" name="type" value="1"  checked="checked"  onclick="javascript:hideSortedId()"/>
								顶层广告：<input  type="radio" id="type2" name="type" value="2"  onclick="javascript:showSortedId()"/>
							<#elseif pcIndexBanner.type == '2'>
								轮播图：<input  type="radio" id="type1" name="type" value="0"  onclick="javascript:showSortedId()"/>
								弹出层：<input  type="radio" id="type1" name="type" value="1"   onclick="javascript:hideSortedId()"/>
								顶层广告：<input  type="radio" id="type2" name="type" value="2"  checked="checked" onclick="javascript:showSortedId()"/>
							</#if>
						</p>
					</div>
					<br/>
					<div class="fluidbox" id = "sortedId">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>排序号：</label>
							<input class="easyui-numberbox w280" id="orderNo" name="orderNo" value="${(pcIndexBanner.orderNo)!''}" data-options="required:true,max:99" >
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								序号越小越靠前显示
								</font>
							</label>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<label class="lab-item"><font class="red">*</font>展示时间：</label>
						
						<input type="text" id="startTime" name="startTime"
								class="txt w200 easyui-validatebox" missingMessage="开始时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"
								value="${(pcIndexBanner.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						~
						<input type="text" id="endTime" name="endTime"
								class="txt w200 easyui-validatebox" missingMessage="结束时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});"
								value="${(pcIndexBanner.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>图片：</label>
							<input type="file" id="imageFile" name="imageFile"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" />
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<label>
								<font style="color: #808080">
								图片最佳像素（或保持该比例）：宽1920，高457
								</font>
							</label>
						</p>
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<img alt="图片" style="width: 768px;height: 180px;"
											src="${domainUrlUtil.EJS_IMAGE_RESOURCES}${pcIndexBanner.image}">
						</p>
						<input type="hidden" id="image" name="image" value="${(pcIndexBanner.image)!''}">
					</div>
					<br/>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="edit" class="btn" value="修改" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />