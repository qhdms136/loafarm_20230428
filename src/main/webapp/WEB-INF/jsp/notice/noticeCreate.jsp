<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<div class="d-flex justify-content-center">
	<div class="noticecreate-write-content">
		<div class="noticecreate-write-title">공지사항</div>
		<div class="d-flex justify-content-center">
			<div class="noticecreate-write-box">
				<div class="d-flex">
					<input type="text" id="subject" class="notice-subject form-control" placeholder="제목을 입력해주세요">
				</div>
				<div class="d-flex">
					<textarea class="notice-content form-control" id="content" rows="10" placeholder="내용을 입력해주세요"></textarea>
				</div>
				<div class="noticecreate-file d-flex justify-content-end">
					<c:if test="${userLoginId eq 'admin'}">
						<div class="noticecreate-btn-box d-flex">
							<button type="button" class="noticecreate-clear btn-sm btn-outline-dark" id="noticeClearBtn">모두 지우기</button>
							<button type="button" class="noticecreate-save mx-3 btn-sm btn-secondary" id="noticeSaveBtn">저장하기</button>
							<button type="button" class="noticecreate-list btn-sm" id="noticeListBtn">목록</button>
						</div>					
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	// 공지사항 목록 이동
	$('#noticeListBtn').on('click', function(){
		location.href="/notice/notice_list_view";
	});
	
	// 모두 지우기 버튼
	$('#noticeClearBtn').on('click', function(){
		// 값을 넣으면 그 값으로 세팅
		$('#subject').val("");
		$('#content').val("");
	});
	
	// 글 저장 버튼
	$('#noticeSaveBtn').on('click', function(){
		// validation
		let subject = $('#subject').val().trim();
		let content = $('#content').val();
		
		if(!subject){
			alert("제목을 입력해주세요");
			return;
		}
		if(!content){
			alert("내용을 입력해주세요");
			return;
		}
		
		console.log(subject);
		console.log(content);
		
		// ajax
		$.ajax({
			// request
			type:"POST"
			, url:"/notice/create"
			, data:{"subject":subject, "content":content}
			// response
			, success:function(data){
				if(data.code == 1){
					// 성공
					alert("글이 저장되었습니다.");
					location.href="/notice/notice_list_view";
				} else{
					// 실패
					alert("글 저장하기 중 오류가 발생했습니다.");
				}
			}
			, error:function(request, status, error){
				alert("글을 저장하는데 실패하였습니다.");
			}
		});
	});
});
</script>