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
		
		$('#btn_edit').click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$("#editWin").window({
				width : 600,
				height : 295,
				href : "${currentBaseUrl}/edit?id="+selected.id,
				title : "修改",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});
		
		// 删除信息
		$('#btn_freeze').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		$.messager.confirm('确认', '确定删除该条发布信息吗？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/deleteShareInfo",
						dataType: "json",
					    data: "id=" + selected.id,
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

		// 查看详情跳转
        $("#btn-gridDetail").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/share/system/getShareInfoDetail?id="+selectedCode.id ;
        });

        // 解冻商家
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
						<label class="lab-item">发布状态 :</label> <@cont.select id="status"
						codeDiv="SHARE_TASK_STATUS" name="q_status" style="width:100px"/>
					</p>
                    <p class="p4 p-item">
                        <label class="lab-item">发布类型 :</label> <@cont.select id="q_taskType"
						codeDiv="SHARE_TASK_TYPE" name="q_taskType" style="width:100px"/>
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
            <th field="classify" width="120" align="center">环境分类</th>
            <th field="title" width="150" align="center">标题</th>
            <th field="content" width="150" align="center">内容</th>
            <th field="createTime" width="150" align="center">发布时间</th>
            <th field="imgUrl" width="40" align="center" formatter="imageFormat">图片</th>
            <th field="status" width="150" align="center">上传状态</th>
            <th field="sts" width="150" align="center">有效状态</th>
        </tr>
        </thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/share/deleteShareInfo">
		<a id="btn_freeze" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">删除</a>
		</@shiro.hasPermission>
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a id="btn-gridDetail" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查看详情</a>
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
