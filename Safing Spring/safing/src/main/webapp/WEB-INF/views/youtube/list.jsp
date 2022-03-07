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
<<<<<<< HEAD
<div id='list-top' style="width: 96% !importent">
<form action="list.yu" method="post">
<input type="hidden" name="curPage" value="1" />
<input type="hidden" name="id"  value="master"/>
	
			<!-- 관리자만 글쓰기 기능 -->
			<c:if test="${loginInfo.member_admin eq 'y' }">
				<li><a class='btn-fill' href='new.yu' style="width: 100px; margin-left: 10px ">글쓰기</a></li>
			</c:if>
	
=======
<div id='list-top'>
<form action="list.yu" method="post">
<input type="hidden" name="curPage" value="1" />
<input type="hidden" name="id"  value="master"/>
	<div>
		<!-- 검색 처리 -->
		<ul>
			<li>
				<select name="search" class='w-px90'>
					<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
					<option value="board_title" ${page.search eq 'board_title' ? 'selected' : '' }>제목</option>
					<option value="board_content" ${page.search eq 'board_content' ? 'selected' : '' }>내용</option>
					<option value="member_id" ${page.search eq 'member_id' ? 'selected' : '' }>작성자</option>
				</select>			
			</li>
			<li><input type='text' name='keyword' value="${page.keyword}" class='w-px300' /></li>
			<li><a class='btn-fill' onclick='$("form").submit()'>검색</a></li>		
		</ul>		
		<ul>
			<li>
				<select name='pageList' class='w-px90' onchange="$('form').submit()">
					<option value="10" ${page.pageList eq 10 ? 'selected' : '' }>10개씩</option>
					<option value="15" ${page.pageList eq 15 ? 'selected' : '' }>15개씩</option>
					<option value="20" ${page.pageList eq 20 ? 'selected' : '' }>20개씩</option>
					<option value="25" ${page.pageList eq 25 ? 'selected' : '' }>25개씩</option>
					<option value="30" ${page.pageList eq 30 ? 'selected' : '' }>30개씩</option>			
				</select>
			</li>
		
			<li>
				<select name="viewType" onchange="$('form').submit()">
					<option value="list" ${page.viewType eq 'list' ? 'selected' : '' }>리스트 형태</option>
					<option value="grid" ${page.viewType eq 'grid' ? 'selected' : '' }>그리드 형태</option>						
				</select>
			</li>
			<!-- 관리자만 글쓰기 기능 -->
			<c:if test="${loginInfo.member_admin eq 'y' }">
				<li><a class='btn-fill' href='new.yu'>글쓰기</a></li>
			</c:if>
		</ul>
	</div>
>>>>>>> origin/명운
</form>
</div>
<div id='data-list'>

<!-- 그리드 형태 -->
<c:if test="${page.viewType eq 'grid'}"><!-- viewType 값이 'grid' 이면 그리드형태를 나타냄 -->
	<ul class='grid'>
		<c:forEach items="${page.list }" var="vo">
<<<<<<< HEAD
			<li style="width: 23%; height: 345px; margin-right: 10px;">
				<div style="height: 100%">
				<a onclick='go_detail(${vo.id})'><img src="${vo.thumbnails }" style="width: 100%; height: 50%"></a>
				<a>${vo.youtubetitle }</a>
					<div style="display: flex;">
						<a class='btn-fill' onclick='go_detail(${vo.id})'>자세히보기</a>			
					</div>
				</div>
=======
			<li>
				<div><a onclick='go_detail(${vo.member_id})'>${vo.board_title }</a></div>
				<div>${vo.member_id }</div>
				<div>${vo.board_writedate } [${vo.board_read_cnt }] <span style="float: right;">${empty vo.file_name ? '' : "<img src='imgs/attach.png' class='file-img' />" }</span> </div>
>>>>>>> origin/명운
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
<<<<<<< HEAD
=======
			<th class='w-px100'>작성자</th>
			<th class='w-px100'>작성일자</th>
			<th class='w-px80'>조회수</th>
			<th class='w-px60'>첨부파일</th>
>>>>>>> origin/명운
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
<<<<<<< HEAD
			<td>${vo.id }</td>
			<td class='left'><a onclick='go_detail(${vo.id})'>${vo.youtubetitle}</a></td>
			
=======
			<td>${vo.no }</td>
			<td class='left'><a onclick='go_detail(${vo.board_id})'>${vo.board_title }</a></td>
			<td>${vo.member_id }</td>
			<td>${vo.board_writedate }</td>
			<td>${vo.board_read_cnt }</td>
			<td>${empty vo.file_name ? '' : '<img src="imgs/attach.png" class="file-img" />'}</td>			
>>>>>>> origin/명운
		</tr>
		</c:forEach>
		
	</tbody>
</table>
</c:if>
</div>
<<<<<<< HEAD
=======
<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>
>>>>>>> origin/명운

<script type="text/javascript">
	function go_detail(id) {
		$('[name=id]').val(id);
		$('form').attr('action', 'detail.yu');
		$('form').submit();
	}
</script>

</body>
</html>













