<#include "/h5v1/commons/_head.ftl" />
<head>
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
</style>
</head>
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default header">
  <div class="am-header-left am-header-nav">
      <a href="javascript:history.go(-1);" class="">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">签订入住协议</h1>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<article>
	<div class="mt10 deal ico public">
    	<h3 class="em2 f16">使用本公司服务所须遵守的条款和条件。</h3>
        <dl class="mt5">
        	<dt class="em2">1.用户资格：</dt>
            <dd>
            	<p>本公司的服务仅向适用法律下能够签订具有法律约束力的合同的个人提供并仅由其使用。在不限制前述规定的前提下，本公司的服务不向18周岁以下或被临时或无限期中止的用户提供。如您不合资格，请勿使用本公司的服</p>
            </dd>
        </dl>
        <dl class="mt5">
        	<dt class="em2">2.您的资料（包括但不限于所添加的任何商品）不得：</dt>
            <dd>
            	<p>（1）具有欺诈性、虚假、不准确或具误导性；</p>
                <p>（2）侵犯任何第三方著作权、专利权、商标权、商业秘密或其他专有权利或发表权或隐私权；</p>
                <p>（3）违反任何适用的法律或法规（包括但不限于有关出口管制、消费者保护、不正当竞争、刑法、反歧视或贸易惯例/公平贸易法律的法律或法规）；</p>
                <p>（4）有侮辱或者诽谤他人，侵害他人合法权益的内容；</p>
                <p>（5）有淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的内容；</p>
                <p>（6）包含可能破坏、改变、删除、不利影响、秘密截取、未经授权而接触或征用任何系统、数据或个人资料的任何病毒、特洛依木马、蠕虫、定时炸弹、删除蝇、复活节彩蛋、间谍软件或其他电脑程序；</p>
            </dd>
        </dl>
        <dl class="mt5">
        	<dt class="em2">3.违约：</dt>
            <dd>
            	<p>如发生以下情形，本公司可能限制您的活动、立即删除您的商品、向本公司社区发出有关您的行为的警告、发出警告通知、暂时中止、无限期地中止或终止您的用户资格及拒绝向您提供服务。</p>
                
            </dd>
        </dl>
        <dl class="mt5">
        	<dt class="em2">4.责任限制：</dt>
            <dd>
            	<p>本公司、本公司的关联公司和相关实体或本公司的供应商在任何情况下均不就因本公司的网站、本公司的服务或本协议而产生或与之有关的利润损失或任何特别、间接或后果性的损害（无论以何种方式产生，包括疏忽）承</p>
                
            </dd>
        </dl>
        <dl class="mt5">
        	<dt class="em2">5.无代理关系：</dt>
            <dd>
            	<p>用户和本公司是独立的合同方，本协议无意建立也没有创立任何代理、合伙、合营、雇员与雇主或特许经营关系。本公司也不对任何用户及其网上交易行为做出明示或默许的推荐、承诺或担保。</p>
                
            </dd>
        </dl>
        <dl class="mt5">
        	<dt class="em2">6.一般规定：</dt>
            <dd>
            	<p>本协议在所有方面均受中华人民共和国法律管辖。本协议的规定是可分割的，如本协议任何规定被裁定为无效或不可执行，该规定可被删除而其余条款应予以执行。</p>
                
            </dd>
        </dl>
    </div>
    <a class="stop1 public3">同意协议，下一步</a>
</article>
<!--内容end-->   
<#include "/h5v1/commons/_footer.ftl" />
<#include "/h5v1/commons/_statistic.ftl" />
<script>
	$(function() {
	
		$(".public3").click(function() {
			window.location.href = domain + "/storeregister/info.html";
		});
		
	});

</script> 
</body>

