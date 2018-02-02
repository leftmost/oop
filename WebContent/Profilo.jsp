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
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-userpic">
				<img src="/oop17/fonts/user.png" class="img-responsive" alt="">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name"><%=request.getAttribute("username")%></div>
				<div class="profile-usertitle-status">
					<span class="indicator label-success"></span>Online
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" disabled
					placeholder="<%=request.getAttribute("tipologia")%>">
			</div>
		</form>
		<ul class="nav menu">
			<li><a href="/oop17/Home"><em
					class="fa fa-dashboard">&nbsp;</em> Dashboard</a></li>
			<li><a href="/oop17/Giochi"><em class="fa fa-play-circle">&nbsp;</em>Giochi</a>
			</li>
			<li class="active"><a href="/oop17/Profilo"><em class="fa fa-address-card">&nbsp;</em>Profilo</a>
			</li>
			<li><a href="/oop17/Timeline"><em class="fa fa-signal">&nbsp;</em>Timeline</a>
			</li>
			<%
				if (!request.getAttribute("tipologia").equals("Utente")) {
			%>
			<li><a href="/oop17/GUtenti"><em class="fa fa-users">&nbsp;</em>GestioneUtenti</a>
			</li>
			<%
				}
			%>
			<%
				if (!request.getAttribute("tipologia").equals("Utente")) {
			%>
			<li><a href="/oop17/GRecensioni"><em class="fa fa-list">&nbsp;</em>Gestione
					Recensioni</a></li>
			<%
				}
			%>
			<li><a href="/oop17/Logout"><em class="fa fa-power-off">&nbsp;</em>Logout</a>
			</li>
		</ul>
	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"> <em class="fa fa-home"></em>
				</a></li>
				<li class="active">Profilo</li>
			</ol>
		</div>
		<!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header"></h1>
			</div>
		</div>
		<!--/.row-->
		<!-- anagrafica -->
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-default ">
					<div class="panel-heading">
						Modifica <span
							class="pull-right clickable panel-toggle panel-button-tab-left"><em
							class="fa fa-toggle-up"></em></span>
					</div>
						<div class="panel-body">
							<div class="col-md-10 col-md-offset-1">
								<form role="form" action="/oop17/Login" method="Post">
									<fieldset>
										<div class="form-group">
											<input class="form-control" name="nome" type="text" required value="<%=request.getAttribute("nome")%>">
										</div>

										<div class="form-group">
											<input class="form-control" name="cognome" type="text" required value="<%=request.getAttribute("cognome")%>">
										</div>

										<div class="form-group text-center">
											<input type="submit" class="btn btn-primary" style="padding: 8px 50px; margin-top: 15px;" value="Salva">
										</div>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!--/.anagrafica-->
			</div>
			<!--/.col-->
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
</body>
</html>
