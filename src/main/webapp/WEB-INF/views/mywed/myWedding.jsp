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
<script src="../../static/js/mywed/myWedding.js" type="text/javascript"></script>

<script type="text/javascript">

<spring:url var="authUrl1" value="/mywed/categoryList" />
<spring:url var="authUrl2" value="/mywed/accessoryList" />
<spring:url var="authUrl3" value="/mywed/optionList" />

</script>


<spring:url var="authUrl" value="/mywed/myWedding" />
<spring:url var="authUrl1" value="/mywed/edit" />
  
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
	<div id="ct" class="mywedding" style="background-image: url(/static/img/img-bg-wedding-01.png);">
		<!-- 페이지 시작 -->
		
		<div class="container">
			<div class="dday-wrp">
				<div class="flex-justify-space">
				
					<c:if test="${diffDay < 0 }">
						<div class="font-num">D+<c:out value='${diffDay*-1}'/></div>
					</c:if>
					<c:if test="${diffDay >= 0}">
						<div class="font-num">D-<c:out value='${diffDay}'/></div>
					</c:if>
						
					<c:set var="tyUser" value="${users.tyUser}" /> 
					<c:choose>
					        <c:when test="${tyUser== 'CM'}">
					       <a href="${myd.urlContract}"><button type="button" class="icon-view" onclick="location.href='${myd.urlContract}'" >계약자 계약서 보기</button></a> 
					       </c:when>
					        <c:when test="${tyUser== 'NM'}">
					            <button type="button" class="icon-edit" onclick="location.href='/mywed/edit?email=${email}&NextPrc=S'">미계약자 수정</button> <!--  /mywed/edit javascript:submitform1();-->
					       </c:when>
					       <c:when test="${tyUser== 'CP'}">
					       </c:when>
					        <c:otherwise>
					        </c:otherwise>
					</c:choose>
				</div>	
			</div>
			<div class="my-info">
				<div>
					<h1 class="title">					
						<div class="date"><c:out value='${wedmonth}'/><br><c:out value='${weddate}'/></div>
						<!--  <span class="font-text" ><c:out value='${wedmonth}'/><br><c:out value='${weddate}'/></span>  -->
						<br>						
						<div class="name"> ${users.nmUser}<br>${myd.nmPartner} </div>					
						<!--  <span class="font-text" >${users.nmUser}<br>${myd.nmPartner}</span>  -->
					</h1>
					<p class="txt text-white">
						<span class="font-num"> <c:out value='${dtWedDay}'/> </span>
						<br>
						<span class="font-num"> ${myd.nmWeddingHall} </span> <!--  아펠가모 광화문  LL 층-->  
					</p>
				</div>
				
				<form class="mywed-form" id="mywedForm" name="mywedForm" method="post" action="${authUrl}">
					<input type="text" class="form-control" placeholder="이메일" name="email" value="<c:out value='${email}'/>" hidden="true"   > 
				</form>		

				<form class="edit-form" id="editForm" name="editForm" method="post" action="${authUrl1}">
					<input type="text" class="form-control" placeholder="이메일" name="email" value="<c:out value='${email}'/>"  hidden="true"  > 
				</form>
								
			</div>
			
			<ul class="list-card">
				<li class="mycard">
					<div class="head">
						<h2 class="title">준비과정</h2>						
						
						<c:set var="tyUser" value="${users.tyUser}" /> 
						<c:choose>
					        <c:when test="${tyUser== 'CM' || tyUser== 'NM'}">
						       <!--  <a class="btn-more" href="<c:url value='/mywed/categoryList?idWedding=${myd.idWedding}' />"><span class="font-num">${totCtgySumAmp}</span> 원</a> -->
						       <a class="btn-more" href="javascript:funcPop1();">
						       		<p><span class="font-num"><fmt:formatNumber value="${totCtgySumAmp}" pattern="###,###,###"/></span>원</p>
						       </a>
						       </c:when>   
						        <c:otherwise>
						        <a class="btn-more" href="#"><span class="font-num"></span> 원</a>
						        </c:otherwise>
						</c:choose>
																		
					</div>
					<div class="body">
						<ul class="list-overflow-hidden">
							
							<c:forEach var="list" items="${mycategorylist}" varStatus="status">							
							<li>
								<dl class="list-dl">
									<dt>${list.nmCategory}</dt>
									<dd>${list.nmCompany}</dd>
								</dl>
							</li>							
							</c:forEach>							
							<c:if test="${empty mycategorylist}">
								<div class="body nodata">
									<p>준비사항이 없습니다.</p>
								</div>
							</c:if>
							
						</ul>
					</div>
				</li>
				<li class="mycard">
					<div class="head">
						<h2 class="title">부대상품</h2>
						
						<c:set var="tyUser" value="${users.tyUser}" /> 
						<c:choose>
						        <c:when test="${tyUser== 'CM'}">

						       <!--  <a class="btn-more" href="<c:url value='/mywed/accessoryList?idWedding=${myd.idWedding}' />">자세히 보기</a>  -->

								<a class="btn-more" href="javascript:funcPop2();">자세히 보기</a>
						       						       
						       </c:when>
						        <c:otherwise>
						        <a class="btn-more" href="#">자세히 보기</a>
						        </c:otherwise>
						</c:choose>
												
					</div>
					<div class="body">
						<ul class="list-overflow-hidden">
						
							<c:forEach var="list" items="${mwdaccessorylist}" varStatus="status">
								<li>${list.dsAccessory}</li>
							</c:forEach>
							<c:if test="${empty mwdaccessorylist}">
								<div class="body nodata">
									<p>부대상품이 없습니다.</p>
								</div>
							</c:if>
														
						</ul>
					</div>
				</li>
				<li class="mycard">
					<div class="head">
						<h2 class="title">선택사항</h2>
						
						<c:set var="tyUser" value="${users.tyUser}" /> 
						<c:choose>
						        <c:when test="${tyUser== 'CM'}">
						       <!--  <a class="btn-more" href="<c:url value='/mywed/optionList?idWedding=${myd.idWedding}' />"><span class="font-num">${totOptnSumAmp}</span> 원</a> -->

						       <a class="btn-more" href="javascript:funcPop3();"><span class="font-num">
						       <fmt:formatNumber value="${totOptnSumAmp}" pattern="###,###,###"/>
						       </span> 원</a>  
						       </c:when>
						        <c:otherwise>
						        <a class="btn-more" href="#"><span class="font-num">
						        <fmt:formatNumber value="${totOptnSumAmp}" pattern="###,###,###"/>
						        </span> 원</a>
						        </c:otherwise>
						</c:choose>
												
					</div>
					<div class="body nodata">
						<ul class="list-overflow-hidden">

							<c:forEach var="list" items="${optionlist}" varStatus="status">
							<li>
								<dl class="list-dl">
									<dt>${list.dsOption}</dt>
									<dd><span class="font-num"> <fmt:formatNumber value="${list.amPrice}" pattern="###,###,###"/></span>
								</dl>
							</li>
							</c:forEach>																					
							<c:if test="${empty optionlist}">
								<div class="body nodata">
									<p>신청한 선택사항이 없습니다.</p>
								</div>
							</c:if>
							
						</ul>
					</div>
				</li>
			</ul>
		
		</div>
		
		<!--// 페이지 끝 -->
		
	</div>
</div>

<!-- modal -->
<div id="modal1" class="modal">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">환영합니다.</h5>
			</div>
			<div class="modal-body">
				<p class="font-weight-normal">아펠가모 · 더채플 · 루벨 웨딩홀 계약자를 위한 앱입니다.<br>더 빠르고 쉬워진 예식 준비와 아펠리안 패키지, 클래스, 매거진 등 다양한 정보를 받아보세요.</p>
				<p class="font-weight-normal mt-3">* 웨딩홀 미계약 고객은 <span class="text-primary">일부 기능이 제한</span>됩니다.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-dark" data-dismiss="modal" id="modalbtn" >닫기</button>
			</div>
		</div>
	</div>
</div>


<div id="modal2" class="modal modal-mycard">
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h5 class="modal-title">준비과정</h5>
			<button type="button" class="modal-close no-style" data-dismiss="modal">팝업닫기</button>
		</div>
		<div class="modal-body">
			<table class="table table-flxed">
				<tbody>			
					<c:forEach var="list" items="${mycategorylist}" varStatus="status">
						<tr>
							<th>${list.nmCategory}</th>
							
							<c:set var="cdCompany" value="${list.cdCompany}" /> 
							<c:choose>
							<c:when test="${cdCompany == 0 || cdCompany == ''}">
								
								<c:set var="nmCompany" value="${list.nmCompany}" /> 
								<c:choose>
								<c:when test="${nmCompany == null || nmCompany == ''}">
									<td class="text-light">
										<p><a class="d-block btn-more v2"  href="<c:url value='/mywed/Schcompany?email=${email}&idWedding=${list.idWedding}&cdCategory=${list.cdCategory}&cdCompany=${list.cdCompany}' />">업체 미정</a></p>
										<p><span class="font-num"><fmt:formatNumber value="${list.amPrice}" pattern="###,###,###"/></span>원</p>
									</td>								
								</c:when>
								<c:otherwise>
									<td>
										<p><a class="d-block btn-more v2"  href="<c:url value='/mywed/Schcompany?email=${email}&idWedding=${list.idWedding}&cdCategory=${list.cdCategory}&cdCompany=${list.cdCompany}' />">${list.nmCompany}</a></p>
										<p><span class="font-num"><fmt:formatNumber value="${list.amPrice}" pattern="###,###,###"/></span>원</p>
									</td>
								</c:otherwise>
								</c:choose>
								
							</c:when>
							<c:otherwise>
								<td>
									<p><a class="d-block btn-more v2"  href="<c:url value='/mywed/Schcompany?email=${email}&idWedding=${list.idWedding}&cdCategory=${list.cdCategory}&cdCompany=${list.cdCompany}' />">${list.nmCompany}</a></p>
									<p><span class="font-num"><fmt:formatNumber value="${list.amPrice}" pattern="###,###,###"/></span>원</p>
								</td>
							</c:otherwise>
							</c:choose>
								
						</tr>
					</c:forEach>
					<c:if test="${empty mycategorylist}">
						<div class="body nodata">
							<p>준비사항 내역이 없습니다.</p>
						</div>
					</c:if>

				</tbody>
			</table>
		</div>
		<div class="modal-footer">
		
			<h6 class="tit">총 금액</h6>			
			<div class="price text-primary text-right"><span class="font-num h3">
			<fmt:formatNumber value="${totCtgySumAmp}" pattern="###,###,###"/>
			</span><span class="h6">원</span></div>
		</div>
	</div>
</div>
</div>

<div id="modal3" class="modal modal-mycard">
<div class="modal-dialog pb-0">
	<div class="modal-content">
		<div class="modal-header">
			<h5 class="modal-title">부대상품</h5>
			<button type="button" class="modal-close no-style" data-dismiss="modal">팝업닫기</button>
		</div>
		<div class="modal-body">
			<table class="table type2 table-flxed mt-2">
				<tbody>
				
					<c:forEach var="list" items="${mwdaccessorylist}" varStatus="status">
					<tr>
						<td class="font-weight-bold">${list.dsAccessory}</td>							
					</tr>
					</c:forEach>																					
					<c:if test="${empty mwdaccessorylist}">
					<tr>
						<td class="font-weight-bold">부대상품이 없습니다</td>
					</tr>
					</c:if>
										
				</tbody>
			</table>
		</div>
	</div>
</div>
</div>

<div id="modal4" class="modal modal-mycard">
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<div class="modal-title">
				<h5>선택사항</h5>
				<p class="text-muted">문의/신청은 예약팀으로 연락주세요.</p>
			</div>
			<button type="button" class="modal-close no-style" data-dismiss="modal">팝업닫기</button>
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
		
		<div class="modal-footer">
			<h6 class="tit">총 금액</h6>
			<div class="price text-primary text-right"><span class="font-num h3">
			<fmt:formatNumber value="${totOptnSumAmp1}" pattern="###,###,###"/>			
			</span><span class="h6">원</span></div>
		</div>
		
		
	</div>
</div>
</div>






<script>


$('#modal1').modal();



</script>
</body>
</html>