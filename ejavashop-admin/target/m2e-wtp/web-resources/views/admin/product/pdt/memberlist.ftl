<script language="javascript">
	var yesNoBox;
	var gradeBox;
	var sourceBox;
	var genderBox;
	var statusBox;
	var optTypeBox;
	var balanceStateBox;
	var addressStateBox;
	$(function(){
		//为客户端装配本页面需要的字典数据,多个用逗号分隔
		<#noescape>
			yesNoBox = eval('(${initJSCodeContainer("YES_NO")})');
			gradeBox = eval('(${initJSCodeContainer("MEMBER_GRADE")})');
			sourceBox = eval('(${initJSCodeContainer("MEMBER_SOURCE")})');
			genderBox = eval('(${initJSCodeContainer("GENDER")})');
			statusBox = eval('(${initJSCodeContainer("MEMBER_STATUS")})');
			optTypeBox = eval('(${initJSCodeContainer("MEMBER_VAL_OPT_TYPE")})');
			balanceStateBox = eval('(${initJSCodeContainer("MEMBER_BALANCE_STATE")})');
			addressStateBox = eval('(${initJSCodeContainer("MEMBER_ADDRESS_STATE")})');
		</#noescape>
		// 查询按钮
		$('#a-gridSearch4').click(function(){
			$('#memberdataGrid').datagrid('reload',queryParamsHandler());
		});

	});

	function yesNoFormat(value,row,index){
		return yesNoBox["YES_NO"][value];
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
	function optTypeFormat(value,row,index){
		return optTypeBox["MEMBER_VAL_OPT_TYPE"][value];
	}
	function balanceStateFormat(value,row,index){
		return balanceStateBox["MEMBER_BALANCE_STATE"][value];
	}
	function addressStateFormat(value,row,index){
		return addressStateBox["MEMBER_ADDRESS_STATE"][value];
	}
	function gdSumit4(){
    	var selected = $('#memberdataGrid').datagrid('getSelections');
    	var selectedid="";
    	var selectedname1="";
    	for(var i=0;i<selected.length;i++){
    		selectedid += selected[i].id+",";
    		selectedname1 += selected[i].name+",";
    	}
    	selectedid = selectedid.substring(0,selectedid.lastIndexOf(","));
    	selectedname1 = selectedname1.substring(0,selectedname1.lastIndexOf(","));
    	$('#memberName').val(selectedid);
    	$('#memberIds').val(selectedid);
    	$('#memberDialog').dialog('close');
    }
</script>

<#--1.queryForm----------------->
<div id="memberDialog" class="easyui-dialog" title="标签管理列表"
	style="width: 1000px; height: 520px;overflow: hidden;"
	data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons-brand4">
<div class="easyui-layout" data-options="fit:true" style="height: 500px;">
<div id="searchbar4" data-options="region:'north'" style="margin:0 auto;height: 45px;" border="false">
	<div id="searchbox4" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
		<form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
			<div class="fluidbox"><!-- 不分隔 -->
				<p class="p4 p-item">
					<label class="lab-item">用户名 :</label>
					<input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">会员等级 :</label>
					<@cont.select id="q_grade" codeDiv="MEMBER_GRADE" style="width:80px"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">手机号 :</label>
					<input type="text" class="txt" id="q_mobile" name="q_mobile" value="${q_mobile!''}"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">会员来源 :</label>
					<@cont.select id="q_source" codeDiv="MEMBER_SOURCE" style="width:80px"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">使用状态 :</label>
					<@cont.select id="q_status" codeDiv="MEMBER_STATUS" style="width:80px"/>
				</p>
			</div>
		</form>
		</div>
	</div>
</div>
<div data-options="region:'center'" border="false">
	<table id="memberdataGrid" class="easyui-datagrid" 
			data-options="rownumbers:true
						,singleSelect:false
						,autoRowHeight:false
						,fitColumns:false
						,collapsible:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:20
						,fit:true
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th field="id" hidden="hidden"></th>
				<th field="name" width="150" align="left" halign="center">用户名</th>  
	            <th field="grade" width="80" align="left" halign="center" formatter="gradeFormat">等级</th>  
	            <th field="gradeValue" width="50" align="left" halign="center">经验值</th>  
	            <th field="integral" width="50" align="left" halign="center">积分</th>  
	            <th field="registerTime" width="150" align="left" halign="center">注册时间</th>  
	            <th field="lastLoginTime" width="150" align="left" halign="center">最后登录时间</th>  
	            <th field="source" width="50" align="left" halign="center" formatter="sourceFormat">来源</th>  
	            <th field="isEmailVerify" width="50" align="left" halign="center" formatter="yesNoFormat">邮箱验证</th>  
	            <th field="isSmsVerify" width="50" align="left" halign="center" formatter="yesNoFormat">手机验证</th>  
	            <th field="canReceiveSms" width="50" align="left" halign="center" formatter="yesNoFormat">接受短信</th>  
	            <th field="canReceiveEmail" width="50" align="left" halign="center" formatter="yesNoFormat">接收邮件</th>  
	            <th field="status" width="70" align="left" halign="center" formatter="statusFormat">使用状态</th>  
			</tr>
		</thead>
	</table>
	<div id="gridTools">
		<a id="a-gridSearch4" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	</div>
</div>
</div>
</div>
<div id="dlg-buttons-brand4" style="text-align: right">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="gdSumit4()">确定</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel"
		onclick="javascript:$('#memberDialog').dialog('close')">取消</a>
</div>
