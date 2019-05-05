<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/"/>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<script language="javascript">
	
$(function () {
	 
	 $("#addSKU").click(function () {
	 		var rowCount = parseInt($('#maxrow').val()) + 1;
            $("#myTable2").append("<tr><td><input type='text' name='q_sku"+ rowCount +"' id='q_sku"+ rowCount +"'  onchange='checkSKU("+ rowCount +")' style='width:120px;text-align:center;'></td>"
            +"<td><input type='text' name='q_normAttr"+ rowCount +"' id='q_normAttr"+ rowCount +"' value='袜颜色,' style='width:120px;text-align:center;'></td><td><input type='text' name='q_barCode"+ rowCount +"' id='q_barCode"+ rowCount +"' class = 'checkBarCode' style='width:120px;text-align:center;'>"
            +"</td><td><input type='text' name='q_barCodePL"+ rowCount +"' id='q_barCodePL"+ rowCount +"' class = 'checkBarCode' style='width:120px;text-align:center;'></td><td><input type='text' name='q_barCodeCS"+ rowCount +"' id='q_barCodeCS"+ rowCount +"' class = 'checkBarCode' style='width:120px;text-align:center;'></td>" 
            +"<td><input type='text' name='q_isVirtualBom"+ rowCount +"' id='q_isVirtualBom"+ rowCount +"' value='0' style='width:120px;text-align:center;'></td></tr>");
            $('#maxrow').val(rowCount);
        });
        
        
        var backUrl = "${currentBaseUrl}";
       	    var options = {
            url: '${currentBaseUrl}save',
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
       	    
       	 $(".checkBarCode").live("blur", function() {
 			checkBarCode(this, "重复的商品条码");
 		});
        
        $("#save").click(function(){
        	var state = $("#state").val();
        	if(state == 6){
        		alert("该商品处于上架状态，请先做下架操作");
        		return;
        	}
        	var count = parseInt($('#maxrow').val());
        	var sku_count = parseInt($('#maxrow_old').val());
        	for (var i = sku_count + 1; i <= count; i++) {
        		if ($("#q_sku" + i).val() == "" || $("#q_normAttr" + i).val() == "袜颜色,") {
        			alert("商品sku和挖颜色为必填项，请检查");
        			return;
        		}
        	}
        	//条码
        	var isRepeat = false;
        	$(".checkBarCode").each(function() {
					if ($(this).val().trim() == "") {
						alert("商品条码不能为空");
						$(this).focus();
						isRepeat = true;
						return;
					}					
				});
        	
        	if(isRepeat){
        		return false;
        	}
        	$("#addForm1").ajaxSubmit(options);
        });
	 
});
	
	function checkSKU(a){
        var sku_count = parseInt($('#maxrow_old').val());
        var sku_new = $("#q_sku" + a).val();
        var productId = $('#productId').val();
        $.ajax({
                type: "get",
                url: "${currentBaseUrl}valide",
                data : {productId:productId,sku:sku_new},
                dataType: "json",
                cache: false,
                success: function (data) {
	                if(data.total>0){
	                	alert(data.message);
	                	$("#q_sku" + a).val("");
	                }
                }
              });
	}
	
	function checkBarCode(obj, msg) {
		var value = $(obj).val().trim();
		if (value!="") {
			var isRepeat = false;
			var curreentIndex = $(".checkBarCode").index(obj);
	    	$(".checkBarCode").each(function() {
				index = $(".checkBarCode").index(this);
				if (index != curreentIndex) {
					if ($(this).val().trim() == value) {
						alert(msg);
						isRepeat = true;
						return false;
					}					
				}
			});
		}
		if (isRepeat) {
			$(obj).val("") ;
			$(obj).focus();
			return false;
		}
		validateBarCode(value, obj);
    }
    
    function validateBarCode(value, obj){
    	$.ajax({
			type : "POST",
			url :  '${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/validatesku',
			data : {
				barCode:value.trim(), sku:""
			},
			dataType : "json",
			success : function(data) {
				if(data.message!=""){
					alert(data.message);
					$(obj).val("");
					$(obj).focus();
				}
			},
			error : function() {
				alert("数据加载失败！");
			}
		});  
    }

</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">增加商品sku
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}waitSale">返回</a>
            </span>
        </h2>
        
        <div class="form-contbox">
		<@form.form method="post"  class="validForm" id="addForm1" name="addForm1">
				<dd class="dd-group">
        		       <table id ="myTable1" style="width:95%;margin-left: 30px"">
						  <thead>
							<tr rel="1" style='background-color:#CCC' height='35px'>
								<th>商品编码</th>
								<th>商品名称</th>
								<th>商城价</th>
								<th>商品上架时间</th>
								<th>商品价格类型</th>
								<th>商品关键字</th>
							</tr>
							<tr>
								<td>${(product.productCode)!''}</td>
								<td>${(product.name1)!''}</td>
								<td>${(product.mallPcPrice)!''}</td>
								<td>${(product.upTime)?string('yyyy-MM-dd HH:mm:ss')}</td>
								<td><#if product.priceStatus==1>一口价<#elseif product.priceStatus==2>阶梯价<#else>整箱价</#if></td>
								<td>${(product.keyword)!''}</td>
							</tr>
						  </thead>
						</table>
				 </dd>
				 <dd class="dd-group">
				 	<table id ="myTable" style="width:95%;margin-left: 30px"">
				 	  <tr>
				 	  	<td>
				 			<input type='button' class="btn" name="addSKu" id="addSKU" value='增加'/>
				 			<input type="button" id="save" class="btn" value="确定"/>
				 		</td>
				 		<td><input type='hidden' id='state' name='state' value='${product.state}'/></td>
				 		<td><input type='hidden' id='maxrow' name='maxrow' value='${maxrow}'/></td>
				 		<td><input type='hidden' id='maxrow_old' name='maxrow_old' value='${maxrow}'/></td>
				 		<td><input type='hidden' id='productId' name='productId' value='${(product.id)!''}'/></td>
				 		<td><input type='hidden' id='productGoodsId' name='productGoodsId' <#list productGoods as productGoods>value='${(productGoods.id)!''}'</#list>/></td>
				 	  </tr>
				 	</table>
				 </dd>
				 <dd class="dd-group">
        			 <table id ="myTable2" style="width:80%;margin-left: 30px"">
        			  <thead>
	        			  <tr rel="1" style='background-color:#CCC' height='35px'>
									<th style='width:10%;text-align:center;'>sku</th>
									<th style='width:10%;text-align:center;'>颜色</th>
									<th style='width:10%;text-align:center;'>商品条码（EA）</th>
									<th style='width:10%;text-align:center;'>商品条码（PL）</th>
									<th style='width:10%;text-align:center;'>商品条码（CS）</th>
									<th style='width:10%;text-align:center;'>是否虚拟组套(0:否，1：是)</th>
									<th style='width:10%;text-align:center;'>价格</th>
									<th style='width:10%;text-align:center;'>库存</th>
						 </tr>
		        	    <#if productGoods?? && productGoods?size &gt; 0>
		        			 <#list productGoods as productGoods>
		        			 	<tr>
		        			  		<td style="text-align:center;">${(productGoods.sku)!''}</td>
		        			  		<td style="text-align:center;">${(productGoods.normName)!''}</td>
		        			  		<td style="text-align:center;">${(productGoods.barCode)!''}</td>
		        			  		<td style="text-align:center;">${(productGoods.barCodePL)!''}</td>
		        			  		<td style="text-align:center;">${(productGoods.barCodeCS)!''}</td>
		        			  		<td style="text-align:center;">${(productGoods.isVirtualBom)!''}</td>
		        			  		<td style="text-align:center;">${(productGoods.mallPcPrice)!''}</td>
		        			  		<td style="text-align:center;">${(productGoods.productStock)!''}</td>
		        			  	</tr>
		        			 </#list>
		        	    </#if>
        			  </thread>
        			 </table>
        	</dd>
        	<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">您可以在此增加已有商品的SKU</label>
					</div>
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
							只能增加非上架状态下的sku，请注意不要填写重复的sku </label>
					</div>
				</dd>
			</dl>
        </@form.form>
        
        </div>
     </div>
</div>

<#include "/seller/commons/_detailfooter.ftl" />