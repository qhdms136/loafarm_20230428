<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="logo-font"><a href="/index/index_view">LoaFarm ������</a></div>
<!-- �α��� ���� -->
<div class="lg-btn">
	<c:choose>
		<c:when test="${not empty userId}">
		<div class="dropdown">
        	<span class="font-weight-bold">${userNickname}�� �ȳ��ϼ���</span>
	      <div class="dropdown-content">
	        <a href="/free/myfree_view?userId=${userId}">�� �Խñ�</a>
	        <a href="/custom/mycustom_view?userId=${userId}">�� Ŀ���͸���¡</a>
	        <a href="/guild/guild_my_view?userId=${userId}">�� ������ �Խñ�</a>
	        <a href="/subuser/mysub_list_view?userId=${userId}">�� ������ ��û���</a>
	        <c:if test="${userLoginId eq 'admin'}">
	         <a href="/notice/notice_create_view">�������� ����</a>
	        </c:if>
	      </div>
    	</div>
			
			<a href="/user/sign_out" class="ml-2 font-weight-bold">�α׾ƿ�</a>
		</c:when>
		<c:when test="${empty userId}">
			<button onclick="location.href='/user/sign_in_view'" class="btn btn-dark">�α���</button>
			<button onclick="location.href='/user/sign_up_view'"class="btn btn-primary mx-3">ȸ������</button>
		</c:when>
	</c:choose>
</div>