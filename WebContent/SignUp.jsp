<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>OOP17</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/datepicker3.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">
	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<div class="row">
			<div
				class="col-xs-10 col-xs-offset-1 col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">Registrazione</div>
					<div class="panel-body">
						<form role="form" action="/oop17/SignUp" method="post">
							<fieldset>
								<div class="form-group col-md-10 col-md-offset-1 <%if(request.getAttribute("emailEsistente")!=null){ out.print("has-error");}%>">
									<input class="form-control" name="email" type="email" required <%if(request.getAttribute("emailEsistente")!=null){ out.print("placeholder='Email esistente!'");}%> placeholder="Email">
								</div>
								<div class="form-group col-md-10 col-md-offset-1 <%if(request.getAttribute("usernameEsistente")!=null){ out.print("has-error");}%>">
									<input class="form-control" name="username" type="text" <%if(request.getAttribute("usernameEsistente")!=null){ out.print("placeholder='Username esistente!'");}%> placeholder="Username">
								</div>
								<div class="form-group col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Name" name="nome" type="text" >
								</div>
								<div class="form-group col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Surname" name="cognome" type="text" >
								</div>
								<div class="form-group col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Password" name="password" type="password" value="" >
								</div>
								<div class="form-group  text-center">
									<input type="submit"  class="btn btn-primary" style="padding: 8px 96px; margin-top:15px;" value="Registrati" >
								</div>
								<div class="form-group  col-md-12 text-center">
									Sei registrato? <a href="Login">Accedi</a>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
			<!-- /.col-->
		</div>
		<!-- /.row -->
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>
