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

<spring:url var="authUrl" value="/member/signupOk" />
</head>
<body>

	<!-- container -->
	<div id="ct">
	
	<!-- 페이지 시작 -->
	<div class="container">
	
		<p class="text-muted small mt-2">정상 등록 되었습니다.!!</p>
	
	</div>
	<div class="fixed-bottom-btn">			
		<a href="signin" class="btn btn-lg btn-info btn-block">확인</a>
	</div>
	
	
	<!--// 페이지 끝 -->
		
	</div>
		
	
</body>
</html>