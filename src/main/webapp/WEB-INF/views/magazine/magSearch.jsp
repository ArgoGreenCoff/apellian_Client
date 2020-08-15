<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ page import="java.util.*,java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Apellian</title>
<link rel="stylesheet" href="../../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../../static/css/swiper.min.css">
<link rel="stylesheet" href="../../static/css/common.css">
<script src="../../static/js/jquery-3.4.1.slim.min.js" type="text/javascript"></script>
<script src="../../static/js/popper.min.js" type="text/javascript"></script>
<script src="../../static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../static/js/swiper.min.js" type="text/javascript"></script>
<script src="../../static/js/common.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js" type="text/javascript"></script> 

<script>
function deleteClick() {
	document.getElementById('magazineSearch').value = '';
}
function changeSearch() {
	setTimeout(function(){
		var strSearch = document.getElementById('magazineSearch').value;
		var searchBody = document.getElementById("searchBody");
		var url="/magazine/ajax_search";

		var form_data = {
				search: strSearch,
				is_ajax: 1
			};
		
		var ajax = $.ajax({ 
			url : url
			, type: "GET"
			, daataType : "jason"
			, contentType: 'application/x-www-form-urlencoded; charset=UTF-8' // json; charset=UTF-8'    // , contentType: 'application/x-www-form-urlencoded; charset=euc-kr'
			, data : form_data
			, beforeSend:function(response){
				
			}	
			, success : function(responseData){
				
				var data = JSON.parse(responseData);
				if(!data || data.length == 0) {
					
					searchBody.innerHTML = '<div class="search-nodata">'+
					'<img class="center-block" src="img/icon/ico-nosearch.png" height="48" alt="">'+
					'<p class="txt">검색 결과가 없습니다.</p><strong class="text-primary h6">직접 입력 ‘'+strSearch+'’</strong></div>';
					
				} else {
					
					var html = '<ul class="list-border mt-2">';
					
		            searchBody.innerHTML = "";
					$.each(data, function(index, item){
						
						strul =  "";
						strul =  ""+item.nmMagazine +"\n";

						 
						html += '<li>';

						if(item.urlImg != "") {
							html += '<div class="thumb">';
							html += '<img class="d-block w-100" src="'+item.urlImg+'" alt="">';
							html += '</div>';
						}
						
						html += '<a class="h6" href="/magazine/magDetail?idMagazine='+item.idMagazine+'&email=${email }">'+item.nmMagazine+'</a><br>';
						html += '<a class="text-muted mt-2" href="/magazine/magDetail?idMagazine='+item.idMagazine+'&email=${email }">'+item.dsMagazine+'</a>';
						html += '</li>';

					});
					html += '</ul>';
					
					
					searchBody.innerHTML = html;
						
				}
			}
				
						
			,complete: function(response){
				
				// var ul = document.getElementById("ul_dynaList");
				// ul.innerHTML = "";	
			}
			, error:function(e) {
				// var ul = document.getElementById("ul_dynaList");
				// ul.innerHTML = "";			
				
				searchBody.innerHTML = '<div class="search-nodata">'+
				'<img class="center-block" src="img/icon/ico-nosearch.png" height="48" alt="">'+
				'<p class="txt">검색 결과가 없습니다.</p><strong class="text-primary h6">직접 입력 ‘'+strSearch+'’</strong></div>';
				
			}
			, fail: function(){
				searchBody.innerHTML = '<div class="search-nodata">'+
				'<img class="center-block" src="img/icon/ico-nosearch.png" height="48" alt="">'+
				'<p class="txt">검색 결과가 없습니다.</p><strong class="text-primary h6">직접 입력 ‘'+strSearch+'’</strong></div>';
			}
		});

			// ajax 실행문
			jQuery.ajax.done; // (function(data){})

		
   	}, 1);
}
</script>

</head>
<body class="">
<div id="wrap">
	<header id="hd" class="search-area">
		<div class="container">
			<!-- <a href="매거진_목록.html" class="icon-back">뒤로가기</a> -->
			<div class="form-search">
				<input type="text" id="magazineSearch" placeholder="매거진 검색" onkeyup="changeSearch();">
				<button type="button" class="icon-x" onclick="deleteClick();">입력 삭제</button>
			</div>
		</div>
	</header>
	
	<!-- container -->
	<div id="ct">
<!-- 페이지 시작 -->
<div class="container" id="searchBody">
	

</div>
<!--// 페이지 끝 -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>

</body>
</html>
