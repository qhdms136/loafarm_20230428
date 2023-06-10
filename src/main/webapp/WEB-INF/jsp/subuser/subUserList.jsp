<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="subuser-list-content">
		<div class="fp-title">내 길드모임 게시글</div>
		<div>
			<div class="mt-4 mr-4 d-flex justify-content-end align-items-center">
			<span style="font-size:18px;"><b>${subcount} / ${guildpost.maxCount}</b></span><span class="mx-3"><b>최대정원</b></span>
			</div>
			<table class="subuser-table table table-hover text-center">
				<thead>
					<tr>
						<th>글쓴이</th>
						<th>내용</th>
						<th>신청날짜</th>
						<th>신청상태</th>
						<th>수락/거절</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${subUserList}" var="subuser">
					<tr>
						<td>${subuser.user.nickname}</td>
						<td class="text-left"><div class="subuser-content"><a href="/subuser/subuser_more_view?id=${subuser.subuser.id}" target="_blank">${subuser.subuser.content}</a></div></td>
						<td><fmt:formatDate value="${subuser.subuser.createdAt}" pattern="yyyy-MM-dd"/></td>
						<c:choose>
							<c:when test="${subuser.subuser.state eq '대기중'}">
								<td><span class="text-secondary"><b>${subuser.subuser.state}</b></span></td>
							</c:when>
							<c:when test="${subuser.subuser.state eq '수락'}">
								<td><span class="text-primary"><b>${subuser.subuser.state}</b></span></td>
							</c:when>
							<c:when test="${subuser.subuser.state eq '거절'}">
								<td><span class="text-danger"><b>${subuser.subuser.state}</b></span></td>
							</c:when>
						</c:choose>
				
						<c:choose>
						<c:when test="${subuser.subuser.state eq '대기중'}">
							<td><button class="subStateBtn btn btn-sm btn-primary"data-user-id="${subuser.subuser.userId}" data-post-id="${subuser.subuser.postId}" data-state="수락">수락</button><button  class="subStateBtn btn btn-sm btn-danger" data-user-id="${subuser.subuser.userId}" data-post-id="${subuser.subuser.postId}" data-state="거절">거절</button></td>
						</c:when>
						<c:when test="${subuser.subuser.state eq '수락'}">
							<td><button class="btn btn-sm btn-primary" disabled>수락</button></td>
						</c:when>
						<c:when test="${subuser.subuser.state eq '거절'}">
							<td><button class="btn btn-sm btn-danger" disabled>거절</button></td>
						</c:when>
						</c:choose>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	$('.subStateBtn').on('click', function(){
		let userId = $(this).data("user-id");
		let postId = $(this).data("post-id");
		let state = $(this).data("state");
		/* console.log(userId);
		console.log(postId);
		console.log(state); */
		
		// ajax
		$.ajax({
			type:"PUT"
			,url:"/subuser/update"
			,data:{"userId":userId, "postId":postId, "state":state}
		// resposne
			,success:function(data){
				if(data.code == 1){
					// 성공
					location.reload(true);
				}
			}
			,error:function(request, status, error){
				alert("수락/거절 하는데 실패했습니다.");
			}
		});
	});
});
</script>