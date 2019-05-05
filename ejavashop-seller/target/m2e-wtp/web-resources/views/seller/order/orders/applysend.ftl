<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/order/orders/"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<script language="javascript">
    var backUrl = "${currentBaseUrl}";
    var options = {
            url: '${currentBaseUrl}create',
            type: 'post',
            success: function (data) {
                if (data && null != data.success && data.success == true) {
                    window.location.href=backUrl+"state7";
                }else if(data.backUrl != null){
                    alert(data.message);
                    window.location.href=data.backUrl;
                }else{
                    refrushCSRFToken(data.csrfToken);
                    alert(data.	message);
                }
            }
        };
    function goback(){
    window.location.href=backUrl+"/state7";
    }
    
    function addGoods(){
       var numberDom = document.getElementsByName("number");
       var deliveredNumDom = document.getElementsByName("deliveredNum");
       var numDom = document.getElementsByName("num");
       var i=0;
       for(i=0;i<numberDom.length;i++){
          var leastNum = numberDom[i].value - deliveredNumDom[i].value;
          if(leastNum < numDom[i].value){
          $.messager.alert('提示', '本次发货数量不能大于剩余数量');
          numDom[i].focus();
          }
       }
       if($('#addForm').form('validate')){
	             $('#addForm').ajaxSubmit(options);
	       }
    }
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">预约发货
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}state7">返回</a>
            </span>
        </h2>

        <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
         <input type="hidden" name="orderId" value="${orderId}"/>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>收货人信息</dt>
                <dd class="dd-group">
                 <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>订单号: </label>
                            <input type="text" id="orderSn" name="orderSn"   class="txt w200 {required:true,maxlength:20}"
                                   data-options="required:true" value="${userInfo.orderSn}" readonly="readonly"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>收货人: </label>
                            <input type="text" id="memberName" name="memberName"   class="txt w200 {required:true,maxlength:20}"
                                   data-options="required:true" value="${userInfo.memberName}"/>
                        </p>
                    </div>
                    <br/>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>收货地址: </label>
                            <input type="text" id="addressAll" name="addressAll" value="${(userInfo.addressAll)!''}" class="txt w200 {required:true}" data-options="required:true"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>详细地址: </label>
                            <input type="text" id="addressInfo" name="addressInfo" value="${(userInfo.addressInfo)!''}" class="txt w200 {required:true}" data-options="required:true"/>
                        </p>
                    </div>
                    <br/>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>邮编: </label>
                            <input type="text" id="zipCode" name="zipCode" value="${(userInfo.zipCode)!''}" class="txt w200 {required:true}" data-options="required:true"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>手机: </label>
                            <input type="text" id="mobile" name="mobile" value="${(userInfo.mobile)!''}" class="txt w200 {required:true}" data-options="required:true"/>
                        </p>
                    </div>
                </dd>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>订单信息</dt>
                <dd class="dd-group">
                <div class="fluidbox">
                      <p class="p2 p-item">
		                            <label class="lab-item"  style="text-align:center;"><font class="red">*</font>商品: </label>
		              </p>
		              <p class="p2 p-item">
		              				<label class="lab-item" style="text-align:center;"><font class="red">*</font>属性: </label>
		              </p>
		              <p class="p2 p-item">
		              				<label class="lab-item" style="text-align:center;"><font class="red">*</font>SKU: </label>
		              </p>
		              <p class="p2 p-item">
		              				<label class="lab-item" style="text-align:center;"><font class="red">*</font>总数量: </label>
		              </p>
		              <p class="p2 p-item">
		              				<label class="lab-item" style="text-align:center;"><font class="red">*</font>已发数量: </label>
		              </p>
		              <p class="p2 p-item">
		              				<label class="lab-item" style="text-align:center;"><font class="red">*</font>本次发货: </label>
		              </p>
                </div>
                   <#list goodsInfo as goodsInfo>
                       <#if goodsInfo ?? && goodsInfo ?size &gt; 0>
                       <input type="hidden" name="orderGoodsId" value="${(goodsInfo.orderGoodsId)!''}"/>
                        <br/>
                           <div class="fluidbox">
		                        <p class="p2 p-item">
		                          <label class="lab-item" style="text-align:center;">${(goodsInfo.productName)!''}</label>
		                          <input type="hidden" name="productName" value="${(goodsInfo.productName)!''}"/>
	                            </p>
	                            <p class="p2 p-item">
	                              <label class="lab-item" style="text-align:center;"> ${goodsInfo.normName}</label>
	                              <input type="hidden" name="normName" value="${(goodsInfo.normName)!''}"/>
	                            </p>
	                            <p class="p2 p-item">
	                               <label class="lab-item" style="text-align:center;"> ${(goodsInfo.productSku)!''}</label>
	                               <input type="hidden" name="productSku" value="${(goodsInfo.productSku)!''}"/>
	                            </p>
	                            <p class="p2 p-item">
	                                 <label class="lab-item" style="text-align:center;">${(goodsInfo.number)!''}</label>
	                                 <input type="hidden" name="number" value="${(goodsInfo.number)!''}"/>
	                            </p>
	                            <p class="p2 p-item">
	                                 <label class="lab-item" style="text-align:center;">
	                                 ${(goodsInfo.deliveredNum)!''}
	                                 </label>
	                                 <input type="hidden" name="deliveredNum" value="${(goodsInfo.deliveredNum)}"/>
	                            </p>
	                            <p class="p2 p-item">
		                            <input type="text" id="num" name="num" class="txt w200 {required:true}" data-options="required:true" style="width:150px;"/>
	                            </p>
	                      </div>
                       </#if>
                    </#list>
                </dd>
            </dl>

            <#--2.batch button-------------->
            <p class="p-item p-btn">
                <input type="button" id="add" class="btn" value="新增" onclick="addGoods()"/>
                <input type="button" id="back" class="btn" value="返回" onclick="goback()"/>
            </p>
        </@form.form>
        </div>
    </div>
</div>

<#include "/seller/commons/_detailfooter.ftl" />