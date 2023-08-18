<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="author" content="Kodinger">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>BUG Company</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="<%=url %>/css/my-login.css">

<style type="text/css">
.rq {
	color: red;
}
</style>

</head>
<%

String ss = session.getAttribute("session") + "";
System.out.println("this is session on index file " + ss);
System.out.println(url);
String userName = session.getAttribute("userName") + "";
userName = userName.equals("null") ? "" : userName;
String e_error = request.getAttribute("e_error") + "";
e_error = (e_error.equals("null") ? "" : e_error);
%>
<body class="my-login-page">
	<section class="h-100" style="background-image: url('img/bg.jpg');">
		<div class="container h-100">

			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand" align="center">
						<img src="<%=url%>/img/logo.jpg" alt="logo">
					</div>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title" align="center">Login</h4>
							<form method="POST" class="my-login-validation" novalidate=""
								action="<%=url %>/AdminController">
								<input type="hidden" name="action" value="login">
								<div class="form-group">
									<label for="type" class="form-label">Select Permission</label> <br>
									 <select
										class="form-select" id="permission" name="permission" required>
										<option value="administrator">Administrator</option>
										<option value="superUser">Super User</option>
									</select>
									<div class="invalid-feedback">Please select a valid type.</div>
								</div>
								<div class="form-group">
									<label for="userName">User Name</label> <input id="email"
										type="text" class="form-control" name="userName" required
										value="<%=userName%>" autofocus>
									<div class="invalid-feedback">User name is invalid</div>

								</div>
								<div class="form-group">
									<label for="password">Password <a href="forgot.jsp"
										class="float-right"> Forgot Password? </a>
									</label> <input id="password" type="password" class="form-control"
										name="password" required data-eye>
									<div class="invalid-feedback">Password is required</div>
								</div>
								<span class="rq"> <%=e_error%></span>

								<div class="form-group m-0">
									<button type="submit" class="btn btn-primary btn-block">
										Login</button>
								</div>
								<div class="mt-4 text-center">
									Don't have an account? <a href="register.jsp">Create One</a>
								</div>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>

		<%@ include file="/Footer/footer.jsp"%>

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
	<script src="js/my-login.js"></script>
</body>
</html>