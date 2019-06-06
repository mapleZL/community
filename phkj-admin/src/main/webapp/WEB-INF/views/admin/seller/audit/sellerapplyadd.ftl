<#include "/admin/commons/_detailheader.ftl" />
<#include "productimgcss.ftl"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){
	<#--加载图片上传控件-->
    $("[name=uploadImg]").multiupload();
    $("[name=uploadImg2]").multiupload();
	
	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage";
	});
	$("#add").click(function(){
		
		var image = $('img[name^=prev_]');
        if (image && image.length > 0) {
            var imageSrc = '';
            for (var i = 0; i < image.length; i++) {
                var imgSrc = $(image[i]).attr('src');
                if (imgSrc.indexOf("resources") != -1)
                    continue;
                imageSrc = imgSrc;
            }
            if ('' != imageSrc) {
                $('#imageSrc').val(imageSrc);//商品图片
            }
        }else{
            $.messager.alert('提示',"商铺logo未传");
            return;
        }
        
		if($("#addForm").form('validate')){
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage/create")
  				 .attr("method", "POST")
  				 .submit();
  		}
		
	});
	
	$("#up_taxLicense").on('change', function() {
		var formData = new FormData();
		formData.append('file', $('#up_taxLicense')[0].files[0]);
		upload("img2", formData);
	});
	
	$("#up_organization").on('change', function() {
		var formData = new FormData();
		formData.append('file', $('#up_organization')[0].files[0]);
		upload("img3", formData);
	});
	
	$("#up_bussinessLicenseImage").on('change', function() {
		var formData = new FormData();
		formData.append('file', $('#up_bussinessLicenseImage')[0].files[0]);
		upload("img4", formData);
	});
	
	<#--鼠标移入移出图片-->
    $('.img').live('mouseover', function () {
        $(this).find('.img-box').show();
    }).live('mouseout', function () {
        $(this).find('.img-box').hide();
    })
    <#--删除图片-->
    $('.del-img1').live('click', function () {
        $(this).parent().parent().parent().remove();
        $('[name=fileIndex]').val($('[name=fileIndex]').val() - 1);
        if ($('[name=fileIndex]').val() == 0) {
            $('#previewImgBox').hide();
        }
    });
    
    $('.del-img2').live('click', function () {
        $(this).parent().parent().parent().remove();
        $("#img2").css("display", "none");
    });
    $('.del-img3').live('click', function () {
        $(this).parent().parent().parent().remove();
        $("#img3").css("display", "none");
    });
    $('.del-img4').live('click', function () {
        $(this).parent().parent().parent().remove();
        $("#img4").css("display", "none");
    });
});
	
	function upload(index, formData) {
		$.ajax({
			url:"/admin/file/uploadFile",
			type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
            	console.log(result);
            	var html = '';
            	html += '<li><div class="img" style="left:100px;height: 21px; float: left;line-height: 150px;">';
            	html += '<img id="prev_0" name="prev_0" src=' + result.data.url + ' width="250" height="150">';
            	html += '<div class="img-box" style="width:250px;height:150px;">';
            	html += '<a class="del-img del-' + index + '" style="top:2px;left:110px;" href="javascript:void(0);">删除</a>';
            	html += '</div></li>';
            	$("#" + index).html(html);
            	$("#" + index).css("display", "block");
            }
		});
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">商家信息修改<span class="s-poar"></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>店铺及联系人信息</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>店铺名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="sellerName" name="sellerName" value="${(seller.sellerName)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>店主名称：</label>
							<input class="easyui-validatebox txt w280" type="text" id="name" name="name" value="${(seller.name)!''}" data-options="required:true,validType:'length[0,50]'" >
						</p>
					</div>
					</br>
					<div class="fluidbox">
					  <dd class="dd-group">
	                    <span class="s-showbtn">
	                    <label class="lab-item"><font class="red">*</font>店铺logo：</label>
	                        <div name="uploadImg" action=""  multiparam='{"url":"/admin/file/uploadPics","validate":{"fileSize":{"value":2048000,"errMsg":"上传图片不允许超过2M"}, "fileMaxNum":{"value":1,"errMsg":"logo只允许上传一张"},"fileType":{"value":"img","errMsg":"上传图片后缀只支持:image、gif、jpeg、jpg、png"}},"callback":"callback1"}' class="upload-img">
	                            <a href="#" class="txt_white">添加图片</a>
	                            <input type="hidden" name="fileIndex" value="0"/>
	                        </div>
	                        <div class="fluidbox">
		                        <p class="p12 p-item">
		                            <label class="lab-item">&nbsp;</label>
		                            <font style="color: #808080">
		                                图片建议像素（或保持该比例）：宽800，高800
		                            </font>
		                        </p>
		                    </div>
	                    </span>
                	</dd>
	                <!-- 预览图片 -->
	                <dd id='previewImgBox' style="display: none">
	                    <input type="hidden" id="imageSrc" name="imageSrc"/>
	                    <ul class='preview-img' id="preview-img">
	                        <li style="display: none" id="prewtemplage">
	                            <div class='img'>
	                                <img width='150' height='150'>
	                                <div class='img-box'>
	                                    <a class='del-img del-img1' href='javascript:void(0);'>删除</a>
	                                </div>
	                            </div>
	                        </li>
	                    </ul>
	                </dd>
					</div>
				</dd>
			</dl>
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>营业执照信息（副本）</dt>
				<dd class="dd-group">
					<div class="fluidbox">
					
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>税务登记证：</label>
							<input type="file" id="up_taxLicense" name="up_taxLicense" value="${(sellerApply.taxLicense)!''}"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" data-options="required:true" />
						<ul class="preview-img" id="img2" style="display: none">
						</ul>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>组织机构代码证：</label>
							<input type="file" id="up_organization" name="up_organization" value="${(sellerApply.organization)!''}"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" data-options="required:true" />
							<ul class="preview-img" id="img3" style="display: none">
							</ul>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>营业执照：</label>
							<input type="file" id="up_bussinessLicenseImage" name="up_bussinessLicenseImage"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" data-options="required:true" />
							<ul class="preview-img" id="img4" style="display: none">
							</ul>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">&nbsp;</label>
							<font style="color: #808080">
							请确保图片清晰，文字可辨并有清晰的红色公章。
							</font>
						</p>
					</div>
					<br/>
				</dd>
			</dl>
			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="更新" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>



<#include "/admin/commons/_detailfooter.ftl" />