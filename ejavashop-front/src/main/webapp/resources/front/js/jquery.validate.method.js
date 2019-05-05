//定义中文消息
	var cnmsg = {
		required: "<font color='red'>必填字段</font>",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串"
		};
	jQuery.extend(jQuery.validator.messages, cnmsg);
	//添加校验规则
	// 手机号码验证
	jQuery.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));  
	}, "<font color='red'>请正确填写您的手机号码</font>");