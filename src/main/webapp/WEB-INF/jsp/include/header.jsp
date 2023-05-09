<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<div class="logo-font"><a href="/index/index_view">LoaFarm 갤러리</a></div>
<!-- 로그인 정보 -->
<div class="lg-btn">
	<c:choose>
		<c:when test="${not empty userNickname}">
			<span class="font-weight-bold">${userNickname}님 안녕하세요</span>
			<a href="/user/sign_out" class="ml-2 font-weight-bold">로그아웃</a>
		</c:when>
		<c:when test="${empty userNickname}">
			<button onclick="location.href='/user/sign_in_view'" class="btn btn-outline-dark">로그인</button>
			<button onclick="location.href='/user/sign_up_view'"class="btn btn-outline-primary mx-3">회원가입</button>
		</c:when>
	</c:choose>
</div>