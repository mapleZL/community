<#include "/h5/commons/_head.ftl" />
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/groupon.css"></script>
<body class="bgf2">
  <!-- 头部 -->
  <header id="header">
    <div class="flex flex-align-center head-bar bg-col">
      <div class="flex-1 text-left">
        <a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/tuan.html"><span class="fa fa-angle-left col-f"></span></a>
      </div>
      <div class="flex-2 text-center col-f">团购详情</div>
      <div class="flex-1 text-right" id="fa-bars">
        <span class="fa fa-bars col-f"></span>
      </div>
    </div>
    <#include "/h5/commons/_hidden_menu.ftl" />
  </header>
  <!-- 头部 end-->

  <div class="s-container container-padtom" id="container">
    <!--图片 -->
    <div class="detail2">
      <img src="${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}${(actGroup.image)!''}"></div>
    <section class="goods_price">
      <div class="group_price">
        <span>团购价：</span>
        <span class="price">¥${(actGroup.price)?string('0.00')!'999999'}</span> <del>市场价:¥${(actGroup.marketPrice)?string('0.00')!'99999999'}</del>
      </div>
    </section>
    <section class="num_time">
      <div class="buy_num">
        <span>${(actGroup.virtualSaleNum + actGroup.saleNum)!0}</span>
        人已参团
      </div>
      <#if stageType?? && stageType == 1 >
		<!-- 即将开始 -->
		<div class="remaining_time">
	        即将开始：
	        <span></span>
      	</div>
	  <#elseif stageType?? && stageType == 2 >
		<!-- 正在进行 -->
		<div class="remaining_time">
	        距离结束：
	        <span></span>
	        
      	</div>
	  <#else>
		<!-- 已经结束 -->
		<div>团购已结束</div>
	  </#if>
    </section>
    <section class="goods_title">
      <a>
        <h2 style="font-size:16px;">${(actGroup.productName)!''}</h2>
      </a>
      <a style="color:red;font-size:14px;">
        <h2>${(actGroup.name)!''}</h2>
      </a>
    </section>
    <div class="bor-btom mar-bt mar-bt-padding">
		<#if norms?? && norms?size &gt; 0>
			<div class="flex pad-bt">
		    	<div class="pad-r">已选：</div>
		    	<div class="flex-2" id="normAttr0"></div>
		    	<div class="flex-2" id="normAttr1"></div>
		    	<div class="flex-2" id="amount"></div>
		    </div>
			<#list norms as norm>
				<div class="flex pad-bt">
					<div class="pad-r">${norm.name}：</div>
					<div class="flex-2 sel-btn" id="normsDiv${norm_index}">
						<#list norm.attrList as normattr>
							<a class="btn btn-default" id="${(normattr.id)!0}" onclick="chooseNorm(this, ${norm_index}, ${(normattr.id)!0}, '${(normattr.name)!}')">${(normattr.name)!""}</a>
						</#list>
					</div>
				</div>
			</#list>
		</#if>
		<!-- 隐藏域 -->
		<!-- 规格值ID  -->
		<input  type="hidden" id="specId0" name="specId0" >
		<input  type="hidden" id="specId1" name="specId1" >
		<!-- 产品ID -->
		<input  type="hidden" id="productId" name="productId" value="${(product.id)!''}">
		<input  type="hidden" id="productGoodsId" name="productGoodsId" value="${(goods.id)!''}">
		<input  type="hidden" id="goodsNormAttrId" name="goodsNormAttrId" value="${(goods.normAttrId)!''}">

		<div class="flex pad-bt pad-bt-padding">
			<div class="pad-r">剩余库存：${(actGroup.stock)!0}</div>
		</div>
	</div>
    <section class="attribute_buy_num">
      <span class="attribute_buy_num_tit">购买数量：</span>
      <div class="attribute_buy_num_val">
        <span class="un_minus_icon" id="minus_num"></span>
	        <input type="text" value="1" id="buyNum" autocomplete="off" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" onblur="modify();">
	        <span class="add_icon" id="add_num"></span>
	        <div class="limit_num">
	          单次限购
	          <span>${(actGroup.purchase)!1}</span>
	          件
	        </div>
      </div>
    </section>
    
    <!-- 商品详情 -->
    <div class="mar_top pad10 bgff bort shop-info">
      <h3>商品介绍</h3>
      <#noescape> ${(actGroup.descinfo)!''}</#noescape>
    </div>
    
    <!-- 底部固定菜单 -->
    <div class="fixed-nav">
	    <div class="flex">
	        <div class="flex-1 yellow-cart" ><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/${(actGroup.productId)!}.html" class="block">对比原商品</a></div>
	        
	        <#if stageType?? && stageType == 1 >
				<!-- 即将开始 -->
				<div class="flex-1 gray-cart">敬请期待</div>
			  <#elseif stageType?? && stageType == 2 >
				<!-- 正在进行 -->
				<div class="flex-1 red-cart" id="buyNow">立即参团</div>
			  <#else>
				<!-- 已经结束 -->
				<div class="flex-1 gray-cart">团购结束</div>
			</#if>
	      </div>
	    </div>
    </div>
   <!-- 主体结束 -->
    

  </div>
  <!-- 主体结束 -->
<#include "/h5/commons/_statistic.ftl" />
  <!-- footer -->
<#include "/h5/commons/_footer.ftl" />

<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-2.1.1.min.js"></script>
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/index.js"></script>

<script>
	// 倒計時
    var intDiff = parseInt(${countTime!0});//倒计时总秒数量
    function timer(intDiff){
        window.setInterval(function(){
        var day=0,
            hour=0,
            minute=0,
            second=0;//时间默认值       
        if(intDiff > 0){
            day = Math.floor(intDiff / (60 * 60 * 24));
            hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
            minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
            second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
        }
        /* if (minute <= 9) minute = '0' + minute;
        if (second <= 9) second = '0' + second; */
        if(day==0){
          /* $(".remaining_time").html("已结束"); */
          $(".remaining_time>span").html("<span>"+hour+"时</span><span>"+minute+"分</span><span>"+second+"秒</span>")
        }else{
          $(".remaining_time>span").html(day+"天");
        }
        // $('.day_show').html(day+"天");
        // $('.hour_show').html('<s id="h"></s>'+hour+'时');
        // $('.minute_show').html('<s></s>'+minute+'分');
        // $('.second_show').html('<s></s>'+second+'秒');
        // intDiff--;
        }, 1000);
    }
    $(function(){
    	$('#fa-bars').on("click",function(){
	      $("#nav").toggleClass('addnav');
		});
	
    	// 倒计时
        timer(intDiff);
    	
     	// 默认将规格选中
		var norms = $("#goodsNormAttrId").val();
		if(norms != null && norms != ""){
			$("#amount").html($("#number").val());
			var strs= new Array(); //定义数组 
			strs=norms.split(","); //字符分割 
			for(i=0;i<strs.length;i++){
				$("#normsDiv"+i).find("a").each(function(){
					var attrid = $(this).attr("id");
					if(attrid==strs[i]){
						$("#normAttr"+i).html($(this).html());
						$("#specId"+i).val(attrid);
						$(this).addClass("active").siblings().removeClass("active");
						return;
					}
				});
			}
		}
		
		$("#buyNow").click(function(){
			
			//未登录不能立即购买
			if (!isUserLogin()) {
				// 未登录跳转到登陆页面
				var toUrl = domain + "/tuan/${(actGroup.id)!0}.html";
				window.location.href = domain+"/login.html?toUrl="+ encodeURIComponent(toUrl);
				return;
			}
			
			var stock = ${(actGroup.stock)!0};
			if (parseInt(stock) < 1) {
				$.dialog('alert','提示', "已经被抢光了，下次要赶早哦！",1000);
				return false;
			}
			
			var buyNum = $("#buyNum").val();
			var limitNum = ${(actGroup.purchase)!0};
			var stock = ${(actGroup.stock)!0};
			if (parseInt(buyNum) < 1) {
				$.dialog('alert','提示', "请输入小于限购数量及库存量的正整数。",1000);
				return false;
			} else if (parseInt(buyNum) > parseInt(limitNum) || parseInt(buyNum) > parseInt(stock)) {
				$.dialog('alert','提示', "请输入小于限购数量及库存量的正整数。",1000);
				return false;
			}
			
			var goodsId = $("#productGoodsId").val();
			if (goodsId == null || goodsId == "" || goodsId == 0) {
				$.dialog('alert','提示', "请选择购买的商品。",1000);
				return false;
			}
			window.location.href=domain+"/order/tuan-" + ${(product.id)!''}+ "-" + goodsId + "-" + ${(seller.id)!''} + "-" + ${(actGroup.id)!''} + "-" + buyNum + ".html";
		});
		
		// 购买数量减少
		$("#minus_num").click(function() {
			var buyNum = $("#buyNum").val();
			if (parseInt(buyNum) <= 1) {
				return;
			} else {
				buyNum = parseInt(buyNum) - 1;
				$("#buyNum").val(buyNum);
				$("#amount").html(buyNum);
			}
		});
		
		// 购买数量增加
		$("#add_num").click(function() {
			var limitNum = ${(actGroup.purchase)!0};
			var stock = ${(actGroup.stock)!0};
			var buyNum = $("#buyNum").val();
			if (parseInt(buyNum) >= parseInt(limitNum) || parseInt(buyNum) >= parseInt(stock)) {
				return;
			} else {
				buyNum = parseInt(buyNum) + 1;
				$("#buyNum").val(buyNum);
				$("#amount").html(buyNum);
			}
		});
    });
    
	// 数量输入框失去焦点
	function modify() {
		var buyNum = $("#buyNum").val();
		var limitNum = ${(actGroup.purchase)!0};
		var stock = ${(actGroup.stock)!0};
		if (buyNum == null || parseInt(buyNum) < 1) {
			buyNum = 1;
			$("#buyNum").val(buyNum);
		} else if (parseInt(buyNum) > parseInt(limitNum) || parseInt(buyNum) > parseInt(stock)) {
			if (parseInt(limitNum) < parseInt(stock)) {
				buyNum = parseInt(limitNum);
			} else {
				buyNum = parseInt(stock);
			}
			$("#buyNum").val(buyNum);
		}
		$("#amount").html(buyNum);
	}

    function chooseNorm(obj, normIndex, normAttrId, normAttrName) {
		$("#normAttr"+normIndex).html(normAttrName);
		$("#specId"+normIndex).val(normAttrId);
		$(obj).addClass("active").siblings().removeClass("active");
		
		var normAttrId0 = $("#specId0").val();
		var normAttrId1 = $("#specId1").val();
		var normAttrIds = "";
		if (normAttrId0 != null && normAttrId0 != "") {
			normAttrIds = normAttrId0;
		}
		if (normAttrId1 != null && normAttrId1 != "") {
			normAttrIds = normAttrIds + "," + normAttrId1;
		}
		$.ajax({
			type : "POST",
			url :  domain+"/getGoodsInfo.html",
			data : {productId:$("#productId").val(), normAttrId:normAttrIds},
			dataType : "json",
			success : function(data) {
				var productGood = data.data;
				if(productGood.id!=null){
					//货品ID
					$("#productGoodsId").val(productGood.id);
					$("#goodsNormAttrId").val(productGood.normAttrId);
				}else{
					//无货品信息 则不能添加购物车和购买
					$.dialog('alert','提示','货品信息为空，请与管理员联系',2000);
					$("#buyNow").attr("disabled","disabled");
					$("#addToCart").attr("disabled","disabled");
				}
			},
			error : function() {
				$.dialog('alert','提示','数据加载失败！',2000);
			}
		});
	}
</script>
</body>
</html>