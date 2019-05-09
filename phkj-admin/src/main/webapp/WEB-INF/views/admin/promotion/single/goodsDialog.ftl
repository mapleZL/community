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
				url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/cate/productCaseTree?id=0"
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
    	if(num>1000){
    		$.messager.alert('提示', '同一个活动最多选择1000个商品。');
			return;
    	}
    	
    	$.each(selected, function(index, item){
				
			var prdHtml = '<div class="fluidbox addItemT">'
				+'<label class="lab-item">&nbsp;</label>'
				+'<input type="hidden" id="ids" name="ids" value="' + item.sellerId + ':' + item.id + '" />'
				+'商品名称：'
				+'<input type="text" id="productName" name="productName" readonly="readonly" style="background:#eee;color:#777;" value="' + item.name1 + '" class="txt w250" />'
				+'&nbsp;&nbsp;商城价：'
				+'<input type="text" id="mallPcPrice" name="mallPcPrice" readonly="readonly" style="background:#eee;color:#777;" value="' + item.mallPcPrice + '" class="txt w50" />'
				+'&nbsp;&nbsp;移动端价：'
				+'<input type="text" id="malMobilePrice" name="malMobilePrice" readonly="readonly" style="background:#eee;color:#777;" value="' + item.malMobilePrice + '" class="txt w50" />'
				+'<a class="a-del-proItem" href="#"><b> —删除</b></a>'
				+'</div>'
				
    		$(".pro-addItem-box").append(prdHtml);
    	});
    	
    	$("#productNum").val(num);
   		$('#goodsDialog').dialog('close');
    }
</script>

<div id="goodsDialog" class="easyui-dialog popBox" title="商品列表"
	style="width: 980px; height: 520px;"
	data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons-brand">
	<div id="searchbar" data-options="region:'north'" style="height: 45px;"
		border="false">
		<div id="searchbox" class="head-seachbox">
			<div class="w-p99 marauto searchCont">
				<form class="form-search" action="doForm" method="post"
					id="gd_queryForm" name="queryForm">
					<div class="fluidbox">
						<p class="p4 p-item">
							<label class="lab-item" style="line-height: 25px;margin-right: 6px;">商品分类:</label>
							<input type="text" class="txt" id="product_case_name" />
							<a onclick="$('#product_case_name').val('');$('#product_case_id').val('');" 
								class="easyui-linkbutton" iconCls="icon-delete">清空</a>
							<input type="hidden" name="q_productCateId" id="product_case_id"/>
							<input type="hidden" name="q_sellerState" value="1"/>
							<input type="hidden" name="q_productCateState" value="1"/>
							<input type="hidden" name="q_state" value="6"/>
							<!-- <input type="hidden" name="q_isTop" value="2"/> -->
							<input type="hidden" name="q_sellerId" value="${(sellerId)!0}"/>
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>

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
							toolbar:'#goodsDialogTools',
							url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/promotion/single/listnopage2',
							queryParams:queryParamsHandler(),
							method:'get'">
				<thead>
					 <tr>
					 	<th field="ck" checkbox="true"></th>
					 	<th field="sellerId" width="70" align="left" halign="center">供应商</th>
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
