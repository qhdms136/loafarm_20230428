<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="fp-content d-flex justify-content-center">
	<div class="fp-write-content">
		<div class="fp-write-title">글쓰기</div>
		<div class="d-flex justify-content-center">
			<div class="fp-write-box">
				<select class="form-select form-control">
					<option>카테고리</option>
					<option value="잡담">잡담</option>
					<option value="투표">투표</option>
					<option value="스킬">스킬</option>
					<option value="정보">정보</option>
					<option value="기타">기타</option>
				</select>
				<div class="d-flex">
					<input type="text" id="subject" class="free-subject form-control" placeholder="제목을 입력해주세요">
				</div>
				<div class="d-flex">
					<textarea class="free-content form-control" id="content" rows="10"></textarea>
				</div>
			</div>
		</div>
	</div>
</div>