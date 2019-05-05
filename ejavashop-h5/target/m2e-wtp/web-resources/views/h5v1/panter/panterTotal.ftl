<#include "/h5v1/commons/_head.ftl" />
<#assign cartNum = 0>
<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
<link rel="stylesheet" href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/resources/panter/css/style.css">
<body style="background:#fff">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

    <h1 class="am-header-title">
    	<#if type ? exists && type == '1'>累计销售汇总表</#if>
    	<#if type ? exists && type == '2'>年度汇总表</#if>
    	<#if type ? exists && type == '3'>分类销售汇总表</#if>
    	<#if type ? exists && type == '4'>合伙人提点统计表</#if>
    	<#if type ? exists && type == '5'>合伙人推荐合伙人</#if>
    </h1>
    <input type="hidden" id= "type" value = "${type}">
    <div class="am-header-right am-header-nav">
        <a href="b-product_list.html" style="color:#ffffff; font-weight: bolder">筛选</a>
    </div>
</header>
<div class="divcons"></div>
<!--头部end-->
<!--侧边栏-->
<nav data-am-widget="menu" class="am-menu  am-menu-offcanvas1"  data-am-menu-offcanvas style="padding:0">
    <a href="javascript: void(0)" class="am-menu-toggle" style="width:33.3%;position: fixed;z-index: 10000;top: 10px;"  id ="sel_filter">
    </a>
    <div class="am-offcanvas">
      <div class="am-offcanvas-bar am-offcanvas-bar-flip" style="background:#eee;">	
      	<div class="ico public"><h3 class="em2 f16">筛选&nbsp;&nbsp;</h3></div>
         <div class="mt10 public ico">
         	<div class="am-alert am-alert-danger" id="my-alert" style="display: none">
			  <p>开始日期应小于结束日期！</p>
			</div>
			<div class="am-g">
              <div class="am-u-sm-12">
                    <select placeholder="请选择合同编号" data-am-selected="{btnWidth: '100%', btnStyle: 'am-header-default'}" name = "signNo" id = "signNo">
                        <#if parterSignList ??>
                        	<#list parterSignList as parterSign>
	                        	<#if type ? exists &&  type == '5'>
	                        		<option value="${parterSign.memberId}">${parterSign.signNo}</option>
	                             <#else>
                        			<option value="${parterSign.signNo}">${parterSign.signNo}</option>
	                             </#if>
                        	</#list>
                        </#if>
                    </select>
              </div>
              <#if type ? exists && type == '1'>
              		<div class="am-u-sm-12"><br>
                    <select placeholder="请选择地年份"  data-am-selected="{btnWidth: '100%', btnStyle: 'am-header-default'}" name = "year" id = "year">
                        	<option value="2016">2016</option>
                        	<option value="2017">2017</option>
                        	<option value="2018">2018</option>
                        	<option value="2018">2019</option>
                        	<option value="2018">2020</option>
                    </select>
                </div>
              </#if>
              <#if type ? exists && type == '4' || type == '5'>
                <div class="am-u-sm-12"><br>
                    <select placeholder="请选择地区"  data-am-selected="{btnWidth: '100%', btnStyle: 'am-header-default'}" name = "areaId" id = "areaId">
                    	<#if regionsList ??>
                    		<#list regionsList as regions>
                        		<option value="${regions.id}">${regions.regionName}</option>
                        	</#list>
                    	</#if>
                    </select>
                </div>
                </#if>
                <#if type ? exists && (type == '3' || type == '4' || type == '5')>
	                <div class="am-u-sm-12"><br>
	                    <input type="text" class="am-form-field" placeholder="合同开始日期" id="startDate" readonly/>
	                </div>
	                <div class="am-u-sm-12"><br>
	                    <input type="text" class="am-form-field" placeholder="合同结束日期" id="endDate" readonly/>
	                </div>
                </#if>
			</div>
         </div>
        <div class="tp4" style="bottom:0">
        	<a href="javascript:void(0);" class="reset_btn">重选</a>
            <a href="javascript:void(0);" class="sure_btn">确定</a>
        </div>
      </div>
    </div>
</nav>
<!--end-->
<article>
	<div id="loading">
		<div style="position:fixed; top: 0px; right:0px; bottom:0px;filter: alpha(opacity=60); background-color: #777;z-index: 1002; left: 0px; opacity:0.5; -moz-opacity:0.5;"></div>
		<center style="position:fixed; left:43%; top: 45%"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/loading-2.gif"></center>
	</div>
	<table class="rwd-table">
	  <tr class = "table-tr" style="border-top: 0px;">
		  <#if type ? exists && type == '1'>
		  		<th>月份</th>
				<th>客户数</th>
				<th>交易额</th>
				<th>新增客户数</th>
				<th>新增交易额</th>
		  </#if>
		  <#if type ? exists && type == '2'>
		  		<th>年份</th>
				<th>客户数</th>
				<th>交易额</th>
				<th>新增客户数</th>
				<th>新增交易额</th>
		  </#if>
		  <#if type ? exists && type == '3'>
					<th>类型</th>
					<th>(销售)客户数</th>
					<th>(销售)数量</th>
					<th>(销售)商品金额</th>
					<th>(退货)客户数</th>
					<th>(退货)数量</th>
					<th>(退货)商品金额</th>
					<th>总数量</th>
					<th>商品总金额</th>
		  </#if>
		  <#if type ? exists && type == '4' || type == '5'>
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
		  </#if>
		  <#if type ? exists && type == '5'>
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
		  </#if>
	  </tr>
	</table>
</article>
<#include "/h5v1/commons/_footer.ftl" />
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/amazeui.datetimepicker.min.js"></script>
<script>
  $(function() {
	  var first= '${first}';
	  if ( first== 'first') {
		  getMore ("","","","","");
	  }
  	var isdate = true;
    var $alert = $('#my-alert');
    $('#startDate').datepicker().on('changeDate.datepicker.amui', function(event) {
    	var startDate = $('#startDate').val();
    	var endDate = $('#endDate').val();
        if (endDate != "" && compareDate(startDate, endDate) > 0) {
          $alert.find('p').text('开始日期应小于结束日期！').end().show();
		  isdate = false;
        }
        else {
          isdate = true;
          $alert.hide();
        }
        $(this).datepicker('close');
      });

	$('#endDate').datepicker().on('changeDate.datepicker.amui', function(event) {
    	var startDate = $('#startDate').val();
    	var endDate = $('#endDate').val();
        if (startDate != "" && compareDate(startDate, endDate) > 0) {
		  $alert.find('p').text('结束日期应大于开始日期！').end().show();
		  isdate = false;
        }
        else {
          isdate = true;
          $alert.hide();
        }
        $(this).datepicker('close');
      });
      
      $(".sure_btn").click(function() {
      	if (isdate){
      		var startDate = $('#startDate').val();
        	var endDate = $('#endDate').val();
        	var areaId = $('#areaId option:selected') .val();
        	var signNo = $('#signNo option:selected') .val();
        	var year = $('#year option:selected') .val();
        	getMore (areaId,signNo,startDate,endDate,year);
        	$('.am-menu-offcanvas1').hide();
      	}
      });
      
      $(".reset_btn").click(function() {
    	   $('#startDate').val('');
	       $('#endDate').val('');
	       $('#areaId').find('option').eq(0).attr('selected', true);
	       $('#signNo').find('option').eq(0).attr('selected', true);
      });
  });
  
	function compareDate(strDate1, strDate2) {
		var date1 = new Date(strDate1.replace(/\-/g, "\/"));
	    var date2 = new Date(strDate2.replace(/\-/g, "\/"));
    	return date1 - date2;
	}
	
	function getMore (areaId,signNo,startDate,endDate,year) {
		var type = $('#type').val();
		$.ajax({
			    type: "GET",
		        url: domain + "/member/panerTotalMore.html?type="+type+"&areaId="+areaId+"&signNo="+signNo+"&startDate="+startDate+"&endDate="+endDate+"&year="+year,
		        //data:{"areaId":areaId},
		        dataType: "html",
		        success: function(data) {
        			$('.rwd-table').find('.rem_tr').remove();
        			$("#loading").hide();
		        	$('.table-tr').append(data);
		        }
		});
	}
</script>
</body>
</html>
