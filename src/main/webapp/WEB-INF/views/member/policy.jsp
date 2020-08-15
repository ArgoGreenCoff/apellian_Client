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
<script src="../../static/js/member/policy.js" type="text/javascript"></script>

<spring:url var="authUrl" value="/member/policy" />
</head>
<body>
	<div id="wrap">
		<header id="hd">
			<div class="container">
				<a href="" class="icon-back">뒤로가기</a>
			</div>
		</header>

		<!-- container -->
		<div id="ct" class="pt-0">
			<!-- 페이지 시작 -->
			<div class="container-pn">
				<h1 class="display-2 bd-tit">약관 동의</h1>
				
				<form class="policy-form" id="policyForm" name="policyForm" method="post" action="${authUrl}">
					
                    <ul class="list-basic v2">
				        <li><a href="agreement01">이용약관</a></li>
				        <li><a href="agreement04">개인정보 처리방침</a></li>
				    </ul>
				</form>
			</div>
			
			
			<!--// 페이지 끝 -->
		</div>
		<a href="" class="btn-top"><img class="d-block w-100"
			src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
	</div>
</body>
</html>