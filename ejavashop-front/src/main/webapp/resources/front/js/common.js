//  加入收藏
function AddFavorite(sURL, sTitle){
    try{
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e){
        try{
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e){
        	jAlert("您的浏览器不支持此操作，请使用Ctrl+D进行添加");
        }
    }
} 


/**
 * 判断是否是空
 * @param value
 */
function isEmpty(value){
	if(value == null || value == "" || value == "undefined" || value == undefined || value == "null"){
		return true;
	}
	else{
		value = value.replace(/\s/g,"");
		if(value == ""){
			return true;
		}
		return false;
	}
}



//刷新验证码
function refreshCode(){
	     jQuery("#code_img").attr("src",domain+"/verify.html?d"+new Date().getTime());
  }
 
 
 /**
  * 控制用户中心 左侧菜单栏选中
  * @param id 
  */
 function  checked(id){
	 $("#"+id).addClass("currnet_page");
 }
 
 
 	/**
	 * 增加手机号校验
	 */
 jQuery.validator.addMethod('isMobile', function(value, element) {
     return this.optional(element) || auth_phone(value);
 },"请输入正确的手机号");
	
	//验证手机号码
	function auth_phone(phone){
	    var re = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
	    return re.test(phone);
	}
	
	/**
	 * 增加邮箱校验
	 */
	jQuery.validator.addMethod('isEmail', function(value, element) {
        return this.optional(element) || auth_email(value);
    },"请输入正确的邮箱地址");

	function auth_email(value){
		// 验证邮箱
		var flag = false;
		var re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(re.test(value)==false){
		}else{
		   flag = true;
		}
		return flag;
	}
	
	
	/**
	 * 增加密码校验
	 */
	jQuery.validator.addMethod('passwordLength', function(value, element) {
	    return this.optional(element) || auth_password(value);
	},"密码长度在6～20字符之间");

	/**
	 * 增加用户名重复校验
	 */
	function auth_password(value){
		var flag = false;
		var password = value.replace(/' '/g, '');
		if (password.length < 6 || password.length > 20) {
		} else {
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * 增加大于0校验  商品评价
	 */
	jQuery.validator.addMethod('biggerThanZero', function(value, element) {
	    return biggerThanZero(value);
	},"请评分");

	/**
	 * 增加大于0校验
	 */
	function biggerThanZero(value){
		var flag = false;
		if (value==null||value==''||value == 0) {
		} else {
			flag = true;
		}
		return flag;
	}
	

	
	/**
	 * 动态加载进货单信息
	 */
	function refreshMycart(){
	 	 $.ajax({
			type:"GET",
			url:domain+"/previewMyCart.html?rd"+Math.random(),
			dataType:"html",
			success:function(data){
				//加载数据
				$("#priviewMycart").html(data);
				$(".addcart-goods-num").html($("#totalNumber").val());
			},
			error:function(){
				jAlert("异常，请重试！");
			}
		});
	}
	
	
	// 我的进货单
	$(".settleup").hover(function(){
		//刷新进货单
		refreshMycart();
		
		$(".settleup dd").css("display",'block');
		$(".settleup dt").css("border-bottom","1px solid #fff");
		$(".settleup dt b").css({"top":"7px","border-style":"dashed dashed solid","border-color":"transparent transparent #ccc"});
		if($(".incart-goods dl").length>4){
			$(".incart-goods-box").css({"height":"267px","overflow-y":"auto"});
		}else{
			$(".incart-goods-box").css({"hieght":"auto","overflow-y":"hidden"});
		}
		
	},function(){
		$(".settleup dt").css("border-bottom","1px solid #efefef");
		$(".settleup dd").css("display",'none');
		$(".settleup dt b").css({"top":"9px","border-style":"dashed dashed solid","border-color":" #CCC transparent transparent"});
	});
	
	