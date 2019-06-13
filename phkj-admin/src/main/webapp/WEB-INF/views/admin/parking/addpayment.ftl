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

    <#--鼠标移入移出图片-->
        $('.img').live('mouseover', function () {
            $(this).find('.img-box').show();
        }).live('mouseout', function () {
            $(this).find('.img-box').hide();
        })
    <#--删除图片-->
        $('.del-img').live('click', function () {
            $(this).parent().parent().parent().remove();
            $('[name=fileIndex]').val($('[name=fileIndex]').val() - 1);
            if ($('[name=fileIndex]').val() == 0) {
                $('#previewImgBox').hide();
            }
        })



        $("#submitBtn").click(function () {
            var paymentType = $('#paymentType option:selected').val();//选中的值
            if(paymentType == undefined || paymentType == null || paymentType==0){
                $.messager.alert('提示','请选择支付方式');
                return;
            }
            var bankName = $("#bankName").val();
            if(paymentType == '2'){
                if(bankName == undefined || bankName == null || bankName == ''){
                    $.messager.alert('提示','请填写银行名称');
                    return;
                }
            }

            var paymentCode = $("#paymentCode").val();
            if (paymentCode == undefined || paymentCode == null  || paymentCode == ''){
                $.messager.alert('提示','请填写支付账号');
                return;
            }

             <#--商品图片-->
            var image = $('img[name^=prev_]');
            if (image && image.length > 0) {
                var imageSrc = '';
                for (var i = 0; i < image.length; i++) {
                    var imgSrc = $(image[i]).attr('src');
                    if (imgSrc.indexOf("resources") != -1)
                        continue;
                    imageSrc += imgSrc + ';';
                }
                if ('' != imageSrc) {
                    $('#qrCode').val(imageSrc);//商品图片
                }
            }

            if(paymentType == '3'){
                if(image == undefined || image == null || image == ''){
                    $.messager.alert('提示','请上传二维码!');
                    return;
                }
            }
            alert('请确认号支付账号信息,防止居民转错账!!!');
            $("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/payment/add")
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
	action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/lost/system/nn"
	method="post">
	<table cellpadding="150" cellspacing="50">
        <div class="dl-group" style="text-align:center">
        <div class="dd-group" >
            <font class="red">*</font> 支付类型 :&nbsp;&nbsp;&nbsp;&nbsp;
            <select name="paymentType" id="paymentType"  class="txt w70 " style="width: 185px;" data-options="panelHeight:'auto',editable:false">
                <option value="0">请选择</option>
                <option value="1">支付宝</option>
				<option value="2">银行卡</option>
				<option value="3">微&nbsp;&nbsp;&nbsp;信</option>
             </select>
        </div>
         <div class="dd-group">
                <font class="red">*</font>银行名称 ：<input class="lab-item" id = "bankName" name ="bankName" placeholder="例 : 农业银行"></input>
         </div>
        <div class="dd-group">
            <font class="red">*</font>支付账号 ：<input class="lab-item" id = "paymentCode" name ="paymentCode" placeholder="支付宝,微信,银行卡号" />
        </div>
        <#--<dl class="dl-group">-->
            <#--<dt class="dt-group"><span class="s-icon"></span>二维码</dt>-->
            <dd class="dd-group" style="text-align:center">
                    <span class="s-showbtn">
                        <div name="uploadImg" action="" index="" multiparam='{"url":"/admin/file/uploadPics","validate":{"fileSize":{"value":2048000,"errMsg":"上传图片不允许超过2M"}, "fileMaxNum":{"value":5,"errMsg":"上传图片最多不能超过5张"},"fileType":{"value":"img","errMsg":"上传图片后缀只支持:image、gif、jpeg、jpg、png"}},"callback":"callback1"}' class="upload-img">
                            <a href="#" class="txt_white">支付二维码</a>
                            <input type="hidden" name="fileIndex" value="0"/>
                        </div>
                        <div class="fluidbox">
	                        <p class="p12 p-item">
	                            <label class="lab-item">&nbsp;</label>
	                            <font style="color: #808080">
	                                图片建议像素（或保持该比例）：宽800，高800
	                            </font>
	                        </p>
	                    </div>
                    </span>
            </dd>
            <!-- 预览图片 -->
            <dd id='previewImgBox' style="display: none" style="text-align:center">
                <input type="hidden" id="qrCode" name="qrCode"/>
                <ul class='preview-img' id="preview-img">
                    <li style="display: none" id="prewtemplage">
                        <div class='img'>
                            <img width='150' height='150'>
                            <div class='img-box'>
                                <a class='del-img' href='javascript:void(0);' style="align-content: center">删除</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </dd>
            <!-- end -->
            <div class="dd-group">
			    <input type="button" id="submitBtn" name="submitBtn" class="btn" value="确定" style="align-content: center" />
            </div>
        </div>
	</table>
</form>