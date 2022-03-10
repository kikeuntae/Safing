<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' type="text/css" href="css/shop_style.css?v=<%= new Date().getTime() %>" >
<script type="text/javascript">
<!-- <anager Check -->
if (${loginInfo.member_admin eq 'n' || empty loginInfo}) {
	alert("관리자로 로그인시 가능합니다.");
	location.href="list.shop";
}	
</head>
<body>
</script>
</head>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="collapse navbar-collapse text-center container" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <label class="m-0">
            <a class="nav-link" href="list.shop">
            	<img class="img-size-30"  src="shop_img/camping.png"/>
            	&nbsp;캠핑용품
			</a>
		</label>
      </li>
      <li class="nav-item">
     	 <label class="m-0">
            <a class="nav-link sub-category" href="ordermanage.shop">
            	<img class="img-size-30"  src="shop_img/order.png"/>
            	&nbsp;주문관리
			</a>
		</label>
      </li>
       <li class="nav-item">
	         <label class="m-0">
		            <a class="nav-link" href="productmanage.shop">
		            	<img class="img-size-30"  src="shop_img/product.png"/>
		            	&nbsp;상품관리
					</a>
			  </label>
	      </li> 
       <li class="nav-item dropdown m-0">
        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
          <img class="img-size-30"  src="shop_img/category.png"/>
          &nbsp;카테고리
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        	<a class="dropdown-item" href="ordermanage.shop?search=all">전체선택</a>
        	<div class="dropdown-divider"></div>
	        <a class="dropdown-item" href="ordermanage.shop?pageList=5">5개씩</a>
			<a class="dropdown-item" href="ordermanage.shop?pageList=10">10개씩</a>
			<a class="dropdown-item" href="ordermanage.shop?pageList=15">15개씩</a>
			<a class="dropdown-item" href="ordermanage.shop?pageList=20">20개씩</a>
        </div>
      </li>
    </ul>
    <form name="managerpage" class="form-inline my-2 my-lg-0" action="ordermanage.shop" method="post">
		<input type="hidden" name="curPage" value="1" />
	    <ul class="navbar-nav form-inline my-2 ">
	    	<li class="nav-item m-2">
				<input class="form-control mr-sm-2" name='keyword' value="${page.keyword}" type="search" placeholder="주문검색하기" aria-label="Search">
			</li>
	    	<li class="nav-item m-2">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit" onclick="$('form').submit()">검색</button>
			</li>
		</ul>	
	</form>
  </div>
</nav>
<!-- Product List -->
<table class="table w-80 my-sm-4 table-center">
  <thead class="table-color">
    <tr class="text-white text-center">
      <th scope="col">번호</th>
      <th scope="col">회원아이디</th>
      <th scope="col">주문번호</th>
      <th scope="col">주문상품번호</th>
      <th scope="col">주문일자</th>    
      <th scope="col">주문상태</th>    
      <th scope="col">관리</th>   
    </tr>
  </thead>
  <tbody >
  	<c:if test="${ empty page.list }">
		<tr class="text-align">
			<td colspan="7">검색하신 정보가 없습니다.</td>
		</tr>
	</c:if>
	<c:forEach items="${page.list}" var="vo">
		<tr>
			<!-- 번호 -->
			<td class="text-align text-center" scope="row">${vo.no }</td>
			<!-- 회원아이디 -->
			<td class="text-align text-center">${vo.member_id }</td>
			<!-- 주문번호 -->
			<td class="text-align text-center" scope="row">${vo.order_num }</td>
			<!-- 주문상품번호 -->
			<c:if test="${vo.product_num gt 0 }">
				<td class="text-align text-center">상품&nbsp;${vo.product_num}</td>
			</c:if>
			<c:if test="${vo.product_num eq 0 || vo.product_num eq null}">
				<td class="text-align text-center">패지키&nbsp;${vo.package_num}</td>
			</c:if>
			<!-- 주문일자 -->
			<td class="text-align text-center">${vo.order_date}</td>
			<!-- 주문상태 -->
			<c:if test="${vo.order_state_num eq 0}">
				<td class="text-align text-center">입금확인 중</td>
			</c:if>
			<c:if test="${vo.order_state_num eq 1}">
				<td class="text-align text-center">배송준비</td>
			</c:if>
			<c:if test="${vo.order_state_num eq 2}">
				<td class="text-align text-center">배송 중</td>
			</c:if>
			<c:if test="${vo.order_state_num eq 3}">
				<td class="text-align text-center">배송완료</td>
			</c:if>
			<c:if test="${vo.order_state_num eq 4}">
				<td class="text-align text-center">환불</td>
			</c:if>
			<!-- 관리 -->
			<td>
				<div class="manage-image">
					<a  data-toggle="modal"  data-target="#order${vo.order_num}">
						<img class="img-size-21" src="shop_img/update.png">
					</a>
				</div>
			</td>
		</tr>
	</c:forEach>
  </tbody>
</table>
<!-- Pager -->
<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/shoppage.jsp" />
</div>
<!-- detail Modal -->
 <c:forEach var="vo" items="${page.list}">
<div class="modal fade" id="order${vo.order_num}" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog w-40">
    <div class="modal-content">
     <div class="text-black text-center">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">주문 상세정보 및 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form name="order${vo.order_num}" action="order_update.shop" method="post">
       	 <input type="hidden" name='order_num' value="${vo.order_num}">
       	 <input type="hidden" name='member_id' value="${vo.member_id}">
       	 <input type="hidden" name='receiver_name' value="${vo.receiver_name}">
       	 <input type="hidden" name='receiver_phone' value="${vo.receiver_phone}">
       	 <input type="hidden" name='receiver_addr' value="${vo.receiver_addr}">
       	 <input type="hidden" name='product_num' value="${vo.product_num}">
       	 <input type="hidden" name='package_num' value="${vo.package_num}">
       	 <input type="hidden" name='product_price' value="${vo.product_price}">
       	 <input type="hidden" name='order_count' value="${vo.order_count}">
		   <table class="table w-100 my-sm-1 table-center">
			  <tbody>
			    <tr>
			       <th class="table-sub-color text-white text-center" scope="col">주문번호</th>
			       <td class="text-align">${vo.order_num}</td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center" scope="col">회원아이디</th>
			       <td class="text-align">${vo.member_id}</td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center" scope="col">수령인 이름</th>
			       <td class="text-align">${vo.receiver_name}</td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center" scope="col">수령인 핸드폰</th>
			       <td class="text-align">${vo.receiver_phone}</td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">수령인 주소</th>
			       <td class="text-align">${vo.receiver_addr}</td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center" scope="col">주문상태</th>
			       <td class="text-align">
				       <select name='order_state_num' class="select-size btn-center">
								<option class="dropdown-item" value="0" ${vo.order_state_num eq 0 ? 'selected' : '' }>입금확인 중</option>
								<option class="dropdown-item" value="1" ${vo.order_state_num eq 1 ? 'selected' : '' }>배송준비</option>
								<option class="dropdown-item" value="2" ${vo.order_state_num eq 2 ? 'selected' : '' }>배송 중</option>
								<option class="dropdown-item" value="3" ${vo.order_state_num eq 3 ? 'selected' : '' }>배송완료</option>
								<option class="dropdown-item" value="4" ${vo.order_state_num eq 4 ? 'selected' : '' }>환불</option>
						</select>
				   </td>	
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center" scope="col">주문일자</th>
			       <td class="text-align text-center">${vo.order_date}</td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center" scope="col">주문상품번호</th>
			       <c:if test="${vo.product_num gt 0 }">
						<td class="text-align">상품&nbsp;${vo.product_num}</td>
					</c:if>
					<c:if test="${vo.product_num lt 0 || vo.product_num eq null}">
						<td class="text-align">패지키&nbsp;${vo.package_num}</td>
					</c:if>
			    </tr>
			     <tr>
			       <th class="table-sub-color text-white text-center" scope="col">결제금액</th>
			       <td class="text-align">
			       		<fmt:formatNumber value="${vo.product_price}" pattern="#,###"/>&nbsp;원
			       </td>
			    </tr>    
			     <tr>
			       <th class="table-sub-color text-white text-center" scope="col">주문수량</th>
			       <td class="text-align">${vo.order_count}</td>
			    </tr>
			    <tr></tr>    
			  </tbody>
		</table>
		</form>
      </div>
      <div class="modal-footer">
	      <div class="text-center btn-center">
	       	 	<button type="button" class="btn btn-secondary mx-2" data-dismiss="modal" onclick="$('[name=order${vo.order_num}]').submit()">수정</button>
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