<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="fc-write-content">
		<div class="fc-write-title">글수정</div>
		<div class="d-flex justify-content-center">
			<div class="fc-write-box">
				<select class="form-select form-control" id="category" name="category">
					<option value="${freePost.category}" selected>${freePost.category}</option>
					<option value="">카테고리</option>
					<option value="잡담">잡담</option>
					<option value="투표">투표</option>
					<option value="스킬">스킬</option>
					<option value="정보">정보</option>
					<option value="기타">기타</option>
				</select>
				<div class="d-flex">
					<input type="text" id="subject" class="free-subject form-control" placeholder="제목을 입력해주세요" value="${freePost.subject}">
				</div>
				<div class="d-flex">
					<textarea class="free-content form-control" id="content" rows="10" placeholder="내용을 입력해주세요">${freePost.content}</textarea>
				</div>
				<div class="fc-file d-flex justify-content-between">
					<input class="fu-file" type="file" id="file" accept=".jpg, .jpeg, .png, .gif">
					<a href="#" class="imageDeleteBtn" data-post-id="${freePost.id}">
						<img class="mr-5"src="/static/img/free/garbage.png" width="25px" height="25px">
					</a>	
					<div class="fc-btn-box d-flex">
						<button type="button" class="fc-clear btn-sm btn-outline-dark" id="freeDeleteBtn" data-post-id="${freePost.id}">삭제하기</button>
						<button type="button" class="fc-save mx-3 btn-sm btn-secondary" id="freeSaveBtn" data-post-id="${freePost.id}">저장하기</button>
						<button type="button" onclick="location.href='/free/free_list_view'" class="fc-list btn-sm" id="freeListBtn">목록</button>
					</div>
				</div>
				<c:if test="${not empty freePost.imagePath}">
					<img class="fu-img-box" src="${freePost.imagePath}" alt="업로드 된 이미지" width="200">
				</c:if>	
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	// 글수정 버튼
	$('#freeSaveBtn').on('click', function(){
		
		// validation
		let category = $("select[name='category']").val();
		let subject = $('#subject').val().trim();
		let content = $('#content').val();
		let file = $('#file').val(); // 파일 경로 C:\fakepath\아아아.jpg
		let freePostId = $(this).data("post-id");
		
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
		
	/* 	console.log(category);
		console.log(subject);
		console.log(content);
		console.log(file);
		console.log(freePostId); */
		
		// 파일 업로드 된 경우에만 확장자 체크
		
		
		// 파일 업로드 된 경우에만 확장자 체크
		if(file != ""){
			let ext = file.split(".").pop().toLowerCase();
			// alert(ext);
			if($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1){
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val("");
				return;
			}
		}
		
		// 이미지 업로드를 위해 폼태그 만들기
		let formData = new FormData();
		formData.append("freePostId", freePostId);
		formData.append("category", category);
		formData.append("subject", subject);
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]);
		console.log(formData);
		
		// ajax
		$.ajax({
			// request
			type:"PUT"
			, url:"/free/update"
			, data:formData
			, encType:"multipart/form-data"	// 파일 업로드 필수 설정
			, processData:false // 파일 업로드 필수 설정
			, contentType:false // 파일 업로드 필수 설정
			
			// response
			, success:function(data){
				if(data.code == 1){
					alert("자유 게시판 글이 수정되었습니다.");
					location.reload(true);
				}
			}
			, error:function(request, status, error){
				alert("자유 게시판 글수정이 실패했습니다.");
			}
		});
	});
	
	// 이미지 삭제 버튼
	$('.imageDeleteBtn').on('click', function(e){
		e.preventDefault();
		let freePostId = $(this).data("post-id");
		// alert(freePostId);
		
		// ajax
		$.ajax({
			// request
			type:"PUT"
			, url:"/free/delete_image"
			, data:{"freePostId":freePostId}
			, success:function(data){
				if(data.code == 1){
					alert("이미지가 삭제되었습니다.");
					location.reload(true);
				}
			}
			,error:function(request, status, error){
				alert("이미지를 삭제하는데 실패했습니다.");
			}
		});
	});
	
	// 게시물 삭제 버튼
	$('#freeDeleteBtn').on('click', function(){
		let freePostId = $(this).data("post-id");
/* 		alert(freePostId); */
		
		// ajax
		$.ajax({	//request
			type:"DELETE"
			, url:"/free/delete"
			, data:{"freePostId":freePostId}
			// response
			,success:function(data){
				if(data.code == 1){
					alert("삭제되었습니다.");
					location.href="/free/free_list_view";
				} else{
					alert(data.errorMessage);
				}
			}
			,error:function(request, status, error){
				alert("게시물을 삭제하는데 실패하였습니다.");
			}
		});
	});
});
	
</script>