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