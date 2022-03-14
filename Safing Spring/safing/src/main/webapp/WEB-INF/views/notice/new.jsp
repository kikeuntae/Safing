<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' type="text/css" href="css/shop_style.css?v=<%= new Date().getTime() %>" >
	<script type="text/javascript" src='js/file_check.js'></script>

	<script src="js/summernote-lite.js" charset="UTF-8"></script>
	<script src="js/summernote-ko-KR.js" charset="UTF-8"></script>
	<link rel="stylesheet" href="css/summernote-lite.css">

	<!-- include libraries(jQuery, bootstrap) -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	
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


<form action="insert.no" method="post" enctype="multipart/form-data">
   <table class="table w-55 my-sm-4 table-center">
   		<tbody >
   			<tr>
				<th colspan="4" scope="row" class="py-5 table-sub-color text-white text-size-2 text-center text-align">
					<h3>공지 글쓰기</h3>
				</th>
			</tr>
	      	<tr>
		      	<th class="text-align" scope="col">제목</th>
		         <td class="py-4">
		            <input class="form-control input-size-90 chk" name="board_title" type="text" placeholder="제목을 입력하세요"/>
		         </td>
		          <th class="text-align text-center" scope="col">글종류</th>
		         <td class="text-align text-left py-4">
			       <select name='board_kinds' class="select-size" >
						<option class="dropdown-item" value="free" >자유게시판</option>
						<option class="dropdown-item" value="notice" selected="selected">공시사항</option>
						<option class="dropdown-item" value="youtube" >유투브</option>
						<option class="dropdown-item" value="video" >동영상</option>
						<option class="dropdown-item" value="review">리뷰</option>
					</select>
		        </td>
	 	   	 </tr>
		      <tr>
		         <th scope="col" class="text-align">내용</th>
		         <td colspan="3">
		               <div class="container px-0">
		                    <textarea class="summernote" name="editordata"></textarea>    
		               </div>
		               <script>
		                  $('.summernote').summernote({
		                       height: 450,
		                       lang: "ko-KR"
		                  });
		               </script>
		         </td>
		      </tr>
		      <tr>
		         <th scope="col" class="text-align">첨부파일</th>
		         <td colspan="3" class='text-left py-4'>
		           	<input type="file" id='attach-file' name='file' />   
					<!-- 이미지 파일인 경우 미리보기 적용 -->
					<span id='preview' ></span>
					<a id='delete-file'>
						<img src="shop_img/close.png" style="width:2%; margin-left : 0.5rem; height:auto; vertical-align: middle; cursor: pointer;"/>
					</a>	        
		         </td>
		      </tr>
         </tbody>
   </table>
</form>
<div class="text-center my-1 mb-5">
	<button type="button" class="btn btn-secondary mx-2" onclick="if ( emptyCheck() ) $('form').submit()">저장하기</button>
 	<button type="button" class="btn btn-outline-success mx-2" onclick="fncancel()">취소하기</button> 		
</div>

<script type="text/javascript">
	<!-- <anager Check -->
	if ( ${ ! empty vo.file_name } ) {
		// 첨부파일이 있는 글인 경우 삭제버튼 보이게
		$('#delete-file').css('display', 'inline');
		// 첨부파일이 이미지 파일인 경우 미리보기
		if ( isImage( '${vo.file_name}' ) )
			$('#preview').html("<img src='${vo.file_path}' id='preview-img'/> ");
	} else {
		$('#delete-file').css('display', 'none');
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
				  location.href="list.no";
			  } else {
				return false;
			  }
		});
		return false;
	}
	
	
	
	</script>


</body>
</html>



