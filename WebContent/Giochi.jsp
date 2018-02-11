<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Gioco"%>
<%@page import="Model.DAO.Interface.GiocoDAOint"%>
<%
	ArrayList<Gioco> listaGiochi = (ArrayList<Gioco>) request.getAttribute("listaGiochi");
%>

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
<link href="css/stelle.css" rel="stylesheet" type="text/css">

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
				<li class="active">Giochi</li>
			</ol>
		</div>
		<!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header"></h1>
			</div>
		</div>
		<!--/.row-->

		<!--Lista Giochi-->
		<!--row-->
		<div class="row">
					<%
						for (Gioco x : listaGiochi) {
					%>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default" style="cursor: pointer;" onclick="window.location='/oop17/Play?Gioco=<% out.print(x.getTitolo());%>'">
					<div class="panel-body easypiechart-panel">
						<div><img src="/oop17/images/scala.jpg" class="img-responsive"></div>
					</div>
					<div class="panel-footer">
					<div class="text-center"><h4><% out.print(x.getTitolo()); %></h4></div>
						
						<!-- VOTAZIONE -->
						<div class="text-center star">
							<div class="rating">
							<%
							for(int i=0;i<5;i++){
							if(i<x.getValutazione()){%><i class="fa fa-star" aria-hidden="true"></i><%}
							else{%><i class="fa fa-star-o" aria-hidden="true"></i><%}
							}
							%>
							</div>
						</div>
					</div>
					<!-- /.VOTAZIONE -->
				</div>
			</div>
			
					<%
						}
					%>
			
			
		</div>

		<!--/.row-->

		<!--/.Lista Giochi-->
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

</body>
</html>
