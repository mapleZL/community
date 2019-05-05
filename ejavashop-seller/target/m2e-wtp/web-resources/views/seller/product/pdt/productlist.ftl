<#include "/seller/commons/_detailheader.ftl" />
<#assign currentBaseUrl="${domainUrlUtil.EJS_URL_RESOURCES}/seller/product/"/>
<script language="javascript">
    var codeBox;
    $(function(){
        //为客户端装配本页面需要的字典数据,多个用逗号分隔
    <#noescape>
        codeBox = eval('(${initJSCodeContainer("PRODUCT_STATE","PRODUCT_IS_TOP")})');
    </#noescape>

        $('#a-gridSearch').click(function(){
            $('#dataGrid').datagrid('reload',queryParamsHandler());
        });
        $("#a-gridAdd").click(function(){
            window.location.href="${currentBaseUrl}chooseCate";
        });
        
        //上架
        $("#a_pro_up").click(function(){
	       	var data = $('#dataGrid').datagrid('getChecked');
	        if(!data||data.length==0){
	         	$.messager.alert('提示','请选择操作行。');
	         	return;
	        }
	        
	        var ids = new Array();
	        var flag = true;
	        $.each(data,function(idx,e){
	        	if(e.state !=3){
	        		flag = false;
	        		return false;
	        	} else{
	        		ids.push(e.id);
	        	}
	        });
	        if(!flag){
	        	$.messager.alert('提示','必须是审核通过的商品才能上架,请检查。');
	            return;
	        }
	        $.messager.confirm('确认', '确定上架选中的商品吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${currentBaseUrl}handler",
	                    data: "ids="+ids+"&type=6",
	                    cache:false,
	                    success:function(e){
	                        $.messager.progress('close');
	                        $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        $.messager.show({
	                        	title:'提示',
	                        	msg:e,
	                        	showType:'show'
	                        });
	                    }
	                });
	            }
	        });
        });
        
        //下架
        $("#a_pro_down").click(function(){
          	var data = $('#dataGrid').datagrid('getChecked');
	        if(!data||data.length==0){
	         	$.messager.alert('提示','请选择操作行。');
	         	return;
	        }
	        
	        var ids = new Array();
	        var flag = true;
	        $.each(data,function(idx,e){
	        	if(e.state !=6){
	        		flag = false;
	        		return false;
	        	} else{
	        		ids.push(e.id);
	        	}
	        });
	        if(!flag){
	        	$.messager.alert('提示','必须是已上架的商品才能下架,请检查。');
	            return;
	        }
	        $.messager.confirm('确认', '确定下架选中的商品吗？', function(r){
	            if (r){
	                $.messager.progress({text:"提交中..."});
	                $.ajax({
	                    type:"POST",
	                    url: "${currentBaseUrl}handler",
	                    data: "ids="+ids+"&type=7",
	                    cache:false,
	                    success:function(e){
	                        $.messager.progress('close');
	                        $('#dataGrid').datagrid('reload',queryParamsHandler());
	                        $.messager.show({
	                        	title:'提示',
	                        	msg:e,
	                        	showType:'show'
	                        });
	                    }
	                });
	            }
	        });
        });
        
        $("#a-gridEdit").click(function(){
            var data = $('#dataGrid').datagrid('getChecked');
            if(!data||data.length==0){
            	$.messager.alert('提示','请选择操作行。');
                return;
            } else if(data.length>1){
            	$.messager.alert('提示','请选择一个商品以编辑');
				return;            	
            }
           	var selected = data[0];
            //提交审核的商品不允许在编辑
            if(2 == selected.state){
                $.messager.alert('提示','该商品已提交审核，暂不允许编辑。');
                return;
            }
            window.location.href="${currentBaseUrl}edit?id="+selected.id;
        });

        $("#a-gridRemove").click(function(){
        	 var data = $('#dataGrid').datagrid('getChecked');
             if(!data||data.length==0){
             	$.messager.alert('提示','请选择操作行。');
                 return;
             } else if(data.length>1){
             	$.messager.alert('提示','请选择一个商品');
 				return;            	
             }
            var selected = data[0];

            $.messager.confirm('确认', '确定删除该商品吗？删除后，此商品将从商城下架，此操作不可恢复。', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${currentBaseUrl}del",
                        dataType: "json",
                        data: "id="+selected.id+"&"+getCSRFTokenParam(),
                        cache:false,
                        success:function(data, textStatus){
                            $('#dataGrid').datagrid('reload');
                            refrushCSRFToken(data.csrfToken);
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });

        $('#a-gridCommit').click(function(){
        	 var data = $('#dataGrid').datagrid('getChecked');
             if(!data||data.length==0){
             	$.messager.alert('提示','请选择操作行。');
                 return;
             }
            alert(data.state);
            var ids = new Array();
            var flag = true;
            $.each(data,function(idx,e){

            	if(e.state !=1 && e.state !=4 && e.state !=7){
            		flag = false;
            		return false;
            	} else{
            		ids.push(e.id);
            	}
            });
            if(!flag){
            	$.messager.alert('提示','必须是新增或审批未被通过的商品才能提交审核,请检查。');
                return;
            }
            $.messager.confirm('确认', '确定提交审核吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"POST",
                        url: "${currentBaseUrl}commit",
                        data: "ids="+ids,
                        cache:false,
                        success:function(e){
                            $.messager.progress('close');
                            $('#dataGrid').datagrid('reload',queryParamsHandler());
                            $.messager.show({
                            	title:'提示',
                            	msg:e,
                            	showType:'show'
                            });
                        }
                    });
                }
            });
        });
    })

    function stateFormat(value,row,index){
        return codeBox["PRODUCT_STATE"][value];
    }
    function isTopFormat(value,row,index){
        return codeBox["PRODUCT_IS_TOP"][value];
    }
    function sellerIsTopFormat(value,row,index){
        return codeBox["PRODUCT_IS_TOP"][value];
    }
    
    function proTitle(value,row,index){
    	if(getLength(value)>20)
    		return "<font title='"+value+"'>"+value.substring(0,19)+"'...'</font>";
        return "<font title='"+value+"'>"+value+"</font>";
    }
    
    function getLength(str){  
        var realLength = 0;  
        for (var i = 0; i < str.length; i++){  
            charCode = str.charCodeAt(i);  
            if (charCode >= 0 && charCode <= 128)   
            realLength += 1;  
            else   
            realLength += 2;  
        }  
        return realLength;  
    } 
</script>

<#--1.queryForm----------------->
<div id="searchbar" data-options="region:'north'" style="margin:0 auto;" border="false">
    <h2 class="h2-title">商品列表 <span class="s-poar"><a class="a-extend" href="#">收起</a></span></h2>
    <div id="searchbox" class="head-seachbox">
        <div class="w-p99 marauto searchCont">
            <form class="form-search" action="doForm" method="post" id="queryForm" name="queryForm">
                <div class="fluidbox"><!-- 不分隔 -->
                    <p class="p4 p-item">
                        <label class="lab-item">商品名称 :</label>
                        <input type="text" class="txt" id="q_name1" name="q_name1" value="${q_name!''}"/>
                    </p>
                    <p class="p4 p-item">
                        <label class="lab-item">状态 :</label>
                        <@cont.select id="q_state" name="q_state" value="${(brand.state)!''}" codeDiv="PRODUCT_STATE" mode="1"/>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>

<div data-options="region:'center'" border="false">
    <table id="dataGrid" class="easyui-datagrid"
           data-options="rownumbers:true
						,singleSelect:false
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
						,pageSize:'${pageSize}'
						,fit:true
    					,url:'${currentBaseUrl}list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'">
        <thead>
        <tr>
            <th field="ck" checkbox="true"></th>
            <th field="name1" width="190" align="left" halign="center" formatter="proTitle">商品名称</th>
            <th field="productCateName" width="100" align="center">商品分类</th>
            <th field="name2" width="150" align="center">促销信息</th>
            <th field="productBrandName" width="90" align="center">商品品牌</th>
            <th field="mallPcPrice" width="90" align="center">商城价</th>
            <th field="upTimeStr" width="90" align="center">上架时间</th>
            <th field="sellerCateName" width="70" align="center">店铺分类</th>
            <th field="sellerIsTop" width="70" align="center" formatter="sellerIsTopFormat">是否店铺推荐</th>
            <th field="state" width="90" align="center" formatter="stateFormat">状态</th>
        </tr>
        </thead>
    </table>

<#--3.function button----------------->
    <div id="gridTools">
        <#noescape>
        	${buttons!}
        </#noescape>
        
        <a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
     </div>

<#include "/seller/commons/_detailfooter.ftl" />