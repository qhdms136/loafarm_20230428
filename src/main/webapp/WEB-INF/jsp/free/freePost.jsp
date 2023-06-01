<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="fp-content">
		<div class="fp-title">자유 게시판</div>
		<div class="rc-btn">
			<a href="/free/free_list_view?recommendCount=10" class="free-img reco1">10추글</a>
			<a href="/free/free_list_view?recommendCount=30" class="free-img reco3">30추글</a>
		</div>
		<div class="cate-area">
			<a href="/free/free_list_view" class="cate-area-one">전체</a>
			<a href="/free/free_list_view?category=잡담"  class="cate-area-one">잡담</a>
			<a href="/free/free_list_view?category=투표"  class="cate-area-one">투표</a>
			<a href="/free/free_list_view?category=스킬"  class="cate-area-one">스킬</a>
			<a href="/free/free_list_view?category=정보"  class="cate-area-one">정보</a>
			<a href="/free/free_list_view?category=기타"  class="cate-area-one">기타</a>
		</div>
		<div>
			<table class="free-table table table-hover text-center">
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
						<td class="text-left"><div class="free-table-content"><a href="/free/free_detail_view?freePostId=${free.freepost.id}&type=${free.freepost.type}">[${free.freepost.category}]  &nbsp;${free.freepost.subject}</a></div></td>
						<td>${free.user.nickname}</td>
						<td><fmt:formatDate value="${free.freepost.createdAt}" pattern="yyyy-MM-dd"/></td>
						<td>${free.freepost.recommendCount}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="d-flex justify-content-center">
			<div>
				<c:choose>
			        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
			        <c:when test="${paging.page<=1}">
			            <span>[이전]</span>
			        </c:when>
			        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
			        <c:otherwise>
			            <a href="/free/free_list_view?category=${category}&page=${paging.page-1}&recommendCount=${recommendCount}">[이전]</a>
			        </c:otherwise>
			    </c:choose>
			     <%--  for(int i=startPage; i<=endPage; i++)      --%>
			    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
			        <c:choose>
			            <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
			            <c:when test="${i eq paging.page}">
			                <span>${i}</span>
			            </c:when>
			
			            <c:otherwise>
			                <a href="/free/free_list_view?category=${category}&page=${i}&recommendCount=${recommendCount}">${i}</a>
			            </c:otherwise>
			        </c:choose>
			    </c:forEach>
			    <c:choose>
			   	     <c:when test="${paging.page>=paging.maxPage}">
						<span>[다음]</span>
		 		     </c:when>
				   	 <c:otherwise>
				 	    <a href="/free/free_list_view?category=${category}&page=${paging.page+1}&recommendCount=${recommendCount}">[다음]</a>
				   	 </c:otherwise>
		   		 </c:choose>
			</div>
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