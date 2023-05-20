<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>     
<div class="d-flex justify-content-center">    
	<div class="cp-content">
		<div class="cp-title">커스터마이징 자랑</div>
		<div class="cp-btn-box d-flex justify-content-end">
			<button class="mx-3 btn btn-outline-primary">최신순</button>
			<button class="mx-3 btn btn-outline-danger ">추천순</button>
			<button class="mx-3 btn btn-outline-dark">목록</button>
			<button class="mx-3 btn btn-dark" id="customWriteBtn">글쓰기</button>
		</div>
		<div class="cp-list-box">
		<c:forEach items="${customPostList}" var="custom">
			<div class="cp-box">
				<div class="d-flex justify-content-center">
				<a href="/custom/custom_detail_view?customPostId=${custom.custompost.id}&type=${custom.custompost.type}">
					<img src="${custom.custompost.imagePath}" alt="커스터마이징" width="250"
						height="250">
				</a>				
				</div>
				<div class="custom-info-box d-flex justify-content-center">
					<div class="card">
						<div class="card-subject mt-2 ml-2"><a href="/custom/custom_detail_view?customPostId=${custom.custompost.id}&type=${custom.custompost.type}">${custom.custompost.subject}</a></div>
						<div class="card-nickname my-2 ml-2">${custom.user.nickname}</div>
						<div class="mb-3 d-flex justify-content-end">
							<img src="/static/img/index/recommend.png" alt="추천" width="25">
							<span class="mx-3">${custom.custompost.recommendCount}</span>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	// 글쓰기 버튼 및 글쓰기 권한 필터링
	$('#customWriteBtn').on('click', function(){
		let userId = '<%=(Integer)session.getAttribute("userId")%>';
		 if(userId == "null"){
			alert("글쓰기 권한이 없습니다. 로그인을 해주세요.");
			location.href="/user/sign_in_view";
		} 
		location.href="/custom/custom_create_view";
	});
});
</script>