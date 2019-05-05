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
   function seeDetails(grade){
       //var grade = obj.options[obj.options.selectedIndex].value;
	   $.ajax({
 			type : "GET",
			url :  "${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/getParterSignArea.html",
			data : {
				memberId:grade,
			}, 
			dataType : "json",
			success : function(data) {
				if (data.success) {
					var htmlStr = "<option>--请选择--</option>";
					var ordersIdDivDom = $('#memberArea');
					ordersIdDivDom.empty();
					var memberAreaId = ${memberAreaId};
					 $.each(data.data, function(i, regions){
						 var selected = "";
						 if (memberAreaId == regions.id) selected = "selected";
						htmlStr = htmlStr +"<option value='"+regions.id+"' " + selected + ">"+regions.regionName+"</option>" ;
					 });
					 ordersIdDivDom.append(htmlStr);
				}
			},
			error : function() {
				jAlert("数据加载失败！");
			}
 		});
   }
   $(function (){
	   $('#querySales').click(function () {
		    var memberId = $('#memberTuijian option:selected').val();
		    if (memberId == "" || memberId == "--请选择--") {
		    	jAlert("请选择合伙人！");
		    	return;
		    }
		    
		    //var memberArea = $('#memberArea option:selected').val();
		   // if (memberArea == "" || memberArea == "--请选择--") {
		    //	jAlert("请选择区域！");
		    //	return;
		   // }
	   		$('#tuijianForm').submit();
	   });
	   var memberTuijianId = ${memberTuijianId};
	   if (memberTuijianId != 0) {
	   		seeDetails(memberTuijianId);
	   }
   });
   
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
					<a href='javascript:void(0)'>推荐合伙人销售明细</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
			    <div>
			    <form class="form-inline" role="form" method="post" action ="/member/parterTuijian.html" id="tuijianForm">
			    		<label for="name"  style ="width:100px;font-size: 16px;margin-left:10px;">合伙人：</label>
			    		<select id ="memberTuijian" name="memberTuijian" onchange="seeDetails(this.value)" class="form-control" style="margin-left: -35px;">
			    		     <option>--请选择--</option>
			    		     <#if memberList ??>
			    		        <#list memberList as member>
			    		        	<option value="${member.id}" <#if memberTuijianId ? exists && memberTuijianId?eval  == member.id > selected="selected" </#if>>${(member.realName)!''}</option>
			    		        </#list>
			    		     </#if>
			    		</select>
			    		<label for="name"  style ="width:100px;font-size: 16px;margin-left:30px;">合伙人区域：</label>
			    		<select id ="memberArea" name="memberArea" class="form-control" >
			    		 	<option>--请选择--</option>
			    		</select>
						<label for="name"  style ="width:100px;font-size: 16px;margin-left:30px;">日期：</label>
			    		<input type='text' name='startTime' id="startTime"  style="margin-left:-50px;width:120px;"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'});" class='form-control '
									<#if startTime ? exists  && startTime != ''>value = "${startTime}"</#if>>
									~
						<input type='text'  name='endTime'  id="endTime"  style="width:120px;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'});"  class='form-control' 
						<#if endTime ? exists  && startTime != ''>value = "${endTime}" </#if>>
									
						<input type="button" name="querySales" id="querySales" value="查 询"  class="btn btn-default">
					</form>
			    </div>
				<table class="table_1" id="refushtable" cellspacing="0" cellpadding="0" border="1" style="margin-top: 10px;">
					<tbody>
						<tr>
							<th >合伙人</th>
							<th >区域名称</th>
							<th>品牌袜销售额</th>
							<th>品牌袜提点</th>
							<th>裸袜销售额</th>
							<th>裸袜提点</th>
							<th>总销售额</th>
							<th>总提点</th>
							<th>开始时间</th>
							<th>结束时间</th>
						</tr>
						<#if parterTuijian ??>
							<#list parterTuijian as parterTuijian>
								<tr>
									<td>${(parterTuijian.parterName)!''}</td>
									<td>${parterTuijian.areaName}</td>
									<td>${parterTuijian.brandSales}</td>
									<td>${parterTuijian.brandPercent}</td>
									<td>${parterTuijian.luowaSales}</td>
									<td>${parterTuijian.luowaPercent}</td>
									<td>${parterTuijian.salesSum}</td>
									<td>${parterTuijian.percentSum}</td>
									<td>${(parterTuijian.startTime)!''}</td>
									<td>${(parterTuijian.endTime)!''}</td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
			</div>
			<#include "/front/commons/_pagination.ftl" />
		</div>
		<script type="text/javascript">
			$(function(){
				//控制左侧菜单选中
				$("#balance").addClass("currnet_page");
			});
		</script>

<#include "/front/commons/_endbig.ftl" />