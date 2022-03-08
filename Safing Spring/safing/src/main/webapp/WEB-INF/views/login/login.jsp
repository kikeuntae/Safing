<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
<title>Safing</title>
<meta charset="utf-8" />
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="img/safing-favicon.png" />
<!-- Google fonts-->
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link href="https://fonts.googleapis.com/css2?family=Tinos:ital,wght@0,400;0,700;1,400;1,700&amp;display=swap" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,wght@0,400;0,500;0,700;1,400;1,500;1,700&amp;display=swap" rel="stylesheet" />

<!-- cdn-->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>

<!-- Core theme CSS (includes Bootstrap)-->
<link rel='stylesheet' type="text/css" href="css/login.css?v=<%= new Date().getTime() %>" >

</head>
<body>
<!-- Background Video-->
<video class="bg-video" playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop"><source src="mp4/bg.mp4" type="video/mp4" /></video>
<!-- Masthead-->
<div class="masthead">
    <div class="masthead-content text-white">
        <div class="container-fluid px-4 px-lg-0">
            <h1 class="fst-italic lh-1 mb-4">안전하게 즐기는 감성 캠핑</h1>
            <p class="mb-5">지치고 힘든 일상에서 벗어나 안전하고 여유롭게 즐길 수 있는 Safing으로 여러분을 초대합니다.</p>
            <!-- to get an API token!-->
            <form name="dataForm" id="dataForm" onsubmit="return go_login()" method="post" enctype="multipart/form-data">
                <!-- Email address input-->
                <div class="row input-group-newsletter">
                    <div class="col"><input class="form-control" name="member_id" id="userid" type="text" placeholder="아이디를 입력하세요"/></div>                           
                </div>
                <div class="row input-group-newsletter">                  
                    <div class="col"><input class="form-control" name="member_pw" id="userpw" type="password" placeholder="비밀번호를 입력하세요"/></div>                        
                </div>
                <div class="col-auto">
              	 	<button type="submit" class="btn btn-secondary m-2">로그인</button>
              	 	<a type="button" class="btn btn-outline-success mx-" href="login.lo">회원가입</a>
                </div> 
            </form>
        </div>
    </div>
</div>
<!-- Social Icons-->
<div class="social-icons">
    <div class="d-flex flex-row flex-lg-column justify-content-center align-items-center h-100 mt-3 mt-lg-0">
       	<a class="btn m-3 social_icon" href="kakaologin.lo"><img class="btn_social"src = "img/btn_kakao.png"></a>
        <a class="btn m-3 social_icon" href="home.ma"><img class="btn_social"src = "img/btn_naver.png"></a>
        <a class="btn m-3 social_icon" href="home.ma"><img class="btn_social"src = "img/btn_home.png"></a>
    </div>
</div>
    
<script type="text/javascript">
<!-- 로그인 확인-->
function go_login() {
	if(($('#userid').val()).trim() == '') {
		// 아이디 입력값이 없으면
		alert('아이디를 입력하세요!');
				
		$('#userid').focus();
		return;
		
	} else if (($('#userpw').val()).trim() == '') {
		// 비밀번호 입력값이 없으면
		alert('비밀번호를 입력하세요!');
		
		$('#userpw').focus();
		return;
	} else {
		login_check();
	}
	
	
	
}

function login_check() {
	var vo = $("#dataForm").serialize();
	
	$.ajax({
		type: "POST",
		data : vo,
		url : "login_check.lo",
		success : function ( response ) {
			if(response){
 	    		location.href="home.ma?member_id="+($('#userid').val();
			} else {
				alert("아이디 또는 비밀번호를 확인하세요.");
			}	
		},
	    error: function (xhr, status, error) {
	    	
	    }
	});
}


</script>
</body>
</html>