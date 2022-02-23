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

</head>
<body>
<%-- <jsp:include page="/WEB-INF/views/include/header.jsp" />
<div id = 'content'> --%>
	<h3>회원 목록</h3>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
			<th>전화번호</th>
			<th>ADMIN</th>
		</tr>
		<c:forEach items="${list }" var="vo">
			<tr>
				<td>${vo.member_id }</td>
				<td><a href='detail.cu?id=${vo.member_id}'> ${vo.member_name }</a></td>
				<td>${vo.member_age }</td>
				<td>${vo.member_gender }</td>
				<td>${vo.member_phone }</td>
				<td>${vo.member_admin }</td>		
			</tr>
		</c:forEach>
	</table>
<%-- </div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" /> --%>
</body>
</html>