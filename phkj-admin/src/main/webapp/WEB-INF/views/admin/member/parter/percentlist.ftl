
<#include "/admin/commons/_detailheader.ftl" />
<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
$(function(){
	$('#a-percentEdit').click(function (){
		var selected = $('#dataGrid').datagrid('getSelected');
		if (!selected) {
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
		$("#percentEdit").window({
			width : 400,
			height : 410,
			title : "设置佣金",
			href : "${currentBaseUrl}/percentEdit?id="+selected.id,
			modal : true,
			shadow : false,
			collapsible : false,
			minimizable : false,
			maximizable : false
		});
	});
	
	$('#a-percentDelete').click(function (){
		var selected = $('#dataGrid').datagrid('getSelected');
		if (!selected) {
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
		$.messager.confirm('删除', '确定删除该条记录吗？', function(r){
			if (r){
				$.ajax({
					type:"POST",
					url: "${currentBaseUrl}/percentDelete",
					dataType: "json",
					data: {parterPercentId:selected.id},
					cache:false,
					success:function(data, textStatus){
						if (data.success) {
							$.messager.alert('提示','修改成功。');
							$('#dataGrid').datagrid('reload',queryParamsHandler());
							return;
						} else {
							$.messager.alert("提示",data.message);
						}
					}
				});
			}
		});
	});
})
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">合伙人佣金列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox">
				<p class="p4 p-item">
					<label class="lab-item">合伙人 :</label>
					<input type="text" class="txt" id="q_memberName" name="q_memberName" value="${q_memberName!''}"/>
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
						,fitColumns:false
						,collapsible:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/percentlistall'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" width="50" align="center" halign="center">id</th>
				<th field="parterSignId" width="50" align="center" halign="center">合伙人id</th>  
				<th field="memberRealName" width="100" align="center" halign="center">合伙人</th>  
				<th field="percentType" width="100" align="center" halign="center">佣金类型</th>  
				<th field="signNo" width="150" align="center" halign="center">合同编号</th>  
	            <th field="startTime" width="200" align="center" halign="center">开始时间</th>  
	            <th field="endTime" width="200" align="center" halign="center">结束时间</th>  
	            <th field="type" width="60" align="center" halign="center" >袜类型</th>
	            <th field="percent" width="200" align="center" halign="center">佣金比例</th>  
	            <th field="createTime" width="150" align="center" halign="center">创建时间</th>  
	            <th field="updateTime" width="150" align="center" halign="center">更新时间</th>  
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<@shiro.hasPermission name="/admin/member/parter/percentEdit">
			<a id="a-percentEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/parter/percentDelete">
			<a id="a-percentDelete" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</@shiro.hasPermission>
	</div>
	<div id= "percentEdit"></div>
	
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />