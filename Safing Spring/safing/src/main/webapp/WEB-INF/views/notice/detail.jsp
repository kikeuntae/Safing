<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>공지사항</title>
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
<!-- Core theme CSS (includes Bootstrap)-->
<link rel='stylesheet' type="text/css" href="css/board.css?v=<%= new Date().getTime() %>">

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
<h3>공지사항</h3>
<table>
	<tr>
		<th class='w-px120'>제목</th>
		<td colspan="5" class='left'>${vo.board_title }</td>
	</tr>
	<tr>
		<th class='w-px120'>작성자</th>
		<td class='left'>${vo.name }</td>
		<th class='w-px120'>작성일자</th>
		<td class='w-px120'>${vo.board_writedate }</td>
		<th class='w-px80'>조회수</th>
		<td class='w-px80'>${vo.board_read_cnt }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="5" class='left'>${fn:replace(vo.board_content, crlf, '<br>')}</td>
	</tr>
	<tr>
		<th class='w-px120'>첨부파일</th>
		<td colspan="5" class='left'>${vo.file_name }
			<!-- 파일이 있는 경우에만 파일 정보를 나타냄 -->
			<!-- 페이지 이동하는 형태가 아니므로 id 값을 get 방식으로 전달 -->
			<c:if test="${ ! empty vo.file_name }">
				<a id='preview'></a>
				<a href='download.bo?id=${vo.member_id}'><i class='fas fa-download font-img' ></i></a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td class='left' colspan="6">
			<a href='detail.bo?id=${vo.prev}'>[ 이전 글] ${vo.prev_title }</a><br />
			<a href='detail.bo?id=${vo.next}'>[ 다음 글] ${vo.next_title }</a>
		</td>
	</tr>
</table>

<div class='btnSet' style="display: flex; margin: 10px auto; width: 25%">
	<a class='btn-fill' onclick='$("form").submit()'>목록으로</a>
	<!-- 글쓴이만 수정/삭제 권한을 가짐 -->
	<c:if test="${vo.member_id eq loginInfo.member_id }">
		<a class='btn-fill' onclick='$("form").attr("action", "modify.no"); $("form").submit()' >수정</a>
		<a class='btn-fill' onclick='if ( confirm("정말 삭제?") ) {href="delete.bo?id=${vo.board_id }" }' >삭제</a>
	</c:if>
</div>

<div class='comment'> 
	<div id='comment_regist'> <!-- 댓글 등록 부분 -->
		<span class='left'><strong>댓글작성</strong></span>
		<span class='right'><a class='btn-fill-s' onclick='comment_regist()'>댓글등록</a></span>
		<textarea id='comment_content'></textarea> <!-- 글을 작성할 부분 -->	
	</div>
	<div id='comment_list'></div>
</div>


<!-- 목록 요청에 필요한 데이터를 post 방식으로 전달하는 방법 -->
<form action="list.no" method="post">
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



</script>
<script type="text/javascript">
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













