<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage"/>
<script language="javascript">
    var codeBox;
    $(function () {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("SHARE_TASK_STATUS")})');
        </#noescape>

        // 查询按钮
        $('#btn-gridSearch').click(function () {
            $('#dataGrid').datagrid('reload', queryParamsHandler());
        });


        $('#btn_udpate').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if (!selected) {
                $.messager.alert('提示', '请选择操作行。');
                return;
            }

            $("#devWin").window({
                width: 400,
                height: 210,
                href: '${domainUrlUtil.EJS_URL_RESOURCES}/admin/visnum/system/detail?id=' + selected.id + "&type=2",
                title: "修改访客次数",
                closed: true,
                shadow: false,
                modal: true,
                collapsible: false,
                minimizable: false,
                maximizable: false
            }).window('open');
        });


        $('#btn_add').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            // if (!selected) {
            //     $.messager.alert('提示', '请选择操作行。');
            //     return;
            // }

            $("#devWin").window({
                width: 400,
                height: 210,
                href: '${domainUrlUtil.EJS_URL_RESOURCES}/admin/visnum/addTime',
                title: "访客次数",
                closed: true,
                shadow: false,
                modal: true,
                collapsible: false,
                minimizable: false,
                maximizable: false
            }).window('open');
        });

        // 删除
        $('#btn_delete').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if (!selected) {
                $.messager.alert('提示', '请选择操作行。');
                return;
            }
            if (selected.sts == '删除') {
                $.messager.alert('提示', '已删除。');
                return;
            }
            $.messager.confirm('确认', '确定删除该申请吗？', function (r) {
                if (r) {
                    $.messager.progress({text: "提交中..."});
                    $.ajax({
                        type: "GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/visit/system/delete",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=3",
                        cache: false,
                        success: function (data, textStatus) {
                            if (data.success) {
                                $('#dataGrid').datagrid('reload');
                            } else {
                                $('#dataGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });






    });

    function getState(value, row, index) {
        var box = codeBox["SELLER_AUDIT_STATE"][value];
        return box;
    }
</script>

<div id="devWin"></div>


<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
     border="false">
    <h2 class="h2-title">
        时效管理 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
    </h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post"
                  id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item" >
                        <label class="lab-item">预约状态 :</label> <@cont.select id="sts"
                    codeDiv="VISITOR_STS" name="q_sts" style="width:100px"/>
                    </p>
                    <p class="p4 p-item" >
                        <label class="lab-item">预约类型 :</label> <@cont.select id="type"
                    codeDiv="VISITOR_TYPE" name="q_type" style="width:100px"/>
                    </p>
                </div>

            </form>
        </div>
    </div>
</div>

<div data-options="region:'center'" border="false">
    <table id="dataGrid" class="easyui-datagrid"
           data-options="rownumbers:true
						,idField :'id'
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:${pageSize}
						,fit:true
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/visit/system/getAlVisitor'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="id" hidden="hidden"></th>
            <th field="visitorName" width="30" align="center">预约人</th>
            <th field="gender" width="10" align="center">性别</th>
            <th field="telephone" width="30" align="center">电话</th>
            <th field="houseId" width="30" align="center">房屋编码</th>
            <th field="overNum" width="20" align="center">有效次数</th>
            <th field="visitorType" width="20" align="center">预约类型</th>
            <th field="passwordType" width="20" align="center">密码类型</th>
            <th field="overTime" width="30" align="center">到期时间</th>
            <th field="applyTime" width="30" align="center">预约时间</th>
            <th field="createTime" width="30" align="center">申请时间</th>
            <th field="sts" width="30" align="center">预约状态</th>
        </tr>
        </thead>
    </table>

    <div id="gridTools">
        <a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>

        <@shiro.hasPermission name="/admin/share/deleteShareInfo">
		    <a id="btn_delete" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
        </@shiro.hasPermission>
    </div>
    <div class="wrapper" id="editWin">

    </div>
</div>

<div id="newstypeWin">
    <form id="newstypeForm" method="post">
        <ul id="newstypeTree"
            style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
    </form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
