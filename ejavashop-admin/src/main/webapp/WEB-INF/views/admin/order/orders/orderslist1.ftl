<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders"/>

<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<!-- <style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style> -->

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
		//确认
		$("#a_submit_pay").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			$.messager.confirm('确认','你确定向客户收取该笔费用？', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						type:"GET",
					    url: "${currentBaseUrl}/gotoSubmitpay",
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
		
		$("#btn-setprice").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href='${currentBaseUrl}/setPrice?orderId='+selected.id;
		});
		
		$("#btn-setLabel").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href='${currentBaseUrl}/setLabel?orderId='+selected.id+'&type=state1';
		});
		//订单信息修改
		$("#btn-modifyInfo").click(function(){
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href='${currentBaseUrl}/modifyInfo?ordersId='+selected.id;
		});
		//退货
		$('#a-returngoods').click(function(){
	        var selectedCode = $('#dataGrid').datagrid('getSelected');
	        if(!selectedCode){
	                $.messager.alert('提示','请选择操作行。');
	                return;
	            }
			$.messager.prompt('确认','您确定要退货吗？', function(r) {
				var bool1 = (r=="");
				var bool2 = (typeof r=="undefined");
				if(bool2){
					return;
				}
				if (!bool1) {
	                   $.messager.progress({text:"提交中..."});
	                   $.ajax({
	                        type:"GET",
                            url: "${currentBaseUrl}/cancelorder?id="+selectedCode.id+"&reason=" + r,
	                        success:function(e) {
	                             $('#dataGrid').datagrid('reload',queryParamsHandler());
                         		 $.messager.progress('close');
		                		 $.messager.show({
					                    title : '提示',
					                    msg : e,
				                        showType : 'show'
	                      			});
	                        }
	                   });
	               }
	         });
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

	function detailFormatter(index,row){
        return '<div style="padding:2px"><table class="ddv"></table></div>';
    }
	
    function onExpandRow(index,row){
        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
        ddv.datagrid({
           fitColumns:true,
           singleSelect:true,
           method:'get',
           url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/ordersProduct/getOrdersProduct?orderId='+row.id,
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
				field : 'moneyActSingle',
				title : '立减金额和',
				width : 50,
				align : 'center'
			},{
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
				<p class="p12 p-item">
					<label class="lab-item">订单号:</label> <input type="text" class="txt"
						id="q_orderSn" name="q_orderSn" value="${q_orderSn!''}" />
				</p>
				<p class="p4 p-item">
					<label class="lab-item">支付码:</label>
					<input id="q_tradeNo" name = "q_tradeNo" type="text"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">订单来源:</label>
					<select type="radio" class="txt" name="q_source">
						<option value="">———全部———</option>
						<option value="1">PC端</option>
						<option value="2">移动端</option>
					</select>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">订单类型:</label>
					<select name="q_orderType" class="txt">
						<option value="">———全部———</option>
						<option value="1">普通订单</option>
						<option value="5">二次加工订单</option>
					</select>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">配送方式:</label>
					<select name="q_logistics" class="txt">
						<option value="">———全部———</option>
						<option value="1">快递配送</option>
						<option value="5">送货上门</option>
						<option value="6">千禧路自提</option>
						<option value="7">大唐市场自提</option>
						<option value="8">物流中转(大唐)</option>
					</select>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">支付方式:</label>
					<select name="q_paymentCode" class="txt">
						<option value="">———全部———</option>
						<option value="pcalipay">PC支付宝</option>
						<option value="h5alipay">H5支付宝</option>
						<option value="wxpay">微信</option>
						<option value="chinapay">银联支付</option>
						<option value="OFFSEND">线下打款</option>
						<option value="bestpay">翼支付</option>
					</select>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">促销信息:</label>
					<select name="q_moneyDiscount" class="txt">
						<option value="0">———全部———</option>
						<option value="1">有</option>
						<option value="2">无</option>
					</select>
				</p>
				<p class="p12 p-item">
				<p class="p4 p-item">
					<label class="lab-item">提交时间:</label>
                        <input type="text" id="q_startTime" name="q_startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w110" data-options="required:true"/>
                        	—
                    	<input type="text" id="q_endTime" name="q_endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w110" data-options="required:true"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">商品金额:</label>
						<input type="text" id="q_moneyProduct_s" name="q_moneyProduct_s" class="txt w110"/>
							—
						<input type="text" id="q_moneyProduct_e" name="q_moneyProduct_e" class="txt w110"/>
				</p>
				<p class="p4 p-item">
					<label class="lab-item">商品数量:</label>
						<input type="text" id="q_number_s" name="q_number_s" class="txt w110"/>
							—
						<input type="text" id="q_number_e" name="q_number_e" class="txt w110"/>
				</p>
				</p>
				<input type="hidden" id="q_orderState" name="q_orderState" value="1"/>
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
				<th field="tradeNo" width="150" align="left" halign="center">支付码</th>
				<th field="memberName" width="120" align="left">买家用户名</th>
				<th field="sellerName" width="120" align="left">店铺</th>
				<th field="moneyProduct" width="80" align="center">商品金额</th>
				
				<th field="servicePrice" width="80" align="center">加工费</th>
				<th field="labelPrice" width="80" align="center">辅料费</th>
				<th field="moneyLogistics" width="80" align="center">运费</th>
				<th field="moneyCoupon" width="80" align="center">红包</th>
				<th field="moneyDiscount" width="80" align="center">折扣金额和</th>
				<th field="moneyOrder" width="80" align="center">订单总金额</th>
				<th field="paymentStatus" width="120" align="center" formatter="paymentStatus">付款状态</th>
				<th field="orderState" width="80" align="center" formatter="getState">订单状态</th>
				<th field="invoiceStatus" width="70" align="center" formatter="invoiceStatus">发票状态</th>
				<th field="invoiceTitle" width="100" align="left">发票抬头</th>
				<th field="invoiceType" width="70" align="center">发票类型</th>
				<th field="paymentName" width="70" align="center">支付方式</th>
				<th field="logisticsName" width="80" align="center">物流名称</th>
				<th field="logisticsNumber" width="100" align="center">快递单号</th>
				<th field="name" width="70" align="center">联系人</th>
				<th field="mobile" width="100" align="center">联系电话</th>
				<th field="addressAll" width="100" align="center">收货城市</th>
				<th field="addressInfo" width="100" align="center">收货地址</th>
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
		<a id="a_submit_pay" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">确认</a>
		<a id="a-returngoods" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">退货</a>
		<@shiro.hasPermission name="/admin/order/orders/setLabel">
		<a id="btn-setLabel" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">设置标签</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/setPrice">
		<a id="btn-setprice" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">设置价格</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/modifyInfo">
		<a id="btn-modifyInfo" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改订单</a>
		</@shiro.hasPermission>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />
