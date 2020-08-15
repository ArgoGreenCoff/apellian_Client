<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	<h1 class="display-2">����</h1>
	<h2 class="price-title">�ݾ�</h2>
	<div class="price text-primary"><span class="font-num h3">${price}</span><span class="h6">��</span></div>
	<hr>
	<h2 class="detail-title2">���/ȯ��</h2>
	<p class="text-muted">${classInfo.dsRefund}</p>
</div>
<div class="fixed-bottom-btn">
	<button type="button" class="btn btn-lg btn-dark btn-block" data-toggle="modal" data-target="#modal1">�����ϱ�</button>
</div>
<!--// ������ �� -->
	</div>
	<a href="" class="btn-top"><img class="d-block w-100" src="../../static/img/icon/btn-top.png" alt="������� �̵�"></a>
</div>

<!-- modal -->
<div id="modal1" class="modal">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">������<br>�Ϸ�Ǿ����ϴ�.</h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-dark" data-dismiss="modal">Ȯ��</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>