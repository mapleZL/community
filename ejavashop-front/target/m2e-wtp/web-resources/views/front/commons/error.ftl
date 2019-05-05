<#include "/front/commons/_headbig.ftl" />

<div class="main">

  <div>
  	<table style="width:100%; height:200px; text-align:center;">
  		<tr>
  			<td width="48%" style="font-size:22px;text-align:right;">
  				<img src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/error.png" />
  				错误信息：
  			</td>
  			<td style="text-align:left;">
  			<span style="font-size:22px;color:red;">${(info)!''}</span>
  		
  			</td>
  		</tr>
  		<tr>
  			<td>	您可以：稍后再试或联系客服。
			返回首页</td>
  			<td></td>
  		</tr>
	</table>
  </div>

<script>
	  var count=3;
	  var interval = window.setInterval(go,1000);
	  function go(){
	    count--;
	    if(count==0) {
	    	window.location.href= domain+"/index.html";
	    	clearInterval(interval);	    	
	    }
	  }
</script>
   

