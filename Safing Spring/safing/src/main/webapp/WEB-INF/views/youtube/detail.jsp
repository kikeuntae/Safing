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
<h3>유튜브 글 상세</h3>
<table class='title' style="width: 750px;">
	<tr>
		<th class='w-px120' style="width: 15px;">제목</th>
		<td colspan="5" class='left'>${vo.youtubetitle }</td>
	</tr>
	
	<tr>
		<th style="width: 15px;">내용</th>
		<td colspan="5" class='left'>
		<iframe width="560" height="315" src="https://www.youtube.com/embed/${vo.play }" title="YouTube video player" 
		frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
		</iframe><br>${fn:replace(vo.youtubecontent, crlf, '<br>')}</td>
	</tr>
</table>

<div class='btnSet' style="display: flex; margin: 10px auto; width: 25%">
	<a class='btn-fill' onclick='$("form").submit()'>목록으로</a>
	<!-- 글쓴이만 수정/삭제 권한을 가짐 -->
	<c:if test="${loginInfo.member_admin eq 'y' }">
		<a class='btn-fill' onclick='$("form").attr("action", "modify.yu"); $("form").submit()' >유튜브 수정</a>
		<a class='btn-fill' onclick='if ( confirm("정말 삭제?") ) {href="delete.yu?id=${vo.id }" }' >삭제</a>
	</c:if>
</div>



<!-- 목록 요청에 필요한 데이터를 post 방식으로 전달하는 방법 -->
<form action="list.yu" method="post">
	<input type="hidden" name="id" value="${vo.id }" />	<!-- 해당 글의 id  -->
	<input type="hidden" name="search" value="${page.search }" /> <!-- 검색조건 -->
	<input type="hidden" name="keyword" value="${page.keyword }" /> <!-- 검색어 -->
	<input type="hidden" name="curPage" value="${page.curPage }" /> <!-- 현재 페이지 -->
	<input type="hidden" name="pageList" value="${page.pageList }" /> <!-- 한 페이지당 보여질 목록 수 -->
	<input type="hidden" name="viewType" value="${page.viewType }" /> <!-- 게시판 형태 -->
</form>

<!-- 이미지를 크게 볼 수 있도록 처리 (popup 형태) -->
<div id='popup-background'></div>
<div id='popup' class='center'></div>



</body>
<style>
	#title > th {
		width: 20px;
	}
	
	td {
		width:150px;
	}
</style>
</html>













