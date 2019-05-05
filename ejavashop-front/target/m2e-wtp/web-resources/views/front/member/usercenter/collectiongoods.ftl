<#include "/front/commons/_headbig.ftl" />
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/My97DatePicker/WdatePicker.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
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
					<a href='javascript:void(0)'>收藏的商品</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />


				<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
				<h3>收藏的商品</h3>
				<div class='mc'>
					<div class='fav-goods-list'>
						<ul>
							<#if productList??>
								<#list productList as product>
									<li>
										<div class='fav-goods-item'>
											<a <#if product?? && product.productState == 6> href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(product.productId)!0}.html'<#else>href = 'javascript:void(0)'</#if> target="_blank">
												<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.productLeadLittle}' <#if product?? && product.productState == 7>style="opacity:0.5;"</#if>>
											</a>
											<a class="goods-delete" data-id="${product.productId}" href="javascript:void(0)"></a>
											<div class='p-name'>
												<a <#if product?? && product.productState == 6>href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(product.productId)!0}.html'<#else>href = 'javascript:void(0)' style="color:#D6E3E9;"</#if> target="_blank" title="${(product.productName)!''}">${(product.productName)!''}</a>
											</div>
											<div class='p-price'>
												<strong <#if product?? && product.productState == 7>style="color:#D6E3E9;"</#if>>￥${(product.mallPcPrice)!''}</strong>
											</div>
											<#--<div class='p-comments'>
												<span class='c-txt'>
													<a href='javascript:void(0)'>${(product.commentsNumber)!''}人评价</a>
												</span>
												<span class='c-perc'>好评度%</span>
											</div>
											<div class='p-opbtns'>
												<a href='javascript:void(0);' target="_blank" class='btn btn-default'>加入进货单</a>
												<a href='javascript:void(0);' onclick="unfollowProduct('${(product.productId)!''}')" class='btn btn-default'>取消收藏</a>
											</div>
											-->
										</div>
									</li>
								</#list>
							</#if>
						</ul>
					</div>
				</div>
				<!-- 分页 -->
				<#include "/front/commons/_pagination.ftl" />
			
			</div>
			<!-- end -->
		</div>
	<script type="text/javascript">
	$(function(){
		//控制左侧菜单选中
		$("#collectionGoods").addClass("currnet_page");
		
		$(".goods-delete").click(function() {
			unfollowProduct($(this));
		});
	});
	
	//取消关注
	function unfollowProduct(obj){
		if(confirm("是否取消关注")){
			var id = obj.data("id");
			$.ajax({
				type:"GET",
				url:domain+"/member/cancelcollectproduct.html",
				dataType:"json",
				async : false,
				data : {productId:id},
				success:function(data){
					if(data.success){
						obj.parent().parent().remove();
					}else{
						jAlert(data.message);
					}
				},
				error:function(){
					jAlert("异常，请重试！");
				}
			});
		}
	}
		
	</script>
	
<#include "/front/commons/_endbig.ftl" />
