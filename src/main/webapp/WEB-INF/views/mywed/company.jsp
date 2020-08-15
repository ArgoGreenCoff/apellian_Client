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
<script src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.2.js" type="text/javascript"></script>
<script src="../../static/js/popper.min.js" type="text/javascript"></script>
<!--   <script src="../../static/js/bootstrap.min.js" type="text/javascript"></script> -->
<script src="../../static/js/swiper.min.js" type="text/javascript"></script>
<script src="../../static/js/common.js" type="text/javascript"></script>
<script src="../../static/js/mywed/company.js" type="text/javascript"></script>

<script type="text/javascript">


$(document).ready(function() {

	
});


function func_del(){
	
	document.getElementById("schNmCompany").value = "";
	
	// ul 초기화 
	var clul = document.getElementById("ul_dynaList");
	clul.innerHTML = "";
	
}

		
function SchList(sVal){
	
	// ul 초기화 
	var clul = document.getElementById("ul_dynaList");
	clul.innerHTML = "";

	var val = sVal.replace("'", "");
	
	if(val=="") {	                
		
		return;

	}
	
	
	var url="/mywed/ajaxCompany";
	
	var ajax = $.ajax({ 
		url : url
		, type: "get"
		, daataType : "jason"
		// , contentType: 'application/json; charset=UTF-8'    // , contentType: 'application/x-www-form-urlencoded; charset=euc-kr'
		, data : { "schNmCompany" : val }
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
				
				// 회사가 없을 경우 처리				
				strul =  "<div class='search-nodata'><img class='center-block' src='../../static/img/icon/ico-nosearch.png' height='48' alt=''>";
				strul =  strul + "<p class='txt'>검색 결과가 없습니다.</p>";
				strul =  strul + "<strong class='text-primary h6'>직접 입력 </strong> : '" + "<a href='/mywed/cost?email=${email}&idWedding=${idWedding}&cdCategory=${cdCategory}&cdCompany=0&nmCompany="+val+"&NextChk=S'>" + val + "'</a>"+ "</div>";
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
					
					// &nmPartner=${nmPartner}&nmPartner=${nmPartner}&nmWeddingHall=${nmWeddingHall}&weddate=${weddate}&wedtime=${wedtime}

					strul =  "";
					strul =  "<li>"+ "<a href='cost?email=${email}&idWedding=${idWedding}&cdCategory=${cdCategory}&cdCompany="+item.cdCompany+"&nmCompany="+item.nmCompany+"&NextChk=S'>" + " <p class='h6'> " + item.nmCompany + "</p>" + "<p class='text-muted mt-2'> " + item.dsAddr + "</p>"+"</a></li>";
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


</script>

<spring:url var="authUrl" value="/mywed/company" />
</head>
<body>

<div id="wrap">

	<header id="hd" class="search-area">
		<div class="container">
			<a href="javascript:history.back();" class="icon-back">뒤로가기</a>
			<div class="form-search">
				<input type="text" placeholder="업체명을 검색해 보세요" name="schNmCompany" id="schNmCompany" value="" onkeyup="SchList(this.value);" >
				<button type="button" class="icon-x" onclick="func_del();" >입력삭제</button>
			</div>
		</div>
	</header>
	

	<!-- container -->
	<div id="ct">
	
		<!-- 페이지 시작 -->
		<div class="container">		
			
			<div id="div_List"></div>
			<ul class="list-border mt-2" id="ul_dynaList">		
			</ul>
			
			
		</div>
			<div><input type="text" id="welcome" width="200" hidden="true"></div>	
		
			<form class="schcompany-form" id="schcompanyForm" name="schcompanyForm" method="post" action="${authUrl}">
				<div class="display-2">							
					
					<input type="text" class="form-control" placeholder="email" name="email" value="<c:out value='${email}'/>" hidden="true" >
					
					<input type="text" class="form-control" placeholder="idWedding" name="idWedding" value="<c:out value='${idWedding}'/>" hidden="true" >
					<input type="text" class="form-control" placeholder="cdCategory" name="cdCategory" value="<c:out value='${cdCategory}'/>" hidden="true" >
					<input type="text" class="form-control" placeholder="cdCompany" name="cdCompany" value="<c:out value='${cdCompany}'/>" hidden="true" >
					<input type="text" class="form-control" placeholder="NextChk" name="NextChk" value="S" hidden="true"> <!-- 다음페이지 자동처리 불가처리 -->
				</div>
			</form>	
						
  
	<!--// 페이지 끝 -->
	</div>
	
		
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="상단으로 이동"></a>	
</div>


</body>
</html>