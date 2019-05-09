<#import "/admin/commons/_macro_controller.ftl" as cont/>
<#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders"/>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>

<script>
	var codeBox;
	var paycodeBox;
	var ivocodeBox;
	$(function() {

		<#noescape>
			codeBox = eval('(${initJSCodeContainer("ORDERS_ORDER_STATE")})');
			paycodeBox = eval('(${initJSCodeContainer("ORDER_PAYMENT_STATUS")})');
			ivocodeBox = eval('(${initJSCodeContainer("ORDER_INVOICE_STATUS")})');
		</#noescape>
		
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
    
    function detailFormatter(index,row){
        return '<div style="padding:2px"><table class="ddv1"></table></div>';
    }
	
    function onExpandRow(index,row){
        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv1');
        ddv.datagrid({
           fitColumns:true,
           singleSelect:true,
           method:'get',
           url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/geterrorordersproduct?orderserrorsn='+row.orderSn,
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
				field : 'packageFee',
				title : '加工费',
				width : 30,
				align : 'center'
			},{
				field : 'labelFee',
				title : '辅料费',
				width : 30,
				align : 'center'
			},{
				field : 'number',
				title : '商品数量',
				width : 50,
				align : 'center'
			}, {
				field : 'moneyAmount',
				title : '网单金额',
				width : 50,
				align : 'center'
			}, {
				field : 'labelName',
				title : '标签名称',
				width : 30,
				align : 'center'
			}, {
				field : 'packageName',
				title : '套餐名称',
				width : 30,
				align : 'center'
			}, {
				field : 'packingType',
				title : '包装方式',
				width : 30,
				align : 'center'
			}, {
				field : 'unitType',
				title : '包装单位',
				width : 30,
				align : 'center'
			}, {
				field : 'packOther',
				title : '辅料需求',
				width : 90,
				align : 'center'
			}]],
			onResize : function() {
				$('#gd_dataGrid').datagrid('fixDetailRowHeight',index);
			},
			onLoadSuccess : function() {
				setTimeout(function() {
					$('#gd_dataGrid').datagrid('fixDetailRowHeight',index);
				}, 0);
			}
		});
	}
	
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
    
</script>
<input type="hidden" value ="${ordersSn}" name = "ordersSn"/>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" border="false">
			<table id="gd_dataGrid" class="easyui-datagrid"
				data-options="rownumbers:true
						,idField :'id'
						,singleSelect:true
						,rowStyler:styler
						,view: detailview
						,autoRowHeight:false
						,fitColumns:false
						,toolbar:'#goodsDialogTools'
						,detailFormatter:detailFormatter
						,onExpandRow:onExpandRow
						,pageSize:10
						,striped:true
						,pagination:true
						,fit:true
    					,url:'${currentBaseUrl}/errororderslist?ordersSn=${ordersSn}'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
				<thead>
					 <tr>
					 	<th field="id" hidden="hidden"></th>
						<th field="orderSn" width="150" align="left" halign="center">订单号</th>
						<th field="memberName" width="120" align="left">买家用户名</th>
						<th field="sellerName" width="120" align="left">店铺</th>
						<th field="moneyProduct" width="80" align="center">商品金额</th>
						
						<th field="servicePrice" width="80" align="center">加工费</th>
						<th field="labelPrice" width="80" align="center">辅料费</th>
						<th field="moneyLogistics" width="80" align="center">运费</th>
						<th field="moneyCoupon" width="80" align="center">红包</th>
						
						<th field="moneyOrder" width="80" align="center">订单总金额</th>
						<th field="paymentStatus" width="70" align="center" formatter="paymentStatus">付款状态</th>
						<th field="orderState" width="80" align="center" formatter="getState">订单状态</th>
						<th field="invoiceStatus" width="70" align="center" formatter="invoiceStatus">发票状态</th>
						<th field="invoiceTitle" width="100" align="left">发票抬头</th>
						<th field="invoiceType" width="70" align="center">发票类型</th>
						<th field="paymentName" width="70" align="center">支付方式</th>
						<th field="logisticsName" width="80" align="center">物流名称</th>
						<th field="logisticsNumber" width="100" align="center">快递单号</th>
						<th field="tradeNo" width="150" align="left" halign="center">支付码</th>
						<th field="name" width="70" align="center">联系人</th>
						<th field="mobile" width="100" align="center">联系电话</th>
						<th field="addressAll" width="100" align="center">收货城市</th>
						<th field="addressInfo" width="100" align="center">收货地址</th>
						<th field="deliverTime" width="150" align="center">发货时间</th>
						<th field="createTime" width="150" align="center">创建时间</th>
						<th field="updateTime" width="150" align="center">修改时间</th>
						<th field="payTime" width="150" align="center">支付时间</th>
						<th field="remark" width="150" align="center">订单备注</th>
			        </tr>
				</thead>
			</table>

		</div>
	</div>
<div id="goodsDialogTools">
	<a id="gd_btn-search" href="javascript:void(0)"
		class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
</div>