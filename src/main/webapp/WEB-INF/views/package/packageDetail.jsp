<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ page import="java.util.*,java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<script src="../../static/js/package/packageDetail.js" type="text/javascript"></script>

<spring:url var="authUrl" value="/package/packageDetail" />
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
			<li class="active"><a class="mn01" href="mywed/myWedding">나의 웨딩</a></li>
			<li><a class="mn02" href="package/packageList">패키지</a></li>
			<li><a class="mn03" href="benefit/winner">혜택</a></li>
			<li><a class="mn04" href="magazine/magList">매거진</a></li>
			<li><a class="mn05" href="benefit/eventList">더보기</a></li>
		</ul>
	</nav>
	

	<!-- container -->
	<div id="ct" class="pt-0">
	
		<!-- 페이지 시작 -->
		
		<div class="detail-slide swiper-container">
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
			<div class="swiper-page"></div>
		</div>
	
		<div class="container">
		
			<div class="detail-header">
				<h1 class="h3">${pkg.nmPkg}</h1>
			</div>
	
			<c:forEach var="list" items="${pkgdetailList}" varStatus="status">
				<dl class="list-dl">
					<dt class="w-20">${list.nmCategory}</dt>
					<dd>${list.nmCompany}</dd>
				</dl>
			</c:forEach>		
			<c:if test="${empty pkgdetailList}">
				<div class="body nodata">
					<p>- 패키지 업체별 내역 -</p>
				</div>
			</c:if>
				
			<hr>
			<div class="detail-title">
				<h2 class="price-title">아펠리안 특별 할인가</h2>
			</div>
			<p class="text-muted">아펠리안 예식장을 이용하는 고객님에게만 특별 할인가로 제공하고 있습니다.</p>
			<div class="price text-primary mt-3">
			<span class="font-num h3"><fmt:formatNumber value="${pkg.amPrice}" pattern="###,###,###"/></span> 		
			<!-- <span class="h6">원 ←→</span>  
			 -->
			</div>
			<hr>
	
			<c:forEach var="list" items="${pkgdetailList}" varStatus="status">
				
				<h2 class="detail-title">${list.nmCategory}</h2>
				
				<ul class="list-survey mb-4">
					<li>
						<h2 class="title">
						<a href="<c:url value='/package/companyDetail?email=${email}&idPkg=${list.idPkg}&cdCompany=${list.cdCompany}' />">
						${list.nmCompany}
						</a>
						</h2>
						<div class="body">
							<div class="txt">
								<dl class="list-dl">
									<dt class="w-150">${list.dsCategoryDetail}</dt>
									<dd></dd>
								</dl>
							</div>
						</div>
					</li>
				</ul>			
			</c:forEach>		
			<c:if test="${empty pkgdetailList}">
				<div class="body nodata">
					<p>- 패키지 업체별 상세 내역 -</p>
				</div>
			</c:if>
						
			<h2 class="detail-title">유의사항</h2>
			<ul class="text-muted small">
				<li>1. 아펠리안은 중개자이며, 판매의 당사자가 아닙니다. <br>상품, 상품 정보, 거래에 관한 의무와 책임은 판매자 에게 있습니다.</li>
				<li>2. 브랜드 상황에 따라 가격이 상이할 수 있습니다.</li>
				<li>3. 문의: 세인트 연락처 02)1234-5678</li>
			</ul>
						
			<div class="fixed-bottom-btn">
			
				<c:set var="tyUser" value="${userInfo.tyUser}" /> 
				<c:choose>
					<c:when test="${tyUser== 'CM'}">
						<a href="/package/packageApply?email=${email}&idPkg=${pkg.idPkg}&gubun=${gubun}" class="btn btn-tit btn-primary btn-block">상담신청</a>
					</c:when>
				    <c:when test="${tyUser== 'NM'}">
				        <a href="#" class="btn btn-tit btn-primary btn-block">웨딩홀 계약 고객만 신청 가능합니다.</a>
				    </c:when>
				    <c:otherwise>
				           <a href="#" class="btn btn-tit btn-primary btn-block">웨딩홀 계약 고객만 신청 가능합니다.</a>
				    </c:otherwise>
				</c:choose>
			</div>
			
			<input type="text" class="inputclass" placeholder="이메일" name="email" value="<c:out value='${email}'/>" hidden="true" >
			<input type="text" class="inputclass" placeholder="idPkg" name="idPkg" value="<c:out value='${pkg.idPkg}'/>" hidden="true" >
			<input type="text" class="inputclass" placeholder="gubun" name="gubun" value="<c:out value='${gubun}'/>" hidden="true" >
	
	</div>		
							

<script>
	var swiper = new Swiper('.detail-slide.swiper-container', {
		pagination: {
			el: '.detail-slide .swiper-page',
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
