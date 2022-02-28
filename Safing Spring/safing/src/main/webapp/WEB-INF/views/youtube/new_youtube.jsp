<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Safing Board</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link rel='stylesheet' type="text/css" href="css/board.css?v=<%= new Date().getTime() %>">
<script type="text/javascript" src='js/file_check.js?v=<%=new Date().getTime()%>'></script>
</head>
<body>
<h3>유튜브 글쓰기</h3>

<form action="insert.yu" method="post" enctype="multipart/form-data" style="width: 50%; margin: 0 auto;">
	<table>
		
		<tr>
			<th class='w-px120'>유튜브 제목</th>
			<td>
				<input type='text' name="youtubetitle" title='제목' class='chk'/>
			</td>
		</tr>
		<tr>
			<th>유튜브 내용</th>
			<td>
				<textarea name='youtubecontent' class='chk' title='내용' ></textarea>
			</td>
		</tr>
		<tr>
			<th>유튜브썸네일</th>
			<td>
				<input type='text' name="thumbnails" title='제목' class='chk' value="" placeholder="https://img.youtube.com/vi/Tmuq76PeTf4/0.jpg" />
				<a href='img/upload.png' onclick=" window.open(this.href, '_blank', 'width=800, height=600'); return false;" style="width:100px">&nbsp;&nbsp;예시보기</a>
			</td>
		</tr>
		<tr>
			<th>유튜브재생ID</th>
			<td>
				<input type='text' name="play" title='제목' class='chk' value="" />
				<a href='img/upload.png' onclick=" window.open(this.href, '_blank', 'width=800, height=600'); return false;" style="width:100px">&nbsp;&nbsp;예시보기</a>
			</td>
		</tr>
			
	</table>
</form>
<div class='btnSet' style="    display: flex;
    width: 200px;
    margin: 0 auto;
    margin-top: 20px;">
	<a class='btn-fill' onclick='if ( emptyCheck() ) $("form").submit()'>저장하기</a>
	<a class='btn-fill' href='list.yu'>취소</a>
</div>
</body>
<style>
input {
	width: 100%;
}

textarea {
	width: 100%;
}

td {
	display: flex;
}
#id {
	width: 1300px;
}

#href {
	width: 1300px;
}
</style>
</html>



