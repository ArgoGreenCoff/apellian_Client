<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
<c:if test="${applyInfo.cdWin == 'W'}">
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
<div class="container">
	<h1 class="display-2">당첨자</h1>
	<div class="box">
		<h2 class="h3 font-serif font-weight-bold">축하합니다!<br><span class="text-primary">당첨</span>되었습니다.</h2>
		<p class="small mt-3">신청자 ${applyCnt}팀 중에서 경쟁을 뚫고<br>당첨자로 선정되었습니다.</p>
	</div>
	<hr>
	<h3 class="detail-title2">알림</h3>
	<p class="text-muted">${classInfo.dsNotice}</p>
	<h3 class="detail-title2 mt-3">입금</h3>
	<p class="text-muted">${classInfo.dsPrice}</p>
	<h3 class="detail-title2 mt-3">취소/환불</h3>
	<p class="text-muted">${classInfo.dsRefund}</p>
	<h3 class="detail-title2 mt-3">마케팅팀 연락처</h3>
	<p class="text-muted">${applyInfo.telApply}</p>
</div>
<div class="fixed-bottom-btn">
	<c:if test="${applyInfo.idPayment == 0}">
		<a href="/benefit/payment?idClass=${applyInfo.idBnfClass}&idUser=${applyInfo.idUser}" class="btn btn-lg btn-dark btn-block">결제하기</a>
	</c:if>
	<c:if test="${applyInfo.idPayment > 0}">
		<a href="#" class="btn btn-lg btn-dark btn-block">이미 결제가 완료되었습니다</a>
	</c:if>
	
</div>
<!--// 페이지 끝 -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>
</c:if>
<c:if test="${applyInfo.cdWin == 'F'}">
<script type="text/javascript">
$( document ).ready(function() {
	$('#modal1').modal();
	$('.modal-title').html('아쉽게도<br>당첨되지 않았습니다.');
	$('.modal-body').html('신청자가 많아 모시지 못하였습니다.<br>다음에 또 도전해주세요.');
});
</script>
</c:if>
<c:if test="${applyInfo.cdWin == 'N'}">
<script type="text/javascript">
$( document ).ready(function() {
	$('#modal1').modal();
	$('.modal-title').html('아직 당첨자가 발표되지 않았습니다.');
	//$('.modal-body').html('신청자가 많아 모시지 못하였습니다.<br>다음에 또 도전해주세요.');
});
</script>
</c:if>


<!-- modal -->
<input type="hidden" type="text" name="modal_type" value="0"/>
<div id="modal1" class="modal">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"></h5>
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-dark" data-dismiss="modal">확인</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

$('#modal1').on('hide.bs.modal', function (e) {

	window.history.go(-1);

});
</script>

</body>
</html>