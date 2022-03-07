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
<title>자유게시판 글쓰기</title>
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
<h3>캠핑 자유게시판 글쓰기</h3>
<!-- 파일 첨부하여 submit 하는 경우
	1. method 는 post 로 지정
	2. form 에는 반드시 enctype='multipart/form-data' 지정
 -->
<form action="insert.bo" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th class='w-px120'>제목</th>
			<td>
				<input type='text' name="board_title" title='제목' class='chk' />
			</td>
			<li>
				<select name='board_kinds' class='w-px90' >
					<option value="free" selected="selected" >free</option>
					<option value="video">video</option>
					<option value="camping">camping</option>
				</select>
			</li>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name='board_content' class='chk' title='내용' ></textarea>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td class='left'>
				<label>
				<a><img src='imgs/select.png' class='file-img' /></a>
				<input type="file" id='attach-file' name='file' />	
				</label>	
				<span id='file-name'></span>
				<!-- 이미지 파일인 경우 미리보기 적용 -->
				<span id='preview'></span>
				<a id='delete-file'><i class='font-img fas fa-minus-circle'></i></a>						
			</td>
		</tr>
			
	</table>
</form>
<div class='btnSet'>
	<a class='btn-fill' onclick='if ( emptyCheck() ) $("form").submit()'>저장하기</a>
	<a class='btn-fill' href='list.bo'>취소</a>
</div>
</body>
</html>



