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
					<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif">
					<div class="fc-btn-box d-flex">
						<button type="button" class="fc-clear btn-sm btn-outline-dark" id="freeClearBtn">삭제하기</button>
						<button type="button" class="fc-save mx-3 btn-sm btn-secondary" id="freeSaveBtn" data-type="free">저장하기</button>
						<button type="button" class="fc-list btn-sm" id="freeListBtn">목록</button>
					</div>
				</div>
				<c:if test="${not empty freePost.imagePath}">
					<img class="fc-img-box" src="${freePost.imagePath}" alt="업로드 된 이미지" width="200">
				</c:if>	
			</div>
		</div>
	</div>
</div>
<script>

	
</script>