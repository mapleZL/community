<#include "/h5v1/commons/_head.ftl" />
<#assign cartNum = 0>
<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
<style>
.tabBox .hd{ overflow:hidden;}
.tabBox .hd a{ background:#f0f2f5; display:inline-block; border-radius:5px; padding:5px 0; width:30%; text-align:center; margin-right:3%; float:left; margin-top:0.8rem; border:1px solid #f0f2f5; color:#333}
.tabBox .hd li.on a{color:#ff7e18; border:1px solid #ff7e18; background:#fff}
.tempWrap{}
.am-tabs-nav li{ float:left;}
.am-tabs-bd .am-tab-panel{padding:0}
.am-nav-tabs>li.am-active>a, .am-nav-tabs>li.am-active>a:focus, .am-nav-tabs>li.am-active>a:hover{color:#ff7e18; border:1px solid #ff7e18; background:#fff; border-radius:5px}
</style>
<body>
<!--头部-->
<header data-am-widget="header" class="header am-header am-header-default tp2">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">确认订单</h1>
</header>
<div class="divcons"></div>
<!--头部end-->
<!--内容-->
<div class="clear"></div>
<article>
<!--开始-->
<@form.form action="" id="orderForm" name="orderForm" method="post">
<!-- 记录该订单是否存在二次加工，如果存在则要特殊处理 -->
<input type="hidden" id="tzm_twoJG" name="twoJGFlag" value="0"/>
<!-- 用户真实姓名 -->
<input type="hidden" id="realName" value="${(member.realName)!''}"/>
<div class="am-tabs" data-am-tabs="{noSwipe: 1}" id="doc-tab-demo-1">
  <ul class="am-tabs-nav am-nav am-nav-tabs hd ico mt10 public transportArea">
  		<li class="am-active" style="display:none"><a data-cartid=""></a></li>
        <li><a data-cartid="8">物流中转</a></li>
        <li><a data-cartid="5">送货上门</a></li>
        <li><a data-cartid="0">快递配送</a></li>
        <li><a data-cartid="6">千禧路自提</a></li>
        <li><a data-cartid="7">大唐自提</a></li>
  </ul>
  <div class="am-tabs-bd">
  	<div class="am-tab-panel" style="display:none">
  	</div>
    <div class="am-tab-panel am-active">
      	<div class="public ico mt10">
            <a class="em2 detail drop f16">中转说明</a>
            <div class="hide">
                <p>1：大袜网为您免费提供“到大唐物流中转”服务，从大唐到您拍下地址的物流费用到付，温馨提示，物流速度会相对慢，请耐心等待。</p>
                <p>2：请在具体收货地址后注明物流中转地点，示例：袍江路1号小麦（浙江）网络科技有限公司（中转地点：大唐镇山下朱一号）</p>
                <p>3：请您根据物流中转服务要求设置收货地址，如因中转地点错误导致货品不能送达，所造成的损失自行负责。</p>
            </div>
        </div>
        <div class="ico mt10 public addr">
            <a href="javascript:void(0);" class="addr_a address_choose">
                <p class="em2">
	                <em>${(address.memberName)!""}</em>
	                <em>${(commUtil.hideMiddleStr(address.mobile,3,4))!""}</em>
                </p>
                <p>收货地址：&nbsp; ${(address.addAll)!""}&nbsp;${(address.addressInfo)!""}
        			大袜网
        		</p>
            </a>
        </div>
    </div>
    <input type="hidden" id="addressId" name="addressId" value="${(address.id)!0}">
    <input type="hidden" value="${(address.addAll)!''}" id="tzm_addressAll"/>
    <div class="am-tab-panel">
      	<div class="public ico mt10">
            <a class="em2 detail drop f16">送货说明</a>
            <div class="hide">
                <div class="tp2">
                    <p>1：为回馈广大客户，符合以下送货范围及条件的，送货服务费全免：</p>
                    <table cellspacing="0" class="table1">
                        <tr>
                            <td class="td1">送货区域</td>
                            <td class="td2">送货范围</td>
                            <td class="td3">单次送货数量</td>
                        </tr>
                    </table>
                     <table cellspacing="0" class="table1">
                        <tr>
                            <td class="td1">一区</td>
                            <td class="td2">暨 阳、陶朱、浣东街道、大唐、草塔</td>
                            <td class="td3">≥1000双</td>
                        </tr>
                        <tr>
                            <td class="td1">二区</td>
                            <td class="td2">枫桥、王家井、牌头、五泄</td>
                            <td class="td3">≥5000双</td>
                        </tr>
                        <tr>
                            <td class="td1">三区/四区</td>
                            <td class="td2">义乌、浦江、上述地区之外诸暨市内其它地区</td>
                            <td class="td3">≥20000双</td>
                        </tr>
                    </table>
                </div>
                <div class="tp2">
                    <p>2：单次送货未达以上数量，请在收货时当面结清送货费用，计费标准如下：</p>
                    <table cellspacing="0" class="table2">
                        <tr>
                            <td class="td1">送货区域</td>
                            <td class="td2">送货范围</td>
                            <td class="td3">配送费</td>
                            <td class="td4">最低收费标准</td>
                        </tr>
                    </table>
                     <table cellspacing="0" class="table2">
						<tr>
							<td class="td1">一区</td>
							<td class="td2">诸暨三个街道、大唐、草塔</td>
							<td class="td3">￥4.00/箱</td>
							<td class="td4">￥20</td>
						</tr>
						<tr>
							<td class="td1">二区</td>
							<td class="td2">枫桥、王家井、牌头、五泄</td>
							<td class="td3">￥4.50/箱</td>
							<td class="td4">￥180</td>
						</tr>
						<tr>
							<td class="td1">三区</td>
							<td class="td2">陈宅、岭北、义乌、浦江</td>
							<td class="td3">￥5.50/箱</td>
							<td class="td4">￥330</td>
						</tr>
						<tr>
							<td class="td1">四区</td>
							<td class="td2">上述地区外诸暨市内其他地区</td>
							<td class="td3">￥5.00/箱</td>
							<td class="td4">￥250</td>
						</tr>
                    </table>
                </div>
                <div class="tp2">
                    <p>3：请您根据以上配送区域设置收货地址，如因收货地址错误导致货品不能送达，所造成的损失由货主自行承担。</p>
                </div>
            </div>
        </div>
        
        <div class="public ico mt10">
		    <a class="em2 drop f16 addr_a ownsendareadiv" data-am-modal="{target: '#my-actions'}">选择配送地区</ a>
		</div>
        
        <div class="ico mt10 public addr">
            <a href="javascript:void(0);" class="addr_a address_choose">
        		<p class="em2">
            		<em>${(address.memberName)!""}</em>
            		<em>${(commUtil.hideMiddleStr(address.mobile,3,4))!""}</em>
        		</p>
        		<p>收货地址：&nbsp; ${(address.addAll)!""}&nbsp;${(address.addressInfo)!""}
					大袜网
				</p>
    		</a>
        </div>
    </div>
    <div class="am-tab-panel">
     	<div class="public ico mt10">
            <h3 class="em2 f16">运送方式</h3>
            <div class="overflow select_way express">
                <a data-express="1">中通快递</a>
                <a data-express="3">顺丰快递</a>
                <#--
                	<a data-express="2">申通快递</a>
                -->
            </div>
        </div>
        
        <div class="ico mt10 public addr">
            <a href="javascript:void(0);" class="addr_a address_choose">
                <p class="em2">
	                <em>${(address.memberName)!""}</em>
	                <em>${(commUtil.hideMiddleStr(address.mobile,3,4))!""}</em>
                </p>
                <p>收货地址：&nbsp; ${(address.addAll)!""}&nbsp;${(address.addressInfo)!""}
        			大袜网
        		</p>
            </a>
        </div>
    </div>
    <div class="am-tab-panel">
    	<div class="public ico mt10">
            <a class="em2 detail drop f16">自提点</a>
        </div>
         <div class="hide" style="margin-top:-20px">
                <div class="ico public">
                    <p>自提点:浙江省诸暨市千禧路20号大袜云仓北门</p>
                    <p>联系人:张建涛</p>
                    <p>联系电话: 0575-87599013   15215936158</p>
                </div>
                <div class="ico public mt10 tp2">
                    <h3 class="em2 f16">仓储出库时间</h3>
                </div>
                <div class="ico public">
                    <h4 class="em2">裸袜：</h4>
                    <p>当日15：00前接单，次日10点前出库</p>
                    <p>当日15：00后接单，次日17点前出库</p>
                </div>
                <div class="ico public">
                    <h4 class="em2">二次加工：</h4>
                    <p>8000双以内，24小时内出库</p>
                    <p>8000-15000双，36小时内出库</p>
                    <p>15000双以上，72小时内出库</p>
                </div>
                <div class="ico public mt10">
                    <h3 class="em2 f16">自提提示</h3>
                 
                    <p>1：请记录订单编号和账号，向联系人出示。</p>
                    <p>2：请在联系人陪同下进入园区，出区时请主动向门卫出示提货手续。</p>
                </div>
         </div>
    </div>
    <div class="am-tab-panel">
    	 <div class="public ico mt10">
            <a class="em2 detail drop f16">自提点</a>
        </div>
         <div class="hide" style="margin-top:-20px">
            <div class="ico public">
                <p>自提点:袜业市场二楼西八街2412号-2415号</p>
                <p>联系人:孟丹</p>
                <p>联系电话: 0575-89000305   13706856474</p>
            </div>
            <div class="ico public mt10 tp2">
                <h3 class="em2 f16">仓储出库时间</h3>
            </div>
            <div class="ico public">
                <h4 class="em2">裸袜：</h4>
                <p>当日15：00前接单，次日10点前出库</p>
                <p>当日15：00后接单，次日17点前出库</p>
            </div>
            <div class="ico public">
                <h4 class="em2">二次加工：：</h4>
                <p>8000双以内，24小时内出库</p>
                <p>8000-15000双，36小时内出库</p>
                <p>15000双以上，72小时内出库</p>
            </div>
            <div class="ico public mt10">
                <h3 class="em2 f16">自提提示</h3>
             
                <p>1：请记录订单编号和账号，向联系人出示。</p>
                <p>2：请在联系人陪同下进入园区，出区时请主动向门卫出示提货手续。</p>
        </div>
    </div>
  </div>
</div>

<!--end-->


<div class="public ico mt10">
	<h3 class="em2 f16">支付方式</h3>
    <div class="overflow select_way pay_way">
    	<a data-id="1" class="cur">在线支付</a>
        <a data-id="2">线下打款</a>
        <#--<a data-id="3">余额支付</a>-->
    </div>
</div>
<div class="public ico mt10">
	<h3 class="em2 f16">订单备注</h3>
    <div class="overflow select_way need_bill">
    	<a data-id="1">要发货单</a>
        <a data-id="2" class="cur">不要发货单</a>
    </div>
</div>
<div class="public ico mt10 public">
	<h3 class="em2 f16">商品清单</h3>
	<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
	<#list cartInfoVO.cartListVOs as cartListVO>
	<#assign seller = cartListVO.seller />
	<#if cartListVO.actFull?? >
                <div class="cart-info">
					<div class="icon-img">
						<span class="reduction-box">
							<span class="reduction">满减</span>
							<em></em>
						</span>
						<p class="enjoy-box">
							<span class="enjoy-preferential">
							<#if cartListVO.actFull.firstFull?? && cartListVO.actFull.firstFull &gt; 0>
	                   		&nbsp;满${(cartListVO.actFull.firstFull)?string('0.00')!"0.00"}-${(cartListVO.actFull.firstDiscount)?string('0.00')!"0.00"}
	                   		</#if>
	                   		<#if cartListVO.actFull.secondFull?? && cartListVO.actFull.secondFull &gt; 0>
	                   		&nbsp;满${(cartListVO.actFull.secondFull)?string('0.00')!"0.00"}-${(cartListVO.actFull.secondDiscount)?string('0.00')!"0.00"}
	                   		</#if>
	                   		<#if cartListVO.actFull.thirdFull?? && cartListVO.actFull.thirdFull &gt; 0>
	                   		&nbsp;满${(cartListVO.actFull.thirdFull)?string('0.00')!"0.00"}-${(cartListVO.actFull.thirdDiscount)?string('0.00')!"0.00"}
	                   		</#if>
	                   		</span>
						</p>
					</div>
                </div>
   </#if>
   </div> 
   <#if (cartListVO.cartList??) && (cartListVO.cartList?size>0) >
       <#list cartListVO.cartList as cart>
       <div class="public ico mt10 public">
            <div data-am-widget="intro" class="am-intro am-cf am-intro-default default">
         	<div class="am-g am-intro-bd">
            <div class="am-intro-left am-u-sm-4" style="padding-left:0">
            <#assign product = cart.product />
            <#assign productGoods = cart.productGoods />
            <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(cart.productId)!0}.html?goodId=${(cart.productGoodsId)}">
            	<#if product.isNorm == 2>
					<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${productGoods.images!''}">
				<#else>
					<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.masterImg!''}">
				</#if>
            </a>
            </div>
            <div class="am-intro-right am-u-sm-8">
            	<a href="javascript:void(0)">
            		<#if cart.actSingle?? && cart.actSingle.type??>
								<#if cart.actSingle.type == 1>
									<!-- 减免固定金额 -->
									<#--
									￥<font>${(productGoods.mallMobilePrice-cart.actSingle.discount)?string("0.00")!"0.00"}</font>-->
								<#elseif cart.actSingle.type == 2>
									<!-- 折扣 -->
									<#--
									￥<font>${(productGoods.mallMobilePrice * cart.actSingle.discount)?string("0.00")!"0.00"}</font>-->
								</#if>
					<#else>
								<#--
									￥<font>${productGoods.mallMobilePrice?string("0.00")!"0.00"}</font>
									<#if cart.isDabiaowa?? && cart.isDabiaowa==1>
										￥<font>${cart.tempdabiaoPriceOnly?string("0.00")!"0.00"}
									<#else>
										￥<font>${product.mallPcPrice?string("0.00")!"0.00"}
										<#if product.priceStatus??&&product.priceStatus==3>
											-￥<font>${product.scatteredPrice?string("0.00")!"0.00"}
										</#if>
									</#if>
								-->
					</#if>
                    <h3 class="title1 em2">${product.name1!''}</h3>
                    <p class="f12 em3">${cart.specInfo!''}</p>
                    <p class="f12 em3">加工方式：
                    			<#if cart.isDabiaowa?? && cart.isDabiaowa==1>
										打标袜
								<#elseif cart.packageFee?? && cart.packageFee !=0>
										二次加工
								<#else>
										裸袜
								</#if>
										; 加工费：
								<#if cart.packageFee??>
										${(cart.packageFee)?string("0.00")!'0.00'} 
								<#else>
										${(cart.packageFee)!'0.00'}
								</#if>
								<#if cart.isDabiaowa?? && cart.isDabiaowa==1> <!-- 如果是打标袜则不要将其加工费用设置为0 -->
								<#else>								
								<input type="hidden" name="tzm_ttfee" value="${(cart.packageFee)!'0.0'}"/>
								</#if>
					</p> 
                </a>
                <#assign cartNum = cartNum + cart.count>
                <div class="overflow">
                	<p class="f12 em3 fl">${productGoods.normName!''}</p>
                    <em class="price2 fr">小计：¥ ${(cart.currDiscountedAmount)?string('0.00')!"0.00"}</em>
                </div>
            </div>
         </div>
     </div>
     </div>
     </#list>
     </#if>
	 </#list>
	 </#if>
</div>
</article>


<!--内容end-->    

<div class="clear"></div>
<#if couponUser?? && couponUser?size &gt; 0>
<div class="public ico mt10">
	<a class="em2 f16 redpapper addr_a red_show" data-am-modal="{target: '#my-redlist'}">使用红包<img src=/resources/h5v1/img/cash_coupon.png></a> 
</div>
</#if>
<article>

	<ul class="ico public mt10 tp2 fee">
	
    	<li class="overflow">
        	<em class="em0 fl">应付金额</em>
            <em class="em4 fr" id="shouldPay">¥${(cartInfoVO.checkedDiscountedCartAmount)?string('0.00')!'0.00'}</em>
        </li>
        <li class="overflow">
        	<em class="em0 fl">商品金额（原价）</em>
            <em class="em4 fr">¥${(cartInfoVO.checkedCartAmount)?string('0.00')!'0.00'}</em>
            <input type="hidden" value="${(cartInfoVO.checkedCartAmount)?string('0.00')!'0.00'}" id="totalPrice_ID"/>
        </li><li class="overflow">
        	<em class="em0 fl">活动节省</em>
            <em class="em4 fr">-  ¥${(cartInfoVO.checkedDiscountedAmount)?string('0.00')!'0.00'}</em>
        </li><li class="overflow">
        	<em class="em0 fl">红包节省</em>
            <em class="em4 fr" id="couponReduce">-  ¥0.00</em>
        </li>
        <li class="overflow">
        	<em class="em0 fl">运费</em>
            <em class="em4 fr" id="shipfee">+  ¥${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'}</em>
        </li>
        <li class="overflow">
        	<em class="em0 fl">加工费</em>
            <em class="em4 fr">+  ¥${(cartInfoVO.servicePrice)?string("0.00")!'0.00'}</em>
        </li><li class="overflow">
        	<em class="em0 fl">辅料费</em>
            <em class="em4 fr">+  ¥${(cartInfoVO.labelPrice)?string("0.00")!'0.00'}</em>
        </li>
    </ul>
    <div class="ico public overflow">
    	<p class="fr">
    		<em>共${cartNum}件商品</em>&nbsp;&nbsp;&nbsp;&nbsp;
    		<em>合计<i class="em4" id="heji">¥${cartInfoVO.finalAmount?string('0.00')!'0.00'}</i></em>
    	</p>
    </div>
</article>

<div class="am-modal-actions" id="my-actions">
  <div class="am-modal-actions-group">
    <ul class="am-list" id="ownerSendArea">
      <li class="am-modal-actions-header">配送地区</li>
      <li><a href="#" data-area="2"><span></span>暨阳街道、陶朱街道、浣东大道、大唐、草塔</a></li>
      <li><a href="#" data-area="3"><span></span>枫桥、王家井、牌头、五泄</a></li>
      <li><a href="#" data-area="4"><span></span>陈宅、岭北、义乌、浦江</a></li>
      <li><a href="#" data-area="5"><span></span>上述地区之外诸暨市内其他地区</a></li>
    </ul>
  </div>
  <div class="am-modal-actions-group">
    <button class="am-btn am-btn-warning am-btn-block" data-am-modal-close>取消</button>
  </div>
</div>


<div class="am-modal-actions" id="my-redlist">
  <div class="am-modal-actions-group">
    <ul class="am-list" id="re-list">
      	<li class="am-modal-actions-header">请选择红包</li>
		<#if couponUser?? && couponUser?size &gt; 0>
	  		<#list couponUser as couponUser>
	  			<li><a href="#" data-red="${couponUser.couponSn}"><span></span>满${couponUser.minAmount}元抵${couponUser.couponValue}元现金</a></li>
	  		</#list>
		</#if>
    </ul>
</div>
  <div class="am-modal-actions-group">
    <button class="am-btn am-btn-warning am-btn-block" data-am-modal-close>关闭</button>
  </div>
</div>

<div>
	<#--页面隐藏值-->
	<!-- 支付方式名称 -->
	<input type="hidden" id='paymentName' name="paymentName" value="${(orderCommitVO.paymentName)!''}"/>
	<!-- 支付方式code -->
	<input type="hidden" id='paymentCode' name="paymentCode" value="${(orderCommitVO.paymentCode)!''}"/>
	<!-- 订单备注 -->
	<input type="hidden" id='remark' name="remark" value="需要发货单"/>
	<!--物流或者是快递种类-->
	<input type="hidden" id="transId" name="transId" value="0"/>
	<!--送货上门区域-->
	<input type="hidden" id="transArea" name="transArea" value="0"/>
	<!--红包-->
	<input type="hidden" id="redId" name="redId" value="0"/>
	<!--订单类型-->
	<input type="hidden" id='ordersTypeName' name="ordersType" value="1"/>
	<!--订单总金额-->
	<input type="hidden" id='totalCartAmount' name='totalCartAmount' value="${(cartInfoVO.totalCheckedNumber)}">
	<!--服务费总额-->
	<input type="hidden" id="servicePrice" id="servicePrice" value="${(cartInfoVO.servicePrice)?string("0.00")!'0.00'}">
	<!--合计-->
	<input type="hidden" id="finalAmount" id="finalAmount" value="${cartInfoVO.finalAmount?string('0.00')!'0.00'}">
	<!--运费-->
    <input type="hidden" id="logisticsFeeAmount" value="${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'}"/>
    <input type="hidden" id="currType" value="1" autocomplete="off">
    <input type="hidden" id='couponType0' name="couponType0" value="0" autocomplete="off"/>
  	<input type="hidden" id='couponSn0' name="couponSn0" autocomplete="off"/>
  	<input type="hidden" id='couponPassword0' name="couponPassword0" autocomplete="off"/>
  	<input type="hidden" id='couponValue0' name="couponValue0" value="0" autocomplete="off"/>
  	<input type="hidden" id='useCouponSellerIds' name="useCouponSellerIds" value=""/>
</div>
</@form.form>
<!--底部-->
<div class="clear"></div>
<div style="height:60px"></div>
<div class="footer overflow">
	<div class="total fl" id = "finalamountFont">合计：¥ ${cartInfoVO.finalAmount?string('0.00')!'0.00'}</div>
    <a href="javascript:void(0)" class="sumbit_order fr" id="submitCart">提交订单</a>
</div>

<!--底部end-->
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/order.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
</html>
