var SignIn = function () {
	
	var rsvSimpleTable;
	
    var initForm = function () {

    }

    return {
        //main function to initiate the module
        init: function () {
            initForm();
        }
    };

}();



function submitform(){
	$('#loginForm').submit(); // ##9999 
}

//휴대폰번호로 인증처리 위해서 ajax 처리 
function Nextsubmit(){
	
	// ul 초기화 
	
	// document.getElementById("welcom").value = "1";

	$('#welcom').val('1');
	
	var form_data = {
			email: $('input[name=email]').val(),
			passwd: $('input[name=passwd]').val(),
			is_ajax: 1
		};
	
	 // document.getElementById("welcom").value = "2";
		$('#welcom').val('2');
	
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
			
			/*
			if(!data){
				// alert("존재하지 않는 ID입니다"); 
				document.getElementById("errmsg1").value = 'error';
				return false;
			}
			*/
			
			
			if (data.code == "00"){
				//location.href="/mywed/myWedding?email="+$('input[name=email]').val();
				closeAct($('input[name=email]').val());   
				
			}
			else {
				
				if(data.code == "E"){
					
					document.getElementById("errmsg1").style.display = '';
					var tmp1 = document.getElementById("errmsg1");
					tmp1.innerHTML = data.Description;
					
					document.getElementById("errmsg2").style.display = 'none';
					var tmp2 = document.getElementById("errmsg2");
					tmp2.innerHTML = data.Description;
					
				}
				else {
					
					document.getElementById("errmsg1").style.display = 'none';
					var tmp1 = document.getElementById("errmsg1");
					tmp1.innerHTML = data.Description;
					
					document.getElementById("errmsg2").style.display = '';
					var tmp2 = document.getElementById("errmsg2");
					tmp2.innerHTML = data.Description;
					
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
			tmp1.innerHTML = "로그인 중 오류 발생";
			
			document.getElementById("errmsg2").style.display = '';
			var tmp2 = document.getElementById("errmsg2");
			tmp2.innerHTML = data.Description;
			
		}
		, fail: function(){
			//오류발생시
			document.getElementById("errmsg1").style.display = 'none';
			var tmp1 = document.getElementById("errmsg1");
			tmp1.innerHTML = "로그인이 실패했습니다";
			
			document.getElementById("errmsg2").style.display = '';
			var tmp2 = document.getElementById("errmsg2");
			tmp2.innerHTML = data.Description;
			
		}
	});

	
	// ajax 실행문
	jQuery.ajax.done; // (function(data){})
	 
}

function closeAct(idUser){
	
	if( client.isMobileAndroid() ) { // Check For Android
		mobileApp.loginOk(idUser);

	}else if( client.isMobileIOS() ){ // Check For iOS
		webkit.messageHandlers.mobileApp.postMessage("{\"functionTitle\": \"loginOk\", \"email\": \""+idUser+"\"}");

	}else{ 
		//alert('Unknown Mobile Device...');
	}

}

$(function() {    
	SignIn.init();
//	$('#errmsg1').style.display = 'none';
//	$('#errmsg2').style.display = 'none';
	
	//document.getElementById("errmsg1").style.display = 'none';
	//document.getElementById("errmsg2").style.display = 'none';
});
