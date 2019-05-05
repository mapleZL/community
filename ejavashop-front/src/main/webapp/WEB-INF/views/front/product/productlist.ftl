<#include "/front/commons/_headbig.ftl" />

<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/list.css">
		<#--
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
		-->
		<#if productTypeAttrWhereAll?? || productCate2s?? || productCate3s?? || brandName?? || productTypeAttrWhereAlls??>
		<div class='container'>
			<div class='level-box clearfix' style="margin-top:10px;">
				<div class='level-nav'>
					<div class='leve-nav-main clearfix'>
						<div class='level-nav-item fist-level' style="display: none;">
							<a href='#' class='level-link'>${(productCatePidPid.name)!""}</a>
							<i class='level-right'>></i>
						</div>
						<div class='level-nav-item' style="display: none;">
							<div class='menu-drop'>
								<div class='trigger'>
									<span class='trigger-name'>${(productCatePid.name)!""}</span>
									<i class='menu-drop-arrow'></i>
								</div>
								
								<div class='menu-drop-main'>
									<ul class='menu-drop-list'>
											<#if productCate2s??>
												<#list productCate2s as productCate2s>
												<li>
													<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/list/${(productCate2s.id)!""}.html'>${(productCate2s.name)!""}</a>
												</li>
												</#list>
											</#if>
									</ul>
								</div>
								
							</div>
							<i class='level-right'>></i>
						</div>
						<div class='level-nav-item' style="display: none;">
							<div class='menu-drop'>
								<div class='trigger'>
									<span class='trigger-name'>${(productCate.name)!""}</span>
									<i class='menu-drop-arrow'></i>
								</div>
								
								<div class='menu-drop-main'>
									<ul class='menu-drop-list'>
											<#if productCate3s??>
												<#list productCate3s as productCate3s>
												<li>
													<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cate/${(productCate3s.id)!""}.html'>${(productCate3s.name)!""}</a>
												</li>
												</#list>
											</#if>
									</ul>
								</div>
								
							</div>
							<i class='level-right'>></i>
						</div>
						<#if brandName??>
							<div class='shop-total selector-set'>
								<a href="javascript:void(0)" onclick="filterBrand(0)" class="ss-item"><b>${(brandName)!""}</b><i></i></a>
							</div>
						</#if>
						
						<#if productTypeAttrWhereAlls??>
							<#list productTypeAttrWhereAlls?keys as key>
								<div class='shop-total selector-set'>
									<a href="javascript:void(0)" class="ss-item" onclick="delFilter('-${(key)!""}')"><b>${productTypeAttrWhereAlls[key]}</b><i></i></a>
								</div>
							</#list>
						</#if>
			
					</div>
				</div>
			</div>
		</div>
		</#if>
		<div class='container' id='shop-tag'>
			<!-- 商品筛选 -->
			<div id='TagContainer' class='selector'>
				<div class='select-title'>
					<h3>
						<b>${(productCate.name)!""}</b>
						<em style='font-weight:bold'>&nbsp;商品筛选</em>
					</h3>
					<div class='shop-total'>
						共 &nbsp;<span>${(productSize)!""}&nbsp;</span>个商品
					</div>
				</div>
				
				<#if brandId == 0>
				<div class='select-price selector-fold'>
					<div class='select-brand'>
						<div class='select-key'>
							<span>品牌：</span>
						</div>
						<div class='select-value'>
							<#--
							<ul class='select-the-letter brand-letter'>
								<li data-type='0'>所有品牌</li>
								<#if productBrandNameFirsts??>
									<#list productBrandNameFirsts as productBrandNameFirst>
										<li data-type='${(productBrandNameFirst)!""}'>--${(productBrandNameFirst)!""}</li>
									</#list>
								</#if>
							</ul>
							-->
							<div class='select-brand-list'>
								<ul id='BrandType'class='value-list v-fixed'>
								<#if productBrands??>
									<#list productBrands as productBrand>
										<li data-type='${(productBrand.nameFirst)!""}'><a href="javascript:void(0)" onclick="filterBrand(${(productBrand.id)!})"><i></i>${(productBrand.name)!""}</a></li>
									</#list>
								</#if>
								</ul>
							</div>
						</div>
						<div class='select-ext' style="display: none;">
							<a class='select-more' style='visibility: visible;' href='javascript:void(0);'>更多 <i></i></a>
						</div>
					</div>
				</div>
				</#if>
				
				
				<#if productTypeAttrVOs??&&(productTypeAttrVOs?size>0)>
					<#list productTypeAttrVOs as productTypeAttrVO>
						<div class='select-price selector-fold  <#if (productTypeAttrVO_index>1)>tag-display</#if>'>
							<div class='select-brand'>
								<div class='select-key'style='padding-top:4px;'>
									<span>${(productTypeAttrVO.name)!""}：</span>
								</div>
								<div class='select-value'>
									<div class='select-brand-list'>
										<ul class='value-list'>
											<#if productTypeAttrVO.values??>
												<#list productTypeAttrVO.values as value>
													<li><a href='javascript:void(0);' onclick="filterAttr(${(productTypeAttrVO.id)!}, ${(value_index)!})"><i>${(value)!""}</i></a></li>
												</#list>
											</#if>
										</ul>
									</div>
								</div>
								<!-- <div class='select-ext'>
									<a class='select-more' style='visibility: visible;' href='javascript:void(0);'>更多 <i></i></a>
								</div> -->
							</div>
						</div>
					</#list>
					<!-- 更多选项 -->
					<div class='select-more-tag' id='SelectMore'>
						<span class='select-wrap'>更多<i></i></span>
					</div>
				</#if>
			</div>
			<!-- end -->
			<!-- shop-list -->
			<div class='select-main' id='SelectMain'>
				<div class='shop-list'>
					<div class='shop-list-wrap'>
						<div class='filter' id='shop-filter'>
							<div class='filter-line top'>
								<div class='composite-sort'>
									<a href="javascript:void(0)" onclick="filterSort(0)" <#if sort?? && sort==0>class='btn-sort'</#if>>综合排序<i></i></a>
									<!--
									<a href="javascript:void(0)" onclick="filterSort(1)" <#if sort?? && sort==1>class='btn-sort'</#if>>销量<i></i></a>
									  -->
									<#if sort??>
									<#if sort == 0 || sort == 1 || sort == 2 || sort == 7 || sort == 8>
										<a href="javascript:void(0)" onclick="filterSort(3)">价格<i></i></a>
									<#else>
										<#if sort == 3>
											<a href="javascript:void(0)" onclick="filterSort(4)" class='btn-sort up'>价格<i></i></a>
										</#if>
										<#if sort == 4>
											<a href="javascript:void(0)" onclick="filterSort(3)" class='btn-sort down'>价格<i></i></a>
										</#if>
									</#if>
									</#if>
									<!--
									<a href="javascript:void(0)" onclick="filterSort(2)" <#if sort?? && sort==2>class='btn-sort'</#if>>评论数<i></i></a>
									  -->
									  <!-- 库存排序区域 -->
									  <#if sort??>
									<#if sort == 0 || sort == 1 || sort == 2 || sort == 3 || sort == 4 >
										<a href="javascript:void(0)" onclick="filterSort(8)">库存<i></i></a>
									<#else>
										<#if sort == 7>
											<a href="javascript:void(0)" onclick="filterSort(8)" class='btn-sort up'>库存<i></i></a>
										</#if>
										<#if sort == 8>
											<a href="javascript:void(0)" onclick="filterSort(7)" class='btn-sort down'>库存<i></i></a>
										</#if>
									</#if>
									</#if>
									<!-- 价格输入最底和最高区间查询 -->
									<a>
										<input class="sw-dpl-input sm-widget-price-start" value="<#if startPrice?? && startPrice!=0.00>${(startPrice)?string('0.00')!}<#else></#if>" placeholder="¥ 最低价" type="text"/>
										<span class="sm-widget-split"></span>
										<input class="sw-dpl-input sm-widget-price-end" value="<#if endPrice?? && endPrice!=10000 && startPrice!=0.00>${(endPrice)?string('0.00')!}<#else></#if>" placeholder="¥ 最高价" type="text"/>
										<span class="sm-widget-price-define" onclick="filterPrice()">
											<span class="sw-dpl-define btn btn-danger btn-xs" style="background:#c00;border-color:#c00;">
												确定
											</span>
									   </span>
									</a>
								</div>
							</div>
							
							<!--二次筛选功能 start-->
							<div class='filter-line clearfix'>
								<div class='f-store'>
									<div class='f-harvested instock'>
										<#if store?? && store==1>
										    <a class="selected"  href="javascript:void(0)" onclick="filterStore(0)"><i></i>仅显示有货</a>
										<#else>
											<a href="javascript:void(0)" onclick="filterStore(1)"><i></i>仅显示有货</a>
										</#if>
									</div>
								</div>
								
								
								<div class='f-feature'>
									<ul>
										<#if doSelf?? && doSelf==0>
										<!-- 
											<li><a href="javascript:void(0)" onclick="filterDoSelf(1)"><i></i>商城自营</a></li>
											 -->
										<#else>
											<li><a class="selected" href="javascript:void(0)" onclick="filterDoSelf(0)"><i></i>商城自营</a></li>
										</#if>
											<li>
												<#if store?? && store==2>
													<a href="javascript:void(0)" class="selected"  onclick="filterStore(0)"><i></i>库存充足</a>
												<#else>
													<a href="javascript:void(0)" onclick="filterStore(2)"><i></i>库存充足</a>
												</#if>
											</li>
											<li>
												<#if store?? && store==3>
													<a href="javascript:void(0)" class="selected"  onclick="filterStore(0)"><i></i>库存紧张</a>
												<#else>
													<a href="javascript:void(0)" onclick="filterStore(3)"><i></i>库存紧张</a>
												</#if>
											</li>
									</ul>
								</div>
						  </div>
						 <!--二次筛选功能 end-->
							
						</div>
						<!-- 商品展示列表页 -->
						<div class='main-item'>
							<ul class='gl-warp clearfix'>
						<#if producListVOs?? && page.pageCount != 0>
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
										<#if producListVO.marketPrice ?? && producListVO.marketPrice != producListVO.mallPcPrice>
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
					<#else>
						<li class="item fl" style="background:#e9faff;border:1px solid #e5e5e5;width: 990px;height:86px;">
							<div style="float:left;margin:20px 24px"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/icon_collapse.png"></div>
							<div style="font-family:MicrosoftYaHei;font-size:18px;color:#888888;text-align:left;padding-top: 21px;">没有找到相关商品。</div>
							<div style="font-family:MicrosoftYaHei;font-size: 12px;color:#888888;text-align:left;padding-top: 10px;">或许您可以在商品列表中筛选到满意的商品。</div>
						</li>
				    </#if>
							</ul>
						</div>
						<div class='page'>
							<!-- 分页 -->
							<#include "/front/commons/_paginationproductlist.ftl" />
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
	//cateTopAjax();
	cateLeftAjax();
}

function delFilter(filter) {
	var urlPath = "${(urlPath)!}";
	var url = urlPath.replace(filter,"");
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

function filterBrand(brand) {
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 2) {
	    	url += 0;			//从第一页开始
	    }
	    else if(i == 6) {
	    	url += brand;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

function filterAttr(attrId, value) {
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 2) {
	    	url += 0;			//从第一页开始
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + "-" + attrId + "_" + value + ".html";
}

function filterStore(store) {
	//1仅显示有货   2是库存充足  3库存紧张
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 5) {
	    	url += store;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	//url 0-1-0-0-0-1-0-0-0-0.html
	//console.dir(url);
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

function filterDoSelf(doSelf) {
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 4) {
	    	url += doSelf;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

function filterSort(sort) {
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 3) {
	    	url += sort;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}
function filterPrice() {
	var prictStart = $(".sm-widget-price-start").val();
	var prictEnd = $(".sm-widget-price-end").val();
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	if(prictStart==""){
		prictStart = 0;
	}
	if(prictEnd==""){
		prictEnd = 10000;
	}
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 8){//最低价_最高价
	    	url += prictStart+"_"+prictEnd;
	    }else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}
function filterStorearea() {
	var storeStart = $(".sm-widget-store-start").val();
	var storeEnd = $(".sm-widget-store-end").val();
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	if(storeStart==""){
		storeStart = 0;
	}
	if(storeEnd==""){
		storeEnd = 1000000000;
	}
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 9){//最低价
	    	url += storeStart+"_"+storeEnd;
	    }else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

function cateTopAjax() {
	var cateTopHtml = "";
	var m_price	= "";					
	$.ajax({
        type:"get",
        url: "${(domainUrlUtil.EJS_URL_RESOURCES)!}/recommend.html?q_recommendType=801",
        dataType: "json",
        cache:false,
        success:function(data){
            if (data.success) {
                $.each(data.rows, function(i, recommend){
                	var product = recommend.product;
                	/*if(product.priceStatus == 2){
                		m_price = product.description;
                	}*/
                	cateTopHtml += "<li><a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' class='recommend-img' target='_blank'>";
                    cateTopHtml += "<img width='100 height='100' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + product.masterImg + "'></a>";
                    cateTopHtml += "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' target='_blank' class='shop-name'>";
                    cateTopHtml += "<em>" + product.name1 + "</em></a><div class='shop-price'><span>特价：</span><strong>";
                    cateTopHtml += "<em class='shop-number'>￥" + m_price + "</em></strong></div><div class='shop-snapped'>";
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
	var m_price	= "";					
	$.ajax({
        type:"get",
        url: "${(domainUrlUtil.EJS_URL_RESOURCES)!}/recommend.html?q_recommendType=803",
        dataType: "json",
        cache:false,							
        success:function(data){
            if (data.success) {
                $.each(data.rows, function(i, recommend){
                	var product = recommend.product;
                	m_price = product.mallPcPrice;
                	/*if(product.priceStatus == 2){
                		m_price = product.description;
                	}*/
                	cateLeftHtml += "<li><div class='goods-img'>";
                    cateLeftHtml += "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' target='_blank' title='" + "product.name1'>";
                    cateLeftHtml += "<img width='200' height='200' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + product.masterImg + "'></a></div>";
                    cateLeftHtml += "<div class='goods-price'><span>特价：</span><strong>￥" + m_price + "</strong></div><div class='goods-name'>";
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
<!-- 登录弹出框 -->
<#include "/front/commons/logindialog.ftl" />
<#include "/front/commons/_endbig.ftl" />
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/list.js'></script>