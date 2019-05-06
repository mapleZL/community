<#include "/admin/commons/_detailheader.ftl" /> 
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/memberCredit"/>
	<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/css/credit.css" type="text/css"></link>
<script type="text/javascript" src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
	var currentBaseUrl = "${currentBaseUrl}";
	var domainURL = "${domainUrlUtil.EJS_URL_RESOURCES}";
	var codeBox;
	
	$(function(){
		<#noescape>
			// 初始化需要的字典数据
	        codeBox = eval('(${initJSCodeContainer("YES_NO","MEMBER_CREDIT_STATE","MEMBER_CREDIT_SOURCE","MEMBER_CG_TYPE")})');
   		</#noescape>
	});
	
	function doexport(val){
		$.messager.confirm('提示', '确定导出订单信息吗？', function(r){
	            if (r){
	            	$.fileDownload('${currentBaseUrl}/doexport?type='+val,{data:queryParamsHandler()});
	            }
	    });
	}
	
	function sotpOrOpenThisAcc(val){
		var selected = $('#dataGrid').datagrid('getSelected');
		var selectId = selected.id;
		if(val == 1){
			if(!window.confirm("确定关闭"+selected.realName+"赊账功能?")){
				return;
			}
		}
		if(val == 2){
			if(!window.confirm("确定开启"+selected.realName+"赊账功能?")){
				return;
			}
		}
		$.ajax({
		type: 'get',
		url : currentBaseUrl+ '/chageState',
		data : {selectId:selectId,state:val},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				$.messager.show({
					title : '提示',
					msg : "修改成功",
					showType : 'show'
				});
				window.location = domainURL + "/admin/member/memberCredit";
			} else {
				var msg = data.message;
				if(!msg){
					msg = "修改失败，请稍后再试";
				}
				$.messager.alert("提示",msg);
			}
		}
	});
	}
</script>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/credit.js"></script>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		赊账管理列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">会员名:</label> <input type="text"
							class="txt" id="q_memberName" name="q_memberName" value="${q_memberName!}" />
						<input type="hidden" name="q_memberId" value="${memberId!}"/>
					</p>
					<p class="p4 p-item">
						<label class="lab-item">额度:</label> 
						<input type="text" class="txt easyui-numberbox" id="q_quota_start" name="q_quota_start" 
							data-options="min:0.01,max:99999999,precision:2"
							value="${queryMap['q_quota_start']!}" /> - <input type="text" class="txt easyui-numberbox" 
							data-options="min:0.01,max:99999999,precision:2,onChange:quotaEndChange"
							id="q_quota_end" name="q_quota_end" value="${queryMap['q_quota_end']!}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">到期日:</label> 
						<input type="text" class="txt" id="q_expireDate" name="q_expireDate" 
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
							value="${q_expireDate!}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">状态:</label> 
						<@cont.select id="sel_member" codeDiv="MEMBER_CREDIT_STATE"
							name="q_state" />
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-datagrid"
		data-options="rownumbers:false
						,idField :'id'
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden" 
					data-options="editor:{type:'numberbox'}"></th>
				<th field="memberBalance" hidden="hidden"
					data-options="editor:{type:'numberbox'}">余额</th>
				<th field="memberName" width="80" align="center">会员</th>
				<th field="realName" width="80" align="left" halign="center">姓名</th>  
				<th field="quota" width="80" align="center">账期额度</th>
				<th field="used" width="80" align="center">已使用额度</th>
				<th field="surplus" width="80" align="center">剩余额度</th>
				<th field="state" width="80" align="center" formatter="stateformt">状态</th>
				<th field="cycle" width="80" align="center">账期周期（天）</th>
				<th field="expireDateStr" width="80" align="center">到期日</th>
				<th field="source" width="80" align="center" formatter="sourceformt">额度来源</th>
				<th field="hasSettled" width="80" align="center" 
					formatter="settleformt">已结清</th>
				<th field="status" width="80" align="center" formatter="statusformt">赊账功能</th>
				<th field="quotaChange" width="80" align="center"
					data-options="editor:{type:'numberbox',options:{required:true,min:0.01,max:99999999,precision:2}}">额度调整</th>
				<th field="cycleChange" width="80" align="center"
					data-options="editor:{type:'numberbox',options:{required:true,min:1,max:365,precision:0,onChange:daychange}}">账期周期（天）</th>
				<th field="expireDateChange" width="80" align="center">下次到期日</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
			
		<a id="btn-reset"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-undo" plain="true">重置查询</a>
		
		<@shiro.hasPermission name="/admin/member/memberCredit/creditLog">
        <a id="a-creditLog" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-tip" plain="true">赊账记录</a>
		</@shiro.hasPermission>
		
        <a id="a-save-change" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
        <a id="a-gridDoExport" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="doexport(1)">总表导出</a>
        <a id="a-gridDoExport2" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="doexport(2)">明细导出</a>
	</div>
	
</div>
<div id="creditlogDialog"></div>
<div id="submitconfirm">
	<div class="confirm-info">
		<table class="altrowstable" id="alternatecolor">
			<thead>
				<tr>
					<td>客户</td>
					<td>已使用</td>
					<td>剩余额度</td>
					<td>状态</td>
					<td>调整额度</td>
					<td>调整账期周期</td>
					<td>到期日</td>
				</tr>
			</thead>
			<tbody id="tbodyinfo">
			</tbody>
		</table>
	</div>
	<div class="win-btn">
		<input type="button" value="提交" onclick="submitform();"/>
		<input type="button" value="取消" onclick="$('#submitconfirm').window('close');"/>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
