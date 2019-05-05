<#include "/front/commons/_headbig.ftl" />

<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/userindex.css"/>
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap-select.min.css"/>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/My97DatePicker/WdatePicker.js'></script>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap-select.min.js'></script>
<style>

.wrapper_main strong {
    font-size: 16px;
    font-family: '\5fae\8f6f\96c5\9ed1';
}

</style>
<script type="text/javascript">
  $(function(){
   $('#querySalesPerson').click(function(){
	   //var areaIds = $("#regionsArea").val();
	   
	   //$("#regionsArea option:selected").each(function(){
        //   areaIds = areaIds + $(this).val() +",";
      // });
	  // $("#areaId").val(areaIds.substring(0,areaIds.length-1));
	   $("#areaId").val($("#regionsArea").val());
	   $("#person").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/member/panerTotalPerson.html")
		 .attr("method", "POST")
		 .submit();
   });
		var str=$('#areaId').val();
		if(str != null && str != '0'){
			var arr=str.split(',');
			//多选下拉框回显
			$('#regionsArea').val(arr);
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
					<a href='javascript:void(0)'>客户下单汇总</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<!--左侧导航 -->
			<#include "/front/commons/_left.ftl" />
			<!-- 右侧主要内容 -->
			<div class='wrapper_main myorder'>
			    <div>
			    <form class="form-inline" role="form" id = "person">
			        <input type= "hidden" name = "areaId" id = "areaId"  value="${areaId}"/>
			    	<div class="form-group">
							<label for="name" style ="width:100px;font-size: 16px">合同编号：</label>
							<select class="form-control" style= "width:220px;display: initial;margin-left:-20px;" name="signNo">
									<option >--请选择--</option>
								<#list parterSignList as parterSign> 
							        <option value="${parterSign.signNo}"  <#if signNo ? exists && signNo =parterSign.signNo > selected="selected" </#if>>${parterSign.signNo}</option>
								</#list>
							</select>
						</div>
			          <label for="name" style ="width:100px;font-size: 16px;margin-left:30px;">日期：</label>
			    		<input type='text' name='startTime' id="startTime"  style="margin-left:-50px;width:130px;"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'});"
									class='form-control ' value = "${startTime}">
									~
						<input type='text' name='endTime' id="endTime"  style="width:130px;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'});"
					    class='form-control'  value = "${endTime}">
					    
					    <div class="form-group">
                           <label class="col-sm-3 control-label" style="width: 90px;font-size: 16px;margin-top: 5px;">地区：</label>
                            <div class="col-sm-4" style="width: 180px;margin-left: -30px;">
                                <select id="regionsArea" name="parterArea" class="selectpicker show-tick form-control" multiple data-live-search="false" style="width: 160px;">
                                        <#if regionsAreaList ??>
											<#list regionsAreaList as regionsArea> 
										        <option value="${regionsArea.id}">${regionsArea.regionName}</option>
											</#list>
										</#if>
                                </select>
                            </div>
                          </div>
						<input type="button" name="querySales" id="querySalesPerson" value="查 询"  class="btn btn-default">
			   </form>
			    </div>
				<table class="table_1" id="refushtable" cellspacing="0" cellpadding="0" border="1"  style="margin-top: 10px;">
					<tbody>
						<tr>
							<th>排名</th>
							<th>用户</th>
							<th>联系方式</th>
							<th>收货人姓名</th>
							<th>收货地址</th>
							<th>下单时间</th>
							<th>交易额</th>
							<th>数量</th>
						</tr>
						<#if salesPersonVO ??>
							<#list salesPersonVO as salesPersonVO>
								<tr>
									<td>${salesPersonVO.sort}</td>
									<td>${salesPersonVO.memberName}</td>
									<td>${salesPersonVO.mobile}</td>
									<td>${salesPersonVO.userName}</td>
									<td>${salesPersonVO.addressAll}${salesPersonVO.addressInfo}</td>
									<td>${salesPersonVO.ordersTime}</td>
									<td>${salesPersonVO.sales}</td>
									<td>${salesPersonVO.number}</td>
								</tr>
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