<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/seo/"/>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>

<script language="javascript">
    $(function () {
        var backUrl = "${currentBaseUrl}";
        var options = {
            url: '${currentBaseUrl}update',
            type: 'post',
            success: function (data) {
            	$.messager.alert('提示','修改成功。');
                window.location.href = backUrl;
            }
        };
        $("#back").click(function () {
            window.location.href = backUrl;
        });
        $("#add").click(function () {
            if($('#addForm').form('validate')){
                $('#addForm').ajaxSubmit(options);
            }
        });
        <#if message??>$.messager.progress('close');
         $.messager.alert('提示','${message}');</#if>
    })
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">SEO详情编辑
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>

        <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>页面路径: </label>
                            <input type="text" id="path" name="path" class="txt w400" value="${(titleKeywordsDescription.path)!''}" data-options="required:true"/>
                        </p>
                        <p class="p6 p-item">
                    		<label class="lab-item"><font class="red">*</font>标题: </label>
                    		<input type="text" id="title" name="title" class="txt w400" value="${(titleKeywordsDescription.title)!''}" data-options="required:true"/>
                    	</p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>关键字: </label>
                            <input type="text" id="keywords" name="keywords" class="txt w400" value="${(titleKeywordsDescription.keywords)!''}" data-options="required:true"/>
                        </p>
                        <p class="p6 p-item">
                    		<label class="lab-item"><font class="red">*</font>描述: </label>
                    		<input type="text" id="description" name="description" class="txt w400" value="${(titleKeywordsDescription.description)!''}" data-options="required:true"/>
                    	</p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>应用页面: </label>
                            <input type="text" id="applyPage" name="applyPage" class="txt w400" value="${(titleKeywordsDescription.applyPage)!''}" data-options="required:true"/>
                        </p>
                    </div>
					<div>
						<input type ="hidden" id = "seo_id" name ="seo_id" value="${titleKeywordsDescription.id}">
					</div>
                </dd>
            </dl>

            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>帮助</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <label class="lab-item">帮助信息。</label>
                    </div>
                </dd>
            </dl>

            <#--2.batch button-------------->
            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="修改"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />