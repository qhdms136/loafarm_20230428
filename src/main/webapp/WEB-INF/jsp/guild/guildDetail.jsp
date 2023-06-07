<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="d-flex justify-content-center">
	<div class="gd-content">
		<div class="gd-header d-flex justify-content-bwteen">
			<div class="col-4 d-flex justify-content-start align-items-center">
				<span class="gd-user-name">${guildPostView.user.nickname}</span>
			</div>
			<div class="col-4 d-flex justify-content-center align-items-center">
				<span><fmt:formatDate value="${guildPostView.guildpost.createdAt}" pattern="yyyy-MM-dd"/></span>
			</div>
			<div class="col-4"></div>	<!-- 3개의 박스를 일정하게 가운데로 맞추기 위해 -->
		</div>
		<div class="d-flex justify-content-center">
			<div class="gd-box">
				<div class="gd-subject">${guildPostView.guildpost.subject}</div>
				<div class="gd-text">${guildPostView.guildpost.content}</div>
				<div>${guildPostView.guildpost.address}</div>
					<div>
						<input type="hidden" id="address" value="${guildPostView.guildpost.address}">
						<div id="map" style="width:100%;height:300px;margin-top:10px;display:block"></div>
						<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=996f3681c69f47af40cf467e79616ae1&libraries=services"></script>
					</div>
				<div style="margin-top:30px;">
					<div class="d-flex justify-content-center">
						<span class="text-primary"><b>신청현황</b></span>
					</div>
					<div class="d-flex justify-content-center">
						<span>${guildPostView.subcount} / ${guildPostView.guildpost.maxCount}</span>					
					</div>
					<textarea class="sub-content mt-3 form-control" id="content" rows="5" placeholder="내용을 입력해주세요"></textarea>
				</div>
				<div class="my-3 d-flex justify-content-center">
					<c:if test="${userId != guildPostView.user.id}">
						<c:if test="${guildPostView.subcount ge guildPostView.guildpost.maxCount}">
							<button class="btn btn-danger" disabled>신청포화</button>
						</c:if>
						<c:if test="${guildPostView.subcount lt guildPostView.guildpost.maxCount}">
							<button id="subBtn" class="btn btn-primary" data-post-id="${guildPostView.guildpost.id}">신청하기</button>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
		<div class="gd-bottom d-flex justify-content-end align-items-center">
			<div>
				<button onclick="location.href='/guild/guild_list_view'" class="btn btn-outline-dark">목록</button>
				<c:choose>
					<c:when test="${userId eq guildPostView.user.id}">
						<button onclick="location.href='/guild/guild_update_view?guildPostId=${guildPostView.guildpost.id}'" class="mx-3 btn btn-dark">글수정</button>
					</c:when>
					<c:when test="${userId != guildPostView.user.id}">
						<button onclick="location.href='/guild/guild_create_view'" class="mx-3 btn btn-dark">글쓰기</button>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</div>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

//지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption); 

//post 주소
var address = $('#address').val();
/* console.log(address); */

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch(address, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">모임장소</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});
$(document).ready(function(){
	$('#subBtn').on('click', function(){
		let content = $('#content').val();
		let postId = $(this).data("post-id");
		/* console.log(content);
		console.log(postId); */
		if(!content){
			alert("내용을 입력해주세요");
			return;
		}
		
		$.ajax({
			// request
			url:"/subuser/is_duplicated_id"
			// response
			,success:function(data){
				if(data.result){
					// 이미 신청 목록이 존재
					alert("길드모임 신청은 아이디당 1개만 가능합니다. \n"
							+ "이미 신청하신 모임글이 있으시다면 신청을 취소하고 다시 넣어주세요");
					location.href="/guild/guild_list_view";
				} else{
					// 신청 가능 
					$.ajax({
						// request
						type:"POST"
						,url:"/subuser/create"
						,data:{"content":content, "postId":postId}
						// response
						,success:function(data){
							if(data.code == 1){
								alert("신청이 완료되었습니다.");
								location.reload(true);
							} else if(data.code == 300){
								alert(data.errorMessage);
								location.reload(true);
							} else{
								alert(data.errorMessage);
							}
						}
						,error:function(request, status, error){
							alert("신청하기를 실패하였습니다.");
						}
					});
				}
			}
		});
	});
});
</script>