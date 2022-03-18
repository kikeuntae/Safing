<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<link rel='stylesheet' type="text/css" href="css/shop_style.css?v=<%= new Date().getTime() %>" >
</head>
<body>
<header class="container-fluid px-0">
	<div id="packageList" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<c:forEach var="i" begin="0" end="${fn:length(package_list)-1}" step="1">
				<c:if test="${i eq 0 }">
					<li data-target="#packageList" data-slide-to="${i}" class="active "></li>
				</c:if>
				<c:if test="${i gt 0}"> 
			    	<li data-target="#packageList" data-slide-to="${i}"></li>
				</c:if>
			</c:forEach>  
		</ol>
	  	<div class="carousel-inner">
	  		<c:forEach var="i" begin="0" end="${fn:length(package_list)-1}" step="1">
				<c:if test="${i eq 0 }">
				    <div class="carousel-item active">
				      	<img src="shop_img/${i+1}.png" class="w-100 d-block"  >
						<div class="carousel-caption d-none d-md-block">
					        <h3 class="text-outline">${package_list[i].package_name}</h3>
							<h5 class="text-outline">${fn:replace(package_list[i].tag_key, '#', '&nbsp;&nbsp;#')}</h5>
							 <div class="text-center ">
	                            <a class="btn text-white text-outline-btn" id="detail_package" data-toggle="modal" data-target="#package${package_list[i].package_num}">
									패키지 상세보기
								</a>  
                        	</div>
				      	</div>
					</div>
		    	</c:if> 
		  	    <c:if test="${i gt 0}"> 
				    <div class="carousel-item">
				      <img src="shop_img/${i+1}.png" class="w-100 d-block"  >
					      <div class="carousel-caption d-none d-md-block">
					       	 <h3 class="text-outline">${package_list[i].package_name}</h3>
							 <h5 class="text-outline">${fn:replace(package_list[i].tag_key, '#', '&nbsp;&nbsp;#')}</h5>
							 <div class="text-center ">
	                            <a class="btn text-white text-outline-btn" id="detail_package" data-toggle="modal" data-target="#package${package_list[i].package_num}">
									패키지 상세보기
								</a> 
                        	</div>
						</div>
				    </div>
		    	</c:if> 
			</c:forEach> 
		</div>
		<button class="carousel-control-prev" type="button" data-target="#packageList" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		</button>
		<button class="carousel-control-next" type="button" data-target="#packageList" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		</button>
	</div>
</header>  
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="collapse navbar-collapse text-center container" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <c:if test="${loginInfo.member_admin eq 'y'}">
	      <li class="nav-item">
	        <label>
	            <a class="nav-link sub-category" href="list.shop">
	            	<img class="img-size-30"  src="shop_img/camping.png"/>
	            	&nbsp;캠핑용품
				</a>
			</label>
	      </li>
	      <li class="nav-item">
	        <label>
	            <a class="nav-link" href="ordermanage.shop">
	            	<img class="img-size-30"  src="shop_img/order.png"/>
	            	&nbsp;주문관리
				</a>
			</label>
	      </li>
	      <li class="nav-item">
	         <label>
		            <a class="nav-link" href="productmanage.shop">
		            	<img class="img-size-30"  src="shop_img/product.png"/>
		            	&nbsp;상품관리
					</a>
			  </label>
	      </li>
      </c:if>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
           <img class="img-size-30"  src="shop_img/category.png"/>
           &nbsp;카테고리
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        	<a class="dropdown-item" href="list.shop?search=all">전체선택</a>
        	<div class="dropdown-divider"></div>
	        <a class="dropdown-item" href="list.shop?search=감성용품">감성용품</a>
			<a class="dropdown-item" href="list.shop?search=텐트">텐트</a>
			<a class="dropdown-item" href="list.shop?search=의자">의자</a>
			<a class="dropdown-item" href="list.shop?search=테이블">테이블</a>
			<a class="dropdown-item" href="list.shop?search=매트/침낭">매트/침낭</a>
			<a class="dropdown-item" href="list.shop?search=취사/BBQ">취사/BBQ</a>   
        </div>
      </li>
    </ul>
	<form class="form-inline my-2 my-lg-0" action="list.shop" method="post">
		<input type="hidden" name="curPage" value="1" />
		<input type="hidden" name="search" value="${page.search}" />
	    <ul class="navbar-nav form-inline my-2 ">
	    	<li class="nav-item m-2">
				<input class="form-control mr-sm-2" name='keyword' value="${page.keyword}" type="search" placeholder="상품검색하기" aria-label="Search">
			</li>
	    	<li class="nav-item m-2">
				<button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="$('form').submit()">검색</button>
			</li>
		</ul>	
	</form>
  </div>
</nav>
 <!-- Section-->
 <section>
	 <c:if test="${ empty page.list }">
		<div class="text-center text-align">
         	<h5>검색하신 정보가 없습니다.</h5>
       	 </div>
	</c:if>
      <div class="container px-4 px-lg-5 mt-5">
          <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 ">
              <c:forEach var="vo" items="${page.list}">
              	<div class="col mb-5">
                   <div class="card h-100">
                       <!-- Product image-->
                       <img class="card-img-top" src="${vo.file_path}" class="w-100"/>
                       <!-- Product details-->
                       <div class="card-body p-4">
                           <div class="text-center text-black">
                               <!-- Product name-->
                              <c:if test="${fn:length(vo.product_name) lt 20}">
					        	 <h5 class="fw-bolder">${vo.product_name}</h5><br>
					        	</c:if>
				      		<c:if test="${fn:length(vo.product_name) gt 19}">
					        	 <h5 class="fw-bolder">${vo.product_name}</h5>
					       	 </c:if>					        
                               <!-- Product reviews-->
                               <div class="d-flex justify-content-center small text-warning mb-2">
                               	<c:forEach var="i" step="1" begin="1" end="5">
                               		<c:if test="${vo.rating gt i}">
                                     <div class="bi-star-fill"></div>
                               		</c:if>
                               		<c:if test="${vo.rating lt i}">
                                     <div class="bi bi-star"></div>
                                    </c:if>
                                   </c:forEach>
                               </div>
                               <!-- Product price-->
                              <fmt:formatNumber value="${vo.product_price}" pattern="#,###"/>&nbsp;원
                           </div>
                       </div>
                       <!-- Product actions-->
                       <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                           <div class="text-center">
                             <button type="button" class="btn btn-outline-dark mt-auto" data-toggle="modal"  data-target="#product${vo.product_num}">
	  							상세보기
							</button> 
                           </div>
                       </div>
                   </div>
              	</div>     
             	</c:forEach>
          </div>
      </div>
  </section>
<!-- Pager -->
<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>

<!-- Package Modal -->
 <c:forEach var="vo" items="${package_detail_list}">
<div class="modal fade" id="package${vo.package_num}" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content height-125">
    <div class="text-black text-center">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">패키지 상세정보</h5>
        <button type="button" class="close border-none" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true" class="fs-7">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div id="packageImage${vo.package_num}" class="carousel slide" data-ride="carousel">
		  <div class="carousel-inner">
			<c:forEach var="img" items="${vo.imagelist}" varStatus="i">
		  		<c:if test="${i.first}">
				    <div class="carousel-item active">
				      <img class="img-fluid d-block mx-auto w-100" src="${img}" class="w-100"/>
				    </div>
		    	</c:if>
		    	<c:if test="${!i.first}">
				    <div class="carousel-item">
				      <img class="img-fluid d-block mx-auto w-100" src="${img}" class="w-100"/>
				    </div>
		    	</c:if>
		    </c:forEach> 
		  </div>
		   <button class="carousel-control-prev" type="button" data-target="#packageImage${vo.package_num}" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-target="#packageImage${vo.package_num}" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </button>
		</div>
		<h2 class="text-uppercase text-space text-space-top">${vo.package_name}</h2>
         <p>${fn:replace(package_list[i].tag_key, '#', '&nbsp;&nbsp;#')}</p>
            <div class="d-flex justify-content-center small text-warning mb-2 text-space">
	           	<c:forEach var="i" step="1" begin="1" end="5">
	           		<c:if test="${vo.rating gt i}">
	                 <div class="bi-star-fill"></div>
	           		</c:if>
	           		<c:if test="${vo.rating lt i}">
	                 <div class="bi bi-star"></div>
	                </c:if>
	               </c:forEach>
	           </div>
        <ul class="list-inline">  
            <li >
                <strong>패키지 가격</strong>
            </li>
            <li class="text-space">
                <fmt:formatNumber value="${vo.package_price}"  pattern="#,###"/>&nbsp;원  
            </li>
            <li >
                <strong>상품 구성</strong>
            </li>
            <li class="text-space" >
                <p><c:forEach var="i" items="${vo.kindlist}">${i}&nbsp;&nbsp;</c:forEach></p>
            </li>
        </ul>
        <img class="img-fluid d-block mx-auto w-100" src="${vo.file_path_info}" class="w-100"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
    </div>
  </div>
</div>
 </c:forEach>
<!-- Product Modal -->
 <c:forEach var="vo" items="${product_detail_list}">
<div class="modal fade" id="product${vo.product_num}" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content height-125">
     <div class="text-black text-center">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">상품 상세정보</h5>
        <button type="button" class="close border-none" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true" class="fs-7">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <div id="productImage${vo.product_num}" class="carousel slide" data-ride="carousel">
		  <div class="carousel-inner">
			<c:forEach var="img" items="${vo.imagelist}" varStatus="i">
		  		<c:if test="${i.first}">
				    <div class="carousel-item active">
				      <img class="img-fluid d-block mx-auto w-100" src="${img}" class="w-100"/>
				    </div>
		    	</c:if>
		    	<c:if test="${!i.first}">
				    <div class="carousel-item">
				      <img class="img-fluid d-block mx-auto w-100" src="${img}" class="w-100"/>
				    </div>
		    	</c:if>
		    </c:forEach> 
		  </div>
		  <button class="carousel-control-prev" type="button" data-target="#productImage${vo.product_num}" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-target="#productImage${vo.product_num}" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </button>
		</div>
			<h2 class="text-uppercase text-space text-space-top">${vo.product_name}</h2>
	       	   <div class="d-flex justify-content-center small text-warning mb-2 text-space" >
                   	<c:forEach var="i" step="1" begin="1" end="5">
                   		<c:if test="${vo.rating gt i}">
                         <div class="bi-star-fill"></div>
                   		</c:if>
                   		<c:if test="${vo.rating lt i}">
                         <div class="bi bi-star"></div>
                        </c:if>
                       </c:forEach>
                   </div>
             <ul class="list-inline">  
               	<li>
                 	 <strong>상품 가격</strong>
                 </li>
                 <li class="list-inline ">
                     <fmt:formatNumber value="${vo.product_price}" pattern="#,###"/>&nbsp;원 
                 </li>                
             </ul>
             <img class="img-fluid d-block mx-auto w-100" src="${vo.file_path_info}" class="w-100"/>
      	</div>
      <div class="modal-footer">
      	<div class="text-center">
       	 <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
      </div>
    </div>
    </div>
  </div>
</div>
</c:forEach>

</body>
</html>
