<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/order/complaint"/>

<style>
.dl-group p img {
	max-width: 120px;
	float: left;
}

.fluidbox .p6 label a {
	font-weight: 900;
	color: #00F;
}
</style>

<script language="javascript">
	$(function() {
        var options = {
            type:"POST",
            url:'${currentBaseUrl}/doAudit',
            dataType:"json",
            async : false,
            success: function (data) {
                if(data.success){
                    window.location.href='${currentBaseUrl}';
                }else{
                    alert(data.message);
                }
            },
            error:function(){
                alert("异常，请重试！");
            }
        };

		$("#back").click(function() {
			window.location.href = '${currentBaseUrl}';
		});

		$("#btn").click(function() {
			$.messager.confirm('确认', '确定提交申诉么？', function(r) {
				if (r) {
					$("#stateType").val('agree');
					$.messager.progress({
						text : "提交中..."
					});
                    $('#addForm').ajaxSubmit(options);
				}
			});
		});

	})

	function closeWin() {
		$('#newstypeWin').window('close');
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			投诉仲裁 <span class="s-poar"> <a class="a-back"
				href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			name="addForm" enctype="multipart/form-data"
			action="${currentBaseUrl}/doAudit"> <input type="hidden"
				value="${(obj.id)!''}" name="id"> <input type="hidden"
				id="stateType" name="stateType">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>投诉信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">投诉人账户: </label> <label>${obj.userName!}</label>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">订单号: </label> <label>${obj.orderSn!}</label>
						</p>
					</div>
					
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">投诉来源: </label> <label>${obj.source!}</label>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">问题描述: </label> <label>${obj.question!}</label>
						</p>
					</div>

					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">投诉商品: </label><label>${obj.orderProductName!}</label>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">投诉内容: </label><label>${obj.content!}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">投诉图片: </label>
                            <#if obj.image?? && "" != obj.image>
                            <label>
                                <a href="${domainUrlUtil.EJS_IMAGE_RESOURCES}${obj.image!''}" target="_blank">查看图片</a>
							</label>
                            </#if>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">投诉时间: </label><label>${obj.complaintTimeStr!}</label>
						</p>
					</div>

				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>申诉信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item">申诉意见: </label>
						<textarea name="optContent" id="optContent" cols="90" rows="3"></textarea>
					</div>

				</dd>
                <dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item">申诉图片: </label>
						<input type="file" name="pic">
					</div>

				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="btn" class="btn" value="提交" />
				<input type="button" id="back" class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/seller/commons/_detailfooter.ftl" />
