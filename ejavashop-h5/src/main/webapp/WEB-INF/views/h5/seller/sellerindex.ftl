<#include "/h5/commons/_head.ftl" />
<body class="bgf2">
    <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="javascript:history.back(-1);">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">${(seller.sellerName)!""}</div>
   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->

	<!-- lunbo -->

	<div style="padding:0 5px 10px 5px;">
	    
	    <#if banners?? && banners?size &gt; 0 >
		<div class="swiper-container" >
		    <div class="swiper-wrapper">
		    	<#list banners as banner>
		    	<div class="swiper-slide">
		    		<a href="${(banner.linkUrl)!''}" class="block">
		    			<img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${(banner.image)!''}" height="150">
		    		</a>
		    	</div>
		        </#list>
		    </div>
		    <div class="swiper-pagination"></div>
		</div>
		</#if>
		<div>
	    	<h2 class="flex flex-pack-justify pad-top">
	    		<div style="padding-top:5px;"><i class="fa fa-institution"></i>${(seller.sellerName)!""}</div>
	    		<#if collected?? && collected == "true" >
	    			<div><a href="javascript:;" class="btn btn-default s-btn" role="button" id="collectShop" disabled="disabled">已收藏</a></div>
	    		<#else>
	    			<div><a href="javascript:;" class="btn btn-default s-btn" role="button" id="collectShop" onclick="collectShop('${(seller.id)!''}')">收藏店铺</a></div>
	    		</#if>

	    	</h2>
	    	<div class="flex flex-pack-justify s-score">
	    		<div>商品描述：<font class="clr53">${(seller.scoreDescription)!'0'}</font></div>
	    		<div>服务态度：<font class="clr53">${(seller.scoreService)!'0'}</font></div>
	    	</div>
	    	<div class="flex flex-pack-justify s-score">
	    		<div>发货速度：<font class="clr53">${(seller.scoreDeliverGoods)!'0'}</font></div>
	    		<div>关注：<font class="clr53">${(seller.collectionNumber)!'0'}</font></div>
	    	</div>
	    </div>
	</div>

	
    
    <!-- 列表 -->
	<div  style="padding:0 5px;" >
		<#if floors?? && floors?size &gt; 0 >
		<#list floors as floor>
		<div class="i-list-box mar-bt">
			<h2>${(floor.name)}</h2>
			<ul class="i-list-ul clear">
				<#if floor.datas?? && floor.datas?size &gt; 0 >
				<#list floor.datas as data >
					<#if data_index &lt; 4 >
					<!-- 如果是商品 -->
					<#if data.dataType?? && data.dataType == 1 >
						<li>
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/product/${(data.product.id)!0}.html" class="block">
								<div class="i-list-img"><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${(data.product.masterImg)!''}" width="189" height="189"></div>
								<div class="product_name">${(data.product.name1)!""}</div>
							</a>
							<div class="i-list-price">￥${(data.product.malMobilePrice)?string("0.00")!"0.00"}</div>
						</li>
					<#elseif data.dataType?? && data.dataType == 2>
						<li>
							<a href="${(data.linkUrl)!''}" class="block">
								<div class="i-list-img"><img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!''}${(data.image)!''}" width="189" height="189"></div>
								<div class="product_name">${(data.title)!""}</div>
							</a>
						</li>
					</#if>
					</#if>
				</#list>
				</#if>
			</ul>
		</div>
		</#list>
		</#if>

		<!-- <div class="text-center font14">点击继续加载 <i class="fa fa-angle-double-down"></i></div> -->
	</div>


	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/swiper/swiper-3.2.7.min.js"></script>
<script>
	$(document).ready(function () {
		var mySwiper = new Swiper ('.swiper-container', {
		  autoplay: 4000,
		  loop: true,
		  // 如果需要分页器
		  pagination: '.swiper-pagination',
		})  
	});

	// 关注店铺
	function collectShop(id){
		// 未登录不能关注店铺
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			var toUrl = domain + "/store/${(seller.id)!0}.html";
			window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
			return;
		}
		$.ajax({
			type:'GET',
			dataType:'json',
			async:false,
			data:{sellerId:id},
			url:domain+'/member/docollectshop.html',
			success:function(data){
				if(data.success){
					// alert("收藏成功");
					$.dialog('alert','提示','收藏成功',2000);
					$("#collectShop").html("&nbsp;已收藏&nbsp;");
					$("#collectShop").attr("disabled","disabled");
				}else{
					// alert(data.message);
					$.dialog('alert','提示',data.message,2000);
				}
			}
		});
	}
</script>
</body>
</html>