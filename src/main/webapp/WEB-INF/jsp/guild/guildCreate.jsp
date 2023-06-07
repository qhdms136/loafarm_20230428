 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="gc-write-content">
		<div class="gc-write-title">길드모임 글쓰기</div>
		<div class="d-flex justify-content-center">
			<div class="gc-write-box">
				<div class="d-flex">
					<input type="text" id="subject" class="free-subject form-control" placeholder="제목을 입력해주세요">
				</div>
				<div class="address-box d-flex">
				<input class="form-control" type="text" id="guild_address" placeholder="주소" readonly>
				<input class="btn btn-outline-dark" type="button" onclick="sample5_execDaumPostcode()" value="주소 검색">
				</div>
				<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
				
				<div class="number-box">
					<span><b>길드모임 최대 인원수</b></span><input type="number" min="5" max="50" class="ml-2" id="maxCount">
				</div>
				<div class="d-flex">
					<textarea class="free-content form-control" id="content" rows="10" placeholder="내용을 입력해주세요"></textarea>
				</div>
				<div class="d-flex justify-content-end">
					<div class="gc-btn-box d-flex">
						<button type="button" class="gc-clear btn-sm btn-outline-dark" id="guildClearBtn">모두 지우기</button>
						<button type="button" class="gc-save mx-3 btn-sm btn-secondary" id="guildSaveBtn">저장하기</button>
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
			alert("최소인원은 5명이며 최대인원은 50명입니다.");
			return;
		}
		if(!content){
			alert("내용을 입력해주세요");
			return;
		}
		/* console.log(subject);
		console.log(address);
		console.log(maxCount);
		console.log(content); */
		$.ajax({
			//request
			url:"/guild/is_duplicated_id"
			//response
			,success:function(data){
				if(data.result){
					// 이미 작성한 글 존재
					alert("길드모임 글은 아이디당 1개만 가능합니다 \n"
							+ "이미 작성하신 글이 있다면 삭제하고 작성해주세요");
					location.href="/guild/guild_list_view";
				} else{
					// ajax 글 작성 가능
					 $.ajax({
						type:"POST"
						, url:"/guild/create"
						, data:{"subject":subject, "address":address, "maxCount":maxCount, "content":content}
						// response
						,success:function(data){
							if(data.code == 1){
								// 성공
								alert("글이 저장되었습니다.");
								location.href="/guild/guild_list_view";
							} else {
								// 중복
								alert(data.errorMessage);
							}
						}
						,error:function(request, status, error){
							alert("글을 저장하는데 실패했습니다.");
						}
					});
				}
			}
		});
	});
});

</script>