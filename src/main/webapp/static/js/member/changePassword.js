var ChangePassword = function () {
	
	var rsvSimpleTable;
	
    var initForm = function () {
    	
    	// 추후 세션처리 할건지 확인 필요

    }

    return {
        //main function to initiate the module
        init: function () {
            initForm();
        }
    };

}();

function submitform(){
	$('#changepasswordForm').submit();
}

$(function() {    
	ChangePassword.init();
});
