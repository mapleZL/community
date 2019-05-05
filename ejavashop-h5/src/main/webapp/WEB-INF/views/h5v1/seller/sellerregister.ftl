<#include "/h5v1/commons/_head.ftl" />
<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
<head>
<style>
.am-header .am-header-title{margin:0 10%; text-align:left}
.am-form-group{margin-bottom:0}
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

  <h1 class="am-header-title">公司资质信息</h1>
</header>
<div class="divcons"></div>
<!--头部end-->

<!--内容-->
<div class="clear"></div>

<input type="hidden" id="id" name="id" value="${(sellerApply.id)!''}">
<input type="hidden" id="state" name="state" value="${(sellerApply.state)!''}">
<input type="hidden" id="message" name="message" value="${message!''}">
<@form.form action="${(domainUrlUtil.EJS_URL_RESOURCES)!}/storeregister/doregister.html" id="registerForm" name="registerForm" method="post" enctype="multipart/form-data">
<article>
	<ul class="wallet mt10 ico">
    	<li><label class="fl">公司名称</label><input type="text" placeholder="（必填)" id="company" name="company" value="${(sellerApply.company)!''}" class="em0 text_01"></li>
        <li><a class="wallet_a"><label class="fl">地址</label><span class="fr em0" style="padding-right:2rem">
        	<div class="content-block fr em0" style="width:100%; text-align:right">
                    <input id="demo1" type="text" readonly value="" class="text4"/>
                    <input id="value1" type="hidden" value=""/> 
            </div>
            </span>
		</a></li>
        <li><label class="fl">详细地址</label><textarea class="textare2" id="companyAdd" name="companyAdd" value="${(sellerApply.companyAdd)!''}" placeholder="（必填)"></textarea></li>
        <li><label class="fl">公司电话</label><input type="number" id="telephone" name="telephone" value="${(sellerApply.telephone)!''}" placeholder="（必填)" class="em0 text_01"></li>
        <li><label class="fl">传真</label><input type="number" id="fax" name="fax" value="${(sellerApply.fax)!''}" placeholder="（必填)" class="em0 text_01"></li>
        <li><label class="fl">法定代表人</label><input type="text" id="legalPerson" name="legalPerson" value="${(sellerApply.legalPerson)!''}" placeholder="（必填)" class="em0 text_01"></li>
        <li><label class="fl">联系人电话</label><input type="number" id="personPhone" name="personPhone" value="${(sellerApply.personPhone)!''}" placeholder="（必填)" class="em0 text_01"></li>
        <li><label class="fl">邮箱</label><input type="text" id="email" name="email" value="${(sellerApply.email)!''}" class="em0 text_01"></li>
        <li><label class="fl">机型及台数</label><input type="number" id="engineSum" name="engineSum" value="${(sellerApply.engineSum)!''}" placeholder="请填写拥有机型和台数信息" class="em0 text_01"></li>
        <li><label class="fl">日产量</label><input type="number" id="madePerDay" name="madePerDay" value="${(sellerApply.madePerDay)!''}" placeholder="请填写相关信息" class="em0 text_01"></li>
        <li><label class="fl">员工人数</label><input type="number" id="employeeSum" name="employeeSum" value="${(sellerApply.employeeSum)!''}" placeholder="请填写相关信息" class="em0 text_01"></li>
        <li><label class="fl">现有品类</label><input type="text" id="ownCate" name="ownCate" value="${(sellerApply.ownCate)!''}" placeholder="请填写相关信息" class="em0 text_01"></li>
        <li><label class="fl">擅长领域</label><input type="text" id="wellArea" name="wellArea" value="${(sellerApply.wellArea)!''}" placeholder="请填写相关信息" class="em0 text_01"></li>
        <li>
        	<div class="fl">
            	<p class="mt5">营业执照</p>
                <p class="em0">（当前未选择任何图片文件）</p>
            </div>
            <div class="am-form-group am-form-file img1 fr">
                <button type="button" class="am-btn am-btn-default am-btn-sm" style="padding:0">
                    <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_select_file.png" width="100%" >
                </button>
                <input type="file" class="form-control" id="up_bussinessLicenseImage" name="up_bussinessLicenseImage" multiple>
            	<input type="hidden" id="bussinessLicenseImage" name="bussinessLicenseImage" value="${(sellerApply.bussinessLicenseImage)!''}">
            </div>
        </li>
        <li>
        	<div class="fl">
            	<p class="mt5">组织机构代码</p>
                <p class="em0">（当前未选择任何图片文件）</p>
            </div>
            <div class="am-form-group am-form-file img1 fr">
            	<button type="button" class="am-btn am-btn-default am-btn-sm" style="padding:0">
                    <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_select_file.png" width="100%" >
                </button>
                <input type="file" id="up_organization" name="up_organization" multiple>
            	<input type="hidden" id="organization" name="organization" value="${(sellerApply.organization)!''}">
            </div>
        </li>
        <li>
        	<div class="fl">
            	<p class="mt5">税务登记证</p>
                <p class="em0">（当前未选择任何图片文件）</p>
            </div>
            <div class="am-form-group am-form-file img1 fr">
                <button type="button" class="am-btn am-btn-default am-btn-sm" style="padding:0">
                    <img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!''}/img/btn_select_file.png" width="100%" >
                </button>
                <input type="file" id="up_taxLicense" name="up_taxLicense" multiple>
            	<input type="hidden" id="taxLicense" name="taxLicense" value="${(sellerApply.taxLicense)!''}">
            </div>
        </li>
    </ul>
     <a href="javascript:void(0)" class="stop1 public3">提交</a>
</article>
</@form.form>
<!--内容end-->    
<#include "/h5v1/commons/_footer.ftl" />
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/sellerRegister.js"></script>
<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js1/LArea.css"><!--三级联动-->
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js1/LAreaData1.js"></script><!--三级联动-->
<script src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/js1/LArea.js"></script><!--三级联动-->
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/bi/member/address.js"></script>
<#include "/h5v1/commons/_statistic.ftl" />
</body>
