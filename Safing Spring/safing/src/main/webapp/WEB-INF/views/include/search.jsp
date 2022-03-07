<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>

<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" type="text/css" />
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link rel='stylesheet' type="text/css" href="css/search.css?v=<%= new Date().getTime() %>">
</head>
<body>
	<%-- <div class="header-nav">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
	</div> --%>
		<!-- Masthead-->
	<div class="s-masthead">
		<div class="container position-relative">
			<div class="row justify-content-center">
				<div class="col-xl-6">
					<div class="text-center text-white">
						<!-- Page heading-->
						<h1 class="mb-5">전국 캠핑장 검색</h1>
						<!-- Signup form-->
						<!-- * * * * * * * * * * * * * * *-->
						<!-- * * * * * * * * * * * * * * *-->
						<!-- This form is pre-integrated with SB Forms.-->
						<!-- To make this form functional, sign up at-->
						<!-- https://startbootstrap.com/solution/contact-forms-->
						<!-- to get an API token!-->
						<form class="form-subscribe" id="contactForm" action="search_list.ma" method="post">
							<!-- Email address input-->
							<div class="row">
								<div class="col">
									<input class="form-control form-control-lg" type="text"
									 name="keyword" placeholder="캠핑장명, 주소, 테마를 검색하세요" value="${page.keyword}" />
								</div>
								<div class="col-auto">
									<a class='btn-fill' onclick='$("form").submit()'>검색</a>
								</div>
							</div>
						</form>
							<!-- Submit success message-->
							<!---->
							<!-- This is what your users will see when the form-->
							<!-- has successfully submitted-->
							<div class="d-none" id="submitSuccessMessage">
								<div class="text-center mb-3">
									<div class="fw-bolder">Form submission successful!</div>
									<p>To activate this form, sign up at</p>
									<a class="text-white"
										href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
								</div>
							</div>
							<!-- Submit error message-->
							<!---->
							<!-- This is what your users will see when there is-->
							<!-- an error submitting the form-->
							<div class="d-none" id="submitErrorMessage">
								<div class="text-center text-danger mb-3">Error sending
									message!</div>
							</div>
					</div>


				</div>
			</div>
		</div>
	</div>



</body>
</html>