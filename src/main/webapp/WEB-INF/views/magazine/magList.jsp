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
</head>
<body class="">
<div id="wrap">
	<!-- gnb -->
	<nav id="gnb">
		<ul class="gnb">
			<li><a class="mn01" href="나의웨딩.html">나의 웨딩</a></li>
			<li><a class="mn02" href="패키지.html">패키지</a></li>
			<li><a class="mn03" href="혜택_클래스.html">혜택</a></li>
			<li class="active"><a class="mn04" href="">매거진</a></li>
			<li><a class="mn05" href="">더보기</a></li>
		</ul>
	</nav>
	
	<!-- container -->
	<div id="ct" class="pt-0 pb-0 magazine sub-nav-fixed">
<!-- 페이지 시작 -->
<div class="sub-title">
	<div class="flex-justify-space w-100">
        <div><h1>매거진</h1></div>
        <div>
            <a href="/magazine/magSearch?email=${email }" class="icon-search">검색</a>
            <a href="/magazine/savedList?email=${email }" class="icon-bookmark">북마크</a>
        </div>
    </div>
</div>
	
<nav class="sub-nav v2">
	<ul class="nav v2 scroll-hori">
		<li class="nav-item">
			<c:choose>
				<c:when test="${category == 1}">
					<a class="nav-link active" href="/magazine/magList?email=${email}&category=1">아펠리안 스토리</a>
				</c:when>
				<c:otherwise>
					<a class="nav-link" href="/magazine/magList?email=${email}&category=1">아펠리안 스토리</a>
				</c:otherwise>
			</c:choose>
		</li>
		<li class="nav-item">
			<c:choose>
				<c:when test="${category == 2}">
					<a class="nav-link active" href="/magazine/magList?email=${email}&category=2">웨딩팁</a>
				</c:when>
				<c:otherwise>
					<a class="nav-link" href="/magazine/magList?email=${email}&category=2">웨딩팁</a>
				</c:otherwise>
			</c:choose>
		</li>
		<li class="nav-item">
			<c:choose>
				<c:when test="${category == 3}">
					<a class="nav-link active" href="/magazine/magList?email=${email}&category=3">아펠리안 초이스</a>
				</c:when>
				<c:otherwise>
					<a class="nav-link" href="/magazine/magList?email=${email}&category=3">아펠리안 초이스</a>
				</c:otherwise>
			</c:choose>
		</li>
		<li class="nav-item">
			<c:choose>
				<c:when test="${category == 4}">
					<a class="nav-link active" href="/magazine/magList?email=${email}&category=4">리얼 후기</a>
				</c:when>
				<c:otherwise>
					<a class="nav-link" href="/magazine/magList?email=${email}&category=4">리얼 후기</a>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
</nav>
<div class="container-np">
	<div class="magazine-slide swiper-container swiper-container-initialized swiper-container-vertical">
        <div class="swiper-wrapper">

       		<c:forEach var="item" items="${magList}" varStatus="status">
            <div class="swiper-slide first swiper-slide-active" style="height: 1000px;">
                <div class="magazine-cover" style="background-image:url(${item.urlImg})">
                    <div>
                        <h3 class="font-serif" data-swiper-parallax="-300" style="transform: translate3d(0px, 0px, 0px);">${item.nmMagazine}</h3>
                        <p data-swiper-parallax="-200" style="transform: translate3d(0px, 0px, 0px);">${item.dsMagazine}</p>
                    </div>
                    <c:if test="${item.urlBlog != '' }"> 
	                    <a href="javascript:magazineClick('${item.urlBlog }')" class="btn-blank"><span class="sr-only">자세히보기</span></a>
                    </c:if>
                    <c:if test="${item.urlBlog == '' }"> 
	                    <a href="<c:url value='/magazine/magDetail?idMagazine=${item.idMagazine}&email=${email }' />" class="btn-blank"><span class="sr-only">자세히보기</span></a>
                    </c:if>
                </div>
            </div>
            </c:forEach>
            
            
        </div>
        <div class="swiper-page"></div>
    <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
</div>
<!--// 페이지 끝 -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>
<script>
    var swiper = new Swiper('.magazine-slide.swiper-container', {
		direction: 'vertical',
        parallax: true,
        speed: 600,
        pagination: {
            clickable: true
		},
        on: {
            init: function (index) {
            },
            slideChangeTransitionEnd: function () {
                if($('.magazine-slide .first').hasClass('swiper-slide-active')){
                    $('.magazine').removeClass('not-first');
                } else {
                    $('.magazine').addClass('not-first');
                }
            },
        },
	});
    
    var swiper = new Swiper('.sub-nav.swiper-container', {
		slidesPerView: 'auto',
		freeMode: true,
		spaceBetween: 8,
	});    


	function magazineClick(url) {

		window.open(url, '_blank');
		
	}
	
</script>

</body>
</html>