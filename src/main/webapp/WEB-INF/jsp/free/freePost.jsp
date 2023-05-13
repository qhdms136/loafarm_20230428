<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="fp-content">
		<div class="fp-title">자유 게시판</div>
		<div class="rc-btn">
			<a href="#" class="free-img reco1">10추글</a>
			<a href="#" class="free-img reco3">30추글</a>
		</div>
		<div class="cate-area">
			<a href="/free/free_list_view" class="cate-area-one">전체</a>
			<a href="/free/free_list_view?category=잡담" class="cate-area-one">잡담</a>
			<a href="/free/free_list_view?category=투표" class="cate-area-one">투표</a>
			<a href="/free/free_list_view?category=스킬" class="cate-area-one">스킬</a>
			<a href="/free/free_list_view?category=정보" class="cate-area-one">정보</a>
			<a href="/free/free_list_view?category=기타" class="cate-area-one">기타</a>
		</div>
		<div>
			<table class="table table-hover text-center">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
						<th>추천</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${freePostList}" var="free">
					<tr>
						<td>${free.freepost.id}</td>
						<td><a href="/free/free_detail_view?freePostId=${free.freepost.id}&userId=${free.user.id}">[${free.freepost.category}]  &nbsp;${free.freepost.subject}</a></td>
						<td>${free.user.nickname}</td>
						<td><fmt:formatDate value="${free.freepost.createdAt}" pattern="yyyy-MM-dd"/></td>
						<td>77</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="d-flex justify-content-end">
			<button onclick="location.href='/free/free_list_view'" class="btn btn-outline-dark" id="freeListBtn">목록</button>
			<button class="btn btn-dark mx-4" id="freeWriteBtn">글쓰기</button>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	
	// 카테고리 필터링 버튼 클릭시 스타일 유지
	$('a.cate-area-one').on('click', function(){
		 $('a.cate-area-one').removeClass("active");
		 $('a.cate-area-one').css('color', "#000000");
		 $(this).css('color',"#ffffff"); 
		 $(this).addClass("active");
	});
	
	// 글쓰기 버튼 및 글쓰기 권한 필터링
	$('#freeWriteBtn').on('click', function(){
		let userId = '<%=(Integer)session.getAttribute("userId")%>';
		 if(userId == "null"){
			alert("글쓰기 권한이 없습니다. 로그인을 해주세요.");
			location.href="/user/sign_in_view";
		} 
		location.href="/free/free_create_view";
	});
});
</script>