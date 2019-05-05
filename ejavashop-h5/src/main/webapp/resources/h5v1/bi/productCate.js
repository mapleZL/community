$(function () {
  
	loginInfoInit();
  
	sessionStorage.removeItem("count");
	$('.con_left a').click(function (){
   		var _this=$(this);
   		_this.addClass('cur');
   		_this.siblings().removeClass("cur")
   		var cateName = _this.data("cate-name");
   		$(".typechild").each(function() {
   			if ($(this).data("parent-cate-name") == cateName) 
   				$(this).show();
   			else
   				$(this).hide();
   		});
	});
});