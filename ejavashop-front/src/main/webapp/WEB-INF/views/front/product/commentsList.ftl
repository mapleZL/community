
<!-- 单品页评论列表  全部评论 -->
<div class='rv-main-target comment-none comment-block' id='box1'>
	<div class='rv-target-item'>
		<#if commentsList??> 
			<#list commentsList as comments>
				<div class='rv-target-list'>
					<div class='rv-target-topic clearfix'>
						<div class='topic-avatar l'>
							<div class='avatar'>
								<img src='${(domainUrlUtil.EJS_STATIC_RESOURCES)!}/img/avatar.jpg'
									class='back'>
							</div>
							<div class='username'>
								<span>${(comments.userName)!''}</span>
							</div>
						</div>
						<div class='topic-main l'>
							<div class='topic-title clearfix'>
								<!-- <div class='fl'>
									<span class='commstar look-commstart' value="${(comment.grade)!''}">
										<a href='javascript:void(0);' class='star1'></a>
										<a href='javascript:void(0);' class='star2'></a>
										<a href='javascript:void(0);' class='star3'></a>
										<a href='javascript:void(0);' class='star4'></a>
										<a href='javascript:void(0);' class='star5 '></a>
									</span>
								</div> -->
							    ${(comments.grade)!''}
								<div class='rv-spring l star'>
									<em class='rv-spring on' style="width: 70px;"></em>
								</div>
							</div>
							<div class='topic-body'>
								<p class='body-content'>${(comments.content)!''}</p>
							</div>
							<div class='body-info clearfix'>
								<div class='date l'>
									<span>${(comments.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</#list> 
		</#if>
	</div>
</div>

<div class='com-table-footer'>
	<!-- 分页 -->
	<#noescape> ${ pm.pageNavigation} </#noescape>
</div>

<script type="text/javascript">
	//初始化星星 
	$(".commstar").each(function(){
		var index = $(this).attr('value');
		$(this).children(".star"+index).addClass("active");
	});
</script>
