var Edit = function () {
	
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
	$('#editForm').submit();
}

/*
function setNmWedHall(){
	$('#Schwedd Hall').submit();
}
*/



function Popwedhall(){
	
	/*
	
	var email1 = document.getElementById("email").value; 
	var nmPartner1 = document.getElementById("nmPartner").value; 
	var nmuser1 = document.getElementById("nmuser").value; 
	var nmWeddingHall1 = document.getElementById("nmWeddingHall").value; 
	var weddate1 = document.getElementById("weddate").value; 
	var wedtime1 = document.getElementById("wedtime").value; 
	
	$('#email1').val(email1);
	$('#nmPartner1').val(nmPartner1);
	
	$('#nmuser1').val(nmuser1);
	$('#nmWeddingHall1').val(nmWeddingHall1);
	$('#weddate1').val(weddate1);
	$('#wedtime1').val(wedtime1);
	
	*/
	
	$('#wedHallForm').submit();
}


var wdDateText = "";

function ClickValue(lst){


	var yearmonth = $('#yearmonth').text();
	
	wdDateText = yearmonth  + "." + lst +"";
	
	$('#wedDate').val( wdDateText );
	
	
}




$(function() {    
	Edit.init();
});
