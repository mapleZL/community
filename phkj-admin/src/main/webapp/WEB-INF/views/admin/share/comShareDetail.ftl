<#include "/admin/commons/_detailheader.ftl" />
<script language="javascript" xmlns="http://www.w3.org/1999/html">
    $(function(){

        $("#back").click(function(){
            window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/system/";
        });
        $("#edit").click(function(){
            if($("#editForm").form('validate')){
                $("#editForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/code/update")
                        .attr("method", "POST")
                        .submit();
            }
        });

        // 删除信息
        $('#examine_del').click(function () {
            var selected = $('#lbs').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sts == '关闭申请' ){
                $.messager.alert('提示','该申请不可以被操作。');
                return;
            }
            if(selected.sts == '申请通过' ){
                $.messager.alert('提示','该申请不可以被操作。');
                return;
            }
            if(selected.sts == '拒绝' ){
                $.messager.alert('提示','该申请不可以被操作。');
                return;
            }
            $.messager.confirm('确认', '确定拒绝该申请吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/system/examineApplyInfo",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=del",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#lbs').datagrid('reload');
                            } else {
                               // $.messager.alert('提示',data.message);
                                $.messager.alert('提示','操作失败!');
                                $('#lbs').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });


        // 删除信息
        $('#examine_add').click(function () {
            var selected = $('#lbs').datagrid('getSelected');
            if(!selected){
                $.messager.alert('提示','请选择操作行。');
                return;
            }
            if(selected.sts == '关闭申请' ){
                $.messager.alert('提示','该申请不可以被操作。');
                return;
            }
            if(selected.sts == '申请通过' ){
                $.messager.alert('提示','该申请已经通过。');
                return;
            }
            if(selected.sts == '拒绝' ){
                $.messager.alert('提示','该申请已被拒绝。');
                return;
            }
            $.messager.confirm('确认', '确定拒绝该申请吗？', function(r){
                if (r){
                    $.messager.progress({text:"提交中..."});
                    $.ajax({
                        type:"GET",
                        url: "${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/system/examineApplyInfo",
                        dataType: "json",
                        data: "id=" + selected.id + "&type=add",
                        cache:false,
                        success:function(data, textStatus){
                            if (data.success) {
                                $('#lbs').datagrid('reload');
                            } else {
                                // $.messager.alert('提示',data.message);
                                $.messager.alert('提示','操作失败!');
                                $('#lbs').datagrid('reload');
                            }
                            $.messager.progress('close');
                        }
                    });
                }
            });
        });


	<#if message??>$.messager.progress('close');alert('${message}');</#if>

	<#if (shareInfo.taskType) == '2'>
        if (shareInfo.carLock == "1"){
            $("input[name='Status'][value=1]").attr("checked",true);
        }else {
            $("input[name='Status'][value=0]").attr("checked",true);
        }
	</#if>
	<#if (shareInfo.taskType) == '3'>
       if(shareInfo.gender == "1"){
           $("input[name='gender'][value=1]").attr("checked",true);
       }else {
           $("input[name='gender'][value=0]").attr("checked",true);
       }
	</#if>

	 <#if (shareInfo.taskType) == '4'>
        <#if (shareInfo.doorLock =='1')>
             $("input[name='lock1'][value=1]").attr("checked",true);
        </#if>
         <#if (shareInfo.doorLock =='0')>
             $("input[name='lock0'][value=1]").attr("checked",true);
         </#if>
	</#if>


	<#--<#if (shareInfo.gender) == '1' >-->
	<#--$("input[name='gender'][value=1]").attr("checked",true);-->
	<#--</#if>-->
	<#--<#if (shareInfo.gender) == '0' >-->
	<#--$("input[name='gender'][value=0]").attr("checked",true);-->
	<#--</#if>-->

    })
</script>

<div class="wrapper">
    <div class="formbox-a">
        <h2 class="h2-title">发布详情<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/system/">返回</a></span></h2>

	<#--1.editForm----------------->
        <div class="form-contbox">

			<@form.form method="post" class="validForm" id="editForm" name="editForm">
                <input type="hidden" id="codeDiv" name="codeDiv" value="${(code.codeDiv)!''}" />
                <input type="hidden" id="codeCd" name="codeCd" value="${(code.codeCd)!''}" />
                <dl class="dl-group">
                    <dt class="dt-group"><span class="s-icon"></span>基本信息</dt>
                    <dd class="dd-group">

                        <div class="fluidbox">
                            <p class="p6 p-item">
                                <label class="lab-item">分类 : </label>
							<#if (shareInfo.taskType) == '1'>拼车</#if>
							<#if (shareInfo.taskType) == '2'>车位共享</#if>
							<#if (shareInfo.taskType) == '3'>时间互换</#if>
							<#if (shareInfo.taskType) == '4'>资源共享</#if>
                            </p>
                            <p class="p6 p-item">
                                <label class="lab-item">发布类型 : </label>
							<#if (shareInfo.shareType) == '1'>居民</#if>
							<#if (shareInfo.shareType) == '2'>社区/物业</#if>
                            </p>

                            <p class="p6 p-item">
                                <label class="lab-item">联系人 : </label>
								${(shareInfo.contact)}
                            </p>

                            <p class="p6 p-item">
                                <label class="lab-item">联系方式 : </label>
								${(shareInfo.telephone)}
                            </p>

                            <p class="p6 p-item">
                                <label class="lab-item">发布状态 : </label>
							<#if (shareInfo.sts) == '0'>删除</#if>
							<#if (shareInfo.sts) == '1'>正常</#if>
                            </p>
                            sts
                            <p class="p6 p-item">
                                <label class="lab-item">发布时间 : </label>
							<#if shareInfo.createTime??>${(shareInfo.createTime)?string("yyyy-MM-dd HH:mm:ss")}</#if>
                            </p>

						<#--<div class="fluidbox">-->
						<#--<p class="p12 p-item">-->
						<#--<label class="lab-item"><font class="red">*</font>是否使用: </label>-->
						<#--<@cont.radio id="useYn" value="${(code.useYn)!''}" codeDiv="USE_YN" />-->
						<#--</p>-->
						<#--</div>-->
                        </div>

                    </dd>
                </dl>
                <!--
                1. 拼车
                2. 共享车位
                -->
                <dl class="dl-group">
                    <dt class="dt-group"><span class="s-icon"></span>详情</dt>
                    <dd class="dd-group">
					<#if (shareInfo.taskType) == "1">
					    <div class="fluidbox">
                            <p class="p6 p-item">
                                <label class="lab-item">创建人  ： </label>
                                    <#--${shareInfo.createUserNamedefault('vakin')}-->
								<#--<#if (shareInfo.createUserName) != ''>${(shareInfo.createUserName!)}</#if>-->
								<#--<#if (shareInfo.createUserName) == ''> -- </#if>-->
                            </p>
                            <p class="p6 p-item">
                                <label class="lab-item">车牌号码 ： </label>
								<#if (shareInfo.carNum) != ''>${(shareInfo.carNum)}</#if>
								<#if (shareInfo.carNum) == ''> -- </#if>
                            </p>
                        </div>

                        <div class="fluidbox">
                            <p class="p6 p-item">
                                <label class="lab-item">出发地 ：</label>
								<#if (shareInfo.departPlace) != ''>${(shareInfo.departPlace)}</#if>
								<#if (shareInfo.departPlace) == ''> -- </#if>
                            </p>
                            <p class="p6 p-item">
                                <label class="lab-item">目的地 ：</label>
								<#if (shareInfo.destination) != ''>${(shareInfo.destination)}</#if>
								<#if (shareInfo.destination) == ''> -- </#if>
                            </p>
                        </div>

                        <div class="fluidbox">
                            <p class="p6 p-item">
                                <label class="lab-item">发车时间 ：</label>
								<#if shareInfo.startTime??>${(shareInfo.startTime)?string("yyyy-MM-dd HH:mm:ss")}</#if>
                            </p>
                        </div>

                        <div class="fluidbox">
                            <p class="p6 p-item">
                                <label class="lab-item">详细内容 ：</label>
                                <textarea name="reworkmes"   cols="60"  rows="5" readonly="true"  style="OVERFLOW:   hidden">${(shareInfo.content)}</textarea>
                            </p>
                        </div>
					</#if>
					<#if (shareInfo.taskType) == "2">
					 <div class="fluidbox">
                         <p class="p6 p-item">
                             <label class="lab-item">创建人  ： </label>
								<#if (shareInfo.createUserName) != ''>${(shareInfo.createUserName)}</#if>
								<#if (shareInfo.createUserName) == ''> -- </#if>
                         </p>
                         <p class="p6 p-item">
                             <label class="lab-item">车位编号 ： </label>
								<#if (shareInfo.carNum) != ''>${(shareInfo.carNum)}</#if>
								<#if (shareInfo.carNum) == ''> -- </#if>
                         </p>
                     </div>

					 <div class="fluidbox">
                         <p class="p6 p-item">
                             <label class="lab-item">收费标准  ： </label>
								<#if (shareInfo.price) != ''>${(shareInfo.price)}</#if>
								<#if (shareInfo.price) == ''> -- </#if>
                         </p>
                         <p class="p6 p-item">
                             <label class="lab-item">车位锁 ： </label>
                             <input type="radio" name="Status" value="1" /> 启用
                             <input type="radio" name="Status" value="0" />  禁用
                         </p>
                     </div>

					<div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">共享开始时间 ：</label>
													<#if shareInfo.startTime??>${(shareInfo.startTime)?string("yyyy-MM-dd HH:mm:ss")}</#if>
                        </p>

                        <p class="p6 p-item">
                            <label class="lab-item">共享结束时间 ：</label>
													<#if shareInfo.endTime??>${(shareInfo.endTime)?string("yyyy-MM-dd HH:mm:ss")}</#if>
                        </p>
                    </div>

					 <div class="fluidbox">
                         <p class="p6 p-item">
                             <label class="lab-item">详细内容 ：</label>
                             <textarea name="reworkmes"   cols="60"  rows="5" readonly="true"  style="OVERFLOW:   hidden">${(shareInfo.content)}</textarea>
                         </p>
                     </div>
					</#if>
					<#if (shareInfo.taskType) == "3">
							 <div class="fluidbox">
                                 <p class="p6 p-item">
                                     <label class="lab-item">创建人  ： </label>
								<#if (shareInfo.createUserName) != ''>${(shareInfo.createUserName)}</#if>
								<#if (shareInfo.createUserName) == ''> -- </#if>
                                 </p>
                                 <p class="p6 p-item">
                                     <label class="lab-item">性别 ： </label>
                                     <input type="radio" name="gender" value="1" /> 男
                                     <input type="radio" name="gender" value="0" />  女
                                 </p>
                             </div>

						<div class="fluidbox">
                            <p class="p6 p-item">
                                <label class="lab-item">互换开始时间 ：</label>
														<#if shareInfo.startTime??>${(shareInfo.startTime)?string("yyyy-MM-dd HH:mm:ss")}</#if>
                            </p>

                            <p class="p6 p-item">
                                <label class="lab-item">互换结束时间 ：</label>
														<#if shareInfo.endTime??>${(shareInfo.endTime)?string("yyyy-MM-dd HH:mm:ss")}</#if>
                            </p>
                        </div>

					 <div class="fluidbox">
                         <p class="p6 p-item">
                             <label class="lab-item">职业  ： </label>
								<#if (shareInfo.profession) != ''>${(shareInfo.profession)}</#if>
								<#if (shareInfo.profession) == ''> -- </#if>
                         </p>
                         <p class="p6 p-item">
                             <label class="lab-item">技能 ： </label>
							  <#if (shareInfo.skill) != ''>${(shareInfo.skill)}</#if>
								<#if (shareInfo.skill) == ''> -- </#if>
                         </p>
                     </div>

						 <div class="fluidbox">
                             <p class="p6 p-item">
                                 <label class="lab-item">详细内容 ：</label>
                                 <textarea name="reworkmes"   cols="60"  rows="5" readonly="true"  style="OVERFLOW:   hidden">${(shareInfo.content)}</textarea>
                             </p>
                         </div>

					</#if>


					<#if (shareInfo.taskType) == "4">
						<div class="fluidbox">
                            <p class="p6 p-item">
                                <label class="lab-item">创建人  ： </label>
								${(shareInfo.createUserName)!}
                            </p>
                        </div>
                    <div class="fluidbox">
                     <p class="p6 p-item">
                         <label class="lab-item">人脸门禁 ： </label>
                         <input type="radio" id="lock1" name="lock1" value="1" /> 有
                         <input type="radio" id = "lock0"name="lock0" value="0" />  无
                     </p>
                    </div>
						<div class="fluidbox">
                            <p class="p6 p-item">
                                <label class="lab-item">共享开始时间 ：</label>
														<#if shareInfo.startTime??>${(shareInfo.startTime)?string("yyyy-MM-dd HH:mm:ss")}</#if>
                            </p>

                        </div>
                     <div class="fluidbox">
                         <p class="p6 p-item">
                             <label class="lab-item">共享结束时间 ：</label>
														<#if shareInfo.endTime??>${(shareInfo.endTime)?string("yyyy-MM-dd HH:mm:ss")}</#if>
                         </p>
                     </div>
						 <div class="fluidbox">

                         </div>
                    <p class="p12 p-item">
                        <label class="lab-item">详细内容 ：</label>
                        <textarea name="reworkmes"   cols="60"  rows="6" readonly="true"  style="OVERFLOW:   hidden">${(shareInfo.content)!}</textarea>
                        <#--<input type="text" value = "${(shareInfo.content)!}"    cols="60"  rows="5">-->
                    </p>
					</#if>
                    </dd>
                </dl>


                    <table id="lbs" class="easyui-datagrid" title="申请列表"
                           data-options="rownumbers:true
						,idField :'id'
						,singleSelect:true
						,autoRowHeight:false
						,fitColumns:true
						,toolbar:'#gridTools'
						,striped:true
						,pagination:true
					    ,pageSize:30
						,fit:true
						,url:'${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/system/getAllApplyByPage?infoId='+${shareInfo.id}
						,onLoadSuccess:dataGridLoadSuccess
    					,method:'get'
                        ">
                        <thead>
                        <tr>
                            <th field="createUserId" hidden="hidden"></th>
                            <th field="id" hidden="hidden"></th>
                            <th field="userName" width="50" align="center">申请人</th>
                            <th field="idCard" width="70" align="center">申请人证件号码</th>
                            <th field="telephone" width="70" align="center">申请人联系方式</th>
                            <th field="address" width="150" align="center">申请人房屋地址</th>
                            <th field="imgUrl" width="70" align="center">审核人</th>
                            <th field="sts" width="70" align="center">申请状态</th>
                            <th field="shareType" width="70" align="center">申请人类型</th>
                            <th field="createTime" width="70" align="center" >创建时间</th>
                            <th field="modifyTime" width="70" align="center" >修改时间</th>
                        </tr>
                        </thead>

                    </table>
                <div id="gridTools">
                    <@shiro.hasPermission name="/admin/share/examine_del">
                    <a id="examine_del" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true">拒绝</a>
                    </@shiro.hasPermission>
                      <@shiro.hasPermission name="/admin/share/examine_add">
                    <a id="examine_add" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">通过</a>
                      </@shiro.hasPermission>
                </div>

			<#--2.batch button-------------->
                <p class="p-item p-btn">
                    <input type="button" id="back" class="btn" value="返回"/>
                </p>
			</@form.form>
        </div>
    </div>



</div>



<#include "/admin/commons/_detailfooter.ftl" />