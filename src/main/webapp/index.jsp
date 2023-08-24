<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BUG selling Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet" href="./assets/css/style.css">
<link rel="stylesheet"
	href="./assets/font/fontawesome-free-5.15.4-web/css/all.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="css/checkout.css">

<link rel="stylesheet" href="css/style.css">
</head>
<body style="background-color: black">
	<%
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (Exception e) {
		e.printStackTrace();
	}
	String ss = session.getAttribute("userName") + "";
	System.out.println("this is user Name" + ss);
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
	String mainVideo = ProductDao.getInstance().selectMainVideo();
	System.out.print("This is main video" + mainVideo);
	if (mainVideo == null) {
		mainVideo = url + "/Product/MainVideo/default.mp4";
	} else {
		mainVideo = url + "/Product/MainVideo/" + mainVideo;
	}
	ArrayList<Product> list = ProductDao.getInstance().testOffset();
	System.out.println("size of list : " + list.size());
	for (Product pr : list) {
		System.out.println(pr.toString());
	}
	System.out.println(mainVideo);
	%>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src="./assets/img/logo.png" alt="Logo" width="70" height="24">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="">My Cart</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Account </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"
								href="<%=url%>/CustomerDirec/register.jsp">Sign Up</a></li>
							<li><a class="dropdown-item" href="<%=url%>/login.jsp">Sign
									In</a></li>
							<li><a class="dropdown-item" href="#">Something </a></li>
						</ul></li>
				</ul>
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>
	<div class="ratio ratio-16x9 ">
		<video controls loop muted autoplay>
			<source src="<%=mainVideo%>">
		</video>
	</div>
	<div class="container">
		<%
		int j = 0;
		System.out.println("j at row Loop" + j);
		for (int i = 0; i < 4; i++) {
		System.out.println("i at row Loop" + i);
		%>
		<div class="row col-md-auto">
			<%
			int count = 0;
			for ( ; count < 4 && j <= list.size() ; j++, count++) {
				System.out.println("j at load card loop " + j);
			%>
			<div class="card border-secondary col-md-3 my-5 p-5">
				<div class="card-header" align="center"></div>
				<video src="<%=url%>/Product/Path/<%=list.get(j).getPath()%>" class="vid1"
					id="vid1" loop controls muted></video>
				<div class="card-body ">
					<h5 class="card-title" align="center"><%=list.get(j).getProductName()%></h5>
					<p class="card-text  limit-text-height "><%=list.get(j).getDescription()%></p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country : <%=list.get(j).getCountry()%></li>
					<li class="list-group-item">Price : <label
						class="text-decoration-line-through"><%=list.get(j).getPrice()%></label></li>
					<li class="list-group-item text-success">Cost : <%=list.get(j).getCost()%></li>
				</ul>
				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>		
			<%
			}
			System.out.println("j at end of loop card " + j);
			%>
		</div>
		<%
		}
		%>
		<div align="center">
			<button class="btn btn-outline-primary my-5">Previous</button>
			<button class="btn btn-outline-primary my-5">Next Page</button>
		</div>
	</div>
	<div>
		<%@ include file="Footer/footer.jsp"%>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
		integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
		integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
		crossorigin="anonymous"></script>

	<script>
		// Listening to the video element
		let clip1 = document.querySelector(".container .row .card .vid1")
		let clip2 = document.getElementById("vid1")

		/* Adding the event listeners on the video to play/pause the video. */
		clip2.addEventListener("mouseover", function(e) {
			clip2.play();
		})
		/* Applying the mouse out event to pause the video */
		clip2.addEventListener("mouseout", function(e) {
			clip2.pause();
		})

		/* Adding the event listeners on the video to play/pause the video. */
		clip1.addEventListener("mouseover", function(e) {
			clip1.play();
		})
		/* Applying the mouse out event to pause the video */
		clip1.addEventListener("mouseout", function(e) {
			clip1.pause();
		})
	</script>
</body>
</html>


