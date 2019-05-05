<#include "/seller/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/order/productBack"/>

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

		$('#btn_edit').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			location.href = '${currentBaseUrl}/edit?id=' + selected.id;
		});
		
		$('#btn_deliver').click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			var state = selected.stateReturn;
			if(state != 2){
				$.messager.alert('提示', '该退货申请不是可收货状态！');
				return;
			}
			var msg = "您确认收货后，将由平台管理员退款给用户，请确认已收到退回的商品！";
			$.messager.confirm('确认', msg, function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						type : "GET",
						url : "${currentBaseUrl}/takeDeliver?id="+selected.id,
						success : function(data, textStatus) {
							$('#dataGrid').datagrid('reload');
							$.messager.progress('close');
							location.href = '${currentBaseUrl}';
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

	function imageFormat(value, row, index) {
		return "<a class='newstype_view' onclick='showimg($(this).attr(\"imgpath\"));' href='javascript:;' imgpath='"
				+ value + "'>点击查看</a>";
	}

	function showimg(href) {
		$("#newstypeTree")
				.html(
						"<img src='${domainUrlUtil.EJS_URL_RESOURCES}/"+href+"' alt='企业logo'>");
		$("#newstypeWin").window('open');
	}

	function proBackState(value, row, index) {
		var box = backCodeBox["MEM_PB_STATE_RETURN"][value];
		return box;
	}
	
	function proMonState(value, row, index) {
		var box = monCodeBox["MEM_PB_STATE_MONEY"][value];
		return box;
	}
</script>

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
							class="txt" id="q_companyName" name="q_companyName"
							value="${queryMap['q_companyName']!''}" />
					</p>
					<p class="p4 p-item">
						<label class="lab-item">退货状态 :</label> <@cont.select
						id="q_stateReturn" codeDiv="MEM_PB_STATE_RETURN"
						value="${queryMap['q_stateReturn']!''}" name="q_stateReturn"
						style="width:100px"/>
					</p>
					<p class="p4 p-item">
						<label class="lab-item">退款状态 :</label> <@cont.select
						id="q_stateMoney" codeDiv="MEM_PB_STATE_MONEY"
						value="${queryMap['q_stateMoney']!''}" name="q_stateMoney"
						style="width:100px"/>
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
						,fitColumns:false
						,toolbar:'#gridTools'
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
				<th field="orderMoney" hidden="hidden"></th>
				<th field="orderSn" width="150" align="center">订单号</th>
				<th field="productName" width="150" align="center">商品名称</th>
				<th field="memberName" width="120" align="center">用户名</th>
				<th field="question" width="150" align="center">问题描述</th>
				<th field="backMoney" width="80" align="center">退款金额</th>
				<th field="backIntegral" width="60" align="center">退回积分</th>
				<th field="backCouponSn" width="120" align="center">退回红包</th>
				<th field="stateReturn" width="100" align="center" formatter="proBackState">退货状态</th>
				<th field="stateMoney" width="100" align="center" formatter="proMonState">退款状态</th>
				<th field="createTime" width="150" align="center">创建时间</th>
			</tr>
		</thead>
	</table>

	<div id="gridTools">
		<a id="btn-search" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/seller/order/productBack/edit">
		<a id="btn_edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">处理退货申请</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/order/productBack/takeDeliver">
		<a id="btn_deliver" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">确认收货</a>
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
