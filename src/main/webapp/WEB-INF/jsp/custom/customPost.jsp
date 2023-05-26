<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>     
<div class="d-flex justify-content-center">    
	<div class="cp-content">
		<div class="cp-title">커스터마이징 자랑</div>
		<div class="cp-btn-box d-flex justify-content-end">
			<button data-type="1"onclick="location.href='/custom/custom_list_view?type=1'" class="typeBtn mx-3 btn btn-outline-primary">최신순</button>
			<button data-type="2" onclick="location.href='/custom/custom_list_view?type=2'" class="typeBtn mx-3 btn btn-outline-danger">추천순</button>
			<button class="mx-3 btn btn-dark" id="customWriteBtn">글쓰기</button>
		</div>
		<div id="customListBox" class="">
			<div class="cp-list-box d-flex flex-wrap justify-content-start">
				<c:forEach items="${customPostList}" var="custom">
					<div class="cp-box">
						<div class="d-flex justify-content-center">
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
									<span class="mx-3">${custom.custompost.recommendCount}</span>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		<div id="more" style="width:100%; height:200px;" class="bg-primary"></div>
		</div>
		
	</div>
<script>
	$('.typeBtn').click(function(){
	    var type = $(this).data("type");
	    console.log(type);
	});
	var footDiv = document.getElementById('more');
	var customListBox = document.getElementById('customListBox');
	var parent = footDiv.parentElement;
	console.log();
	var cnt = 1;
	customListBox.onscroll = function(e){
		var rect = footDiv.getBoundingClientRect().top - customListBox.getBoundingClientRect().top;
		console.log(rect);
		

		let option = {
			    threshold: 1,
		}
		const observer = new IntersectionObserver(entries => {
			 
			entries.forEach((entry) => {	 
			    // 관찰 대상 들어옴 true  
				if (entry.isIntersecting) {
			        	console.log('화면에서 노출됨');
			        	settime = setTimeout(() => $.ajax({
			        			type:"POST"
				        		,url:"/custom/more"
				        		,data:{"cnt":cnt}
			        			,success:function(data){
			        				if(data.code == 1){
			        				 // ajax
			    		           			 $.ajax({
					    		            	url:"/custom/custom_more_list_view"
					    		    	        ,data:{"cnt":cnt}
					    		    	        ,success:function(data){
					    		    	        	cnt += 1;
					    				            console.log(cnt);
					    		    	          $('.cp-list-box').append(data);
					    		    	        }
					    		            });
				        			} else {
				        				return;
				        			}
			        			}
			        	}), 500); // 불려지는 시간 설정
			        	
			        	observer.unobserve(footDiv);
			        	return;
			        } else {
			            console.log('화면에서 제외됨');
			            return;
			        }
			    })
			}, option);
		if(rect == 400) {
		  	//실행할 로직 (콘텐츠 추가)
			observer.observe(footDiv);
		  	return;
		} else {
			return;
		}
	};
	//observer.observe(target);
$(document).ready(function(){
	
	// 글쓰기 버튼 및 글쓰기 권한 필터링
	$('#customWriteBtn').on('click', function(){
		let userId = '<%=(Integer)session.getAttribute("userId")%>';
		 if(userId == "null"){
			alert("글쓰기 권한이 없습니다. 로그인을 해주세요.");
			location.href="/user/sign_in_view";
		} 
		location.href="/custom/custom_create_view";
	});
	
	$('.cp-box:last-child').on('click', function() {
		console.log(this);
	});
});
</script>
</div>
