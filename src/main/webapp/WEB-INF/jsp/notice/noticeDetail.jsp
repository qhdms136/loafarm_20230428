<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="noticedetail-content">
		<div class="noticedetail-header d-flex justify-content-bwteen">
			<div class="col-4 d-flex justify-content-start align-items-center">
				<span class="noticedetail-user-name">admin</span>
			</div>
			<div class="col-4 d-flex justify-content-center align-items-center">
				<span><fmt:formatDate value="${notice.createdAt}" pattern="yyyy-MM-dd"/></span>
			</div>
			<div class="col-4"></div><!-- 3개의 박스를 일정하게 가운데로 맞추기 위해 -->
		</div>
		<div class="d-flex justify-content-center">
			<div class="noticedetail-box">
				<div class="noticedetail-subject">${notice.subject}</div>
				<div class="noticedetail-text">${notice.content}</div>
			</div>
		</div>
		<div class="noticedetail-bottom d-flex justify-content-end align-items-center">
			<div>
				<button onclick="location.href='/notice/notice_list_view'" class="btn btn-outline-dark">목록</button>
				<c:choose>
					<c:when test="${userId eq notice.userId}">
						<button onclick="location.href='/notice/notice_update_view?postId=${notice.id}'" class="mx-3 btn btn-dark">글수정</button>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</div>
