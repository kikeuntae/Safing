<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' type="text/css" href="css/shop_style.css?v=<%= new Date().getTime() %>" >
	<script type="text/javascript" src='js/file_check.js?v=<%=new Date().getTime()%>'></script>
	
	<script type="text/javascript">
	<!-- <anager Check -->
	if (${loginInfo.member_admin eq 'n' || empty loginInfo}) {
		alert("관리자로 로그인시 가능합니다.");
		location.href="list.shop";
	}
	
	function fncancel(){
		swal({
			  title: "입력한 내용이 모두 사라집니다.",
			  text: "취소하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  location.href="list.yu";
			  } else {
				return false;
			  }
		});
		return false;
	}
	

	
	</script>
	
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="collapse navbar-collapse text-center container" id="navbarSupportedContent">
    	<ul class="navbar-nav mr-auto">
      		<li class="nav-item">
		        <label class="m-0">
		            <a class="nav-link sub-category" href="list.yu">
		            	<img class="img-size-30"  src="shop_img/youtube.png"/>
		            	&nbsp;유투브
					</a>
				</label>
	      	</li>
	    </ul>
	    <ul class="navbar-nav form-inline my-2 ">
			 <c:if test="${loginInfo.member_admin eq 'y' }">
				<li class="nav-item m-2">
				    <div>
				 	      <a type="button" class="btn btn-outline-secondary mt-auto py-1-1 px-2" href="new.yu">
				 	     	 <img class="img-size-21"  src="shop_img/upload.png"/>
				     	     &nbsp;영상올리기
				 		  </a> 
				     </div>
				</li>
			</c:if>
		</ul>	
	</div>
</nav>

<form action="insert.yu" method="post" enctype="multipart/form-data">
	<table class="table w-40 my-sm-4 table-center">
  		<tbody >
	  		<tr>
				<th colspan="3" scope="row" class="py-5 table-sub-color text-white text-size-2 text-center text-align">
					<h2>유투브 영상올리기</h2>
				</th>
			</tr>
			<tr>
				<th scope="row" class="text-align">제목</th>
				<td colspan="2"class="text-align py-4">
			    	<div class="row m-0">
			    		<input class="form-control input-size-90" name="youtubetitle" type="text" placeholder="제목을 입력하세요"/>
			    	</div>
		   		</td>
		    </tr>
			<tr>
				<th scope="row" class="text-align">내용</th>
				<td colspan="2" class="text-align py-4">
			    	<div class="row m-0">
			    		<textarea class="form-control input-size-90 h-150p chk" name='youtubecontent' title='내용' ></textarea>
			    	</div>
		   		</td>
		    </tr>
			<tr>
				<th scope="row" class="text-align">썸네일</th>
				<td class="text-align py-4">
					<input type='text' name="thumbnails" title='제목' class='form-control w-100 chk' placeholder="예시 : https://img.youtube.com/vi/Tmuq76PeTf4/0.jpg" />
					
				</td>
				<td class="text-align py-4">
					<a type="button" data-toggle="modal" data-target="#modal1">&nbsp;&nbsp;예시보기</a>
				</td>
		    </tr>
			<tr>
				<th scope="row" class="text-align">재생ID</th>
				<td class="text-align py-4">
					<input type='text' name="play" title='제목' class='chk form-control w-100' placeholder="id값만 입력해주세요."/>
				</td>
				<td class="text-align py-4">
					<a type="button" data-toggle="modal" data-target="#modal2">&nbsp;&nbsp;예시보기</a>
				</td>
		    </tr>
 		 </tbody>
	</table>
</form>

<div class="text-center my-1 mb-5">
	<button type="button" class="btn btn-secondary mx-2" onclick="if ( emptyCheck() ) $('form').submit()">등록하기</button>
 	<button type="button" class="btn btn-outline-success mx-2" onclick="fncancel()">취소하기</button> 		
</div>

<!-- detail Modal1 -->
<div class="modal fade" id="modal1" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog w-40">
    <div class="modal-content">
     <div class="text-black text-center">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">예시보기</h5>
        <button type="button" class="close border-none" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      		<img class="img-fluid d-block mx-auto w-100" src="img/upload.png" class="w-100"/>
      </div>
      <div class="modal-footer">
      	<div class="text-center btn-center">
       	 	<button type="button" class="btn btn-outline-success mx-2" data-dismiss="modal">닫기</button>
      	</div>
      </div>
    </div>
    </div>
  </div>
</div>
<!-- detail Modal2 -->
<div class="modal fade" id="modal2" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog w-40">
    <div class="modal-content">
     <div class="text-black text-center">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">예시보기</h5>
        <button type="button" class="close border-none text-size-2" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      		<img class="img-fluid d-block mx-auto w-100" src="img/upload.png" class="w-100"/>
      </div>
      <div class="modal-footer">
      	<div class="text-center btn-center">
       	 	<button type="button" class="btn btn-outline-success mx-2" data-dismiss="modal">닫기</button>
      	</div>
      </div>
    </div>
    </div>
  </div>
</div>



</body>
</html>



