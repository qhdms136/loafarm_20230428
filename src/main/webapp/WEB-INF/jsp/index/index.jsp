<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="img-container">
	<div class="slide-container">
		<div class="slide-image">
			<a class="slide-image" href="https://lostark.game.onstove.com/Promotion/Pcroom/230329" target="_blank">
				<img src="/static/img/index/loafarmbena.png">
			</a>
		</div>	
		<div class="slide-image">
			<a class="slide-image" href="https://lostark.game.onstove.com/GameGuide/Pages/%EC%B4%88%EC%8B%AC%EC%9E%90%EB%A5%BC%20%EC%9C%84%ED%95%9C%20FAQ" target="_blank">
				<img src="/static/img/index/loafarmbena4.jpg">
			</a>
		</div>
		<div class="slide-image">
			<a class="slide-image" href="https://lostark.game.onstove.com/Promotion/Attendcheck/230426" target="_blank">
				<img src="/static/img/index/loafarmbena3.jpg">
			</a>
		</div>
		<div class="slide-image">
			<a class="slide-image" href="https://lostark.game.onstove.com/Promotion/Update/230222/Gargadis" target="_blank">
				<img src="/static/img/index/loafarmbena5.jpg">
			</a>
		</div>
		<div class="slide-image">
			<a class="slide-image" href="https://lostark.game.onstove.com/Promotion/Update/230222/Ivorytower" target="_blank">
				<img src="/static/img/index/loafarmbena6.jpg">
			</a>
		</div>
	</div>
	
	<div class="slide-container">
		<div class="slide-image">
			<a class="slide-image" href="https://lostark.game.onstove.com/Promotion/Pcroom/230329" target="_blank">
				<img src="/static/img/index/loafarmbena.png">
			</a>
		</div>	
		<div class="slide-image">
			<a class="slide-image" href="https://lostark.game.onstove.com/GameGuide/Pages/%EC%B4%88%EC%8B%AC%EC%9E%90%EB%A5%BC%20%EC%9C%84%ED%95%9C%20FAQ" target="_blank">
				<img src="/static/img/index/loafarmbena4.jpg">
			</a>
		</div>
		<div class="slide-image">
			<a class="slide-image" href="https://lostark.game.onstove.com/Promotion/Attendcheck/230426" target="_blank">
				<img src="/static/img/index/loafarmbena3.jpg">
			</a>
		</div>
		<div class="slide-image">
			<a class="slide-image" href="https://lostark.game.onstove.com/Promotion/Update/230222/Gargadis" target="_blank">
				<img src="/static/img/index/loafarmbena5.jpg">
			</a>
		</div>
		<div class="slide-image">
			<a class="slide-image" href="https://lostark.game.onstove.com/Promotion/Update/230222/Ivorytower" target="_blank">
				<img src="/static/img/index/loafarmbena6.jpg">
			</a>
		</div>
	</div>
</div>



<div class="d-flex justify-content-center">
	<div class="loa-hot-board">
		<div class="ml-3 mt-2">
			<span class="lf-hot">로아팜 화제글</span> <span class="lf-today">오늘의
				화제</span>
			<button class="btn-sm btn-dark" onclick="location.href='/free/free_list_view?recommendCount=10'"
				style="font-size: 12px; margin-left: 40px;">10추 더보기</button>
		</div>
		<div class="today-content">
			<ul class="today-list mt-2">
			<c:forEach items="${freePostList}" begin="0" end="9" var="free">
				<li>
					<div class="today-subject">
					<a href="/free/free_detail_view?freePostId=${free.freepost.id}&type=${free.freepost.type}">
						<span class="cate text-primary">${free.freepost.category}</span>
						<span class="txt">${free.freepost.subject}</span>
					</a>
					</div>
				</li>
			</c:forEach>	
			</ul>
		</div>
	</div>
</div>
<div class="content2">
	<span class="cm-subject">나만의 멋진 캐릭터 꾸미기!</span>
	<div class="conent2-1 d-flex justify-content-center mt-4">
		<c:forEach items="${customPostList}" var="custom">
		<div class="custom-best-box">
			<div class="card-img mt-4 d-flex justify-content-center">
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
						<span class="custom-recommendCount mx-3">${custom.custompost.recommendCount}</span>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
</div>
<div class="content3 d-flex">
	<div class="article-left">
		<div
			class="article-title d-flex justify-content-between align-items-center">
			<span class="notice-title">공지</span>
			<button class="mr-5 btn-sm btn-dark" onclick="location.href='/notice/notice_list_view'">더보기</button>
		</div>
		<div class="d-flex justify-content-center">
			<div class="article-tb mt-3">
				<table class="table text-center">
					<tbody>
						<c:forEach items="${NoticePostList}"  begin="0" end="4" var="notice">
							<tr>
								<td>공지</td>
								<td class="text-left"><div class="noticepost-list-subject"><a href="/notice/notice_detail_view?postId=${notice.id}">${notice.subject}</a></div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="article-right">
		<div class="article-title d-flex justify-content-between align-items-center">
			<span class="guild-title">길드모임</span>
			<button class="mr-5 btn-sm btn-dark" onclick="location.href='/guild/guild_list_view'">더보기</button>
		</div>
		<div class="d-flex justify-content-center">
			<div class="article-tb mt-3">
				<table class="index-guild table text-center">
					<tbody>
						<c:forEach items="${GuildPostList}"  begin="0" end="4" var="guild">
							<tr>
								<td style="height:30px;">길드모임</td>
								<td class="text-left"><div class="guild-table-content"><a href="/guild/guild_detail_view?guildPostId=${guild.id}">${guild.subject}</a></div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>