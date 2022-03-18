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
	
	function fncancel(){
		var msg = "등록한 정보가 모두 사라집니다."
		msg += "\n취소하시겠습니까?"
		if(confirm(msg)){
			location.href="productmanage.shop";
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
		</ul>	
		</form>
	  </div>
	</nav>
<!-- Product insert -->
<form name="dataForm" id="dataForm" onsubmit="return registerAction()" method="post" enctype="multipart/form-data">
	<table class="table w-35 my-sm-4 table-center">
  		<tbody >
	  		<tr>
				<th colspan="2" scope="row" class="py-5 table-sub-color text-white text-size-2 text-center text-align">
					<h2>상품등록</h2>
				</th>
			</tr>
			<tr>
				<th scope="row" class="text-align">상품명</th>
				<td class="text-align py-3">
			    	<div class="row m-sm-2">
			    		<input class="form-control input-size-70" name="product_name" type="text" placeholder="상품명을 입력하세요"/>
			    	</div>
		   		</td>
		    </tr>
			<tr>
				<th scope="row" class="text-align">가격</th>
				<td class="text-align py-3">
				   	<div class="row m-sm-2">
				   		<div class="input-group p-0">
				   			<input class="form-control input-size-70" name="product_price" type="number" placeholder="가격을 입력하세요" aria-label="Amount (to the nearest dollar)"/>
						  <div class="input-group-append">
						    <span class="input-group-text form-control radius-left text-black">원</span>
						  </div>
						</div>
				   	</div>
				</td>
			</tr>
			<tr>
				<th scope="row" class="text-align">수량</th>
				<td class="text-align py-3">
				   	<div class="row m-sm-2">
				   		<div class="input-group p-0">
				   			<input class="form-control input-size-70" name="product_stock" type="number" placeholder="수량을 입력하세요" aria-label="Amount (to the nearest dollar)"/>
						  <div class="input-group-append">
						    <span class="input-group-text form-control radius-left text-black">개</span>
						  </div>
						</div>
				   	</div>
				</td>
			</tr>
			<tr>
	   			<th scope="row" class="text-align">상품종류</th>
			    <td class="text-align py-3">
			    	<div class="row m-sm-2">
			    		<select name='product_kind' class="select-size-40" >
							<option class="dropdown-item" value="감성용품" >감성용품</option>
							<option class="dropdown-item" value="텐트" >텐트</option>
							<option class="dropdown-item" value="의자" >의자</option>
							<option class="dropdown-item" value="테이블" >테이블</option>
							<option class="dropdown-item" value="매트/침낭" >매트/침낭</option>
							<option class="dropdown-item" value="취사/BBQ" >취사/BBQ</option>
			 			 </select>
			    	</div>
			    </td>
		   </tr>
			<tr>
	   			<th scope="row" class="text-align">사진등록</th>
			    <td class="text-align py-3">
			    	<div class="row m-sm-2">
			    		<button id="btn-upload" class="btn btn-outline-success px-0 px-1 mb-1 btn-size-30" type="button" style="border: 1px solid #ddd; outline: none;">파일 추가</button>
			  			<input id="input_file" multiple="multiple" type="file" style="display:none;">
			  			<span style="font-size:0.8rem; color: gray; vertical-align: center">※첨부파일은 최대 10개까지 등록이 가능합니다.</span>
						<div id="articlefileChange"></div>
			    	</div>
			    </td>
		   </tr>
  </tbody>
</table>
</form>
<div class="text-center my-1 mb-5">
	<button type="button" class="btn btn-secondary mx-2" onclick="$('form').submit()">등록하기</button>
 	<button type="button" class="btn btn-outline-success mx-2" onclick="fncancel()">취소하기</button> 		
</div>
<!-- 파일 업로드 스크립트 -->
<script>
$(document).ready(function()
		// input file 파일 첨부시 fileCheck 함수 실행
		{
			$("#input_file").on("change", fileCheck);
		});

/**
 * 첨부파일로직
 */
$(function () {
    $('#btn-upload').click(function (e) {
        e.preventDefault();
        $('#input_file').click();
    });
});

// 파일 현재 필드 숫자 totalCount랑 비교값
var fileCount = 0;
// 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
var totalCount = 10;
// 파일 고유넘버
var fileNum = 0;
// 첨부파일 배열
var content_files = new Array();

function fileCheck(e) {
    var files = e.target.files;
    
    // 파일 배열 담기
    var filesArr = Array.prototype.slice.call(files);
    
    // 파일 개수 확인 및 제한
    if (fileCount + filesArr.length > totalCount) {
      $.alert('파일은 최대 '+totalCount+'개까지 업로드 할 수 있습니다.');
      return;
    } else {
    	 fileCount = fileCount + filesArr.length;
    }
    
    // 각각의 파일 배열담기 및 기타
    filesArr.forEach(function (f) {
      var reader = new FileReader();
      reader.onload = function (e) {
        content_files.push(f);
        $('#articlefileChange').append(
       		'<div id="file' + fileNum + '" onclick="fileDelete(\'file' + fileNum + '\')">'
       		+ '<font style="font-size:1rem">' + f.name + '</font>'  
       		+ '<img src="shop_img/close.png" style="width:3%; margin-left : 0.5rem; height:auto; vertical-align: middle; cursor: pointer;"/>' 
       		+ '<div/>'
		);
        fileNum ++;
      };
      reader.readAsDataURL(f);
    });
    console.log(content_files);
    //초기화 한다.
    $("#input_file").val("");
  }

// 파일 부분 삭제 함수
function fileDelete(fileNum){
    var no = fileNum.replace(/[^0-9]/g, "");
    content_files[no].is_delete = true;
	$('#' + fileNum).remove();
	fileCount --;
    console.log(content_files);
}

/*
 * 폼 submit 로직
 */
function registerAction(){
	
	var form = $("form")[0];
	
	var formData = new FormData(form);
	for (var x = 0; x < content_files.length; x++) {
		// 삭제 안한것만 담아 준다. 
		if(!content_files[x].is_delete){
			 formData.append("article_file", content_files[x]);
		}
	}
		
  /*
   * 파일업로드 multiple ajax처리
  */    
	$.ajax({
   	      type: "POST",
   	   	  enctype: "multipart/form-data",
   	      url: "file-upload.shop",
       	  data : formData,	       		    		     
       	  processData: false,
   	      contentType: false,
   	      success: function (response) {
   	    	if(response){
   	    		produt_insert();
			} else{
				alert("상품등록에 실패하였습니다.");
			}
   	      },
   	      error: function (xhr, status, error) {
 	    	alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
   	    	return false;
   	      }
   	 });
	return false;
}

function produt_insert() {
	var vo = $("#dataForm").serialize();
	
	$.ajax({
		type: "POST",
		data : vo,
		url : "product_insert.shop",
		success : function ( response ) {
			if(response){
 	    		alert("상품등록이 완료되었습니다.");
 	    		location.href="productmanage.shop";
			} else {
				alert("상품등록에 실패하였습니다.");
			}	
		},
	    error: function (xhr, status, error) {
	    	
	    }
	});
}
</script>
</body>
</html>