<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders"/>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<style>
	#optable td,.stylePrice{
		text-align: center;
	}
</style>
<script language="javascript">
	var protectedPrice = Number("${(orders.protectedPrice)!0}");
	var domain = "${domainUrlUtil.EJS_URL_RESOURCES}";
	var paycodeBox;
	$(function() {
		<#noescape>
			paycodeBox = eval('(${initJSCodeContainer("ORDER_PAYMENT_STATUS")})');
		</#noescape>
		$("#pay-label").html(paycodeBox["ORDER_PAYMENT_STATUS"][${(orders.paymentStatus)!0}]);
		
		$("#back").click(function(){
			 	window.location.href='${currentBaseUrl}/${type}';
		});
	});
	
	function submitdata(){
		var opinfo = new Array();
		$.each($("#opinfoDiv tr[name='opinfostr']"), function(idx, e) {
			var this_ = $(this);
			var data_ = new Array();
			$.each(this_.find("td :input[type='hidden']"), function(i2, e2) {
				var val_ = $(e2).val();
				data_.push(val_ ? val_ : 0);
			});
			opinfo.push(data_);
		});
		var gif = opinfo.join("!@|@!");
		$("#opinfo").val(gif);
		 $('#addForm').ajaxSubmit({
            url: '${currentBaseUrl}/setLabelSave',
            type: 'post',
            success: function (data) {
                if (data && null != data.success && data.success == true) {
                    window.location.href='${currentBaseUrl}/${type}';
                }else{
                	$.messager.show({
        				title : '提示',
        				msg : data.message,
        				showType : 'show'
        			});
                }
            }
        });
	}
	
	var selectedA;
	function selectLabel(obj,isSelfLabel){
		selectedA = obj;
		$("#labeldialog").dialog({
			title:'标签列表',
            width: 780,
            height: 422,
            modal: true,
            href: "${currentBaseUrl}/labeldialog?isSelfLabel="+isSelfLabel+"&memberId=${orders.memberId}"
		});
	}
	
	function labelCallBack(data) {
		if(selectedA){
			$(selectedA).prev().html(data.name);
			$(selectedA).html("点击更改");
			$(selectedA).next().val(data.id);
		}
	}
	
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			<#if source?? && source==1>
			网单价格设置 <#else>订单详情查看</#if><span class="s-poar"> <a class="a-back"
				href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/${type}">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			action="${currentBaseUrl}/setPriceSvae" name="addForm"> 
			<input type="hidden" name="orderId" value="${orders.id}" /> 
			<input type="hidden" name="opinfo" id="opinfo" />
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>订单信息
				</dt>
				<dd class="dd-group">
					<#--
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">订单号: </label> <label>${(orders.orderSn)!''}</label>
						</p>
					</div>

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">买家用户名:
							</label> <label>${(orders.memberName)!}</label>
						</p>
					</div>

					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">卖家: </label> <label>${(orders.sellerName)!''}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">订单金额: </label>
							<label>${(orders.moneyOrder)}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">付款状态: </label>
							<label id="pay-label">
							</label>
						</p>
					</div>-->
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
					<div class="fluidbox">
						<p class="p12 p-item" style="margin-left: -67px;">
							<label class="lab-item">收货人:
							</label> <label>${(orders.name)!''}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item" style="margin-left: -55px;">
							<label class="lab-item">联系电话:
							</label> <label>${(orders.mobile)!''}</label>
						</p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item" style="margin-left: -55px;">
							<label class="lab-item">收货地址:
							</label> <label>${(orders.addressInfo)!''}</label>
						</p>
					</div>
				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>订单备注
				</dt>
				<dd class="dd-group">

					<div class="fluidbox">
						<p class="p12 p-item">
							<label>${(orders.remark)!''}</label>
						</p>
					</div>

				</dd>
			</dl>
			
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>网单信息
				</dt>
				<dd class="dd-group">
					<div>
						<table id ="myTable1" style="width:70%;margin-left: 30px"">
						  <thead>
							<tr rel="1" style='background-color:#CCC' height='35px'>
								<td>支付流水号</td>
								<td>商品金额(￥)</td>
								<td>优惠金额(￥)</td>
								<td>促销活动</td>
								<td>运费(￥)</td>
								<td>加工费(￥)</td>
								<td>辅料费(￥)</td>
								<td>应付(￥)</td>
								<td>实付(￥)</td>
							</tr>
							<tr>
								<td>${(orders.tradeNo)!''}</td>
								<td>${(orders.moneyProduct)!''}</td>
								<td>${(orders.moneyDiscount)!''}</td>
								<td><#if orders.couponUserId &gt; 0>红包 </#if><#if orders.moneyActFull &gt; 0> 满减</#if></td>
								<td>${(orders.moneyLogistics)!''}</td>
								<td>${(orders.servicePrice)!''}</td>
								<td>${(orders.labelPrice)!''}</td>
								<td>${(orders.moneyOrder)!''}</td>
								<td>${(orders.moneyPaidReality)!''}</td>
							</tr>
						  </thread>
						 </table>
					</div>
					<table>
					</table>
					<div id="opinfoDiv">
						<div class="fluidbox" name="dyTable">
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								style="margin-left: 30px" id="optable">
								<tbody>
									<tr
										style="background: #CCC; border: 1px solid #e2e1e1; font-size: 12px;">
										<td width="10%" style="padding: 6px;">货品名称</td>
										<td width="10%" style="padding: 6px;">是否自供标</td>
										<td width="10%" style="padding: 6px;">套餐</td>
										<td width="15%" style="padding: 6px;">标签</td>
										<td width="5%" style="padding: 6px;">网单金额</td>
										<td width="15%" style="padding: 6px;">规格</td>
										<td width="15%" style="padding: 6px;">商品sku</td>
										<td width="13%" style="padding: 6px;">商品单价</td>
										<td width="7%" style="padding: 6px;">商品数量</td>
									</tr>
									<#if orders.orderProductList?? && orders.orderProductList?size &gt; 0> 
									<#list orders.orderProductList as op>
									<tr style="border: 1px solid #e2e1e1" name="opinfostr">
										<td style="display: none">
											<input name="opid" type="hidden" value="${(op.id)!''}">
										</td>
										<td>
											<label>${(op.productName)!''}</label>
										</td>
										<td>
											<label><#if (op.isSelfLabel)?? && (op.isSelfLabel) == 1>是<#else>否</#if></label>
										</td>
										<td>
											<label>${(op.packageName)!'-'}</label>
										</td>
										<td>
											<#if (op.productPackageId)?? && (op.productPackageId != 0)>
											<label><#if (op.productLabelId)?? && (op.productLabelId) != 0>${(op.labelName)!}</#if></label>
											<a href="javascript:;" style="color:blue" onclick="selectLabel(this,${op.isSelfLabel})">
												<#if (op.productLabelId)?? && (op.productLabelId) != 0>点击更改<#else>点击选择</#if>
											</a>
											<#else>
												-
											</#if>
											<input type="hidden" name="labelid" value="${(op.productLabelId)!}"/>
										</td>
										<td>
											<label>${(op.moneyAmount)!''}</label>
										</td>
										<td style="padding: 6px;">
											<label>${(op.specInfo)!''}</label>
										</td>
										<td>
											<label>${(op.productSku)!''}</label>
										</td>
										<td>
											<label>${(op.moneyPrice)!''}</label>
										</td>
										<td>
											<label>${(op.number)!''}</label>
										</td>
									</tr>
									</#list> 
									</#if>
								</tbody>
							</table>
						</div>
					</div>

				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">您可以在此修改每个网单的成交价格、网单标签，系统会自动计算该订单金额</label>
					</div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							价格不得低于商品保护价 </label>
					</div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							只有未付款的订单可以修改网单价格 </label>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="确认"
					onclick="submitdata()" /> <input type="button" id="back"
					class="btn" value="返回" />
			</p>
			</@form.form>
		</div>
	</div>
</div>
<div id="labeldialog" style="position:fixed"></div>
<#include "/admin/commons/_detailfooter.ftl" />
