var WeddingHall = function () {
	
	var rsvSimpleTable;
	
    var initForm = function () {
    	
    	// 화면 조회처리
    	// $('#mywedForm').submit();
    }

    return {
        //main function to initiate the module
        init: function () {
            initForm();
        }
    };

}();

function submitform(){
	
	$('#schWeddingHallForm').submit();
}


function submitform1(){
	
	
	$('#schWeddingHallForm1').submit();
}



$(function() {    
	WeddingHall.init();
});
