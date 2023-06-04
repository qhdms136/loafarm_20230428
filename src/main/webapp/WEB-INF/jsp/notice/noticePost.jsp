<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="noticepost-list-content">
		<div class="fp-title">공지사항</div>
		<div>
			<table class="noticepost-list-table table table-hover text-center">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${NoticePostList}" var="notice">
						<tr>
							<td>공지</td>
							<td class="text-left"><div class="noticepost-list-subject"><a href="#">${notice.subject}</a></div></td>
							<td><span>admin</span></td>
							<td><fmt:formatDate value="${notice.createdAt}" pattern="yyyy-MM-dd"/></td>
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
			            <span class="paging-unlink-ko">[이전]</span>
			        </c:when>
			        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
			        <c:otherwise>
			            <a class="paging-link-ko" href="/notice/notice_list_view?page=${paging.page-1}">[이전]</a>
			        </c:otherwise>
			    </c:choose>
			     <%--  for(int i=startPage; i<=endPage; i++)      --%>
			    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
			        <c:choose>
			            <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
			            <c:when test="${i eq paging.page}">
			                <span class="paging-unlink-number">&nbsp; ${i} &nbsp;</span>
			            </c:when>
			
			            <c:otherwise>
			                <a class="paging-link-number" href="/notice/notice_list_view?page=${i}"> &nbsp; ${i} &nbsp; </a>
			            </c:otherwise>
			        </c:choose>
			    </c:forEach>
			    <c:choose>
			   	     <c:when test="${paging.page>=paging.maxPage}">
						<span class="paging-unlink-ko">[다음]</span>
		 		     </c:when>
				   	 <c:otherwise>
				 	    <a class="paging-link-ko" href="/notice/notice_list_view?page=${paging.page+1}">[다음]</a>
				   	 </c:otherwise>
		   		 </c:choose>
			</div>
		</div>
		
	</div>
</div>
