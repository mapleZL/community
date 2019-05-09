/**
 * form表单中所有文本值 是否包含有非法字符 如果有则 返回true 没有则返回 false
 * @param form  要校验的form表单
 * @returns {Boolean}
 */
function checkAllTextValid(form) {
	var flag = false;
    for (var i = 0; i < form.elements.length; i++) {
        if (form.elements[i].type == "text"||form.elements[i].type == "textarea") {
            if (/[<>$]+|script/.test(form.elements[i].value.toLowerCase())){
            	flag = true;
            	form.elements[i].select();
            	break;
            }
        }
    }
    if (flag){
    	alert("文本框包含有非法字符，请重新输入");
    	return true;
    }else {
    	return false;
    }
}
/**
 * 校验图片格式是否正确（.png  .jpg .jpeg  .gif .bmp 五种格式视为正确的图片格式） 如果格式正确返回 true  不正确 返回false
 * @param file  <input type="file"/> 组件
 * @returns {Boolean}
 */
function validatePictureType(file){
	if(file){
		if(file.value){
			var type = file.value.substr(file.value.lastIndexOf(".")).toLowerCase();
			if(".png"==type||".jpg"==type||".jpeg"==type||".gif"==type||".bmp"==type){
				return true;
			}else{
				alert("图片格式不正确，请上传gif,bmp,jpg,jpeg,png格式的图片");
				return false;
			}
		}
	}
	return true;
}

function showCheckMessage(messageEleId,message,color){
	$("#"+messageEleId).html(""+message);
	$("#"+messageEleId).css("color",color);
}
function changeCheckFlag(flagEleId,flag){
	$("#"+flagEleId).val(flag);
}
function checkResult(flagEleId,flag,messageEleId,message,color){
	changeCheckFlag(flagEleId,flag);
	showCheckMessage(messageEleId,message,color);
}
//只能输入中文校验
// obj 要校验的字符串 ,flagEleId 页面隐藏域Id 用于存储验证成功和失败, messageEleId 页面显示域 用于显示验证结果信息
function chinese(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\u4E00-\u9FA5]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"只允许输入中文！","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//只能输入2到10位中文
function atleasttwochinese(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\u4E00-\u9FA5]{2,11}/g)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"只允许输入2-10位中文！","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
 

//只能输入中文和英文
function chineseAndEn(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"不允许有空格！","red");
		}else{
			if(obj.match(/^[a-zA-Z\u4E00-\u9FA5]*$/g)){
				checkResult(flagEleId,"true",messageEleId,"正确！","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"只允许输入中文或英文！","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//只能输入数字
function numberOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[0-9]*$/g) && !obj.match(/^0+[\d]/) && !obj.match(/^0+\.+0$/g)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入正确的数字！","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//只能输入中文和英文数字和下划线
function chineseEnNuAndUnl(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"不允许有空格！","red");
		}else{
			if(obj.match(/^[a-zA-Z0-9_\u4E00-\u9FA5]*$/g)){
				checkResult(flagEleId,"true",messageEleId,"正确！","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"只允许输入中文、英文、数字 或 _","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//只能输入手机号码
function mobileOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(!!obj.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入正确的手机号码！","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//最多只能输入两位小数 数字
function floatMax2Only(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"不允许有空格！","red");
		}else{
			if(obj.match(/^\d+(\.\d{1,2})?$/g) && !obj.match(/^0+[\d]/) && !obj.match(/^0+\.+0$/g)){
				checkResult(flagEleId,"true",messageEleId,"正确！","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"只允许输入最多两位小数数字","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//只能输入中文和括号
function chineseAndEnblock(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	 if(text!=""){
		 if(obj.match(/^[\u2E80-\uFE4F()（）]+$/g) && obj.indexOf(" ") == -1){
			 checkResult(flagEleId,"true",messageEleId,"正确！","green");
		 }else{
			 checkResult(flagEleId,"false",messageEleId,"请输入正确的公司名称，不允许输入空格","red");
		 }
	 }else{
		 if(must){
				checkResult(flagEleId,"false",messageEleId,"请输入！","red");
			}else{
				checkResult(flagEleId,"true",messageEleId,"","green");
			}
	 }
}

//只能输入邮箱
function emailOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	//校验类似于hxu@sitechasia.com的邮件
	pattern1="^[a-zA-Z0-9_.\-]+[@]{1}[a-zA-Z0-9_\-]+[.]{1}[a-zA-Z0-9_\-]+$";
	//校验类似于huanxu@yahoo.com.cn的邮件
	pattern2="^[a-zA-Z0-9_.\-]+[@]{1}[a-zA-Z0-9_\-]+[.]{1}[a-zA-Z0-9_\-]+[.]{1}[a-zA-Z0-9_\-]+$";
	if(text!=""){
		if((obj.match(pattern1) || obj.match(pattern2)) && obj.indexOf(" ") == -1){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入正确的邮箱(请勿输入空格)","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//只能输入数字和英文下划线
function numAndEnOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[a-zA-Z0-9_]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"只允许输入数字、英文或_","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}



//3-20个英文或者数字或下划线（_）组成
function atleastthreenumAndEnOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\u0391-\uFFE5A-Za-z]{3,21}/g)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"只允许输入3-20数字、英文或_","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//最多只能输入6位小数 数字(主要用于输入经纬度)
function floatMax6Only(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^\d+(\.\d{1,6})?$/g) && !obj.match(/^0+[\d]/) && !obj.match(/^0+\.+0$/g) ){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"只允许输入最多六位小数数字","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//验证折扣，小于1的小数
function discountOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^(\d(\.\d{1,2})?|10)$/)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"只允许输入最多两位小数数字","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//数字和横杠(-),用来输入电话号及中间的-,并且不能输入空格
function telephoneOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"不允许有空格！","red");
		}else{
			if(obj.match(/^((1[35847])\d{9})$|^((\d{3,4}-)?(\d{7,8})((-\d{1,4})?))$/) && obj.indexOf(' ') == -1){
//			if(obj.match(/^([[0-9]{3}-|\[0-9]{4}-]?([0-9]{8}|[0-9]{7})?[[0-9]{3}-|\[0-9]{4}-])|((13|15|18|14)[0-9]{9})$/) && obj.indexOf(' ') == -1){
				checkResult(flagEleId,"true",messageEleId,"正确！","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"请输入正确的电话号！","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//验证传真
function chuanzhenOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"不允许有空格！","red");
		}else{
			if(obj.match(/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/) && obj.indexOf(' ') == -1){
				checkResult(flagEleId,"true",messageEleId,"正确！","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"请输入正确的传真！","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//验证身份证
function idCardOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入正确的身份证","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//验证银行卡
function bankCardOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/(^\d{10,30}$)/)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入正确的银行卡","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//只能输入邮编
function postcodeOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[0-9][0-9]{5}$/)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入正确的邮编","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//手机号和电话号
function mobileAndTelphoneOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
//	var phone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	//var telPhone = /^((0\d{3,4}-\d{7,8})|(1[35874]\d{9}))$/;
	if(text!=""){
		if(obj.match(/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/) || obj.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入正确的手机号或座机号，座机号格式为：区号-座机号","red")
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//只能输入中文、英文和括号
function chineseEnAndBracket(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\(\)\（\）a-zA-Z\u4E00-\u9FA5]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入中文或英文！允许含有括号，不能输入空格","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//验证不为空，不能含有空格
function notNullOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!="" && obj.indexOf(" ") == -1){
		checkResult(flagEleId,true,messageEleId,"正确！","green");
	}else{
		if(must){
			checkResult(flagEleId,false,messageEleId,"请输入正确内容！不能包含空格！","red");
		}else{
			checkResult(flagEleId,false,messageEleId,"","green");
		}
	}
}

//最多只能输入两位小数 数字
function moneryOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[0-9]([0-9]*|.[0-9]{1,2})/g)){
			checkResult(flagEleId,true,messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,false,messageEleId,"只允许输入最多两位小数数字","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,false,messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,false,messageEleId,"","green");
		}
	}
}

//只能输入数字和英文下划线和@
function numAndEnXAOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[a-zA-Z0-9_@]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"登录名称由4-20个数字或英文或_@组成","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//密码验证，密码位数为6-20位，数字+字母，而且不能是纯数字，纯字母，纯特殊字符
//不能输入空格
function passwordOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{6,20}$/)){
		//if(obj.match(/^(?![\d]+$)(?![a-zA-Z]+$).{6,20}$/)&&!obj.match(/((?=[\x21-\x7e]+)[^A-Za-z0-9])/)&&!checkStr(obj)&& obj.indexOf(' ')==-1){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"密码为6-20位数字和字母组合。","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//密码验证，密码位数为6-20位，数字+字母，数字+特殊字符，字母+特殊字符，数字+字母+特殊字符组合，而且不能是纯数字，纯字母，纯特殊字符
//不能输入空格
function passwordOnly1(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/(^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$)/) && obj.indexOf(' ')==-1){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"密码位数为6-20位，由数字、字母、特殊字符组合而成。","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//只能输入版本号
function versionNum(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[0-9]+(.[0-9]+)*$/) ){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"版本号格式不正确","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//禁止输入非法字符
function legalOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(!obj.match(/[<>$]+|script/) ){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"禁止输入非法字符","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}


//验证中文
function checkStr(str){ 
	// [\u4E00-\uFA29]|[\uE7C7-\uE7F3]汉字编码范围 
	var re1 = new RegExp("([\u4E00-\uFA29]|[\uE7C7-\uE7F3])"); 
	if (!re1.test(str)){ 
	return false; 
	} 
	return true; 
	} 
	//--> 

 

//验证网址
function urlOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(checkStr(obj)||obj.match(/\s/)){
		//if(obj.match(/[\u4E00-\uFA29]|[\uE7C7-\uE7F3])/)){
			checkResult(flagEleId,"false",messageEleId,"不允许有中文和空格！","red");
		}else{
			//if(!obj.match(/^[http]+:\/\/(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$/) ){
			if(obj.match(/http:\/\/.+/) ){
				checkResult(flagEleId,"true",messageEleId,"正确！","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"请输入正确的网址","red");
			}
		}
		
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//只能输入数字,不能以0开头
function positiveNumberOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[0-9]*$/g) && !obj.match(/^0+[\d]/) && !obj.match(/^0+0$/g) ){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入正确的数字！","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//只能输入一位小数 数字 最高减免金额,不能以0开头
function freeOnly(obj,flagEleId,messageEleId,must){
	var text = obj;
	if(text!=""){
		if(obj.match(/^\d+(\.\d{0,1})?$/g)&& !obj.match(/^0+[\d]/) && !obj.match(/^0+0$/g)){
			checkResult(flagEleId,'true',messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,'false',messageEleId,"请输入正确的数字,最多一位小数,不能以0开头","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,'false',messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,'true',messageEleId,"","green");
		}
	}
}

//验证满减，最多一位小数且是‘.0’，不能以0开头
function discountRateOnly1(obj,flagEleId,messageEleId,must){
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"不允许有空格！","red");
		}else{
			if(obj.match(/^\d+(\.0{0,1})?$/g) && !obj.match(/^0+[\d]/) && !obj.match(/^0+0$/g) ){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入正确的数字,最多一位小数且是'.0',不能以0开头","red");
		}
	 }
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//验证折扣，小于1的小数，小数点后最多两位
function discountRateOnly(obj,flagEleId,messageEleId,must){
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"不允许有空格！","red");
		}else{
			if(obj.match(/^([0]\.[0-9]{1}[1-9]{1})$|^([0]\.[1-9]{1})$/g)){
				checkResult(flagEleId,"true",messageEleId,"正确！","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"只允许输入0到1之间的小数，小数点后最多两位","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//只能输入中文和（）
function chineseAndEnblock(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\(\)\（\）\u4E00-\u9FA5]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"只能输入中文和（）","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}


//只输入空格，提示“不允许输入空格”---商品名称
function notNullOnly2(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(obj.match(/\s/)){
		checkResult(flagEleId,"false",messageEleId,"不允许有空格！","red");
	}else{
		if(text!="" && obj.indexOf(" ") == -1){
			checkResult(flagEleId,true,messageEleId,"正确！","green");
		}else{
			if(must){
				checkResult(flagEleId,false,messageEleId,"请输入！","red");
			}else{
				checkResult(flagEleId,false,messageEleId,"","green");
			}
		}
	}
	
}


//不允许只输入空格
function notSpaceOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
		if(text!=""){
			text = $.trim(obj);
			if(text!=""){
				checkResult(flagEleId,true,messageEleId,"正确！","green");
			}else{
				checkResult(flagEleId,false,messageEleId,"不允许只输入空格！","red");
			}
		}else{
			if(must){
				checkResult(flagEleId,false,messageEleId,"请输入！","red");
			}else{
				checkResult(flagEleId,true,messageEleId,"","green");
			}
		}
	
}

//只能输入中文、英文和逗号
function chineseEnAndComma(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\,a-zA-Z\u4E00-\u9FA5]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"正确！","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"请输入中文、英文或逗号！不能输入空格","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//只能大于零的数字
function moreThanZeroOnly(obj,flagEleId,messageEleId,must){
//		var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"不允许有空格！","red");
		}else{
			if(obj.match(/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/g)){
				checkResult(flagEleId,"true",messageEleId,"正确！","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"只允许输入大于零的数字，小数点后最多两位","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//评分 只允许输入正数，最多一位小数
function gradeOnly(obj,flagEleId,messageEleId,must){
	//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"不允许有空格！","red");
		}else{
			if(obj.match(/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{0,1})?$/g)){
				checkResult(flagEleId,"true",messageEleId,"正确！","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"只允许输入大于零的数字，小数点后最多一位","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"请输入！","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//商品介绍不能大于100字
function notSpaceLengthOnly(obj,flagEleId,messageEleId,must){
//		var text = $.trim(obj);
		var text = obj;
			if(text!=""){
				text = $.trim(obj);
				if(text!=""){
					if(text.length <=100){
						checkResult(flagEleId,true,messageEleId,"正确！","green");
					}else {
						checkResult(flagEleId,false,messageEleId,"输入内容请小于100个字符！","red");
					}
				}else{
					checkResult(flagEleId,false,messageEleId,"不允许只输入空格！","red");
				}
			}else{
				if(must){
					checkResult(flagEleId,false,messageEleId,"请输入！","red");
				}else{
					checkResult(flagEleId,true,messageEleId,"","green");
				}
			}
		
	}