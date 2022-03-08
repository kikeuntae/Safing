<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>search list</title>
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
<link rel='stylesheet' type="text/css" href="css/search_list.css?v=<%= new Date().getTime() %>">
<!-- Font Awesome -->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
</head>
<body>

<div class="search">
		<jsp:include page="/WEB-INF/views/include/search.jsp" />
</div>
<c:forEach items="${page.list}" var="vo">
<div class="camp_search_list">
				<div class="c_list update">
					<a href="/bsite/camp/info/read.do?c_no=3348&amp;viewType=read01&amp;listOrdrTrget=last_updusr_pnttm" class="dc_none"><span class="skip">캠핑장정보 더보기</span>
						<div class="img_box" >
						<c:choose >
						<c:when test="${vo.firstimageurl eq '-' }"><img src="img/emptyimage.png" alt="${vo.facltnm}"></c:when>
							<c:otherwise>
								<img src="${vo.firstimageurl }" alt="${vo.facltnm}">	
							</c:otherwise>
						</c:choose>			
						</div> 
						
						</a>
						
					<div class="camp_cont">
						<p class="item_group">
							<span class="item_t01">관광사업자 등록업체</span>
							<span class="item_t02">리뷰수 0</span> <span class="item_t03">조회수 10829</span> <span class="item_t04">추천수 1</span>
						</p>
						<h2 class="camp_tt">
							<a href="detail.ma?contentid=${vo.contentid }">${vo.facltnm}</a>
						</h2>
						<span class="camp_stt"></span> 
						<span class="camp_txt">
						 <a href="detail.ma?contentid=${vo.contentid }"><span class="skip">캠핑장정보 더보기</span>${vo.intro } </a>
						 
						</span>
						<ul class="camp_info01">
							<li class="addr"><i class="addr"></i>${vo.addr1 }</li>
							<li class="call_num"><i class="call_num"></i>${vo.tel }</li>
							</ul>
						<!--아이콘모음-->
						<div class="camp_item_box">
					
						<div class="icon_flex">
							<c:forEach items="${vo.iconList}" var="icon">
							<div>
								<div ><img src="img/${icon.value}"></div>
								<div style="display: block; color: #123456">${icon.key}</div>							
							</div>	
							</c:forEach>
						</div>	
								<%-- <c:choose>
								<c:when test="${vo.sbrscl eq '무선인터넷' }">
									<li><i class="i.ico_wifi"><span>무선인터넷</span></i></li>
								</c:when>
								<c:when test="${vo.sbrscl eq '물놀이장' }">
									<li><i class="i.ico_pool"><span>물놀이장</span></i></li>
								</c:when>
									<li><i class="${ert1}"><span>샤워장</span></i></li>
									<li><i class="i.ico_toilet"><span>화장실</span></i></li>
									<li><i class="i.ico_volt"><span>전기</span></i></li>
									<li><i class="i.ico_wood"><span>장작판매</span></i></li>
									<li><i class="i.ico_mart"><span>마트</span></i></li>
									<li><i class="i.ico_walk"><span>산책로</span></i></li>
									<li><i class="i.ico_ground"><span>운동장</span></i></li>
									<li><i class="i.ico_playzone"><span>놀이터</span></i></li>
									<li><i class="i.ico_hotwater"><span>온수</span></i></li>
									<li><i class="i.ico_ico_sports"><span>운동시설</span></i></li>
									<li><i class="i.ico_ico_ico_tramp"><span>트램폴린</span></i></li>
									<c:otherwise></c:otherwise>
								</c:choose> --%>
							
							</div>
						<!--//아이콘모음-->
					</div>
				</div>
			</div>
		


<<<<<<< HEAD
		</c:forEach>	
		<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/page.jsp" />						
</div>	
=======
		</c:forEach>		
>>>>>>> 682ce78c21391dff70414534ef6368237c38780b
<script type="text/javascript">

	var str = ${vo.sbrscl};
	var split = str.split(',');
	

</script>
</body>
</html>