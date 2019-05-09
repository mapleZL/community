<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/productBack"/>

<script type="text/javascript" 
	src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>

<style>
#newstypeTree img {
	max-width: 390px;
	max-height: 290px;
}
</style>

<script language="javascript">
	var backCodeBox;
	var monCodeBox;
	$(function() {

		<#noescape>
			backCodeBox = eval('(${initJSCodeContainer("MEM_PB_STATE_RETURN")})');
			monCodeBox = eval('(${initJSCodeContainer("MEM_PB_STATE_MONEY")})');
		</#noescape>

		$('#btn_backMoney').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			var state = selected.stateReturn;
			if(state != 3){
				$.messager.alert('提示', '只有申请状态是已经收货的申请才能退款！');
				return;
			}
			var stateMoney = selected.stateMoney;
			if(stateMoney != 1){
				$.messager.alert('提示', '该申请已经退款，请勿重复操作！');
				return;
			}
			var msg = "用户货款将由系统退还到用户的账户中，确认要退款吗？";
			$.messager.confirm('确认', msg, function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						type:"POST",
						url : "${currentBaseUrl}/backmoney",
						dataType: "json",
						data: {id:selected.id},
						cache:false,
						success:function(data, textStatus){
							if (data.success) {
								$.messager.alert('提示','退款成功。');
								$('#dataGrid').datagrid('reload',queryParamsHandler());
							} else {
								$.messager.alert("提示",data.message);
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

	});

	function proBackState(value, row, index) {
		var box = backCodeBox["MEM_PB_STATE_RETURN"][value];
		return box;
	}
	
	function proMonState(value, row, index) {
		var box = monCodeBox["MEM_PB_STATE_MONEY"][value];
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
           url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/productBack/getBackGoodRecord?backId='+row.id,
			loadMsg : '数据加载中...',
			height : 'auto',
			columns : [[{
				field : 'productSku',
				title : '商品sku',
				width : 120,
				halign : 'center'
			}, {
				field : 'productId',
				title : '商品id',
				width : 70,
				halign : 'center'
			}, {
				field : 'backNum',
				title : '退货数量',
				width : 80,
				halign : 'center'
			}, {
				field : 'wellNum',
				title : '正品数量',
				width : 50,
				align : 'center'
			}, {
				field : 'badNum',
				title : '次品数量',
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
		退货申请列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">订单号:</label> <input type="text"
							class="txt" id="q_orderSn" name="q_orderSn"
							value="${q_orderSn!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">退货人:</label> <input type="text"
							class="txt" id="q_backMan" name="q_backMan"
							value="${q_backMan!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">退货数:</label> <input type="text"
							class="txt" id="q_backNum" name="q_backNum"
							value="${q_backNum!''}" />
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
				<th field="ordersId" width="100" align="center">订单id</th>
				<th field="ordersSn" width="100" align="center">订单号</th>
				<th field="backNum" width="100" align="center">退货数量</th>
				<th field="backMan" width="120" align="center">退货人</th>
				<th field="wellNum" width="100" align="center">正品数量</th>
				<th field="badNum" width="100" align="center">次品数量</th>
				<th field="mobile" width="150" align="center">联系方式</th>
				<th field="checkMan" width="150" align="center">审核人</th>
				<th field="createTime" width="150" align="center">创建时间</th>   
				<th field="updateTime" width="150" align="center">修改时间</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<@shiro.hasPermission name="/admin/order/productBack/backmoney">
		<a id="btn_backMoney" href="/admin/order/productBack/backmoney" class="easyui-linkbutton" iconCls="icon-edit" plain="true">退款到账户</a>
		</@shiro.hasPermission>
		 <a id="btn-search"
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
	</div>
</div>

<div id="newstypeWin">
	<form id="newstypeForm" method="post">

		<ul id="newstypeTree"
			style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;"></ul>
	</form>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
