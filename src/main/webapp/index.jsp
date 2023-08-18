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
	String avatarPath = session.getAttribute("avatarPath") + "";
	System.out.println(avatarPath);
	if (avatarPath == null) {
		avatarPath = "default.jpg";
	}
	
	String mainVideo = ProductDao.getInstance().selectMainVideo();
	System.out.print("this is main video" + mainVideo);
	if(mainVideo.equals("")){
		mainVideo = url+ "/img/default.mp4";	
	} else {
		
		mainVideo =url+ "/MainVideo/"+mainVideo;	
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
					<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Account </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">Action</a></li>
							<li><a class="dropdown-item" href="#">Another action</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">Something else
									here</a></li>
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
	<div class="ratio ratio-16x9">
		<iframe src="<%=mainVideo%>" title="YouTube video"
			allowfullscreen></iframe>
	</div>
	<div class="container">
		<div class="row">
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>
				<div class="ratio ratio-16x9">
					<iframe src="<%=url%>/img/video1.mp4" title="YouTube video"
						allowfullscreen></iframe>
				</div>
				<div class="card-body ">
					<h5 class="card-title" align="center">Fast And Furious</h5>
					<p class="card-text">This is a best beautiful place in the
						world</p>
				</div>

				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country : United State</li>
					<li class="list-group-item">Price : <label
						class="text-decoration-line-through">36.000</label></li>
					<li class="list-group-item text-success">Cost : 30.000</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>

			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>
				<img src="<%=url%>/img/index3.jpg" class="card-img-top" alt="...">
				<div class="card-body text-success">

					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>

				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>
				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
		</div>
		<div class="row">
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>

				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>

				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>

			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>
				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">

					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>

				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>

				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
		</div>
		<div class="row">
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>

				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>

				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>

			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>
				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">

					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>

				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>

				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
		</div>
		<div class="row">
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>

				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>

				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>

			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>
				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">

					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>

				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>

				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
			<div class="card border-secondary col-md-3">
				<div class="card-header" align="center">
					<img src="./assets/img/logo.png" alt="">
				</div>

				<img src="..." class="card-img-top" alt="...">
				<div class="card-body text-success">
					<h5 class="card-title">Success card title</h5>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Country</li>
					<li class="list-group-item">Price</li>
					<li class="list-group-item">Cost</li>
				</ul>
				<jsp:include page="Footer/buttonFooter.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<div>
		<%@ include file="Footer/footer.jsp"%>
	</div>

</body>
</html>