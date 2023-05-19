<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="cc-write-content">
		<div class="cc-write-title">커스터마이징 글쓰기</div>
		<div class="d-flex justify-content-center">
			<div class="cc-write-box">
				<div class="d-flex">
					<input type="text" id="subject" class="custom-subject form-control" placeholder="제목을 입력해주세요">
				</div>
				<div class="cc-file d-flex justify-content-between">
					<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif">
					<div class="cc-btn-box d-flex">
						<button type="button" class="cc-clear btn-sm btn-outline-dark" id="customClearBtn">모두 지우기</button>
						<button type="button" class="cc-save mx-3 btn-sm btn-secondary" id="customSaveBtn" data-type="custom">저장하기</button>
						<button type="button" class="cc-list btn-sm" id="customListBtn">목록</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	// 커스터마이징 목록으로 이동
	$('#customListBtn').on('click', function(){
		location.href="/custom/custom_list_view";
	});
	
	// 모두 지우기 버튼
	$('#customClearBtn').on('click', function(){
		$('#subject').val("");
	});
	
	// 글 저장 버튼
	$('#customSaveBtn').on('click', function(){
		// validation
		let subject = $('#subject').val().trim();
		let type = $(this).data("type");
		let file = $('#file').val(); //파일 경로
		
		console.log(subject);
		console.log(type);
		console.log(file);
		
		// 유효성 검사
		if(!subject){
			alert("제목을 입력해주세요");
			return;
		}
		if(!file){
			alert("이미지파일을 넣어주세요");
			return;
		}
		
		// 파일 업로드시 확장자 체크
		if(file != ""){
			// 확장자만 뽑아서 소문자로 변환 후 검사
			let ext = file.split(".").pop().toLowerCase();
			//alert(ext);
			if($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1){
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val(""); // 파일 업로드 실패 시 파일명 비우기
				return;
			}
		}
		
		// ajax
		// 이미지 업로드 시 form 필수
		let formData = new FormData();
		formData.append("subject", subject);
		formData.append("type", type);
		formData.append("file", $('#file')[0].files[0]);
		console.log(formData);
		
		$.ajax({
			// request
			type:"POST"
			, url:"/custom/create"
			, data:formData
			, enctype:"multipart/form-data"	// 파일 업로드를 위한 필수 설정
			, processData:false // 파일 업로드를 위한 필수 설정
			, contentType:false // 파일 업로드를 위한 필수 설정
			// response
			,success:function(data){
				if(data.code == 1){
					alert("글이 저장되었습니다.");
					location.href="/custom/custom_list_view"
				} else {
					alert(data.errorMessage);
				}
			}
			,error:function(request, status, error){
				alert("글을 저장하는데 실패했습니다.");
			}
		});
	})
});
</script>