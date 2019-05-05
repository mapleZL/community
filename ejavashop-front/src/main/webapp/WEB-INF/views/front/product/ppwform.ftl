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
					<#if product.priceStatus == 1>
					<!--1 都是一口价的商品   -->
						<div class="summary-product">
							<em class="obj-title">价格:</em>
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
								<span class="fd-unit">/${(product.unit)!''}</span>
								<input type="hidden" id="ppw_mallPcPrice_${normattr.id}" name="ppw_mallPcPrice" value="${(normattr.pgMallPcPrice)?string('0.00')!'0.00'}"/>
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
							<div class="qipiliangdiv" style="float: none;width: 568px;margin-left: -12px;">
								<span class="qipiliangspanA">起批量:</span>
								<span class="qipiliangspanB">≥${(pAttr.value)!'10'}${(product.unit)!''}(1手)</span>
							</div>
						</div>
					</#if>
					<#if productPrice?? && product.pricePid?? && product.priceStatus == 2>
						<!-- 2 都是 阶梯价的商品 -->
						<input type="hidden" id="jti_mallPcPrice1" value="${(productPrice.price1)?string('0.00')!'0.00'}"/>
						<input type="hidden" id="jti_mallPcPrice2" value="${(productPrice.price2)?string('0.00')!'0.00'}"/>
						<input type="hidden" id="jti_mallPcPrice3" value="${(productPrice.price3)?string('0.00')!'0.00'}"/>
						<input type="hidden" id="jti_mallPcPrice1_qpl" value="${(productPrice.price1E)!''}"/>
						<input type="hidden" id="jti_mallPcPrice2_qpl" value="${(productPrice.price2S)!''}"/>
						<input type="hidden" id="jti_mallPcPrice3_qpl" value="${(productPrice.price3S)!''}"/>
						<div class="summary-product">
							<span class="ppwjietijiaspricespan">价格:</span>
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
					<#if product.priceStatus == 3>
						<!-- 整箱价 -->
						<input type="hidden" id="zxj_mallPcPrice_t" value="${(product.mallPcPrice)?string('0.00')!'0.00'}"/>
						<input type="hidden" id="zxj_mallPcPrice_s" value="${(product.scatteredPrice)?string('0.00')!'0.00'}"/>
						<input type="hidden" id="fullContainerQuantity_id" value="${(product.fullContainerQuantity)!''}"/>
						<input type="hidden" id="ppw_mallPcPrice" value="${(goods.mallPcPrice)?string('0.00')!'0.00'}"/>
						<div class="summary-product">
							<div class="pricestatusdiv3A">
								<span class="luowajiacss3A">价格:</span>
								<strong class='p-price' id="mallPcPrice_t" >
									<span>¥</span>
									${(product.mallPcPrice)?string('0.00')!'0.00'}
									<span class="fd-unit">/${(product.unit)!''}</span>
								</strong>
								<strong class='p-price' id="mallPcPrice_s" >
									<span>¥</span>
									${(product.scatteredPrice)?string('0.00')!'0.00'}
									<span class="fd-unit">/${(product.unit)!''}</span>
								</strong>
								<#if goods.mallOriginalPrice?? && goods.mallOriginalPrice != product.mallPcPrice>
									<del>
										¥${(goods.mallOriginalPrice)?string('0.00')!'0.00'}
									</del>
								</#if>
							</div>
							<div class="pricestatusdiv3B">
								<span class="qipiliancss3B">起批量:</span>
								<em>${(product.fullContainerQuantity)!''}*n ${(product.unit)!''}</em>
								<em style="padding-left: 99px;">10*n ${(product.unit)!''}</em>
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
								<div class='dt'>购买数量：</div>
								<span class="tip-qipiliang-2">&nbsp;按手起批&nbsp;1手=${(pAttr.value)!'10'}${(product.unit)!''}&nbsp;</span>
								<div class='amountnum'>
									<a class="countbtn unclick" href="javascript:;" id="productMinus_${(normattr.id)!'0' }">-</a>
									<input type='text' class="coutinput" name="ppwpop_number" id="ppwnumber_${(normattr.id)!'0' }" value='0' onchange="checknum(this)">
									<a class="countbtn  <#if normattr.productStock?? && normattr.productStock==0 >unclick<#else>countaddbtn</#if>" href="javascript:;" id="productPlus_${(normattr.id)!'0' }">+</a>
									<input type='hidden' id='buy-num-hidden_${(normattr.id)!'0' }' name="count" value='1' data-now="1">
									<span style="line-height: 27px;">&#12288;(手)</span>
								</div>
								<input type="hidden" name="ppwpop_productGoodsId" value=" ${(normattr.productGoodsId)!'0' }"/>
								<span class="product-stock">
									<input type="hidden" name="ppwProductStock" id="productStock_${(normattr.id)!'0' }" value="${(normattr.productStock)!'0'}" />
										<#if ((normattr.productStock)!0) &gt;= ((goods.productStockWarning)!0) >
											<em style="color:green;margin-left: -100px;">${(normattr.productStock)!'0'}${(product.unit)!''}可售</em>
										<#else>
											<em style="color:red;margin-left: -100px;">${(normattr.productStock)!'0'}${(product.unit)!''}可售</em>
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
						</#list>
						</#list>
						</#if>
						<!-- 循环显示结束 -->
					</div>
					<!-- 购买数量区块结束  -->
				</div>
				
				<!-- 已选清单区块开始 -->
				<div class="obj-list" style="display: block;">
					<div class="list-total">
						<p class="checked-amount">
							<span class="checked-amount-value">0</span>
							<span class="checked-amount-unit">${(product.unit)!''}</span>
						</p>
						<span style="height: 20px;border-left:1px solid #e5e5e5;margin-top: 15px;float: left;"></span>
						<p class="checked-price">
							<span class="checked-price-value">0.00</span>
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
	                			<tr id="tablelisttr_${normattr.id}" style="display: none;">
	                				<td class="prop">${normattr.name}</td>
	                				<td class="prop">
	                					<#if product.productBrandId?? && product.productBrandId==1>伊兹密尔
										<#elseif product.productBrandId?? && product.productBrandId==2>木几良品
										<#elseif product.productBrandId?? && product.productBrandId==3>南极人
										<#elseif product.productBrandId?? && product.productBrandId==4>北极绒
										<#elseif product.productBrandId?? && product.productBrandId==5>俞兆林
										<#elseif product.productBrandId?? && product.productBrandId==6>开花屋
										<#elseif product.productBrandId?? && product.productBrandId==7>浪莎
										<#elseif product.productBrandId?? && product.productBrandId==8>裸袜
										<#elseif product.productBrandId?? && product.productBrandId==9>Kaikaya
										<#elseif product.productBrandId?? && product.productBrandId==10>柳成行
										<#elseif product.productBrandId?? && product.productBrandId==11>哲步
										<#elseif product.productBrandId?? && product.productBrandId==12>启星
										<#elseif product.productBrandId?? && product.productBrandId==13>5IX/CCUMI
										</#if>
	                				</td>
	                				<td class="amount2">
	                					<span id="tablelisttdspanA_${normattr.id}"></span>
	                					${(product.unit)!''}
	                				</td>
	                				<td class="amount2" style="display: none;">
	                					<span id="tablelisttdspanB_${normattr.id}"></span>
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
						<a href="javascript:;" class="do-purchase" id="ppwform_dopurchase">
							<span>立即订购</span>
						</a>
						<a href="javascript:;" class="do-cart" id="ppwform_docart">
							<span>加入进货单</span>
						</a>
						<button style="width: 160px;height: 38px;border-radius:0px;font-size: 16px;" onclick="selectAllSKUForPPW('${productId!''}')"  type="button" class="btn btn-default">一键清仓</button>
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