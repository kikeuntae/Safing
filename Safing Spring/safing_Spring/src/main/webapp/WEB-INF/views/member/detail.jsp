<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Insert title here</title>
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
<link rel='stylesheet' type="text/css" href="css/board.css?v=<%= new Date().getTime() %>">

<title>Insert title here</title>
</head>
<body>
<%-- <jsp:include page="/WEB-INF/views/include/header.jsp" />
<div id = 'content'> --%>
	<h3>${vo.member_name } 님의 회원 정보</h3>
	<table class='w-pct60'>
		<tr>
			<th>아이디</th>
			<td>${vo.member_id }</td>
		</tr>	
		<tr>
			<th>비번</th>
			<td>${vo.member_pw }</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${vo.member_age }</td>
		</tr>
		<tr>
			<th class='w-px160'>성별</th>
			<td>${vo.member_gender }</td>
		</tr>
		<tr>
			<th>ADMIN</th>
			<td>${vo.member_admin }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${vo.member_phone }</td>
		</tr>
	</table>
	<div class='btnSet' style="display: flex; margin: 10px auto; width: 25%">
		<a class='btn-fill' href="list.cu">고객 목록</a>
		<a class='btn-fill' href="new.cu">신규 고객</a>
		<a class='btn-fill' href="modify.cu?id=${vo.member_id}">수정</a>
		<a class='btn-fill' 
		onclick="if ( confirm('정말 삭제하시겠습니까?') ) 
		{ href='delete.cu?id=${vo.member_id}'}  alert('삭제되었습니다.') ">삭제</a>
	</div>
<%-- </div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" /> --%>
</body>
</html>










