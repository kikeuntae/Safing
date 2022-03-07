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
<h3>고객정보 수정</h3>
<form action="update.cu" method="post">
<input type="hidden" name="id" value="${vo.member_id}">
	<table class='w-pct40'>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="member_pw" value="${vo.member_pw}" /></td>				
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="member_name" value="${vo.member_name}" /></td>						
		</tr>
		<tr>
			<th>나이</th>
			<td><input type="number" name="member_age" value="${vo.member_age}" /></td>		
		</tr>
		<tr>
			<th>성별</th>
			<td><label><input type="radio" ${vo.member_gender eq '남' ? 'checked' : '' } name='member_gender' value="남" />남</label>
				<label><input type="radio" ${vo.member_gender eq '여' ? 'checked' : '' } name='member_gender' value="여" />여</label>
			</td>				
		</tr>		
		<tr>
			<th>ADMIN</th>
			<td><input type="text" name="member_admin" value="${vo.member_admin}" /></td>				
		</tr>	
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="member_phone" value='${vo.member_phone}'/></td>				
		</tr>	
	</table>
</form>
	<div class='btnSet'>
		<a class='btn-fill' onclick='$("form").submit()'>저장</a>
		<a class='btn-empty' href='detail.cu?id=${vo.member_id}'>취소</a>
	</div>
<%-- </div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" /> --%>
</body>
</html>