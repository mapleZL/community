<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/sendgoods/"/>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<script language="javascript">
  var backUrl = "${currentBaseUrl}";
       $(function () {
        var options = {
            url: '${currentBaseUrl}create',
            type: 'post',
            success: function (data) {
                if (data && null != data.success && data.success == true) {
                    window.location.href=backUrl;
                }else if(data.backUrl != null){
                    alert(data.message);
                    window.location.href=data.backUrl;
                }else{
                    refrushCSRFToken(data.csrfToken);
                    alert(data.message);
                }
            }
        };
  
  
  
      function goback(){
       window.location.href=backUrl;
      }
      function changecity(val){
        var cityDom = document.getElementById("cityId");
        cityDom.innerHTML="";
         $.ajax({
	        type:"get",
	        url: "${domainUrlUtil.EJS_URL_RESOURCES}/seller/regions/getRegionByParentId?parentId="+val,
	        success:function(data){
	                var selectOption = '<option value ="">-- 请选择 --</option>'
	                $.each(data.data, function(i, region){
	                        selectOption += "<option value=" + region.id + ">" + region.regionName + "</option>";
	                })
	                cityDom.innerHTML=selectOption;
	        }
	    });
      }
      
      function addGoods(){
            var validator = $('#addForm').validate();
            if(validator.form()){
                $('#addForm').ajaxSubmit(options);
            }
      }
      
        var attrCont = 0;
        var custCont = 0;
        //检索属性删除
        $('.delAttr').live("click", function(){
            if(attrCont <= 1){
                $.messager.alert('提示','请至少保留一条检索属性。');
                return;
            }
            attrCont--;
            $(this).parent().parent().remove();
        });
        
        //新增一个属性 检索属性
        var addAttrHtml = "<div class=\"fluidbox attr\">" + $('[class=addAttr]').parent().parent().prev().html() + " </div>";
        function addAttr(){
            $(this).parent().parent().before(addAttrHtml);
            $("input[type=text]").validatebox();
            attrCont++;
            }
      function querySku(ordersId){
	      if(ordersId ==null || ordersId ==""){
	       $.messager.alert('提示','订单编号不能为空。否则无法查询SKU。');
	      }
	       $.ajax({
	        type:"get",
	        url: backUrl+"queryOrdersId?ordersId="+ordersId,
	        success:function(data){
	                var selectOption = '<option value ="">-- 请选择 --</option>'
	                $.each(data.data, function(i, region){
	                        selectOption += "<option value=" + region.id + ">" + region.regionName + "</option>";
	                })
	                cityDom.innerHTML=selectOption;
	        }
	    });
      }
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">预约发货编辑
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>

        <#--1.addForm----------------->
        <div class="form-contbox">
        <@form.form method="post" class="validForm" id="addForm" name="addForm">
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>收货人信息</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>预约人: </label>
                            <input type="text" id="bookingUser" name="bookingUser"   class="txt w200 {required:true,maxlength:20}"
                                   data-options="required:true"/>
                        </p>
                    </div>
                    <br/>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>发货时间: </label>
                            <input type="text" id="sendTime" name="sendTime"
								class="txt w200 easyui-validatebox" missingMessage="活动时间必填"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
								value="${(actFlashSale.actDate?string('yyyy-MM-dd'))!''}" readonly="readonly">
					    </p>
                    </div>
                    <br/>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>收货人: </label>
                            <input type="text" id="name" name="name" value="${(attr.name)!''}" class="txt w200 {required:true}" data-options="required:true"/>
                        </p>
                    </div>
                    <br/>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>联系方式: </label>
                            <input type="text" id="mobile" name="mobile" value="${(attr.mobile)!''}" class="txt w200 {required:true,integer:true}" data-options="required:true"/>
                        </p>
                    </div>
                    <br/>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>手机: </label>
                            <input type="text" id="phone" name="phone" value="${(attr.phone)!''}" class="txt w200 {required:true,integer:true}" data-options="required:true" />
                        </p>
                    </div>
                    <br/>
                    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>收货地址：</label>
							<select  id="provinceId" name="provinceId" onchange = "changecity(this.value)">
								<option value="">-- 请选择 --</option>
								<#if provinceList ??>
		               			<#list provinceList as region>
		                   			<option value="${(region.id)!''}">${(region.regionName)!''}</option>
		               			</#list>
		           				</#if>
		       				</select>
		       				<select id="cityId" name="cityId">
								<option value="">-- 请选择 --</option>
								<#if cityList ??>
		                 		<#list cityList as region>
		                     	<option value="${(region.id)!''}">${(region.regionName)!''}</option>
		               			</#list>
		             			</#if>
		         			</select>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>详细地址：</label>
							<input type="text" class="easyui-validatebox txt w280" name="addressDetail"  id="addressDetail"  data-options="required:true,maxlength:50"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>总重量：</label>
							<input type="text" class="easyui-validatebox txt w280" name="weight"  id="weight"  data-options="required:true,double:true"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>运费：</label>
							<input type="text" class="easyui-validatebox txt w280" name="takePrice"  id="takePrice"  data-options="required:true,double:true"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>发货数量：</label>
							<input type="text" class="easyui-validatebox txt w280" name="sendNum"  id="sendNum"  data-options="required:true,integer:true"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>订单编号：</label>
							<input type="text" class="easyui-validatebox txt w280" name="ordersId"  id="ordersId"  data-options="required:true,integer:true" onblur="querySku(this.value)"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox attr">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>SKU: </label>
                            <input type="text" name="attrName" value="${(attr.name)!''}"  class="txt w200 easyui-validatebox" missingMessage="SKU必填" data-options="required:true,validType:'length[2,20]'"/>
                        </p>
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>数量: </label>
                            <input type="text" name="attrValue" value="${(attr.value)!''}" class="txt w200 easyui-validatebox" missingMessage="数量必填" data-options="required:true,integer:true"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:void(0);" style="color: #2A7AD2;" class="delAttr">删除</a>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <a href="javascript:void(0);" class="addAttr" onclick="addAttr()"><img src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/images/newclass.jpg" style="padding-left: 78px"/>新增一个SKU</a>
                        </p>
                    </div>
                    <br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>发货说明：</label>
							<input type="text" class="easyui-validatebox txt w280" name="remark"  id="remark"/>
						</p>
					</div>
                </dd>
            </dl>
            <dl class="dl-group">
                <dt class="dt-group"><span class="s-icon"></span>订单信息</dt>
                <dd class="dd-group">
                    <div class="fluidbox">
                        <label class="lab-item">帮助信息。</label>
                    </div>
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