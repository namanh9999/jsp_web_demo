<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BUG selling Page</title>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
	integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/img">
<link rel="stylesheet" type="text/css" href="css/style.css">

<script type="text/javascript">
	
</script>
</head>
<body>
	<%
	String ss = session.getAttribute("userName") + "";


	System.out.println("this is user Name" + ss);
	%>
	<div class="hero">
		<video autoplay loop muted class="back-video">
			<source src="img/video.mp4">
		</video>
		<nav>
			<img alt="logo" src="img/401-logo.png" class="logo">
			<ul>
				<li><a href="#">Home</a></li>
				<li><a href="#">Fashion</a></li>
				<li><a href="#">Contact Us</a></li>
				<li><a
					href="https://www.facebook.com/profile.php?id=100086915034670">Author</a></li>
				<%
				if (ss.equals("null")) {
				%>
				<li id="signUp"><a href="register.jsp">Sign Up</a></li>
				<li id="signIn"><a href="index2.jsp">Sign In</a></li>
				<%
				} else {
				%>

				<li id="signIn"><a href="changePassword.jsp">Change
						password</a></li>
				<li id="signIn" ><a href="changeInfor.jsp">Change Infor</a></li>

				<li id="signIn" ><button class="btn-outline-primary">Logout</button></li>
				<%
				}
				%>
			</ul>
		</nav>
		<div class="content">
			<h1>VietNam</h1>
			<a href="https://youtube.com">Explore</a>
		</div>
	</div>

	<div>
		<div id="carouselExampleInterval" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active" data-bs-interval="10000">
					<img src="img/index2.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item" data-bs-interval="2000">
					<img src="img/index3.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="img/index4.jpg " class="d-block w-100" alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleInterval" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	</div>


</body>

</body>
</html>