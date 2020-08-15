<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ page import="java.util.*,java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport"  content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Apellian</title>
<link rel="stylesheet" href="../../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../../static/css/swiper.min.css">
<link rel="stylesheet" href="../../static/css/common.css">
<script src="../../static/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../../static/js/popper.min.js" type="text/javascript"></script>
<script src="../../static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../static/js/swiper.min.js" type="text/javascript"></script>
<script src="../../static/js/common.js" type="text/javascript"></script>
<!--  <script src="../../static/js/package/company.js" type="text/javascript"></script>  -->

<script type="text/javascript">


// onclick="" 


$("#submitCompCost").click(function(){
	
	$('#costForm').submit();	
})


function costsubmit(){

	$('#costForm').submit();	

}

</script>

<spring:url var="authUrl" value="/mywed/cost" />
</head>
<body>

<div id="wrap">
	<header id="hd">
		<div class="container">
			<a href="javascript:history.back();" class="icon-back">뒤로가기</a>			
		</div>
	</header>
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
	<div id="ct">
	
		<!-- 페이지 시작 -->
		<div class="container">
			<div class="display-2">
				<h1 class="h1 font-serif">비용 입력</h1>
			</div>
				
			<form class="cost-form" id="costForm" name="costForm" method="post" action="${authUrl}">
				<div>
					<input type="text" class="form-control" placeholder="email"  name="email"  value="<c:out value='${email}'/>"  hidden="true" >
					<input type="text" class="form-control" placeholder="idWedding"  name="idWedding"  value="<c:out value='${idWedding}'/>"  hidden="true" >
					<input type="text" class="form-control" placeholder="cdcategory" name="cdCategory" value="<c:out value='${cdCategory}'/>" hidden="true" >
					<input type="text" class="form-control" placeholder="cdCompany"  name="cdCompany"  value="<c:out value='${cdCompany}'/>" hidden="true" >
					<input type="text" class="form-control" placeholder="nmCompany"  name="nmCompany"  value="<c:out value='${nmCompany}'/>" hidden="true" >
					<input type="text" class="form-control" placeholder="NextChk"    name="NextChk"    value="<c:out value='${NextChk}'/>" hidden="true"  > <!-- 현재 페이지 등록처리 -->
				</div>
				
				<div class="form-group " <c:if test="${result.code eq 'p'}">has-error</c:if>> <!--  left-select -->
					<div class="form-survey">
						<div class="form-row form-lg align-items-center">
							<div class="col-8 offset-2">
								<input type="num" name="amPrice" class="form-control text-center font-num" placeholder="0" onKeyup="inputNumberAutoComma(this);if($(this).val()){ $('#a').addClass('btn-primary');} else {$('#a').removeClass('btn-primary');}">
							</div>
							<span class="col-form-label col-auto font-weight-bold">원</span>
						</div>
					</div>				
				            
					<c:if test="${result.code eq 'p'}">
					<span class="error-next-box">${result.description}</span>
					</c:if>
					<c:if test="${result.code eq '00'}">
					<span class="error-next-box">${result.description}</span>
					</c:if>							
				</div>
				
			</form>	
			
			
		</div>
		
		
					
							
		<div class="fixed-bottom-btn">
			<button type="button" id="a" class="btn btn-info btn-block" onclick="costsubmit();">입력 완료</button>
		</div>  
		<!--// 페이지 끝 -->
		</div>
		
		
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>


</body>
</html>