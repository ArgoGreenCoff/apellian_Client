<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ page import="java.util.*,java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Apellian</title>
</head>
<body>
${result.userInfo.username} <br>
${result.userInfo.nmUser} <br>
${result.userInfo.noTel} <br>
${result.userInfo.tyUser} 

</body>
<script>
	mobileApp.loginComplete('${result.userInfo.username}','${result.userInfo.nmUser}','${result.userInfo.noTel}', '${result.userInfo.tyUser}');
	function closeAct(){
		mobileApp.closeLogin();
	}
	$(function() {    
		
	});
	</script>
</html>