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
	 		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/actgroup/update")
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
		<h2 class="h2-title">团购查看<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/seller/promotion/actgroup">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
				<dd class="dd-group">
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>团购分类：</label>
							<select name="type" disabled="disabled">
								<#if actGroupTypes ??>
									<#list actGroupTypes as actGroupType>
							  			<option value="${actGroupType.id}" <#if actGroupType.id==actGroup.type>selected</#if> >${actGroupType.name}</option>
							  		</#list>
							  	</#if>
							</select>
						</p>
					</div>
					<br/>
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商品：</label>
							<div id="productName" >${(actGroup.productName)!}</div>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>促销标题：</label>
							"${(actGroup.name)!}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>渠道：</label>
							<@cont.select id="channel" name="channel" codeDiv="CHANNEL" value="${(actGroup.channel)!}" mode="2" disabled="disabled"/>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>活动图片：</label>
							<#if (actGroup.image)??> 
								&nbsp;&nbsp;&nbsp;&nbsp;<a href="${domainUrlUtil.EJS_IMAGE_RESOURCES}${(actGroup.image)!''}" target="_blank">查看图片</a>
							</#if>
						</p>
					</div>
					<br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>市场价: </label>
                           ${(actGroup.marketPrice)!''}
                        </p>
                    </div>
                    <br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>团购价: </label>
                            ${(actGroup.price)!''}
                        </p>
                    </div>
                     <br/>
                    
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>库存：</label>
							${(actGroup.stock)!''}
						</p>
					</div>
					<br/>
					<div class="fluidbox">
						<label class="lab-item"><font class="red">*</font>活动时间：</label>
						${(actGroup.startTime?string('yyyy-MM-dd HH:mm:ss'))!''}
						~
						${(actGroup.endTime?string('yyyy-MM-dd HH:mm:ss'))!''}
					</div>
					<br/>
					<div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>活动描述: </label>
                        <div style="padding-left: 140px;padding-top: 2px;">
                        	<#noescape>
                        	${(actGroup.descinfo)!}
                        	</#noescape>
                        </div>
                        </p>
                    </div>
					<br/>
				</dd>
			</dl>

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