<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>OOP2018 - Sign Up</title>
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
					<div class="panel-heading">Sign Up</div>
					<div class="panel-body">
						<form role="form" action="/web/SignInController" method="post">
							<fieldset>
								<div class="form-group col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Email" name="email" type="email" required>
								</div>
								<div class="form-group col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Username" name="username" type="text" required>
								</div>
								<div class="form-group col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Name" name="name" type="text" required>
								</div>
								<div class="form-group col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Surname" name="surname" type="text" required>
								</div>
								<div class="form-group col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Password" name="password" type="password" value="" required>
								</div>
								<div class="form-group  text-center">
									<input type="submit"  class="btn btn-primary" style="padding: 8px 96px; margin-top:15px;" value="Sign Up" >
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
