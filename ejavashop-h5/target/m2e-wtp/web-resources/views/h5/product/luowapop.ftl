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
							<!--顶部显示部分渲染结束  end -->
								<ul class="tabClick" id="tab">
									<#list norm.attrList as normattr>
										<li id="licss_${(normattr.id)!0}" onclick="luowaColornameClick(this)" class="licss-list" style="width: 100px;background-color:#f5f5f5;border-right:1px solid #e5e5e5; <#if normattr_index == 0 >border-bottom:2px solid red; <#else></#if>"  >  <!-- 默认第一个为选中状态 -->
										<a id="${(normattr.id)!0}" style="<#if normattr_index == 0 > color:#ff3c23; <#else></#if>" data-iscustom="${(norm.isCustom)?string('true','false')}" data-pic-url="${(normattr.url)!''}">${(normattr.name)!""}</a>
										<i class="badge" id="badgespan_${(normattr.id)!0}" style="background-color:#ff3c23;display: none;color: white;position: absolute;"></i>
										</li>
									</#list>
																			
								</ul>
								<div>
									<#list norm.attrList as normattr>
										<div id="shuliangul_${(normattr.id)!'0' }_all" class="shuliangclass" style="position: fixed;top:150px;left: 35%; width: 250px; <#if normattr_index == 0 >display:block;<#else>display:none;</#if>">
											<div style="float:left; width:100px;" >
												<div class="jiagong-list">
													<div style="text-align: left;margin-bottom: 5%;">&#12288;加工方式</div>
													<button id="jiagong_${(normattr.id)!0}_two" class="two">
														裸袜
													</button>
													<button id="jiagong_${(normattr.id)!0}_three" style=" <#if product.dabiaowaFlag?? && product.dabiaowaFlag==1 > display: block;<#else> display: none; </#if> ">
														打标袜
													</button>
													<button id="jiagong_${(normattr.id)!0}_four">
														二次加工
													</button>
													<input type="hidden" id="goodsStock_${(normattr.id)!0}" name="goodsStock" value="${(normattr.productStock)!0}">
												</div>
											</div>
											<div class="flex pad-bt" style="float:left; width:120px">
												<div class="flex-2">
													<div class="goumai-sl1">
														购买数量(手)
													</div>
													<!-- 加工方式：裸袜  two-->
													<input type="hidden" name="luowapop_dabiaowaFlag" id="dabiaowaFlag_${(normattr.id)!'0' }_two" />
													<input type="hidden" name="productPackageId" id="productPackageId_${(normattr.id)!'0' }_two" />
													<input type="hidden" name="luowapop_productGoodsId" value="${(normattr.productGoodsId)!'0' }"/>
													<input type="hidden" name="isSelfLabel" id="isSelfLabeltxt_${(normattr.id)!'0' }_two" value="0"/>
													<div class="shuliang-more" id="shuliangdiv_${(normattr.id)!'0' }_two" style="position: relative;top:5px;left:15px;">
														<em id="productMinus_${(normattr.id)!'0' }_two" style="border: #d7d7d7 solid 1px;width: 2.6rem;height: 2.6rem;
															text-align: center;line-height: 2.6rem;color: #5d5d5d;font-size: 1.4rem; font-style:normal;
															font-weight: bold;display: block;margin-bottom: 1rem;float: left;border-radius: 0.3rem 0 0 0.3rem;">－</em>
														<!--
														<a class="quantity-decrease" id="productMinus_${(normattr.id)!'0' }_two"> 
															<i class="fa fa-minus-square">
															</i>
														</a> 
														-->
														 <input type="number"
															onkeyup="this.value=this.value.replace(/\D/g,'')"
															style="height: 2.6rem;width: 4rem;border: #d7d7d7 solid;float: left;
															border-width: 1px 0;outline: none;background: none;text-align: center;
															line-height: 2.6rem;font-size: 1.6rem;color: #5d5d5d;"
															name="luowapop_number"
															onafterpaste="this.value=this.value.replace(/\D/g,'')"
															class="quantity" size="4" value="0" id="number${(normattr.id)!'0' }_two"
															onblur="modify_two(this);">
														<em id="productPlus_${(normattr.id)!'0' }_two" style="border: #d7d7d7 solid 1px;width: 2.6rem;height: 2.6rem;
														text-align: center;line-height: 2.6rem;color: #5d5d5d;font-size: 1.4rem; font-style:normal;
														font-weight: bold;display: block;margin-bottom: 1rem;float: left;border-radius: 0 0.3rem 0.3rem 0;">＋</em>
														<!-- 
														<a class="quantity-increase" id="productPlus_${(normattr.id)!'0' }_two"> 
															<i class="fa fa-plus-square">
															</i>
														</a> 
														-->
													</div>
													<!-- 加工方式：打标价 three -->
													<input type="hidden" name="luowapop_dabiaowaFlag" id="dabiaowaFlag_${(normattr.id)!'0' }_three" />
													<input type="hidden" name="productPackageId" id="productPackageId_${(normattr.id)!'0' }_three" />
													<input type="hidden" name="luowapop_productGoodsId" value="${(normattr.productGoodsId)!'0' }"/>
													<input type="hidden" name="isSelfLabel" id="isSelfLabeltxt_${(normattr.id)!'0' }_three" value="0"/>
													<div class="shuliang-more" id="shuliangdiv_${(normattr.id)!'0' }_three" style="position: relative;top:5px;display: none;">
														<em id="productMinus_${(normattr.id)!'0' }_three" style="border: #d7d7d7 solid 1px;width: 2.6rem;height: 2.6rem;
															text-align: center;line-height: 2.6rem;color: #5d5d5d;font-size: 1.4rem; font-style:normal;
															font-weight: bold;display: block;margin-bottom: 1rem;float: left;border-radius: 0.3rem 0 0 0.3rem;">－</em>
														 <input type="text"
															onkeyup="this.value=this.value.replace(/\D/g,'')"
															style="height: 2.6rem;width: 4rem;border: #d7d7d7 solid;float: left;
															border-width: 1px 0;outline: none;background: none;text-align: center;
															line-height: 2.6rem;font-size: 1.6rem;color: #5d5d5d;"
															name="luowapop_number"
															onafterpaste="this.value=this.value.replace(/\D/g,'')"
															class="quantity" size="4" value="0" id="number${(normattr.id)!'0' }_three"
															onblur="modify_three(this);">
														<em id="productPlus_${(normattr.id)!'0' }_three" style="border: #d7d7d7 solid 1px;width: 2.6rem;height: 2.6rem;
														text-align: center;line-height: 2.6rem;color: #5d5d5d;font-size: 1.4rem; font-style:normal;
														font-weight: bold;display: block;margin-bottom: 1rem;float: left;border-radius: 0 0.3rem 0.3rem 0;">＋</em>
													</div>
													<!-- 加工方式：二次加工 four -->
													<input type="hidden" name="luowapop_dabiaowaFlag" id="dabiaowaFlag_${(normattr.id)!'0' }_four" />
													<input type="hidden" name="productPackageId" id="productPackageId_${(normattr.id)!'0' }_four" />
													<input type="hidden" name="luowapop_productGoodsId" value="${(normattr.productGoodsId)!'0' }"/>
													<input type="hidden" name="isSelfLabel" id="isSelfLabeltxt_${(normattr.id)!'0' }_four" value="0"/>
													<div class="shuliang-more" id="shuliangdiv_${(normattr.id)!'0' }_four" style="position: relative;top: 5px;display: none;">
														<em id="productMinus_${(normattr.id)!'0' }_four" style="border: #d7d7d7 solid 1px;width: 2.6rem;height: 2.6rem;
															text-align: center;line-height: 2.6rem;color: #5d5d5d;font-size: 1.4rem; font-style:normal;
															font-weight: bold;display: block;margin-bottom: 1rem;float: left;border-radius: 0.3rem 0 0 0.3rem;">－</em>
														 <input type="text"
															onkeyup="this.value=this.value.replace(/\D/g,'')"
															style="height: 2.6rem;width: 4rem;border: #d7d7d7 solid;float: left;
															border-width: 1px 0;outline: none;background: none;text-align: center;
															line-height: 2.6rem;font-size: 1.6rem;color: #5d5d5d;"
															name="luowapop_number"
															onafterpaste="this.value=this.value.replace(/\D/g,'')"
															class="quantity" size="4" value="0" id="number${(normattr.id)!'0' }_four"
															onblur="modify_four(this);">
														<em id="productPlus_${(normattr.id)!'0' }_four" style="border: #d7d7d7 solid 1px;width: 2.6rem;height: 2.6rem;
														text-align: center;line-height: 2.6rem;color: #5d5d5d;font-size: 1.4rem; font-style:normal;
														font-weight: bold;display: block;margin-bottom: 1rem;float: left;border-radius: 0 0.3rem 0.3rem 0;">＋</em>
													</div>
													<div style="overflow:auto;width:120px">
													(一手=${(pAttr.value)!'10'}${(product.unit)!''})
												   <input type="hidden" name="ppwProductStock" id="productStockSpan_${(normattr.id)!'0' }" value="${(normattr.productStock)!0}"/> 
												   </div>
													<#if ((goods.productStock)!0) &gt;= ((goods.productStockWarning)!0) > 
													<div style="color:green;display:inline;">
														<!-- 货源充足 -->
													库存<div style="display:inline;" id="productStockGreenSpan_${(normattr.id)!'0' }">${(normattr.productStock)!'0'}</div>${(product.unit)!''}
													</div>
													 <#else>
													 <div style="color:red;display:inline;">
													<!-- 货源紧张 -->
													库存<div style="display:inline;" id="productStockRedSpan_${(normattr.id)!'0' }">${(normattr.productStock)!'0'}</div>${(product.unit)!''}
													</div>
													</#if> 
												</div>
											</div>
										</div>
									</#list>
								</div>
													
								<!-- SKU颜色和加工方式显示区域 end -->								
						</div>
					</div>
				</div>
			</div>
			</#list> 
			</#if>
			
										
			<#if norms?? && norms?size &gt; 0>
			<#list norms as norm>
				<#list norm.attrList as normattr>
				<!-- 打标信息区域 -->			
				<div class="detaildabiao" id="detaildabiao_${(normattr.id)!0}">
					<div class="s-container" id="container">
					    <div class="pad10" style="margin: 10px;">
						  <table width="100%" class="goods-table" style="font-size: 10px;" cellspacing="0" cellpadding="0" >
						  	 <tr>
						  	 	<td width="64" style="padding: 0;">打标价</td>
						  	 	<#if product.price2Status?? && product.price2Status ==2 >
						  	 		<!-- 阶梯价 -->
							  	 	<td style="padding: 0;line-height:100%;">
							  	 			<span style="color: red;">¥<#if productPrice2.price1??>${(productPrice2.price1)?string('0.00')!'999999'}<#else>${(productPrice2.price1)!'999999'}</#if></span><i>&nbsp;&nbsp;(${(productPrice2.price1S)}-${(productPrice2.price1E)}${(product.unit)!''})</i>
							  	 			<span style="color: red;">¥<#if productPrice2.price2??>${(productPrice2.price2)?string('0.00')!'999999'}<#else>${(productPrice2.price2)!'999999'}</#if></span><i>&nbsp;&nbsp;(${(productPrice2.price2S)}-${(productPrice2.price2E)}${(product.unit)!''})</i>						
							  	 			<span style="color: red;">¥<#if productPrice2.price3??>${(productPrice2.price3)?string('0.00')!'999999'}<#else>${(productPrice2.price3)!'999999'}</#if></span><i>&nbsp;&nbsp;(${(productPrice2.price3S)}-${(productPrice2.price3E)}${(product.unit)!''})</i>
							  	 	</td>
						  	 	<#elseif product.price2Status?? && product.price2Status ==1>
						  	 		<!--  一口价-->
							  	 	<td style="padding: 0;">
							  	 		<ul>
							  	 			<li style="margin: 0px;"><span>¥<#if productPrice2.priceOnly??>${(productPrice2.priceOnly)?string('0.00')!'999999'}<#else>${(productPrice2.priceOnly)!'999999'}</#if></span><i></i></li>
										</ul>
							  	 	</td>
							  	 <#else>
						  	 	</#if>
						  	 </tr>
						  	 <#if productPackage2?? && product.productBrandId == 8 &&  product.id!=10>
							  	 <tr>
							  	 	<td width="64" style="padding: 0;">包装单位</td>
							  	 	<td >${(productPackage2.packageUnitStr)!''}</td>
							  	 </tr>
							  	<tr>
							  	 	<td width="64" style="padding: 0;">商标</td>
							  	 	<td s>${(shangbiaoInfo)!''}</td>
							  	 </tr>
							  	 <tr>
							  	 	<td width="64" style="padding: 0;">辅料</td>
							  	 	<td s>${(productPackage2.fuliaoStr)!''}</td>
							  	 </tr>
							  	 <tr>
							  	 	<td width="64" style="padding: 0;">打标说明</td>
							  	 	<td align="left" style="line-height:20px; padding:3px">${(productPackage2.describe)!''}</td>
							  	 </tr>
						  	</#if>
						  </table>
						</div>
				    </div>
				</div>
				<!--显示二次加工信息位置，作用，和二次加工按钮绑定，动作保持与二次加工按钮一致  -->
				<div class="processing-img" id="processingimg_${(normattr.id)!'0' }">
					<span style="position: relative;top: 35px;left: 45%;" class="topClose">
						<img width="8%" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/fwc/icon_close.png">
					</span>
					<ul class="processing-ul">
						<#list productPackage as pka>
							<li data-product-pageck-id="${pka.id}" style="height: 52px;width: 24%;margin-bottom: 3%;padding-top:0;padding-left: 0;padding-right: 0;">
								<a href="javascript:;" class="a-img" id="aimg_${(normattr.id)!'0' }"> 
									<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(pka.image)!''}"
										onerror="this.src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/nopic.jpg'"
										alt="" >
										 <#--
										<p data-describe="<p>辅料：${pka.packOther}</p><p>描述：${pka.describe}</p>"
											class="atip">${(pka.name)!''}
										</p> -->
									<img alt="" id="pkaimageid_${(normattr.id)!'0' }_${pka.id}" style="position: relative;top: -51%;left:55%;display: none;width: 43%;" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/fwc/icon_click.png">
								</a>
								<p style="font-size:6px;">
									加工费<i>${(pka.price)!0}</i>元
								</p>
							</li> 
						</#list>
						<li>
							<p class="processing-brand" id="processingbrand_${(normattr.id)!'0' }" style="display: block;line-height: 22px;margin-top: 0%;text-align: left;">
								&#12288;<input type="checkbox" id="isSelfLabel_${(normattr.id)!'0' }" class="process-item" /> 
								<label for="isSelfLabel_${(normattr.id)!'0' }" style="font-size: 10px;">是否自供商标</label>
							</p>
						</li>
					</ul>
				</div>
			</#list> 
			</#list> 
			</#if>
											
											
			<!--激活按钮时候的颜色  #27cdf2 -->
			<a class="block-liji popup-confirm" href="javascript:;" id="luowapop_Confirm">
				确定 
			</a>
			<!-- <a class="block-liji" href="javascript:;" style="display:block;font-size:24px;color:#fff;background-color: #27cdf2;line-height:40px;margin-top:2%;" id="skuAllSelectforLuowa">
				颜色全选
			</a> -->
			<!-- 隐藏域 -->
			<!-- 规格值ID  -->
			<input type="hidden" id="specId0" name="specId0"> <input type="hidden" id="specId1" name="specId1">
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

