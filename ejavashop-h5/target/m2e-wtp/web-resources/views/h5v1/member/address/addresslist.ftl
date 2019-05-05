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
</style>
<body style="background:#eee">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2 header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">收货地址</h1>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
		<#if addressList??>
			<#list addressList as address>
			
			<div class="mt10 ico">
		     	<div class="tp2 public addr">
		        	<p><em>${address.memberName}</em><em>${address.mobile}</em></p>
		            <p>${address.addAll}&nbsp;${address.addressInfo}</p>
		        </div>
		        <div class="overflow public">
		            	<!-- 如果是从订单页跳转过来显示选择按钮 -->
	            	<#if isFromOrder?? && isFromOrder == "1" >
			       	<#else>
			       	<div class="ect-radio1 fl">
		            <input name="msg_type" type="radio" id="msg-type${(address.id)!0}" value="${(address.id)!0}"  <#if (address.state)=1>checked </#if>>
		            <label for="msg-type${(address.id)!0}"><i></i>设为默认地址</label>
		            </div>
			        </#if>
		            <div class="overflow fr delete_box">
		            	<!-- 如果是从订单页跳转过来显示选择按钮 -->
	            		<#if isFromOrder?? && isFromOrder == "1" >
			            	<a href="javascript:void(0);" onclick="choseAddress(${(address.id)!0})">选择</a>
			            	<a href="javascript:void(0);" class="address-edit" data-address-id ="${(address.id)!0}" >编辑</a>
			            <#else>
			            	<a href="javascript:void(0);" class="address-edit" data-address-id ="${(address.id)!0}" >编辑</a>
			            <!-- 如果是从订单页跳转过来不显示删除按钮 -->
			              <a class="deleteAdd" data-add-id="${(address.id)!0}"  >删除</a>
			            </#if>
		            </div>
		        </div>
		     </div>
        </#list>
     </#if>

     <div class="divcon mt10"></div>
     <div class="footer">
     	<a class="stop1" href="javascript:void(0);">添加新地址</a>
     </div>
</article>
<input type="hidden" id="isFromOrder" value="${(isFromOrder)!0}">
<input type="hidden" id="orderType" value="${(orderType)!''}">
<input type="hidden" id="actInfo" value="${(actInfo)!''}">
<input type="hidden" id="message" value="${(message)!''}">
<input type="hidden" id="deleteId">

<!--内容end--> 
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/addressList.js"></script>
<#include "/h5v1/commons/_statistic.ftl" /> 
</body>
</html>
