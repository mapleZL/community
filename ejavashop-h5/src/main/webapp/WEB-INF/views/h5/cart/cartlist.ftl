	<div style="padding-bottom:130px;">
		<#if (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
		<#list cartInfoVO.cartListVOs as cartListVO>
		<#assign seller = cartListVO.seller />
	        <h2 class="cart-h2 pad10" style="display: none;">
	        	<!-- <input type="checkbox"> -->&nbsp;<span>${seller.sellerName!''}</span>
	        </h2>
	        <#if cartListVO.actFull?? >
	        <div class="full-box clear">
	            <div class="full-reduction">满减</div>
	            <div class="full-money">
	              <span>
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
                  		<br>
	              		&nbsp;(已减:${(cartListVO.orderDiscount)?string('0.00')!"0.00"}元)
	              		</#if>
	              </span>
	            </div>
	        </div>
	        </#if>
	        <#if (cartListVO.cartList??) && (cartListVO.cartList?size>0) >
            <#list cartListVO.cartList as cart>
            <#assign product = cart.product />
            <#assign productGoods = cart.productGoods />
	        <div class="oder-list">
				<dl class="img-ul cart-ul flex">
					<div class="checksty">
						<input type='checkbox' name="checkItem" id="${(cart.id)!''}" value="${(cart.id)!''}" onchange="checkedChange(this)" autocomplete="off" <#if cart?? && cart.checked?? && cart.checked == 1>checked="checked"</#if>>
					</div>
					<dt style="width:80px;height:80px;">
						<a <#if product?? && product.state == 6> href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${cart.productId!0}.html?goodId=${(cart.productGoodsId)!0}"<#else>href = "javascript:void(0);"</#if>>
<!-- 							<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.masterImg!''}"> -->
							<#if product?? && product.isNorm == 2 && productGoods?? && (productGoods.images!='')>
							<img optype="goods" data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${productGoods.images!''}" <#if product?? && product.state == 7>style="opacity:0.5;"</#if>>
							<#else>
							<img data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.masterImg!''}" <#if product?? && product.state == 7>style="opacity:0.5;"</#if>>
							</#if>
						</a>
					</dt>
				<dd class="flex-2 pos_relative">
					<div class="product_name">
						<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${cart.productId!0}.html?goodId=${(cart.productGoodsId)!0}" <#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>>
							${(product.name1)!""}
							<#if product.sellerId == 0>
							<span style="vertical-align:top;display:inline-block;width:16px;height:16px;BACKGROUND: url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/redpacket.png') no-repeat center 50%;"></span>
							<#else>
							<span></span>
							</#if>
							&nbsp;<span <#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>>${cart.specInfo!''}</span>
						</a>
						<#--
						<span <#if product?? && product.state == 6>style="color:red"<#else>style="color:#D6E3E9;"</#if>>${(cart.productPackageName)!}</span>
                  	 	<#if (cart.productPackageId)?? && (cart.productPackageId) != 0>
	                    	<#if (cart.isSelfLabel)?? && (cart.isSelfLabel) == 1>
	                    	<span <#if product?? && product.state == 6>style="color:red"<#else>style="color:#D6E3E9;"</#if>>自供标</span>
	                    	<#else>
	                    	<span <#if product?? && product.state == 6>style="color:red"<#else>style="color:#D6E3E9;"</#if>>平台标</span>
	                    	</#if>
                   		</#if>
						-->
					</div>
					<#if product?? && product.id == 10>
						<input type="hidden" id="productId_T" value="10"/>
					<#else>
						<input type="hidden" id="productId_P" value="0"/>
					</#if>
					<#if product.priceStatus==3>
						<div <#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>>￥<font>${(product.mallPcPrice)?string('0.00')!"0.00"}-￥${(product.scatteredPrice)?string('0.00')!"0.00"}</font>
							<#if cart.isDabiaowa?? && cart.isDabiaowa==1>
								<span>(打标袜)</span>
								<#elseif cart.packageFee?? && cart.packageFee !=0>
								<span>(二次加工)</span>
								<#else>
								<span>(裸袜)</span>
							</#if>
						</div>
					<#else>
						<div <#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>>￥<font>
								<#if cart.isDabiaowa?? && cart.isDabiaowa==1><!-- 如果是打标袜 -->
										<#if product?? && product.price2Status == 1><!-- 如果是一口价 -->
												${(cart.tempdabiaoPriceOnly)?string('0.00')!"0.00"}
											<#else><!-- 阶梯价 -->
												${(cart.tempdabiaoPriceOnly)?string('0.00')!"0.00"}
										</#if>
									<#else>
									${(productGoods.mallMobilePrice)?string('0.00')!"0.00"}
								</#if>
						</font>
							<#if cart.isDabiaowa?? && cart.isDabiaowa==1>
								<span>(打标袜)</span>
								<#elseif cart.packageFee?? && cart.packageFee !=0>
								<span>(二次加工)</span>
								<#elseif product?? && product.productBrandId==8>
								<span>(裸袜)</span>
								<#else>
								<span></span>
							</#if>
						</div>
					</#if>
					<div>
					
					<div style="position: relative;top:5px;">
						<em <#if product?? && product.state == 6> onclick="cartminus(this)" </#if> class="quantity-decrease">－</em>
						 	<input type="text" <#if product?? && product.state == 6> 
						 						onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" 
						 					   <#else>
						 					   	readonly="readonly" class="quantity" name="ppwpop_number" 
						 					   </#if>
								class="quantity" size="6" value="${cart.count!''}" id="number" onblur="modify(this);">
						<em class="quantity-increase" <#if product?? && product.state == 6> onclick="cartplus(this)" </#if>>＋</em>
						<span class="quantity-unit" <#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>>&nbsp;( ${(product.unit)!'双'} )</span>
					<#--
						<a class="quantity-decrease" <#if product?? && product.state == 6> onclick="cartminus(this)" </#if>>
					    	<i class="fa fa-minus-square"></i> 
						</a>
						<input style="color: black; border:1px #ddd solid" type="text" <#if product?? && product.state == 6> onkeyup="this.value=this.value.replace(/\D/g,'')" 
							onafterpaste="this.value=this.value.replace(/\D/g,'')" <#else>readonly="readonly" style="color:#D6E3E9;"</#if> class="quantity" 
							size="6" value="${cart.count!''}" id="number" onblur="modify(this);">
						<a class="quantity-increase" <#if product?? && product.state == 6> onclick="cartplus(this)" </#if>>
					    	<i class="fa fa-plus-square"></i>
						</a>
					-->
						<!-- (库存${productGoods.productStock!'0'}件) -->
						<input type="hidden" id="productStock" name="productStock" value="${productGoods.productStock!'0'}"/>
						<input type="hidden" id="cartId" name="cartId" value="${cart.id!'0'}"/>
						<input type="hidden" id="stockNums" name="stockNums" value="${cart.stockNums!'10'}">
						<input type="hidden"  name='maxStock' value="${(productGoods.maxStock)!99999}">
					</div>
					<div class="cart_delate"><i class="fa fa-trash" onclick="deleteCart(${cart.id!'0'})"></i></div>
				</dd>
				</dl>
				<div class="cartPrice" style="border: 0px solid blue;margin-left: 0;text-align: center;">
					 <span class='productStock'>已省:${(cart.currDiscounted)?string('0.00')!'0.00'}</span>
					 <span>加工费:+<#if cart.packageFee??>${(cart.packageFee)?string('0.00')!'0.00'}<#else>${(cart.packageFee)!'0.00'}</#if></span>
					 <span>辅料费:+<#if cart.labelFee??>${(cart.labelFee)?string('0.00')!'0.00'}<#else>${(cart.labelFee)!'0.00'}</#if></span>
					 <span>小计:<font>¥${(cart.currDiscountedAmount)?string('0.00')!"0.00"}</font></span>
				</div>
	        </div>
	        </#list>
	        </#if>
	    </#list>
	    <div class="cartPrice" style="text-align: center;">
			<span>加工费:&yen;${(cartInfoVO.servicePrice)?string('0.00')!'0.00'}</span>
			<span>辅料费:&yen;${(cartInfoVO.labelPrice)?string('0.00')!'0.00'}</span>
	    	<span>
	    	总计:<font>&yen;${(cartInfoVO.checkedDiscountedCartAmount)?string('0.00')!'0.00'}</font>
	    	</span>
		</div>
	    <#else>
	    <div style="height:70%;" class="flex flex-pack-center flex-align-center">
			<div>
				<p class="text-center"><i class="fa fa-shopping-cart"></i><br>进货单是空的，去挑一件中意的商品吧！</p>
		        <p class="mar_top text-center"><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" class="a_btn">去逛逛</a></p>
			</div>
		</div>
	    </#if>

	    <input type="hidden" id="cartAmount" name="cartAmount" value="${cartInfoVO.checkedDiscountedCartAmount!'0.00'}"/>
	    <input type="hidden" id="totalNumber" name="totalNumber" value="${cartInfoVO.totalNumber!0}"/>
	    <input type="hidden" id="totalCheckedNumber" name="totalCheckedNumber" value="${cartInfoVO.totalCheckedNumber!0}"/>
    </div>
	<!-- 合计层 -->
	<#if cartInfoVO?? &&(cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
	<div class="totallayer">
		<div class="flex flex-align-center" style="height:100%; position:absolute; bottom:0; left:0; width:100%;">
			<div class="pad10">
				<input type='checkbox' id="checkAllFoot" onchange="checkedChangeAll(this)" autocomplete="off" <#if cartInfoVO.totalNumber == cartInfoVO.totalCheckedNumber>checked="checked"</#if>>
				&nbsp;全选
			</div>
			<div class="padt_b10 flex-1">
			   <span class="font16">合计<font class="cartAmountFont">¥${(cartInfoVO.checkedDiscountedCartAmount)?string('0.00')!'0.00'}</font></span>
			 </div>
			 <div class="flex-1 go-pay font16">
			 	<a href='javascript:;' onclick="goorderinfo()" class="block">
			 		去结算
			 		<#--
			 		<font class="totalNumberFont">(${cartInfoVO.totalCheckedNumber!0})</font>
			 		-->
			 	</a>
			 </div>
		</div>
	</div>
	</#if>
