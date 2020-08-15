var PackageList = function () {
	
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
	$('#packagelistForm').submit();
}


$(function() {    
	PackageList.init();
});
