<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' type="text/css" href="css/search_list.css?v=<%= new Date().getTime() %>">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" type="text/css" />
	<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="search">
		<jsp:include page="/WEB-INF/views/include/search.jsp" />
	</div>
	<div class="container-fluid">
		<c:forEach items="${page.list}" var="vo">
			<div class="camp_search_list">
				<div class="c_list update py-5 m-0 box-width">
					<div class="container">
						<a href="detail.ma?contentid=${vo.contentid }" class="dc_none">
							<span class="skip">캠핑장정보 더보기</span>
							<div class="img_box">
								<c:choose >
									<c:when test="${vo.firstimageurl eq '-' }">
										<img src="img/emptyimage.png" class="table-radius-5 height-125" alt="${vo.facltnm}">
									</c:when>
									<c:otherwise>
											<img src="${vo.firstimageurl }" class="table-radius-5 height-125" alt="${vo.facltnm}">	
									</c:otherwise>
								</c:choose>			
							</div> 
						</a>
						<div class="camp_cont">
							<p class="item_group ">
								<span class="item_t01 table-radius-5">관광사업자 등록업체</span>
								<span class="item_t02 table-radius-5">리뷰수 0</span>
								<span class="item_t03 table-radius-5">조회수 10829</span>
								<span class="item_t04 table-radius-5">추천수 1</span>
							</p>
							<h2 class="camp_tt">
								<a href="detail.ma?contentid=${vo.contentid }">${vo.facltnm}</a>
							</h2>
							<span class="camp_stt"></span>
							<c:if test="${vo.intro ne '-' }"> 
								<span class="camp_txt mb-sm-1">
									<a href="detail.ma?contentid=${vo.contentid }">
										<span class="skip">캠핑장정보 더보기</span>${vo.intro }
									</a>
								</span>
							</c:if>
							<c:if test="${vo.intro eq '-' }"> 
								<span class="camp_txt mb-sm-1">
									<a href="detail.ma?contentid=${vo.contentid }">
										<span class="skip">캠핑장정보 더보기</span>캠핑장정보 더보기
									</a>
								</span>
							</c:if>
							<ul class="camp_info01 p-0">
								<li class="addr pb-0 text-align">
									<i class="addr"></i>
									<p class="mb-0 pt-03">${vo.addr1 }<p>
								</li>
								<c:if test="${vo.tel ne '-' }"> 
									<li class="call_num pb-0 ">
										<i class="call_num text-align"></i>
										<p class="mb-0 pt-03">${vo.tel }<p>
									</li>
								</c:if>	
							</ul>
							<!--아이콘모음-->
							<c:if test="${!empty vo.iconList}">
								<div class="camp_item_box mt-sm-1">
									<div class="icon_flex">
										<c:forEach items="${vo.iconList}" var="icon">
											<div>
												<div ><img src="img/${icon.value}"></div>
												<div style="display: block; color: #123456">${icon.key}</div>							
											</div>	
										</c:forEach>
									</div>		
								</div>
							</c:if>
							<c:if test="${empty vo.iconList}">
								<div class="camp_item_box mt-sm-1">
									<div class="icon_flex">
										<div>
											<div ><img src="img/warning.png"></div>
											<div style="display: block; color: #123456">준비중</div>							
										</div>	
									</div>		
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class='btnSet'>
		<jsp:include page="/WEB-INF/views/include/page.jsp" />						
	</div>	
	<script type="text/javascript">
	
		var str = ${vo.sbrscl};
		var split = str.split(',');
		
	
	</script>
</body>
</html>