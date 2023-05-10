<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="fp-content d-flex justify-content-center">
	<div class="fp-box">
		<div class="fp-title">자유 게시판</div>
		<div class="rc-btn">
			<a href="#" class="free-img reco1">10추글</a>
			<a href="#" class="free-img reco3">30추글</a>
		</div>
		<div class="cate-area">
			<a href="#" class="cate-area-one">전체</a>
			<a href="#" class="cate-area-one">잡담</a>
			<a href="#" class="cate-area-one">투표</a>
			<a href="#" class="cate-area-one">스킬</a>
			<a href="#" class="cate-area-one">정보</a>
			<a href="#" class="cate-area-one">기타</a>
		</div>
		<div>
			<table class="table text-center">
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
					<tr>
						<td>1</td>
						<td>자유게시판입니다.~~~~</td>
						<td>aaaa</td>
						<td>2023-05-08</td>
						<td>77</td>
					</tr>
					<tr>
						<td>1</td>
						<td>자유게시판입니다.~~~~</td>
						<td>aaaa</td>
						<td>2023-05-08</td>
						<td>77</td>
					</tr>
					<tr>
						<td>1</td>
						<td>자유게시판입니다.~~~~</td>
						<td>aaaa</td>
						<td>2023-05-08</td>
						<td>77</td>
					</tr>
					<tr>
						<td>1</td>
						<td>자유게시판입니다.~~~~</td>
						<td>aaaa</td>
						<td>2023-05-08</td>
						<td>77</td>
					</tr>
					<tr>
						<td>1</td>
						<td>자유게시판입니다.~~~~</td>
						<td>aaaa</td>
						<td>2023-05-08</td>
						<td>77</td>
					</tr>
				
				</tbody>
			</table>
		</div>
		<div class="d-flex justify-content-end">
			<button class="btn btn-outline-dark" id="freeListBtn">목록</button>
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
	
	// 글쓰기 버튼
	$('#freeWriteBtn').on('click', function(){
		location.href="/free/free_create_view";
	});
});
</script>