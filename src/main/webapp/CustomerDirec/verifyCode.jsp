<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
</head>
<body>
	<%
	String e_verifyCode = request.getAttribute("e_verifyCode") + "";
	e_verifyCode = e_verifyCode.equals("null") ? "" : e_verifyCode;
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
	String action  = session.getAttribute("action") + "";
	%>
	<form class="row g-3" action="<%=url%>/CustomerController"
		method="POST">
		<div style="text-align: center;">
			<input type="hidden" name="action" value="<%=action%>">
			<div class="col-auto">
				<label for="staticEmail2" class="visually-hidden">verify
					code</label> <input type="text" readonly class="form-control-plaintext"
					id="staticEmail2" value="verify code">
			</div>
			<div class="col-auto">
				<label for="inputPassword2" class="visually-hidden">Enter
					here</label> <input type="text" class="form-control" id="verifyCode"
					name="verifyCode" placeholder="Enter here">
			</div>
			<span class="rq"><%=e_verifyCode%></span>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary mb-3">Confirm
				</button>
			</div>
		</div>
	</form>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
	integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
	integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
	crossorigin="anonymous"></script>

</html>