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
<script src="../../static/js/member/withdraw.js" type="text/javascript"></script> 

<spring:url var="authUrl" value="/member/withdraw" />
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
		
			<form class="withdraw-form" id="withdrawForm" name="withdrawForm" method="post" action="${authUrl}">
					
				<h1 class="h4 pt-2">탈퇴하기</h1>
				<p class="text-muted small mt-2">아펠리안 계정을 탈퇴하면 모든 정보가 삭제됩니다.<br>탈퇴 후 동일 이메일 주소로 신규 가입이 30일간 제한됩니다.</p>
				
				<div class="form-group" <c:if test="${result.code eq 'C'}">has-error</c:if>>
					<h2 class="h6 mt-4 mb-1">탈퇴 사유를 알려주세요. <span class="text-primary">(필수)</span></h2>
					<ul>
						<li>
				            <label class="input-chk type2">
				                <input type="checkbox" class="sr-only" name="ynWithdrawReason1" value="Y"> <!-- <c:out value='${ynWithdrawReason1}'/> -->
				                <i></i> 콘텐츠 내용 부족
				            </label>
				        </li>
				        <li>
				            <label class="input-chk type2">
				                <input type="checkbox" class="sr-only" name="ynWithdrawReason2" value="Y"> <!-- <c:out value='${ynWithdrawReason2}'/> -->
				                <i></i> 서비스 이용 불편
				            </label>
				        </li>
				        <li>
				            <label class="input-chk type2">
				                <input type="checkbox" class="sr-only" name="ynWithdrawReason3" value="Y"> <!-- <c:out value='${ynWithdrawReason3}'/> -->
				                <i></i> 웨딩홀 계약 취소
				            </label>
				        </li>
				        <li>
				            <label class="input-chk type2">
				                <input type="checkbox" class="sr-only" name="ynWithdrawReason4" value="Y"> <!-- <c:out value='${ynWithdrawReason4}'/> -->
				                <i></i> 자주 이용하지 않음
				            </label>
				        </li>
				        <li>
				            <label class="input-chk type2">
				                <input type="checkbox" class="sr-only" name="ynWithdrawReason5" value="Y"> <!-- <c:out value='${ynWithdrawReason5}'/> -->
				                <i></i> 기타
				            </label>
				        </li>
				     </ul>
				     <c:if test="${result.code eq 'C'}">
						<span class="error-next-box">${result.description}</span>
					</c:if>
				</div>
			    
			    
			    <div class="form-group" <c:if test="${result.code eq 'D'}">has-error</c:if>>
				    <h2 class="h6 mt-4 mb-1">어떤점이 불편하셨나요? <span class="text-primary">(필수)</span></h2>			     
				    <div class="form-group">
						<p class="form-label-small sr-only">불편사항</p>
						<input type="text" class="form-control" placeholder="사유" name="dswithdraw" value="<c:out value='${dswithdraw}'/>">
					</div>
					<c:if test="${result.code eq 'D'}">
					<span class="error-next-box">${result.description}</span>
					</c:if>					
				</div>			
				 
				 	 
				 <div class="form-group" <c:if test="${result.code eq 'W'}">has-error</c:if>>
					<p class="form-label-small">비밀번호</p>
					<div class="input-right-btn">
						<input name="passwd" type="password" class="form-control" placeholder="비밀번호" value="<c:out value='${passwd}'/>" onKeyup="if($(this).val()){ $('#a').addClass('btn-primary');} else {$('#a').removeClass('btn-primary');}">
						<c:if test="${result.code eq 'W'}">
						<span class="error-next-box">${result.description}</span>
						</c:if>
						<button type="button" class="icon-invisible">보기</button>
					</div>
					
					<input type="text" class="inputclass" placeholder="이메일" name="email" id="email"  value="<c:out value='${email}'/>" hidden="true"> 
					 	
				</div>
				
			</form>
			
		</div>
		<div class="fixed-bottom-btn">			
			<a href="javascript:submitform();" id='a' class="btn btn-lg btn-info btn-block">탈퇴하기</a>
		</div>
		
		
		<!--// 페이지 끝 -->
			
		</div>
		
		<a href="" class="btn-top"><img class="d-block w-100"
			src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
	</div>




</body>
</html>