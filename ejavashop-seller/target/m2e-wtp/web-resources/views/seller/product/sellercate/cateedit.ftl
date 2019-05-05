<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/sellerCate/"/>
<script language="javascript">
    $(function () {
        var backUrl = "${currentBaseUrl}";
        var options = {
            url: '${currentBaseUrl}update',
            type: 'post',
            success: function (data) {
                if (data && null != data.success && data.success == true) {
                    window.location.href=backUrl;
                }else if(data.backUrl != null){
                    alert(data.message);
                    window.location.href=data.backUrl;
                }else{
                    refrushCSRFToken(data.csrfToken);
                    alert(data.message);
                }
            }
        };
        $("#back").click(function () {
            window.location.href = backUrl;
        });
        $("#add").click(function () {
            if($('#addForm').form('validate')){
                //上级分类赋值
                var parentId = $('select[level]').last().val();
                if('' == parentId){
                    parentId = $('select[level]').last().prev().val();
                }
                if('' == parentId || -1 == parentId){
                    parentId = 0;
                }
                $('#pid').val(parentId);
                $('#addForm').ajaxSubmit(options);
            }
        });

        $('#selectType').click(function(){
            $('#typeDlg').dialog('open');
        });

        <#if message??>$.messager.progress('close');alert('${message}');</#if>
        <#--
        //上级分类
        $("select[name^='parentId_']").live("change", function(){
            var level = $(this).attr("level");
            //控制最多只有三级分类
            if(level == 1)
                return;
            var id = $(this).attr("id");
            var parentId = $(this).val();

            $("select[name^='parentId_']").each(function(){
                var subLevel = $(this).attr("level");
                if (parseInt(subLevel) > parseInt(level)) {
                    $(this).remove();
                }
            })
            $.ajax({
                type:"get",
                url: "${currentBaseUrl}getByPid?id=" + parentId,
                dataType: "json",
                cache:false,
                success:function(data){
                    if (data && data.length > 0) {
                        var children = "<select id='parentId_" + parseInt(level +1) + "' name='parentId_"+parseInt(level +1)+"' level="+parseInt(level +1) +" class='w210'>";
                        children += "<option value=''>请选择</option>";
                        $.each(data, function(i, n){
                            children += "<option value=" + n.id + ">" + n.name + "</option>";
                        })
                        children += "</select>"
                        $('#'+id).after('&nbsp;&nbsp;&nbsp;' + children);
                    }
                }
            });
        });-->
    });
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">店铺商品分类编辑
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <input type="hidden" name="id" id="id" value="${(cate.id)!''}"
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>分类名称: </label>
                            <input type="text" id="name" name="name" value="${(cate.name)!''}" class="txt w200 easyui-validatebox" missingMessage="分类名称必填，2-20个字符" data-options="required:true,validType:'length[2,20]'"/>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>上级分类: </label>
                            <input type="hidden" name="pid" id="pid"/>
                            <#if parentCate?? && parentCate?size&gt;0>
                            <#--大类-->
                            <#if parentCate.B?? && parentCate.B?size&gt;0>
                            <select id="parentId_0" name="parentId_0" level="0" class="w210" disabled>
                                <option value="-1">无</option>
                                <#if parentCate.B.cateList?? && parentCate.B.cateList?size&gt;0>${parentCate.B.cateId}
                                    <#list parentCate.B.cateList as productCate>
                                        <option value="${productCate.id}" <#if productCate.id?string == parentCate.B.cateId?string>selected</#if>>${productCate.name}</option>
                                    </#list>
                                </#if>
                            </select>
                            </#if>
                            <#--中类-->
                            <#if parentCate.M?? && parentCate.M?size&gt;0>
                                <select id="parentId_0" name="parentId_1" level="1" class="w210" disabled>
                                    <option value="-1">无</option>
                                    <#if parentCate.M.cateList?? && parentCate.M.cateList?size&gt;0>${parentCate.M.cateId}
                                        <#list parentCate.M.cateList as productCate>
                                            <option value="${productCate.id}" <#if productCate.id?string == parentCate.M.cateId?string>selected</#if>>${productCate.name}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </#if>
                            <#--小类-->
                            <#if parentCate.S?? && parentCate.S?size&gt;0>
                            <select id="parentId_0" name="parentId_2" level="2" class="w210" disabled>
                                <option value="-1">无</option>
                                <#if parentCate.S.cateList?? && parentCate.S.cateList?size&gt;0>${cateList.S.cateId}
                                    <#list parentCate.S.cateList as productCate>
                                        <option value="${productCate.id}" <#if productCate.id?string == parentCate.S.cateId?string>selected</#if>>${productCate.name}</option>
                                    </#list>
                                </#if>
                            </select>
                            </#if>
                            <#else>
                            <select id="parentId_0" name="parentId_0" level="0" class="w210" disabled>
                                <option value="-1">无</option>
                                <#if B?? && B?size&gt; 0>
                                    <#list B as productCate>
                                        <option value="${productCate.id}">${productCate.name}</option>
                                    </#list>
                                </#if>
                            </select>
                            </#if>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                选择当前分类的上级分类，如需添加一级分类，请选择“无”。
                            </font>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>顺序位: </label>
                            <input type="text" id="sort" name="sort" value="${(cate.sort)!''}" class="txt w200 easyui-numberbox" data-options="min:1,max:999,required:true" missingMessage="顺序位必填，1-999"/>
                        </p>
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
                <input type="button" id="add" class="btn" value="编辑"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>

<#include "/seller/commons/_detailfooter.ftl" />