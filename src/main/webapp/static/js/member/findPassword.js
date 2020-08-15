var FindPassword = function () {
	
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
	$('#findpasswordForm').submit();
}

$(function() {    
	FindPassword.init();
});
