<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="gm-content">
		<div class="fp-title">내 길드모임 게시글</div>
		<div>
			<table class="gm-table table table-hover text-center">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
						<th>최대정원</th>
						<th>신청목록</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${guildMyList}" var="myguild">
					<tr>
						<td>${myguild.guildpost.id}</td>
						<td class="text-left"><a href="/guild/guild_detail_view?guildPostId=${myguild.guildpost.id}">${myguild.guildpost.subject}</a></td>
						<td>${myguild.user.nickname}</td>
						<td><fmt:formatDate value="${myguild.guildpost.createdAt}" pattern="yyyy-MM-dd"/></td>
						<td>${myguild.guildpost.maxCount}</td>
						<td><a href="#">신청목록</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
