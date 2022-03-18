<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<html>
<head>
	<link rel='stylesheet' type="text/css" href="css/tiles.css?v=<%= new Date().getTime() %>" >
	<link rel='stylesheet' type="text/css" href="css/join_style.css?v=<%= new Date().getTime() %>" >
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
</head>
<body>
<form action="join_member" method="post">
	<table class="table w-35 my-sm-4 table-center">
			<tbody>
	    		<tr>
	     			<th colspan="2" scope="row" class="py-5 table-sub-color text-white text-size-2 text-center text-align">
	  					<h2>회원 가입</h2>
	  					<p class="m-0">안전하게 즐기는 감성 캠핑</p>
					</th>
	  		   </tr>
			   <tr>
			   		<th scope="row" class="text-align">아이디</th>
				    <td class="text-align py-3">
				    	<div class="row">
				    		<input class="form-control input-size-70 chk" name="member_id" type="text" placeholder="아이디를 입력하세요"/>
				    		<input type="button" class="btn btn-outline-success p-0 input-size-25 ms-2" id=btn-id value="중복확인">
				    		<div class='valid text-left' id="valid_comment" >영문소문자, 숫자만 입력하세요.</div>
				    	</div>
				    </td>
			   </tr>
			   <tr>
			   		<th scope="row" class="text-align">비밀번호</th>
				    <td class="text-align py-3">
				    	<div class="row">
				    		<input class="form-control input-size-92 chk" name="member_pw" type="password" placeholder="비밀번호를 입력하세요"/>
					    	<div class='valid text-left' id="valid_comment">영문대/소문자, 숫자 모두 모두 포함하여 입력해주세요</div>
				    	</div>
				    </td>
			   </tr>
			   <tr>
			   		<th scope="row" class="text-align">비밀번호<br>확인</th>
				    <td class="text-align py-3">
				    	<div class="row">
				    		<input class="form-control input-size-92 chk" name="member_pw_ck" type="password" placeholder="비밀번호를 다시 입력하세요"/>
				    		<div class='valid text-left' id="valid_comment">비밀번호를 다시 입력하세요.</div>
				    	</div>
				    </td>
				   
			   </tr>
			   <tr>
			   		<th scope="row" class="text-align">성명</th>
				    <td class="text-align py-3">
				    	<div class="row">
				    		<input class="form-control input-size-92" name="member_name" type="text" placeholder="성명을 입력하세요"/>
				    	</div>
				    </td>
			   </tr>
			   <tr>
			   		<th scope="row" class="text-align">나이</th>
				    <td class="text-align py-3">
				    	<div class="row">
				    		<input class="form-control input-size-92" name="member_age" type="number" placeholder="'만' 나이를 입력하세요"/>
				    	</div>
				    </td>
			   </tr>
		 	   <tr>
			   		<th scope="row" class="text-align">전화번호</th>
				    <td class="text-align py-3">
				    	<div class="row">
				    		<input class="form-control input-size-92" name="member_phone" type="text" placeholder="'-'을 입력하세요"/>
				    	</div>
				    </td>
			   </tr>
			   <tr>
			   		<th scope="row" class="text-align">성별</th>
				    <td class="text-align py-3">
				    	<div class="col">
				    		<label><input type='radio' name='member_gender' value='남' checked/> 남성</label>
							<label class="ms-2"><input type='radio' name='member_gender' value='여'  />여성</label>
				    	</div>
				    </td>
			   </tr>
			   
			</tbody>
	</table>
</form>
<div class="text-center">
	<button type="button" class="btn btn-secondary mx-2 mb-5" onclick='go_join()'>회원가입</button>
 	<button type="button" class="btn btn-outline-success mx-2 mb-5" onclick="fncancel()">취소하기</button>  		
</div>

<script type="text/javascript" src="js/join_check.js"></script>
<script>
	function go_join() {
		if ($('[name=member_name]').val() == '') {
			swal("회원가입이 불가능합니다.", "성함을 입력하세요.", "error");
			$('[name=member_name]').focus();
			return;
		}
		
		// 중복확인을 하여 이미 사용 중인 경우
		if ($('[name=member_id]').hasClass('checked') ) {
			if ( ($('[name=member_id]').siblings('div').hasClass('invalid'))  ) {
				swal("회원가입이 불가능합니다.", join.id.unUsable.desc, "error");
				$('[name=member_id]').focus();
				return;
			}
		} else {
			// 중복확인 하지 않은 경우
			if ( ! item_check ($('[name=member_id]'))  ) return;
			else {
				swal("회원가입이 불가능합니다.", join.id.valid.desc, "error");
				$('[name=member_id]').focus();
				return;
			}
		}
		
		if ( ! item_check ( $('[name=member_pw]')) ) return; 
		if ( ! item_check ( $('[name=member_pw_ck]')) ) return; 

		member_insert();
		
		
	}
	
	//회원가입
	function member_insert() {
		var vo = $("form").serialize();
		
		$.ajax({
			type: "POST",
			data : vo,
			url : "join_member.lo",
			success : function ( response ) {
				if(response){
					swal("회원가입 성공!", "회원가입을 축하드립니다.", "success");
	 	    		location.href="home.ma";
				} else {
					swal("회원가입 실패!", "회원가입이 불가능합니다.", "error");
				}	
			},
		    error: function (xhr, status, error) {
		    	
		    }
		});
	}
	
	
	// 각 유효성 검사 항목을 체크할 함수
	function item_check( item ) {
		var data = join.tag_status( item );
		if ( data.code == 'invalid' ) {
			swal("회원가입이 불가능합니다.", data.desc, "error");
			item.focus();
			return false;
		} else return true;
	}
</script>
	
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script>
	// class=chk 에 대한 유효성 검사
	$('.chk').on('keyup', function (e) {
		  
		// id 입력 후 Enter 키를 누르면 아이디 중복확인 버튼 실행
		if ($(this).attr('name') == 'member_id') {
			if(e.keyCode==13) id_check();
			else $(this).removeClass('checked');
		}
		var data = join.tag_status ( $(this) ); // 입력하고 있는 tag의 값을 data 에 할당
		// 반환된 결과값(data)엔 code와 desc 가 있음.
		$(this).siblings('div').text(data.desc).removeClass().addClass(data.code);
	});
	  
 
	// 아이디 중복확인 버튼 클릭시
	$('#btn-id').on('click', function () {
		id_check();
	});
	  
	function id_check() {
		var $id = $('[name=member_id]');
		// class 에 'checked'가 있다면 호출한 곳으로 리턴
		if ($id.hasClass('checked')) return;
		
		var data = join.tag_status($id);
		if ( data.code == 'invalid') {
			swal("중복확인이 불가능합니다.", data.desc, "error");
			$id.focus();
			return;
		}
		// DB에서 id 값을 가져와 중복 여부 확인
		$.ajax({
			type: "POST",
			data : {id:$id.val()},
			url : "id_check.lo",
			success : function ( response ) {// true : 사용 가능 / false : 이미 사용 중
				var data = join.id_usable( response );		// 성공시 값이 있으면
				$id.siblings('div').text( data.desc ).removeClass().addClass( data.code );
				$id.addClass('checked');
			},
		    error: function (xhr, status, error) {
		    	
		    }
		});
	}
  
	function fncancel(){
		swal({
			  title: "등록한 정보가 모두 사라집니다.",
			  text: "취소하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  location.href="home.ma";
			  } else {
			   
			  }
		});
	}
	
</script>

</body>
</html>