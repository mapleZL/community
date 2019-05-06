<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders"/>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

	function submitForm(){
		var paymentCode_o = $("#paymentCode_o").val();
		var createTime_o = $("#createTime_o").val();
		var paymentCode_n = $("#q_paymentCode").val();
		var createTime_n = $("#q_createTime").val();
		if(paymentCode_o!=""&&paymentCode_o!=null&&paymentCode_o!=paymentCode_n){
			if(!confirm("您已修改支付方式，请注意提交时间，线上支付的普通订单会在提交订单后三小时自动取消，提交请确认")){
			}
		}else{
			return;
		}
		window.location.href="${currentBaseUrl}/modifyOrdersInfo?ordersId="+$("#orderId").val()+"&q_paymentCode="+paymentCode_n+"&q_createTime="+createTime_n;
	}
	
	function goToOringal(){
		window.location.href="${currentBaseUrl}/state1";
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			订单信息修改
			<span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}/state1">返回</a>
            </span>
		</h2>
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm"> 
			<input type="hidden" name="orderId" id="orderId" value="${orders.id}" /> 
			<input type="hidden" name="opinfo" id="opinfo" />
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>订单信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<table id ="myTable1" style="width:95%;margin-left: 30px"">
						  <thead>
							<tr rel="1" style='background-color:#CCC' height='35px'>
								<td>订单编码</td>
								<td>提交时间</td>
								<td>订单来源</td>
								<td>订单类型</td>
								<td>订单状态</td>
								<td>配送方式</td>
								<td>承运公司</td>
								<td>支付方式</td>
								<td>促销信息</td>
							</tr>
							<tr>
								<td>${(orders.orderSn)!''}</td>
								<td>${(orders.createTime)?string('yyyy-MM-dd HH:mm:ss')}</td>
								<td><#if orders.source==1>PC端<#else>移动端</#if></td>
								<td><#if orders.orderType==1>普通订单<#else>二次加工订单</#if></td>
								<td id="pay-label"></td>
								<td><#if orders.logisticsId==1||orders.logisticsId==3>快递<#else>${(orders.logisticsName)!''}</#if></td>
								<td><#if orders.logisticsId==1>ZTO<#else><#if orders.logisticsId==3>SF<#else>--</#if></#if></td>
								<td>${(orders.paymentName)!''}</td>
								<td><#if orders.servicePrice &gt; 0>有<#else>无</#if></td>
							</tr>
						  </thead>
						</table>
					</div>
				</dd>	
			 </dl>
			 
			 <dl class="dl-group">
			 	<dt class="dt-group">
					<span class="s-icon"></span>可修改信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item">支付方式:</label>
							<select name="q_paymentCode" id="q_paymentCode" class="txt">
								<option value="ONLINE">在线支付</option>
								<option value="OFFSEND">线下打款</option>
							</select>
						</p>
						<p class="p6 p-item">
							<label class="lab-item">提交时间:</label>
                        	<input type="text" id="q_createTime" name="q_createTime" value="${(orders.createTime)?string('yyy-MM-dd HH:mm:ss')!''}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt w160" data-options="required:true"/>
						</p>
					</div>
					<div>
						<input type="hidden" id="paymentCode_o" value="${(orders.paymentCode)}"/>
						<input type="hidden" id="createTime_o" value="${(orders.createTime)?string('yyy-MM-dd HH:mm:ss')!''}"/>
					</div>
				</dd>
			 </dl>
			 <p class="p-item p-btn">
			 	<input type='button' class="btn" value="确认修改" onclick="submitForm()"/>
			 	<input type='button' class="btn" value="返       回" onclick="goToOringal()"/>
			 </p>
			 <dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">您可以在此修改订单的提交时间和支付方式</label>
					</div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							支付方式修改后请同时修改提交时间，避免订单由于提交时间而被系统自动取消 </label>
					</div>
				</dd>
			</dl>
			 </@form.form>
		</div>
	</div>
</div>
<#include "/admin/commons/_detailfooter.ftl" />