<#include "/admin/commons/_detailheader.ftl" />

<script type="text/javascript" src="${domainUrlUtil.EJS_STATIC_RESOURCES}/resources/admin/jslib/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">

$(function(){

    $("input[name='taskType'][value=0]").attr("checked",true);

	$("#back").click(function(){
 		window.location.href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/system/";
	});
	$("#add").click(function(){
		
		if($("#addForm").form('validate')){
            var taskType = $("input[name='taskType']:checked").val();
            if (taskType == undefined || taskType == null) {
				$.messager.alert('提示','请选择分类');
				return;
			}
			var title = $("#title").val();
			if (title == undefined || title == null || title == '') {
				$.messager.alert('提示','标题必须填写');
				return;
			}

            var contact = $("#contact").val();
            if (contact == undefined || contact == null || contact == '') {
                $.messager.alert('提示','联系人必须填写');
                return;
            }

            var telephone = $("#telephone").val();
            if (telephone == undefined || telephone == null || telephone == '') {
                $.messager.alert('提示','联系电话必填');
                return;
            }

            var doorLock = $("input[name='doorLock']:checked").val();
            if(doorLock == undefined || doorLock == null) {
                $.messager.alert('提示','请选择人脸门禁');
                return;
			}

            var startTime = $('#startTime').datetimebox('getValue');
            if (startTime == undefined || startTime == null ) {
                $.messager.alert('提示','请选择开始时间');
                return;
            }

            var endTime= $('#endTime').datetimebox('getValue');
            if (endTime == undefined || endTime == null ) {
                $.messager.alert('提示','请选择开始时间');
                return;
            }
            var content = $("#content").val();

		$("#addForm").attr("action", "${domainUrlUtil.EJS_URL_RESOURCES}/admin/share/system/addShare")
  				 .attr("method", "POST")
  				 .submit();
  		}
	});

});

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">物业共享<span class="s-poar"><a class="a-back" href="${domainUrlUtil.EJS_URL_RESOURCES}/admin/system/">返回</a></span></h2>
		
		<#--1.addForm----------------->
		<div class="form-contbox">
			<@form.form method="post" class="validForm" id="addForm" name="addForm" enctype="multipart/form-data">
			<dl class="dl-group">
				<dt class="dt-group"><span class="s-icon"></span>发布共享</dt>
				<dd class="dd-group">
					<div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>分类 ： </label>
                            <input type="radio" id = "taskType" name="taskType" value="4" /> 资源共享
                        </p>
					</div>

					<br/>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>标题 ：</label>
                            <input id="title" name="title"  />
						</p>
					</div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>联系人 ：</label>
                            <input id="contact" name="contact"  />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>联系电话 ：</label>
                            <input id="telephone" name="telephone"  />
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>人脸门禁 ： </label>
                            <input type="radio" name="doorLock" value="1" /> 有
                            <input type="radio" name="doorLock" value="0" />  无
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>共享开始时间 ：</label>
                            <input class="easyui-datetimebox" name="startTime" id = "startTime"
                                    value="3/4/2010 2:3" style="width:150px">
                        </p>
                    </div>

                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item"><font class="red">*</font>共享结束时间 ：</label>
                            <input class="easyui-datetimebox" name="endTime" id = "endTime"
                                    value="3/4/2010 2:3"  style="width:150px">
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p6 p-item">
                            <label class="lab-item">详细内容 ：</label>
                            <textarea id="content" name="content"   cols="60"  rows="5"   style="OVERFLOW:   hidden"></textarea>
                        </p>
                    </div>
				</dd>
			</dl>

			<#--2.batch button-------------->
			<p class="p-item p-btn">
				<input type="button" id="add" class="btn" value="新增" />
				<input type="button" id="back" class="btn" value="返回"/>
			</p>
			</@form.form>
		</div>
	</div>
</div>

<#include "/admin/commons/_detailfooter.ftl" />