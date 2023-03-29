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
</head>
<body>
	<!-- Start Navbar-->
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01"
				aria-controls="navbarTogglerDemo01" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
				<a class="navbar-brand" href="#"> <img src="logo1.webp"
					alt="Bootstrap" width="30" height="24">
				</a>

				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Sale</a></li>
					<li class="nav-item"><a class="nav-link" href="#">News</a></li>
					<li class="nav-item"><a class="nav-link disabled">Disabled</a>
					</li>
				</ul>
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success" type="submit"
						value="search">Search</button>
				</form>
				<form class="d-flex" action="GET">
					<button class="btn btn-outline-success" type="submit" value="login">Logout</button>
				</form>

			</div>
		</div>
	</nav>

	<!-- End Navbar -->

	<div id="carouselExampleAutoplaying" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="img/hinh-nen-tet-12.png" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="img/hinh-nen-tet-11.png" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="img/hinh-nen-tet-17.png" class="d-block w-100" alt="...">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
	<!-- Page Contents -->

	<!-- Header Page Contents -->

	<!-- Contact Us  -->

	<div class="container-fluid">
		<div class=""></div>
	</div>
	<div class="container-fluid text-center">
		<div class="row">
			<div class="col">
				<div class="card" style="width: 24rem;">
					<img src="img/hinh-nen-tet-14.png" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Family Picture</h5>

					</div>


				</div>
			</div>
			<div class="col">
				<div class="card" style="width: 24rem;">
					<img src="img/hinh-nen-tet-11.png" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Chung cake and Day cake</h5>

					</div>


				</div>
			</div>
			<div class="col">

				<div class="card" style="width: 24rem;">
					<img src="img/hinh-nen-tet-7.png" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Li Xi</h5>

					</div>

				</div>

			</div>
			<div class="col">
				<div class="card" style="width: 24rem;">
					<img src="img/hinh-nen-tet-18.png" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Tet's Picture</h5>

					</div>
				</div>
			</div>
		</div>


		<!-- Contact Us  -->
		<!-- Contact Us  -->
		<center>
			<p align="center">
			<h2 color="orange">Contact Us</h2>
			</p>
		</center>
		<!--  Customer Contact -->
		<div class="mb-3" onmouseenter="changeText()"
			onkeypress="changeText()">
			<label for="email" class="form-label">Email address</label> <input
				type="email" class="form-control" id="email"
				placeholder="name@example.com">
		</div>
		<div>
			<label for="fullname" class="form-label">Tell us about your
				name</label> <input type="text" class="form-control" id="fullname">
		</div>
		<div class="mb-3">
			<label for="textarea" class="form-label">Note for seller </label>
			<textarea class="form-control" id="texarea" rows="3"></textarea>
		</div>
	</div>

</body>

</body>
</html>