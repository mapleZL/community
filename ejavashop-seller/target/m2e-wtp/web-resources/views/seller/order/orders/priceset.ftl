<#include "/seller/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/order/orders"/>
<style>
	#optable td,.stylePrice{
		text-align: center;
	}
	
	.stylePrice{
		width: 90px !important;
		margin: 1px 5px;
	}
		
	.packdes{
		font-size: 14px;
		padding: 20px;
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
		
		$(".numberbox").numberbox({
			"onChange":function(){
				calculateAmount();
			}
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
		//opid,packprice,labelprice,money_price
		//多条数据以“!@|@!”分隔
		var gif = opinfo.join("!@|@!");
		$("#opinfo").val(gif);
		if ($("#addForm").form('validate')) {
			$("#add").attr("disabled",true);
			 $('#addForm').ajaxSubmit({
	            url: '${currentBaseUrl}/setPriceSvae',
	            type: 'post',
	            success: function (data) {
	                if (data && null != data.success && data.success == true) {
	                	$.messager.show({
	        				title : '提示',
	        				msg : '更改成功',
	        				showType : 'show'
	        			});
	                	setTimeout(function() {
		                    window.location.reload(true);
	    				}, 2000);
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
	}
	
	function opamountChange(obj){
		var val_ = Number($(obj).val());
		var protectedPrice = $(obj).attr("protectedPrice");
		if(val_ < Number(protectedPrice)){
			$.messager.show({
				title : '提示',
				msg : '网单价格不能低于商品保护价',
				showType : 'show'
			});
			$(obj).val(protectedPrice);
			return;
		}
	}
	
	function packDetail(pt,des){
		$("#pt-p").html(pt);
		$("#des-p").html(des);
		$("#packdialog").dialog('open');
	}
	
	/**
	 * 计算价格
	 */
	function calculateAmount(){
		//遍历网单数据
		  //所有网单总价
        var sum = 0;
        
		$.each($("#opinfoDiv tr[name='opinfostr']"), function(idx, e) {
			var this_ = $(this);
			//每个网单输入数据
			var data_ = new Array();//格式：商品数量,套餐价格,标签价格,商品单价
			
			var moneyActSingle = this_.prev("input[name='moneyActSingle']").val();
			$.each(this_.find("td :input[type='hidden']:not(:first)"), function(i2, e2) {
				var val_ = $(e2).val();
				data_.push(val_ ? val_ : 0);
			});
			var proNumber = Number(data_[0]);
			//商品总金额 ＝ 商品单价*商品数量
			var productAmount = proNumber * Number(data_[3]);
			//标签费总金额 ＝ 标签费单价*商品数量
			var labelFeeTotal = Number(data_[2]) * proNumber
			//套餐费总金额 ＝ 套餐单价*商品数量
			var packFeeTotal = Number(data_[1]) * proNumber;
			//网单价格 ＝ 商品价格总和+标签价格和+套餐价格和
			var opprice = productAmount + labelFeeTotal + packFeeTotal;
			
			//当前网单
			this_.find("label[name='moneyAmount']").html(opprice.toFixed(2));
			
	        //总金额 ＝ 所有网单金额和
	        sum += opprice;
		});
		
		var moneyLogistics = Number($("#moneyLogistics").val());
		sum = sum - Number("${(orders.moneyDiscount)!0}") + moneyLogistics;
		$("#orderamount").html("&yen;"+sum.toFixed(2));
	}
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			网单价格设置 <span class="s-poar"> <a class="a-back"
				href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/order/orders/state1">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			action="${currentBaseUrl}/setPriceSvae" name="addForm"> <input
				type="hidden" name="orderId" value="${(orders.id)!}" /> 
				<input type="hidden" name="opinfo" id="opinfo" />
				<input type="hidden" name="moneyLogistics" id="moneyLogistics" value="${(orders.moneyLogistics)!0}"/>
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
							<label id="orderamount">&yen;${(orders.moneyOrder)}</label>
						</p>
					</div>
					 <div class="fluidbox">
                        <p class="p12 p-item">
                            <label style="width: 100%;text-align: left;margin-left: 84px;">
                            <font style="color: #808080">
                               	网单金额之和，已减优惠金额
                            </font>
                            </label>
                        </p>
                    </div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item">优惠金额: </label>
							<label>${(orders.moneyDiscount)}</label>
						</p>
					</div>
					 <div class="fluidbox">
                        <p class="p12 p-item">
                            <label style="width: 100%;text-align: left;margin-left: 84px;">
                            <font style="color: #808080">
                                优惠金额总额（满减、立减、红包等所有优惠额的和）
                            </font>
                            </label>
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
										<td width="5%" style="padding: 6px;">网单金额</td>
										<td width="17%" style="padding: 6px;">规格</td>
										<td width="5%" style="padding: 6px;">商品sku</td>
										<td width="8%" style="padding: 6px;">商品数量</td>
										<td width="10%" style="padding: 6px;">套餐名称</td>
										<td width="10%" style="padding: 6px;">套餐市场价</td>
										<td width="5%" style="padding: 6px;">辅料</td>
										<td width="10%" style="padding: 6px;">标签市场价</td>
										<td width="5%" style="padding: 6px;">套餐价格</td>
										<td width="5%" style="padding: 6px;">标签价格</td>
										<td width="5%" style="padding: 6px;">商品单价</td>
									</tr>
									<#if orders.orderProductList?? && orders.orderProductList?size &gt; 0> 
									<#list orders.orderProductList as op>
									<input type="hidden" name="moneyActSingle" value="${(op.moneyActSingle)!0}"/>
									<tr style="border: 1px solid #e2e1e1" name="opinfostr">
										<td style="display: none">
											<input name="opid" type="hidden" value="${(op.id)!''}">
										</td>
										<td>
											<label>${(op.productName)!''}</label>
										</td>
										<td>
											<label name="moneyAmount">${(op.moneyAmount)!''}</label>
										</td>
										<td>
											<label>${(op.specInfo)!''}</label>
										</td>
										<td>
											<label>${(op.productSku)!''}</label>
										</td>
										<td>
											<#--
										    2016-8-15 19:39：00 B哥让注释的，说不让修改数量
											<input class="easyui-numberbox stylePrice" name="number" id="number"
													data-options="min:0,max:99999,precision:0,required:true"
													missingMessage="请输入商品数量"
													value="${(op.number)!0}"/>
										-->
										<label name="moneyAmount">${(op.number)!0}</label>
										</td>
										<td>
											<label style="cursor: pointer;" 
												onclick="packDetail('${(op.packOther)!}','${(op.packDescribe)!}')">${(op.packageName)!'-'}</label>
										</td>
										<td>
											<label>${(op.packprice)!'-'}</label>
										</td>
										<!-- add bytongzhaomei 新增辅料 -->
										<td>
											<label>${(op.productLabelId)!'-'}</label>
										</td>
										<!-- end -->
										<td>
											<label>${(op.labelprice)!'-'}</label>
										</td>
										<td>
											<#if (op.productPackageId)?? && (op.productPackageId) != 0>
												<!-- 存在套餐，可修改价格 -->
												<input class="easyui-numberbox stylePrice" name="packageFee" 
													data-options="min:0.01,max:99999,precision:2"
													value="${(op.packageFee)!0}"/>
											<#else>
												<label>未选择套餐</label>
												<input type="hidden" name="packageFee"/>
											</#if>
										</td>
										<td>
											<#if (op.productPackageId)?? && (op.productPackageId) != 0 && (op.productLabelId)?? && (op.productLabelId) != 0>
												<!-- 存在标签，可修改价格 -->
												<input class="easyui-numberbox stylePrice" name="labelFee" 
													data-options="min:0.01,max:99999,precision:2"
													value="${(op.labelFee)!0}"/>
											<#else>
												<label>未设置标签</label>
												<input type="hidden" name="labelFee"/>
											</#if>
										</td>
										<td>
											<input type="text" protectedPrice="${(op.protectedPrice)!0}"
												onchange="opamountChange(this)"
												value="${(op.moneyPrice)!''}"  class="easyui-numberbox stylePrice" 
												data-options="min:0.01,max:99999,precision:2,required:true">
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
						<label class="lab-item" style="width: 100%; text-align: left;">您可以在此修改每个网单的套餐价格、标签价格、商品价格，系统会自动计算该订单金额</label>
					</div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							商品价格不得低于商品保护价 </label>
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

<div id="packdialog" class="easyui-dialog popBox" title="套餐详情"
	style="width: 400px; height: 160px;"
	data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
	buttons="#dlg-buttons-award-act">

	<div class="packdes">
		<p><label>辅料：</label><span id="pt-p"></span></p>
		<p><label>描述：</label><span id="des-p"></span></p>
	</div>
</div>
<#include "/seller/commons/_detailfooter.ftl" />
