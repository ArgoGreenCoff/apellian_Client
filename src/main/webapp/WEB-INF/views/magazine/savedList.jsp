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
<script src="../../static/js/swiper.min.js" type="text/javascript"></script>
<script src="../../static/js/common.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js" type="text/javascript"></script> 
<script src="../../static/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
function bookmarkClick(selIndex) {

	var f = document.paging;
	f.selIdMagazine.value = selIndex;


	$('#modal1').modal("show");

}

function deleteBookmark(){
	var f = document.paging;
	var selIndex = f.selIdMagazine.value;

	var idMagazine = 0;
	var cnt = 0;
	<c:forEach var="item" items="${savedList}">
    if(cnt == selIndex)       
    	idMagazine="${item.idMagazine}";
	cnt++;
	</c:forEach>


	
	var url="/magazine/ajax_delete";

	var form_data = {
			idMagazine: idMagazine,
			email: '${email}',
			is_ajax: 1
		};
	
	var ajax = $.ajax({ 
		url : url
		, type: "GET"
		, daataType : "jason"
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8' 
		, data : form_data
		, beforeSend:function(response){
			
		}	
		, success : function(responseData){
			
			var ret = responseData;
			if(ret == 1) {
				alert('삭제 하였습니다.');
				window.location.reload()
			} else {
				alert('삭제에 실패하였습니다. 다시 시도해주세요.');
			}
		}
			
					
		,complete: function(response){
			
			// var ul = document.getElementById("ul_dynaList");
			// ul.innerHTML = "";	
		}
		, error:function(e) {
			// var ul = document.getElementById("ul_dynaList");
			// ul.innerHTML = "";			
		}
		, fail: function(){
			
		}
		});

		// ajax 실행문
		jQuery.ajax.done; // (function(data){})
}

</script>
</head>

<body class="">

    <form name="paging">
    	<input type="hidden" name="selIdMagazine" id="selIdMagazine"/>
    </form>


<div id="wrap">
	<header id="hd">
		<div class="container">
			<a href="" class="icon-back">뒤로가기</a>
		</div>
	</header>
	<!-- gnb 
	<nav id="gnb">
		<ul class="gnb">
			<li><a class="mn01" href="나의웨딩.html">나의 웨딩</a></li>
			<li><a class="mn02" href="패키지.html">패키지</a></li>
			<li class="active"><a class="mn03" href="혜택_클래스.html">혜택</a></li>
			<li><a class="mn04" href="">매거진</a></li>
			<li><a class="mn05" href="">더보기</a></li>
		</ul>
	</nav>
	-->
	
	<!-- container -->
	<div id="ct">
<!-- 페이지 시작 -->
<div class="container">
	<h1 class="display-2">저장 목록</h1>
	<ul class="list-mymagazine">
	
	
		<c:forEach var="item" items="${savedList}" varStatus="status">
	    <li>
	        <div class="flex-justify-star">
	            <div class="thumb">
                    <img class="d-block w-100" src="https://via.placeholder.com/75x75" alt="">
                </div>
	            <div class="tit w-100">
	                <h3>${item.nmMagazine }</h3>
	                <p>${item.dsMagazine }</p>
	            </div>
	            <div class="save">
	                <a href="javascript:bookmarkClick(${status.index});" class="icon-bookmark blk check"></a>
	            </div>
	        </div>
	        <a href="<c:url value='/magazine/magDetail?idMagazine=${item.idMagazine}&email=${email }' />" class="btn-blank" style="right:30px"><span class="sr-only">더보기</span></a>
	    </li>
		</c:forEach>
	
	</ul>
</div>


<div id="modal1" class="modal show" aria-modal="true" style="display: none;">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">매거진 삭제</h5>
			</div>
			<div class="modal-body">
				<p>매거진을 저장 목록에서 삭제하시겠어요?</p>
			</div>
			<div class="modal-footer group">
		    	<button type="button" class="btn btn-outline-muted" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-dark" onClick="deleteBookmark();">삭제</button>
			</div>
		</div>
	</div>
</div>


<!--// 페이지 끝 -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>

</body>
</html>