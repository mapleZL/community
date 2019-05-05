<#include "/front/commons/_headbig.ftl" />

<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/list.css">
		<p><br/></p>
		<div class='container'>
			<div class='level-box clearfix'>
				<div class='level-nav'>
					<div class='leve-nav-main clearfix'>
					
						<div class='level-nav-item fist-level'>
							<a href='' class='level-link'>${(productCatePid.name)!}</a>
							<i class='level-right'>></i>
						</div>
						
						<div class='level-nav-item'>
							<b>${(productCate.name)!}</b>
						</div>
						
					</div>
				</div>
			</div>
		</div>

		<div class='container' id='shop-tag'>
			<div id='TagContainer' class='selector'>
				<div class='select-price selector-fold'>
					<div class='select-brand'>
						<div class='select-key'style='padding-top:4px;'>
							<span>分类：</span>
						</div>
						<div class='select-value'>
							<div class='select-brand-list'>
								<ul class='value-list'>
									<#if productCates??>
										<#list productCates as productCate>
											<li><a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cate/${(productCate.id)!}.html' target='_blank'><i></i>${(productCate.name)!}</a></li>
										</#list>
									</#if>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end -->

			<!-- shop-list -->
			<div class='select-main' id='SelectMain'>

			    <!-- 列表此处更改 -->
		<#if productListCate2s??>
		    <#list productListCate2s as productListCate2>
				<div class='shop-list' style="float:none;">
				    <h2 class="classifyh2">${productListCate2.productCate.name}</h2>
					<div class='shop-list-wrap classify_list_wrap' >
						
						<!-- 商品展示列表页 -->
						<div class='main-item'>
							<ul class='gl-warp clearfix'>
							
							<#if productListCate2.products??>	
							<#list productListCate2.products as product>
								<li class='item fl'>
									<div class='gl-wrap-i'>
										<div class='wrap-pic limit'>
											<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(product.id)!0}.html' class='' title='${(product.name1)!""}'>
												<img width="200" height="200" src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(product.masterImg)!""}'>
											</a>
										</div>
										<div class='pro-price limit'>
											<p class='price-number fl'>
												<b>¥</b>
												<strong>${(product.mallPcPrice)!""}</strong>
											</p>
											<div class='label-box fr'>
												<i class='label fl'>降</i>
											</div>
										</div>
										<div class='look-goods limit'>
											<#if product.productStock gt 0>
											<span class='font-pale fl'>有货</span>
											<#else>
											<span class='font-pale fl'>无货</span>
											</#if>
											<span class='shop-comment fr fl'>${(product.commentsNumber)!"0"} 条评价</span>
										</div>
										<div class='shops-details-btn shoping-cart'>
											<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(product.id)!0}.html'  target="_blank" class='search-btn b-normal btn-light cart-btn fl'>查看详情</a>
											<a href="javascript:void(0);" onclick="addCart('${(product.id)!''}','${(product.sellerId)!''}')" class='search-btn b-normal btn-light cart-btn fr'>加入进货单</a>
										</div>
									</div>
								</li>
							</#list>	
							</#if>	
							</ul>
						</div>
						
					</div>
				</div>
			</#list>
		</#if>
				
			<!-- end -->
		</div>
<#include "/front/commons/logindialog.ftl" />		
<#include "/front/commons/_endbig.ftl" />

<script type="text/javascript">
	function addCart(productId,sellerId) {
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
		<#--
		<#if Session.memberSession??>
			 <#assign user = Session.memberSession.member>
		</#if>
		<#if !user??>
			jAlert("请登录之后再添加进货单！");   
			return false;
		</#if>
		-->
		$.ajax({
			type : "POST",
			url :  domain+"/cart/addtocart.html",
			data : {productId:productId,sellerId:sellerId},
			dataType : "json",
			success : function(data) {
				if(data.success){
						//跳转到添加进货单成功页面
						window.open(domain+"/cart/add.html");  
				}else{
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});
	}
</script>