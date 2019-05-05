<#include "/seller/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/operate/sellerTransport"/>

<script type="text/javascript" 
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>

<script language="javascript">
	var codeBox;
	$(function(){
		<#noescape>
			codeBox = eval('(${initJSCodeContainer("TRANSPORT_STATE","TRANSPORT_TYPE","TRANSPORT_TIME")})');
		</#noescape>
		
		$('#btn-inuse').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			var state = selected.state;
			if(state == 1){
				$.messager.alert('提示', '该模板已启用，请勿重复操作！');
				return;
			}
			$.messager.confirm('确认', '确定启用该模板吗?启用该模板后，其他模板将会被禁用！', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});

					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/inuse",
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
	
	var currentBaseUrl = "${currentBaseUrl}";
	var url_res = "${domainUrlUtil.EJS_URL_RESOURCES}";
</script>
<script
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/seller/transport_list.js"></script>


<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		运费模板 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">模板名称 :</label> <input type="text"
							class="txt" id="q_qq" name="q_qq"
							value="${queryMap['q_qq']!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">状态 :</label> <@cont.select id="q_state"
						codeDiv="TRANSPORT_STATE" value="${queryMap['q_state']!''}" 
						name="q_state" style="width:100px"/>
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
						,view: detailview
						,detailFormatter:detailFormatter
						,onExpandRow:onExpandRow
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="transName" width="120" align="center">模板名称</th>
				<th field="transType" width="120" align="center" formatter="transType">计价方式</th>
				<th field="transTime" width="120" align="center" formatter="transTime">发货时间</th>
				<th field="createtime" width="90" align="center">创建时间</th>
				<th field="state" width="50" align="center" formatter="getState">状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<!-- <a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">新增</a> <a id="btn_edit"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true">编辑</a> <a id="btn_del"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-delete" plain="true">删除</a> -->
		<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		
		<@shiro.hasPermission name="/seller/operate/sellerTransport/add">
		<a id="btn_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/operate/sellerTransport/edit">
		<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/operate/sellerTransport/inuse">
		<a id="btn-inuse" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">启用</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/operate/sellerTransport/del">
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
<#include "/seller/commons/_detailfooter.ftl" />
