<#include "/front/commons/_headbig.ftl" />

<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css'>

		<div class='w w1 header container'>
			<!-- <div id='logo'>
				<a href='' target='_blank' class='link1'>
					<img src=''>
				</a>
			</div> -->
			<div class='stepflex'>
				<dl class='first done'>
					<dt class='s-num'>1</dt>
					<dd class='s-text'>
						1.我的进货单
						<s></s>
						<b></b>
					</dd>
				</dl>
				<dl class='normal done'>
					<dt class='s-num'>2</dt>
					<dd class='s-text'>
						2.填写核对订单信息
						<s></s>
						<b></b>
					</dd>
				</dl>
				<dl class='normal last doing'>
					<dt class='s-num'>3</dt>
					<dd class='s-text'>
						3.成功提交订单
						<s></s>
						<b></b>
					</dd>
				</dl>
			</div>
		</div>
		<!-- 订单成功信息 -->
		<div class='container w'>
			<div class='msop mts'>
				<s class='icon-mts'></s>
				<h3 class='ftc-02'>感谢您，订单提交成功！请到<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html?orderState=1'>订单中心</a>查看详情。</h3>
				<#if ordervo??>
					<#if (ordervo.ordersList?size>1)>
					<h2>亲爱的用户，由于您购买的商品分属不同的商家，此订单被拆分为${(ordervo.ordersList?size) }个订单进行结算及配送。</h2>
					</#if>
			   </#if>
			<#if ordervo??>
			<#list ordervo.ordersList as order>
				<div class='mcs' id='msop_detail'>
					<ul class='list-order'>
						<li class='li-st'>
							<div class='li-for1'>
								订单号：
								<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=${(order.id)!''}'>${(order.orderSn)!'' }</a>
							</div>
							<div class='li-for2'>
								${(order.paymentName)!'' }：
								<strong class='ftc-01'>${(order.moneyOrder - order.moneyIntegral)?string('0.00')!'' }元</strong>
							</div>
							<div style="clear: both;margin-top: 40px;">
								<#if order.paymentCode == "OFFSEND" && order_index==0 >
									<#--
										<div style="font-size: 20px; line-height: 40px;margin-top: 10px;">
										<p><b>小麦（浙江）网络科技有限公司</b></p>
										<p>招商银行诸暨支行：<b>5759 0352 9310 588</b></p>
										<p>支付宝账号：<b>xmwl@dawawang.com</b></p>
										<p>联系电话：0575-89007153</p>
										</div>
									-->
									<table class="table table-bordered table-hover">
										  <thead>
										      <tr>
										         <th>开户行</th>
										         <th>开户名称</th>
										         <th>账号</th>
										         <th>币种</th>
										      </tr>
										   </thead>
										   <tbody>
										      <tr>
										         <td>华夏银行</td>
										         <td>小麦(浙江)网络科技有限公司</td>
										         <td>1325 2000 0004 55303</td>
										         <td>CNY</td>
										      </tr>
										      <tr>
										         <td>中国光大银行杭州滨江支行</td>
										         <td>小麦(浙江)网络科技有限公司</td>
										         <td>7966 0188 0000 59222</td>
										         <td>CNY</td>
										      </tr>
										   </tbody>
									</table>
									<h4 class="text-danger" style="color: rgb(255,153,0);">说明:</h4>
									<ol style="margin-left: 15px;margin-top: 15px;">
									  <li>①.选择线下打款时，请备注订单单号，用于保证订单及时核销，订单单号务必填写正确</li>
									  <li>②.订单单号需填写在电汇凭证的【汇款用途】、【附言】、【摘要】等栏内（因不同银行备注不同，最好在所有可填写备注的地方均填写）</li>
									  <li>③.一个订单单号对应一个订单和金额，请勿多转账或少转账</li>
									  <li>④.收到款后本平台将会第一时间确认并处理您的订单</li>
									  <li>⑤.查询付款情况请致电0575-89007153</li>
									</ol>
								</#if>
							</div>
						</li>
					</ul>
				</div>
			  </#list>
			 </#if>	
			</div>
			
		</div>
<#include "/front/commons/_endbig.ftl" />