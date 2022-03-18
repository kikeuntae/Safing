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
<h3>신규 고객 입력</h3>
	<form action="insert.cu" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="member_id" /></td>				
			</tr>	
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="member_pw" /></td>				
			</tr>						
			<tr>
				<th>이름</th>
				<td><input type="text" name="member_name" /></td>				
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="number" name="member_age" /></td>				
			</tr>			
			<tr>
				<th>성별</th>
				<td><label><input type="radio" name='member_gender' value="남" />남</label>
					<label><input type="radio" name='member_gender' value="여" checked />여</label>
				</td>				
			</tr>		
			<tr>
				<th>ADMIN</th>
				<td><input type="text" name="member_admin" /></td>				
			</tr>		
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="member_phone" /></td>				
			</tr>		
		</table>
</form>
	<div class='btnSet'>
		<a class='btn-fill' onclick='$("form").submit()'>저장</a>
		<a class='btn-empty'>취소</a>
	</div>
<%-- </div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" /> --%>
</body>
</html>