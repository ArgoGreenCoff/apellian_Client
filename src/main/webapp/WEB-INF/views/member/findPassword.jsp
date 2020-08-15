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
<script src="../../static/js/member/findPassword.js" type="text/javascript"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script> 


<script type="text/javascript">

$(document).ready(function() {

	document.getElementById("errmsg1").style.display = 'none';
	
});



// 휴대폰번호로 인증처리 위해서 ajax 처리 

function Nextsubmit(){
	
	// ul 초기화 
	
	// document.getElementById("welcom").value = "1";

	$('#welcom').val('1');
	
	var form_data = {
			email: $('input[name=email]').val(),			
			is_ajax: 1
		};
		
	
	var url="/member/ajax_findpwd";
	
	var ajax = $.ajax({ 
		url : url
		, type: "GET"
		, daataType : "jason"
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8' // json; charset=UTF-8'    // , contentType: 'application/x-www-form-urlencoded; charset=euc-kr'
		, data : form_data
		, beforeSend:function(response){
			
		}	
		, success : function(responseData){
			
			var data = responseData;
			result = responseData;
			
			// 인증처리 보내고 이메일 확인 창으로 이동 : 오류가 아닐 경우는 정상임
			if((data.code == "E")||(data.code == "T")||(data.code == "C")){
				
				document.getElementById("errmsg1").style.display = '';
				var tmp1 = document.getElementById("errmsg1");
				tmp1.innerHTML = data.Description;
				
			}
			else {
				
				document.getElementById("errmsg1").style.display = 'none';
				var tmp1 = document.getElementById("errmsg1");
				tmp1.innerHTML = data.Description;
				
									
				var nextLocation = "/member/emailAuth?email="+$('input[name=email]').val() +""+"&idauthcd="+data.code;
				location.href=nextLocation;

			}
		}
		
			
					
		,complete: function(response){
			
			// var ul = document.getElementById("ul_dynaList");
			// ul.innerHTML = "";	
		}
		, error:function(e) {
			// var ul = document.getElementById("ul_dynaList");
			// ul.innerHTML = "";			
			
			document.getElementById("errmsg1").style.display = 'none';
			var tmp1 = document.getElementById("errmsg1");
			tmp1.innerHTML = "처리 중 오류 발생";
			
			
		}
		, fail: function(){
			//오류발생시
			document.getElementById("errmsg1").style.display = 'none';
			var tmp1 = document.getElementById("errmsg1");
			tmp1.innerHTML = "처리 실패했습니다";
			
		}
	});

	
	// ajax 실행문
	jQuery.ajax.done; // (function(data){})
	 
}




</script>




<spring:url var="authUrl" value="/member/findPassword" />
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
			
				<div class="display-2">
					<h1 class="h4">비밀번호를 잊으셨나요?</h1>
					<p class="text-muted small mt-2">가입한 이메일 주소를 입력해주세요.</p>
				</div>
				
				<div class="form-group mt-4">
					<form class="findpassword-form" id="findpasswordForm" name="findpasswordForm" method="post" action="${authUrl}">
					
						<div class="form-group <c:if test="${fn:contains(result.code, 'E')}">has-error</c:if>">
							<p class="form-label-small">이메일</p>
							
							<input type="text" class="form-control" placeholder="이메일" name="email" value="<c:out value='${email}'/>" onKeyup="if($(this).val()){ $('#a').addClass('btn-primary');} else {$('#a').removeClass('btn-primary');}">
							<!-- 
							<c:if test="${fn:contains(result.code, 'E')}">
							<span class="error-next-box">${result.description}</span>
							</c:if>
							-->
							<span class="error-next-box" id='errmsg1' style="display:''"></span>
						</div>
					</form>	
				</div>				
			</div>

			<div class="fixed-bottom-btn">
				<a href="javascript:Nextsubmit();" id="a" class="btn btn-lg btn-info btn-block">확인</a>
			</div>
			<!--// 페이지 끝 -->
		</div>
		<a href="" class="btn-top"><img class="d-block w-100"
			src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
	</div>
</body>
</html>