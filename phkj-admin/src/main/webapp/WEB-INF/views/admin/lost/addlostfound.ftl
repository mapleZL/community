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
		$('#submitBtn').click(function() {
            var type = $("input[name='type']:checked").val();
            if (type == undefined || type == null) {
                $.messager.alert('提示','请选择分类');
                return;
            }
            var title = $("#title").val();
            if (title == undefined || title == null || title == '') {
                $.messager.alert('提示','标题必须填写');
                return;
            }
            var telephone = $("#telephone").val();
            if (telephone == undefined || telephone == null || telephone == '') {
                $.messager.alert('提示','联系电话必填');
                return;
            }
            var createName = $("#createName").val();
            if (createName == undefined || createName == null) {
                $.messager.alert('提示','联系人必填');
                return;
            }

            $("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/lost/system/addLostFound")
                    .attr("method", "POST")
                    .submit();

		});
	});
</script>

<form id="addForm"
	action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/lost/system/addLostFound"
	method="post">
	<table cellpadding="200" cellspacing="70">
        <div class="fluidbox">
            <p class="p6 p-item">
                <font class="red">*</font>分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类 ：&nbsp;&nbsp;
                <input type="radio" name="type" value="1" /> 失物
                <input type="radio" name="type" value="0" />  招领
            </p>
        </div>
        <div class="fluidbox">
            <font class="red">*</font>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题 ：<input class="lab-item" id = "title" name ="title"></input>
        </div>
        <div class="fluidbox">
            <font class="red">*</font>内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容 ：<input class="lab-item" id = "content" name ="content"></input>
        </div>
        <div class="fluidbox">
            <font class="red">*</font>联&nbsp;&nbsp;系&nbsp;人 ：<input class="lab-item" id = "createName" name  = "createUserName"></input>
        </div>
        <div class="fluidbox">
            <font class="red">*</font>联系方式 ：<input class="lab-item" id = "telephone" name  = "telephone"></input>
        </div>
		<tr>
			<td colspan="2"><input type="button" id="submitBtn" class="btn" value="确定" /></td>
		</tr>
	</table>
</form>