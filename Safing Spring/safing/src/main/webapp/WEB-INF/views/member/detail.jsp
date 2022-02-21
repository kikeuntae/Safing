<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	<div class='btnSet'>
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










