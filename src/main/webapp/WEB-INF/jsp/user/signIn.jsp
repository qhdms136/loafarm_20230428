<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div class="d-flex justify-content-center">
	<div class="login-box">
		<h1 class="title-margin font-weight-bold">Sign In</h1>
		
		<%-- 키보드 Enter키로 로그인이 될 수 있도록 form 태그를 만들어준다.(submit 타입의 버튼이 동작됨) --%>
		<form id="loginForm" action="/user/sign_in" method="post">
			<div class="form-style2">
				<input type="text" id="loginId" name="loginId" autocomplete="off" required>
				<label for="loginId"><span>아이디</span></label>
			</div>
			<div class="form-style2">
				<input type="password" id="password" name="password" autocomplete="off" required>
				<label for="password"><span>Password</span></label>
			</div>
			
			<%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
			<div class="login-margin">
			<input type="submit" id="loginBtn" class="btn btn-block btn-outline-dark my-3" value="로그인">
			<a class="sign-btn btn btn-block btn-primary" href="/user/sign_up_view">회원가입</a>
			</div>
		</form>
	</div>
</div>