<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/"/>

<script language="javascript">
    var protectedPrice = "${(product.protectedPrice)!0}";
    var isNorm = Number("${(product.isNorm)!1}");
    var domain = "${domainUrlUtil.EJS_URL_RESOURCES}";
    var from = "${from}";
</script>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/js/product/pdt/goodsset.js"></script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">修改商品库存价格
            <span class="s-poar">
                <a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/${from}">返回</a>
            </span>
        </h2>

    <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" 
        	action="${currentBaseUrl}goodssetSumit" name="addForm">
            <input type="hidden" name="id" value="#{(product.id)!''}"/>
            <input type="hidden" name="goodsinfo" id="goodsinfo"/>
            <input type="hidden" name="from" value="${from}"/>
            <input type="hidden" id="number" value="${number}"/>
            <input type="hidden" id="checktype" value="1"/>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>商品基本信息</dt>
                <dd class="dd-group">
               
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">商品保护价: </label>
                            <label>${(product.protectedPrice)!0}</label>
                        </p>
                    </div>
                    <#--
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>商城价: </label>
                            <input type="text" id="mallPcPrice" name="mallPcPrice" 
                            	<#if product.isNorm == 2>readonly="readonly"</#if> 
                            	value="${(product.mallPcPrice)!''}" 
                            	class="txt w200 easyui-numberbox" data-options="min:1,max:99999,precision:2,required:true"/>
                        </p>
                    </div>
					
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                价格必须是0.01~9999999之间的数字，且不能高于市场价。
                                此价格为商品实际销售价格，如果商品存在规格，该价格显示最低价格
                            </font>
                        </p>
                    </div>

                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>商城价(mobile): </label>
                            <input type="text" id="malMobilePrice" name="malMobilePrice"
                           		 <#if product.isNorm == 2>readonly="readonly"</#if>
                                   value="${(product.malMobilePrice)!''}" class="txt w200 easyui-numberbox"
                                   data-options="min:1,max:99999,precision:2,required:true"/>
                        </p>
                    </div>

                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                价格必须是0.01~9999999之间的数字，且不能高于市场价。
                                此价格为手机商城商品实际销售价格，如果商品存在规格，该价格显示最低价格
                            </font>
                        </p>
                    </div>-->

						<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>价格设置: </label>
							<label><input type="radio" name="price" id="price_1" value="1" onchange="stepOne()" <#if product.priceStatus?? && product.priceStatus==1> checked="checked" </#if> />一口价</label>
							<label><input type="radio" name="price" id="price_2" value="2" onchange="stepTwo()" <#if product.priceStatus?? && product.priceStatus==2> checked="checked" </#if> />阶梯价</label>
							<label><input type="radio" name="price" id="price_3" value="3" onchange="stepThree()" <#if product.priceStatus?? && product.priceStatus==3> checked="checked" </#if>/>整箱价</label>
						</p>
					</div>
					<div id="sx_one">
						<p class="p12 p-item">
							<label class="lab-item"><font class="white">.</label>
							价格(￥)<input type="text" id="price_only" name="price_only" value="${(product.mallPcPrice)!''}" class="txt w120 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
						</p>
					</div>
					<div id="sx_two">
						<p class="p12 p-item">
							<label class="lab-item"><font class="white">.</label>
							价格1(￥)<input type="text" id="price_step_1" name="price_step_1" value="${(productPrice.price1)!''}" class="txt w120 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
							数量阶梯<input type="text" id="stone_step_1s" name="stone_step_1s" value="${(productPrice.price1S)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/> -
								    <input type="text" id="stone_step_1e" name="stone_step_1e" value="${(productPrice.price1E)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/>
							分佣比例<input type="text" id="percentageScale1" name="percentageScale1" value="${(productPrice.percentageScale1)!''}" class="txt w120 easyui-numberbox" data-options="min:0.000,max:1,precision:3,required:true"/>
						</p>
						<p class="p12 p-item">
							<label class="lab-item"><font class="white">.</label>
							价格2(￥)<input type="text" id="price_step_2" name="price_step_2" value="${(productPrice.price2)!''}" class="txt w120 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
							数量阶梯<input type="text" id="stone_step_2s" name="stone_step_2s" value="${(productPrice.price2S)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/> -
								    <input type="text" id="stone_step_2e" name="stone_step_2e" value="${(productPrice.price2E)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/>
							分佣比例<input type="text" id="percentageScale2" name="percentageScale2" value="${(productPrice.percentageScale2)!''}" class="txt w120 easyui-numberbox" data-options="min:0.000,max:1,precision:3,required:true"/>
						</p>
						<p class="p12 p-item">
							<label class="lab-item"><font class="white">.</label>
							价格3(￥)<input type="text" id="price_step_3" name="price_step_3" value="${(productPrice.price3)!''}" class="txt w120 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
							数量阶梯<input type="text" id="stone_step_3s" name="stone_step_3s" value="${(productPrice.price3S)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/> -
									<input type="text" id="stone_step_3e" name="stone_step_3e" value="${(productPrice.price3E)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/>
							分佣比例<input type="text" id="percentageScale3" name="percentageScale3" value="${(productPrice.percentageScale3)!''}" class="txt w120 easyui-numberbox" data-options="min:0.000,max:1,precision:3,required:true"/>
						</p>
					</div>
					
					<div id="sx_three">
						<p class="p12 p-item">
							<label class="lab-item"><font class="white">.</label>
						       整箱价格<input type="text" id="quantity_price" name="quantity_price" value="${(product.mallPcPrice)!''}" class="txt w120 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
						       整箱数量<input type="text" id="container_quantity" name="container_quantity" value="${(product.fullContainerQuantity)!''}" class="txt w120 easyui-numberbox" data-options="min:0,max:99999,precision:0,required:true"/>
						</p>
						<p class="p12 p-item">
							<label class="lab-item"><font class="white">.</label>
							散&#12288;价&#12288;<input type="text" id="scattered_price" name="scattered_price" value="${(product.scatteredPrice)!''}" class="txt w120 easyui-numberbox" data-options="min:0.01,max:99999,precision:2,required:true"/>
						</p>
					</div>
					
					
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>商品库存: </label>
                            <input type="text" id="productStock" name="productStock"
                                   missingMessage="请输入商品库存"
                                   data-options="required:true" <#if product.isNorm == 2>readonly="readonly"</#if>
                                   value="${(product.productStock)!''}" class="txt w200 easyui-numberbox"/>
                        </p>
                    </div>

                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                0~999999999之间的整数，用户显示在单品页下，发生交易，系统会自动计算库存
                            </font>
                        </p>
                    </div>
                    
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>促销信息: </label>
                            <textarea id="name2" name="name2"
                                   missingMessage="请输入商品促销信息"
                                   data-options="required:true,validType:'length[1,200]'"
                                   cols="90" rows="5"
                                   class="easyui-validatebox">${(product.name2)!''}</textarea>
                        </p>
                    </div>
                   
                   <#if product.isNorm==2>
                    <div id="normDiv">
                          <div class="fluidbox" name="dyTable">
                              <table width="86%" border="0" cellspacing="0" cellpadding="0" style="margin-left:60px">
                                  <tbody>
                                  <tr style="background:#CCC;border:1px solid #e2e1e1;font-size:12px;">
                                      <td width="15%" style="padding:6px;">规格值</td>
                                      <td width="15%" style="padding:6px;">sku</td>
                                      <td width="15%" style="padding:6px;">库存</td>
                                      <td width="15%">PC价格</td>
                                      <#--
                                      <td width="15%">mobile价格</td>-->
                                  </tr>
                                  <#if goodslist?? && goodslist?size &gt; 0>
                                  <#list goodslist as good>
                                  <tr style="border:1px solid #e2e1e1" name="goodstr">
                                      <td style="display: none">
                                      	<input name="goodsid" type="hidden" value="${(good.id)!''}">
                                      </td>
                                      
                                      <td>
                                          <label>${(good.normName)!''}</label>
                                      </td>
                                      <td style="padding:6px;">
                                          <label>${(good.sku)!''}</label>
                                      </td>
                                      <td>
                                          <input name="" type="text" id="inventory_details_stock_${good_index}" 
                                           style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;"
                                            value="${(good.productStock)!''}" class="styleStock">
                                      </td>
                                      <td>
                                          <input name="" type="text" id="inventory_details_pprice_${good_index}" 
                                           style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" 
                                           value="${(good.mallPcPrice)!''}" class="stylePrice">
                                      </td>
                                      <#--
                                      <td>
                                          <input name="" type="text" id="inventory_details_mprice_${good_index}" 
                                           style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" 
                                           value="${(good.mallMobilePrice)!''}" class="stylePrice">
                                      </td>-->
                                  </tr>
                                  </#list>
                                  </#if>
                                  </tbody>
                              </table>
                          </div>
                    </div>
                    </#if>
                </dd>
            </dl>
        
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>帮助</dt>
                <dd class="dd-group">
                	<div class="fluidbox">
                        <label class="lab-item" style="width: 100%; text-align: left;">您可以在此修改商品的价格和库存</label>
                    </div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							库存不得低于1<br>
						</label>
					</div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							价格不得低于商品保护价
						</label>
					</div>
				</dd>
            </dl>

            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="确认" onclick="submitdata()"/>
                <input type="button" id="back" class="btn" value="返回"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>
<script>
function stepOne(){
	$("#sx_two").hide();
	$("#sx_three").hide();
	$("#sx_one").show(); 
	$("#checktype").val(1);
}

function stepTwo(){
	$("#sx_one").hide();
	$("#sx_three").hide();
	$("#sx_two").show();
	$("#checktype").val(2);
}

function stepThree(){
	$("#sx_one").hide();
	$("#sx_two").hide();
	$("#sx_three").show();
	$("#checktype").val(3);
}

var temp = $("input[name='price']:checked").val();
if(temp==1){
	stepOne();
}else if(temp==2){
	stepTwo();
}else{
	stepThree();
}

</script>
<#include "/seller/commons/_detailfooter.ftl" />