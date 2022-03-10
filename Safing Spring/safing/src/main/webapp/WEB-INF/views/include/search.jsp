<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' type="text/css" href="css/search.css?v=<%= new Date().getTime() %>">
</head>
<body>
<form class="form-subscribe" id="contactForm" action="search_list.ma" method="post" >
	<input type="hidden" name="curPage" value="1" />
	<div class="s-masthead relative-0 my-1">
		<div class="container position-relative">
			<div class="row justify-content-center">
				<div class="col-xl-6 table-white-color table-radius-5 absolute-mt-45">
					<div class="text-center text-black m-2">
						<p class="text-size-3 m-0"><strong class="text-main-color">세이핑인증</strong> 캠핑장에서</p>
						<p class="mb-3 text-size-2">편안한 캠핑을 즐겨보세요!</p>
						<div class="row">
							<div class="col-auto p-0">
								<select name='search' class="select-size float-right" >
						        	<option class="dropdown-item" value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
									<option class="dropdown-item" value="facltnm" ${page.search eq 'facltnm' ? 'selected' : '' }>캠핑장 이름</option>
									<option class="dropdown-item" value="addr1" ${page.search eq 'addr1' ? 'selected' : '' }>주소</option>
								</select>
							</div>
							<div class="col">
								<input type="text" name="keyword" class="form-control p-1-5 margin-center" name='product_name' placeholder="캠핑장명, 주소를 검색하세요" value="${page.keyword}" />
							</div>
							<div class="col-auto p-0" >
								<button type="submit" class="btn btn-secondary float-left">검색</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>