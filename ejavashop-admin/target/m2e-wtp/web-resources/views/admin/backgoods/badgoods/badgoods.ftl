<#include "/admin/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/admin/product/badgoods/"/>
<script language="javascript">
function goquery1(){
  $('#dataGrid').datagrid('reload', queryParamsHandler());
}
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">次品列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">供应商id :</label>
                        <input type="text" class="txt" id="q_sellerId" name="q_sellerId" value="${q_sellerId!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">供应商名称 :</label>
                        <input type="text" class="txt" id="q_sellerName" name="q_sellerName" value="${q_sellerName!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">商品Sku:</label>
                        <input type="text" class="txt" id="q_productSku" name="q_productSku" value="${q_productSku!''}"/>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>

<#--2.datagrid----------------->
<div data-options="region:'center'" border="false">
    <table id="dataGrid" class="easyui-datagrid"
           data-options="rownumbers:true
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
                <th field="id" width="100" align="left" halign="center">主键</th>
				<th field="backGoodsId" width="100" align="left" halign="center">退货id</th>
				<th field="ordersId" width="100" align="left" halign="center">订单id</th>
				<th field="ordersSn" width="100" align="left" halign="center">订单号</th>
				<th field="badNum" width="100" align="left" halign="center">次品数量</th>
				<th field="productId" width="100" align="left" halign="center">商品id</th>
				<th field="productSku" width="100" align="left" halign="center">商品sku</th>
				<th field="productName" width="100" align="left" halign="center">商品名称</th>
				<th field="sellerId" width="100" align="left" halign="center">供应商id</th>
				<th field="sellerName" width="100" align="left" halign="center">供应商名称</th>
				<th field="createTime" width="100" align="left" halign="center">退货时间</th>
				<th field="updateTime" width="100" align="left" halign="center">更新时间</th>
        </tr>
        </thead>
    </table>

    <#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="javascript:goquery1()">查询</a>
    </div>
        	<div id="addRole" class="wrapper">
		  <div class="formbox-a">
			<form id="addRoleForm" method="post">
				<input type="hidden" id="roleid" name="id" value="0">
				<input type="hidden" id="checkreason" name="checkreason"/>
				<div class="form-contbox">
					<dl class="dl-group">

						<dd class="dd-group">
							<div class="fluidbox">
								<p class="p12 p-item">
									<label class="lab-item"><font class="red">*</font>审核不通过原因:
									</label>
									<textarea class="txt w200 easyui-validatebox"  id="checkNote"
										name="checkNote"
										data-options="required:true,validType:['length[1,100]']" ></textarea>
									<span class="title_span">1-100个字符</span>
								</p>
							</div>
						</dd>
					</dl>

					<p class="p-item p-btn">
						<a class="easyui-linkbutton" iconCls="icon-save" id="addBtn">保存</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-delete" onclick="closeW()">关闭</a> 
					</p>
				</div>
			</form>
		</div>
	</div>
	
	<div id="allotResourceWin">
		
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />