$('.stop1').click(function (){
	confirm_("您确定要退出登录吗？", this, function(t) {
		deleteMemberInfo();
		goUrl(t);
	});
});
function goUrl (){
	window.location.href = domain + "/logout.html";
}

function deleteMemberInfo(){
	websqlOpenDB();
	websqlDeleteAllDataFromTable("MEMBER");
}
