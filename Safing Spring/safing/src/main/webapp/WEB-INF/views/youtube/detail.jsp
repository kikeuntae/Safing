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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="collapse navbar-collapse text-center container" id="navbarSupportedContent">
    	<ul class="navbar-nav mr-auto">
      		<li class="nav-item">
		        <label class="m-0">
		            <a class="nav-link sub-category" href="list.cu">
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
<!-- 목록 형태 -->
<table class="table w-65 my-sm-4 table-center">
	<tbody >
		<tr class="h-100">
   			<th colspan="2" scope="row" class="py-4 text-white text-size-2 text-center text-align">
				<iframe src="https://www.youtube.com/embed/${vo.play }" title="YouTube video player" class="w-100 h-100p"
				frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
				</iframe>
			</th>
  		</tr>
		
		<tr>
			<th scope="row" class="text-align">제목</th>
			<td class="text-align py-3 text-left">${vo.youtubetitle }</td>
	    </tr>
		<tr>
			<th scope="row" class="text-align">내용</th>
			<td  class=" text-align py-3 text-left">${fn:replace(vo.youtubecontent, crlf, '<br>')}</td>
	    </tr>
  </tbody>
</table>

<div class="text-center my-sm-3">
	<button type="button" class="btn btn-secondary mx-2" onclick='$("form").submit()'>목록으로</button>
	<c:if test="${(loginInfo.member_admin eq 'y')   }">
		<button type="button" class='btn btn-outline-success mx-2' onclick='$("form").attr("action", "modify.yu"); $("form").submit()' >수정</button>
		
		<button type="button" class='btn btn-outline-delete mx-2' onclick="youtube_delete(${vo.id})" >삭제</button>
	</c:if>
</div>


<!-- 목록 요청에 필요한 데이터를 post 방식으로 전달하는 방법 -->
<form action="list.yu" method="post">
	<input type="hidden" name="id" value="${vo.id }" />	<!-- 해당 글의 id  -->
	<input type="hidden" name="search" value="${page.search }" /> <!-- 검색조건 -->
	<input type="hidden" name="keyword" value="${page.keyword }" /> <!-- 검색어 -->
	<input type="hidden" name="curPage" value="${page.curPage }" /> <!-- 현재 페이지 -->
	<input type="hidden" name="pageList" value="${page.pageList }" /> <!-- 한 페이지당 보여질 목록 수 -->
	<input type="hidden" name="viewType" value="${page.viewType }" /> <!-- 게시판 형태 -->
</form>

<script type="text/javascript">
function youtube_delete(id){
	swal({
		  title: "게시글 삭제",
		  text: "선택한 글을 삭제 하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  location.href="delete.yu?id=" + id;
		  } else {
			return false;
		  }
	});
	return false;
}


</script>



</body>
</html>













