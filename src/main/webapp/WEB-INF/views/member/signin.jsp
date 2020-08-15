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
<!-- 공통 CSS --> 
<%@ include file="../include/resource-css.jsp" %>
<spring:url var="authUrl" value="/member/signin" />
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
				<h1 class="display-2">로그인</h1>
				<form class="login-form" id="loginForm" name="loginForm" method="post" action="${authUrl}">
					
	                <div class="form-group <c:if test="${fn:contains(result.code, 'E')}">has-error</c:if>">
						<p class="form-label-small">이메일</p>
						<input name="email" type="text" class="form-control" placeholder="이메일" value="<c:out value='${email}'/>">
						<!-- 
						<c:if test="${fn:contains(result.code, 'E')}">						
						<span class="error-next-box" id='errmsg1'>${result.description}</span>
						</c:if>
						 -->
						<span class="error-next-box" id='errmsg1' style="display:''"></span>
						
					</div>
					<div class="form-group">
						<p class="form-label-small">비밀번호</p>
						<div class="input-right-btn">
							<input name="passwd" type="password" class="form-control" placeholder="비밀번호" value="<c:out value='${passwd}'/>" onKeyup="if($(this).val()){ $('#a').addClass('btn-primary');} else {$('#a').removeClass('btn-primary');}">
							<button type="button" class="icon-invisible">보기</button>							
						</div>						
						<span class="error-next-box" id='errmsg2' style="display:''">${result.description}</span>							
					</div>
				</form>
				
			</div>
			<div class="fixed-login-btn">
				<a href="findPassword">비밀번호를 잊으셨나요?</a>
			</div>
			
			<div class="fixed-bottom-btn">
				<!--  <button type="button" id="a" class="btn btn-info btn-block" onclick="loginsubmit();" >로그인</button>      -->   <!--submitform-->
				<a href="javascript:Nextsubmit();" id='a' class="btn btn-lg btn-info btn-block">로그인</a>
			</div>
			<!--// 페이지 끝 -->
		</div>
		<a href="" class="btn-top"><img class="d-block w-100"
			src="/static/img/icon/btn-top.png" alt="상단으로 이동"></a>
	</div>
	
	
	
	<!-- 공통 Javascript --> 
	<%@ include file="../include/resource-js.jsp" %>
	<!-- Page Level 리소스 -->
	<script src="/static/js/member/signin.js" type="text/javascript"></script>

</body>
</html>