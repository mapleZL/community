<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/"/>

<#include "productimgcss.ftl"/>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/skupicupload.js"></script>
<script language="javascript">
    $(function () {
        <#--加载图片上传控件-->
        $("[name=uploadImg]").multiupload();
        var backUrl = "${currentBaseUrl}";
        var options = {
            url: '${currentBaseUrl}create',
            type: 'post',
            success: function (data) {
                if (data && null != data.success && data.success == true) {
                    alert("活动发布成功");
                    window.location.href='${domainUrlUtil.EJS_URL_RESOURCES}/admin/event/add';
                }else if(data.backUrl != null){
                    alert(data.message);
                    window.location.href=data.backUrl;
                }else{
                	$("#add").removeAttr("disabled");
                    refrushCSRFToken(data.csrfToken);
                    $.messager.alert('提示',data.message);
                }
                refrushCSRFToken(data.csrfToken);
            }
        };

        $("#add").click(function () {
            var title = $('#title').val();
            var postBegin = $('#postBegin').val();
            var postEnd = $('#postEnd').val();

            var description = UM.getEditor('myEditor').getContent();
            if(description == ''){
                $.messager.alert('提示',"请填写商品描述");
                return;
            }
            $('#content').val(description);//商品描述信息
			
            <#--商品图片-->
            var image = $('img[name^=prev_]');
            if (image && image.length > 0) {
                var imageSrc = '';
                for (var i = 0; i < image.length; i++) {
                    var imgSrc = $(image[i]).attr('src');
                    if (imgSrc.indexOf("resources") != -1)
                        continue;
                    imageSrc += imgSrc + ';';
                }
                if ('' != imageSrc) {
                    $('#imageSrc').val(imageSrc);//商品图片
                }
            }else{
                $.messager.alert('提示',"商品图片,至少传一张");
                return;
            }

            if (postBegin == '') {
                $.messager.alert('提示', '开始时间必填。');
                return;
            }
            if (postBegin == '') {
                $.messager.alert('提示', '结束时间必填。');
                return;
            }
           
            if ($('#addForm').form('validate')) {
	            $(this).attr("disabled",true);
                $('#addForm').ajaxSubmit(options);
            }
            
        });

        <#--鼠标移入移出图片-->
        $('.img').live('mouseover', function () {
            $(this).find('.img-box').show();
        }).live('mouseout', function () {
            $(this).find('.img-box').hide();
        })
        <#--删除图片-->
        $('.del-img').live('click', function () {
            $(this).parent().parent().parent().remove();
            $('[name=fileIndex]').val($('[name=fileIndex]').val() - 1);
            if ($('[name=fileIndex]').val() == 0) {
                $('#previewImgBox').hide();
            }
        })
        
    });
    function callback1(result) {
    }
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">发布商品-填写商品详细信息
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>热门活动发布</dt>
                    <!-- 状态默认是新建 -->
                    <input type="hidden" id="state" name="state" value="1"/>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>标题: </label>
                            <input type="text" id="title" name="title" value="${(event.title)!''}" class="txt w300 easyui-validatebox" missingMessage="活动标题" data-options="required:true,validType:'length[3,100]'"/>
                        </p>
                        <input type="hidden" id="id" name="id" value="${(event.id)!''}"/>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>下发方: </label>
                            <input type="text" id="sourceType" name="sourceType" value="${(event.sourceType)!''}" class="txt w300 easyui-validatebox" missingMessage="下发方" data-options="required:true,validType:'length[3,100]'"/>
                        </p>
                    </div>
					
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>开始时间: </label>
                            <input type="text" id="postBegin" name="postBegin" value="${(event.postBegin)!''}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w300" data-options="required:true"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>结束时间: </label>
                            <input type="text" id="postEnd" name="postEnd" value="${(event.postEnd)!''}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w300" data-options="required:true"/>
                        </p>
                    </div>

                </dd>
            </dl>
            </dd>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>活动正文</dt>
                <input type="hidden" id=content name="content"/>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>正文: </label>
                        <div style="padding-left: 140px;padding-top: 2px;"><#include "productdesc.ftl"/></div>
                        </p>
                    </div>
                </dd>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>活动图片</dt>
                <dd class="dd-group">
                    <span class="s-showbtn">
                        <div name="uploadImg" action="" index="${waitShow_index!''}" multiparam='{"url":"/admin/file/uploadPics","validate":{"fileSize":{"value":2048000,"errMsg":"上传图片不允许超过2M"}, "fileMaxNum":{"value":5,"errMsg":"上传图片最多不能超过5张"},"fileType":{"value":"img","errMsg":"上传图片后缀只支持:image、gif、jpeg、jpg、png"}},"callback":"callback1"}' class="upload-img">
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
                                    <a class='del-img' href='javascript:void(0);'>删除</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </dd>
                <!-- end -->
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>帮助</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <label class="lab-item">帮助信息。</label>
                    </div>
                </dd>
            </dl>

            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="新增"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />