<#include "/front/commons/_headbig.ftl" />

<style>

.purchase-loading {
	width: 100%;
	height: 100%;
	min-height: 90px;
	position: fixed;
	left: 0;
	top: 0;
	_position: absolute;
	_width: expression(documentElement.clientWidth);
	_height: expression(documentElement.clientHeight);
	_top: expression(documentElement.scrollTop);
	background:
		url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/blank.gif') 0 0
		repeat;
	z-index: 3000
}

.purchase-loading .loading-cont {
	width: 100px;
	height: 100px;
	background:
		url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/loading04.gif')
		no-repeat;
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -50px 0 0 -50px
}
.tzm_spancss{
	font-size:14px;
	color:#333333;
}

            /*SELECT W/DOWN-ARROW*/
            select#sendArea
            {
               width                    : 330pt;
               height                   : 20pt;
               line-height              : 20pt;
               padding-right            : 20pt;
               text-indent              : 4pt;
               text-align               : left;
               vertical-align           : middle;
               box-shadow               : inset 0 0 3px #606060;
               border                   : 1px solid #acacac;
               -moz-border-radius       : 6px;
               -webkit-border-radius    : 6px;
               border-radius            : 5px;
               -webkit-appearance       : none;
               -moz-appearance          : none;
               appearance               : none;
               font-family              : Arial,  Calibri, Tahoma, Verdana;
               font-size                : 13pt;
               font-weight              : 500;
               color                    : #000099;
               cursor                   : pointer;
               outline                  : none;
            }
            select#sendArea option
            {
                padding             : 4px 10px 4px 10px;
                font-size           : 11pt;
                font-weight         : normal;
            }
            select#sendArea option[selected]{ font-weight:bold}
            select#sendArea option:nth-child(even) { background-color:#f5f5f5; }
            select#sendArea:hover {font-weight: 700;}
            select#sendArea:focus {box-shadow: inset 0 0 5px #000099; font-weight: 600;}
 
            /*LABEL FOR SELECT*/
            label#lblSelect{ position: relative; display: inline-block;}
            /*DOWNWARD ARROW (25bc)*/
            label#lblSelect::after
            {
                content                 : "\25bc";
                position                : absolute;
                top                     : 0;
                right                   : 0;
                bottom                  : 0;
                width                   : 20pt;
                line-height             : 20pt;
                vertical-align          : middle;
                text-align              : center;
                background              : #000099;
                color                   : #fefefe;
               -moz-border-radius       : 0 6px 6px 0;
               -webkit-border-radius    : 0 6px 6px 0;
                border-radius           : 0 6px 6px 0;
                pointer-events          : none;
            }
</style>

<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/member/myreciptaddress.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/areaSupport.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css">

<div class='w w1 header container'>
	<div id='logo' style="display: none;">
		<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html' target='_blank' class='link1'>
			<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/logo.jpg'>
		</a>
	</div>
	<div class='stepflex'>
		<dl class='first done'>
			<dt class='s-num'>1</dt>
			<dd class='s-text'>1.我的进货单<s></s><b></b>
			</dd>
		</dl>
		<dl class='normal doing'>
			<dt class='s-num'>2</dt>
			<dd class='s-text'>2.填写核对订单信息<s></s><b></b>
			</dd>
		</dl>
		<dl class='normal last'>
			<dt class='s-num'>3</dt>
			<dd class='s-text'>3.成功提交订单<s></s><b></b>
			</dd>
		</dl>
	</div>
</div>
<!--  -->

<@form.form id = "invoiceForm"  method="POST" autocomplete="off">
	<!-- 收货地址ID  -->
	<input type="hidden" id="addressId" name="addressId" value="${(defaultAddress.id)!''}"/>
	<input type="hidden" id="tzm_addressAll" value="${(defaultAddress.addAll)!''}"/>
	<input type="hidden" id='invoiceStatus' name="invoiceStatus" value="${(orderCommitVO.invoiceStatus)!''}"/>
	<!-- 发票内容 -->
	<input type="hidden" id='invoiceType' name="invoiceType" value="${(orderCommitVO.invoiceType)!''}"/>
	<!-- 发票抬头 -->
	<input type="hidden" id='invoiceTitle' name="invoiceTitle" value="${(orderCommitVO.invoiceTitle)!''}"/>
	<!-- 支付方式名称 -->
	<input type="hidden" id='paymentName' name="paymentName" value="${(orderCommitVO.paymentName)!''}"/>
	<!-- 支付方式code -->
	<input type="hidden" id='paymentCode' name="paymentCode" value="${(orderCommitVO.paymentCode)!''}"/>
	<!-- 服务费 -->
	<input type="hidden" id='servicePrice' name='servicePrice' value="${(cartInfoVO.servicePrice)!'0.00'}">
	<input type="hidden" id='isBalancePay' name="isBalancePay" value="false"/>
	<input type="hidden" id='balance' name="balance"/>
	<input type="hidden" id='balancePwd' name="balancePwd"/>
	<input type="hidden" id='integral' name="integral"/>
	<input type="hidden" id='sendArea1' name="sendArea1" value="1"/>
	<input type="hidden" id='flag' name="flag" value="0"/>
	<!-- 默认大唐物流中转  现在默认为空值，提交订单时判定，再提示SB用户要怎么选择配送方式 -->
	<input type="hidden" id='transportType' name='transportType' value="">
	<!-- 购物车中商品的总数量 -->
	<input type="hidden" id='totalCartAmount' name='totalCartAmount' value="${(cartInfoVO.totalCheckedNumber)}">
	<!-- 用户真实姓名 -->
	<input type="hidden" id="realName" value="${(member.realName)!''}"/>
	<!-- 记录优惠券使用信息 -->
	<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
		<#list cartInfoVO.cartListVOs as cartListVO>
			<input type="hidden" id='couponType${(cartListVO.seller.id)}' name="couponType${(cartListVO.seller.id)!0}" value="0"/>
			<input type="hidden" id='couponSn${(cartListVO.seller.id)}' name="couponSn${(cartListVO.seller.id)!0}"/>
			<input type="hidden" id='couponPassword${(cartListVO.seller.id)}' name="couponPassword${(cartListVO.seller.id)!0}"/>
			<input type="hidden" id='couponValue${(cartListVO.seller.id)}' name="couponValue${(cartListVO.seller.id)!0}" value="0"/>
		</#list>
	</#if>
	<input type="hidden" id='couponType0' name="couponType0" value="0"/>
	<input type="hidden" id='couponSn0' name="couponSn0"/>
	<input type="hidden" id='couponPassword0' name="couponPassword0"/>
	<input type="hidden" id='couponValue0' name="couponValue0" value="0"/>
	<!-- 记录使用了优惠券的商家ID，多个ID用英文逗号分隔 -->
	<input type="hidden" id='useCouponSellerIds' name="useCouponSellerIds" value=""/>
	<!-- 记录该订单是否存在二次加工，如果存在则要特殊处理 -->
	<input type="hidden" id="tzm_twoJG" name="twoJGFlag" value="0"/>
	<!-- 订单备注 -->
	<input type="hidden" id="remark" name="remark" value="需要发货单"/>
	<!-- 默认为直发 -->
	<input type="hidden" id="sendType" name="sendType" value="0"/>
</@form.form>
		<!--  -->
<div class='container'>
	<div class='w container'>
		<div class='m-order'>
			<div class='mt'>
				<h2>填写并核对信息</h2>
			</div>
			<div class='mc'>
		        <#if member.isTransferBussiness == '1'>
			       <div class='checkout-steps'>
						<div class='step-tit'>
							<h3>一件代发</h3>
						</div>
						<div class='step-cont'>
							<div class='remark-list'>
								<div class='list-cont'>
									<ul id='remark-list'>
										<li class='remark-li'>
											<div class='remark-item  item-selected online-remark' value="直发" pname="直发" onclick = "zhiFa()"><b></b>直发</div>
										</li>
										<li class='remark-li'>
											<div class='remark-item  online-remark' value="代发" pname="代发" onclick = "daiFa()"><b></b>代发</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
				  </div>
				</#if>
		        <div class='checkout-steps' id = "ordersSendType">
						<div class='step-tit'>
							<h3>配送方式</h3>
							<div class='extra-r'>
								<!--<a href='javascript:void(0);' class='ftx-05 addaddress' onclick="addOrEditAddress(0)">新增收货地址</a>-->
								 <input type = "radio" name="ordersType" id="LogisticsTransfer" style="vertical-align:middle;vertical-align:middle; margin-top:-2px;" value="8" />
								 <label for="LogisticsTransfer"  class='ftx-05' style="cursor:pointer;font-weight:100;" >物流中转（大唐）</label>
								 <span style="margin-left: 20px;"></span>
								 <input type = "radio" name="ordersType" id="takeSelf_qx" style="vertical-align:middle;vertical-align:middle; margin-top:-2px;" value="6" />
								 <label for="takeSelf_qx"  class='ftx-05' style="cursor:pointer;font-weight:100;" >千禧路自提</label>
								 <span style="margin-left: 20px;"></span>
								 <input type = "radio" name="ordersType" id="takeSelf_dt" style="vertical-align:middle;vertical-align:middle; margin-top:-2px;" value="7" />
								 <label for="takeSelf_dt"  class='ftx-05' style="cursor:pointer;font-weight:100;" >大唐市场自提</label>
								 <span style="margin-left: 20px;"></span>
								 <input type = "radio" name="ordersType" id="sendService" style="vertical-align:middle;vertical-align:middle; margin-top:-2px;" value="5"  />
								 <label for="sendService" class='ftx-05' style="cursor:pointer;font-weight:100" >送货上门</label>
								 <span style="margin-left: 20px;"></span>
								 <input type = "radio" name="ordersType" id="sendNow" style="vertical-align:middle;vertical-align:middle; margin-top:-2px;" value="1" />
								 <label for="sendNow"  class='ftx-05' style="cursor:pointer;font-weight:100;" >快递配送</label>
								 <span style="margin-left: 20px;"></span>
								 
								 <#--
								 <input type = "radio" name="ordersType" id="threeStore" value="4"   />
								 <label for="threeStore" class='ftx-05'  style="cursor:pointer;font-weight:100">云仓托管</label>
								 -->
							</div>
						</div>
				</div>
				<div class='checkout-steps'  id="transfer_One" style="height:190px;display:none;">
						<div class='step-tit'>
					      <span style="font-size:14px;color:#333333;float: left;margin-right: 82px;">中转说明</span>
					      <ul style="float: left;font-size:14px;color:#333333;">
					      	<li>1：温馨提示，物流速度会相对慢，请耐心等待。</li>
					      	<#--到大唐物流中转服务”，1000（含）双以上免费。1000双以下收20派送费，从大唐到您拍下地址的物流费用到付<span style="color:red;">到付</span>，-->
					      	<li>2：请在具体收货地址后注明物流中转地点，示例如下：</li>
					      	<li style="padding-left: 20px;">
					      		<span style="display: block;float: left;">街道地区：</span>
					      		<input disabled="disabled"  style="outline:none;resize:none;float: left;width: 490px;" value="&#12288;袍江路1号小麦（浙江）网络科技有限公司（中转地点：大唐镇山下朱一号）" />
					      	</li>
					      </ul>
					      <div style="clear: both;margin-left: 130px;margin-top: 20px;">
					      	<span style="font-size:14px;color:#666666;"><span class="icon icon-circle" style="color:#27CDF2;"></span>我已根据物流中转服务要求设置收货地址，如因中转地点错误导致货品不能送达，所造成的损失自行负责。</span>
					      </div>
					   </div>
				</div>
				
				<div class='checkout-steps'  id="sendService_dw" style="height:600px;display:none;">
					<div>
						<table class="tzm_spancss">
						  <tr>
						  	<td width='250'><strong>选择配送地区和收货地址:</strong></td>
	      				  </tr>
	      				  <tr>
						  	<td>
						  	<label id="lblSelect" style="margin-left: 150px;margin-top: 10px;">
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
					<div class='step-tit'>
					      <span class="tzm_spancss">
					      	<strong>送货说明</strong>
					      </span>
					      <span class="tzm_spancss" style="margin-left: 85px;">1、单次送货未达送货标准数量，需在收货时结清送货费用，计费标准如下。</span>
					      <table class="tzm_spancss" style="margin-left: 140px;">
					      	<thead>
					      		<tr>
					      			<th width="100">送货区域</th>
					      			<th width="250">送货范围</th>
					      			<th width="150">配送费（元/箱）</th>
					      			<th width="150">最低收费标准（元）</th>
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
					      <span class="tzm_spancss" style="margin-left: 135px;">2、为回馈广大客户，在”大袜网”上线公测期间符合送货范围及以下条件的，送货服务费全免。</span>
					      <table class="tzm_spancss" style="margin-left: 140px;">
					      	<thead>
					      		<tr>
					      			<th width="100">送货区域</th>
					      			<th width="350">送货范围</th>
					      			<th width="150">单次送货数量大于等于</th>
					      		</tr>
					      	</thead>
					      	<tbody>
					      		<tr>
					      			<td>一区</td>
					      			<td>诸暨三个街道、大唐、草塔</td>
					      			<td>1000双</td>
					      		</tr>
					      		<tr>
					      			<td>二区</td>
					      			<td>枫桥、王家井、牌头、五泄</td>
					      			<td>5000双</td>
					      		</tr>
					      		<tr>
					      			<td>三/四区</td>
					      			<td>义乌、浦江、上述地区之外诸暨市内其他地区</td>
					      			<td>20000双</td>
					      		</tr>
					      	</tbody>
					      </table>
					      <div style="clear: both;margin-left: 140px;margin-top: 20px;">
					      	<span style="font-size:14px;color:#666666;"><span class="icon icon-circle" style="color:#27CDF2;"></span>我已根据物流中转服务要求设置收货地址，如因中转地点错误导致货品不能送达，所造成的损失自行负责。</span>
					      </div>
					</div>
				</div>
				
				<div class='checkout-steps'  id="transfer_qx" style="height:500px;display:none;">
					<div class='step-tit'>
					      <div>
						      <span class="tzm_spancss" style="display: block;float: left;margin-right:85px; ">
						      	<strong>自提提示</strong>
						      </span>
						      <table class="tzm_spancss" style="float: left;">
						      	<tr>
						      		<td width="150"><span class="icon  icon-map-marker" style="color: #27CDF2;font-size: 14px;"></span>千禧路自提货点</td>
						      		<td width="300"></td>
						      	</tr>
						      	<tr>
						      		<td>地址：</td>
						      		<td>浙江省诸暨市千禧路20号大袜云仓北门</td>
						      	</tr>
						      	<tr>
						      		<td>联系人：</td>
						      		<td>张建涛</td>
						      	</tr>
						      	<tr>
						      		<td>联系电话：</td>
						      		<td>0575-87599013&#12288;&#12288;&#12288;&#12288;15215936158</td>
						      	</tr>
						      </table>
					      	<hr style="border:2px solid #e8e8e8;clear: both;">
					      	<table class="tzm_spancss" style="margin-left: 140px;">
					      		<tr>
					      			<td width="150"><strong>注意：仓储出库时间</strong></td>
					      			<td width="300"></td>
					      		</tr>
					      		<tr>
					      			<td>裸袜</td>
					      			<td>当日15:00前接单&#12288;&#12288;&#12288;&#12288;次日10点前出库</td>
					      		</tr>
					      		<tr>
					      			<td></td>
					      			<td>当日15:00后接单&#12288;&#12288;&#12288;&#12288;次日17点前出库</td>
					      		</tr>
					      		<tr>
					      			<td>二次加工</td>
					      			<td>8000双以内&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;24小时内出库</td>
					      		</tr>
					      		<tr>
					      			<td></td>
					      			<td>8000-15000双&#12288;&#12288;&#12288;&#12288;&nbsp;36小时内出库</td>
					      		</tr>
					      		<tr>
					      			<td></td>
					      			<td>15000双以上&#12288;&#12288;&#12288;&#12288;&#12288;72小时内出库</td>
					      		</tr>
					      	</table>
					      	<div class="tzm_spancss" style="margin-left: 140px;">1：请记录订单编号和账号，向联系人出示。</div>
					      	<div class="tzm_spancss" style="margin-left: 140px;">2：请在联系人的陪同下进入园区，出区时请主动向门卫出示提货手续。</div>
				      </div>
					</div>
				</div>
				<#-- 大唐自提点 -->
				<div class='checkout-steps'  id="transfer_dt" style="height:500px;display:none;">
				<div class='step-tit'>
				      <div>
					      <span class="tzm_spancss" style="display: block;float: left;margin-right:85px; ">
					      	<strong>自提提示</strong>
					      </span>
					      <table class="tzm_spancss" style="float: left;">
					      	<tr>
					      		<td width="150"><span class="icon  icon-map-marker" style="color: #27CDF2;font-size: 14px;"></span>大唐袜业市场提货点</td>
					      		<td width="300"></td>
					      	</tr>
					      	<tr>
					      		<td>地址：</td>
					      		<td>袜业市场二楼西八街2412号-2415号</td>
					      	</tr>
					      	<tr>
					      		<td>联系人：</td>
					      		<td>孟丹</td>
					      	</tr>
					      	<tr>
					      		<td>联系电话：</td>
					      		<td>0575-89000305&#12288;&#12288;&#12288;&#12288;13706856474</td>
					      	</tr>
					      </table>
				      	<hr style="border:2px solid #e8e8e8;clear: both;">
				      	<table class="tzm_spancss" style="margin-left: 140px;">
				      		<tr>
				      			<td width="150"><strong>注意：仓储出库时间</strong></td>
				      			<td width="300"></td>
				      		</tr>
				      		<tr>
				      			<td>裸袜</td>
				      			<td>当日15:00前接单&#12288;&#12288;&#12288;&#12288;次日10点前出库</td>
				      		</tr>
				      		<tr>
				      			<td></td>
				      			<td>当日15:00后接单&#12288;&#12288;&#12288;&#12288;次日17点前出库</td>
				      		</tr>
				      		<tr>
				      			<td>二次加工</td>
				      			<td>8000双以内&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;24小时内出库</td>
				      		</tr>
				      		<tr>
				      			<td></td>
				      			<td>8000-15000双&#12288;&#12288;&#12288;&#12288;&nbsp;36小时内出库</td>
				      		</tr>
				      		<tr>
				      			<td></td>
				      			<td>15000双以上&#12288;&#12288;&#12288;&#12288;&#12288;72小时内出库</td>
				      		</tr>
				      	</table>
				      	<div class="tzm_spancss" style="margin-left: 140px;">1：请记录订单编号和账号，向联系人出示。</div>
				      	<div class="tzm_spancss" style="margin-left: 140px;">2：请在联系人的陪同下进入园区，出区时请主动向门卫出示提货手续。</div>
			      </div>
				</div>
			  </div>
					
				<!-- 物流类型 -->
		        <div class='checkout-steps' id="tiantian3">
						<div class='step-tit'>
							<h3>运送方式</h3>
							<div class='extra-r'>
								<!--<a href='javascript:void(0);' class='ftx-05 addaddress' onclick="addOrEditAddress(0)">新增收货地址</a>-->
								 <input type = "radio" name="transportType" id="transportType1" style="vertical-align:middle;vertical-align:middle; margin-top:-2px;" value="1"  />
								 <label for="transportType1"  class='ftx-05' style="cursor:pointer;font-weight:100;" onchange="changeTransType1()">中通快递</label>
								 <span style="margin-left: 20px;"></span>
								 <#--<input type = "radio" name="transportType" id="transportType2" style="vertical-align:middle;vertical-align:middle; margin-top:-2px;" value="2" />
								 <label for="transportType2" class='ftx-05'  style="cursor:pointer;font-weight:100">申通快递</label>
								 <span style="margin-left: 20px;"></span>
								 -->
								 <input type = "radio" name="transportType" id="transportType3" style="vertical-align:middle;vertical-align:middle; margin-top:-2px;" value="3" />
								 <label for="transportType3" class='ftx-05'  style="cursor:pointer;font-weight:100">顺丰快递</label>
								 <#--<span style="margin-left: 20px;"></span>
								 <input type = "radio" name="transportType" id="transportType9" style="vertical-align:middle;vertical-align:middle; margin-top:-2px;" value="9" />
								 <label for="transportType9" class='ftx-05'  style="cursor:pointer;font-weight:100">邮政</label>
								 <span style="margin-left: 10px;"></span>
								 <span style="color: #FF3C23;font-size: 14px;"><span class="icon icon-warning-sign"></span>
								 	受G20期间对快递区域限制的影响,发往杭州的快递,9月6日之前只能发顺丰,9月7日普通快递恢复正常.其他区域快递和物流正常发运。
								 </span>
								 -->
								 <#--
							 	 	<input type = "radio" name="transportType" id="transportType5" value="5" />
						    	 	<label for="transportType5" class='ftx-05' style="cursor:pointer;font-weight:100" >自配送</label>
								 	<input type = "radio" name="transportType" id="transportType2" value="2" />
							    	<label for="transportType2" class='ftx-05' style="cursor:pointer;font-weight:100" >自配送</label>
								 	<span style="margin-left: 20px;"></span>
								 	<input type = "radio" name="transportType" id="transportType4" value="4"   />
								 	<label for="transportType4" class='ftx-05'  style="cursor:pointer;font-weight:100">物流</label>
								 -->
							</div>
						</div>
						<div id="tzm_zpsong" style="border:0px solid red;display: none;">
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
				<!-- end -->
				<div class='checkout-steps'>
					<div class='step-tit'  id="tiantian1">
						<h3>收货人信息</h3>
						<div style="margin-left: 12%;">
							<a href='javascript:void(0);' class='ftx-05 addaddress' onclick="addOrEditAddress(0)">新增收货地址</a>
						</div>
					</div>
					<div class='step-cont'  id="tiantian2">
						<div id='consignee-addr'>
							<div class='consignee-cont consignee-off' style='position: relative;' id='consignee1'>
								<ul class="consignee-list" id='consignee-list' style='top:0px;position:relative;'>
									<#if addressList??>
										<#list addressList as address>
											<li style='display: list-item;' class='order-select' value="${(address.id)!''}" name="${(address.addAll)!''}">
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
							<span class='ftx-05'>更多地址<span class="icon icon-double-angle-down"></span></span>
						</div>
						<div class='more-addr switch-off hide' id='consigneeItemHideClick' onclick='hide_ConsigneeAll()'>
							<span class='ftx-05'>收起地址<span class="icon icon-double-angle-up"></span></span>
						</div>
						<!-- end -->
					</div>
					<div class='hr'></div>
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
												<div class='payment-item  offline-payment' value="OFFLINE" pname="货到付款"><b></b>货到付款</div>
											</li>
											<li class='payment-li'>
												<div class='payment-item  item-selected online-payment' value="ONLINE" pname="在线支付"><b></b>在线支付</div>
											</li>
											<li class='payment-li'>
												<div class='payment-item  online-payment' value="OFFSEND" pname="线下打款"><b></b>线下打款</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class='hr'></div>
							<div class='step-tit'>
								<h3>订单备注</h3>
							</div>
							<div class='step-cont'>
								<div class='remark-list'>
									<div class='list-cont'>
										<ul id='remark-list'>
											<li class='remark-li'>
												<div class='remark-item  online-remark' value="不需要发货单" pname="不需要发货单"><b></b>不需要发货单</div>
											</li>
											<li class='remark-li'>
												<div class='remark-item  item-selected online-remark' value="需要发货单" pname="需要发货单"><b></b>需要发货单</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class='hr'></div>

							<!--送货清单 -->
							<div class='step-tit'>
								<h3>送货清单</h3>
								<div class='extra-r'>
									<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/cart/detail.html' class='return-edit ftx-05'>返回修改进货单</a>
								</div>
							</div>
							<div class='step-cont'>
							<#if cartInfoVO?? && (cartInfoVO.cartListVOs??) && (cartInfoVO.cartListVOs?size &gt; 0) >
							<#list cartInfoVO.cartListVOs as cartListVO>
								<#assign seller = cartListVO.seller />
								<div class='shopping-lists'>
									<div class='order-common-list'>
										<div class='goods-tit'>
<!-- 											<h4 id='vendor_name_h'>商家：${(seller.sellerName)!'' }</h4> -->
											<#--
											<span class="coupon-button seller_hb" id="coupon" value="${(seller.id)!0}" orderamount="${(cartListVO.sellerCheckedDiscountedAmount)?string('0.00')!'0.00'}">使用红包</span>
											<div class="full-off" id="couponInfoDiv${(seller.id)!0}"></div>
											-->
										</div>
										<div class='goods-suit goods-suit-tit'>
											<div class='goods-suit-tit'>
												<#if cartListVO.actFull?? >
												<span class='sales-icon'>满减</span>
												<strong>
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
					                        		&nbsp;(已减:${(cartListVO.orderDiscount)?string('0.00')!"0.00"}元现金)
					                        		</#if>
				                        		</strong>
				                        		</#if>
											</div>
											<#-- 小计信息
											<div>
					                        	&nbsp;&nbsp;&nbsp;小计:${(cartListVO.sellerCheckedDiscountedAmount)?string('0.00')!"0.00"}
					                        	<span class='ftx-01'>已减:
			                                        <span class='moneyPreferential'>${(cartListVO.sellerCheckedDiscounted)?string('0.00')!'0.00'}</span> 
			                                    </span>
					                        </div>
					                        -->
										</div>
											
										<#if (cartListVO.cartList??) && (cartListVO.cartList?size>0) >
					                    <#list cartListVO.cartList as cart>
					                    	<#assign product = cart.product />
					                        <#assign productGoods = cart.productGoods />
											<!--  单品 -->
											<div class='goods-items '>
												<div class='goods-item goods-item-extra'>
													<div class='p-img'>
														<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(cart.productId)!0}.html' target="_blank">
															<#if product.isNorm == 2>
															<img width="80" height="80" style="margin-top: -5px;" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${productGoods.images!''}">
															<#else>
															<img width="80" height="80" style="margin-top: -5px;" src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${product.masterImg!''}">
															</#if>
														</a>
													</div>
													<div class='goods-msg'>
														<div class='goods-msg-gel'>
															<div class='p-name'>
																<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(cart.productId)!0}.html' target='_blank'>${product.name1!''} ${cart.specInfo!''}</a>
																<#if product.sellerId == 0>
																<span style="vertical-align:top;display:inline-block;width:16px;height:16px;BACKGROUND: url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/redpacket.png') no-repeat center 50%;"></span>
																<#else>
																<span></span>
																</#if>
															</div>
															<div class='p-price'>
																<strong class=''>￥${(productGoods.mallPcPrice)?string('0.00')!0}</strong>
																<br>
																<#if product??&&product.priceStatus==3>
																	<strong class=''>￥${(product.scatteredPrice)?string('0.00')!0}</strong>
																</#if>
															</div>
															<div class='p-num'>
																<span> x ${(cart.count)!'1' }</span>
															</div>
															<div class='p-state'>
																<span>
																	<#if (productGoods.productStock)?? && (productGoods.productStock &gt; 0)>
																		有货
																	<#else>
																		<span style="color:red">无货</span>
																	</#if>
																</span>
															</div>
															<div class='p-total'>
																<strong>
																	${(cart.currDiscountedAmount)?string('0.00')!0}
																</strong>
																<br>
																<span class='productStock'>已省:${(cart.currDiscounted)?string('0.00')!0}</span>
																<input type="hidden" name="tzm_yisheng" value="${(cart.actSingle.discount)!'0.0'}"/>
																<br>
																<span class='productStock'>套餐费:+${(cart.packageFee)!'0.0'}</span>
																<#if cart.isDabiaowa?? && cart.isDabiaowa==1> <!-- 如果是打标袜则不要将其加工费用设置为0 -->
																	<#else>
																	<input type="hidden" name="tzm_ttfee" value="${(cart.packageFee)!'0.0'}"/>
																</#if>
															</div>
														</div>
													</div>
													<div class='' style='float:left;'>
														<span class='ftx-04'><!-- 7天无理由退货 --></span>
													</div>
												</div>
											</div>
										</#list>
										</#if>
									</div>
								</div>
							</#list>
							</#if>	

							</div>
							<div class='hr'></div>
							<!-- 发票 -->
							<div class='step-tit' style="display: none;">
								<h3>发票信息</h3>
							</div>
							<div class='step-content' style="display: none;">
									<div class='invoice-cont'>
										<span class='mr10 invoice_title' > 不开发票</span>&nbsp;
										<span class='mr10 invoice_title_show' ></span>&nbsp;
										<span class='mr10 invoice_content_show'></span>&nbsp;
										<a href='javascript:void(0);' class='ftx-05 invoice-edit'>修改</a>

									</div>
							</div>
							<div class='hr'></div>
							<!--  -->
							<div class='order-coupon' id="tzm_hideBalanceId">
								<!--余额支付 -->
								<div class='item' id='balance-div'>
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
													<label id='canUsedBalanceId'>
													<input type='checkbox' id='selectOrderBalance' autocomplete="off">
													使用余额（账户当前余额：${(member.balance)!'0.00' }元）
													</label>
												</div>
											</div>
										</div>
									</div>
									<!-- 记录一个hidden值方便计算 -->
									<input type="hidden" id="usedBalanceHidden" autocomplete="off"/>
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
								</div>
								<!-- end -->
								
								<!--使用积分 -->
								<#if config?? && config.integralScale?? && config.integralScale &gt; 0>
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
								</#if>
								<!--  结束-->
								<!--  -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class='order-summary'>
			<div class='statistic' style='float:right;width: 440px;'>
				<#--红包
				<div class='list' style="margin-top:15px">
					<span class="full-off" id="couponInfoDiv0">
					</span>
					<div class="coupon-button dww_tyhb" id="coupon" value="0" style="margin-right:20px" orderamount="${(cartInfoVO.checkedCartAmount)?string("0.00")!'0.00'}">使用红包</div>
				</div>
				-->
				<div class='list'>
					<span>
						<em class='ftx-01'>${cartInfoVO.totalCheckedNumber!0}</em>
						 件商品，总商品金额（原价）：
					</span>
					 <em class='price' id='warePriceId'>￥${(cartInfoVO.checkedCartAmount)?string("0.00")!'0.00'}</em>
					 <input type="hidden" value="${(cartInfoVO.checkedCartAmount-cartInfoVO.servicePrice)?string("0.00")!'0.00'}" id="tzm_warePriceId"/>
				</div>
				<div class='list'>
					<span>
						 总商品金额（优惠）：
					</span>
					 <em class='price' id='discountAmountPriceId'>￥${(cartInfoVO.checkedDiscountedCartAmount)?string("0.00")!'0.00'}</em>
				</div>
				<div class='list'>
					<span>活动节省：</span>
					<em class='price' id='discountPriceId'>
						<font style="color:#FF6600"> - ￥${(cartInfoVO.checkedDiscountedAmount)?string("0.00")!'0.00'} </font>
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
						<font style="color:#FF6600" id="shipfee"> + ￥${(cartInfoVO.logisticsFeeAmount)?string("0.00")!'0.00'} </font>
						<input id="tzm_yunfei" value="${(cartInfoVO.logisticsFeeAmount)?string("0.00")!'0.00'} "/>
					</em>
				</div>
				<div class='list'>
					<span>加工费：</span>
					<em class='price' id='packagePriceId'>
						<font style="color:#FF6600"> + &yen;${(cartInfoVO.servicePrice)?string("0.00")!'0.00'} </font>
					</em>
				</div>
				<div class='list'>
					<span>辅料费：</span>
					<em class='price' id='labelPriceId'>
						<font style="color:#FF6600"> + &yen;${(cartInfoVO.labelPrice)?string("0.00")!'0.00'} </font>
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
					<em class='price' id='sumPayPriceId'>￥${(cartInfoVO.finalAmount)?string("0.00")!'0.00'}</em>
					<!-- 记录一个hidden值方便计算 -->
					<input type="hidden" id="sumPayPriceHidden" value="${(cartInfoVO.finalAmount)?string('0.00')!'0.00'}" autocomplete="off"/>
				</div>
			</div>
		</div>
		<div class='clr'></div>
		<div class='trade-foot'>
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
								<strong id='payPriceId'>￥${(cartInfoVO.finalAmount)?string("0.00")!'0.00'}</strong>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div id="takeselfid" class='consignee-foot'  style="display:none;">
			   <div id='hideInfo'>
			       <p>提货地址：<span>浙江省诸暨市千禧路20号大袜云仓北门</span></p>
  			       <p>联系电话：<span>0575-87599013      15215936158</span></p>
  			    </div>
		    </div>
			<div class='consignee-foot'  id = "showOrhidden">
			    <div id="showInfo">
				<#if defaultAddress??>
         				<#assign adds = defaultAddress.addAll+defaultAddress.addressInfo>
					<p>
          				寄送至： <span id="addressDetail" title="${adds}">${commUtil.substring(adds,30)}</span>
       				</p>
       				<p>
        				收货人：<span id="consigneeName">${(defaultAddress.memberName)!'' }</span>
        					<span id="consigneeMobile">${commUtil.hideMiddleStr((defaultAddress.mobile)!'',3,4)}</span>
       				</p>
      			</#if>
      		  </div>
			</div>
		</div>
	</div>
</div>
<!--页脚 -->
<#include "/front/commons/_endbig.ftl" />

<!-- 收货地址显示区 -->
<div class='background-layer' id='Harvest'>
</div>
<!-- end -->
<!-- 修改发票 -->
<div class='background-layer' id='mainId'>
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

<!-- 使用红包弹出框-->
<div class="popWin" style="display:none">
    <div class="popWin_box bounce">
    	<!-- 记录当前的优惠券填写类型 1为选择优惠券，2为填写优惠码 -->
    	<input type="hidden" id="currType" value="1" autocomplete="off">
    	<!-- 记录当前选择优惠券的商家ID -->
    	<input type="hidden" id="currSellerId" value="0" autocomplete="off">
    	<!-- 记录当前选择优惠券的商家订单的订单金额 -->
    	<input type="hidden" id="currSellerOrderAmount" autocomplete="off">
    	<!-- 记录所有优惠券的优惠金额和，用于显示 -->
    	<input type="hidden" id="couponValueSum" value="0" autocomplete="off">
        <div class="tit">
        	<div class="tit-top">
				<span>已绑定红包：</span>
				<select id="selectCoupon" name="selectCoupon" class="selectCoupon" style="width: 55%;">
				</select>
			</div>
			<div class="coupon-msg-sel"></div>
        </div>
        <div class="off-line" style="display:none">
			<span>请输入优惠码及密码</span>
			<form action="">
				序列号：<input type="text" name="couponSn" id="couponSn" style="width:200px"><br>
				<em class="prompting">请输入正确的序列号</em><br>
				密&nbsp;&nbsp;&nbsp;码：<input type="password" id="couponPassword" name="couponPassword" style="width:200px" autocomplete="off">
			</form>
			<div class="coupon-msg-inp"></div>
		</div>
		<button class="btn_change" style="display: none;">输入优惠码</button>
        <div class="con clearfix">
            <a class="cancel" id="couponCancel" href="javascript:;" >取消</a>
            <a class="submit" id="couponSubmit" href="javascript:;" >确定</a>
            <a class="submitnotuse" id="couponNotUse" href="javascript:;" >取消使用红包</a>
        </div>
    </div>
</div>

<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/order.js'></script>
<script type="text/javascript">

	//点击优惠券弹出层
	$('.seller_hb').on('click',function(){
		//获得总商品金额（原价）tzm_warePriceId
		var totalWarePrice = $("#tzm_warePriceId").val();
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
		//	data : {sellerId:sellerId,totalWarePrice:totalWarePrice},
			dataType : "json",
			success : function(data) {
				if (data.success) {
	                var selectOption = '<option value ="">-- 请选择 --</option>'
	                var selectOption = ''
	                if(data.data.length==0){
	                	selectOption += "<option value=''>很抱歉，该系列商品没有使用的红包。</option>";
	                }else{
		                $.each(data.data, function(i, couponUser){
			                	var txtInfo = couponUser.couponSn + " ";
			                	if (parseFloat(couponUser.minAmount) > 0) {
			                		txtInfo += "满" + couponUser.minAmount + "元 ";
			                	}
			                	txtInfo += "抵" + couponUser.couponValue + "元现金";
			                	selectOption += "<option value=" + couponUser.couponSn + ">" + txtInfo + "</option>";
		                })
	                }
	                $("#selectCoupon").append(selectOption);
	            } else {

	            }
			}
		}); 
	});
	
	$(".dww_tyhb").on('click',function(){
	    //获得总商品金额（原价）tzm_warePriceId
		var totalWarePrice = $("#tzm_warePriceId").val();
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
		$("#currType").val(1);
		$("#currSellerId").val(0);
		$("#currSellerOrderAmount").val($(this).attr("orderamount"));
		// 获取已绑定的优惠码
		$.ajax({
			type : "GET",
			url :  domain+"/order/getsellercoupon.html",
			data : {sellerId:0},
			dataType : "json",
			success : function(data) {
				if (data.success) {
	                var selectOption = '<option value ="">-- 请选择 --</option>'
	                var selectOption = ''
	                if(data.data.length==0){
	                	selectOption += "<option value=''>很抱歉，该系列商品没有使用的红包。</option>";
	                }else{
		                $.each(data.data, function(i, couponUser){
			                	var txtInfo = couponUser.couponSn + " ";
			                	if (parseFloat(couponUser.minAmount) > 0) {
			                		txtInfo += "满" + couponUser.minAmount + "元 ";
			                	}
			                	txtInfo += "抵" + couponUser.couponValue + "元现金";
			                	selectOption += "<option value=" + couponUser.couponSn + ">" + txtInfo + "</option>";
		                })
	                }
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
	// 取消使用红包
	$('#couponNotUse').on('click',function(){
		var sellerId = $("#currSellerId").val();
		$("#couponType" + sellerId).val(0);
        $("#couponSn" + sellerId).val("");
        $("#couponPassword" + sellerId).val("");
        $("#couponInfoDiv" + sellerId).html("");
        
     	// 计算金额
        calculateCouponValue(sellerId, 0,1);
        
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
		var orderAmount =  $("#currSellerOrderAmount").val();
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
	                $("#couponInfoDiv" + sellerId).html("<font color='red' size='3pt'><b>" + couponInfoDiv + "</b></font>");
	                
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
	function calculateCouponValue(sellerId, newCouponValue,type) {
		// 订单金额
		var sumPayPriceHidden= $("#sumPayPriceHidden").val();
		//判断 如果 自配送则运费不加
		var boolFlag = $("#transportType").val();
		var tzm_yunfei;
		
		if(boolFlag==5 && type !=1){
			tzm_yunfei = $("#tzm_yunfei").val();
			sumPayPriceHidden = parseFloat(sumPayPriceHidden)-parseFloat(tzm_yunfei)
		}
		//end
		// 如果修改使用的优惠券，则需要重新计算订单应付金额
		var couponValueOld = $("#couponValue" + sellerId).val();
		// 已使用红包的金额和
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
			// var orderTotalPrice=  parseFloat("${cartInfoVO.cartAmount!'0.00'}");
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
	$("#selectOrderBalance").click(function(){
		//如果余额小于等于0 那么不允许选中
		<#if member??&&member.balance??>
			<#if member.balance<=0>
				<#if !memberCredit?? || memberCredit.surplus <= 0 >
				$(this).prop("checked", false);
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
			$("#balance_pwd_div").show();
			// 计算账户余额及订单金额  订单金额 = 订单金额-优惠 -使用余额
			// 1、订单金额小于或等于余额 则余额使用显示订单金额 ，
			// 2、订单金额大于余额，则余额使用显示余额金额 
			// 余额
			var balance = parseFloat('${(member.balance)!'0.00'}');
			var userblanance = balance;
			//balance = balance >= 0?balance:0;

			var creditAmount = 0;
			<#if memberCredit?? && memberCredit.surplus &gt;0 >
			var creditTotal = Number('${(memberCredit.surplus)!0}');
			//余额+可赊账额度 （如果赊账已结清，则余额为0）
			var balanceable = creditTotal.toFixed(2);
// 			if(userblanance >0){
// 				//用户还有余额
// 				balanceable = balanceable+Number(userblanance);
// 			}
			//只有余额小于订单金额且可支付余额大于等于订单金额时才会使用赊账
			if(Number(balance) < Number(orderTotalPrice) && balanceable >= Number(orderTotalPrice)){
				if($(this).siblings("span").length>0){
					$(this).siblings("span").remove();
				}
				$(this).parent().append("<span style='color: red;'>您的账户可赊账<b>${memberCredit.surplus}</b>元，使用余额支付后，您的账户会产生欠款，请在规定时间内还清欠款。</span>");
				//赊账金额
				creditAmount = Number(orderTotalPrice);
// 				if(userblanance >0){
// 					//余额不足的部分
// 					creditAmount = creditAmount - userblanance;
// 				}
				
				//赊账金额大于赊账总额
				//赊账金额 ＝ 赊账总额
// 				if(creditAmount > creditTotal){
// 					creditAmount = creditTotal;
// 				}
				$("#creditPriceListDiv").show();
				$("#creditPay").html(creditAmount);
				$("#usedCreditHidden").val(creditAmount);
			} else if(balanceable < Number(orderTotalPrice)){
				$(this).siblings("span").remove();
				$(this).parent().append("<span style='color: red;'>您的账户余额（包含赊账）可使用总额为<b>"+balanceable+
					"</b>元，其中余额欠款<b>"+userblanance+"</b>元，赊账剩余额度<b>${memberCredit.surplus}</b>元，不足以支付当前订单，请充值或还清欠款。</span>");
				$(this).prop("checked", false);
				$("#balance_pwd_div").hide();
				return;
			}
			</#if>
			
			usedBalanceHidden = balance;
			var sumPayPrice = 0.00;//计算后的订单金额
			//订单金额
			sumPayPrice = orderTotalPrice - balanceable;
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
				$("#balancePay").html(0);
				//隐藏域赋值
				$("#balance").val(balance);
			}
			//金额计算显示
			$("#balancePriceListDiv").show();

			$("#isBalancePay").val('true');
		}else{
			<#if memberCredit?? && memberCredit.surplus &gt;0 >
			$(this).siblings("span").remove();
			$("#creditPriceListDiv").hide();
			</#if>
			
			// 不使用余额，把余额加回去
			orderTotalPrice = parseFloat(orderTotalPrice) + 
				parseFloat(usedBalanceHidden);
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
	});
	
	//支付方式选中
	$(".payment-item").click(function(){
		$(this).addClass("item-selected").parent().siblings().children().removeClass("item-selected");
		//赋值
		$("#paymentCode").val($(this).attr("value"));
		$("#paymentName").val($(this).attr("pname"));
		//如果为线下打款则隐藏使用余额 tzm_hideBalanceId
		var temp_flag = $(this).attr("value");
		if(temp_flag=="ONLINE"){
			var  tzm_ttfee = $("input[name='tzm_ttfee']");
			var tzm_ttfee_bool = false;
			tzm_ttfee.each(function(i){
				if(parseFloat($(this).val()) > 0 ){//如果有套餐费，则此订单存在二次加工
					tzm_ttfee_bool = true;
				}
			})
			if(tzm_ttfee_bool){
				$("#tzm_hideBalanceId").hide();
			}else{
				$("#tzm_hideBalanceId").show();
			}
		}else if(temp_flag=="OFFSEND"){//线下打款
			$("#balancePassword").val('');//清空密码
			$("#tzm_hideBalanceId").hide();
		}
	});
	
	
	//备注选中
	$(".remark-item").click(function(){
		$(this).addClass("item-selected").parent().siblings().children().removeClass("item-selected");
		//赋值
		$("#remark").val($(this).attr("value"));
	});
	
	//鼠标移入
	$('.online-payment').hover(function(){	
			$(this).addClass('payment-item-hover');
	},function(){
			$(this).removeClass('payment-item-hover');
	}); 
	
	//鼠标移入
	$('.online-remark').hover(function(){	
			$(this).addClass('remark-item-hover');
	},function(){
			$(this).removeClass('remark-item-hover');
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
		var val = $("input[name='transportType']:checked").val();
		addressSelect(val);
	}
	
	// 点击收货地址 ，样式切换，并且赋值
	function addressSelect(val){
			
			$(".consignee-item").click(function(){
				$(this).addClass("item-selected").parent().siblings().children().removeClass("item-selected");
				var obj = $(this).addClass("item-selected").parent();
				var oldAddressId = $("#addressId").val();
				var newAddressId = $(obj).val();
				var val = $("input[name='transportType']:checked").val();
				if (oldAddressId != newAddressId) {
					// 如果旧的地址和新的地址不相等，则ajax调用方法计算新的运费
					$.ajax({
						type : "POST",
						url :  domain+"/order/calculateTransFee.html",
						data : {addressId:newAddressId,transportType:val},
						dataType : "json",
						success : function(data) {
							if(data.success){
								$("#sumPayPriceId").html("￥"+data.data.finalAmount);
								$("#payPriceId").html("￥"+data.data.finalAmount);
								$("#sumPayPriceHidden").val(data.data.finalAmount);
								
								//只有立即发货 才会有运费
								 var sendNowVal=$('#sendNow:checked').val();
								 if(sendNowVal == 1){
										$("#freightPriceId").children("font").html(" + ￥"+data.data.logisticsFeeAmount);
								 }else{
								 		$("#freightPriceId").children("font").html(" + ￥ 0.00");
								 }
								
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
				var tzm_addressAll = obj[0].attributes[3].nodeValue;//[仝照美]动态设置隐藏域中的地址
				$("#tzm_addressAll").val(tzm_addressAll);
				$("#addressId").val($(obj).val());
				
				//为结算按钮下的收货地址信息赋值
				$("#addressDetail").html($(this).siblings('.addr-detail').find(".addr-info").html());
				$("#consigneeName").html($(this).siblings('.addr-detail').find(".addr-name").html());
				$("#consigneeMobile").html($(this).siblings('.addr-detail').find(".addr-tel").html());
			});
	}
	
	function hide_ConsigneeAll() {
		//设置默认地址
		var val = $("input[name='transportType']:checked").val();
		addressSelect(val);
		
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
  		
  		//提交订单 
  		function submitOrder(){
  			//为用户提示说明要选择一种配送方式，不要选错了。
  			var userTip = $("#transportType").val();
  			var sendType = $("#sendType").val();
  			var flag = $("#flag").val();
  			if(sendType == 1 && flag == 1 ){
  				jAlert("一件代发暂不支持二次加工商品！");
  				return;
  			}
  			//alert(sendType);
//  			console.dir(userTip);
//  			console.dir(userTip==6 || userTip==7);
  			if(userTip == "" && sendType == 0){
  				jAlert("请选择配送方式");
  				//跳转到顶部
  				window.scrollTo(0,0);
  				return false;
  			}else if((userTip==6 || userTip==7)  && sendType == 0){
  				//自提时则要求用户真实姓名不为空
  	  			var realName = $("#realName").val();
  	  			if(realName == ""){
  	  				//如果真实姓名为空则跳转到个人资料完善页面
  	  				jAlert('尊敬的用户,即将跳转个人资料,请完善您的真实姓名,谢谢', '提示',function(){
  							window.location.href=domain+"/member/info.html"
  						});
  	  				return false;
  	  			}
  			}
  			else if (userTip == 1  && sendType == 0) {
				var val = $("input[name='transportType']:checked").val();
				if (typeof(val) == "undefined" && sendType == 0) {
  	  				//运送方式
	  				jAlert("请选择运送方式");
	  				//跳转到顶部
	  				window.scrollTo(0,0);
  	  				return false;
  	  			}
  			}
  			//end
  		    var radioDom =  document.getElementsByName("ordersType");
  		    var i= 0;
  		    var ordersType = 0;
  		    for(i=0;i< radioDom.length;i++){
  		    	if(radioDom[i].checked){
  		    	   ordersType = radioDom[i].value;
  		    	}
  		    }
  		    var orderType = $('#transportType').val();
  		    var sendArea = $('#sendArea').val();
  		    //送货上门时判断配送地址是否已经选择
  		    if(orderType == 5  && sendType == 0){
  		    	//送货上门时判断地址是否为浙江地区
  		    	var selectaddressAll = $("#tzm_addressAll").val();
  		    	if(selectaddressAll.indexOf("浙江")<0){
  		    		jAlert("送货上门只针对特定区域，请重新选择收货地址或者更改配送方式");
  		    		$('#sendArea').focus();
  		    		return;
  		    	}
  		    	if(sendArea == 1 && sendType == 0){
  		    		jAlert("请选择配送地区和收货地址");
  		    		$('#sendArea').focus();
  		    		return;
  		    	}else{
  		    		//送货上门的时候需要进行特殊处理，配送方式加配送地区
  		    		$('#transportType').val(orderType+''+sendArea);
  		    	}
  		    }
  		    //判断收货地址是否存在,除去自提点提货的单子不需要校验
  			if(orderType != 6 && orderType != 7  && sendType == 0){
  				/* var tzm_addressAll = $("#tzm_addressAll").val();
  	  		    var addressAllBool = false;
  	  		    if(tzm_addressAll.indexOf("浙江杭州")==0 && orderType == 1){
  	  		    	addressAllBool = true;
  	  		    } */
	  			if(isEmpty($("#addressId").val())){
	  				jAlert("请添加或选择收货地址");
	  				$(".ftx-05.addaddress").focus();
	  				return false;
	  			}
  				//如果是浙江 就不应该选中
	  			/* if(addressAllBool){
	  				jAlert("当前收货地址只能发顺丰");
	  				return false;
	  			} */
  			}
//  		var temp = $("input[name='transportType']:checked").val();
//  		$("#transportType").val(temp);
			var servicePrice = $("#servicePrice").val();
			if(servicePrice>0){
				orderType = 5;
			}else{
				orderType = 1;
			}
  			var actionUrl = domain + "/order/ordercommit.html?ordersType="+orderType;
  			var param = "";
  			
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
  			param = $("#invoiceForm").serialize();
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
  				if(totalCartAmount >=20000){
  					transportFee = 0.00;
  				}
  			}
  			//四区运费计算
  			if(sendArea==5){
  				transportFee = t*5;
  				if(transportFee < 250){
  					transportFee = 250;
  				}
  				if(totalCartAmount >=20000){
  					transportFee = 0.00;
  				}
  			}
  			$('#sendArea1').val(sendArea);
  			//先将运费清0
  			$("#shipfee").html("+ ￥ " + transportFee);
  			var tzm_noyunfei = ($("#sumPayPriceHidden").val()-$("#tzm_yunfei").val() + transportFee).toFixed(2);
  			$("#sumPayPriceId").html("+ ￥ "+tzm_noyunfei);
  			$("#payPriceId").html("￥ "+tzm_noyunfei);
  	}
  	//中通
  	function changeTransType1(){
  		$("#transportType").val("1");
  	}
  	//顺丰
  	function changeTransType1(){
  		$("#transportType").val("3");
  	}
  	//申通
  	function changeTransType1(){
  		$("#transportType").val("2");
  	}
  	
  	$(function(){
  	    $('#threeStore').click(function(){
  	   		$("#tiantian1").hide();
  			$("#tiantian2").hide();
  			$("#tiantian3").hide();
  			$("#shipfee").html("+ ￥ 0.00")
  			$("#sumPayPriceId").html((${(cartInfoVO.finalAmount)?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string("0.00")!'0.00'}).toFixed(2));
  			$("#sumPayPriceHidden").val((${(cartInfoVO.finalAmount)?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string("0.00")!'0.00'}).toFixed(2));
  			$("#payPriceId").html((${(cartInfoVO.finalAmount)?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string("0.00")!'0.00'}).toFixed(2));
  			$("#addressId").val(0);
  			$("#takeselfid").css("display","none");
  			$("#showOrhidden").hide();
  			$("#takeSelfAdress").hide();
  	    });
  	    $('#takeBySelf').click(function(){
  	    	//上门自提  设置千禧路为默认选中状态
  	    	$('input:radio[name=transportType]')[0].checked = true;
  	    	//end
  	    	$("#tiantian1").hide();
  			$("#tiantian2").hide();
  			$("#tiantian3").hide();
  			$("#shipfee").html("+ ￥ 0.00");
  			//$("#transportType").val("0");
  			<#--
  			$("#sumPayPriceId").html((${(cartInfoVO.finalAmount)?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string("0.00")!'0.00'}).toFixed(2));
  			$("#sumPayPriceHidden").val((${(cartInfoVO.finalAmount)?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string("0.00")!'0.00'}).toFixed(2));
  			$("#payPriceId").html((${(cartInfoVO.finalAmount)?string('0.00')!'0.00'}-${(cartInfoVO.logisticsFeeAmount)?string("0.00")!'0.00'}).toFixed(2));
  			-->
  			var tzm_noyunfei = ($("#sumPayPriceHidden").val()-$("#tzm_yunfei").val()).toFixed(2);
  			$("#sumPayPriceId").html("+ ￥ "+tzm_noyunfei);
  			$("#payPriceId").html("￥ "+tzm_noyunfei);
  			
  			$("#addressId").val(0);
  			$("#takeselfid").css("display","block");                   
  			$("#showOrhidden").hide();
  			$("#takeSelfAdress").show();
  	    });
  	    	//默认显示物流中转
  	    	$("#tiantian1").hide();
  			$("#tiantian2").show();
  			$("#tiantian3").hide();
  			$("#transfer_One").show();
  			$('#sendService_dw').hide();
  			$('#transfer_qx').hide();
  	    	$('#transfer_dt').hide();
  	    //物流中转
  	    $('#LogisticsTransfer').click(function(){
  	    	$("#transfer_One").show();
  	    	$("#tiantian1").show();
  			$("#tiantian2").show();
  			$("#tiantian3").hide();
  			$('#transfer_qx').hide();
  	    	$('#transfer_dt').hide();
  	    	$('#sendService_dw').hide();
  	    	$('#transportType').val(8);
  	    	//添加运费为0
  	    	//var totalCartAmount = $('#totalCartAmount').val();
  	    	//少于1000收你的20块
  	    	var tranFee = 0.00;
  	    	//if(totalCartAmount<1000){
  	    	//	tranFee = 20.00;
  	    	//}
  			$("#shipfee").html("+ ￥ "+ tranFee +"");
  			var tzm_noyunfei = ($("#sumPayPriceHidden").val()-$("#tzm_yunfei").val()+tranFee).toFixed(2);
  			$("#sumPayPriceId").html("+ ￥ "+tzm_noyunfei);
  			$("#payPriceId").html("￥ "+tzm_noyunfei);
  	    });
  	    //千禧路自提点
  	    $('#takeSelf_qx').click(function(){
  	    	$('#transfer_qx').show();
  	    	$('#transfer_dt').hide();
  	    	$("#transfer_One").hide();
  	    	$("#tiantian1").hide();
  			$("#tiantian2").hide();
  			$("#tiantian3").hide();
  			$('#sendService_dw').hide();
  			$('#transportType').val(6);
  			//添加运费为0
  			$("#shipfee").html("+ ￥ 0.00");
  			var tzm_noyunfei = ($("#sumPayPriceHidden").val()-$("#tzm_yunfei").val()).toFixed(2);
  			$("#sumPayPriceId").html("+ ￥ "+tzm_noyunfei);
  			$("#payPriceId").html("￥ "+tzm_noyunfei);
  	    });
  	    //大唐自提点
  	    $('#takeSelf_dt').click(function(){
  	    	$('#transfer_dt').show();
  	    	$('#transfer_qx').hide();
  	    	$("#transfer_One").hide();
  	    	$("#tiantian1").hide();
  			$("#tiantian2").hide();
  			$("#tiantian3").hide();
  			$('#sendService_dw').hide();
  			$('#transportType').val(7);
  			//添加运费为0
  			$("#shipfee").html("+ ￥ 0.00");
  			var tzm_noyunfei = ($("#sumPayPriceHidden").val()-$("#tzm_yunfei").val()).toFixed(2);
  			$("#sumPayPriceId").html("+ ￥ "+tzm_noyunfei);
  			$("#payPriceId").html("￥ "+tzm_noyunfei);
  	    });
  	    //送货上门
  	    $('#sendService').click(function(){
  	    	$('#sendService_dw').show();
  	    	$('#transfer_dt').hide();
  	    	$('#transfer_qx').hide();
  	    	$("#transfer_One").hide();
  	    	$("#tiantian1").show();
  			$("#tiantian2").show();
  			$("#tiantian3").hide();
  			$('#transportType').val(5);
  			//运费先清零
  			$("#shipfee").html("+ ￥ 0.00");
  			var tzm_noyunfei = ($("#sumPayPriceHidden").val()-$("#tzm_yunfei").val()).toFixed(2);
  			$("#sumPayPriceId").html("+ ￥ "+tzm_noyunfei);
  			$("#payPriceId").html("￥ "+tzm_noyunfei);
  			//确保用户来回切换配送方式后邮费不重新计算
			changeTransportFee();
  	    });
  	    //默认物流中转是大唐，邮费为0
  		if($("input[name='transportType']:checked")){
  			$("#shipfee").html("+ ￥ 0.00");
  			var tzm_noyunfei = ($("#sumPayPriceHidden").val()-$("#tzm_yunfei").val()).toFixed(2);
  			$("#sumPayPriceId").html("+ ￥ "+tzm_noyunfei);
  			$("#payPriceId").html("￥ "+tzm_noyunfei);
  		}
  	    //立即发货
  	     $('#sendNow').click(function(){
  	     	$("#transfer_One").hide();
  	    	//安排发货  设置中通快递为默认选中状态
//   	    $('input:radio[name=transportType]')[2].checked = true;
   	    	//end
  	    	$("#tiantian1").show();
  			$("#tiantian2").show();
  			$("#tiantian3").show();
//  		$("#takeselfid").hide();
//  		$("#takeselfid").css("diaplay","none");
//  		$("#showOrhidden").show();
//  		$("#takeSelfAdress").hide();
			$('#sendService_dw').hide();
			$('#transfer_dt').hide();
  	    	$('#transfer_qx').hide();
  	    	$('#transportType').val(1);
  			//添加运费
  			var temp_value= $("#tzm_yunfei").val();
  			$("#shipfee").html("+ ￥"+temp_value);
			var  tzm_youyunfei = parseFloat($("#sumPayPriceHidden").val()).toFixed(2);  			
  			$("#sumPayPriceId").html("+ ￥ "+tzm_youyunfei);
  			$("#payPriceId").html("￥ "+tzm_youyunfei);
  			
  	    });

		// 把选中的地址显示出来
		$(".consignee-item").parents("li").css("display","none");
		$(".consignee-item.item-selected").parent("li").css("display","list-item");
		$("#addressId").val("${(defaultAddress.id)!''}")
		$("#sumPayPriceHidden").val(${cartInfoVO.finalAmount!'0.00'});
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
			if($(this).is(":checked")){
				var sumPayPrice = $("#sumPayPriceHidden").val();
				// 如果积分使用后应付金额小于0则不能使用积分
				if (parseFloat(sumPayPrice) < 1) {
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
		
		//如果有二次加工，则此时不可以使用余额支付
		var  tzm_ttfee = $("input[name='tzm_ttfee']");
		var tzm_ttfee_bool = false;
		tzm_ttfee.each(function(i){
			if(parseFloat($(this).val()) > 0 ){
				tzm_ttfee_bool = true;
			}
		})
		if(tzm_ttfee_bool){
			$("#tzm_hideBalanceId").hide();
		}
		//end
		//如果单品立减，则隐藏 使用红包功能
		var  tzm_yisheng = $("input[name='tzm_yisheng']");
		var tzm_yisheng_bool = false;
		tzm_yisheng.each(function(i){
			if(parseFloat($(this).val())!=0 && parseFloat($(this).val()) < 0.9 ){//如果有单品立减不为0并且不小于0.9则可以使用红包
				tzm_yisheng_bool = true;
			}
		})
		if(tzm_yisheng_bool){
			$("#coupon").hide();
		}
		//end
	});
  	
  	//运送方式 动态计算
//  	$("input[name='transportType']:checked").val();
  	$("input[name='transportType']").click(function(){
  		var transportType = this.value;
  		if(transportType == 5){
  			$("#transportType").val(transportType);
  			$("#tzm_zpsong").show();
  			//没有运费  重新计算
  			$("#shipfee").html("+ ￥ 0.00");
  			var tzm_noyunfei = ($("#sumPayPriceHidden").val()-$("#tzm_yunfei").val()).toFixed(2);
  			$("#sumPayPriceId").html("+ ￥ "+tzm_noyunfei);
  			$("#payPriceId").html("+ ￥ "+tzm_noyunfei);
  		}else{
  			$("#tzm_zpsong").hide();
	  		$("#transportType").val(transportType);
			var oldAddressId = $("#addressId").val();
			if (oldAddressId != "") {
				// 如果旧的地址和新的地址不相等，则ajax调用方法计算新的运费
				// 提交loading
				$.ajax({
					type : "POST",
					url :  domain+"/order/calculateTransFee.html",
					data : {addressId:oldAddressId,transportType:transportType},
					dataType : "json",
					success : function(data) {
						if(data.success){
							$("#sumPayPriceId").html("￥"+data.data.finalAmount);
							$("#payPriceId").html("￥"+data.data.finalAmount);
							$("#sumPayPriceHidden").val(data.data.finalAmount);
							
							//只有立即发货 才会有运费
							 var sendNowVal=$('#sendNow:checked').val();
							 if(sendNowVal == 1){
									$("#freightPriceId").children("font").html(" + ￥"+data.data.logisticsFeeAmount);
									$("#tzm_yunfei").val(data.data.logisticsFeeAmount);
							 }else{
							 		$("#freightPriceId").children("font").html(" + ￥ 0.00");
							 }
							
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
			//$("#addressId").val($(obj).val());
			
			//为结算按钮下的收货地址信息赋值
			$("#addressDetail").html($(this).siblings('.addr-detail').find(".addr-info").html());
			$("#consigneeName").html($(this).siblings('.addr-detail').find(".addr-name").html());
			$("#consigneeMobile").html($(this).siblings('.addr-detail').find(".addr-tel").html());
  		}
  	});
  	
  	function daiFa(){
  		//ordersSendType transfer_One  sendService_dw transfer_qx transfer_dt tiantian3 tiantian1 tiantian2
  	 	$('#sendType').val("1");
  	 	$('#sendArea1').val("1");
  	 	$.ajax({
			type : "GET",
			url :  domain+"/cart/selectpackage.html",
			//data : {sellerId:sellerId},
		    //data : {sellerId:sellerId,totalWarePrice:totalWarePrice},
			//dataType : "json",
			success : function(data) {
				if (data.data) {
					jAlert("一件代发暂不支持二次加工商品！");
					$('#flag').val("1");
				} else {
	            	$('#ordersSendType').hide();
			  	 	$('#transfer_One').hide();
			  	 	$('#sendService_dw').hide();
			  	 	$('#transfer_qx').hide();
			  	 	$('#transfer_dt').hide();
			  	 	$('#tiantian3').hide();
			  	 	$('#tiantian1').hide();
			  	 	$('#tiantian2').hide();
			  		//添加运费为0
					$("#shipfee").html("+ ￥ 0.00");
					var tzm_noyunfei = ($("#sumPayPriceHidden").val()-$("#tzm_yunfei").val()).toFixed(2);
					$("#sumPayPriceId").html("+ ￥ "+tzm_noyunfei);
					$("#payPriceId").html("+ ￥ "+tzm_noyunfei);
	            }
			}
		}); 
  	}
  	
  	function zhiFa(){
  	 	$('#sendType').val("0");
  	 	//alert($('#sendArea').val());
  	 	if($('#sendArea').val() == ''){
  	 		$('#sendArea1').val("1");
  	 	}else{
  	 		$('#sendArea1').val($('#sendArea').val());
  	 	}
  	 	var transportType = $('#transportType').val();
  	 	$('#ordersSendType').show();
  	 	//物流中转（大唐）
  	 	if(transportType ==  8){
  	 		$('#transfer_One').show();
  	 		$('#tiantian1').show();
  	  	 	$('#tiantian2').show();
  	 	}
  	 	//千禧路自提
  	 	if(transportType ==  6){
  	 		$('#transfer_qx').show();
  	 	} 
  	 	//大唐市场自提
  	 	if(transportType ==  7){
  	 		$('#transfer_dt').show();
  	 	} 
  	 	
  	 	//送货上门
  	 	if(transportType ==  5){
  	 		$('#sendService_dw').show();
  	 		$('#tiantian1').show();
  	  	 	$('#tiantian2').show();
  	 	} 
  	 	//快递配送
  	 	if(transportType ==  1){
  	 		$('#tiantian3').show();
  	  	 	$('#tiantian1').show();
  	  	 	$('#tiantian2').show();
  	 	} 
  	 	
  	 	//添加运费
		var temp_value= $("#tzm_yunfei").val();
		$("#shipfee").html("+ ￥"+temp_value);
		var  tzm_youyunfei = parseFloat($("#sumPayPriceHidden").val()).toFixed(2);  			
		$("#sumPayPriceId").html("+ ￥ "+tzm_youyunfei);
		$("#payPriceId").html("+ ￥ "+tzm_youyunfei);
  	 	
  	}
</script>
