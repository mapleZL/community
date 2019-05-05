<#include "/front/commons/_headbig.ftl" />
<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/groupCate.css">
    <!-- S 主体 -->
    <div class="container" style="position:relative;">
      <!-- S 轮播 -->
      <div class="all" id='box'>
          <div class="screen">
              <ul class="screen-ul">
	              <#if actBiddingBanners??>
	        		<#list actBiddingBanners as actBiddingBanner>
	                  <li><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/${(actBiddingBanner.linkUrl)!}" target="_blank"><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(actBiddingBanner.image)!}" width="1000" height="330"/></a></li>
	                </#list>
	              </#if>
              </ul>
              <ol>
              </ol>
          </div>
      </div>
      <!-- E 轮播 -->

      <!-- S 商品阶梯竞价 -->
      <h2 class="group-purchase-tit">商品阶梯竞价（进行中）</h2>
      <div class="group-purchase">
      
      <#if actBiddings??>
      	<#list actBiddings as actBidding>
	      <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/bidding/${(actBidding.id)!}.html" target="_blank">
		      <div class="list-item">
		        <div class="item-img">
		            <img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(actBidding.image)!}" alt="">
		        </div>
		        <div class="item-info">
		          <p><#if actBidding.sellerId==1><em>商城自营</em></#if>${(actBidding.productName)!}</p>
		        </div>
		        <div class="item-purch">
		          <div class="item-purch-l <#if actBidding.stock lt 1>item-purch-over</#if>">
		            <p class="cur-price">
		              <span class="yuan">¥</span>
		              <span class="item-purch-price">${(actBidding.firstPrice)!}</span>
		              <del>¥${(actBidding.marketPrice)!}</del>
		            </p>
		          </div>
		          <div class="item-purch-r <#if actBidding.stock lt 1>item-purch-over</#if>">
		            <div>
		              <i>${actBidding.virtualSaleNum + actBidding.saleNum}</i><br>
		              <span>人已买</span>
		            </div>
		          </div>
		        </div>
		      </div>
	      </a>
      	</#list>
      </#if>
       
      </div>
      <!-- E 商品阶梯竞价 -->
      <!-- S 阶梯竞价 -->
          <div class="group-cate-flxed">
            <h2 class="group-cate-title">阶梯竞价商品分类</h2>
            <div class="group-itemnav">
            	
             <div class="dd">
                 <p class="item">
                 	<a class="item-a item-a-cur" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/bidding-sale.html">进行中</a>
                 </p>
                 <p class="item">
                 	<a class="item-a" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/bidding-start.html">未开始</a>
                 </p>
                 <p class="item">
                 	<a class="item-a" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/bidding-end.html">已结束</a>
                 </p>
              </div>
              
              <div class="div-h3">
              	<#if actBiddingTypes??>
        			<#list actBiddingTypes as actBiddingType>
		                <h3 class="itemnav-h3">
		                  <a class="item-a <#if actBiddingType.id=type>item-a-cur</#if>" href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/bidding-sale.html?type=${(actBiddingType.id)!}">${(actBiddingType.name)!""}</a>
		                </h3>
                	</#list>
                </#if>
              </div>
            </div>
          </div>
          <!-- E 阶梯竞价 -->
          
          <#if page?? && page.pageCount gt 1>
		      <div class="pagin-box">
		      	<#include "/front/commons/_pagination.ftl" />
		      </div>
	      </#if>
    </div>
    <!-- E 主体 -->

    <script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bannerSlider.js"></script>
    <script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/list.js'></script>
    <script>
      $(function(){
	        // 点击阶梯竞价商品分类菜单改变当前样式
	        $(".item .item-a").on("click",function(){
	          $(this).addClass("item-a-cur").parent("p").siblings("p").find(".item-a").removeClass("item-a-cur");
	          $(".div-h3 .item-a").removeClass("item-a-cur");
	        });
	
	        $(".div-h3 .item-a").on("click",function(){
	          $(this).addClass("item-a-cur").parent(".itemnav-h3").siblings(".itemnav-h3").find(".item-a").removeClass("item-a-cur");
	          $(".item .item-a").removeClass("item-a-cur");
	        });
	        
	       
	        $('.carousel').carousel({
			  interval: 2000
			});
			
			 // 滚动定位阶梯竞价商品
	        $(window).scroll(function(){
	          var st = parseInt($(".top-head").css("height"));
	          // 获取可视区域的宽度
	          var cliWidth = $(document).width() < $('body').width() ? $(document).width() : $('body').width();
	          // 获取定位框的宽度/2
	          var groupCateFlxedW = $(".group-cate-flxed").width()/2;
	          // 计算需要定位的位置
	          var fixedPos = (cliWidth-1000)/2-groupCateFlxedW;
	          // console.log(fixedPos);
	          if($(window).scrollTop()>st){
	            $(".group-cate-flxed").css({"position":"fixed","right":fixedPos+"px"});
	          }else {
	            $(".group-cate-flxed").css({"position":"absolute","right":"0"});
	          }
	       });
	      
	        // 阶梯竞价商品边框改变颜色
	        $(".list-item").on("mouseenter",function(){
	          $(this).addClass("borderColor");
	        });
	        $(".list-item").on("mouseleave",function(){
	          $(this).removeClass("borderColor");
	        });
        
	        // 给轮播图ul设定宽度
	        var sWidth = $(".screen li").width();
	        var liLength = $(".screen li").length+1;
	        var screenUlWidth = sWidth*liLength;
	        $(".screen-ul").css("width",screenUlWidth+"px");
	        // 加载轮播
	        loadbanner("box");
	      
	 });     
    </script>

    
<#include "/front/commons/_endbig.ftl" />
 