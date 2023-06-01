<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="gp-content">
		<div class="gp-title">길드모임</div>
		
		<div>
			<table class="guild-table table table-hover text-center">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
						<th>최대정원</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${guildPostList}" var="guild">
					<tr>
						<td>${guild.guildpost.id}</td>
						<td class="text-left"><div class="guild-table-content"><a href="/guild/guild_detail_view?guildPostId=${guild.guildpost.id}">${guild.guildpost.subject}</a></div></td>
						<td>${guild.user.nickname}</td>
						<td><fmt:formatDate value="${guild.guildpost.createdAt}" pattern="yyyy-MM-dd"/></td>
						<td>${guild.guildpost.maxCount}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="d-flex justify-content-end">
			<button onclick="location.href='/free/free_list_view'" class="btn btn-outline-dark" id="guildListBtn">목록</button>
			<button class="btn btn-dark mx-4" id="guildWriteBtn">글쓰기</button>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	// 글쓰기 버튼 및 글쓰기 권한 필터링
	$('#guildWriteBtn').on('click', function(){
		let userId = '<%=(Integer)session.getAttribute("userId")%>';
		 if(userId == "null"){
			alert("글쓰기 권한이 없습니다. 로그인을 해주세요.");
			location.href="/user/sign_in_view";
		} 
	location.href="/guild/guild_create_view";			
	});
});
</script>