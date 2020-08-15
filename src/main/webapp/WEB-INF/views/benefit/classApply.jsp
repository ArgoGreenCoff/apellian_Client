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
<script src="../../static/js/benefit/apply.js" type="text/javascript"></script>
<script type="text/javascript">

$( document ).ready(function() {
	var result = ${result};
	if(result == 1) {
		//alert("클래스 신청을 완료 하였습니다.");

		$('#modal1').modal();
		$('.modal-title').html('신청이<br>완료되었습니다.');
		$('.modal-body').html('신청해주셔서 감사합니다.');
		$('input[name=modal_type]').val(result);
	} else if(result == -1) {
		alert("신청에 실패하였습니다. 다시 시도해 주세요.");
	} else if(result == -2) {

		$('#modal1').modal();
		$('.modal-title').html('클래스 신청 제한');
		$('.modal-body').html('<p>당첨자 발표가 되지 않은 클래스는<br>최대 2회까지 신청 가능합니다.</p>');
		$('input[name=modal_type]').val(result);
	}
});
</script>

<spring:url var="authUrl" value="/benefit/classApply" />

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
	<h1 class="display-1">클래스 신청</h1>
	
	<form class="applyform" id="applyForm" name="applyForm" method="post" action="${authUrl}">
		<input id="idClass" name="idClass" type="hidden" value="${idClass }"/>
		<input id="email" name="email" type="hidden" value="${email }"/>
		<div class="form-group">
			<p class="form-label">1. 신청사유를 알려주세요.</p>
			<div class="form-text-size">
				<textarea class="form-control" rows="5" name="reason"></textarea>
				<span class="num">0/300</span>
			</div>
		</div>
		<div class="form-group">
			<p class="form-label">2. 누구와 함께 참여하실 예정이신가요?</p>
			<ul>
				<li>
					<label class="input-chk">
						<input type="radio" class="sr-only" name="radio01" value="spouse" checked>
						<i></i> 배우자
					</label>
				</li>
				<li>
					<label class="input-chk">
						<input type="radio" class="sr-only" name="radio01" value="parents">
						<i></i> 부모님
					</label>
				</li>
				<li>
					<label class="input-chk">
						<input type="radio" class="sr-only" name="radio01" value="freind">
						<i></i> 친구
					</label>
				</li>
				<li>
					<label class="input-chk">
						<input type="radio" class="sr-only" name="radio01" value="colleague">
						<i></i> 회사동료
					</label>
				</li>
				<li>
					<label class="input-chk">
						<input type="radio" class="sr-only" name="radio01" value="etc">
						<i></i> 기타
					</label>
					<input type="text" class="form-control" placeholder="상세히 적어주세요." name="etcDescription">
				</li>
			</ul>
		</div>
		<c:if test="${classInfo.question3rd != '' && classInfo.question3rd != null}">
		<div class="form-group">
			<p class="form-label">3. ${classInfo.question3rd }</p>
			<div class="form-text-size">
				<textarea class="form-control" rows="5" name="brand"></textarea>
				<span class="num">0/300</span>
			</div>
		</div>
		</c:if>
		
		<div class="form-group">
			<p class="form-label">이름</p>
			<input type="text" class="form-control" placeholder="이름" name="name">
		</div>
		<div class="form-group">
			<p class="form-label">연락처</p>
			<input type="text" class="form-control" placeholder="연락처" name="phone">
		</div>
	</form>
		
</div>
<div class="fixed-bottom-btn">
	<button type="button" class="btn btn-lg btn-dark btn-block" data-toggle="modal" onClick="submitform();">클래스 신청</button>
</div>
<!--// 페이지 끝 -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>

<!-- modal -->
<input type="hidden" type="text" name="modal_type" value="0"/>
<div id="modal1" class="modal">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">신청이<br>완료되었습니다.</h5>
			</div>
			<div class="modal-body">
				<p>신청해주셔서 감사합니다.<br>당첨자 발표는 10월 23일입니다.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-dark" data-dismiss="modal">확인</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

$('#modal1').on('hide.bs.modal', function (e) {

	window.history.go(-2);

});

</script>
</body>
</html>