<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Apellian</title>
<link rel="stylesheet" href="../../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../../static/css/swiper.min.css">
<link rel="stylesheet" href="../../static/css/common.css">
<script src="../../static/js/jquery-3.4.1.slim.min.js" type="text/javascript"></script>
<script src="../../static/js/popper.min.js" type="text/javascript"></script>
<script src="../../static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../static/js/swiper.min.js" type="text/javascript"></script>
<script src="../../static/js/common.js" type="text/javascript"></script>
</head>
<body>
<c:if test="${applyInfo.cdWin == 'W'}">
<div id="wrap">
	<header id="hd">
		<div class="container">
			<a href="" class="icon-back">�ڷΰ���</a>
		</div>
	</header>
	<!-- gnb -->
	<nav id="gnb">
		<ul class="gnb">
			<li><a class="mn01" href="���ǿ���.html">���� ����</a></li>
			<li><a class="mn02" href="��Ű��.html">��Ű��</a></li>
			<li class="active"><a class="mn03" href="����_Ŭ����.html">����</a></li>
			<li><a class="mn04" href="">�Ű���</a></li>
			<li><a class="mn05" href="">������</a></li>
		</ul>
	</nav>
	
	<!-- container -->
	<div id="ct">
<!-- ������ ���� -->
<div class="container">
	<h1 class="display-2">��÷��</h1>
	<div class="box">
		<h2 class="h3 font-serif font-weight-bold">�����մϴ�!<br><span class="text-primary">��÷</span>�Ǿ����ϴ�.</h2>
		<p class="small mt-3">��û�� ${applyCnt}�� �߿��� ������ �հ�<br>��÷�ڷ� �����Ǿ����ϴ�.</p>
	</div>
	<hr>
	<h3 class="detail-title2">�˸�</h3>
	<p class="text-muted">${classInfo.dsNotice}</p>
	<h3 class="detail-title2 mt-3">�Ա�</h3>
	<p class="text-muted">${classInfo.dsPrice}</p>
	<h3 class="detail-title2 mt-3">���/ȯ��</h3>
	<p class="text-muted">${classInfo.dsRefund}</p>
	<h3 class="detail-title2 mt-3">�������� ����ó</h3>
	<p class="text-muted">${applyInfo.telApply}</p>
</div>
<div class="fixed-bottom-btn">
	<c:if test="${applyInfo.idPayment == 0}">
		<a href="/benefit/payment?idClass=${applyInfo.idBnfClass}&idUser=${applyInfo.idUser}" class="btn btn-lg btn-dark btn-block">�����ϱ�</a>
	</c:if>
	<c:if test="${applyInfo.idPayment > 0}">
		<a href="#" class="btn btn-lg btn-dark btn-block">�̹� ������ �Ϸ�Ǿ����ϴ�</a>
	</c:if>
	
</div>
<!--// ������ �� -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="������� �̵�"></a>
</div>
</c:if>
<c:if test="${applyInfo.cdWin == 'F'}">
<script type="text/javascript">
$( document ).ready(function() {
	$('#modal1').modal();
	$('.modal-title').html('�ƽ��Ե�<br>��÷���� �ʾҽ��ϴ�.');
	$('.modal-body').html('��û�ڰ� ���� ����� ���Ͽ����ϴ�.<br>������ �� �������ּ���.');
});
</script>
</c:if>
<c:if test="${applyInfo.cdWin == 'N'}">
<script type="text/javascript">
$( document ).ready(function() {
	$('#modal1').modal();
	$('.modal-title').html('���� ��÷�ڰ� ��ǥ���� �ʾҽ��ϴ�.');
	//$('.modal-body').html('��û�ڰ� ���� ����� ���Ͽ����ϴ�.<br>������ �� �������ּ���.');
});
</script>
</c:if>


<!-- modal -->
<input type="hidden" type="text" name="modal_type" value="0"/>
<div id="modal1" class="modal">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"></h5>
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-dark" data-dismiss="modal">Ȯ��</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

$('#modal1').on('hide.bs.modal', function (e) {

	window.history.go(-1);

});
</script>

</body>
</html>