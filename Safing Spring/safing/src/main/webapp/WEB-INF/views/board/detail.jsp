<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' type="text/css" href="css/shop_style.css?v=<%= new Date().getTime() %>" >
	
	<style type="text/css">
		#popup { width: 450px; height: 450px; border: 2px solid #666; display: none; }
		#popup-background {
			position: absolute; left:0; top:0; width: 100%; height: 100%;
			background-color: #000; opacity: 0.3; display: none;
		}
		.popup { width: 100%; height: 100%;}
		.comment { margin: 0 auto; padding-top: 20px; width: 500px; }
		#comment_regist { width: 100%}
		#comment_regist span {width: 50%; float: left;}
		textarea#comment { width: 96%; height: 60px; margin-top: 5px; resize: none; }	
		
	</style>


</head>
<body>
<!-- Navigation -->
<form action="list.bo" method="post">
	<input type="hidden" name="curPage" value="1" />
	<input type="hidden" name="member_id" />
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="collapse navbar-collapse text-center container" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <label>
	            <a class="nav-link sub-category" href="list.bo">
	            	<img class="img-size-30"  src="shop_img/board.png"/>
	            	&nbsp;자유게시판
				</a>
			</label>
	      </li>
	      <c:if test="${loginInfo.member_admin eq 'y'}">
		      <li class="nav-item">
		        <label>
		            <a class="nav-link" href="boardmanage.bo">
		            	<img class="img-size-30"  src="shop_img/manage_board.png"/>
		            	&nbsp;게시판관리
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
		</ul>	
	  </div>
	</nav>
</form>
<!-- 목록 형태 -->
<table class="table w-40 my-sm-4 table-center ">
	<tbody >
		<tr>
   			<th colspan="6" scope="row" class="py-4 table-sub-color text-white text-size-2 text-center text-align">
				<h4>게시글</h4>
			</th>
  		   </tr>
		<tr>
			<th scope="row" class="text-align">제목</th>
			<td colspan="5" class="text-align py-3 text-left"> ${vo.board_title }</td>
	    </tr>
		<tr>
			<th scope="row" class="text-align">작성자</th>
			<td class="text-align py-3"> ${vo.member_name }</td>
			<th scope="row" class="text-align">작성일자</th>
			<td class="text-align py-3 "> ${vo.board_writedate }</td>
			<th scope="row" class="text-align">조회수</th>
			<td class="text-align py-3 ">${vo.board_read_cnt }</td>
	    </tr>
		<tr>
			<th scope="row" class="text-align">내용</th>
			<td colspan="5" class=" text-align py-5 text-left">${fn:replace(vo.board_content, crlf, '<br>')}</td>
	    </tr>
		<tr>
			<th scope="row" class="text-align">첨부파일</th>
			<td colspan="5" class="text-align py-3 text-left">${vo.file_name }
				<c:if test="${ ! empty vo.file_name }">
					<iframe width="300" height="300" src="${vo.file_path}" title="video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
					<a id='preview'></a>
					<a href='download.bo?id=${vo.board_id}'><i class='fas fa-download font-img' ></i></a>
				</c:if>
			</td>
	    </tr>
		<tr>
			<td  colspan="6" class="text-align py-3 text-left">
				<a href='detail.bo?id=${vo.prev}'>[이전 글] ${vo.prev_title }</a><br/>
				<a href='detail.bo?id=${vo.next}'>[다음 글] ${vo.next_title }</a>
			</td>
	    </tr>
  </tbody>
</table>

<table class="table w-35 my-sm-4 table-center comment">
	<tbody >
		<tr>
			<th colspan="2" class="text-align text-left">댓글</th>
		</tr>
		<tr class='comment'>
			<td id='comment_regist'>
				<textarea id='comment_content' class="table-radius-5"></textarea> <!-- 글을 작성할 부분 -->	
			</td>
			<td class="text-align">
				<a class='btn-outline-success table-radius-5 px-1' onclick='comment_regist()'>댓글등록</a>
			</td>
		<tr>
		<tr id='comment_list'></tr>
 </tbody>
</table>


<div class="text-center my-sm-3 py-4">
	<button type="button" class="btn btn-secondary mx-2" onclick='$("form").submit()'>목록으로</button>
	<c:if test="${(vo.member_id eq loginInfo.member_id ) or (loginInfo.member_admin eq 'y')   }">
		<button type="button" class='btn btn-outline-success mx-2' onclick='$("form").attr("action", "modify.bo"); $("form").submit()' >수정</button>
		
		<button type="button" class='btn btn-outline-delete mx-2' onclick="board_delete(${vo.board_id})" >삭제</button>
	</c:if>
</div>

<!-- 목록 요청에 필요한 데이터를 post 방식으로 전달하는 방법 -->
<form action="list.bo" method="post">
	<input type="hidden" name="id" value="${vo.board_id }" />	<!-- 해당 글의 id  -->
	<input type="hidden" name="search" value="${page.search }" /> <!-- 검색조건 -->
	<input type="hidden" name="keyword" value="${page.keyword }" /> <!-- 검색어 -->
	<input type="hidden" name="curPage" value="${page.curPage }" /> <!-- 현재 페이지 -->
	<input type="hidden" name="pageList" value="${page.pageList }" /> <!-- 한 페이지당 보여질 목록 수 -->
	<input type="hidden" name="viewType" value="${page.viewType }" /> <!-- 게시판 형태 -->
</form>

<!-- 이미지를 크게 볼 수 있도록 처리 (popup 형태) -->
<div id='popup-background'></div>
<div id='popup' class='center'></div>
<script type="text/javascript" src='js/file_check.js' ></script>
<script type="text/javascript">

// 댓글 등록하기
function comment_regist() {
	if ( ${empty loginInfo } ) { // 로그인 정보가 없으면
		alert('댓글을 등록하려면 로그인하세요!');
		return;
	} else if ( $.trim( $('#comment_content').val() ) == '' ) { // 로그인은 되어 있는데 댓글을 적지 않았다면
		alert('댓글을 입력하세요!');
		$('#comment_content').val('');
		$('#comment_content').focus();
		return;
	}
	
	$.ajax ({
		/* 경로 형태로 url 지정 */
		url: 'board/comment/regist'
		, data : { board_id : ${vo.board_id} , comment_content: $('#comment_content').val()}
			/* 원 글의 id, 입력한 댓글을 데이터로 보냄 */
		, success : function( response ) {
			if ( response ) {	// true
				alert('댓글이 등록되었습니다.');
				$('#comment_content').val('');
				comment_list();			// 댓글 목록 조회 요청
			} else	// false
				alert('댓글 등록 실패!');
		}, error : function (req, text) {
			alert (text + ':' + req.status);
		}
	});
}
function comment_list() {
	$.ajax({
		url : 'board/comment/list/${vo.board_id}'
		// , data : { pid:801 }
		, success : function ( response ) {
			$('#comment_list').html( response );
		// 성공한 결과를 #comment_list 영역에 출력하는데 
		// 결과는 html 코드로 작성된 text 이므로
		// .html( response ); 형태로 값을 출력할수 있도록 지정
		}, error : function (req, text) {
			alert(text + ':' + req.status);
		}
	});
}

function board_delete(board_id){
	swal({
		  title: "게시글 삭제",
		  text: "선택한 글을 삭제 하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  location.href="board_delete.bo?board_id="+board_id;
		  } else {
			return false;
		  }
	});
	return false;
}


$(function () { // $(document).ready() 와 같은 의미
	// 첨부된 파일이 이미지 파일인 경우 미리보기 함.
	if ( ${ ! empty vo.file_name}) {	// 첨부 파일이 있는 경우
		if ( isImage( '${vo.file_name}' ) ) {	// 이미지 파일인 경우
			$('#preview').html("<img src='${vo.file_path}' id='preview-img' />");
		}		
	}
	comment_list();		// 댓글 목록 조회 함수 호출
});

$(document).on('click', '#preview-img', function () {
	$('#popup, #popup-background').css('display', 'block');
	$('#popup').html("<img src='${vo.file_path}' class='popup' />");
}).on('click', '#popup-background', function () {
	$('#popup, #popup-background').css('display', 'none');
});

</script>

</body>
</html>













