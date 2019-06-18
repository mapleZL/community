<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage"/>
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("SHARE_TASK_STATUS")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});


        $('#btn_udpate').click(function() {
            var selected = $('#dataGrid').datagrid('getSelected');
            if (!selected) {
                $.messager.alert('提示', '请选择操作行。');
                return;
            }

            $("#devWin").window({
                width :400,
                height : 210,
                href : '${domainUrlUtil.EJS_URL_RESOURCES}/admin/overtime/system/detail?id=' + selected.id+ "&type=2",
                title : "选择维修人员",
                closed : true,
                shadow : false,
                modal : true,
                collapsible : false,
                minimizable : false,
                maximizable : false
            }).window('open');
        });


        $('#btn_add').click(function() {
            var selected = $('#dataGrid').datagrid('getSelected');
            // if (!selected) {
            //     $.messager.alert('提示', '请选择操作行。');
            //     return;
            // }

            $("#devWin").window({
                width :400,
                height : 210,
                href : '${domainUrlUtil.EJS_URL_RESOURCES}/admin/overtime/addTime',
                title : "选择维修人员",
                closed : true,
                shadow : false,
                modal : true,
                collapsible : false,
                minimizable : false,
                maximizable : false
            }).window('open');
        });

        // 通过信息
        $('#btn_del').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sts == '删除'){
                $.messager.alert('提示','已删除。');
                return;
            }
            $.messager.confirm('确认', '确定删除该申请吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/overtime/system/delelte",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=3",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload');
                            } else {
                                $.messager.alert('提示',"删除失败!");
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
					<p class="p4 p-item" hidden = "hidden">
						<label class="lab-item">发布状态 :</label> <@cont.select id="status"
						codeDiv="SHARE_TASK_STATUS" name="q_status" style="width:100px"/>
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
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/overtime/system/getVisitorOverTime'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="overTime" width="30" align="center">限制时间</th>
				<th field="createName" width="30" align="center">发布人</th>
				<th field="createTime" width="30" align="center">创建时间</th>
				<th field="sts" width="30" align="center">状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
         <@shiro.hasPermission name="/admin/vistime/add">
                <a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
         </@shiro.hasPermission>
         <@shiro.hasPermission name="/admin/vistime/udpate">
                <a id="btn_udpate" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">修改</a>
         </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/vistime/del">
                <a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
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
