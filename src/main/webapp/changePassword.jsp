<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/my-login.css">

<style type="text/css">
.rq {
	color: red;
}
</style>
</head>
<%
String ss = request.getSession() + "";
String e_oldPass = request.getAttribute("e_oldPass") + "";
e_oldPass = e_oldPass.equals("null") ? "" : e_oldPass;

String e_confirm = request.getAttribute("e_confirm") + "";
e_confirm = e_confirm.equals("null") ? "" : e_confirm;

%>

<body class="my-login-page">
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand">
						<img src="img/logo.jpg" alt="logo">
					</div>

					<form action="changePass" method="POSt"></form>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title" align="center">Change Password</h4>
							<form method="POST" class="my-login-validation" novalidate=""
								action="changePass">
								<div class="form-group">
									<label for="oldPass">Your's Password</label> <input id="email"
										type="password" class="form-control" name="oldPass" required
										autofocus>
									<div class="invalid-feedback">Password not match</div>
									<span class="rq"><%=e_oldPass%></span>
								</div>
								<div class="form-group">
									<label for="password">New Password </label> <input
										id="password" type="password" class="form-control"
										name="newPass" required data-eye>
									<div class="invalid-feedback">Password is required</div>
								</div>
								<div class="form-group">
									<label for="password">Confirm Password </label> <input
										id="password" type="password" class="form-control"
										name="confirm" required data-eye onkeyup="passwordCheck()">

									<div class="invalid-feedback">Password is required</div>
									<span class="rq" ><%=e_confirm%></span>
								</div>
								<div class="form-group m-0">
									<button type="submit" class="btn btn-primary btn-block">
										Change</button>
								</div>
								<div class="mt-4 text-center">
									Don't have an account? <a href="register.jsp">Create One</a>
								</div>
							</form>
						</div>
					</div>
					<div class="footer">Copyright &copy; 2023 &mdash; BUG</div>
				</div>
			</div>
		</div>
	</section>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="js/my-login.js">
		function passwordCheck {
			password = document.getElementById("newPass").value;
			confirm = document.getElementById("confirm").value;
			if (matKhau != matKhauNhapLai) {
				document.getElementById("msg").innerHTML = "Password not match";
				return false;
			} else {
				document.getElementById("msg").innerHTML = "";
				return true;
			}
		}
	</script>
</body>
</html>