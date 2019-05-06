<#import "/admin/commons/_macro_controller.ftl" as cont/>

<script>
	var codeBox1;
	$(function(){
		 <#noescape>
	        codeBox1 = eval('(${initJSCodeContainer("PRODUCT_STATE","PRODUCT_IS_TOP")})');
	    </#noescape>
	    
	    $('#product_case_name').click(function(){
			$("#caseWin").window({
				width : 350,
				height : 480,
				title : "商品分类",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		
			$("#productCaseTree").tree({
				method: "get",
				cascadeCheck: false,
				onClick:function(node){
					$('#product_case_name').val(node.text);
					$('#product_case_id').val(node.id);
					closeWin();
				},
				onLoadSuccess:function(){
					$("#resourceTree").tree('expandAll');
				},
				url: "${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/cate/productCaseTree?id=0"
			});
	    });
	    
		// 查询按钮
		$('#gd_btn-search').click(function() {
			$('#gd_dataGrid').datagrid('reload', queryParamsHandler());
		});
	});
	
	function isTopFormat(value,row,index){
        return codeBox1["PRODUCT_IS_TOP"][value];
   	}

	function closeWin(){
		$("#caseWin").window('close');
	}
	
    function stateFormat(value,row,index){
        return codeBox1["PRODUCT_STATE"][value];
    }
    
    function gdSumit(){
    	var selected = $('#gd_dataGrid').datagrid('getSelections');
    	
    	var num = parseInt($("#productNum").val()) + selected.length;
    	if(num>20){
    		$.messager.alert('提示', '同一个活动最多选择20个商品。');
			return;
    	}
    	var productIds = "";
    	$.each(selected, function(index, item){
			productIds = productIds + item.id + ",";
    	});
    	generateReciecptList(productIds.substring(0, productIds.length - 1));
    	
   		$('#goodsDialog').dialog('close');
    }
</script>

<div id="goodsDialog" class="easyui-dialog popBox" title="商品列表"
	style="width: 980px; height: 520px;"
	data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons-brand">

	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" border="false">
			<table id="gd_dataGrid" class="easyui-datagrid"
				data-options="
							rownumbers:true,
							autoRowHeight:false,
							striped : true,
							pagination : false,
							singleSelect : false,
							fit:true,
							fitColumns:true,
							method:'get'">
				<thead>
					 <tr>
					 	<th field="ck" checkbox="true"></th>
			            <th field="name1" width="190" align="left" halign="center">商品名称</th>
			            <th field="productCateName" width="100" align="center">商品分类</th>
			            <th field="productBrandName" width="90" align="center">商品品牌</th>
			            <th field="mallPcPrice" width="70" align="center">商城价</th>
			            <th field="malMobilePrice" width="70" align="center">商城价(移动端)</th>
			            <th field="isTop" width="90" align="center" formatter="isTopFormat">是否推荐</th>
			            <th field="upTimeStr" width="90" align="center">上架时间</th>
			            <th field="state" width="70" align="center" formatter="stateFormat">状态</th>
			        </tr>
				</thead>
			</table>

		</div>
	</div>
</div>
<div id="goodsDialogTools">
	<a id="gd_btn-search" href="javascript:void(0)"
		class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
</div>
<div id="dlg-buttons-brand" style="text-align: right">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok" onclick="gdSumit()">确定</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-cancel"
		onclick="javascript:$('#goodsDialog').dialog('close')">取消</a>
</div>

<div id="caseWin">
	<form id="productCaseForm" method="post">
		<div style="margin-top: 5px;">
			<label style="font-weight: bold; margin-left: 15px;">商品分类
			</label> <input id="resIds" type="hidden" name="resIds"> <input
				id="roleId" type="hidden" name="roleId" value="${id!}">
		</div>
	
		<ul id="productCaseTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;">
			<div style="padding: 12px 140px;text-align: center;">数据加载中...</div>
		</ul>
		<div>
			<p class="p-item p-btn">
				<a id="saveRuleRes" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-delete"
					onclick="closeWin();">关闭</a>
			</p>
		</div>
	</form>
</div>
