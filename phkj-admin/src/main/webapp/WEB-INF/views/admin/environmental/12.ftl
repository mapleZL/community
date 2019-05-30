<#include "/admin/commons/_detailheader.ftl" />
<script language="javascript">
	var codeBox;
	var workdayBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("REPAIRE_MEMBER_STATE")})');
		workdayBox = eval('(${initJSCodeContainer("SCHEDULING_DAY")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		// 使用账号
		$('#btn_use').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			// 判断是否是已经启动
			if(selected.sts == 2){
				$.messager.alert('提示','该条申请已处理,请不要重复操作。');
				return;
			}
	 		$.messager.confirm('确认', '确定使用该维修人员', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/repair/member/enable",
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
		
		$("#a-gridAdd").click(function(){
	 		window.location.href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/repair/member/add";
		});
		
		// 禁用账号
		$('#btn_forbidden').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			// 判断是否是已经审核通过的数据
			if(selected.status == 3){
				$.messager.alert('提示','该条申请已处理,请不要重复操作。');
				return;
			}
	 		$.messager.confirm('确认', '确定禁用该维修人员？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/repair/member/forbidden",
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
		
	});

	function getState(value, row, index) {
		var box = codeBox["REPAIRE_MEMBER_STATE"][value];
		return box;
	}
	
	function getWorkDay(value, row, index) {
		var box = workdayBox["SCHEDULING_DAY"][value];
		return box;
	}
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		维修人员列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">维修人员姓名 :</label> <input type="text"
							class="txt" id="q_user_name" name="q_user_name" value="${q_user_name!''}" />
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
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/environ/getSystemAllEnviron'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="classify" width="120" align="center">环境分类</th>
				<th field="title" width="150" align="center">标题</th>
				<th field="content" width="70" align="center" formatter="getWorkDay">描述</th>
				<th field="sts" width="70" align="center" formatter="getState">状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
	<@shiro.hasPermission name="/admin/repair/member/add">
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/repair/member/enable">
		<a id="btn_use" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">使用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/repair/member/forbidden">
		<a id="btn_forbidden" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">禁用</a>
		</@shiro.hasPermission>
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
	
</div>
<#include "/admin/commons/_detailfooter.ftl" />
