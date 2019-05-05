jQuery(document).ready(function($){
	var $form_modal = $('.cd-user-modal'),
		$form_login = $form_modal.find('#cd-login'),
		$form_signup = $form_modal.find('#cd-signup'),
		$form_modal_tab = $('.cd-switcher'),
		$tab_login = $form_modal_tab.children('li').eq(0).children('a'),
		$tab_signup = $form_modal_tab.children('li').eq(1).children('a'),
		$main_nav = $('.main_nav');

	//弹出窗口
	$main_nav.on('click', function(event){
		$form_modal.addClass('is-visible');	
	});

	//关闭弹出窗口
	$('.cd-user-modal').on('click', function(event){
		if( $(event.target).is($form_modal) || $(event.target).is('.cd-close-form') ) {
			$form_modal.removeClass('is-visible');
		}	
	});

	//关闭弹出窗口
	$('.cd-close-form').click(function(event){
		$form_modal.removeClass('is-visible');
	});
	
	//使用Esc键关闭弹出窗口
	$(document).keyup(function(event){
    	if(event.which=='27'){
    		$form_modal.removeClass('is-visible');
	    }
    });

	//切换表单
	$form_modal_tab.on('click', function(event) {
		event.preventDefault();
		( $(event.target).is( $tab_login ) ) ? login_selected() : signup_selected();
	});

	function login_selected(){
		$form_login.addClass('is-selected');
		$form_signup.removeClass('is-selected');
		$form_forgot_password.removeClass('is-selected');
		$tab_login.addClass('selected');
		$tab_signup.removeClass('selected');
	}

	function signup_selected(){
		$form_login.removeClass('is-selected');
		$form_signup.addClass('is-selected');
		$form_forgot_password.removeClass('is-selected');
		$tab_login.removeClass('selected');
		$tab_signup.addClass('selected');
	}
	
	var navUrl = location.href;
	navUrl = navUrl.replace(domain, "");
	if (navUrl == "" || navUrl == "/") {
		$(".indexMenu span").eq(0).addClass("on");
	}
	else {
		var indexMenus = new Array("member/index.html:4", "/index.html:0", "productTypeList.html:1", "detail.html:2", "order.html:3");
		for (var i = 0; i < indexMenus.length; i ++) {
			var im = indexMenus[i].split(":");
			if (navUrl.indexOf(im[0]) > 0) {
				$(".indexMenu span").eq(im[1]).addClass("on");
				break;
			}
		}
	}
	
	$(".am-header-left a").click(function() {
		var historyUrl = document.referrer;
		if (historyUrl.indexOf("login.html") > 0 || historyUrl.indexOf("register.html") > 0 || historyUrl.indexOf("forgetpassword.html") > 0) {
			location.href = domain + "/index.html";
		}
		else {
			history.go(-1);
		}
	});
	
});

function confirm_(showMsg, obj, callback) {
	if ($("#c-confirm").length > 0) {
		$("#c-confirm .model_box").html(showMsg);
		$('#c-confirm').modal({
			relatedTarget: obj,
	        onConfirm: function(options) {
	        	var $t = $(this.relatedTarget);
	        	try {callback($t);} catch(e){}
	        },
	        onCancel: function() {
	        	$("body").removeClass("am-dimmer-active");
	        	$(".am-dimmer").remove();
	        	$("#c-confirm").remove();
	        }
	      });
		return;
	}
	var confirmHtml = '<div class="am-modal am-modal-confirm" tabindex="-1" id="c-confirm" style="top:30%">'+
	'<div class="am-modal-dialog" style="border-radius:5px;">'+
	'<div class="model_box em2">' + showMsg + '</div>'+
	'<div class="am-modal-footer" style="padding:10px 0">'+
	'<span class="am-modal-btn em2" data-am-modal-confirm>确定</span>'+
	'<span class="am-modal-btn em1" data-am-modal-cancel>取消</span>'+
	'</div></div></div>';
	$("body").append(confirmHtml);
	$('#c-confirm').modal({
		relatedTarget: obj,
        onConfirm: function(options) {
        	var $t = $(this.relatedTarget);
        	try {callback($t);} catch(e){}
        },
        onCancel: function() {
        	$("body").removeClass("am-dimmer-active");
        	$(".am-dimmer").remove();
        	$("#c-confirm").remove();
        }
	});
}

function alert_(showMsg, obj, callback) {
	if ($("#c-alert").length > 0) {
		$("#c-alert .model_box").html(showMsg);
		$('#c-alert').modal({
			relatedTarget: obj,
	        onConfirm: function(options) {
	        	var $t = $(this.relatedTarget);
	        	try {callback($t);} catch(e){}
	        }
		});
		return;
	}
	var confirmHtml ='<div class="am-modal am-modal-alert" tabindex="-1" id="c-alert" style="top:30%">'+
	'<div class="am-modal-dialog" style="border-radius:5px;">'+
	'<div class="model_box em2">' + showMsg + '</div>'+
	'<div class="am-modal-footer" style="padding:10px 0">'+
	'<span class="am-modal-btn em2" data-am-modal-confirm>确定</span>'+
	'</div></div></div>';
	$("body").append(confirmHtml);
	$('#c-alert').modal({
		relatedTarget: obj,
        onConfirm: function(options) {
        	var $t = $(this.relatedTarget);
        	try {callback($t);} catch(e){}
        }
	});
}

function remove_(obj) {
	obj.fadeTo("slow", 0.01, function(){//fade
		$(this).slideUp("slow", function() {//slide up
			$(this).remove();//then remove from the DOM
			return true;
		});
	});
}

//异步加载用户登录信息
function loginInfoInit() {
	$.ajax({
		type:"POST",
		url:domain+"/getloginuser.html",
		success:function(data) {
			if (data.success) {
				if (data.data != null && data.data.name != null) {
					$(".index-search-login").html("退出").click(function() {
						confirm_("您确定要退出登录吗？", this,function(t) {
							//deleteMemberInfo();
							window.location.href = domain + "/logout.html";
						});
					});
				}
				else {
					$(".index-search-login").click(function() {
						location.href = domain + "/login.html";
					});
				}
			}
		},
		error:function() {
		}
	});
}

function deleteMemberInfo(){
	websqlOpenDB();
	websqlDeleteAllDataFromTable("MEMBER");
}

var isLoading = false;		//页面滚动到最后，是否正在加载中状态。。。
