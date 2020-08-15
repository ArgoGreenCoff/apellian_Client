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
		alert("이벤트 신청을 완료 하였습니다.");
		window.history.go(-2);
	} else if(result == -1) {
		alert("신청에 실패하였습니다. 다시 시도해 주세요.");
	}
});



function submitform(){
	
	if(document.applyForm.name.value==""){	
		alert("이름을 입력해주세요.");
	}else if(document.applyForm.phone.value==""){	
		alert("전화번호를 입력해주세요.");
	}else if(document.applyForm.address.value==""){	
		alert("주소를 입력해주세요.");
	}else{//
		$('#applyForm').submit();
	}
}

</script>

<spring:url var="authUrl" value="/benefit/${type }" />

</head>
<body class="">
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
	<h1 class="display-2">주소 입력</h1>
	
	<form class="applyform" id="applyForm" name="applyForm" method="post" action="${authUrl}">
		<input id="email" name="email" type="hidden" value="${email }"/>
		<div class="form-group">
			<p class="form-label-small">이름</p>
			<input type="text" class="form-control" name="name" placeholder="이름">
		</div>
		<div class="form-group">
			<p class="form-label-small">연락처</p>
			<input type="text" class="form-control" name="phone" placeholder="연락처" value="">
		</div>
		<div class="form-group">
			<p class="form-label-small">주소</p>
			<input type="text" class="form-control" name="address" placeholder="주소" value="">
		</div>
	</form>
</div>
<div class="fixed-bottom-btn">
	<button type="button" class="btn btn-lg btn-dark btn-block" onClick="submitform();">신청하기</button>
</div>
<!--// 페이지 끝 -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>

</body>
</html>