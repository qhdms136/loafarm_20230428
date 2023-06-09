<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="mycustompost-content">
		<div class="fp-title">내 커스터마이징</div>
		<div>
			<table class="mycustompost-table table table-hover text-center">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>날짜</th>
						<th>추천수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customPosList}" var="custompost">
						<tr>
							<td>${custompost.id}</td>
							<td class="text-left"><div class="custompost-subject"><a href="/custom/custom_detail_view?customPostId=${custompost.id}&type=${custompost.type}">${custompost.subject}</a></div></td>
							<td><fmt:formatDate value="${custompost.createdAt}" pattern="yyyy-MM-dd"/></td>
							<td>${custompost.recommendCount}</td>
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
			            <a class="paging-link-ko" href="/free/myfree_view?page=${paging.page-1}&userId=${userId}">[이전]</a>
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
			                <a class="paging-link-number" href="/free/myfree_view?page=${i}&userId=${userId}"> &nbsp; ${i} &nbsp; </a>
			            </c:otherwise>
			        </c:choose>
			    </c:forEach>
			    <c:choose>
			   	     <c:when test="${paging.page>=paging.maxPage}">
						<span class="paging-unlink-ko">[다음]</span>
		 		     </c:when>
				   	 <c:otherwise>
				 	    <a class="paging-link-ko" href="/free/myfree_view?page=${paging.page+1}&userId=${userId}">[다음]</a>
				   	 </c:otherwise>
		   		 </c:choose>
			</div>
		</div>
		
	</div>
</div>
