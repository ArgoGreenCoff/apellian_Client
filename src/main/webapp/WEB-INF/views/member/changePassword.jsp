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
<script src="../../static/js/member/changePassword.js" type="text/javascript"></script>

<spring:url var="authUrl" value="/member/changePassword" />
</head>
<body>
	<div id="wrap">
		<header id="hd">
			<div class="container">
				<a href="" class="icon-back">뒤로가기</a>
			</div>
		</header>

		<!-- container -->
		<div id="ct">
			<!-- 페이지 시작 -->
			<div class="container">
			
				<div class="display-2">
					<h1 class="h4">비밀번호 변경</h1>					
				</div>
				
				<div class="form-group mt-4">
					
					<form class="changepassword-form" id="changepasswordForm" name="changepasswordForm" method="post" action="${authUrl}">
					
						<div class="form-group <c:if test="${fn:contains(result.code, 'W1')}">has-error</c:if>">
							<input type="text" class="form-control" placeholder="이메일" name="email"  value="<c:out value='${email}'/>" hidden="true" > <!-- hidden="true" -->
							<p class="form-label-small">현재 비밀번호</p>
							<div class="input-right-btn">
								<input type="password" class="form-control" placeholder="현비밀먼호" name="oldpasswd"  value="<c:out value='${oldpasswd}'/>"   >
								<button type="button" class="icon-invisible">보기</button>
							</div>
						</div>
						<c:if test="${fn:contains(result.code, 'W1')}">
						<span class="error-next-box">${result.description}</span>
						</c:if>
						
						<div class="form-group <c:if test="${fn:contains(result.code, 'W2')}">has-error</c:if>">
						<p class="form-label-small">새 비밀번호</p>
							<div class="input-right-btn">
								<input type="password" class="form-control" placeholder="새비밀먼호" name="newpasswd"  value="<c:out value='${newpasswd}'/>"   onKeyup="if($(this).val()){ $('#a').addClass('btn-primary');} else {$('#a').removeClass('btn-primary');}">
								<button type="button" class="icon-invisible">보기</button>
							</div>
						</div>
						<c:if test="${fn:contains(result.code, 'W2')}">
						<span class="error-next-box">${result.description}</span>
						</c:if>
						
					</form>
						
				</div>				
			</div>

			<div class="fixed-bottom-btn">
				<a href="javascript:submitform();" id='a' class="btn btn-lg btn-info btn-block">변경하기</a>
			</div>
			<!--// 페이지 끝 -->
		</div>
		<a href="" class="btn-top"><img class="d-block w-100"
			src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
	</div>
</body>
</html>