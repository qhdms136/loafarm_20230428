<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sign-up">
	<div class="d-flex justify-content-center">
		<div class="sign-up-box">
			<h1 class="m-4 font-weight-bold">Sign Up</h1>
			<form id="signUpForm" action="/user/sign_up" method="post">
			<div class="d-flex">
				<div class="form-style1">
					<input type="text" id="loginId" name="loginId" autocomplete="off" required>
					<label for="loginId"><span>아이디</span></label>
				</div>
				<%-- 인풋 옆에 중복확인 버튼을 옆에 붙이기 위해 div 만들고 d-flex --%>
					<button type="button" id="loginIdCheckBtn" class="btn btn-outline-dark btn-xs">중복확인</button>
			</div>		
				<%-- 아이디 체크 결과 --%>
				<div class="hidden-box">
					<div id="idCheckValid" class="small text-danger d-none">ID는 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.</div>
					<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
					<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
				</div>
			<div class="d-flex">	
				<div class="form-style1">
					<input type="text" id="nickname" name="nickname" autocomplete="off" required>
					<label for="nickname"><span>Nickname</span></label>
				</div>
				<%-- 인풋 옆에 중복확인 버튼을 옆에 붙이기 위해 div 만들고 d-flex --%>
					<button type="button" id="nicknameCheckBtn" class="btn btn-outline-dark btn-xs">중복확인</button>
			</div>
				<%-- 닉네임 체크 결과 --%>
				<div class="hidden-box">
					<div id="nicknameCheckValid" class="small text-danger d-none">닉네임은 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.</div>
					<div id="nicknameCheckDuplicated" class="small text-danger d-none">이미 사용중인 닉네임입니다.</div>
					<div id="nicknameCheckOk" class="small text-success d-none">사용 가능한 닉네임 입니다.</div>
				</div>	
				<div class="form-style1">
					<input type="password" id="password" name="password" autocomplete="off" required>
					<label for="password"><span>Password</span></label>
				</div>
				<div class="form-style1">
					<input type="password" id="confirmPassword" name="confirmPassword" autocomplete="off" required>
					<label for="confirmPassword"><span>Confirm password</span></label>
				</div>	
				<div class="form-style1">
					<input type="text" id="email" name="email" autocomplete="off" required>
					<label for="email"><span>이메일</span></label>
				</div>
				<br>
				<div class="d-flex justify-content-center m-3">
					<button type="submit" id="signUpBtn" class="btn btn-dark">가입하기</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	// 중복 확인 버튼 클릭(ID)
	$('#loginIdCheckBtn').on('click', function(){
		// hidden 경고문구 초기화
		$('#idCheckValid').addClass("d-none");
		$('#idCheckDuplicated').addClass("d-none");
		$('#idCheckOk').addClass("d-none");
		
		let loginId = $('#loginId').val().trim();
		// 아이디 유효성 검사
		var regId = /^[a-zA-Z0-9]{4,12}$/;
		if(!regId.test(loginId)){
			$('#idCheckValid').removeClass("d-none");
			return false;
		}
		
		// ajax
		$.ajax({
			// request
			url:"/user/is_duplicated_id"
			,data:{"loginId":loginId}
		
			// response
			,success:function(data){
				if(data.result){
					// 중복
					$('#idCheckDuplicated').removeClass("d-none");
				} else{
					$('#idCheckOk').removeClass("d-none");
				}
			}
		});
	});
	// 닉네임 중복확인 버튼 클릭
	$('#nicknameCheckBtn').on('click', function(){
		// hidden 경고문 초기화
		$('#nicknameCheckValid').addClass("d-none");
		$('#nicknameCheckDuplicated').addClass("d-none");
		$('#nicknameCheckOk').addClass("d-none");
		
		let nickname = $('#nickname').val().trim();
		
		// 닉네임 유효성 검사
		var regNickname = /^[a-zA-Z0-9]{4,12}$/;
		if(!regNickname.test(nickname)){
			$('#nicknameCheckValid').removeClass("d-none");
			return false;
		}
		
		// ajax
		$.ajax({
			// request
			url:"/user/is_duplicated_nickname"
			,data:{"nickname":nickname}
		
			// response
			,success:function(data){
				if(data.result){
					// 중복
					$('#nicknameCheckDuplicated').removeClass("d-none");
				} else{
					$('#nicknameCheckOk').removeClass("d-none");
				}
			}
		});
	});
	// 회원가입
	$('#signUpForm').on('submit', function(e){
		e.preventDefault();
		
		// validation
		let loginId = $('#loginId').val().trim();
		let nickname = $('#nickname').val().trim();
		let password = $('#password').val();
		let confirmPassword = $('#confirmPassword').val();
		let email = $('#email').val().trim();
		
		// 이메일 유효성 검사
		var regEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		
		// 비밀번호 유효성 검사
		var regPw = /^[a-zA-Z0-9]{4,12}$/;
		if(!regPw.test(password)){
			alert("비밀번호는 4~12자 영문 대소문자, 숫자만 입력하세요.");
			return false;
		}
		
		if(password != confirmPassword){
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
		
		if($('#idCheckOk').hasClass("d-none")){
			alert("아이디 중복 확인을 다시 해주세요");
			return false;
		}
		
		if($('#nickname').hasClass("d-none")){
			alert("닉네임 중복 확인을 다시 해주세요");
			return false;
		}
		
		if(!regEmail.test(email)){
			alert("잘못된 이메일 형식입니다.");
			return false;
		}
		
		// ajax
		let url = $(this).attr("action");
		/* console.log(url); */
		let params = $(this).serialize();
		/* console.log(params); */
		
		$.post(url, params) // request
		.done(function(data){
			if(data.code == 1){
				alert("가입을 환영합니다!!! 로그인을 해주세요.");
				location.href="/user/sign_in_view";
			} else{
				alert(data.errorMessage);
			}
		});
	});
	
});
</script>