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

<script type="text/javascript">

</script>

<spring:url var="authUrl" value="/mywed/accessoryList1234" />
</head>
<body>

<div id="wrap">
	<header id="hd" class="search-area">
		<div class="container">
			<a href="javascript:history.back();" class="icon-back">뒤로가기</a>
		</div>
	</header>

	<!-- container -->
	<div id="ct">
	
		<!-- 페이지 시작 -->
		<div class="container">		
		
			<div class="display-2">			
				<h1 class="h4">부대상품</h1>				
			</div>	
					
			<div class="modal-body">
				<table class="table type2 table-flxed mt-2">
					<colgroup>
						<col>
						<col style="width: 130px">
					</colgroup>
					<tbody>
						<c:forEach var="list" items="${Mywedacrylist}" varStatus="status">
						<tr>
							<th>${list.dsAccessory}</th>							
						</tr>
						</c:forEach>																					
						<c:if test="${empty Mywedacrylist}">
							<div class="body nodata">
								<p>부대상품이 없습니다.</p>
							</div>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>

<!-- 
		<form class="mywedacry-form" id="mywedacryForm" name="mywedacryForm" method="post" action="${authUrl}">
			<input type="text" class="form-control" placeholder="wedId" name="idWedding" value="<c:out value='${idWedding}'/>" hidden="true">
			<a href="javascript:submitform();" class="btn btn-lg btn-info btn-block" hidden="true" >테스트</a>				
		</form>				
 -->
 		
	<!--// 페이지 끝 -->
	</div>
		
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>


</body>
</html>