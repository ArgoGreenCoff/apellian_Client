var Company = function () {
	
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
	
	$('#schcompanyForm').submit();	
}


$(function() {    
	Company.init();
});
