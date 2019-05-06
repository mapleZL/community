<script type="text/javascript">
var gradeBox;
var sourceBox;
var genderBox;
var statusBox;
$(function(){
	<#noescape>
	gradeBox = eval('(${initJSCodeContainer("MEMBER_GRADE")})');
	sourceBox = eval('(${initJSCodeContainer("MEMBER_SOURCE")})');
	genderBox = eval('(${initJSCodeContainer("GENDER")})');
	statusBox = eval('(${initJSCodeContainer("MEMBER_STATUS")})');
	</#noescape>

	// 查询按钮
	$('#a-dialog-Search').click(function(){
		$('#memberDataGrid').datagrid('reload',queryParamsHandler());
	});
});
function submit() {
	var selectedRow = $("#memberDataGrid").datagrid('getSelected');
	if (!selectedRow) {
		$.messager.alert('提示', '请选择操作行。');
		return;
	}
	var callbackfunc = eval('memberCallBack');
	callbackfunc(selectedRow);
	$("#memberDialog").dialog('close');
}

function closedialog(){
	$('#memberDialog').dialog('close');
	$("#sel_member").val(0);
}

function gradeFormat(value,row,index){
	return gradeBox["MEMBER_GRADE"][value];
}
function sourceFormat(value,row,index){
	return sourceBox["MEMBER_SOURCE"][value];
}
function genderFormat(value,row,index){
	return genderBox["GENDER"][value];
}
function statusFormat(value,row,index){
	return statusBox["MEMBER_STATUS"][value];
}
</script>

<div id="memberDialog" class="easyui-dialog popBox" title="会员列表"
	style="width: 980px; height: 422px;"
	data-options="resizable:true,closable:false,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons-award-act">

	<div data-options="region:'north'" border="false" style="margin:0 auto;">
		<div id="searchbox" class="head-seachbox">
			<div class="w-p99 marauto searchCont">
				<form class="form-search" action="doForm" method="post"
					id="queryForm" name="queryForm">
					<div class="fluidbox">
						<p class="p4 p-item">
							<label class="lab-item">用户名 :</label>
							<input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/>
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div  data-options="fit:true,region:'center'" border="false" style="height:100%">
			<table id="memberDataGrid" class="easyui-datagrid"
				data-options="
							rownumbers:true,
							singleSelect : true,
							autoRowHeight:true,
							pagination:true,
							toolbar:'#gridTools',
							fit:true,
							fitColumns:true,
							pagination:true,
							url:'${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/member/member/list',
							method:'get'">
				<thead>
					 <tr>
			          	<th field="name" width="150" align="left" halign="center">用户名</th>  
			            <th field="grade" width="80" align="left" halign="center" formatter="gradeFormat">等级</th>  
			            <th field="gradeValue" width="50" align="left" halign="center">经验值</th>  
			            <th field="integral" width="50" align="left" halign="center">积分</th>  
			            <th field="gender" width="50" align="left" halign="center" formatter="genderFormat">性别</th>  
			            <th field="mobile" width="110" align="left" halign="center">手机号</th>  
			            <th field="source" width="50" align="left" halign="center" formatter="sourceFormat">来源</th>  
			            <th field="status" width="70" align="left" halign="center" formatter="statusFormat">使用状态</th>  
			        </tr>
				</thead>
			</table>
	</div>
	
	<div id="gridTools">
		<a id="a-dialog-Search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
	
	<div id="dlg-buttons-award-act" style="text-align: right">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="submit()">确定</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="closedialog()">取消</a>
	</div>
</div>
