<div id="goodsDialog" class="easyui-dialog popBox" title="商品列表"
	style="width: 1000px; height: 520px;overflow: hidden;"
	data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons-brand">
	<div class="easyui-layout" data-options="fit:true"
		style="height: 500px;">
		<div id="searchbar" data-options="region:'north'"
			style="height: 45px;" border="false">
			<div id="searchbox" class="head-seachbox">
				<div class="w-p99 marauto searchCont">
					<form class="form-search" action="doForm" method="post"
						id="gd_queryForm" name="queryForm">
						<div class="fluidbox">
							<p class="p4 p-item">
								<label class="lab-item">商品名称 :</label> <input type="text" class="txt" id="q_name1" name="q_name1" value="${q_name!''}" />
							</p>
							<p class="p4 p-item" style="display: none;">
								<label class="lab-item"
									style="line-height: 25px;margin-right: 6px;">商品分类:</label> <input
									type="text" class="txt" id="product_case_name" /> <a
									onclick="$('#product_case_name').val('');$('#product_case_id').val('');"
									class="easyui-linkbutton" iconCls="icon-delete">清空</a> <input
									type="hidden" name="q_productCateId" id="product_case_id" /> <input
									type="hidden" name="q_sellerState" value="1" /> <input
									type="hidden" name="q_productCateState" value="1" /> <input
									type="hidden" name="q_state" value="6" />
								<!-- <input type="hidden" name="q_isTop" value="2"/> -->
							</p>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div data-options="region:'center'" border="false">
			<table id="gd_dataGrid" class="easyui-datagrid"
				data-options="rownumbers:false,
							idField :'id',
							autoRowHeight:false,
							singleSelect:true,
							striped : true,
							pagination:true,
							pageSize:5,
							fit:true,
							fitColumns:true,
							queryParams:queryParamsHandler(),
							toolbar:'#goodsDialogTools',
							method:'get'">
				<thead>
					<tr>
						<th field="id" type="text" data-options="editor:{type:'numberbox'}">商品ID</th>
						<th field="name1" width="200" align="left" halign="center">商品名称</th>
						<th field="seller" width="80" align="center">商家</th>
						<th field="productCateName" width="70" align="center">商品分类</th>
						<th field="productBrandName" width="90" align="center">商品品牌</th>
						<th field="productCode" width="120" align="center">商品编码</th>
						<th field="mallPcPrice" width="70" align="center">商城价</th>
						<th field="productStock" width="50" align="center">库存</th>
						<th field="upTime" width="100" align="center">上架时间</th>
						<th field="ck" field="hasSettled" width="50" align="center"
							formatter="settformt"></th>
						<th field="cycleChange" width="300" align="center"
							data-options="editor:{type:'numberbox',options:{required:true,min:1,max:66665,precision:0}}">输入序列号</th>
					</tr>
				</thead>
			</table>

		</div>
	</div>
</div>


<div id="goodsDialogTools">
	<a id="gd_btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
</div>
<div id="dlg-buttons-brand" style="text-align: right">
	<a id="saveRuleRes" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#goodsDialog').dialog('close')">取消</a>
</div>

<script>
	
	$('#saveRuleRes').click(function() {
						var ids = "";
						var sorts = "";
						$("tr[id^='datagrid-row']").each(function() {
							var editableLen = $(this).find("input[type='hidden']").length;
							if (editableLen > 0) {
									$(this).find("td").each(function() {
										if ($(this).attr("field") == "id") {
											//rowsdata.push("id:"+$(this).find("input[type='hidden']").val());
											ids += $(this).find("input[type='text']").val() +  ",";
										}
										if ($(this).attr("field") == "cycleChange") {
											//rowsdata.push("sort:"+$(this).find("input[type='hidden']").val());
											sorts += $(this).find("input[type='hidden']").val() +  ",";
										}
									});
								}
						});
						if (ids.length > 0) {
							ids = ids.substring(0, (ids.length - 1));
							sorts = sorts.substring(0, (sorts.length - 1));
						}
						var selected = $('#dataGrid').datagrid('getSelected');
						$.ajax({
									type : "POST",
									url : "${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floor/sort",
									dataType : "json",
									data : {"ids":ids, "sorts":sorts, "floorCode":selected.floorCode},
									cache : false,
									success : function(data) {
										//alert(data.data);
										if (data.data == "success") {
											$.messager.alert('提示', '保存成功');
										} else {
											$.messager.alert('提示', data.message);
										}
									}
								});
					});
	function settformt(value, row, index) {
		var checked = "";
		if (value == 1) {
			checked = "checked disabled";
		}
		var html = "<input type='checkbox' " + checked
				+ " onchange='setechange(this)'/>";
		return html;
	}

	function setechange(obj) {
		var selected = $('#gd_dataGrid').datagrid('getSelected');
		var rowIndex = $('#gd_dataGrid').datagrid('getRowIndex', selected);
		if (!$(obj).is(':checked')) {
			reject(rowIndex);
		} else {
			onClickRow(rowIndex);
			$(".datagrid-btable")
					.find("tr")
					.each(
							function() {
								if ($(this).attr("datagrid-row-index") == rowIndex) {
									$(this)
											.find("td")
											.each(
													function() {
														if ($(this).attr(
																"field") == "cycleChange") {
															$(this)
																	.find(
																			".numberbox")
																	.numberbox(
																			'setValue',
																			1000);
															$('#dataGrid')
																	.datagrid(
																			'validateRow',
																			rowIndex);
														}
													});
								}
							});
		}
	}

	function onClickRow(index) {
		$('#gd_dataGrid').datagrid('selectRow', index).datagrid('beginEdit',
				index);
		endIndex = index;
	}

	function reject(index) {
		$('#gd_dataGrid').datagrid('cancelEdit', index);
	}
	// 查询按钮
	$('#gd_btn-search').click(function() {
		$('#gd_dataGrid').datagrid('reload', queryParamsHandler());
});
	
</script>