<#include "/admin/commons/_detailheader.ftl" />
<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
	var yesNoBox;
	$(function(){
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			yesNoBox = eval('(${initJSCodeContainer("PARTER_CODE")})');
		</#noescape>
		// 查询按钮
		$('#a-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		//设置佣金
		$('#a-percent').click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$("#makePercent").window({
				width : 600,
				height : 320,
				title : "设置佣金",
				href : "${currentBaseUrl}/percent?parterSignId="+selected.id,
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});
		//设置区域
		$('#a-selectAddress').click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$("#allotResourceWin").window({
				width : 400,
				height : 510,
				title : "分配区域",
				href : "${currentBaseUrl}/selectAddress?parterSignId="+selected.id,
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});
	});
	function yesNoFormat(value,row,index){
		return yesNoBox["PARTER_CODE"][value];
	}
	function goLinkedOrders(value,row,index){
		var parterId = ''+row.id;
	     return "<font style='color:blue;cursor:pointer' title='"+
                value+"' onclick='openwin(\""+parterId+"\")'>"+value+"</font>";
	}
	
	function openwin(parterId){
	        $("#ordersdialog").dialog({
				title:'合伙人佣金列表',
				width : 600,
				height : 320,
	            modal: true, 
                maximizable: true,
                resizable: true,
	            href: "${currentBaseUrl}/listParterPercent?parterId="+parterId
			});
    }
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
	<h2 class="h2-title">合伙人列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
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
    					,url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/parter/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" width="50" align="center" halign="center">id</th>
	            <th field="memberRealName" width="180" align="center" halign="center"  formatter="goLinkedOrders">合伙人</th>  
	            <th field="signNo" width="180" align="center" halign="center" >合同编号</th>  
				<th field="memberId" width="50" align="center" halign="center">用户id</th>  
	            <th field="memberName" width="180" align="center" halign="center" >用户</th>  
	            <th field="startTime" width="200" align="center" halign="center">合同开始时间</th>  
	            <th field="status" width="60" align="center" halign="center"  formatter = "yesNoFormat">状态</th>
	            <th field="endTime" width="200" align="center" halign="center">合同结束时间</th>  
	            <th field="createTime" width="200" align="center" halign="center">创建时间</th>  
	            <th field="createMan" width="150" align="center" halign="center">创建人</th>  
			</tr>
		</thead>
	</table>

	<#--3.function button----------------->
	<div id="gridTools">
		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/admin/member/parter/selectAddress">
			<a id="a-selectAddress" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">设置区域</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/parter/percent">
			<a id="a-percent" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">设置佣金</a>
		</@shiro.hasPermission>
	</div>
	
	<div id="allotResourceWin">
	</div>
	<div id="makePercent">
	</div>
	<div id="ordersdialog"></div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />