<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css" rel="stylesheet">
	<link rel='stylesheet' type="text/css" href="css/sub_layout.css?v=<%=new Date().getTime()%>">
	

        <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
		<link rel='stylesheet' type="text/css" href="css/tiles.css?v=<%= new Date().getTime() %>" >
	
	
</head>

<body>
	<div id="s_contanier">
		<!--상단타이틀영역-->
		<div id="sub_title_wrap2">
			<!--Location-->
			<div class="location_w2">
				<div class="location_inner2"></div>
			</div>
			<!--//Location-->
			<div class="layout">
				<!--타이틀-->
				<div class="s_title2">
					<p class="camp_name">${vo.facltnm }</p>
					<p class="camp_s_tt"></p>
				</div>
			</div>
			<div class="sub_bg_w">
				<div class="camp_bg_03">
					<!-- <img src="img/visual3.jpg" style="display: inline-table; height: 700px;"> -->
				</div>
			</div>
		</div>
		<!--//상단타이틀영역-->
		<div class="gray_bar"></div>

		<section id="section1" style="right: 0px; margin-top: 90px; top: 20px">
			<h2 class="skip" style="display: none;">캠핑장정보 영역 입니다.</h2>
			<!--콘텐츠박스-->
			<div id="cont_inner">
				<div class="sub_layout layout">
					<article>
						<header class="camp_top_info">
							<h2 class="skip">캠핑장 정보</h2>
							<div class="camp_info_box">
								<div class="img_b">
									<c:choose>
										<c:when test="${vo.firstimageurl eq '-' }">
											<img src="img/emptyimage.png" alt="${vo.facltnm}">
										</c:when>
										<c:otherwise>
											<img src="${vo.firstimageurl }" alt="${vo.facltnm}">
										</c:otherwise>
									</c:choose>
								</div>
								<!--//S:table-->
								<div class="cont_tb">
									<table class="table">

										<colgroup>
											<col style="width: 30%;">
											<col style="width: 70%;">
										</colgroup>
										<tbody>
											<tr>
												<th scope="col">주소</th>
												<td>${vo.addr1 }</td>
											</tr>
											<tr class="camp_call_pcVer">
												<th scope="col">문의처</th>
												<td>${vo.tel }</td>
											</tr>
											<tr>
												<th scope="col">예약 구분</th>
												<td>${vo.resvecl }</td>
											</tr>
											<tr>
												<th scope="col">캠핑장 유형</th>
												<td>${vo.induty }</td>
											</tr>
											<tr>
												<th scope="col">운영기간</th>
												<td>봄, 여름, 가을, 겨울</td>
											</tr>
											<tr>
												<th scope="col">운영일</th>
												<td>${vo.operdecl }</td>
											</tr>
											<tr>
												<th scope="col">주변이용가능시설</th>
												<td>낚시, 해수욕, 농어촌체험시설</td>
											</tr>
										</tbody>
									</table>
									<div class="camp_call_tmVer">
										<ul>
											<li class="tt">문의처</li>
											<li class="call"><a href="tel:01064860548" title="전화하기"
												class="btn_bB1 best">전화하기</a></li>
										</ul>
									</div>
									<div class="btn_w">
										<button type="button" class="btn_bB1 best"
											onclick="fnCampLike(); return false">추천하기</button>
										<button type="button" class="btn_rB1 zzim"
											onclick="fnCampBkmk(); return false">찜하기</button>
										<button type="button" class="btn_gB1 btn_modify"
											onclick="location.href='/zboard/createForm.do?lmCode=campRequest&amp;c_no=100254'; return false;">정보수정요청</button>
									</div>
								</div>
								<!--//E:table-->

							</div>

						</header>
					</article>

				</div>

				<!-- 이벤트3행시배너 -->
				<!-- //이벤트3행시배너 -->

				<!--내용시작-->
				<div id="contents">
					<div class="camp_cont_w">

						<div class="line_100"></div>
						<div class="layout">
							<ul class="camp_tab05" style="list-style: none;">
								<li><a
									href="/bsite/camp/info/read.do?c_no=100254&amp;viewType=read01"
									class="camp_intro">캠핑장 소개</a></li>
							</ul>

							<div class="camp_intro">

								<p class="camp_intro_txt">
									<span>${vo.intro } 
								</p>
								<h3 class="icon_h3">캠핑장 시설정보</h3>

								<div class="camp_item_box">

									<div class="icon_flex">
										<c:forEach items="${vo.iconList}" var="icon">
											<div>
												<div>
													<img src="img/${icon.value}">
												</div>
												<div style="display: block; color: #123456">${icon.key}</div>
											</div>
										</c:forEach>
									</div>
								</div>
								<h3 class="icon_h3">기타 주요시설</h3>
								<section id="table_type03">

									<div class="table_w">
										<table class="table_t4 camp_etc_tb">
											<caption>
												<strong>기타 주요시설 안내 테이블</strong>

											</caption>

											<tbody class="t_c">
												<tr>
													<th scope="col">SafeZone 가입 유무</th>
													<td>
														<ul class="table_ul05">
															<li>${vo.sfzone }</li>
														</ul>
													</td>
												</tr>
												<tr>
													<th scope="col">반려동물 출입</th>
													<td class="etc_type">${vo.animalcmgcl }</td>
												</tr>
												<tr>
													<th scope="col">바베큐</th>
													<td class="etc_type">개별</td>
												</tr>
											</tbody>
										</table>
									</div>
									<p class="camp_intro_txt">
										<span class="info_notice"> &nbsp;* Safing에 등록된 정보는
											현장상황과 다소 다를 수 있으니 <span class="info_f_red">반려동물 동반 여부,
												부가 시설물, 추가차량</span> 등 원활한 캠핑을 위해 꼭 필요한 사항은 해당 캠핑장에 미리 확인하시기 바랍니다.
										</span>
									</p>
								</section>
								<!-- 지도를 표시할 div 입니다 -->
								<div id="map" style="width: 100%; height: 350px;"></div>



								<h3 class="icon_h3 mt_50">${vo.facltnm }</h3>
								<div class="box_photo">
									<div id="gallery"
										style="max-width: 100%; min-width: 150px; overflow: visible; height: auto;"
										class="ug-gallery-wrapper ug-theme-tiles" >
										<c:forEach items="${img_list}" var="vo">
													<c:choose>
														<c:when test="${vo.imageurl eq null }">			
															<img src="img/emptyimage.png" src=""
																style="width: 236px; height: 177px; left: 0px; top: 0px; margin:10px;">												
														</c:when>
														<c:otherwise>
														<a href="${vo.imageurl }" data-toggle="lightbox" data-gallery="example-gallery" style="display: contents;">
															<img src="${vo.imageurl }"
																class="ug-thumb-image ug-trans-enabled"
																style="width: 236px; height: 177px; left: 0px; top: 0px; margin:10px; overflow: visible;">
																</a>
														</c:otherwise>
													</c:choose>
												</c:forEach>
										<div class="ug-overlay-disabled" style="display: none">
											</div>
										<div
											class="ug-tiles-wrapper ug-tiletype-columns ug-tiles-rest-mode ug-tiles-transit"
											style="position: relative; height: 177px;">

												</div>
												



											</div>                                                                                                                                                                                                           
										</div>
									</div>
									<div class="ug-tiles-preloader ug-preloader-trans"
										style="opacity: 1; position: absolute; margin: 0px; left: 584px; top: 100px; display: none;"></div>
								</div>
							</div>
							<div style="margin-top: -30px; margin-bottom: 30px;">※ 모든
								컨텐츠의 저작권은 Safing에 있습니다. 무단 사용 및 불법 재배포는 법적 조치를 받을 수 있습니다.</div>
							<!-- 		<button type="button" class="btn_grey_M">더보기</button> -->

						</div>
					</div>
				</div>
			</div>
			<!--//내용종료-->
	</div>
	<!--//콘텐츠박스-->
	</section>
	</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d84d17c12c47d013c9658aecf9316aea"></script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.min.js"></script>
		
		
	<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(${vo.mapy}, ${vo.mapx}), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(${vo.mapy}, ${vo.mapx}); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
// marker.setMap(null);    


$(document).on('click', '[data-toggle="lightbox"]', function(event) {
    event.preventDefault();
    $(this).ekkoLightbox();
});
</script>

</body>
<style>
.ekko-lightbox-nav-overlay a:last-child span {
    text-align: right;
    color: white;
    font-size: 50px;
}
.ekko-lightbox-nav-overlay a span {
    padding: 0 30px;
    color: white;
    font-size: 50px;
}

</style>
</html>