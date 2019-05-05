<#include "/seller/commons/_detailheader.ftl" />

<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/"/>
<script language="javascript">
    $(function () {
//         $("[name=uploadImg]").multiupload();
        var backUrl = "${currentBaseUrl}";
        var options = {
            url: '${currentBaseUrl}add',
            type: 'GET',
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
            var parentId = $('select[level]').last().val();
            if(parentId){
            	
            }else{
            	$.messager.alert('提示','请选择具体的分类。');
            	return;
            }
            if(!parentId){
                parentId = $('select[level]').last().prev().val();
            }
            if('' == parentId || -1 == parentId){
                parentId = 0;
            }
            if(parentId == 0){
                $.messager.alert('提示','请选择具体的分类。');
                return;
            }
            $('#cateId').val(parentId);
            $("#addForm").submit();
        });

        $("select[name^='parentId_']").live("click", function(){
            var level = $(this).attr("level");
            //控制最多只有三级分类
            //if(level == 1)
                //return;
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
                url: "${currentBaseUrl}getCateById?id=" + parentId,
                dataType: "json",
                cache:false,
                success:function(data){
                    if (data && data.success && data.rows && data.rows.length > 0) {
                        var children = "<select id='parentId_" + parseInt(level +1) + "' name='parentId_"+parseInt(level +1)+"' level="+parseInt(level +1) +" class='w240' size='18'>";
                        //children += "<option value=''>请选择</option>";
                        $.each(data.rows, function(i, n){
                            children += "<option value=" + n.id + ">" + n.name + "</option>";
                        })
                        children += "</select>"
                        $('#'+id).after('&nbsp;&nbsp;&nbsp;' +children);
                    }
                }
            });
        });

    })

</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">发布商品-选择分类
            <#--<span class="s-poar">-->
                <#--<a class="a-back" href="${currentBaseUrl}">返回</a>-->
            <#--</span>-->
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="get" class="validForm" 
        	id="addForm" name="addForm" action="${currentBaseUrl}/add">
            <input type="hidden" id="cateId" name="cateId" />
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                <dd class="dd-group">
                    <#if cate?? && cate?size &gt; 0>
                    <div class="fluidbox" style="padding-left: 215px">
                        <p class="p12 p-item">
                            <select id="parentId_0" name="parentId_0" level="0" class="w240" size="18">
                                <#list cate as cc>
                                    <option value="${(cc.id)!''}"> ${(cc.name)!''}</option>
                                </#list>
                            </select>
                        </p>
                    </div>
                    </#if>
                </dd>
            </dl>

            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>提示</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <label>&nbsp;&nbsp;&nbsp;选择分类没有分类信息,请先申请可以经营的商品分类,具体路径:[商品管理]-[商品分类申请]。</label><br/>
                        <br/><br/>
                    </div>
                </dd>
            </dl>
            <#if cate?? && cate?size &gt; 0>
            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="下一步"/>
            </p>
            </#if>
        </@form.form>
        </div>
    </div>
</div>

<#include "/seller/commons/_detailfooter.ftl" />



