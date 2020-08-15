var WithDraw = function () {
	
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
	$('#withdrawForm').submit();
}

$(function() {    
	WithDraw.init();
});
