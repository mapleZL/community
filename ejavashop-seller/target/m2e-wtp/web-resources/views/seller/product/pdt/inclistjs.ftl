<script type="text/javascript" src="${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/resources/admin/jslib/easyui/datagrid-detailview.js"></script>

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
            //window.location.href="${currentBaseUrl}chooseCate";
            window.location.href="${currentBaseUrl}add?cateId=1";
        });
        
        //同步oms
        $('#a-syncoms').click (function () {
        	var data = $('#dataGrid').datagrid('getChecked');
        	 if(!data || data.length == 0){
 	         	$.messager.alert('提示','请选择操作行。');
 	         	return;
 	        }
        	var ids = new Array();
 	        $.each(data,function(idx,e){
 	        		ids.push(e.id);
 	        });
 	       $.messager.confirm('确认', '确定同步选中商品到oms？', function(r){
               if (r){
                   $.messager.progress({text:"提交中..."});
                   $.ajax({
                       type:"POST",
                       url: "${currentBaseUrl}syncoms",
                       data: "ids="+ids,
                       cache:false,
                       success:function(e){
                           $.messager.progress('close');
                           //$('#dataGrid').datagrid('reload',queryParamsHandler());
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
    
    	function detailFormatter(index,row){
        return '<div style="padding:2px"><table class="ddv"></table></div>';
    }
    
 	function onExpandRow(index,row){
        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
        ddv.datagrid({
           fitColumns:true,
           singleSelect:true,
           method:'get',
           url:'${currentBaseUrl}/list_goods?productId='+row.id,
			loadMsg : '数据加载中...',
			height : 'auto',
			columns : [[{
				field : 'normName',
				title : '规格值',
				width : 150,
				align : 'left',
				halign : 'center'
			}, {
				field : 'sku',
				title : 'SKU',
				width : 150,
				align : 'center'
			}, {
				field : 'mallPcPrice',
				title : '商城PC价格',
				width : 150,
				align : 'center'
			}, {
				field : 'mallMobilePrice',
				title : '商城Mobile价格',
				width : 150,
				align : 'center'
			}, {
				field : 'productStock',
				title : '库存',
				width : 150,
				align : 'center'
			}]],
			onResize : function() {
				$('#dataGrid').datagrid('fixDetailRowHeight',index);
			},
			onLoadSuccess : function() {
				setTimeout(function() {
					$('#dataGrid').datagrid('fixDetailRowHeight',index);
				}, 0);
			}
		});
	}
 
    function openwin(id){
        window.open("${(domainUrlUtil.EJS_FRONT_URL)!}/product/"+ id +".html?preview=1");
    }
    
    
    function proTitle(value,row,index){
        return "<font style='color:blue;cursor:pointer' title='"+
                value+"' onclick='openwin("+row.id+")'>"+value+"</font>";
    }
</script>