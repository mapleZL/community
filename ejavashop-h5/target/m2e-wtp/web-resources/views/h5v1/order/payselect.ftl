<#include "/h5v1/commons/_head.ftl" />
<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
.on{border: 1px solid #ff9c00;background: url(${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_pay_per.png) 95% center no-repeat #fff; background-size: 20px;}
.cur{background: #ff7e18;box-shadow: 0 3px 2px 0 #e96b08;}
</style>
<body style="background:#f8f8f8">
<!--头部-->
<header data-am-widget="header" class="am-header am-header-default tp2">
  <div class="am-header-left am-header-nav">
      <a href="javascript:void(0);">
          <img class="am-header-icon-custom" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/drop.png" alt=""/>
      </a>
  </div>

  <h1 class="am-header-title">支付方式</h1>
</header>
<!--头部end-->

<!--内容-->
<div class="clear"></div>
<@form.form action="" id="payForm" name="payForm" method="GET">
<input type="hidden" name="paySessionstr" value="${paySessionstr!''}">
<input type="hidden" name="relationOrderSn" id="relationOrderSn" value="${relationOrderSn!''}">
<input type="hidden" name="fromType" id="fromType" value="${fromType!''}">
<input type="hidden" name="localIp" id="localIp"/>
<input type="hidden" name="optionsRadios" id="optionsRadios" value=""/>
<input type="hidden" name="selectOrderBalance" id="selectOrderBalance" value=""/>
<input type="hidden" name="memberBalance" id="memberBalance" value="${member.balance}"/>
<input type="hidden" name="sumPayPriceHidden" id="sumPayPriceHidden" value="${(payAmount)?string('0.00')!'' }"/>
<input type="hidden" name="memberCredit" id="memberCredit" <#if memberCredit?? && memberCredit.surplus &gt;0 >value="${memberCredit.surplus}"</#if>/>
<!-- <input type="hidden" name="payType"  value=""> --><!-- 支付方式 -->
<article>
	<div class="mt10 ico public order_success">
    	<h3 class="f16">订单提交成功，请您尽快打款！</h3>
        <p class="em3">请您在3小时内完成支付，否则订单将自动取消。</p>
        <p>订单号：<em class="em4">${relationOrderSn}</em></p>
        <p>订单金额：<em class="em4">¥ ${(payAmount)?string('0.00')!'' }元</em></p>
        <p id="keleyivisitorip"  style="display:none;"></p>
    </div>
    <h3 class="wap em2 pay_title">选择支付方式</h3>
    <div class="pay_way" id="pay">
    	<a href="#" class="pay_a"  data-payway="alipay">
        	<dl>
            	<dt><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/pay_ali.png"></dt>
                <dd>支付宝</dd>
            </dl>
        </a>
        <a href="#" class="pay_a weixin_pay" data-payway="wxpay" style="display:none">
        	<dl>
            	<dt><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/pay_wechat.png"></dt>
                <dd>微信支付</dd>
            </dl>
        </a>
        <#--<a href="#" class="pay_a" data-payway="chinapay">
        	<dl>
            	<dt><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/pay_up.png"></dt>
                <dd>银行卡支付</dd>
            </dl>
        </a>-->
        <#if memberCredit?? && memberCredit.surplus &gt;0 >
        <a href="#" class="pay_a main_nav" data-payway="balance">
        	<dl>
            	<dt><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/pay_ye.png"></dt>
                <dd>平台余额（当前余额${member.balance}元）</dd>
            </dl>
        </a>
        </#if>
    </div>
  	<div class="mt30 swap">
    	<a class="stop1 radius public3 confirm-pay">确认支付</a>
    </div>
</article>
<!--内容end-->  
<#include "/h5v1/commons/_footer.ftl" />
<#include "/h5v1/commons/_statistic.ftl" />
<!--支付弹框-->
<div class="cd-user-modal"> 
    <div class="cd-user-modal-container swap">
        <div class="overflow" style="border-bottom:1px solid #ff7e18; padding:10px">
            <h3 class="fl">请输入支付密码</h3>
            <a class="cd-close-form fr"><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/cha.png"></a>
        </div>
        <div class="pumps1">
            <p>订单金额</p>
            <em>¥${(payAmount)?string('0.00')!'' }</em>
            <#if memberCredit?? && memberCredit.surplus &gt;0 >
            <p class="tp2 tp4 platform">
            	<i><img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/pay_ye.png"></i>平台余额（当前余额${member.balance}元)<br/>
            	<span class="after_balance" style='color: red;'>您的账户可赊账<b>${memberCredit.surplus}</b>元，使用余额支付后，您的账户会产生欠款，请在规定时间内还清欠款。</span>
            </p>
            </#if>
            <div class="overflow pwd_list">
               <input type="password" name="balancePassword" id="balancePassword" class="m_text fl">
            </div>
        </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn cd-close-form balance-pay">确定</span>
	    </div>
    </div>
</div>
</@form.form>
<!--end-->
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/paySelect.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
</html>
