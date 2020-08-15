<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ page import="java.util.*,java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.Calendar"%>


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
<!--  <script src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.2.js" type="text/javascript"></script>  -->
<script src="../../static/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="../../static/js/popper.min.js" type="text/javascript"></script>
<script src="../../static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../static/js/swiper.min.js" type="text/javascript"></script>
<script src="../../static/js/common.js" type="text/javascript"></script>
<script src="../../static/js/mywed/edit.js" type="text/javascript"></script>



<script type="text/javascript">



$(document).ready(function() {

	
});

function popModal(){
	$('#modal1').modal();
	
}

		
		
function func_del(){
	
	document.getElementById("schnmWeddingHall").value = "";

	// ul 초기화 
	var clul = document.getElementById("ul_dynaList");
	clul.innerHTML = "";
}

		
function SechList(sVal){

	// ul 초기화 
	var clul = document.getElementById("ul_dynaList");
	clul.innerHTML = "";
	
	var val = sVal.replace("'", "");
	
	if(val=="") {	                

		return;

	}
	
	
	var url="/mywed/ajaxtest";
	
	var ajax = $.ajax({ 
		url : url
		, type: "get"
		, daataType : "jason"
		, data : { "schnmWeddingHall" : val }
		, beforeSend:function(response){
			
		}	
		, success : function(responseData){
			
			var data = JSON.parse(responseData);
			
			document.getElementById("welcome").value = "Success OK";
			
			if(!data){
				// alert("존재하지 않는 ID입니다"); 
				document.getElementById("welcome").value = "X";
				return false;
			} 
			
			// ul 초기화 
			var ul = document.getElementById("ul_dynaList");
			ul.innerHTML = "";
			
			var li = document.createElement("li");
		    var children = ul.children.length + 1;
			
			
			if ( data.length == 0 ){
				
				
				var div = document.getElementById("div_List");
				div.innerHTML = "";
				
				
				strul =  "<div class='search-nodata'><img class='center-block' src='../../static/img/icon/ico-nosearch.png' height='48' alt=''>";
				strul =  strul + "<p class='txt'>검색 결과가 없습니다.</p>";
				strul =  strul + "<strong class='text-primary h6'>직접 입력 </strong> : '" + "<span id='0' value='" + val + "' onclick='listGetValue(this.id);'>"+ val + "'</span></div>";
				$("#ul_dynaList").append(strul);
				
			}
			else {
				
				// 내역 조회
				
				var html = '';
				
			    // 세팅항목 예
			    // li.setAttribute("id", "element"+children);
			    // li.appendChild(document.createTextNode("Element "+children)); // 값 세팅
			    // ul.appendChild(li);
				
				document.getElementById("welcome").value = "setting";
				
				
				if(data.length != 0){
	                
	                document.getElementById("welcome").value = data.length + ' 건';
	            };

				
	            var strul =  "";
				$.each(data, function(index, item){

					strul =  "";
					strul =  strul + "<li> <p class='h6'><span id='"+ item.idWeddingHall + "' value='" + item.nmWeddingHall + "' onclick='listGetValue(this.id);'>"+ item.nmWeddingHall + "</span></p><p class='text-muted mt-2'> " + item.dsAddr + "</p></li>";
					 
					$("#ul_dynaList").append(strul);
					
				})
				
				
				// 최종건수
				strul =  "";
				strul =  "<li><p class='h6'> 총 " + data.length + " 건</p><p class='text-muted mt-2'></p></li>";
				$("#ul_dynaList").append(strul);				
				
				
			}
		}
		
		,complete: function(response){
			
			// var ul = document.getElementById("ul_dynaList");
			// ul.innerHTML = "";	
		}
		, error:function(e) {
			// var ul = document.getElementById("ul_dynaList");
			// ul.innerHTML = "";			
		}
		, fail: function(){
			//오류발생시
			// var ul = document.getElementById("ul_dynaList");
			// ul.innerHTML = "";
		}
	});

	
	// ajax 실행문
	jQuery.ajax.done; // (function(data){})
	 
}



// 팝업 선택값 가져오기
function listGetValue(lst){
	
	
	var rtnId = "";
	
	if (lst == null) { rtnId = "0"; }
	else { rtnId = String(lst); }
		
	var rtnVal = (document.getElementById(lst).innerText).replace("'","");
	
    document.getElementById("idWeddingHall").value = String(rtnId);
    document.getElementById("nmWeddingHall").value = rtnVal;
	
	$('#modal1').modal('toggle');
	
}



//<!--   --------------------------------  -->


	
// <!--   --------------------------------  -->

function pad(n, width) {
	  n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}
	

var today = new Date(); // 오늘 날짜
var date = new Date();

function beforem() //이전 달을 today에 값을 저장
{ 
	today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
		build(); //만들기
}
   
function nextm()  //다음 달을 today에 저장
{
	today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
    build();
}
   
function build()
{
	var nMonth = new Date(today.getFullYear(), today.getMonth(), 1); //현재달의 첫째 날
	var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0); //현재 달의 마지막 날
	var tbcal = document.getElementById("calendar"); // 테이블 달력을 만들 테이블
	var yearmonth = document.getElementById("yearmonth"); //  년도와 월 출력할곳
	//yearmonth.innerHTML = today.getFullYear() + "년 "+ pad((today.getMonth() + 1), 2) + "월"; //년도와 월 출력
	yearmonth.innerHTML = today.getFullYear() + "."+ pad((today.getMonth() + 1), 2) + ""; //년도와 월 출력
       
	if(today.getMonth()+1==12) //  눌렀을 때 월이 넘어가는 곳
	{
		before.innerHTML=(today.getMonth())+"월";
		next.innerHTML="1월";
	}
	else if(today.getMonth()+1==1) //  1월 일 때
    {
		before.innerHTML="12월";
		next.innerHTML=(today.getMonth()+2)+"월";
	}
	else //   12월 일 때
	{
		before.innerHTML=(today.getMonth())+"월";
		next.innerHTML=(today.getMonth()+2)+"월";
	}
       
      
    
	// 남은 테이블 줄 삭제
	while (tbcal.rows.length > 2) 
	{
	    tbcal.deleteRow(tbcal.rows.length - 1);
	}
	var row = null;
	row = tbcal.insertRow();
	var cnt = 0;
	var rcnt = 0; // 줄 수가 6개가 아니면 추가를 위한 변수   
	
	// 1일 시작칸 찾기
	for (i = 0; i < nMonth.getDay(); i++) 
	{
	    cell = row.insertCell();
	    cnt = cnt + 1;
	    rcnt = rcnt + 1;
	}
	
	// 달력 출력
	for (i = 1; i <= lastDate.getDate(); i++) // 1일부터 마지막 일까지
	{ 
		cell = row.insertCell();
		cell.innerHTML = "<span id='"+ pad(i, 2) + "' onclick='ClickValue(this.id);' value='"+ pad(i, 2) + "'><a class='ui-state-default' href='#' >"+i+"</a></span>";

		cnt = cnt + 1;
		if (cnt % 7 == 1) {//일요일 계산
		    // cell.innerHTML = "<font color=#FF9090>" + i//일요일에 색
		    cell.innerHTML = "<span id='"+ pad(i, 2) + "' onclick='ClickValue(this.id);' value='"+ pad(i, 2) + "'><a class='ui-state-default' href='#' > <font color=#FF9090>"+i+"</font></a></span>";
		    
		}
		if (cnt % 7 == 0) { // 1주일이 7일 이므로 토요일 계산
		    // cell.innerHTML = "<font color=#7ED5E4>" + i//토요일에 색
		    cell.innerHTML = "<span id='"+ pad(i, 2) + "' onclick='ClickValue(this.id);' value='"+ pad(i, 2) + "'><a class='ui-state-default' href='#' > <font color=#7ED5E4>"+i+"</font></a></span>";
		    row = calendar.insertRow();// 줄 추가
		    rcnt = rcnt + 1;
		}
		if(today.getFullYear()==date.getFullYear()&&today.getMonth()==date.getMonth()&&i==date.getDate()) 
		{
		    cell.bgColor = "#BCF1B1"; //오늘날짜배경색
		}
	}

	// 달력 위치 맞추기 위한 추가 처리 
	/*
	if (rcnt < 6) {
		cell.innerHTML = "<span id='"+ pad(i+1, 2) + "' onclick='ClickValue(this.id);' value='"+ pad(i+1, 2) + "'><a class='ui-state-default' href='#' >"+"</a></span>";
		row = calendar.insertRow();// 줄 추가
		cell = row.insertCell();
		
		$('#nmPartner').val( rcnt );
		
	}
	*/
	
	
}


// <!--  --------------------------------------  -->


</script>


<spring:url var="authUrl" value="/mywed/edit" />
<!-- <spring:url var="authUrl1" value="/mywed / SchweddHall" /> --> <!--  weddingHallOk -->
</head>


<body onload="build();">



<div id="wrap">
	<header id="hd">
		<div class="container">
			<a href="javascript:history.back();" class="icon-back">뒤로가기</a>
		</div>
	</header>
	<!-- gnb -->
	<nav id="gnb">
		<ul class="gnb">
			<li class="active"><a class="mn01" href="mywed/myWedding.jsp">나의 웨딩</a></li>
			<li><a class="mn02" href="package/packageList.jsp">패키지</a></li>
			<li><a class="mn03" href="">혜택</a></li>
			<li><a class="mn04" href="">매거진</a></li>
			<li><a class="mn05" href="">더보기</a></li>
		</ul>
	</nav>
	

	<!-- container -->
	<div id="ct">
		<!-- 페이지 시작 -->
		<div class="container">
			<!-- 
			<form class="wedHall-form" id="wedHallForm" name="wedHallForm" method="post" action="${authUrl1}">	
				
				<input type="text" class="form-control" placeholder="사용자id(email)" name="email" value="<c:out value='${email}'/>" hidden="true">
				
				<input type="text" class="form-control" placeholder="신부이름" name="nmPartner1" value="<c:out value='${nmPartner1}'/>"  hidden="true">
				<input type="text" class="form-control" placeholder="신랑이름" name="nmuser1" value="<c:out value='${nmuser1}'/>" hidden="true">				
				<input type="text" class="form-control" placeholder="웨딩홀명" name="nmWeddingHall1" value="<c:out value='${nmWeddingHall1}'/>" hidden="true">
				<input type="text" class="form-control" placeholder="날짜 선택" name="weddate1" value="<c:out value='${weddate1}'/>" hidden="true">
				<input type="text" class="form-control" placeholder="시간선택" name="wedtime1" value="<c:out value='${wedtime1}'/>" hidden="true">
				
			
										
			</form>
			
				 --> 
						
			<form class="edit-form" id="editForm" name="editForm" method="post" action="${authUrl}">	
		
		
				<input type="text" class="form-control" placeholder="사용자id(email)" name="email" id="email" value="<c:out value='${email}'/>" hidden="true">
				<input type="text" class="inputclass" placeholder="인증위한값" name="gogo" id="gogo" value='' hidden="true" >
				<input type="text" class="form-control" placeholder="NextPrc" name="NextPrc"    value="<c:out value='${NextPrc}'/>" hidden="true"> <!-- 현재 페이지 등록처리 -->
				 
				
				
				<h1 class="display-2">예식 정보를 입력해주세요.</h1>
				<div class="form-group">
					<p class="form-label">신부님 이름을 입력해주세요.</p>
					<div class="form-group <c:if test="${fn:contains(result.code, 'E41')}">has-error</c:if>">
						<input type="text" class="form-control" placeholder="신부이름" name="nmWoman" id="nmWoman" value="<c:out value='${nmWoman}'/>" >
						<c:if test="${fn:contains(result.code, 'E41')}">
						<span class="error-next-box">${result.description}</span>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<p class="form-label">신랑님 이름을 입력해주세요.</p>
					<div class="form-group <c:if test="${fn:contains(result.code, 'E42')}">has-error</c:if>">						
						<input type="text" class="form-control" placeholder="신랑이름" name="nmMan" id="nmMan" value="<c:out value='${nmMan}'/>" >
						<c:if test="${fn:contains(result.code, 'E42')}">
						<span class="error-next-box">${result.description}</span>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<p class="form-label">계약하신 웨딩홀명을 입력해주세요.</p>
					
					<div><input type="text" id="idWeddingHall" name="idWeddingHall" width="7" value="<c:out value='${idWeddingHall}'/>" hidden="true"></div>
					
					<div class="form-group <c:if test="${fn:contains(result.code, 'E43')}">has-error</c:if>">						
						<input type="text" class="form-control" placeholder="웨딩홀명" name="nmWeddingHall" id="nmWeddingHall" onclick="popModal();" value="<c:out value='${nmWeddingHall}'/>" >
						<c:if test="${fn:contains(result.code, 'E43')}">
						<span class="error-next-box">${result.description}</span>
						</c:if>
					</div>
					
				</div>
				<div class="form-group">
					<p class="form-label">예식 날짜를 선택해주세요.</p>
					<div class="input-date-wrp">
					
					<div class="form-group <c:if test="${fn:contains(result.code, 'E44')}">has-error</c:if>">
		
						<input type="text" class="form-control input-date input-icon-pos-right on" placeholder="날짜 선택" name="weddate" id="weddate" value="<c:out value='${weddate}'/>" >
						<div id="ui-datepicker-div" class="ui-datepicker" id="cal_div">
						
							 	
							<table id="calendar"  class="ui-datepicker-calendar" >
							    <thead>
						        <tr>
						            <td><font size=1%; color="#B3B6B3"><label onclick="beforem()" id="before" ></label></font></td>
						            <td colspan="5" align="center" ><span class="ui-datepicker-month" id="yearmonth" ><strong>2020. 01</strong></span></td>
						            <td><font size=1%; color="#B3B6B3"><label onclick="nextm()" id="next"></label></font></td>
						        </tr>
						        <tr>
						            
									<th scope="col">
										<span title="일요일"><font color="#FF9090">일</font></span>
									</th>
									<th scope="col">
										<span title="월요일">월</span>
									</th>
									<th scope="col">
										<span title="화요일">화</span>
									</th>
									<th scope="col">
										<span title="수요일">수</span>
									</th>
									<th scope="col">
										<span title="목요일">목</span>
									</th>
									<th scope="col">
										<span title="금요일">금</span>
									</th>
									<th scope="col">
										<span title="토요일"><font color=#7ED5E4>토</font></span>
									</th>
															            
						        </tr>
						        </thead>
						    </table>
						    					    
						</div>
					
				
					<c:if test="${fn:contains(result.code, 'E44')}">
					<span class="error-next-box">${result.description}</span>
					</c:if>
					</div>
				</div>
										
				</div>
				<div class="form-group">
		
					<div class="form-group <c:if test="${fn:contains(result.code, 'E45')}">has-error</c:if>">
		
					<p class="form-label">예식 시간을 입력해주세요.</p>
					<div class="input-date-wrp">
						<select class="form-control custom-select input-clock input-icon-pos-right on" name="wedtime" id="wedtime" >
							<option value="">시간 선택</option>
							<option value="0000">00:00</option>
							<option value="0030">00:30</option>
							<option value="0100">01:00</option>
							<option value="0130">01:30</option>
							<option value="0200">02:00</option>
							<option value="0230">02:30</option>
							<option value="0300">03:00</option>
							<option value="0330">03:30</option>
							<option value="0400">04:00</option>
							<option value="0430">04:30</option>
							<option value="0500">05:00</option>
							<option value="0530">05:30</option>
							<option value="0600">06:00</option>
							<option value="0630">06:30</option>
							<option value="0700">07:00</option>
							<option value="0730">07:30</option>
							<option value="0800">08:00</option>
							<option value="0830">08:30</option>
							<option value="0900">09:00</option>
							<option value="0930">09:30</option>
							<option value="1000">10:00</option>
							<option value="1030">10:30</option>
							<option value="1100">11:00</option>
							<option value="1130">11:30</option>
							<option value="1200">12:00</option>
							<option value="1230">12:30</option>
							<option value="1300">13:00</option>
							<option value="1330">13:30</option>
							<option value="1400">14:00</option>
							<option value="1430">14:30</option>
							<option value="1500">15:00</option>
							<option value="1530">15:30</option>
							<option value="1600">16:00</option>
							<option value="1630">16:30</option>
							<option value="1700">17:00</option>
							<option value="1730">17:30</option>
							<option value="1800">18:00</option>
							<option value="1830">18:30</option>
							<option value="1900">19:00</option>
							<option value="1930">19:30</option>
							<option value="2000">20:00</option>
							<option value="2030">20:30</option>
							<option value="2100">21:00</option>
							<option value="2130">21:30</option>
							<option value="2200">22:00</option>
							<option value="2230">22:30</option>
							<option value="2300">23:00</option>
							<option value="2330">23:30</option>
							<option value="2400">24:00</option>							
						</select>
					</div>
					<script type="text/javascript">

						$(document).ready(function() {
	
							$('#wedTime').val('<c:out value='${wedTime}'/>').prop("selected",true);
						});
										
					</script>
					
					<c:if test="${fn:contains(result.code, 'E45')}">
					<span class="error-next-box">${result.description}</span>
					</c:if>
					</div>
					
				</div>
			
			
			</form>	
		</div>
		
		
		
		<div class="fixed-bottom-btn">			
			<a href="javascript:submitform();" class="btn btn-lg btn-dark btn-block">입력완료</a>
		</div>
	</div>
</div>




<div id="modal1" class="modal modal-mycard">
<div class="modal-dialog pb-0">
	<div class="modal-content">
		<div class="modal-header">
			<div class="form-search">
				<button type="button" class="modal-close no-style" data-dismiss="modal">팝업닫기</button>
				<input type="text" placeholder="업체명을 검색해 보세요" name="schnmWeddingHall" id="schnmWeddingHall" value="" onkeyup="SechList(this.value)" >
				<button type="button" class="icon-x" onclick="func_del();">입력 삭제</button>
			</div>
		</div>
		<div>
		<span> &nbsp;<br>&nbsp;</span>
		</div>
		
		
		<div class="modal-body">
			
			<div><input type="text" id="welcome" width="200" hidden="true"></div>				
			
			<div id="div_List"></div>
			<ul class="list-border mt-2" id="ul_dynaList">		
			</ul>
			
						
		</div>
	</div>
</div>
</div>

	
<script>
$(function(){
	var inputDateWrap = $('.input-date-wrp');
	var uiDatepicker = $('#ui-datepicker-div');
	$('.input-date').click(function(){
		uiDatepicker.show();
		$('#wrap').addClass('datepicker-open');
	});
	
	$(document).click(function (e) {
	    e.stopPropagation();
	    if (inputDateWrap.has(e.target).length === 0) {
	        uiDatepicker.hide();
			$('#wrap').removeClass('datepicker-open');
	    }
	});
});





</script>
	    
	
	<!--// 페이지 끝 -->
	<!-- 
	</div>
		<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>
	</div>
	 -->
	
	</body>
</html>