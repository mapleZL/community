<#include "/h5/commons/_head.ftl" />

<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/css/list-search.css">

<body>
	<!-- 头部 -->
	<header id="header">
		<div class="flex flex-align-center head-bar">
			<div class="flex-1 text-left">
				<!-- <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/product/${(productId)!0}.html?type=${(type)!0}"> -->
				<a href="javascript:history.back();">
					<span class="fa fa-angle-left">
					</span>
				</a>
			</div>
			<div class="flex-2 text-center">商品详情</div>
			<div class="flex-1 text-right" id="fa-bars">
				
			</div>
		</div>
		<#include "/h5/commons/_hidden_menu.ftl" />
	</header>
	<!-- 头部 end-->

	<!-- S 主体 -->
	<section style="padding: 10px; overflow: hidden; background: #fff;margin-bottom: 40px;">
		<form action="${(domainUrlUtil.EJS_URL_RESOURCES)!}/eosearch.html" method="post">
		<div class='search-form' style="overflow: hidden;">
			<input type='text' class="input-search" name="sku" value="${sku!''}" placeholder="请输入SKU码">
			<input type='submit' value='查询' class='input-btn'>
		</div>
		</form>
		<div class="prompt-box" style="display: none;">请在输入框中输入商品编码查询！</div>
		<div style="display: block;margin-bottom: 0px;">
		<#if pglist?? && pglist?size &gt; 0>
		<#list pglist as pg>
		<form action="" method="POST"  name="cartForm">
			<input  type="hidden" name="productId" value="${(pg.productId)!}">
			<input  type="hidden" name="productGoodId" value="${(pg.id)!}">
			<input  type="hidden" name="number" value="10">
			<input  type="hidden" name="sellerId" value="${(pg.sellerId)!0}">
			
			<div class="order-d-box">
				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(pg.productId)!}.html" class="block">
					<dl class="flex order-d-dl">
						<dt style="width: 80px; height: 80px;">
							<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(pg.images)!}' 
								onerror="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg'">
						</dt>
						<dd class="padl flex-2">
							<div class="product_name">
								${(pg.productName)!}<br/>
								${(pg.sku)!}
							</div>

							<div class="clr53 font12" style="font-size:18px">
								&yen; <font>${(pg.mallMobilePrice)!}</font>
								<#if pg.mallOriginalPrice ?? && pg.mallOriginalPrice != pg.mallMobilePrice>
								<span style="color: black;text-decoration: line-through;font-size: 12px;"><span>¥</span>${(pg.mallOriginalPrice)!""}</span>
								</#if>
							</div>
							<div class="font12 pad-top" style="display: none;">${(pg.normName)!}</div>
						</dd>
					</dl>
				</a>
				<button class="order-d-btn" type="button" onclick="add2cart(this)">加入进货单</button>
			</div>
		</form>
		</#list>
		<#else>
		<div class="order-d-box">
			<div class="no-products">
				请输入SKU码进行查询
			</div>
		</div>
		</#if>
		</div>
		
		<div id="product_list_more"></div>
			
		<#if pglist?? && pglist?size==pagesize>
			<div id="product_list_more_json" class="text-center font14 pad-top2">查看更多<i class="fa fa-angle-double-down"></i></div>
			<div id="product_list_more_json_wait" class="text-center font14 pad-top2" style="display:none;">请稍等，正在努力加载中...</div>
			<div id="product_list_more_json_no" class="text-center font14 pad-top2" style="display:none;">已展示全部商品</div>
		<#elseif sku?? && sku!="">
			<div id="product_list_more_json_no" class="text-center font14 pad-top2">已展示全部商品</div>
		</#if>
		<input type="hidden"  name="list_page" id="list_page" value="1" />
		
	</section>
	<!-- E 主体 -->
	
	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

<script type="text/javascript">
	function add2cart(obj){
		//未登录不能添加进货单
		if (!isUserLogin()) {
			//showid('ui-dialog');
			window.location.href=domain+"/login.html";
			return;
		}
		var form_ = $(obj).closest("form[name='cartForm']");
// 		console.info(form_.find("input[type='hidden'][name='productGoodsId']").val());
		 $.ajax({
			type : "POST",
			url :  domain+"/cart/addtocart.html",
			data : form_.serialize(),
			dataType : "json",
			success : function(data) {
				if(data.success){
					window.location.href=domain+"/cart/detail.html";
				}else{
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("请稍后再试！");
			}
		});
	}
	function add3cart(productId,productGoodId,number){
		//未登录不能添加进货单
		if (!isUserLogin()) {
			//showid('ui-dialog');
			window.location.href=domain+"/login.html";
			return;
		}
// 		console.info(form_.find("input[type='hidden'][name='productGoodsId']").val());
		 $.ajax({
			type : "POST",
			url :  domain+"/cart/addtocart.html",
			data : {
				productId:productId,
				productGoodId:productGoodId,
				number:number
			},
			dataType : "json",
			success : function(data) {
				if(data.success){
					window.location.href=domain+"/cart/detail.html";
				}else{
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("请稍后再试！");
			}
		});
	}
	
	$(function(){
		$("#product_list_more_json").click(function(){
			
			var list_page = $("#list_page").val();
			list_page++;
			$("#list_page").val(list_page);
			var urljson = "${(domainUrlUtil.EJS_URL_RESOURCES)!}/eosearchJson.html?sku=${sku!''}&pageNum=" + list_page;
			var listJsonHtml = "";
			$("#product_list_more_json_wait").show();
			$("#product_list_more_json").hide();
			$("#product_list_more_json_no").hide();
			$.ajax({
	            type:"get",
	            url: urljson,
	            dataType: "json",
	            cache:false,
	            success:function(data){
	                if (data.success) {
	                    $.each(data.rows, function(i, pg){
	                    	listJsonHtml += "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + pg.productId+ ".html' class='block'>";
	                    	listJsonHtml += "<dl class='flex list-dl'>";
	                    	listJsonHtml += "<dt><img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + pg.images + "' width='80' height='80' onerror=\"this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg'\"></dt>";
	                    	listJsonHtml += "<dd class='padl flex-2'>";
	                    	listJsonHtml += "<div class='product_name'>" + pg.productName + "</div>";
	                    	listJsonHtml += "<div class='product-desript'>";
	                    	listJsonHtml += "<p class='clr53' style='font-size:18px'>￥<font>" + pg.mallMobilePrice + "</font>";
	                    	//pg.mallOriginalPrice ?? && pg.mallOriginalPrice != pg.mallMobilePrice
	                    	if (pg.mallOriginalPrice != "" && pg.mallOriginalPrice != pg.mallMobilePrice) {
	                    		//listJsonHtml += '<span style="color: black;text-decoration: line-through;font-size: 12px;"><span>¥</span>' + pg.mallOriginalPrice + '</span>';
	                    	}
	                    	listJsonHtml += "</p>";
	                    	if(pg.productStock > 0) {
	                    		//listJsonHtml += "<p class='ratings'><span>有货</span>";
	                    	}else {
	                    		//listJsonHtml += "<span>无货</span>";
	                    	}
	                    	//listJsonHtml += "&nbsp;&nbsp;";
	                    	//listJsonHtml += "<font>" + product.commentsNumber + "</font>条评价</p>";
	                    	//加入进货单
	                    	listJsonHtml += "<button type=\"button\" onclick=add3cart(" + pg.productId + "," + pg.id + "," + 10 +") class=\"order-d-btn\">加入进货单</button>";
	                    	listJsonHtml += "</div></dd></dl></a>";
	                    })
	                    $("#product_list_more").append(listJsonHtml);
	                    if ((data.total) == ${(pagesize)!}) {
	                        $("#product_list_more_json").show();
	                        $("#product_list_more_json_no").hide();
	                        $("#product_list_more_json_wait").hide();
	                    } else {
	                        $("#product_list_more_json").hide();
	                        $("#product_list_more_json_no").show();
	                        $("#product_list_more_json_wait").hide();
	                    }
	                }
	            }
	        });
	        
		});
		
	})
</script>
</body>
</html>