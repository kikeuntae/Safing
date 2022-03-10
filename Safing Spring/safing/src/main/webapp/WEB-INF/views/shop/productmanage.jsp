<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	function product_delete(product_num){
		var msg = "선택하신 상품을 삭제하시겠습니까?"
		if(confirm(msg)){
			location.href="product_delete.shop?product_num="+product_num;
		}
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
	            <a class="nav-link" href="list.shop">
	            	<img class="img-size-30"  src="shop_img/camping.png"/>
	            	&nbsp;캠핑용품
				</a>
			</label>
	      </li>
	      <li class="nav-item">
	     	  <label class="m-0">
	            <a class="nav-link" href="ordermanage.shop">
	            	<img class="img-size-30"  src="shop_img/order.png"/>
	            	&nbsp;주문관리
				</a>
			</label>
	      </li>
	      <li class="nav-item">
	          <label class="m-0">
	            <a class="nav-link sub-category" href="productmanage.shop">
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
	        	<a class="dropdown-item" href="productmanage.shop?search=all">전체선택</a>
	        	<div class="dropdown-divider"></div>
		        <a class="dropdown-item" href="productmanage.shop?search=감성용품">감성용품</a>
				<a class="dropdown-item" href="productmanage.shop?search=텐트">텐트</a>
				<a class="dropdown-item" href="productmanage.shop?search=의자">의자</a>
				<a class="dropdown-item" href="productmanage.shop?search=테이블">테이블</a>
				<a class="dropdown-item" href="productmanage.shop?search=매트/침낭">매트/침낭</a>
				<a class="dropdown-item" href="productmanage.shop?search=취사/BBQ">취사/BBQ</a>   
	        </div>
	      </li>
	    </ul>
	    <form class="form-inline my-2 my-lg-0" action="productmanage.shop" method="post">
		<input type="hidden" name="curPage" value="1" />
	    <ul class="navbar-nav form-inline my-2 ">
	    	<li class="nav-item m-2">
			      <input class="form-control mr-sm-2" name='keyword' value="${page.keyword}" type="search" placeholder="상품검색하기" aria-label="Search">
			</li>
	    	<li class="nav-item m-2">
			      <button class="btn btn-outline-success my-2 my-sm-0" type="submit" onclick="$('form').submit()">검색</button>
			</li>
	    	<li class="nav-item m-2">
			    <div>
			 	      <a type="button" class="btn btn-outline-secondary mt-auto py-0 px-2" href="productnew.shop">
			 	     	 <img class="img-size-30"  src="shop_img/product_insert.png"/>
			     	     &nbsp;상품등록
			 		  </a> 
			     </div>
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
      <th scope="col">상품이름</th>
      <th scope="col">상품번호</th>
      <th scope="col">가격</th>
      <th scope="col">수량</th>
      <th scope="col">상품종류</th>      
      <th scope="col">등록일자</th>     
      <th scope="col">관리</th>     
    </tr>
  </thead>
  <tbody >
  	<c:if test="${ empty page.list }">
		<tr class="text-align text-center">
			<td colspan="8">검색하신 정보가 없습니다.</td>
		</tr>
	</c:if>
	<c:forEach items="${page.list}" var="vo">
		<tr>
			<td class="text-align text-center" >${vo.no }</td>
			<td class="text-align">${vo.product_name }</td>
			<td class="text-align text-center" >${vo.product_num }</td>
			<td class="text-align text-center"><fmt:formatNumber value="${vo.product_price}" pattern="#,###"/>&nbsp;원</td>
			<td class="text-align text-center">${vo.product_stock}</td>
			<td class="text-align text-center">${vo.product_kind}</td>
			<td class="text-align text-center">${vo.product_date}</td>
			<td>
				<div class="manage-image">
					<a data-toggle="modal" data-target="#product${vo.product_num}">
						<img class="img-size" src="shop_img/update.png">
					</a>
					<a  onclick="product_delete(${vo.product_num})">
						<img class="img-size"  src="shop_img/delete.png">
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

<div class="modal fade" id="product${vo.product_num}" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog w-40">
    <div class="modal-content">
     <div class="text-black text-center">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">상품 상세정보 및 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form name="product${vo.product_num}" action="product_update.shop" method="post">
       <input type="hidden" name='product_num' value="${vo.product_num}">
		   <table class="table w-100 my-sm-1 table-center">
			  <tbody>
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">상품번호</th>
			       <td class="text-align text-left">${vo.product_num}</td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">상품이름</th>
			       <td class="text-align">
			       		<div class="input-group">
			       			<input type="text" class="form-control" name='product_name' value="${vo.product_name}">
			       		</div>
			       </td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">가격</th>
			       <td class="text-align text-center">
			       		<div class="input-group">
						  <input type="number" class="form-control" name='product_price' value="${vo.product_price}" aria-label="Amount (to the nearest dollar)">
						  <div class="input-group-append">
						    <span class="input-group-text form-control text-black">원</span>
						  </div>
						</div>
			       </td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">수량</th>
			       <td class="text-align text-center">
			       		<div class="input-group">
						  <input type="number" class="form-control" name='product_stock' value="${vo.product_stock}" aria-label="Amount (to the nearest dollar)">
						  <div class="input-group-append">
						    <span class="input-group-text form-control text-black">개</span>
						  </div>
						</div>
			       </td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">상품종류</th>
			       <td class="text-align text-left">
				       <select name='product_kind' class="select-size" >
							<option class="dropdown-item" value="감성용품" ${vo.product_kind eq "감성용품" ? 'selected' : '' }>감성용품</option>
							<option class="dropdown-item" value="텐트" ${vo.product_kind eq "텐트" ? 'selected' : '' }>텐트</option>
							<option class="dropdown-item" value="의자" ${vo.product_kind eq "의자" ? 'selected' : '' }>의자</option>
							<option class="dropdown-item" value="테이블" ${vo.product_kind eq "테이블" ? 'selected' : '' }>테이블</option>
							<option class="dropdown-item" value="매트/침낭" ${vo.product_kind eq "매트/침낭" ? 'selected' : '' }>매트/침낭</option>
							<option class="dropdown-item" value="취사/BBQ" ${vo.product_kind eq "취사/BBQ" ? 'selected' : '' }>취사/BBQ</option>
						</select>
			       </td>
			    </tr>   
			    <tr>
			       <th class="table-sub-color text-white text-center text-align" scope="col">등록일자</th>
			       <td class="text-align text-left">${vo.product_date}</td>
			    </tr>   
			  </tbody>
		</table>
		</form>
      </div>
      <div class="modal-footer">
      	<div class="text-center btn-center">
       	 	<button type="button" class="btn btn-secondary mx-2" data-dismiss="modal" onclick="$('[name=product${vo.product_num}]').submit()">수정</button>
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