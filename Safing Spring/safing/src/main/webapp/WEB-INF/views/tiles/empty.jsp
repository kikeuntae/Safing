<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="container-fluid">
		<div  class="row">
			<tiles:insertAttribute name="content" ignore="true"/>
		</div>
	</div>
</body>
</html>