var PackageApply = function () {
	
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
	$('#packageapplyForm').submit();
}

$(function() {    
	PackageApply.init();
});
