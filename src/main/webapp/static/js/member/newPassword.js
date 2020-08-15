var NewPassword = function () {
	
	var rsvSimpleTable;
	
    var initForm = function () {
    	
    	// 추후 세션처리 할건지 확인 필요
    	// $('#email').val(Users.username()); // request.getParameter("email");    	

    }

    return {
        //main function to initiate the module
        init: function () {
            initForm();
        }
    };

}();

function submitform(){
	$('#newpasswordForm').submit();
}

$(function() {    
	NewPassword.init();
});
