<#include "/h5/commons/_head.ftl" />
<body>
   <!-- 头部 -->
   <header id="header">
   	  <div class="flex flex-align-center head-bar">
   	  	 <div class="flex-1 text-left">
   	  	 	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html">
   	  	 		<span class="fa fa-angle-left"></span>
   	  	 	</a>
   	  	 </div>
   	  	 <div class="flex-2 text-center">${(productCate.name)!''}</div>
   	  	 
   	  	 <div class="flex-1 text-right">
   	  	 	<#--<i onclick="changeLayout(this)" id="imgOrlistViewid" class="fa fa-th-large"></i> -->
   	  	 </div>
   	  	
   	  </div>
   	  <#include "/h5/commons/_hidden_menu.ftl" />
   </header>
   <!-- 头部 end-->
   
	<div class="containe" id="container">
		<nav>
		  <div class="flex flex-align-center nav-2-bar" id="nav-2-bar">
			<div class="flex-1"><a href="javascript:void(0)" onclick="filterSort(0)" <#if sort?? && sort == 0>class="btn-sort"</#if>>默认</a></div>
			<div class="flex-1">
				<#if sort??>
					<#if sort == 0 || sort == 1 || sort == 2>
						<a href="javascript:void(0)" onclick="filterSort(3)">价格</a>
					<#else>
						<#if sort == 3>
							<a href="javascript:void(0)" onclick="filterSort(4)" class="btn-sort">价格<span class="fa fa-long-arrow-up"></span></a>
						</#if>
						<#if sort == 4>
							<a href="javascript:void(0)" onclick="filterSort(3)" class="btn-sort">价格<span class="fa fa-long-arrow-down"></span></a>
						</#if>
					</#if>
				</#if>
			</div>
			<#-- 
			<div class="flex-1"><a href="javascript:void(0)" onclick="filterSort(1)" <#if sort?? && sort == 1>class="btn-sort"</#if>>销量</a></div>
			-->
			<div style="display: none;" class="flex-1"><a href="javascript:void(0)" onclick="filterSort(2)" <#if sort?? && sort == 2>class="btn-sort"</#if>>评论</a></div>
			<div class="flex-1" id="list-filter">筛选&nbsp;<span class="fa fa-caret-right"></span></div>
		  </div>
		</nav>

		<div class="listbox" style="margin-bottom:50px;border: 0px solid red;">
			<div id="product_list_more">
			
			</div>
			
			<div id="product_list_more_json" class="text-center font14 pad-top2">查看更多<i class="fa fa-angle-double-down"></i></div>
			<div id="product_list_more_json_wait" class="text-center font14 pad-top2" style="display:none;">请稍等，正在努力加载中...</div>
			<div id="product_list_more_json_no" class="text-center font14 pad-top2" style="display:none;">已展示全部商品</div>
			<input type="hidden"  name="list_page" id="list_page" value="0" />
		</div>
		<div class="push_msk" id="push_msk"></div>
		<br>
	</div>
	<!-- 主体结束 -->

	<!-- footer -->
    
	<!-- 筛选层 -->
	<div class="slidebar" id="slidebar">
   	   <div class="s-container overtouch" style="height:100%;">
			<div class="flex flex-align-center head-bar" style="position: relative;">
		   	  	 <div class="flex-1 text-left btn btn-default btn-xs" id="sliderarrow" style="color: black;">关闭</div>
		   	  	 <div class="flex-1 text-left"><span class="fa fa-angle-left"></span></div>
		   	  	 <div class="flex-1 text-right btn btn-default btn-xs"><a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/cate/${(productCate.id)!''}.html">清除</a></div>
	   	   </div>
   	   	   <#--
   	   	   <div class="l-chioce">
	   	    	<#if store?? && store==0>
				    <a class="btn btn-default" href="javascript:void(0)" onclick="filterStore(1)"><i class="fa fa fa-check"></i>仅显示有货</a>
				<#else>
					<a class="btn btn-default current" href="javascript:void(0)" onclick="filterStore(0)"><i class="fa fa fa-check"></i>仅显示有货</a>
				</#if>
			
				<#if doSelf?? && doSelf==0>
				    <a class="btn btn-default" href="javascript:void(0)" onclick="filterDoSelf(1)"><i class="fa fa fa-check"></i>平台自营</a>
				<#else>
					<a class="btn btn-default current" href="javascript:void(0)" onclick="filterDoSelf(0)"><i class="fa fa fa-check"></i>平台自营</a>
				</#if>
   	   	    </div>
   	   	   -->
   	   	   <div class="menubox" >
   	   	   	   <h2 class="flex flex-pack-justify">
   	   	   	   	   <div>商品品牌</div>
   	   	   	   	   <div><#if brandId!=0><font class="cl70 font12">${(productBrandName)!''}&nbsp;</font></#if><span class="fa fa-angle-down"></span></div>
   	   	   	   </h2>
   	   	   	   <ul class="menu-2-ul">
   	   	   	   		<#if brandId==0>
	   	   	   	   		<li class="flex flex-pack-justify clr53">
		   	   	   	   	  	  <div><i></i>全部</div>
		   	   	   	   	  	  <div><span class="fa fa-check" style="display:inline-block;"></span></div>
		   	   	   	   	</li>
	   	   	   	   	<#else>
	   	   	   	   		<li class="flex flex-pack-justify" onclick="filterBrand(0)">
		   	   	   	   	  	  <div><i></i>全部</div>
		   	   	   	   	  	  <div><span class="fa fa-check"></span></div>
		   	   	   	   	</li>
	   	   	   	   	</#if>
   	   	   	      <#if productBrands??>
				    <#list productBrands as productBrand>
		   	   	   	   	  <li class="flex flex-pack-justify <#if brandId==productBrand.id>clr53</#if>" onclick="filterBrand(${(productBrand.id)!})">
		   	   	   	   	  	  <div><i></i>${(productBrand.name)!""}</div>
		   	   	   	   	  	  <div><span class="fa fa-check"  <#if brandId==productBrand.id>style="display:inline-block;"</#if>></span></div>
		   	   	   	   	  </li>
   	   	   	   	  	</#list>
				  </#if>
   	   	   	   </ul>
   	   	   </div>
		   
		   <#if productTypeAttrVOsAll?? && (productTypeAttrVOsAll?size>0)>
		   	 <#list productTypeAttrVOsAll as productTypeAttrVO>
	   	   	   <div class="menubox">
	   	   	   	   <h2 class="flex flex-pack-justify">
	   	   	   	   	   <div>${(productTypeAttrVO.name)!""}</div>
	   	   	   	   	   <div><#if productTypeAttrVO.isChoice != 0><font class="cl70 font12">${(productTypeAttrVO.choiceName)!""}&nbsp;</font></#if><span class="fa fa-angle-down"></span></div>
	   	   	   	   </h2>
	   	   	   	   <ul class="menu-2-ul">
	   	   	   	   	  <#if productTypeAttrVO.isChoice == 0>
		   	   	   	   	  <li class="flex flex-pack-justify clr53">
		   	   	   	   	  	  <div>全部</div>
		   	   	   	   	  	  <div><span class="fa fa-check" style="display:inline-block;"></span></div>
		   	   	   	   	  </li>
		   	   	   	   	   <#if productTypeAttrVO.values??>
						  	 <#list productTypeAttrVO.values as value>
				   	   	   	   	  <li class="flex flex-pack-justify" onclick="filterWhereAllNew('-${(productTypeAttrVO.id)!}_${(value_index)!}')">
				   	   	   	   	  	  <div>${(value)!""}</div>
				   	   	   	   	  	  <div><span class="fa fa-check"></span></div>
				   	   	   	   	  </li>
		   	   	   	   	     </#list>
					     </#if>
	   	   	   	   	  <#else>
		   	   	   	   	  <li class="flex flex-pack-justify" onclick="filterWhereAll('-${(productTypeAttrVO.choiceAll)!}')">
		   	   	   	   	  	  <div>全部</div>
		   	   	   	   	  	  <div><span class="fa fa-check"></span></div>
		   	   	   	   	  </li>
		   	   	   	   	  
		   	   	   	   	   <#if productTypeAttrVO.values??>
						  	 <#list productTypeAttrVO.values as value>
				   	   	   	   	  <li class="flex flex-pack-justify <#if value_index==productTypeAttrVO.isChoiceIndex>clr53</#if>" onclick="filterWhereAllChange('-${(productTypeAttrVO.choiceAll)!}', '-${(productTypeAttrVO.id)!}_${(value_index)!}')">
				   	   	   	   	  	  <div>${(value)!""}</div>
				   	   	   	   	  	  <div><span class="fa fa-check" <#if value_index==productTypeAttrVO.isChoiceIndex>style="display:inline-block;"</#if>></span></div>
				   	   	   	   	  </li>
		   	   	   	   	     </#list>
					     </#if>
	   	   	   	   	  </#if>
	   	   	   	   	  
	   	   	   	   </ul>
	   	   	   </div>
			</#list>
		</#if>
   	   </div>
	</div>
	<!-- 筛选层  end -->
	
	<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

<script type="text/javascript">
function filterWhereAll(delfilter) {
	var urlPath = "${(urlPath)!}";
	var url = urlPath.replace(delfilter, "");
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

function filterWhereAllNew(filter) {
	var urlPath = "${(urlPath)!}";
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + urlPath + filter + ".html";
}

function filterWhereAllChange(delfilter, changefilter) {
	var urlPath = "${(urlPath)!}";
	var url = urlPath.replace(delfilter, "");
	url = url + changefilter;
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

function filterSort(sort) {
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 3) {
	    	url += sort;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

function filterStore(store) {
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 5) {
	    	url += store;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

function filterDoSelf(doSelf) {
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 4) {
	    	url += doSelf;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

function filterBrand(brand) {
	var urlPath = "${(urlPath)!}";
	var urlPaths = urlPath.split("-");
	var url = "";
	for(var i=0; i<urlPaths.length; i++) {
	    if(i == 6) {
	    	url += brand;
	    } else {
	    	url += urlPaths[i];
	    }
		if((i+1) != urlPaths.length) {
			url += "-";
		}
	}
	self.location="${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
}

var sessionKey = "producListSession";

function pageIsHistory() {
	var list_page = $("#list_page").val();
	if (list_page == 0) sessionStorage.clear();
	else {
		var ssVal = sessionStorage.getItem(sessionKey);
		$("#product_list_more").append(ssVal);
		
        $("img").lazyload({
            effect:'fadeIn'
        });
		
	}
}

$(function(){
	pageIsHistory();
	
	getMoreList();
	
	$("#product_list_more_json").click(function(){
        getMoreList();
	});
	
})

	function getMoreList() {		
		var list_page = $("#list_page").val();
		list_page++;
		$("#list_page").val(list_page);
		
		var urlPath = "${(urlPath)!}";
		var urlPaths = urlPath.split("-");
		var url = "listjson-";
		for(var i=1; i<urlPaths.length; i++) {
		    if(i == 2) {
		    	url += list_page;
		    } else {
		    	url += urlPaths[i];
		    }
			if((i+1) != urlPaths.length) {
				url += "-";
			}
		}
		var urljson = "${(domainUrlUtil.EJS_URL_RESOURCES)!}/" + url + ".html";
		
		var listJsonHtml = "";
		$("#product_list_more_json_wait").show();
		$("#product_list_more_json").hide();
		$("#product_list_more_json_no").hide();
		$.ajax({
            type:"get",
            url: urljson,
            dataType: "json",
            cache:false,
            success:function(data){
                if (data.success) {
                    $.each(data.data, function(i, product){
                    	var largeBool = false;// $("#imgOrlistViewid").hasClass("fa fa-th-large");
                    	var listBool = $("#imgOrlistViewid").hasClass("fa fa-list-ul");
                    	if(largeBool){
	                    	listJsonHtml += "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' class='block'>";
	                    	listJsonHtml += "<dl class='flex shopwindow-dl'>";
	                    	listJsonHtml += "<dt><img class='lazy" + list_page + "' data-original='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + product.masterImg + "' width='100' height='100'></dt>";
	                    	listJsonHtml += "<dd class='padl flex-2'>";
	                    	listJsonHtml += "<div class='product_name'>" + product.name1 + "</div>";
	                    	listJsonHtml += "<div class='product-desript'>";
	                    	listJsonHtml += "<p class='clr53' style='font-size:18px'>￥<font>" + product.malMobilePrice + "</font>";
	                    	
	                    	if (product.marketPrice != "" && product.marketPrice != null && product.marketPrice != product.malMobilePrice) {
	                    		listJsonHtml += ' <span style="color: black;text-decoration: line-through;font-size: 12px;"><span>¥</span>' + product.marketPrice + '</span>';
	                    	}
	                    	listJsonHtml += "</p>";
	                    	if(product.productStock > 0) {
	                    		//listJsonHtml += "<p class='ratings'><span>有货</span>";
	                    	}else {
	                    		//listJsonHtml += "<span>无货</span>";
	                    	}
	                    	//listJsonHtml += "&nbsp;&nbsp;";
	                    	//listJsonHtml += "<font>" + product.commentsNumber + "</font>条评价</p>";
	                    	//加入进货单
	                    	listJsonHtml += "<a href=\"javascript:void(0)\" onclick=addCart(" + product.id + "," + product.sellerId + ") class=\"product-join\">加入进货单</a>";
	                    	listJsonHtml += "</div></dd></dl></a>";
                    	}
                    	else {
                    		listJsonHtml += "<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html' class='block'>";
	                    	listJsonHtml += "<dl class='flex list-dl'>";
	                    	listJsonHtml += "<dt><img class='lazy" + list_page + "' data-original='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}" + product.masterImg + "' width='100' height='100'></dt>";
	                    	listJsonHtml += "<dd class='padl flex-2'>";
	                    	listJsonHtml += "<div class='product_name'>" + product.name1 + "</div>";
	                    	listJsonHtml += "<div class='product-desript'>";
	                    	listJsonHtml += "<p class='clr53' style='font-size:18px'>￥<font>" + product.malMobilePrice + "</font>";
	                    	
	                    	if (product.marketPrice != "" && product.marketPrice != null && product.marketPrice != product.malMobilePrice) {
	                    		listJsonHtml += ' <span style="color: black;text-decoration: line-through;font-size: 12px;"><span>¥</span>' + product.marketPrice + '</span>';
	                    	}
	                    	listJsonHtml += "</p>";
	                    	if(product.productStock > 0) {
	                    		//listJsonHtml += "<p class='ratings'><span>有货</span>";
	                    	}else {
	                    		//listJsonHtml += "<span>无货</span>";
	                    	}
	                    	//listJsonHtml += "&nbsp;&nbsp;";
	                    	//listJsonHtml += "<font>" + product.commentsNumber + "</font>条评价</p>";
	                    	//加入进货单
	                    	listJsonHtml += "<a href=\"${(domainUrlUtil.EJS_URL_RESOURCES)!}/product/" + product.id+ ".html\" class=\"product-join\">查看详情</a>";
	                    	listJsonHtml += "</div></dd></dl></a>";
                    	}
                    });
                    
                    $("#product_list_more").append(listJsonHtml);
	                $("img.lazy" + list_page ).lazyload({
	                    effect:'fadeIn'
	                })
                    var ssVal = sessionStorage.getItem(sessionKey);
					if (ssVal == null) ssVal = "";
					sessionStorage.setItem(sessionKey, ssVal + listJsonHtml);
					if (data.total == 0) {
						$("#product_list_more_json").show();
						$("#product_list_more_json_no").hide();
						$("#product_list_more_json_wait").hide();
					}
					else if (data.total == 2) {
						$("#product_list_more").append("<div style='background:#e9faff;border-top:1px solid #e5e5e5;border-bottom:1px solid #e5e5e5;width: 410px;height:86px;margin: 5px 0 0 0;'><div style='float:left;margin:20px 24px'><img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/icon_collapse.png'></div><div style='font-family:MicrosoftYaHei;font-size:18px;color:#888888;text-align:left;padding-top: 21px;'>没有找到相关商品。</div><div style='font-family:MicrosoftYaHei;font-size: 12px;color:#888888;text-align:left;padding-top: 10px;'>或许您可以在商品列表中筛选到满意的商品。</div></div>");
						
						$("#product_list_more_json_wait").hide();
						$("#product_list_more_json").hide();
					}
					else {
						$("#product_list_more_json").hide();
						$("#product_list_more_json_no").show();
						$("#product_list_more_json_wait").hide();
					}
                }
            }
        });
	}

					//点击加载更多
				/* 	$(document).ready(function() {
				$('#product_list_more_json').click(function(){
		myloadoriginal = this.text;
		$(this).text('请稍等，正在努力加载中...');
		var myload = this;
		setTimeout(function() { $(myload).text(myloadoriginal); }, 2000);
		});
		}); */



/**
		添加进货单
	*/
	function addCart(productId,sellerId){
		//未登录不能添加进货单
		if (!isUserLogin()) {
			// 未登录跳转到登陆页面
			window.location.href = domain+"/login.html";
			return;
		}
		$.ajax({
			type : "POST",
			url :  domain+"/cart/addtocart2.html",
			data : {productId:productId,sellerId:sellerId},
			dataType : "json",
			success : function(data) {
				if(data.success){
					//跳转到添加进货单成功页面
					$.dialog('alert','提示','添加成功',2000,function(){
						window.location.reload(true);
					});
				}else{
					jAlert(data.message);
				}
			},
			error : function() {
				jAlert("数据加载失败！");
			}
		});
	}
	/**
	*changeLayout  两种视图显示  ： 列表视图 和 图标视图
	*/
	function changeLayout(obj){
		if($(obj).hasClass("fa fa-th-large")){
			//切换为列表视图
			$(obj).removeClass("fa fa-th-large");
			$(obj).addClass("fa fa-list-ul");
			$(".listbox > dl").removeClass("flex shopwindow-dl");
			$(".listbox > dl").addClass("flex list-dl");
			$("#product_list_more > dl").removeClass("flex shopwindow-dl");
			$("#product_list_more > dl").addClass("flex list-dl");
			console.dir("列表视图");
		}else{
			//切换为图标视图
			$(obj).removeClass("fa fa-list-ul");
			$(obj).addClass("fa fa-th-large");
			console.dir("图标视图");
			$(".listbox > dl").removeClass("flex list-dl");
			$(".listbox > dl").addClass("flex shopwindow-dl");
			$("#product_list_more > dl").removeClass("flex list-dl");
			$("#product_list_more > dl").addClass("flex shopwindow-dl");
		}
	}
</script>
</body>
</html>
