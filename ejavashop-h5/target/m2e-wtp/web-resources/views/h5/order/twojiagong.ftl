<#include "/h5/commons/_head.ftl" />
<body class="bgff">
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/member/order.html">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">下单成功</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
   
	<div class="errorbox">
	     <div class="arrow-fl"></div>
	     <#if paymentStatus==3>
         <h3 class='ftc-02 text-center' style="color:#000"><i class="fa fa-smile-o"></i>&nbsp;亲，当前订单经过修改后，需要财务进行审核，核完后会有客服联系您。谢谢配合。</h3>
         <#else>
         <h3 class='ftc-02 text-center' style="color:#000"><i class="fa fa-smile-o"></i>&nbsp;亲，当前订单是二次加工订单，后续会有客服联系您进行二次加工相关事宜讨论。谢谢配合。</h3>
         </#if>
         <h3 class='ftc-02 text-center' style="color:#000"><i class="fa fa-smile-o"></i>&nbsp;感谢您，订单提交成功！</h3>
         <div class="padt_b10 borbt text-center" style="color:#000">请到&nbsp;<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/order.html' class="btn s-btn">订单中心</a>&nbsp;查看详情。</div>
    </div>
	<!-- 主体结束 -->

	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />
 <script type="text/javascript">
$(function(){
	
});

</script>

</body>
</html>