<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.5.6/css/ionicons.min.css">
<link rel="stylesheet" type="text/css" href="css/slick.css" />
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="search">
		<jsp:include page="/WEB-INF/views/include/search.jsp" />
	</div>
	<section id="section3">
		<div class="section_03">
			<div class="layout">
				<div class="title_w">
					<h2 class="tt01"><b>Safing 가입 캠핑장</b></h2>
					<span class="s_tt">SafeZone 으로 안전한 캠핑을 즐겨보세요!</span>
				</div>
				<section>
					<div class="tour_list_w">
						<div class="tour_list slick-initialized slick-slider"
							style="width: 100%;">
							<div class="slick-list draggable">
								<div class="slick-track"
									style="opacity: 1; width: 1155px; transform: translate3d(0px, 0px, 0px);">
									<c:forEach items="${list}" var="vo">
										<div class="slick-slide slick-current slick-active"
											tabindex="0" style="width: 231px;" data-slick-index="0"
											aria-hidden="false">
											<div class="t_img">
												<a title="이달의 추천 캠핑장입니다." target="_BLANK" tabindex="0">
													<img class="r_img" src="${vo.firstimageurl }"
													onclick="location.href='detail.ma?contentid=${vo.contentid }'">
													<span class="r_img_on"
													onclick="location.href='detail.ma?contentid=${vo.contentid }'">
														캠핑장정보<br>자세히보기
												</span> <span class="m_on"
													onclick="location.href='detail.ma?contentid=${vo.contentid }'"></span>
												</a>
											</div>
											<div class="t_box table-radius-5">
												<p class="tt">${vo.facltnm }</p>
												<span class="s_tt">${vo.intro }</span>
												<div class="t_c">
													<button type="button" class="btn btn-secondary p-1" onclick="location.href='detail.ma?contentid=${vo.contentid }'" tabindex="0">자세히 보기</button>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</section>
	<section id="section5">
		<section class="ftco-section" style="margin: 50px;">
			<div class="container">
				<div class="row">
					<div class="col-md-12 text-center">
						<h2 class="heading-section mb-3">캠린이를 위한 꿀팁!</h2>
						<p class="heading-section mb-4">캠핑을 처음 시작하시는 분들은 필수 시청!</p>
					</div>
					<div class="col-md-12">
						<div class="featured-carousel owl-carousel">
							<c:forEach items="${tip_list}" var="vo">
								<div class="item">
									<div class="testimony-wrap py-4">
										<p class="mb-4">
											<img src="${vo.thumbnails}"
												onclick="location.href='detail.yu?id=${vo.id}'"
												style="width: 100%;">
										</p>
										<div class="d-flex align-items-center"></div>

									</div>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>
		</section>
		<div class="thema_bg_w2">
			<div class="thema_bg_01">
				<img src="img/visual1.png" class="img-fluid" alt="고캠핑 대표 이미지입니다.">
			</div>
		</div>

	</section>
</body>

<script type="text/javascript"
	src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/slick.min.js"></script>
<script type="text/javascript"
	src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>


<script type="text/javascript">
	
</script>

<style>
.owl-nav {
	font-size: 40px;
	color: white;
	position: absolute;
	top: 150px;
}

.owl-prev .owl-next {
	position: absolute;
}

.owl-prev {
	left: -60px;
}

.owl-next {
	left: 1350px;
}
</style>

</html>