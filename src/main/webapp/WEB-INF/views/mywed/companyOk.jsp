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


$("#schNmCompany").click(function(){
	if(e.keycode==13){		
		$('#schcompanyForm').submit();	
	}	
}

</script>

<spring:url var="authUrl" value="/mywed/companyOk" />
</head>
<body>

<div id="wrap">
	<header id="hd" class="search-area">
		<div class="container">
			<a href="" class="icon-back">뒤로가기</a>
		</div>
	</header>

	<!-- container -->
	<div id="ct">
	
		<!-- 페이지 시작 -->
		<div class="container">		
		
			<form class="schcompanyOk-form" id="schcompanyOkForm" name="schcompanyOkForm" method="post" action="${authUrl}">
				<div class="display-2">							
					
					<input type="text" class="form-control" placeholder="업체명 검색" name="schNmCompany" value="" >
					
					<input type="text" class="form-control" placeholder="idwed" name="idWedding" value="<c:out value='${idWedding}'/>" >
					<input type="text" class="form-control" placeholder="cdcategory" name="cdCategory" value="<c:out value='${cdCategory}'/>" >
					<input type="text" class="form-control" placeholder="cdcategory" name="cdCompany" value="<c:out value='${cdCompany}'/>" >
		
					
				</div>
			</form>	
			
			
						
			<div class="modal-body">
				<table class="table type2 table-flxed mt-2">
					<colgroup>
						<col>
						<col style="width: 130px">
					</colgroup>
					<tbody>
						<c:forEach var="list" items="${rtnSchNmCompanylist}" varStatus="status">
							<tr>
							<td>
								<ul class="list-border mt-2">
									<li>	
										
											<p class="h6">${list.nmCompany}</p>
								        	<p class="text-muted mt-2"> ${list.dsAddr}</p>
								        
																		
									</li>		
								</ul>
							</td>
							</tr>
						</c:forEach>																					
						<c:if test="${empty rtnSchNmCompanylist}">
							<tr>
								<th>
								<a class="btn-more" href="<c:url value='/mywed/cost?idWedding=${list.idWedding}&cdCategory=${list.cdCategory}&idWeddingHall=${list.idWeddingHall}&cdCompany=' />">
								직접 입력 - ${schNmCompany} 
								</a>
								</th>
								<td class="text-muted text-left">
								</td>							
							</tr>
							<tr>
								<th class="text-muted text-left">
									<div class="body nodata">
										<p>검색 결과가 없습니다.</p>
									</div>
								</th>
								<td >
									
								</td>							
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>

  
	<!--// 페이지 끝 -->
	</div>
		
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
</div>


</body>
</html>