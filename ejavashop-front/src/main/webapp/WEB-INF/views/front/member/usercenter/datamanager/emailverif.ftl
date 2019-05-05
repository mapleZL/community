<#include "/front/commons/_headbig.ftl" />
		<div class='container w'>
			<div class='breadcrumb'>
				<strong class='business-strong'>
					<a href='${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html'>首页</a>
				</strong>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>大袜网</a>
				</span>
				<span>
					&nbsp;>&nbsp;
					<a href='javascript:void(0)'>邮箱绑定</a>
				</span>
			</div>
		</div>
		<div class='container w'>
			<#if sucess?? && sucess == "true">
  				<div class="emailbox">
  					恭喜您绑定邮件成功。<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/index.html">去进货吧！</a>
  				</div>
	  		<#else>
	  			<div class="emailbox">
	  				额，绑定失败了，<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!}/member/info.html">再试一次吧！</a>
	  				<br>
	  				${(message)!""}
	  			</div>
  		  	</#if>
		</div>
	<script type="text/javascript">
		$(function(){
			
		});
	</script>
	
<#include "/front/commons/_endbig.ftl" />
