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
				<li class="active">Dashboard</li>
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
						Dashboard <span
							class="pull-right clickable panel-toggle panel-button-tab-left"><em
							class="fa fa-toggle-up"></em></span>
					</div>
					<div class="panel-body timeline-container">
						<ul class="timeline">
							<li>
								<div class="timeline-badge">
									<em class="glyphicon glyphicon-user"></em>
								</div>
								<div class="timeline-panel">
									<div class="timeline-heading">
										<h4 class="timeline-title">Anagrafica</h4>
									</div>
									<div class="timeline-body">
										<p>Username: <b><%=request.getAttribute("username")%></b></p>
										<p>Nome: <b><%=request.getAttribute("nome")%></b></p>
										<p>Cognome: <b><%=request.getAttribute("cognome")%></b></p>
									</div>
								</div>
							</li>
							<li>
								<div class="timeline-badge primary">
									<em class="glyphicon glyphicon-tasks"></em>
								</div>
								<div class="timeline-panel">
									<div class="timeline-heading">
										<h4 class="timeline-title">Livello 
										<%out.print((int)request.getAttribute("esperienza")/100);%></h4>
									</div>
									<div class="timeline-body">
										<p><b><%=request.getAttribute("esperienza")%></b> XP</p>
									</div>
								</div>
							</li>
							<li>
								<div class="timeline-badge">
									<em class="glyphicon glyphicon-flag"></em>
								</div>
								<div class="timeline-panel">
									<div class="timeline-heading">
										<h4 class="timeline-title">Trofei</h4>
									</div>
									<div class="timeline-body">
									<%int trofei=(int)request.getAttribute("esperienza")/100; for(int i=0;i<trofei;i++){
										out.print("Trofeo "+i+"\n");
									}%>
									</div>
								</div>
							</li>
						</ul>
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
