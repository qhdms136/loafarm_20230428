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
<link rel="stylesheet" type="text/css" href="/static/css/style2.css">
</head>
<!-- 로그인 후 뒤로가기 막기 -->
<script type="text/javascript">
 window.history.forward();
 function noBack(){window.history.forward();}
 window.onunload=function(){null};
</script>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
	<div id="wrap">
		<section class="content">
			<jsp:include page="../${view}.jsp"/>
		</section>
		<footer class="d-flex justify-content-center align-items-center">
			<jsp:include page="../include/footer.jsp"/>
		</footer>
	</div>
</body>
</html>