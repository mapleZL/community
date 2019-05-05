<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/fwc-css/houtai.css"/>
<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/My97DatePicker/WdatePicker.js'></script>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
	
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
					<a href='javascript:void(0)'>我的个人资料</a>
				</span>
			</div>
		</div>
		<!--左侧导航 -->
		<div class='container w'>
		<div>
		<#include "/front/commons/_left.ftl" />
		</div>
		<!-- 主体区域 -->
  		<div class='wrapper_main1'>
		<h3>我的个人资料</h3>

		<div class="main-current1">
		<form id='personalfileForm'>
			<p>尊敬的用户，完善您的个人资料，将有助于我们更好的为您提供服务，带有<em>*</em>的栏目为必填项。</p><br>
		<ul class="list-group ul-group1">
			  <li class="list-group-item1">
			  	<span>
			  	用户名：　　　　<i>${(member.name)!''}</i>
			  	</span>
					　　　　　
			  </li><br>
			  <li class="list-group-item1">
			  	<span><em>*</em>真实姓名：</span>
			  	<input type="text" id="realName" name="realName" value="${(member.realName)!''}" class='s-infotitle1'>
			  </li><br>
			  <li class="list-group-item1">
			  	<span>性别：</span>
			  	　　　　　<input type="radio" name="gender" value="1" style="width:13px; height:13px;" <#if member.gender?? && member.gender==1>checked="checked;"</#if> >男
			  	<input type="radio" name="gender" value="2" style="width:13px; height:13px;" <#if member.gender?? && member.gender==2>checked="checked;"</#if> >女
			  </li><br>
			  <li >
			  	<span >生日：</span>
				<input type="text" id="birthday" name="birthday"  maxlength="10"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});" value="${(member.birthday?string("yyyy-MM-dd"))!''}"  class='s-infotitle1'>
			  </li><br>
			  <li class="list-group-item1">
			  	<span>手机号：</span>
			  	<input type="text" class='s-infotitle1' name="mobile" value="${(member.mobile)!''}">
			  </li><br>
			  <li class="list-group-item1" style="width:500px;">
			  	<span>固定电话：</span>
			  	<input type="text" placeholder="区号" name="quhaoNum" value="${(member.quhaoNum)!''}" style="width:80px;height:30px;margin-left:47px;text-align: center;">
			  	<input type="text" placeholder="电话号码" name="teleNum" value="${(member.teleNum)!''}" style="width:100px;height:30px;margin:0 10px 0 10px;text-align: center;">
			  	<input type="text" placeholder="分机" name="fenjiNum" value="${(member.fenjiNum)!''}" style="width:80px;height:30px;text-align: center;">
			  </li><br>
			  <li class="list-group-item1">
			  	<span >QQ：</span>
			  	<input type="text" class='s-infotitle1' name="qq" value="${(member.qq)!''}">
			  </li><br>
			  <li class="list-group-item1">
			  	<span >微信：</span>
			  	<input type="text" class='s-infotitle1' name="wechatNum" value="${(member.wechatNum)!''}">
			  </li><br>
			  <li class="list-group-item1">
			  	<span >邮箱：</span>
			  	<input type="text" class='s-infotitle1' name="email" value="${(member.email)!''}">
			  </li><br>
			  <li class="list-group-item1">
			  	<span >身份证号码：</span>
			  	<input type="text"class='s-infotitle1' name="certificateNum" value="${(member.certificateNum)!''}">
			  </li><br>
			  <div class="fenge">分割线</div>
		</ul>
		<ul class="list-group1">
			  <li>
			  	<span>用户身份：</span> 
			  		 　　　<input type="radio" name="userCertify" value="0" style="width:13px; height:13px;" <#if member.userCertify?? && member.userCertify==0>checked="checked;"</#if> >企业
			  	<input type="radio" name="userCertify" value="1" style="width:13px; height:13px;" <#if member.userCertify?? && member.userCertify==1>checked="checked;"</#if> >个体工商户
			  	<input type="radio" name="userCertify" value="2" style="width:13px; height:13px;" <#if member.userCertify?? && member.userCertify==2>checked="checked;"</#if> >自然人
			  	
			  			

			  </li><br>
			  <!-- begin -->
			  <li>
			  	<span >所在地区：</span>
			  	<input type="text" style="margin-left: 46px;width:260px;" readonly="readonly" name="userAddress" id="userAddress" value="${(member.userAddress)!''}"/>
			  	<a href='javascript:void(0);' class='add-address ftx-05' onclick="addOrEditAddressaaa(0)">选择地址</a>
			  </li><br>
			  <li class="list-group-item1" style="height:60px;">
			  	<span >所在地区地址：</span>
			  	<input type="text" class='s-infotitle1' name="userAddinfo" id="userAddinfo" value="${(member.userAddinfo)!''}" style="width:389px; height:28px;">
				  </li><br>
			  <li>
			  	<span >店铺地区：</span>
			  	<input type="text" style="margin-left: 46px;width:260px;" readonly="readonly" name="dpAddress" id="dpAddress" value="${(member.dpAddress)!''}"/>
			  	<a href='javascript:void(0);' class='add-address ftx-05' onclick="addOrEditAddressbbb(0)">选择地址</a>
			  </li><br>
			  <li class="list-group-item1">
			  	<span >店铺具体地址：</span>
					<input type="text" class='s-infotitle1' name="dpAddinfo" id="dpAddinfo" value="${(member.dpAddinfo)!''}" style="width:389px; height:28px;">
			  </li><br>
			   <!--end  -->
			  <li class="list-group-item1"style="width:461px;">
			  	<span >网店名称：</span>
					<input type="text"class='s-infotitle1' name="wdName" style="width:300px; height:28px;" value="${(member.wdName)!''}">

			  </li><br>
			  <li class="list-group-item1">
			  	<span >网店网址：</span>
				<input type="text" class='s-infotitle1' name="wdwebAddress" style="width:389px; height:28px;" value="${(member.wdwebAddress)!''}" placeholder="http://www.dawawang.com">


				<span class="em-errMes"></span>
			  </li><br>
		</ul>
			<!-- <button class="preserve">保存</button> -->
			<hr>
			<input type='button' value='确认提交' class='preserve' style="margin-top: 0px;">
			</form>
		</div>
	</div>
	</div>
	<!-- 收货人信息 -->
		<div class='background-layer' id='Harvest'>
		</div>
		
<script type="text/javascript">
	$(function(){
		//控制左侧菜单选中
		$("#personalfile").addClass("currnet_page");
		// 身份证号码验证 
		jQuery.validator.addMethod("isIdCardNo", function (value, element) {
        return this.optional(element) || isIdCardNo(value);
    	}, "请正确输入您的身份证号码");
		//增加身份证验证
		function isIdCardNo(num) {
		    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
		    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
		    var varArray = new Array();
		    var intValue;
		    var lngProduct = 0;
		    var intCheckDigit;
		    var intStrLen = num.length;
		    var idNumber = num;
		    // initialize
		    if ((intStrLen != 15) && (intStrLen != 18)) {
		        return false;
		    }
		    // check and set value
		    for (i = 0; i < intStrLen; i++) {
		        varArray[i] = idNumber.charAt(i);
		        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
		            return false;
		        } else if (i < 17) {
		            varArray[i] = varArray[i] * factorArr[i];
		        }
		    }
		    if (intStrLen == 18) {
		        //check date
		        var date8 = idNumber.substring(6, 14);
		        var a = /^(\d{4})(\d{2})(\d{2})$/;
		        if (!a.test(date8)) { 
					alert("日期格式不正确!") 
					return false 
				} 
		        
		       // if (isDate8(date8) == false) {
		       //     return false;
		       // }
		       // calculate the sum of the products
		        for (i = 0; i < 17; i++) {
		            lngProduct = lngProduct + varArray[i];
		        }
		        // calculate the check digit
		        intCheckDigit = parityBit[lngProduct % 11];
		        // check last digit
		        if (varArray[17] != intCheckDigit) {
		            return false;
		        }
		    }
		    else {        //length is 15
		    	//check date
		        var date6 = idNumber.substring(6, 12);
		        if (isDate6(date6) == false) {
		            return false;
		        }
		    }
		    return true;
		}
		//校验
		jQuery("#personalfileForm").validate({
			errorPlacement : function(error, element) {
				var obj = $(".em-errMes").css('display', 'block');
				error.appendTo(obj);
			},
	        rules : {
	            "realName":{required:true,minlength:2},
	            "mobile":{required:false,isMobile:true},
	            "email":{required:false,isEmail:true},
	            "certificateNum":{required:false,isIdCardNo:true},
	            "wdwebAddress":{required:false,url:true}
	        },
	        messages:{
	        	"realName":{required:"真实姓名不能为空",minlength:"请输入一个长度最少是 {0} 的字符串"},
	            "mobile":{required:"请输入手机号"},
	            "email":{required:"请输入邮箱"},
	            "certificateNum":{required:"请正确输入您的身份证号码"},
	            "wdwebAddress":{url:"请输入有效的URL"}
	        }
	    }); 
		
		$(".preserve").click(function(){
			if($("#personalfileForm").valid()){
				$(".preserve").attr("disabled","disabled");
				var params = $('#personalfileForm').serialize();
				$.ajax({
					type:"POST",
					url:domain+"/member/saveinfo.html",
					dataType:"json",
					async : false,
					data : params,
					success:function(data){
						if(data.success){
							jAlert('保存成功', '提示',function(){
								window.location.href=domain+"/member/info.html"
							});
						}else{
							jAlert(data.message);
							$(".preserve").removeAttr("disabled");
						}
					},
					error:function(){
						jAlert("异常，请重试！");
						$(".preserve").removeAttr("disabled");
					}
				});
			}
			
		});
	})
	
		//新增收货地址 或编辑收货地址
function addOrEditAddressaaa(id){
	$("#Harvest").addClass("lay-display");
	$.ajax({
		type:"GET",
		url:domain+"/member/editaddressaaa.html",
		dataType:"html",
		data : {id:id},
		success:function(data){
			$("#Harvest").html(data);
		},
		error:function(){
			jAlert("异常，请重试！");
		}
	});
}
	
	//新增收货地址 或编辑收货地址
function addOrEditAddressbbb(id){
	$("#Harvest").addClass("lay-display");
	$.ajax({
		type:"GET",
		url:domain+"/member/editaddressbbb.html",
		dataType:"html",
		data : {id:id},
		success:function(data){
			$("#Harvest").html(data);
		},
		error:function(){
			jAlert("异常，请重试！");
		}
	});
}

	
	
</script>
	<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/areaSupport.js'></script>
<#include "/front/commons/_endbig.ftl" />