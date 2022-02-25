<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Safing Main</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" type="text/css" />
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link rel='stylesheet' type="text/css" href="css/search.css?v=<%= new Date().getTime() %>">

</head>
<body>
<div>
<input type="hidden" name="curPage" value="1" />

	<div class="header-nav">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
	</div>


<div class="search">
		<jsp:include page="/WEB-INF/views/include/search.jsp" />
</div>
<section id="section2">

	</section>
	<section id="section3">
		<h2>
			<span class="skip">한국관광공사 추천 2월의 낭만 여행</span>
		</h2>
		<div class="section_03">
			<div class="layout">
				<div class="title_w">
					<h2 class="tt01">
						<b>Safing 가입 캠핑장</b>
						<!-- <i class="tt_point"><span class="skip">아이콘이미지</span></i>-->
					</h2>
					<span class="s_tt">SafeZone 으로 안전한 캠핑을 즐겨보세요!</span>
				</div>
				<!-- 
					<div class="title_w">
						<h2 class="tt01">
							<b>2월,</b> <span>한국관광공사 추천 2월의 낭만 여행</span> <i class="tt_point"><span class="skip">아이콘이미지</span></i>
						</h2>
						<span class="s_tt">봄과 겨울 사이. 볼거리.놀거리 먹거리 여행</span>
					</div>
					 -->
				<section>
					<h2 class="skip">이달의 추천 캠핑장입니다</h2>

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
													<img class="r_img" src="${vo.firstimageurl }" onclick="location.href='detail.ma?contentid=${vo.contentid }'" >
													 <span class="r_img_on" onclick="location.href='detail.ma?contentid=${vo.contentid }'"> 캠핑장정보<br>자세히보기 </span>
													 
													  <span class="m_on" onclick="location.href='detail.ma?contentid=${vo.contentid }'"></span>
												</a>
											</div>
											<div class="t_box">
												<p class="tt">${vo.facltnm }</p>
												<span class="s_tt">${vo.intro }</span>
												<div class="t_c">
													<button type="button" class="btn_blue_m1"
														onclick="location.href='detail.ma?contentid=${vo.contentid }'"
														tabindex="0">자세히 보기</button>
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

	<div class="section_05">
		<div class="layout">
			<!--타이틀-->
			<div class="title_w">
				<h2 class="tt01">
					캠린이를 위한 꿀팁! <i class="tt_point"><span class="skip">아이콘이미지</span></i>
				</h2>
				<span class="s_tt">캠핑 초보들을 위한 꿀팁 여기에 다 있다~! </span>
			</div>
			<!--테마슬라이드-->
			<div class="them_cont02">
				<div class="item_w slick-initialized slick-slider">
					<button class="slick-prev slick-arrow" aria-label="Previous"
						type="button" style="display: block;">Previous</button>
								<button class="slick-next slick-arrow" aria-label="Next" type="button" style="display: block;">Next</button>
					<div class="slick-list draggable">
						<div class="slick-track"
							style="opacity: 1; width: 5776px; transform: translate3d(-912px, 0px, 0px);">
							</div>
							<c:forEach items="${tip_list}" var="vo">
								<div class="slick-slide slick-cloned" tabindex="-1"
									style="width: 304px;" data-slick-index="-3" aria-hidden="true">
									<a href="https://www.youtube.com/watch?v=${vo.play}">
										<div class="them_item">
											<img src="${vo.thumbnails}" >
											<p class="them_tt">${vo.youtubetitle }</p>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
					
					
				</div>

			</div>
			<!--//테마슬라이드-->
			<div class="thema_bg">
			<!--S:비쥬얼영역-->
		<div class="thema_bg_w2">
			<div class="thema_bg_01">
				<img src="img/visual1.png" alt="고캠핑 대표 이미지입니다.">
			</div>
		</div>
		<!--E:비쥬얼영역-->
			
			</div>
		</div>
		
	<!-- Icons Grid-->
</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
<script type="text/javascript">
$(document).ready(function() {

	/*1번슬라이드*/
	$('.tour_list').slick({   
		arrows : true,
		infinite: true,
		speed: 300,
		autoplay : true,
		autoplaySpeed : 2000,
		slidesToShow: 5,
		slidesToScroll: 1,
		responsive: [
		{
		  breakpoint: 1024,
		  settings: {
			slidesToShow: 3,
			slidesToScroll: 3,
			infinite: true,
			dots: false
		  }
		},
		{
		  breakpoint: 600,
		  settings: {
			slidesToShow: 1,
			slidesToScroll: 1
		  }
		},
		{
		  breakpoint: 480,
		  settings: {
			slidesToShow: 1,
			slidesToScroll: 1
		  }
		} 
	  ]
	}) 

});
 
$(document).ready(function() {
	/*2캠핑족을위한꿀팁 슬라이드*/
	 $('.tip_list').slick({   
		arrows : true,
		infinite: true,
		speed: 300,
		autoplay : true,
		autoplaySpeed : 2000,
		slidesToShow: 3,
		slidesToScroll: 1,
		responsive: [
		{
		  breakpoint: 1024,
		  settings: {
			slidesToShow: 3,
			slidesToScroll: 3,
			infinite: true,
			dots: false
		  }
		},
		{
		  breakpoint: 600,
		  settings: {
			slidesToShow: 1,
			slidesToScroll: 1
		  }
		},
		{
		  breakpoint: 480,
		  settings: {
			  slidesToShow: 1,
			  slidesToScroll: 1
		  }
		} 
	  ]
	});
});

$(document).ready(function() {

	 //3팝업존슬라이드
	 $('.pop_img_w').slick({   
		arrows : true,
		infinite: true,
		speed: 300,
		autoplay : true,
		autoplaySpeed : 2000,
		slidesToShow: 1,
		slidesToScroll: 1,
		 
		})
	
});

$(document).ready(function() {

	 //테마,그리고 캠핑 슬라이드
	 $('.item_w').slick({   
		arrows : true, 
	 
		infinite: true,
		speed: 300,
		autoplay : true,
		autoplaySpeed : 3500,
		slidesToShow: 3,
		slidesToScroll: 3,
		
		responsive: [
		{
		  breakpoint: 1024,
		  settings: {
			slidesToShow: 3,
			slidesToScroll: 3,
			infinite: true,
			dots: false
		  }
		},
		{
		  breakpoint: 740,
		  settings: {
			slidesToShow: 2,
			slidesToScroll: 2
		  }
		},
		 
		{
		  breakpoint: 540,
		  settings: {
			  slidesToShow: 1,
			  slidesToScroll: 1
		  }
		} 
	  ]
		 
		});
	 
	  
});


$(document).ready(function() {

	 //메인슬라이드
	 $('.m_bg_w').slick({ 
		fade: true,
		arrows : false, 
		infinite: true,
		speed: 500,
		dots: true,
		autoplay : true,
		autoplaySpeed : 5000,
		slidesToShow: 1,
		slidesToScroll: 1,
		
		 
		});

		$('.pause').on('click', function() {
			$('.m_bg_w')
				.slick('slickPause')
		});

		$('.play').on('click', function() {
			$('.m_bg_w')
				.slick('slickPlay')
		});

	 //메인슬라이드
	 $('.m_notice_banner').slick({ 
		fade: true,
		arrows : false, 
		infinite: true,
		speed: 500,
		dots: true,
		autoplay : true,
		autoplaySpeed : 5000,
		slidesToShow: 1,
		slidesToScroll: 1,
		});
	
});


</script>
</html>