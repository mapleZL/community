<#include "/front/commons/_headbig.ftl" />

<#if Session.memberSession??>
  <#assign user = Session.memberSession.member>
</#if>
   <style>
		.em-errMes{
			padding-left: 0px;
			display: block;
			line-height: 26px;
			margin-top: 10px;
		}
		/*商品下架样式*/
		.m-itemover-title {
		  height: 38px;
		  line-height: 38px;
		  border: 1px solid #ddd;
		  background: #f5f5f5;
		}
		.m-itemover-title h3 {
		  padding-top: 10px;
		  padding-left: 10px;
		}
		.go-flash-sale {
			display:inline-block;
			line-height:18px;
		}
		#gotop:hover{
			background-position:0 -30px;
		}
		#gotop{
		    width: 30px;
		    height: 30px;
		    text-indent: -9999px;
		    overflow: hidden;
		    color: #fff;
		    background: url('http://www.dawawang.com/images/top_icon.gif') no-repeat;
		    position: absolute;
		    display: none;
		    cursor: pointer;
		}
	</style>
	<script type="text/javascript">
			(function(){
				var sUserAgent = navigator.userAgent;
				if (sUserAgent.indexOf('Android') > -1 
						|| sUserAgent.indexOf('iPhone') > -1 
						|| sUserAgent.indexOf('iPad') > -1 
						|| sUserAgent.indexOf('iPod') > -1 
						|| sUserAgent.indexOf('Symbian') > -1) {
					var hrefstr = location.href;
					hrefstr = hrefstr.replace("www","m");
	                location.href = hrefstr;           
				} else {
					//console.dir(location.href);
				}    
			})();
		</script>
	<div style="width: 50px;height: 50px;border: 0px solid red;position: fixed;left:95%;top:88%;">
		<a id="gotop" href="javascript:void(0)" title="回到顶部" style="display: inline;">回到顶部</a>
	</div> 
	
	
		<div id='root-nav'>
			<div class='container'>
				<div class='subheader'>
					<strong>
						<#if productCatePP?? >
							${(productCatePP.name)!''}
						</#if>
					</strong>
					<span>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/list/${(productCateP.id)!0}.html'>${(productCateP.name)!''}</a>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cate/${(productCate.id)!0}.html'>${(productCate.name)!''}</a>
						&nbsp;>&nbsp;
					</span>
					<span>
					<!-- 品牌的链接 TODO -->
						<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/cate/${(product.productCateId)!0}.html">${(productBrand.name)!''}</a>
						&nbsp;>&nbsp;
						${(product.name1)!''}
					</span>
				</div>
			</div>	
		</div>
		<div id='p-box'>
			<div class='container'>
				<div class='product-intro m-item-grid'>
					<div class="right-extra">
						<div>
							<div id="preview" class="spec-preview">
								<!-- 默认第一张图 -->
								<#if productLeadPicList?? && productLeadPicList?size &gt; 0>
							        		<#list productLeadPicList as img>
							        			<#if img_index == 0>
							        				<span class="jqzoom">
														<img style="width:350px;height:350px" jqimg="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${img}" 
															src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${img}" />
													</span> 
								            		<#break>
											    </#if>
							        		</#list>
							        </#if>
							</div>
   							 <!--缩图开始-->
						    <div class="spec-scroll"> 
						    	<a class="prev">&lt;</a> 
						    	<a class="next">&gt;</a>
						      	<div class="items">
							        <ul>
							        	<#if productLeadPicList?? && productLeadPicList?size &gt; 0>
							        		<#list productLeadPicList as img>
							        			<li>
							          				<img  bimg="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${img}" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${img}" onmousemove="preview(this);">
							            		</li>
							        		</#list>
							        	</#if>
							        </ul>
						        </div>
						    </div>
   							<!--缩图结束-->
						</div>
					</div>
			<#if product.id == 157>
			<!-- 进货单表单 -->
			<!-- luowa begin -->
			<form action="" method="POST"  name="cartForm" id="cartForm">
			<!-- 隐藏域 -->
				<!-- 产品ID -->
				<input  type="hidden" name="productId" value="${productId!''}">
				<input  type="hidden" name="sellerId" value="${(seller.id)!''}">
				<input  type="hidden" name="productGoodsId" id="productGoodsId" value="${(goods.id)!''}">
				<input  type="hidden"  id='goodsNormAttrId' value="${(goods.normAttrId)!''}">
				<input  type="hidden"  id='maxStock' value="${(goods.maxStock)!99999}">
				<input  type="hidden"  id="status" value="${status!''}">
				<input  type="hidden"  id="stockNums" value="${(pAttr.value)!'10'}">
					<div class='m-item-inner'>
							<div id='itemInfo' style="border:2px solid red;">
								<div id='detaile-name'>
									<h1>
										${(product.name1)!''}
										<#if product.sellerId == 0>
										<span style="vertical-align:middle;display:inline-block;width:16px;height:16px;BACKGROUND: url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/redpacket.png') no-repeat center 50%;"></span>
										<#else>
										<span></span>
										</#if>
									</h1>
									<div class='p-ad' id='p-ad'>
										${(product.name2)!''}
									</div> 
								</div>
							<!-- 判断是否下架 -->
							<#if product?? && product.state?? && product.state == 6>
								<div id='summary'>
									<div id='CommentCount' style="display: none;">
										<p class='cumulative-comment'>累计评价</p>
										<a id='CountNumber'>${(statisticsVO.productCommentsAllCount)!'' }</a>
									</div>
									<#if product.priceStatus == 1>
										<div class="summary-product">
											<em>一 口 价：</em>
											<strong class='p-price' id="mallPcPrice" >￥${(goods.mallPcPrice)!''}</strong>
											<#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != goods.mallPcPrice>
											&nbsp;&nbsp;<span style="text-decoration: line-through;font-size: 15px;position: absolute;top:21px;">￥${(goods.mallOriginalPrice)!''}&nbsp;</span>
											</#if>
										</div>
										<div>&nbsp;</div>
									</#if>
									<#if productPrice?? && product.pricePid?? && product.priceStatus == 2>
										<div class="summary-product">
											<div>
											<em>&nbsp;${(productPrice.price1S)!''}-${(productPrice.price1E)!''}${(product.unit)!''}</em><br/><br/>
											<strong class='p-price' id="mallPcPrice1" >￥${(productPrice.price1)!''}</strong>
											</div>
											<div>
											<em>&nbsp;${(productPrice.price2S)!''}-${(productPrice.price2E)!''}${(product.unit)!''}</em><br/><br/>
											<strong class='p-price' id="mallPcPrice2" >￥${(productPrice.price2)!''}</strong>
											</div>
											<div>
											<em>&nbsp;≥${(productPrice.price3S)!''}${(product.unit)!''}</em><br/><br/>
											<strong class='p-price' id="mallPcPrice3" >￥${(productPrice.price3)!''}</strong>
											</div>
										</div>
									</#if>
									<#if product.priceStatus == 3>
										<div class="summary-product">
											<div >
											<em>&nbsp;单色整箱价（${(product.fullContainerQuantity)!''}*n）</em><br/><br/>
											<strong class='p-price' id="mallPcPrice_t" >￥${(product.mallPcPrice)!''}</strong>
											<#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != product.mallPcPrice>
											&nbsp;&nbsp;<span style="text-decoration: line-through;font-size: 15px;position: absolute;top:51px;">￥${(goods.mallOriginalPrice)!''}&nbsp;</span>
											</#if>
											</div>
											<div style="margin-left:25px">
											<em>&nbsp;散价（10*n）</em><br/><br/>
											<strong class='p-price' id="mallPcPrice_s" >￥${(product.scatteredPrice)!''}</strong>
											</div>
										</div>
									</#if>
									<#--
									<div class="summary-product">
										<em>商 城 价：</em>
										<strong class='p-price' id="mallPcPrice" >￥${(goods.mallPcPrice)!''}</strong>
									</div>
									<div class="old-product">
										<em>市 场 价：</em>
										<del>￥${(product.marketPrice)!''}</del>
									</div>
									-->
									<div>&nbsp;</div>
									<div class='summary-top' id='flashSaleInfoDiv'>
									</div>
									<div class='summary-top' id='actInfoDiv'>
									</div>
									<div class="p-choose" id="couponInfoDiv">
									</div>
									<div class='summary-service' style="display:none;">
										<div class='dt'>服　　务：</div>
										<div class='dd'>由 ${seller.sellerName} 发货并提供售后服务</div>
									</div>
									
								</div>
								<div id='Choose' class='p-choose-wrap clearfix' style="position: relative;">
									<#if norms??>
										<#list norms as norm>
											<div id='ChooseNorm${norm_index}' class='li choosenorms'>
												<div class='dt'>${norm.name}：</div>
												<div class='dd norms' >
													<#list norm.attrList as normattr>
														<div class='item' val="${normattr.id}" data-stock="${normattr.sort}"
															data-iscustom="${(norm.isCustom)?string('true','false')}"
															<#--下架-->
															<#if (normattr.sort == 1) >
															style="opacity:0.3; filter: alpha(opacity=30); background-color:#000;"	
															</#if>
															data-pic-url="${(normattr.url)!''}">
															<b></b>
															<a href='javascript:void(0);' title='${normattr.name}'
															<#if (normattr.sort == 1) >
															onmouseover="this.style.cssText='border: 1px solid #ccc;cursor:text'" onmouseout="this.style.cssText='border: 1px solid #ccc;'"
															</#if> >
															<#if norm.isCustom == true>
															<img style="max-height: 14px;margin-bottom: 1.92pt;" 
																src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(normattr.url)!''}"
																onerror="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg'"/>
															</#if>
															${normattr.name}
															</a>
														</div>
													</#list>
													<!-- 规格值ID  -->
													<input  type="hidden" name="specId" class="attrid" >
													<!-- 规格详情， 用逗号分隔 ，例如颜色：黑色 -->
													<input  type="hidden" name="specInfo" class="attrname" >
												</div>
											</div>
										</#list>
									</#if>
									
									<div id='ChooseBtns' class='li'>
										<div class='dt'>购买数量：</div>
										<div class='dd'>
											<div class='choose-amount'>
												<div class='wrap-input'>
													<input type=button class='btn-reduce' id='min' value='-'>
													<a class='btn-add' id='add'>+</a>
													<input type='text' id='buy-num' value='1' data-now="1"  onchange="checknum(this)">
													<input type='hidden' id='buy-num-hidden' name="count" value='1' data-now="1">
												</div>
											</div>
											<i style="font-size:14px;
														color:#999999;">手( 一手=${(pAttr.value)!'10'}${(product.unit)!''} )</i>
											<span style='line-height:46px;
														font-size:14px;
														color:#999999;'>
												<input type="hidden" id="productStock" value="${(goods.productStock)!'0'}" />
													<#if ((goods.productStock)!0) &gt;= ((goods.productStockWarning)!0) >
														<em id="productStockWarning" style="color:green">
														<#--货源充足-->库存${(goods.productStock)!'0'}${(product.unit)!''} 
													<#else>
														<em id="productStockWarning" style="color:red">
														库存${(goods.productStock)!'0'}${(product.unit)!''}
														  <#--
															<#if product.sellerId == 0 && 100 &gt; goods.productStock>
																货源紧张 库存0${(product.unit)!''} )
												
															<#else>
																货源紧张 库存${(goods.productStock)!'0'}${(product.unit)!''} )
															</#if>
														  --> 
													</#if>
												</em>
												<#if product.isCxbh?? && product.isCxbh == 1>
												<span style="margin-left: 20px;font-size:14px;color: #FF3C23;font-weight: bold; "><span class="fa fa-recycle bg7"></span>&nbsp;本品会持续补货</span>
												<#else>
												<span></span>
												</#if>
											</span>
											
											<span class="em-errMes"></span>
										</div>
									</div>
									
									
									<div class='tzm-border'>
										<div class='tip'>
											<span style="color:red">请选择您要的商品信息</span>
										</div>
										<a href='javascript:void(0);' class='close internation-close'></a>
									</div>
								</div>
								
								<#if productPackage?? && productBrand.id== 8 && product.id !=10 >
								<!-- 需要二次加工选项 -->
								<div class="processing-box" >
									<p class="processing-p">
										<label>
										<input type="checkbox" name="packageCheck"
											id="packageCheck" class="process-item"/>&nbsp;
										<span id="pcinfo">需要二次加工（备注：加工费不含辅料费）</span>
										</label>
									</p>
									<input type="hidden" name="productPackageId" id="productPackageId"/>
									<div class="processing-img">
										<ul>
											<#list productPackage as pka>
										 	<li data-product-pageck-id="${pka.id}">
										 		<a href="javascript:;" class="a-img">
										 			<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(pka.image)!''}" 
										 				onerror="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg'"
										 				alt="">
										 			<p data-original-title="<p style='text-align: left'>描述：${pka.describe}</p>" 
										 				class="atip"><label><input style="margin:0px 3px 0 5px;float:none;vertical-align:middle; margin-top:-2px;" type="checkbox" name="pak" value="${pka.id}" data-name="${(pka.name)!''}"/>${(pka.name)!''}</label></p>
										 		</a>
										 		<p>加工费<i>${(pka.price)!0}</i>元</p>
										 	</li>
										 	</#list>
										</ul>
									</div>
									<p class="processing-brand">
										<label><input type="checkbox" name="isSelfLabel" id="isSelfLabel" value="1" class="process-item"/>&nbsp;
										<span>是否自供商标</span>
										</label>
									</p>
								</div>
								</#if>
								<!-- 已选清单 -->
								<div class="selected-list">
									<div class="selected-list-box">
										<span class="selected-tit" style="cursor: pointer;"><i <#if user??>data-original-title="点击查看清单"</#if>>已选清单</i></span>
										<span class=""><#if selectedlist??>${count}<#else>0</#if>${(product.unit)!''}</span>
										<span class="selected-line">|</span>
										<span class=""><#if selectedlist??>${amount}元（不含优惠）<#else>0元</#if></span>
									
									</div>
									<div class="selected-list-hide">
										<table border="1">
											<#if selectedlist??>
											<#list selectedlist as sl>
											<tr>
												<td style="width:65%">${(sl.specInfo)!''}</td>
												<td style="width:27%">${(sl.count)!0}</td>
												<td style="width:8%"><a href="javascript:;" 
													data-cartid="${sl.id}" data-cartcount="${(sl.count)!0}"
													onclick="delcart(this)">删除</a></td>
											</tr>
											</#list>
											</#if>
										</table>
									</div>
								</div>
								
								<div id='MainBtns' class='li'>
										<#--
										<#if (goods.productStock)??>
											<#if goods.productStock &lt; 1>
											<span style="font-size:14px;color:red">无货</span>
											<#else>
											<button type="button" class="btn btn-warning buynow">立即购买</button>&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn btn-danger addcart">加入进货单</button>&nbsp;&nbsp;&nbsp;&nbsp;
											</#if>
										<#else><span style="font-size:14px;color:red">无货</span>
										</#if>
										-->
										<button type="button" class="btn btn-warning buynow">立即购买</button>&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn btn-danger addcart">加入进货单</button>&nbsp;&nbsp;&nbsp;&nbsp;
										<#if statisticsVO??> 
											<#if statisticsVO.collectedProduct=true> 已加入收藏夹 
											<#else>
												<button id="collectProduct" onclick="collectProductMy('${productId!''}')" 
													type="button" class="btn btn-default">加入收藏夹</button>
											</#if>
										</#if>
								</div>
								
							<#else> 
								<div class="m-itemover">
			                        <div class="m-itemover-title">
			                            <h3><strong>即将上架，敬请期待！ </strong></h3>
			                        </div>
			                    </div>
			                </#if>
		                    <input type="hidden" value="${(product.state)!''}" id="tzm_productState"/>
		                    <input type="hidden" value="${(product.upTime)?string('yyyy-MM-dd HH:mm:ss')}" id="tzm_productUpTime"/>
						</div>
					</div>
					</form>
					<!-- luo wa end -->
					<#else>
					<!-- 品牌袜begin -->
						<!-- 进货单表单 -->
					<form action="" method="POST"  name="cartForm" id="cartForm">
					<!-- 隐藏域 -->
						<!-- 产品ID -->
						<input  type="hidden" name="productId" value="${productId!''}">
						<input  type="hidden" name="sellerId" value="${(seller.id)!''}">
						<input  type="hidden" name="productGoodsId" id="productGoodsId" value="${(goods.id)!''}">
						<input  type="hidden"  id='goodsNormAttrId' value="${(goods.normAttrId)!''}">
						<input  type="hidden"  id='maxStock' value="${(goods.maxStock)!99999}">
						<input  type="hidden"  id="status" value="${status!''}">
						<input  type="hidden"  id="stockNums" value="${(pAttr.value)!'10'}">
							<div class='m-item-inner'>
									<div id='itemInfo' ">
										<div id='detaile-name'>
											<h1>
												${(product.name1)!''}
												<#if product.sellerId == 0>
												<span style="vertical-align:middle;display:inline-block;width:16px;height:16px;BACKGROUND: url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/redpacket.png') no-repeat center 50%;"></span>
												<#else>
												<span></span>
												</#if>
											</h1>
											<div class='p-ad' id='p-ad'>
												${(product.name2)!''}
											</div> 
										</div>
									<!-- 判断是否下架 -->
									<#if product?? && product.state?? && product.state == 6>
										<div id='summary'>
											<div id='CommentCount' style="display: none;">
												<p class='cumulative-comment'>累计评价</p>
												<a id='CountNumber'>${(statisticsVO.productCommentsAllCount)!'' }</a>
											</div>
											<#if product.priceStatus == 1>
												<div class="summary-product">
													<em>价格：</em>
													<strong class='p-price' id="mallPcPrice" >￥${(goods.mallPcPrice)!''}</strong>
													<#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != goods.mallPcPrice>
													&nbsp;&nbsp;<span style="text-decoration: line-through;font-size: 15px;position: absolute;top:21px;">￥${(goods.mallOriginalPrice)!''}&nbsp;</span>
													</#if>
												</div>
												<div>&nbsp;</div>
											</#if>
											<#if productPrice?? && product.pricePid?? && product.priceStatus == 2>
												<div class="summary-product">
													<div>
													<em>&nbsp;${(productPrice.price1S)!''}-${(productPrice.price1E)!''}${(product.unit)!''}</em><br/><br/>
													<strong class='p-price' id="mallPcPrice1" >￥${(productPrice.price1)!''}</strong>
													</div>
													<div>
													<em>&nbsp;${(productPrice.price2S)!''}-${(productPrice.price2E)!''}${(product.unit)!''}</em><br/><br/>
													<strong class='p-price' id="mallPcPrice2" >￥${(productPrice.price2)!''}</strong>
													</div>
													<div>
													<em>&nbsp;≥${(productPrice.price3S)!''}${(product.unit)!''}</em><br/><br/>
													<strong class='p-price' id="mallPcPrice3" >￥${(productPrice.price3)!''}</strong>
													</div>
												</div>
											</#if>
											<#if product.priceStatus == 3>
												<div class="summary-product">
													<div >
													<em>&nbsp;单色整箱价（${(product.fullContainerQuantity)!''}*n）</em><br/><br/>
													<strong class='p-price' id="mallPcPrice_t" >￥${(product.mallPcPrice)!''}</strong>
													<#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != product.mallPcPrice>
													&nbsp;&nbsp;<span style="text-decoration: line-through;font-size: 15px;position: absolute;top:51px;">￥${(goods.mallOriginalPrice)!''}&nbsp;</span>
													</#if>
													</div>
													<div style="margin-left:25px">
													<em>&nbsp;散价（10*n）</em><br/><br/>
													<strong class='p-price' id="mallPcPrice_s" >￥${(product.scatteredPrice)!''}</strong>
													</div>
												</div>
											</#if>
											<#--
											<div class="summary-product">
												<em>商 城 价：</em>
												<strong class='p-price' id="mallPcPrice" >￥${(goods.mallPcPrice)!''}</strong>
											</div>
											<div class="old-product">
												<em>市 场 价：</em>
												<del>￥${(product.marketPrice)!''}</del>
											</div>
											-->
											<div>&nbsp;</div>
											<div class='summary-top' id='flashSaleInfoDiv'>
											</div>
											<div class='summary-top' id='actInfoDiv'>
											</div>
											<div class="p-choose" id="couponInfoDiv">
											</div>
											<div class='summary-service' style="display:none;">
												<div class='dt'>服　　务：</div>
												<div class='dd'>由 ${seller.sellerName} 发货并提供售后服务</div>
											</div>
											
										</div>
										<div id='Choose' class='p-choose-wrap clearfix' style="position: relative;">
											<#if norms??>
												<#list norms as norm>
													<div id='ChooseNorm${norm_index}' class='li choosenorms'>
														<div class='dt' style="line-height:30px;">${norm.name}：</div>
														
														<div class='dd norms' >
															<#list norm.attrList as normattr>
																<div class='item' val="${normattr.id}" data-stock="${normattr.sort}"
																	data-iscustom="${(norm.isCustom)?string('true','false')}"
																	<#--下架-->
																	<#if (normattr.sort == 1) >
																	style="opacity:0.3; filter: alpha(opacity=30); background-color:#000;"	
																	</#if>
																	data-pic-url="${(normattr.url)!''}">
																	<b></b>
																	<a href='javascript:void(0);' title='${normattr.name}'
																	<#if (normattr.sort == 1) >
																	onmouseover="this.style.cssText='border: 1px solid #ccc;cursor:text'" onmouseout="this.style.cssText='border: 1px solid #ccc;'"
																	</#if> >
																	<#if norm.isCustom == true>
																	<img style="max-height: 14px;margin-bottom: 1.92pt;" 
																		src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(normattr.url)!''}"
																		onerror="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg'"/>
																	</#if>
																	${normattr.name}
																	</a>
																</div>
															</#list>
															<!-- 规格值ID  -->
															<input  type="hidden" name="specId" class="attrid" >
															<!-- 规格详情， 用逗号分隔 ，例如颜色：黑色 -->
															<input  type="hidden" name="specInfo" class="attrname" >
														</div>
													</div>
															<div class="li choosenorms">
															
															<div class="dt">库存数量：</div>
															<span style='
																font-size:14px;
																color:#999999;'>
														<input type="hidden" id="productStock" value="${(goods.productStock)!'0'}" />
															<#if ((goods.productStock)!0) &gt;= ((goods.productStockWarning)!0) >
																<em id="productStockWarning" style="color:green">
																<#--货源充足-->库存${(goods.productStock)!'0'}${(product.unit)!''} 
															<#else>
																<em id="productStockWarning" style="color:red">
																库存${(goods.productStock)!'0'}${(product.unit)!''}
																  <#--
																	<#if product.sellerId == 0 && 100 &gt; goods.productStock>
																		货源紧张 库存0${(product.unit)!''} )
														
																	<#else>
																		货源紧张 库存${(goods.productStock)!'0'}${(product.unit)!''} )
																	</#if>
																  --> 
															</#if>
														</em>
														<#if product.isCxbh?? && product.isCxbh == 1>
														<span style="margin-left: 20px;font-size:14px;color: #FF3C23;font-weight: bold; "><span class="fa fa-recycle bg7"></span>&nbsp;本品会持续补货</span>
														<#else>
														<span></span>
														</#if>
													</span>
															<i style="margin-left:10px;">本品会持续补货</i>
															
															</div>
												</#list>
											</#if>
											
											<div id='ChooseBtns' class='li'>
												<div class='dt'>购买数量：</div>
												<div class='dd'>
													<div class='choose-amount'>
														<div class='wrap-input'>
															<input type=button class='btn-reduce' id='min' value='-'>
															<a class='btn-add' id='add'>+</a>
															<input type='text' id='buy-num' value='1' data-now="1"  onchange="checknum(this)">
															<input type='hidden' id='buy-num-hidden' name="count" value='1' data-now="1">
														</div>
													</div>
													<i style="font-size:14px; line-height:43px;
																color:#999999;">手( 一手=${(pAttr.value)!'10'}${(product.unit)!''} )</i>
													
													
													<span class="em-errMes"></span>
												</div>
											</div>
											
											
											<div class='tzm-border'>
												<div class='tip'>
													<span style="color:red">请选择您要的商品信息</span>
												</div>
												<a href='javascript:void(0);' class='close internation-close'></a>
											</div>
										</div>
										
										<#if productPackage?? && productBrand.id== 8 && product.id !=10 >
										<!-- 需要二次加工选项 -->
										<div class="processing-box" >
											<p class="processing-p">
												<label>
												<input type="checkbox" name="packageCheck"
													id="packageCheck" class="process-item"/>&nbsp;
												<span id="pcinfo">需要二次加工（备注：加工费不含辅料费）</span>
												</label>
											</p>
											<input type="hidden" name="productPackageId" id="productPackageId"/>
											<div class="processing-img">
												<ul>
													<#list productPackage as pka>
												 	<li data-product-pageck-id="${pka.id}">
												 		<a href="javascript:;" class="a-img">
												 			<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(pka.image)!''}" 
												 				onerror="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg'"
												 				alt="">
												 			<p data-original-title="<p style='text-align: left'>描述：${pka.describe}</p>" 
												 				class="atip"><label><input style="margin:0px 3px 0 5px;float:none;vertical-align:middle; margin-top:-2px;" type="checkbox" name="pak" value="${pka.id}" data-name="${(pka.name)!''}"/>${(pka.name)!''}</label></p>
												 		</a>
												 		<p>加工费<i>${(pka.price)!0}</i>元</p>
												 	</li>
												 	</#list>
												</ul>
											</div>
											<p class="processing-brand">
												<label><input type="checkbox" name="isSelfLabel" id="isSelfLabel" value="1" class="process-item"/>&nbsp;
												<span>是否自供商标</span>
												</label>
											</p>
										</div>
										</#if>
										<!-- 已选清单 -->
										<div class="selected-list">
											<div class="selected-list-box">
												<span class="selected-tit" style="cursor: pointer; display:block; width:70px;float:left;"><i <#if user??>data-original-title="点击查看清单"</#if>>已选清单</i></span>
												<div style="height:40px; border:1px solid red;width:550px;float:right;line-height:40px; ">
												<span class="">袜颜色</span>
												<span class="selected-line">|</span>
												<span class="">袜款式</span>
												<span class="selected-line">|</span>
												<span class="">数量<#if selectedlist??>${count}<#else>0</#if>${(product.unit)!''}</span>
												<span class="selected-line">|</span>
												<span class="">价格<#if selectedlist??>${amount}元（不含优惠）<#else>0元</#if></span>
												<span class="" style="float:right; color:red;cursor: pointer;">点击查看详情</span>
											</div>
											</div>
											<div class="selected-list-hide">
												<table border="1">
													<#if selectedlist??>
													<#list selectedlist as sl>
													<tr>
														<td style="width:65%">${(sl.specInfo)!''}</td>
														<td style="width:27%">${(sl.count)!0}</td>
														<td style="width:8%"><a href="javascript:;" 
															data-cartid="${sl.id}" data-cartcount="${(sl.count)!0}"
															onclick="delcart(this)">删除</a></td>
													</tr>
													</#list>
													</#if>
												</table>
											</div>
										</div>
										
										<div id='MainBtns' class='li'>
												<#--
												<#if (goods.productStock)??>
													<#if goods.productStock &lt; 1>
													<span style="font-size:14px;color:red">无货</span>
													<#else>
													<button type="button" class="btn btn-warning buynow">立即购买</button>&nbsp;&nbsp;&nbsp;&nbsp;
													<button type="button" class="btn btn-danger addcart">加入进货单</button>&nbsp;&nbsp;&nbsp;&nbsp;
													</#if>
												<#else><span style="font-size:14px;color:red">无货</span>
												</#if>
												-->
												<button type="button" class="btn btn-warning buynow">立即购买</button>&nbsp;&nbsp;&nbsp;&nbsp;
												<button type="button" class="btn btn-danger addcart">加入进货单</button>&nbsp;&nbsp;&nbsp;&nbsp;
												<#if statisticsVO??> 
													<#if statisticsVO.collectedProduct=true> 已加入收藏夹 
													<#else>
														<button id="collectProduct" onclick="collectProductMy('${productId!''}')" 
															type="button" class="btn btn-default">加入收藏夹</button>
													</#if>
												</#if>
										</div>
										
									<#else> 
										<div class="m-itemover">
					                        <div class="m-itemover-title">
					                            <h3><strong>即将上架，敬请期待！ </strong></h3>
					                        </div>
					                    </div>
					                </#if>
				                    <input type="hidden" value="${(product.state)!''}" id="tzm_productState"/>
				                    <input type="hidden" value="${(product.upTime)?string('yyyy-MM-dd HH:mm:ss')}" id="tzm_productUpTime"/>
								</div>
							</div>
							</form>
							<!-- 品牌袜end -->
					</#if>
					<!-- right -->
					<div class='m-item-ext' style="border: 1px solid red;display: none;">
						<div class='extInfo' id='extInfo'>
							<div class='seller-infor' style="display: none;">
								<a target="_blank" href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html' title='${(seller.sellerName)!''}' class='infor-name'>${(seller.sellerName)!''}</a>
							</div>
							<div class='seller-pop-box' style="display: none;">
								<div class='z-pop-desc-show'>
									<dl class='pop-score-detail'>
										<dt class='score-title'>
											<span class='rating-name'>商家满意度</span>
										</dt>
										<dd class='score-infor'>
											<div class='score-part'>
												<span class='score-desc'>商品描述：<em class='score'>${(seller.scoreDescription)!'0'}</em>分</span>
											</div>
											<div class='score-part'>
												<span class='score-desc'>服务态度：<em class='score'>${(seller.scoreService)!'0'}</em>分</span>
											</div>
											<div class='score-part'>
												<span class='score-desc'>发货速度：<em class='score'>${(seller.scoreDeliverGoods)!'0'}</em>分</span>
											</div>
										</dd>
									</dl>
									<div class='pop-shop-detail'>
										<div class='item'>
											<span class='label'>店铺名称：</span>
											<span class='text'><a target="_blank" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html">${(seller.sellerName)!''}</a></span>
										</div>
										<div class='item'>
											<span class='label'>所&nbsp;在&nbsp;地&nbsp;：</span>
											<span class='text'> ${(sellerLocation)!''}</span>
										</div>
									</div>
								</div>
							</div>
							<dl class='customer-service clearfix' style="border-bottom: 0px solid red;display: none;">
								<dd class='service'>
									<span class='item'>
										<b style='font-weight:700'>联 系 客 服 </b>
									</span>
									<br>
									<div class='custom-service'>
										<#if sellerQqList?? && sellerQqList?size &gt; 0>
											<#list sellerQqList as qq>
												<span class='item'>
													<b>${(qq.name)!''}：</b>
													<a href='http://wpa.qq.com/msgrd?v=3&uin=${(qq.qq)!''}&Site=${(qq.qq)!''}&Menu=yes' target='_blank'><img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/qq.jpg' width='25' height='25'></a>
												</span>
											</#list>
										</#if>
									</div>
									<br><br>
									<div class='custom-service'>
										<span class='item'>
											<b style="font-weight: bold;">联&nbsp;系&nbsp;电&nbsp;话：</b>
											<br>
											<span>4008-456-002</span>
										</span>
									</div>
								</dd>
							</dl>
							<div class='pop-shop-enter' style="display: none;">
								<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html' target='_blank' class='btn btn-default'>进入店铺</a>
								&nbsp;&nbsp;
								<#if statisticsVO??> 
										<#if statisticsVO.collectedShop=true> 已收藏  
										<#else>
											<a id="collectShop" href='javascript:void(0)' onclick="collectShop('${(seller.id)!''}')" class='btn btn-default'>收藏店铺</a>
										</#if>
								</#if> 
								
							</div>
						</div>
					</div>
					<!-- 上面信息不显示，此处动态加载热卖推荐的商品 -->
					<div class='m-item-ext' style="border:0px solid red;text-align: center;">
						<div class='left' >
							<div class='m m2'>
								<div class='mt' style="border-bottom:0px; padding-left:60px;">
									<h2 style="font-weight:bold;">热卖推荐</h2>
								</div>
								<div class='mc'>
									<div id="cateTopId"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- end -->
				</div>
			</div>
			<!-- 商品推荐 -->
			<#if producListVOs??>
			<div class="shop_bd_2 clearfix" style="background-color: white;">
				<div class="shop_bd_tuijian">
	                    <ul class="tuijian_tabs">
	                        <li class="hover" ><a href="javascript:void(0);">推荐搭配</a></li>
						</ul>
						<div class="tuijian_content">
	                        <div id="tuijian_content_1" class="tuijian_shangpin" style="display: block;">
	                            <ul>
	                            	<#list producListVOs as producListVO>
	                                <li>
	                                    <dl>
	                                        <dt><a target="_blank" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(producListVO.id)!0}.html"><img width="180" height="200" src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(producListVO.masterImg)!""}'></a></dt>
	                                        <dd><a target="_blank" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(producListVO.id)!0}.html">${(producListVO.name1)!""}</a></dd>
	                                        <dd><b>¥</b><em>${(producListVO.mallPcPrice)!""}</em>元</dd>
	                                    </dl>
	                                </li>
	                                </#list>
	                            </ul>
	                        </div>
	                     </div>
				</div>
			</div>
			</#if>
		<div class='container'>
			<div class='left'>
				<div id='browse-browse-pop' class='m m2 related-buy'>
					<div class='mt' style="border-bottom:0px; text-align:center;padding-top:5px;">
						<b>精品推荐</b>
					</div>
					<div class='mc'>
						<ul>
							<#if productTop?? && productTop?size &gt; 0>
								<#list productTop as top>
									<li class='fore1'>
										<div class='p-img'>
											<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${top.id!0}.html' target='_blank' title='${top.name1 }'>
												<img width='160' height='160' alt='${(top.name1)!''}' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(top.masterImg)!""}'>
											</a>
										</div>
										<div class='p-name' style="width:160px;margin-left:16px;text-align:center">
											<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${top.id!0}.html' target='_blank' title=''>${(top.name1)!''}</a>
										</div>
										<div class='p-price'>
											<strong>￥${(top.mallPcPrice)!''}</strong>
										</div>
									</li>
									
								</#list>
							</#if>
							
						</ul>
					</div>
				</div>
			</div>
			<div class='right'>
				<div id='product-detail' class='m m1'>
					<div class='mt' id='pro-detail-hd'>
						<div class='mt-inner tab-trigger-wrap clearfix'>
							<ul class='m-tab-trigger'>
								<li class='li-curr curr trig-item' data-table='1'>
									<a href='javascript:void(0);'>商品详情</a>
								</li>
								<li class='li-curr trig-item' data-table='2' style="display: none;">
									<a href='javascript:void(0);'>商品评价（${(statisticsVO.productCommentsAllCount)!'' }）</a>
								</li>
								<li class='li-curr trig-item' data-table='3' style="display: none;">
									<a href='javascript:void(0);'>咨询（${(statisticsVO.productAskCount)!'' }）</a>
								</li>
								<!-- <li class='li-curr trig-item' data-table='4'>
									<a href='javascript:void(0);'>规格属性</a>
								</li> -->
							</ul>
						</div>
					</div>
					<!-- 商品介绍 -->
					<div class='b-table bcent-table' id='table1'>
						<div class='mc'>
							<div class='p-parameter'>
								<ul id='parameter2' class='p-parameter-list'>
									<li title=''>商品名称：${(product.name1)!'' }</li>
									<li title='' style="display:none;">店铺： <a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html' target='_blank'>${(seller.sellerName)!''}</a></li>
									<li title='2015-04-01 20:35:24'>上架时间：${(product.upTime?string("yyyy-MM-dd"))!'' }</li>
								
									<#if productAttr?? && productAttr?size &gt; 0>
										<#list productAttr as attr>
											<li title=''>${(attr.name)!''}：${(attr.value)!''}</li>
										</#list>
									</#if>
								</ul>
							</div>
						</div>
						<div class='detail-content clearfix' style="border: 0px;">
							<div class='detail-content-wrap'>
								<div class='detail-content-item'>
									<p align='center'>
										 <#noescape> ${(product.description)!''}</#noescape>
										<!--<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/center.jpg' style='margin-top:10px;margin-bottom:10px;'>
										<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/center1.jpg' style='margin-top:10px;margin-bottom:10px;'> -->
									</p>
								</div>
							</div>
						</div>
						<!-- 
						添加售后保障  每个商品详情都一样存在  此处使用include 来引入 
						<#include "/front/commons/_After-sale-protection.ftl" />
						-->
						<!-- end -->
					</div>
					<!-- 商品评价 -->
					<div class='b-table'  id='table2'>
						<div id='state'>
							<strong>权利声明：</strong>
							<br>
							大袜网上的所有商品信息、客户评价、商品咨询、网友讨论等内容，是大袜网重要的经营资源，未经许可，禁止非法转载使用。
                			<p>
                				<b>注：</b>
                				本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，并不承担任何法律责任。
                			</p>
						</div>
						<div id='comment' class='m'>
							<div class='mt'>
								<h2>商品评价</h2>
							</div>
							<div class='mc'>
								<div id='i-comment'>
									<div class='rate'>
										<strong>${(statisticsVO.productCommentsHighProportion)!'' }<span>%</span></strong><br>
										<span>好评度</span>
									</div>
									<div class='percent'>
										<dl>
											<dt>好评<span>(${(statisticsVO.productCommentsHighProportion)!'' }%)</span></dt>
											<dd><div class='progress'></div></dd>
										</dl>
										<dl class='dl'>
											<dt>中评<span>(${(statisticsVO.productCommentsMiddleProportion)!'' }%)</span></dt>
											<dd><div class='progress' style='width:0%'></div></dd>
										</dl>
										<dl class='dl'>
											<dt>差评<span>(${(statisticsVO.productCommentsLowProportion)!'' }%)</span></dt>
											<dd><div class='progress' style='width:0%'></div></dd>
										</dl>
									</div>
								</div>
							</div>
						</div>
						<div id='comments-list' class='m'>
							<div class='mt'>
								<div class='mt-inner tab-trigger-wrap clearfix'>
									<ul class='m-tab-trigger'>
										<li class='li-curr curr comment-li' data-box='1'>
											<a href='javascript:void(0);' onclick="showAllComments('all','${productId}')" >全部评价<em>(${(statisticsVO.productCommentsAllCount)!'' })</em></a>
										</li>
										<li class='li-curr comment-li' data-box='2'>
											<a href='javascript:void(0);' onclick="showAllComments('high','${productId}')">好评<em>(${(statisticsVO.productCommentsHighCount)!'' })</em></a>
										</li>
										<li class='li-curr comment-li' data-box='3'>
											<a href='javascript:void(0);' onclick="showAllComments('middle','${productId}')">中评<em>(${(statisticsVO.productCommentsMiddleCount)!'' })</em></a>
										</li>
										<li class='li-curr comment-li' data-box='4'>
											<a href='javascript:void(0);' onclick="showAllComments('low','${productId}')">差评<em>(${(statisticsVO.productCommentsLowCount)!'' })</em></a>
										</li>
									</ul>
								</div>
							</div>
							
							<!-- 评价列表（全部、好、中、差 -->
							<div id = "commentsList"></div>
							
						</div>
					</div>
					<!-- 咨询 -->
					<div class='b-table' id='table3'>
						<div class='cs-main-wrap mt10 m'>
							<div class='mt'>
								<div class='mt-inner tab-trigger-wrap clearfix'>
									<ul class='m-tab-trigger'>
										<li class="li-curr curr advice-li" data-number='1'>
											<a href='javascript:void(0);' onclick="showProductAskList('${productId!''}')">全部咨询<em>(${(statisticsVO.productAskCount)!'' })</em></a>
										</li>
									</ul>
								</div>
							</div>
							<!--  咨询  列表区域 begin-->
								<div id = "productAskList"></div>
							<!--  咨询  列表区域 end-->
							
						<div id="editConsultaiion" style="height:500px;margin-top:20px;">
							<a style="color:blue" href='javascript:void(0);' onclick="editConsultaiion('${productId!''}','${(seller.id)!''}')">我要咨询</a>
						</div>
						</div>
						
					</div>
					
					 </div>
				</div>
			</div>

		<!-- S优惠券隐藏页面页面 -->
		<div class="toolbar-wrap" style="display: none">
			<h3 class="tbar-panel-header">
				<span class="title"><i></i><em>红包</em></span>
				<span class="close-panel">×</span>
			</h3>
			<div class="coupon-box">
				<div class="coupon-wrap">
					<div class="coupon-type">可领取的券
						<span class="line-left"></span>
						<span class="line-right"></span>
					</div>
					<div id="couponListDiv"></div>
				</div>
			</div>
		</div>
		<!-- E优惠券隐藏页面页面 -->
		
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/details.js'></script>
<link rel='stylesheet' type='text/css' href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/zoomify/zoomify.min.css' />
<script type='text/javascript' src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/zoomify/zoomify.min.js'></script>
<script type="text/javascript">
// 日志对象，用于调试
function Logger() {
};
Logger.prototype.setLevel = function(l){
	this.l = l;
}
Logger.prototype.i = function(msg) {
	if (typeof console != 'undefined') {
		if(this.l == 'debug')
			return console.debug(msg);
	}
}
var log = new Logger();
log.setLevel("debug");
//保存销售单位 盒或者双
var unitVal = '${product.unit}';
function cateTopAjax() {
	var cateTopHtml = "";
	var m_price	= "";					
	$.ajax({
        type:"get",
        url: "${(domainUrlUtil.EJS_URL_RESOURCES)!}/cateTop.html?cateId=" + 1,
        dataType: "json",
        cache:false,
        success:function(data){
            if (data.success) {
               	data.data.splice(3,1);
                $.each(data.data, function(i, product){
                	m_price = product.mallPcPrice;
                	if(product.priceStatus == 2){
                		m_price = product.description;
                	}
                	cateTopHtml += "<li><a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' class='recommend-img' target='_blank'>";
                    cateTopHtml += "<img width='100 height='100' class='img-thumbnail' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + product.masterImg + "'></a><br>";
                    cateTopHtml += "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' target='_blank' class='shop-name'>";
                    cateTopHtml += "<em>" + product.name1 + "</em></a><br><span>特价：</span><strong>";
                    cateTopHtml += "<em style=color:red;>￥" + m_price + "</em></strong><div class='shop-snapped'>";
                    cateTopHtml += "</div></li>";
                })
                $("#cateTopId").append(cateTopHtml);
            }
        }
    });
}
//点击 立即购买触发
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
//end
	function delcart(obj){
		var id_ = $(obj).data("cartid");
		if($(obj).data("cartcount")>1){
			if(confirm("您选择了多个该规格货品，是否确定全部删除？")){
				delbyid(id_);
			}
		} else{
			delbyid(id_);
		}
	}
	
	function delbyid(id){
		$.ajax({
			type : "GET",
			url :  domain+"/cart/deleteCartById.html",
			data : {id:id},
			dataType : "json",
			success : function(data) {
				if(data.success){
					window.location.reload(true);
				}else {
					jAlert("删除失败");
				}
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});	
	}
	
	$(function(){
		<#if user??>
		$(".selected-tit i").tooltip({
			animation:true,
			trigger:'hover' //触发tooltip的事件
		});
		</#if>
		
		$(".atip").tooltip({
			animation:true,
			html:true,
			trigger:'hover' //触发tooltip的事件
		});
		 
		// 已选清单状态
		$(".selected-list-box").on("click",function(){
			$(".selected-list-hide").toggleClass("selected-list-show");
		});
		
		$("#packageCheck").attr("checked",false);
		$("#isSelfLabel").attr("checked",false);

	$('.a-img img').zoomify();
	
	//和渲染热卖推荐商品对应的JS
	cateTopAjax();

		// 选中二次加工按钮状态
		$("#packageCheck").click(function(){
			if($(this).is(":checked")){
				$(".processing-img").show();
			}else{
				$(".processing-img").hide();
				$(".processing-brand").hide();
				$("#pcinfo").html("需要二次加工（备注：加工费不含辅料费）");
				$("#productPackageId").val(null);
				$("#isSelfLabel").attr("checked",false);
				$("input[name='pak']").attr("checked",false);
			}
		});
		
		$(".processing-img input[name='pak']").bind("click",function(){
			var val = "";
			if ($(this).is(':checked')) {
				var info = "您选择了【";
				var packname = $(this).data("name");
				info += packname + "】，订单提交后，客服会第一时间与您联系，加工费不含辅料费。";
				var packname = $(this).data("name");
				info += packname + "】";
				val = $(this).val();
				$("#pcinfo").html(info);
				$(this).val($(this).val());
				$("#productPackageId").val($(this).val());
			}
			$("input[name='pak']").each(function() {
				if ($(this).val() != val) {
					$(this).attr("checked",false);	
				}
			});
			$(".processing-img").hide();
			$(".processing-brand").show();
		});
		
		/****************
		// 点击二次加工套餐系列
		$(".processing-img li").on("click",function(){
			var info = "需要二次加工，订单提交后，客服会第一时间与您联系，您选择了【";
			var packname = $(this).find("p").html().trim();
			info += packname + "】";
			$(this).parent().parent().hide();
			$(".processing-brand").show();
			
			$("#productPackageId").val($(this).data("product-pageck-id"));
			
			$("#pcinfo").html(info);
		});
		*****************/
		
		$('.close-panel').on('click',function(){
			$('.toolbar-wrap').hide(500);
		});
		
		//异步加载评价及咨询列表
		showAllComments("all","${productId!''}");
		showProductAskList("${productId!''}");
		showProductActInfo('${productId!0}','${(seller.id)!0}');
		showProductFlashSaleInfo('${productId!0}');
		
		//默认将规格选中
		var norms = $("#goodsNormAttrId").val();
		if(!isEmpty(norms)){
			var strs= new Array(); //定义数组 
			strs=norms.split(","); //字符分割 
			for(i=0;i<strs.length;i++){
				 $("#ChooseNorm"+i).find(".item").each(function(){
						var attrid = $(this).attr("val");
						if(attrid==strs[i]){
							 //规格详情
							var norminfo = $(this).parent().siblings(".dt").html();
							var attrinfo = $(this).find("a").attr("title");
							$(this).siblings(".attrname").val("").val(norminfo+attrinfo);
							$(this).siblings(".attrid").val("").val($(this).attr("val"));
							$(this).addClass("selected").siblings().removeClass("selected");
							return;
						}
					});
			}
		}
		
		//选择规格
		$(".choosenorms .item").click(function(){
			//Terry add 20160805 若库存小于100则不能选择
			var stock = $(this).data('stock');
			if (stock == 1) return;
			//Terry add end
			if($(this).data('iscustom') == true){
				//加载图片
				var pic_ = $(this).data('pic-url');
				if(pic_){
					var url_ = ";s"
					if(pic_ == "-1"){
						url_ = "${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg";
					} else{
						url_ = "${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}"+pic_;
					}
					$(".jqzoom img").attr("src",url_).attr("jqimg",url_);
				}
			}
			//为隐藏域赋值
			$(this).siblings(".attrid").val("").val($(this).attr("val"));
			//规格详情
			var norminfo = $(this).parent().siblings(".dt").html();
			var attrinfo = $(this).find("a").attr("title");
			$(this).siblings(".attrname").val("").val(norminfo+attrinfo);
			$(this).addClass("selected").siblings().removeClass("selected");
			//异步加载价格及库存信息
			queryPrice();
		});
		
		//校验
		jQuery.validator.addMethod("balance", function(value,element) {
			var productStock = Number($("#productStock").val());
			   var  stockNums = $("#stockNums").val;
	        // value = parseInt(value)*10;
	           value = parseInt(value)*parseInt(stockNums);
	           productStock = parseInt(productStock);
	         return this.optional(element) || value<= productStock; 
	    }, $.validator.format(" 不能大于库存 "));
	          
	    jQuery.validator.addMethod("checkBalance", function(value,element) {
	    	 var  stockNums = $("#stockNums").val;
	         var  balance=0;
	         //value = parseInt(value)*10;
	         value = parseInt(value)*parseInt(stockNums);
	         return this.optional(element) || value>balance;   
	    }, $.validator.format(" 购买数量必须大于0 "));
	    
		jQuery("#cartForm").validate({
			errorPlacement : function(error, element) {
				var obj = element.parent().parent().
					siblings(".em-errMes").css('display', 'block');
				error.appendTo(obj).css("background","#FFEEEE");
			},
	        rules : {
	            "count":{required:true,number:true,balance:true,checkBalance:true}
	        },
	        messages:{
	            "count":{required:"请输入数量",number:"请输入数字"}
	        }
	    }); 
		
		//添加进货单  立即购买点击事件
		$(".buynow,.addcart").click(function(){
			var isAddcart = $(this).hasClass("addcart");
			//未登录不能添加进货单
			if (!isUserLogin()) {
				showid('ui-dialog');
				return;
			}
			
			var status = $("#status").val();
			var productId = $("#productId").val();
			if(status>0 && productId==10){
				jAlert("尊敬的用户，该商品仅供首次购买本商城商品时体验购买，请选择其它商品！");
				return;
			}
			
			//如果有规格，判断是否选择了规格，如果没有规格则判断是否有货品ID
			//默认只有两个规格
			var Selectcolor = $(this).parents("#itemInfo").find("#ChooseNorm0 .item");
			var SelectVersion = $(this).parents("#itemInfo").find("#ChooseNorm1 .item");
			if(Selectcolor.hasClass("selected") && SelectVersion.hasClass("selected")){
				$(this).parents("#itemInfo").find(".tzm-border").css("display",'none');
			}else{
				// 如果是没有规格的商品，则判断隐藏的货品ID，如果有则通过，没有则报错
				var goodId = $("#productGoodsId").val();
				if (goodId == null || goodId == "") {
					$(this).parents("#itemInfo").find(".tzm-border").css("display",'block');
					return false;
				}
			}
			if($("#cartForm").valid()){
				//重新赋值  计算方式：*10
				var stockNums = $("#stockNums").val();
				$("#buy-num-hidden").val($("#buy-num").val()*parseInt(stockNums));
				//先做购买数校验
				var inputstock_ = Number($("#buy-num-hidden").val());
				var maxStock_ = Number($("#maxStock").val());
				if(inputstock_ > maxStock_){
					if(confirm("很抱歉，您目前最大只能购买"+maxStock_+unitVal+
							"商品，系统将会修改您下单的数量，是否继续提交？")){
						$("#buy-num-hidden").val(maxStock_);
					} else{
						return false;
					}
				}
				
				var params = $("#cartForm").serialize();
				 $.ajax({
					type : "POST",
					url :  domain+"/cart/addtocart.html",
					data : params,
					dataType : "json",
					success : function(data) {
						if(data.success){
							if(isAddcart){
								//不跳转
								window.location.reload(true);
							}else{
								//跳转到提交订单页面
								window.location.href=domain+"/order/info.html";
							}
						}else{
							jAlert(data.message);
						}
					},
					error : function() {
						jAlert("数据加载失败！");
					}
				}); 
			} 
		});
	}); //页面初始加载完毕
	
	
	/**
    * 输入购买数量后进行校验
	*/
 /* function checknum(obj){
		var val = $(obj).val();
		var datanow = $(obj).attr("data-now");
		//判断是否为正整数
		if(!isIntege1(val)){
			$(obj).val(datanow);
			return false;
		}
		//如果值为10 不能点-
		var decrement = $(obj).siblings(".btn-reduce");
	    if (parseInt(val)==10){
	    	$(decrement).attr('disabled',true);
	    }else if(parseInt(val) < 10){
			$(decrement).attr('disabled',true);
			val = parseInt(val) + 10;
			$(obj).val(val);
			return false;
		} else{
	    	$(decrement).removeAttr("disabled");
	    }
		$(obj).attr("data-now",val);
	}  */
function checknum(obj){
		var val = $(obj).val();
		var datanow = $(obj).attr("data-now");
		//判断是否为正整数
		if(!isIntege1(val)){
			$(obj).val(1);
			return false;
		}
		//如果值为1 不能点-
		var decrement = $(obj).siblings(".btn-reduce");
	    if (parseInt(val)==1){
	    	$(decrement).attr('disabled',true);
	    }else if(parseInt(val) < 1){
			$(decrement).attr('disabled',true);
			val = parseInt(val) + 1;
			$(obj).val(val);
			return false;
		} else{
	    	$(decrement).removeAttr("disabled");
	    }
		$(obj).attr("data-now",val);
	} 

	//显示所有评价列表
	function showAllComments(type,productId){
		$.ajax({
			type : "POST",
			url :  domain+"/commentsList.html",
			data : {type:type,productId:productId,targetDiv:"commentsList"},
			dataType : "html",
			success : function(data) {
				$('#commentsList').html(data);
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});
	}
	
	//点击规格信息查询
	function queryPrice(){
		var flag = true;
		$("input[name='specId']").each(function(){
				if($(this).val().length<1){
					flag = false;
					return;
				}
			}
		);
		
		var params = $("#cartForm").serialize();
		if(flag){
			$.ajax({
				type : "POST",
				url :  domain+"/getGoodsInfo.html",
				data : params,
				dataType : "json",
				success : function(data) {
					var goods = data.data;
					if(goods.id!=null){
						//商城价格
						$("#mallPcPrice").html("￥"+goods.mallPcPrice);
						//库存
						//var stockinfo_ = "货源充足";
						var stockinfo_ = "库存"+goods.productStock+unitVal;
						var color_ = "green";
						var ps_ = Number(goods.productStock);
						var warn_ = Number(goods.productStockWarning);
						if(ps_ < warn_){
							//stockinfo_ = "货源紧张";
							stockinfo_ = "库存"+goods.productStock+unitVal;
							color_ = "red";
						}
						$("#productStockWarning").html(stockinfo_).css("color",color_);
						$("#productStock").val(goods.productStock);
						//货品ID
						$("#productGoodsId").val(goods.id);
						//最大购买数
						$("#maxStock").val(goods.maxStock);
					}else{
						//无货品信息 则不能添加进货单和购买
						jAlert("货品信息为空，请与管理员联系");
						$(".buynow").attr("disabled","disabled");
						$(".addcart").attr("disabled","disabled");
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});
		}
	}
	
	
	//显示咨询列表
	function showProductAskList(productId){
		$.ajax({
			type : "POST",
			url :  domain+"/productAskList.html",
			data : {productId:productId,targetDiv:"productAskList"},
			dataType : "html",
			success : function(data) {
				$('#productAskList').html(data);
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});
	}
	/**
	 * 关注产品
	 */
	function collectProductMy(id){
		//未登录不能关注产品
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
		$.ajax({
			type:'GET',
			dataType:'json',
			async:false,
			data:{productId:id},
			url:domain+'/member/docollectproduct.html',
			success:function(data){
				if(data.success){
					// jAlert("收藏成功");
					//window.location.href=domain+"/member/collectproduct.html";
					$("#collectProduct").html("已收藏");
					$("#collectProduct").attr("disabled","disabled");
				}else{
					jAlert(data.message);
				}
			}
		});
	}
	
	/**
	 * 关注店铺
	 */
	function collectShop(id){
		//未登录不能关注店铺
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
		$.ajax({
			type:'GET',
			dataType:'json',
			async:false,
			data:{sellerId:id},
			url:domain+'/member/docollectshop.html',
			success:function(data){
				if(data.success){
					//jAlert("收藏成功");
					$("#collectShop").html("已收藏");
					$("#collectShop").attr("disabled","disabled");
				}else{
					jAlert(data.message);
				}
			}
		});
	}
	
	
	/**
	 * 添加咨询
	 */
	function editConsultaiion(productId,sellerId){
		//未登录不能添加咨询
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
	 	 $.ajax({
			type:"GET",
			url:domain+"/member/addquestion.html",
			dataType:"html",
			async : false,
			data:{productId:productId,sellerId:sellerId},
			success:function(data){
				//加载数据
				$("#editConsultaiion").html(data);
			},
			error:function(){
				jAlert("异常，请重试！");
			}
		});
	}
	
	// 显示优惠券列表
	function showCouponList() {
		if($('.toolbar-wrap').css('display')=='none'){
			$('.toolbar-wrap').show(500);
		}else {
			$('.toolbar-wrap').hide(500);
		}
	}
	
	// 异步加载商品促销信息
	function showProductActInfo(productId, sellerId){
		var sellerName = "${(seller.sellerName)!''}";
		var stockNums = $("#stockNums").val();
		$.ajax({
			type : "POST",
			url :  domain+"/getproductactinfo.html",
			data : {productId:productId,sellerId:sellerId},
			dataType : "json",
			success : function(data) {
				if (data.success && data.data != null) {
					var productActVO = data.data;
					// 加载单品立减和满减
					if (productActVO.actSingle == null && productActVO.actFull == null) {
						// 都是空不作操作
					} else {
						var actHtml = '<div class="dt">促销信息：</div>';
						actHtml += '<div class="dd">';
						var actSingle = productActVO.actSingle;
						if (actSingle != null) {
							actHtml += '	<span class="fullCut">';
							if (actSingle.type == 1) {
								actHtml += '		<em>立减</em> 下单即享' + actSingle.discount + '元优惠';
							} else if (actSingle.type == 2) {
							//	var dis = parseInt(parseFloat(actSingle.discount)*10);
								var dis = parseInt(parseFloat(actSingle.discount)*parseInt(stockNums));
								actHtml += '		<em>立减</em> 下单即享' + dis + '折优惠';
							}
							actHtml += '	</span>';
						}
						var actFull = productActVO.actFull;
						if (actFull != null) {
							actHtml += '	<span class="fullCut">';
							actHtml += '		<em>满减</em> ';
							// 满999.00减10.00，满4999.00减100.00，满12999.00减300.00
							if (actFull.firstFull != null && actFull.firstFull > 0) {
								actHtml += '满' + actFull.firstFull + '减' + actFull.firstDiscount;
							}
							if (actFull.secondFull != null && actFull.secondFull > 0) {
								actHtml += '，满' + actFull.secondFull + '减' + actFull.secondDiscount;
							}
							if (actFull.thirdFull != null && actFull.thirdFull > 0) {
								actHtml += '，满' + actFull.thirdFull + '减' + actFull.thirdDiscount;
							}
							actHtml += '	</span>';
						}
						actHtml += '</div>';
						$("#actInfoDiv").html(actHtml);
					}
					
					// 加载优惠券信息
					var couponList = productActVO.couponList;
					if (couponList != null && couponList.length > 0) {
						var couponBtnHtml = '<span >领　　券：</span>'
										  + '<a href="javascript:;" class="J-open-tb receive" onclick="showCouponList()"><span>我要领券</span></a>';
						//$("#couponInfoDiv").html(couponBtnHtml);
						var couponListHtml = "";
						for (var i=0; i < couponList.length; i++) {
							var coupon = couponList[i];
							couponListHtml += '<div class="coupon-item">';
							couponListHtml += '	<div class="coupon-price">';
							couponListHtml += '		<em>￥</em><span>'+ coupon.couponValue +'</span>';
							couponListHtml += '	</div>';
							couponListHtml += '	<div class="coupon-info">';
							couponListHtml += '		<span class="coupon-info-tit">仅限'+ sellerName +'使用</span>';
							couponListHtml += '		<span class="condition">满'+coupon.minAmount+'元可用</span>';
							couponListHtml += '	</div>';
							couponListHtml += '	<a href="javascript:;" class="btn-get" onclick=receiveCoupon(' + coupon.id + ')>';
							couponListHtml += '		立即领取';
							couponListHtml += '	</a>';
							couponListHtml += '	<p class="coupon-time">有效期:'+coupon.useStartTime.substring(0,10)+' - '+coupon.useEndTime.substring(0,10)+'</p>';
							couponListHtml += '</div>';
						}
						$("#couponListDiv").html(couponListHtml);
					}
					
				} else {
					
				}
			}
		});
	}
	
	// 异步加载限时抢购活动信息
	function showProductFlashSaleInfo(productId) {
		$.ajax({
			type : "POST",
			url :  domain+"/getproductflashinfo.html",
			data : {productId:productId},
			dataType : "json",
			success : function(data) {
				if (data.success && data.data != null) {
					var productActVO = data.data;
					// 加载限时抢购信息
					if (productActVO.actFlashSaleProduct != null) {
						var actFlashSaleProduct = productActVO.actFlashSaleProduct;
						var flashHtml = '<div class="dt">整点秒杀：</div>';
						flashHtml += '<div class="dd">';
						flashHtml += '<strong class="p-price" style="font-weight:400;font-size:12px">';
						var stageType = productActVO.stageType;
						// 如果是正在进行
						if (stageType == 2) {
							flashHtml += actFlashSaleProduct.price + '元秒杀正在进行中&nbsp;&nbsp;';
							flashHtml += '<a href="javascript:;" class="J-open-tb receive" onclick="gotoFlashSale()"><span class="go-flash-sale">我要秒杀</span></a>';
						} else if (stageType == 3) {
							// 如果是即将开始
							flashHtml += actFlashSaleProduct.price + '元秒杀即将开始&nbsp;&nbsp;';
							flashHtml += '<a href="javascript:;" class="J-open-tb receive" onclick="gotoFlashSale()"><span class="go-flash-sale">去看看</span></a>';
						} else if (stageType == 1) {
							// 如果是已经结束
							flashHtml += actFlashSaleProduct.price + '元秒杀结束了~~~&nbsp;&nbsp;';
							flashHtml += '<a href="javascript:;" class="J-open-tb receive" onclick="gotoFlashSale()"><span class="go-flash-sale">去看看</span></a>';
						}
						flashHtml += '</strong>';
						$("#flashSaleInfoDiv").html(flashHtml);
					}
				}
			}
		});
	}
	
	// 领取优惠券
	function receiveCoupon(couponId) {
		//未登录不能领取
		if (!isUserLogin()) {
			showid('ui-dialog');
			return;
		}
	 	$.ajax({
			type:"POST",
			url:domain+"/member/reveivecoupon.html",
			dataType:"json",
			data:{couponId:couponId},
			success:function(data){
				if (data.success) {
					jAlert("领取成功，您可在用户中心查看您的红包！");
				} else {
					jAlert(data.message);
				}
			},
			error:function(){
				jAlert("领取失败，请稍后再试！");
			}
		});
	}
	
	// 跳转到限时抢购页面
	function gotoFlashSale() {
		window.location.href=domain+"/product/${(productId)!0}.html?type=1";  
	}
	
	$("#gotop").click(function(){
		acceleration = 0.3;
		time = 15;
		var x=0,y=0;

		if (document.documentElement) {
		   x = document.documentElement.scrollLeft || 0;
		   y = document.documentElement.scrollTop  || 0;
		}

		var speed = 1 + acceleration;
		window.scrollTo(x, Math.floor(y / speed));
		if(y > 0) {
			var invokeFunction = "gototop()";
			window.setTimeout(invokeFunction, time);
		}
	})
//仝照美  如果商品状态为7并且上架时间大于当前时间 则特殊处理
	var d2=new Date();//取今天的日期
	var tzm_productState = $("#tzm_productState").val();
	var tzm_productUpTime = $("#tzm_productUpTime").val();
	var showUpTime = tzm_productUpTime.replace(tzm_productUpTime[4],"年");
	showUpTime = showUpTime.replace(showUpTime[7],"月");
	showUpTime = showUpTime.replace(" ","日  ");
	showUpTime = showUpTime.substring(0,showUpTime.lastIndexOf(":"));
	var d1 = new Date(Date.parse(tzm_productUpTime));
	var bool = d1>d2 && tzm_productState==7;
	if(bool){//如果为true则满足条件，则执行相应JS显示指定内容
		var html = "";
		html += "<h3 style='color:#FF3C23;font-size:14px;'><strong>该商品将于";
		html += showUpTime;
		html += "出售，敬请期待！";
		html += "</strong></h3>";
		$(".m-itemover .m-itemover-title").html(html);
		//显示文字调整结束 
		//$(".spec-scroll").hide();
		//$(".jqzoom img").attr('src',"${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ccumi.png");
		//$(".jqzoom").removeClass("jqzoom");
		//$(".right").hide();
		//显示文字调整结束 
	}
//end
</script>
<!-- 登录弹出框 -->
<#include "/front/commons/logindialog.ftl" />
<#include "/front/commons/_endbig.ftl" />
<script type="text/javascript">
document.write('<img width="1" height="1" style="position:absolute;" src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product_look_log.html?memberId='+ memberId + '&productId='+ ${productId!0} + '" />');
</script>