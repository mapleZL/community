<#include "/front/commons/_headbig.ftl" />
<style type='text/css' rel="stylesheet">
  .recivetitle{
     font-family:微软雅黑;
     font-size:14px;
     font-weight:100;
     display:block;
     font-color:#000;
  }
  .ulclass {
  float:left;
  width:700px;
  }
  .liclass{
  width:350px;
  float:left;
  }

</style>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/member/myreciptaddress.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/areaSupport.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css">

<@form.form id = "invoiceForm"  method="POST" autocomplete="off">
    <input type="hidden" name="ordersId" value="${ordersId}"/>
	<!-- 收货地址ID  -->
	<input type="hidden" id="addressId" name="addressId" value="${(defaultAddress.id)!''}"/>
	<input type="hidden" id='invoiceStatus' name="invoiceStatus" value="${(orderCommitVO.invoiceStatus)!''}"/>
	<!-- 发票内容 -->
	<input type="hidden" id='invoiceType' name="invoiceType" value="${(orderCommitVO.invoiceType)!''}"/>
	<!-- 发票抬头 -->
	<input type="hidden" id='invoiceTitle' name="invoiceTitle" value="${(orderCommitVO.invoiceTitle)!''}"/>
	<!-- 支付方式名称 -->
	<input type="hidden" id='paymentName' name="paymentName" value="在线支付"/>
	<!-- 支付方式code -->
	<input type="hidden" id='paymentCode' name="paymentCode" value="ONLINE"/>
	<input type="hidden" id='isBalancePay' name="isBalancePay" value="false"/>
	<!--余额账户支付总金额-->
	<input type="hidden" id='balance' name="balance"/>
	<input type="hidden" id='balancePwd' name="balancePwd"/>
	
	<input type="hidden" id='integral' name="integral"/>
	<!--运费-->
	<input type="hidden" id='takePrice' name="takePrice" value="0"/>
	 <!--现金支付金额-->
	<input type="hidden" id='moneyPaidReality' name="moneyPaidReality"/>
	<!-- 记录红包使用信息 -->
	<!--本次发货数量-->
	 <input type="hidden" name="mobile1"  id="mobile1" value="${userInfo.mobile}"/>
	 <input type="hidden" name="zipCode1"  id="zipCode1" value="${userInfo.zipCode}"/>
	 <input type="hidden" name="orderSn1"  id="orderSn1"  value="${userInfo.orderSn}"/>
	 <input type="hidden" name="memberName1"  id="memberName1" value="${userInfo.memberName}"/>
	 <input type="hidden" name="productSku1" id="productSku1" value=""/>
	 <input type="hidden" id="orderGoodsId1"  name="orderGoodsId1" value=""/>
	 <input type="hidden" name="number1" id="number1"  value=""/>
	 <input type="hidden" name="transportType" id="transportType" value="1"/>
	
	
	<!--
	<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
		<#list cartInfoVO.cartListVOs as cartListVO>
			<input type="hidden" id='couponType${(cartListVO.seller.id)!0}' name="couponType${(cartListVO.seller.id)!0}" value="0"/>
			<input type="hidden" id='couponSn${(cartListVO.seller.id)!0}' name="couponSn${(cartListVO.seller.id)!0}"/>
			<input type="hidden" id='couponPassword${(cartListVO.seller.id)!0}' name="couponPassword${(cartListVO.seller.id)!0}"/>
			<input type="hidden" id='couponValue${(cartListVO.seller.id)!0}' name="couponValue${(cartListVO.seller.id)!0}" value="0"/>
		</#list>
	</#if>
	-->
	<!-- 记录使用了红包的商家ID，多个ID用英文逗号分隔 -->
	<input type="hidden" id='useCouponSellerIds' name="useCouponSellerIds" value=""/>
</@form.form>


		<div class='container w'>
			<div class='breadcrumb'>
				<strong class='business-strong'>
					<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>首页</a>
				</strong>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>大袜网</a>
				</span>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>订单中心</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder' >
				<h3>云仓托管发货</h3>
				   <div class='mc'>
				        <!--收货人信息-->
			        <div id="default_address_div" >
				        <div style="margin-left:10px;height:120px;">
				          <div style="margin-left:10px;">
				               <h2 class="recivetitle">收货人信息</h2>
				          </div>
				          <div style="margin-left:10px;">
				              <ul class="ulclass">
				               <li class="liclass">
					                <p><label>订单号: </label>
					              			<input type="text" name="orderSn"  id="orderSn"  value="${userInfo.orderSn}"  style="border:0;outline:none;"/>
					                </p>
				              </li>
				               <li class="liclass">
					                 <p><label>收货人:</label>
					                   		<input type="text" name="memberName"  id="memberName"  value="${userInfo.memberName}"  style="border:0;outline:none;"/>
				              		</p>
				               </li>
				              </ul>
				              <ul class="ulclass">
				                <li class="liclass">
				                   <p>
				                       <label>收货地址:</label>
				                  	   <input type="text" name="addressAll"  id="addressAll"  value="${userInfo.addressAll}"  style="border:0;outline:none;"/>
				                   </p>
				                </li>
				                <li class="liclass">
				                   <p>
				                  		<label>详细地址:</label>
				                  		<input type="text" name="addressInfo"  id="addressInfo"  value="${userInfo.addressInfo}"  style="border:0;outline:none;width: 240px;"/>
				                   </p>
				                </li>
				              </ul>
				              <ul class="ulclass">
				                 <li class="liclass">
						              <p>
						                  <label>邮编:</label>
						                  <input type="text" name="zipCode"  id="zipCode"  value="${userInfo.zipCode}"  style="border:0;outline:none;"/>
						              </p>
						          </li>
						          <li class="liclass">
						              <p>
						                  <label>电话:</label>
						                  <input type="text" name="mobile"  id="mobile"  value="${userInfo.mobile}"  style="border:0;outline:none;"/>
						              </p>
						           </li>
				              </ul>
				         </div>
				       </div>
				       <!--收货人信息end-->
				       <div style="margin-left:10px;">
				           <a href="javascript:void()" onclick="showMoreAddress()">+ 更多地址</a>
				       </div>
				       <!--收货地址-->
				       <div style="margin-left:10px;display:none;" class='checkout-steps' id="tiantian">
				               <div class='step-tit'  id="tiantian1">
									<h3>使用新的地址</h3>
									<div class='extra-r'>
										<a href='javascript:void(0);' class='ftx-05 addaddress' onclick="addOrEditAddress(0)">新增收货地址</a>
									</div>
								</div>
								<div class='step-cont'  id="tiantian2">
									<div id='consignee-addr'>
										<div class='consignee-cont consignee-off' style='position: relative;' id='consignee1'>
											<ul class="consignee-list" id='consignee-list' style='top:0px;position:relative;'>
												<#if addressList??>
													<#list addressList as address>
														<li style='display: list-item;' class='order-select' value="${(address.id)!''}" >
															<#if hasDefaultAdd??&&hasDefaultAdd='yes'&&(address.state)=1>
																<div class='consignee-item item-selected'>
																	<span>默认地址</span>
																	<b></b>
																</div>
																<#elseif hasDefaultAdd??&&hasDefaultAdd='no'&&address_index=0>
																<div class='consignee-item item-selected'>	
																	<span>${address.memberName}</span>
																	<b></b>
																</div>
																<#else>
																	<div class='consignee-item'>	
																		<span>${address.memberName}</span>
																		<b></b>
																	</div>
															</#if>
															<div class='addr-detail'>
																<span class='addr-name'>${address.memberName}</span>
																<span class='addr-info'>
																	<#assign adds = address.addAll+address.addressInfo>
																	${commUtil.substring(adds,30)}
																</span>
																<span class='addr-tel'>${commUtil.hideMiddleStr(address.mobile,3,4)}</span>
															</div>
															<div class='op-btns'>
																<!-- <a href='' class='ftx-05'>设为默认地址</a> -->
																<a href='javascript:void(0)' class='ftx-05' onclick="addOrEditAddress('${address.id}')">编辑</a>
																<!-- <a href='' class='ftx-05'>删除</a> -->
															</div>
														</li>
													</#list>
												</#if>
											</ul>
										</div>
									</div>
									<!-- 收起地址和更多地址 -->
									<div class='more-addr switch-on' id='consigneeItemAllClick' onclick='show_ConsigneeAll();'>
										<span class='ftx-05'>更多地址</span>
									</div>
									<div class='more-addr switch-off hide' id='consigneeItemHideClick' onclick='hide_ConsigneeAll()'>
										<span class='ftx-05'>收起地址</span>
									</div>
									<!-- end -->
								</div>
								<div class='hr'></div>
				       </div>
			       </div>
				        <!-- 选中上门自提时 -->
				       <div id="smzt_address_div" style="margin-left:10px;height:120px;display:none;">
					       <div style="margin-left:10px;">
				               <h2 class="recivetitle">发货人信息</h2>
				           </div>
				           <div style="margin-left:10px;">
				              <ul class="ulclass">
								   <li class="liclass">
									   <p>
										   	<label>提货地址：</label>
										   	<span>浙江省绍兴诸暨市千禧路20号猫头鹰网仓</span>
									   </p>
									</li>
									<li>
									   <p>
										   	<label>联系电话：</label>
										    <span>0310-3838438</span>
									   </p>
									</li>
								</ul>
							</div>
						</div>
				       <!--收货地址end-->
				       <!-- 物流方式 -->
				       <div>
							<div>
								<div class='step-tit'>
									<h3>物流方式</h3>
								</div>
								<div class='step-cont'>
									<div class='payment-list'>
										<div class='list-cont'>
											<ul>
												<li class='payment-li'>
													<div class='payment-item  online-payment item-selected' value="SENDNOW" pname="立即发货" onclick="hiddenAddress(1)"><b></b>立即发货</div>
												</li>
												<li class='payment-li'>
													<div class='payment-item  online-payment' value="TAKESELF" pname="上门自提" onclick="hiddenAddress(2)"><b></b>上门自提</div>
													<input id="smzt_status" type="hidden" value="" />
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- end -->
					  <!-- 物流类型 -->
						<div class='step-tit' id="sbisyou_id">
							<div class='extra-r' style="margin-left: 1%;">
								<!--<a href='javascript:void(0);' class='ftx-05 addaddress' onclick="addOrEditAddress(0)">新增收货地址</a>-->
								 <input type = "radio" name="transportType" id="transportType1" value="1" checked="checked;" onchange="changeFee()"/>
								 <label for="transportType1"  class='ftx-05' style="cursor:pointer;font-weight:100;" onblur="changeFee()">中通快递</label>
								 <span style="margin-left: 20px;"></span>
								 <input type = "radio" name="transportType" id="transportType3" value="3" onchange="changeFee()" />
								 <label for="transportType3" class='ftx-05'  style="cursor:pointer;font-weight:100">顺丰快递</label>
								 <span style="margin-left: 20px;"></span>
								 <#--
								 	<input type = "radio" name="transportType" id="transportType2" value="2" onchange="changeFee()" />
								 	<label for="transportType2" class='ftx-05' style="cursor:pointer;font-weight:100" >圆通快递</label>
								 	<span style="margin-left: 20px;"></span>
								 	<input type = "radio" name="transportType" id="transportType4" value="4" onchange="changeFee()" />
								 	<label for="transportType4" class='ftx-05'  style="cursor:pointer;font-weight:100">物流</label>
								 -->
							</div>
						</div>
					<!-- end -->
			       <!-- 支付方式 -->
						 <div id='shipAndSkuInfo'>
							<div id='payShipAndSkuInfo'>
								<div class='step-tit'>
									<h3>支付方式</h3>
								</div>
								<div class='step-cont'>
									<div class='payment-list'>
										<div class='list-cont'>
											<ul id='payment-list'>
												<li class='payment-li' style="display: none;">
													<div class='payment-item  offline-payment' value="OFFLINE" pname="货到付款" onclick="showAddress()"><b></b>货到付款</div>
												</li>
												<li class='payment-li'>
													<div class='payment-item  item-selected online-payment' value="ONLINE" pname="在线支付" ><b></b>在线支付</div>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
				       <!--订单详情-->
				       <div class='step-tit'>
						  <h3>订单详情</h3>
						</div>
				       <div style="margin-left:10px;">
				           <@form.form id="threesendForm" method="post" >
				                <table class='tb-void'>
									<thead>
										<tr>
											<th width='300'  colspan="2">商品</th>
											<th>商品sku</th>
											<th>购买总数量</th>
											<th>已发货数量</th>
											<th>本次发货</th>
										</tr>
									</thead>
									<#list goodsInfo as goodsInfo>
									    <#if goodsInfo ?? && goodsInfo ?size &gt; 0>
									         <input type="hidden" value="${goodsInfo.orderGoodsId}"  name="orderGoodsId"/>
									         <input type="hidden" value="<#if goodsInfo.fullContainerQuantity==0>0<#else>${goodsInfo.fullContainerQuantity}</#if>"  name="fullContainerQuantity"/>
									         <tbody>
											     <tr>
											        <td><img  width='50' height='50' class='err-product' title="${(goodsInfo.productName)!''}"
														src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${goodsInfo.images!''}"/>
												    </td>
												    <td>
												       <span> ${goodsInfo.productName}</span><br/>
												       <input type="hidden" name="productName" value="${goodsInfo.productName}"/>
												       <span>${goodsInfo.normName}</span>
												       <!-- 隐藏域  保存商品唯一标识 -->
												       <input type="hidden" name="productId_name" value="${goodsInfo.productId}"/>
												       <input type="hidden" name="normName" value="${goodsInfo.normName}"/>
												    </td>
												    <td>
												      <span>${goodsInfo.productSku}</span>
												      <input type="hidden" name="productSku" value="${goodsInfo.productSku}"/>
												    </td>
												    <td>
												      <span>${goodsInfo.number}</span>
												      <!-- 隐藏域   保存商品数量  -->
												      <input type="hidden" name="number_name" value="${goodsInfo.number}"/>
												      <input type="hidden" name="number" value="${goodsInfo.number}"/>
												    </td>
												    <td>
												       <span>${(goodsInfo.deliveredNum)!''}</span>
												      <input type="hidden" name="deliveredNum" value="<#if goodsInfo.deliveredNum ??>0<#else>${(goodsInfo.deliveredNum)!''}</#if>"/>
												    </td>
												    <td>
												      <input onblur="ajaxCaculate(this.value)" type="text" name="num" value="0"/>
												    </td>
											     </tr>
										    </tbody>
								       </#if>
								</#list>
							</table>
				          </@form.form>
				        </div>
				        <!--订单详情end-->
				        
				        <!-- 发票 -->
				        <!--
							<div class='step-tit'>
								<h3>发票信息</h3>
							</div>
							<div class='step-content'>
									<div class='invoice-cont'>
										<span class='mr10 invoice_title' > 不开发票</span>&nbsp;
										<span class='mr10 invoice_title_show' ></span>&nbsp;
										<span class='mr10 invoice_content_show'></span>&nbsp;
										<a href='javascript:void(0);' class='ftx-05 invoice-edit'>修改</a>

									</div>
							</div>
							<div class='hr'></div>
							<div class='order-coupon'>-->
								<!--余额支付-->
								<!--<div class='item' id='balance-div'>
									<div class='toggle-title'>
										<a href='javascript:void(0);' class='toggler'>
											<b></b>
											使用余额
										</a>
									</div>
									<div class='toggle-wrap' >
										<div class='cbox'>
											<div class='inner'>
												<div class='form'>
													<input type='checkbox' id='selectOrderBalance' autocomplete="off">
													<label id='canUsedBalanceId'>使用余额（账户当前余额：${(member.balance)!'0.00' }元）</label>
												</div>
											</div>
										</div>
									</div>-->
									<!-- 记录一个hidden值方便计算 -->
									<!--<input type="hidden" id="usedBalanceHidden" autocomplete="off"/>
									<input type="hidden" id="usedCreditHidden" autocomplete="off"/>
								</div>
								<div id="balance_pwd_div" style="display:none">
										<div >
											<div>
												<div >
													支付密码：<input type='password' id='balancePassword' name="balancePassword" autocomplete="off">
												</div>
											</div>
										</div>
									</div>-->
								<!-- end -->
				                      <!--使用积分 -->
										<!--<#if config?? && config.integralScale?? && config.integralScale &gt; 0>
										<div class='item' id='balance-div'>
											<div class='toggle-title'>
												<a href='javascript:void(0);' class='toggler'>
													<b></b>
													使用积分
												</a>
											</div>
											<div class='toggle-wrap' id='balance-div'>
												<div class='cbox'>
													
													<div id="orderBeanItem" class="inner">
												        <div class="beans-2015">
												           	<div class="cho-con">
												 					<label for="xxx"><input type="checkbox" name="" id="integralUse">
												 					使用积分</label>
																	<div class="cho-bar disabled" id="useBean">
																		<input type="text" id="jdBeanVal" value="0" readonly="readonly"> 
																		<span class="plus" id="plus">+</span>
																		<span class="minus" id="reduction">-</span>
																	</div>
																	<div class="cho-r">
																		<span>个</span> <span class="bean-exchange">￥0.00</span>
																	</div>
															</div>
															<div class="fake-hr"></div>
															<div class="cho-result">
																	共<span class="total">${(member.integral!0)}</span>个积分，本次可用 <span class="available">${(member.integral - ((member.integral)%(config.integralScale)))!0}</span>个积分 
																	<span class="beans-cho-tip">使用规则(<i style="color:#edd28b;">积分满${config.integralScale!0}即可使用：每次使用积分为n*${config.integralScale!0}</i>)</span>
																	<span class="clr"></span>
															</div>
												        </div>
												    </div>
												
												</div>
		
											</div>
										</div>
										</#if>-->
								<!--  结束-->
				 <!--  </div>
		   </div>
	   </div>-->
	   		<div class='order-summary'>
			<div class='statistic' style='float:right'>
				<div class='list' style="display: block;">
					<span>
						<em class='ftx-01' id="tzm_totalnumber">0</em>
						 件商品，总商品金额（原价）：
					</span>
					 <em class='price' id='warePriceId'>￥0.00</em>
				</div>
				<div class='list' style="display: none;">
					<span>
						 总商品金额（优惠）：
					</span>
					   <em class='price' id='discountAmountPriceId'>￥0.00</em>
					 <input type="hidden" id="tzm_ppricetotal" value="0"/>
				</div>
				<div class='list'>
					<span>活动节省：</span>
					<em class='price' id='discountPriceId'>
						<font style="color:#FF6600"> - ￥0.00 </font>
					</em>
				</div>
				<div class='list'>
					<span>红包节省：</span>
					<em class='price'>
						<font style="color:#FF6600"> - ￥<em id='couponPriceId'>0.00</em> </font>
					</em>
				</div>
				<div class='list'>
					<span>运费：</span>
					<em class='price' id='freightPriceId'>
						<font style="color:#FF6600"> + ￥0.00 </font>
					</em>
				</div>
				<div class='list'>
					<span>套餐服务费：</span>
					<em class='price' id='packagePriceId'>
						<font style="color:#FF6600"> + &yen;0.00</font>
					</em>
				</div>
				<div class='list' id="balancePriceListDiv" style="display:none;">
					<span>余额 ：</span>
					<em class='price' >
						<font style="color:#FF6600"> -￥<em id='balancePay'></em></font>
					</em>
				</div>
				<div class='list' id="creditPriceListDiv" style="display:none;">
					<span>赊账 ：</span>
					<em class='price' >
						<font style="color:#FF6600"> -￥<em id='creditPay'></em></font>
					</em>
				</div>
				<div class='list' id="integralPriceListDiv" style="display:none;">
					<span>积分 ：</span>
					<em class='price' >
						<font style="color:#FF6600"> -￥<em id='integralPay'></em></font>
					</em>
				</div>
				<div class='list'>
					<span>应付总额：</span>
					<em class='price' id='sumPayPriceId'>￥0.00</em>
					<!-- 记录一个hidden值方便计算 -->
					<input type="hidden" id="sumPayPriceHidden" value="0" autocomplete="off"/>
				</div>
			</div>
		</div>
		<div class='clr'></div>
		<div class='trade-foot' style="float:right;">
				<div class='group' id='checkout-floatbar'>
				<div class='checkout-buttons'>
					<div class='sticky-wrap'>
						<div class='inner'>
							<button type='button' class='checkout-submit btn btn-danger' id='order-submit' onclick="submitOrder()"> 
								提交订单
								<b></b>
							</button>
							<span class='total'>
								应付总额：
								<strong id='payPriceId'>￥0.00</strong>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class='consignee-foot' style="background-color:white;border-top:0;">
				<#if defaultAddress??>
         				<#assign adds = defaultAddress.addAll+defaultAddress.addressInfo>
         		<div style="display:none;">
					<p>
          				寄送至： <span id="addressDetail" title="${adds}">${commUtil.substring(adds,30)}</span>
       				</p>
       				<p>
        				收货人：<span id="consigneeName">${(defaultAddress.memberName)!'' }</span>
        					<span id="consigneeMobile">${commUtil.hideMiddleStr((defaultAddress.mobile)!'',3,4)}</span>
       				</p>
       			</div>
      			</#if>
			</div>
		</div>
	</div>
</div>

<#include "/front/commons/_endbig.ftl" />
<!-- 收货地址显示区 -->
<div class='background-layer' id='Harvest'>
</div>

<!-- 修改发票 -->
<div class='background-layer' id='mainId' style="display: none;">
	<div class='internation'>
		<div class='internation-title'>
			<span>发票信息</span>
		</div>
		<div class='internation-content'>
			<div id='dialogIframe'>
				<div class='invoice-thickbox' id='invoice-tab'>	
					<div class='tab-nav'>
						<ul>
							<li id='click_1' class='tab-nav-item tab-item-selected' value='1'>普通发票<b></b></li>
							<!-- <li id='click_2' class='tab-nav-item  disabled' value='2'>电子发票<b></b></li>
							<li id='click_3' class='tab-nav-item  disabled' value='3'>增值税发票<b></b></li> -->
						</ul>
					</div>
					<div class='form' id='Invoice'>
						<div class='item'>
							<span class='label'>发票抬头：</span>
							<div class='fl'>
								<div class='invoice-list invoice-tit-list' id='invoice-tit-list'>
									<div class='invoice-item invoice-item-selected'>
										<div id='invoice-1'>
											<span class='fore2'>
												<input  type='text' class='itxt' readonly="readonly" value='个人'>
												<b></b>
											</span>
										</div>
									</div>
									<#if invoiceList??>
										<#list invoiceList as invoice>
											<div class='invoice-item'>
												<div id='invoice-1'>
													<span class='fore2'>
														<input  type='text' class='itxt' readonly="readonly" value='${invoice.content}'>
														<b></b>
													</span>
												</div>
												<div class='btns'>
													<a href='javascript:void(0)' class='ftx-05 edit-tit'>编辑</a>
													<a href='javascript:void(0)' class='ftx-05 update-tit hide' onclick="updateInvoice(this,'${invoice.id}')">保存</a>
													<a href='javascript:void(0)' class='ftx-05 ml10 del-tit' onclick="delInvoice(this,'${invoice.id}')">删除</a>
												</div>
											</div>
										</#list>
									</#if>
									
									<div class='invoice-item invoice-item-selected' id='save-invoice' style='display:none'>
										<div class='add-invoice-tit'>
											<input name="content" type='text' class='itxt itxt04'>
											<div class='btns'>
												<a href='javascript:void(0)' class='ftx-05 save-tit' onclick="saveInvoce(this)">保存</a>
											</div>
										</div>
									</div>
								</div>
								<div class='add-invoice' id='add-invoice'>
									<a href='javascript:void(0);' class='ftx-05' onclick='add_save();'>新增单位发票</a>
								</div>
							</div>
						</div>
					</div>
					<div class='tab-box'>
						<div class='tab-con'>
							<div class='form'>
								<div class='item'>
									<span class='label'>发票内容：</span>
									<div class='fl'>
										<div class='invoice-list' id='InvoiceInformation'>
											<ul id='electro_book_content_radio'>
												<li class='invoice-item' val="noinvoice">
													不开发票
													<b></b>
												</li>
												<li class='invoice-item invoice-item-selected' val="明细">
													明细
													<b></b>
												</li>
												<!-- <li class='invoice-item' val="办公用品">办公用品<b></b></li>
												<li class='invoice-item' val="电脑配件">电脑配件<b></b></li>
												<li class='invoice-item' val="耗材">耗材<b></b></li> -->
											</ul>
										</div>
									</div>
								</div>
								<div class='item' style='margin-top:30px'>
									<span class='label'>&nbsp;</span>
									<div class='fl'>
										<div class='op-obt'>
											<a href='javascript:void(0)' class='btn btn-default save-invoice'>保存发票信息</a>
											<a href='javascript:void(0)' class='btn btn-default cancel-invoice' style='margin-left:10px'>取消</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 右上角退出按钮 -->
		<a href='javascript:void(0);' class='internation-close harvest-close-invoice'></a>
	</div>
	<!--  -->
</div>

<script type="text/javascript">
   	// 收货人信息鼠标移入移出
 	$('#consignee-addr').delegate('li','mouseenter',function(){
    	$(this).addClass('li-hover');
    }).delegate('li','mouseleave',function(){
		$(this).removeClass('li-hover');
	});
	
	// 新增地址
	$(".addaddress").click(function(){
		orderCenter();
		$("#Harvest").addClass("lay-display");
	});
	// 关闭收货信息层
	$(".harvest-close").click(function(){
		$("#Harvest").removeClass("lay-display");
	});

	// 居中
	function orderCenter(){
    	var v_top=($(window).height()-375)/2;
    	var v_left=($(window).width()-690)/2;
   		$(".internation").css({"left":v_left+"px","top":v_top+"px"});
	}
	
	function changeFee(){
		var transportType = $("input[name='transportType']:checked").val();
		$("#transportType").val(transportType);
		ajaxCaculate(transportType);
	}
	
	function hiddenAddress(flag){
		
			//1为立即发货
			if(flag==1){
				//显示运送方式
				$("#sbisyou_id").show();
				
				$("#smzt_status").val("");
				$("#default_address_div").show();
				$("#smzt_address_div").hide();
				
				ajaxCaculate(4);
				
			}
			//2为上门自提
			if(flag==2){
				//隐藏运送方式 
				$("#sbisyou_id").hide();
				//不走支付界面 
				$("#paymentCode").val("TAKESELF");
 				$("#paymentName").val("上门自提");
 				
 				//在触发  立即发货 计算运费
				$("#smzt_status").val("1");
				$("#default_address_div").hide();
				$("#smzt_address_div").show();
				
				// 验证本次发货数量
		  	    if(!checkNum()){
		  	      return;
		  	    }
		  		//商品ID  productId_name    商品数量 number_name
		  		//当前选中收货地址id
		  		var addressId = $("#addressId").val();
		  		var orderSn = $("#orderSn").val();
		  		//不同商品对应发货 数量
		  		var nums = $("input[name='num']");
		  		var val="";
		  		var productIds = $("input[name='productId_name']");
		  		var same_numtotal=0;
		  		var numstrs = "";
		  		var productIds2 = "";
		  		var tzm_totalnumbers = 0;
		  		for(var i=0;i<productIds.length;i++){
		  				var flag = productIds.length-1;
		  				var bool = ((i+1)<=flag && productIds[i].value == productIds[i+1].value) ;
		  				if(bool){
		  					//same_numtotal = parseInt(nums[i].value) + parseInt(nums[i+1].value);
		  					numstrs += 0 + ",";
		  					productIds2 += productIds[i].value + ",";
		  					i = i+1;
		  				}else{
		  					numstrs += 0 + ",";
		  					productIds2 += productIds[i].value + ",";
		  				}
		  		}
		  		for(var i=0;i<productIds.length;i++){
		  				tzm_totalnumbers += parseInt(nums[i].value);
		  		}
		  		numstrs = numstrs.substring(0,numstrs.lastIndexOf(","));
		  		productIds2 = productIds2.substring(0,productIds2.lastIndexOf(","));
		  		// 根据发货数量和地址 异步计算运费
		  		$("#sumPayPriceHidden").val(0);
		  		var transportType = $("input[name='transportType']:checked").val();
		  		$.ajax({
		  			type : "GET",
					url :  domain+"/member/getFee.html",
					data : {
						productIds:productIds2,
						numstrs:numstrs,
						transportType:transportType,
						memberAddressId:addressId
					}, 
					dataType : "json",
					success : function(data) {
						if(data.success){
						//保存 当前运费总额
		  				$("#takePrice").val(data.data);
						//tzm_totalnumber 动态渲染 送、多少件 商品数量
						$("#tzm_totalnumber").html(tzm_totalnumbers);
						 	//运费  动态渲染
						 	$("#freightPriceId").children("font").html(" + ￥"+data.data);
						 	//应付总额 动态渲染
						 	var sumPayPrice = $("#sumPayPriceHidden").val();
						 	var ppricetotal = $("#tzm_ppricetotal").val();
							$("#sumPayPriceHidden").val(sumPayPrice-ppricetotal+data.data);
							$("#sumPayPriceId").html(" ￥"+(sumPayPrice-ppricetotal+data.data));
							$("#payPriceId").html(" ￥"+(sumPayPrice-ppricetotal+data.data));
						}else{
							jAlert(data.message);
						}
					},
					error : function() {
						jAlert("数据加载失败！");
					}
		  		});
  		
			}
	}
	
	function showAddress(){
		$("#address").show();
	}
	
		//显示更多地址
	function show_ConsigneeAll(){
		$("#consigneeItemAllClick").addClass("hide");
		$("#consigneeItemHideClick").removeClass("hide");
		$("#consignee1").removeClass("consignee-off");
		if($('#consignee-list li').length>4){
			$('#consignee-addr .consignee-cont').css({
			    'height':162,
			    'position':'relative',
			    'overflow-y': 'auto'
 					 });
		}else{
			 $('#consignee-addr .consignee-cont').css({
		      'height':'auto'
		    });
		    $('#consignee-addr ul').css({
		    'position':'relative'
		    });
		}
		$(".consignee-item").parents("li").css("display","list-item");
		//设置默认地址
		addressSelect();
	}
	
		function hide_ConsigneeAll() {
		//设置默认地址
		addressSelect();
		
		$("#consigneeItemAllClick").removeClass("hide");
		$("#consigneeItemHideClick").addClass("hide");
		$("#consignee1").addClass("consignee-off");
		$('#consignee-addr .addr-ctrl').hide();
		$('#consignee-addr .consignee-cont').css({
			'height':'40px',
			'overflow-y': 'hidden'
			});
		$('#consignee-addr ul').css({
		    'top': '0px',
		    'position':'absolute'
		});

		var li_selected = $(".consignee-item.item-selected").parent("li");//当前选中li
		var first_li = $(".consignee-item").parents("li").last();//当前列表第一项
		var _tempstr = first_li.find("div span").first().html();
		if(_tempstr && _tempstr.indexOf("默认地址") > -1) {
		    // 1.插入在默认地址之后
		    li_selected.clone().insertAfter(first_li);
		} else {
		    // 2.插入在地址列表第一位
		    li_selected.clone().insertBefore(first_li);
		}
		  li_selected.remove();
		  // 收起并定位第一页功能
		  $(".consignee-item").parents("li").css("display","none");
		  $(".consignee-item.item-selected").parent("li").css("display","list-item");
		  // 初始化地址组件的绑定事件，否则移动dom会导致绑定失效，因此改动组件采用delegate绑定
	}
	
	function showMoreAddress(){
	     // 判断是否隐藏
	      if($("#tiantian").is(":hidden")){
	         $("#tiantian").show();
	      }else{
	         $("#tiantian").hide();
	      }
	}
	//下面新增JS方法【仝照美】
	//点击红包弹出层
	$('.coupon-button').on('click',function(){
		// 清空已有值
		$("#selectCoupon").empty();
		$("#couponSn").val("");
		$("#couponPassword").val("");
		$(".coupon-msg-sel").html("");
		$(".coupon-msg-inp").html("");
		// 默认显示选择优惠码
		$('.off-line').hide().siblings('.tit').show('slow');
		// 显示对话框
		$('.popWin').show();
		// 记录需要用到的值
		var sellerId = $(this).attr("value");
		$("#currType").val(1);
		$("#currSellerId").val(sellerId);
		$("#currSellerOrderAmount").val($(this).attr("orderamount"));
		// 获取已绑定的优惠码
		$.ajax({
			type : "GET",
			url :  domain+"/order/getsellercoupon.html",
			data : {sellerId:sellerId},
			dataType : "json",
			success : function(data) {
				if (data.success) {
	                var selectOption = '<option value ="">-- 请选择 --</option>'
	                $.each(data.data, function(i, couponUser){
	                	var txtInfo = couponUser.couponSn + " ";
	                	if (parseFloat(couponUser.minAmount) > 0) {
	                		txtInfo += "满" + couponUser.minAmount + "元 ";
	                	}
	                	txtInfo += "抵" + couponUser.couponValue + "元现金";
	                	selectOption += "<option value=" + couponUser.couponSn + ">" + txtInfo + "</option>";
	                })
	                $("#selectCoupon").append(selectOption);
	            } else {

	            }
			}
		});
	});
	// 取消
	$('#couponCancel').on('click',function(){
		$("#currType").val(1);
		$("#currSellerId").val(0);
		$(".coupon-msg-sel").html("");
		$(".coupon-msg-inp").html("");
		$('.popWin').hide();
	});
	// 取消使用优惠券
	$('#couponNotUse').on('click',function(){
		var sellerId = $("#currSellerId").val();
		$("#couponType" + sellerId).val(0);
        $("#couponSn" + sellerId).val("");
        $("#couponPassword" + sellerId).val("");
        $("#couponInfoDiv" + sellerId).html("");
        
     	// 计算金额
        calculateCouponValue(sellerId, 0);
        
		$(".coupon-msg-sel").html("");
		$(".coupon-msg-inp").html("");
		$('.popWin').hide();
	});
	// 确定
	$('#couponSubmit').on('click',function(){
		var currType = $("#currType").val();
		var sellerId = $("#currSellerId").val();
		
		var couponSn = "";
		var couponPassword = $("#couponPassword").val();
		// 当前是选择优惠券
		if (currType == 1) {
			couponSn = $("#selectCoupon").val();
			if (couponSn == null || couponSn == "") {
				$(".coupon-msg-sel").html("请选择要使用的红包");
				return false;
			}
		} else {
			// 当前为填写优惠码
			couponSn = $("#couponSn").val();
			if (couponSn == null || couponSn == "") {
				$(".coupon-msg-inp").html("请输入要使用的优惠码");
				return false;
			}
			if (couponPassword == null || couponPassword == "") {
				$(".coupon-msg-inp").html("请输入要优惠码密码");
				return false;
			}
		}
		
        var orderAmount = $("#currSellerOrderAmount").val();
        // 校验优惠券可用性
		$.ajax({
			type : "GET",
			url :  domain+"/order/checksellercoupon.html",
			data : {
				orderAmount:orderAmount,
				couponType:currType,
				couponSn:couponSn,
				couponPassword:couponPassword,
				servicePrice:Number("${(cartInfoVO.servicePrice)!0}"),
				sellerId:sellerId
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
	                // 校验通过
	                // 记录使用的各商家的优惠券信息
	                $("#couponType" + sellerId).val(currType);
	                $("#couponSn" + sellerId).val(couponSn);
	                $("#couponPassword" + sellerId).val(couponPassword);
	                
	                var couponInfoDiv = couponSn + " 优惠" + data.data.couponValue + "元";
	                $("#couponInfoDiv" + sellerId).html(couponInfoDiv);
	                
	                // 记录使用了优惠券的商家ID
	                var sellerIds = $("#useCouponSellerIds").val();
	                sellerIds += "," + sellerId;
	                $("#useCouponSellerIds").val(sellerIds);
	                
	                // 计算金额
	                calculateCouponValue(sellerId, data.data.couponValue);
	                
					$(".coupon-msg-sel").html("");
					$(".coupon-msg-inp").html("");
					$('.popWin').hide();
	            } else {
	            	// 校验未通过
	            	if (currType == 1) {
        				$(".coupon-msg-sel").html(data.message);
        				return false;
	        		} else {
        				$(".coupon-msg-inp").html(data.message);
        				return false;
	        		}
	            }
			}
		});
	});
	
	// 重新计算优惠券优惠金额、订单总金额
	function calculateCouponValue(sellerId, newCouponValue) {
		// 订单金额
		var sumPayPriceHidden= $("#sumPayPriceHidden").val();
		// 如果修改使用的优惠券，则需要重新计算订单应付金额
		var couponValueOld = $("#couponValue" + sellerId).val();
		// 已使用优惠券的金额和
		var couponValueSum = $("#couponValueSum").val();
		
		if (couponValueOld == null) {
			couponValueOld = 0;
		}
		if (couponValueSum == null) {
			couponValueSum = 0;
		}
		
		// 新的订单金额=原订单金额+被替换的优惠券金额-新使用的优惠券金额
		sumPayPriceHidden = parseFloat(sumPayPriceHidden) + parseFloat(couponValueOld) - parseFloat(newCouponValue);
		sumPayPriceHidden = sumPayPriceHidden.toFixed(2);
		// 新的优惠券金额和=原优惠券金额和+被替换的优惠券金额-新使用的优惠券金额
		couponValueSum = parseFloat(couponValueSum) + parseFloat(couponValueOld) - parseFloat(newCouponValue);
		couponValueSum = couponValueSum.toFixed(2);
		
		// 记录该优惠券的抵扣金额
		$("#couponValue" + sellerId).val(newCouponValue);
		// 记录新的金额和
		$("#couponValueSum").val(couponValueSum);
		// 记录新的订单金额
		$("#sumPayPriceHidden").val(sumPayPriceHidden);
		
		// 修改显示金额
		$("#sumPayPriceId").html("￥"+sumPayPriceHidden);
		$("#payPriceId").html("￥"+sumPayPriceHidden);
		// 把负数改成正数显示
		couponValueSum = parseFloat(Math.abs(couponValueSum)).toFixed(2);
		$("#couponPriceId").html(couponValueSum);
	}
	
	// 优惠券模式切换
	$('.btn_change').on('click',function(){
		if($('.off-line').css('display')=='none'){
			$("#currType").val(2);
			$(".btn_change").html("选择优惠码");
			$('.off-line').show('slow').siblings('.tit').hide();
		}else {
			$("#currType").val(1);
			$(".btn_change").html("输入优惠码");
			$('.off-line').hide().siblings('.tit').show('slow');
		}
		$(".coupon-msg-sel").html("");
		$(".coupon-msg-inp").html("");
	});

	//余额消息
	$(".toggle-title").click(function(){
		var btn = $(this).parent();
		if(btn.hasClass("toggle-active")){
			btn.removeClass("toggle-active");
			$(this).siblings().css("display","none");
			$("#balance_pwd_div").hide();
			// 订单金额
			// var orderTotalPrice=  parseFloat("0.0");
			/* var orderTotalPrice= $("#sumPayPriceHidden").val();
			$("#selectOrderBalance").prop("checked",false);
			$("#balance_pwd_div").hide();
			$("#balancePriceListDiv").hide();
			// 不选中 密码清空
			$("#balancePassword").val('');
			// 隐藏域清空
			$("#balance").val('');
			$("#isBalancePay").val('false');
			$("#sumPayPriceId").html("￥"+orderTotalPrice);
			$("#payPriceId").html("￥"+orderTotalPrice);
			$("#sumPayPriceHidden").val(orderTotalPrice); */
		}else{
			btn.addClass("toggle-active");
			$(this).siblings().css("display","block");
		}
	});
	
	//选中余额checkbox 
	var fun_checkSalary = function(){
	   //订单金额
		var orderTotalPrice= $("#sumPayPriceHidden").val();
		var usedBalanceHidden = $("#usedBalanceHidden").val();
		var usedCreditHidden = $("#usedCreditHidden").val();
		if (usedBalanceHidden == null) {
			usedBalanceHidden = 0;
		}
		if (usedCreditHidden == null) {
			usedCreditHidden = 0;
		}
	   
      //如果账户总额为0 不允许被选中
      	var tdx_sumprice = $("#sumPayPriceHidden").val();
	    if(tdx_sumprice  < 1){
	    $("#selectOrderBalance").attr("checked", false);
	    returnBalance(orderTotalPrice,usedBalanceHidden,usedCreditHidden);
	    return;
	    }
		//如果余额小于等于0 那么不允许选中
		<#if member??&&member.balance??>
			<#if member.balance<=0>
				<#if !memberCredit?? || memberCredit.surplus <= 0 >
				$(this).prop("checked", false);
				</#if>
			</#if>
		</#if>

		if($(this).prop("checked")){
			$("#balance_pwd_div").show();
			// 计算账户余额及订单金额  订单金额 = 订单金额-优惠 -使用余额
			// 1、订单金额小于或等于余额 则余额使用显示订单金额 ，
			// 2、订单金额大于余额，则余额使用显示余额金额 
			// 余额
			var balance = parseFloat('${(member.balance)!'0.00'}');
			var userblanance = balance;
			balance = balance >= 0?balance:0;

			var creditAmount = 0;
			<#if memberCredit?? && memberCredit.surplus &gt;0 >
			var creditTotal = Number('${(memberCredit.surplus)!0}');
			//余额+可赊账额度 （如果赊账已结清，则余额为0）
			var balanceable = Number(userblanance) + creditTotal;
			//只有余额小于订单金额且可支付余额大于等于订单金额时才会使用赊账
			if(Number(balance) < Number(orderTotalPrice) && balanceable >= Number(orderTotalPrice)){
				$(this).next().after("<span style='color: red;'>您的账户可赊账<b>${memberCredit.surplus}</b>元，使用余额支付后，您的账户会产生欠款，请在规定时间内还清欠款。</span>");
				//余额小于订单金额，使用赊账
				creditAmount = Number(orderTotalPrice) - Number(balance);//余额不足的部分
				
				//赊账金额大于赊账总额
				//赊账金额 ＝ 赊账总额
				if(creditAmount > creditTotal){
					creditAmount = creditTotal;
				}
				$("#creditPriceListDiv").show();
				$("#creditPay").html(creditAmount);
				$("#usedCreditHidden").val(creditAmount);
			} else if(balanceable < Number(orderTotalPrice)){
				$(this).siblings("span").remove();
				$(this).next().after("<span style='color: red;'>您的账户余额（包含赊账）总额为<b>"+balanceable+
					"</b>元，其中余额欠款<b>"+userblanance+"</b>元，可赊账<b>${memberCredit.surplus}</b>元，不足以支付当前订单，请充值或还清欠款。</span>");
				$(this).prop("checked", false);
				$("#balance_pwd_div").hide();
				return;
			}
			</#if>
			
			usedBalanceHidden = balance;
			var sumPayPrice = 0.00;//计算后的订单金额
			//订单金额
			sumPayPrice = orderTotalPrice - balance - creditAmount;
			sumPayPrice = sumPayPrice.toFixed(2);
			if(sumPayPrice<=0){
				sumPayPrice = 0.00;
				usedBalanceHidden = orderTotalPrice;
			}
			$("#sumPayPriceId").html("￥"+sumPayPrice);
			$("#payPriceId").html("￥"+sumPayPrice);
			$("#sumPayPriceHidden").val(sumPayPrice);
			$("#usedBalanceHidden").val(usedBalanceHidden);
			
			
			if(orderTotalPrice <= balance){
				$("#balancePay").html(orderTotalPrice);
				//隐藏域赋值
				$("#balance").val(orderTotalPrice);
			}else{
				$("#balancePay").html(balance);
				//隐藏域赋值
				$("#balance").val(balance);
			}
			//金额计算显示
			$("#balancePriceListDiv").show();

			$("#isBalancePay").val('true');
		}else{
			returnBalance(orderTotalPrice,usedBalanceHidden,usedCreditHidden);
		}
	}
	
	$("#selectOrderBalance").click(fun_checkSalary);
	
	//不适用余额 返回金额
	function returnBalance(orderTotalPrice,usedBalanceHidden,usedCreditHidden){
	      <#if memberCredit?? && memberCredit.surplus &gt;0 >
			$(this).siblings("span").remove();
			$("#creditPriceListDiv").hide();
			</#if>
			
			// 不使用余额，把余额加回去
			orderTotalPrice = parseFloat(orderTotalPrice) + 
				parseFloat(usedBalanceHidden) + Number(usedCreditHidden);
			
			$("#balance_pwd_div").hide();
			$("#balancePriceListDiv").hide();
			//不选中 密码清空
			$("#balancePassword").val('');
			//隐藏域清空
			$("#balance").val('');
			$("#isBalancePay").val('false');
			$("#sumPayPriceId").html("￥"+orderTotalPrice);
			$("#payPriceId").html("￥"+orderTotalPrice);
			$("#sumPayPriceHidden").val(orderTotalPrice);
			$("#usedBalanceHidden").val(0);
	}
	
	//支付方式选中
	$(".payment-item").click(function(){
 		$(this).addClass("item-selected").parent().siblings().children().removeClass("item-selected");
		//赋值
	//	$("#paymentCode").val($(this).attr("value"));
	//	$("#paymentName").val($(this).attr("pname"));
		
	})
	//鼠标移入
	$('.online-payment').hover(function(){	
			$(this).addClass('payment-item-hover');
	},function(){
			$(this).removeClass('payment-item-hover');
	}); 

	// 新增地址
	$(".addaddress").click(function(){
		orderCenter();
		$("#Harvest").addClass("lay-display");
	});
	// 关闭收货信息层
	$(".harvest-close").click(function(){
		$("#Harvest").removeClass("lay-display");
	});

	// 居中
	function orderCenter(){
    	var v_top=($(window).height()-375)/2;
    	var v_left=($(window).width()-690)/2;
   		$(".internation").css({"left":v_left+"px","top":v_top+"px"});
	}
	// 设置弹出曾的高
	$(".background-layer").css("height",$(window).height());
  	// 修改发票窗口弹出
	$(".invoice-edit").click(function(){
		orderCenter();
		$("#mainId").addClass("lay-display");
	});
  	// 发票窗口退出
  	$(".cancel-invoice").click(function(){
		$("#mainId").removeClass("lay-display");
	});
  	// 点右上角关闭发票编辑窗口 
	$(".harvest-close-invoice").click(function(){
		$("#mainId").removeClass("lay-display");
	});
	// 发票内容点击 选中样式切换
	$("#InvoiceInformation li").click(function(){
		$(this).addClass("invoice-item-selected").siblings().removeClass("invoice-item-selected");
	});
  	function add_save(){
		$('#invoice-tit-list .invoice-item-selected').removeClass('invoice-item-selected');
		$('#save-invoice').show().addClass('invoice-item-selected').removeClass('hide').find('input').removeAttr('readonly').val('').focus();
		$('#invoice-tit-list').scrollTop($('#invoice-tit-list')[0].scrollHeight);
		$('#add-invoice').hide();
	}
	//鼠标移入移出（发票抬头）
	$(".invoice-item").mouseover(function(){
		$(this).addClass("hover");
	}).mouseout(function(){
		$(this).removeClass("hover");
	});
	
	//新增发票效果 
	$(".invoice-item").click(function(){
		$(this).addClass("invoice-item-selected").siblings().removeClass("invoice-item-selected");
		var _$save = $(this).attr('id');
		if(_$save){
			return;
		}else{
			var len = $('#invoice-tit-list').find('.invoice-item').length;
			if (len < 11) {			
				if($('#add-invoice').is(":hidden")){
					$('#save-invoice').hide();
					$('#add-invoice').show();
				}
			} else {
				$('#add-invoice').hide();
			}
		}
		//将其余组件的保存按钮隐藏，输入框只读
		$(this).siblings().find('.fore2 .itxt').attr('readonly','readonly');
		$(this).siblings().find(".edit-tit").removeClass('hide').next().addClass('hide');
	});
	
	//编辑发票
	$(".edit-tit").click(function(){
		$(this).addClass('hide').next().removeClass('hide');	
		$(this).parent().prev().find('.fore2 .itxt').removeAttr('readonly').focus();
	});

	// 收货人信息鼠标移入移出
 	$('#consignee-addr').delegate('li','mouseenter',function(){
    	$(this).addClass('li-hover');
    }).delegate('li','mouseleave',function(){
		$(this).removeClass('li-hover');
	});

	//显示更多地址
	function show_ConsigneeAll(){
		$("#consigneeItemAllClick").addClass("hide");
		$("#consigneeItemHideClick").removeClass("hide");
		$("#consignee1").removeClass("consignee-off");
		if($('#consignee-list li').length>4){
			$('#consignee-addr .consignee-cont').css({
			    'height':162,
			    'position':'relative',
			    'overflow-y': 'auto'
 					 });
		}else{
			 $('#consignee-addr .consignee-cont').css({
		      'height':'auto'
		    });
		    $('#consignee-addr ul').css({
		    'position':'relative'
		    });
		}
		$(".consignee-item").parents("li").css("display","list-item");
		//设置默认地址
		addressSelect();
	}
	
	// 点击收货地址 ，样式切换，并且赋值
	function addressSelect(){
		$(".consignee-item").click(function(){
			$(this).addClass("item-selected").parent().siblings().children().removeClass("item-selected");
			var obj = $(this).addClass("item-selected").parent();
			var oldAddressId = $("#addressId").val();
			var newAddressId = $(obj).val();
			if (oldAddressId != newAddressId) {
				// 如果旧的地址和新的地址不相等，则ajax调用方法计算新的运费
				$.ajax({
					type : "POST",
					url :  domain+"/order/calculateTransFee.html",
					data : {addressId:newAddressId},
					dataType : "json",
					success : function(data) {
						if(data.success){
							//$("#sumPayPriceId").html("￥"+data.data.finalAmount);
							//$("#payPriceId").html("￥"+data.data.finalAmount);
							var ppricetotal = $("#tzm_ppricetotal").val();
							$("#sumPayPriceHidden").val(data.data.finalAmount-ppricetotal);
							$("#sumPayPriceId").html(" ￥"+(data.data.finalAmount-ppricetotal));
							$("#payPriceId").html(" ￥"+(data.data.finalAmount-ppricetotal));
							$("#freightPriceId").children("font").html(" + ￥"+data.data.logisticsFeeAmount);
							
							// 重新选择地址后，由于金额变化，清除使用余额信息和使用积分信息
							// 清除使用余额
							$("#selectOrderBalance").prop("checked", false);
							$("#balance_pwd_div").hide();
							$("#balancePriceListDiv").hide();
							//不选中 密码清空
							$("#balancePassword").val('');
							//隐藏域清空
							$("#balance").val('');
							$("#isBalancePay").val('false');
							$("#usedBalanceHidden").val(0);
							
							// 清除使用积分
							$("#integralUse").prop("checked", false);
							$("#integralPriceListDiv").hide();
				  			$("#integralPay").html(0);
				  			$("#integral").val(0);
							$("#useBean").addClass("disabled");
							$("#jdBeanVal").val(0);
							$(".bean-exchange").html("￥"+ "0.00");
						}
					}
				});
			}
			
			//为隐藏域的收货地址ID赋值
			$("#addressId").val($(obj).val());
			
			//为结算按钮下的收货地址信息赋值
			$("#addressDetail").html($(this).siblings('.addr-detail').find(".addr-info").html());
			$("#consigneeName").html($(this).siblings('.addr-detail').find(".addr-name").html());
			$("#consigneeMobile").html($(this).siblings('.addr-detail').find(".addr-tel").html());
		});
	}
	
	function hide_ConsigneeAll() {
		//设置默认地址
		addressSelect();
		
		$("#consigneeItemAllClick").removeClass("hide");
		$("#consigneeItemHideClick").addClass("hide");
		$("#consignee1").addClass("consignee-off");
		$('#consignee-addr .addr-ctrl').hide();
		$('#consignee-addr .consignee-cont').css({
			'height':'40px',
			'overflow-y': 'hidden'
			});
		$('#consignee-addr ul').css({
		    'top': '0px',
		    'position':'absolute'
		});

		var li_selected = $(".consignee-item.item-selected").parent("li");//当前选中li
		var first_li = $(".consignee-item").parents("li").last();//当前列表第一项
		var _tempstr = first_li.find("div span").first().html();
		if(_tempstr && _tempstr.indexOf("默认地址") > -1) {
		    // 1.插入在默认地址之后
		    li_selected.clone().insertAfter(first_li);
		} else {
		    // 2.插入在地址列表第一位
		    li_selected.clone().insertBefore(first_li);
		}
		  li_selected.remove();
		  // 收起并定位第一页功能
		  $(".consignee-item").parents("li").css("display","none");
		  $(".consignee-item.item-selected").parent("li").css("display","list-item");
		  // 初始化地址组件的绑定事件，否则移动dom会导致绑定失效，因此改动组件采用delegate绑定
	}
  		
	//保存发票抬头
	function saveInvoce(obj){
		var content = $(obj).parent().siblings("input").val();
		if(content.length==0){
			jAlert("请输入内容");
			$(obj).parent().siblings("input").focus();
			return false;
		}else if(content.length<2){
			jAlert("请输入完整公司名称");
			$(obj).parent().siblings("input").focus();
			return false;
		}
 			//异步提交
		$.ajax({
		type : "POST",
		url :  domain+"/order/saveinvoice.html",
		data : {content:content},
		dataType : "json",
		success : function(data) {
			if(data.success){
				//先隐藏保存部分
    			$(obj).parent().parent().parent().hide();
    			$('#add-invoice').show();
    			//拷贝一个抬头对象，并进行操作
    			var firstObj = $(obj).parent().parent().parent().siblings(":first");
    			var newtitle = $(firstObj).clone(true);
    			$(newtitle).addClass("invoice-item-selected").siblings().removeClass("invoice-item-selected");
    			$(newtitle).find("input").val(content);
    			$(firstObj).after($(newtitle));
			}else {
				jAlert(data.message);
			}
		},
		error : function() {
			jAlert("数据加载失败！");
		}
		});
  	}
  		
 	//更新发票抬头
	function updateInvoice(obj,id){
		var content = $(obj).parent().siblings().find("input").val();
		if(content.length==0){
			jAlert("请输入内容");
			$(obj).parent().siblings("input").focus();
			return false;
		}else if(content.length<2){
			jAlert("请输入完整公司名称");
			$(obj).parent().siblings("input").focus();
			return false;
		}
 			
		$.ajax({
		type : "POST",
		url :  domain+"/order/updateinvoice.html",
		data : {content:content,id:id},
		dataType : "json",
		success : function(data) {
			if(data.success){
				//隐藏编辑按钮 输入框不可编辑
				$(obj).addClass('hide').prev().removeClass('hide');	
				$(obj).parent().siblings().find("input").attr("readonly","readonly")
			}else {
				jAlert(data.message);
			}
		},
		error : function() {
			jAlert("数据加载失败！");
		}
		});
  	}

  		//点击  "保存发票信息" 将选定的值赋给隐藏域，并展示
  		$(".save-invoice").click(function(){
  			//窗口关闭
  			$("#mainId").removeClass("lay-display");
  			//赋值
  			var title = $(".invoice-tit-list").find(".invoice-item.invoice-item-selected").find("input").val();
  			//发票内容
  			var content = $("#InvoiceInformation").find("li.invoice-item-selected").attr("val");
  			if(content=='noinvoice'){
  				$("#invoiceStatus").val('0');
  				$("#invoiceTitle").val('');
      			$("#invoiceType").val('');
      			$(".invoice_title").html("不开发票");
      			$(".invoice_title_show").html('');
      			$(".invoice_content_show").html('');
  			}else {
  				$("#invoiceTitle").val(title);
  				if (title == "个人") {
  					// 如果发票选择个人
  					$("#invoiceStatus").val('2');
  				} else {
  					$("#invoiceStatus").val('1');
  				}
      			$("#invoiceType").val(content);
      			$(".invoice_title").html("普通发票（纸质）");
      			$(".invoice_title_show").html(title);
      			$(".invoice_content_show").html(content);
  			}
  		});
  		
  		//删除发票
  		function delInvoice(obj,id){
  			if(confirm("确定要删除该发票信息吗？")){
   			$.ajax({
				type : "GET",
				url :  domain+"/order/deleteinvoice.html",
				data : {invoiceId:id},
				dataType : "json",
				success : function(data) {
					if(data.success){
						//移除该行发票内容
		    			$(obj).parent().parent('.invoice-item').remove();
					}else {
						jAlert(data.message);
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});
  			}
  		}
  		
  		function addStrs(name){
	  		var strs = "";
	  		var  nameDom = document.getElementsByName(name);
	  		for(var i =0;i<nameDom.length;i++){
	  		     if(i != (nameDom.length-1)){
			  		     strs = strs+nameDom[i].value+",";
	  		     }else{
	  		     		 strs = strs+nameDom[i].value;
	  		     }
	  		}
  		return strs;
  		}
  		
  		//提交订单 
  		function submitOrder(){
  		     var orderGoodsId = addStrs("orderGoodsId");
  		     var productSku = addStrs("productSku");
  		     var num = addStrs("num");
  			var actionUrl = domain + "/member/saveThreeSendOrders.html?orderGoodsId="+orderGoodsId+"&productSku="+productSku+"&num="+num;
  			var param = "";
  			//判断收货地址是否存在
  			if(isEmpty($("#addressId").val())){
  				jAlert("请添加或选择收货地址");
  				$(".ftx-05.addaddress").focus();
  				return false;
  			}
  			
  			var sumPayPriceValue = $("#sumPayPriceHidden").val();
  			 $("#moneyPaidReality").val(sumPayPriceValue);
  			
  			//判断是否使用余额支付  异步调用账户密码
  			var balance = $("#selectOrderBalance");
  			var balancePwd = $("#balancePassword").val();
  			$("#balancePwd").val(balancePwd);
      		if($(balance).prop("checked")){
      			if(isEmpty(balancePwd)){
      				jAlert("密码不能为空");
      				$("#balancePassword").focus();
      				return false;
      			}
      			//验证支付密码
      			var checkpwd = checkBalancePwd(balancePwd);
      			if(!checkpwd){
      				return false;
      			}
      		}
      		
      		
      		//提交订单按钮屏蔽，避免重复提交
      	   $("#order-submit").attr("disabled",true);
      		// 提交loading
  			$('body').append("<div id='submit_loading' class='purchase-loading'><div class='loading-cont'></div></div>");
  			param = $("#invoiceForm").serialize();
  			$.ajax({
				type : "POST",
				dataType : "json",
				url : actionUrl,
				data : param,
				async:false,
				success : function(data) {
					if (data.success) {
						var data = data.data;
						var paySessionstr = data.paySessionstr;
						var goJumpPayfor = data.goJumpPayfor;
						var relationOrderSn = data.relationOrderSn;
						var payAmount = data.payAmount;
						
						 //跳转到成功页面
						 if (goJumpPayfor) {
							successUrl = domain+"/order/pay.html";
							newurl = successUrl + "?relationOrderSn=" + relationOrderSn +
									"&paySessionstr="+paySessionstr+"&rid=" + Math.random()+"&payType=threesend";
							window.setTimeout('window.location.href=newurl;', 450);
							return;
						} else {
							successUrl = domain+"/order/success.html";
							window.location.href = successUrl+"?relationOrderSn="+relationOrderSn+"&rd="+Math.random();
							return;
						}
		
					} else {
						// 更新token值
						$("input[name='CSRFToken']").val(result.csrfToken);
						$("#order-submit").removeAttr("disabled");
						if (result.message != null) {
							$("#submit_loading").remove();
							jAlert(result.message);
							return;
						} else {
							$("#submit_loading").remove();
							showSubmitErrorMessage("系统出错了~~~, 请稍后重试...");
							return;
						}
					}
				},
				error : function(error) {
					$("#order-submit").removeAttr("disabled");
					$("#submit_loading").remove();
					jAlert("亲爱的用户请不要频繁点击, 请稍后重试...");
				}
  			});
  		}
  		
  	//验证支付密码
  	function checkBalancePwd(balancePwd){
		var correct = false;
		$.ajax({
			type : "GET",
			url :  domain+"/order/checkbalancepwd.html",
			data : {balancePwd:balancePwd},
			dataType : "json",
			async:false,
			success : function(data) {
				if(data.success){
					correct = data.data.correct;
					var errcount = parseInt(data.data.pwdErrCount);
				   	if(errcount>=6){
				   		jAlert("支付密码输错超过6次,请用其他方式支付");
						// $(".toggle-title").click();
						return false;
				   	}
					if(!correct){
						jAlert("支付密码不正确，您最多还可以输入"+(6-errcount)+"次");
						return false;
					}
				}else {
					jAlert(data.message);
					return false;
				}
			},
			error : function() {
				jAlert("验证密码失败！");
			}
		});
  		return correct;
  	}
  	
  	var usedJdBeanCount = 0;
  	var _max = ${(member.integral - ((member.integral)%(config.integralScale)))!0};
  	var exchangeRate = ${config.integralScale!0};
  	function beanExchange(){
  		var operate = $(this).text();
  		if($("#integralUse").is(":checked")){
  			var sumPayPrice = $("#sumPayPriceHidden").val();
  			var jdBeanVal = $("#jdBeanVal").val();
  			if("+" == operate && jdBeanVal < _max){
				// 如果积分使用后应付金额小于0则不能使用积分
				if (parseFloat(sumPayPrice) < 1) {
					return;
				}
  				$("#jdBeanVal").val(parseInt(jdBeanVal)+exchangeRate);
  				if(($("#jdBeanVal").val()) == (_max)){
  					$(this).addClass("disabled");
  				}
  				$("#reduction").removeClass("disabled");
  				beanUse();
  				
  				sumPayPrice = parseFloat(sumPayPrice) - 1;
  			}else if("-" == operate && jdBeanVal > 0){
  				$("#jdBeanVal").val(parseInt(jdBeanVal)-exchangeRate);
  				if(($("#jdBeanVal").val()) == 0){
  					$(this).addClass("disabled");
  				}
  				$("#plus").removeClass("disabled");
  				beanUse();
  				
  				sumPayPrice = parseFloat(sumPayPrice) + 1;
  			}else{
  				$(this).addClass("disabled");
  			}
  			$("#integralPriceListDiv").show();
  			$("#integralPay").html(($("#jdBeanVal").val())/exchangeRate);
  			
  			sumPayPrice = sumPayPrice.toFixed(2);
  			
  			$("#sumPayPriceId").html("￥"+sumPayPrice);
			$("#payPriceId").html("￥"+sumPayPrice);
			$("#sumPayPriceHidden").val(sumPayPrice);
  		} else {
  			$("#integralPriceListDiv").hide();
  			$("#integralPay").html(0);
  			$("#integral").val(0);
  			
  			var sumPayPrice = $("#sumPayPriceHidden").val();
  			sumPayPrice = sumPayPrice - $("#jdBeanVal").val();
  			$("#sumPayPriceId").html("￥"+sumPayPrice);
			$("#payPriceId").html("￥"+sumPayPrice);
			$("#sumPayPriceHidden").val(sumPayPrice);
  		}
  	}

  	function beanUse(){
  		var beanCount = $("#jdBeanVal").val();
  		$(".bean-exchange").html("￥"+ (beanCount/exchangeRate).toFixed(2));
  		//useCancelEditJdBean(beanCount,exchangeRate,beanCount>0?false:true);
  		$("#integral").val(beanCount);
  	}
  	
  	$(function(){
  	    $('#threeStore').click(function(){
  	   		$("#tiantian1").hide();
  			$("#tiantian2").hide();
  	    });
  	    $('#takeBySelf').click(function(){
  	    	$("#tiantian1").hide();
  			$("#tiantian2").hide();
  	    });
  	     $('#sendNow').click(function(){
  	    	$("#tiantian1").show();
  			$("#tiantian2").show();
  	    });
  	});
  	
  	$(function(){
		// 把选中的地址显示出来
		$(".consignee-item").parents("li").css("display","none");
		$(".consignee-item.item-selected").parent("li").css("display","list-item");
		$("#addressId").val("${(defaultAddress.id)!''}")
		$("#sumPayPriceHidden").val(0);
		$("#usedBalanceHidden").val(0);
		// 积分tips
	  	// $(".beans-cho-tip").tips({
	   //  	trigger: '.qmark-tip',
	   //  	tipsClass: 'qmarkTip',
	   //  	mouseenterDelayTime: 200,
	   //  	autoResize:false,
	   //  	hasClose: false
	  	// });
		if(usedJdBeanCount > 0){
			$("#integralUse").attr("checked",true);
			$("#jdBeanVal").val(usedJdBeanCount);
			$(".bean-exchange").html("￥"+ (usedJdBeanCount/exchangeRate).toFixed(2));
			$("#useBean").removeClass("disabled");	
			if(usedJdBeanCount == _max && usedJdBeanCount > 0){
				$("#plus").addClass("disabled");
			}else if(_max > 0 && usedJdBeanCount == 0){
				$("#reduction").addClass("disabled");
			}
		} else {
			$("#integralUse").attr("checked",false);
			$("#jdBeanVal").val(0);
		}
		if (${(member.integral)!0} < exchangeRate) {
			$("#integralUse").attr("checked",false);
			$("#integralUse").attr("disabled",true);
		}
		
		$("#integralUse").click(function(){
			if (${(member.integral)!0} < exchangeRate) {
				$("#integralUse").attr("checked",false);
				$("#integralUse").attr("disabled",true);
				return;
			}
		    var sumPayPrice = $("#sumPayPriceHidden").val();
		    if (parseFloat(sumPayPrice) < 1) {
					$("#integralUse").attr("checked",false);
					return;
				}
			if($(this).is(":checked")){
				var takePrice = $("#takePrice").val();
				// 如果积分使用后应付金额小于0则不能使用积分
				if (parseFloat(payPrice) < 1) {
					$("#integralUse").attr("checked",false);
					return;
				}
				
				$("#useBean").removeClass("disabled");
				$("#jdBeanVal").val(exchangeRate);
				$(".bean-exchange").html("￥"+ (($("#jdBeanVal").val())/exchangeRate).toFixed(2));
				
				$("#integralPriceListDiv").show();
	  			$("#integralPay").html(($("#jdBeanVal").val())/exchangeRate);
	  			
	  			sumPayPrice = parseFloat(sumPayPrice) - 1;
	  			$("#sumPayPriceId").html("￥"+sumPayPrice);
				$("#payPriceId").html("￥"+sumPayPrice);
				$("#sumPayPriceHidden").val(sumPayPrice);
			}else{
				
				var integralVal = $("#jdBeanVal").val();
				var sumPayPrice = $("#sumPayPriceHidden").val();
	  			sumPayPrice = parseFloat(sumPayPrice) + (integralVal/exchangeRate);
	  			
	  			$("#sumPayPriceId").html("￥"+sumPayPrice);
				$("#payPriceId").html("￥"+sumPayPrice);
				$("#sumPayPriceHidden").val(sumPayPrice);
				
				$("#integralPriceListDiv").hide();
	  			$("#integralPay").html(0);
	  			$("#integral").val(0);
				
				$("#useBean").addClass("disabled");
				$("#jdBeanVal").val(0);
				$(".bean-exchange").html("￥"+ "0.00");
			}
			var beanCount = $("#jdBeanVal").val();
			$("#integral").val(beanCount);
			//useCancelEditJdBean(beanCount,exchangeRate,beanCount>0?false:true);
		});
		$("#plus").click(beanExchange);
		$("#reduction").click(beanExchange);
	});
  	
  	//验证本次发货数量 整箱只能一次发货整箱的数量的倍数
  	function checkNum(){
  	     //本次发货数量
  	     var numDom  = document.getElementsByName("num");
  	     //总数量
  	     var numberDom  = document.getElementsByName("number");
  	     //已发货数量
  	     var deliveredNumDom  = document.getElementsByName("deliveredNum");
  	     //商品id 
  	     var productIdDom = document.getElementsByName("productId_name");
  	     //整箱基础数量
  	     var fullContainerQuantityDom = document.getElementsByName("fullContainerQuantity");
  	     
  	     for(var i=0;i<numDom.length;i++){
  	         //剩余数量
  	         var least = numberDom[i].value-deliveredNumDom[i].value;
  	         if(least < numDom[i].value){
  	             jAlert("本次发货数量已超过剩余可发货的数量");
  	             numDom[i].value = 0;
  	             //numDom[i].focus();
  	             return false;
  	         }else{
  	         //总数量%整箱基本数量=0 说明是整箱 只能每次发一箱
  	              if(fullContainerQuantityDom[i].value != 0){
  	                   var flag = numberDom[i].value%fullContainerQuantityDom[i].value;
	  	               var numflag = numDom[i].value%fullContainerQuantityDom[i].value;
	  	               if(flag == 0){
	  	                 if(numflag != 0){
		  	                 jAlert("改订单为整箱订单，发货数量只能输入"+fullContainerQuantityDom[i].value+"的倍数");
		  	  	             numDom[i].value = 0;
		  	                 //numDom[i].focus();
		  	                 return false;
	  	                 }
	  	             }
  	             }
  	         }
  	     }
  	     return true;
  	}
  	
  	function ajaxCaculate(val){
  	
  	//如果是上门自提 不计算运费
  	var flag = $("#smzt_status").val();
  	if(flag!=1){
	  	// 验证本次发货数量
	  	    if(!checkNum()){
	  	      return;
	  	    }
	  		//商品ID  productId_name    商品数量 number_name
	  		//当前选中收货地址id
	  		var addressId = $("#addressId").val();
	  		var orderSn = $("#orderSn").val();
	  		//不同商品对应发货 数量
	  		var nums = $("input[name='num']");
	  		var val="";
	  		var productIds = $("input[name='productId_name']");
	  		var same_numtotal=0;
	  		var numstrs = "";
	  		var productIds2 = "";
	  		var tzm_totalnumbers = 0;
	  		for(var i=0;i<productIds.length;i++){
	  				var flag = productIds.length-1;
	  				var bool = ((i+1)<=flag && productIds[i].value == productIds[i+1].value) ;
	  				if(bool){
	  					same_numtotal = parseInt(nums[i].value) + parseInt(nums[i+1].value);
	  					numstrs += same_numtotal + ",";
	  					productIds2 += productIds[i].value + ",";
	  					i = i+1;
	  				}else{
	  					numstrs += nums[i].value + ",";
	  					productIds2 += productIds[i].value + ",";
	  				}
	  		}
	  		for(var i=0;i<productIds.length;i++){
	  				tzm_totalnumbers += parseInt(nums[i].value);
	  		}
	  		numstrs = numstrs.substring(0,numstrs.lastIndexOf(","));
	  		productIds2 = productIds2.substring(0,productIds2.lastIndexOf(","));
	  		// 根据发货数量和地址 异步计算运费
	  		$("#sumPayPriceHidden").val(0);
	  		var transportType = $("input[name='transportType']:checked").val();
	  		$.ajax({
	  			type : "GET",
				url :  domain+"/member/getFee.html",
				data : {
					productIds:productIds2,
					numstrs:numstrs,
					transportType:transportType,
					memberAddressId:addressId
				}, 
				dataType : "json",
				success : function(data) {
					if(data.success){
					//保存 当前运费总额
	  				$("#takePrice").val(data.data);
					//tzm_totalnumber 动态渲染 送、多少件 商品数量
					$("#tzm_totalnumber").html(tzm_totalnumbers);
					 	//跳转到添加进货单成功页面
					 	//运费  动态渲染
					 	$("#freightPriceId").children("font").html(" + ￥"+data.data);
					 	//应付总额 动态渲染
					 	var sumPayPrice = $("#sumPayPriceHidden").val();
					 	var ppricetotal = $("#tzm_ppricetotal").val();
						$("#sumPayPriceHidden").val(sumPayPrice-ppricetotal+data.data);
						$("#sumPayPriceId").html(" ￥"+(sumPayPrice-ppricetotal+data.data));
						$("#payPriceId").html(" ￥"+(sumPayPrice-ppricetotal+data.data));
					}else{
						jAlert(data.message);
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
	  		});
  		}
  	    
  	}
  //运送方式 动态计算
  	$("input[name='transportType']").click(function(){
  		var transportType = this.value;
  		//如果是上门自提 不计算运费
  	  	var flag = $("#smzt_status").val();
  		
  		
  		
  	// 根据发货数量和地址 异步计算运费
  		$("#sumPayPriceHidden").val(0);
  		$.ajax({
  			type : "GET",
			url :  domain+"/member/getFee.html",
			data : {
				productIds:productIds2,
				numstrs:numstrs,
				transportType:transportType,
				memberAddressId:addressId
			}, 
			dataType : "json",
			success : function(data) {
				if(data.success){
				//保存 当前运费总额
  				$("#takePrice").val(data.data);
				//tzm_totalnumber 动态渲染 送、多少件 商品数量
				$("#tzm_totalnumber").html(tzm_totalnumbers);
				 	//跳转到添加进货单成功页面
				 	//运费  动态渲染
				 	$("#freightPriceId").children("font").html(" + ￥"+data.data);
				 	//应付总额 动态渲染
				 	var sumPayPrice = $("#sumPayPriceHidden").val();
				 	var ppricetotal = $("#tzm_ppricetotal").val();
					$("#sumPayPriceHidden").val(sumPayPrice-ppricetotal+data.data);
					$("#sumPayPriceId").html(" ￥"+(sumPayPrice-ppricetotal+data.data));
					$("#payPriceId").html(" ￥"+(sumPayPrice-ppricetotal+data.data));
				}else{
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("数据加载失败！");
			}
  		});
  		
  	})
</script>