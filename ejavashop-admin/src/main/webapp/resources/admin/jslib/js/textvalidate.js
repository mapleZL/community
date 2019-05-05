/**
 * form���������ı�ֵ �Ƿ�����зǷ��ַ� ������� ����true û���򷵻� false
 * @param form  ҪУ���form��
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
    	alert("�ı�������зǷ��ַ�������������");
    	return true;
    }else {
    	return false;
    }
}
/**
 * У��ͼƬ��ʽ�Ƿ���ȷ��.png  .jpg .jpeg  .gif .bmp ���ָ�ʽ��Ϊ��ȷ��ͼƬ��ʽ�� �����ʽ��ȷ���� true  ����ȷ ����false
 * @param file  <input type="file"/> ���
 * @returns {Boolean}
 */
function validatePictureType(file){
	if(file){
		if(file.value){
			var type = file.value.substr(file.value.lastIndexOf(".")).toLowerCase();
			if(".png"==type||".jpg"==type||".jpeg"==type||".gif"==type||".bmp"==type){
				return true;
			}else{
				alert("ͼƬ��ʽ����ȷ�����ϴ�gif,bmp,jpg,jpeg,png��ʽ��ͼƬ");
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
//ֻ����������У��
// obj ҪУ����ַ��� ,flagEleId ҳ��������Id ���ڴ洢��֤�ɹ���ʧ��, messageEleId ҳ����ʾ�� ������ʾ��֤�����Ϣ
function chinese(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\u4E00-\u9FA5]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"ֻ�����������ģ�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//ֻ������2��10λ����
function atleasttwochinese(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\u4E00-\u9FA5]{2,11}/g)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"ֻ��������2-10λ���ģ�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
 

//ֻ���������ĺ�Ӣ��
function chineseAndEn(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"�������пո�","red");
		}else{
			if(obj.match(/^[a-zA-Z\u4E00-\u9FA5]*$/g)){
				checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"ֻ�����������Ļ�Ӣ�ģ�","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//ֻ����������
function numberOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[0-9]*$/g) && !obj.match(/^0+[\d]/) && !obj.match(/^0+\.+0$/g)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��������ȷ�����֣�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//ֻ���������ĺ�Ӣ�����ֺ��»���
function chineseEnNuAndUnl(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"�������пո�","red");
		}else{
			if(obj.match(/^[a-zA-Z0-9_\u4E00-\u9FA5]*$/g)){
				checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"ֻ�����������ġ�Ӣ�ġ����� �� _","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//ֻ�������ֻ�����
function mobileOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(!!obj.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��������ȷ���ֻ����룡","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//���ֻ��������λС�� ����
function floatMax2Only(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"�������пո�","red");
		}else{
			if(obj.match(/^\d+(\.\d{1,2})?$/g) && !obj.match(/^0+[\d]/) && !obj.match(/^0+\.+0$/g)){
				checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"ֻ�������������λС������","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//ֻ���������ĺ�����
function chineseAndEnblock(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	 if(text!=""){
		 if(obj.match(/^[\u2E80-\uFE4F()����]+$/g) && obj.indexOf(" ") == -1){
			 checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		 }else{
			 checkResult(flagEleId,"false",messageEleId,"��������ȷ�Ĺ�˾���ƣ�����������ո�","red");
		 }
	 }else{
		 if(must){
				checkResult(flagEleId,"false",messageEleId,"�����룡","red");
			}else{
				checkResult(flagEleId,"true",messageEleId,"","green");
			}
	 }
}

//ֻ����������
function emailOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	//У��������hxu@sitechasia.com���ʼ�
	pattern1="^[a-zA-Z0-9_.\-]+[@]{1}[a-zA-Z0-9_\-]+[.]{1}[a-zA-Z0-9_\-]+$";
	//У��������huanxu@yahoo.com.cn���ʼ�
	pattern2="^[a-zA-Z0-9_.\-]+[@]{1}[a-zA-Z0-9_\-]+[.]{1}[a-zA-Z0-9_\-]+[.]{1}[a-zA-Z0-9_\-]+$";
	if(text!=""){
		if((obj.match(pattern1) || obj.match(pattern2)) && obj.indexOf(" ") == -1){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��������ȷ������(��������ո�)","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//ֻ���������ֺ�Ӣ���»���
function numAndEnOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[a-zA-Z0-9_]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"ֻ�����������֡�Ӣ�Ļ�_","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}



//3-20��Ӣ�Ļ������ֻ��»��ߣ�_�����
function atleastthreenumAndEnOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\u0391-\uFFE5A-Za-z]{3,21}/g)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"ֻ��������3-20���֡�Ӣ�Ļ�_","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//���ֻ������6λС�� ����(��Ҫ�������뾭γ��)
function floatMax6Only(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^\d+(\.\d{1,6})?$/g) && !obj.match(/^0+[\d]/) && !obj.match(/^0+\.+0$/g) ){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"ֻ�������������λС������","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//��֤�ۿۣ�С��1��С��
function discountOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^(\d(\.\d{1,2})?|10)$/)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"ֻ�������������λС������","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//���ֺͺ��(-),��������绰�ż��м��-,���Ҳ�������ո�
function telephoneOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"�������пո�","red");
		}else{
			if(obj.match(/^((1[35847])\d{9})$|^((\d{3,4}-)?(\d{7,8})((-\d{1,4})?))$/) && obj.indexOf(' ') == -1){
//			if(obj.match(/^([[0-9]{3}-|\[0-9]{4}-]?([0-9]{8}|[0-9]{7})?[[0-9]{3}-|\[0-9]{4}-])|((13|15|18|14)[0-9]{9})$/) && obj.indexOf(' ') == -1){
				checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"��������ȷ�ĵ绰�ţ�","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//��֤����
function chuanzhenOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"�������пո�","red");
		}else{
			if(obj.match(/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/) && obj.indexOf(' ') == -1){
				checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"��������ȷ�Ĵ��棡","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//��֤���֤
function idCardOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��������ȷ�����֤","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//��֤���п�
function bankCardOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/(^\d{10,30}$)/)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��������ȷ�����п�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//ֻ�������ʱ�
function postcodeOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[0-9][0-9]{5}$/)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��������ȷ���ʱ�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//�ֻ��ź͵绰��
function mobileAndTelphoneOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
//	var phone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
	//var telPhone = /^((0\d{3,4}-\d{7,8})|(1[35874]\d{9}))$/;
	if(text!=""){
		if(obj.match(/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/) || obj.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��������ȷ���ֻ��Ż������ţ������Ÿ�ʽΪ������-������","red")
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//ֻ���������ġ�Ӣ�ĺ�����
function chineseEnAndBracket(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\(\)\��\��a-zA-Z\u4E00-\u9FA5]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"���������Ļ�Ӣ�ģ����������ţ���������ո�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//��֤��Ϊ�գ����ܺ��пո�
function notNullOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!="" && obj.indexOf(" ") == -1){
		checkResult(flagEleId,true,messageEleId,"��ȷ��","green");
	}else{
		if(must){
			checkResult(flagEleId,false,messageEleId,"��������ȷ���ݣ����ܰ����ո�","red");
		}else{
			checkResult(flagEleId,false,messageEleId,"","green");
		}
	}
}

//���ֻ��������λС�� ����
function moneryOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[0-9]([0-9]*|.[0-9]{1,2})/g)){
			checkResult(flagEleId,true,messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,false,messageEleId,"ֻ�������������λС������","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,false,messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,false,messageEleId,"","green");
		}
	}
}

//ֻ���������ֺ�Ӣ���»��ߺ�@
function numAndEnXAOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[a-zA-Z0-9_@]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��¼������4-20�����ֻ�Ӣ�Ļ�_@���","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//������֤������λ��Ϊ6-20λ������+��ĸ�����Ҳ����Ǵ����֣�����ĸ���������ַ�
//��������ո�
function passwordOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{6,20}$/)){
		//if(obj.match(/^(?![\d]+$)(?![a-zA-Z]+$).{6,20}$/)&&!obj.match(/((?=[\x21-\x7e]+)[^A-Za-z0-9])/)&&!checkStr(obj)&& obj.indexOf(' ')==-1){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"����Ϊ6-20λ���ֺ���ĸ��ϡ�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//������֤������λ��Ϊ6-20λ������+��ĸ������+�����ַ�����ĸ+�����ַ�������+��ĸ+�����ַ���ϣ����Ҳ����Ǵ����֣�����ĸ���������ַ�
//��������ո�
function passwordOnly1(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/(^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$)/) && obj.indexOf(' ')==-1){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"����λ��Ϊ6-20λ�������֡���ĸ�������ַ���϶��ɡ�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//ֻ������汾��
function versionNum(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[0-9]+(.[0-9]+)*$/) ){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"�汾�Ÿ�ʽ����ȷ","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//��ֹ����Ƿ��ַ�
function legalOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(!obj.match(/[<>$]+|script/) ){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��ֹ����Ƿ��ַ�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}


//��֤����
function checkStr(str){ 
	// [\u4E00-\uFA29]|[\uE7C7-\uE7F3]���ֱ��뷶Χ 
	var re1 = new RegExp("([\u4E00-\uFA29]|[\uE7C7-\uE7F3])"); 
	if (!re1.test(str)){ 
	return false; 
	} 
	return true; 
	} 
	//--> 

 

//��֤��ַ
function urlOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(checkStr(obj)||obj.match(/\s/)){
		//if(obj.match(/[\u4E00-\uFA29]|[\uE7C7-\uE7F3])/)){
			checkResult(flagEleId,"false",messageEleId,"�����������ĺͿո�","red");
		}else{
			//if(!obj.match(/^[http]+:\/\/(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$/) ){
			if(obj.match(/http:\/\/.+/) ){
				checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"��������ȷ����ַ","red");
			}
		}
		
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//ֻ����������,������0��ͷ
function positiveNumberOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[0-9]*$/g) && !obj.match(/^0+[\d]/) && !obj.match(/^0+0$/g) ){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��������ȷ�����֣�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//ֻ������һλС�� ���� ��߼�����,������0��ͷ
function freeOnly(obj,flagEleId,messageEleId,must){
	var text = obj;
	if(text!=""){
		if(obj.match(/^\d+(\.\d{0,1})?$/g)&& !obj.match(/^0+[\d]/) && !obj.match(/^0+0$/g)){
			checkResult(flagEleId,'true',messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,'false',messageEleId,"��������ȷ������,���һλС��,������0��ͷ","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,'false',messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,'true',messageEleId,"","green");
		}
	}
}

//��֤���������һλС�����ǡ�.0����������0��ͷ
function discountRateOnly1(obj,flagEleId,messageEleId,must){
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"�������пո�","red");
		}else{
			if(obj.match(/^\d+(\.0{0,1})?$/g) && !obj.match(/^0+[\d]/) && !obj.match(/^0+0$/g) ){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"��������ȷ������,���һλС������'.0',������0��ͷ","red");
		}
	 }
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//��֤�ۿۣ�С��1��С����С����������λ
function discountRateOnly(obj,flagEleId,messageEleId,must){
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"�������пո�","red");
		}else{
			if(obj.match(/^([0]\.[0-9]{1}[1-9]{1})$|^([0]\.[1-9]{1})$/g)){
				checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"ֻ��������0��1֮���С����С����������λ","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//ֻ���������ĺͣ���
function chineseAndEnblock(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\(\)\��\��\u4E00-\u9FA5]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"ֻ���������ĺͣ���","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}


//ֻ����ո���ʾ������������ո�---��Ʒ����
function notNullOnly2(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(obj.match(/\s/)){
		checkResult(flagEleId,"false",messageEleId,"�������пո�","red");
	}else{
		if(text!="" && obj.indexOf(" ") == -1){
			checkResult(flagEleId,true,messageEleId,"��ȷ��","green");
		}else{
			if(must){
				checkResult(flagEleId,false,messageEleId,"�����룡","red");
			}else{
				checkResult(flagEleId,false,messageEleId,"","green");
			}
		}
	}
	
}


//������ֻ����ո�
function notSpaceOnly(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
		if(text!=""){
			text = $.trim(obj);
			if(text!=""){
				checkResult(flagEleId,true,messageEleId,"��ȷ��","green");
			}else{
				checkResult(flagEleId,false,messageEleId,"������ֻ����ո�","red");
			}
		}else{
			if(must){
				checkResult(flagEleId,false,messageEleId,"�����룡","red");
			}else{
				checkResult(flagEleId,true,messageEleId,"","green");
			}
		}
	
}

//ֻ���������ġ�Ӣ�ĺͶ���
function chineseEnAndComma(obj,flagEleId,messageEleId,must){
//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/^[\,a-zA-Z\u4E00-\u9FA5]*$/g)){
			checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
		}else{
			checkResult(flagEleId,"false",messageEleId,"���������ġ�Ӣ�Ļ򶺺ţ���������ո�","red");
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//ֻ�ܴ����������
function moreThanZeroOnly(obj,flagEleId,messageEleId,must){
//		var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"�������пո�","red");
		}else{
			if(obj.match(/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/g)){
				checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"ֻ�����������������֣�С����������λ","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}
//���� ֻ�����������������һλС��
function gradeOnly(obj,flagEleId,messageEleId,must){
	//	var text = $.trim(obj);
	var text = obj;
	if(text!=""){
		if(obj.match(/\s/)){
			checkResult(flagEleId,"false",messageEleId,"�������пո�","red");
		}else{
			if(obj.match(/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{0,1})?$/g)){
				checkResult(flagEleId,"true",messageEleId,"��ȷ��","green");
			}else{
				checkResult(flagEleId,"false",messageEleId,"ֻ�����������������֣�С��������һλ","red");
			}
		}
	}else{
		if(must){
			checkResult(flagEleId,"false",messageEleId,"�����룡","red");
		}else{
			checkResult(flagEleId,"true",messageEleId,"","green");
		}
	}
}

//��Ʒ���ܲ��ܴ���100��
function notSpaceLengthOnly(obj,flagEleId,messageEleId,must){
//		var text = $.trim(obj);
		var text = obj;
			if(text!=""){
				text = $.trim(obj);
				if(text!=""){
					if(text.length <=100){
						checkResult(flagEleId,true,messageEleId,"��ȷ��","green");
					}else {
						checkResult(flagEleId,false,messageEleId,"����������С��100���ַ���","red");
					}
				}else{
					checkResult(flagEleId,false,messageEleId,"������ֻ����ո�","red");
				}
			}else{
				if(must){
					checkResult(flagEleId,false,messageEleId,"�����룡","red");
				}else{
					checkResult(flagEleId,true,messageEleId,"","green");
				}
			}
		
	}