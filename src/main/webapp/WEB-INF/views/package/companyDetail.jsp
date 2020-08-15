<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ page import="java.util.*,java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Apellian</title>
<link rel="stylesheet" href="../../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../../static/css/swiper.min.css">
<link rel="stylesheet" href="../../static/css/common.css">
<script src="../../static/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../../static/js/popper.min.js" type="text/javascript"></script>
<script src="../../static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../static/js/swiper.min.js" type="text/javascript"></script>
<script src="../../static/js/common.js" type="text/javascript"></script>

<spring:url var="authUrl" value="/package/companyDetail" />
</head>
<body>
<div id="wrap">
	<header id="hd">
		<div class="container">
			<a href="" class="icon-back">뒤로가기</a>
		</div>
	</header>
	<!-- gnb -->
	<nav id="gnb">
		<ul class="gnb">
			<li class="active"><a class="mn01" href="mywed/myWedding.jsp">나의 웨딩</a></li>
			<li><a class="mn02" href="package/packageList.jsp">패키지</a></li>
			<li><a class="mn03" href="">혜택</a></li>
			<li><a class="mn04" href="">매거진</a></li>
			<li><a class="mn05" href="">더보기</a></li>
		</ul>
	</nav>
	

	<!-- container -->
	<div id="ct" class="pt-0">
	
		<!-- 페이지 시작 -->	
		<div class="detail-slide swiper-container">
			<div class="swiper-wrapper">
				
				<c:forEach var="list" items="${pkgimagesList}" varStatus="status">
					
					<div class="swiper-slide">
						<img class="d-block w-100" src='${list.urlImg}' alt="">
					</div>						
				</c:forEach>
				<c:if test="${empty pkgimagesList}">
					<div class="body nodata">
						<p>- 패키지 IMAGE -</p>
					</div>
				</c:if>

			</div>
			<div class="swiper-page"></div>
		</div>

		<div class="container">
			<div class="detail-header">
				<h1 class="h5"><span class="badge-square bg-muted text-white">S</span>${cdcompanydetail.nmCompany}</h1>
			</div>
			<dl class="list-dl">
				<dt class="w-20">영업시간</dt>
				<dd>${cdcompanydetail.dsOpHour}</dd>
			</dl>
			<dl class="list-dl">
				<dt class="w-20">주소</dt>
				<dd>${cdcompanydetail.dsAddr}</dd>
			</dl>
			<dl class="list-dl">
				<dt class="w-20">문의</dt>
				<dd>${cdcompanydetail.dsContact}</dd>
			</dl>
			<hr>
			<h2 class="detail-title">업체소개</h2>
			<img class="d-block w-100" src='${cdcompanydetail.urlCompanyImg}' alt="">
			<div class="mt-4">${cdcompanydetail.dsAbout}				
			</div>
			<hr>
			<h2 class="detail-title">인스타그램</h2>
			<!--  
			<ul class="list-insta row">
				<li class="col-4"><img class="d-block w-100" src="https://via.placeholder.com/106x106" alt=""></li>
			</ul>
			 -->
			<button type="button" class="btn btn-outline-muted btn-block mt-3">${cdcompanydetail.idInstagram}</button>
		</div>
		<script>
			var swiper = new Swiper('.detail-slide.swiper-container', {
				pagination: {
					el: '.swiper-page',
				},
			});
		</script>				
			
		<!--// 페이지 끝 -->
		
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>




<!-- modal -->
<div id="modalPhoto" class="modal modal-photo">
	<div class="modal-dialog modal-dialog-centered">
		<button type="button" class="modal-close no-style" data-dismiss="modal">팝업닫기</button>
		<div class="modal-content">
			<div class="modal-body">
				<div class="photo-slide">
					<div class="swiper-container">
						<div class="swiper-wrapper">
						
							
							<c:forEach var="list" items="${pkgimagesList}" varStatus="status">
								
								<div class="swiper-slide">
									<a data-toggle="modal" href="#modalPhoto" data-idx='${list.idPkg}'><img class="d-block w-100" src='${list.urlImg}' alt=""></a>
								</div>
									
							</c:forEach>							
							<c:if test="${empty pkgimagesList}">
								<div class="body nodata">
									<p>- 패키지 IMAGE -</p>
								</div>
							</c:if>
							
						</div>
					</div>
					<div class="swiper-fraction font-num"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var photoSwiper = new Swiper('.photo-slide .swiper-container', {
		pagination: {
			el: '.photo-slide .swiper-fraction',
			type: 'fraction',
		},
	});
	
	var photoIdx = 0;
	$('a[href="#modalPhoto"]').click(function(){
		photoIdx = $(this).attr('data-idx');
	});
	$('#modalPhoto').on('show.bs.modal', function (e) {
		setTimeout(function(){
			$(window).resize();
			photoSwiper.update();
			photoSwiper.slideTo(photoIdx);
		},200);
	});
</script>


</body>
</html>
