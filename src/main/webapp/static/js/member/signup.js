var SignUp = function () {
	
	var rsvSimpleTable;
	
    var initForm = function () {
    	// document.getElementById("nocountry").value = "+82";
    }

    return {
        //main function to initiate the module
        init: function () {
            initForm();
        }
    };

}();

function submitform(){
	
	// document.getElementById("nocountry").value = "+82";
	// $("#nocountry").val('+82');
	$('#regForm').submit();	

	
	
}


var time = 180; // 기준시간
var min = ""; // 분
var sec = ""; // 초

//시간계산
function certtime(tim){

	
	time = tim;
	
	var x=setInterval(function(){
		min=parseInt(time/60);
		sec=time%60;
		document.getElementById("lblcerttime").innerHTML = '남은시간 ' + min + '분 ' + sec+'초';
		//$("#disp2").val(min + '분' + sec+'초');
		time--;
		
		if(time<0) {
			clearInterval(x);
			document.getElementById("lblcerttime").innerHTML = "시간초과";
			//$("#disp2").val('시간초과');
		}
	}, 1000);
}


////////////////////////////////////////////////////////////////////////



//랜덤함수 저장처리
function gocert_submit(){
	
	
	certtime(180);
	

	// 랜덤 값을 auth_code 에 값을 입력후 그 키 값을 받아서
	// authid 에 세팅한다
	$("#gocert").val('1');

	
	// submitform();
	$('#regForm').submit();
	
}


//auth id 로 인증번호 6자리 입력값과 비교해서 확인 처리
//확인이 되면 certyn 에 값 세팅
function certcheck_submit(){
	
	if (document.getElementById("lblcerttime").innerHTML == "시간초과") {
		
		Alert("휴대전화번호 다시 인증하여 주십시요.");
		return;
	}
	
	
	// authid 값을 서버 db로 검색 해당 키값과 
	// 입력된 certinum 값을 비교해서 처리

	// authid 에 세팅한다
	$("#gocert").val('2');	
	
	// submitform();
	$('#regForm').submit();

}



function listValue(lst){

    var li1 = document.getElementsByTagName("li");
    
    var tmp = "";
    for(i=0; i<li1.length; i++){
            if(lst == li1[i].getAttribute("id")){
            // alert(li1[i].getAttribute("value"));
            // $("#nocountry").val(li1[i].getAttribute("value"));
            $('.Selnocountry').text(li1[i].getAttribute("value"));
            
            // $('.Selnocountry').text(li1[i].getAttribute("value"));
            
            // document.getElementById("nocountry").val = li1[i].getAttribute("value");
            // $('nocountry').val(li1[i].getAttribute("value"));
            
            tmp = li1[i].getAttribute("value");
            
            $('#nocountry').val(tmp);
            
            $('#modal').modal('hide'); //닫기 
        }
    }

}





$(function() {    
	SignUp.init();
	// 200716 start
	var gocert = $("#gocert").val();
	if(gocert == 2) {
		certtime(180);
	}
	// 200716 end
});




