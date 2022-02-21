<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='js/file_check.js?v=<%=new Date().getTime()%>'></script>
</head>
<body>
<h3>유튜브 수정</h3>

<form action="update.yu" method="post" enctype="multipart/form-data">
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
</div>

<script type="text/javascript">
	function go_detail(id) {
		$('[name=id]').val(id);
		$('form').attr('action', 'detail.bo');
		$('form').submit();
	}
</script>

</body>
</html>



