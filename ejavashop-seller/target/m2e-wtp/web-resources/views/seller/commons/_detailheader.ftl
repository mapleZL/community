<#import "/seller/commons/_macro_controller.ftl" as cont/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	  <title>大袜网商家管理系统</title>
	  <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
	  
	  <script>
	  	var imgdomain = "${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}";
	  </script>
	  <#--
	  <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/jquery.min.js"></script>
	  -->
	  <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery-1.8.2.min.js"></script>
	  <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/jquery.easyui.min.js"></script>
	  <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/locale/easyui-lang-zh_CN.js"></script>
	  <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/func.js"></script>
      <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.form.js"></script>


      <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.ui.widget.js"></script>
      <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.iframe-transport.js"></script>
      <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.fileupload.js"></script>
      <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.filedownload.js"></script>
      <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery.multifile.upload.js"></script>
      <script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>

	  <link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/themes/default/easyui.css" type="text/css"></link>
	  <link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/themes/icon.css" type="text/css"></link>
	  <link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/css/style.css" type="text/css"></link>
  </head>
	<body class="easyui-layout" style="overflow-y:scroll;">
	<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>