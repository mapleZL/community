<#import "/h5/commons/_macro_controller.ftl" as cont/>
<#include "/h5/commons/_head.ftl" />
<!--  -->
<link rel="stylesheet" href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/css/fwc/frozen.css">
    <body ontouchstart="">
	   <!-- 头部 -->
	   <header id="header">
	   	  <div class="flex flex-align-center head-bar">
	   	  	 <div class="flex-1 text-left">
	   	  	 	<a href="javascript:history.back();">
	   	  	 		<span class="fa fa-angle-left"></span>
	   	  	 	</a>
			 </div>
	   	  	 <div class="flex-2 text-center">个人资料</div>
	   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
	   	  </div>
	   	  <#include "/h5/commons/_hidden_menu.ftl" />
	   </header>
	   <!-- 头部 end-->
       <div class="ui-form ui-border-t">
    <form id='personalfileForm'>
    <div class="ui-form-item ui-form-item-l ui-border-b ">
            <label >
               用户名
            </label>
            <input type="text" id="name" name="name" readonly="readonly" value="${(member.name)!''}" placeholder="请输入手机号码">
            
        </div>
        <div class="ui-form-item ui-form-item-l ui-border-b ui-arrowlink">
            <label >
               真实姓名
            </label>
            <input type="text" id="realName" name="realName" value="${(member.realName)!''}" placeholder="必填">
        </div>
       <div class="ui-form-item ui-form-item-l ui-border-b ui-arrowlink">
            <label >
                手机号
            </label>
            <input type="text" id="mobile" name="mobile" value="${(member.mobile)!''}" placeholder="">
           
        </div>
         <div class="ui-form-item ui-form-item-l ui-border-b ui-arrowlink">
           <div class="ui-form ">
        <div class=" ui-form-item-radio ui-border-b" style="float:left; ">
            <label class="ui-radio" for="radio">
           		 性别
                <input type="radio"   name="gender" value="1" style="margin-left:40px;outline:none;" <#if member.gender?? && member.gender==1>checked</#if> > 
            </label>
            <span>男</span>
        </div>
        <div class="ui-form-item ui-form-item-radio ui-border-b" style="float:left;">
            
            <label class="ui-radio" for="radio">
                <input type="radio"  name="gender" value="2" style="outline:none;" <#if member.gender?? && member.gender==2>checked</#if> >
            </label>
            <span>女</span>
        </div>
</div>
           
</div>

        <div class="ui-form-item ui-form-item-l ui-border-b ui-arrowlink">
            <label >
                qq
            </label>
            <input type="text" id="qq" name="qq" value="${(member.qq)!''}" placeholder="">
           
        </div>
            <div class="ui-form-item ui-form-item-l ui-border-b ui-arrowlink">
            <label >
               微信
            </label>
            <input type="text" id="wechatNum" name="wechatNum" value="${(member.wechatNum)!''}" placeholder="">
           
        </div>
            <div class="ui-form-item ui-form-item-l ui-border-b ui-arrowlink">
            <label >
                邮箱
            </label>
            <input type="text" id="email" name="email" value="${(member.email)!''}"  placeholder="">
           
        </div>
			<div class="ui-btn-wrap">
    <button class="ui-btn-lg ui-btn-primary" id="personSubmit" style="margin-top:70px;">
       	提交
    </button>
    </form>
</div>
<!-- footer -->
	<#include "/h5/commons/_footer.ftl" />
	<#include "/h5/commons/_statistic.ftl" />

<script type="text/javascript">
 
	$("#personSubmit").click(function(){
		$("#personSubmit").attr("disabled","disabled");
		var realNameVal = $("#realName").val();
		if(realNameVal==""){
			$.dialog('alert','提示',"真实姓名不能为空",2000,function(){ $("#personSubmit").removeAttr("disabled"); });
			return;
		}
		var params = $('#personalfileForm').serialize();
		$.ajax({
			type:"POST",
			url:domain+"/member/saveinfo.html",
			dataType:"json",
			async : false,
			data : params,
			success:function(data){
				if(data.success){
					// alert("保存成功");
					//重新加载数据
					// window.location.href=domain+"/member/info.html";
					$.dialog('alert','提示','保存成功',2000,function(){ window.location.href=domain+"/member/info.html"; });
				}else{
					// alert(data.message);
					// $("#personSubmit").removeAttr("disabled");
					$.dialog('alert','提示',data.message,2000,function(){ $("#personSubmit").removeAttr("disabled"); });
				}
			}
		});
	});
</script>
