
function submitform(){
	
	if(document.applyForm.reason.value==""){	
		alert("신청 사유를 입력해주세요.");
//	}else if(document.applyForm.brand.value==""){	
//		alert("브랜드를 입력해주세요.");
	}else if(document.applyForm.name.value==""){	
		alert("이름을 입력해주세요.");
	}else if(document.applyForm.phone.value==""){	
		alert("연락처를 입력해주세요.");
	}else{//
		$('#applyForm').submit();
	}
	
}


