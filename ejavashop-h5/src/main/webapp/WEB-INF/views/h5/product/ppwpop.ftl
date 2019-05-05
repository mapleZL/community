<div class="cd-popup2">
<input type="hidden" name="flagForCartOrBuy" id="luowaopo_flagforcartorbuy" value="0"/>
	<div class="cd-popup-container2" style="margin-top:0%;">
		<p></p>
		<div class="cd-buttons"></div>
		<a href="#0" class="cd-popup-close"></a>
		<div class="bor-btom">
			<#if norms?? && norms?size &gt; 0>
			<div class="flex pad-bt">
				<div class="pad-r"></div>
				<div class="flex-2"></div>
				<div class="flex-2"></div>
				<div class="flex-2"></div>
			</div>
			<#list norms as norm>
			<div class="flex pad-bt">
				<div class="pad-r"></div>
				<div class="flex-2 sel-btn" id="normsDiv">
					<div class="con-qh">
						<div class="conIn">
							<!-- 代码部分begin -->
							<!-- 图片  价格  右上角关闭按钮  商品名称  渲染界面 -->
							<div style="height: 100px;width:100%;background-color: white;margin-bottom: 5px;padding:5px 0 0 5px;">
								
								<div style="width: 30%;height: 100%;float: left;" id="hovertreephoto">
									<#list norm.attrList as normattr>
									<#if normattr_index == 0 >
									<img class="img-thumbnail" id="imgthumbnail_${(normattr.id)!0}" alt="img" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(normattr.url)!''}" onerror="javascript:this.src='http://i02.pictn.sogoucdn.com/04c164dbbbcda227';">
									<#else>
									<img class="img-thumbnail" id="imgthumbnail_${(normattr.id)!0}" style="display: none;" alt="img" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(normattr.url)!''}" onerror="javascript:this.src='http://i02.pictn.sogoucdn.com/04c164dbbbcda227';">
									</#if>
									</#list>
								</div>
								<div style="width: 65%;height: 100%;float: left;margin-left: 10px;text-align: left;">
									<div style="position: absolute;top: 5px;right: -20px;" onclick="closepopdiv()">
										<img alt="" width="50%" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/fwc/icon_close.png">
									</div>
									<#if product.priceStatus==3>
										<div>
										<span style="font-size: 24px;color: #ff3c23;">¥${(product.mallPcPrice)?string('0.00')!'999999'}</span>
										<span style="font-size: 12px;">
										散价(10*n)${(product.unit)!''}
										</span>
										</div>
										<div>
										<span style="font-size: 24px;color: #ff3c23;">¥${(product.mallPcPrice)?string('0.00')!'999999'}</span>
										<span style="font-size: 12px;">
										单色整箱价(${(product.fullContainerQuantity)}*n)${(product.unit)!''}
										</span>
										</div>
									<#elseif product.priceStatus==2>
										<div>
										<span style="font-size: 24px;color: #ff3c23;">¥${(productPrice.price1)?string('0.00')!'999999'}</span>
										<span style="font-size: 12px;">${(productPrice.price1S)}-${(productPrice.price1E)}${(product.unit)!''}</span>
										</div>
										<div>
										<span style="font-size: 24px;color: #ff3c23;">¥${(productPrice.price2)?string('0.00')!'999999'}</span>
										<span style="font-size: 12px;">${(productPrice.price2S)}-${(productPrice.price2E)}${(product.unit)!''}</span>
										</div>
										<div>
										<span style="font-size: 24px;color: #ff3c23;">¥${(productPrice.price3)?string('0.00')!'999999'}</span>
										<span style="font-size: 12px;">≥${(productPrice.price3S)}${(product.unit)!''}</span>
										</div>
									<#elseif product.priceStatus==1>
										<span style="font-size: 36px;color: #ff3c23;">¥</span>
										<span style="font-size: 36px;color: #ff3c23;margin-left: 14px;">
											<#list norm.attrList as normattr>
											<span id="xxxspan_${(normattr.id)!0}" style="<#if normattr_index == 0 > display:inline-block; <#else>display:none;</#if>">
												${(normattr.pgMallPcPrice)?string('0.00')!'999999'}
											</span>
											</#list>
										</span>
									</#if>
									<div style="margin:10px 0;font-size: 12px;" class="productnamePS1">${(product.name1)!''}</div>
								</div>
							</div>
							<!-- end -->
								<#--
								<div class="focus-img" id="focus-img" style="display: none;">
									<div class="focus-img-con" id="focus-img-con" style="border: 1px solid red;">
										<ul class="tabClick" id="tabmore">
											<#list norm.attrList as normattr>
												<li class="licss-list" id="tabmoreli_${(normattr.id)!0}" onclick="colornameClick(this)" style="width: 100px; <#if normattr_index == 0 >border-bottom:2px solid red;<#else></#if>"  >
												<a  id="${(normattr.id)!0}" style=" <#if normattr_index == 0 > color:#ff3c23; <#else></#if>" data-iscustom="${(norm.isCustom)?string('true','false')}" data-pic-url="${(normattr.url)!''}">${(normattr.name)!""}</a>
													<span class="badge" id="tabmorebadgespan_${(normattr.id)!0}" style="background-color:#ff3c23;display: none;position: absolute;"></span>
												</li> 
											</#list>
										</ul>
									</div>
								</div>
								-->
							<!-- =============================== -->
								<ul class="tabClick" id="tab">
									<#list norm.attrList as normattr>
										<li class="licss-list" id="tabli_${(normattr.id)!0}" onclick="colornameClick(this)" style="background-color: #f5f5f5;border-right: 1px solid #e5e5e5;width: 100px; <#if normattr_index == 0 >border-bottom:2px solid red;<#else></#if>"  >  <!-- 默认第一个为选中状态 -->
										<a  id="${(normattr.id)!0}" style="<#if normattr_index == 0 > color:#ff3c23; <#else></#if>" data-iscustom="${(norm.isCustom)?string('true','false')}" data-pic-url="${(normattr.url)!''}">${(normattr.name)!""}</a>
											<span class="badge" id="badgespan_${(normattr.id)!0}" style="background-color:#ff3c23;display: none;color: white;position: absolute;"></span>
										</li> 
									</#list>
								</ul>
								<div id="content" style="position: fixed;top:150px;left: 35%; width: 250px;top:160px;">
									<#list norm.attrList as normattr>
									<div id="contentul_${(normattr.id)!'0' }" class="shuliangclass" style="<#if normattr_index == 0 > display:block; <#else>display:none;</#if>">
										<div class="goumai-sl1" style="float:left; width:100px;">
											<div>尺码&#12288;&#12288;</div>
											<div>均码&#12288;&#12288;</div>
											<input type="hidden" name="ppwpop_productGoodsId" value=" ${(normattr.productGoodsId)!'0' }"/>
											<input type="hidden" id="productStockSpan" value="${(normattr.productStock)!0}" /> 
											<input type="hidden" id="goodsStock_${(normattr.id)!'0' }" name="goodsStock" value="${(normattr.productStock)!0}" />
										</div>
										<div class="shuliang-more" style="float:left; width:120px">
											<div>购买数量(手)</div>
											<div style="position: relative;top:5px;left:15px;">
												<em id="productMinus_${(normattr.id)!'0' }" style="border: #d7d7d7 solid 1px;width: 2.6rem;height: 2.6rem;
												text-align: center;line-height: 2.6rem;color: #5d5d5d;font-size: 1.4rem; font-style:normal;
												font-weight: bold;display: block;margin-bottom: 1rem;float: left;border-radius: 0.3rem 0 0 0.3rem;">－</em>
												 <input type="text"
													onkeyup="this.value=this.value.replace(/\D/g,'')"
													style="height: 2.6rem;width: 4rem;border: #d7d7d7 solid;float: left;
													border-width: 1px 0;outline: none;background: none;text-align: center;
													line-height: 2.6rem;font-size: 1.6rem;color: #5d5d5d;" 
													name="ppwpop_number" onafterpaste="this.value=this.value.replace(/\D/g,'')"
													class="quantity" size="4" value="0" id="number${(normattr.id)!'0' }"
													onblur="modify(this);">
													<em id="productPlus_${(normattr.id)!'0' }" style="border: #d7d7d7 solid 1px;width: 2.6rem;height: 2.6rem;
												text-align: center;line-height: 2.6rem;color: #5d5d5d;font-size: 1.4rem; font-style:normal;
												font-weight: bold;display: block;margin-bottom: 1rem;float: left;border-radius: 0 0.3rem 0.3rem 0;">＋</em>
											</div>
										
											<div style="overflow:auto;width:120px">
												(一手=${(pAttr.value)!'10'}${(product.unit)!''})
											</div>
											<input type="hidden" name="ppwProductStock" id="productStockSpan_${(normattr.id)!'0' }" value="${(normattr.productStock)!0}"/> 
											<#if ((goods.productStock)!0) &gt;= ((goods.productStockWarning)!0) > 
											<div>
												<!-- 货源充足 -->
												库存<div style="display:inline;" id="productStockGreenSpan_${(normattr.id)!'0' }">${(normattr.productStock)!'0'}</div>${(product.unit)!''}
											</div>
											 <#else>
											 <div>
												<!-- 货源紧张 -->
												库存<div style="display:inline;" id="productStockRedSpan_${(normattr.id)!'0' }">${(normattr.productStock)!'0'}</div>${(product.unit)!''}
											</div>
											</#if> 
										</div>
										
									</div>
									</#list>
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
			</#list> 
			</#if>
			<!--激活按钮时候的颜色  #27cdf2 -->
			<a class="block-liji popup-confirm" href="javascript:;" id="ppwpop_Confirm">
				确定
			 </a>
			 <!-- <a class="block-liji" href="javascript:;" style="display:block;font-size:24px;color:#fff;background-color: #27cdf2;line-height:40px;margin-top:2%;" id="skuAllSelectforPPW">
				颜色全选
			</a> -->
			<!-- 隐藏域 -->
			<!-- 规格值ID  -->
			<input type="hidden" id="specId0" name="specId0">
			<input type="hidden" id="specId1" name="specId1">
			<!-- 产品ID -->
			<input type="hidden" id="productId" name="productId" value="${productId!''}">
			<input type="hidden" id="productGoodsId" name="productGoodsId" value="${(goods.id)!''}">
			<input type="hidden" id="goodsNormAttrId" name="goodsNormAttrId" value="${(goods.normAttrId)!''}"> 
			<input type="hidden" id="goodsStock" name="goodsStock" value="${(goods.productStock)!0}">
			<input type="hidden" id='maxStock' value="${(goods.maxStock)!99999}">
			<input type="hidden" id="orders_count" value="${status}"> 
			<input type="hidden" id="stockNums" value="${pAttr.value}"> 
		</div>
	</div>
</div>

