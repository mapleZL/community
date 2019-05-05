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
					<a href='javascript:void(0)'>申诉信息查看</a>
				</span>
			</div>
		</div>
		
<form id ="complainForm" >
<!-- 隐藏域 -->
<input type="hidden" name="sellerId" value="${(orderProduct.sellerId)!''}">
<input type="hidden" name="orderId" value="${(orderProduct.ordersId)!''}">
<input type="hidden" name="productBackId" value="${(info.productBackId)!''}">
<input type="hidden" name="productExchangeId" value="${(info.productExchangeId)!''}">
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
							<ul>
							  <li class="list-group-item2">
							  	<span class='s-infotitle complain-title'>投诉时间：</span>
							  	<span >${(info.complaintTime?string("yyyy-MM-dd HH:mm:ss"))!'' }</span>
							  	
							  </li>
							  <li class="list-group-item2">
							  	<span class='s-infotitle complain-title'>状态：</span>
							  	<span>
							  		<#if info.state??>
									  		<#assign state = info.state/>
									  		<#if state==1>待审核
										  		<#elseif state==2>投诉不通过
								  				<#elseif state==3>投诉通过
								  				<#elseif state==4>卖家申诉待审核
								  				<#elseif state==5>卖家申诉不通过
								  				<#elseif state==6>卖家申诉通过
								  				<#else>审核中
									  		</#if>
							  		    </#if>
							  	</span>
							  </li>
							  <li class="list-group-item2">
							  	<span class='s-infotitle complain-title'>投诉内容：</span>
							  	<span>${(info.content)!'' }</span>
							  </li>
							  <li class="list-group-item2">
							  	<span class='s-infotitle complain-title'>投诉图片：</span>
							  	<span>
							  	<#if info.image?? && info.image != ''>
							  		<a href="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(info.image)!''}" target='_blank'>查看</a>
							  	<#else>
							  		无
							  	</#if>
							  	</span>
							  </li>
							  
							  <li class="list-group-item2">
							  	<span class='s-infotitle complain-title'>商家申诉时间：</span>
							  	<span>
							  		<#if info.sellerComplaintTime??>
							  			${(info.sellerComplaintTime?string("yyyy-MM-dd HH:mm:ss"))!'' }
							  		</#if>
							  	</span>
							  </li>
							  <li class="list-group-item2">
							  	<span class='s-infotitle complain-title'>商家申诉内容：</span>
							  	<span>${(info.sellerCompContent)!''}</span>
							  </li>
							  <li class="list-group-item2">
							  	<span class='s-infotitle complain-title'>商家申诉图片：</span>
							  	<span>
							  	<#if info.sellerCompImage?? && info.sellerCompImage != ''>
							  		<a href="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(info.sellerCompImage)!''}" target='_blank'>查看</a>
							  	<#else>
							  		无
							  	</#if>
							  	</span>
							  </li>
							  
							  <li class="list-group-item2">
							  	<span class='s-infotitle complain-title fl'>平台意见：</span>
							  	<span>${(info.optContent)!'无' }</span>
							  </li>
							</ul>
								<br/><br/>
							<!-- end -->
							
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
		
		//点击提交按钮事件
		$(".btn-default").click(function(){
			
			if($("#complainForm").valid()){
				
				$(".btn-default").attr("disabled","disabled");
				var params = $('#complainForm').serialize();
				  $.ajax({
					type:"POST",
					url:domain+"/member/savecomplain.html",
					dataType:"json",
					async : false,
					data : params,
					success:function(data){
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
				}); 
			}
			
		});
	});
	
</script>

<#include "/front/commons/_endbig.ftl" />
