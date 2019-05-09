<#include "/admin/commons/_detailheader.ftl" /> <#assign
currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders"/>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
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
	
	.panel-fit{
			height: 100%;
			margin: 0;
			padding: 0;
			border: 0;
		    overflow: inherit;
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
			 //window.location.href='${currentBaseUrl}/state1';
			 window.history.back();
		});
		
		$(".numberbox").numberbox({
			"onChange":function(cur_,pri_){
				//calculateAmount();
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
		submitOrders();
	}
	
	function submitOrders(){
	     $('#addForm').ajaxSubmit({
			            url: '${currentBaseUrl}/setNumberSave',
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
	
	
	function checkNumber(this_,id){
	   var hideNumber1 = $('#hideNumber_'+id).val();
// 	   for(var i=0;i<nowNumber1.length;i++){
		    if(Number($(this_).val()) > Number(hideNumber1)){
		       $.messager.alert("提示","sku数量只能减少不能增加。");
		       $(this_).val(Number(hideNumber1));
		   }
// 	   }
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
			alert
			var proNumber = parseFloat(Number(data_[0]));
			//商品总金额 ＝ 商品单价*商品数量
			var productAmount = proNumber * Number(data_[4]);
			//标签费总金额 ＝ 标签费单价*商品数量
			var labelFeeTotal = Number(data_[3]) * proNumber;
			//套餐费总金额 ＝ 套餐单价*商品数量
			var packFeeTotal = Number(data_[2]) * proNumber;
			//网单价格 ＝ 商品价格总和+标签价格和+套餐价格和
			var opprice = parseFloat(productAmount) + parseFloat(labelFeeTotal) + parseFloat(packFeeTotal);
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
			设置退货数量 <span class="s-poar"> <a class="a-back"
				href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state1">返回</a>
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm"
			action="${currentBaseUrl}/setNumberSave" name="addForm"> <input
				type="hidden" name="orderId" value="${orders.id}" /> 
				<input type="hidden" name="opinfo" id="opinfo" />
				<input type="hidden" name="moneyLogistics" id="moneyLogistics" value="${(orders.moneyLogistics)!0}"/>
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
										<td width="10%" style="padding: 6px;">套餐名称</td>
										<td width="5%" style="padding: 6px;">套餐价格</td>
										<td width="5%" style="padding: 6px;">标签价格</td>
										<td width="5%" style="padding: 6px;">商品单价</td>
										<td width="8%" style="padding: 6px;">商品数量</td>
										<td width="8%" style="padding: 6px;">退货数量</td>
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
											<label style="cursor: pointer;" 
												onclick="packDetail('${(op.packOther)!}','${(op.packDescribe)!}')">${(op.packageName)!'-'}</label>
										</td>
										<td>
											<label>${(op.packprice)!'-'}</label>
										</td>
										<td>
											<label>${(op.labelprice)!'-'}</label>
										</td>
										<td>
											<label>${(op.moneyPrice)!'-'}</label>
										</td>
										<td>
											<label>${(op.number)!'-'}</label>
										</td>
										<td>
											<input class="easyui-numberbox stylePrice pronumber" name="number" id="number_${(op.id)!0}" 
													onchange="checkNumber(this,${(op.id)!0})"
										 			data-options="min:0,max:999999,precision:0,required:true"
													missingMessage="请输入商品数量"
													value="0"/>
													<#--这个影藏域不能删除，不能删除，不能删除，能删除，删除，除。。。。。-->
													<input  type="hidden" name="hideNumber" id = "hideNumber_${(op.id)!0}" value="${(op.number)!0}"/>
										</td>
									</tr>
									</#list> 
									</#if>
								</tbody>
							</table>
						</div>
					</div>

				</dd>
				<dd class="dd-group">
                    <div class="fluidbox">
                    	<p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>退货说明: </label>
                        	<div style="padding-left: 140px">
                        		<textarea id="returnInfo" name="returnInfo" rows="8" cols="83" value=""></textarea>
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
						<label class="lab-item" style="width: 100%; text-align: left;">此处仅供您部分退货使用，填入退货数量，系统会自动计算该订单金额</label>
					</div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							退货数量不得高于订单的购买数量</label>
					</div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							只有已出库的订单可以进行退货操作 </label>
					</div>
				</dd>
			</dl>

			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="确认" onclick="submitdata()" />
				<input type="button" id="back" class="btn" value="返回" />
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
<#include "/admin/commons/_detailfooter.ftl" />
