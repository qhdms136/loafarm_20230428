<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="fd-content">
		<div class="fd-header d-flex justify-content-bwteen">
			<div class="col-4 d-flex justify-content-start align-items-center">
				<span class="fd-user-name">${freePostView.user.loginId}</span>
			</div>
			<div class="col-4 d-flex justify-content-center align-items-center">
				<span><fmt:formatDate value="${freePostView.freepost.createdAt}" pattern="yyyy-MM-dd"/></span>
			</div>
			<div class="col-4"></div>
		</div>
		<div class="d-flex justify-content-center">
			<div class="fd-box">
				<div class="fd-category">[${freePostView.freepost.category}]</div>
				<div class="fd-subject">${freePostView.freepost.subject}</div>
				<div class="fd-img d-flex justify-content-center">
					<c:if test="${not empty freePostView.freepost.imagePath}">
						<img src="${freePostView.freepost.imagePath}" alt="업로드 이미지" width="350px;">
					</c:if>	
				</div>
				<div class="fd-text">${freePostView.freepost.content}</div>
				<div class="d-flex justify-content-center">
					<div class="fd-like-box d-flex justify-content-center align-items-center">	
						<a href="#" class="recommend-btn" data-post-id="${freePostView.freepost.id}" data-type="free">
							<img src="/static/img/free/good_like_none.png" width="30" height="30" alt="recommend-none">
						</a>
						<div class="fd-like-count">77</div>
					</div>
				</div>
			</div>
		</div>
		<div class="fd-bottom d-flex justify-content-end align-items-center">
			<div>
				<button onclick="location.href='/free/free_list_view'" class="btn btn-outline-dark">목록</button>
				<c:choose>
					<c:when test="${userId eq freePostView.user.id}">
						<button class="mx-3 btn btn-dark">글수정</button>
					</c:when>
					<c:when test="${userId != freePostView.user.id}">
						<button class="mx-3 btn btn-dark">글쓰기</button>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div class="fd-comment-box">
			<div class="fd-comment-title border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
			<div class="fd-comment-write d-flex">
						<input type="hidden" name="type" value="자유">
						<input type="text" class="fd-comment-input form-control mr-2" placeholder="댓글 달기"/>
						<button type="button" class="fd-comment-btn btn btn-light">게시</button>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	// 추천하기 버튼 클릭
	$('.fd-like-box > a').on('click', function(e){
		e.preventDefault();
		
		let postId = $(this).data("post-id");
		let type = $(this).data("type");
		alert(postId + ":::" + type);
		
		$. ajax({
			// request
			url:"/recommend/" + postId + "/" + type
			
			// response
			,success:function(data){
				if(data.code == 1){
					location.reload();
				} else{
					alert("추천 error 발생");
				}
			}
			,error:function(request, status, error){
				alert("추천하기 중 오류가 발생했습니다.");
			}
		});
	});
});
</script>