<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>社区管理系统平台端</title>
	
<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/themes/grinder/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/css/login.css" type="text/css"></link>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
	//刷新验证码
	function refreshCode(this_) {
		this_.src = "${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html?d=" 
				+ new Date().getTime();
	}
	
	function submitform(){
		if($("#loginform").form("validate")){
			$("#loginform").submit();
		}
	}
	
	$(function(){
		document.onkeydown = keyDownSubmit;
		function keyDownSubmit(e){
			var theEvent = e || window.event;
			var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
			if(code == 13){
				$("#loginbtn").click();
			}
		}
	});
</script>
</head>
<body>
  	<h2>社区管理系统平台端</h2>
	 <div class="formbox">	
          <h3>用户登录</h3>
          <form action="${domainUrlUtil.EJS_URL_RESOURCES}/admin/doLogin" 
          		method="post" id="loginform">
          	   <div class="inputbox">	
	          	    <label for="">用户名</label>
	          	    <input type="text" name="name" id="name" 
	          	    	class="txt w200 easyui-validatebox"
	          	    	 missingMessage="请输入用户名" 
	          	    	 data-options="required:true">
          	   </div>
          	  
          	   <div class="inputbox">	
	          	    <label for="">密码</label>
	          	    <input type="password" type="password" 
	          	    	name="password" id="pwd"
	          	    	class="easyui-validatebox"
	          	    	missingMessage="请输入密码" 
	          	    	data-options="required:true">
          	   </div>
          	  
          	   <div class="inputbox">	
	          	    <label>验证码</label>
	          	    <input type="text" name="verifyCode" style="width:116px;"
	          	   		class="easyui-validatebox"
	          	    	missingMessage="请输入验证码" 
	          	    	data-options="required:true,validType:'length[4,4]',novalidate:true,tipPosition:'left'">
	          	    <img alt="验证码"
						style="cursor: pointer;vertical-align: top;height: 22px;margin: 0px 20px;"
						src="${(domainUrlUtil.EJS_URL_RESOURCES)!}/verify.html"
						id="code_img" onclick="refreshCode(this);" />
          	   </div>
          	   
          	   <div class="inputbox">	
          	   	<label for=""></label>
          	   	<font color="red">${(message)!}</font>
          	   </div>
          	   
          	   <div class="inputbox">	
	          	    <label for="">&nbsp;</label>
	          	    <input type="button" onclick="submitform()" id="loginbtn" value="登录">
          	   </div>
          </form>
	 </div>
	 <div class="bottom"> 版权所有 浙江仆汇科技有限公司.保留一切权利 </div>
</body>
</html>