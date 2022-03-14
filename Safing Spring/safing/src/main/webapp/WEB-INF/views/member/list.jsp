<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' type="text/css" href="css/shop_style.css?v=<%= new Date().getTime() %>" >
	<script type="text/javascript">
	
	function member_delete(member_id){
		swal({
			  title: "회원정보 삭제",
			  text: "선택한 회원정보를 삭제 하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  location.href="member_delete.cu?member_id="+member_id;
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
		            <a class="nav-link sub-category" href="list.cu">
		            	<img class="img-size-30"  src="shop_img/member.png"/>
		            	&nbsp;회원관리
					</a>
				</label>
	      	</li>
	    </ul>
	</div>
</nav>
<!-- Product List -->
<table class="table w-80 my-sm-4 table-center">
  <thead class="table-color">
    <tr class="text-white text-center">
      <th scope="col">아이디</th>
      <th scope="col">이름</th>
      <th scope="col">나이</th>
      <th scope="col">성별</th>
      <th scope="col">전화번호</th>
      <th scope="col">관지자여부</th>      
      <th scope="col">관리</th>   
    </tr>
  </thead>
  <tbody >
  	<c:if test="${ empty list }">
		<tr class="text-align text-center">
			<td colspan="7">검색하신 정보가 없습니다.</td>
		</tr>
	</c:if>
	<c:forEach items="${list }" var="vo">
		<tr>
			<td class="text-align text-center" >${vo.member_id }</td>
			<td class="text-align text-center"> ${vo.member_name }</td>
			<td class="text-align text-center" >${vo.member_age }</td>
			<td class="text-align text-center">${vo.member_gender }</td>
			<td class="text-align text-center">${vo.member_phone }</td>
			<td class="text-align text-center">${fn:toUpperCase(vo.member_admin)}</td>
			<td>
				<div class="manage-image">
					<a data-toggle="modal" data-target="#member${vo.member_id}">
						<img class="img-size-30" src="shop_img/update.png">
					</a>
					<a  onclick="member_delete(${vo.member_id})">
						<img class="img-size-30"  src="shop_img/delete.png">
					</a>
				</div>
			</td>
		</tr>
	</c:forEach>
  </tbody>
</table>
<!-- detail Modal -->
 <c:forEach var="vo" items="${list}">
<div class="modal fade" id="member${vo.member_id}" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog w-40">
    <div class="modal-content">
     <div class="text-black text-center">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">회원 상세정보 및 수정</h5>
        <button type="button" class="close border-none" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form name="member${vo.member_id}" action="member_update.cu" method="post">
       <input type="hidden" name='member_id' value="${vo.member_id}">
		   <table class="table w-100 my-sm-1 table-center">
			  <tbody>
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">아이디</th>
			       <td class="text-align text-left">${vo.member_id}</td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">이름</th>
			       <td class="text-align">
			       		<div class="input-group">
			       			<input type="text" class="form-control" name='member_name' value="${vo.member_name}">
			       		</div>
			       </td>
			    </tr>
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">나이</th>
			       <td class="text-align text-left">${vo.member_age}</td>
			    </tr>
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">성별</th>
			       <td class="text-align text-left">${vo.member_gender}</td>
			    </tr>
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">전화번호</th>
			         <td class="text-align">
			       		<div class="input-group">
			       			<input type="text" class="form-control" name='member_phone' value="${vo.member_phone}">
			       		</div>
			       </td>
			    </tr>
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">관지자여부</th>
			       <td class="text-align text-left">
				       <select name='member_admin' class="select-size" >
							<option class="dropdown-item" value="y" ${vo.member_admin eq "y" ? 'selected' : '' }>Y</option>
							<option class="dropdown-item" value="n" ${vo.member_admin eq "n" ? 'selected' : '' }>N</option>
						</select>
			       </td>
			    </tr>   
			  </tbody>
		</table>
		</form>
      </div>
      <div class="modal-footer">
      	<div class="text-center btn-center">
       	 	<button type="button" class="btn btn-secondary mx-2" data-dismiss="modal" onclick="$('[name=member${vo.member_id}]').submit()">수정</button>
       	 	<button type="button" class="btn btn-outline-success mx-2" data-dismiss="modal">닫기</button>
      	</div>
      </div>
    </div>
    </div>
  </div>
</div>
</c:forEach>
</body>
</html>