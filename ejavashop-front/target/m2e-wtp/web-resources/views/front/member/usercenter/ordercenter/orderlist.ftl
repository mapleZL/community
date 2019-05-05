<#include "/front/commons/_headbig.ftl" />
<style type='text/css' rel="stylesheet">.tzm_spancss {
	font-size: 14px;
	color: #333333;
	margin-left: 80px;
}

.tzm_spancss td {
	width: 90px;
	padding: 5px;
}

.tzm_spancss #queryOrders {
	border-radius: 4px;
	width: 100px;
	height: 26px;
	background: #27cdf2;
	border: none;
	color: #fff;
}

.tzm_spancss tr td input {
	height: 25px;
}
</style>

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
			
			

			<div class='wrapper_main myorder'>
				<h3>我的订单</h3>
				
				<table class="tzm_spancss"  >
					<tr >
						<td style="">订单编码</td>
						<td><input id='orderSn' name='orderSn'></td>
						<td width= '80'>订单类型</td>
						<td width='120'>
							<select name="orderType" id="orderType"style="width:162px;height:25px;" >   
		        				<option value="">所有</option>   
							    <option value="1">普通订单</option>   
							    <option value="2">二次加工订单</option>   
		      				</select>
		      			</td>
		      			<td width= '80' style="text-align:center">配送方式</td>
		      			<td width='120'>
		      				<select name="logisticType" id="logisticType"style="width:162px;height:25px;">
		      					<option value="">所有</option>
		      					<option value="5">送货上门</option>
		      					<option value="6">大唐自提点</option>
		      					<option value="7">千禧路自提点</option>
		      					<option value="8">物流中转</option>
		      					<option value="1">中通</option>
		      					<option value="3">顺丰</option>
		      				</select>
		      				
		      				</td> 
		      			</tr>
		      			
		      			<tr>
		      			<td width= '80'>支付方式</td>
		      			<td width='120'> 
		      				<select name="payType" id="payType"style="width:162px;height:25px;">
		      					<option value="">所有</option>
		      					<option value="pcalipay">PC支付宝</option>
		      					<option value="h5alipay">H5支付宝</option>
		      					<option value="wxpay">微信</option>
		      					<option value="ONLINE">在线支付</option>
		      					<option value="OFFSEND">线下打款</option>
		      					<option value="BALANCE">余额支付</option>
		      				</select>
		      			</td>
		      			<#--
		      			<td width='80' style="text-align:center">促销信息</td>
		      			<td width='120'>
		      				<select name="coupnType" id="coupnType" width='80'style="width:162px;height:25px;">
		      					<option value="">所有</option>
		      					<option value="2">有</option>
		      					<option value="3">无</option>
		      				</select>
		      			</td>
	      			</tr>
	      		
	      			<tr>-->
	      				<td>提交时间</td>
	      				<td ><input type="date"  id="createTime_s" name="createTime_s" value="" style="width:162px;"/></td>
	      				<td style="text-align:center;">—</td>
	      				<td><input type="date"  id="createTime_e" name="createTime_e" value="" style="width:162px;"/></td>
	      			</tr>
	      			<tr>
	      				<td colspan="6" align="center">
	      					<input type="button" name="queryOrders" id="queryOrders" value="查询">
	      					<input type="button" name="rest" id="queryOrders" class="query_reset" value="重置">
	      				</td>
	      			</tr>
	      		</table>
				<div class='mc'>
					<table class='tb-void'>
						<thead>
							<tr>
								<th width='300'>订单信息</th>
								<th>数量</th>
								<th>订单类型</th>
								<th>配送方式</th>
								<th>支付方式</th>
								<th>促销信息</th>
								<th>订单金额</th>
								<#--
								<th>状态</th>-->
								<th width="200">操作</th>
							</tr>
						</thead>
						
						<#if ordersList??>
							<#list ordersList as order>
						<tbody>
							<tr class='tr-th'>
								<td colspan="8">  
									<span class=''>${order.createTime?string("yyyy-MM-dd HH:mm:ss")}&nbsp;&nbsp;订单编号:
										<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=${(order.id)!''}' target='_blank' title="<#if order.tradeNo ? exists>${(order.tradeNo)!''}<#else>${(order.orderSn)!''}</#if>"><#if order.tradeNo ? exists>${(order.tradeNo)!''}<#else>${(order.orderSn)!''}</#if></a>
										<#--<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=${(order.id)!''}' target='_blank' title="${(order.orderSn)!''}">${(order.orderSn)!''}</a>-->
									</span>
								</td>
							</tr>
								
							<#if (order.orderProductList)??>
								<#list (order.orderProductList) as op>
								<tr class='tr-td'>
									<td>
										<div class='img-list'>
												<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(op.productId)!0}.html?goodId=${(op.productGoodsId)!0}" target='_blank' class='img-box'>
													<#if op.productGoods??>
													<img optype="goods"  width='50' height='50' class='err-product' title="${(op.productName)!''}"
														data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productGoods.images!''}">
													<#else>
													<img  width='50' height='50' class='err-product' title="${(op.productName)!''}"
														data-original="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${op.productLeadLittle!''}">
													</#if>
												</a>
												<div class='p-msg'>
													<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(op.productId)!0}.html?goodId=${(op.productGoodsId)!0}'  
													target='_blank'>${(op.productName)!''}</a>
													<#if op.sellerId == 0>
													<span style="vertical-align:top;display:inline-block;width:16px;height:16px;BACKGROUND: url('${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/redpacket.png') no-repeat center 50%;"></span>
													<#else>
													<span></span>
													</#if>
												</div>
												<div class='p-msg' style="height: 15px;color: #888888;">
													${(op.specInfo)!''}
												</div>
										</div>
									</td>
									<td>
										<div class='p-num'>${(op.number)!''}</div>
									</td>
									<#assign sizeOrders = (order.orderProductList)?size>
									<#if op_index == 0>
									<#--这块直接根据订单类型来读取吧【仝照美】
									<#if order.servicePrice &gt; 0> 二次加工订单<#else>普通订单 </#if>
									-->
									<td rowspan="${(sizeOrders)!1}"> <#if order.orderType == 5 > 二次加工订单<#if order.paymentStatus == 2><font color='red'>(确认中)</font></#if><#elseif order.orderType == 1 || order.orderType== 3>普通订单 </#if></td>
									<td rowspan="${(sizeOrders)!1}"> ${(order.logisticsName)!''}</td>
									<td rowspan="${(sizeOrders)!1}"> ${(order.paymentName)!''}</td>
									<td rowspan="${(sizeOrders)!1}"> <#if order.moneyDiscount &gt; 0><font color="blue">有</font><#else><font color="red">无</font></#if></td>
								
									<#--
									<td rowspan="${(sizeOrders)!1}">
										<div class='u-name'>${(order.name)!''}</div>
									</td>-->
									<td rowspan="${(sizeOrders)!1}">
										￥${(order.moneyOrder)!''}
										<#--
											<br>
										
										${(order.paymentName)!''}
										-->
									</td>
									<#--
									<td rowspan="${(sizeOrders)!1}">
										<#if order.orderState??>
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
							  		        <#if order.orderState?? && order.paymentCode??>
												<#if (order.orderState==4)>	
								  		    	<div class='tool-tip'>
												</div>
										    </#if>
										</#if>
									</td>
									-->
									<td rowspan="${(sizeOrders)!1}" style="width:90px;">
										<span>
										    <input type="button" value="查看详情" onclick="listOrdersDetails(${(order.id)!''})" style="border-radius:4px;width:80px;height:26px;background:#27cdf2;border:none;color:#fff;"/>
											<#--<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id=${(order.id)!''}" target='_blank' >查看详情</a>-->
										</span>
										<#if order.orderState?? && order.paymentCode??>
											<!--<#if (order.orderState==1)||(order.orderState==2)||(order.orderState==3)>--><!-- 订单状态 为1（未付款的订单）或者 2待确认 或者 3待发货的订单 才能取消 -->
												<span>
													 <#if order.orderState==1>
														<a href='javascript:void(0)' onclick="cancalOrder('${order.tradeNo}')" class='remove-order'>取消订单&nbsp;&nbsp;&nbsp;</a>
													<#else>
													        <#if order.orderType == 4>
													              <a href='javascript:void(0);'  class='btn btn-danger payments-but' onclick="threeSend('${order.id}')"  target='_blank' style="color: white;">提货</a>
													        </#if>
												  </#if>
											 </span>
											<!--</#if> -->
											<#--
											<#if (order.orderState==5)>
												<span>
													<a target='_blank' href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/addcomment.html?id=${(order.id)!0}' target='_self'>评价晒单</a>
												</span>
												<span>
													<a target='_blank' href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/backapply.html?id=${(order.id)!0}" onclick="" target='_self'>申请退换货</a>
												</span>
											</#if>
											-->
											<!-- 已发货状态 可以确认收货 -->
											<#if (order.orderState==4)>
												<span>
													<a  href='javascript:void(0)' onclick="goodsReceipt('${(order.id)!''}')">确认收货</a>
													<#if (order.orderType==4)>
													       <a  href='javascript:void(0)'  class='btn btn-danger payments-but' style="color: white;" onclick="threeSend('${(order.id)!''}')"  target='_blank'>提货</a>
													</#if>
												</span>
											</#if>
										    <#if order.payTime ? exists>
									      	
									        <#else>
												<#if (order.orderState==1)&&(order.paymentCode != 'OFFSEND') && (order.paymentStatus != 2)>
													<span><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/order/pay.html?relationOrderSn=<#if order.tradeNo ? exists>${(order.tradeNo)!''}<#else>${(order.orderSn)!''}</#if>&rid=${commUtil.randomString(20)}" class='btn btn-danger payments-but'>付款</a></span><br/>
												</#if>
											</#if>
												 <#if order.orderState == 5 || order.orderState == 6>
												 	<span>
												  		<a href='javascript:void(0)' onclick="deleteOrder('${order.id}')" class='remove-order'>&nbsp;删除订单&nbsp;&nbsp;</a>
												  	</span>
												 </#if>
										  </#if>
									</td>
								</#if>

								</tr>
								</#list>
							</#if>
							
						</tbody>
							</#list>
						</#if>
						
					</table>
				</div>
				<!-- 分页 -->
				<#include "/front/commons/_pagination.ftl" />
			</div>
		</div>
<script type="text/javascript">
	$(".tool-tip").hover(function(){
		$(this).children(".delivery-detail").show();
	},function(){
		$(this).children(".delivery-detail").hide();
	});
	
	$(".query_reset").click(function() {
		if (confirm("您确定要重置查询条件吗？")) {
			$("#orderSn").val("");
			$("#orderType").val("");
			$("#logisticType").val("");
			$("#payType").val("");
			$("#createTime_s").val("");
			$("#createTime_e").val("");
		}
	});


	//控制左侧菜单选中
	$("#myorder").addClass("currnet_page");
	
	//取消订单
	function cancalOrder(tradeNo){
		if(confirm("确定要取消该订单吗？")){
			$.ajax({
				type : "GET",
				url :  domain+"/member/cancalorder.html",
				data : {tradeNo:tradeNo},
				dataType : "json",
				success : function(data) {
					if(data.success){
						window.location.reload();
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
	
	//删除订单
	function deleteOrder(orderId){
		if(confirm("确定要删除该订单吗？")){
			$.ajax({
				type : "GET",
				url :  domain+"/member/deleteorder.html",
				data : {orderId:orderId},
				dataType : "json",
				success : function(data) {
					if(data.success){
						window.location.reload();
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
	
	//云仓托管发货
	function threeSend(ordersId){
	    if(ordersId != null  || ordersId !=''){
	         window.location.href=domain+"/member/threesend.html?ordersId="+ordersId;
	    }
	}
	
	//确认收货
	function goodsReceipt(ordersId){
			$.ajax({
				type : "GET",
				url :  domain+"/member/goodreceive.html",
				data : {ordersId:ordersId},
				dataType : "json",
				success : function(data) {
					if(data.success){
						window.location.reload();
					}else {
						jAlert(data.message);
					}
				},
				error : function() {
					jAlert("数据加载失败！");
				}
			});
	}
	
	$(function () {
		$('#queryOrders').click(function(){
			var orderSn = $("#orderSn").val();
			var orderType = $("#orderType").val();
			var logisticType = $("#logisticType").val();
			var payType = $("#payType").val();
			var coupnType = $("#coupnType").val();
			var createTime_s = $("#createTime_s").val();
			var createTime_e = $("#createTime_e").val();
			var date = orderSn+";"+orderType+";"+logisticType+";"+payType+";"+coupnType+";"+createTime_s+";"+createTime_e;
			
			window.location.href=domain+"/member/ordersquery.html?q_strs=" +date;
		});
	});
	
	function listOrdersDetails(ordersId){
		window.open("${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/orderdetail.html?id="+ordersId);
	}
</script>

<#include "/front/commons/_endbig.ftl" />
