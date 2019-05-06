<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/probaby/"/>
<style>
	iframe .panel-fit, .panel-fit body {
	    overflow: scroll;
	}
</style>
<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">商品预约收货
            <span class="s-poar">
                <a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/vertify">返回</a>
            </span>
        </h2>
		<#--1.addForm----------------->		
		<div class="form-contbox">
		<@form.form method="post" class="validForm" id="addForm1" name="addForm1">
			<p class="p12 p-item">
					<label><font class="red">*</font>商家名称：</label>
					<select name="seller_id" id="seller_id">
					<option value="">请选择商家</option>
						<#if sellers??>
	                     	<#list sellers as seller>
							<option value="${(seller.id)!}">${(seller.sellerName)!}</option>
							</#list>
						</#if>
					</select>&nbsp;&nbsp;
					<input type="button" value="选择商品" id="pro"/>
					<!--<input type="button" id="add" class="btn" value="增加"/>-->
            </p>

    	 <table id="myTb" style=" width:100%">
 			<thead>
 			<tr id="content" target="sid_user" rel="1" style='background-color:#ADD8E6' height='35px'>
  					<th data-field="sku" align="left">SKU</th>
  					<th data-field="color" align="left">颜色</th>
  					<th data-field="product_stock" align="left">仓库库存</th>
  					<th data-field="total_amount" align="left">预计入库量</th>
 			</tr>
 			</thead>
 			<tbody>
 					<input type="hidden" name="seller_id" id="seller_id" value="${seller_id!''}">
 			</tbody>
		</table>
		
		
    	<p class="p-item p-btn">
                <label><font class="red">*</font>预约入库时间: </label>
                <input type="text" id="date_probaby" name="date_probaby" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH'})" class="txt w120" data-options="required:true"/>
            		
          		<input type="button" id="save" class="btn" value="预约提交"/>
            	<input type="button" id="remove" class="btn" value="移除"/>
    	</p>
		    
			</@form.form>
          </div>
        </div>
       </div>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.validate.min.js"></script>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<div style="display: none">
<#include "goodsDialog.ftl"/>
</div>
<#include "/admin/commons/_detailfooter.ftl" />
<script language="javascript">
	function selectAll(a){
	    a=a+"";
		var arr = a.split(".");
		for(var i=0;i<arr[1];i++){
			document.getElementById(arr[0]+""+i).checked=true;
		}
	}

	function generateReciecptList(productIds) {
    	if(productIds==""){
			$.messager.alert('提示','请先选择需要预约的商品');
			return;
    	}
    	
    	var pds = productIds.split(",");
    	for (var i = 0; i < pds.length; i ++) {    	
	        $.ajax({
	            type: "GET",
	            dataType: "json",
	            url: "${currentBaseUrl}list?productId=" + pds[i],
	            success: function(json) {
	                var typeData = eval(json);
	                var data = typeData.rows;
	                var rownum = '';
	                $.each(data, function(i, n) {
	                    var tbBody = "";
	                    var colStr = n.normName;
	                    var color = '';
	                    if(colStr!=null||colStr!=""){
	                  	  	var c = colStr.split(",");
	                  	  	color = c[1].substr(0,2);
	                    }
	                    if(i==0){
	                    	rownum = data.length;
	                    	var w = n.productId+"."+rownum;
	                    	tbBody+="<tr style='background-color:#00FFFF' height='30px'><td align='left'><input type='checkbox' onchange='selectAll("+ w +")' name='dele' id='"+ n.productId +"' value='"+ rownum +"'/>" + "SPU" + n.productId + "</td><td></td><td></td><td></td></tr>"
	                    }
	                    tbBody += "<tr><td align='left' id='sku'><input type='checkbox'	name='dele' id='"+ n.productId + ""+ i +"' value='"+ n.sku +"'/>" + n.sku + "</td>" + "<td align='left'>" + color + "</td>" + "<td align='left'>"+ n.productStock +"</td><td align='left'><input class='easyui-numberbox w100' type='text' id='prop_stock' name='prop_stock' value='' data-options='required:true'></td>"
	                    +"<td id='"+ n.productId + ""+ i +"'></td><td><input type='hidden' name='q_sku' value='"+ n.sku +"'></td></tr>";
	                    $("#myTb").append(tbBody);
	                }
	                );
	            },
	            error: function(json) {
	                alert("加载失败!");
	            }
	        });
        }
	}

    $(function () {
		$('#pro').click(function(){
			var sellerId = $("#seller_id").val();
			if (sellerId == "") {
				$.messager.alert('提示','请先选择商家名称');
				return;
			}
			var url = "${(domainUrlUtil.EJS_URL_RESOURCES)!}/admin/product/listnopage?q_sellerId="+sellerId;
			$("#gd_dataGrid").datagrid({url: url});
			$('#goodsDialog').dialog('open');
			$('#gd_dataGrid').datagrid('unselectAll');
		});
	
        $("#back").click(function () {
            window.location.href = backUrl;
        });
        
        $("#remove").click(function(){
         if(confirm("确定要删除数据")){
        	var arr = document.getElementsByName("dele");
        	for(var i=0;i<arr.length;i++){
        	$(':checkbox[name=dele]').each(function(){
				if($(this).attr('checked')){
				$(this).closest('tr').remove();
				}
			});
			}
			}else{
				return;
			}
        });
        
        $("#add").click(function () {
        	//校验查询条件
        	var p_name1 = document.getElementById("name1").value;
        	var p_product_code = document.getElementById("product_code").value;
        	var p_seller_id = document.getElementById("seller_id").value;
        	if(p_name1==""&&p_product_code==""&&p_seller_id==""){
        		alert("请输入查询条件!")
        		return;
        	}
            $.ajax({
                type: "GET",
                dataType: "json",
                url: "${currentBaseUrl}list?str="+p_name1+","+p_product_code+","+p_seller_id,
                success: function(json) {
                    var typeData = eval(json);
                    var data = typeData.rows;
                    var rownum = '';
                    $.each(data, function(i, n) {
                        var tbBody = "";
                        var colStr = n.normName;
                        var color = '';
                        if(colStr!=null||colStr!=""){
                      	  	var c = colStr.split(",");
                      	  	color = c[1].substr(0,2);
                        }
                        if(i==0){
                        	rownum = data.length;
                        	var w = n.productId+"."+rownum;
                        	tbBody+="<tr style='background-color:#00FFFF' height='30px'><td align='left'><input type='checkbox' onchange='selectAll("+ w +")' name='dele' id='"+ n.productId +"' value='"+ rownum +"'/>" + "SPU" + n.productId + "</td><td></td><td></td><td></td></tr>"
                        }
                        tbBody += "<tr><td align='left' id='sku'><input type='checkbox'	name='dele' id='"+ n.productId + ""+ i +"' value='"+ n.sku +"'/>" + n.sku + "</td>" + "<td align='left'>" + color + "</td>" + "<td align='left'>"+ n.productStock +"</td><td align='left'><input class='easyui-numberbox w100' type='text' id='prop_stock' name='prop_stock' value='' data-options='required:true'></td>"
                        +"<td id='"+ n.productId + ""+ i +"'></td><td><input type='hidden' name='q_sku' value='"+ n.sku +"'></td></tr>";
                        $("#myTb").append(tbBody);
                    }
                    );
                },
                error: function(json) {
                    alert("加载失败!");
                }
            });
        });
        
        
        
        var backUrl = "${currentBaseUrl}";
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
                    window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/vertify";
                }
            }
        };
        
        $("#save").click(function(){
        	var p_date_probaby = document.getElementById("date_probaby").value;
        	if(p_date_probaby==""){
        		alert("请选择预约入库时间!");
        		return;
        	}
        	$("#addForm1").ajaxSubmit(options);
        });
        
        <#if message??>$.messager.progress('close');
        alert('${message}');</#if>
    });
</script>