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
	action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/overtime/system/addOverTime"
	method="post">
	<table cellpadding="20" cellspacing="10">
		<tr>
            <td><label>限制时间(小时)</label></td>
            <td><input class="txt w200"   id="time" name="overTime" oninput="value=value.replace(/[^\d]/g,'')"
                       required="required" value="1" /></td>
		</tr>
        <tr>
			<td colspan="2"><input type="button" id="submitBtn" class="btn" value="确定" /></td>
		</tr>
	</table>
</form>