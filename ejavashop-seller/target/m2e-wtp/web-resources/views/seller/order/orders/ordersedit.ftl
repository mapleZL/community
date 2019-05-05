<style>
#devForm {
	margin: 20px 10px;
}

#devForm table td {
	padding: 8px 0;
}

form input[type="button"] {
	margin: 0 100px;
}
</style>

<script>
	$(function() {
		$('#ccName').val($("#ccId").find("option:selected").text());
		$('#submitBtn').click(function() {
			$('#devForm').submit();
		});
	});
</script>

<form id="devForm"
	action="${domainUrlUtil.EJS_URL_RESOURCES}/seller/order/orders/changeOrdersMoney"
	method="post">
	<input id="orderid" type="hidden" name="orderid" value="${orders.id!}" />
	<input id="source" type="hidden" name="source" value="${source!}" />
	<table cellpadding="20" cellspacing="10">
		<tr>
			<td>订&nbsp;单&nbsp;号&nbsp;：</td>
			<td>${orders.orderSn!}</td>
		</tr>
		<tr>
			<td><label>订单金额：</label></td>
			<td><input class="txt w200 easyui-numberbox" id="moneyOrder"
				name="moneyOrder" value="${orders.moneyOrder}"
				data-options="required:true,precision:2,min:0.00" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" id="submitBtn" class="btn"
				value="确定修改" /></td>
		</tr>
	</table>
</form>