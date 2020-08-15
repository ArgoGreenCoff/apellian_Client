var WeddingHall = function () {
	
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
	
	$('#schWeddingHallForm').submit();
}


$(function() {    
	WeddingHall.init();
});


