<input type="hidden" name="flagForCartOrBuy" id="luowaopo_flagforcartorbuy" value="0"/>
	<div class="am-modal-actions" id="my-actions" style="background:#fff; bottom:-20px">
		<p></p>
		<div class="cd-buttons"></div>
		<div class="am-modal-actions-group" style="margin:0">
			<#if norms?? && norms?size &gt; 0>
			<#list norms as norm>
			<div class="flex pad-bt">
				<div class="flex-2 sel-btn" id="normsDiv">
					<div class="con-qh">
						<div class="conIn">
							<!-- 代码部分begin -->
							<!-- 图片  价格  右上角关闭按钮  商品名称  渲染界面 -->
							
							<div class="top ico" id="hovertreephoto">
								<div class="fl img left">
									<div id="hovertreephoto">
										<#list norm.attrList as normattr>
											<#if normattr_index == 0 >
												<img class="img-thumbnail" id="imgthumbnail_${(normattr.id)!0}" alt="img" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(normattr.url)!''}" onerror="javascript:this.src='http://i02.pictn.sogoucdn.com/04c164dbbbcda227';">
											<#else>
												<img class="img-thumbnail" id="imgthumbnail_${(normattr.id)!0}" style="display: none;" alt="img" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(normattr.url)!''}" onerror="javascript:this.src='http://i02.pictn.sogoucdn.com/04c164dbbbcda227';">
											</#if>
										</#list>
									</div>
						        </div>
							    <div class="fl overflow right"> 
							        <div class="overflow">
							            <h3 class="pri fl">
							            	<#if product.priceStatus == 3>
												¥${(product.mallPcPrice)?string('0.00')!'999999'}-¥${(product.scatteredPrice)?string('0.00')!'999999'}
											<#elseif product.priceStatus == 2 && (productPrice)??>>
												¥${(productPrice.price3)?string('0.00')!'999999'}-¥${(productPrice.price1)?string('0.00')!'999999'}
											<#elseif product.priceStatus == 1>
												¥${(goods.mallMobilePrice)?string('0.00')!'999999'}
											</#if>		
							            </h3>
							               <div class="fr close" data-am-modal-close onclick="closepopdiv()">
							                   <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/close.png" >
							               </div>
							         </div>
							     	<p style="padding-top:5px">${(product.name1)!''}</p>
							    </div>
						    </div>
						    
						    <!--顶部显示部分渲染结束  end -->
							<div class="clear"></div>
							<ul class="tp2 sort">
								<li>袜颜色</li>
					            <li>加工方式</li>
					            <li>数量</li>
							</ul>
							
							<div class="overflow cart">
								<div class="fl cart_l" style="overflow:scroll;height:200px;" >
									<ul class="tabClick" id="tab">
										<#list norm.attrList as normattr>
											<div class="cart1" >
												<#if normattr_index == 0 >
													<span class="cur" id="tabli_${(normattr.id)!0}" onclick="colornameClick(this)"><a id="${(normattr.id)!0}" data-iscustom="${(norm.isCustom)?string('true','false')}" data-pic-url="${(normattr.url)!''}">${(normattr.name)!""}</a><i class="num show_num" id="badgespan_${(normattr.id)!0}"  ></i></span>
												<#else>
													<span id="tabli_${(normattr.id)!0}" onclick="colornameClick(this)" ><a id="${(normattr.id)!0}" data-iscustom="${(norm.isCustom)?string('true','false')}" data-pic-url="${(normattr.url)!''}">${(normattr.name)!""}</a><i class="num show_num" id="badgespan_${(normattr.id)!0}"  ></i></span>
												</#if>
													
											</div>
										</#list>											
									</ul>
								</div>
								
								<div class="fl cart_r">
									<#list norm.attrList as normattr>
										<div id="contentul_${(normattr.id)!'0' }" class="shuliangclass" style="<#if normattr_index == 0 > display:block; <#else>display:none;</#if>">
											
											<div class="overflow ss">
												<a class="fl active specifications" >均码</a>
												<div class="nums fr">

								                	<input type="hidden" name="ppwpop_productGoodsId" value=" ${(normattr.productGoodsId)!'0' }"/>
													<input type="hidden" id="productStockSpan" value="${(normattr.productStock)!0}" /> 
													<input type="hidden" id="goodsStock_${(normattr.id)!'0' }" name="goodsStock" value="${(normattr.productStock)!0}" />
													
													<input type="hidden" name="ppw_unitPrice" id="ppw_unitPrice_${(normattr.id)!'0' }" value="0"/>
													<input type="hidden" name="ppw_number" id="ppw_number_${(normattr.id)!'0' }" value="0"/>
													<input type="hidden" name="ppw_totalPrice" id="ppw_totalPrice_${(normattr.id)!'0' }" value="0"/>
													
													<div id="shuliangdiv_${(normattr.id)!'0' }" >
														<span><a id="productMinus_${(normattr.id)!'0' }">-</a></span>
								                        <input type="text" value="0" class="em1 num1 getnum fl tota" name="ppwpop_number" id="number${(normattr.id)!'0' }" onblur="modify(this);">
								                        <span><a id="productPlus_${(normattr.id)!'0' }" >+</a></span>
													</div>

							                    </div>
											</div>
											
											<input type="hidden" name="ppwProductStock" id="productStockSpan_${(normattr.id)!'0' }" value="${(normattr.productStock)!0}"/> 
											
											<div class="wap kc">
                								<p>库存<em id="productStockSpan_${(normattr.id)!'0' }">${(normattr.productStock)!'0'}</em>${(product.unit)!''}</p>
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
			
			
			<div class="clear"></div>
		    <div class="t1 overflow public">
		    	<span class="fl">按手起批(一手=${(pAttr.value)!''}${(product.unit)!''})</span>
		        <div class="fr">
		        	<span style="margin-right:1rem">共<em class="em1" id='totalNumber'>0</em>${(product.unit)!''}</span>
		            <em class="em1" id="totalPrice">￥0</em>
		        </div>
		    </div>
		    
		    <!--激活按钮时候的颜色  #27cdf2 -->
		    <a href="javascript:;" class="sure" style="margin-bottom:20px" id="ppwpop_Confirm">确定</a>		

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


