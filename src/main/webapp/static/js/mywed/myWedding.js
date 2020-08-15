var MyWedding = function () {
	
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
	
	// 고객email세팅
	// $("#email").val("TEST");
	/*
	var chknmUser =  document.getElementById("chknmUser").value; 

	if (chknmUser.length == 0){
		$('#mywedForm').submit();	
	}
	*/
	/*
	$("modalbtn").one("click", function() {
		$('#mywedForm').submit();
	});

	
    $(document).ready(function(){

    	$("#btnlogin").bind("click", function(){
    	
    		alert("클릭");
    		$('#modal1').modal();

    	
    	})
	
	// $("#Select2").trigger("click");


    });
	
    	*/ 
    
	
	$('#mywedForm').submit();	
	
	
}


function submitform1(){
	
	// 고객email세팅
	// $("#email").val("TEST");

	/*
	var chknmUser1 =  document.getElementById("chknmUser1").value; 

	if (chknmUser1.length == 0){
		$('#editForm').submit();
	}
	*/
	
	$('#editForm').submit();
	
	
}


function submitform2(){
	
	// $('#mywedForm').submit();
	
	$("#btnlogin").trigger("click");
	 
	$("btnlogin").one("click", function() {
		$('#mywedForm').submit();
	});	
	
	// $("modalbtn").one("click", function() {
	//	$('#mywedForm').submit();
	// });	
	
}


// 준비사항
function funcPop1(){
	
	$('#modal2').modal();
}


// 부가상품
function funcPop2(){
	
	$('#modal3').modal();
}


// 선택사항
function funcPop3(){
	
	$('#modal4').modal();
}



$(function() {    
	MyWedding.init();
});
