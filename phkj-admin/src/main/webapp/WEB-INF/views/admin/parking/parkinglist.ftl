<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" 
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("REPAIRE_RECORD_STATE")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});

        // 添加
        $('#btn_add').click(function() {
            $("#devWin").window({
                width :700,
                height : 500,
                href : '${domainUrlUtil.EJS_URL_RESOURCES}/admin/payment/system/add',
                title : "添加支付类型",
                closed : true,
                shadow : false,
                modal : true,
                collapsible : false,
                minimizable : false,
                maximizable : false
            }).window('open');
        });
	 		
		$("#newstypeWin").window({
				width : 750,
				height : 500,
				title : "报修图片",
				closed : true,
				shadow : false,
				modal : true,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});

		// 通过信息
        $('#btn_pass').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sts == '未通过'){
                $.messager.alert('提示','已拒绝无法通过。');
                return;
            }
            if(selected.sts == '删除'){
                $.messager.alert('提示','已删除无法通过。');
                return;
			}
            if(selected.sts == '已到期'){
                $.messager.alert('提示','已到期无法通过。');
                return;
            }
            if(selected.sts == '已通过'){
                $.messager.alert('提示','已审核。');
                return;
            }
            $.messager.confirm('确认', '确定通过该申请吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/parking/system/update",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=1",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload');
                            } else {
                                $.messager.alert('提示',"通过失败!");
                                $('#dataGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });

        // 拒绝信息
        $('#btn_stop').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sts == '已通过'){
                $.messager.alert('提示','已通过审核无法拒绝申请。');
                return;
            }
            if(selected.sts == '删除'){
                $.messager.alert('提示','已删除无法拒绝申请。');
                return;
            }
            if(selected.sts == '已到期'){
                $.messager.alert('提示','已到期无法通过。');
                return;
            }
            if(selected.sts == '未通过'){
                $.messager.alert('提示','已审核。');
                return;
            }
            $.messager.confirm('确认', '确定拒绝该申请吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/parking/system/update",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=2",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload');
                            } else {
                                $.messager.alert('提示',"通过失败!");
                                $('#dataGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });

        // 通过信息
        $('#btn_del').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sts == '已通过'){
                $.messager.alert('提示','已通过审核无法拒绝申请。');
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
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/parking/system/update",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=3",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#dataGrid').datagrid('reload');
                            } else {
                                $.messager.alert('提示',"通过失败!");
                                $('#dataGrid').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });


	});
	
	function imageFormat(value, row, index) {
		return "<a class='newstype_view' onclick='showimg($(this).attr(\"imgpath\"));' href='javascript:;' imgpath='"
				+ value + "'>点击查看</a>";
	}
	
	function showimg(href) {
		if (href && href != 'null') {
			var imgs = JSON.parse(href);
			var html = '';
			for (var i = 0; i < imgs.length; i++) {
				html += "<img src='" + imgs[i] + "' >"
			}
			$("#newstypeTree").html(html);
			$("#newstypeWin").window('open');
		} else {
			$.messager.alert('提示','该条记录暂无图片。');
			return;
		}
	}

	function getState(value, row, index) {
		var box = codeBox["REPAIRE_RECORD_STATE"][value];
		return box;
	}
</script>

<div id="devWin"></div>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		租用管理 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">申请状态 :</label> <@cont.select id="status"
                    codeDiv="PARKING_STATUS" name="q_status" style="width:100px"/>
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
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/parking/system/getSystemAll'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="carNum" width="70" align="center">车牌号</th>
                <th field="price" width="70" align="center">租用价格</th>
                <th field="startTime" width="70" align="center">开始时间</th>
                <th field="endTime" width="90" align="center">到期时间</th>
                <th field="sts" width="70" align="center">状态</th>
                <th field="remarks" width="70" align="center">备注</th>
                <th field="createTime" width="90" align="center">申请时间</th>
                <th field="createUserName" width="70" align="center">申请人</th>
                <th field="examineName" width="70" align="center">审核人</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
        <a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/parkingn/pass">
		<a id="btn_pass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">通过</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/parking/stop">
		<a id="btn_stop" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">拒绝</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/parking/del">
		<a id="btn_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
	
</div>
<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
