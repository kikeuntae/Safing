<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- tiels 라이브러리를 사용할 수 있도록 선언 -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<meta charset="UTF-8">
<title>Safing > </title>
       <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Safing</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
		<link rel='stylesheet' type="text/css" href="css/tiles.css?v=<%= new Date().getTime() %>" >
<style>		


								
	header ul, header ul li {
		margin :0;
		padding : 0;
		display: flex;
		color: #735340;
	}
	
	header ul, header ul li a{
		text-decoration : none;
		color: #735340;
		font-weight: bold;
	}
	
	header .category{
		font-size : 18px;
	}
	
	header .category ul li:not(:first-child) {
		padding-left: 30px;
	}
	
	header .category ul li a:hover, header .category ul li a.active {
		font-weight: bold;
		color : #A9AF7E;
	}
	
</style>
    </head>
    <header style="padding : 15px 0; text-align : center; display: flex; width: 80%;" >
        <!-- Navigation-->
<<<<<<< HEAD
                <a class="navbar-brand" href="home.ma" ${category eq 'ma' ? "class='active'" : '' }><img alt="logo" src="img/logo_color.png" style="width: 200px;"></a>          
              <div class='category' style="margin-left: 200px; margin-top: 25px;">
                    <ul >
                        <li ><a href="#portfolio">쇼핑</a></li>
                        <li ><a href="list.bo" ${category eq 'bo' ? "class='active'" : '' }>자유게시판</a></li>
                        <li ><a href="list.no" ${category eq 'no' ? "class='active'" : '' }>공지사항</a></li>
                        <li ><a href="list.yu" ${category eq 'yu' ? "class='active'" : '' }>유튜브</a></li>
                        <li ><a href="list.cu" ${category eq 'cu' ? "class='active'" : '' }>관리자</a></li>
                        <li ><a href="login.lo" ${category eq 'lo' ? "class='active'" : '' }>로그인 / 회원가입</a></li>
=======
        <nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="home.ma">Safing</a>
                <button class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#portfolio">쇼핑</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="list.bo">자유게시판</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="list.no">공지사항</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="list.yu">유튜브</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="list.cu">관리자</a></li>
>>>>>>> origin/명운
                    </ul>
                  <div style='position: absolute; right: 0; top: 20px; margin-right: 200px;'>
	<%-- 	<ul>
			<!-- 로그인하지 않은 상태 -->
				<c:if test="${ empty loginInfo }">
					<li>				
						<a class='btn-fill' href='login'>로그인</a>
						<a class='btn-fill' href='member'>회원가입</a>
					</li>
				</c:if>
			<!-- 로그인한 상태 -->
				<c:if test="${ !empty loginInfo }">
					<li>				
						<strong>${loginInfo.name }</strong> 님 
						<a class='btn-fill' href='logout'>로그아웃</a>
					</li>
				</c:if>				
		</ul> --%>		
		</div>  
                </div>     
        </header>