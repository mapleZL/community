function typenews(rows,page,id){
	$.ajax({
		url:domain+"/index/typeNews.html?typeid="+id+
			"&rows="+rows+"&page="+page+"&date_="+new Date().getTime(),
		success:function(e){
			$(".article-right").html(e);
		}
	});
}