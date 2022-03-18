<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		            <a class="nav-link sub-category" href="list.yu">
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
<!-- Section-->
 <section>
	 <c:if test="${ empty page.list }">
		<div class="text-center text-align">
         	<h5>검색하신 정보가 없습니다.</h5>
       	 </div>
	</c:if>
      <div class="container px-4 px-lg-5 mt-5">
          <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-3 ">
              <c:forEach var="vo" items="${page.list}">
              	<div class="col mb-5">
                   <div class="card h-100">
                       <!-- Product image-->
                       <a type="button" onclick='go_detail(${vo.id})'>
                       		<img class="card-img-top" src="${vo.thumbnails }" class="w-100"/>
                       </a>
                       <!-- Product details-->
                       <div class="card-body p-4">
                           <div class="text-center text-black">
                               <!-- Product name-->
                            	<a>${vo.youtubetitle }</a>			        
                             
                           </div>
                       </div>
                       <!-- Product actions-->
                       <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                           <div class="text-center">
                             <a type="button" class='btn btn-outline-secondary' onclick='go_detail(${vo.id})'>자세히보기</a>
                           </div>
                       </div>
                   </div>
              	</div>     
             	</c:forEach>
          </div>
      </div>
  </section>
  
<script type="text/javascript">
	function go_detail(id) {
		location.href="detail.yu?id="+id;
	}
</script>

</body>
</html>













