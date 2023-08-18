<%@page import="model.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.AdminDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<title>Super user page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
</head>
<body class="py-4">
	<%
	ArrayList<Admin> list = (ArrayList<Admin>) session.getAttribute("adminList");
	for (Admin ad : list) {
		System.out.print(ad.toString());
	}
	%>

	<main>
		<div class="container">

			<h1>Bootstrap grid examples</h1>
			<p class="lead">Basic grid layouts to get you familiar with
				building within the Bootstrap grid system.</p>
			<p>
				In these examples the
				<code>.themed-grid-col</code>
				class is added to the columns to add some theming. This is not a
				class that is available in Bootstrap by default.
			</p>

			<h2 class="mt-4">Admin Accounts</h2>
			<p>There are all admin accounts in my database :</p>

				<div class="row mb-3 text-center">
				<div class="col-md-3 themed-grid-col">ID</div>
				<div class="col-md-3 themed-grid-col">User name</div>
				<div class="col-md-3 themed-grid-col">Email</div>
				<div class="col-md-3 themed-grid-col">Phone number</div>

			<%
			for (Admin ad : list) {
			%>
				<div class="row mb-3 text-center">
				<div class="col-md-3 themed-grid-col"><%=ad.getAdminID() %></div>
				<div class="col-md-3 themed-grid-col"><%=ad.getUserName() %></div>
				<div class="col-md-3 themed-grid-col"><%=ad.getEmailRegister() %></div>
				<div class="col-md-3 themed-grid-col"><%=ad.getPhoneNumber() %></div>

			</div>

			<%
			}
			%>


			<h2 class="mt-4">Overall Products</h2>
			<p>
				By using the
				<code>.row-cols-*</code>
				classes, you can easily create a grid with equal columns.
			</p>
			<div class="row row-cols-md-3 mb-3 text-center">
				<div class="col themed-grid-col">
					<code>.col</code>
					child of
					<code>.row-cols-md-3</code>
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					child of
					<code>.row-cols-md-3</code>
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					child of
					<code>.row-cols-md-3</code>
				</div>
			</div>

			<h2 class="mt-4">Three unequal columns</h2>
			<p>
				Get three columns <strong>starting at desktops and scaling
					to large desktops</strong> of various widths. Remember, grid columns should
				add up to twelve for a single horizontal block. More than that, and
				columns start stacking no matter the viewport.
			</p>
			<div class="row mb-3 text-center">
				<div class="col-md-3 themed-grid-col">.col-md-3</div>
				<div class="col-md-6 themed-grid-col">.col-md-6</div>
				<div class="col-md-3 themed-grid-col">.col-md-3</div>
			</div>

			<h2 class="mt-4">Two columns</h2>
			<p>
				Get two columns <strong>starting at desktops and scaling to
					large desktops</strong>.
			</p>
			<div class="row mb-3 text-center">
				<div class="col-md-8 themed-grid-col">.col-md-8</div>
				<div class="col-md-4 themed-grid-col">.col-md-4</div>
			</div>

			<h2 class="mt-4">Full width, single column</h2>
			<p class="text-warning">No grid classes are necessary for
				full-width elements.</p>

			<hr class="my-4">

			<h2 class="mt-4">Two columns with two nested columns</h2>
			<p>
				Per the documentation, nesting is easy—just put a row of columns
				within an existing column. This gives you two columns <strong>starting
					at desktops and scaling to large desktops</strong>, with another two (equal
				widths) within the larger column.
			</p>
			<p>At mobile device sizes, tablets and down, these columns and
				their nested columns will stack.</p>
			<div class="row mb-3 text-center">
				<div class="col-md-8 themed-grid-col">
					<div class="pb-3">.col-md-8</div>
					<div class="row">
						<div class="col-md-6 themed-grid-col">.col-md-6</div>
						<div class="col-md-6 themed-grid-col">.col-md-6</div>
					</div>
				</div>
				<div class="col-md-4 themed-grid-col">.col-md-4</div>
			</div>

			<hr class="my-4">

			<h2 class="mt-4">Mixed: mobile and desktop</h2>
			<p>The Bootstrap v5 grid system has six tiers of classes: xs
				(extra small, this class infix is not used), sm (small), md
				(medium), lg (large), xl (x-large), and xxl (xx-large). You can use
				nearly any combination of these classes to create more dynamic and
				flexible layouts.</p>
			<p>Each tier of classes scales up, meaning if you plan on setting
				the same widths for md, lg, xl and xxl, you only need to specify md.</p>
			<div class="row mb-3 text-center">
				<div class="col-md-8 themed-grid-col">.col-md-8</div>
				<div class="col-6 col-md-4 themed-grid-col">.col-6 .col-md-4</div>
			</div>
			<div class="row mb-3 text-center">
				<div class="col-6 col-md-4 themed-grid-col">.col-6 .col-md-4</div>
				<div class="col-6 col-md-4 themed-grid-col">.col-6 .col-md-4</div>
				<div class="col-6 col-md-4 themed-grid-col">.col-6 .col-md-4</div>
			</div>
			<div class="row mb-3 text-center">
				<div class="col-6 themed-grid-col">.col-6</div>
				<div class="col-6 themed-grid-col">.col-6</div>
			</div>

			<hr class="my-4">

			<h2 class="mt-4">Mixed: mobile, tablet, and desktop</h2>
			<div class="row mb-3 text-center">
				<div class="col-sm-6 col-lg-8 themed-grid-col">.col-sm-6
					.col-lg-8</div>
				<div class="col-6 col-lg-4 themed-grid-col">.col-6 .col-lg-4</div>
			</div>
			<div class="row mb-3 text-center">
				<div class="col-6 col-sm-4 themed-grid-col">.col-6 .col-sm-4</div>
				<div class="col-6 col-sm-4 themed-grid-col">.col-6 .col-sm-4</div>
				<div class="col-6 col-sm-4 themed-grid-col">.col-6 .col-sm-4</div>
			</div>

			<hr class="my-4">

			<h2 class="mt-4">Gutters</h2>
			<p>
				With
				<code>.gx-*</code>
				classes, the horizontal gutters can be adjusted.
			</p>
			<div class="row row-cols-1 row-cols-md-3 gx-4 text-center">
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gx-4</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gx-4</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gx-4</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gx-4</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gx-4</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gx-4</code>
					gutters
				</div>
			</div>
			<p class="mt-4">
				Use the
				<code>.gy-*</code>
				classes to control the vertical gutters.
			</p>
			<div class="row row-cols-1 row-cols-md-3 gy-4 text-center">
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gy-4</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gy-4</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gy-4</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gy-4</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gy-4</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.gy-4</code>
					gutters
				</div>
			</div>
			<p class="mt-4">
				With
				<code>.g-*</code>
				classes, the gutters in both directions can be adjusted.
			</p>
			<div class="row row-cols-1 row-cols-md-3 g-3 text-center">
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.g-3</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.g-3</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.g-3</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.g-3</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.g-3</code>
					gutters
				</div>
				<div class="col themed-grid-col">
					<code>.col</code>
					with
					<code>.g-3</code>
					gutters
				</div>
			</div>
		</div>
	</main>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>