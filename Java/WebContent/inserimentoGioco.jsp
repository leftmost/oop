<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>OOP17</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

<!--Custom Font-->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">
<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>OOP</span>2017</a>
			</div>
		</div>
		<!-- /.container-fluid -->
	</nav>
	
	<!-- SIDEBAR -->
	<%@ include file="sidebar.jsp" %> 
	<!-- /.SIDEBAR -->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"> <em class="fa fa-home"></em>
				</a></li>
				<li class="active">Inserimento</li>
			</ol>
		</div>
		<!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header"></h1>
			</div>
		</div>
		<!-- FORM -->
		<div class="row">
			<div class="col-xs-10 col-xs-offset-1 col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">Inserimento Gioco</div>
					<div class="panel-body">
						<form role="form" action="/oop17/InserimentoGioco" method="Post">
							<fieldset>
								<div class="form-group col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Nome gioco" name="nuovoGioco" type="text" required value="">
								</div>
								
								<div class="form-group col-md-10 col-md-offset-1">
									<input class="form-control" name="img" type="hidden" required>
								</div>
								
								<div class="form-group text-center">
									<input type="submit"  class="btn btn-primary" style="padding: 8px 103px; margin-top:15px;" value="Inserisci" >
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
			
		
			
			
			<!-- /.col-->
		</div>
			<%
			if (request.getAttribute("mex")!=null) {
				if ((boolean)request.getAttribute("mex")==true) {
			%>
				<div class="form-group">
					<div id='delete' class="alert bg-success" role="alert">
						<em class="fa fa-lg fa-check-circle">&nbsp;</em> Gioco inserito con successo!
						<a href="#" class="pull-right"><em class="fa fa-lg fa-close"></em></a>
					</div>
				</div>
			<%
			}else{%>
				<div class="form-group">
					<div id='delete' class="alert bg-danger" role="alert">
						<em class="fa fa-lg fa-times-circle">&nbsp;</em> Gioco già presente!
						<a href="#" class="pull-right"><em class="fa fa-lg fa-close"></em></a>
					</div>
				</div>
			<%	
			}}
			%>
		<!-- /.FORM -->
	</div>
	<!--/.row-->

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/custom.js"></script>
	<script type="text/javascript">
		setTimeout(function() {
			$('#delete').remove();
		}, 2500);
	</script>

</body>
</html>
