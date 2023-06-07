<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<c:forEach items="${customPostList}" var="custom">
			<div class="cp-box">
				<div class="card-img d-flex justify-content-center">
				<a href="/custom/custom_detail_view?customPostId=${custom.custompost.id}&type=${custom.custompost.type}">
					<img src="${custom.custompost.imagePath}" alt="커스터마이징" width="250"
						height="250">
				</a>				
				</div>
				<div class="custom-info-box d-flex justify-content-center">
					<div class="card">
						<div class="card-subject mt-2 ml-2"><a href="/custom/custom_detail_view?customPostId=${custom.custompost.id}&type=${custom.custompost.type}">${custom.custompost.subject}</a></div>
						<div class="card-nickname my-2 ml-2">${custom.user.nickname}</div>
						<div class="mb-3 d-flex justify-content-end">
							<img src="/static/img/index/recommend.png" alt="추천" width="25">
							<span class="custom-recommendCount mx-3">${custom.custompost.recommendCount}</span>
						</div>
					</div>
				</div>
			</div>
</c:forEach>    