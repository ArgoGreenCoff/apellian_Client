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

<script type="text/javascript">

 // document.getElementById('spnmail').innerHTML = ${email};
 
// $('#spnmail').text(<c:out value='${email}'/>);

var a = document.getElementById("spnmail");
a.innerHTML = ${email};
	

</script>
<spring:url var="authUrl" value="/member/account" />
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
				<h1 class="display-2">계정 정보</h1>

				<div class="flex-justify-space">				
					<div>
						<p class="form-label-small">이메일 </p>
						<p><b><span id="spnmail">${email}</span></b></p>
						
						<input type="text" class="form-control" disabled="disabled" name="email" value="<c:out value='${email}'/>" hidden="true" >
						 
					</div>
					<a href="/member/withdraw?email=${email}" class="btn-form-button wh">계정 탈퇴</a>
				</div>				
			</div>
			<!--// 페이지 끝 -->
			
		</div>
		<a href="" class="btn-top"><img class="d-block w-100"
			src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
	</div>




</body>
</html>