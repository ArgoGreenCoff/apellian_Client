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
<!--  <script src="../../static/js/package/categoryList.js" type="text/javascript"></script> -->

<script type="text/javascript">


</script>

<spring:url var="authUrl" value="/mywed/categoryList1234" />
</head>
<body>

<div id="wrap">
	<header id="hd" class="search-area">
		<div class="container">
			<a href="javascript:history.back();" class="icon-back">뒤로가기</a> <!--  /mywed/myWedding?email=${email} -->
		</div>
	</header>

	<!-- container -->
	<div id="ct">
	
		<!-- 페이지 시작 -->
		<div class="container">		
		
			<input type="text" class="form-control" placeholder="" name="email" value="<c:out value='${email}'/>" hidden="true">
		

			
			<!--  <div class="display-2">  -->			
				<h5 class="modal-title">준비과정</h5>				
			<!-- </div> -->	
					
			<div class="modal-body">
				<table class="table type2 table-flxed">
					<colgroup>
						<col style="width: 130px">
						<col>
					</colgroup>
					<tbody>
						<c:forEach var="list" items="${popcategorylist}" varStatus="status">
						<tr>
							<th>${list.nmCategory} </th>
							<td class="text-muted text-right">
							
								<a class="btn-more" href="<c:url value='/mywed/company?idWedding=${list.idWedding}&cdCategory=${list.cdCategory}&cdCompany=${list.cdCompany}' />">
								
								<c:set var="cdCompany" value="${list.cdCompany}" /> 
								<c:choose>
							        <c:when test="${cdCompany == 0}">
							       ${list.nmCompany} (미지정 업체)
							       </c:when>
							       <c:when test="${cdCompany == null}">
							       ${list.nmCompany} (미지정 업체)
							       </c:when>
							        <c:otherwise>
							        ${list.nmCompany}
							        </c:otherwise>
								</c:choose>
								
								</a>
								<br>
								<span class="font-num"><fmt:formatNumber value="${list.amPrice}" pattern="###,###,###"/></span> 원
							</td>
						</tr>
						</c:forEach>																					
						<c:if test="${empty popcategorylist}">
							<div class="body nodata">
								<p>준비사항 내역이 없습니다.</p>
							</div>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>


 <!-- 
		<form class="mywedcategory-form" id="mywedcategoryForm" name="mywedcategoryForm" method="post" action="${authUrl}">
			<input type="text" class="form-control" placeholder="idwed" name="idWedding" value="<c:out value='${idWedding}'/>" hidden="true">
			<a href="javascript:submitform();" class="btn btn-lg btn-info btn-block" hidden="true" >테스트</a>				
		</form>				
 -->
  
		<div class="modal-footer group">
			<h6 class="tit">총 금액</h6>
			<div class="price text-primary text-right"><span class="font-num h3">
			<fmt:formatNumber value="${totCtgySumAmp}" pattern="###,###,###"/>
			</span><span class="h6">원</span></div>
		</div>
 		
	<!--// 페이지 끝 -->
	</div>
		
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>


</body>
</html>