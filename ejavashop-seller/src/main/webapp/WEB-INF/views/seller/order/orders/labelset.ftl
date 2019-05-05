<#include "/seller/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/order/orders"/>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/seller/jslib/js/jquery.form.js"></script>
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
			 window.location.href='${currentBaseUrl}/state1';
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
                    window.location.href='${currentBaseUrl}/state1';
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
            width: 980,
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
			网单价格设置 <span class="s-poar"> <a class="a-back"
				href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state1">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			action="${currentBaseUrl}/setPriceSvae" name="addForm"> <input
				type="hidden" name="orderId" value="${orders.id}" /> 
				<input type="hidden" name="opinfo" id="opinfo" />
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>订单信息
				</dt>
				<dd class="dd-group">

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
					<div id="opinfoDiv">
						<div class="fluidbox" name="dyTable">
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								style="margin-left: 30px" id="optable">
								<tbody>
									<tr
										style="background: #CCC; border: 1px solid #e2e1e1; font-size: 12px;">
										<td width="20%" style="padding: 6px;">货品名称</td>
										<td width="10%" style="padding: 6px;">是否自供标</td>
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
											<label><#if (op.productLabelId)?? && (op.productLabelId) != 0>${(op.labelName)!}</#if></label>
											<a href="javascript:;" style="color:blue" onclick="selectLabel(this,${op.isSelfLabel})">
												<#if (op.productLabelId)?? && (op.productLabelId) != 0>点击更改<#else>点击选择</#if>
											</a>
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
<div id="labeldialog"></div>
<#include "/seller/commons/_detailfooter.ftl" />
