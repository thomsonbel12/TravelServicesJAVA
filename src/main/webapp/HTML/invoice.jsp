<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Invoice</title>
<link rel="icon" href="<c:url value="/assets/img/logo.png"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/invoice.css"/>">
</head>

<body>
	<div style="display: flex">
		<a href="/ProjectTravelServices/home" class="print-button">Trở về
			trang chủ</a>
		<!-- Print Button -->
		<a href="javascript:window.print()" class="print-button">Print
			this invoice</a>
	</div>


	<!-- Invoice -->
	<div id="invoice">

		<!-- Header -->
		<div class="row">
			<div class="col-md-6">
				<div id="logo">
					<img src="images/logo.png" alt="">
				</div>
			</div>

			<div class="col-md-6">

				<p id="details">
					<strong>Order:</strong> #${OrderId} <br> <strong>Issued:</strong>
					${Date} <br> Due 7 days from date of issue
				</p>
			</div>
		</div>


		<!-- Client & Supplier -->
		<div class="row">
			<div class="col-md-12">
				<h2>Invoice</h2>
			</div>

			<div class="col-md-6">
				<strong class="margin-bottom-5">Supplier</strong>
				<p>
					LHT C. <br> 498, Lê Văn Việt <br> Tăng Nhơn Phú A, Thủ
					Đức <br>
				</p>
			</div>

			<div class="col-md-6">
				<strong class="margin-bottom-5">Customer</strong>
				<p>
					${NameCustomer} <br> SĐT: ${PhoneNumber} <br> Email:
					${Email} <br>
				</p>
			</div>
		</div>


		<!-- Invoice -->
		<div class="row">
			<div class="col-md-12">
				<table class="margin-top-20">
					<tr>
						<th>Hotel</th>
						<th>Quantity</th>
						<th>Total</th>
					</tr>
					<c:forEach items="${listCartsSelected}" var="o">
						<tr>
							<td>${o.getName_hotel()}</td>
							<td>${o.getTotal_rooms()}</td>
							<td>${o.getTotalPrice()}</td>
						</tr>
					</c:forEach>

				</table>
			</div>

			<div class="col-md-4 col-md-offset-8">
				<table id="totals">
					<tr>
						<th>Total Due</th>
						<th><span>${Total} VNĐ</span></th>
						<p>Hình thức thanh toán: ${sessionScope.Type_Payment}</p>
					</tr>
				</table>
			</div>
		</div>


		<!-- Footer -->
		<div class="row">
			<div class="col-md-12">
				<ul id="footer">
					<li><span>TravelServices</span></li>
					<li>haovo1512@gmail.com</li>
					<li>(+84)799 197 703</li>
				</ul>
			</div>
		</div>

	</div>
</body>

</html>