<#include "/front/commons/_headbig.ftl" />

<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/list.css">
		<div class='container'>
			<div class='hot-salse'>
				<div id='HotSalse' class='hot-salse-main'>
					<div class='hot-recommend'>热卖推荐</div>
					<div class='recommend-details mc'>
						<ul class='mc clearfix list-ul'>
							 <div id="cateTopId"></div>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class='container'>
			<div class='level-box clearfix'>
				<div class='level-nav'>
					<div class='leve-nav-main clearfix'>
						<div class='level-nav-item fist-level'>
							<a href='#' class='level-link'>搜索结果</a>
							<i class='level-right'>></i>
						</div>
						<div class='level-nav-item'>
						${(keyword)!''}
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class='container' id='shop-tag'>
			<!-- 商品筛选 -->
			<!-- end -->
			<!-- shop-list -->
			<div class='select-main' id='SelectMain'>
				<div class='shop-list'>
					<div class='shop-list-wrap'>
						<!-- 商品展示列表页 -->
						<div class='main-item'>
						
							<!-- 排序 Start-->
						    <div class='filter' id='shop-filter'>
								<div class='filter-line top'>
							 		<div class='composite-sort'>
							 			<a href="javascript:void(0)" onclick="solrSort('0')" <#if sortPrice?? && sortPrice == 'normal' && sortStock == 'normal'>class='btn-sort' </#if>>综合排序<i></i></a> 
										<#if sortPrice??>
											<#if sortPrice == 'normal'>
												<a href="javascript:void(0)" onclick="solrSort('priceDesc')">价格<i></i></a>
											<#else>
												<#if sortPrice == 'ASC'>
													<a href="javascript:void(0)" onclick="solrSort('priceAsc')" class='btn-sort up'>价格<i></i></a>
												</#if>
												<#if sortPrice == 'DESC'>
													<a href="javascript:void(0)" onclick="solrSort('priceDesc')" class='btn-sort down'>价格<i></i></a>
												</#if>
										   </#if>
										 </#if>
										  <!-- 库存排序区域 -->
									     <#if sortStock??>
											<#if sortStock == 'normal' >
												<a href="javascript:void(0)" onclick="solrSort('stockDesc')">库存<i></i></a>
											<#else>
												<#if sortStock == 'ASC'>
													<a href="javascript:void(0)" onclick="solrSort('stockAsc')" class='btn-sort up'>库存<i></i></a>
												</#if>
												<#if sortStock == 'DESC'>
													<a href="javascript:void(0)" onclick="solrSort('stockDesc')" class='btn-sort down'>库存<i></i></a>
												</#if>
										  </#if>
									   </#if>
									<div class='shop-total'  style="float: left;padding-left: 15px; padding-top: 4px;">
										共 &nbsp;<span>${(count)!""}&nbsp;</span>个商品
									</div>
									</div>
								</div>
							</div>
							<!-- 排序 End-->
							
							<ul class='gl-warp clearfix'>
						<#if producListVOs??>
							<#list producListVOs as producListVO>
								<li class='item fl'>
									<div class='gl-wrap-i'>
										<div class='wrap-pic limit'>
											<a  target="_blank"  class='pro-img fl b1' href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(producListVO.id)!0}.html'>
												<#if producListVO.productStock &gt; 0>
													<img width="200" height="200" data-original='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(producListVO.masterImg)!""}'>
												<#else>
													<img style="position: absolute;top:30px; left:10px;" data-original='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/sold_out.png'>
													<img width="200" height="200" data-original='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(producListVO.masterImg)!""}'>
												</#if>
											</a>
										</div>
										<div class='pro-price limit' style="float: left;margin-top: 10px;">
											<p class='price-number fl'>
												<b>¥</b>
												<strong>${(producListVO.mallPcPrice)!""}</strong>
										<#if producListVO.marketPrice ?? && producListVO.marketPrice &gt; producListVO.mallPcPrice>
											<span style="color: black;text-decoration: line-through;font-size: 12px;">
												<i>¥</i>
												${(producListVO.marketPrice)!"0.00"}
											</span>
										</#if>
											</p>
										</div>
										<div class='look-goods limit' style="float: left;margin-top: 10px;
										<#if producListVO.marketPrice ?? && producListVO.marketPrice &gt; producListVO.mallPcPrice>
										margin-left: 77px;
										<#else>
										margin-left: 125px;
										</#if>">
										<#if producListVO.productStock gt 0>
											<span class='font-pale fl'>有货</span>
										<#else>
											<span class='font-pale fl'>无货</span>
										</#if>
										<#--<span class='shop-comment fr fl'>${(producListVO.commentsNumber)!"0"} 条评价</span>-->
										</div>
										<div class='pro-name limit' style="clear: both;">
											<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(producListVO.id)!0}.html" class='' target='_blank'>
											<#noescape>${(producListVO.name1)!""}</#noescape>
											</em></a>
										</div>
										<#--
										<div class='shops-details-btn shoping-cart'>
											<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(producListVO.id)!0}.html'  target="_blank" class='search-btn b-normal btn-light cart-btn fl'>查看详情</a>
											<a href="javascript:void(0);" onclick="addCart('${(producListVO.id)!''}','${(producListVO.sellerId)!''}')" class='search-btn b-normal btn-light cart-btn fr'>加入进货单</a>
										</div>
										-->
									</div>
								</li>
						  </#list>
				    </#if>
							</ul>
						</div>
						<div class='page'>
							<!-- 分页 -->
							<#include "/front/commons/_paginationutil.ftl" />
						</div>
					</div>
				</div>
				<div class='promote-shop'>
					<div class='promote-bar'>
						<!-- 用户最终购买了 -->
						<div class='promote-goods'>
							<div class='mt'>
								<h3>推广商品</h3>
							</div>
							<div class='goods-mc'>
								<ul>
				    				 <div id="cateLeftId"></div>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end -->
		</div>

<script type="text/javascript">
window.onload=function(){
	cateTopAjax();
	cateLeftAjax();
}


function cateTopAjax() {
	var cateTopHtml = "";						
	$.ajax({
        type:"get",
        url: "${(domainUrlUtil.EJS_URL_RESOURCES)!}/recommend.html?q_recommendType=801",
        dataType: "json",
        cache:false,
        success:function(data){
            if (data.success) {
                $.each(data.rows, function(i, recommend){
                	var product = recommend.product;
                	cateTopHtml += "<li><a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' class='recommend-img' target='_blank'>";
                    cateTopHtml += "<img width='100 height='100' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + product.masterImg + "'></a>";
                    cateTopHtml += "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' target='_blank' class='shop-name'>";
                    cateTopHtml += "<em>" + product.name1 + "</em></a><div class='shop-price'><span>特价：</span><strong>";
                    cateTopHtml += "<em class='shop-number'>￥" + product.mallPcPrice + "</em></strong></div><div class='shop-snapped'>";
                    cateTopHtml += "<a href='javascript:void(0);' onclick=addCart(" + product.id + "," + product.sellerId + ")" + " class='btn btn-default' target='_blank'>立即抢购</a>";
                    cateTopHtml += "</div></li>";
                })
                $("#cateTopId").append(cateTopHtml);
            }
        }
    });
}

function cateLeftAjax() {
	var cateLeftHtml = "";						
	$.ajax({
        type:"get",
        url: "${(domainUrlUtil.EJS_URL_RESOURCES)!}/recommend.html?q_recommendType=802",
        dataType: "json",
        cache:false,							
        success:function(data){
            if (data.success) {
                $.each(data.rows, function(i, recommend){
                	var product = recommend.product;
                	cateLeftHtml += "<li><div class='goods-img'>";
                    cateLeftHtml += "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' target='_blank' title='" + "product.name1'>";
                    cateLeftHtml += "<img width='200' height='200' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + product.masterImg + "'></a></div>";
                    cateLeftHtml += "<div class='goods-price'><span>特价：</span><strong>￥" + product.mallPcPrice + "</strong></div><div class='goods-name'>";
                    cateLeftHtml += "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' target='_blank' title='" + product.name1 + "'>" + product.name1 + "</a>";
                    cateLeftHtml += "</div></li>";
                })
                $("#cateLeftId").append(cateLeftHtml);
            }
        }
    });
}

	/**
		添加进货单
	*/
	function addCart(productId,sellerId){
		//未登录不能添加进货单
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
		$.ajax({
			type : "POST",
			url :  domain+"/cart/addtocart.html",
			data : {productId:productId,sellerId:sellerId},
			dataType : "json",
			success : function(data) {
				if(data.success){
					//跳转到添加进货单成功页面
					location.href=domain+"/cart/add.html"; 
					//window.open(domain+"/cart/add.html");  
				}else{
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});
	}
	
	//solr 排序
	function solrSort (type) {
		var priceSort = $('#priceSort');
		var stockSort = $('#stockSort');
		if ( type == 'priceDesc') {
			if (priceSort.val() == 'DESC') {
				priceSort.val('ASC');
			} else {
				priceSort.val('DESC');
			}
			stockSort.val('normal');
		} else if (type == 'priceAsc') {
			if (priceSort.val() == 'ASC') {
				priceSort.val('DESC');
			} else {
				priceSort.val('ASC');
			}
			stockSort.val('normal');
		} else if (type == 'stockAsc') {
			if (stockSort.val() == 'ASC') {
				stockSort.val('DESC');
			} else {
				stockSort.val('ASC');
			}
			priceSort.val('normal');
		} else if (type == 'stockDesc') {
			if (stockSort.val() == 'DESC') {
				stockSort.val('ASC');
			} else {
				stockSort.val('DESC');
			}
			priceSort.val('normal');
		} else {
			priceSort.val('normal');
			stockSort.val('normal');
		}
		$('#search_form').submit();
	}
	

//记录搜索日志存储到数据库中	
var memberId = 0;
<#if user??>
	memberId = ${(user.id)!0};
</#if>
var keyword = "${(keyword)!''}";
document.write('<img width="1" height="1" style="position:absolute;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/save_search_logs.html?keyword='+ keyword + '&memberId='+ memberId + '" />');
</script>
<!-- 登录弹出框 -->
<#include "/front/commons/logindialog.ftl" />
<#include "/front/commons/_endbig.ftl" />
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/list.js'></script>