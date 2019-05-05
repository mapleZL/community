$(function() {
	var message = $("#message").val();
	if (message != "") {
		alert_(message);
	}
	
	$('#up_bussinessLicenseImage').on('change', function() {
		addFileResult(this);
    });

	$('#up_organization').on('change', function() {
		addFileResult(this);
    });

	$('#up_taxLicense').on('change', function() {
		addFileResult(this);
    });
		
	$(".public3").click(function() {
		
		if($("#company").val() == null || $("#company").val() == ""){
			alert_("公司名称为必填项，请检查");
			return;
		}
		if($("#value1").val() == null || $("#value1").val() == ""){
			alert_("地址为必填项，请检查");
			return;
		}
		if($("#companyAdd").val() == null || $("#company").val() == ""){
			alert_("详细地址为必填项，请检查");	
			return;
		}
		if($("#fax").val() == null || $("#fax").val() == ""){
			alert_("传真为必填项，请检查");
			return;
		}
		if($("#legalPerson").val() == null || $("#legalPerson").val() == ""){
			alert_("法定代表人为必填项，请检查");
			return;
		}
		if($("#personPhone").val() == null || $("#personPhone").val() == ""){
			alert_("联系人电话为必填项，请检查");
			return;
		}
		if($("#email").val() == null || $("#email").val() == ""){
			alert_("邮箱为必填项，请检查");
			return;
		}
		if($("#engineSum").val() == null || $("#engineSum").val() == ""){
			alert_("机型及台数为必填项，请检查");
			return;
		}
		if($("#madePerDay").val() == null || $("#madePerDay").val() == ""){
			alert_("日产量为必填项，请检查");
			return;
		}
		if($("#employeeSum").val() == null || $("#employeeSum").val() == ""){
			alert_("员工人数为必填项，请检查");
			return;
		}
		if($("#ownCate").val() == null || $("#ownCate").val() == ""){
			alert_("现有品类为必填项，请检查");
			return;
		}
		if($("#wellArea").val() == null || $("#wellArea").val() == ""){
			alert_("擅长领域为必填项，请检查");
			return;
		}
		if($("#up_bussinessLicenseImage").val() == null || $("#up_bussinessLicenseImage").val() == ""){
			alert_("营业执照为必填项，请检查");
			return;
		}
		if($("#up_organization").val() == null || $("#up_organization").val() == ""){
			alert_("组织机构为必填项，请检查");
			return;
		}
		if($("#up_taxLicense").val() == null || $("#up_taxLicense").val() == ""){
			alert_("税务登记证为必填项，请检查");
			return;
		}
		
		$("#registerForm").submit();
	});
	
});

function addFileResult(obj) {
	var fileNames = '';
	$.each(obj.files, function() {
		fileNames += '<span class="am-badge">' + this.name + '</span> ';
	});
	$(obj).parent().prev().children(".em0").html(fileNames);
}