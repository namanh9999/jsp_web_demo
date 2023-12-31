<%@page import="java.sql.Date"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="author" content="Kodinger">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Register &mdash; Bootstrap 4 Login Page Snippet</title>
<style type="text/css">
.rq {
	color: red;
}
</style>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/my-login.css">
</head>

<body class="my-login-page">

	<%
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
	String e_email = request.getAttribute("e_email") + "";
	e_email = (e_email.equals("null") ? "" : e_email);
	String e_userName = request.getAttribute("e_userName") + "";
	e_userName = e_userName.equals("null") ? "" : e_userName;
	String e_repassword = request.getAttribute("e_Repassword") + "";
	e_repassword = e_repassword.equals("null") ? "" : e_repassword;
	String userName = request.getAttribute("userName") + "";
	userName = userName.equals("null") ? "" : userName;
	String fullName = request.getAttribute("fullName") + "";
	fullName = fullName.equals("null") ? "" : fullName;
	String address = request.getAttribute("address") + "";
	address = address.equals("null") ? "" : address;
	String birth = request.getAttribute("birth") + "";
	birth = birth.equals("null") ? "" : birth;
	String phoneNumber = request.getAttribute("phoneNumber") + "";
	phoneNumber = phoneNumber.equals("null") ? "" : phoneNumber;
	String email = request.getAttribute("email") + "";
	email = email.equals("null") ? "" : email;
	String emailRegister = request.getAttribute("emailRegister") + "";
	emailRegister = emailRegister.equals("null") ? "" : emailRegister;
	%>
	<section class="h-100">
		<div class="container h-100">
			<video autoplay loop muted class="back-video"></video>
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand" align="center">
						<img src="<%=url%>/assets/img/logo.png"
							alt="bootstrap 4 login page">
					</div>
					<div class="card fat">
						<div class="card-body">
							<h2 class="card-title" align="center">Admin Register</h2>

							<form method="POST" class="my-login-validation" novalidate=""
								action="<%=url%>/AdminController">
								<input type="hidden" name="action" value="AdminAccountRegister">
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="userName">User Name</label> <input id="userName"
												type="text" class="form-control" name="userName"
												value="<%=userName%>" required autofocus> <span
												class="rq"><%=e_userName%></span>
											<div class="invalid-feedback">What's your UserName?</div>
										</div>

										<div class="form-group">
											<label for="emailAddress">E-Mail Address</label> <input
												id="emailAddress" type="email" class="form-control"
												name="emailAddress" value="<%=email%>" required>
											<div class="invalid-feedback">Your email is invalid</div>
											<span class="rq"><%=e_email%></span>
										</div>

										<div class="form-group">
											<label for="fullName">Full Name</label> <input id="fullName"
												type="text" class="form-control" name="fullName"
												value="<%=fullName%> " required autofocus>
											<div class="invalid-feedback">What's your UserName?</div>
										</div>


										<div class="form-group">
											<label for="address">Address</label> <input id="address"
												type="text" class="form-control" name="address"
												value="<%=address%>" required autofocus>
											<div class="invalid-feedback">Where's your address?</div>
										</div>

									</div>

									<div class="col-sm-6">

										<div class="form-group">
											<label for="name">Birth</label> <input id="birth" type="date"
												class="form-control" name="birth" value="<%=birth%>"
												required autofocus>

											<div class="invalid-feedback">When your birth day</div>
										</div>


										<div class="form-group">
											<label for="phoneNumber">Phone Number</label> <input
												id="phoneNumber" type="tel" class="form-control"
												name="phoneNumber" value="<%=phoneNumber%>" required
												autofocus>
											<div class="invalid-feedback">What's your UserName?</div>
										</div>

										<div class="form-group">
											<label for="password">Password</label> <input id="password"
												type="password" class="form-control" name="password"
												required data-eye>
											<div class="invalid-feedback">Password is required</div>
										</div>
										<div class="form-group">
											<label for="rePassword">Re-Enter Password</label> <input
												id="rePassword" type="password" class="form-control"
												name="rePassword" required data-eye>
											<div class="invalid-feedback">Password is required</div>

											<span class="rq"><%=e_repassword%></span>
										</div>
									</div>

								</div>

								<div class="form-group m-0">
									<button class="btn btn-primary btn-block" type = "submit">Register</button>
								</div>

							</form>
							<div class="mt-4 text-center">
								<a href="<%=url%>/Admin/index.jsp"><button
										class="btn-primary">Back to admin page</button> </a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="footer" align="center">
				<jsp:include page="/Footer/footer.jsp"></jsp:include>
			</div>
		</div>
	</section>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="js/my-login.js"></script>
</body>
</html>
