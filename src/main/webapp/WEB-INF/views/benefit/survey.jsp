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
			<a class="nav-link active" href="<c:url value='/benefit/survey?email=${email}' />">설문조사</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="<c:url value='/benefit/eventList?email=${email}' />">이벤트</a>
		</li>
	</ul>
</nav>
<div class="container-sm">
	<ul class="list-survey">
	
		<c:forEach var="item" items="${list}" varStatus="status">
		<li id="survey" onclick="surveyClick('${item.dsSurveyUrl }')">
			<h2 class="title">${item.nmSurvey}</h2>
			<div class="body" >
				<div class="gift" style='height:60px;width:60px;'><img class="d-block w-100" src="${item.prizeImg}" alt=""></div>
				<div class="txt">
					<strong>${item.nmSurvey}로 바로가기</strong>
					<p class="text-muted"></p>
				</div>
			</div>
		</li>
		</c:forEach>
		
	</ul>
</div>
<!--// 페이지 끝 -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>
<!-- modal -->
<div id="modal1" class="modal" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">설문 완료!</h5>
			</div>
			<div class="modal-body">
				<p>설문에 참여해주셔서 감사합니다.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-dark">확인</button>
			</div>
		</div>
	</div>
</div>
<script>
	//$('#modal1').modal();
	
	function surveyClick(url) {

		window.open(url, '_blank');
		
	}
</script>

</body>
</html>