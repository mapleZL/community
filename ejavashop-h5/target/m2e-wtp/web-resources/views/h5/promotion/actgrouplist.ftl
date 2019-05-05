<#include "/h5/commons/_head.ftl" />
<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/groupon.css">

<body>
  <!-- 头部 -->
  <header id="header">
    <div class="flex flex-align-center head-bar bg-color">
      <div class="flex-1 text-left">
        <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html">
  	 		<span class="fa fa-angle-left"></span>
  	 	</a>
      </div>
      <div class="flex-2 text-center fa-lg">团购</div>
      <div class="flex-1 text-right" id="fa-bars"></div>
    </div>
    <#include "/h5/commons/_hidden_menu.ftl" />
  </header>
  <!-- 头部 end-->

  <!-- S 导航 -->
  <section class="nav_area">
    <div class="nav_top_area">
      <div class="tab_nav">
        <ul class="swiper-wrapper-ul">
          <li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/tuan.html" <#if type==0>class="active"</#if> data-cid="tg1">团购首页</a></li>
          <#if actGroupTypes??>
          	<#list actGroupTypes as actGroupType>
          		<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/tuan.html?type=${(actGroupType.id)}" <#if type==actGroupType.id>class="active"</#if> data-cid="tg2">${(actGroupType.name)!}</a></li>
            </#list>
          </#if>
        </ul>
      </div>
      <div class="show_more">
        <span class="fa fa-angle-down fa-lg"></span>
      </div>
    </div>
    <ul class="show_more_list">
      <li>
        <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/tuan.html" <#if type==0>class="active"</#if> data-cid="tg1">团购首页</a>
      </li>
      <#if actGroupTypes??>
      	<#list actGroupTypes as actGroupType>
  			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/tuan.html?type=${(actGroupType.id)}" <#if type==actGroupType.id>class="active"</#if> data-cid="tg2">${(actGroupType.name)!}</a></li>
  		</#list>
      </#if>
    </ul>
  </section>
  <!-- E 导航 -->

  <!-- S 轮播 -->
  <div>
      <div class="swiper-container" >
          <div class="swiper-wrapper">
          	  <#if actGroupBanners??>
	      		  <#list actGroupBanners as actGroupBanner>
	              	  <div class="swiper-slide"><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/${(actGroupBanner.linkUrl)!}"><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(actGroupBanner.image)!}" height="200"></a></div>
	              </#list>
      		  </#if>
          </div>
          <div class="swiper-pagination"></div>
      </div>
  </div>
  <!-- E 轮播 -->

  <!-- S 商品 -->
  <div  style="padding:0 5px;" class="group_section">
    <div class="i-list-box mar-bt">
      <h2>精品团/${(typeName)!''}</h2>
      <ul class="i-list-ul clear">
	      <#if actGroups??>
		     <#list actGroups as actGroup>
		        <li>
		          <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/tuan/${(actGroup.id)!}.html" class="block">
		            <div class="i-list-img">
		              <img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(actGroup.image)!}" width="189" height="189"></div>
		            <div class="product_name">${(actGroup.productName)!}</div>
		          </a>
		          <div class="i-list-price">￥${(actGroup.price)!}</div>
		          <div class="have_buy">${actGroup.virtualSaleNum + actGroup.saleNum}人已经参团</div>
		        </li>
	         </#list>
	      </#if>
	      
	      <div id="product_list_more"></div>
      </ul>
    </div>

	<#if actGroups?? && actGroups?size==pagesize>
		<div id="product_list_more_json" class="text-center font14 pad-top2">查看更多<i class="fa fa-angle-double-down"></i></div>
		<div id="product_list_more_json_no" class="text-center font14 pad-top2" style="display:none;">已展示全部商品</div>
	<#else>
		<div id="product_list_more_json_no" class="text-center font14 pad-top2">已展示全部商品</div>
	</#if>
	<input type="hidden"  name="list_page" id="list_page" value="1" />
  </div>
  <!-- E 商品 -->


  <script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
  <script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/groupon.js"></script>
  <script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/index.js"></script>

<script type="text/javascript">
$(function(){
	$("#product_list_more_json").click(function(){
		var list_page = $("#list_page").val();
		list_page++;
		$("#list_page").val(list_page);
		
		var urljson = "${(domainUrlUtil.EJS_URL_RESOURCES)!}/tuanJson.html?type=${type}&page=" + list_page;
		
		var listJsonHtml = "";
		$.ajax({
            type:"get",
            url: urljson,
            dataType: "json",
            cache:false,
            success:function(data){
                if (data.success) {
                    $.each(data.rows, function(i, actGroup){
                    	listJsonHtml += "<li><a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/tuan/" + actGroup.id + ".html' class='block'>";
                    	listJsonHtml += "<div class='i-list-img'>";
                    	listJsonHtml += "<img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + actGroup.image + "' width='189' height='189'></div>";
	            		listJsonHtml += "<div class='product_name'>" + actGroup.productName + "</div></a>";
	              	    listJsonHtml += "<div class='i-list-price'>￥" + actGroup.price + "</div>";
	            		listJsonHtml += "<div class='have_buy'>" + (actGroup.virtualSaleNum + actGroup.saleNum) + "人已经参团</div></li>";
                    })
                    $("#product_list_more").append(listJsonHtml);
                    if ((data.total) == ${(pagesize)!}) {
                        $("#product_list_more_json").show();
                        $("#product_list_more_json_no").hide();
                    } else {
                        $("#product_list_more_json").hide();
                        $("#product_list_more_json_no").show();
                    }
                }
            }
        });
        
	});
	
})
 </script>
  <!-- 轮播 -->
  <script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/swiper/swiper-3.2.7.min.js"></script>
  <script>
      $(document).ready(function () {
         var mySwiper = new Swiper ('.swiper-container', {
           autoplay: 3000,
           loop: true,
           // 如果需要分页器
           pagination: '.swiper-pagination'
        })  ;

         // 滑动导航栏
         initnav();

        // 显示影藏的导航栏
        $(".nav_top_area .show_more").on("click",function(){
          $(".show_more_list").toggleClass("tog_more_list");
        });
        
        // 点击导航栏里面的li
        $(".swiper-wrapper-ul li").on("click",function(){
          $(this).find("a").addClass("active").parent("li").siblings().find("a").removeClass("active");
          $(".show_more_list li").eq($(this).index()).find("a").addClass("active").parent("li").siblings().find("a").removeClass("active");
        });

        // 点击隐藏导航里面的li
        var liLength = $(".show_more_list li").length;
        var liwidth = $(".swiper-wrapper-ul li").outerWidth();

        $(".show_more_list li").on("click",function(){
          $(this).find("a").addClass("active").parent("li").siblings().find("a").removeClass("active");
          var liIndex = $(this).index();
          $(".swiper-wrapper-ul li").eq(liIndex).find("a").addClass("active").parent("li").siblings().find("a").removeClass("active");
          if(liIndex==0){
            $(".swiper-wrapper-ul").css("transform","translateX(-"+liIndex*liwidth+"px)");
          }else if(liIndex==1){
            var twoLi=liIndex*liwidth-liwidth;
            currX=-twoLi;   //改变滑动的起始位置
            $(".swiper-wrapper-ul").css("transform","translateX(-"+twoLi+"px)");
          }else if(liIndex==liLength-1){
            var lastLi = $('.tab_nav > ul').width() - $('.tab_nav').width(); 
            currX=-lastLi;
            $(".swiper-wrapper-ul").css("transform","translateX(-"+lastLi+"px)");
          }else {
            var zhl=liIndex*liwidth-liwidth-liwidth;
            currX=-zhl;
            $(".swiper-wrapper-ul").css("transform","translateX(-"+zhl+"px)");
          }
          $(this).parent().toggleClass("tog_more_list");
        });
      });
  </script>

</body>
</html>