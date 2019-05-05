<div class="clear"></div>
<div data-am-widget="intro" class="am-intro am-cf am-intro-default overflow" >
	<#if (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
	<#list cartInfoVO.cartListVOs as cartListVO>
	<#if (cartListVO.cartList??) && (cartListVO.cartList?size>0) >
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
	<#list cartListVO.cartList as cart>
	<#assign seller = cartListVO.seller />
	<#assign product = cart.product />
    <#assign productGoods = cart.productGoods />
    <div class="oder-list">
    <dl class="cart-ul">
     <div class="am-g am-intro-bd ico mt10">
    	<!--左边-->
        <div class="ect-radio1 fl ect" style="width:10%">
            <input type='checkbox' name="checkItem" id="${(cart.id)!''}" value="${(cart.id)!''}" onchange="checkedChange(${(cart.id)!''})" autocomplete="off" <#if cart?? && cart.checked?? && cart.checked == 1>checked="checked"</#if>>
            <label for="${(cart.id)!''}"><i></i></label>
         </div>
        <!--左边end-->
        <!--右边-->
        <div class="am-g am-intro-bd fr" style="width:93%">
            <div class="am-intro-left am-u-sm-4 cart_img" style="padding-left:0">
            	<a <#if product?? && product.state == 6> href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${cart.productId!0}.html?goodId=${(cart.productGoodsId)!0}"<#else>href = "javascript:void(0);"</#if>>
	                <#if product?? && product.isNorm == 2 && productGoods?? && (productGoods.images!='')>
						<img optype="goods" lazyLoadSrc="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${productGoods.images!''}" <#if product?? && product.state == 7>style="opacity:0.5;"</#if>>
					<#else>
						<img lazyLoadSrc="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.masterImg!''}" <#if product?? && product.state == 7>style="opacity:0.5;"</#if>>
					</#if>
				</a>
            </div>
            <div class="am-intro-right am-u-sm-8 product_name" style="padding-right:1rem">
               	<h3 class="title1">${(product.name1)!""}</h3>
                <p class="f12 em0">${cart.specInfo!''}</p>
                <#if product.priceStatus==3>
							<em class="price1">
							 <#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>￥${(product.mallPcPrice)?string('0.00')!"0.00"}-￥${(product.scatteredPrice)?string('0.00')!"0.00"}
								<i class="em0 f12">
									<#if cart.isDabiaowa?? && cart.isDabiaowa==1>
										<span>(打标袜)</span>
										<#elseif cart.packageFee?? && cart.packageFee !=0>
										<span>(二次加工)</span>
										<#else>
										<span>(裸袜)</span>
									</#if>
								</i></em>
				<#else>
							<em class="price1">
							<#if product?? && product.state == 7>style="color:#D6E3E9;"</#if>￥
									<#if cart.isDabiaowa?? && cart.isDabiaowa==1><!-- 如果是打标袜 -->
											<#if product?? && product.price2Status == 1><!-- 如果是一口价 -->
													${(cart.tempdabiaoPriceOnly)?string('0.00')!"0.00"}
												<#else><!-- 阶梯价 -->
													${(cart.tempdabiaoPriceOnly)?string('0.00')!"0.00"}
											</#if>
										<#else>
										${(productGoods.mallMobilePrice)?string('0.00')!"0.00"}
									</#if>
								<i class="em0 f12">
								<#if cart.isDabiaowa?? && cart.isDabiaowa==1>
									<span>(打标袜)</span>
									<#elseif cart.packageFee?? && cart.packageFee !=0>
									<span>(二次加工)</span>
									<#elseif product?? && product.productBrandId==8>
									<span>(裸袜)</span>
									<#else>
									<span></span>
								</#if>
								</i></em>
				</#if>
				<div>
					
				</div>
                <div class="spin overflow">
           		<!--加减部分-->
                <div class="input-group fl overflow"> 
                         <span class="input-group-addon fl" <#if product?? && product.state == 6> onclick="cartminus(this)" </#if>>-</span>
                         <input type="text" class="form-num form-contro fl quantity" id="number"
                         	<#if product?? && product.state == 6> 
						 			onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" 
						 	<#else>
						 			readonly="readonly" name="ppwpop_number" 
						 	</#if>
                         size="6" value="${cart.count!''}" onblur="modify(this);">
                         <span class="input-group-addon fl" onclick="cartplus(this)">+</span> 
                         
                         <input type="hidden" id="productStock" name="productStock" value="${productGoods.productStock!'0'}"/>
						 <input type="hidden" id="cartId" name="cartId" value="${cart.id!'0'}"/>
						 <input type="hidden" id="stockNums" name="stockNums" value="${cart.stockNums!'10'}">
						 <input type="hidden" id="maxStock" name='maxStock' value="${(productGoods.maxStock)!99999}">
						 <input type='hidden' name='productGoodsId' id='productGoodsId' value='${productGoods.id}'>
			             <input type='hidden' name='productId' id='productId' value='${product.id}'>
                </div>
                <!--加减部分end-->
                 <a class="delete fr cart_delete" data-cartid="${cart.id!'0'}" onclick="deleteThisDeatil(${cart.id!'0'})"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/delete.png"></a>
            </div>
            </div>
         </div>
     <!--右边end-->
    </div>
	<p class="tp2 tp4 em4 ico fees">
		<em style="color:#666;">已省：${(cart.currDiscounted)?string('0.00')!'0.00'}</em>
		<em style="color:#666;">加工费：+<#if cart.packageFee??>${(cart.packageFee)?string('0.00')!'0.00'}<#else>${(cart.packageFee)!'0.00'}</#if></em>
		<em style="color:#666;">辅料费：+<#if cart.labelFee??>${(cart.labelFee)?string('0.00')!'0.00'}<#else>${(cart.labelFee)!'0.00'}</#if></em>
		<em>小计：¥${(cart.currDiscountedAmount)?string('0.00')!"0.00"}</em>
	</p>
	</div>
	</dl>
	<div class="overflow"></div>
	<div class="bottom overflow" style="z-index:1">
	    <div class="ect-radio1 fl" style="margin-top:0; width:40%">
	        <input name="checkAllFoot" type="checkbox" id="checkAllFoot" onchange="checkedChangeAll(this)" <#if cartInfoVO.totalNumber == cartInfoVO.totalCheckedNumber>checked="checked"</#if> style="margin-top:10px">
	        <label for="checkAllFoot"><i style="margin-right:10px; margin-top:19px; margin-left:10px"></i>全选</label>
	     </div>
	     <em class="fl">合计：¥ ${(cartInfoVO.checkedDiscountedCartAmount)?string('0.00')!'0.00'}</em>
	     <a href="javascript:void(0)" class="fr join_a cart_submit">去结算</a>
	 </div>
	</#list>
	</#if>
	</#list>
	<#else>
		<div class="da_box">
	    	<div class="da_img">
	        	<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/cart_icon.png">
	        </div>
	        <p>进货单是空的，去挑一件中意的商品吧！</p>
	        <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html"  class="sub_btn">去逛逛</a>
        </div>	
	</#if>
</div>
