<#include "/h5/commons/_head.ftl" />
<body class="bgf2">
<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="javascript:history.back(-1);">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">填写订单</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"><span class="fa fa-bars" style="color: white;"></span></div>
   	  </div>
   </header>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   <!-- 头部 end-->
   
	<div style="padding-bottom:60px;margin-bottom: 40px;" id="orderMainDiv">
	  <@form.form action="" id="orderForm" name="orderForm" method="post">
		   <!-- 记录该订单是否存在二次加工，如果存在则要特殊处理 -->
		  <input type="hidden" id="tzm_twoJG" name="twoJGFlag" value="0"/>
		  <!-- 用户真实姓名 -->
		  <input type="hidden" id="realName" value="${(member.realName)!''}"/>
	       <div class="order-d-box">
		    
		         <ul class="flex flex-pack-justify">
		           <li>配送方式</li>
		           <div class="flex-2 sel-btn pad-top" id="sendWayDiv" style="margin-top:-20px">
		           <a class="btn btn-default" onclick="changeTransteType(this,1)">物流中转</a>
		           <a class="btn btn-default" onclick="changeTransteType(this,2)">送货上门</a>
		           <a class="btn btn-default" onclick="changeTransteType(this,3)">快递配送</a>
		           <a class="btn btn-default" onclick="changeTransteType(this,4)">千禧路自提</a>
		           <a class="btn btn-default" onclick="changeTransteType(this,5)">大唐自提</a>
				<#--
					<a class="btn btn-default active" onclick="setSendWay(this, 'SENDNOW')">立即发货</a>
					<a class="btn btn-default" onclick="setSendWay(this, 'TAKESELF')">上门自提</a>
					<a class="btn btn-default" onclick="setSendWay(this, 'THREESEND')">云仓托管</a>
				-->
			  </div>
		         </ul>
		      
		      <div class='step-tit' id="transport">
					<div class='extra-r' style="line-height:0; margin:10px;">
						<input class="magic-radio" type="radio" name="transportType" id="transportType6" value="6"/>
						<input class="magic-radio" type="radio" name="transportType" id="transportType7" value="7"/>
						<!--<a href='javascript:void(0);' class='ftx-05 addaddress' onclick="addOrEditAddress(0)">新增收货地址</a>-->
						<input class="magic-radio" type = "radio" name="transportType" id="transportType8" value="8" style="margin-top: 10px;" onChange="changeFee()"/>
						<input class="magic-radio" type = "radio" name="transportType" id="transportType5" value="5" style="margin-top: 10px;" <#if transportType==5> checked="checked;"</#if> onChange="changeFee()" />
						<input class="magic-radio"  type = "radio" name="transportType" id="transportType0" value="0" style="margin-top: 10px;" <#if transportType==1||transportType==3> checked="checked;"</#if> onChange="changeFee()" />
						<#--
						 上门自提时，设置类型值为0  
						<input type="radio" name="transportType" value="0" id="transportType5" style="width: 0px;"/>
							<input type = "radio" name="transportType" id="transportType2" value="2" <#if transportType==2> checked="checked;"</#if> onChange="changeFee()" />
							<label for="transportType2" class='ftx-05' style="cursor:pointer;font-weight:100" >圆通快递</label>
							<span style="margin-left: 20px;"></span>
							<input type = "radio" name="transportType" id="transportType4" value="4" <#if transportType==4> checked="checked;"</#if>onChange="changeFee()"  />
							<label for="transportType4" class='ftx-05'  style="cursor:pointer;font-weight:100">物流</label>
						-->
					</div>
			</div>
			<!-- 快递配送 -->
			<div id="expressDif" style="display: none;">
					<li style="list-style: none; margin-bottom:5px;">运送方式</li>
		           <a class="btn btn-default" onclick="changeKuaidiType(this,1)">中通快递</a>
		            <#-- <a class="btn btn-default" onclick="changeKuaidiType(this,3)">申通快递</a>-->
		           <a class="btn btn-default" onclick="changeKuaidiType(this,2)">顺丰快递</a>
		           <#-- <a class="btn btn-default" onclick="changeKuaidiType(this,4)">邮政</a>-->
		           
				<input class="magic-radio" type = "radio" name="LogisticType" id="transportType1" value="1" style="margin-top: 10px;" <#if transportType==1> checked="checked;"</#if> onChange="changeFee()"/>
				<input class="magic-radio" type = "radio" name="LogisticType" id="transportType3" value="3" style="margin-top: 10px;" <#if transportType==3> checked="checked;"</#if> onChange="changeFee()"  />
				<input class="magic-radio" type = "radio" name="LogisticType" id="transportType2" value="2" style="margin-top: 10px;" <#if transportType==2> checked="checked;"</#if> onChange="changeFee()"  />
				<input class="magic-radio" type = "radio" name="LogisticType" id="transportType9" value="9" style="margin-top: 10px;" <#if transportType==9> checked="checked;"</#if> onChange="changeFee()"  />
				<#--
				<span style="color: #FF3C23;font-size: 12px;"><span class="fa fa-warning"></span>
				 	受G20期间对快递区域限制的影响,发往杭州的快递,9月6日之前只能发顺丰,9月7日普通快递恢复正常.其他区域快递和物流正常发运。
				</span>
				-->
			</div>
			<!-- end -->
			<!-- 物流中转 大唐 -->
			<div class='checkout-steps'  id="transfer_One" style="display: none;">
				<a href="javascript:;" class="block" onclick="wuluiShowOrHidden(this)">
			         <ul class="flex flex-pack-justify">
			           <li>中转说明</li>
			           <li>
			              <span id="payDownSpan" class="fa fa-angle-down"></span>
			          </li>
			         </ul>
			      </a>
				<div class='step-tit' id="sh_wuliuid" style="display: none;">
			      <ul style="float: left;font-size:14px;color:#333333;word-break:break-all; width:100%; overflow:auto">
			      	<li>1：大袜网为您免费提供“到大唐物流中转”服务，从大唐到您拍下地址的物流费用<span style="color:red;">到付</span>，温馨提示，物流速度会相对慢，请耐心等待。</li>
			      	<li>2：请在具体收货地址后注明物流中转地点，示例如下：</li>
			      	<li style="padding-left: 0px;">
			      		<span style="display: block;float: left;">街道地区：</span>
			      		<br>
			      		<span style="background-color: #666;">袍江路1号小麦（浙江）网络科技有限公司（中转地点：大唐镇山下朱一号）</span>
			      	</li>
			      </ul>
			      <div style="clear: both;margin-left: 0px;margin-top: 20px;">
			      	<span style="font-size:14px;color:#666666;"><span class="fa fa-circle" style="color:#27CDF2;"></span>我已根据物流中转服务要求设置收货地址，如因中转地点错误导致货品不能送达，所造成的损失自行负责。</span>
			      </div>
			   </div>
			</div>
			<!-- end -->
			<!-- 送货上门 -->
			<div class='checkout-steps'  id="sendService_dw" style="display:none;">
				<a href="javascript:;" class="block" onclick="songshangShowOrHidden(this)">
			         <ul class="flex flex-pack-justify">
			           <li>送货说明</li>
			           <li>
			              <span id="payDownSpan" class="fa fa-angle-down"></span>
			          </li>
			         </ul>
			      </a>
			      <div id="sh_firstid" style="display: none;">
					<div>
						<table class="tzm_spancss">
						  <tr>
						  	<td width='250'><strong>选择配送地区和收货地址:</strong></td>
	      				  </tr>
	      				  <tr>
						  	<td>
						  	<label id="lblSelect" style="margin-left: 0px;margin-top: 10px;">
								<select name="sendArea" id="sendArea" onchange="changeTransportFee()">   
		        					<option value="1">配送地区</option>   
							        <option value="2">暨阳街道、陶朱街道、浣东大道、大唐、草塔</option>   
							        <option value="3">枫桥、王家井、牌头、五泄</option>   
							        <option value="4">陈宅、岭北、义乌、浦江</option>   
							        <option value="5">上述地区之外诸暨市内其他地区</option>   
		      					</select>
	      					</label>
		      				</td>
	      				  </tr>
	      			</table>
				</div>
				<div id="tzm_zpsong">
					<span>自配送说明：</span>
					<div style="font-size: 14px;margin-top: 10px;margin-left: 20px;margin-bottom: 20px;">为回馈广大客户，在”大袜网”上线公测期间符合送货范围及以下条件的，送货服务费全免：</div>
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>送货区域</th>
								<th>送货范围</th>
								<th>单次送货数量要求（双）</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>一区</td>
								<td>诸暨三个街道、大唐、草塔</td>
								<td>≥1000</td>
							</tr>
							<tr>
								<td>二区</td>
								<td>枫桥、王家井、牌头、五泄</td>
								<td>≥5000</td>
							</tr>
							<tr>
								<td>三区/四区</td>
								<td>义乌、浦江、上述地区之外诸暨市内其他地区</td>
								<td>≥20000</td>
							</tr>
						</tbody>
					</table>
					<div style="font-size: 14px;margin-top: 10px;margin-left: 20px;margin-bottom: 20px;">单次送货未达以上数量，请在收货时当面结清送货费用，计费标准如下：</div>
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>送货区域</th>
								<th>送货范围</th>
								<th>配送费（￥/箱）</th>
								<th>最低收费标准（￥）</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>一区</td>
								<td>诸暨三个街道、大唐、草塔</td>
								<td>4</td>
								<td>20</td>
							</tr>
							<tr>
								<td>二区</td>
								<td>枫桥、王家井、牌头、五泄</td>
								<td>4.5</td>
								<td>180</td>
							</tr>
							<tr>
								<td>三区</td>
								<td>陈宅、岭北、义乌、浦江</td>
								<td>5.5</td>
								<td>330</td>
							</tr>
							<tr>
								<td>四区</td>
								<td>上述地区之外诸暨市内其他地区</td>
								<td>5</td>
								<td>250</td>
							</tr>
						</tbody>
					</table>
					<div style="font-size: 14px;margin-top: 10px;margin-left: 20px;margin-bottom: 20px;">(请根据以上配送区域设置收货地址，如因收货地区错误导致货品不能送达，所造成的损失由货主自负)</div>
				</div>
				</div>
		    </div>
		    <!-- end -->
		    <!-- 上门自提 千禧路20号 -->
      <div class='checkout-steps'  id="takeSelfAdress_qxl" style="display:none;border: 0px solid red;margin-left: 15px;">
      		<a href="javascript:;" class="block" onclick="qxlShowOrHidden(this)">
		         <ul class="flex flex-pack-justify">
		           <li>千禧路自提点</li>
		           <li>
		              <span id="payDownSpan" class="fa fa-angle-down"></span>
		          </li>
		         </ul>
	      	</a>
      	<div id="sh_qianxiluid" style="display: none;">
		   <div>
		   		<table class="table table-bordered table-hover">
		   			<tr>
		   				<td>自提点</td>
		   				<td>浙江省诸暨市千禧路20号大袜云仓北门</td>
		   			</tr>
		   			<tr>
		   				<td>联系人</td>
		   				<td>张建涛</td>
		   			</tr>
		   			<tr>
		   				<td>联系电话</td>
		   				<td>0575-87599013&nbsp;&nbsp;&nbsp;&nbsp;15215936158</td>
		   			</tr>
		   		</table>
		  </div>
		  <div>
		  	<table class="table table-bordered table-hover">
		  		<caption>仓储目前出库时效为</caption>
		  		<thead>
		  			<tr>
		  				<th>类型</th>
		  				<th>WMS接单时间/数量（双）</th>
		  				<th>出库时效</th>
		  			</tr>
		  		</thead>
		  		<tbody>
		  			<tr>
		  				<td>裸袜</td>
		  				<td>15:00以前</td>
		  				<td>次日10点前</td>
		  			</tr>
		  			<tr>
		  				<td>裸袜</td>
		  				<td>15:00以后</td>
		  				<td>次日17点前</td>
		  			</tr>
		  			<tr>
		  				<td>需二次加工</td>
		  				<td>8000以内</td>
		  				<td>24小时内</td>
		  			</tr>
		  			<tr>
		  				<td>需二次加工</td>
		  				<td>8000-15000</td>
		  				<td>36小时内</td>
		  			</tr>
		  			<tr>
		  				<td>需二次加工</td>
		  				<td>15000以上</td>
		  				<td>72小时内</td>
		  			</tr>
		  		</tbody>
		  	</table>
		  	<h5>
			注意：<br>
			&#12288;&#12288;1.请记录订单编号和账号，向联系人出示。<br>
			&#12288;&#12288;2.请在联系人陪同下进入园区，出区时请主动向门卫出示<br>&#12288;&#12288;提货手续。
		  	</h5>
		  </div>
		  </div>
	  </div>
      <!-- end -->
      <!-- 上门自提 大唐 -->
      <div class='checkout-steps'  id="takeSelfAdress_dat" style="display:none;border: 0px solid red;margin-left: 15px;">
     		 <a href="javascript:;" class="block" onclick="datangShowOrHidden(this)">
		         <ul class="flex flex-pack-justify">
		           <li>大唐自提点</li>
		           <li>
		              <span id="payDownSpan" class="fa fa-angle-down"></span>
		          </li>
		         </ul>
	      	</a>
	     <div id="sh_datangid" style="display: none;"> 
		  <div>
		   		<table class="table table-bordered table-hover">
		   			<tr>
		   				<td>自提点</td>
		   				<td>大唐轻纺袜业城现代物流隔壁</td>
		   			</tr>
		   			<tr>
		   				<td>联系人</td>
		   				<td>戚碧桃</td>
		   			</tr>
		   			<tr>
		   				<td>联系电话</td>
		   				<td>0575-89000305&nbsp;&nbsp;&nbsp;&nbsp;18105756083</td>
		   			</tr>
		   		</table>
		  </div>
		  <div>
		  	<table class="table table-bordered table-hover">
		  		<caption>仓储目前出库时效为</caption>
		  		<thead>
		  			<tr>
		  				<th>类型</th>
		  				<th>WMS接单时间/数量（双）</th>
		  				<th>出库时效</th>
		  			</tr>
		  		</thead>
		  		<tbody>
		  			<tr>
		  				<td>裸袜</td>
		  				<td>15:00以前</td>
		  				<td>次日10点前</td>
		  			</tr>
		  			<tr>
		  				<td>裸袜</td>
		  				<td>15:00以后</td>
		  				<td>次日17点前</td>
		  			</tr>
		  			<tr>
		  				<td>需二次加工</td>
		  				<td>8000以内</td>
		  				<td>24小时内</td>
		  			</tr>
		  			<tr>
		  				<td>需二次加工</td>
		  				<td>8000-15000</td>
		  				<td>36小时内</td>
		  			</tr>
		  			<tr>
		  				<td>需二次加工</td>
		  				<td>15000以上</td>
		  				<td>72小时内</td>
		  			</tr>
		  		</tbody>
		  	</table>
		  	<h5>
			注意：<br>
			&#12288;&#12288;1.请记录订单编号和账号，向联系人出示。<br>
			&#12288;&#12288;2.请在联系人陪同下进入园区，出区时请主动向门卫出示<br>&#12288;&#12288;提货手续。
		  	</h5>
		  </div>
		  </div>
	  </div>
      <!-- end -->
	  <!-- 配送方式名称 -->
	  <input type="hidden" id='ordersTypeName' name="ordersType" value="1"/>
	  <input type="hidden" id='totalCartAmount' name='totalCartAmount' value="${(cartInfoVO.totalCheckedNumber)}">
	  <input type="hidden" id="servicePrice" id="servicePrice" value="${(cartInfoVO.servicePrice)?string("0.00")!'0.00'}">
      <div class="order-d-box bgfaf3"  id="addressDiv">
      <a href="javascript:;" class="block" onclick="chooseAddress()">
         <ul class="flex flex-pack-justify">
           <#if address?? >
           <li class="clear" style="margin-left: 35px;">
             <span class="o-u-infor"><i >收货人:</i>&nbsp;<font>${(address.memberName)!""}</font></span>
             <span class="o-u-infor"><i ></i>&nbsp;<font>${(commUtil.hideMiddleStr(address.mobile,3,4))!""}</font></span><br>
             <p>收获地址: &nbsp; ${(address.addAll)!""}&nbsp;${(address.addressInfo)!""}</p>
             <input type="hidden" value="${(address.addAll)!''}" id="tzm_addressAll"/>
           </li>
           <#else>
           <li class="clear">
           	  去选择地址
           </li>
           </#if>
           <li><span class="fa fa-angle-right" style="line-height:60px;"></span></li>
         </ul>
      </a>
      </div>
      <input type="hidden" id="addressId" name="addressId" value="${(address.id)!0}">
      <div class="order-d-box">
      	<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
		<#list cartInfoVO.cartListVOs as cartListVO>
			<#assign seller = cartListVO.seller />
            <h2 class="cart-h2 pad10" style="display: none;">
                <span>${(seller.sellerName)!'' }</span>
                <!-- S add 满减 -->
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
                <!-- E add 满减 -->
            </h2>
            <div>
           	  <#if (cartListVO.cartList??) && (cartListVO.cartList?size>0) >
              <#list cartListVO.cartList as cart>
              <#assign product = cart.product />
              <#assign productGoods = cart.productGoods />
	              <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(cart.productId)!0}.html?goodId=${(cart.productGoodsId)}" class="block">
	              <dl class="img-ul cart-ul flex">
	                <dt style="width:80px;height:80px;">
	                
	                <#if product.isNorm == 2>
					<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${productGoods.images!''}">
					<#else>
					<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.masterImg!''}">
					</#if>
															
	                </dt>
	                <dd class="flex-2 pos_relative">
						<div class="product_name">
							${product.name1!''} 
							<#if product.sellerId == 0>
							<span style="vertical-align:top;display:inline-block;width:16px;height:16px;BACKGROUND: url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/redpacket.png') no-repeat center 50%;"></span>
							<#else>
							<span></span>
							</#if>
							${cart.specInfo!''}
						</div>
						<div class="clr53 font12">
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
								￥<font>${productGoods.mallMobilePrice?string("0.00")!"0.00"}</font>-->
								<#if cart.isDabiaowa?? && cart.isDabiaowa==1>
									￥<font>${cart.tempdabiaoPriceOnly?string("0.00")!"0.00"}
								<#else>
									￥<font>${product.mallPcPrice?string("0.00")!"0.00"}
									<#if product.priceStatus??&&product.priceStatus==3>
										-￥<font>${product.scatteredPrice?string("0.00")!"0.00"}
									</#if>
								</#if>
							</#if>
							<font style="margin-left: 10px;">加工费:+<#if cart.packageFee??>${(cart.packageFee)?string("0.00")!'0.00'}<#else>${(cart.packageFee)!'0.00'}</#if></font>
							<font style="margin-left: 10px;">辅料费:+<#if cart.labelFee??>${(cart.labelFee)?string("0.00")!'0.00'}<#else>${(cart.labelFee)!'0.00'}</#if></font>
							<#if cart.isDabiaowa?? && cart.isDabiaowa==1> <!-- 如果是打标袜则不要将其加工费用设置为0 -->
								<#else>								
								<input type="hidden" name="tzm_ttfee" value="${(cart.packageFee)!'0.0'}"/>
							</#if>
						</div>
						<div>x <font>${(cart.count)!"0"}</font></div>
	                </dd>
	              </dl>
	              </a>
              </#list>
              <!-- S 小计 -->
              <div class="subtotal">
                <i>小计:${(cartListVO.sellerCheckedDiscountedAmount)?string('0.00')!"0.00"}</i>
                <span>
                	<#if cartListVO.sellerCheckedDiscounted?? && cartListVO.sellerCheckedDiscounted &gt; 0>
                	&nbsp;&nbsp;(
                		<#if cartListVO.orderDiscount?? && cartListVO.orderDiscount &gt; 0>
                			<#if (cartListVO.sellerCheckedDiscounted - cartListVO.orderDiscount) &gt; 0>
	                			立减:${(cartListVO.sellerCheckedDiscounted - cartListVO.orderDiscount)?string('0.00')!0}
	                			&nbsp;
	                		</#if>
	                		满减:${(cartListVO.orderDiscount)?string('0.00')!"0.00"}
	                	<#else>
	                		立减:${(cartListVO.sellerCheckedDiscounted)?string('0.00')!0}
                		</#if>
                		<input type="hidden" name="tzm_yisheng" value="${(cartListVO.sellerCheckedDiscounted)?string('0.00')!0}"/>
                	)
                	</#if>
                </span>
              </div>
              <!-- E 小计 -->
              <!-- S 优惠券 -->
              <#--
              <div class="order-d-box ">
              	  <!-- 记录优惠券使用信息
				  <input type="hidden" id='couponType${(cartListVO.seller.id)!0}' name="couponType${(cartListVO.seller.id)!0}" value="0" autocomplete="off"/>
				  <input type="hidden" id='couponSn${(cartListVO.seller.id)!0}' name="couponSn${(cartListVO.seller.id)!0}" autocomplete="off"/>
				  <input type="hidden" id='couponPassword${(cartListVO.seller.id)!0}' name="couponPassword${(cartListVO.seller.id)!0}" autocomplete="off"/>
				  <input type="hidden" id='couponValue${(cartListVO.seller.id)!0}' name="couponValue${(cartListVO.seller.id)!0}" value="0" autocomplete="off"/>
				  
		    	  <!-- 记录当前的优惠券填写类型 1为选择优惠券，2为填写优惠码
    			  <input type="hidden" id="currType${(cartListVO.seller.id)!0}" value="1" autocomplete="off">
		    	  <!-- 记录当前选择优惠券的商家订单的订单金额
		    	  <input type="hidden" id="sellerOrderAmount${(cartListVO.seller.id)!0}" value="${(cartListVO.sellerCheckedDiscountedAmount)?string('0.00')!'0.00'}" autocomplete="off">
    			  
                  <div class="clearfix">
	                  <span class="coupons" sellerId="${(cartListVO.seller.id)!0}">使用红包</span>
	                  <span class="coupons-mark" id="couponsMark${(cartListVO.seller.id)!0}"></span>  
                  </div>
                  <div class="switch-coupons-box clearfix" id="switchCouponsBox${(cartListVO.seller.id)!0}" style="display: none">
	                  <div class="bg-box clearfix">
		                  <div class="online-box" id="onlineBox${(cartListVO.seller.id)!0}">
			                  <select id="selectCoupon${(cartListVO.seller.id)!0}" name="selectCoupon${(cartListVO.seller.id)!0}" class="selectCoupon">   
			                  </select>
		                  </div>
		                  <div class="coupons-box" id="couponsBox${(cartListVO.seller.id)!0}" style="display: none">
			                  <span class="tit-txt">序列号：</span>
			                  <input type="text" name="currCouponSn${(cartListVO.seller.id)!0}" id="currCouponSn${(cartListVO.seller.id)!0}"><br>
			                  <br>
			                  <span class="tit-txt">密 码：</span>
			                  <input type="password" id="currCouponPassword${(cartListVO.seller.id)!0}" name="currCouponPassword${(cartListVO.seller.id)!0}" autocomplete="off">
		                  </div>
		                  <div style="display: none;" class="switch-box" id="switchBox${(cartListVO.seller.id)!0}" sellerId="${(cartListVO.seller.id)!0}">输入</div>
	                  </div>
	                  <div id="couponMsgDiv${(cartListVO.seller.id)!0}" style="color:red"></div>
	                  <div class="con">
	                      <a class="cancel" sellerId="${(cartListVO.seller.id)!0}" href="javascript:;" ><span>取消</span></a>
				          <a class="submit couponSubmit" sellerId="${(cartListVO.seller.id)!0}" href="javascript:;" ><span>确定</span></a>
				          <a class="casu couponNotUse" sellerId="${(cartListVO.seller.id)!0}" href="javascript:;" ><span>取消使用红包</span></a>
	                  </div>
                  </div>
              </div>
                  -->
              <!-- E 优惠券 -->
              </#if>
            </div> 
         </#list>
         </#if>
      </div>

      <div class="order-d-box">
      <a href="javascript:;" class="block" onclick="choosePayment(this)">
         <ul class="flex flex-pack-justify">
           <li>支付方式</li>
           <li>
              <span id="paymentCodeSpan">在线支付</span>&nbsp;&nbsp;<span id="payDownSpan" class="fa fa-angle-down"></span>
          </li>
         </ul>
      </a>
      <div class="flex-2 sel-btn pad-top text-center" id="paymentCodeDiv" style="display:none;">
		<a class="btn btn-default active" onclick="setPayment(this, 'ONLINE')">在线支付</a>
		<#--<a class="btn btn-default" onclick="setPayment(this, 'OFFLINE')">货到付款</a>-->
		<a class="btn btn-default" onclick="setPayment(this, 'OFFSEND')">线下打款</a>
	  </div>
      </div>
      
      <div class="order-d-box">
      <a href="javascript:;" class="block" onclick="chooseRemark(this)">
         <ul class="flex flex-pack-justify">
           <li>订单备注</li>
           <li>
              <span id="remarkCodeSpan">需要发货单</span>&nbsp;&nbsp;<span id="remarkDownSpan" class="fa fa-angle-down"></span>
          </li>
         </ul>
      </a>
      <div class="flex-2 sel-btn pad-top text-center" id="orderRemarkDiv" style="display:none;">
      	<a class="btn btn-default" onclick="setRemark(this, 'NONEED')">不需要发货单</a>
		<a class="btn btn-default active" onclick="setRemark(this, 'NEED')">需要发货单</a>
	  </div>
      </div>
      
	  <!-- 支付方式名称 -->
	  <input type="hidden" id='paymentName' name="paymentName" value="${(orderCommitVO.paymentName)!''}"/>
	  <!-- 支付方式code -->
	  <input type="hidden" id='paymentCode' name="paymentCode" value="${(orderCommitVO.paymentCode)!''}"/>
	  <!-- 订单备注 -->
	  <input type="hidden" id='remark' name="remark" value="需要发货单"/>
	  
	  <input type="hidden" id="transId" name="transId" value="">

      <div class="order-d-box" style="display: none;">
      <a href="javascript:;" class="block" onclick="setInvoice(this)">
         <ul class="flex flex-pack-justify">
           <li>发票信息</li>
           <li class="flex text-right">
             <div class="o-f-invoice">
             	<span id="invoiceTitleSpan">不开发票</span>
             	<br>
             	<span id="invoiceTypeSpan"></span>
             </div>
             <div><span id="invoiceDownSpan" class="fa fa-angle-down"></div>
          </li>
         </ul>
      </a>  
      </div>
      <div id="invoiceDiv" style="display:none;" class="o-box-layer">
      	<div class="pad-bt">
          发票类型: <font class="cl70">纸质发票</font>
        </div>
      	<div class="pad-bt">
          发票抬头:
          <a class="btn btn-default" onclick="newInvoiceTitle(this)">新抬头</a><br>
          <p class="padt_b10">
          <input type="text" id="newInvoiceTitleText" name="newInvoiceTitleText" class="form-control" style="display:none;">
          </p>
          <select class="form-control" id="invoiceTitleSlt" name="invoiceTitleSlt">
        <option selected='true'>个人</option>
        <#if invoiceList??>
        <#list invoiceList as invoice>
                <option value="${(invoice.content)!''}">${(invoice.content)!''}</option>
                </#list>
              </#if>
          </select>
        </div>
      	<div class="cl70">
          发票内容:
          <font>明细</font>
        </div>
      	<div>
      		<lable id="incoiceErr" class="clr53"></lable>
      	</div>
      	<button type="button" class="btn btn-block" style="margin-top:20px;" onclick="saveInvoice()">保存并使用</button>
      	<button type="button" class="btn btn-block" style="margin-top:20px;" onclick="noInvoice()">不开发票</button>
      </div>
      <input type="hidden" id='invoiceStatus' name="invoiceStatus" value="${(orderCommitVO.invoiceStatus)!''}"/>
	  <!-- 发票内容 -->
	  <input type="hidden" id='invoiceType' name="invoiceType" value="${(orderCommitVO.invoiceType)!''}"/>
	  <!-- 发票抬头 -->
	  <input type="hidden" id='invoiceTitle' name="invoiceTitle" value="${(orderCommitVO.invoiceTitle)!''}"/>

	  <!-- 使用余额 -->
	  <div class="order-d-box" id="tzm_hideBalanceId">
      <a href="javascript:;" class="block" onclick="useBalance(this)">
         <ul class="flex flex-pack-justify">
           <li>
           	 使用余额<span class="clrbf">（当前余额：${(member.balance)!'0.00' }元）</span>
           </li>
           <li>
             <span id="useBlanceSpan">不使用余额</span>&nbsp;&nbsp;<span id="balanceDownSpan" class="fa fa-angle-down"></span>
          </li>
         </ul>
      </a>
      </div>
      <div id="balanceDiv" style="display:none;" class="o-box-layer">
      	<div class="cho-con">
			<input type="checkbox" id="selectOrderBalance" autocomplete="off" style=" width: 20px;
    height: 20px;
    margin-top: 10px;
    margin-left: 45px;">
			<label style="margin-top:4px;">使用余额</label>
			<input type="password" id="balancePwd" readonly="readonly" 
				class="form-control" style="display:inline-block; 
				width:40%;" autocomplete="off" >
		</div>
		<div>
			<label id="balanceErr" class="clr53"></label>
		</div>
		<button type="button" class="btn btn-block" style="margin-top:20px;" onclick="confirmUseBalance()">确定</button>
      </div>
      <input type="hidden" id='isBalancePay' name="isBalancePay" value="false"/>

	  <!-- 使用积分 -->
      <div class="order-d-box" style="display: none;">
      <a href="javascript:;" class="block" onclick="useIntegral(this)">
         <ul class="flex flex-pack-justify">
           <li>
           	积分<span class="clrbf">&nbsp;共可用${(member.integral - ((member.integral)%(config.integralScale)))!0}积分</span>
           </li>
           <li class="flex text-right">
             <div class="o-f-invoice">
             	<span>使用</span><span id="useIntegralSpan">0</span><span>积分</span>
             	<br>
             	<span id="integralToMoney1">￥0.00</span>
             </div>
             <div><span id="integralDownSpan" class="fa fa-angle-down"></div>
          </li>
         </ul>
      </a>
      </div>
      <div id="integralDiv" style="display:none;" class="o-box-layer">
      	<span>使用规则(<i style="color:#edd28b;">积分满${config.integralScale!0}即可使用：每次使用积分为n*${config.integralScale!0}</i>)</span>
      	<div class="cho-con pad-top">
			<span>使用积分</span>
			<div style="padding-top:5px;">
				<a class="btn btn-default" onclick="plusIntegral()">+</a>
				<input type="text" id="integralText" value="0" readonly="readonly" class="form-control" style="display:inline-block; width:40%;">
				<a class="btn btn-default" onclick="minusIntegral()">-</a>
				<span id="integralToMoney2">￥0.00</span>
			</div>
		</div>
		<div>
			<label id="integralErr" class="clr53"></label>
		</div>
		<button type="button" class="btn btn-block" onclick="confirmUseIntegral()">确定</button>
      </div>
      <!-- 使用积分数 -->
      <input type="hidden" id="integral" name="integral" value="0"/>

      <div class="order-d-box">
         <ul class="flex flex-pack-justify">
           <li> 应付金额</li>
           <li class="flex text-right">
             <span class="clr53">￥${(cartInfoVO.checkedDiscountedCartAmount)?string('0.00')!'0.00'}</span>
          </li>
         </ul>
         <ul class="flex flex-pack-justify">
           <li> 商品金额(原价)</li>
           <li class="flex text-right">
             <span class="clr53">￥${(cartInfoVO.checkedCartAmount)?string('0.00')!'0.00'}</span>
             <input type="hidden" value="${(cartInfoVO.checkedCartAmount)?string('0.00')!'0.00'}" id="totalPrice_ID"/>
          </li>
         </ul>
         <ul class="flex flex-pack-justify">
           <li> 活动节省</li>
           <li class="flex text-right">
             <span class="clr53"> - ￥${(cartInfoVO.checkedDiscountedAmount)?string('0.00')!'0.00'}</span>
          </li>
         </ul>
         <ul class="flex flex-pack-justify">
           <li> 红包节省</li>
           <li class="flex text-right">
             <span class="clr53" id="couponDiscountSpan"> - ￥0.00</span>
          </li>
         </ul>
         <ul class="flex flex-pack-justify">
           <li> 运费</li>
           <li class="flex text-right">
             <span class="clr53" id="shipfee"> + ￥${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'}</span>
             <input type="hidden" value="${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'}" id="tzm_yunfei"/>
          </li>
         </ul>
         <ul class="flex flex-pack-justify">
           <li> 加工费</li>
           <li class="flex text-right">
             <span class="clr53"> + &yen;${(cartInfoVO.servicePrice)?string("0.00")!'0.00'}</span>
          </li>
         </ul>
         <ul class="flex flex-pack-justify">
           <li> 辅料费</li>
           <li class="flex text-right">
             <span class="clr53"> + &yen;${(cartInfoVO.labelPrice)?string("0.00")!'0.00'}</span>
          </li>
         </ul>
         <ul class="flex flex-pack-justify" style="display:none;" id="balanceUl">
           <li> 余额</li>
           <li class="flex text-right">
             <span class="clr53" id="balanceSpan">- ￥0.00</span>
          </li>
         </ul>
         <ul class="flex flex-pack-justify" style="display:none;" id="integralUl">
           <li> 积分</li>
           <li class="flex text-right">
             <span class="clr53" id="integralSpan">- ￥0.00</span>
         </li>
         </ul>
      
      </div>
      <!-- 记录使用了优惠券的商家ID，多个ID用英文逗号分隔，放到form里面 -->
	  <input type="hidden" id='useCouponSellerIds' name="useCouponSellerIds" value=""/>
	  </@form.form>
    </div>
	<!-- 主体结束 -->

	<!-- 合计层 -->
	<div class="totallayer">
		<div class="flex flex-align-center" style="height:100%; position:absolute; bottom:0; left:0; width:100%;">
			<div class="flex-2">
			   <span class="font14">实付款:</span><font class="font16">&nbsp;¥</font><font class="font16" id="finalamountFont">${cartInfoVO.finalAmount?string('0.00')!'0.00'}</font>
			 </div>
			 <div class="go-pay padlr10 font16"><a href="javascript:;" class="block" onclick="submitOrder(this)">提交订单</a></div>
		</div>
	</div>
    
    <!-- 使用积分数量，记录一个hidden值方便计算 -->
	<input type="hidden" id="usedIntegralHidden" value="0"/>
    <!-- 使用余额数量，记录一个hidden值方便计算 -->
	<input type="hidden" id="usedBalanceHidden" value="0"/>
    <!-- 应付款金额，记录一个hidden值方便计算 -->
	<input type="hidden" id="sumPayPriceHidden" value="${cartInfoVO.finalAmount!'0.00'}"/>
	<input type="hidden" id="usedCreditHidden" autocomplete="off"/>
	
	<!-- 记录所有优惠券的优惠金额和，用于显示 -->
    <input type="hidden" id="couponValueSum" value="0" autocomplete="off">
    
	<!-- footer -->
	<#include "/h5/commons/_footer2.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

<script>
	$(document).ready(function () {
		// 点击使用余额checkbox
		$("#selectOrderBalance").click(function(){
			// 如果余额小于等于0 那么不允许选中
			<#if member??&&member.balance??>
				<#if member.balance<=0 >
					<#if !memberCredit?? || memberCredit.surplus <= 0 >
						$(this).prop("checked", false);
						$("#balancePwd").val("");
						$("#balancePwd").attr("readonly", "readonly");
						return;
					</#if>
				</#if>
			</#if>
			
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
			
			if($(this).prop("checked")){
				$("#balancePwd").removeAttr("readonly");
				// 余额
				var balance = parseFloat('${(member.balance)!'0.00'}');
				var userblanance = balance;
// 				balance = balance >= 0?balance:0;

				var creditAmount = 0;
				<#if memberCredit?? && memberCredit.surplus &gt;0 >
				var creditTotal = Number('${(memberCredit.surplus)!0}');
				//余额+可赊账额度 （如果赊账已结清，则余额为0）
				var balanceable = creditTotal.toFixed(2);
				//只有余额小于订单金额且可支付余额大于等于订单金额时才会使用赊账
				if(Number(balance) < Number(orderTotalPrice) && balanceable >= Number(orderTotalPrice)){
					$(this).closest(".o-box-layer").prev().find(".flex-pack-justify li .clrbf").
						after("<span style='color: red;'>您的账户可赊账<b>${memberCredit.surplus}</b>元，使用余额支付后，您的账户会产生欠款，请在规定时间内还清欠款。</span>");
					$("#useBlanceSpan").hide();
					//赊账金额
					creditAmount = Number(orderTotalPrice);
					
// 					//赊账金额大于赊账总额
// 					//赊账金额 ＝ 赊账总额
// 					if(creditAmount > creditTotal){
// 						creditAmount = creditTotal;
// 					}
// 					$("#creditPriceListDiv").show();
// 					$("#creditPay").html(creditAmount);
					$("#usedCreditHidden").val(creditAmount);
				} else if(balanceable < Number(orderTotalPrice)){
					$(this).closest(".o-box-layer").prev().find(".flex-pack-justify li .clrbf").siblings('span').remove();
					$(this).closest(".o-box-layer").prev().find(".flex-pack-justify li .clrbf").after("<span style='color: red;'>您的账户余额（包含赊账）总额为<b>"+balanceable+
						"</b>元，其中余额欠款<b>"+userblanance+
						"</b>元，可赊账<b>${memberCredit.surplus}</b>元，不足以支付当前订单，请充值或还清欠款。</span>");
					$("#useBlanceSpan").hide();
					$(this).prop("checked", false);
					$("#balancePwd").attr("readonly", "readonly");
					return;
				}
				</#if>
			} else {
				$(this).closest(".o-box-layer").prev().find(".flex-pack-justify li .clrbf").siblings('span').remove();
				$("#useBlanceSpan").show();
				$("#balancePwd").val("");
				$("#balancePwd").attr("readonly", "readonly");
			}
		});
		
		$('.coupons').click(function(){
			//totalPrice_ID取得商品总金额（原价）
			var totalPrice = $("#totalPrice_ID").val();
			var sellerId = $(this).attr("sellerId");
			if($(this).parent().siblings('#switchCouponsBox' + sellerId).css('display')=="none"){
				$(this).parent().siblings('#switchCouponsBox' + sellerId).slideDown();
				// 清空已有值
				$("#selectCoupon" + sellerId).empty();
				$("#currCouponSn" + sellerId).val("");
				$("#currCouponPassword" + sellerId).val("");
				$("#couponMsgDiv" + sellerId).html("");
				// 默认显示选择优惠码
				$('#onlineBox' + sellerId).show().siblings('#couponsBox' + sellerId).hide();
				// 记录需要用到的值
				$("#currType" + sellerId).val(1);
				// 获取已绑定的优惠码
				$.ajax({
					type : "GET",
					url :  domain+"/order/getsellercoupon.html",
					data : {sellerId:sellerId},
					dataType : "json",
					success : function(data) {
						if (data.success) {
			                var selectOption = '<option value ="">-- 请选择 --</option>'
			                var selectOption = ''
			                if(data.data.length==0){
			                	selectOption += "<option value=''>很抱歉，该系列商品没有使用的红包。</option>";
			                }else{
				                $.each(data.data, function(i, couponUser){
				                	var txtInfo = "";
				                	if (parseFloat(couponUser.minAmount) > 0) {
				                		txtInfo += "满" + couponUser.minAmount + "元 ";
				                	}
				                	txtInfo += "抵" + couponUser.couponValue + "元现金";
				                	txtInfo += " " + couponUser.couponSn;
				                	selectOption += "<option value=" + couponUser.couponSn + ">" + txtInfo + "</option>";
				                })
			                }
			                $("#selectCoupon" + sellerId).append(selectOption);
			            } else {

			            }
					}
				});
			}else {
				$(this).parent().siblings('#switchCouponsBox' + sellerId).slideUp();
			}
	    });

		// 切换
		$('.switch-box').click(function(){
			var sellerId = $(this).attr("sellerId");
			if($('#onlineBox' + sellerId).css('display')=="none"){
				$("#currType" + sellerId).val(1);
				$('#onlineBox' + sellerId).show().siblings('#couponsBox' + sellerId).hide();
				$(this).html("输入");
			}else {
				$("#currType" + sellerId).val(2);
				$('#couponsBox' + sellerId).show().siblings('#onlineBox' + sellerId).hide();
				$(this).html("选择");
			}
		});
		
		// 取消
		$('.cancel').on('click',function(){
			var sellerId = $(this).attr("sellerId");
			$("#currType" + sellerId).val(1);
			$("#couponMsgDiv" + sellerId).html("");
			$(this).parent().parent('#switchCouponsBox' + sellerId).slideUp();
		});
		// 取消使用优惠券
		$('.couponNotUse').on('click',function(){
			var sellerId = $(this).attr("sellerId");
			
			// 计算金额
	        calculateCouponValue(sellerId, 0,1);
			
			$("#couponType" + sellerId).val(0);
	        $("#couponSn" + sellerId).val("");
	        $("#couponPassword" + sellerId).val("");
	        $("#couponsMark" + sellerId).html("");
	        
			$("#couponMsgDiv").html("");
			$(this).parent().parent('#switchCouponsBox' + sellerId).slideUp();
		});
		// 确定
		$('.couponSubmit').on('click',function(){
			var obj = $(this);
			var sellerId = $(this).attr("sellerId");
			var currType = $("#currType" + sellerId).val();
			
			var currCouponSn = "";
			var currCouponPassword = $("#currCouponPassword" + sellerId).val();
			// 当前是选择优惠券
			if (currType == 1) {
				currCouponSn = $("#selectCoupon" + sellerId).val();
				if (currCouponSn == null || currCouponSn == "") {
					$("#couponMsgDiv" + sellerId).html("请选择要使用的红包");
					return false;
				}
			} else {
				// 当前为填写优惠码
				currCouponSn = $("#currCouponSn" + sellerId).val();
				if (currCouponSn == null || currCouponSn == "") {
					$("#couponMsgDiv" + sellerId).html("请输入要使用的优惠码");
					return false;
				}
				if (currCouponPassword == null || currCouponPassword == "") {
					$("#couponMsgDiv" + sellerId).html("请输入要优惠码密码");
					return false;
				}
			}
			
	        var orderAmount = $("#sellerOrderAmount" + sellerId).val();
	        // 校验优惠券可用性
			$.ajax({
				type : "GET",
				url :  domain+"/order/checksellercoupon.html",
				data : {
					orderAmount:orderAmount,
					couponType:currType,
					couponSn:currCouponSn,
					couponPassword:currCouponPassword,
					servicePrice:Number("${(cartInfoVO.servicePrice)!0}"),
					sellerId:sellerId
				},
				dataType : "json",
				success : function(data) {
					if (data.success) {
		                // 校验通过
		                
		                // 计算金额
		                calculateCouponValue(sellerId, data.data.couponValue);
		                
		                // 记录使用的各商家的优惠券信息
		                $("#couponType" + sellerId).val(currType);
		                $("#couponSn" + sellerId).val(currCouponSn);
		                $("#couponPassword" + sellerId).val(currCouponPassword);
		                
		                var couponInfoDiv = currCouponSn + " 优惠" + data.data.couponValue + "元";
		                $("#couponsMark" + sellerId).html(couponInfoDiv);
		                
		                // 记录使用了优惠券的商家ID
		                var sellerIds = $("#useCouponSellerIds").val();
		                sellerIds += "," + sellerId;
		                $("#useCouponSellerIds").val(sellerIds);
		                
						$("#couponMsgDiv" + sellerId).html("");
						obj.parent().parent('#switchCouponsBox' + sellerId).slideUp();
		            } else {
		            	// 校验未通过
        				$("#couponMsgDiv" + sellerId).html(data.message);
        				return false;
		            }
				}
			});
		});
	});
	
	//配送地区费用重新计算
	function changeTransportFee(){
		var sendArea = $("#sendArea").val();
  			var totalCartAmount = $('#totalCartAmount').val();
  			var box_amount = parseFloat(totalCartAmount/500).toFixed(2);
  			var arr_am = box_amount.split('.');
  			var t = parseFloat(arr_am[0]);
  			var transportFee = 0.00;
  			if(arr_am[1]>0){
  				t += 1;
  			}
  			//一区运费计算
  			if(sendArea==2){
  				transportFee = t*4;
  				if(transportFee<20){
  					transportFee = 20;
  				}
  				if(totalCartAmount >=1000){
  					transportFee = 0.00;
  				}
  			}
  			//二区运费计算
  			if(sendArea==3){
  				transportFee = t*4.5;
  				if(transportFee < 180){
  					transportFee = 180;
  				}
  				if(totalCartAmount >=5000){
  					transportFee = 0.00;
  				}
  			}
  			//三区运费计算
  			if(sendArea==4){
  				transportFee = t*5.5;
  				if(transportFee < 330){
  					transportFee = 330;
  				}
  				if(totalCartAmount >=2000){
  					transportFee = 0.00;
  				}
  			}
  			//四区运费计算
  			if(sendArea==5){
  				transportFee = t*5;
  				if(transportFee < 250){
  					transportFee = 250;
  				}
  				if(totalCartAmount >=2000){
  					transportFee = 0.00;
  				}
  			}
  			//动态计算运费
			$("#shipfee").html(" + ￥"+transportFee);
			$("#finalamountFont").html(((${cartInfoVO.finalAmount?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'})+transportFee).toFixed(2));
	}
	
	//根据重新选择运送方式重新计算邮费
	function changeFee(){
		$("#sendArea option:first").prop("selected", 'selected');
		//end
		var transportType = $("input[name='transportType']:checked").val();
		var transType = $("input[name='LogisticType']:checked").val();
	   if(transportType == 5){//送货上门
			$("#expressDif").hide();
			$("#transfer_One").hide();
			$("#addressDiv").hide();
			$("#takeSelfAdress_qxl").hide();
			$("#takeSelfAdress_dat").hide();
			$("#sendService_dw").show();
			$("#transId").val(transportType);
			//动态计算运费
			$("#shipfee").html(" + ￥0.00");
			$("#finalamountFont").html((${cartInfoVO.finalAmount?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'}).toFixed(2));
		}else if(transportType == 8){//物流中转【大唐】
			$("#expressDif").hide();
			$("#sendService_dw").hide();
			$("#addressDiv").hide();
			$("#takeSelfAdress_qxl").hide();
			$("#takeSelfAdress_dat").hide();
			$("#transfer_One").show();
			$("#transId").val(transportType);
			$("#shipfee").html(" + ￥0.00");
			$("#finalamountFont").html((${cartInfoVO.finalAmount?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'}).toFixed(2));
		}else if(transportType == 0){//快递配送
			$("#sendService_dw").hide();
			$("#transfer_One").hide();
			$("#takeSelfAdress_qxl").hide();
			$("#takeSelfAdress_dat").hide();
			$("#expressDif").show();
			$("#addressDiv").show();
			if(transType==1||transType==3||transType==2||transType==9){
				$("#transId").val(transType);
				var addressId = $("#addressId").val();
				//使用Ajax异步调用计算运费
				//window.location.href=domain+"/order/info.html?addressId="+addressId+"&transportType="+transType;
				$.ajax({
					type : "POST",
					url :  domain+"/order/calculateTransFee.html",
					data : {addressId:addressId,transportType:transType},
					dataType : "json",
					success : function(data) {
						if(data.success){
							$("#shipfee").html(" + ￥"+data.data.logisticsFeeAmount.toFixed(2));
							$("#finalamountFont").html(data.data.finalAmount.toFixed(2));
						}
					}
				});
			}
		}else if(transportType == 6){//千禧路自提
			$("#transId").val(transportType);
			$("#addressDiv").hide();
			$("#expressDif").hide();
			$("#sendService_dw").hide();
			$("#transfer_One").hide();
			$("#takeSelfAdress_dat").hide();
			$("#takeSelfAdress_qxl").show();
			$("#shipfee").html(" + ￥0.00");
			$("#finalamountFont").html((${cartInfoVO.finalAmount?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'}).toFixed(2));
		}else if(transportType == 7){//大唐自提
			$("#transId").val(transportType);
			$("#addressDiv").hide();
			$("#expressDif").hide();
			$("#sendService_dw").hide();
			$("#transfer_One").hide();
			$("#takeSelfAdress_qxl").hide();
			$("#takeSelfAdress_dat").show();
			$("#shipfee").html(" + ￥0.00");
			$("#finalamountFont").html((${cartInfoVO.finalAmount?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'}).toFixed(2));
		}
		
	}
	
	// 重新计算优惠券优惠金额、订单总金额
	function calculateCouponValue(sellerId, newCouponValue,type) {
		// 订单金额
		var sumPayPriceHidden= $("#sumPayPriceHidden").val();
		//判断 如果 自配送则运费不加
		var boolFlag = $("input[name='transportType']:checked").val();
		var tzm_yunfei;
		
		if(boolFlag==5 && type !=1){
			tzm_yunfei = $("#tzm_yunfei").val();
			sumPayPriceHidden = parseFloat(sumPayPriceHidden)-parseFloat(tzm_yunfei)
		}
		//end
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
		$("#finalamountFont").html(sumPayPriceHidden);
		// 把负数改成正数显示
		couponValueSum = parseFloat(Math.abs(couponValueSum)).toFixed(2);
		$("#couponDiscountSpan").html("- ￥" + couponValueSum);
	}
	
	function chooseAddress() {
		window.location.href = domain+"/member/address.html?isFromOrder=1";
	}
	//点击配送方式
	function chooseSendWay(_this) {
		if ($("#sendWayDiv").is(':hidden')) {
			$("#sendWayDiv").show();
			$(_this).find(".fa-angle-down").addClass("addangle-down");
		} else {
			$("#sendWayDiv").hide();
			$(_this).find(".fa-angle-down").removeClass("addangle-down");
		}
	}
	
	// 点击支付方式
	function choosePayment(_this) {
		if ($("#paymentCodeDiv").is(':hidden')) {
			$("#paymentCodeDiv").show();
			$(_this).find(".fa-angle-down").addClass("addangle-down");
		} else {
			$("#paymentCodeDiv").hide();
			$(_this).find(".fa-angle-down").removeClass("addangle-down");
		}
	}
	//物流中转  说明显示和隐藏
	function wuluiShowOrHidden(_this) {
		if ($("#sh_wuliuid").is(':hidden')) {
			$("#sh_wuliuid").show();
			$(_this).find(".fa-angle-down").addClass("addangle-down");
		} else {
			$("#sh_wuliuid").hide();
			$(_this).find(".fa-angle-down").removeClass("addangle-down");
		}
	}
	//送货上门  显示和隐藏
	function songshangShowOrHidden(_this) {
		if ($("#sh_firstid").is(':hidden')) {
			$("#sh_firstid").show();
			$(_this).find(".fa-angle-down").addClass("addangle-down");
		} else {
			$("#sh_firstid").hide();
			$(_this).find(".fa-angle-down").removeClass("addangle-down");
		}
	}
	//千禧路显示和隐藏
	function qxlShowOrHidden(_this) {
		if ($("#sh_qianxiluid").is(':hidden')) {
			$("#sh_qianxiluid").show();
			$(_this).find(".fa-angle-down").addClass("addangle-down");
		} else {
			$("#sh_qianxiluid").hide();
			$(_this).find(".fa-angle-down").removeClass("addangle-down");
		}
	}
	//大唐显示和隐藏
	function datangShowOrHidden(_this) {
		if ($("#sh_datangid").is(':hidden')) {
			$("#sh_datangid").show();
			$(_this).find(".fa-angle-down").addClass("addangle-down");
		} else {
			$("#sh_datangid").hide();
			$(_this).find(".fa-angle-down").removeClass("addangle-down");
		}
	}
	// 点击支付方式
	function chooseRemark(_this) {
		if ($("#orderRemarkDiv").is(':hidden')) {
			$("#orderRemarkDiv").show();
			$(_this).find(".fa-angle-down").addClass("addangle-down");
		} else {
			$("#orderRemarkDiv").hide();
			$(_this).find(".fa-angle-down").removeClass("addangle-down");
		}
	}
	
	//setSendWay
	// 选择配送方式
	function setSendWay(obj, paymentCode) {
		$(obj).addClass("active").siblings().removeClass("active");
		if (paymentCode == "SENDNOW") {
			//$("#takeSelfAdress").hide();
			location.reload();//刷新当前页面
			//$("#ordersTypeName").val("1");
			//$("#sendWaySpan").html("立即发货");
			//$("#addressDiv").show();
			//$("#transport").show();
			
			//动态计算运费
			//var tzm_youyunfei = parseFloat($("#tzm_yunfei").val()).toFixed(2);
			//$("#shipfee").html(" + ￥"+tzm_youyunfei);
			//$("#finalamountFont").html((${cartInfoVO.finalAmount?string('0.00')!'0.00'}).toFixed(2));
			
		} else if (paymentCode == "TAKESELF"){//上门自提
			//上门自提  设置千禧路为默认选中状态
  	    	$('input:radio[name=transportType]')[4].checked = true;
  	    	$("#transId").val(6);//默认千禧路
  	    	//end
			$("#expressDif").hide();
			$("#sendService_dw").hide();
			$("#transfer_One").hide();
			//显示提示信息
			$("#takeSelfAdress").show();
			//$("#transportType5").attr(checked,true);
			$('input:radio[name=transportType]')[3].checked = true;
			
			$("#ordersTypeName").val("3");
			$("#sendWaySpan").html("上门自提");
			$("#addressDiv").hide();
			$("#transport").hide();
			$("#shipfee").html(" + ￥0.00");
			$("#finalamountFont").html((${cartInfoVO.finalAmount?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'}).toFixed(2));
		    $("#addressId").val(0);
		}else{
			$("#ordersTypeName").val("4");
			$("#sendWaySpan").html("云仓托管");
			$("#addressDiv").hide();
			$("#transport").hide();
			$("#shipfee").html(" + ￥0.00");
			$("#finalamountFont").html((${cartInfoVO.finalAmount?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string('0.00')!'0.00'}).toFixed(2));
			$("#addressId").val(0);
		}
		$("#sendWayDiv").hide();
		$("#sendWayDownSpan").removeClass("addangle-down");
	}
	//不同配送方式进行样式和隐藏域的赋值
	function changeTransteType(obj,v){
		$(obj).addClass("active").siblings().removeClass("active");
		
		//物流中转
		if(v==1){
			$("#transportType8").prop("checked", true);
			changeFee();
		}
		//送货上门
		if(v==2){
			$("#transportType5").prop("checked", true);
			changeFee();
		}
		//快递配送
		if(v==3){
			$("#transportType0").prop("checked", true);
			changeFee();
		}
		//千禧路自提
		if(v==4){
			$("#transportType6").prop("checked", true);
			changeFee();
		}
		//大唐自提
		if(v==5){
			$("#transportType7").prop("checked", true);
			changeFee();
		}
	}
	//快递配送切换
	function changeKuaidiType(obj,v){
		$(obj).addClass("active").siblings().removeClass("active");
		
		//中通快递
		if(v==1){
			$("#transportType1").prop("checked", true);
			changeFee();
		}
		//顺丰快递
		if(v==2){
			$("#transportType3").prop("checked", true);
			changeFee();
		}
		//申通快递
		if(v==3){
			$("#transportType2").prop("checked", true);
			changeFee();
		}
		//邮政
		if(v==4){
			$("#transportType9").prop("checked", true);
			changeFee();
		}
	}
	
	// 选择支付方式
	function setPayment(obj, paymentCode) {
		$(obj).addClass("active").siblings().removeClass("active");
		if (paymentCode == "OFFLINE") {
			$("#paymentName").val("货到付款");
			$("#paymentCode").val("OFFLINE");
			$("#paymentCodeSpan").html("货到付款");
		} else if(paymentCode == "ONLINE"){
			$("#paymentName").val("在线支付");
			$("#paymentCode").val("ONLINE");
			$("#paymentCodeSpan").html("在线支付");
			//线下支付不可使用余额
			var  tzm_ttfee = $("input[name='tzm_ttfee']");
			var tzm_ttfee_bool = false;
			tzm_ttfee.each(function(i){
				if(parseFloat($(this).val()) > 0 ){//如果有套餐费，则此订单存在二次加工
					tzm_ttfee_bool = true;
				}
			})
			if(tzm_ttfee_bool){
				//console.dir("==============隐藏使用余额==============")
				$("#tzm_hideBalanceId").hide();
			}else{
				$("#tzm_hideBalanceId").show();
			}
			//end
		}else{
			$("#paymentName").val("线下打款");
			$("#paymentCode").val("OFFSEND");
			$("#paymentCodeSpan").html("线下打款");
			//线下支付不可以使用余额
			$("#tzm_hideBalanceId").hide();
		}
		$("#paymentCodeDiv").hide();
		$("#payDownSpan").removeClass("addangle-down");
	}
	
	// 选择订单备注
	function setRemark(obj, remarkCode) {
		$(obj).addClass("active").siblings().removeClass("active");
		if (remarkCode == "NEED") {
			$("#remark").val("需要发货单");
			$("#remarkCodeSpan").html("需要发货单");
		} else if(remarkCode == "NONEED"){
			$("#remark").val("不需要发货单");
			$("#remarkCodeSpan").html("不需要发货单");
		}else {
			$("#remark").val("不需要发货单");
			$("#remarkCodeSpan").html("不需要发货单");
			
		}
		$("#orderRemarkDiv").hide();
		$("#remarkDownSpan").removeClass("addangle-down");
	}
	
	// 点击发票信息
	function setInvoice(_this) {
		if ($("#invoiceDiv").is(':hidden')) {
			$("#invoiceDiv").show();
			$(_this).find(".fa-angle-down").addClass("addangle-down");
		} else {
			$("#invoiceDiv").hide();
			$(_this).find(".fa-angle-down").removeClass("addangle-down");
		}
	}
	
	// 点击新抬头
	function newInvoiceTitle(obj) {
		// 新发票抬头输入框隐藏，显示输入框，隐藏下拉框
		if ($("#newInvoiceTitleText").is(':hidden')) {
			$("#newInvoiceTitleText").show();
			$("#invoiceTitleSlt").hide();
			
			$(obj).html("取消");
		} else {
			$("#newInvoiceTitleText").hide();
			$("#invoiceTitleSlt").show();
			
			$(obj).html("新抬头");
		}
	}
	
	// 保存并使用发票信息
	function saveInvoice() {
		var title = "";
		// 新发票抬头输入框隐藏，说明是选择已有抬头
		if ($("#newInvoiceTitleText").is(':hidden')) {
			title = $("#invoiceTitleSlt").val();
			if (title == null || title == "") {
				$("#incoiceErr").html("请选择发票抬头");
				return;
			}
		} else {
			title = $("#newInvoiceTitleText").val();
			if (title == null || title == "") {
				$("#incoiceErr").html("请填写发票抬头");
				return;
			}
			if (title.length > 50) {
				$("#incoiceErr").html("发票抬头长度不能超过50");
				return;
			}
			
			// 如果新填写抬头，且抬头不是个人，则保存数据库
			if (title != "个人") {
				$.ajax({
					type : "POST",
					url :  domain+"/order/saveinvoice.html",
					data : {content:title},
					dataType : "json",
					// 保存抬头，为增加用户体验流畅不管是否保存成功不做任何操作
				});
			}
		}

		$("#invoiceTitleSpan").html("纸质发票-" + title);
		$("#invoiceTypeSpan").html("明细");
		$("#invoiceDiv").hide();
		
		// 给隐藏标签赋值
		var invoiceStatus = 0;
		if (title == "个人") {
			invoiceStatus = 2;
		} else {
			invoiceStatus = 1;
		}
		$("#invoiceStatus").val(invoiceStatus);
		$("#invoiceType").val("明细");
		$("#invoiceTitle").val(title);
		
		$("#invoiceDownSpan").removeClass("addangle-down");
	}
	
	// 不要发票
	function noInvoice() {
		$("#invoiceTitleSpan").html("不开发票");
		$("#invoiceTypeSpan").html("");
		$("#invoiceDiv").hide();
		
		$("#invoiceStatus").val(0);
		$("#invoiceType").val("");
		$("#invoiceTitle").val("");
		$("#invoiceDownSpan").removeClass("addangle-down");
	}
	
	// 点击使用积分
	function useIntegral(_this) {
		if ($("#integralDiv").is(':hidden')) {
			$("#integralDiv").show();
			$(_this).find(".fa-angle-down").addClass("addangle-down");
		} else {
			$("#integralDiv").hide();
			$(_this).find(".fa-angle-down").removeClass("addangle-down");
		}
	}
	
	// 确定使用积分
	function confirmUseIntegral() {
		// 获得使用积分数量
		var integral = parseInt($("#integralText").val());
		// 计算积分转换后金额
		var intMoney = (integral/exchangeRate).toFixed(2);
		
		// 计算实付款
		// 应付款
		var orderTotalPrice= parseFloat($("#sumPayPriceHidden").val());
		// 已使用的积分金额
		var usedIntegralHidden = parseFloat($("#usedIntegralHidden").val());
		// 减去上一次修改积分使用数量时产生的金额
		orderTotalPrice = orderTotalPrice + usedIntegralHidden;
		// 计算出本次修改积分数量后的应付款
		orderTotalPrice = orderTotalPrice - intMoney;
		
		// 使用积分后应付金额小于0报错
		if (orderTotalPrice < 0) {
			$("#integralErr").html("积分使用超过订单金额，请调整使用积分数量");
			return;
		}
		
		// 修改积分部分值
		$("#integralDiv").hide();
		$("#integral").val(integral);
		$("#useIntegralSpan").html(integral);
		$("#integralToMoney1").html("￥"+ intMoney);
		
		// 修改隐藏值
		$("#sumPayPriceHidden").val(orderTotalPrice.toFixed(2));
		$("#usedIntegralHidden").val(intMoney);
		
		// 修改显示值
		if (parseInt(intMoney) <= 0) {
			$("#integralUl").hide();
		} else {
			$("#integralUl").show();
		}
		$("#integralSpan").html("- ￥" + intMoney);
		$("#finalamountFont").html(orderTotalPrice.toFixed(2));
		
		$("#integralDownSpan").removeClass("addangle-down");
	}
	
  	var _max = ${(member.integral - ((member.integral)%(config.integralScale)))!0};
  	var exchangeRate = ${config.integralScale!0};
  	
  	// 增加使用积分
  	function plusIntegral() {
  		var orderTotalPrice = parseFloat($("#sumPayPriceHidden").val());
		if (orderTotalPrice <= 0) {
			return;
		}
		
		// 已使用的积分金额
		var usedIntegralHidden = parseFloat($("#usedIntegralHidden").val());
		// 减去上一次修改积分使用数量时产生的金额
		orderTotalPrice = orderTotalPrice + usedIntegralHidden;
		
  		var integral = $("#integralText").val();
  		// 增加后积分数
  		var plusIntegral = parseInt(integral) + exchangeRate;
  		if (plusIntegral > _max) {
  			// 增加后积分数大于最大积分数则不再增加
  			return;
  		}
  		
  		// 计算金额
		var intMoney = (plusIntegral/exchangeRate).toFixed(2);
  		// 增加后应付款小于等于0则不能再增加
  		if ((orderTotalPrice - intMoney) < 0) {
  			return;
  		}
  		
		$("#integralText").val(plusIntegral);
		$("#integral").val(plusIntegral);

		$("#integralToMoney2").html("￥"+ intMoney);
  	}
  	
 	// 减少使用积分
  	function minusIntegral() {
  		var integral = $("#integralText").val();
  		// 减少后积分数
  		var minusIntegral = parseInt(integral) - exchangeRate;
  		if (minusIntegral < 0) {
  			// 减少后积分数小于0则不再减少
  			return;
  		} else {
  			$("#integralText").val(minusIntegral);
  			$("#integral").val(minusIntegral);
  			
  			// 计算金额
  			var intMoney = (minusIntegral/exchangeRate).toFixed(2);
  			$("#integralToMoney2").html("￥"+ intMoney);
  		}
  	}
  	
 	// 点击使余额
 	function useBalance(_this) {
 		if ($("#balanceDiv").is(':hidden')) {
			$("#balanceDiv").show();
			$(_this).find(".fa-angle-down").addClass("addangle-down");
		} else {
			$("#balanceDiv").hide();
			$(_this).find(".fa-angle-down").removeClass("addangle-down");
		}
 	}
 	
 	// 确定使用余额
 	function confirmUseBalance() {
 		if ($("#selectOrderBalance").prop("checked")) {
 			var password = $("#balancePwd").val();
 			if (password == null || password =="") {
 				$("#balanceErr").html("请输入支付密码");
 				return;
 			}
 			$("#isBalancePay").val("true");
 			$("#useBlanceSpan").html("使用余额");
 			
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
 			
 			// 计算使用余额、实付款
 			// 应付款
			var orderTotalPrice= parseFloat($("#sumPayPriceHidden").val());
 			// 用户余额
			var balance = parseFloat("${(member.balance)!'0.00'}");
			balance = balance + Number(usedCreditHidden);
 			
 			// 上一次编辑余额时使用的余额
 			var lastUsedBalanceHidden = parseFloat($("#usedBalanceHidden").val());
 			// 还原应付金额
 			orderTotalPrice = orderTotalPrice + lastUsedBalanceHidden;
			var usedBalanceHidden = balance;
			var sumPayPrice = 0.00;//计算后的订单金额
			//订单金额
			sumPayPrice = orderTotalPrice - balance;
			sumPayPrice = sumPayPrice.toFixed(2);
			if(sumPayPrice<=0){
				sumPayPrice = 0.00;
				usedBalanceHidden = orderTotalPrice;
			}
			// 修改隐藏值
 			$("#usedBalanceHidden").val(usedBalanceHidden.toFixed(2));
 			$("#sumPayPriceHidden").val(sumPayPrice)
 			
 			// 修改显示值
 			$("#balanceUl").show();
 			$("#balanceSpan").html("- ￥" + usedBalanceHidden.toFixed(2));
 			$("#finalamountFont").html(sumPayPrice);
 		} else {
 			$("#balancePwd").val("");
 			$("#balanceErr").html("");
 			$("#isBalancePay").val("false");
 			$("#useBlanceSpan").html("不使用余额");
 			
 			// 计算使用余额、实付款
 			// 应付款
			var orderTotalPrice= parseFloat($("#sumPayPriceHidden").val());
 			// 用户余额
			var balance = parseFloat("${(member.balance)!'0.00'}");
 			// 使用的余额
			var usedBalanceHidden = $("#usedBalanceHidden").val();
 			if (usedBalanceHidden == null || usedBalanceHidden == "") {
 				usedBalanceHidden = 0;
 			}
			usedBalanceHidden = parseFloat(usedBalanceHidden);
			var sumPayPrice = 0.00;//计算后的订单金额
			//订单金额
			sumPayPrice = orderTotalPrice + usedBalanceHidden;
			sumPayPrice = sumPayPrice.toFixed(2);
			// 修改隐藏值
 			$("#usedBalanceHidden").val(0);
 			$("#sumPayPriceHidden").val(sumPayPrice)
 			
 			// 修改显示值
 			$("#balanceUl").hide();
 			$("#balanceSpan").html("- ￥0.00");
 			$("#finalamountFont").html(sumPayPrice);
 		}
 		$("#balanceDiv").hide();
 		
 		$("#balanceDownSpan").removeClass("addangle-down");
 	}
 	
 	// 提交订单 
	function submitOrder(obj){
		var transportType = $("input[name='transportType']:checked").val();
		//快递配送  必须选择一种快递方式 并且验证浙江杭州只能发顺丰
		if(transportType == 0){
			var LogisticType = $("input[name='LogisticType']:checked").val();
			if(typeof LogisticType == "undefined"){//提示请选择一种快递方式
				$.dialog('alert','提示','请选择快递方式',2000);
				return false;
			}else{
				/* var tzm_addressAll = $("#tzm_addressAll").val();
				var addressAllBool = false;
				if(tzm_addressAll.indexOf("浙江杭州")==0 && LogisticType == 1){
  	  		    	addressAllBool = true;
  	  		    } */
				//如果是浙江 就提示只能发顺丰
				/* if(addressAllBool){
					$.dialog('alert','提示','当前收货地址只能发顺丰',2000);
	  				return false;
	  			} */
			}
		}
		//end
		var transId = $("#transId").val();
		//如果用户没有选择配送方式，就弹出框提示一下。
		if(transId==""){
			$.dialog('alert','提示','请选择配送方式',2000);
			return false;
		}
		//end
		var sendArea = $("#sendArea").val();
		var servicePrice = $("#servicePrice").val();
		var ordersType;
		if(servicePrice > 0){
			ordersType = 5;
			$("#ordersTypeName").val(5);
		}else if(servicePrice = 0){
			ordersType = 1;
			$("#ordersTypeName").val(1);
		}
		//自提订单  没有真实姓名时则要求用户完善个人资料
		if(transId == 6 || transId == 7){
//			ordersType = 3;
//			$("#ordersTypeName").val(3);
			var realName = $("#realName").val();
			if(realName == ""){
				$.dialog('alert','提示','尊敬的用户,即将跳转个人资料,请完善您的真实姓名,谢谢',3000,function(){ window.location.href=domain+'/member/info.html'; });
				return false;
			}
		}
		if(transId == 5){
			//送货上门时判断地址是否为浙江地区
  		    var selectaddressAll = $("#tzm_addressAll").val();
  		    if(selectaddressAll.indexOf("浙江")<0){
  		    	$.dialog('alert','提示','送货上门只针对特定区域，请重新选择收货地址或者更改配送方式',2000);
  		    	$('#sendArea').focus();
  		    	return;
  		    }
			if(sendArea == 1){
				$.dialog('alert','提示','请选择配送区域',2000);
				return false;
			}else{
				$("#transId").val(transId + '' + sendArea);
			}
		}
		// 判断收货地址是否存在
		var addressId = $("#addressId").val();
		if(!$("#addressDiv").is(":hidden")){
			if(addressId == null || addressId == "" || addressId == 0){
				// alert("请添加或选择收货地址");
				$.dialog('alert','提示','请添加或选择收货地址',2000);
				return false;
			}
		}
		if(transportType == 7 || transportType == 8){
			$("#transId").val(transportType);
		}
		// 判断是否使用余额支付  异步调用账户密码
		var balance = $("#selectOrderBalance");
		var balancePwd = $("#balancePwd").val();
 		if($(balance).prop("checked")){
 			if(balancePwd == null || balancePwd == ""){
 				// alert("支付密码不能为空");
 				$.dialog('alert','提示','支付密码不能为空',2000);
 				return false;
 			}
 			// 验证支付密码
 			var checkpwd = checkBalancePwd(balancePwd);
 			if(!checkpwd){
 				return false;
 			}
 		}
 		
 		// 提交订单按钮屏蔽，避免重复提交
 		$(obj).attr("disabled",true);
 		// 提交loading
	 	$('body').append("<div id='submit_loading' class='purchase-loading'><div class='loading-cont'></div></div>");
		var actionUrl = domain + "/order/ordercommit.html";
		//begin tzm_ttfee获取name为tzm_ttfee的所有input值，判定是否有套餐费【仝照美】
			var  tzm_ttfee = $("input[name='tzm_ttfee']");
			var tzm_ttfee_bool = false;
			tzm_ttfee.each(function(i){
				if(parseFloat($(this).val()) > 0 ){//如果有套餐费，则此订单存在二次加工
					tzm_ttfee_bool = true;
				}
			})
			if(tzm_ttfee_bool){
				//设置为1代表次订单为二次加工订单，后台做特殊处理
				$("#tzm_twoJG").val("1");
			}
			//end
		var param = $("#orderForm").serialize() + "&balancePwd=" + balancePwd;
		$.ajax({
			type : "POST",
			dataType : "json",
			url : actionUrl,
			data : param,
			async:false,
			success : function(result) {
				if (result.success) {
					var data = result.data;
					var paySessionstr = data.paySessionstr;
					var goJumpPayfor = data.goJumpPayfor;
					var relationOrderSn = data.relationOrderSn;
					var payAmount = data.payAmount;
					
					 //跳转到成功页面
					 if (goJumpPayfor) {
						successUrl = domain+"/order/pay.html";
						newurl = successUrl + "?relationOrderSn=" + relationOrderSn +
								"&paySessionstr="+paySessionstr+"&rid=" + Math.random();
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
						// alert(result.message);
		 				$.dialog('alert','提示',result.message,2000);
						return;
					} else {
						$("#submit_loading").remove();
						showSubmitErrorMessage("亲爱的用户请不要频繁点击, 请稍后重试...");
						return;
					}
				}
			},
			error : function(error) {
				$("#order-submit").removeAttr("disabled");
				$("#submit_loading").remove();
				// alert("亲爱的用户请不要频繁点击, 请稍后重试...");
 				$.dialog('alert','提示','亲爱的用户请不要频繁点击, 请稍后重试...',2000);
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
				   		// alert("支付密码输错超过6次,请用其他方式支付");
		 				$.dialog('alert','提示','支付密码输错超过6次,请用其他方式支付',2000);
						return false;
				   	}
					if(!correct){
						// alert("支付密码不正确，您最多还可以输入"+(6-errcount)+"次");
		 				$.dialog('alert','提示',"支付密码不正确，您最多还可以输入"+(6-errcount)+"次",2000);
						return false;
					}
				}else {
					// alert(data.message);
	 				$.dialog('alert','提示',data.message,2000);
					return false;
				}
			},
			error : function() {
				// alert("验证密码失败！");
 				$.dialog('alert','提示','验证密码失败！',2000);
			}
		});
		return correct;
	}
	
	$(function(){
		//如果有二次加工，则此时不可以使用余额支付
		var  tzm_ttfee = $("input[name='tzm_ttfee']");
		var tzm_ttfee_bool = false;
		tzm_ttfee.each(function(i){
			if(parseFloat($(this).val()) > 0 ){//如果有套餐费，则此订单存在二次加工
				tzm_ttfee_bool = true;
			}
		})
		if(tzm_ttfee_bool){
			//console.dir("==============隐藏使用余额==============")
			$("#tzm_hideBalanceId").hide();
		}
		//end
		//如果单品立减，则隐藏 使用红包功能
		var  tzm_yisheng = $("input[name='tzm_yisheng']");
		var tzm_yisheng_bool = false;
		tzm_yisheng.each(function(i){
			if(parseFloat($(this).val()) > 0 ){//如果有套餐费，则此订单存在二次加工
				tzm_yisheng_bool = true;
			}
		})
		if(tzm_yisheng_bool){
			//console.dir("==============隐藏 使用红包==============")
			$("#coupon").hide();
		}
		//end
		var transportType = ${(transportType)!'8'}
		if(transportType == 1||transportType == 3){
			$("#expressDif").show();
			$("#transportType0").checked = true;
		}
	})
</script>
</body>
</html>