<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Safing Main</title>
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
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Roboto|Courgette|Pacifico:400,700" rel="stylesheet">
<title>회원가입</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<style>
		

	body {
		color: #999;
		background: #e2e2e2;
		font-family: 'Roboto', sans-serif;
	}
	.form-control{
      min-height: 41px;
      box-shadow: none;
      border-color: #e1e1e1;
      border-radius: 30px;
   }
	.form-control:focus{
		border-color: #00cb82;
	}	
    .form-control, .btn{       
        border-radius: 3px;
    }
	.form-header{
		margin: -30px -30px 20px;
		padding: 30px 30px 10px;
		text-align: center;
		background: #00cb82;
		border-bottom: 1px solid #eee;
		color: #fff;
	}
	.form-header h2{
		font-size: 34px;
		font-weight: bold;
        margin: 0 0 10px;
		font-family: 'Pacifico', sans-serif;
    }
	.form-header p{
		margin: 20px 0 15px;
		font-size: 17px;
		line-height: normal;
		font-family: 'Courgette', sans-serif;
	}
    .signup-form{
		width: 540px;
		margin: 0 auto;	
		padding: 30px 0;	
	}
    .signup-form form{
		color: #999;
		border-radius: 3px;
        background: #f0f0f0;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
	.signup-form .form-group{
		margin-bottom: 20px;
	}		
	.signup-form label{
		font-weight: normal;
		font-size: 13px;
	}
    .signup-form input[type="checkbox"]{
		margin-top: 2px;
	}
    .signup-form .btn{        
        font-size: 16px;
        font-weight: bold;
		background: #00cb82;
		border: none;
		min-width: 350px;
    }
	.signup-form .btn:hover, .signup-form .btn:focus{
		background: #00b073 !important;
        outline: none;
	}
    .signup-form a{
		color: #00cb82;		
	}
    .signup-form a:hover{
		text-decoration: underline;
	}
	
	.form-flex {
		display: flex;
	}
	
	.ui-datepicker-calendar {
		background-color: white;
		width: 400px;
		height: 200px;
		margin-left:10px;
	}
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
</head>
<body>
<div class="signup-form">
    <form action="join" method="post">
		<div class="form-header">
			<h2>회원 가입</h2>
			<p>안전하게 즐기는 감성 캠핑</p>
		</div>
		<div class="form-group">
			<label>아이디</label>
		
        	<input type="text"  class="form-control" name="member_id" required="required"  "placeholder="아이디" > 
        	<input type="button" id=btn-id value="중복확인" style="margin-left: 10px;">
        	 <div class='valid' style="display: inline;">아이디를 입력하세요(영문소문자, 숫자만 입력 가능)</div>
        	
        	
        </div>
		
        <div class="form-group">
			<label>이름</label>
        	<input type="text" class="form-control" name="member_name" required="required" placeholder="이름" >
     
        </div>
        <div class="form-group">
			<label>이메일</label>
        	<input type="email" class="form-control" name="email" required="required" placeholder="이메일" >
        	<div class='valid'>이메일을 입력하세요</div>
        	
        </div>
		<div class="form-group">
			<label>비밀번호</label>
            <input type="password" class="form-control" name="member_pw" required="required" placeholder="비밀번호">
            <div class='valid'>비밀번호를 입력하세요(영문대/소문자, 숫자를 모두 포함)</div>
        </div>
		<div class="form-group">
			<label>비밀번호 확인</label>
            <input type="password" class="form-control" name="pw_ck" required="required"placeholder="비밀번호">
            <div class='valid'>비밀번호를 다시 입력하세요.</div>
        </div>
       <div  class="form-group"> 
        <label>성별</label>
				<label><input type='radio' name='member_gender' value='남' />남</label>
				<label><input type='radio' name='member_gender' value='여' checked />여</label>
	   </div>
	   <div class="form-group">
			<label>생년월일</label>
            <input type="text" class="form-control" name="age" readonly>
        </div>
        <div class="form-group">
			<label>휴대전화</label>
			<div class="form-flex">
        	<input type="text"  class="form-control" name="member_phone" required="required" style="width:420px; "placeholder="휴대전화" > 
        	<input type="button" value="인증번호" onclick="#" style="margin-left: 10px;">
        	</div>
        </div>
        <div class="form-group">
			<label>인증번호</label>
			<div class="form-flex">
        	<input type="text"  class="form-control" name="tel2" required="required" style="width:420px;"placeholder="인증번호" > 
        	<input type="button" value="인증확인" onclick="#" style="margin-left: 10px;">
        	</div>
        </div>
        
	   
        <div class="form-group">
			<label class="checkbox-inline"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
		</div>
		<div class="form-group">
			<a  class="btn btn-primary btn-block btn-lg" onclick="go_join();">회원가입</a>
		</div>	
    </form>
	<div class="text-center small">Already have an account? <a href="#">Login here</a></div>
</div>

	<script type="text/javascript" src="js/join_check.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	
	function go_join() {
		if ($('[name=member_name]').val() == '') {
			// alert('성명 입력');
			$('[name=member_name]').focus();
			return;
		}
		
		// 중복확인을 하여 이미 사용 중인 경우
		if ($('[name=member_id]').hasClass('checked') ) {
			if ( ($('[name=member_id]').siblings('div').hasClass('invalid'))  ) {
				alert('회원 가입 불가! \n' + join.id.unUsable.desc);
				$('[name=member_id]').focus();
				return;
			}
		} else {
			// 중복확인 하지 않은 경우
			if ( ! item_check ($('[name=member_id]'))  ) return;
			else {
				alert('회원 가입 불가! \n' + join.id.valid.desc);
				$('[name=member_id]').focus();
				return;
			}
		}
		
		if ( ! item_check ( $('[name=member_pw]')) ) return; 
		if ( ! item_check ( $('[name=pw_ck]')) ) return; 
		if ( ! item_check ( $('[name=email]')) ) return; 
	/* 	if ( ! item_check ( $('[name=member_age]')) ) return; 
		if ( ! item_check ( $('[name=member_phone]')) ) return;
		if ( ! item_check ( $('[name=tel2]')) ) return; */
		
		
		$('form').submit();
		
	}
	
	// 각 유효성 검사 항목을 체크할 함수
	function item_check( item ) {
		var data = join.tag_status( item );
		if ( data.code == 'invalid' ) {
			alert('회원 가입 불가! \n' + data.desc );
			item.focus();
			return false;
		} else return true;
	}
	
	
	function daum_post() {
	    new daum.Postcode({
	        oncomplete: function( response) {
	            // 조회된 우편번호를 입력하기 위한 제이쿼리 선언
	            // name이 post 인 태그의 val(값)을 받아온 변수(response) 내 zonecode 값을 담음
	            $('[name=post]').val( response.zonecode )
	            // 검색된 기본 주소 타입: R(도로명), J(지번)
	            var addr = response.userSelectType == 'J' ? response.jibunAddress : response.roadAddress;
	            
	            if ( response.buildingName != '' ) addr += '(' + response.buildingName + ')'; 
	            
	            // name 이 addr 인 태그의 0번지에 값을 할당
	            $('[name=addr]').eq(0).val( addr );
	        }
	    }).open();
	}
	</script>
	
	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
	  <script>
	  // class=chk 에 대한 유효성 검사
	  $('.form-control').on('keyup', function (e) {
		  
		// id 입력 후 Enter 키를 누르면 아이디 중복확인 버튼 실행
		if ($(this).attr('name') == 'member_id') {
			if(e.keyCode==13) id_check();
			else $(this).removeClass('checked');
		}
		var data = join.tag_status ( $(this) ); // 입력하고 있는 tag의 값을 data 에 할당
		// 반환된 결과값(data)엔 code와 desc 가 있음.
		$(this).siblings('div').text(data.desc).removeClass().addClass(data.code);
	});
	  
	  
	  // 생일이 선택되면 기호 나타남.
	  $('[name=age]').change(function () {
		$('#delete').css('display', 'inline');
	  });
	  
	  // #delete 클릭시 생일 값 삭제되면서 기호가 안 나타남
	  $('#delete').click(function () {
		$('[name=age]').val('');
		$('#delete').css('display', 'none');
	})
	  
	  
	  $( function() {
		// 나이 제한을 두기 위한 처리 (만 13세 이상만 가입을 할 수 있다면...)  
		var today = new Date(); // 오늘 날짜 선언
		
		// 오늘 날짜의 연도 데이터를 뽑아 13년을 뺌. 월은 그대로 유지, 일은 오늘 날짜로부터 1일을 뺌
		var endDay = new Date( today.getFullYear() - 13, today.getMonth(), today.getDate() -1);		
	    $( "[name=age]" ).datepicker({
	    	dayNamesMin : ['일', '월', '화', '수', '목', '금', '토' ]
	    ,monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']	
	    ,changeMonth : true 
	    ,changeYear : true
	    ,showMonthAfterYear : true
	    ,dateFormat : 'yy-mm-dd'
	    
	    ,maxDate : endDay   		// 달력에 나타날 최대 일자 지정
	    });
	  } );
	  
	  // 아이디 중복확인 버튼 클릭시
	  $('#btn-id').on('click', function () {
			id_check();
	});
	  
	  function id_check() {
		var $member_id = $('[name=member_id]');
		// class 에 'checked'가 있다면 호출한 곳으로 리턴
		if ($member_id.hasClass('checked')) return;
		
		var data = join.tag_status($member_id);
		if ( data.code == 'invalid') {
			alert ('아이디 중복확인 불필요\n' + data.desc);
			$member_id.focus();
			return;
		}
		// DB에서 id 값을 가져와 중복 여부 확인
		$.ajax({
			url : 'id_check'
			, data : {member_id:$member_id.val()}
			, success : function ( response ) { // true : 사용 가능 / false : 이미 사용 중
				var data = join.member_id_usable( response );		// 성공시 값이 있으면
				$member_id.siblings('div').text( data.desc ).removeClass().addClass( data.code );
				$member_id.addClass('checked');
			}, error : function (req, text) {
				alert(text + ':' + req.status);
			}
			
			
		});
	}
	  
	  
	  </script>

</body>
</html>