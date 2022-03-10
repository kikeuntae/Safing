<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
	<link rel='stylesheet' type="text/css" href="css/tiles.css?v=<%= new Date().getTime() %>" >
</head>
	<header>
       	<!-- Navigation-->
       	<nav class="navbar navbar-expand-lg navbar-light bg-light py-4">
        	<div class="container">
				<a class="navbar-brand" href="home.ma">
					<img alt="logo" src="img/logo_color.png" class="img-fluid img-size-40">
				</a>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
			  		<ul class="navbar-nav mx-4">
			  			<li class="nav-item m-2 ">
			  				<a href="list.shop" ${category eq 'shop' ? "class='active vertical-mid'" : "class='nonactive vertical-mid'" }>캠핑용품</a>
			  			</li>
                        <li class="nav-item m-2">
                        	<a href="list.bo" ${category eq 'bo' ? "class='active vertical-mid'" : "class='nonactive vertical-mid'" }>자유게시판</a>
                        </li>
                        <li class="nav-item m-2">
                        	<a href="list.no" ${category eq 'no' ? "class='active vertical-mid vertical-mid'" : "class='nonactive vertical-mid'" }>공지사항</a>
                        </li>
                        <li class="nav-item m-2">
                        	<a href="list.yu" ${category eq 'yu' ? "class='active vertical-mid'" : "class='nonactive vertical-mid'" }>유튜브</a>
                        </li>
                        <li class="nav-item m-2">
                        	<a href="list.cu" ${category eq 'cu' ? "class='active vertical-mid'" : "class='nonactive vertical-mid'" }>관리자</a>
                        </li>
			  		</ul>
			  		<ul class="navbar-nav form-inline my-2 ">
						<!-- 로그인하지 않은 상태 -->
						<c:if test="${ empty loginInfo }">
							<li class="nav-item m-2">				
								<a type="button" class="btn btn-secondary p-1" href="login.lo">로그인</a>
							</li>
							<li class="nav-item m-2">
              	 				<a type="button" class="btn btn-outline-success p-1" href="join.lo">회원가입</a>
              	 			</li>	
						</c:if>
						<!-- 로그인한 상태 -->
						<c:if test="${ !empty loginInfo }">
							<li class="nav-item m-2 ms-4">				
								<a type="button" class="text-black p-1 vertical-mid" ><strong>${loginInfo.member_name}</strong> 님 환영합니다.</a>
							</li>
							<li class="nav-item m-2">
              	 				<a type="button" class="btn btn-outline-success p-1" href='logout.lo'>로그아웃</a>
              	 			</li>	
						</c:if>
					</ul>	
				</div>
			</div>
		</nav>        
</header>