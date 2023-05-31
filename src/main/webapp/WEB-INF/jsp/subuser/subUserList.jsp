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
			<table class="gm-table table table-hover text-center">
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
						<td class="text-left">${subuser.subuser.content}</td>
						<td><fmt:formatDate value="${subuser.subuser.createdAt}" pattern="yyyy-MM-dd"/></td>
						<td>${subuser.subuser.state}</td>
						
						<c:choose>
						<c:when test="${subuser.subuser.state eq '대기중'}">
							<td><button class="btn btn-sm btn-primary">수락</button><button  class="btn btn-sm btn-danger">거절</button></td>
						</c:when>
						<c:when test="${subuser.subuser.state eq '수락'}">
							<td><div>수락</div></td>
						</c:when>
						</c:choose>
						
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
