<#include "/front/commons/_headbig.ftl" />

<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/userindex.css"/>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/My97DatePicker/WdatePicker.js'></script>
<style>

.wrapper_main strong {
    font-size: 16px;
    font-family: '\5fae\8f6f\96c5\9ed1';
}

</style>
<script>
   function seeDetails(ordersId){
	   $.ajax({
 			type : "GET",
			url :  "${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/getSalesDetailsByOrdersId.html",
			data : {
				ordersId:ordersId,
			}, 
			dataType : "json",
			success : function(data) {
				if (data.success) {
					var htmlStr = "<tr>"+
					 					  "<th>SKU</th>"+
					 					  "<th>单价</th>"+
					 					  "<th>加工费</th>"+
					 					  "<th>辅料费</th>"+
					 					  "<th>数量</th>"+
					 					  "<th colspan='2'>总金额</th>"+
					 					  "<th colspan='2'>退货数量</th>"+
					 					  "<th colspan='2'>退货金额</th>"+
										"</tr>";
					var ordersIdDivDom = $('#'+ordersId);
					ordersIdDivDom.append("");
					 $.each(data.data, function(i, salesDetailsVO){
						htmlStr = htmlStr + "<tr>"+
						                               "<td style='background-color:#fbf7f7;'>"+salesDetailsVO.orderSn+"</td>" +
						                               "<td style='background-color:#fbf7f7;'>"+salesDetailsVO.moneyLogistics+"</td>" +
						                               "<td style='background-color:#fbf7f7;'>"+salesDetailsVO.servicePrice+"</td>" +
						                               "<td style='background-color:#fbf7f7;'>"+salesDetailsVO.labelPrice+"</td>" +
						                               "<td style='background-color:#fbf7f7;'>"+salesDetailsVO.number+"</td>" +
						                               "<td colspan='2' style='background-color:#fbf7f7;'>"+salesDetailsVO.moneyOrder+"</td>" +
						                               "<td colspan='2' style='background-color:#fbf7f7;'>"+salesDetailsVO.returnNumber+"</td>" +
						                               "<td colspan='2' style='background-color:#fbf7f7;'>"+salesDetailsVO.endMoney+"</td>" +
													  "</tr>";
					 });
					 ordersIdDivDom.append(htmlStr);
					 $('#xx_'+ordersId).hide();
					 $('#vv_'+ordersId).show();
				}
			},
			error : function() {
				jAlert("数据加载失败！");
			}
 		});
   }
   
   function seeDetails1(ordersId){
	     $('#xx_'+ordersId).show();
		 $('#vv_'+ordersId).hide();
		 var ordersIdDivDom = $('#'+ordersId);
	     ordersIdDivDom.empty("");
   }
</script>

		<div class='container w'>
			<div class='breadcrumb'>
				<strong class='business-strong'>
					<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>首页</a>
				</strong>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>大袜网</a>
				</span>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>销售明细表</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
			    <div>
			    <form class="form-inline" role="form" method="post" action ="/member/getSalesDetails.html">
			    		<div class="form-group">
							<label for="name" style ="width:100px;font-size: 16px">合同编号：</label>
							<select class="form-control" style= "width:220px;display: initial;margin-left:-20px;" name="signNo">
									<option >--请选择--</option>
								<#list parterSignList as parterSign> 
							        <option value="${parterSign.signNo}" <#if signNo ? exists && signNo =parterSign.signNo > selected="selected" </#if>>${parterSign.signNo}</option>
								</#list>
							</select>
						</div>
						<label for="name"  style ="width:100px;font-size: 16px;margin-left:30px;">日期：</label>
			    		<input type='text' name='startTime' id="startTime"  style="margin-left:-50px;"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'});" class='form-control '
									value = "${startTime}">
									~
						<input type='text'  name='endTime'  id="endTime"  
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'});"  class='form-control' 
						value = "${endTime}">
									
						<input type="submit" name="querySales" id="querySales" value="查 询"  class="btn btn-default">
					</form>
			    </div>
				<table class="table_1" id="refushtable" cellspacing="0" cellpadding="0" border="1"  style="margin-top: 10px;">
					<tbody>
						<tr>
						    <th></th>
							<th >订单号</th>
							<th >出库时间</th>
							<th>买家用户名</th>
							<th>商品金额</th>
							<th>加工费</th>
							<th>辅料费</th>
							<th>运费</th>
							<th>订单总金额</th>
							<th>退货总金额</th>
							<th>最终结算金额</th>
						</tr>
						<#if salesDetailsVO ??>
							<#list salesDetailsVO as salesDetailsVO>
								<tr>
								    <td><span style="cursor:pointer" onclick = "seeDetails('${salesDetailsVO.ordersId}')"  id="xx_${salesDetailsVO.ordersId}"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/fwc/sign_add.png" height="28" width="28" alt=""></span>
								    <span style="cursor:pointer;display:none" onclick = "seeDetails1('${salesDetailsVO.ordersId}')"  id="vv_${salesDetailsVO.ordersId}"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/fwc/sign_delete.png" height="28" width="28" alt=""></span></td>
									<td>${salesDetailsVO.orderSn}</td>
									<td>${salesDetailsVO.ordersTime}</td>
									<td>${salesDetailsVO.name}</td>
									<td>${salesDetailsVO.moneyProduct}</td>
									<td>${salesDetailsVO.servicePrice}</td>
									<td>${(salesDetailsVO.labelPrice)!''}</td>
									<td>${salesDetailsVO.moneyLogistics}</td>
									<td>${salesDetailsVO.moneyOrder}</td>
									<td>${(salesDetailsVO.returnMoneyOrder)!''}</td>
									<td>${(salesDetailsVO.endMoney)!''}</td>
								</tr>
								 <tbody id ="${salesDetailsVO.ordersId}"></tbody>
							</#list>
						</#if>
					</tbody>
				</table>
			</div>
			<#include "/front/commons/_paginationutil.ftl" />
		</div>
		<script type="text/javascript">
			$(function(){
				//控制左侧菜单选中
				$("#balance").addClass("currnet_page");
			});
		</script>

<#include "/front/commons/_endbig.ftl" />