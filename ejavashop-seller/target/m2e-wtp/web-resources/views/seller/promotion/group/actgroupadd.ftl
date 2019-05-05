<#include "/seller/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<link href="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/umeditor/lang/zh-cn/zh-cn.js"></script>

<script language="javascript">
$(function(){
	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/actgroup";
	});
	$("#add").click(function(){
		
		if($("#addForm").form('validate')){
			var productId = $('#productId').val();
			if(productId == '') {
				$.messager.alert('提示','请选择商品');
				return;
			}
			var startTime = $('#startTime').val();
			var endTime = $('#endTime').val();
			
			var startInt = new Date(startTime.replace("-", "/").replace("-", "/"));
			var endInt = new Date(endTime.replace("-", "/").replace("-", "/"));
			
			if(endInt <= startInt) {
				$.messager.alert('提示','活动结束时间不能小于开始时间');
				return;
			}
			
			var descinfo = UM.getEditor('myEditor').getContent();
            $('#descinfo').val(descinfo);//商品描述信息
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/actgroup/create")
  				 .attr("method", "POST")
  				 .submit();
  		}
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
		<h2 class="h2-title">添加团购<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/actgroup">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
							<input type="hidden" id="productId" name="productId" />
							<input type="hidden" id="descinfo" name="descinfo" />
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>团购分类：</label>
							<select name="type">
								<#if actGroupTypes ??>
									<#list actGroupTypes as actGroupType>
							  			<option value = "${actGroupType.id}">${actGroupType.name}</option>
							  		</#list>
							  	</#if>
							</select>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>选择商品：<image id="selectProduct" href="javascript:void(0)" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/easyui/themes/icons/search.png" /></label>
							<label id="productName" />
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
							<input class="easyui-validatebox txt w400" type="text" id="title" name="name" missingMessage="必须填写，输入2到200个字符" value="${(actGroup.name)!}" data-options="required:true,validType:'length[2,200]'" >
						</p>
					</div>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                促销标题必须填写，输入2到200个字符
                            </font>
                        </p>
                    </div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>渠道：</label>
							<@cont.select id="channel" name="channel" codeDiv="CHANNEL" mode="2"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动图片：</label>
							<input type="file" id="imageFile" name="imageFile"
								style="height: 21px; float: left;line-height: 30px; vertical-align: middle;"
								missingMessage="请选择图片"
								class="txt w200 easyui-validatebox" data-options="required:true" />
						</p>
					</div>
					<br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>市场价: </label>
                            <input type="text" id="marketPrice" name="marketPrice" value="${(actGroup.marketPrice)!''}" class="txt w200 easyui-numberbox" data-options="min:0,max:99999,precision:2,required:true"/>
                        </p>
                    </div>
                    <br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>团购价: </label>
                            <input type="text" id="price" name="price" value="${(actGroup.price)!''}" class="txt w200 easyui-numberbox" data-options="min:0,max:99999,precision:2,required:true"/>
                        </p>
                    </div>
                     <br/>
                    
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>限购数量：</label>
							<input class="txt w200 easyui-numberbox" id="purchase" name="purchase" value="${(actGroup.purchase)!''}" data-options="required:true,min:1,max:9999999" >
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
							<label class="lab-item"><font class="red">*</font>库存：</label>
							<input class="txt w200 easyui-numberbox" id="stock" name="stock" value="${(actGroup.stock)!''}" data-options="required:true,min:0,max:9999999" >
						</p>
					</div>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                                库存表示可以卖多少产品
                            </font>
                        </p>
                    </div>
					<br/>
					<div class="fluidbox">
						<label class="lab-item"><font class="red">*</font>活动时间：</label>
						<input type="text" id="startTime" name="startTime"
								class="txt w200 easyui-validatebox"
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"
								value="${(actGroup.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
						~
						<input type="text" id="endTime" name="endTime"
								class="txt w200 easyui-validatebox" 
								data-options="required:true"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});"
								value="${(actGroup.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly">
					</div>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label>
                            <font style="color: #808080">
                               只有在当前时间内才能看到此团购商品
                            </font>
                        </p>
                    </div>
					<br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>活动描述: </label>
                        <div style="padding-left: 140px;padding-top: 2px;">
                        <script type="text/plain" id="myEditor" style="width:600px;height:240px;">
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
				<input type="button" id="add" class="btn" value="新增" />
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