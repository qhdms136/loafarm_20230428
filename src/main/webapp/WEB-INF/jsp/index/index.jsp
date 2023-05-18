<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div>
	<div class="img-size"></div>
</div>
<div class="d-flex justify-content-center">
	<div class="loa-hot-board">
		<div class="ml-3 mt-2">
			<span class="lf-hot">로아팜 화제글</span> <span class="lf-today">오늘의
				화제</span>
			<button class="btn-sm btn-dark"
				style="font-size: 12px; margin-left: 40px;">30추 더보기</button>
		</div>
		<div class="today-content">
			<ul class="today-list">
			<c:forEach items="${freePostList}" begin="0" end="9" var="free">
				<li>
					<a href="#">
						<span class="cate text-primary">${free.freepost.category}</span>
						<span class="txt">${free.freepost.subject}</span>
					</a>
				</li>
			</c:forEach>	
			</ul>
		</div>
	</div>
</div>
<div class="content2">
	<span class="cm-subject">나만의 멋진 캐릭터 꾸미기!</span>
	<div class="conent2-1 d-flex justify-content-center mt-4">
		<div class="custom-best-box">
			<div class="mt-4 d-flex justify-content-center">
				<img src="/static/img/index/custom.jpg" alt="커스터마이징" width="250"
					height="250">
			</div>
			<div class="custom-info-box d-flex justify-content-center">
				<div class="card">
					<div class="card-subject mt-2 ml-2">제 커스터마이징 입니다~~</div>
					<div class="card-nickname my-2 ml-2">aaaa</div>
					<div class="mb-3 d-flex justify-content-end">
						<img src="/static/img/index/recommend.png" alt="추천" width="25">
						<span class="mx-3">77</span>
					</div>
				</div>
			</div>
		</div>
		<div class="custom-best-box"></div>
		<div class="custom-best-box"></div>
	</div>
</div>
<div class="content3 d-flex">
	<div class="article-left">
		<div
			class="article-title d-flex justify-content-between align-items-center">
			<a href="#" class="notice-title">공지</a>
			<button class="mr-5 btn-sm btn-dark">더보기</button>
		</div>
		<div class="d-flex justify-content-center">
			<div class="article-tb mt-3">
				<table class="table text-center">
					<tbody>
						<tr>
							<td>공지</td>
							<td>공지입니다.~~~~~</td>
						</tr>
						<tr>
							<td>공지</td>
							<td>공지입니다.~~~~~</td>
						</tr>
						<tr>
							<td>공지</td>
							<td>공지입니다.~~~~~</td>
						</tr>
						<tr>
							<td>공지</td>
							<td>공지입니다.~~~~~</td>
						</tr>
						<tr>
							<td>공지</td>
							<td>공지입니다.~~~~~</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="article-right">
		<div
			class="article-title d-flex justify-content-between align-items-center">
			<a href="#" class="guild-title">길드모임</a>
			<button class="mr-5 btn-sm btn-dark">더보기</button>
		</div>
		<div class="d-flex justify-content-center">
			<div class="article-tb mt-3">
				<table class="table text-center">
					<tbody>
						<tr>
							<td>길드모임</td>
							<td>길드모임입니다.~~~~~</td>
						</tr>
						<tr>
							<td>길드모임</td>
							<td>길드모임입니다.~~~~~</td>
						</tr>
						<tr>
							<td>길드모임</td>
							<td>길드모임입니다.~~~~~</td>
						</tr>
						<tr>
							<td>길드모임</td>
							<td>길드모임입니다.~~~~~</td>
						</tr>
						<tr>
							<td>길드모임</td>
							<td>길드모임입니다.~~~~~</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>