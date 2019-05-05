<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>大袜网商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"></link>
		
		<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		
		<link  rel="stylesheet" type="text/css" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'/>
		<!-- 采用CDN方式引入字体图标，优点:访问速度快 -->
		<link href="//cdn.bootcss.com/font-awesome/3.0.2/css/font-awesome-ie7.min.css" rel="stylesheet">
		<link href="//cdn.bootcss.com/font-awesome/3.0.2/css/font-awesome.min.css" rel="stylesheet">
		
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/tzm_base.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/ten_items.css">
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
		<script type="text/javascript">
			var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
			var flag = "${flag}";
			$(function(){
				if(flag == 1){
					$("html,body").animate({scrollTop:480},1000);
				}else if(flag == 2){
					$("html,body").animate({scrollTop:850},1000);
				}else if(flag == 3){
					$("html,body").animate({scrollTop:1220},1000);
				}else if(flag == 4){
					$("html,body").animate({scrollTop:2000},1000);
				}else if(flag == 5){
					$("html,body").animate({scrollTop:2700},1000);
				}else if(flag == 6){
					$("html,body").animate({scrollTop:3300},1000);
				}else if(flag == 7){
					$("html,body").animate({scrollTop:3800},1000);
				}else if(flag == 8){
					$("html,body").animate({scrollTop:4200},1000);
				}else if(flag == 9){
					$("html,body").animate({scrollTop:4900},1000);
				}else if(flag == 10){
					$("html,body").animate({scrollTop:6300},1000);
				}
			})
		</script>
	</head>
	<body>
	<!--  主体区域 -->
		<div class='container topmargin'>
			<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html" target="_blank" title="大袜网欢迎您"> 
				<img alt="大袜网" src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/dawawang.png" /> 
			</a>
			<span>平台服务详情</span>
		</div>
		<div class="container bg_service">
			<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/bg_service.png" alt="服务详情" />
		</div>
		<div class="container aaa" id="aaa">
			<div class="aaa_1">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/title_s01.png" alt="视觉包装" />
			</div>
			<div class="aaa_2">
				&#12288;&#12288;大袜网设有一站式专业视觉包装服务中心，帮助客户对商品进行全方位的视觉包装，包括商品拍摄、定制拍摄、后期制作修图、
				数据包制作、商品<br/><br/>包装设计等。我们致力于通过专业的审美和视觉语言把商品从制造商更好更有效地传递给消费者，提高商品的动销率，
				解决客户商品视觉包装的痛点。
			</div>
			<div class="aaa_3">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/vision_img_s01.png" alt="商品定制拍摄" />
				<span>商品定制拍摄</span>
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/&.png" alt="&" />
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/vision_img_s02.png" alt="后期修图" />
				<span>后期修图</span>
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/&.png" alt="&" />
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/vision_img_s03.png" alt="数据包制作" />
				<span>数据包制作</span>
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/&.png" alt="&" />
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/vision_img_s04.png" alt="包装制作" />
				<span>包装制作</span>
			</div>
		</div>
		<div class="container bbb" id="bbb">
			<div class="bbb_1">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/title_s02.png" alt="二次加工" />
			</div>
			<div class="bbb_2">
			大袜网提供对袜子商品的二次加工，对商品进行美化包装。包括商品品牌的设计，贴牌；包装的设计，成型；礼盒的设计，分装等！
			</div>
			<div class="bbb_3">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/machining_img_s01.png" alt="" />			
				<span>[ 品牌设计 ]</span>
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/machining_img_s02.png" alt="" />			
				<span>[ 商品贴牌 ]</span>
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/machining_img_s03.png" alt="" />			
				<span>[ 包装·礼盒设计 ]</span>
			</div>
		</div>
		<div class="container ccc">
			<div class="ccc_1">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/title_s03.png" alt="品牌运营" />
			</div>
			<div class="ccc_2">
				&#12288;&#12288;大袜网有专业的品牌运营团队，利用品牌这一最重要的无形资本，在营造强势品牌的基础上，更好的发挥强势品牌的扩张功能，
				促进品牌商品的生<br/><br/>产经营，使品牌资产有形化，同时对客户的商品进行定位，分析，推广，帮助客户在不同的平台进行专业的
				品牌运营，包括大袜网，淘宝，天猫，京<br/><br/>东，唯品会，蘑菇街等。
			</div>
			<div class="ccc_3">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/brand_all_img.png" alt="" />
			</div>
		</div>
		<div class="container ddd">
			<div class="ddd_1">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/title_s04.png" alt="品牌运营" />
			</div>
			<div class="ddd_2">
				&#12288;&#12288;大袜网可以为您的商品以及品牌进行策划包装，用大袜网丰富的资源进行宣传，线上通过自媒体以及站内资源为您量身打造策划推广活动，
				线下通<br/><br/>过广告投放等方式为您的产品，品牌进行宣传，帮助客户获取更多的用户资源，打开市场。同时大袜网平台
				会不定期的做线上活动，开新品订货会等，<br/><br/>用可行的方式为客户提供方便快捷的服务，让客户在买到心意商品的同时
				还可以感受到大袜网人性的服务理念。
			</div>
			<div class="ddd_3">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/plan_img.png" alt="" />
				<span>[ online&offine 线上线下全方位推广 ]</span>
			</div>
		</div>
		<div class="container eee">
			<div class="eee_1">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/title_s05.png" alt="大袜云仓" />
			</div>
			<div class="eee_2">
			&#12288;&#12288;袜云仓托管是一个专为电子商务打造的云仓储服务平台。主要针对电商仓储、物流快递、供应链问题提供解决方法方案。
			我们拥有3.8万平的袜云<br/><br/>仓，秉承“诚信、专业、简单、高效”的办事原则，为客户提供优质、贴心的服务；为供应商提供精准的市场分析。
			公司采用全面开放的云仓托管模式；<br/><br/>自主研制的智能wms仓储管理系统为分拣打包过程节省大部分时间；简单直观的任务交互系统，
			让您随时随地上传任务并管理订单；及时的售后反应处<br/><br/>理。创新的云存储模式,不管您在什么地方,我们都能
			将您的货物通过快递送至买家手中,极大的提高了消费者购物体验,也为您的店铺增加了信誉!袜云仓的<br/><br/>优势在于帮助客户解决仓储的问题，
			一来节省仓储的开支、人员的开支；二来可以提供一件代发的优质服务，
			为客户去中间化，省事、省时、省力、省<br/><br/>钱。
			</div>
			<div class="eee_3">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/warehouse_img.png" alt="" />
			</div>
		</div>
		<div class="container fff">
			<div class="fff_1">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/title_s06.png" alt="私人定制" />
			</div>
			<div class="fff_2">
			&#12288;&#12288;大袜网提供根据你的特别需求进行个性化的私人定制服务，为您提供您专属的商品，为您设计您专属的品牌：提供来样定制；
			提供来设计稿定制；<br/><br/>提供选样定制
			</div>
			<div class="fff_3">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/customized_img_s01.png" alt="" />
				<span>设计品牌</span>
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/customized_img_s02.png" alt="" />
				<span>来样定制</span>
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/customized_img_s03.png" alt="" />
				<span>设计稿定制</span>
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/customized_img_s04.png" alt="" />
				<span>选样定制</span>
			</div>
		</div>
		<div class="container ggg">
			<div class="ggg_1">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/title_s07.png" alt="供应入驻" />
			</div>
			<div class="ggg_2">
			仅需3步，即可轻松入驻，与平台合作共赢
			</div>
			<div class="ggg_3">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/settled_img_s01.png" alt="" />
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/plus_img.png" alt="" />
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/settled_img_s01.png" alt="" />
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/plus_img.png" alt="" />
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/settled_img_s02.png" alt="" />
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/equal_img.png" alt="" />
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/settled_img_s03.png" alt="" />
			</div>
		</div>
		<div class="container hhh">
			<div class="hhh_1">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/title_s08.png" alt="大袜金融" />
			</div>
			<div class="hhh_2">
				&#12288;&#12288;大袜网与华夏银行、绍兴银行、诸暨农商银行、村镇银行、北京银行、招商银行、中国银行战略合作，提供各类金融服务。
				根据您的需求，为您方<br/><br/>便快捷的解决金融问题。
			</div>
			<div class="hhh_3">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/finance_img.png" alt="" />
			</div>
		</div>
		<div class="container iii">
			<div class="iii_1">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/title_s09.png" alt="创业孵化" />
			</div>
			<div class="iii_2">
				&#12288;&#12288;做为一站式创业服务平台，大袜网进行资源聚合、平台赋能，招募仓储专业人员、资源拥有者、内部专业人员、优质用户、第三方物流公司、
				高校<br /><br />优秀创业团队成为行业合伙人，设定灵活创业机制，明确权责，全程培训督导，最终实现资源互补、互利共赢。
				<br /><br />
				项目一：百校创客计划<br /><br />
				项目简介：<br /><br />
				&#12288;&#12288;大袜网结合自身丰富资源，与各高校建立创客联盟，通过寻找袜样设计师、爱心营销达人等系列活动，营造大学生的创意创业氛围，选拔、培育、
				<br /><br />提升大学生优秀创意创业团队，搭建共赢校园创意创业平台。大袜网计划在5年内建设完成全国100所高校创客联盟基地。<br /><br />
				招募对象：<br /><br />
				高校各类创业团队；美术、设计及相关专业学生；高校广告、营销及相关专业学生等；<br /><br />
				合作模式：<br /><br />
				    O2O体验店（线下、线上、自营；批发、零售）<br /><br />
				（1）功能：形象展示、商品展示、自营；场地要求：工作室、店铺<br /><br />
				（2）仓储：总部袜云仓<br /><br />
				（3）物流：一件代发<br /><br />
				（4）奖惩办法<br /><br />
				    &#12288;&#12288;&#12288;&#12288;1、年销售额前10强，增加铺货量、返点、现金奖励<br /><br />
				    &#12288;&#12288;&#12288;&#12288;2、年销售后10名，整改，无明显改进将取消加盟资格<br /><br />
				    &#12288;&#12288;&#12288;&#12288;3、新锐奖（进步最快、影响力大等）每年10个，给予现金奖励<br /><br />
				
				项目二：创客联盟<br /><br />
				项目简介：<br /><br />
				袜业产业链运营主体，组建全产业链服务体系，强强联合，短板互补，细化分工，互利共赢。<br /><br />
				招募对象：<br /><br />
				平台、原料商、生产商、品牌商、摄影公司、设计公司、广告公司、公关公司、媒体、辅料商、物流公司、快递公司、金融公司等。<br /><br />
				合作模式：<br /><br />
				平台入驻，线上交易，资源共享。
			</div>
			<div class="iii_3">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/idea_img.png" alt="" />
				<span>［让梦想成真 ]</span>
			</div>
		</div>
		<div class="container jjj">
			<div class="jjj_1">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/title_s010.png" alt="行业数据" />
			</div>
			<div class="jjj_2">
				获取行业数据，对数据进行整理、调研、分析，发布行业数据，季度报告，结合市场情况给出建议，为行业发展方向提供指导：<br /><br />
				1.行业资讯（公开）；<br /><br />
				2.行业季度报告，每季度一次，商品上架销售<br /><br />
				3.爆款预告<br /><br />
				4.定制网络销售监控服务；客户有需求监控某家网络销售店铺<br /><br />
				5.定制专属报告，上门针对性调研分析；对客户企业目前情况深入调研，结合市场情况给出建议。
			</div>
			<div class="jjj_3">
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/data_img_s01.png" alt="" />
				<img src="${domainUrlUtil.EJS_STATIC_RESOURCES!}/img/ten/data_img_s02.png" alt="" />
			</div>
		</div>
	<!-- end -->
		<div style="margin-bottom: 100px;"></div>
		<#include "/front/commons/_endbig.ftl" />
	</body>
</html>