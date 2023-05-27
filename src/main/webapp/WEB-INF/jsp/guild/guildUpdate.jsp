<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="gc-write-content">
		<div class="gc-write-title">길드모임 글수정</div>
		<div class="d-flex justify-content-center">
			<div class="gc-write-box">
				<div class="d-flex">
					<input type="text" id="subject" class="free-subject form-control" placeholder="제목을 입력해주세요" value="${guildPost.subject}">
				</div>
				<div class="address-box d-flex">
				<input class="form-control" type="text" id="guild_address" placeholder="주소" readonly value="${guildPost.address}">
				<input class="btn btn-outline-dark" type="button" onclick="sample5_execDaumPostcode()" value="주소 검색">
				</div>
				<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
				
				<div class="number-box">
					<span><b>길드모임 최대 인원수</b></span><input type="number" min="5" max="50" class="ml-2" id="maxCount" value="${guildPost.maxCount}">
				</div>
				<div class="d-flex">
					<textarea class="free-content form-control" id="content" rows="10" placeholder="내용을 입력해주세요">${guildPost.content}</textarea>
				</div>
				<div class="d-flex justify-content-end">
					<div class="gc-btn-box d-flex">
						<button type="button" class="gc-clear btn-sm btn-outline-dark" id="guildClearBtn" data-post-id="${guildPost.id}">모두 지우기</button>
						<button type="button" class="gc-save mx-3 btn-sm btn-secondary" id="guildSaveBtn" data-post-id="${guildPost.id}">저장하기</button>
						<button type="button" class="gc-list btn-sm" id="guildListBtn">목록</button>
					</div>
				</div>	
			</div>
		</div>
	</div>
</div>
<script>
// 카카오 지도 api
function sample5_execDaumPostcode() {
      new daum.Postcode({
          oncomplete: function(data) {
              var addr = data.address; // 최종 주소 변수

              // 주소 정보를 해당 필드에 넣는다.
              document.getElementById("guild_address").value = addr;
           
          }
      }).open();
 }
 
$(document).ready(function(){
	// 길드 게시판 목록을 이동
	$('#guildListBtn').on('click', function(){
		location.href="/guild/guild_list_view";
	});

	// 모두 지우기 버튼
	$('#guildClearBtn').on('click', function(){
		// 값을 넣으면 그 값으로 세팅
		$('#guild_address').val("");
		$('#subject').val("");
		$('#maxCount').val("");
		$('#content').val("");
	});
	
	// 글 저장 버튼
	$('#guildSaveBtn').on('click', function(){
		
		// validation
		let subject = $('#subject').val().trim();
		let address = $('#guild_address').val().trim();
		let maxCount = $('#maxCount').val();
		let content = $('#content').val();
		let postId = $(this).data("post-id");
		// 유효성 검사
		if(!subject){
			alert("제목을 입력해주세요");
			return;
		}
		if(!address){
			alert("주소를 입력해주세요");
			return;
		}
		if(!maxCount){
			alert("최대 정원수를 입력해주세요");
			return;
		}
		if(maxCount > 50 || maxCount < 5){
			alert("최소인원은 5명부터이며 최대인원은 50명입니다.");
		}
		if(!content){
			alert("내용을 입력해주세요");
			return;
		}
		
		console.log(subject);
		console.log(address);
		console.log(maxCount);
		console.log(content);
		console.log(postId)
		// ajax
		 $.ajax({
			type:"PUT"
			, url:"/guild/update"
			, data:{"postId":postId, "subject":subject, "address":address, "maxCount":maxCount, "content":content}
			// response
			,success:function(data){
				if(data.code == 1){
					// 성공
					alert("글이 수정되었습니다.");
					location.reload(true);
				} else {
					// 중복
					alert(data.errorMessage);
				}
			}
			,error:function(request, status, error){
				alert("글을 수정하는데 실패했습니다.");
			}
		});
		
	});
});

</script>