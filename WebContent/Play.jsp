<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Recensione"%>
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
				<li class="active">Giochi / <%=request.getAttribute("titolo")%></li>
			</ol>
		</div>
		<!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header"></h1>
			</div>
		</div>
		<!--/.row-->

		<!--Gioco-->
		<!--row-->
		<div class="row">
			<div class="col-xs-6 col-md-12">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">	
						<div class="text-center">
							<center><img src="/oop17/images/gioca.png" class="img-responsive" alt=""><center>
						</div>
						<form role="form" action="/oop17/Play" method="post">
							<div class="panel-footer">
								<input type="hidden" name="Gioco" value="<%=request.getAttribute("titolo")%>">
								<button type="submit" class="btn btn-success"><i class="fa fa-play-circle"></i> Play</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--/.Gioco-->
		<!--/.Risultato-->
		
		<!--/.Risulatato-->
		<%
			if (request.getAttribute("vittoria")!=null) {
				if ((boolean)request.getAttribute("vittoria")==true) {
			%>
				<div class="form-group">
					<div id='delete' class="alert bg-success" role="alert">
						<em class="fa fa-lg fa-check-circle">&nbsp;</em>Hai Vinto! :)
						<a href="#" class="pull-right"><em class="fa fa-lg fa-close"></em></a>
					</div>
				</div>
			<%
			}else{%>
				<div class="form-group">
					<div id='delete' class="alert bg-danger" role="alert">
						<em class="fa fa-lg fa-times-circle">&nbsp;</em> Hai Perso! :(
						<a href="#" class="pull-right"><em class="fa fa-lg fa-close"></em></a>
					</div>
				</div>
			<%	
			}}
			%>
			
			<!--/.RECESIONI -->

		<div class="panel panel-default chat">
			<div class="panel-heading">
				Recensioni <span
					class="pull-right clickable panel-toggle panel-button-tab-left"><em
					class="fa fa-toggle-up"></em></span>
			</div>
			<div class="panel-body">
				<ul>
					<%
						ArrayList<Recensione> lista = (ArrayList<Recensione>) request.getAttribute("recensioni");
						for (Recensione x : lista) {
					%>
					<li class="left clearfix"><span class="chat-img pull-left">
							<img src="/oop17/fonts/user.png" height="50" width="50" alt="User Avatar"
							class="img-circle">
					</span>
						<div class="chat-body clearfix">
							<div class="header">
								<strong class="primary-font">
									<%
										out.print(x.getUtente_username());
									%>
								</strong>
							</div>
							<p>
								<%
									out.print(x.getRecensione());
								%>
							</p>
						</div></li>
					<%
						}
					%>
				</ul>
			</div>
			<%
			if (request.getAttribute("disattiva")!=null) {
			%>
			<div class="panel-footer">
				<form action="/oop17/Recensione" method="post">
					<div class="input-group">
								<input type="text" name="recensione" class="form-control input-md" placeholder="In attesa di Moderazione..."> <span class="input-group-btn">
								<button type="submit" disabled class="btn btn-primary btn-md">Invia</button>
							</span>
						
					</div>
				</form>
			</div>
			<%}else{%>
			<div class="panel-footer">
				<form action="/oop17/Recensione" method="post">
					<div class="input-group">
								<input type="hidden" name="Gioco" value="<%out.print(request.getAttribute("titolo"));%>">
								<input type="text" name="recensione" class="form-control input-md" placeholder="Scrivi una recensione..."> <span class="input-group-btn">
								<button type="submit" class="btn btn-primary btn-md">Invia</button>
							</span>
						
					</div>
				</form>
			</div>
			<%}%>
		</div>
	</div>
	<!--/.main-->

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
