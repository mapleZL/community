<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/productback.css">

		<div class='container w'>
			<div class='breadcrumb'>
				<strong class='business-strong'>
					<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>首页</a>
				</strong>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>大袜网</a>
				</span>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>申诉申请</a>
				</span>
			</div>
		</div>
		
<form id ="complainForm" enctype="multipart/form-data">
<!-- 隐藏域 -->
<input type="hidden" name="sellerId" value="${(orderProduct.sellerId)!''}">
<input type="hidden" name="orderId" value="${(order.id)!''}">
<input type="hidden" name="productBackId" value="${(productBackId)!''}">
<input type="hidden" name="productExchangeId" value="${(productExchangeId)!''}">
<!-- 网单id -->
<input type="hidden" name="orderProductId" value="${(orderProduct.id)!''}">

		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>申诉</h3>
				<table class='table_1' id="refushtable" cellspacing="0" cellpadding="0" border='1'>
					<tbody>
						<tr>
							<th>商品名称</th>
							<th>购买数量</th>
						</tr>
						<tr>
							<td>
								<ul class='list-proinfo'>
									<li>
										<#if orderProduct??>
											<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(orderProduct.productId)!0}.html' target="_blank">
												<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${orderProduct.productLeadLittle}' width='50' height='50' title='${(orderProduct.productName)!''}'>
											</a>
											<div class='p-info-name'>
													<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(orderProduct.productId)!0}.html' target='_blank'>${(orderProduct.productName)!''}</a>
											</div>
										</#if>
									</li>
								</ul>
							</td>
							<td>${(orderProduct.number)!''}</td>
						</tr>
					</tbody>
				</table>
				<div class='apply-form' id='consignee-form'>
					<div class='repair-steps'>
						<div class='repair-step repair-step-curr'>
							
							<!-- 问题描述 -->
							<div class='miaoshuDiv'>
								<!-- 问题描述 -->
								<div class='sellerPrompt' style='height:132px'>
									<span class='label'>
										<em>*</em>
										<span>投诉内容：</span> 
									</span>
									<div class='fl'>
										<textarea name="content" class='miaoshu-text'></textarea>
										<div style='text-align:right;'>10-500字</div>
									</div>
									<em class='em-errMes'></em>
								</div>
                                <div class='sellerPrompt' style='height:40px'>
									<span class='label'>
										<em>*</em>
										<span>投诉图片：</span>
									</span>
									<div class='fl'>
                                        <input type="file" id="pic" name="pic"/>
                                    </div>
								</div>

								<div class='sellerPrompt'>
									<div class="button-center">
										<a href='javascript:void(0)' class='btn btn-default btn-7'>提交</a>
									</div>
								</div>
								<!-- end -->
							</div>
							<!-- end -->
							
							
						</div>
					</div>
				</div>
			</div>
 </div>
 
</form>
<script type="text/javascript">
	

	$(function(){
		//控制左侧菜单选中
		$("#complainlist").addClass("currnet_page");
		
		//校验
		jQuery("#complainForm").validate({
			errorPlacement : function(error, element) {
				var obj = element.parent().siblings(".em-errMes").css('display', 'block');
				error.appendTo(obj);
			},  
	        rules : {
	            "content":{required:true,minlength:10,maxlength:500}
	        },
	        messages:{
	            "content":{required:"请输入内容",minlength:"不能小于10个字呦",maxlength:"不能超过500个字呦"}
	        }
	    });


        var params = $('#complainForm').serialize();
        var options = {
            type:"POST",
            url:domain+"/member/savecomplain.html",
            dataType:"json",
            async : false,
            data : params,
            success: function (data) {
                if(data.success){
                	//jAlert("保存成功");
                    //window.location.href=domain+'/member/order.html';
                    
                    jAlert('保存成功', '提示',function(){
                    	window.location.href=domain+'/member/order.html'
					});
                }else{
                	jAlert(data.message);
                    $(".btn-default").removeAttr("disabled");
                }
            },
            error:function(){
            	jAlert("异常，请重试！");
                $(".btn-default").removeAttr("disabled");
            }
        };
		
		//点击提交按钮事件
		$(".btn-default").click(function(){
			if($("#complainForm").valid()){
				$(".btn-default").attr("disabled","disabled");
                $('#complainForm').ajaxSubmit(options);
			}
			
		});
	});
	
</script>

<#include "/front/commons/_endbig.ftl" />
