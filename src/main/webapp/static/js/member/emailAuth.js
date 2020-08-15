var EmailAuth = function () {
	
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
	$('#emailauthForm').submit();
}

$(function() {    
	EmailAuth.init();
});
