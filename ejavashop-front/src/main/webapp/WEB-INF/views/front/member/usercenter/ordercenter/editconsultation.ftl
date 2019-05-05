<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/user.css"></link>
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/base.css">
<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
		<@form.form id="addconsultationform">
			<input type="hidden" id="askinfoId"  name="id" value="${(askinfo.id)!''}"/>
			<input type="hidden" name="productId" value="${productId}"/>
			<input type="hidden" name="sellerId" value="${sellerId}"/>

			<!-- 编辑咨询 -->
			<div class='box-t'></div>
			<div class='evaluat-form'>
				<div class='item'>
					<em style="color:red;">*</em> 咨询内容：
					<div class='cont'>
						<textarea class='area area01' name='askContent'>${(askinfo.askContent)!''}</textarea>
						<span class='msg-error-01 hide'></span>
					</div>
				</div>
				<div >
					<a href='javascript:void(0)' class='btn btn-danger cr '>提交</a>
				</div>
			</div>
	</@form.form>	
		
	<script type="text/javascript">
		$(function() {
			//校验
			jQuery("#addconsultationform").validate(
					{
						errorPlacement : function(error, element) {
							var obj = element.nextAll(".msg-error-01")
									.removeClass('hide');
							error.appendTo(obj);
						},
						rules : {
							"askContent" : {
								required : true,
								minlength : 10,
								maxlength : 500
							}
						},
						messages : {
							"askContent" : {
								required : "请输入内容",
								minlength : "不能小于10个字呦",
								maxlength : "不能超过500个字呦"
							}
						}
					});

			$(".btn-danger").click(function() {

				if ($("#addconsultationform").valid()) {
					$(".btn-danger").attr("disabled", "disabled");
					var params = $('#addconsultationform').serialize();
					$.ajax({
						type : "POST",
						url : domain + "/member/savequestion.html",
						dataType : "json",
						async : false,
						data : params,
						success : function(data) {
							if (data.success) {
								//jAlert("保存成功");
								//location.replace(location.href);
								
								jAlert('保存成功', '提示',function(){
									location.replace(location.href)
								});
							} else {
								jAlert(data.message);
								$(".btn-danger").removeAttr("disabled");
							}
						},
						error : function() {
							jAlert("异常，请重试！");
							$(".btn-danger").removeAttr("disabled");
						}
					});
				}

			});
		});
	</script>
