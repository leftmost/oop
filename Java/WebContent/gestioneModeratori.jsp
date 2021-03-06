<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Utente"%>
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
	<%@ include file="sidebar.jsp"%>
	<!-- /.SIDEBAR -->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"> <em class="fa fa-home"></em>
				</a></li>
				<li class="active">Gestione moderatori</li>
			</ol>
		</div>

		<!--TITOLO-->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Gestione moderatori</h1>
			</div>
		</div>
		<!--/.TITOLO-->
		<div class="col-lg-12">
			<%
			if (request.getAttribute("mexag")!=null) {
				if ((boolean)request.getAttribute("mexag")==true) {
			%>
				<div class="form-group">
					<div id='delete' class="alert bg-success" role="alert">
						<em class="fa fa-lg fa-check-circle">&nbsp;</em> Nominato Moderatore!
						<a href="#" class="pull-right"><em class="fa fa-lg fa-close"></em></a>
					</div>
				</div>
			<%
			}else{%>
			    <div class="form-group">
					<div id='delete' class="alert bg-success" role="alert">
						<em class="fa fa-lg fa-check-circle">&nbsp;</em> Retrocesso ad Utente!
						<a href="#" class="pull-right"><em class="fa fa-lg fa-close"></em></a>
					</div>
				</div>
			<%	
			}}
			%>
			<%
			if (request.getAttribute("mexags")!=null) {
				if ((boolean)request.getAttribute("mexags")==true) {
			%>
			    <div class="form-group">
					<div id='delete' class="alert bg-danger" role="alert">
						<em class="fa fa-lg fa-times-circle">&nbsp;</em> Non puoi promuovere un Moderatore!
						<a href="#" class="pull-right"><em class="fa fa-lg fa-close"></em></a>
					</div>
				</div>
			<%
			}else{%>
				<div class="form-group">
					<div id='delete' class="alert bg-danger" role="alert">
						<em class="fa fa-lg fa-times-circle">&nbsp;</em> Non puoi retrocedere un Utente!
						<a href="#" class="pull-right"><em class="fa fa-lg fa-close"></em></a>
					</div>
				</div>
			<%	
			}}
			%>

		<!--BODY-->
		<div class="row">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Utente</th>
							<th>Email</th>
							<th>Nome</th>
							<th>Cognome</th>
							<th>Tipologia</th>
							<th colspan="2"></th>
						</tr>
					</thead>
					<tbody>
					<%
						ArrayList<Utente> lista = (ArrayList<Utente>) request.getAttribute("UteMod");
						for (Utente x : lista) {
					%>
					<tr>
					<th><%out.print(x.getUsername());%></th>
					<th><%out.print(x.getEmail());%></th>
					<th><%out.print(x.getNome());%></th>
					<th><%out.print(x.getCognome());%></th>
					<th><%out.print(x.getTipologia());%></th>
					<th class="text-center">
						<form method="post">
							<input type="hidden" name="username" value="<%out.print(x.getUsername());%>">
							<input type="hidden" name="promuovi">
							<input class="btn btn-success" type="submit" value="Nomina">
						</form>
					</th>
					<th class="text-center">
						<form method="post">
							<input type="hidden" name="retrocedi">
							<input type="hidden" name="username" value="<%out.print(x.getUsername());%>">
							<input class="btn btn-danger" type="submit" value="Retrocedi">
						</form>
					</th>
					</tr>	
					<%}%>
					</tbody>
					
				</table>
			</div>
		</div>
		<!--/.BODY-->
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
