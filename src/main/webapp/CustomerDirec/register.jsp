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
	String e_Repassword = request.getAttribute("e_Repassword") + "";
	e_Repassword = e_Repassword.equals("null") ? "" : e_Repassword;
	String e_gender = request.getAttribute("e_gender") + "";
	e_gender = e_gender.equals("null") ? "" : e_gender;

	String userName = request.getAttribute("userName") + "";
	userName = userName.equals("null") ? "" : userName;
	String fullName = request.getAttribute("fullName") + "";
	fullName = fullName.equals("null") ? "" : fullName;
	String gender = request.getAttribute("gender") + "";
	gender = gender.equals("null") ? "" : gender;
	String address = request.getAttribute("address") + "";
	address = address.equals("null") ? "" : address;
	String deliAddress = request.getAttribute("deliAddress") + "";
	deliAddress = deliAddress.equals("null") ? "" : deliAddress;
	String shipAddress = request.getAttribute("shipAddress") + "";
	shipAddress = shipAddress.equals("null") ? "" : shipAddress;
	String buyAddress = request.getAttribute("buyAddress") + "";
	buyAddress = buyAddress.equals("null") ? "" : buyAddress;
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
						<img src="<%=url%>/assets/img/logo.png" alt="bootstrap 4 login page">
					</div>
					<div class="card fat">
						<div class="card-body">
							<h2 class="card-title" align="center">Register</h2>

							<form method="POST" class="my-login-validation" novalidate=""
								action="<%=url%>/CustomerController">
								<input type="hidden" name="action" value="register">
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

											<span class="rq"><%=e_Repassword%></span>
										</div>
										<label>Gender</label>
										<div class="form-group radio-check">
											<div>
												<input type="radio" name="gender" value="Men"
													<%=(gender.equals("Men")) ? "checked='checked'" : ""%>
													id="male"> <label for="male">Men</label>
											</div>
											<div>
												<input type="radio" name="gender" value="Women"
													<%=(gender.equals("Women")) ? "checked='checked'" : ""%>
													id="female"> <label for="female">Women</label>
											</div>

										</div>

										<span class="rq"><%=e_gender%></span>

									</div>

									<div class="col-sm-6">

										<div class="form-group">
											<label for="address">Address</label> <input id="address"
												type="text" class="form-control" name="address"
												value="<%=address%>" required autofocus>
											<div class="invalid-feedback">Where's your address?</div>
										</div>

										<div class="form-group">
											<label for="deliAddress">Delivery Address </label> <input
												id="deliAddress" type="text" class="form-control"
												name="deliAddress" value="<%=deliAddress%>" required>
											<div class="invalid-feedback">Where's your delivery
												address</div>
										</div>
										<div class="form-group">
											<label for="">Shipping Address</label> <input
												id="shipAddress" type="text" class="form-control"
												name="shipAddress" value="<%=shipAddress%>" required
												data-eye>
											<div class="invalid-feedback">Where's your shipping
												address</div>
										</div>
										<div class="form-group">
											<label for="buyAddress">Buy Address</label> <input
												id="buyAddress" type="text" class="form-control"
												name="buyAddress" value="<%=buyAddress%>" required data-eye>
											<div class="invalid-feedback">Where's your buy address</div>
										</div>

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
									</div>
								</div>
								<div class="form-group">
									<div class="form-check form-switch">
										<input class="form-check-input" type="checkbox" role="switch"
											id="emailRegister" name="emailRegister"
											<%=(emailRegister.equals("on")) ? "checked='checked'" : ""%>>
										<label class="form-check-label" for="flexSwitchCheckDefault">I
											agree using this email to received discount and announce </label>
									</div>
									<div class="form-check form-switch">
										<input class="form-check-input" type="checkbox" role="switch"
											id="agreeTerms" name="agreeTerms" checked> <label
											class="form-check-label" for="flexSwitchCheckChecked">I
											agree to the <a href="https://youtube.com"> Term of
												Services</a>
										</label>
									</div>

								</div>

								<div class="form-group m-0">
									<button onclick="checkRadioButton()"
										class="btn btn-primary btn-block">Register</button>
								</div>
								<div class="mt-4 text-center">
									Already have an account? <a href="<%=url%>/index2.jsp">Login</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="footer" align="center">
				Copyright &copy; 2023 &mdash; BUG
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
