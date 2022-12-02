<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"
	integrity="sha512-NhSC1YmyruXifcj/KFRWoC561YpHpc5Jtzgvbuzx5VozKpWvQ+4nXhPdFgmx8xqexRcpAglTj9sIBWINXa8x5w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="icon" href="<c:url value="/assets/img/landlordLogo.png"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/fonts/fontawesome-free-6.2.0-web/css/all.min.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/base.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/main.css"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/landlordCSS/landLordIndex.css"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/landlordCSS/landlordSidebar.css"/>">
<title>Chủ Nhà Nè</title>
</head>
<body>
	<div id="body" class="app__landLord app__landLord-home">
		<div class="app_header">
			<div class="app__header-user">
				<div class="app__header-user-display">
					<!-- <div class="app__header-user-display" onclick="user_menu_click()"> -->
					<div class="app__header-user-btn">
						<img src="${sessionScope.acclandlord.getAvatar()}" alt=""
							class="user-img">
					</div>
					<i class="fa-sharp fa-solid fa-caret-down"></i>

				</div>

				<div class="app__header-user-menu">
					<div class="user-menu-text">TÀI KHOẢN CỦA TÔI</div>
					<div class="user-menu-option">
						<ul>
							<li><i class="fa-solid fa-list"></i> <!-- <a href="">Đơn đặt chỗ của tôi</a> -->
								Khách Sạn của Tôi</li>

							<li><i class="fa-solid fa-envelope"></i> <!-- <a href="">Hộp thư</a> -->
								Hộp thư</li>

							<li><i class="fa-solid fa-heart"></i> <!-- <a href="">Hồ sơ của tôi</a> -->
								Thống kê</li>

							<li><i class="fa-solid fa-address-card"></i> <!-- <a href="">Hồ sơ của tôi</a> -->
								Hồ sơ của tôi</li>

							<span class="data-number"> Đặc biệt </span>

							<li class="landlord-register"
								onclick="location.href='/ProjectTravelServices/home'"><i
								class="fa-solid fa-house-flag"></i> Làm người đi thuê</li>

							<button
								onclick="location.href='/ProjectTravelServices/loginlandlord'"
								class="user-menu-option-logout">
								<i class="fa-solid fa-right-from-bracket"></i> Đăng xuất
							</button>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="app_sidebar">
			<div class="sidebar_list">
				<ul class="sidebar_list-content">
					<li class="sidebar_item logo_img"><a href="#"> <img
							src="<c:url value="/assets/img/landlordLogo.png"/>" alt="">
					</a></li>
					<li class="sidebar_item active sidebar_item-home"><a
						href="#home"> <span class="sidebar_item-icon"> <i
								class="fa-solid fa-house"></i>
						</span> <span class="sidebar_item-name">Trang Chủ</span>
					</a></li>
					<li class="sidebar_item sidebar_item-own"><a href="#"> <span
							class="sidebar_item-icon"> <i
								class="fa-solid fa-building-user"></i>
						</span> <span class="sidebar_item-name">Sở Hữu</span>
					</a></li>
					<li class="sidebar_item sidebar_item-customer"><a href="#">
							<span class="sidebar_item-icon"> <i
								class="fa-solid fa-users"></i>
						</span> <span class="sidebar_item-name">Khách Hàng</span>
					</a></li>
					<li class="sidebar_item sidebar_item-statis"><a href="#">
							<span class="sidebar_item-icon"> <i
								class="fa-solid fa-chart-line"></i>
						</span> <span class="sidebar_item-name">Số Liệu</span>
					</a></li>
					<li class="sidebar_item sidebar_item-profile"><a href="#">
							<span class="sidebar_item-icon"> <i
								class="fa-solid fa-id-card-clip"></i>
						</span> <span class="sidebar_item-name">Tài Khoản</span>
					</a></li>
				</ul>
				<div class="sidebar_bottom-btn"></div>
			</div>
		</div>
		<div class="app_body">
			<div id="app_body-content-home"
				class="app_body-content app_body-content-home">
				<div
					class="app_body-content-item app_body-content-header semiBlack-text fontWeight-600">
					<h3>TRANG CHỦ</h3>
				</div>
				<div class="app_body-content-item overall_content">
					<div style="background-color: #fec106;"
						class="overall_content-item">

						<div class="overall_content-item-body">
							<div class="overall_content-data">
								<div class="overall_content-data-name">Tài sản của Tôi</div>
								<div class="overall_content-data-number">
									<span class="data-number"> ${SoKhachSan} </span> <span
										class="data-number-extension">Khách sạn, căn hộ</span>
								</div>
							</div>

							<i class="fa-solid fa-building-user"></i>
						</div>
						<div style="background-color: #d2a725;"
							class="overall_content-item-info">
							<a href="#"> Chi tiết <i class="fa-solid fa-circle-right"></i>
							</a>
						</div>
					</div>
					<div style="background-color: #16a1b9;"
						class="overall_content-item">
						<div class="overall_content-item-body">
							<div class="overall_content-data">
								<div class="overall_content-data-name">Tổng số lượng khách</div>
								<div class="overall_content-data-number">
									<span class="data-number"> ${SoKhachHang} </span> <span
										class="data-number-extension">Khách hàng</span>
								</div>
							</div>
							<i class="fa-solid fa-users"></i>
						</div>
						<div style="background-color: #1892a6;"
							class="overall_content-item-info">
							<a href="#"> Chi tiết <i class="fa-solid fa-circle-right"></i>
							</a>
						</div>
					</div>
					<div style="background-color: #29a643;"
						class="overall_content-item">
						<div class="overall_content-item-body">
							<div class="overall_content-data">
								<div class="overall_content-data-name">Doanh Thu (VNĐ)</div>
								<div class="overall_content-data-number">
									<span class="data-number"> ${TongSoTien} </span> <span
										class="data-number-extension">VNĐ</span>
								</div>
							</div>
							<i class="fa-solid fa-chart-line"></i>
						</div>
						<div style="background-color: #16842c;"
							class="overall_content-item-info">
							<a href="#"> Chi tiết <i class="fa-solid fa-circle-right"></i>
							</a>
						</div>
					</div>
					<div style="background-color: #dc3547;"
						class="overall_content-item">

						<div class="overall_content-item-body">
							<div class="overall_content-data">
								<div class="overall_content-data-name">Đơn đặt</div>
								<div class="overall_content-data-number">
									<span class="data-number"> ${TongDonDat} </span> <span
										class="data-number-extension">Đơn đặt phòng</span>
								</div>
							</div>
							<i class="fa-solid fa-book"></i>
						</div>
						<div style="background-color: #c02635;"
							class="overall_content-item-info">
							<a href="#"> Chi tiết <i class="fa-solid fa-circle-right"></i>
							</a>
						</div>
					</div>
				</div>

				<div class="app_body-content-item content-home-detail"
					style="margin-top: 48px;">
					<div class="recent-list recentOrder">
						<div class="content-detail-header">
							<h2 class="semiBlack-text">Đơn đặt phòng gần đây</h2>
							<a href="#" class="padding-8 blue-button">Xem tất cả</a>
						</div>
						<table>
							<thead>
								<tr>
									<td>Tên khách sạn</td>
									<td>Giá tiền</td>
									<td>Phương thức thanh toán</td>
									<td>Tình trạng</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Khách sạn tềnh iu 1</td>
									<td>1,200,000</td>
									<td class="fontWeight-600">Khi nhận phòng</td>
									<td><span class="status notcheckedin">Chờ nhận
											phòng</span></td>
								</tr>
								<tr>
									<td>Khách sạn tềnh iu 1</td>
									<td>1,200,000</td>
									<td class="fontWeight-600">Khi nhận phòng</td>
									<td><span class="status notcheckedin">Chờ nhận
											phòng</span></td>
								</tr>
								<tr>
									<td>Khách sạn tềnh iu 1</td>
									<td>1,200,000</td>
									<td class="fontWeight-600">Khi nhận phòng</td>
									<td><span class="status notcheckedin">Chờ nhận
											phòng</span></td>
								</tr>
								<tr>
									<td>Khách sạn tềnh iu 1</td>
									<td>1,200,000</td>
									<td class="fontWeight-600">Khi nhận phòng</td>
									<td><span class="status notcheckedin">Chờ nhận
											phòng</span></td>
								</tr>
								<tr>
									<td>Khách sạn tềnh iu 1</td>
									<td>1,200,000</td>
									<td class="fontWeight-600">Khi nhận phòng</td>
									<td><span class="status notcheckedin">Chờ nhận
											phòng</span></td>
								</tr>
								<tr>
									<td>Khách sạn tềnh iu 2</td>
									<td>2,200,000</td>
									<td class="fontWeight-600">Khi nhận phòng</td>
									<td><span class="status notcheckedin">Chờ nhận
											phòng</span></td>
								</tr>
								<tr>
									<td>Khách sạn tềnh iu 3</td>
									<td>1,200,000</td>
									<td class="fontWeight-600">MOMO</td>
									<td><span class="status checkedin">Đã nhận phòng</span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="recent-list recentCustomer">
						<div class="content-detail-header">
							<h2 class="semiBlack-text">Khách hàng gần đây</h2>
							<a href="#" class="padding-8 blue-outline-button">Xem tất cả</a>
						</div>
						<table>
							<thead>
								<tr>
									<td>Avatar</td>
									<td>Tên Khách hàng</td>
									<!-- <td>Số lượng đơn</td> -->
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${top5RecentUsers}" var="o">
									<tr>
										<td>
											<div class="recent-customer-avatar">
												<img src="${o.getAvatar() }" alt="">
											</div>
										</td>
										<td>${o.getName_user()}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>

			<!-- <div id="app_body-content-property" class="app_body-content app_body-content-property">
                <div class="app_body-content-item app_body-content-header semiBlack-text fontWeight-600">
                    <h3>TÀI SẢN CỦA TÔI</h3>
                </div>
                <div class="app_body-content-item">
                    
                </div>

                
            </div> -->
		</div>
	</div>

	<script type="module"
		src="<c:url value="/scriptLanlord/landlordSidebar.js"/>"></script>
	<!-- Mở menu user -->
	<script>
		const app = document.querySelector('.app__header-user-display');
		const menuClick = document.querySelector('.app__header-user-menu');
		function user_menu_click() {
			// hide()
			menuClick.classList.toggle("show");
		}

		app.addEventListener('click', user_menu_click);
		menuClick.addEventListener('click', function() {
			event.stopPropagation();
		});
		app.addEventListener('click', function() {
			event.stopPropagation();
		});
	</script>

	<!-- Mở sidebar -->
	<script>
		const app_header = document.querySelector('.app_header');
		const app_body = document.querySelector('.app_body');
		const sidebar_list = document.querySelector(".sidebar_list");
		const sidebar_bottom_btn = document
				.querySelector(".sidebar_bottom-btn");

		const recentOrder = document.querySelector('.recentOrder')
		const recentCustomer = document.querySelector('.recentCustomer')

		function checkSidebar() {
			if (sidebar_list.classList.contains('active')) {
				app_body.style.marginLeft = "240px";

				recentOrder.style.width = "100%";
				recentCustomer.style.width = "100%";

			} else {
				app_body.style.marginLeft = "100px";

				recentOrder.style.width = "800px";
				recentCustomer.style.width = "550px";
			}
		}
		function sidebar_listToggle() {
			// hide()
			menuClick.classList.remove("show");
			sidebar_list.classList.toggle('active');
			checkSidebar()
		}
		sidebar_bottom_btn.addEventListener('click', sidebar_listToggle);

		sidebar_list.addEventListener('click', function(e) {
			e.stopPropagation();
		});
		sidebar_bottom_btn.addEventListener('click', function(e) {
			e.stopPropagation();
		});
	</script>

	<script>
		function hide() {
			menuClick.classList.remove("show");
			// sidebar_list.classList.remove('active');
			checkSidebar()
		}
		document.addEventListener('click', function() {
			hide();
		})
	</script>
</body>
</html>