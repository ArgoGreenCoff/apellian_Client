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
<script src="../../static/js/package/packageApply.js" type="text/javascript"></script>

<spring:url var="authUrl" value="/package/packageApply" />
</head>
<body>
<div id="wrap">
	<header id="hd">
		<div class="container">
			<a href="" class="icon-back">뒤로가기</a>
		</div>
	</header>
	<!-- gnb -->
	<nav id="gnb">
		<ul class="gnb">
			<li class="active"><a class="mn01" href="mywed/myWedding">나의 웨딩</a></li>
			<li><a class="mn02" href="package/packageList">패키지</a></li>
			<li><a class="mn03" href="">혜택</a></li>
			<li><a class="mn04" href="">매거진</a></li>
			<li><a class="mn05" href="">더보기</a></li>
		</ul>
	</nav>
	
	<!-- container -->
	<div id="ct" class="pt-0">
		
	<!-- 페이지 시작 -->
	<div class="container">
		<h1 class="display-1">상담 신청</h1>
		
			
	    <ul class="list-survey mb-4">
	        <li>
	            <h2 class="title">${pkg.nmPkg}</h2>
	            <div class="body d-block">
	                <div class="w100">
	                
						<c:forEach var="list" items="${pkgdetailList}" varStatus="status">
							
							<dl class="list-dl">
								<dt class="w-25">${list.nmCategory}</dt>
								<dd>${list.nmCompany}</dd>
							</dl>
						</c:forEach>
						<c:if test="${empty pkgdetailList}">
							<div class="body nodata">
								<p>- 패키지 업체별 내역 -</p>
							</div>
						</c:if>
							                    
	                </div>
	            </div>
	        </li>
	    </ul>
	    
		<form class="packageapply-form" id="packageapplyForm" name="packageapplyForm" method="post" action="${authUrl}">
			<div class="form-group">
				<div class="form-label">
					<p>의견</p>
					<p class="text-muted">궁금하거나, 특별히 신경썼으면 하는 내용을 적어주세요.</p>
				</div>
				<div class="form-text-size">
					<textarea class="form-control" rows="5" placeholder="최소 5자 이상 입력해주세요." id="dsconsult" name="dsconsult" value="<c:out value='${dsconsult}'/>" > </textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="form-label">
					<p>관심 업체</p>
					<p class="text-muted">관심 있는 업체가 있는 경우 적어주세요.</p>
				</div>
				<div class="form-text-size">
					<textarea class="form-control" rows="5" placeholder="최소 5자 이상 입력해주세요." id="dscompany" name="dscompany" value="<c:out value='${dscompany}'/>" > </textarea>
				</div>
			</div>
			<div class="form-group">
				<p class="form-label">예산 <span class="text-primary">(필수)</span></p>
				<ul>
					<li>  <!--  예산코드 : 10 (100-150)	15 (150-200)	20 (200-250)	25 (250-300)	30 (300-350)	40 (400이상) -->
						<label class="input-chk input-chk-pos-right">
							<input type="radio" class="sr-only" name="cdbudget" checked value="10">
							<i></i> 100 ~ 150만원
						</label>
					</li>
					<li>
						<label class="input-chk input-chk-pos-right">
							<input type="radio" class="sr-only" id="cdbudget" name="cdbudget" value="15">
							<i></i> 150 ~ 200만원
						</label>
					</li>
					<li>
						<label class="input-chk input-chk-pos-right">
							<input type="radio" class="sr-only" id="cdbudget" name="cdbudget" value="20">
							<i></i> 200 ~ 250만원
						</label>
					</li>
					<li>
						<label class="input-chk input-chk-pos-right">
							<input type="radio" class="sr-only" id="cdbudget" name="cdbudget" value="25">
							<i></i> 250 ~300만원
						</label>
					</li>
					<li>
						<label class="input-chk input-chk-pos-right">
							<input type="radio" class="sr-only" id="cdbudget" name="cdbudget" value="30">
							<i></i> 300 ~ 350만원
						</label>
					</li>
					<li>
						<label class="input-chk input-chk-pos-right">
							<input type="radio" class="sr-only" id="cdbudget" name="cdbudget" value="40">
							<i></i> 400만원 이상
						</label>
					</li>
				</ul>
			</div>
			<hr>
			<div class="form-group">
				<div class="form-label">
					<p>추가 상담</p>
					<p class="text-muted">함께 상담이 가능한 분야입니다.</p>
				</div>
				<ul class="row no-gutters">
					<li class="col-6">
						<label class="input-chk type2">
							<input type="checkbox" class="sr-only" checked id="ynaddtional01" name="ynaddtional01" value="Y">
							<i></i> 본식 스냅
						</label>
					</li>
					<li class="col-6">
						<label class="input-chk type2">
							<input type="checkbox" class="sr-only" id="ynaddtional02" name="ynaddtional02" value="Y">
							<i></i> 허니문
						</label>
					</li>
					<li class="col-6">
						<label class="input-chk type2">
							<input type="checkbox" class="sr-only" checked id="ynaddtional03" name="ynaddtional03" value="Y">
							<i></i> DVD
						</label>
					</li>
					<li class="col-6">
						<label class="input-chk type2">
							<input type="checkbox" class="sr-only" id="ynaddtional04" name="ynaddtional04" value="Y">
							<i></i> 가전 (하이마트)
						</label>
					</li>
					<li class="col-6">
						<label class="input-chk type2">
							<input type="checkbox" class="sr-only" id="ynaddtional05" name="ynaddtional05" value="Y">
							<i></i> 예물
						</label>
					</li>
					<li class="col-6">
						<label class="input-chk type2">
							<input type="checkbox" class="sr-only" id="ynaddtional06" name="ynaddtional06" value="Y">
							<i></i> 가구 (한샘)
						</label>
					</li>
					<li class="col-6">
						<label class="input-chk type2">
							<input type="checkbox" class="sr-only" id="ynaddtional07" name="ynaddtional07" value="Y">
							<i></i> 예복
						</label>
					</li>
					<li class="col-6">
						<label class="input-chk type2">
							<input type="checkbox" class="sr-only" id="ynaddtional08" name="ynaddtional08" value="Y">
							<i></i> 데이트스냅, 제주 스탭
						</label>
					</li>
					<li class="col-6">
						<label class="input-chk type2">
							<input type="checkbox" class="sr-only" id="ynaddtional09" name="ynaddtional09" value="Y">
							<i></i> 한복
						</label>
					</li>
					<li class="col-6">
						<label class="input-chk type2">
							<input type="checkbox" class="sr-only" id="ynaddtional10" name="ynaddtional10" value="Y">
							<i></i> 기타
						</label>
					</li>
				</ul>
			</div>
			<hr>
			
			
		    <div class="form-group" <c:if test="${result.code eq 'I'}">has-error</c:if>>
		    	<p class="form-label">약관 동의 <span class="text-primary">(필수)</span></p>
		        <label class="input-chk type2 agreement">				     
		            <input type="checkbox" class="sr-only" name="chkperinfo" id="chkperinfo" value="Y">
		            <i></i> <span> <a href="/member/agreement02"><u>개인정보 수집</u></a> , <a href="agreement03"><u>제 3자 정보 제공 동의</a>에 모두 동의합니다.</u></span>
		        </label>				        
		    </div>
            <c:if test="${result.code eq 'I'}">
			<span class="error-next-box">${result.description}</span>
			</c:if>	
			

			
			<input type="text" class="inputclass" placeholder="이메일" name="email" value="<c:out value='${email}'/>" hidden="true" >
			<input type="text" class="inputclass" placeholder="idPkg" name="idPkg" value="<c:out value='${pkg.idPkg}'/>" hidden="true"  >
			<input type="text" class="inputclass" placeholder="gubun" name="gubun" value="<c:out value='${gubun}'/>" hidden="true" >
	
			
		</form>
		
	</div>

	<div class="fixed-bottom-btn">				
		<a href="javascript:submitform();" class="btn btn-lg btn-info btn-block">상담 신청</a>
		<!--  <button type="button" class="btn btn-lg btn-info btn-block" data-toggle="modal" data-target="#modal1">상담 신청</button>  -->
	</div>
	    
	
	<!--// 페이지 끝 -->
	</div>
		<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
	</div>
	
	</body>
</html>