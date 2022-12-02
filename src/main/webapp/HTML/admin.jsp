<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css"
	rel="stylesheet">
<title>Admin Page</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body class="p-0 m-0 border-0 bd-example">

	<div>
		<nav class="navbar navbar-expand-lg bg-light">
			<div class="container-fluid">
				<a class="navbar-brand" href="/ProjectTravelServices/admin">Admin</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/ProjectTravelServices/home">Trang Chủ</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Số liệu</a>
						</li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> Người dùng </a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="adminRole?role=1">Người
										thuê nhà</a></li>
								<li><a class="dropdown-item" href="adminRole?role=2">Người
										Cho Thuê</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item" href="adminRole?role=0">Xem
										tất cả</a></li>
							</ul></li>
						<!-- <li class="nav-item">
                        <a class="nav-link disabled">Disabled</a>
                    </li> -->
					</ul>
					<form action="adminSearch" method="post" class="d-flex"
						role="search">
						<input name="search_input" class="form-control me-2" type="search"
							placeholder="Search by Name" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>
			</div>
		</nav>
	</div>

	<div>
		<c:if test="${sessionScope.Role!='2'}">
			<table id="user" class="table">
				<span>
					<h1>
						Người Thuê (<span>${CountUsers}</span> Tài Khoản)
					</h1>
				</span>
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Tên</th>
						<th scope="col">Số lượng đơn</th>
						<th scope="col">Chỉnh sửa</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ListAllUsers}" var="y">
						<tr class="table-primary">
							<th scope="row">${y.getUser_id()}</th>
							<td>${y.getName_user()}</td>
							<td>${y.getTotalOrder()}</td>
							<td class="table-danger"><a
								href="deleteUser?UserID=${y.getUser_id()}"> Xóa tài khoản </a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</c:if>

		<c:if test="${sessionScope.Role!='1'}">
			<table id="landlord" class="table">
				<span>
					<h1>
						Người Cho Thuê (<span>${CountLandLords}</span> Tài Khoản)
					</h1>
				</span>
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Tên</th>
						<th scope="col">Số lượng đơn</th>
						<th scope="col">Tiền lời</th>
						<th scope="col">Chỉnh sửa</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ListAllLandLords}" var="y">
						<tr class="table-primary">
							<th scope="row">${y.getLandLord_id()}</th>
							<td>${y.getName_LandLord()}</td>
							<td>${y.getTotalOrder()}</td>
							<td class="profit_admin">${y.getProfit()} <span style="font-weight: 600">VNĐ</span></td>
							<td class="table-danger"><a
								href="deleteLandlord?LandlordId=${y.getLandLord_id()}"> Xóa
									tài khoản </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<script type="module" src="<c:url value="/script/numbeWithComma.js"/>"></script>
	<script type="module">
		import { numberWithCommas, removeCommas } from "./script/numbeWithComma.js";
		const profit_admin = document.getElementsByClassName("profit_admin")
		for (let i = 0; i < profit_admin.length; i++) {
			profit_admin[i].innerHTML = numberWithCommas(profit_admin[i].innerHTML);
		}
	</script>
</body>
</html>