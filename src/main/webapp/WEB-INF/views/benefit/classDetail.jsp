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
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=3ddnz0afa0"></script>

<script type="text/javascript">

function copyAddr() {

	var isiOSDevice = navigator.userAgent.match(/ipad|iphone/i);

	var addr = "${classInfo.dsAddr}";
	var tempElem = document.createElement('textarea');
	tempElem.value = addr;  
	document.body.appendChild(tempElem);

	if(isiOSDevice)
	{
		var editable = tempElem.contentEditable;
		var readOnly = tempElem.readOnly;

		tempElem.contentEditable = true;
		tempElem.readOnly = false;

		var range = document.createRange();
		range.selectNodeContents(tempElem);

		var selection = window.getSelection();
		selection.removeAllRanges();
		selection.addRange(range);

		tempElem.setSelectionRange(0, 999999);
		tempElem.contentEditable = editable;
		tempElem.readOnly = readOnly;
		document.execCommand("copy");
		document.body.removeChild(tempElem);

	}
	else 
	{
		tempElem.select();
		document.execCommand("copy");
		document.body.removeChild(tempElem);
	}
		

	alert('복사 완료 - '+ addr);
}

function goPage(url) {
	// name이 paging인 태그
	var f = document.paging;
	
	// form 태그의 하위 태그 값 매개 변수로 대입
	f.idClass.value = '${classInfo.idBnfClass}';
	f.email.value = '${email}';
	
	// input태그의 값들을 전송하는 주소
	f.action = "/benefit/"+url
	
	// 전송 방식 : post
	f.method = "get"
	f.submit();
}
</script>
</head>
<body>
    <!-- 페이지 전송 폼 -->
    <form name="paging">
    	<input type="hidden" name="idClass"/>
    	<input type="hidden" name="email"/>
    </form>
    <!-- a태그로 인수 전달 -->

<div id="wrap">
	<header id="hd">
		<div class="container">
			<a href="" class="icon-back">뒤로가기</a>
		</div>
	</header>
	<!-- gnb -->
	<nav id="gnb">
		<ul class="gnb">
			<li><a class="mn01" href="나의웨딩.html">나의 웨딩</a></li>
			<li><a class="mn02" href="패키지.html">패키지</a></li>
			<li class="active"><a class="mn03" href="혜택_클래스.html">혜택</a></li>
			<li><a class="mn04" href="">매거진</a></li>
			<li><a class="mn05" href="">더보기</a></li>
		</ul>
	</nav>
	
	<!-- container -->
	<div id="ct">
<!-- 페이지 시작 -->
<div class="detail-slide swiper-container">
	<div class="swiper-wrapper">
	
		<c:forEach var="item" items="${listImg}" varStatus="status">
			<div class="swiper-slide">
				<img class="d-block w-100" src="${item.urlBnfClassImage}" alt="" onerror="this.src='https://via.placeholder.com/375x375'">
			</div>
		</c:forEach>
	</div>
	<div class="swiper-page"></div>
</div>
<div class="container">
	<div class="detail-header">
		<h1 class="h3 mb-2">${classInfo.nmBnfClass}</h1>
		<p class="text-muted">${classInfo.dsBnfClass}</p>
	</div>
	<dl class="list-dl">
		<dt id='test'>일시</dt>
		<dd><fmt:formatDate value="${classInfo.dtClass}" pattern="MM월 dd일 (E)" /> ${classInfo.tmClass}</dd>
	</dl>
	<dl class="list-dl">
		<dt>장소</dt>
		<dd>${classInfo.nmPlace}</dd>
	</dl>
	<dl class="list-dl">
		<dt>대상</dt>
		<dd>
		<c:if test="${classInfo.ynPreCouple == 'Y'}">
		결혼 예정자 (동반 1인) 
		</c:if>
		<c:if test="${classInfo.ynNewlyCouple == 'Y'}">
		신혼 부부 (동반 1인)
		</c:if>
		</dd>
	</dl>
	<dl class="list-dl">
		<dt>신청</dt>
		<dd><fmt:formatDate value="${classInfo.dtApplyTo}" pattern="MM월 dd일 (E) HH" />시까지 신청</dd>
	</dl>
	<dl class="list-dl">
		<dt>인원</dt>
		<dd>${classInfo.dsPersons}</dd>
	</dl>
	<hr>
	<h2 class="detail-title">소개</h2>
	<p class="text-muted">${classInfo.dsAbout}</p>
	<hr>
	<h2 class="detail-title">이런 분들을 위한 클래스</h2>
	<ul class="list-type-dot text-muted">
		<li>${classInfo.dsFor}</li>
	</ul>
	<hr>
	<h2 class="detail-title">커리큘럼</h2>
	<dl class="list-dl">
		${classInfo.dsCurriculum}
	</dl>
	<hr>
	<h2 class="detail-title">깜짝 혜택</h2>
	<p class="text-muted">${classInfo.dsBenefit}</p>
	<div class="benefit-slide swiper-container mt-2">
		<div class="swiper-wrapper">
		<c:forEach var="item" items="${listBnfImg}" varStatus="status">
			<div class="swiper-slide">
				<img class="d-block w-100" src="${item.urlBnfClassBnfImage}" alt="" onerror="this.src='https://via.placeholder.com/120x120'">
			</div>
		</c:forEach>
		</div>
	</div>
	<hr>
	<h2 class="detail-title">참가 신청</h2>
	<dl class="list-dl">
		<dd>${classInfo.dsApply}</dd>
	</dl>
	<hr>
	<h2 class="detail-title">유의사항</h2>
	<ul class="list-type-dot text-muted">
		<li>${classInfo.dsNotice}</li>
	</ul>
	<hr>
	<h2 class="detail-title">장소</h2>
	<p class="detail-title">${classInfo.nmPlace}</p>
	<p class="text-muted">${classInfo.dsAddr}</p>
	<button type="button" class="btn btn-outline-muted btn-block mt-3 mb-3" onclick="copyAddr()">주소 복사</button>
	<p class="text-muted">${classInfo.dsParking}</p>
	
	<div class="d-block w-100 mt-2" id="map" style="width:100%;height:400px;"></div>
	<c:if test="${x  != '0'}">
	<script>
		var mapOptions = {
		    center: new naver.maps.LatLng(${y}, ${x}),
		    zoom: 16
		};
		
		var map = new naver.maps.Map('map', mapOptions);

		var marker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(${y}, ${x}),
		    map: map
		});
	</script>
	</c:if>
	
</div>
<div class="fixed-bottom-btn">


	<c:if test="${applyState == 0}">
	<a href="javascript:goPage('classApply');" class="btn btn-tit btn-primary btn-block">
		신청하기
		<div class="txt">현재까지 ${classApplyCnt}명이 신청했습니다.</div>
	</a>
	</c:if>
	<c:if test="${applyState == 4}">
	<a class="btn btn-tit btn-primary btn-block">
		모집종료
	</a>
	</c:if>
	<c:if test="${applyState == 5}">
	<a href="javascript:goPage('winner');" class="btn btn-tit btn-primary btn-block">
		당첨자 발표 확인하기
	</a>
	</c:if>


</div>
<script>
	var swiper = new Swiper('.detail-slide.swiper-container', {
		pagination: {
			el: '.swiper-page',
		},
	});
	
	var swiper = new Swiper('.benefit-slide.swiper-container', {
		slidesPerView: 'auto',
		freeMode: true,
		spaceBetween: 8,
	});
</script>
<!--// 페이지 끝 -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>
</body>
</html>