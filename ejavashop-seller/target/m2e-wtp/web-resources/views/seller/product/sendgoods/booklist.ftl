<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/sendgoods/"/>
<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("GOODS_STATUS")})');
    </#noescape>
    
    $("#btn-returnchecck").click(function(){
			var selectedCode = $('#dataGrid').datagrid('getSelected');
		            if(!selectedCode){
		                $.messager.alert('提示','请选择操作行。');
		                return;
		             }
		             var status = selectedCode.status;
			         if(status =="9"){
			         $.messager.alert('提示','该申请已被驳回');
			          }
			          $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${currentBaseUrl}passreturn?id="+selectedCode.id,
                        success:function(e) {
                         $('#dataGrid').datagrid('reload',queryParamsHandler());
                         $.messager.progress('close');
		                 $.messager.show({
			                    title : '提示',
			                    msg : e,
		                        showType : 'show'
	                      });
                        }
                    });
		});	
		
     $("#addBtn").click(function(){
			var isValid = $("#addRoleForm").form('validate');
			var selectedCode = $('#dataGrid').datagrid('getSelected');
			var checkNote = $('#checkNote').val();
			var hideValue = $("#checkreason").val();
			var url;
			if(hideValue ==1){
			url="${currentBaseUrl}audit?type=unpase&checkNote="+checkNote+"&id="+selectedCode.id;
			}
			if(hideValue ==2){
			url="${currentBaseUrl}returngoods?id="+selectedCode.id+"&type=uncheck&checkNote="+checkNote;
			}
			if(isValid){
			$.messager.progress({
				text : "提交中..."
			});
			$.ajax({
                type:"GET",
                url: url,
                success:function(e){
                    closeW();
                    $('#dataGrid').datagrid('reload');
                    $.messager.progress('close');
                    $.messager.show({
                        title : '提示',
                        msg : e,
                        showType : 'show'
                    });
                }
            });
			}
		});
    

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-gridAdd").click(function(){
            window.location.href="${currentBaseUrl}add";
        });
        
         $("#btn_audit").click(function(){
	         var selectedCode = $('#dataGrid').datagrid('getSelected');
	            if(!selectedCode){
	                $.messager.alert('提示','请选择操作行。');
	                return;
	            }
	            var status = selectedCode.status;
	            if(status =="2"){
	            $.messager.alert('提示','该条记录已审核完成');
	            }
            $.ajax({
                type:"GET",
                url: "${currentBaseUrl}audit?type=pass&checkNote=&id="+selectedCode.id,
                success:function(e){
                    $('#dataGrid').datagrid('reload');
                    $.messager.progress('close');
                    $.messager.show({
                        title : '提示',
                        msg : e,
                        showType : 'show'
                    });
                }
            });
        });
        
        $("#a-gridEdit").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            window.location.href="${currentBaseUrl}edit?id="+selectedCode.id;
        });

        $("#a-gridRemove").click(function(){
            var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }

            $.messager.confirm('Confirm', '确定删除吗？', function(r){
                if (r) {
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${currentBaseUrl}del?id="+selectedCode.id,
                        success:function(e) {
                         $('#dataGrid').datagrid('reload');
                         $.messager.progress('close');
		                 $.messager.show({
			                    title : '提示',
			                    msg : e,
		                        showType : 'show'
	                      });
                        }
                    });
                }
            });
        });
    })
    
    function getState(value, row, index) {
		var box = codeBox["GOODS_STATUS"][value];
		return box;
	}

    function typeFormat(value,row,index){
        return codeBox["NORM_TYPE"][value];
    }
    function afterDataGridLoaded(){
    }
    function dataGridLoadFail(){
        alert("服务器异常");
    }
    function dataGridLoadSuccess(row,data){

    }
    function cc(row){
    }
    function bl(row,param){
        if (!row) {
            param.id = 0;
        }
    }
    
	function closeW(){
	    $("#addRole").window("close");
	} 
	
	function returnInStore(){
	        var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selectedCode.status=="10"){
             $.messager.alert('提示','已确认收货的订单不能申请退货。');
                return;
            }
            if(selectedCode.status=="9"){
             $.messager.alert('提示','退货申请被驳回订单不能再次申请退货。');
                return;
            }
             if(selectedCode.status=="7"){
             $.messager.alert('提示','退货申请正在处理中，请您耐心等待。');
                return;
            }
            $.messager.confirm('Confirm', '确定退货吗？', function(r){
                if (r) {
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${currentBaseUrl}returngoods?id="+selectedCode.id+"&type=return&checkNote=",
                        success:function(e) {
                         $('#dataGrid').datagrid('reload');
                         $.messager.progress('close');
		                 $.messager.show({
			                    title : '提示',
			                    msg : e,
		                        showType : 'show'
	                      });
                        }
                    });
                }
            });
	}
	
	function uncheck(){
	    var selectedCode = $('#dataGrid').datagrid('getSelected');
            if(!selectedCode){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
             $.messager.confirm('Confirm', '确定驳回退货申请吗？', function(r){
             if (r) {
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${currentBaseUrl}returngoods?id="+selectedCode.id+"&type=uncheck",
                        success:function(e) {
                         $('#dataGrid').datagrid('reload');
                         $.messager.progress('close');
		                 $.messager.show({
			                    title : '提示',
			                    msg : e,
		                        showType : 'show'
	                      });
                        }
                    });
                    }
             });
	}
	
	function comcheck1(comValue){   
             var selectedCode = $('#dataGrid').datagrid('getSelected');
	            if(!selectedCode){
	                $.messager.alert('提示','请选择操作行。');
	                return;
	            }
	            var status = selectedCode.status;
	            if(status =="6"){
	            $.messager.alert('提示','该条记录已审核完成');
	            }
	       var checkreasonDom = $("#checkreason");
	       if(comValue == 1){
	            checkreasonDom.attr("value","1");
	       }
	       if(comValue == 2){
	            checkreasonDom.attr("value","2");
	       }
			$("#addRole").window({
				width : 600,
				height : 223,
				title : "审核",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		}
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">商品预发货列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox">
                    <p class="p4 p-item">
                        <label class="lab-item">预约人 :</label>
                        <input type="text" class="txt" id="q_bookingUser" name="q_bookingUser" value="${q_bookingUser!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">收货人 :</label>
                        <input type="text" class="txt" id="q_name" name="q_name" value="${q_name!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">联系方式 :</label>
                        <input type="text" class="txt" id="q_mobile" name="q_mobile" value="${q_mobile!''}"/>
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
						,fitColumns:false
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
            <th field="sendAddress" width="100" align="left">省市区</th>
            <th field="addressDetail" width="100" align="left">详细地址</th>
            <th field="payType" width="100" align="center">支付方式</th>
            <th field="sendNum" width="100" align="center">发货总数量</th>
            <th field="weight" width="100" align="center">重量</th>
            <th field="takePrice" width="100" align="center">运费</th>
            <th field="mobile" width="100" align="center">联系方式</th>
          <!--  <th field="bookingUser" width="100" align="bookingUser">预约人</th>-->
            <th field="checkMan" width="100" align="center">审核人</th>
            <th field="status" width="100" align="center" formatter="getState">状态</th>
            <th field="logisticsNo" width="100" align="center">快递单号</th>
            <th field="payMan" width="100" align="center">付款人</th>
            <th field="logisticsName" width="100" align="center">物流公司</th>
            <th field="name" width="150" align="center">收货人</th>
            <th field="createTime" width="150" align="center">创建时间</th>
            <th field="sendTime" width="150" align="发货时间">发货时间</th>
            <th field="remark" width="150" align="center">发货说明</th>
            <th field="checkNote" width="150" align="center">审核说明</th>
        </tr>
        </thead>
    </table>

    <#--3.function button----------------->
    <div id="gridTools">
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<@shiro.hasPermission name="/seller/product/sendgoods/edit">
        <a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        </@shiro.hasPermission>
		<@shiro.hasPermission name="/seller/product/sendgoods/del">
        <a id="a-gridRemove" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/seller/product/sendgoods/audit">
        <a id="btn_audit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true">审核通过</a>
        <a id="btn_audit1" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:comcheck1(1)">审核不通过</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/seller/product/sendgoods/returngoods">
        <a id="btn-returnInStore" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="returnInStore()">整单退货</a>
       </@shiro.hasPermission>
       <@shiro.hasPermission name="/seller/product/sendgoods/returncheck">
        <a id="btn-returnuncheck" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:comcheck1(2)">退货驳回</a>
       </@shiro.hasPermission>
       <@shiro.hasPermission name="/seller/product/sendgoods/passreturn">
        <a id="btn-returnchecck" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saved" plain="true" onclick="check()">退货审核</a>
        </@shiro.hasPermission>
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

<#include "/seller/commons/_detailfooter.ftl" />