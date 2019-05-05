<script type="text/javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("PRODUCT_LABEL_STATE","PRODUCT_LABEL_MEMBER")})');
		</#noescape>

		// 查询按钮
		$('#a-dialog-Search').click(function() {
			$('#labelDataGrid').datagrid('reload', queryParamsHandler());
		});
	});
	function submit() {
		var selectedRow = $("#labelDataGrid").datagrid('getSelected');
		if (!selectedRow) {
			$.messager.alert('提示', '请选择操作行。');
			return;
		}
		labelCallBack(selectedRow);
		$("#labeldialog").dialog('close');
	}

	function closedialog() {
		selectedA = null;
		$('#labeldialog').dialog('close');
	}

	function memberformat(value, row, index) {
		if (value == 0)
			return codeBox["PRODUCT_LABEL_MEMBER"][value];
		else
			return row.memberName;
	}

	function stateformat(value, row, index) {
		return codeBox["PRODUCT_LABEL_STATE"][value];
	}
</script>


<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">标签名称 :</label> <input type="text"
							class="txt" id="q_name" name="q_name" value="${q_name!''}" />
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false" style="height: 80%">
	<table id="labelDataGrid" class="easyui-datagrid"
		data-options="
							rownumbers:true,
							singleSelect : true,
							autoRowHeight:true,
							pagination:true,
							toolbar:'#gridTools',
							fit:true,
							fitColumns:true,
							pagination:true,
							url:'${domainUrlUtil.EJS_URL_RESOURCES}/seller/operate/productLabel/list?q_memberId=${memberId}',
							queryParams:queryParamsHandler(),
							method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="name" width="80" align="center">标签名称</th>
				<th field="memberId" width="80" align="center" formatter="memberformat">所属客户</th>
				<th field="state" width="80" align="center" formatter="stateformat">状态</th>
				<th field="marketPrice" width="80" align="center">市场价</th>
				<th field="sku" width="80" align="center">sku</th>
				<th field="norms" width="80" align="center">规格</th>
				<th field="stock" width="80" align="center">库存</th>
			</tr>
		</thead>
	</table>
</div>

<div id="gridTools">
	<a id="a-dialog-Search" href="javascript:void(0)"
		class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
</div>

<div style="text-align: center;margin: 5px;">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="submit()">确定</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel" onclick="closedialog()">取消</a>
</div>
