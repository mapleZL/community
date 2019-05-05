<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/brand/"/>
<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("SELLER_STATE")})');
    </#noescape>

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-gridAdd").click(function(){
            window.location.href="${currentBaseUrl}/add";
        });
        $("#a-gridEdit").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            //提交审核的品牌不允许在编辑
            if ( 1 == selectedCode.state){
                $.messager.alert('提示','该品牌已经提交审核，不允许在进行编辑。');
                return;
            }
            if(2 == selectedCode.state){
                $.messager.alert('提示','显示中的品牌，不允许在进行编辑。');
                return;
            }
            window.location.href="${currentBaseUrl}edit?id="+selectedCode.id;
        });

        $("#a-gridRemove").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }

            //提交审核、显示中的品牌不允许删除
            if (1 == selectedCode.state){
                $.messager.alert('提示','该品牌已经提交审核，不允许删除。');
                return;
            }
            if(2 == selectedCode.state){
                $.messager.alert('提示','显示中的品牌，不允许删除。');
                return;
            }

            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selectedCode);
            $.messager.confirm('Confirm', '确定删除吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${currentBaseUrl}del",
                        dataType: "json",
                        data: "id="+selectedCode.id+"&"+getCSRFTokenParam(),
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('deleteRow',selectedIndex);
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            refrushCSRFToken(data.csrfToken);
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });

        $('#a-gridCommit').click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }

            var selectedIndex = $('#dataGrid').datagrid('getRowIndex', selectedCode);
            $.messager.confirm('Confirm', '确定提交审核吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${currentBaseUrl}commit",
                        dataType: "json",
                        data: "id="+selectedCode.id+"&"+getCSRFTokenParam(),
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('deleteRow',selectedIndex);
                            }else{
                                $.messager.alert('提示', data.message);
                            }
                            refrushCSRFToken(data.csrfToken);
                            $.messager.progress('close');
                            $('#dataGrid').datagrid('reload',queryParamsHandler());
                        }
                    });
                }
            });
        });
    })

    function imageFormat(value,row,index){
        return "<a href='${domainUrlUtil.EJS_IMAGE_RESOURCES}" + value + "' target='_blank'><font color='0227A9'>${domainUrlUtil.EJS_IMAGE_RESOURCES}"+value+"</font></a>";
    }
    function stateFormat(value,row,index){
        return codeBox["SELLER_STATE"][value];
    }

</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">我的品牌 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox"><!-- 不分隔 -->
                    <p class="p4 p-item">
                        <label class="lab-item">品牌名称 :</label>
                        <input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">状态 :</label>
                        <@cont.select id="q_state" name="q_state" value="${(brand.state)!''}" codeDiv="SELLER_STATE" mode="1"/>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>

<#--2.datagrid----------------->
<div data-options="region:'center'" border="false">
    <table id="dataGrid" class="easyui-datagrid"
           data-options="rownumbers:true
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="name" width="150" align="center">品牌名称</th>
            <th field="nameFirst" width="100" align="center">首字母</th>
            <th field="image" width="150" align="center" formatter="imageFormat">图片地址</th>
            <th field="state" width="110" align="center" formatter="stateFormat">状态</th>
            <th field="createUser" width="110" align="center">创建人</th>
            <th field="createTime" width="170" align="center">创建时间</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        
		<@shiro.hasPermission name="/seller/product/brand/add">
        <a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        </@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/product/brand/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        </@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/product/brand/del">
        <a id="a-gridRemove" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/product/brand/commit">
        <a id="a-gridCommit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">提交审核</a>
        </@shiro.hasPermission>
        
     </div>
</div>
<#include "/seller/commons/_detailfooter.ftl" />