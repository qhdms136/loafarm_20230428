<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoaFarm 갤러리</title>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<!-- bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<!-- bootstrap css -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<!-- 내가 만든 스타일 시트 -->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">
</head>
<body>
	<div id="wrap">
		<header class="d-flex align-items-center justify-content-between"">
				<span class="logo-font">LoaFarm 갤러리</span>
				<div class="lg-btn">
					<button class="btn btn-outline-dark">로그인</button>
					<button class="btn btn-outline-primary mx-3">회원가입</button>
				</div>
		</header>
		<nav class="menu">
				<ul class="nav nav-fill">
					<li class="nav-item">
						<a class="nav-link" href="#">자유 게시판</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">커스터마이징 자랑</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">길드모임</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">공지</a>
					</li>
				</ul>
		</nav>
		<section>
			<div>
				<div class="img-size"></div>
			</div>
		</section>
		<footer></footer>
	</div>
</body>
</html>