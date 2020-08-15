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
<body>
<div id="wrap">
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
	<div id="ct" class="pt-0">
<!-- 페이지 시작 -->
<div class="sub-title">
	<h1>혜택</h1>
</div>
<nav class="sub-nav">
	<ul class="nav">
		<li class="nav-item">
			<a class="nav-link" href="<c:url value='/benefit/classList?email=${email}' />">클래스</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="<c:url value='/benefit/survey?email=${email}' />">설문조사</a>
		</li>
		<li class="nav-item">
			<a class="nav-link active" href="<c:url value='/benefit/eventList?email=${email}' />">이벤트</a>
		</li>
	</ul>
</nav>
<div class="container-sm">
	<ul class="list-thumb-lg">
	
		<c:if test="${myd != null}">
		<li>
			<div class="thumbnail">
				<img src="https://via.placeholder.com/351x260" alt="">
			</div>
			<div class="meta meta-type2">
				<p class="tit">100일 기념 선물</p>
				<p class="text-muted">예식날의 설렘을 기억하실 수 있도록 더채플앳청담 시그니처 향의 디퓨저를 선물로 드립니다.</p>
			</div>
			<div class="info">
				<ul class="list-type-dot text-muted">
					<li>예식날의 설렘을 기억하실 수 있도록</li>
					<li>더채플앳청담 시그니처 향의 디퓨저를 선물로 드립니다.</li>
					<c:if test="${address100 != ''}">
					<li>배송지 정보가 변경될 경우 더채플앳청담 예식팀 02)123-4567로 연락주세요.</li>
					</c:if>
				</ul>
			</div>
			
			<c:if test="${address100 != ''}">
				<div class="btn bg-info btn-block text-white mt-3">${address100 } 으로 택배발송</div>
			</c:if>
			<c:if test="${address100 == ''}">
				<a href="/benefit/eventAddress100?email=${email }&type=1" class="btn btn-outline-muted btn-block mt-3">평일 택배 수령지 정보 입력</a>
			</c:if>
		</li>
		</c:if>
		<c:if test="${myd != null}">
		<li>
			<div class="thumbnail">
				<img src="https://via.placeholder.com/351x260" alt="">
			</div>
			<div class="meta meta-type2">
				<p class="tit">1주년 기념 선물</p>
				<p class="text-muted">CJ 외식 10만원 상품권을 선물로 드립니다.</p>
			</div>
			<div class="info">
				<ul class="list-type-dot text-muted">
					<li>예식날의 설렘을 기억하실 수 있도록 CJ 외식 10만원 상품권을 선물로 드립니다.</li>
					<c:if test="${address1 != ''}">
					<li>우체국 등기로 발송되며, 직접 수령 불가시 반송될 수 있습니다.</li>
					<li>등기 접수 후 안내 문자가 발송됩니다.</li>
					<li>상품권 뒷면에서 사용처, 사용방법, 유효기간을 확인해주세요.</li>
					<li>상품권 훼손, 분실, 유효기간 만료시 사용이 불가합니다.</li>
					<li>수령지 정보가 변경될 경우 더채플앳청담 예식팀 02)123-4567로 연락주세요.</li>
					</c:if>
				</ul>
			</div>
			<c:if test="${address1 != ''}">
				<div class="btn bg-info btn-block text-white mt-3">${address1 } 으로 등기발송</div>
			</c:if>
			<c:if test="${address1 == ''}">
				<a href="/benefit/eventAddress1?email=${email }&type=2" class="btn btn-outline-muted btn-block mt-3">평일 등기 수령지 정보 입력</a>
			</c:if>
		</li>
		</c:if>
	
	
	
		<c:forEach var="item" items="${list}" varStatus="status">
	
		<li>
			<div class="thumbnail">
				<div class="badge-wrp">
					<c:if test="${item.ynPreCouple == 'Y'}">
					<span class="badge badge-dark">예비부부</span>
					</c:if>
					<c:if test="${item.ynNewlyCouple == 'Y'}">
					<span class="badge badge-outline-wh">신혼부부</span>
					</c:if>
				</div>
				<a href="<c:url value='/benefit/eventDetail?idBnfEvent=${item.idBnfEvent}&email=${email }' />">
					<img src="${listImg[status.count-1][0].urlBnfEventImage}" alt="" onerror="this.src='https://via.placeholder.com/351x260'">
				</a>
			</div>
			<div class="meta meta-type2">
				<p class="tit">${item.nmBnfEvent }</p>
				<p class="text-muted">${item.dsBnfEvent }</p>
			</div>
			<div class="info">
				<dl class="list-dl">
					<dt>일시</dt>
					<dd><fmt:formatDate value="${item.dtEvent}" pattern="MM월 dd일 (E)" /> ${item.tmEvent}</dd>
				</dl>
				<dl class="list-dl">
					<dt>모집</dt>
					<dd><fmt:formatDate value="${item.dtApplyTo}" pattern="MM월 dd일 (E) HH" />시까지</dd>
				</dl>
			</div>
		</li>
		</c:forEach>
	</ul>
</div>
<!--// 페이지 끝 -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>
</body>
</html>