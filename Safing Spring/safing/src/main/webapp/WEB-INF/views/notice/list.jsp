<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' type="text/css" href="css/shop_style.css?v=<%= new Date().getTime() %>" >
</head>
<body>
<!-- Navigation -->
<form action="list.no" method="post">
	<input type="hidden" name="curPage" value="1" />
	<input type="hidden" name="member_id" />
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="collapse navbar-collapse text-center container" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <label>
	            <a class="nav-link sub-category" href="list.no">
	            	<img class="img-size-30"  src="shop_img/notice.png"/>
	            	&nbsp;공지사항
				</a>
			</label>
	      </li>
	      <c:if test="${loginInfo.member_admin eq 'y'}">
		      <li class="nav-item">
		        <label>
		            <a class="nav-link" href="noticemanage.no">
		            	<img class="img-size-30"  src="shop_img/manage_board.png"/>
		            	&nbsp;공지글 관리
					</a>
				</label>
		      </li>
	      </c:if>
	    </ul>
	    <ul class="navbar-nav form-inline my-2 ">
	    	<li class="nav-item m-2 ">
				<select name='pageList' class="select-size-100"  onchange="$('form').submit()">
					<option class="dropdown-item"  value="10" ${page.pageList eq 10 ? 'selected' : '' }>10개씩</option>
					<option class="dropdown-item"  value="15" ${page.pageList eq 15 ? 'selected' : '' }>15개씩</option>
					<option class="dropdown-item"  value="20" ${page.pageList eq 20 ? 'selected' : '' }>20개씩</option>
					<option class="dropdown-item"  value="25" ${page.pageList eq 25 ? 'selected' : '' }>25개씩</option>
					<option class="dropdown-item"  value="30" ${page.pageList eq 30 ? 'selected' : '' }>30개씩</option>
				</select>
			</li>
	    	<li class="nav-item m-2 ">
				<select name='search' class="select-size-100" >
					<option class="dropdown-item" value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
					<option class="dropdown-item" value="board_title" ${page.search eq 'board_title' ? 'selected' : '' }>제목</option>
					<option class="dropdown-item" value="board_content" ${page.search eq 'board_content' ? 'selected' : '' }>내용</option>
					<option class="dropdown-item" value="member_id" ${page.search eq 'member_id' ? 'selected' : '' }>작성자</option>
				</select>
			</li>
	    	<li class="nav-item m-2 ">
				<input class="form-control mr-sm-2" name='keyword' value="${page.keyword}" type="search" placeholder="게시글 검색하기" aria-label="Search">
			</li>
	    	<li class="nav-item m-2">
				<button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="$('form').submit()">검색</button>
			</li>
			 <c:if test="${loginInfo.member_admin eq 'y'}">
				<li class="nav-item m-2">
				    <div>
				 	      <a type="button" class="btn btn-outline-secondary mt-auto py-1-1 px-2" href="new.no">
				 	     	 <img class="img-size-30"  src="shop_img/write.png"/>
				     	     &nbsp;글쓰기
				 		  </a> 
				     </div>
				</li>
			</c:if>
		</ul>	
	  </div>
	</nav>
</form>
<!-- 목록 형태 -->
<table class="table w-75 my-sm-4 table-center">
	<thead class="table-color">
		  <tr class="text-white text-center">
			    <th scope="col">번호</th>
			    <th scope="col">제목</th>
			    <th scope="col">작성자</th>
			    <th scope="col">작성일자</th>
			    <th scope="col">조회수</th>
		  </tr>
	</thead>
	<tbody >
	<c:if test="${ empty page.list }">
		<tr class="text-align text-center">
			<td colspan="8">검색하신 정보가 없습니다.</td>
		</tr>
	</c:if>
	<c:forEach items="${page.list }" var="vo">
		<tr>
			<td class="text-align text-center" >${vo.no }</td>
			<td class="text-left text-align">
				<a type="button" onclick='go_detail(${vo.board_id})'>${vo.board_title }</a>
			</td>
			<td class="text-align text-center" >${vo.member_name }</td>
			<td class="text-align text-center">${vo.board_writedate }</td>
			<td class="text-align text-center">${vo.board_read_cnt }</td>
		</tr>
	</c:forEach>
  </tbody>
</table>			
<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>

<script type="text/javascript">
function go_detail(id) {
	location.href="detail.no?id="+id;
	
}
</script>
</body>
</html>













