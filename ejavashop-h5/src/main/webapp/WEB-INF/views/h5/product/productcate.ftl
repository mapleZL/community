<#include "/h5/commons/_head.ftl" />

<body >
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="javascript:history.back(-1);">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">商品分类</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
   
	<div class="s-container" style="height:100%;">
	    <div class="clear">
	        <!-- 左侧菜单 -->	
			<div class="menunav" style="float:left;">
				<div class="swiper-container" id="swiper-container">
				    <div class="swiper-wrapper" id="swiper-wrapper">
				    	<#if typeList??> 
							<#list typeList as cate1>
							    <div class="swiper-slide text_overflow <#if cate1_index==0>slide-active</#if>">
									<#if cate1.name?replace("、","")?length gt 4>
										${cate1.name?replace("、","")?substring(0,4)}
									<#else>
										${cate1.name}
									</#if>
								</div>
							</#list> 
						</#if>
				    </div>	    
				</div>
			</div> 
			<!-- 左侧菜单结束 -->
			
			<div class=" menu-group" id="menu-group" style="overflow:hidden;">
				<div style="height:100%; position:absolute; padding-bottom:40px; width:80%;" class="overtouch">
					<#list typeList as cate1>
						    <div class="pad10" <#if cate1_index != 0>style="display:none"</#if>>
							    	<dl class="menu-group-level pad-top2">
							    		 <dt style="display:none ;">${cate1.id }${cate1.name }</dt>
							    		 <dd class="level-3">
											<#list cate1.childList as cate2>
											<#if cate1_index == 0>
											<a href='0-1-0-0-0-0-${(cate2.id)!}-0-0-0.html'><i>${cate2.name!""}</i></a>
											<#else>
							    		 		<a href="0-1-0-0-0-0-0-0-0-0-${(cate2.id)!}_${(cate2_index)!}.html" class="text_overflow ">${(cate2.name)!'' }</a>
											</#if>
									    	</#list>
							    		 </dd>
							    	</dl>
						    </div>
					</#list> 
				</div>	
			</div>
		</div>
    </div>
	<!-- 主体结束 -->

	<!-- footer -->
	<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
    <script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/index.js"></script>
    <script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js"></script>
    <#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

 <!-- 分类菜单滑动 -->
 <script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/swiper/swiper-3.2.7.min.js"></script>
 <script>
 $(document).ready(function () {
        $("html,body").css("overflow","hidden");
 		var h = $('body').height()-40;
 		$(".menunav").css('height',h);
 		var n = parseInt(h / 50);
	    var mySwiper = new Swiper ('.swiper-container', {
	    direction: 'vertical',
	    height: h,
	    preventClicks : true,
        slidesPerView : n,
	    paginationClickable: true,
	    loop: false	     
	   });

	   $("#swiper-wrapper").on("click",".swiper-slide",function(){
          var _this=$(this);
          var index=_this.index();
          _this.addClass('slide-active').siblings().removeClass("slide-active");

          $("#menu-group .pad10").eq(index).css("display","block").siblings().css("display","none");
	   });
  }); 
 </script>
</body>
</html>