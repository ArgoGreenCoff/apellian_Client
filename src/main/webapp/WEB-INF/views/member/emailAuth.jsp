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
<script src="../../static/js/member/emailAuth.js" type="text/javascript"></script>
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
			idauthcd: $('input[name=idauthcd]').val(),			
			cd1: $('input[name=cd1]').val(),			
			cd2: $('input[name=cd2]').val(),			
			cd3: $('input[name=cd3]').val(),			
			cd4: $('input[name=cd4]').val(),			
			is_ajax: 1
		};
		
	
	var url="/member/ajax_emailAuth";
	
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
			if(data.code == "E"){
				
				document.getElementById("errmsg1").style.display = '';
				var tmp1 = document.getElementById("errmsg1");
				tmp1.innerHTML = data.Description;
				
			}
			else {
				
				document.getElementById("errmsg1").style.display = 'none';
				var tmp1 = document.getElementById("errmsg1");
				tmp1.innerHTML = data.Description;
				
									
				var nextLocation = "/member/newPassword?email="+$('input[name=email]').val();
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




// 길이 체크
function numberMaxLength(e){
    if(e.value.length == e.maxLength){
    	
    	document.getElementById(next).focus();
    	/*
    	if ((e.name == "cd1") || (e.name == "cd2") || (e.name == "cd3")) {
    		document.getElementById(next).focus();
    	}
    	else if (e.name == "cd4") {
    		document.getElementById(next).focus();
            // e.value = e.value.slice(0, e.maxLength);    		
    	}
    	*/
    	

    }
}


//숫자체크와 자동포커스 

function go_next(arg, nextname,size){

 value = arg.value;
 len = value.length;
 is_num = Number(value);

 if(!is_num) {
  if((len > 0) && (value != '0') && (value != '00') && (value != '000')) {
   alert('숫자를 넣어주세요');
   arg.select();
   arg.focus();
   return false;
  }
 }

 if(len == size){
  if (nextname != null) nextname.focus();
  return true;
 }
}

function onlyNumber1(){
    if ((event.keyCode<48)||(event.keyCode>57)){
            event.returnValue=false;
    }
}


function js_tab_order(arg,nextname,len) {  
    if (arg.value.length==len) {
        nextname.focus() ;
        return;
     }
}

function on_keyCheck(arg, nextname, len){
	
	// inputNumberAutoComma(this);
	
	if($(this).val()){ 
		$('a').addClass('btn-primary');
	} else {
		$('a').removeClass('btn-primary'); 
	}
	
	 $('welcome').val("2");
	 
	// document.getElementById("welcome").value = "1";
	/*
    if ((event.keyCode<48)||(event.keyCode>57)){
        event.returnValue=false;
	}
	
    
   // $('#welcome').val('2');
    
    // document.getElementById("welcome").value = "2";
	
    if (arg.value.length==len) {nextname.focus() ;return;}
     }
    
    // $('#welcome').val('3');
    
    
    // document.getElementById("welcome").value = "3";
	 */
    
}


function submitform(){

	// 서버전송
	$('#emailauthForm').submit();
	
}

</script>

<spring:url var="authUrl" value="/member/emailAuth" />

</head>
<body onLoad="document.emailauthForm.cd1.focus();">
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
					<h1 class="h4">인증번호를 확인해주세요.</h1>
					<p class="text-muted small mt-2">발송된 코드 4자리를 입력하시면  비밀번호를 변경하실 수 있습니다.</p>
				</div>
				
				<div class="form-row form-group mt-5">
					<form class="emailauth-form" id="emailauthForm" name="emailauthForm" method="post" action="${authUrl}">
						 
						<input type="text" class="form-control" placeholder="email" name="email" id="email" value='${email}' hidden="true"  > 
						<input type="number" class="form-control" placeholder="인증번호 id" name="idauthcd" id="idauthcd" value="<c:out value='${idauthcd}'/>" hidden="true" >   <!--  value="<c:out value='${idauthcd}'/>"  > -->

						<div class="form-group <c:if test="${fn:contains(result.code, 'E')}">has-error</c:if>">
							<div class="form-row form-md">
								<div class="col-3">
									<input type="number" class="form-control text-center font-num" placeholder="0" name="cd1" id="cd1" value="<c:out value='${cd1}'/>" maxlength="1" size="1" onKeyup="if($(this).val()){ $('#a').addClass('btn-primary'); } else {$('#a').removeClass('btn-primary');}" >
								</div>
								<div class="col-3">
									<input type="number" class="form-control text-center font-num" placeholder="" name="cd2" id="cd2" value="<c:out value='${cd2}'/>" maxlength="1" size="1" onKeyup="if($(this).val()){ $('#a').addClass('btn-primary'); } else {$('#a').removeClass('btn-primary');}" >
								</div>
								<div class="col-3">
									<input type="number" class="form-control text-center font-num" placeholder="" name="cd3" id="cd3" value="<c:out value='${cd3}'/>" maxlength="1" size="1" onKeyup="if($(this).val()){ $('#a').addClass('btn-primary'); } else {$('#a').removeClass('btn-primary');}" >
								</div>
								<div class="col-3">
									<input type="number" class="form-control text-center font-num" placeholder="" name="cd4" id="cd4" value="<c:out value='${cd4}'/>" maxlength="1" size="1" onKeyup="if($(this).val()){ $('#a').addClass('btn-primary'); } else {$('#a').removeClass('btn-primary');}" > 
								</div>								
							</div>
						</div>
						
						<!-- 
						<c:if test="${fn:contains(result.code, 'E')}">
						<span class="error-next-box">${result.description}</span>
						</c:if>
						 -->
						
						<span class="error-next-box" id='errmsg1' style="display:''"></span>
						
						<!--  <input type="text" class="form-control font-num text-center" name="authid" hidden="true" >  -->
						
						
						<!--  <div><input type="text" id="welcome" disabled></div>  -->
						
						
					</form>	
				</div>
				
				<!-- <p class="text-center h6 mt-5">코드가 발송되었습니다.</p> -->
								
			</div>

			<div class="fixed-bottom-btn">
				<!--  <button type="button" id="a" class="btn btn-info btn-block" onclick="submitform();">확인</button> -->
				<a href="javascript:Nextsubmit();" id="a" class="btn btn-lg btn-info btn-block">확인</a>
			</div>

			<!--// 페이지 끝 -->
		</div>
		<a href="" class="btn-top"><img class="d-block w-100"
			src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
	</div>
</body>
</html> 