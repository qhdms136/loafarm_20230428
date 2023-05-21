<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="cd-content">
		<div class="cd-header d-flex justify-content-bwteen">
			<div class="col-4 d-flex justify-content-start align-items-center">
				<span class="cd-user-name">${customPostView.user.nickname}</span>
			</div>
			<div class="col-4 d-flex justify-content-center align-items-center">
				<span><fmt:formatDate value="${customPostView.custompost.createdAt}" pattern="yyyy-MM-dd"/></span>
			</div>
			<div class="col-4"></div>
		</div>
		<div class="d-flex justify-content-center">
			<div class="cd-box">
				<div class="d-flex justify-content-center">
					<div class="cd-subject">${customPostView.custompost.subject}</div>
				</div>
				<div class="cd-img d-flex justify-content-center">
					<c:if test="${not empty customPostView.custompost.imagePath}">
						<img src="${customPostView.custompost.imagePath}" alt="업로드 이미지" width="500px;">
					</c:if>	
				</div>
				<div class="d-flex justify-content-center">
					<div class="cd-like-box d-flex justify-content-center align-items-center">	
						<c:choose>
						<%-- 추천하기가 눌려있지 않을 때 --%>
							<c:when test="${customPostView.filledRecommend eq false}">
								<a href="#" class="recommend-btn" data-post-id="${customPostView.custompost.id}" data-type="custom">
									<img src="/static/img/free/good_like_none.png" width="30" height="30" alt="recommend-none">
								</a>
							</c:when>
							<c:when test="${customPostView.filledRecommend eq true}">
								<a href="#" class="recommend-btn" data-post-id="${customPostView.custompost.id}" data-type="custom">
									<img src="/static/img/free/good_like_red.png" width="30" height="30" alt="recommend-none">
								</a>
							</c:when>
						</c:choose>
						<div class="cd-like-count">${customPostView.recommendCount}</div>
					</div>
				</div>
			</div>
		</div>
		<div class="cd-bottom d-flex justify-content-end align-items-center">
			<div>
				<button onclick="location.href='/custom/custom_list_view'" class="btn btn-outline-dark">목록</button>
				<c:choose>
					<c:when test="${userId eq customPostView.user.id}">
						<button onclick="location.href='/custom/custom_update_view?customPostId=${customPostView.custompost.id}'" class="mx-3 btn btn-dark">글수정</button>
					</c:when>
					<c:when test="${userId != customPostView.user.id}">
						<button onclick="location.href='/custom/custom_create_view'" class="mx-3 btn btn-dark">글쓰기</button>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div class="cd-comment-box">
			<div class="cd-comment-title border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
			</div>
			<div class="cd-comment-list">
				<c:forEach items="${customPostView.commentList}" var="comments">
					<c:if test="${customPostView.custompost.id eq comments.comment.postId and customPostView.custompost.type eq comments.comment.type}">
						<div class="d-flex">
							<div class="cd-comment-name">${comments.user.nickname}</div>
							<div class="cd-comment-date">(<fmt:formatDate value="${comments.comment.createdAt}" pattern="yyyy-MM-dd"/>)</div>
						</div>
						<div class="cd-comment-content">${comments.comment.content}
							<c:if test="${not empty comments.comment.content and (userId eq comments.comment.userId)}">
							<a href="#" class="commentDelBtn" data-comment-id="${comments.comment.id}">
								<img src="/static/img/free/garbage.png" width="25px" height="25px;">
							</a>
							</c:if>
						</div>
					</c:if>
				</c:forEach>
			</div>
			<div class="cd-comment-write d-flex">
						<input type="text" class="cd-comment-input form-control mr-2" placeholder="댓글 달기"/>
						<button type="button" class="cd-comment-btn btn btn-light" data-post-id="${customPostView.custompost.id}" data-type="${customPostView.custompost.type}">게시</button>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	// 추천하기 버튼 클릭
	$('.cd-like-box > a').on('click', function(e){
		e.preventDefault();
		
		let postId = $(this).data("post-id");
		let type = $(this).data("type");
		
		$.ajax({
			// request
			url:"/recommend/" + postId + "/" + type
			
			// response
			,success:function(data){
				if(data.code == 1){
					location.reload(true);
				} else{
					alert("추천 error 발생");
				}
			}
			,error:function(request, status, error){
				alert("추천하기 중 오류가 발생했습니다.");
			}
		});
	});
	
	// 댓글 게시 버튼
	$('.cd-comment-btn').on('click', function(){
		let postId = $(this).data("post-id");
		let content = $(this).prev().val();
		let type = $(this).data("type");
		
		console.log(postId);
		console.log(content);
		console.log(type);
		
		if(!content || content == ""){
			alert("댓글 내용을 입력해주세요.");
			return;
		}
		
		// ajax
		$.ajax({
			// request
			type:"POST"
			,url:"/comment/create"
			,data:{"postId":postId, "content":content, "type":type}
			,success:function(data){
				if(data.code == 1){
					location.reload(true);
				} else {
					alert(data.errorMessage);
				}
			}
			,error:function(request, status, error){
				alert("댓글 게시 중 시스템 오류가 발생했습니다.");
			}
		});
	});
	
	// 댓글 삭제 버튼
	$('.commentDelBtn').on('click', function(e){
		e.preventDefault();
		let commentId = $(this).data("comment-id");
		alert(commentId);
		
		// ajax
		$.ajax({
			// request
			url:"/comment/delete/" + commentId
			// response
			,success:function(data){
				if(data.code == 1){
					alert("댓글이 삭제되었습니다.");
					location.reload(true);
				}
			}
			,error:function(request, status, error){
				alert("댓글 삭제 중 오류가 발생했습니다.");
			}
		});
	})
});
</script>