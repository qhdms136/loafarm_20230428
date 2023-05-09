<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="fc-content d-flex justify-content-center">
	<div class="fc-write-content">
		<div class="fc-write-title">글쓰기</div>
		<div class="d-flex justify-content-center">
			<div class="fc-write-box">
				<select class="form-select form-control" id="category" name="category">
					<option value="">카테고리</option>
					<option value="잡담">잡담</option>
					<option value="투표">투표</option>
					<option value="스킬">스킬</option>
					<option value="정보">정보</option>
					<option value="기타">기타</option>
				</select>
				<div class="d-flex">
					<input type="text" id="subject" class="free-subject form-control" placeholder="제목을 입력해주세요">
				</div>
				<div class="d-flex">
					<textarea class="free-content form-control" id="content" rows="10" placeholder="내용을 입력해주세요"></textarea>
				</div>
				<div class="fc-file d-flex justify-content-between">
					<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif">
					<div class="fc-btn-box d-flex">
						<button class="fc-clear btn-sm btn-outline-dark" id="freeClearBtn">모두 지우기</button>
						<button class="fc-save mx-3 btn-sm btn-secondary" id="freeSaveBtn">저장하기</button>
						<button class="fc-list btn-sm" id="freeListBtn">목록</button>
					</div>
						
				</div>
					<img class="fc-img-box" src="/static/img/free/redgood.png" alt="업로드 된 이미지" width="200">
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	// 자유 게시판 목록을 이동
	$('#freeListBtn').on('click', function(){
		location.href="/free/free_list_view";
	});
	
	// 모두 지우기 버튼
	$('#freeClearBtn').on('click', function(){
		// 값을 넣으면 그 값으로 세팅
		$('#subject').val("");
		$('#content').val("");
	});
	
	// 글 저장 버튼
	$('#freeSaveBtn').on('click', function(){
		
		// validation
		let category = $("select[name='category']").val();
		let subject = $('#subject').val().trim();
		let content = $('#content').val();
		let file = $('#file').val(); // 파일 경로 C:\fakepath\아아아.jpg
		
		// 유효성 검사
		if(!category){
			alert("카테고리 목록을 골라주세요");
			return;
		}
		if(!subject){
			alert("제목을 입력해주세요");
			return;
		}
		if(!content){
			alert("내용을 입력해주세요");
			return;
		}
		
		// 파일 업로드 된 경우에만 확장자 체크
		if(file !=""){
			// 확장자만 뽑아서 소문자로 변환하고 검사
			let ext = file.split(".").pop().toLowerCase();
			// alert(ext);
			if($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1){
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val(""); // 실패해도 파일이 남겨지므로 비운다.
				return;
			}
		}
		
		console.log(category);
		console.log(subject);
		console.log(content);
		console.log(file);
		
	});
});
</script>