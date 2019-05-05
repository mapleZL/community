<input type="hidden" name="flagForCartOrBuy" id="luowaopo_flagforcartorbuy" value="0"/>
	<div class="am-modal-actions" id="my-actions" style="background:#fff; bottom:-20px">
		<p></p>
		<div class="cd-buttons"></div>
		<div class="am-modal-actions-group" style="margin:0">
			<#if norms?? && norms?size &gt; 0>
			<#list norms as norm>
			<div class="flex pad-bt">
				<div class="flex-2 sel-btn" id="normsDiv">
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
									<#elseif product.priceStatus == 2 && (productPrice)??>
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
										<span <#if normattr_index == 0 >class="cur" </#if> id="licss_${(normattr.id)!0}" onclick="luowaColornameClick(this)" ><a id="${(normattr.id)!0}" data-iscustom="${(norm.isCustom)?string('true','false')}" data-pic-url="${(normattr.url)!''}">${(normattr.name)!""}</a><i class="num show_num" id="badgespan_${(normattr.id)!0}"  ></i></span>
									</div>
								</#list>									
							</ul>
						</div>
						<div class="fl cart_r">
							
							<#list norm.attrList as normattr>
								<div id="shuliangul_${(normattr.id)!'0' }_all" class="shuliangclass" style=" <#if normattr_index == 0 >display:block;<#else>display:none;</#if>">
									<div class="overflow ss">
										<a class="fl active specifications" id="jiagong_${(normattr.id)!0}_two">裸袜</a>
										<div class="nums fr">
						                	<input type="hidden" name="luowapop_dabiaowaFlag" id="dabiaowaFlag_${(normattr.id)!'0' }_two" />
											<input type="hidden" name="productPackageId" id="productPackageId_${(normattr.id)!'0' }_two" />
											<input type="hidden" name="luowapop_productGoodsId" value="${(normattr.productGoodsId)!'0' }"/>
											<input type="hidden" name="isSelfLabel" id="isSelfLabeltxt_${(normattr.id)!'0' }_two" value="0"/>
											<input type="hidden" name="luowa_price" id="luowaPrice_${(normattr.id)!'0' }_two" value="0"/>
											
											<input type="hidden" name="luowa_unitPrice" id="luowa_unitPrice_${(normattr.id)!'0' }" value="0"/>
											<input type="hidden" name="luowa_number" id="luowa_number_${(normattr.id)!'0' }" value="0"/>
											<input type="hidden" name="luowa_totalPrice" id="luowa_totalPrice_${(normattr.id)!'0' }" value="0"/>

											<div id="shuliangdiv_${(normattr.id)!'0' }_two" >
												<span><a id="productMinus_${(normattr.id)!'0' }_two">-</a></span>
						                        <input type="number" value="0" class="em1 num1 getnum fl tota" onkeyup="this.value=this.value.replace(/\D/g,'')" name="luowapop_number" onafterpaste="this.value=this.value.replace(/\D/g,'')" size="4" id="number${(normattr.id)!'0' }_two" onblur="modify_two(this);">
						                        <span><a id="productPlus_${(normattr.id)!'0' }_two" >+</a></span>
											</div>


		
					                    </div>
									</div>
										
									<div class="overflow ss">
										<a class="fl active main_nav specifications" id="jiagong_${(normattr.id)!0}_four">二次加工</a>
										<div class="nums fr">

						                	<input type="hidden" name="luowapop_dabiaowaFlag" id="dabiaowaFlag_${(normattr.id)!'0' }_four" />
											<input type="hidden" name="productPackageId" id="productPackageId_${(normattr.id)!'0' }_four" />
											<input type="hidden" name="luowapop_productGoodsId" value="${(normattr.productGoodsId)!'0' }"/>
											<input type="hidden" name="isSelfLabel" id="isSelfLabeltxt_${(normattr.id)!'0' }_four" value="0"/>
											<input type="hidden" name="luowa_price" id="luowaPrice_${(normattr.id)!'0' }_four" value="0"/>
											
											<input type="hidden" name="jiagong_unitPrice" id="jiagong_unitPrice_${(normattr.id)!'0' }" value="0"/>
											<input type="hidden" name="jiagong_number" id="jiagong_number_${(normattr.id)!'0' }" value="0"/>
											<input type="hidden" name="jiagong_package_unitPrice" id="jiagong_package_unitPrice_${(normattr.id)!'0' }" value="0"/>
											<input type="hidden" name="jiagong_totalPrice" id="jiagong_totalPrice_${(normattr.id)!'0' }" value="0"/>
											
											<div id="shuliangdiv_${(normattr.id)!'0' }_four">
												<span><a id="productMinus_${(normattr.id)!'0' }_four">-</a></span>
						                        <input type="number" value="0" class="em1 num1 getnum fl tota" onkeyup="this.value=this.value.replace(/\D/g,'')" name="luowapop_number" onafterpaste="this.value=this.value.replace(/\D/g,'')" size="4" id="number${(normattr.id)!'0' }_four" onblur="modify_four(this);">
						                        <span><a id="productPlus_${(normattr.id)!'0' }_four">+</a></span>
											</div>
 
							                    </div>
									</div>
									<div class="overflow ss">
										<p style="text-align:left" id="displayPackage_${(normattr.id)!0}"></p>
									</div>
									<input type="hidden" id="goodsStock_${(normattr.id)!0}" name="goodsStock" value="${(normattr.productStock)!0}">
									 
        							<div class="wap kc">
        								<p>库存<em id="productStockSpan_${(normattr.id)!'0' }">${(normattr.productStock)!'0'}</em>${(product.unit)!''}</p>
        							</div>		
								</div>	
							</#list>	
						</div>
					</div>
				</div>
			</div>
			
			<#if norms?size == (norm_index + 1)>
			<div class="clear"></div>
		    <div class="t1 overflow public">
		    	<span class="fl">按手起批(一手=${(pAttr.value)!''}${(product.unit)!''})</span>
		        <div class="fr">
		        	<span style="margin-right:1rem">共<em class="em1" id='totalNumber'>0</em>${(product.unit)!''}</span>
		            <em class="em1" id="totalPrice">￥0</em>
		        </div>
		    </div>
		    <a class="sure" style="margin-bottom:20px" id="luowapop_Confirm">确定</a>								
											

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
			</#if>
	<div class="cd-user-modal" id="processingimg_${(normattr.id)!'0' }">
		<div class="cd-user-modal-container bz">
			<h3 class="tp2">选择包装方式</h3>
			<div class="am-slider am-slider-default" data-am-flexslider="" style="width:80%; margin:1rem auto"> 
	                <div class="am-viewport" style="overflow: hidden; position: relative;">
	                	<ul class="am-slides bd_list" style="width: 800%; transition-duration: 0s; transform: translate3d(-596px, 0px, 0px);">
		                	<#list productPackage as pka>
								<li data-product-pageck-id="${pka.id}" data-product-package-name="${pka.name!''}" data-product-package-price="${(pka.price)!0}" class="" data-thumb-alt="" style="width: 298px; margin-right: 0px; float: left; display: block;">
									<a href="javascript:;" class="a-img" id="aimg_${(normattr.id)!'0' }"> 
										<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(pka.image)!''}" draggable="false">
									</a>
									<p>${(pka.name)!''}(加工费 ${(pka.price)!0}元)</p>
								</li> 
							</#list>
	                	</ul>
					</div>
					<ol class="am-control-nav am-control-paging">
						<li><a href="#" class="">1</a><i></i></li>
						<li><a href="#" class="am-active">2</a><i></i></li>
					</ol>
					<ul class="am-direction-nav">
						<li class="am-nav-prev"><a class="am-prev" href="#"> </a></li>
						<li class="am-nav-next"><a class="am-next" href="#"> </a></li>
					</ul>
				</div>
	            <div class="tp4 close_btn overflow">
	                <a href="#" class="cd-close-form" id="closePackage">关闭</a>
	                <a href="#" id="confirmPackage" class="cd-close-form">选择</a>
	            </div>
			</div>
		</div>
	</div>
			</#list> 
			</#if>

