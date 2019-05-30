<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage"/>
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("SHARE_TASK_STATUS")})');
		</#noescape>

        $("#newstypeWin").window({
            width : 750,
            height : 420,
            title : "车辆图片",
            closed : true,
            shadow : false,
            modal : true,
            collapsible : false,
            minimizable : false,
            maximizable : false
        });
        // 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		// 删除信息
		$('#btn_freeze').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			if(selected.sts == '删除'){
                $.messager.alert('提示','已删除。');
                return;
            }
	 		$.messager.confirm('确认', '确定删除该条发布信息吗？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/environ/system/delete",
						dataType: "json",
					    data: "id=" + selected.id,
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

        // 确认接受
        $('#btn_confirm').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.status != '待处理'){
                $.messager.alert('提示','本条任务无法已被接收。');
                return;
            }
            $.messager.confirm('确认', '确定接收该条发布信息吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/environ/system/update",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=1",
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

        // 确认接受
        $('#btn_finish').click(function () {
            var selected = $('#dataGrid').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if (selected.sts == '删除' ){
                $.messager.alert('提示','本条任务已删除。');
                return;
            }
            if(selected.status != '处理中'){
                $.messager.alert('提示','本条任务无法办结完成。');
                return;
            }
            $.messager.confirm('确认', '确定办结该条发布信息吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/environ/system/update",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=2",
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


		// 查看详情跳转
        $("#btn-gridDetail").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/share/system/getShareInfoDetail?id="+selectedCode.id ;
        });

        // 删除商家
		$('#btn_unfreeze').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$.messager.confirm('确认', '确定解冻该商家吗？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/unfreeze",
						dataType: "json",
					    data: "sellerId=" + selected.id,
					    cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$('#dataGrid').datagrid('reload');
						    } else {
						    	$.messager.alert('提示',data.message);
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
                alert(imgs[i]);
                html += "<img src='" +  + "' >"
            }
            $("#newstypeTree").html(html);
            $("#newstypeWin").window('open');
        } else {
            $.messager.alert('提示','该条记录暂无图片。');
            return;
        }
    }

	function getState(value, row, index) {
		var box = codeBox["SELLER_AUDIT_STATE"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		共享列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">分类 :</label> <@cont.select id="q_type"
                    codeDiv="ENVIRON_TYPE" name="q_type" style="width:100px"/>
                    </p>
					<p class="p4 p-item">
						<label class="lab-item">有效状态 :</label> <@cont.select id="q_sts"
						codeDiv="ENVIRON_STS" name="q_sts" style="width:100px"/>
					</p>
                    <p class="p4 p-item">
                        <label class="lab-item">上传状态 :</label> <@cont.select id="q_status"
						codeDiv="ENVIRON_STATUS" name="q_status" style="width:100px"/>
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
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/environ/getSystemAllEnviron'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="id" hidden="hidden"></th>
            <th field="classify" width="70" align="center">环境分类</th>
            <th field="title" width="150" align="center">标题</th>
            <th field="content" width="100" align="center">内容</th>
            <th field="createTime" width="70" align="center">发布时间</th>
            <th field="imgUrl" width="50" align="center" formatter="imageFormat">图片</th>
            <th field="status" width="70" align="center">上传状态</th>
            <th field="sts" width="40" align="center">有效状态</th>
            <th field="examineName" width="70" align="center">审核人</th>
        </tr>
        </thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/environ/system/delete">
		    <a id="btn_freeze" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		    <a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <@shiro.hasPermission name="/admin/environ/system/update">
		    <a id="btn_confirm" href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-saved" plain="true">确认接收</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/admin/environ/system/update">
		    <a id="btn_finish" href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-saved" plain="true">办结完成</a>
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
