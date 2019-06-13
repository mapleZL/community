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
<#include "productimgcss.ftl"/>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/skupicupload.js"></script>
<script>
    $(function () {
        $("[name=uploadImg]").multiupload();

        $("#submitBtn").click(function () {
            var price = $('#price').val();//选中的值
            if(price == undefined || price == null || price ==0){
                $.messager.alert('提示','请填写价钱');
                return;
            }
            $("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/price/system/save")
                    .attr("method", "POST")
                    .submit();
        });



    });


    function imageFormat(value, row, index) {
        return "<a class='newstype_view' onclick='showimg($(this).attr(\"imgpath\"));' href='javascript:;' imgpath='"
                + value + "'>点击查看</a>";
    }

    function showimg(href) {
        if (href && href != 'null') {
            var imgs = JSON.parse(href);
            var html = '';
            for (var i = 0; i < imgs.length; i++) {
                html += "<img src='" + imgs[i] + "' >"
            }
            $("#newstypeTree").html(html);
            $("#newstypeWin").window('open');
        } else {
            $.messager.alert('提示','该条记录暂无图片。');
            return;
        }
    }

    function callback1(result) {
        //console.log("1"+result.names[0]);
    }
</script>

<form id="addForm"
	action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/price/system/save"
	method="post">
	<table cellpadding="150" cellspacing="50">
        <div class="dl-group" style="text-align:center">

        <div class="dd-group">
            <font class="red">*</font>价钱(每月) ：<input class="lab-item" id = "price" name ="price" />
        </div>
            <div class="dd-group">
			    <input type="button" id="submitBtn" name="submitBtn" class="btn" value="确定" style="align-content: center" />
            </div>
        </div>
	</table>
</form>