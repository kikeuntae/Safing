<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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