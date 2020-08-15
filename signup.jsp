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
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script> 
<link rel="stylesheet" href="../../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../../static/css/swiper.min.css">
<link rel="stylesheet" href="../../static/css/common.css">
<script src="../../static/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../../static/js/popper.min.js" type="text/javascript"></script>
<script src="../../static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../static/js/swiper.min.js" type="text/javascript"></script>
<script src="../../static/js/common.js" type="text/javascript"></script>
<script src="../../static/js/member/signup.js" type="text/javascript"></script>


<script type="text/javascript">


$("#Selnocountry").val("+82");
// document.getElementById("nocountry").value = "+82";


$(document).ready(function() {

	
});






// 휴대폰번호로 인증처리 위해서 ajax 처리 

function Nextsubmit(){
	
	// ul 초기화 
	
	var form_data = {
			email: $('input[name=email]').val(),
			passwd: $('input[name=passwd]').val(),
			is_ajax: 1
		};
	
	
	var url="/member/ajax_signin";
	
	var ajax = $.ajax({ 
		url : url
		, type: "GET"
		, daataType : "jason"
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8' // json; charset=UTF-8'    // , contentType: 'application/x-www-form-urlencoded; charset=euc-kr'
		, data : form_data
		, beforeSend:function(response){
			
		}	
		, success : function(responseData){
			
			$('input[name=welcom]').val('ok');
			
			// var data = JSON.parse(responseData);
			var data = responseData;
			result = responseData;
			
			// document.getElementById("welcom").value = "Success OK";
			$('#welcom').val('Success OK');
			
			
			if (data.code == "00"){
				location.href="/mywed/myWedding?email="+$('input[name=email]').val();
			}
			else {
				
				if(data.code == "E"){
					
					document.getElementById("errmsg1").style.display = '';
					var tmp1 = document.getElementById("errmsg1");
					tmp1.innerHTML = data.Description;
					
				}
				else {
					
					document.getElementById("errmsg1").style.display = 'none';
					var tmp1 = document.getElementById("errmsg1");
					tmp1.innerHTML = data.Description;
					
				}
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
			tmp1.innerHTML = "인증번호 추출 중 오류 발생";
			
		}
		, fail: function(){
			//오류발생시
			document.getElementById("errmsg1").style.display = 'none';
			var tmp1 = document.getElementById("errmsg1");
			tmp1.innerHTML = "인증번호 추출이 실패했습니다";
			
		}
	});

	
	// ajax 실행문
	jQuery.ajax.done; // (function(data){})
	 
}



</script>

<spring:url var="authUrl" value="/member/signup" />
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
				<h1 class="display-2">아펠리안에 오신것을 환영합니다.</h1>
				<form class="signup-form" id="regForm" name="regForm" method="post" action="${authUrl}">
					
					<div class="form-group <c:if test="${fn:contains(result.code, 'E')}">has-error</c:if>">					
						<p class="form-label-small">이메일</p>
						<input type="text" class="form-control" placeholder="이메일" name="email" value="<c:out value='${email}'/>">						
						<c:if test="${fn:contains(result.code, 'E')}">
						<span class="error-next-box">${result.description}</span>
						</c:if>						
					</div>
					
					<div class="form-group" <c:if test="${result.code eq 'P'}">has-error</c:if>>					
						<p class="form-label-small">비밀번호</p>
						<div class="input-right-btn">
							<input type="password" class="form-control" placeholder="비밀번호" name="passwd" value="<c:out value='${passwd}'/>">							
							<c:if test="${result.code eq 'P'}">
							<span class="error-next-box">${result.description}</span>
							</c:if>							
							<button type="button" class="icon-invisible">보기</button>							
						</div>
					</div>					
					<div class="form-group" <c:if test="${result.code eq 'N'}">has-error</c:if>>
						<p class="form-label-small">이름</p>
						<input type="text" class="form-control" placeholder="이름" name="nmuser" value="<c:out value='${nmuser}'/>">
						<c:if test="${result.code eq 'N'}">
						<span class="error-next-box">${result.description}</span>
						</c:if>							
					</div>					
					<div class="form-group left-select" <c:if test="${result.code eq 'T'}">has-error</c:if>>
						<p class="form-label-small">휴대전화 번호</p>				        
				        <a href="#modal" data-toggle="modal" class="contry-select" > <!--  +82  -->
				        <div class="Selnocountry" name="Selnocountry">+82</div>
				        <!--  <input type="text" class="form-control" placeholder="" name="nocoun try " value="<c:out value='$ { n ocountry }'/>" > --> 
				        <i class="icon-arrows"></i></a>				        
						<input type="number" class="form-control" placeholder="" name="notel" value="<c:out value='${notel}'/>">
						<c:if test="${result.code eq 'T'}">
						<span class="error-next-box">${result.description}</span>
						</c:if>
					</div>
					
					<!-- 랜덤 5자리 숫자 생성후 auth_code 에 등록 해서 키값을 hidden 으로 되어 있는 authid 에 넣어서 비교 처리 -->
					<button type="button" class="btn btn-outline-muted btn-block mt-3 mb-3" onclick="gocert_submit()" name="gocertbtn" >인증번호 발송</button><!--    -->
					 
					<input type="text" class="form-control" name="nocountry" id="nocountry" placeholder="외국국번"  value="<c:out value='${nocountry}'/>" hidden="true" >
					
					<input type="text" class="inputclass" placeholder="인증위한값" name="gocert" id="gocert" value='${gocert}' hidden="true" >
					<input type="text" class="inputclass" placeholder="인증확인유무" name="certyn" id="certyn" value='${certyn}' hidden="true"> 
					<input type="number" class="form-control" placeholder="인증번호 id" name="idauthcd" value="<c:out value='${idauthcd}'/>" hidden="true" > 
					<input type="number" class="form-control" placeholder="인증번호 실제키" name="cdauth" value="<c:out value='${cdauth}'/>" hidden="true" > <!-- --> 

				
				    <!-- 인증번호 입력 -->
				    <div class="int-area-form">			
				    	<div class="form-group int-area" <c:if test="${result.code eq 'C'}">has-error</c:if>>
				            <div class="ps-box">
				                <!--  남은시간 02:49 -->
				                <em><div id="lblcerttime">남은시간 03:00</div></em>
				                <input type="number" class="form-control" placeholder="인증번호 입력" name="certinum" value="<c:out value='${certinum}'/>">				                
				            </div>
				            <button type="button" class="btn-form-button" onclick="certcheck_submit()">확인</button>				            				            
				        </div>
				        <c:set var="code" value="${result.code}" /> 
						<c:choose>
						<c:when test="${code eq 'C'}">
					       <span class="error-next-box"><i>i</i>${result.description}</span>
					       </c:when>
					       <c:when test="${code eq '00'}">
					       <span class="error-next-box"><i>i</i>${result.description}</span>
					       </c:when>
					        <c:otherwise>
					        </c:otherwise>
						</c:choose>
				    </div>
				    
				    <!-- // -->
				    				    
				    <div class="form-group" <c:if test="${result.code eq 'I'}">has-error</c:if>>
				        <label class="input-chk type2 agreement">				     
				            <input type="checkbox" class="sr-only" name="chkperinfo">
				            <!--  <input type="checkbox" name="chkperinfo" class="form-control text-center font-num" onKeyup="if($(this).val()){ $('#a').addClass('btn-primary');} else {$('#a').removeClass('btn-primary');}">  -->
				            <i></i> <span><a href="agreement01">이용약관</a> , <a href="agreement02">개인정보 수집</a> , <a href="agreement03">제 3자 정보 제공 동의</a>에 모두 동의합니다.</span>
				        </label>				        
				    </div>
		            <c:if test="${result.code eq 'I'}">
					<span class="error-next-box">${result.description}</span>
					</c:if>				    
				</form>
				
			</div>
			
			<span class="error-next-box" id='errmsg1' style="display:''">${result.description}</span>
						
			
			<div class="fixed-bottom-btn">				
				<a href="javascript:submitform();" class="btn btn-lg btn-info btn-block">시작하기</a>
				<!--  <button type="button" id="a" class="btn btn-info btn-block" onclick="javascript:submitform();">시작하기</button>  -->
			</div>
			<!--// 페이지 끝 -->

		</div>
		<a href="" class="btn-top"><img class="d-block w-100"
			src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
	</div>
	

<div id="modal" class="modal modal-mycard v2">
	<div class="modal-dialog pb-0">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">나라선택</h3>
				<button type="button" class="modal-close no-style" data-dismiss="modal">팝업닫기</button>
			</div>
			<!-- 
			<div class="form-search v2">
				<input type="text" placeholder="검색">
				<button type="button" class="icon-x">입력 삭제</button>
			</div>
			 -->
			<div class="modal-body">
                <!-- 검색 결과 -->
				<!--  <span class="select-contry">대한민국 <em></em></span>  -->
				<!--  <span class="list-label">전체</span>  -->
				<div>    
                    <ul class="list-basic">						
						<!--
						<li id="lst1" onclick="listValue(this.id);" value="list 값1">list 값1</li>                     
                        <li><a href="#">그린랜드</a></li>
                        <li><a href="#">그린랜드</a></li>
                         -->
                         
                        <li id="lst1"        onclick="listValue(this.id);" value="+233"    ><a href="#">가나                       </a></li>     
						<li id="lst2"        onclick="listValue(this.id);" value="+228"    ><a href="#">가다                       </a></li>     
						<li id="lst3"        onclick="listValue(this.id);" value="+241"    ><a href="#">가봉                       </a></li>     
						<li id="lst4"        onclick="listValue(this.id);" value="+592"    ><a href="#">가이아나                   </a></li>     
						<li id="lst5"        onclick="listValue(this.id);" value="+220"    ><a href="#">감비아                     </a></li>     
						<li id="lst6"        onclick="listValue(this.id);" value="+590"    ><a href="#">과들루프                   </a></li>     
						<li id="lst7"        onclick="listValue(this.id);" value="+502"    ><a href="#">과테말라                   </a></li>     
						<li id="lst8"        onclick="listValue(this.id);" value="+379"    ><a href="#">교황청(바티칸시티)         </a></li>     
						<li id="lst9"        onclick="listValue(this.id);" value="+473"    ><a href="#">그레나다                   </a></li>     
						<li id="lst10"       onclick="listValue(this.id);" value="+995"    ><a href="#">그루지야                   </a></li>     
						<li id="lst11"       onclick="listValue(this.id);" value="+30"     ><a href="#">그리스                     </a></li>     
						<li id="lst12"       onclick="listValue(this.id);" value="+299"    ><a href="#">그린랜드                   </a></li>     
						<li id="lst13"       onclick="listValue(this.id);" value="+224"    ><a href="#">기니                       </a></li>     
						<li id="lst14"       onclick="listValue(this.id);" value="+245"    ><a href="#">기니비사우                 </a></li>     
						<li id="lst15"       onclick="listValue(this.id);" value="+264"    ><a href="#">나미비아                   </a></li>     
						<li id="lst16"       onclick="listValue(this.id);" value="+234"    ><a href="#">나이지리아                 </a></li>     
						<li id="lst17"       onclick="listValue(this.id);" value="+27"     ><a href="#">남아프리카                 </a></li>     
						<li id="lst18"       onclick="listValue(this.id);" value="+31"     ><a href="#">네덜란드                   </a></li>     
						<li id="lst19"       onclick="listValue(this.id);" value="+599"    ><a href="#">네덜란드령앤틸리스         </a></li>     
						<li id="lst20"       onclick="listValue(this.id);" value="+977"    ><a href="#">네팔                       </a></li>     
						<li id="lst21"       onclick="listValue(this.id);" value="+47"     ><a href="#">노르웨이                   </a></li>     
						<li id="lst22"       onclick="listValue(this.id);" value="+227"    ><a href="#">니제르                     </a></li>     
						<li id="lst23"       onclick="listValue(this.id);" value="+505"    ><a href="#">니카라과                   </a></li>     
						<li id="lst24"       onclick="listValue(this.id);" value="+886"    ><a href="#">대만                       </a></li>     
						<li id="lst25"       onclick="listValue(this.id);" value="+82"     ><a href="#">대한민국                   </a></li>     
						<li id="lst26"       onclick="listValue(this.id);" value="+45"     ><a href="#">덴마크                       </a></li>     
						<li id="lst27"       onclick="listValue(this.id);" value="+49"     ><a href="#">독일                         </a></li>     
						<li id="lst28"       onclick="listValue(this.id);" value="+670"    ><a href="#">동티모르                     </a></li>     
						<li id="lst29"       onclick="listValue(this.id);" value="+856"    ><a href="#">라오스                       </a></li>     
						<li id="lst30"       onclick="listValue(this.id);" value="+231"    ><a href="#">라이베리아                   </a></li>     
						<li id="lst31"       onclick="listValue(this.id);" value="+371"    ><a href="#">라트비아                     </a></li>     
						<li id="lst32"       onclick="listValue(this.id);" value="+7"      ><a href="#">러시아제국                   </a></li>     
						<li id="lst33"       onclick="listValue(this.id);" value="+961"    ><a href="#">레바논                       </a></li>     
						<li id="lst34"       onclick="listValue(this.id);" value="+266"    ><a href="#">레소토                       </a></li>     
						<li id="lst35"       onclick="listValue(this.id);" value="+40"     ><a href="#">루마니아                     </a></li>     
						<li id="lst36"       onclick="listValue(this.id);" value="+352"    ><a href="#">룩셈부르크                   </a></li>     
						<li id="lst37"       onclick="listValue(this.id);" value="+250"    ><a href="#">르완다                       </a></li>     
						<li id="lst38"       onclick="listValue(this.id);" value="+218"    ><a href="#">리비아                       </a></li>     
						<li id="lst39"       onclick="listValue(this.id);" value="+370"    ><a href="#">리투아니아                   </a></li>     
						<li id="lst40"       onclick="listValue(this.id);" value="+423"    ><a href="#">리히텐슈타인                 </a></li>     
						<li id="lst41"       onclick="listValue(this.id);" value="+261"    ><a href="#">마다가스카르                 </a></li>     
						<li id="lst42"       onclick="listValue(this.id);" value="+596"    ><a href="#">마르티니크                   </a></li>     
						<li id="lst43"       onclick="listValue(this.id);" value="+262"    ><a href="#">마요트                       </a></li>     
						<li id="lst44"       onclick="listValue(this.id);" value="+853"    ><a href="#">마카오                       </a></li>     
						<li id="lst45"       onclick="listValue(this.id);" value="+389"    ><a href="#">마케도니아                   </a></li>     
						<li id="lst46"       onclick="listValue(this.id);" value="+265"    ><a href="#">말라위                       </a></li>     
						<li id="lst47"       onclick="listValue(this.id);" value="+60"     ><a href="#">말레이시아                   </a></li>     
						<li id="lst48"       onclick="listValue(this.id);" value="+223"    ><a href="#">말리                         </a></li>     
						<li id="lst49"       onclick="listValue(this.id);" value="+52"     ><a href="#">멕시코                       </a></li>     
						<li id="lst50"       onclick="listValue(this.id);" value="+377"    ><a href="#">모나코                       </a></li>     
						<li id="lst51"       onclick="listValue(this.id);" value="+212"    ><a href="#">모로코                       </a></li>     
						<li id="lst52"       onclick="listValue(this.id);" value="+230"    ><a href="#">모리셔스                     </a></li>     
						<li id="lst53"       onclick="listValue(this.id);" value="+222"    ><a href="#">모리타니                     </a></li>     
						<li id="lst54"       onclick="listValue(this.id);" value="+258"    ><a href="#">모잠비크                     </a></li>     
						<li id="lst55"       onclick="listValue(this.id);" value="+382"    ><a href="#">몬테네그로                   </a></li>     
						<li id="lst56"       onclick="listValue(this.id);" value="+960"    ><a href="#">몰디브                       </a></li>     
						<li id="lst57"       onclick="listValue(this.id);" value="+373"    ><a href="#">몰르더바                     </a></li>     
						<li id="lst58"       onclick="listValue(this.id);" value="+356"    ><a href="#">몰타                         </a></li>     
						<li id="lst59"       onclick="listValue(this.id);" value="+976"    ><a href="#">몽골리아                     </a></li>     
						<li id="lst60"       onclick="listValue(this.id);" value="+1"      ><a href="#">미국                         </a></li>     
						<li id="lst61"       onclick="listValue(this.id);" value="+95"     ><a href="#">미얀마(버마)                 </a></li>     
						<li id="lst62"       onclick="listValue(this.id);" value="+973"    ><a href="#">바레인                       </a></li>     
						<li id="lst63"       onclick="listValue(this.id);" value="+880"    ><a href="#">방글라데시                   </a></li>     
						<li id="lst64"       onclick="listValue(this.id);" value="+229"    ><a href="#">베냉                         </a></li>     
						<li id="lst65"       onclick="listValue(this.id);" value="+58"     ><a href="#">베네수엘라                   </a></li>     
						<li id="lst66"       onclick="listValue(this.id);" value="+84"     ><a href="#">베트남                       </a></li>     
						<li id="lst67"       onclick="listValue(this.id);" value="+32"     ><a href="#">벨기에                       </a></li>     
						<li id="lst68"       onclick="listValue(this.id);" value="+375"    ><a href="#">벨라루스                     </a></li>     
						<li id="lst69"       onclick="listValue(this.id);" value="+501"    ><a href="#">벨리즈                       </a></li>     
						<li id="lst70"       onclick="listValue(this.id);" value="+387"    ><a href="#">보스니아헤르체고비나         </a></li>     
						<li id="lst71"       onclick="listValue(this.id);" value="+267"    ><a href="#">보츠와나                     </a></li>     
						<li id="lst72"       onclick="listValue(this.id);" value="+591"    ><a href="#">볼리비아                     </a></li>     
						<li id="lst73"       onclick="listValue(this.id);" value="+257"    ><a href="#">부룬디                       </a></li>     
						<li id="lst74"       onclick="listValue(this.id);" value="+226"    ><a href="#">부르키나파소                 </a></li>     
						<li id="lst75"       onclick="listValue(this.id);" value="+975"    ><a href="#">부탄                         </a></li>     
						<li id="lst76"       onclick="listValue(this.id);" value="+359"    ><a href="#">불가리아                     </a></li>     
						<li id="lst77"       onclick="listValue(this.id);" value="+55"     ><a href="#">브라질                       </a></li>     
						<li id="lst78"       onclick="listValue(this.id);" value="+673"    ><a href="#">브루나이                     </a></li>     
						<li id="lst79"       onclick="listValue(this.id);" value="+966"    ><a href="#">사우디아라비아               </a></li>     
						<li id="lst80"       onclick="listValue(this.id);" value="+378"    ><a href="#">산마리노                     </a></li>     
						<li id="lst81"       onclick="listValue(this.id);" value="+239"    ><a href="#">상투메프린시페               </a></li>     
						<li id="lst82"       onclick="listValue(this.id);" value="+508"    ><a href="#">생피에르미클롱               </a></li>     
						<li id="lst83"       onclick="listValue(this.id);" value="+212"    ><a href="#">서부사하라                   </a></li>     
						<li id="lst84"       onclick="listValue(this.id);" value="+221"    ><a href="#">세네갈                       </a></li>     
						<li id="lst85"       onclick="listValue(this.id);" value="+381"    ><a href="#">세르비아                     </a></li>     
						<li id="lst86"       onclick="listValue(this.id);" value="+248"    ><a href="#">세이셸                       </a></li>     
						<li id="lst87"       onclick="listValue(this.id);" value="+590"    ><a href="#">세인트Barthelemy             </a></li>     
						<li id="lst88"       onclick="listValue(this.id);" value="+590"    ><a href="#">세인트마틴                   </a></li>     
						<li id="lst89"       onclick="listValue(this.id);" value="+290"    ><a href="#">세인트헬레나                 </a></li>     
						<li id="lst90"       onclick="listValue(this.id);" value="+252"    ><a href="#">소말리아                     </a></li>     
						<li id="lst91"       onclick="listValue(this.id);" value="+249"    ><a href="#">수단                         </a></li>     
						<li id="lst92"       onclick="listValue(this.id);" value="+597"    ><a href="#">수리남                       </a></li>     
						<li id="lst93"       onclick="listValue(this.id);" value="+94"     ><a href="#">스리랑카                     </a></li>     
						<li id="lst94"       onclick="listValue(this.id);" value="+47"     ><a href="#">스발바르                     </a></li>     
						<li id="lst95"       onclick="listValue(this.id);" value="+268"    ><a href="#">스와질란드                   </a></li>     
						<li id="lst96"       onclick="listValue(this.id);" value="+46"     ><a href="#">스웨덴                       </a></li>     
						<li id="lst97"       onclick="listValue(this.id);" value="+41"     ><a href="#">스위스                       </a></li>     
						<li id="lst98"       onclick="listValue(this.id);" value="+34"     ><a href="#">스페인                       </a></li>     
						<li id="lst99"       onclick="listValue(this.id);" value="+421"    ><a href="#">슬로바키아                   </a></li>     
						<li id="lst100"      onclick="listValue(this.id);" value="+386"    ><a href="#">슬로베니아                   </a></li>     
						<li id="lst101"      onclick="listValue(this.id);" value="+963"    ><a href="#">시리아                       </a></li>     
						<li id="lst102"      onclick="listValue(this.id);" value="+232"    ><a href="#">시에라리온                   </a></li>     
						<li id="lst103"      onclick="listValue(this.id);" value="+65"     ><a href="#">싱가포르                     </a></li>     
						<li id="lst104"      onclick="listValue(this.id);" value="+971"    ><a href="#">아랍에미리트                 </a></li>     
						<li id="lst105"      onclick="listValue(this.id);" value="+297"    ><a href="#">아루바                       </a></li>     
						<li id="lst106"      onclick="listValue(this.id);" value="+374"    ><a href="#">아르메니아                   </a></li>     
						<li id="lst107"      onclick="listValue(this.id);" value="+54"     ><a href="#">아르헨티나                   </a></li>     
						<li id="lst108"      onclick="listValue(this.id);" value="+354"    ><a href="#">아이슬란드                   </a></li>     
						<li id="lst109"      onclick="listValue(this.id);" value="+509"    ><a href="#">아이티                       </a></li>     
						<li id="lst110"      onclick="listValue(this.id);" value="+353"    ><a href="#">아일랜드                     </a></li>     
						<li id="lst111"      onclick="listValue(this.id);" value="+44"     ><a href="#">아일오브맨                   </a></li>     
						<li id="lst112"      onclick="listValue(this.id);" value="+994"    ><a href="#">아제르바이잔                 </a></li>     
						<li id="lst113"      onclick="listValue(this.id);" value="+93"     ><a href="#">아프가니스탄                 </a></li>     
						<li id="lst114"      onclick="listValue(this.id);" value="+376"    ><a href="#">안도라                       </a></li>     
						<li id="lst115"      onclick="listValue(this.id);" value="+355"    ><a href="#">알바니아                     </a></li>     
						<li id="lst116"      onclick="listValue(this.id);" value="+213"    ><a href="#">알제리                       </a></li>     
						<li id="lst117"      onclick="listValue(this.id);" value="+244"    ><a href="#">앙골라                       </a></li>     
						<li id="lst118"      onclick="listValue(this.id);" value="+47"     ><a href="#">얀마웬                       </a></li>     
						<li id="lst119"      onclick="listValue(this.id);" value="+291"    ><a href="#">에리트레아                   </a></li>     
						<li id="lst120"      onclick="listValue(this.id);" value="+372"    ><a href="#">에스토니아                   </a></li>     
						<li id="lst121"      onclick="listValue(this.id);" value="+593"    ><a href="#">에콰도르                     </a></li>     
						<li id="lst122"      onclick="listValue(this.id);" value="+251"    ><a href="#">에티오피아                   </a></li>     
						<li id="lst123"      onclick="listValue(this.id);" value="+503"    ><a href="#">엘살바도르                   </a></li>     
						<li id="lst124"      onclick="listValue(this.id);" value="+44"     ><a href="#">연합왕국                     </a></li>     
						<li id="lst125"      onclick="listValue(this.id);" value="+44"     ><a href="#">영국해협에있는작은섬         </a></li>     
						<li id="lst126"      onclick="listValue(this.id);" value="+967"    ><a href="#">예멘아랍공화국               </a></li>     
						<li id="lst127"      onclick="listValue(this.id);" value="+968"    ><a href="#">오만                         </a></li>     
						<li id="lst128"      onclick="listValue(this.id);" value="+43"     ><a href="#">오스트리아                   </a></li>     
						<li id="lst129"      onclick="listValue(this.id);" value="+504"    ><a href="#">온두라스                     </a></li>     
						<li id="lst130"      onclick="listValue(this.id);" value="+962"    ><a href="#">요르단                       </a></li>     
						<li id="lst131"      onclick="listValue(this.id);" value="+256"    ><a href="#">우간다                       </a></li>     
						<li id="lst132"      onclick="listValue(this.id);" value="+598"    ><a href="#">우루과이                     </a></li>     
						<li id="lst133"      onclick="listValue(this.id);" value="+998"    ><a href="#">우즈베키스탄                 </a></li>     
						<li id="lst134"      onclick="listValue(this.id);" value="+380"    ><a href="#">우크라이나                   </a></li>     
						<li id="lst135"      onclick="listValue(this.id);" value="+964"    ><a href="#">이라크                       </a></li>     
						<li id="lst136"      onclick="listValue(this.id);" value="+98"     ><a href="#">이란                         </a></li>     
						<li id="lst137"      onclick="listValue(this.id);" value="+972"    ><a href="#">이스라엘                     </a></li>     
						<li id="lst138"      onclick="listValue(this.id);" value="+20"     ><a href="#">이집트                       </a></li>     
						<li id="lst139"      onclick="listValue(this.id);" value="+39"     ><a href="#">이탈리아                     </a></li>     
						<li id="lst140"      onclick="listValue(this.id);" value="+91"     ><a href="#">인도                         </a></li>     
						<li id="lst141"      onclick="listValue(this.id);" value="+62"     ><a href="#">인도네시아공화국             </a></li>     
						<li id="lst142"      onclick="listValue(this.id);" value="+81"     ><a href="#">일본                         </a></li>     
						<li id="lst143"      onclick="listValue(this.id);" value="+1876"   ><a href="#">자메이카                     </a></li>     
						<li id="lst144"      onclick="listValue(this.id);" value="+260"    ><a href="#">잠비아                       </a></li>     
						<li id="lst145"      onclick="listValue(this.id);" value="+262"    ><a href="#">재회                         </a></li>     
						<li id="lst146"      onclick="listValue(this.id);" value="+44"     ><a href="#">저지                         </a></li>     
						<li id="lst147"      onclick="listValue(this.id);" value="+240"    ><a href="#">적도기니                     </a></li>     
						<li id="lst148"      onclick="listValue(this.id);" value="+86"     ><a href="#">중국                         </a></li>     
						<li id="lst149"      onclick="listValue(this.id);" value="+236"    ><a href="#">중앙아프리카공화국           </a></li>     
						<li id="lst150"      onclick="listValue(this.id);" value="+253"    ><a href="#">지부티                       </a></li>     
						<li id="lst151"      onclick="listValue(this.id);" value="+350"    ><a href="#">지브롤터                     </a></li>     
						<li id="lst152"      onclick="listValue(this.id);" value="+263"    ><a href="#">짐바브웨                     </a></li>     
						<li id="lst153"      onclick="listValue(this.id);" value="+235"    ><a href="#">차드                         </a></li>     
						<li id="lst154"      onclick="listValue(this.id);" value="+420"    ><a href="#">체코공화국                   </a></li>     
						<li id="lst155"      onclick="listValue(this.id);" value="+56"     ><a href="#">칠레                         </a></li>     
						<li id="lst156"      onclick="listValue(this.id);" value="+237"    ><a href="#">카메룬                       </a></li>     
						<li id="lst157"      onclick="listValue(this.id);" value="+238"    ><a href="#">카보베르데                   </a></li>     
						<li id="lst158"      onclick="listValue(this.id);" value="+76"     ><a href="#">카자흐스탄                   </a></li>     
						<li id="lst159"      onclick="listValue(this.id);" value="+974"    ><a href="#">카타르                       </a></li>     
						<li id="lst160"      onclick="listValue(this.id);" value="+855"    ><a href="#">캄보디아                     </a></li>     
						<li id="lst161"      onclick="listValue(this.id);" value="+1"      ><a href="#">캐나다                       </a></li>     
						<li id="lst162"      onclick="listValue(this.id);" value="+254"    ><a href="#">케냐                         </a></li>     
						<li id="lst163"      onclick="listValue(this.id);" value="+269"    ><a href="#">코모로                       </a></li>     
						<li id="lst164"      onclick="listValue(this.id);" value="+383"    ><a href="#">코소보                       </a></li>     
						<li id="lst165"      onclick="listValue(this.id);" value="+506"    ><a href="#">코스타리카                   </a></li>     
						<li id="lst166"      onclick="listValue(this.id);" value="+618"    ><a href="#">코코스아일랜드               </a></li>     
						<li id="lst167"      onclick="listValue(this.id);" value="+225"    ><a href="#">코트디부아르                 </a></li>     
						<li id="lst168"      onclick="listValue(this.id);" value="+57"     ><a href="#">콜롬비아                     </a></li>     
						<li id="lst169"      onclick="listValue(this.id);" value="+242"    ><a href="#">콩고공화국                   </a></li>     
						<li id="lst170"      onclick="listValue(this.id);" value="+243"    ><a href="#">콩고민주공화국               </a></li>     
						<li id="lst171"      onclick="listValue(this.id);" value="+53"     ><a href="#">쿠바                         </a></li>     
						<li id="lst172"      onclick="listValue(this.id);" value="+965"    ><a href="#">쿠웨이트                     </a></li>     
						<li id="lst173"      onclick="listValue(this.id);" value="+385"    ><a href="#">크로아티아                   </a></li>     
						<li id="lst174"      onclick="listValue(this.id);" value="+618"    ><a href="#">크리스마스섬                 </a></li>     
						<li id="lst175"      onclick="listValue(this.id);" value="+996"    ><a href="#">키르기스스탄                 </a></li>     
						<li id="lst176"      onclick="listValue(this.id);" value="+357"    ><a href="#">키프로스                     </a></li>     
						<li id="lst177"      onclick="listValue(this.id);" value="+992"    ><a href="#">타지키스탄                   </a></li>     
						<li id="lst178"      onclick="listValue(this.id);" value="+255"    ><a href="#">탄자니아                     </a></li>     
						<li id="lst179"      onclick="listValue(this.id);" value="+66"     ><a href="#">태국                         </a></li>     
						<li id="lst180"      onclick="listValue(this.id);" value="+649"    ><a href="#">터크스케이커스제도           </a></li>     
						<li id="lst181"      onclick="listValue(this.id);" value="+90"     ><a href="#">터키                         </a></li>     
						<li id="lst182"      onclick="listValue(this.id);" value="+993"    ><a href="#">투르크메니스탄               </a></li>     
						<li id="lst183"      onclick="listValue(this.id);" value="+216"    ><a href="#">튀니지                       </a></li>     
						<li id="lst184"      onclick="listValue(this.id);" value="+507"    ><a href="#">파나마                       </a></li>     
						<li id="lst185"      onclick="listValue(this.id);" value="+595"    ><a href="#">파라과이                     </a></li>     
						<li id="lst186"      onclick="listValue(this.id);" value="+298"    ><a href="#">파로에섬                     </a></li>     
						<li id="lst187"      onclick="listValue(this.id);" value="+92"     ><a href="#">파키스탄                     </a></li>     
						<li id="lst188"      onclick="listValue(this.id);" value="+675"    ><a href="#">파푸아뉴기니                 </a></li>     
						<li id="lst189"      onclick="listValue(this.id);" value="+680"    ><a href="#">팔라우                       </a></li>     
						<li id="lst190"      onclick="listValue(this.id);" value="+970"    ><a href="#">팔레스타인지역               </a></li>     
						<li id="lst191"      onclick="listValue(this.id);" value="+51"     ><a href="#">페루                         </a></li>     
						<li id="lst192"      onclick="listValue(this.id);" value="+351"    ><a href="#">포르투갈                     </a></li>     
						<li id="lst193"      onclick="listValue(this.id);" value="+500"    ><a href="#">포클랜드제도                 </a></li>     
						<li id="lst194"      onclick="listValue(this.id);" value="+48"     ><a href="#">폴란드                       </a></li>     
						<li id="lst195"      onclick="listValue(this.id);" value="+33"     ><a href="#">프랑스                       </a></li>     
						<li id="lst196"      onclick="listValue(this.id);" value="+594"    ><a href="#">프랑스어령기아나             </a></li>     
						<li id="lst197"      onclick="listValue(this.id);" value="+679"    ><a href="#">피지                         </a></li>     
						<li id="lst198"      onclick="listValue(this.id);" value="+358"    ><a href="#">핀란드                       </a></li>     	
						<li id="lst199"      onclick="listValue(this.id);" value="+63"     ><a href="#">필리핀제도                   </a></li>     
						<li id="lst200"      onclick="listValue(this.id);" value="+850"    ><a href="#">한국(북한)                   </a></li>     
						<li id="lst201"      onclick="listValue(this.id);" value="+36"     ><a href="#">헝가리                       </a></li>     
						<li id="lst202"      onclick="listValue(this.id);" value="+852"    ><a href="#">홍콩                         </a></li>     
                    </ul>
                </div>
                <!-- // -->
			</div>
		</div>
	</div>
</div>
<script>
	// $('#modal1').modal();
</script>

	
</body>
</html>