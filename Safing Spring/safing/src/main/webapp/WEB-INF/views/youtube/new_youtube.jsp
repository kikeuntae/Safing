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
<h3>유튜브 글쓰기</h3>

<form action="insert.yu" method="post" enctype="multipart/form-data">
	<table>
			<tr>
			<th class='w-px120'>제목</th>
			<td>
				<input type='text' name="board_title" title='제목' class='chk' />
			</td>
			<li>
			글 종류
				<select name='board_kinds' class='w-px90' >
					<option value="youtube" selected="selected" >youtube</option>
				</select>
			</li>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name='board_content' class='chk' title='내용' ></textarea>
			</td>
		</tr>
		<tr>
			<th class='w-px120'>유튜브 제목</th>
			<td>
				<input type='text' name="youtubetitle" title='제목' class='chk'/>
			</td>
		</tr>
		<tr>
			<th>유튜브 내용</th>
			<td>
				<textarea name='youtubecontent' class='chk' title='내용' ></textarea>
			</td>
		</tr>
		<tr>
			<th>유튜브동영상링크</th>
			<td>
				<input type='text' name="play" title='제목' class='chk' value="" />
			</td>
		</tr>
		<tr>
			<th>유튜브메인이미지링크</th>
			<td>
				<input type='text' name="thumbnails" title='제목' class='chk' value="" />
			</td>
		</tr>
			
	</table>
</form>
<div class='btnSet'>
	<a class='btn-fill' onclick='if ( emptyCheck() ) $("form").submit()'>저장하기</a>
	<a class='btn-empty' href='list.yu'>취소</a>
</div>
</body>
</html>



