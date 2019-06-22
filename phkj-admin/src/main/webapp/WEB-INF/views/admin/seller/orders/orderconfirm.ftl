<#include "/admin/commons/_detailheader.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>
<script language="javascript">
	var codeBox;
	$(function() {
		<#noescape>
		codeBox = eval('(${initJSCodeContainer("ORDERS_TYPE","ORDERS_STATE")})');
		</#noescape>
		
		// 查询按钮
		$('#btn-gridSearch').click(function(){
			$('#dataGrid').datagrid('reload',queryParamsHandler());
		});
		
		// 确认订单
		$('#a-gridCommit').click(function () {
			var selected = $('#dataGrid').datagrid('getSelected');
	 		if(!selected){
				$.messager.alert('提示','请选择操作行。');
				return;
			}
	 		// 判断是否是已经审核通过的数据
			if(selected.status == 1){
				$.messager.alert('提示','该条订单已处理,请不要重复操作。');
				return;
			}
	 		$.messager.confirm('确认', '确定审核通过该条订单吗', function(r){
				if (r){
					$.messager.progress({text:"提交中..."});
					$.ajax({
						type:"GET",
					    url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/confirm",
						dataType: "json",
					    data: "orderSn=" + selected.orderSn,
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

	function stateFormat(value,row,index){
		return codeBox["ORDERS_STATE"][value];
	}

	function ordersTypeFormat(value,row,index){
		return codeBox["ORDERS_TYPE"][value];
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
           url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/ordersProduct/getOrdersProduct?orderSn='+row.orderSn,
			loadMsg : '数据加载中...',
			height : 'auto',
			columns : [[{
				field : 'productName',
				title : '货品名称',
				width : 120,
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
	
	function goLinkedOrders(value,row,index){
		var ordersSn = ''+row.orderSn;
	     return "<font style='color:blue;cursor:pointer' title='"+
                value+"' onclick='openwin(\""+ordersSn+"\")'>"+value+"</font>";
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
</script>

<div id="searchbar" data-options="region:'north'" style="margin:0 auto;"
	border="false">
	<h2 class="h2-title">
		订单列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<input type="hidden" id="q_userType" name="q_userType" value="seller"/>
					<p class="p4 p-item">
						<label class="lab-item">订单编号 :</label>
						<input type="text" class="txt" id="q_order_sn" name="q_order_sn" value="${q_order_sn!''}"/>
					</p>
					<#--<p class="p4 p-item">-->
						<#--<label class="lab-item">状态 :</label>-->
						<#--<@cont.select id="q_order_state" name="q_order_state" value="${q_order_state!''}" codeDiv="ORDERS_STATE" mode="1"/>-->
					<#--</p>-->
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
   					,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/confirmOrders'
   					,queryParams:queryParamsHandler()
   					,onLoadSuccess:dataGridLoadSuccess
   					,method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="orderSn" width="150" align="center">订单编号</th>
				<th field="orderType" width="150" align="center" formatter="ordersTypeFormat">订单类型</th>
				<th field="moneyProduct" width="70" align="center">订单总价</th>
				<th field="createTime" width="150" align="center">创建时间</th>
				<th field="updateTime" width="150" align="center">修改时间</th>
				<th field="memberName" width="150" align="center">收货人</th>
	            <th field="mobile" width="150" align="center">联系电话</th>
	            <th field="addressInfo" width="150" align="center">配送地址</th>
				<th field="orderState" width="150" align="center" formatter="stateFormat">订单状态</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a id="a-gridCommit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">确认订单</a>
	</div>
	
</div>
<div id="newstypeWin">
	<form id="newstypeForm" method="post">
		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
