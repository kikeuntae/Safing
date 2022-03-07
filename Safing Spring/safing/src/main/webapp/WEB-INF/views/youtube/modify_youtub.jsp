<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Date"%>
=======
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
>>>>>>> origin/명운
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Insert title here</title>
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

=======
<title>Insert title here</title>
>>>>>>> origin/명운
<script type="text/javascript" src='js/file_check.js?v=<%=new Date().getTime()%>'></script>
</head>
<body>
<h3>유튜브 수정</h3>
<<<<<<< HEAD
<div id="youtube_content" >
<form action="update.yu?id=${vo.id }" method="post" enctype="multipart/form-data" style="width: 50%; margin: 0 auto;">
=======

<form action="update.yu" method="post" enctype="multipart/form-data">
>>>>>>> origin/명운
	<table>
		<tr>
			<th class='w-px120'>제목</th>
			<td>
				<input type='text' name="youtubetitle" title='제목' class='chk' value="${vo.youtubetitle }" />
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name='youtubecontent' class='chk' title='내용' >${vo.youtubecontent}</textarea>
			</td>
		</tr>
		<tr>
<<<<<<< HEAD
			<th>유튜브썸네일</th>
			<td>
				<input id='href' type='text' name="thumbnails" title='제목' class='chk' value="${vo.thumbnails }" />
				<a href='img/upload.png' onclick=" window.open(this.href, '_blank', 'width=800, height=600'); return false;">&nbsp;&nbsp;예시보기</a>
			</td>
		</tr>
		<tr>
			<th>유튜브재생ID</th>
			<td>			
				<input id='id' type='text' name="play" title='제목' class='chk' value="${vo.play }" />
				<a href='img/upload.png' onclick=" window.open(this.href, '_blank', 'width=800, height=600'); return false;">&nbsp;&nbsp;예시보기</a>
			</td>
		</tr>		
	</table>
</form>
</div>
<div class='btnSet' style="    display: flex;
    width: 200px;
    margin: 0 auto;
    margin-top: 20px;">
	<a class='btn-fill' onclick='if ( emptyCheck() ) $("form").submit()'>저장하기</a>
	<a class='btn-fill'onclick='history.go(-1)'>취소</a>
=======
			<th>게시판id </th>
			<td>
			<input type='text' name="board_id" title='게시판id' class='chk' value="${vo.board_id }"  readonly />
			</td>
		</tr>
		<tr>
			<th>유튜브동영상링크</th>
			<td>
				<input type='text' name="play" title='제목' class='chk' value="${vo.play }" />
			</td>
		</tr>
		<tr>
			<th>유튜브메인이미지링크</th>
			<td>
				<input type='text' name="thumbnails" title='제목' class='chk' value="${vo.thumbnails }" />
			</td>
		</tr>
			
	</table>
</form>
<div class='btnSet'>
	<a class='btn-fill' onclick='if ( emptyCheck() ) $("form").submit()'>저장하기</a>
	<td class='left'><a onclick='go_detail(${vo.board_id})'>취소</a></td>
>>>>>>> origin/명운
</div>

<script type="text/javascript">
	function go_detail(id) {
		$('[name=id]').val(id);
		$('form').attr('action', 'detail.bo');
		$('form').submit();
	}
<<<<<<< HEAD
	
	

	
	
	
	
</script>
</body>
<style>
input {
	width: 100%;
}

textarea {
	width: 100%;
}

td {
	display: flex;
}
#id {
	width: 1300px;
}

#href {
	width: 1300px;
}
</style>
=======
</script>

</body>
>>>>>>> origin/명운
</html>



