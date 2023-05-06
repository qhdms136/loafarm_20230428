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
					<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
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