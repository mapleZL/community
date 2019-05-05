
<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
<div class='incart-goods-box ps-container'>
	<div class='incart-goods'>
		<div class='sub-title'>
			<h4>最新加入的商品</h4>
		</div>
		<#list cartInfoVO.cartListVOs as cartListVO>
			<#if (cartListVO.cartList??) && (cartListVO.cartList?size>0) >
               <#list cartListVO.cartList as cart>
                   <#assign product = cart.product />
                   <#assign productGoods = cart.productGoods />
				<dl>
					<dt class='shop-googs-name'>
						<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${cart.productId!0}.html'>${(product.name1)!'' }&nbsp;${(cart.specInfo)!'' }</a>
					</dt>
					
					<dd class='mcart-mj'>
						<a href='' title="${(product.name1)!'' }">
							<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(product.masterImg)!''}">
						</a>
					</dd>
					<dd class='mcart-price'>
					<#if product.priceStatus == 3>
						<em>${productGoods.normName!""}</em>
					<#else>
						<em>
							<#if cart.isDabiaowa?? && cart.isDabiaowa==1><!-- 如果是打标袜 -->
									<#if product?? && product.price2Status == 1><!-- 如果是一口价 -->
											${(cart.tempdabiaoPriceOnly)?string('0.00')!"0.00"}
										<#else><!-- 阶梯价 -->
											${(cart.tempdabiaoPriceOnly)?string('0.00')!"0.00"}
									</#if>
								<#else>
								${(productGoods.mallPcPrice)?string('0.00')!"0.00"}
							</#if>×${(cart.count)!0}
						</em>
					</#if>
					</dd>
					<!-- <dd class='handle'>
						<em><a href='javascript:void(0);'>删除</a></em>
					</dd> -->
				</dl>
			</#list>
			</#if>
		</#list>
	</div>
</div>
<div class='checkout'>
	<span class='checkout-price'> 
		共<i>${(cartInfoVO.totalNumber)!0}</i>种商品&nbsp;&nbsp;
		总计金额： <em>¥${(cartInfoVO.checkedDiscountedCartAmount)?string("0.00")!'0.00'}</em>
	</span>
	<span class='checkout-price'>
		已节省:<i>-¥${(cartInfoVO.checkedDiscountedAmount)?string("0.00")!'0.00'}</i>
		辅料费:<i><#if cartInfoVO.labelPrice??>${(cartInfoVO.labelPrice)?string('0.00')!'0.00'}<#else>${(cartInfoVO.labelPrice)!'0.00'}</#if></i>
		加工费:<i>+¥${(cartInfoVO.servicePrice)?string("0.00")!'0.00'}</i>
	</span>
	<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cart/detail.html' class='btn btn-danger' style='color: #fff; margin-top: 5px;display: block;margin-left:170px;width: 50%;'>
		去结算
	</a>
</div>
<input type="hidden" id="totalNumber" name="totalNumber" value="${cartInfoVO.totalNumber!0}"/>
<#else>
	<!-- 如果没有商品的话显示这个 -->
	<div class='no-order'>
		<div class="emptycart">
	      <div class="emptycart_line"></div>
	      <div class="emptycart_txt"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/settleup-nogoods.png" alt="">进货单中还没有商品，赶紧选购吧</div>
	   </div>
	</div>
	<input type="hidden" id="totalNumber" name="totalNumber" value="0"/>
</#if>

