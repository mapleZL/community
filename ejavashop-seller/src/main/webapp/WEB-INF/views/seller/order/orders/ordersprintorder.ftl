<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/order/orders"/>

<script language="javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/lodop/LodopFuncs.js"></script>
<script language="javascript" type="text/javascript"> 
    var LODOP; //声明为全局变量       
	function myBlankDesign() {   
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
		LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${(domainUrlUtil.EJS_IMAGE_RESOURCES)!}/${(courierCompany.imagePath)!}'>");
		<#noescape>
		${(courierCompany.content)!''}
		</#noescape>
		LODOP.PRINT_DESIGN();
	};
</script>


<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			设计打印模板
			<span class="s-poar">
			</span>
		</h2>

		<#--1.addForm----------------->
		<div class="form-contbox">

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red"></font>物流公司:
							</label> ${(courierCompany.companyName)!''}
						</p>
						<p class="p6 p-item">
							<label class="lab-item">快递单号: </label> ${(orders.logisticsNumber)!''}
						</p>
					</div>
					<#--<div class="fluidbox">
						<p class="p6 p-item">
							<label class="lab-item"><font class="red"></font>收件人姓名:
							</label> ${(consigneeName)!''}
						</p>
						<p class="p6 p-item">
							<label class="lab-item">收件人电话: </label> ${(consigneePhone)!''}
						</p>
					</div>
					
					
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>收件人地址:
							</label> ${(consigneeAdds)}
						</p>
					</div>
					-->
					<p class="p-item p-btn">
						<input type="button" onclick="myBlankDesign()" class="btn" value="打印" /> 
					</p>
				</dd>
			</dl>

			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>帮助
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<label class="lab-item" style="width: 100%; text-align: left;">
						  打印快递单是集成Lodop，版本是：Lodop 6.198<br/>
						  Lodop 官网是：http://www.lodop.net/<br/>
						  第一次使用需要安装浏览器插件 <a href="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/lodop/Lodop.zip"><b>下载</b></a> <br/>
						  也可以去Lodop下载插件，下载地址是：http://www.lodop.net/download.html<br/>
						  <br/>
						</label>
					</div>
				</dd>
			</dl>

		</div>
	</div>
</div>

<#include "/seller/commons/_detailfooter.ftl" />
