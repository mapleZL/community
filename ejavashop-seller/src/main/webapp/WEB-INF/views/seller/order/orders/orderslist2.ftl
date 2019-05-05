<#include "/seller/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/order/orders"/>

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
	var paycodeBox;
	var ivocodeBox;
	$(function() {

		<#noescape>
			codeBox = eval('(${initJSCodeContainer("ORDERS_ORDER_STATE")})');
			paycodeBox = eval('(${initJSCodeContainer("ORDER_PAYMENT_STATUS")})');
			ivocodeBox = eval('(${initJSCodeContainer("ORDER_INVOICE_STATUS")})');
		</#noescape>

		$('#btn_cancel').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			var state = selected.orderState;
			if(state != 1 && state != 2){
				$.messager.alert('提示', '只能修改未付款或待确认的订单，请确认订单状态！');
				return;
			}
			$.messager.confirm('确认', '确定取消该订单吗?此操作不可撤消', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});

					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/cancelorder",
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
		
		$('#btn_edit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			var state = selected.orderState;
			if(state != 2){
				$.messager.alert('提示', '只能修改未付款或待确认的订单，请确认订单状态！');
				return;
			}
			$("#devWin").window({
				width :320,
				height : 200,
				href : '${currentBaseUrl}/edit?id='+selected.id+"&source=2",
				title : "修改订单金额",
				closed : true,
				shadow : false,
				modal : true,
				collapsible : false,
				minimizable : false,
				maximizable : false
			}).window('open');
		});

		//确认订单
		$("#a_comfirm").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			if(selected.paymentCode!='OFFLINE'){
				$.messager.alert('提示', '只有货到付款的订单才需要确认。');
				return;
			}
			if(selected.orderState!=2){
				$.messager.alert('提示', '只有待确认的订单才需要确认。');
				return;
			}
			$.messager.confirm('确认','确定要确认该订单吗？', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/confirm",
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
		
		// 查询按钮
		$('#btn-search').click(function() {
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});
		
		// 订单打印
		$('#btn-print').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			window.open("${currentBaseUrl}/print?id="+selected.id);
		});

	});

	function getState(value, row, index) {
		var box = codeBox["ORDERS_ORDER_STATE"][value];
		return box;
	}
	
	function paymentStatus(value, row, index) {
		var box = paycodeBox["ORDER_PAYMENT_STATUS"][value];
		return box;
	}
	
	function invoiceStatus(value, row, index) {
		var box = ivocodeBox["ORDER_INVOICE_STATUS"][value];
		return box;
	}

	function styler(value,row,index){
		switch (row.orderState) {
		case 3:
			return  'color:red'; 
			break;
		case 6:
			return  'color:#959595'; 
			break;
		default:
			break;
		}
	}
	
	function detailFormatter(index,row){
        return '<div style="padding:2px"><table class="ddv"></table></div>';
    }
	
    function onExpandRow(index,row){
        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
        ddv.datagrid({
           fitColumns:true,
           singleSelect:true,
           method:'get',
           url:'${domainUrlUtil.EJS_URL_RESOURCES}/seller/order/ordersProduct/getOrdersProduct?orderId='+row.id,
			loadMsg : '数据加载中...',
			height : 'auto',
			columns : [[{
				field : 'productName',
				title : '货品名称',
				width : 120,
				align : 'left',
				halign : 'center'
			}, {
				field : 'specInfo',
				title : '规格',
				width : 70,
				align : 'left',
				halign : 'center'
			}, {
				field : 'productSku',
				title : '商品SKU',
				width : 80,
				align : 'left',
				halign : 'center'
			}, {
				field : 'moneyPrice',
				title : '商品单价',
				width : 50,
				align : 'center'
			}, {
				field : 'number',
				title : '商品数量',
				width : 50,
				align : 'center'
			}, {
				field : 'moneyAmount',
				title : '网单金额',
				width : 50,
				align : 'center'
			}]],
			onResize : function() {
				$('#dataGrid').datagrid('fixDetailRowHeight',index);
			},
			onLoadSuccess : function() {
				setTimeout(function() {
					$('#dataGrid').datagrid('fixDetailRowHeight',index);
				}, 0);
			}
		});
	}
</script>

<div id="devWin"></div>

<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		订单列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<form class="form-search" action="doForm" method="post" id="queryForm"
			name="queryForm">
			<div class="fluidbox">
				<p class="p4 p-item">
					<label class="lab-item">订单号:</label> <input type="text" class="txt"
						id="q_orderSn" name="q_orderSn" value="${q_orderSn!''}" />
				</p>
				<input type="hidden" id="q_orderState" name="q_orderState" value="2"/>
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
						,rowStyler:styler
						,view: detailview
						,autoRowHeight:false
						,fitColumns:false
						,toolbar:'#gridTools'
						,detailFormatter:detailFormatter
						,onExpandRow:onExpandRow
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
				<th field="id" hidden="hidden"></th>
				<th field="orderSn" width="150" align="left" halign="center">订单号</th>
				<#--
				<th field="memberName" width="120" align="left">买家用户名</th>
				<th field="sellerName" width="120" align="left">店铺</th>
				-->
				<th field="moneyProduct" width="80" align="center">商品金额</th>
				<th field="moneyLogistics" width="80" align="center">运费</th>
				<th field="moneyOrder" width="80" align="center">订单总金额</th>
				<th field="paymentStatus" width="70" align="center" formatter="paymentStatus">付款状态</th>
				<th field="orderState" width="80" align="center" formatter="getState">订单状态</th>
				<th field="invoiceStatus" width="70" align="center" formatter="invoiceStatus">发票状态</th>
				<th field="invoiceTitle" width="100" align="left">发票抬头</th>
				<th field="invoiceType" width="70" align="center">发票类型</th>
				<th field="paymentName" width="70" align="center">支付方式</th>
				<th field="logisticsName" width="80" align="center">物流名称</th>
				<#--
				<th field="logisticsNumber" width="100" align="center">快递单号</th>
				<th field="name" width="70" align="center" hidden="hidden">联系人</th>
				<th field="mobile" width="100" align="center" hidden="hidden">联系电话</th>
				<th field="addressInfo" width="100" align="center" hidden="hidden">收货地址</th>
				-->
				<th field="addressAll" width="100" align="center" hidden="hidden">收货城市</th>
				<th field="deliverTime" width="150" align="center">发货时间</th>
				<th field="createTime" width="150" align="center">创建时间</th>
				<th field="updateTime" width="150" align="center">修改时间</th>
				<th field="remark" width="150" align="center">订单备注</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a id="btn-print" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
		
		<@shiro.hasPermission name="/seller/order/orders/confirm">
		<a id="a_comfirm" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">确认</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/order/orders/edit">
		<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/order/orders/cancelorder">
		<a id="btn_cancel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">取消</a>
		</@shiro.hasPermission>
		
	</div>
</div>

<#include "/seller/commons/_detailfooter.ftl" />
