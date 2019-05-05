<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/sendgoods/"/>
<style>
	iframe .panel-fit, .panel-fit body {
	    overflow: scroll;
	}
</style>
<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">编辑
            <span class="s-poar">
                <a class="a-back" href="${currentBaseUrl}">返回</a>
            </span>
        </h2>
		<#--1.addForm----------------->		
		<div class="form-contbox">
		<@form.form method="post" class="validForm" id="addForm1" name="addForm1">
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>发货地址：</label>
					<input class="txt w140" type="text" id="sendAddress" name="sendAddress" value="${bookingSendGoods.sendAddress!''}">
			</p>
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>支付方式：</label>
					<input class="txt w140" type="text" id="payType" name="payType" value="${bookingSendGoods.payType!''}">
			</p>
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>支付时间：</label>
					<input class="txt w140" type="text" id="payTime" name="payTime" value="${bookingSendGoods.payTime!''}">
			</p>sendNum
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>发货数量：</label>
					<input class="txt w140" type="text" id="sendNum" name="sendNum" value="${bookingSendGoods.sendNum!''}">
			</p>
			
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>数量：</label>
					<input class="txt w140" type="text" id="weight" name="weight" value="${bookingSendGoods.weight!''}">
			</p>
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>运费：</label>
					<input class="txt w140" type="text" id="takePrice" name="takePrice" value="${bookingSendGoods.takePrice!''}">
			</p>
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>预订人：</label>
					<input class="txt w140" type="text" id="bookingUser" name="bookingUser" value="${bookingSendGoods.bookingUser!''}">
			</p>sendNum
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>联系方式：</label>
					<input class="txt w140" type="text" id="mobile" name="mobile" value="${bookingSendGoods.mobile!''}">
			</p>
			
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>数量：</label>
					<input class="txt w140" type="text" id="weight" name="weight" value="${bookingSendGoods.weight!''}">
			</p>
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>运费：</label>
					<input class="txt w140" type="text" id="takePrice" name="takePrice" value="${bookingSendGoods.takePrice!''}">
			</p>
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>预订人：</label>
					<input class="txt w140" type="text" id="bookingUser" name="bookingUser" value="${bookingSendGoods.bookingUser!''}">
			</p>sendNum
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>联系方式：</label>
					<input class="txt w140" type="text" id="mobile" name="mobile" value="${bookingSendGoods.mobile!''}">
			</p>
			
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>手机：</label>
					<input class="txt w140" type="text" id="weight" name="weight" value="${bookingSendGoods.weight!''}">
			</p>
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>运费：</label>
					<input class="txt w140" type="text" id="takePrice" name="takePrice" value="${bookingSendGoods.takePrice!''}">
			</p>
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>预订人：</label>
					<input class="txt w140" type="text" id="bookingUser" name="bookingUser" value="${bookingSendGoods.bookingUser!''}">
			</p>sendNum
			<p class="p3 p-item">
					<label class="lab-item"><font class="red"></font>联系方式：</label>
					<input class="txt w140" type="text" id="mobile" name="mobile" value="${bookingSendGoods.mobile!''}">
			</p>

    	 <table id="myTb" style=" width:100%">
 			<thead>
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
          		<input type="button" id="save" class="btn" value="预约提交"/>
    	</p>
		    
			</@form.form>
          </div>
        </div>
       </div>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.validate.min.js"></script>
<script src="${domainUrlUtil.EJS_URL_RESOURCES}/resources/admin/jslib/js/jquery.form.js"></script>
<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<#include "/admin/commons/_detailfooter.ftl" />
<script language="javascript">

    $(function () {
      
        
        <#if message??>$.messager.progress('close');
        alert('${message}');</#if>
    });
</script>