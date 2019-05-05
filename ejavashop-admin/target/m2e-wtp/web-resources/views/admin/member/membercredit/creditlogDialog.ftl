<script>
	$(function(){
		$("#btn-cl-search").click(function(){
			$('#creditlogDialogDataGrid').datagrid('reload',queryParamsHandler());
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont">
			<form class="form-search" action="doForm" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">会员名 :</label> <input type="text"
							class="txt" id="q_memberName" name="q_memberName"/>
					</p>
					<p class="p4 p-item">
						<label class="lab-item">订单号 :</label> <input type="text"
							class="txt" id="q_orderSn" name="q_orderSn"/>
					</p>
					<p class="p4 p-item">
					<label class="lab-item">创建时间:</label>
                        <input type="text" id="q_startTime" name="q_startTime"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00'})" class="txt w110"/>
                        	—
                        <input type="text" id="q_endTime" name="q_endTime"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59'})" class="txt w110"/>
                	</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="creditlogDialogDataGrid" class="easyui-datagrid"
		data-options="singleSelect : true,
						autoRowHeight:true,
						pagination:true,
						toolbar:'#clgridTools',
						fit:true,
						pagination:true,
						url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/memberCreditLog/list?q_creditId=${creditId!}',
						queryParams:queryParamsHandler(),
						method:'get'">
		<thead>
			<tr>
				<th field="id" hidden="hidden"></th>
				<th field="type" width="60" align="center" formatter="logtype">赊账类型</th>
				<th field="memberName" width="80" align="center">会员</th>
				<th field="orderSn" width="80" align="center" formatter="ordersnfmt">订单号</th>
				<th field="orderMoney" width="80" align="center" formatter="ordersamountfmt">订单金额</th>
				<th field="orderDay" width="80" align="center" formatter="ordersdatefmt">订单日期</th>
				<th field="orderPayState" width="80" align="center" formatter="orderspaystatefmt">订单支付状态</th>
				<th field="balanceBefore" width="80" align="center" formatter="orderspaybeforefmt">支付前余额</th>
				<th field="balanceAfter" width="80" align="center" formatter="orderspayafterfmt">支付后余额</th>
				<th field="creditAmount" width="80" align="center">变动金额</th>
				<th field="creditBefore" width="90" align="center">变动前剩余额度</th>
				<th field="creditAfter" width="90" align="center">变动后剩余额度</th>
				<th field="expireDateBefore" width="80" align="center" formatter="expdatefmt">变动前到期日</th>
				<th field="periodAccount" width="80" align="center" formatter="periodfmt">账期周期(天)</th>
				<th field="expireDateAfter" width="80" align="center" formatter="expdateafterfmt">变动后到期日</th>
				<th field="createName" width="80" align="center">创建人</th>
				<th field="creatTime" width="80" align="center">创建时间</th>				
			</tr>
		</thead>
	</table>
</div>
</div>
<div id="clgridTools">
	<a id="btn-cl-search"
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-search" plain="true">查询</a>
</div>