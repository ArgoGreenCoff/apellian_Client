<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ page import="java.util.*,java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
</script>
  
</head>
<body>

<div id="wrap">

	<!-- gnb -->
	<nav id="gnb">
		<ul class="gnb">
			<li class="active"><a class="mn01" href="mywed/myWedding">나의 웨딩</a></li>
			<li><a class="mn02" href="package/packageList">패키지</a></li>
			<li><a class="mn03" href="benefit/winner">혜택</a></li>
			<li><a class="mn04" href="magazine/magList">매거진</a></li>
			<li><a class="mn05" href="benefit/eventList">더보기</a></li>
		</ul>
	</nav>
	

	<!-- container -->
	<div id="ct" class="mywedding" >
		<!-- 페이지 시작 -->
		
		<div class="container">
			<div class="dday-wrp">
				
			</div>
			<div class="my-info">
				
								
			</div>
			
			<ul class="list-card">
				<li class="mycard">
				</li>
				<li class="mycard">
					<div class="head">
						
					</div>
					<div class="body nodata">
						<ul class="list-overflow-hidden">
							
						</ul>
					</div>
				</li>
			</ul>
		
		</div>
		<input type="text" class="form-control" placeholder="사용자id(email)" name="email" value="<c:out value='${email}'/>" >
		
		<!--// 페이지 끝 -->
		
	</div>
</div>

<!-- modal -->
<div id="modal1" class="modal">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">등록이 <br>완료되었습니다.</h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-dark" data-dismiss="modal" onclick="location.href='/mywed/myWedding?email=${email}'">등록 완료!</button>
			</div>
		</div>
	</div>
</div>


<script>


$('#modal1').modal();



</script>
</body>
</html>