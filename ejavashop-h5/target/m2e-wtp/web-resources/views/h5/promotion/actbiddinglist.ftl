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
      <div class="flex-2 text-center fa-lg">阶梯竞价</div>
      <div class="flex-1 text-right" id="fa-bars"></div>
    </div>
    <#include "/h5/commons/_hidden_menu.ftl" />
  </header>
  <!-- 头部 end-->

		<div class="buybar flex">
			<div class="flex-1 <#if biddingfront==2>current</#if>"><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/bidding-end.html"><span class="fa fa-circle-o"></span><br>已结束</a></div>
			<div class="flex-1 <#if biddingfront==1>current</#if>"><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/bidding-sale.html"><span class="fa fa-circle-o-notch"></span><br>正在疯抢</a></div>
			<div class="flex-1 <#if biddingfront==3>current</#if>"><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/bidding-start.html"><span class="fa fa-clock-o"></span><br>即将开始</a></div>
		</div>
		
  <!-- S 导航 -->
  <section class="nav_area">
    <div class="nav_top_area">
      <div class="tab_nav">
        <ul class="swiper-wrapper-ul">
          <#if actBiddingTypes??>
          	<#list actBiddingTypes as actBiddingType>
          		<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/<#if biddingfront==1>bidding-sale<#elseif biddingfront==2>bidding-end<#else>bidding-start</#if>.html?type=${(actBiddingType.id)}" <#if type==actBiddingType.id>class="active"</#if> data-cid="tg2">${(actBiddingType.name)!}</a></li>
            </#list>
          </#if>
        </ul>
      </div>
      <div class="show_more">
        <span class="fa fa-angle-down fa-lg"></span>
      </div>
    </div>
    <ul class="show_more_list">
      <#if actBiddingTypes??>
      	<#list actBiddingTypes as actBiddingType>
  			<li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/<#if biddingfront==1>bidding-sale<#elseif biddingfront==2>bidding-end<#else>bidding-start</#if>.html?type=${(actBiddingType.id)}" <#if type==actBiddingType.id>class="active"</#if> data-cid="tg2">${(actBiddingType.name)!}</a></li>
  		</#list>
      </#if>
    </ul>
  </section>
  <!-- E 导航 -->

  <!-- S 轮播 -->
  <div>
      <div class="swiper-container" >
          <div class="swiper-wrapper">
          	  <#if actBiddingBanners??>
	      		  <#list actBiddingBanners as actBiddingBanner>
	              	  <div class="swiper-slide"><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/${(actBiddingBanner.linkUrl)!}"><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(actBiddingBanner.image)!}" height="200"></a></div>
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
      <ul class="i-list-ul clear">
	      <#if actBiddings??>
				<#list actBiddings as actBidding>
					<dl class="flex list-dl">
						<dt class="grabdt"><a><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(actBidding.image)!}"></a></dt>
						<dd class="padl flex-2  pos_relative">
							<div class="product_name">${(actBidding.productName)!}</div>
							<div class="product-desript">
							   <#if biddingfront==2>
							   	  <p class="clr53">最终价格</p>
						   	   <#else>
							   	  <p class="clr53">最低可优惠至</p>
						   	   </#if>
							   <p class="clr53">￥<font>${(actBidding.lowestPrice)!}<font></p>
							</div>
							<div class="flex flex-pack-justify mar_top">
								<div>预付款:<font class="clr53">￥${(actBidding.firstPrice)!}</font></div>
								<div>已抢购:<font class="clr53">${actBidding.virtualSaleNum + actBidding.saleNum}</font>人</div>
							</div>
							<a class="Grab_btn" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/bidding/${(actBidding.id)}.html">
							<#if biddingfront==1>
							马上抢
							<#else>
							查看
							</#if>
							</a>
						</dd>
					</dl>
				</#list>
			</#if>
	      
	      <div id="product_list_more"></div>
      </ul>
    </div>

	<#if actBiddings?? && actBiddings?size==pagesize>
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
		
		var urljson = "${(domainUrlUtil.EJS_URL_RESOURCES)!}/biddingJson.html?type=${(type)!}&biddingfront=${(biddingfront)!}&page=" + list_page;
		
		var biddingfront = ${(biddingfront)!0};
		var listJsonHtml = "";
		$.ajax({
            type:"get",
            url: urljson,
            dataType: "json",
            cache:false,
            success:function(data){
                if (data.success) {
                    $.each(data.rows, function(i, actBidding){
                    	listJsonHtml += "<dl class='flex list-dl'>";
                    	listJsonHtml += "<dt class='grabdt'><a><img src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + actBidding.image +"'></a></dt>";
                    	listJsonHtml += "<dd class='padl flex-2  pos_relative'>";
                    	listJsonHtml += "<div class='product_name'>" + actBidding.productName + "</div>";
                    	listJsonHtml += "<div class='product-desript'>";
                    	listJsonHtml += "<p class='clr53'>最低可优惠至</p></div>";
                    	listJsonHtml += "<p class='clr53'>￥<font>" + actBidding.lowestPrice + "</font></p></div>";
                    	listJsonHtml += "<div class='flex flex-pack-justify mar_top'>";
                    	listJsonHtml += "<div>预付款:<font class='clr53'>￥" + actBidding.firstPrice + "</font></div>";
                    	listJsonHtml += "<div>已抢购:<font class='clr53'>" + (actBidding.virtualSaleNum + actBidding.saleNum) + "</font>人</div>";
                    	listJsonHtml += "</div><a class='Grab_btn' href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/bidding-" + actBidding.id + ".html'>";
                    	if(biddingfront == 1) {
                    		listJsonHtml += "马上抢";
                    	} else {
                    		listJsonHtml += "查看";
                    	}
                    	listJsonHtml += "</a></dd></dl>";
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