<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>OOP2017 - Login</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/datepicker3.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">
	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<!-- FORM -->
		<div class="row">
			<div
				class="col-xs-10 col-xs-offset-1 col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">Log in</div>
					<div class="panel-body">
						<form role="form" action="/oop17/Login" method="Post">
							<fieldset>
								<div class="form-group <%if(request.getAttribute("ErrorUser")!=null){ out.print("has-error");}%> col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Username" name="username" type="text" required value="<%if(request.getAttribute("UserInsert")!=null){ out.print(request.getAttribute("UserInsert"));}%>">
								</div>
								
								<div class="form-group <%if(request.getAttribute("ErrorPassword")!=null){ out.print("has-error");}%> col-md-10 col-md-offset-1">
									<input class="form-control" placeholder="Password" name="password" type="password" required>
								</div>
								
								<div class="form-group text-center">
									<input type="submit"  class="btn btn-primary" style="padding: 8px 103px; margin-top:15px;" value="Login" >
								</div>
								</fieldset>
						</form>
						<div class="form-group  col-md-12 text-center">
							Non sei registrato? <a href="/oop17/SignUp">Registrati</a>
						</div>
					</div>
				</div>
			</div>
			<!-- /.col-->
		</div>
		<!-- /.FORM -->
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>
