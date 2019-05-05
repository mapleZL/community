<#include "/h5v1/commons/_head.ftl" />
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
.ect-radio1{ margin-top:10px}
.ect-radio1 input[type=radio],.ect-radio1 input[type=checkbox]{display: none;}
input[type=radio]{margin:4px 0 0;margin-top: 1px\9;line-height: normal;}
input[type=radio]{-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;padding: 0;}
input[type="radio"]{box-sizing: border-box;padding: 0;}
.ect-radio1 input:checked+label i, .ect-checkbox input:checked+label i{color:rgb(255, 0, 0);background: url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_add_per.png) center center  no-repeat;background-size:100%;border:0;}
.ect-radio1 label i, .ect-checkbox label i{width:1.2em;height:1.2em;display: block;float:left;margin-top: 0.1em;background:url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_add_nor.png) center center no-repeat; background-size:100%; margin-right:10px}
.am-intro-bd{padding:10px 0}
input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{color:#888;}
input:-moz-placeholder,textarea:-moz-placeholder{color:#888}
</style>
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>
  <h1 class="am-header-title">收货地址</h1>
</header>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
	<form id="addresform"  style="margin-top :50px;">
	<ul class="wallet mt10 ico">
        <li><a><input type="text" placeholder="收货人姓名" id = "memberName" name="memberName" class="text4" value="${(address.memberName)!''}"></a></li>
        <li><a><input type="number" placeholder="手机号码" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id = "mobile" name="mobile" class="text4" value="${(address.mobile)!''}"></a></li>
		<li>
            <a class="wallet_a">
                <em class="em0 fl">省、市、区</em>
                <div class="content-block fr em0" style="width:70%; text-align:right">
                    <input id="demo1" type="text" readonly value="${(address.addAll)!''}" class="text4"/>
                    <input id="value1" type="hidden" value="${(address.provinceId)!''},${(address.cityId)!''},${(address.areaId)!''}"/> 
                 </div>
             </a>
         </li>
        <li><a><input type="number" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="邮编" class="text4" id = "zipCode" name="zipCode" value="${(address.zipCode)!''}"></a></li>
        <li><textarea class="textarea public" placeholder="街道"  id= "addressInfo" name="addressInfo" >${(address.addressInfo)!''}</textarea></li>
    </ul>
    <#--
    <div class="ect-radio1 public">
        <input name="msg_type" type="checkbox" id="msg-type2" value="1">
        <label for="msg-type2"><i></i>设为默认地址</label>
    </div>
    -->
    <label id="errLabel" style="color:red"></label>
    <input type="hidden" id="id" name="id" value="${(address.id)!''}">
	<input type="hidden" id="addAll" name="addAll" value="">
	<input type="hidden" id="provinceId" name="provinceId" value="">
	<input type="hidden" id="cityId" name="cityId" value="">
	<input type="hidden" id="areaId" name="areaId" value="">
	 <!-- 1 默认地址 2 非默认 -->
	<input type="hidden" id="state" name="state" value="${(address.state)!''}">
	<input type="hidden" id="a" name="phone" value="${(address.phone)!''}">
	<input type="hidden" id="email" name="email" value="${(address.email)!''}">
	<input type="hidden" id="isFromOrder" value="${(isFromOrder)!0}">
	<input type="hidden" id="orderType" value="${(orderType)!''}">
	<input type="hidden" id="actInfo" value="${(actInfo)!''}">
	<input type="hidden" id="message" value="${(message)!''}">
    <a href="javascript:void(0);" class="stop1 mt10">
    				<#if isFromOrder?? && isFromOrder == "1" >
         				保存地址并使用
         			<#else>
         				保存地址
         			</#if></a>
     </form>
</article>
<!--内容end-->   
<#include "/h5v1/commons/_footer.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js1/LArea.css"><!--三级联动-->
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js1/LAreaData1.js"></script><!--三级联动-->
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js1/LArea.js"></script><!--三级联动-->
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/address.js"></script>
<#include "/h5v1/commons/_statistic.ftl" /> 
</body>
</html>
