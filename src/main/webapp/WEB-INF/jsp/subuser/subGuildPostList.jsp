<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="subuser-list-content">
		<div class="fp-title">내 길드모임 신청목록</div>
		<div style="margin-top:50px;">
			<table class="sub-guildpost-table table table-hover text-center">
				<thead>
					<tr>
						<th>신청 게시물</th>
						<th>신청날짜</th>
						<th>신청상태</th>
						<th>삭제 / 신청취소</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${subGuildPostList}" var="subguildpost">
					<tr>
						<td><div class="subguildpost-subject"><a href="/guild/guild_detail_view?guildPostId=${subguildpost.guildpost.id}">${subguildpost.guildpost.subject}</a></div></td>
						<td><fmt:formatDate value="${subguildpost.subuser.createdAt}" pattern="yyyy-MM-dd"/></td>
						<c:choose>
						<c:when test="${subguildpost.subuser.state eq '거절'}">
							<td><span class="text-danger"><b>거절됨</b></span></td>
						</c:when>
						<c:when test="${subguildpost.subuser.state eq '대기중'}">
							<td><span class="text-secondary"><b>대기중</b></span></td>
						</c:when>
						<c:when test="${subguildpost.subuser.state eq '수락'}">
							<td><span class="text-primary"><b>수락됨</b></span></td>
						</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${subguildpost.subuser.state eq '거절'}">
								<td><button class="deleteBtn btn btn-sm btn-danger" data-post-id="${subguildpost.guildpost.id}">삭제</button></td>
							</c:when>
							<c:when test="${subguildpost.subuser.state eq '대기중' or subguildpost.subuser.state eq '수락'}">
								<td><button class="deleteBtn btn btn-sm btn-primary" data-post-id="${subguildpost.guildpost.id}">신청취소</button></td>
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
	$('.deleteBtn').on('click', function(){
		let postId = $(this).data("post-id");
		/* console.log(postId); */
		// ajax
		$.ajax({
			type:"DELETE"
			, url:"/subuser/delete"
			, data:{"postId":postId}
			// resposne
			, success:function(data){
				if(data.code == 1){
					alert("신청이 삭제/취소 되었습니다.");
					location.reload(true);
				} else{
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error){
				alert("신청목록을 삭제/취소 하는 중 오류가 발생했습니다.");
			}
		});
	});
});
</script>