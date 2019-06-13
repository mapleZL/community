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
		<@shiro.hasPermission name="/admin/product/cate">
		<a id='304' href="javascript:void(0);" onclick="addTab('分类管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/cate')">分类管理</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/waitSale">
		<a id='308' href="javascript:void(0);" onclick="addTab('待售商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/waitSale')">待售商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/add">
		<a id='211' href="javascript:void(0);" onclick="addTab('新增商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/add')">新增商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/onSale">
		<a id='309' href="javascript:void(0);" onclick="addTab('在售商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/onSale')">在售商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/delSale">
		<a id='310' href="javascript:void(0);" onclick="addTab('已删除商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/delSale')">已删除商品</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/product/examine">
		<a id='311' href="javascript:void(0);" onclick="addTab('待审核商品', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/examine')">待审核商品</a>
		</@shiro.hasPermission>
		
		<@shiro.hasPermission name="/admin/seller/manage/edit">
		<a href="javascript:void(0);" onclick="addTab('商家信息', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/seller/manage/edit')">商家信息</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>

	<@shiro.hasPermission name="/admin_menu_orders">
	<div title="订单管理" class="ra_div">
		<@shiro.hasPermission name="/admin/order/orders/confirmorder">
		<a href="javascript:void(0);" onclick="addTab('待确认订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders/confirmorder')">待确认订单</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/order/orders">
		<a href="javascript:void(0);" onclick="addTab('所有订单', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/order/orders')">所有订单</a>
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
		<@shiro.hasPermission name="/admin/property/repair/undo">
		<a href="javascript:void(0);" onclick="addTab('待确认报修', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/property/repair/undo')">待确认报修</a>
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
		<@shiro.hasPermission name="/admin/member/pet/system">
		<a href="javascript:void(0);" onclick="addTab('宠物登记', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/member/pet/system')">宠物登记</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>

	<@shiro.hasPermission name="/admin_menu_activity">
	<div title="活动管理" class="ra_div">
		<@shiro.hasPermission name="/notice/activity">
		<a href="javascript:void(0);" onclick="addTab('活动审核', '${domainUrlUtil.EJS_URL_RESOURCES}/notice/activity')">活动报名审核</a>
		</@shiro.hasPermission>
		<@shiro.hasPermission name="/notice/activity">
		<a href="javascript:void(0);" onclick="addTab('新增热门活动', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/event/add')">新增热门活动</a>
		</@shiro.hasPermission>
	</div>
	</@shiro.hasPermission>
    <!--
            --gaowei --邻居共享页面
        -->
	<@shiro.hasPermission name="/admin_menu_shareInfo">
		<div title="邻里共享" class="ra_div">
			<@shiro.hasPermission name="/admin/share">
                <a href="javascript:void(0);" onclick="addTab('共享大厅', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/')">共享大厅</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name="/admin/share/system">
                <a href="javascript:void(0);" onclick="addTab('物业发布', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/system')">物业发布</a>
			</@shiro.hasPermission>
        </div>

	</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin_menu_complaint">
		<div title="意见投诉" class="ra_div">
			<@shiro.hasPermission name="/admin/complaint/system">
                <a href="javascript:void(0);" onclick="addTab('投诉信息', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/complaint/system')">意见投诉</a>
			</@shiro.hasPermission>
        </div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_environmental">
		<div title="环境秩序" class="ra_div">
			<@shiro.hasPermission name="/admin/overtime">
                <a href="javascript:void(0);" onclick="addTab('时效管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/overtime/')">时效管理</a>
			</@shiro.hasPermission>
		<@shiro.hasPermission name="/admin/environ">
            <a href="javascript:void(0);" onclick="addTab('环境秩序', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/environ/')">环境秩序</a>
        </@shiro.hasPermission>
        </div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_lostfound">
		<div title="失物招领" class="ra_div">
			<@shiro.hasPermission name="/admin/lost">
                <a href="javascript:void(0);" onclick="addTab('失物招领大厅', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/lost/')">失物招领大厅</a>
			</@shiro.hasPermission>
			<@shiro.hasPermission name="/admin/lost/system">
                <a href="javascript:void(0);" onclick="addTab('社区物业发布', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/lost/system')">物业登记</a>
			</@shiro.hasPermission>
        </div>
	</@shiro.hasPermission>
	<@shiro.hasPermission name="/admin_menu_parking">
			<div title="停车服务" class="ra_div">
				<@shiro.hasPermission name="/admin/payment">
					<a href="javascript:void(0);" onclick="addTab('支付管理', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/payment/')">支付管理</a>
				</@shiro.hasPermission>
				<@shiro.hasPermission name="/admin/price">
					<a href="javascript:void(0);" onclick="addTab('价钱设置', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/price/')">价钱设置</a>
				</@shiro.hasPermission>
			</div>
	</@shiro.hasPermission>

	<#-- 移动端管理 -->
	<@shiro.hasPermission name="/admin_menu_mobile">
		<div title="移动端管理" class="ra_div">
			<@shiro.hasPermission name="/admin/mindex/banner">
				<a id='404' href="javascript:void(0);" onclick="addTab('首页轮播图', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/mindex/banner')">首页轮播图</ a>
			</@shiro.hasPermission>
			<#--<@shiro.hasPermission name="/admin/mindex/floor">-->
				<#--<a id='408' href="javascript:void(0);" onclick="addTab('移动端首页楼层', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/mindex/floor')">移动端首页楼层</ a>-->
			<#--</@shiro.hasPermission>-->
			<#--<@shiro.hasPermission name="/admin/mindex/floordata">-->
				<#--<a id='412' href="javascript:void(0);" onclick="addTab('楼层数据', '${domainUrlUtil.EJS_URL_RESOURCES}/admin/mindex/floordata')">楼层数据</ a>-->
			<#--</@shiro.hasPermission>-->
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

</div>


