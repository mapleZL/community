<#include "/admin/commons/_detailheader.ftl" />
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("MEMBER_PROPERY_STATE")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		// 审核通过
		$('#btn_pass').click(function () {
			debugger;
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			// 判断是否是已经审核通过的数据
			if(selected.sts != 1){
				$.messager.alert('提示','该条申请已处理,请不要重复操作。');
				return;
			}
	 		$.messager.confirm('确认', '确定审核通过该条申请吗', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"POST",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/notice/activity/changeSts",
						dataType: "json",
					    data: "id=" + selected.id + "&sts=2",
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
		
		// 审核不通过
		$('#btn_noPass').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
			// 判断是否是已经审核通过的数据
			if(selected.sts != 1){
				$.messager.alert('提示','该条申请已处理,请不要重复操作。');
				return;
			}
	 		$.messager.confirm('确认', '确定驳回该条申请吗？', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"POST",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/notice/activity/changeSts",
						dataType: "json",
					    data: "id=" + selected.id + "&sts=0",
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
		var box = codeBox["MEMBER_PROPERY_STATE"][value];
		return box;
	}
	
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		房屋信息列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">业主名 :</label> <input type="text"
							class="txt" id="q_member_name" name="q_member_name" value="${q_member_name!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">活动名称 :</label> <input type="text"
							class="txt" id="q_activity_title" name="q_activity_title" value="${q_activity_title!''}" />
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
    					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/notice/activity/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="memberName" width="50" align="center">申请人姓名</th>
				<th field="phone" width="60" align="center">申请人手机号</th>
				<th field="createTime" width="60" align="center">申请时间</th>
				<th field="activityTitle" width="120" align="center">活动标题</th>
				<th field="houseInfo" width="80" align="center">申请人房屋信息</th>
				<th field="sts" width="70" align="center" formatter="getState">状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
	<@shiro.hasPermission name="/notice/activity/changeSts">
		<a id="btn_pass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">审核通过</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/notice/activity/changeSts">
		<a id="btn_noPass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">审核不通过</a>
		</@shiro.hasPermission>
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
	
</div>
<#include "/admin/commons/_detailfooter.ftl" />
