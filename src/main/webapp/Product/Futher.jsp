<%@page import="java.sql.Date"%>
<%@page import="model.Product"%>
<%@page import="dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Further Product</title>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();

%>
<link rel="icon" type="image/x-icon" href="<%=url%>/img/icon.png">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.108.0">
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.3/examples/checkout/">
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}
</style>
<%
String productID = ProductDao.getInstance().makeID();
System.out.print(productID);

String title = "";
// if( action = request.getAttribute("action"))
// To Do 
String e_file = request.getAttribute("e_file") + "";
e_file = e_file.equals("null") ? "" : e_file;
System.out.println("e_file" + e_file);
String e_productName = request.getAttribute("e_productName") + "";
e_productName = e_productName.equals("null") ? "" : e_productName;
System.out.println("e_productName" + e_productName);
String productName = "";
String author = "";
Date publishYear = null;
double cost = 0;
double price = 0;
int quantity = 0;
String type = "";
String country = "";
String language = "";
String description = "";
String setMain = "";
String fileName = "";

if (!e_productName.equals("") || !e_file.equals("")) {
	Object obj = session.getAttribute("Product");
	Product pd = (Product) obj;
	productID = pd.getProductID();
	productName = pd.getProductName();
	author = pd.getAuthor();
	publishYear = pd.getPublishYear();
	cost = pd.getCost();
	price = pd.getPrice();
	quantity = pd.getQuantity();
	type = pd.getType();
	country = pd.getCountry();
	language = pd.getLanguage();
	description = pd.getDescription();
	setMain = request.getAttribute("setMain") + "";
	fileName = pd.getPath();

}
%>
<!-- Custom styles for this template -->
<link href="checkout.css" rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container">
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4" src="<%=url%>/assets/img/logo.png"
				alt="" width="72" height="57">
			<h2><%=title%></h2>
			<p class="lead">Add Film's Parameters</p>
			<form class="needs-validation" novalidate
				enctype="multipart/form-data" method="post"
				action="<%=url%>/ProductController">
				<input type="hidden" name="action" value="addProduct"> <input
					type="hidden" name="productID" value="<%=productID%>">
				<div class="row g-3">
					<div class="col-sm-6">
						<label for="name" class="form-label">Name</label> <input
							type="text" class="form-control" id="productName"
							name="productName" placeholder="" required
							value="<%=productName%>">
						<div class="invalid-feedback">Valid name is required.</div>
						<span style="color : red"><%=e_productName%></span>
					</div>

					<div class="col-sm-6">
						<label for="author" class="form-label">Author</label> <input
							type="text" class="form-control" id="author" name="author"
							placeholder="" required value="<%=author%>">
						<div class="invalid-feedback">Valid last name is required.</div>
					</div>

					<div class="col-sm-6">
						<label for="price" class="form-label">Price</label> <input
							type="number" class="form-control" id="price" name="price"
							placeholder="" required value="<%=price%>">

						<div class="invalid-feedback">Valid price is required.</div>
					</div>

					<div class="col-sm-6">
						<label for="cost" class="form-label">Cost</label> <input
							type="number" class="form-control" id="cost" name="cost"
							placeholder="" required value="<%=cost%>">
						<div class="invalid-feedback">Valid cost is required.</div>
					</div>
					<div class="col-sm-6">
						<label for="publishYear" class="form-label">Publish Year</label> <input
							type="date" class="form-control" id="publishYear"
							name="publishYear" placeholder="" required
							value="<%=publishYear%>">
						<div class="invalid-feedback">Valid publish year is
							required.</div>
					</div>

					<div class="col-sm-6">
						<label for="quantiry" class="form-label">Quantity</label> <input
							type="number" class="form-control" id="quantity" name="quantity"
							placeholder="" required value="<%=quantity%>">
						<div class="invalid-feedback">Valid Quantity is required.</div>
					</div>

					<div class="col-sm-4">
						<label for="country" class="form-label">Country</label> <select
							class="form-select" id="country" name="country" required>
							<option ><%=country%></option>
							<option>United States</option>
						</select>
						<div class="invalid-feedback">Please select a valid country.</div>
					</div>
					<div class="col-sm-4">
						<label for="language" class="form-label">Language</label> <select
							class="form-select" id="language" name="language" required>
							<option value=""><%=language%></option>
							<option value="English">English</option>

						</select>
						<div class="invalid-feedback">Please select a valid
							language.</div>
					</div>
					<div class="col-sm-4">
						<label for="type" class="form-label">Type</label> <select
							class="form-select" id="type" name="type" required>
							<option value="<%=type%>"><%=type%></option>
							<option value="Funny">Funny</option>
						</select>
						<div class="invalid-feedback">Please select a valid type.</div>
					</div>
					<div class="col-sm-12">
						<label for="path" class="form-label">File's film</label> <input
							type="file" class="form-control" id="path" name="path" required
							value="<%=fileName%>">
						<div class="invalid-feedback">Please select film</div>
						<span style="color: red"><%=e_file%></span>

					</div>

					<div class="form-floating">
						<textarea class="form-control" placeholder="Leave a comment here"
							id="description" name="description" style="height: 100px"
							required><%=description%></textarea>
						<label for="floatingTextarea2">Describe</label>
						<div class="invalid-feedback">Please describe for this film</div>

						<span></span>

					</div>
				</div>
				<hr class="my-4">
				<div class="form-check" align="left">
					<input type="checkbox" class="form-check-input" id="setMainVideo"
						name="setMainVideo"> <label class="form-check-label"
						for="save-info">Set to the main video</label> <span></span>

				</div>


				<hr class="my-4">
				<button class="w-100 btn btn-primary btn-lg" type="submit">Confirm</button>
			</form>

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="<%=url%>/js/checkout.js"></script>
</body>
</html>