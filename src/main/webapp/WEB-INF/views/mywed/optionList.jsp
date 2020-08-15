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
<script src="../../static/js/package/optionList.js" type="text/javascript"></script>

<script type="text/javascript">

</script>

<spring:url var="authUrl" value="/mywed/optionList222" />
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
				<h1 class="h4">선택사항</h1>
				<p class="text-muted small mt-2">문의/신청은 예약팀으로 연락주세요.</p>
			</div>	
					
			<div class="modal-body">
				<table class="table type2 table-flxed mt-2">
					<colgroup>
						<col>
						<col style="width: 130px">
					</colgroup>
					<tbody>
						<c:forEach var="list" items="${optionlist}" varStatus="status">
						<tr>
							<th>${list.dsOption}</th>
							<td class="text-muted text-right"><span class="font-num">   
							<fmt:formatNumber value="${list.amPrice}" pattern="###,###,###"/>
							</span> 원</td>
						</tr>
						</c:forEach>																					
						<c:if test="${empty optionlist}">
							<div class="body nodata">
								<p>신청한 선택사항이 없습니다.</p>
							</div>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>

<!-- 
		<form class="mywedoption-form" id="mywedoptionForm" name="mywedoptionForm" method="post" action="${authUrl}">
			<input type="text" class="form-control" placeholder="이메일" name="idWedding" value="<c:out value='${idWedding}'/>" hidden="true">
			<a href="javascript:submitform();" class="btn btn-lg btn-info btn-block" hidden="true" >테스트</a>				
		</form>				
 -->
 
		<div class="modal-footer">
			<h6 class="tit">총 금액</h6>
			<div class="price text-primary text-right"><span class="font-num h3">
			<fmt:formatNumber value="${totOptnSumAmp}" pattern="###,###,###"/>			
			</span><span class="h6">원</span></div>
		</div>
 		
	<!--// 페이지 끝 -->
	</div>
		
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>


</body>
</html>