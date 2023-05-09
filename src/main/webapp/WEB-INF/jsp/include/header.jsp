<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<div class="logo-font"><a href="/index/index_view">LoaFarm ������</a></div>
<!-- �α��� ���� -->
<div class="lg-btn">
	<c:choose>
		<c:when test="${not empty userNickname}">
			<span class="font-weight-bold">${userNickname}�� �ȳ��ϼ���</span>
			<a href="/user/sign_out" class="ml-2 font-weight-bold">�α׾ƿ�</a>
		</c:when>
		<c:when test="${empty userNickname}">
			<button onclick="location.href='/user/sign_in_view'" class="btn btn-outline-dark">�α���</button>
			<button onclick="location.href='/user/sign_up_view'"class="btn btn-outline-primary mx-3">ȸ������</button>
		</c:when>
	</c:choose>
</div>