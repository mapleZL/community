<style>
    .ra_div a {
        display: block;
        border-bottom: dotted 1px #dedede;
        padding-bottom: 3px;
        text-decoration: none;
        color: #000;
        font-weight: normal;
        padding-top: 5px;
        padding-left: 15px;
    }
</style>
<div id="aa" class="easyui-accordion" data-options="fit:true"
     style="height: auto;">

	<@shiro.hasPermission name="/admin_menu_product">
	<div title="商品管理" class="ra_div">
		<@shiro.hasPermission name="/admin/product/brand">
		<a id='301' href="javascript:void(0);" onclick="addTab('品牌管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/brand')">品牌管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/norm">
		<a id='302' href="javascript:void(0);" onclick="addTab('规格管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/norm')">规格管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/type">
		<a id='303' href="javascript:void(0);" onclick="addTab('类型管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/type')">类型管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/cate">
		<a id='304' href="javascript:void(0);" onclick="addTab('分类管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/cate')">分类管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/brand/todo">
		<a id='305' href="javascript:void(0);" onclick="addTab('待审核品牌', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/brand/todo')">待审核品牌</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/cate/audit">
		<a id='306' href="javascript:void(0);" onclick="addTab('商家分类申请', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/cate/audit')">商家分类申请</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/waitSale">
		<a id='308' href="javascript:void(0);" onclick="addTab('待售商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/waitSale')">待售商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/waitSale">
		<a id='455' href="javascript:void(0);" onclick="addTab('阶梯价待售商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/waitSale2')">阶梯价待售商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/onSale">
		<a id='309' href="javascript:void(0);" onclick="addTab('在售商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/onSale')">在售商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/delSale">
		<a id='310' href="javascript:void(0);" onclick="addTab('已删除商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/delSale')">已删除商品</a>
		</@shiro.hasPermission>
		
		<@shiro.hasPermission name="/admin/product/sendgoods">
		<a id='573' href="javascript:void(0);" onclick="addTab('商品预约发货', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/sendgoods')">商品预约发货</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/vertify">
		<a id='591' href="javascript:void(0);" onclick="addTab('商品预约收货', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/vertify')">商品预约收货</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/badgoods">
		<a id='598' href="javascript:void(0);" onclick="addTab('次品管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/badgoods')">次品管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/productbuystock">
		<a href="javascript:void(0);" onclick="addTab('最低库存销售设定', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/productbuystock')">最低库存销售设定</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/personaltailor">
		<a href="javascript:void(0);" onclick="addTab('私人订制采购', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/personaltailor')">私人订制采购</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/wmsclassify">
		<a href="javascript:void(0);" onclick="addTab('wms分类管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/wmsclassify')">wms分类管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/productattr">
		<a href="javascript:void(0);" onclick="addTab('商品属性管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/productattr')">商品属性管理</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_order">
	<div title="交易管理" class="ra_div">
		<@shiro.hasPermission name="/admin/order/orders">
		<a id='336' href="javascript:void(0);" onclick="addTab('全部订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders')">全部订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state1">
		<a id='337' href="javascript:void(0);" onclick="addTab('未付款订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state1')">未付款订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state2">
		<a id='338' href="javascript:void(0);" onclick="addTab('待确认订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state2')">待确认订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state3">
		<a id='339' href="javascript:void(0);" onclick="addTab('待发货订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state3')">待发货订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state4">
		<a id='340' href="javascript:void(0);" onclick="addTab('已发货订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state4')">已发货订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state5">
		<a id='341' href="javascript:void(0);" onclick="addTab('已完成订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state5')">已完成订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state7">
		<a id='345' href="javascript:void(0);" onclick="addTab('云仓订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state7')">云仓托管订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders/state6">
		<a id='342' href="javascript:void(0);" onclick="addTab('已取消订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/state6')">已取消订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/productBack">
		<a id='343' href="javascript:void(0);" onclick="addTab('退货管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/productBack')">退货管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/productExchange">
		<a id='344' href="javascript:void(0);" onclick="addTab('换货管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/productExchange')">换货管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/complaint">
		<a id='62' href="javascript:void(0);" onclick="addTab('投诉管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/complaint')">投诉管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/leadingin">
		<a id='500' href="javascript:void(0);" onclick="addTab('订单导入', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/leadingin')">订单导入</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_repair">
	<div title="物业管理" class="ra_div">
		<@shiro.hasPermission name="/admin/repair/member">
		<a href="javascript:void(0);" onclick="addTab('维修人员管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/repair/member')">维修人员管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/property/repair">
		<a href="javascript:void(0);" onclick="addTab('社区报修管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/property/repair')">社区报修管理</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_promotion">
	<div title="客户信息" class="ra_div">
		<@shiro.hasPermission name="/admin/member/car">
		<a href="javascript:void(0);" onclick="addTab('车辆认证', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/car')">车辆认证</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/parking/lot">
		<a href="javascript:void(0);" onclick="addTab('车位认证', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parking/lot')">车位认证</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/house">
		<a href="javascript:void(0);" onclick="addTab('房屋认证', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/house')">房屋认证</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	
	<@shiro.hasPermission name="/admin_menu_member">
	<div title="会员管理" class="ra_div">
		<@shiro.hasPermission name="/admin/member/member">
		<a id='66' href="javascript:void(0);" onclick="addTab('会员管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/member')">会员管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/config/gradevalue">
		<a id='69' href="javascript:void(0);" onclick="addTab('会员经验规则管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/config/gradevalue')">会员经验规则管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/config/intvalue">
		<a id='70' href="javascript:void(0);" onclick="addTab('会员积分规则管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/config/intvalue')">会员积分规则管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/config/grade">
		<a id='71' href="javascript:void(0);" onclick="addTab('会员等级配置', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/config/grade')">会员等级配置</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/drawmoney">
		<a id='72' href="javascript:void(0);" onclick="addTab('会员提款申请', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/drawmoney')">会员提款申请</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/productcomments">
		<a id='150' href="javascript:void(0);" onclick="addTab('会员商品评价管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/productcomments')">会员商品评价管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/productask">
		<a id='151' href="javascript:void(0);" onclick="addTab('会员商品咨询管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/productask')">会员商品咨询管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/parter">
		<a id='152' href="javascript:void(0);" onclick="addTab('合伙人管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter')">合伙人管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/parter">
		<a id='153' href="javascript:void(0);" onclick="addTab('合伙人佣金管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter/percentlist')">合伙人佣金管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/member/memberCredit">
		<a href="javascript:void(0);" onclick="addTab('赊账管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/memberCredit')">赊账管理</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_seller">
	<div title="商家管理" class="ra_div">
		<@shiro.hasPermission name="/admin/seller/audit">
		<a id='67' href="javascript:void(0);" onclick="addTab('商家申请', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/audit')">商家申请</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/seller/manage">
		<a id='68' href="javascript:void(0);" onclick="addTab('商家管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage')">商家管理</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>

	<@shiro.hasPermission name="/admin_menu_pcindex">
	<div title="PC端首页管理" class="ra_div">
		<@shiro.hasPermission name="/admin/pcindex/banner">
		<a id='445' href="javascript:void(0);" onclick="addTab('PC首页轮播图', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/banner')">PC首页轮播图</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/recommend">
		<a id='452' href="javascript:void(0);" onclick="addTab('PC推荐商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/recommend')">PC推荐商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floor">
		<a id='459' href="javascript:void(0);" onclick="addTab('PC首页楼层', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floor')">PC首页楼层</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floorclass">
		<a id='466' href="javascript:void(0);" onclick="addTab('PC首页楼层分类', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorclass')">PC首页楼层分类</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floordata">
		<a id='474' href="javascript:void(0);" onclick="addTab('PC首页楼层分类数据', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floordata')">PC首页楼层分类数据</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floorpatch">
		<a id='478' href="javascript:void(0);" onclick="addTab('PC首页楼层碎屑', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/floorpatch')">PC首页楼层碎屑</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floorpatch">
		<a id='479' href="javascript:void(0);" onclick="addTab('PC精品推荐管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/jprecommend')">PC精品推荐管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/pcindex/floorpatch">
		<a id='480' href="javascript:void(0);" onclick="addTab('PC首页楼层商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/pcindex/jprecommend1')">PC首页楼层商品</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_operate">
	<div title="运营管理" class="ra_div">
		<@shiro.hasPermission name="/admin/operate/courierCompany">
		<a id='352' href="javascript:void(0);" onclick="addTab('物流公司', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/courierCompany')">物流公司</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/config">
		<a id='383' href="javascript:void(0);" onclick="addTab('配置管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/config')">配置管理</a>
		</@shiro.hasPermission>
	
		<@shiro.hasPermission name="/admin/operate/productLabel">
		<a href="javascript:void(0);" onclick="addTab('标签管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/productLabel')">标签管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/operate/productPackage">
		<a href="javascript:void(0);" onclick="addTab('套餐设定', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/productPackage')">套餐设定</a>
		</@shiro.hasPermission>
		
		<@shiro.hasPermission name="/admin/operate/transport">
		<a href="javascript:void(0);" onclick="addTab('运费设置', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/transport')">运费设置</a>
		</@shiro.hasPermission>
		
		<@shiro.hasPermission name="/admin/system/report">
		<a href="javascript:void(0);" onclick="addTab('通用报表', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/report')">通用报表</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/operate/seo">
		<a href="javascript:void(0);" onclick="addTab('物流公司', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/operate/seo')">SEO设置</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_settlement">
	<div title="统计结算" class="ra_div">
	    <@shiro.hasPermission name="/admin/wmsinterface">
		<a id='350' href="javascript:void(0);" onclick="addTab('wms推送管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/wmsinterface')">wms推送管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/settlement">
		<a id='347' href="javascript:void(0);" onclick="addTab('结算管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/settlement')">结算管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/orderday">
		<a id='364' href="javascript:void(0);" onclick="addTab('每日订单统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orderday')">每日订单统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/productday">
		<a id='365' href="javascript:void(0);" onclick="addTab('每日商品统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/productday')">每日商品统计</a>
		</@shiro.hasPermission>
		
		<@shiro.hasPermission name="/admin/report/orders/orderOverview">
		<a href="javascript:void(0);" onclick="addTab('订单概况', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orders/orderOverview')">订单概况</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/product/productSale">
		<a href="javascript:void(0);" onclick="addTab('商品销量统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/product/productSale')">商品销量统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/orders/saleOverview">
		<a href="javascript:void(0);" onclick="addTab('订单销量统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orders/saleOverview')">订单销量统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/product/phurchaseRate">
		<a href="javascript:void(0);" onclick="addTab('购买率统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/product/phurchaseRate')">购买率统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/product/pvStatistics">
		<a href="javascript:void(0);" onclick="addTab('商品浏览量统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/product/pvStatistics')">商品浏览量统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/orders/goodsReturnRate">
		<a href="javascript:void(0);" onclick="addTab('退货率统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orders/goodsReturnRate')">退货率统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/orders/CPIstatistics">
		<a href="javascript:void(0);" onclick="addTab('人均消费统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orders/CPIstatistics')">人均消费统计</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/report/orderfinanceday">
		<a href="javascript:void(0);" onclick="addTab('每日收款统计', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/report/orderfinanceday')">每日收款统计</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_search">
	<div title="搜索管理" class="ra_div">
		<@shiro.hasPermission name="/admin/searchlogs">
		<a id='570' href="javascript:void(0);" onclick="addTab('搜索词历史记录', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/searchlogs')">搜索词历史记录</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/searchIndexes">
		<a id='368' href="javascript:void(0);" onclick="addTab('索引初始化', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/searchIndexes')">索引初始化</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/cache/pcacheIndexes">
		<a id='720' href="javascript:void(0);" onclick="addTab('Pc首页静态化', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/cache/pcacheIndexes')">PC首页静态化</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/cache/mcacheIndexes">
		<a id='722' href="javascript:void(0);" onclick="addTab('H5首页静态化', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/cache/mcacheIndexes')">H5首页静态化</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/cache/refreshProductAttrCache">
		<a id='722' href="javascript:void(0);" onclick="addTab('刷新商品属性缓存', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/cache/refreshProductAttrCache')">刷新商品属性缓存</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/search/keyword">
		<a id='368' href="javascript:void(0);" onclick="addTab('关键词设置', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/search/keyword')">关键词设置</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/search/keywordfilter">
		<a id='370' href="javascript:void(0);" onclick="addTab('敏感词过滤', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/search/keywordfilter')">敏感词过滤</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/searchrecord">
		<a id='370' href="javascript:void(0);" onclick="addTab('模糊搜索词', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/searchrecord')">模糊搜索词</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/searchkeyword">
		<a id='384' href="javascript:void(0);" onclick="addTab('敏感词', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/searchkeyword')">敏感词</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_system">
	<div title="系统管理" class="ra_div">
		<@shiro.hasPermission name="/admin/system/code">
		<a id='55' href="javascript:void(0);" onclick="addTab('数据字典', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/code')">数据字典</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/role">
		<a id='56' href="javascript:void(0);" onclick="addTab('角色管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/role')">角色管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/resource">
		<a id='57' href="javascript:void(0);" onclick="addTab('资源管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/resource')">资源管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/adminuser">
		<a id='82' href="javascript:void(0);" onclick="addTab('管理员管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/adminuser')">管理员管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/adminuser/editpwd">
		<a id='399' href="javascript:void(0);" onclick="addTab('修改密码', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/adminuser/editpwd')">修改密码</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_news">
	<div title="网站文章" class="ra_div">
		<@shiro.hasPermission name="/admin/system/newstype">
		<a id='63' href="javascript:void(0);" onclick="addTab('文章分类', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/newstype')">文章分类</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/news">
		<a id='64' href="javascript:void(0);" onclick="addTab('文章管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/news')">文章管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/system/newsParter">
		<a id='65' href="javascript:void(0);" onclick="addTab('合作伙伴管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/newsParter')">合作伙伴管理</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_parter">
		<div title="合伙人报表" class="ra_div">
			<@shiro.hasPermission name="/admin/member/parter/panerTotalMonth">
				<a id='401' href="javascript:void(0);" onclick="addTab('每月销售汇总', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter/panerTotalMonth')">每月销售汇总</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name="/admin/member/parter/panerTotalDays">
				<a id='402' href="javascript:void(0);" onclick="addTab('每日销售汇总', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter/panerTotalDays')">每日销售汇总</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name="/admin/member/parter/getSalesDetails">
				<a id='403' href="javascript:void(0);" onclick="addTab('销售明细汇总', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter/getSalesDetails')">销售明细汇总</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name="/admin/member/parter/parterSalesSum">
				<a id='404' href="javascript:void(0);" onclick="addTab('合伙人提点汇总', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter/parterSalesSum')">合伙人提点汇总</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name="/admin/member/parter/parterTuijian">
				<a id='405' href="javascript:void(0);" onclick="addTab('推荐合伙人', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/parter/parterTuijian')">推荐合伙人</a>
			</@shiro.hasPermission>
		</div>
	</@shiro.hasPermission>

</div>


