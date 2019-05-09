<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/productattr/"/>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>

<script language="javascript">
    $(function () {
        var backUrl = "${currentBaseUrl}";
        var options = {
            url: '${currentBaseUrl}edit',
            type: 'post',
            success: function (data) {
            	$.messager.alert('提示','保存成功。');
                window.location.href = backUrl;
            }
        };
        $("#back").click(function () {
            window.location.href = backUrl;
        });
        $("#edit").click(function () {
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
        <h2 class="h2-title">商品属性列表
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>

        <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <#if productTypeAttr1?? && productTypeAttr1?size &gt; 0>
                <dl class="dl-group" style="margin:20px 10px 10px 10px">
                    <dt class="dt-group"><span class="s-icon"></span>商品属性</dt>
                    <input type="hidden" id="custAttr" name="custAttr"/>
                        <dd class="dd-group">
                            <div class="fluidbox">
		                    <#list productTypeAttr1 as productTypeAttr1>
			                	<#if productTypeAttr1_index % 2 == 0>
			                    </div>
			                    <div class="fluidbox">
			                	</#if>
			                	<p class="p6 p-item">
			                	<label class="lab-item">${(productTypeAttr1.name)!''}:</label>
			                       <input name="type_${(productTypeAttr1.id)!''}" data="${(productTypeAttr1.id)!''}" value="${(productTypeAttr1.value)!''}" class="txt w600"/>
			                    </p>
		                    </#list>
                            </div>
                        </dd>
                 </dl>
                </#if>
                <#if productTypeAttr2?? && productTypeAttr2?size &gt; 0>
                <dl class="dl-group" style="margin:20px 10px 10px 10px">
                    <dt class="dt-group"><span class="s-icon"></span>自定义属性</dt>
                    <input type="hidden" id="custAttr" name="custAttr"/>
                        <dd class="dd-group">
                            <div class="fluidbox">
		                    <#list productTypeAttr2 as productTypeAttr2>
			                	<#if productTypeAttr2_index % 2 == 0>
			                    </div>
			                    <div class="fluidbox">
			                	</#if>
			                	<p class="p6 p-item">
			                	<label class="lab-item">${(productTypeAttr2.name)!''}:</label>
			                       <input name="type_${(productTypeAttr2.id)!''}" data="${(productTypeAttr2.id)!''}" value="${(productTypeAttr2.value)!''}" class="txt w600"/>
			                    </p>
		                    </#list>
                            </div>
                        </dd>
                 </dl>
                 </#if>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>帮助</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <label class="lab-item" style="width: 100%; text-align: left;">
                        	1.此页面仅供商品属性的修改，不提供新增和删除功能。
                       	</label>
                    </div>
                </dd>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <label class="lab-item" style="width: 100%; text-align: left;">
                        	2.新加的属性请用“,”号隔开（半角英文逗号）。
                        </label>
                    </div>
                </dd>
            </dl>

            <#--2.batch button-------------->
            <p class="p-item p-btn">
                <input type="button" id="edit" class="btn" value="修改"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />