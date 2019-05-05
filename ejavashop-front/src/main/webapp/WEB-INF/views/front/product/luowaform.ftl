<!-- 进货单表单 -->
<form action="" method="POST"  name="cartForm" id="cartForm">
<!-- 隐藏域 -->
<!-- 产品ID -->
<input  type="hidden" id="productId" value="${productId!''}">
<input  type="hidden" name="sellerId" value="${(seller.id)!''}">
<input  type="hidden" name="productGoodsId" id="productGoodsId" value="${(goods.id)!''}">
<input  type="hidden"  id='goodsNormAttrId' value="${(goods.normAttrId)!''}">
<input  type="hidden"  id='maxStock' value="${(goods.maxStock)!99999}">
<input  type="hidden"  id="status" value="${status!''}">
<input  type="hidden"  id="stockNums" value="${(pAttr.value)!'10'}">
<input  type="hidden"  id="priceStatusId" value="${(product.priceStatus)!''}">
<input  type="hidden"  id="price2StatusId" value="${(product.price2Status)!''}">
	<div class='m-item-inner'>
		<div id='itemInfo'>
			<div id='detaile-name'>
				<h1 class="d-title">
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
				<#--
					<div id='CommentCount'>
						<p class='cumulative-comment'>累计评价</p>
						<a id='CountNumber'>${(statisticsVO.productCommentsAllCount)!'' }</a>
					</div>
				-->
				<!-- 价格显示区块开始 -->
				<!-- 就一个价  一口价 -->
				<#if product.priceStatus == 1>
					<div class="summary-product">
						<em class="obj-title">裸袜价:</em>
						<#if norms??>
						<#list norms as norm>
						<#list norm.attrList as normattr>
						<strong class='p-price' id="mallPcPrice_${normattr.id}" style="<#if normattr_index == 0 >
											        display:inline-block;
											        <#else>
											        display:none;
											        </#if>"">
							<span class="fd-cny">¥</span>
							<span class="fd-value">${(normattr.pgMallPcPrice)?string('0.00')!'0.00'}</span>
							<input type="hidden" id="ppw_mallPcPrice_${normattr.id}" name="ppw_mallPcPrice" value="${(normattr.pgMallPcPrice)?string('0.00')!'0.00'}"/>
							<span class="fd-unit">/${(product.unit)!''}</span>
						</strong>
						</#list>
						</#list>
						</#if>
						
						<#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != goods.mallPcPrice>
							<span class="linethrowprice">
								<del>
									¥${(goods.mallOriginalPrice)?string('0.00')!'0.00'}
								</del>
							</span>
						</#if>
					</div>
					<div class="qipiliangdiv">
						<span class="qipiliangspanA">起批量:</span>
						<span class="qipiliangspanB">≥${(pAttr.value)!'10'}${(product.unit)!''}(1手)</span>
					</div>
				</#if>
				<!-- 有三个价  阶梯 价 -->
				<#if productPrice?? && product.pricePid?? && product.priceStatus == 2>
					<input type="hidden" id="jti_mallPcPrice1" value="${(productPrice.price1)?string('0.00')!'0.00'}"/>
					<input type="hidden" id="jti_mallPcPrice2" value="${(productPrice.price2)?string('0.00')!'0.00'}"/>
					<input type="hidden" id="jti_mallPcPrice3" value="${(productPrice.price3)?string('0.00')!'0.00'}"/>
					<input type="hidden" id="jti_mallPcPrice1_qpl" value="${(productPrice.price1E)!''}"/>
					<input type="hidden" id="jti_mallPcPrice2_qpl" value="${(productPrice.price2S)!''}"/>
					<input type="hidden" id="jti_mallPcPrice3_qpl" value="${(productPrice.price3S)!''}"/>
					<div class="summary-product">
						<span class="ppwjietijiaspricespan">裸袜价:</span>
						<strong class="ppwjietijiaspan">
							<span class="fd-cny">¥</span>
							<span class='fd-value' id="mallPcPrice1" >${(productPrice.price1)?string('0.00')!'0.00'}</span>
							<span class="fd-unit">/${(product.unit)!''}</span>
						</strong>
						<strong class="ppwjietijiaspan">
							<span class="fd-cny">¥</span>
							<span class='fd-value' id="mallPcPrice2" >${(productPrice.price2)?string('0.00')!'0.00'}</span>
							<span class="fd-unit">/${(product.unit)!''}</span>
						</strong>
						<strong class="ppwjietijiaspan">
							<span class="fd-cny">¥</span>
							<span class='fd-value' id="mallPcPrice3" >${(productPrice.price3)?string('0.00')!'0.00'}</span>
							<span class="fd-unit">/${(product.unit)!''}</span>
						</strong>
						<#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != goods.mallPcPrice>
							<span class="linethrowprice">
								<!-- <del>
									¥${(goods.mallOriginalPrice)?string('0.00')!'0.00'}
								</del> -->
							</span>
						</#if>
						<div class="qipiliangdiv" style="width: 568px;">
							<span class="qipiliangspanA" style="margin-left: -12px;">起批量:</span>
							<span class="qipiliangspanB">
								${(productPrice.price1S)!''}-${(productPrice.price1E)!''}${(product.unit)!''}
							</span>
							<span class="qipiliangspanB" style="margin-left: 68px;"> 
								${(productPrice.price2S)!''}-${(productPrice.price2E)!''}${(product.unit)!''}
							</span>
							<span class="qipiliangspanB" style="margin-left: 50px;">
								≥${(productPrice.price3S)!''}${(product.unit)!''}
							</span>
						</div>
					</div>
				</#if>
				<!-- 二个价   整箱价和散价 -->
				<#if product.priceStatus == 3>
					<input type="hidden" id="zxj_mallPcPrice_t" value="${(product.mallPcPrice)?string('0.00')!'0.00'}"/>
					<input type="hidden" id="zxj_mallPcPrice_s" value="${(product.scatteredPrice)?string('0.00')!'0.00'}"/>
					<input type="hidden" id="fullContainerQuantity_id" value="${(product.fullContainerQuantity)!''}"/>
					<input type="hidden" id="ppw_mallPcPrice" value="${(goods.mallPcPrice)?string('0.00')!'0.00'}"/>
					<div class="summary-product">
						<div class="pricestatusdiv3A">
							<span class="luowajiacss3A">裸袜价格:</span>
							<strong class='p-price' id="mallPcPrice_s" >
								<span>¥</span>
								${(product.scatteredPrice)?string('0.00')!'0.00'}
								<span class="fd-unit">/${(product.unit)!''}</span>
							</strong>
							<strong class='p-price' id="mallPcPrice_t" >
								<span>¥</span>
								${(product.mallPcPrice)?string('0.00')!'0.00'}
								<span class="fd-unit">/${(product.unit)!''}</span>
							</strong>
							<#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != product.mallPcPrice>
								<span class="linethrowprice">
									<del style="top: 10px;">
										¥${(goods.mallOriginalPrice)?string('0.00')!'0.00'}
									</del>
								</span>
							</#if>
						</div>
						<div class="pricestatusdiv3B">
							<span class="qipiliancss3B">起批数量:</span>
							<em style="padding-left: 40px;">${(pAttr.value)!'10'}*n ${(product.unit)!''}</em>
							<em style="padding-left: 108px;">${(product.fullContainerQuantity)!''}*n ${(product.unit)!''}</em>
						</div>
					</div>
					<div class="dabiaowadiv" style="min-height:0;">
						<div class="summary-product">
							<div class="pricestatusdiv3A">
								<span class="luowajiacss3A">二次加工:</span>
								<strong class='p-price' id="mallPcPrice_t" >
									<span>¥</span>
									${(product.mallPcPrice)?string('0.00')!'0.00'}
									<span class="fd-unit">/${(product.unit)!''}</span>
								</strong>
								<#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != product.mallPcPrice>
									<span class="linethrowprice">
										<del style="top: 10px;">
											¥${(goods.mallOriginalPrice)?string('0.00')!'0.00'}
										</del>
									</span>
								</#if>
							</div>
						</div>
					</div>
				</#if>
				<!-- 价格显示区块结束  -->
				<#--
				<div class="summary-product">
					<em>商 城 价：</em>
					<strong class='p-price' id="mallPcPrice" >￥${(goods.mallPcPrice)!''}</strong>
				</div>
				<div class="old-product">
					<em>市 场 价：</em>
					<del>￥${(product.marketPrice)!''}</del>
				</div>
				<div>&nbsp;</div>
				-->
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
			<!-- 打标价格信息显示在下面 -->
			<#if product.price2Status?? && (product.price2Status ==2 || product.price2Status ==1 )>
			<div class="dabiaowadiv">
					<!-- 打标袜阶梯价 -->
					<#if product.price2Status?? && product.price2Status ==2 >
						<input type="hidden" id="dabiaowa_mallPcPrice1" value="${(productPrice2.price1)?string('0.00')!'0.00'}"/>
						<input type="hidden" id="dabiaowa_mallPcPrice2" value="${(productPrice2.price2)?string('0.00')!'0.00'}"/>
						<input type="hidden" id="dabiaowa_mallPcPrice3" value="${(productPrice2.price3)?string('0.00')!'0.00'}"/>
						<input type="hidden" id="dabiaowa_mallPcPrice1_qpl" value="${(productPrice2.price1E)!''}"/>
						<input type="hidden" id="dabiaowa_mallPcPrice2_qpl" value="${(productPrice2.price2S)!''}"/>
						<input type="hidden" id="dabiaowa_mallPcPrice3_qpl" value="${(productPrice2.price3S)!''}"/>
						
						<span class="dabiaowaspanA">打标价:</span>
						<span class="dabiaowaspanAspan1">
							<span>¥</span>
							<#if productPrice2.price1??>${(productPrice2.price1)?string('0.00')!'999999'}<#else>${(productPrice2.price1)!'999999'}</#if>
							<i class="dabiaowa-unit">/${(product.unit)!''}</i>
						</span>
		  	 			<span class="dabiaowaspanAspan1">
							<span>¥</span>
		  	 				<#if productPrice2.price2??>${(productPrice2.price2)?string('0.00')!'999999'}<#else>${(productPrice2.price2)!'999999'}</#if>
		  	 				<i class="dabiaowa-unit">/${(product.unit)!''}</i>
		  	 			</span>
		  	 			<span class="dabiaowaspanAspan1">
							<span>¥</span>
		  	 				<#if productPrice2.price3??>${(productPrice2.price3)?string('0.00')!'999999'}<#else>${(productPrice2.price3)!'999999'}</#if>
		  	 				<i class="dabiaowa-unit">/${(product.unit)!''}</i>
		  	 			</span>
		  	 			<div class="qipiliangdiv">
		  	 				<span class="qipiliangspanA">起批量:</span>
							<span class="qipiliangspanB">
								${(productPrice2.price1S)}-${(productPrice2.price1E)}${(product.unit)!''}
							</span>
			  	 			<span class="qipiliangspanB" style="padding-left:121px;">
			  	 				${(productPrice2.price2S)}-${(productPrice2.price2E)}${(product.unit)!''}
			  	 			</span>						
			  	 			<span class="qipiliangspanB" style="padding-left:109px;">
			  	 				≥${(productPrice2.price3S)}${(product.unit)!''}
			  	 			</span>
		  	 			</div>
					<#elseif product.price2Status?? && product.price2Status ==1>
						<!-- 打标袜一口价 -->
						<input type="hidden" id="dabiaowaPriceOnlyId" value="${(productPrice2.priceOnly)?string('0.00')!'999999'}"/>
						<span class="dabiaowaspanA">打标价:</span>
						<span class="dabiaowa-cny">¥</span>
						<span class="dabiaowa-value"><#if productPrice2.priceOnly??>${(productPrice2.priceOnly)?string('0.00')!'999999'}<#else>${(productPrice2.priceOnly)!'999999'}</#if></span>
						<span class="dabiaowa-unit">/${(product.unit)!''}</span>
						<div class="qipiliangdiv">
							<span class="qipiliangspanA">起批量:</span>
							<span class="qipiliangspanB">≥${(pAttr.value)!'10'}${(product.unit)!''}(1手)</span>
						</div>
					<#else>
					</#if>
				</div>
				<!-- 打标信息区域 -->
				<#if productPackage2?? && product.productBrandId == 8 &&  product.id!=10>
					<div class="qipiliangdiv">
						<span class="qipiliangspanA" style="color: #666;">打标信息:</span>
						<#if productLeadPicList?? && productLeadPicList?size &gt; 0>
			        		<#list productLeadPicList as img>
			        			<#if !img_has_next>
								<img class="dabiaoimgcss" alt="" onmousemove="preview(this);" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${img}" width="30" height="30" onerror="this.src=http://images.dawawang.com/images/seller/1/8c2bd774-5384-4502-bb46-689655616cd2.jpg">
								</#if>
			        		</#list>
			        	</#if>
							        	
						<span id="dabiaoxinxieclipss" title="包装单位:${(productPackage2.packageUnitStr)!''}/商标:${(shangbiaoInfo)!''}/辅料:${(productPackage2.fuliaoStr)!'无'}/打标说明:${(productPackage2.describe)!''}">
							<span class="samemarginleft">包装单位:${(productPackage2.packageUnitStr)!''}</span>
							<span class="samemarginleft">商标:${(shangbiaoInfo)!''}</span>
							<span class="samemarginleft">辅料:${(productPackage2.fuliaoStr)!'无'}</span>
							<span class="samemarginleft">打标说明:${(productPackage2.describe)!''}</span>
						</span>
					</div>
				<#else>
				</#if>
			<#else>
			</#if>
			<!-- 颜色显示区块开始 -->
			<div id='Choose' class='p-choose-wrap clearfix' style="position: relative;">
					<#if norms??>
						<#list norms as norm>
							<div id='ChooseNorm${norm_index}' class='li choosenorms'>
								<div class='dt'>${norm.name}：</div>
								<div class='dd norms' >
									<#list norm.attrList as normattr>
										<div id="ppwitemid_${normattr.id}" class="item <#if normattr_index == 0 > selected <#else> </#if>" val="${normattr.id}" data-stock="${normattr.sort}"
											data-iscustom="${(norm.isCustom)?string('true','false')}"
											<#--下架-->
											<#if (normattr.sort == 1) >
											style="opacity:0.3; filter: alpha(opacity=30); background-color:#000;"	
											</#if>
											data-pic-url="${(normattr.url)!''}">
											<b class="ppwitemskuB_${normattr.id}"></b>
											<a href='javascript:void(0);' title='${normattr.name}' class="ppwitemskuA_${normattr.id}"
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
					<!-- 颜色显示区块结束 -->
					<!-- 购买数量区块开始 -->
					<div id='ChooseBtns' class='li'>
						<!--  循环显示开始-->
						<#if norms??>
						<#list norms as norm>
						<#list norm.attrList as normattr>
							<div class='pro_key' id="ppwdd_${normattr.id}" style="<#if normattr_index == 0 >
											        display:block;
											        <#else>
											        display:none;
											        </#if>"">
				       		 <!-- 库存数据提示区域 -->
								<div class="luowapstockdiv">
									<span class="luowastockcount">库存数量:</span>
									<span class="product-stock" style="margin-left:50px;">
										<input type="hidden" name="ppwProductStock" id="productStock_${(normattr.id)!'0' }" value="${(normattr.productStock)!'0'}" />
											<#if ((normattr.productStock)!0) &gt;= ((goods.productStockWarning)!0) >
												<em style="color:green;">${(normattr.productStock)!'0'}${(product.unit)!''}可售</em>
											<#else>
												<em style="color:red;">${(normattr.productStock)!'0'}${(product.unit)!''}可售</em>
											</#if>
										<#if product.isCxbh?? && product.isCxbh == 1>
										<span style="margin-left: 20px;font-size:14px;font-weight: bold;color: red;">
											<span class="fa fa-recycle bg7" style="color: #69BA51;"></span>&nbsp;本品会持续补货
										</span>
										<#else>
										<span></span>
										</#if>
									</span>
								</div>
									<span class="tip-qipiliang">&nbsp;按手起批&nbsp;1手=${(pAttr.value)!'10'}${(product.unit)!''}&nbsp;</span>
								<!-- 库存显示区域结束   -->
								<div class='dt' style="padding-top: 0;">购买数量:</div>
								<div class="luowadivcss">
									<input type="hidden" name="productPackageId" id="productPackageId_${normattr.id}_two"/>
									<input type="hidden" name="luowapop_dabiaowaFlag" id="dabiaowaFlagId_${(normattr.id)!'0' }_two" value="0" />
									<input type="checkbox" name="isSelfLabel" id="isSelfLabel_${normattr.id}_two" class="process-item" style="width: 0;height: 0;display: none;"/>
									<span class="luowadivcssspan">裸&#12288;&#12288;袜</span>
									<div class='amountnum' style="float: none;padding-top:0;">
										<a class="countbtn unclick" href="javascript:;" id="productMinus_${(normattr.id)!'0' }_two">-</a>
										<input type='text' class="coutinput" name="ppwpop_number_two" id="ppwnumber_${(normattr.id)!'0' }_two" value='0' onchange="checknumtwo(this)">
										<a class="countbtn <#if normattr.productStock?? && normattr.productStock==0 >unclick<#else>countaddbtn</#if>" href="javascript:;" id="productPlus_${(normattr.id)!'0' }_two">+</a>
										<input type='hidden' id="buy-num-hidden_${(normattr.id)!'0' }_two" name="luowacount" value="0" />
										<span style="line-height: 27px;">&#12288;(手)</span>
									</div>
								</div>
								<#if product.dabiaowaFlag?? && product.dabiaowaFlag==1>
								<input type="hidden" name="productPackageId" id="productPackageId_${normattr.id}_three"/>
								<input type="hidden" name="luowapop_dabiaowaFlag" id="dabiaowaFlagId_${(normattr.id)!'0' }_three" value="1" />
								<input type="checkbox" name="isSelfLabel" id="isSelfLabel_${normattr.id}_three" class="process-item" style="width: 0;height: 0;display: none;"/>
								<div class="dabiaowadivcss">
									<span class="dabiaowadivcssspan">打&nbsp;标&nbsp;&nbsp;袜</span>
									<div class='amountnum' style="float: none;padding-top:0;">
										<a class="countbtn unclick" href="javascript:;" id="productMinus_${(normattr.id)!'0' }_three">-</a>
										<input type='text' class="coutinput" name="ppwpop_number_three" id="ppwnumber_${(normattr.id)!'0' }_three" value='0' onchange="checknumthree(this)">
										<a class="countbtn <#if normattr.productStock?? && normattr.productStock==0 >unclick<#else>countaddbtn</#if>" href="javascript:;" id="productPlus_${(normattr.id)!'0' }_three">+</a>
										<input type='hidden' id="buy-num-hidden_${(normattr.id)!'0' }_three" name="luowacount" value="0" />
										<span style="line-height: 27px;">&#12288;(手)</span>
									</div>
								</div>
								<#else>
								<input type="hidden" name="productPackageId" id="productPackageId_${normattr.id}_three"/>
								<input type="hidden" name="luowapop_dabiaowaFlag" id="dabiaowaFlagId_${(normattr.id)!'0' }_three" value="0" />
								<input type="checkbox" name="isSelfLabel" id="isSelfLabel_${normattr.id}_three" class="process-item" style="width: 0;height: 0;display: none;"/>
								<input type='hidden' id="buy-num-hidden_${(normattr.id)!'0' }_three" name="luowacount" value="0" />
								</#if> 
								<div class="twojiagdivcss">
									<span class="twojiagdivcssspan">二次加工</span>
									<div class='amountnum' style="float: none;padding-top:0;">
										<a class="countbtn unclick" href="javascript:;" id="productMinus_${(normattr.id)!'0' }_four">-</a>
										<input type='text' class="coutinput" name="ppwpop_number_four" id="ppwnumber_${(normattr.id)!'0' }_four" value='0' onchange="checknumfour(this)">
										<a class="countbtn <#if normattr.productStock?? && normattr.productStock==0 >unclick<#else>countaddbtn</#if>" href="javascript:;" id="productPlus_${(normattr.id)!'0' }_four">+</a>
										<input type='hidden' id="buy-num-hidden_${(normattr.id)!'0' }_four" name="luowacount" value="0" />
										<span class="abcabcspan" style="vertical-align:middle;">&#12288;(手)</span>
									</div>
									<#if productPackage?? && productBrand.id== 8 && product.id !=10 >
										<!-- 需要二次加工选项 -->
										<div class="processing-box" style="clear: both;">
											<p class="processing-p" style="margin-left: 100px;">
												<label style="margin-top: 3px;">
													<input type="checkbox" name="packageCheck" id="packageCheck_${normattr.id}" class="process-item" style="vertical-align:middle;" />
													<span id="pcinfo_${normattr.id}" style="vertical-align:middle;">需要二次加工（加工费不含辅料费）</span>
												</label>
											</p>
											<input type="hidden" name="productPackageId" id="productPackageId_${normattr.id}_four"/>
											<input type="hidden" name="luowapop_dabiaowaFlag" id="dabiaowaFlagId_${(normattr.id)!'0' }_four" value="0" />
											<div class="processing-img processing-img_${normattr.id}">
												<div class="ercijiaogongHead">
													<span>选择二次加工方式</span> <i class="fa fa-times-circle" onclick="closeProcessingImg(${normattr.id})"></i>
												</div>
												<ul>
													<#list productPackage as pka>
												 	<li data-product-pageck-id="${pka.id}">
												 		<a href="javascript:;" class="a-img">
												 			<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(pka.image)!''}" 
												 				onerror="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg'"
												 				alt="">
												 			<p data-original-title="<p style='text-align: left'>描述：${pka.describe}</p>"  class="atip">
												 				<label>
												 				<input class="aaaaainputcss" id="processimgidid_${normattr.id}" type="checkbox" name="pak" value="${pka.id}" data-name="${(pka.name)!''}"/>
												 					${(pka.name)!''}
												 				</label>
												 			</p>
												 		</a>
												 		<p>加工费<i>${(pka.price)!0}</i>元</p>
												 	</li>
												 	</#list>
												</ul>
											</div>
											<p class="processing-brand processing-brand_${normattr.id}">
												<label>
													<input type="checkbox" name="isSelfLabel" id="isSelfLabel_${normattr.id}_four" class="process-item" style="vertical-align: middle;"/>
													&nbsp;<span style="vertical-align: middle;">是否自供商标</span>
												</label>
											</p>
										</div>
										</#if>
								</div>
								<input type="hidden" name="ppwpop_productGoodsId" id="ppwpopproductGoodsId_${normattr.id}" value="${(normattr.productGoodsId)!'0' }"/>
							</div>
						</#list>
						</#list>
						</#if>
						<!-- 循环显示结束 -->
					</div>
					<!-- 购买数量区块结束  -->
				</div>
				<!-- 已选清单区块开始 -->
				<div class="obj-list" style="display:block;">
					<div class="list-total">
						<p class="checked-amount">
							<span class="checked-amount-value">0</span>
							<input type="hidden" id="checked-amount-value-id" value="0"/>
							<span class="checked-amount-unit">${(product.unit)!''}</span>
						</p>
						<span style="height: 20px;border-left:1px solid #e5e5e5;margin-top: 15px;float: left;"></span>
						<p class="checked-price">
							<span class="checked-price-value">0.00</span>
							<input type="hidden" id="checked-price-value-id" value="0"/>
							<span class="checked-price-unit">元</span>
						</p>
					</div>
					<div style="float: left;width: 150px;height: 40px;"></div>
					<div class="list-selected">
                    	已选清单
	                    <i class="fa fa-angle-up">
	                    </i>
	                </div>
	                <!-- 点击已经清单弹出清单列表 -->
	                <div class="list-info" style="display: none;">
	                	<table class="table-list">
	                		<tbody>
	                		<#if norms??>
							<#list norms as norm>
							<#list norm.attrList as normattr>
							<!-- 为裸袜显示 -->
	                			<tr id="tablelisttr_${normattr.id}_two" style="display: none;">
	                				<td class="prop">${normattr.name}</td>
	                				<td class="prop">
	                					裸袜
	                				</td>
	                				<td class="amount2">
	                					<span id="tablelisttdspanA_${normattr.id}_two"></span>
	                					${(product.unit)!''}
	                				</td>
	                				<td class="amount2" style="display: none;">
	                					<span id="tablelisttdspanB_${normattr.id}_two"></span>
	                					元
	                				</td>
	                			</tr>
	                			<!-- 为打标袜显示 -->
	                			<tr id="tablelisttr_${normattr.id}_three" style="display: none;">
	                				<td class="prop">${normattr.name}</td>
	                				<td class="prop">
	                					打标袜(不含加工费)
	                				</td>
	                				<td class="amount2">
	                					<span id="tablelisttdspanA_${normattr.id}_three"></span>
	                					${(product.unit)!''}
	                				</td>
	                				<td class="amount2" style="display: none;">
	                					<span id="tablelisttdspanB_${normattr.id}_three"></span>
	                					元
	                				</td>
	                			</tr>
	                			<!-- 为二次加工显示 -->
	                			<tr id="tablelisttr_${normattr.id}_four" style="display: none;">
	                				<td class="prop">${normattr.name}</td>
	                				<td class="prop">
	                					二次加工(不含加工费)
	                				</td>
	                				<td class="amount2">
	                					<span id="tablelisttdspanA_${normattr.id}_four"></span>
	                					${(product.unit)!''}
	                				</td>
	                				<td class="amount2" style="display: none;">
	                					<span id="tablelisttdspanB_${normattr.id}_four"></span>
	                					元
	                				</td>
	                			</tr>
	                		</#list>
							</#list>
							</#if>
	                		</tbody>
	                	</table>
	                </div>
				</div>
				<!-- 已选显示区块结束 -->
			<div class='obj-order'>
						<a href="javascript:;" class="do-purchase" id="luowaform_dopurchase">
							<span>立即订购</span>
						</a>
						<a href="javascript:;" class="do-cart" id="luowaform_docart">
							<span>加入进货单</span>
						</a>
						<button style="width: 160px;height: 38px;border-radius:0px;font-size: 16px;" onclick="selectAllSKUForLuowa('${productId!''}')"  type="button" class="btn btn-default">一键清仓</button>
				</div>
				<!-- 订购数量必须为大于0的整数 提示语 -->
				<div class="tip-container">
					<div class="ppwtip">订购数量必须为大于0的整数</div>
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