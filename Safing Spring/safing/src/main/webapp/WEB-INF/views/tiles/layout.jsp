<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${category eq 'ma' }"><c:set var='title' value="홈"/> </c:when>
	<c:when test="${category eq 'shop' }"><c:set var='title' value="캠핑용품"/> </c:when>
	<c:when test="${category eq 'bo' }"><c:set var='title' value="자유게시판"/> </c:when>
	<c:when test="${category eq 'no' }"><c:set var='title' value="공지사항"/> </c:when>
	<c:when test="${category eq 'yu' }"><c:set var='title' value="유튜브"/> </c:when>
	<c:when test="${category eq 'cu' }"><c:set var='title' value="관리자"/> </c:when>
	<c:when test="${category eq 'join' }"><c:set var='title' value="회원가입"/> </c:when>
	<c:when test="${category eq 'search' }"><c:set var='title' value="캠핑검색"/> </c:when>
</c:choose>
<!DOCTYPE html>
<html>
<head>
	<title>safing ${title}</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<link rel="icon" type="image/x-icon" href="img/safing-favicon.png" />
	<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="container-fluid">
		<tiles:insertAttribute name="content" />
	</div>
	<div class="container-fluid">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>