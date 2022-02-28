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
</head>
<body>
<h3>유튜브 게시판</h3>
<div id='list-top' style="width: 96% !importent">
<form action="list.yu" method="post">
<input type="hidden" name="curPage" value="1" />
<input type="hidden" name="id"  value="master"/>
	
			<!-- 관리자만 글쓰기 기능 -->
			<c:if test="${loginInfo.member_admin eq 'y' }">
				<li><a class='btn-fill' href='new.yu' style="width: 100px; margin-left: 10px ">글쓰기</a></li>
			</c:if>
	
</form>
</div>
<div id='data-list'>

<!-- 그리드 형태 -->
<c:if test="${page.viewType eq 'grid'}"><!-- viewType 값이 'grid' 이면 그리드형태를 나타냄 -->
	<ul class='grid'>
		<c:forEach items="${page.list }" var="vo">
			<li style="width: 23%; height: 345px; margin-right: 10px;">
				<div style="height: 100%">
				<a onclick='go_detail(${vo.id})'><img src="${vo.thumbnails }" style="width: 100%; height: 50%"></a>
				<a>${vo.youtubetitle }</a>
					<div style="display: flex;">
						<a class='btn-fill' onclick='go_detail(${vo.id})'>자세히보기</a>			
					</div>
				</div>
			</li>
		</c:forEach>
	</ul>
</c:if>

<!-- 목록 형태 -->
<c:if test="${page.viewType eq 'list'}">	<!-- viewType 값이 'list' 이면 목록 형태를 나타냄 -->
<table>
	<thead>
		<tr>
			<th class='w-px70'>번호</th>
			<th>제목</th>
		</tr>
	</thead>
	<tbody>	
		<!-- 조회된 목록이 없을 경우 정보 표시 -->
		<c:if test="${ empty page.list }">
			<tr>
				<td colspan="6">방명록 정보가 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${page.list }" var="vo">
		<tr>
			<td>${vo.id }</td>
			<td class='left'><a onclick='go_detail(${vo.id})'>${vo.youtubetitle}</a></td>
			
		</tr>
		</c:forEach>
		
	</tbody>
</table>
</c:if>
</div>

<script type="text/javascript">
	function go_detail(id) {
		$('[name=id]').val(id);
		$('form').attr('action', 'detail.yu');
		$('form').submit();
	}
</script>

</body>
</html>













