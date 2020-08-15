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
			<a class="nav-link active" href="<c:url value='/benefit/classList?email=${email}' />">클래스</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="<c:url value='/benefit/survey?email=${email}' />">설문조사</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="<c:url value='/benefit/eventList?email=${email}' />">이벤트</a>
		</li>
	</ul>
</nav>
<div class="container">
	<ul class="list-thumb-lg">
	
	
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

				<a href="<c:url value='/benefit/classDetail?idClass=${item.idBnfClass}&email=${email }' />">
					<img src="${listImg[status.count-1][0].urlBnfClassImage}" alt="" onerror="this.src='https://via.placeholder.com/351x180'">
				</a>
			</div>
			<div class="meta">
				<p class="tit">${item.nmBnfClass}</p>
				<p class="text-muted">${item.dsBnfClass}</p>
			</div>
			<div class="info">
				<dl class="list-dl">
					<dt>일시</dt>
					<dd><fmt:formatDate value="${item.dtClass}" pattern="MM월 dd일 (E)" /> ${item.tmClass}</dd>
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