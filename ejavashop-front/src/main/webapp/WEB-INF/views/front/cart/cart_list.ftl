<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
<div class='yy-cart'>
	<div class='cart-main'>
		<div class='cart-thead' style="font-family:MicrosoftYaHei;
									 font-size:14px;
									 color:#666666;
									 line-height:34px;
									 text-align:left;">
			<div class='column t-checkbox'>
				<div class='cart-checkbox' style="margin-top: 4px;">
					<input type='checkbox' id="checkAllHead" class='yycheckbox' onchange="checkedChangeAll(this)" autocomplete="off" <#if cartInfoVO.totalNumber == cartInfoVO.totalCheckedNumber>checked="checked"</#if>>
				</div>
				全选
			</div>
			<!-- <div class='column t-checkbox'>&nbsp;</div> -->
			<div class='column t-goods'>商品名称</div>
			<!-- <div class='column t-props'></div> -->
			
			<div class='column t-twojg' style="margin-right: 3%;margin-left: 4%;">二次加工</div>
			<#--
			<div class='column t-zgb' style="margin-right: 3%;">自供标</div>
			-->
			<div class='column t-price' style="margin-right: 3%;padding-right: 0px;width:60px;">单价(元)</div>
			<div class='column t-jgfee' style="margin-right: 3%;">加工费(元)</div>
			<div class='column t-flfee' style="margin-right:1%;">辅料费</div>
			
			<div class='column t-quantity'>数量</div>
			<div class='column t-sum' style="width: 100px;margin-left: 2%;">小计(元)</div>
			<div class='column t-action'>交易状态</div>
			<div class='column t-action' style="margin-left: 2%;">操作</div>
		</div>
		<div id='cartList'>
			<#assign isCheckAll = 1 />
			<#list cartInfoVO.cartListVOs as cartListVO>
			    <#assign seller = cartListVO.seller />
			    <div class='cart-item-list'>
			        <div class='cart-tbody'>
			            <div class='shop' style="display: none;">
			                <a target="_blank" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/store/${(seller.id)!0}.html" class='shop-name self-shop-name'>${seller.sellerName!''}</a>
			            </div>
			            <div class='item-list' style="border: 0px;"> 
			                <div class='item-full'> 
			                    <div class='item-header' style="display: none;"> 
			                        <div class='f-txt'>
			                        	<!-- 满减活动信息 -->
			                        	<#if cartListVO.actFull?? >
			                        	<span class='full-icon full-gray-icon'>
											满减
											<b></b>
										</span>
			                        		<#if cartListVO.actFull.firstFull?? && cartListVO.actFull.firstFull &gt; 0>
			                        		&nbsp;满${(cartListVO.actFull.firstFull)?string('0.00')!"0.00"}-${(cartListVO.actFull.firstDiscount)?string('0.00')!"0.00"}
			                        		</#if>
			                        		<#if cartListVO.actFull.secondFull?? && cartListVO.actFull.secondFull &gt; 0>
			                        		&nbsp;满${(cartListVO.actFull.secondFull)?string('0.00')!"0.00"}-${(cartListVO.actFull.secondDiscount)?string('0.00')!"0.00"}
			                        		</#if>
			                        		<#if cartListVO.actFull.thirdFull?? && cartListVO.actFull.thirdFull &gt; 0>
			                        		&nbsp;满${(cartListVO.actFull.thirdFull)?string('0.00')!"0.00"}-${(cartListVO.actFull.thirdDiscount)?string('0.00')!"0.00"}
			                        		</#if>
			                        		
			                        		<#if cartListVO.orderDiscount?? && cartListVO.orderDiscount &gt; 0>
			                        		&nbsp;(已减:${(cartListVO.orderDiscount)?string('0.00')!"0.00"})
			                        		</#if>
			                        	<#else>
			                        		&nbsp;
			                        	</#if>
			                        </div>
			                        <div class='f-price'> 
			                            <strong>${(cartListVO.sellerCheckedDiscountedAmount)?string('0.00')!'0.00'}</strong> 
			                            <span>已减：<em>${(cartListVO.sellerCheckedDiscounted)?string('0.00')!'0.00'}</em></span>
			                        </div>
			                    </div>
			                    <#if (cartListVO.cartList??) && (cartListVO.cartList?size>0) >
			                    <#list cartListVO.cartList as cart>
			                    	<#if cart?? && cart.checked?? && cart.checked == 0>
			                    		<#assign isCheckAll = 0 />
			                    	</#if>
			                        <#assign product = cart.product />
			                        <#assign productGoods = cart.productGoods />
			                        <div class='item-last item-item item-selected' style="border: 1px solid red;
	                        															background:#e9faff;
																						border:1px solid #e8e8e8;
																						height:118px;
																						margin-bottom: 10px;">
			                            <div class='item-form'>
			                            	<div class='cell p-checkbox'>
			                            		<input type='checkbox' name="checkItem" id="${(cart.id)!''}" value="${(cart.id)!''}" onchange="checkedChange(this)" class='yycheckbox' autocomplete="off" <#if cart?? && cart.checked?? && cart.checked == 1>checked="checked"</#if>>
			                            	</div>
			                            	
			                                <div class='cell p-goods'>
			                                    <div class='goods-item' style="height: 20px;"> 
			                                        <div class='p-img'>
			                                            <a target='_blank' href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${cart.productId!0}.html">
			                                            <#if product?? && product.isNorm == 2 && productGoods?? && (productGoods.images!='')>
														<img optype="goods" width='80' height='80' data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${productGoods.images!''}" <#if product?? && product.state == 7>style="opacity:0.5;"</#if>>
														<#else>
														<img width='80' height='80' data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.masterImg!''}" <#if product?? && product.state == 7>style="opacity:0.5;"</#if>>
														</#if>
<!-- 			                                            <img width='80' height='80'  alt="${product.name1!''}" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.masterImg!''}"/> -->
			                                            </a> 
			                                        </div> 
			                                        <div class='item-msg'>
			                                            <div class='p-name' style="font-family:MicrosoftYaHei;
																					font-size:12px;
																					color:#666666;
																					line-height:12px;
																					font-weight:bold;
																					text-align:left;"> 
			                                                <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${cart.productId!0}.html"  target='_blank' 
			                                                		<#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>> 
			                                                		${product.name1!''}
			                                                	<#if cart.isDabiaowa?? && cart.isDabiaowa==1>
																	(打标袜)
																	<#elseif cart.packageFee?? && cart.packageFee !=0>
																	(二次加工)
																	<#elseif product?? && product.productBrandId==8>
																	(裸袜)
																	<#else>
																	<span></span>
																</#if>
			                                                </a> 
			                                                <#if product.sellerId == 0>
															<span style="vertical-align:top;display:inline-block;width:16px;height:16px;BACKGROUND: url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/redpacket.png') no-repeat center 50%;"></span>
															<#else>
															<span></span>
															</#if>
			                                            </div> 
			                                        </div>
			                                    </div> 
			                                    <!-- 此原来位置下方，现和商品合并为一个 -->
				                                <div class='cell p-props' style="padding-left:0px;
												                                font-family:MicrosoftYaHei;
																				font-size:12px;
																				color:#888888;
																				line-height:12px;
																				text-align:left;">
					                                <span <#if product?? && product.state == 6>style="display: inline-block;overflow: hidden;float: left;width: 140px;"<#else>style="display: inline-block;overflow: hidden;float: left;width: 140px;color:#D6E3E9;"</#if>>
					                              	  ${cart.specInfo!''}
					                                </span><br>
				                                <span style="color:red;display: none;" class="atip" 
				                                	data-original-title="<p style='text-align: left'>辅料：${(cart.packOther)!}</p><p style='text-align: left'>单价：&yen; ${(cart.packageUnitFee)!}</p><p style='text-align: left'>描述：${(cart.packDescribe)!}</p>" >
				                                	${(cart.productPackageName)!}
				                                </span>
				                                <#--
		                                           	<#if (cart.productPackageId)?? && (cart.productPackageId) != 0>
		                                            	<#if (cart.isSelfLabel)?? && (cart.isSelfLabel) == 1>
		                                            	<span style="color:red;float: left">自供标</span>
		                                            	<#else>
		                                            	<span style="color:red;float: left">平台标</span>
		                                            	</#if>
		                                           	</#if>
				                                -->
				                                </div> 
				                                <!-- end -->
			                                </div> 
			                                <!-- 二次加工 -->
			                                <div class="cell p-price"  <#if product?? && product.state == 6>style="width: 110px;"<#else>style="width: 110px;color:#D6E3E9;"</#if>> 
			                                	<#if productPackage?? && cart.productPackageId?? && cart.productPackageId!=0> 
													<#list productPackage as pp>
														<#if cart.productPackageId?? && cart.productPackageId==pp.id>
													    	<span>${(pp.name)}</span>
													    </#if>
												    </#list>
											    <#else>
												    	<span>—</span>
											    </#if>
			                                </div>
			                                <!-- 自供标 -->
		                                 <#-- 
			                                <div class='cell p-zgb'> 自供标
			                                	<#if op.productLabelId?? && op.productLabelId==0>
													<span>否</span>
											    <#else>
											    	<span>是</span>
											    </#if>
			                                </div>
		                                -->
			                                <div class='cell p-price' style="width: 100px;"> 
				                                <#--
				                                	<#if product??&&product.priceStatus==1>
				                                    	<strong class='unit-price'>
				                                   	 		${(productGoods.mallPcPrice)?string('0.00')!0}
				                                    	</strong>
				                                    </#if>
			                                    -->
			                                    	<strong class='unit-price' <#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>>
			                                   	 		<#if cart.isDabiaowa?? && cart.isDabiaowa==1><!-- 如果是打标袜 -->
																<#if product?? && product.price2Status == 1><!-- 如果是一口价 -->
																		${(cart.tempdabiaoPriceOnly)?string('0.00')!"0.00"}
																	<#else><!-- 阶梯价 -->
																		${(cart.tempdabiaoPriceOnly)?string('0.00')!"0.00"}
																</#if>
															<#else>
															${(productGoods.mallPcPrice)?string('0.00')!"0.00"}
														</#if>
			                                    	</strong> 
			                                    	<#if product?? && product.id==10>
			                                    		<input type="hidden" id="productId_T" value="${(product.id)}"/>
			                                    	<#else>
			                                    		<input type="hidden" id="productId_P" value="0"/>
			                                    	</#if>
			                                    <#if product??&&product.priceStatus==3>
			                                    	<strong class='unit-price' <#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>>
			                                   	 		${(product.scatteredPrice)?string('0.00')!0}
			                                    	</strong> 
			                                    </#if>
			                                </div> 
			                                <!--加工费 -->
			                                <div class='cell p-jgfee' <#if product?? && product.state == 6>
			                                style="margin-right:6%;margin-left:3%;"
			                                <#else>
			                                style="margin-right:6%;margin-left:3%;color:#D6E3E9;"
			                                </#if>>
			                                	${(cart.packageUnitFee)!'—'}
			                                </div>
			                                <!-- 辅料费 -->
			                                <div class='cell p-flfee' <#if product?? && product.state == 6>
			                                style="margin-right: 0%;"
			                                <#else>
			                                style="margin-right: 0%;color:#D6E3E9;"
			                                </#if>>
			                                	${(cart.labelFee)!'—'}
			                                </div>
			                                
			                                <div class='cell p-quantity' style="margin-left: 2.5%;"> 
			                                    <div class='quantity-form'>
				                                      <input type=button  class='btn-reduce decrement'  value='-' <#if product?? && product.state == 6>onclick='decrement(this)'<#else>style="color:#D6E3E9;"</#if>>
				                                        <input type='text'  class='itxt buy-num' data-now="${cart.count!''}" value="${cart.count!''}" 
				                                        	<#if product?? && product.state == 6>onchange="numchange(this)" style="font-size: 13px;"<#else>style="font-size: 13px;color:#D6E3E9;"</#if>>
				                                       	<input  type="hidden"  name='maxStock' value="${(productGoods.maxStock)!99999}">
				                                        <a href='javascript:void(0);' class='increment'  <#if product?? && product.state == 6>onclick='increment(this)'<#else>style="color:#D6E3E9;"</#if>>+</a>
			                                         <!-- 进货单id -->
			                                        <input class='cartId' type='hidden' value="${cart.id!0}" name='id' > 
			                                        <input type='hidden' name="stockNums" id="stockNums" value="${cart.stockNums}">
			                                        <input type='hidden' name='productGoodsId' id='productGoodsId' value='${productGoods.id}'>
			                                        <input type='hidden' name='productId' id='productId' value='${product.id}'>
			                                    </div>
		                                        <div style="text-align: center;">${(product.unit)!'双'}</div>
			                                    <div class='ac ftx-03 quantity-txt' style="display: none;">
			                                        <#if productGoods.productStock?? && productGoods.productStock &gt; 0>
		                                        		    库存量<span class='productStock'>${productGoods.productStock!0}</span>
			                                        <#else>
			                                            <span style='color:red'>无货</span>
			                                        </#if>
			                                    </div>
			                                </div> 
			                                <div class='cell p-sum' style="width: 119px;">
			                                    <strong class='subtotal' <#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>>
			                                        ${(cart.currDiscountedAmount)?string('0.00')!0}
			                                    </strong>
			                                    <br>
			                                    <span class='productStock' <#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>>
			                                 		  已省:${(cart.currDiscounted)?string('0.00')!0}
			                                    </span>
			                                </div>
			                                <!-- 新增【等待付款】状态列 -->
			                                <div class='cell p-ops' style="margin-left: 0%;">
			                                	<span <#if product?? && product.state == 6>style="font-family:MicrosoftYaHei;
															font-size:12px;
															color:#666666;
															line-height:14px;
															text-align:left;"<#else>style="font-family:MicrosoftYaHei;
															font-size:12px;
															color:#666666;
															line-height:14px;
															text-align:left;color:#D6E3E9;"</#if>>等待付款</span>
			                                </div>
			                                <!-- 删除 -->
			                                <div class='cell p-ops' style="margin-left:2%;">
			                                    <a href='javascript:void(0);' class='cart-remove' onclick="deleteSingle(this,'${(cart.id)!0}')">删除</a>
			                                    <!-- <a href='javascript:void(0);'>收藏</a> -->
			                                </div>
			                                <!-- end -->
			                            </div> 
			                        </div>
			                    </#list>
			                    </#if>
			                </div>
			            </div>
			        </div>
			    </div>
			</#list>
			
		</div>
	</div>
</div>
<div id='cart-floatbar'>
	<div class='clearing'>
		<div class='cart-toolbar' style="width: 1209px;height: 80px;">
			<div class='toolbar-wrap' style="width: 1198px;height: 100px;background-color: #fff;border: 0;">
				<div class='options-box'>
					<div class='select-all' style="font-family:MicrosoftYaHei;
													font-size:14px;
													color:#666666;
													line-height:14px;
													text-align:left;">
						
						<div class='cart-checkbox'>
							<input type='checkbox' id="checkAllFoot" class='yycheckbox' onchange="checkedChangeAll(this)" autocomplete="off" <#if cartInfoVO.totalNumber == cartInfoVO.totalCheckedNumber>checked="checked"</#if>>
						</div>
							全选 
					</div>
					<div class='operation'>
						<!--
						<a href='' class='remove-batch'>删除选中的商品</a>
						<a href='' class='follow-batch'>收藏</a>
						-->
					</div>
					<div class='toolbar-right'>
						<div class='normal'>
							<div class='comm-right'>
								<div class='btn-area' style="padding-top: 6px;">
									<a href='javascript:void(0);' onclick="goorderinfo()" class='submit-btn' >
										去结算
										<b></b>
									</a>
								</div>
								<div class='price-sum'>
									<span class='txt'>总价（不含运费）：</span>
									<span class='price sumPrice'>
										<em id="sumPrice">￥${(cartInfoVO.checkedDiscountedCartAmount)?string("0.00")!'0.00'}</em>
									</span>
									<br>
									<span class="txt">已节省：</span>
									<span class="price totalRePrice">-￥${(cartInfoVO.checkedDiscountedAmount)?string("0.00")!'0.00'}</span>
									<br>
									<span class="txt">加工费：</span>
									<span class="price totalRePrice" style="color:red">+&yen;${(cartInfoVO.servicePrice)?string("0.00")!'0.00'}</span>
									<br>
									<span class="txt">辅料费：</span>
									<span class="price totalRePrice" style="color:red">+&yen;${(cartInfoVO.labelPrice)?string("0.00")!'0.00'}</span>
								</div>
								<div class='amount-sum'>
									已选择
									<em id="selectedCount">${cartInfoVO.totalCheckedNumber!0}</em>
									件商品
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<#else>
	<div class="cart-empty">
		<div class="message">
			<ul>
				<li class="txt">进货单空空的哦~，去看看心仪的商品吧~</li>
				<li>
					<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" class="ftx-05"> 去进货&gt;</a>
				</li>
			</ul>
		</div>
	</div>
</#if>
<input type="hidden" id="totalNumber" name="totalNumber" value="${(cartInfoVO.totalNumber)!0}"/>
