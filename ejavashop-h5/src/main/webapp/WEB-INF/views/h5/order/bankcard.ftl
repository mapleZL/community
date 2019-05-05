<#include "/h5/commons/_head.ftl" />
<#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]>
<head>
	<style>
	.kahao-input input{
		width: 100%;
		height: 40px;
		border: 1px solid #bbb;
		font-size: 18px;
		text-indent: 10px;
		outline: none;
	}
	.kahao-input{
		margin-right: 20px;
		padding:7px 0 0 20px;
		height:70px;
		margin-top: 30px;
	
	}
	
	.pay-state{
		width: 100%;
		margin: 0px 0px 0 10px;}
	
	.pay-state p{
		font-size: 14px;
		margin-left:9px;
		width:290px;
		word-wrap: break-word;
		word-break: normal; 

		
	}
	
	
	 .kahao-input button{
		width: 100%;
		height:34px;
		background: #27cdf2;
		border: 1px solid #ccc;
		border-radius: 5px;
		font-size: 20px;
		color: #fff;
		line-height:34px;
	
	
	}
	
	</style>
	
	<script type = "text/javascript">
    window.onload =function() {  
            document.getElementById("bankCardNo").onkeyup =function() {  
                this.value =this.value.replace(/\s/g,'').replace(/(\d{4})(?=\d)/g,"$1 ");  
            };  
        };  
	</script>
</head>
  <body class="bgf2">
	   <!-- 头部 -->
	   <header id="header">
	   	  <div class="flex flex-align-center head-bar">
	   	  	 <div class="flex-1 text-left">
	   	  	 	<a href="${(domainUrlUtil.EJS_URL_RESOURCES)!''}/member/order.html">
	   	  	 		<span class="fa fa-angle-left"></span>
	   	  	 	</a>
	   	  	 </div>
	   	  	 <div class="flex-2 text-center">收银台</div>
	   	  	 <div class="flex-1 text-right" id="fa-bars"></div>
	   	  </div>
	   	  <#include "/h5/commons/_hidden_menu.ftl" />
	   </header>
	   <input type="hidden" name="errorMessage" id="errorMessage" value="${errorMessage!''}">
    <@form.form action="" id="payForm" name="payForm" method="GET">
		<input type="hidden" name="paySessionstr" value="${paySessionstr!''}">
		<input type="hidden" name="relationOrderSn" id="relationOrderSn" value="${relationOrderSn!''}">
		<input type="hidden" name="fromType" id="fromType" value="${fromType!''}">
		<input type="hidden" name="balancePassword" id="balancePassword" value="${balancePassword!''}">
		<input type="hidden" name="selectOrderBalance" id="selectOrderBalance" value="${selectOrderBalance!''}">
		<input type="hidden" name="optionsRadios" id="optionsRadios" value="bestpay">
		<input type="hidden" name="payType" id="payType" value="frombandcard.ftl">
    	<div class="main-pay">
			<div class="kahao-input">	
				<input type="text" placeholder="请输入银行卡号" name= "bankCardNo" id= "bankCardNo"> 
			</div>
			<div class="pay-state">
				<P>支付说明：</P>
				<P>请输入正确的银行卡账号，系统检测通过之后，需要您再次输入银行卡号</P>
			</div >
				<div class="kahao-input">
	    				<button onclick="goPay()">下一步</button>
	    		</div>
        </div>
    </@form.form>
 <#include "/h5/commons/_footer.ftl" />
 <#include "/h5/commons/_statistic.ftl" />
  <script type="text/javascript">
    function goPay(){
	      var bankCardNo = document.getElementById("bankCardNo").value;
	      if(bankCardNo == ''){
	        alert("请输入正确的银行卡号");
	        return;
	      }
	      //去支付
		$("#payForm").attr("action", "${(domainUrlUtil.EJS_URL_RESOURCES)!}/payindex.html")
			 .attr("method", "GET")
			 .submit();
    }
    
    var errorMessage = document.getElementById("errorMessage").value;
    if(errorMessage != ''){
      alert(errorMessage);
    }
  </script>
 </body>