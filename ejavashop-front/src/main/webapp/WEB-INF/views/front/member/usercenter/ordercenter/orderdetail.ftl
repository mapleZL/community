<#include "/front/commons/_headbig.ftl" />

<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/order.css">

		<div class='container w'>
			<div class='breadcrumb'>
				<strong>
					<a href='javascript:void(0)'>大袜网</a>
				</strong>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>订单中心</a>
					&nbsp;>&nbsp;订单：${(order.orderSn)!''}
				</span>
			</div>
			
			<#if errorMsg!="">
				<h1 style="margin: 30px;text-align: center;color:red">	
					${errorMsg}
				</h1>
			<#else>
			<div class='m' id='orderstate'>
				<div class='mt'>
					<strong>${errorMsg}
						<#if (order.tradeNo) ? exists>支付码：${(order.tradeNo)!''}<#else>订单号：${(order.orderSn)!''}</#if>&nbsp;&nbsp;&nbsp;&nbsp; 状态：
						<#--订单号：${(order.orderSn)!''}&nbsp;&nbsp;&nbsp;&nbsp; 状态：-->
						<span class='ftx14'>
								<#if  order.orderState??>
							  		<#assign state = order.orderState>
							  		<#if state==1>未付款  
							  		<#elseif state==2>待确认
							  		<#elseif state==3>待发货
							  		<#elseif state==4>已发货
							  		<#elseif state==5>已完成
							  		<#elseif state==6>已取消
								  	<#else>
							  		</#if>
					  		    </#if>
						</span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<span class='ftx02'>物流公司名称：${(order.logisticsName)!''}</span>
					</strong>
				</div>
				<div class='mc'>
					<#if  order.orderState??>
				  		<#assign state = order.orderState>
				  		<#if state==1>
				  			尊敬的客户，您的订单等待付款。
				  		<#elseif state==2>
				  			尊敬的客户，您已经提交了订单，请等待系统确认。
				  		<#elseif state==3>
				  			尊敬的客户，我们正在为您备货。
				  		<#elseif state==4>
				  			尊敬的客户，您的订单商品已经从库房发出，请您做好收货准备。
				  		<#elseif state==5>
				  			尊敬的客户，您的订单已完成。
				  		<#elseif state==6>
				  			尊敬的客户，您的订单已取消。
					  	<#else>
				  		</#if>
		  		    </#if>
				</div>
			</div>
			<div class='mb10 ml10'>
				重要提醒：我们的平台及销售商不会以
				<strong class='ftx04'>订单异常、系统升级</strong>
				为由，要求您点击任何网址链接进行退款操作。
			</div>
			<!-- 进度条 -->
			<div class='section3' id='process'>
				<#if  order.orderState??>
					<#assign state = order.orderState>
					<div class='node fore ready'>
						<ul>
							<li class='tx1'>&nbsp;</li>
							<li class='tx2'>提交订单</li>
						</ul>
					</div>
					<div class='proce ready'></div>
					<div class='node ready'>
						<ul>
							<li class='tx1'>&nbsp;</li>
							<li class='tx2'>商品出库</li>
						</ul>
					</div>
					<div class='proce ready   <#if (state<4)>default</#if>'></div>
					<div class='node ready <#if (state<4)>default</#if>'>
						<ul>
							<li class='tx1'>&nbsp;</li>
							<li class='tx2'>等待收货</li>
						</ul>
					</div>
					<div class='proce ready   <#if state<5>default</#if>'>
					</div>
					<div class='node ready <#if state<5>default</#if>'>
						<ul>
							<li class='tx1'>&nbsp;</li>
							<li class='tx2'>已完成</li>
						</ul>
					</div>
					
				</#if>
			</div>
			<!--end-->
			<!--跟踪、付款信息 -->
			<div class='m' id='ordertrack'>
				<ul class='tab'>
					<li class='curr li-table' data-table='1'>
						<h2>订单跟踪</h2>
					</li>
					<li class=' li-table' data-table='2'>
						<h2>付款信息</h2>
					</li>
				</ul>
				<div class='clr'></div>
				<!-- 订单跟踪 -->
				<div class='mc tabcon hide show' id='table1'>
					<table cellpadding='0' cellspacing='0' width='100%'>
						<tbody id='tbody_track'>
							<tr>
								<th class='15%'>
									<strong>处理时间</strong>
								</th>
								<th class='50%'>
									<strong>处理信息</strong>
								</th>
								<th class='35%'>
									<strong>操作人</strong>
								</th>
							</tr>
						</tbody>
						<tbody>
						<#if orderLogList??>
							<#list orderLogList as orderLog> 
								<tr>
									<td>${orderLog.createTime?string("yyyy-MM-dd HH:mm:ss") }</td>
									<td>${orderLog.content}</td>
									<td>${orderLog.operatingName}</td>
								</tr>
							</#list>
						</#if>
							
						</tbody>
					</table>
				</div>
				<!-- 付款信息 -->
				<div class='mc tabcon hide' id='table2'>
					<table cellpadding='0' cellspacing='0' width='100%'>
					
							    <tbody>
									<tr>
										<td width='25%' id='daiFuName'>
											<#if order.paymentStatus??>
												<#if (order.paymentStatus==1)>
										 			付款方式：${(order.paymentName)!'' }
												<#else>
													未付款
												</#if>
											</#if>
										</td>
										<td width='75%' id='daiFuPeople'></td>
									</tr>
									<tr>
										<td width='25%' id='daiFuPeople'>&nbsp;</td>
									</tr>
									<tr>
										<td> 商品金额：+ ￥${(order.moneyProduct)?string('0.00')!'' }</td>
										<td> 优惠总额：- ￥${(order.moneyDiscount)?string('0.00')!'' }</td>
									</tr>
									<tr>
										<td> 运费金额：+ ￥${(order.moneyLogistics)!'0.00'}</td>
										<td> 余额支付：- ￥${(order.moneyPaidBalance)?string('0.00')!'' }</td>
									</tr>
									<tr>
										<td width='25%' id='daiFuPeople'>套餐服务费：+ ￥${(order.servicePrice)?string('0.00')!'' }</td>
										<td> 订单满减：- ￥${(order.moneyActFull)?string('0.00')!'' }</td>
									</tr>
									<tr>
										<td width='25%' id='daiFuPeople'>红包抵扣： - ￥${(order.moneyCoupon)?string('0.00')!'' }</td>
										<td> 现金支付：- ￥${(order.moneyPaidReality)?string('0.00')!'' }</td>
									</tr>
									<tr>
										<td width='25%' id='daiFuPeople'>&nbsp;</td>
									</tr>
									<tr>
										<td> 订单金额：￥${(order.moneyOrder)?string('0.00')!'' }</td>
										<td>
											<#if order.paymentStatus??>
												<#if (order.paymentStatus==1)>
										 			实付金额：&nbsp;&nbsp;￥${(order.moneyPaidReality + order.moneyPaidBalance)?string('0.00')!'' }
												<#else>
													应付金额：&nbsp;&nbsp;￥${(order.moneyOrder - order.moneyIntegral)?string('0.00')!'' }
												</#if>
											</#if>
										</td>
									</tr>
								</tbody>
							
						
					</table>
					<div style="clear: both;margin-top: 20px;">
								<#--  <#if order.paymentCode == "OFFSEND">-->
									<h4 class="text-danger" style="color: rgb(255,153,0);">说明:</h4>
									<ol style="margin-left: 15px;">
									  <li>①.选择线下打款时，请备注订单单号，用于保证订单及时核销，订单单号务必填写正确</li>
									  <li>②.订单单号需填写在电汇凭证的【汇款用途】、【附言】、【摘要】等栏内（因不同银行备注不同，最好在所有可填写备注的地方均填写）</li>
									  <li>③.一个订单单号对应一个订单和金额，请勿多转账或少转账</li>
									  <li>④.收到款后本平台将会第一时间确认并处理您的订单</li>
									  <li>⑤.查询付款情况请致电0575-89007153</li>
									</ol>
									<table class="table table-bordered table-hover" style="margin-top: 15px;">
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
								<#-- </#if>-->
					</div>
					
				</div>
			</div>
			<!-- end -->
			<!-- 订单信息 -->
			<div class='m' id='orderinfo'>
				<div class='mt'>
					<strong>订单信息</strong>
				</div>
				<div class='mc'>
					<!-- 顾客信息 -->
					<!--自提订单影藏掉收货人信息-->
					<#if order.orderType != 3>
					  <dl class='fore'>
					    <dt>收货人信息</dt>
						<dd>
							<ul>
								<li>收&nbsp;货&nbsp;人：${(order.name)!'' }</li>
								<li>地&nbsp;&nbsp;&nbsp;&nbsp;址：${(order.addressAll)!''}&nbsp;${(order.addressInfo)!''}</li>
								<li>手机号码： <#if commUtil??>  ${commUtil.hideMiddleStr(order.mobile,3,4)!''}  </#if>   </li>
								<li>固定电话：</li>
							</ul>
						</dd>
					</dl>
					<!-- end -->
					<#else>
						<#if order.logisticsId==6>
						   <dl class='fore'>
							    <dt>配送信息</dt>
								<dd>
									<ul>
										<li>自提点：浙江省诸暨市千禧路20号大袜云仓北门</li>
										<li>联系人：张建涛</li>
										<li>联系电话 ：0575-87599013&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;15215936158</li>
										<li>提货时间：当天15：00之前支付完成的订单，当天可提货,<br/><span style="margin-left:60px;"> 当天15：00之后支付完成的订单，次日可提.<span></li><br/>
										<li>注意：
											<span style="margin-left:10px;">1.请记录订单编号和账号，向门卫表明身份和目的。</span><br/>
											<span style="margin-left:50px;">2.请在联系人陪同下进入园区，出区时请主动向门卫出示提货手续。</span>
										</li>
									</ul>
								</dd>
							</dl>
							<#elseif order.logisticsId==7>
							<dl class='fore'>
							    <dt>配送信息</dt>
								<dd>
									<ul>
										<li>自提点：大唐轻纺袜业城现代物流隔壁</li>
										<li>联系人：戚碧桃</li>
										<li>联系电话 ：0575-89000305&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;18105756083</li>
										<li>提货时间：当天15：00之前支付完成的订单，当天可提货,<br/><span style="margin-left:60px;"> 当天15：00之后支付完成的订单，次日可提.<span></li><br/>
										<li>注意：
											<span style="margin-left:10px;">1.请记录订单编号和账号，向门卫表明身份和目的。</span><br/>
											<span style="margin-left:50px;">2.请在联系人陪同下进入园区，出区时请主动向门卫出示提货手续。</span>
										</li>
									</ul>
								</dd>
							</dl>
						</#if>
					</#if>
					<!--支付与配送方式 -->
					<dl>
						<dt>支付及配送方式</dt>
						<dd>
							<ul>
								<li>支付方式：${(order.paymentName)!'' }</li>
								<#if order.orderType != 3>
								<li>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：￥${order.moneyLogistics!'0.00'}</li>
								<#else>
								<li>配送方式：上门自提</li>
								</#if>
							</ul>
						</dd>
					</dl>
					
					<!--订单备注 -->
					<dl>
						<dt>订单备注</dt>
						<dd>
							<ul>
								<li>${(order.remark)!'' }</li>
							</ul>
						</dd>
					</dl>
					
					<!-- end -->
					<!-- 发票信息 -->
					<dl style="display: none;">
						<dt>发票信息</dt>
						<dd>
							<ul>
								<#if order??&&(order.invoiceStatus!=0)>
									<li>发票类型：${(order.invoiceType)!'' }</li>
										<li>发票抬头：${(order.invoiceTitle)!'' }</li>
									<#else>
										<li>不开发票</li>
								</#if>
								
							</ul>
						</dd>
					</dl>
					<!--  -->
					<!-- 商品清单 -->
					<dl>
						<dt>
							<span class='i-mt'>商品清单</span>
						</dt>
						<dd class='p-list'>
							<table cellpadding='0' cellspacing="0" width='100%'>
								<tbody>
									<tr>
										<th width='12%'>商品图片</th>
										<th width='25%'>商品名称</th>
										<th width='10%' style="text-align: center;">二次加工</th>
										<th width='5%' style="text-align: center;">自供标</th>
										<th width='10%' style="text-align: center;">商品单价</th>
										<th width='10%' style="text-align: center;">加工费</th>
										<th width='10%' style="text-align: center;">辅料费</th>
										<!-- <th width='8%'>积分数</th> -->
										<th width='7%' style="text-align: center;">商品数量</th>
										<!-- <th width='11%' >操作</th> -->
									</tr>
									<#if (order.orderProductList)??>
										<#list (order.orderProductList) as op>
									<tr>
										<td>
											<div class='img-list'>
													<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(op.productId)!0}.html" class='img-box' target='_blank'>
<!-- 													<img width='50' height='50' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productLeadLittle}'> -->
													<#if (op.product)?? && op.product.isNorm == 2 && (op.productGoods)?? && (op.productGoods.images!='')>
													<img optype="goods"  width='50' height='50' class='err-product' title="${(op.productName)!''}"
														src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productGoods.images!''}">
													<#else>
													<img  width='50' height='50' class='err-product' title="${(op.productName)!''}"
														src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productLeadLittle!''}">
													</#if>
												</a>
											</div>
										</td>
										<td>
											<div class='al fl'>
												<a href='' class='flk13'>${(op.productName)!''}</a>
												<#if op.sellerId == 0>
												<span style="vertical-align:top;display:inline-block;width:16px;height:16px;BACKGROUND: url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/redpacket.png') no-repeat center 50%;"></span>
												<#else>
												<span></span>
												</#if>
												</br>
												<#if op.productUrl?? &&(order.orderState == 3 || order.orderState == 4 || order.orderState == 5)>
													<a href='${(op.productUrl)!''}' style="color:red" >点击链接下载数据包</a>
												</#if>
											</div>
										</td>
										<!-- 二次加工 productPackage -->
										<td>
											<#if productPackage?? && op.productPackageId?? && op.productPackageId!=0> 
												<#list productPackage as pp>
													<#if op.productPackageId?? && op.productPackageId==pp.id>
												    	<span>${(pp.name)}</span>
												    </#if>
											    </#list>
										    <#else>
											    	<span>—</span>
										    </#if>
										</td>
										<!-- 自供标 -->
										<td>
											<#if op.isSelfLabel?? && op.isSelfLabel==1>
												<span>是</span>
										    <#else>
										    	<span>否</span>
										    </#if>									
										</td>
										<td>
											<span class="ftx04">￥${(op.moneyPrice)?string('0.00')!''}</span>
										</td>
										<!-- <td>
											<span>+50</span>
											<br>
											<span>赠送积分</span>
										</td> -->
										<!-- 加工费 -->
										<td>
											<#if op.packageFee??>
												<span class="ftx04">￥${(op.packageFee)?string('0.00')!'—'}</span>
											<#else>
									    		<span>—</span>
										    </#if>
										</td>
										<!-- 辅料费 -->
										<td>
											<#if op.labelFee??>
												<span class="ftx04">￥${(op.labelFee)?string('0.00')!'—'}</span>
											<#else>
												<span class="ftx04">￥0.00</span>
											</#if>
										</td>
										<td>${(op.number)!''}</td>
										<td style="display: none;">
										
									<#if order.orderState?? && order.paymentCode??>
										<!-- 已完成的订单 才能评价和申请退换货 -->
										<#if (order.orderState==5)>
											<span>
												<a target='_blank' href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/addcomment.html?id=${(order.id)!0}' target='_self'>评价</a>
											<br>
											<a target='_blank' href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/backapply.html?id=${(order.id)!0}" onclick="" target='_self'>申请退换货</a>
											</span>
										</#if>
									</#if>
										</td>
									</tr>
										</#list>
									</#if>
								</tbody>
							</table>
						</dd>
					</dl>
					<!-- end -->
				</div>
				<!-- 金额 -->
				<div class='total'>
					<ul>
						<#--<li><span>+ 商品总额：</span>￥${(order.moneyProduct-order.servicePrice)?string('0.00')!'0.00' }</li>-->
						<li><span>+ 商品总额：</span>￥${(order.moneyProduct)?string('0.00')!'0.00' }</li>
						<li><span>+ 运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</span>￥${order.moneyLogistics!'0.00' }</li>
						<#if order.servicePrice?? && order.servicePrice != 0.00>
						<li><span>+ 套餐服务费：</span>￥${(order.servicePrice)?string('0.00')!'' }</li>
						</#if>
						<#if order.labelPrice?? >
						<li><span>+ 辅料费：</span>￥${order.labelPrice}</li>
						</#if>
						<li><span>- 优惠总额：</span>￥${(order.moneyDiscount)?string('0.00')!'0.00'}</li>
						<li><span>- 积分支付：</span>￥${(order.moneyIntegral)?string('0.00')!'0.00' }</li>
						<li><span>- 余额支付：</span>￥${(order.moneyPaidBalance)?string('0.00')!'0.00' }</li>
						<li><span>- 现金支付：</span>￥${(order.moneyPaidReality)?string('0.00')!'0.00' }</li>
						<#-- <div class='list'>
							<span>
								总商品金额：
							</span>
							 <em class='price' id='warePriceId'>+ ￥${order.moneyProduct)?string('0.00')!'0.00' }</em>
						</div>
						<div class='list'>
							<span>
								运费：
							</span>
							 <em class='price' id='warePriceId'>+ ￥${(order.moneyLogistics)?string('0.00')!'0.00' }</em>
						</div>
						<div class='list'>
							<span>
								优惠总额：
							</span>
							 <em class='price' id='warePriceId'>- ￥${(order.moneyDiscount)?string('0.00')!'0.00'}</em>
						</div>
						<div class='list'>
							<span>
								余额支付：
							</span>
							 <em class='price' id='warePriceId'>- ￥${(order.moneyPaidBalance)?string('0.00')!'0.00'}</em>
						</div>
						<div class='list'>
							<span>
								积分支付：
							</span>
							 <em class='price' id='warePriceId'>- ￥${(order.moneyIntegral)?string('0.00')!'0.00'}</em>
						</div> -->
					</ul>
					<span class='clr'></span>
					<div class='extra'>
						<#if order.paymentStatus??>
							<#if (order.paymentStatus==1)>
					 			实付金额：
								<span class='ftx04'>
									<b>￥${(order.moneyPaidReality + order.moneyPaidBalance)?string('0.00')!''}</b>
								</span>
							<#else>
								应付金额：
								<span class='ftx04'>
									<b>￥${(order.moneyOrder - order.moneyIntegral)?string('0.00')!''}</b>
								</span>
							</#if>
						</#if>
					</div>
				</div>
				<!-- end -->
			</div>
			<!-- end -->
			</#if>
			
		</div>

		<script type="text/javascript">
			//订单
			var obj=$(".li-table:eq(0)");//获取每个li索引
		    $(".li-table").click(function(){
		       $(".li-table").eq($(this).index()).addClass("curr").siblings().removeClass("curr");
		        var obj=$(this);
		        var table=$(this).data('table');
		        $(".hide").removeClass("show");
		        $("#table"+table).addClass("show");
		    });

		    $("#barcode span").mouseover(function(){
		    	$("#sn_list").removeClass("hide");
		    }).mouseout(function(){
		    	$("#sn_list").addClass("hide");
		    });
		    
		    
		</script>
<#include "/front/commons/_endbig.ftl" />
 