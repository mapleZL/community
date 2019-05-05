var curLocation = location.href;
if(curLocation.indexOf("html") < 0){
	var element1 = $("#ft")[0].children[0];
	$(element1).css("color","#48D1CC");
	var element2 = element1.firstElementChild.firstElementChild;
	$(element2).css({"background":"url('/resources/h5/img/icon/icon_home_click.png') 0 0 no-repeat","background-size":"30px 30px"});
}else if(curLocation.indexOf("productTypeList.html") > 0){
	var element1 = $("#ft")[0].children[1];
	$(element1).css("color","#48D1CC");
	var element2 = element1.firstElementChild.firstElementChild;
	$(element2).css({"background":"url('/resources/h5/img/icon/icon_commodity_click.png') 0 0 no-repeat","background-size":"30px 30px"});
}else if(curLocation.indexOf("cart/detail.html") > 0){
	var element1 = $("#ft")[0].children[2];
	$(element1).css("color","#48D1CC");
	var element2 = element1.firstElementChild.firstElementChild;
	$(element2).css({"background":"url('/resources/h5/img/icon/icon_cart_click.png') 0 0 no-repeat","background-size":"30px 30px"});
}else if(curLocation.indexOf("member/order.html") > 0){
	var element1 = $("#ft")[0].children[3];
	$(element1).css("color","#48D1CC");
	var element2 = element1.firstElementChild.firstElementChild;
	$(element2).css({"background":"url('/resources/h5/img/icon/icon_order_click.png') 0 0 no-repeat","background-size":"30px 30px"});
}else if(curLocation.indexOf("member/index.html") > 0){
	var element1 = $("#ft")[0].children[4];
	$(element1).css("color","#48D1CC");
	var element2 = element1.firstElementChild.firstElementChild;
	$(element2).css({"background":"url('/resources/h5/img/icon/icon_member_click.png') 0 0 no-repeat","background-size":"30px 30px"});
}