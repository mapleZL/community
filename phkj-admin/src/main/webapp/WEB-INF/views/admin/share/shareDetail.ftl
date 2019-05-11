<#include "/admin/commons/_detailheader.ftl" />

<script language="javascript">
$(function(){

    $("#back").click(function(){
	 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/";
		});
	$("#edit").click(function(){
		if($("#editForm").form('validate')){
	 		$("#editForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/code/update")
  				 .attr("method", "POST")
  				 .submit();
		}
	});
	<#if message??>$.messager.progress('close');alert('${message}');</#if>

	<#if (shareInfo.taskType) == '2'>
		<#if (shareInfo.carLock) == '1' >
	    $("input[name='Status'][value=1]").attr("checked",true);
		</#if>
		<#if (shareInfo.carLock) == '0' >
	    $("input[name='Status'][value=0]").attr("checked",true);
		</#if>
	</#if>

	<#if (shareInfo.taskType) == '3'>
		<#if (shareInfo.gender) == '1' >
		$("input[name='gender'][value=1]").attr("checked",true);
		</#if>
		<#if (shareInfo.gender) == '0' >
		$("input[name='gender'][value=0]").attr("checked",true);
		</#if>
	</#if>

	<#if (shareInfo.taskType) == '4'>
		<#if (shareInfo.doorLock) == '1' >
		$("input[name='lock'][value=1]").attr("checked",true);
		</#if>
		<#if (shareInfo.doorLock) == '0' >
		$("input[name='lock'][value=0]").attr("checked",true);
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
		<h2 class="h2-title">发布详情<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/">返回</a></span></h2>
		
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
								<#if (shareInfo.createUserName) != ''>${(shareInfo.createUserName)}</#if>
								<#if (shareInfo.createUserName) == ''> -- </#if>
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
								<#if (shareInfo.createUserName) != ''>${(shareInfo.createUserName)}</#if>
								<#if (shareInfo.createUserName) == ''> -- </#if>
                             </p>
                             <p class="p6 p-item">
                                 <label class="lab-item">人脸门禁 ： </label>
                                 <input type="radio" name="lock" value="1" /> 有
                                 <input type="radio" name="lock" value="0" />  无
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
				</dd>
			</dl>
                <dl class="dl-group">
                    <dt class="dt-group"><span class="s-icon"></span>共享申请</dt>
                    <dd class="dd-group">
                        <div class="fluidbox">
                            <label class="lab-item">帮助信息。</label>
                        </div>
                    </dd>
                </dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>

</div>

<#include "/admin/commons/_detailfooter.ftl" />