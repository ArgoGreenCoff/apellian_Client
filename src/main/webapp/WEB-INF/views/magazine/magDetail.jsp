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

<spring:url var="iconBlack" value="../../static/img/icon/btn-bookmarks-on-black.png" />
<spring:url var="iconWhite" value="../../static/img/icon/btn-bookmarks-off-white.png" />

<script type="text/javascript">


function addBookmark() {



	
	if($("#iconBook").attr("src") == '${iconBlack}') {
		alert('이미 북마크 된 글입니다.');
	} else {
		
		var url="/magazine/ajax_add";
	
		var form_data = {
				idMagazine: '${magInfo.idMagazine}',
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
					alert("북마크 추가완료");
					$("#iconBook").attr("src", '${iconBlack}');
				} else {
					alert("북마크 추가실패");
					$("#iconBook").attr("src", '${iconWhite}');
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
	
}
</script>
</head>
<body class="">
<div id="wrap">
	<header id="hd" class="v2">
		<div class="container" style="display:block">
			<div>
				<a href="javascript:addBookmark();" class="" style="float: right; width:44px; height:44px">
				
					<c:if test="${isBookmark}">
						<img src=${ iconBlack} id="iconBook" alt="" style="height: 40px;">
					</c:if>
					<c:if test="${!isBookmark}">
						<img src=${ iconWhite} id="iconBook" alt="" style="height: 40px;">
					</c:if>
					
				</a>
			</div>
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
	<div id="ct" class="pt-0 magazine">
<div class="magazine-header" style="background-image:url(${magInfo.urlImg})">
    <div class="detail-header" style="opacity: 1;">
        <h1 class="h3 mb-2">${magInfo.nmMagazine }</h1>
        <p>${magInfo.dsMagazine}</p>
        <div class="meta-inf flex-justify-space">
            <span>아펠리안 스토리</span>
            <span><fmt:setLocale value="en_us" scope="session"/><fmt:formatDate value="${magInfo.dtReg}" pattern="MMM MM.YYYY" /></span>
        </div>
    </div>
</div>
<!-- 페이지 시작 -->
<div class="container" style="margin-top: 368px;">
	<!-- 매거진 본문 -->
	<div class="magazine-cont">
		${magInfo.dsContent }
	</div>
	<!-- // -->
	
	
    <div class="list-related">
   		<c:if test="${nextMagazine.idMagazine > 0}">
	        <a href="/magazine/magDetail?idMagazine=${nextMagazine.idMagazine }&email=${email }">다음 편 - ${nextMagazine.nmMagazine }</a>
       	</c:if>
        <c:if test="${preMagazine.idMagazine > 0}">
	        <a href="/magazine/magDetail?idMagazine=${preMagazine.idMagazine }&email=${email }">이전 편 - ${preMagazine.nmMagazine }</a>
	    </c:if>
    </div>
</div>
<script>
    var mhH = $('.magazine-header').outerHeight();
    var max = mhH;
	$('.magazine .container').css('margin-top', mhH+'px');

	$(window).scroll(function(){
		var scrollPX = $(this).scrollTop();
		if( scrollPX  < max ) {
			$(".detail-header").css({"opacity": (max-scrollPX)/max });
		}else{
			$(".detail-header").css({"opacity": 0});
		}	
	});
</script>
<!--// 페이지 끝 -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>

</body>
</html>