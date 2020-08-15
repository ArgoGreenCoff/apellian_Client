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
<link rel="stylesheet" href="/static/css/bootstrap.min.css">
<link rel="stylesheet" href="/static/css/swiper.min.css">
<link rel="stylesheet" href="/static/css/common.css">
<script src="/static/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="/static/js/popper.min.js" type="text/javascript"></script>
<script src="/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/static/js/swiper.min.js" type="text/javascript"></script>
<script src="/static/js/common.js" type="text/javascript"></script>
<script src="/static/js/package/packageList.js" type="text/javascript"></script>

<script type="text/javascript">

// $("#cdPkgTheme").val(requst.getparam("idPkgnmPkgTheme"));

</script>

<spring:url var="authUrl" value="/package/packageList" />
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
	<div id="ct" class="pt-0">
	
		<!-- 페이지 시작 -->	
		
		 				
		<div class="sub-title">
			<h1>아펠리안 패키지</h1>
		</div>
		<nav class="sub-nav">
			<ul class="nav">
					
				<c:forEach var="list" items="${cdpkgthemelist}"  varStatus="status">
					<li class="nav-item">
					
							
						<c:set var="cdPkgTheme" value="${list.cdPkgTheme}" /> 
						<c:choose>
						        <c:when test="${cdPkgTheme== NowcdPkgTheme}">
							<a class="nav-link active" href="<c:url value='/package/packageList?email=${email}&cdPkgTheme=${list.cdPkgTheme}' />">${list.nmPkgTheme}</a>
						       </c:when>
						        <c:otherwise>
							<a class="nav-link " href="<c:url value='/package/packageList?email=${email}&cdPkgTheme=${list.cdPkgTheme}' />">${list.nmPkgTheme}</a>
						        </c:otherwise>
						</c:choose>						
							
													
					</li>
				</c:forEach>				
				<c:if test="${empty cdpkgthemelist}">
					<div class="body nodata">
						<p>- 테마 준비 중 -</p>
					</div>
				</c:if>			
								
			</ul>
		</nav>
		<div class="container-sm">
			<ul class="list-thumb-lg">
			
			    <c:forEach var="list" items="${themeimagesList}" varStatus="status">
				<li>
					<div class="thumbnail">
							
						<a href="<c:url value='/package/packageDetail?email=${email}&idPkg=${list.idPkg}' />">
							<img src='${list.urlImg}' alt="">
						</a>
						
						<!-- 카테고리 상세 들어가야 됨 -->
					</div>
					
					<div class="meta meta-type2">
	                	<p class="tit">
	                	<a href="<c:url value='/package/packageDetail?email=${email}&idPkg=${list.idPkg}' />">
	                	${list.nmPkg}
	                	</a>
	                	</p>
						
						<div class="badge-wrp">
							<c:forEach var="item" items="${themedetailList}" varStatus="status">								
								<c:if test="${list.idPkg == item.idPkg}">  
								<span class="badge badge-outline-gray"><span class="a">${item.nmCategory}</span>${item.nmCompany}</span>
								</c:if>								
							</c:forEach>
							<c:if test="${empty themedetailList}">
								<div class="body nodata">
									<p></p>
								</div>
							</c:if>
						</div>
					</div>
				
				</li>
				</c:forEach>
				<c:if test="${empty themeimagesList}">
					<div class="body nodata">
						<p>- 패키지  준비 중 -</p>
					</div>
				</c:if>
										
			</ul>		
		</div>

		<input type="text" class="inputclass" placeholder="이메일" name="email" value="<c:out value='${email}'/>" hidden="true" >
		<input type="text" class="inputclass" placeholder="선택된테마" name="NowcdPkgTheme" value="<c:out value='${NowcdPkgTheme}'/>" hidden="true" >
		
		<!--// 페이지 끝 -->
		
	</div>
	<a href="javascript:;" onclick="$('html, body').animate( { scrollTop : 0 }, 500);" class="btn-top">
		<img class="d-block w-100" src="/static/img/icon/btn-top.png" alt="상단으로 이동">
	</a>
</div>

</body>
</html>