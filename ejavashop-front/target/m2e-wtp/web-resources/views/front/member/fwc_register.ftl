<!Doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link rel="icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/ico/favicon.ico" type="image/x-icon" />
		<!-- 采用CDN方式引入字体图标，优点:访问速度快 -->
		<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/font-awesome/css/font-awesome.min.css">
		<link  rel="stylesheet" href='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/bootstrap.min.css'>
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/tzm_base.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc_shangjiazhuce.css">
		<link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/jquery.alerts.css"/>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery-1.9.1.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/bootstrap.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.validate.min.js'></script>
		<script type="text/javascript" src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/common.js'></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/jquery.alerts.js"></script>
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/member/members.js"></script>
		
		<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js/fwc_qqkefu.js"></script>
        <link rel="stylesheet" type="text/css" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc_qqku.css">
        
		<script type="text/javascript">
		var domain = '${(domainUrlUtil.EJS_URL_RESOURCES)!}';
		
		var msgcode;
		var intervalId;
		  if(/AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE|HTC/.test(navigator.userAgent))){
			if(window.location.href.indexOf("?mobile")<0){
				try{
					var url = location.href;
				    var sp = url.split("?");
				    var mobileUrl = "${(domainUrlUtil.EJS_H5_URL)!}/register.html?"+ sp[1];
				    var mobileUrl1 = "${(domainUrlUtil.EJS_H5_URL)!}/register.html";
					if(/iPad|Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){
						if (sp.length > 1){
							window.location = mobileUrl;
						}else{
							window.location = mobileUrl1;
						}
					}else{
						if (sp.length > 1){
							window.location = mobileUrl;
						}else{
							window.location = mobileUrl1;
						}
					}
				}catch(e){}
			}
		}
		</script>
		<style type="text/css">
		#userName-error,#smsCode-error,#verifyCode-error,#password-error,#repassword-error{
			display: block;
		}
		</style>
	</head>
    <body>

    				<div class="login-top">
    				<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html">
			  			  <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/dawawang.png" height="600" width="1920" alt="">
			    	</a>
    						<h1>用户注册</h1>
    			</div>
			<div class="sigin-mian">

				<div class="sigin-bg">

							<div class="sigin-content">
						<h2>大袜网平台用户服务协议</h2>
					<p>
						本协议由您与小麦（浙江）网络科技有限公司共同缔结，本协议无论作为合同附件或单独使用都具有合同效力。
本协议中协议双方合称协议方，小麦（浙江）网络科技有限公司在协议中亦称为“小麦”。
本协议中小麦提供的服务为“大袜网平台服务及其关联服务（以下简称大袜网平台服务）。<br/>
 一、 协议内容及签署<br/>
 1、本协议内容包括协议正文及所有小麦已经发布的或将来可能发布的各类规则。所有规则为本协议不可分割的组成部分，与协议正文具有同等法律效力。除另行明确声明外，任何大袜网平台服务均受本协议约束<br/>
2、在使用大袜网平台服务之前，您应当认真阅读并遵守《大袜网服务协议》（以下简称“本协议”），请您务必审慎阅读、充分理解各条款内容，特别是免除或者限制责任的条款、争议解决和法律适用条款。免除或者限制责任的条款可能将以加粗字体显示，您应重点阅读。如您对协议有任何疑问的，应向大袜网客服咨询。 
当您按照注册页面提示填写信息、阅读并同意本协议且完成全部注册程序后，或您按照激活页面提示填写信息、阅读并同意本协议且完成全部激活程序后，或您以其他大袜网允许的方式实际使用大袜网平台服务时，即表示您已充分阅读、理解并接受本协议的全部内容，并与大袜网平台达成协议。您承诺接受并遵守本协议的约定，届时您不应以未阅读本协议的内容或者未获得大袜网对您问询的解答等理由，主张本协议无效，或要求撤销本协议。<br/>
二、协议范围<br/>
1、本协议由您与大袜网平台的经营者共同缔结，本协议具有合同效力。大袜网平台的经营者是指法律认可的经营大袜网平台网站的责任主体，大袜网平台网站包括但不限于大袜网（域名为dawawang.com）本协议项下各大袜网平台的经营者可单称或合称为“大袜网”。有关大袜网平台经营者的信息请查看各大袜网平台首页底部公布的公司信息和证照信息。<br/>
2、除另行明确声明外，大袜网平台服务包含任何大袜网及其关联公司提供的基于互联网以及移动网的相关服务，且均受本协议约束。如果您不同意本协议的约定，您应立即停止注册/激活程序或停止使用大袜网平台服务。<br/> 
3、本协议内容包括协议正文、法律声明、《大袜网规则》及所有大袜网已经发布或将来可能发布的各类规则、公告或通知（以下合称“大袜网规则”或“规则”）。所有规则为本协议不可分割的组成部分，与协议正文具有同等法律效力。<br/> 
4、大袜网有权根据需要不时地制订、修改本协议及/或各类规则，并以网站公示的方式进行变更公告，无需另行单独通知您。变更后的协议和规则一经在网站公布后，立即或在公告明确的特定时间自动生效。若您在前述变更公告后继续使用大袜网平台服务的，即表示您已经阅读、理解并接受经修订的协议和规则。若您不同意相关变更，应当立即停止使用大袜网平台服务。<br/>
三、协议正文注册与账户<br/>

1、注册者资格
您确认，在您完成注册程序或以其他大袜网允许的方式实际使用大袜网平台服务时，您应当是具备完全民事行为能力和完全民事力的自然人、法人或其他组织。若您不具备前述主体资格，则您及您的监护人应承担因此而导致的一切后果，且大袜网有权注销或永久冻结您的账户，并向您及您的监护人索偿相应损失。<br/>
2、注册和账户<br/>
a）当您按照注册页面提示填写信息、阅读并同意本协议且完成全部注册程序后，或在您按照激活页面提示填写信息、阅读并同意本协议且完成全部激活程序后，或您以其他大袜网允许的方式实际使用大袜网平台服务时，您即受本协议约束。您可以使用您提供或确认的邮箱、手机号码或者大袜网允许的其它方式作为登录手段进入大袜网平台。<br/>
b）您了解并同意，如您在大袜网平台注册成功，您即可以同步获得大袜网平台后续能开通的其他网站/服务（以下合称“大袜网站”）而无需重新注册。您在此明确授权，您的账户注册信息在您通过大袜网平台注册成功时，已授权大袜网披露给所有大袜网站并同时授权大袜网站使用，以使您更便捷地使用大袜网站服务。<br/>
c）您后续可以对账户设置大袜网昵称，您也可以通过该大袜网昵称登录大袜网平台。您设置的大袜网昵称不得侵犯或涉嫌侵犯他人合法权益。如您设置的大袜网昵称涉嫌侵犯他人合法权益，大袜网有权终止向您提供大袜网平台服务，注销您的大袜网昵称。大袜网昵称被注销后将开放给任意用户注册。<br/> 
d）您的登录名、大袜网昵称和密码不得以任何方式买卖、转让、赠与或继承，除非有法律明确规定或司法裁定，并经大袜网同意，且需提供大袜网要求的合格的文件材料并根据大袜网制定的操作流程办理。<br/> 
3、用户信息<br/> 
a）在注册或激活流程时，您应当依照法律法规要求，按相应页面的提示准确提供您的资料，并于资料信息变更时及时更新，以保证您所提交资料的真实、及时、完整和准确。如有合理理由怀疑您提供的资料错误、不实、过时或不完整的，大袜网有权向您发出询问及/或要求改正的通知，并有权直接做出删除相应资料的处理，直至中止、终止对您提供部分或全部大袜网平台服务。大袜网对此不承担任何责任，您将承担因此产生的任何直接或间接损失及不利后果。<br/>
b）您应当准确填写并及时更新您提供的电子邮件地址、联系电话、联系地址、邮政编码等联系方式，以便大袜网或其他用户与您进行有效联系，因通过这些联系方式无法与您取得联系，导致您在使用大袜网平台服务过程中产生任何损失或增加费用的，应由您完全独自承担。您了解并同意，您有义务保持你提供的联系方式的真实性和有效性，如有变更或需要更新的，您应按大袜网的要求进行操作。<br/>
 4、账户安全  <br/>
  您须自行负责对您的大袜网账号和密码保密，且须对您在该登录名、大袜网昵称和密码下发生的所有活动（包括但不限于信息披露、发布信息、网上点击同意或提交各类规则协议、网上续签协议或购买服务等）承担责任。您同意：
(a) 如发现任何人未经授权使用您的大袜网登录名、大袜网昵称和密码，或发生违反保密规定的任何其他情况，您会立即通知大袜网，并授权大袜网将该信息同步给大袜网站；及<br/>
(b) 确保您在每个上网时段结束时，以正确步骤离开网站/服务。大袜网不能也不会对因您未能遵守本款规定而发生的任何损失负责。您理解大袜网对您的请求采取行动需要合理时间，大袜网对在采取行动前已经产生的后果（包括但不限于您的任何损失）不承担任何责任。提高（降低）其他会员的信用度。<br/>
5、用户登录名注销<br/>
 a）如您连续12个月未使用您的邮箱、手机或大袜网认可的其他方式和密码登录过大袜网平台，也未登录过其他任意大袜网站，且您的账户下不存在未到期的有效业务，您的大袜网登录名可能被注销，不能再登录任意一家大袜网站，所有大袜网站服务同时终止。<br/>
 b）您同意并授权大袜网站，您如在任意大袜网站有欺诈、发布或销售假冒伪劣/侵权商品、侵犯他人合法权益或其他违反法律法规、大袜网站规则等行为，该网站在大袜网站的范围内对此有权披露，您的大袜网登录名可能被注销、不能再登录任意一家大袜网站，所有大袜网站服务同时终止。 <br/>
四、大袜网规则<br/>
大袜网平台服务<br/>
  1、通过大袜网及其关联公司提供的大袜网平台服务和其它服务，会员可在大袜网平台上查询商品和服务信息、达成交易意向并进行交易、参加大袜网组织的活动以及使用其它信息服务及技术服务，具体以所开通的平台提供的服务内容为准。
  2、您在大袜网平台上交易过程中与其他会员发生交易纠纷时，一旦您或其它会员任一方或双方共同提交大袜网要求调处，则大袜网作为独立第三方，有权根据单方判断做出调处决定，您了解并同意接受大袜网的判断和调处决定。 
  3、您了解并同意，大袜网有权应政府部门（包括司法及行政部门）的要求，向其提供您向大袜网提供的用户信息和交易记录等必要信息。如您涉嫌侵犯他人知识产权等合法权益，则大袜网亦有权在初步判断涉嫌侵权行为存在的情况下，向权利人提供您必要的身份信息<br/>。
 4、您在使用大袜网平台服务过程中，所产生的应纳税赋，以及一切硬件、软件、服务及其它方面的费用，均由您独自承担。<br/>
 5、您了解并同意其他所有大袜网已经发布或将来可能发布的各类规则、公告或通知。<br/>
五、法律申明<br/>
大袜网平台服务使用规范 <br/>
1、在大袜网平台上使用大袜网服务过程中，您承诺遵守以下约定：<br/> 
a)实施的所有行为均遵守国家法律、法规等规范性文件及大袜网平台各项规则的规定和要求，不违背社会公共利益或公共道德，不损害他人的合法权益，不偷逃应缴税费，不违反本协议及相关规则。<br/>
b)在与其他会员交易过程中，遵守诚实信用原则，不采取不正当竞争行为，不扰乱网上交易的正常秩序，不从事与网上交易无关的行为。 <br/>
c)不发布国家禁止销售的或限制销售的商品或服务信息（除非取得合法且足够的许可），不发布涉嫌侵犯他人知识产权或其它合法权益的商品或服务信息，不发布违背社会公共利益或公共道德或大袜网认为不适合在大袜网平台上销售的商品或服务信息，不发布其它涉嫌违法或违反本协议及各类规则的信息<br/>。 
d)不以虚构或歪曲事实的方式不当评价其他会员，不采取不正当方式制造或提高自身的信用度，不采取不正当方式制造或提高（降低）其他会员的信用度。<br/>
e)不对大袜网上的任何数据作商业性利用，包括但不限于在未经大袜网事先书面同意的情况下，以复制、传播等任何方式使用大袜网站上展示的资料。<br/>
f)不使用任何装置、软件或例行程序干预或试图干预大袜网的正常运作或正在大袜网上进行的任何交易、活动。您不得采取任何将导致不合理的庞大数据负载加诸大袜网网络设备的行动。<br/>
 2.您了解并同意<br/>
 a)大袜网有权对您是否违反上述承诺做出单方认定，并根据单方认定结果适用规则予以处理或终止向您提供服务，且无须征得您的同意或提前通知予您。<br/>
 b)经国家行政或司法机关的生效法律文书确认您存在违法或侵权行为，或者大袜网根据自身的判断，认为您的行为涉嫌违反本协议和/或规则的条款或涉嫌违反法律法规的规定的，则大袜网有权在大袜网上公示您的该等涉嫌违法或违约行为及大袜网已对您采取的措施。<br/>
c)对于您在大袜网上发布的涉嫌违法或涉嫌侵犯他人合法权利或违反本协议和/或规则的信息，大袜网有权不经通知您即予以删除，且按照规则的规定进行处罚<br/>。 
d)对于您在大袜网上实施的行为，包括您未在大袜网上实施但已经对大袜网及其用户产生影响的行为，大袜网有权单方认定您行为的性质及是否构成对本协议和/<br/>或规则的违反，并据此作出相应处罚。您应自行保存与您行为有关的全部证据，并应对无法提供充要证据而承担的不利后果。 
e)对于您涉嫌违反承诺的行为对任意第三方造成损害的，您均应当以自己的名义独立承担所有的法律责任，并应确保大袜网免于因此产生损失或增加费用。<br/>
 f)如您涉嫌违反有关法律或者本协议之规定，使大袜网遭受任何损失，或受到任何第三方的索赔，或受到任何行政管理部门的处罚，您应当赔偿大袜网因此造成的损失及（或）发生的费用，包括合理的律师费用。<br/><br/><br/>
	
		<span>委托支付三方协议</span><br/>
本协议由注册用户与小麦（浙江）网络科技有限公司、平台商家共同缔结，本协议无论作为合同附件或单独使用都具有合同效力。
本协议中协议三方合称协议方，注册用户（以下简称甲方），小麦（浙江）网络科技有限公司（以下简称乙方）、平台供应商（以下简称丙方）。<br/><br/>

鉴于：
甲方与丙方的销售订单发生的交易金额，甲方同意委托乙方向丙方代付金额款项。
甲方需要乙方为其提供付费代理服务。甲方同意将每次的交易金额全额汇入乙方结算账户，由乙方代为向丙方支付。经甲乙丙三方平等协商达成本协议，以共同信守：
第一条 义务范围、方式及要求<br/>
1.1甲方委托乙方代理付费项目为甲方与丙方销售订单中的项目款项。<br/>
1.2除上述费用外，乙方不得因委托向丙方支付其他费用。<br/>
1.3乙方每月按丙方商品在乙方平台上的成交金额，由乙方从结算账户中转账，划入丙方账户。<br/>
1.4甲乙丙三方同意通过乙方统一代付费平台，每月按时、足额支付销售订单款项，并及时结算、统一管理。<br/>
1.5甲乙丙三方应当建立正常的工作联系制度，定期协同分析双方业务中的存在的问题，保证三方工作渠道畅通。<br/>
第二条 手续费及管理<br/>
2.1甲方无需支付乙方任何代支付费用。<br/>
2.2代付款等相应票据由乙方统一管理，但甲方有权调取核实。 <br/>                                                                                                                                                                                                                                                                                                 
2.3 甲乙双方必须保证账务、收据、金额相符。<br/>
2.4因乙方原因给甲方造成损失，由乙方承担责任并负责妥善处理。由甲方过错造成的损失，由甲方承担。<br/>
第三条 甲方的权利和义务<br/>
3.1甲方对乙方授权为不可撤销授权<br/>。 
3.2甲方同意将支付给丙方的订单交易金额汇入乙方的结算账户，由乙方在约定付款日统一支付给丙方。<br/>
3.3甲方应按丙方在乙方交易平台上的交易金额进行结算。<br/>
3.5甲方有权对乙方支付费用进行核实，乙方应予以配合。<br/>
3.6非乙方原因产生的丙方与甲方的争议由甲方负责处理。<br/>
第四条 乙方的权利与义务<br/>
4.1乙方保证按时将甲丙发生的订单交易金额支付给丙方。<br/>
4.2乙方每月转账丙方的金额为乙方提供的与丙方核对确认的结算单货款金额。<br/>
4.3乙方有义务为甲方、丙方提供付费情况、账户余额，配合甲丙方相应工作。<br/>
4.4乙方应严格遵守操作规章制度,因乙方原因造成甲丙方损失的，由乙方负责赔偿。<br/>
4.5乙方应确保甲方有关业务的保密工作，不得向任何单位（法律规定的除外）或个人泄露，否则引起的不良后果和经济损失由乙方承担；若是非乙方原因导致泄密，则乙方不承担相关责任<br/>。
4.6若甲方满足要求申请成为乙方的月结客户，乙方为甲方及时代付了丙方的货款，而甲方没有根据月结客户需签订的《结算协议》及时向乙方支付其代付的金额，则乙方有权向甲方主张债权，并且终止对甲方的欠款额度。<br/>
4.7乙方有权根据甲方与丙方的具体订单信息，生成有订单信息显示的《委托支付三方协议》，甲方与丙方对此无异议。<br/>
4.8除约定的付款责任外，乙方不承担其他责任。<br/>
第五条 丙方的权利义务<br/>
5.1丙方有权要求乙方支付每月款项，在乙方未依照约定支付时，也有权要求甲方支付；丙方书面通知甲方，甲方也可作出书面情况说明，获得丙方许可后，甲方可以补缴或缓缴款项，甲方也可以委托由乙方代缴，由乙方代缴的，甲方需先将对应款项汇至乙方账户。<br/>
第六条 保密<br/>
6.1未经对方书面许可，任何一方不得向第三方提供、披露与对方业务有关的资料和信息，但法律另有规定的除外。<br/>
第七条 权利义务的转让<br/>
7.1任何一方未经对方书面同意，不得转让本协议下的任何一项权利和义务。<br/>
第八条 违约责任<br/>
8.1任何一方未履行本协议下的任何一项义务均视为违约。任何一方在收到对方的具体说明违约情况的书面通知后，如确认违约行为实际存在，则应对违约行为予以纠正并书面通知对方；如认为违约行为不存在，则应向对方提出书面异议说明。在此情形下，甲、乙、丙三方应就此问题进行协商，协商不成的，按本协议争议条款解决。违约方应承担因自己的违约行为而给守约方造成的直接经济损失。<br/>
8.2非因甲方原因，乙方未按协议规定将款项划拨给丙方，甲方有权以书面的形式要求乙方将款项划入丙方接受汇款账户。乙方不予履行的，甲方有权终止本协议，由此造成的损失，由违约方承担。<br/>
第九条 免责条款<br/>
9.1因不可抗力导致甲乙双方或一方不能履行或不能完全履行本协议项下有关义务时，甲乙双方相互不承担违约责任。但遇有不可抗力的一方或双方应于不可抗力发生后5天内将情况以书面形式告知对方，并提供有关部门的证明。在不可抗力影响消除后的合理时间内，一方或双方应当继续履行合同。<br/>
第十条 通知<br/>
10.1依照本协议要求，任何一方发出的通知或其他联系应以中文书写，通知可以专人递交，或以挂号信件，或以公认的快递服务或图文传真发送到另一方，通知视为有效送达的日期应按下述方法确定：<br/>
（1）专人递交的通知在专人递交之日视为送达；<br/>
（2）以挂号信件发出的通知应在寄出日（以邮戳为凭）后第10日视为已送达；<br/>
（3）以图文传真发送通知在成功传送和接受日后的第1个工作日视为已送达。<br/>
第十一条 争议和解决<br/>
11.1对于因本协议履行而发生的争议，双方可协商解决，协商不成的向合同签订地所在法院提起诉讼。<br/>
第十二条 其它<br/>
12.1三方均为依法成立并合法存在的企业法人或金融机构，有权以自身名义，权利和权限从事本合同项下的业务经营活动，并以自身名义签署和履行本协议。<br/>
12.2如果本协议的任何条款在任何时间变成不合法、无效或不可执行时，本协议其他条款的效力不受影响。<br/>
12.3未经三方书面确认,任何一方不得变更或修改本协议。<br/>
12.4对于本协议未尽事宜,三方可签订补充协议或以附件的形式对本协议中的有关问题作出补充、说明、解释，本协议的补充协议和附件为本协议的组成部分，与本协议具有同等法律效力。<br/>
第十三条 协议期限<br/>
13.1用户注册前，请认真阅读本协议，一经注册，即视为知悉并接受本协议；如不同意，请勿注册。<br/>
13.2乙方和丙方自始知悉并接受此协议。<br/>
13.3本协议长期有效。<br/>

		</p>		

					</div>
				<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/fwc/signup_bg.jpg" height="600" width="1920" alt="">
				</div>
			</div>
			<div class="sigin-side">
					
					<div class="insigin-side">
							<p>验证后可重置密码</p>
						</div>
					<div class="sigin-enter">
					<div class=" top-button name">
						<button id="caigou">我是采购商</button>
						<button id="gongying">我是供应商</button>
					</div>
					<!-- 提交form表单开始 -->
					<form class="form-horizontal forms-group" id='form' method="POST">
						<input type="hidden" name="saleCode" value="${saleCode}"  id="saleCode"/>
						<input type="hidden" name="inviterId" value="${inviterId}"  id="inviterId"/>
						<input type="hidden"  id="memberType" name='memberType' value="2" >
						<!-- end -->
						
						
						<div class="name">
							<span >手机号</span>
						<input type="text" id="userName" name='name' placeholder="请输入手机号码">
						</div>
						<div class="verify name">
							<span style="float:left;">验证码</span>
							<input type="text" id="verifyCode" name="verifyCode" placeholder="请输入验证码">
						<img src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html" id="code_img" onclick="refreshCode();" style="cursor: pointer;" height="27" width="73" alt="">
							
						</div>
						<div class="password name">
							<span >短信验证</span>
						<input type="text" id="smsCode" name="smsCode"  placeholder="请输入手机验证码">
						<a href="javascript:void(0);" class="huoqu" onclick="getmobVerify(this)">获取验证码</a>
						</div>
						<div class="password name">
							<span >设置密码</span>
						<input type="password" id="password" name='password' placeholder="请输入6~20位的字母和数字组合">
						
						</div>
						<div class="password name">
							<span >确认密码</span>
						<input type="password" id="repassword" name='repassword' placeholder="请再次输入密码">
						
						</div>
						<div class="forget-password">
							<div style="float:left"><label><input type="checkbox" style="vertical-align:middle; margin-top:-2px;" checked="checked" name="acceptProtocol" id="acceptProtocol" />&nbsp;我已阅读并同意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></div>
							<div style="float:left">《大袜网平台用户服务协议》《委托付款三方协议》</div>
						</div>
						<a href="javasrcipt:;" class="chongzhi" id="signupButton" >立即注册</a>
						<div class="forget-password">
							<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/login.html" class="forget-left">已有账号，立即登录</a>
							
						</div>
						</form>
					</div>
					
			</div>

						<script type="text/javascript">
								var leftbgc=document.getElementById('caigou')
								var rightbgc=document.getElementById('gongying')

								rightbgc.onclick=function  (argument) {
									//供应商memberType为1
									$("#memberType").val(1);
									rightbgc.style.backgroundColor='#00a0e9';
									rightbgc.style.color='#fff';

									leftbgc.style.backgroundColor='#fff';
									leftbgc.style.color='#000';
								}
									leftbgc.onclick=function  (argument) {
									//采购商memberType为2
									$("#memberType").val(2);
									leftbgc.style.backgroundColor='#00a0e9';
									leftbgc.style.color='#fff';

									rightbgc.style.backgroundColor='#fff';
									rightbgc.style.color='#000';

								}
									
								//获取验证码校验	
								function getmobVerify(this_){
									var obj = $(this_);
									var validator = $("#form").validate();
									var userName = validator.element("#userName");
									var verifyCode = validator.element("#verifyCode");
									var textBool = obj.text().indexOf("获取验证码")>=0;
									var mob = $("#userName").val();
									var verifycode = $("#verifyCode").val();
									if(userName && verifyCode && textBool){
										$.ajax({
											type : 'get',
											url : domain + '/sendVerifySMS.html?mob=' + mob
													+ '&verifycode=' + verifycode,
											success : function(e) {
												if (e.success) {
													var time = 120;
													obj.text(time + "秒");
													time--;
													intervalId = setInterval(function() {
														obj.text(time + "秒");
														if (time == 0) {
															clearInterval(intervalId);
															obj.text("获取验证码");
														}
														time--;
													}, 1000);
												} else {
													jAlert(e.message);
												}
											}
										});
									}
								}	

						</script>

		<#include "/front/commons/_endbig.ftl" />