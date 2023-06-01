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
						<td>${subguildpost.subuser.state}</td>
						<td><button class="btn btn-sm btn-danger">삭제</button></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>