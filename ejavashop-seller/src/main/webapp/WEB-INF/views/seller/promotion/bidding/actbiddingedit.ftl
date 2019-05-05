<#include "/seller/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<link href="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/lang/zh-cn/zh-cn.js"></script>

<script language="javascript">
$(function(){
	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/actbidding";
	});
	$("#add").click(function(){
		var r = /^[0-9]*[1-9][0-9]*$/　　//正整数  
		var saleNumberAll=$("input[name='saleNumberAll']")
		var numberSaleNumber = saleNumberAll.length;
		for(var i=0; i<numberSaleNumber; i++) {
			var saleNumber = saleNumberAll[i].value;
			if(!(r.test(saleNumber))) {
				$.messager.alert('提示','销量请输入正整数');
				return;
			}
		}
		if(numberSaleNumber > 1) {
			for(var i=0; i<numberSaleNumber; i++) {
				if((i+1) != numberSaleNumber) {
					var saleNumber1 = saleNumberAll[i].value;
					var saleNumber2 = saleNumberAll[i+1].value;
					if(Number(saleNumber1) >= Number(saleNumber2)) {
						$.messager.alert('提示','销量是逐级累加的');
						return;
					}
				}
			}
		}
		
		var rPrice = /^[0-9]*[1-9][0-9]*$/　　//正整数  
		var priceNmberAll=$("input[name='priceNmberAll']")
		var priceNmberNumber = priceNmberAll.length;
		for(var i=0; i<priceNmberNumber; i++) {
			var priceNmber = priceNmberAll[i].value;
			if(!(rPrice.test(priceNmber))) {
				$.messager.alert('提示','价格请输入正整数');
				return;
			}
		}
		if(priceNmberNumber > 1) {
			for(var i=0; i<priceNmberNumber; i++) {
				if((i+1) != priceNmberNumber) {
					var priceNmber1 = priceNmberAll[i].value;
					var priceNmber2 = priceNmberAll[i+1].value;
					if(Number(priceNmber1) <= Number(priceNmber2)) {
						$.messager.alert('提示','价格是逐级递减的');
						return;
					}
				}
			}
		}
		
		if($("#addForm").form('validate')){
			var productId = $('#productId').val();
			if(productId == '') {
				$.messager.alert('提示','请选择商品');
				return;
			}
			var startTime = $('#startTime').val();
			var endTime = $('#endTime').val();
			var firstEndTime = $('#firstEndTime').val();
			var lastEndTime = $('#lastEndTime').val();
			
			var startInt = new Date(startTime.replace("-", "/").replace("-", "/"));
			var endInt = new Date(endTime.replace("-", "/").replace("-", "/"));
			var firstEndTimeInt = new Date(firstEndTime.replace("-", "/").replace("-", "/"));
			var lastEndTimeInt = new Date(lastEndTime.replace("-", "/").replace("-", "/"));
			
			if(endInt <= startInt) {
				$.messager.alert('提示','活动结束时间不能小雨开始时间');
				return;
			}
			if(firstEndTimeInt <= endInt) {
				$.messager.alert('提示','首付款结束时间不能小于活动结束时间');
				return;
			}
			if(lastEndTimeInt <= firstEndTimeInt) {
				$.messager.alert('提示','尾款结束时间不能小于首付款结束时间');
				return;
			}
			
			var descinfo = UM.getEditor('myEditor').getContent();
            $('#descinfo').val(descinfo);//商品描述信息
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/actbidding/update")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});
	
	$("#addActivityBiddingPrice").click(function(){
		var addActivityBiddingPrice = "";
	 	addActivityBiddingPrice += "<p class='p12 p-item'>";
	 	addActivityBiddingPrice += "<label class='lab-item'>&nbsp;</label>";
	 	addActivityBiddingPrice += "销量：<input type='text' id='saleNumber' name='saleNumberAll' class='txt w200 easyui-numberbox' data-options='min:1,max:99999,required:true'/>";
	 	addActivityBiddingPrice += "价格：<input type='text' id='priceNmber' name='priceNmberAll' class='txt w200 easyui-numberbox' data-options='min:1,max:99999,required:true'/>";
	 	addActivityBiddingPrice += "<a href='javascript:void(0);' onclick='delMethod(this)'>删除</a></p>";
	 	$("#activityBiddingPrice").append(addActivityBiddingPrice);
	 	//$("input[type=text]").validatebox();
	 	//$("input[name=saleNumber]").numberbox();
	 	//$("input[name=priceNmber]").numberbox();
    });
	
	$('#selectProduct').click(function(){
    	$('#productDlg').dialog('open');
    });
        
	<#if message??>$.messager.progress('close');$.messager.alert('提示','${message}');</#if>
})

function delMethod(obj) {
	obj.parentNode.remove();
}

function productCallBack(data){
    $('#productId').val('');
    $('#productName').html("");
    if(data && data.length > 0){
        var productName = '';
        var productId = '';
        $.each(data, function(j, n){
            productId = n.id;
            productName = n.name1;
        })
         $('#productId').val(productId);
         $('#productName').html(productName);
    }
}
    
</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">修改团购<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/actbidding">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			
			<input type="hidden" id="productId" name="productId" value="${(actBidding.productId)!}"/>
			<input type="hidden" id="descinfo" name="descinfo" />
			<input type="hidden" id="id" name="id" value="${(actBidding.id)!}"/>
			
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>集合竞价分类：</label>
							<select name="type">
								<#if actBiddingTypes ??>
									<#list actBiddingTypes as actBiddingType>
							  			<option value="${actBiddingType.id}" <#if actBiddingType.id==actBidding.type>selected</#if>>${actBiddingType.name}</option>
							  		</#list>
							  	</#if>
							</select>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>选择商品：<image id="selectProduct" href="javascript:void(0)" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/easyui/themes/icons/search.png" /></label>
							<span id="productName">${(actBidding.productName)!}</span>
						</p>
					</div>
					 <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                商品必须在商城销售，并且一个商品只能做一次活动。
                            </font>
                        </p>
                    </div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>促销标题：</label>
							<input class="easyui-validatebox txt w400" type="text" id="title" name="name" missingMessage="必须填写，输入2到100个字符" value="${(actBidding.name)!}" data-options="required:true,validType:'length[2,100]'" >
						</p>
					</div>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                促销标题必须填写，输入2到100个字符
                            </font>
                        </p>
                    </div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>渠道：</label>
							<@cont.select id="channel" name="channel" codeDiv="CHANNEL" mode="2" value="${(actBidding.channel)!}"/>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动图片：</label>
							<input type="file" id="imageFile" name="imageFile"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200"/>
							<#if (actBidding.image)??> 
								&nbsp;&nbsp;&nbsp;&nbsp;<a href="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(actBidding.image)!''}" target="_blank">查看图片</a>
							</#if>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>市场价: </label>
                            <input type="text" id="marketPrice" name="marketPrice" value="${(actBidding.marketPrice)!''}" class="txt w200 easyui-numberbox" data-options="min:0,max:99999,precision:2,required:true"/>
                        </p>
                    </div>
                    <br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>初始价格: </label>
                            <input type="text" id="price" name="price" value="${(actBidding.price)!''}" class="txt w200 easyui-numberbox" data-options="min:0,max:99999,precision:2,required:true"/>
                        </p>
                    </div>
                     <br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>首付款项: </label>
                            <input type="text" id="firstPrice" name="firstPrice" value="${(actBidding.firstPrice)!''}" class="txt w200 easyui-numberbox" data-options="min:0,max:99999,precision:2,required:true"/>
                        </p>
                    </div>
                    <br/>
                    
                    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>限购数量：</label>
							<input class="txt w200 easyui-numberbox" id="purchase" name="purchase" value="${(actBidding.purchase)!''}" data-options="required:true,min:1,max:9999999" >
						</p>
					</div>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                限购数量，没人每次下单最多可以购买多少商品，最少为1
                            </font>
                        </p>
                    </div>
					<br/>
					
                    <div class="fluidbox">
                        <p class="p12 p-item">
	                    	<label class="lab-item">阶梯价格：</label>
                            <a href="javascript:void(0);" id="addActivityBiddingPrice"><img src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/images/newclass.jpg" style="padding-left: 78px"/>新增阶梯价格</a>
                        </p>
                    </div>
                    <div class="fluidbox">
                    	<#if actBiddingPrices??>
	                    	<#list actBiddingPrices as actBiddingPrice>
	                    		<p class='p12 p-item'>
		 						<label class='lab-item'>&nbsp;</label>
	 							销量：<input type='text' id='saleNumber' name='saleNumberAll' value="${(actBiddingPrice.saleNum)!}" class='txt w200 easyui-numberbox' data-options='min:1,max:99999,required:true'/>
	 							价格：<input type='text' id='priceNmber' name='priceNmberAll' value="${(actBiddingPrice.price)!}" class='txt w200 easyui-numberbox' data-options='min:1,max:99999,required:true'/>
	 							<a href='javascript:void(0);' onclick='delMethod(this)'>删除</a>
								</p>	
							</#list>
						</#if>
                        <div id="activityBiddingPrice" />
                    </div>
                   <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                请认真填写，销售额到相应数量之后，享受不同的价格，不填写销量不管为多少价格都是上面填写的初始价格。
                            </font>
                        </p>
                    </div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>库存：</label>
							<input class="txt w200 easyui-numberbox" id="stock" name="stock" value="${(actBidding.stock)!''}" data-options="required:true,min:0,max:9999999" >
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<label class="lab-item"><font class="red">*</font>活动时间：</label>
						<input type="text" id="startTime" name="startTime"
								class="txt w200 easyui-validatebox"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"
								value="${(actBidding.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						~
						<input type="text" id="endTime" name="endTime"
								class="txt w200 easyui-validatebox" 
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});"
								value="${(actBidding.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>首付款截止时间：</label>
							<input type="text" id="firstEndTime" name="firstEndTime"
								class="txt w200 easyui-validatebox"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'endTime\')}'});"
								value="${(actBidding.firstEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						</p>
					</div>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                首付款付款截止时间，该时间不小于活动结束时间。
                            </font>
                        </p>
                    </div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>尾款款截止时间：</label>
							<input type="text" id="lastEndTime" name="lastEndTime"
								class="txt w200 easyui-validatebox"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'firstEndTime\')}'});"
								value="${(actBidding.lastEndTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						</p>
					</div>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                尾款截止时间，该时间必须大于首付款截止时间。
                            </font>
                        </p>
                    </div>
					<br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>活动描述: </label>
                        <div style="padding-left: 140px;padding-top: 2px;">
                        <script type="text/plain" id="myEditor" style="width:600px;height:240px;">
                        	<#noescape>
                        		${(actBidding.descinfo)!}
                        	</#noescape>
						</script>
						<script type="text/javascript">
					    	var um = UM.getEditor('myEditor');
						</script>
                        </div>
                        </p>
                    </div>
					<br/>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="修改" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<div style="display: none">
	<div id="productDlg" class="easyui-dialog popBox" title="商品列表"
		style="width: 980px; height: 520px;"
		data-options="resizable:true,closable:true,closed:true,cache: false,modal: true"
		buttons="#dlg-buttons-brand">
	
		<div class="easyui-layout" data-options="fit:true">
			<table id="brandDataGrid" class="easyui-datagrid"
				data-options="
							rownumbers:true,
							autoRowHeight:false,
							striped : true,
							singleSelect : true,
							fit:true,
							fitColumns:true,
							pagination:true,
							pageSize:'20',
							url:'${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/list?q_state=6',
							method:'get'">
				<thead>
					<tr>
						<th field="name1" width="300" align="left">商品名称</th>
						<th field="productStock" width="150" align="center">商品库存</th>
						<th field="malMobilePrice" width="150" align="center">商品价格</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="dlg-buttons-brand" style="text-align: right">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="productSubmit()">确定</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#productDlg').dialog('close')">取消</a>
		</div>
	</div>
	
	<script language="javascript">
		function productSubmit() {
			var selectedRow = $("#brandDataGrid").datagrid('getSelections');
			if (selectedRow == null) {
				$.messager.alert('友情提示', '请至少选择一个对象');
				return false;
			}
			var callbackfunc = eval('productCallBack');
			callbackfunc(selectedRow);
			$("#productDlg").dialog('close');
		}
	</script>
</div>

<#include "/seller/commons/_detailfooter.ftl" />