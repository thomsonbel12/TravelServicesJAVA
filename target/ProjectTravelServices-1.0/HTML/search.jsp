<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<title>Travel Services</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- reset css -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"
	integrity="sha512-NhSC1YmyruXifcj/KFRWoC561YpHpc5Jtzgvbuzx5VozKpWvQ+4nXhPdFgmx8xqexRcpAglTj9sIBWINXa8x5w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="icon" href="<c:url value="/assets/img/logo.png"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/fonts/fontawesome-free-6.2.0-web/css/all.min.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/main.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/base.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/calendar.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/cart.css"/>">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> -->
</head>

<body>

	<div id="app" class="app__main">
		<div id="header" class="app__header">
			<div class="app__header-logo">
				<a href="/ProjectTravelServices/home" class="app__header-logo">
					<img src="<c:url value ="/assets/img/logo.png"/>" alt=""
					class="app__header-logo--link">
				</a>
			</div>
			<c:if test="${sessionScope.acc==null}">
				<div class="app__header-getin">
					<div class="app__header-login">
						<button onclick="location.href='/ProjectTravelServices/login'"
							class="app__header-button">
							<!-- <a href="#" class="app_header-login--link"> -->
							????ng nh???p
							<!-- </a> -->
						</button>
					</div>

					<div class="app__header-signin">
						<button onclick="location.href='/ProjectTravelServices/signup'"
							class="app__header-button">
							<!-- <a href="dangKi.html" class="app_header-login--link"> -->
							T???o t??i kho???n
							<!-- </a> -->
						</button>
					</div>

					<div class="app__header-user" style="display: none">
						<div class="app__header-user-display"></div>
						<div class="app__header-user-menu"></div>
					</div>
				</div>
			</c:if>

			<c:if test="${sessionScope.acc!=null}">
				<div class="app__header-option">
					<a class="rgbRed_OutlineButton button-padding_12">????ng k?? cho
						thu?? nh??</a> <span class="shopping_cart"> <a
						class="rgbRed-text" href="/ProjectTravelServices/cartdetail"> <i
							class="fa-solid fa-cart-shopping"> <span
								class="cart-item-number total_cart_room total_cart_room"></span>
						</i>
					</a>
						<div class="shopping_cart-items">
							<div class="cart-text">Danh s??ch c???a t??i</div>

							<div class="cart-list">

								<c:forEach items="${sessionScope.listCarts}" var="y">
									<div class="cart-item">
										<div class="cart-item-img">
											<img
												src="${y.getImage()}"
												alt="">
										</div>
										<div class="cart-item-detail">
											<div class="cart-item-name">${y.getName_hotel()}</div>
											<div class="cart-item-desc">
												<div class="cart-item-date">
													<label style="color: #333;">Ng??y nh???n ph??ng</label>
													<div class="cart-day">${y.getCheckIn_date()}</div>
												</div>
												<div class="cart-item-people">
													<div style="color: #333;">1 ph??ng</div>
													<div>2 ng?????i l???n, 1 tr??? em</div>
												</div>
												<div class="cart-item-money">
													${y.getPrice_room()} &nbsp;<span>VN??</span>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
	
							</div>

							<div class="cart-item cart-item-detail-link">
								<p>
									C?? <span class="rgbRed-text fontWeight-700 total_cart_room"></span>
									ph??ng trong danh s??ch
								</p>
								<a class="rgbRed_OutlineButton button-padding_12"
									href="/ProjectTravelServices/cartdetail">Chi ti???t danh s??ch</a>
							</div>
						</div>
					</span>
				</div>
				<div class="app__header-user">
					<div class="app__header-user-display">
						<!-- <div class="app__header-user-display" onclick="user_menu_click()"> -->
						<div class="app__header-user-btn">
							<img src="${sessionScope.avatar}" alt="" class="user-img">
						</div>
						<i class="fa-sharp fa-solid fa-caret-down"></i>
					</div>

					<div class="app__header-user-menu">
						<div class="user-menu-text">T??I KHO???N C???A T??I</div>
						<div class="user-menu-option">
							<ul>
								<li><i class="fa-solid fa-list"></i> <!-- <a href="">????n ?????t ch??? c???a t??i</a> -->
									????n ?????t ch??? c???a t??i</li>

								<li><i class="fa-solid fa-envelope"></i> <!-- <a href="">H???p th??</a> -->
									H???p th??</li>

								<li><i class="fa-solid fa-heart"></i> <!-- <a href="">H??? s?? c???a t??i</a> -->
									Danh s??ch y??u th??ch</li>

								<li onclick="location.href='/ProjectTravelServices/myprofile'"><i
									class="fa-solid fa-address-card"></i> H??? s?? c???a t??i</li>

								<span> ?????c bi???t </span>

								<li class="landlord-register"><i
									class="fa-solid fa-house-flag"></i> ????ng k?? cho thu?? nh??</li>
								<button class="user-menu-option-logout"
									onclick="location.href='logout'">
									<i class="fa-solid fa-right-from-bracket"></i> ????ng xu???t
								</button>

							</ul>
						</div>
					</div>
				</div>
			</c:if>


		</div>
		<div class="app__body">
			<div class="search-container">
				<div class="search-container-text">
					<h2>T??M T??? ???M V???I LHT C.</h2>
					<p>R???ng r??i h??n, ch??n th???c h??n, nhi???u l?? do ????? ??i du l???ch h??n.
					</p>
				</div>

				<div class="search-container-all">
					<div class="search-container-list">
						<ul>
							<li id="hotel-category"
								class="search-category search-container-select-hotel"><i
								class="fa-solid fa-hotel"></i> <span>Kh??ch s???n & Nh??</span></li>
							<li id="house-category"
								class="search-category search-container-select-house"><i
								class="fa-solid fa-house-flag"></i> <span>Ch??? ??? ri??ng</span></li>
							<li id="xxx0-category"
								class="search-category search-container-select-xxx0"><i
								class="fa-solid fa-wrench"></i> <span>C???p nh???t sau</span></li>
							<li id="xxx1-category"
								class="search-category search-container-select-xxx1"><i
								class="fa-solid fa-wrench"></i> <span>C???p nh???t sau</span></li>
							<li id="xxx2-category"
								class="search-category search-container-select-xxx2"><i
								class="fa-solid fa-wrench"></i> <span>C???p nh???t sau</span></li>
							<li id="xxx3-category"
								class="search-category search-container-select-xxx3"><i
								class="fa-solid fa-wrench"></i> <span>C???p nh???t sau</span></li>
						</ul>
					</div>

					<div class="search-container-content">
						<form class="search-content-item" action="search" method="post">
							<div id="item-category-hotel"
								class="content-item-category content-item-category-hotel">
								<button
									class="content-item-button blue-button button-padding_12">
									Ch??? ??? qua ????m</button>
								<button
									class="content-item-button blue-outline-button button-padding_12">
									Ch??? ??? trong ng??y</button>

								<div class="content-item-input-text">
									<!-- <i class="fa-light fa-magnifying-glass"></i> -->
									<label for="place-input"> <i
										class="fa-solid fa-magnifying-glass"></i>
									</label> <input name="search_input" id="place-input" type="text"
										placeholder="Nh???p ?????a ??i???m du l???ch ho???c t??n Kh??ch s???n">
									<div class="item-input-search-wrap">
										<div class="item-input-search-list">
											<ul>
												<li class="search-list-item">

													<div>
														<i class="fa-solid fa-location-dot"></i>
													</div>
													<div class="search-list-item-text">
														<p class="search-item-place">H??? Ch?? Minh</p>
														<p class="search-item-desc">Th??nh Ph???</p>
													</div>
												</li>
												<li class="search-list-item">
													<div>
														<i class="fa-sharp fa-solid fa-bed"></i>
													</div>
													<div class="search-list-item-text">
														<p class="search-item-place">Hotel Plaza 5 sao</p>
														<p class="search-item-desc">Kh??ch S???n</p>
													</div>
												</li>
												<li class="search-list-item">
													<div>
														<i class="fa-solid fa-location-dot"></i>
													</div>
													<div class="search-list-item-text">
														<p class="search-item-place">H??? Ch?? Minh</p>
														<p class="search-item-desc">Th??nh Ph???</p>
													</div>
												</li>
											</ul>
										</div>
									</div>
								</div>

								<div class="content-input-selection">
									<div class="content-input-date-select">
										<div class="input-date-wrap input-date-checkin">
											<i class="far fa-calendar-alt"></i> <input id="SelectDate"
												placeholder="Ng??y Nh???n ph??ng">

										</div>
										<div class="input-date-wrap input-date-checkout">
											<i class="far fa-calendar-alt"></i> <input id="SelectDate"
												placeholder="Ng??y Tr??? ph??ng">
										</div>

									</div>
									<div class="content-input-people-room">
										<div class="input-people-room-text">
											<i class="fa-solid fa-users"></i>
											<div class="input-people-text">
												<div class="input-people">
													<span class="adultNum">1</span> ng?????i l???n, <span
														class="childNum">0</span> tr??? em
												</div>

												<div class="input-room">1 ph??ng</div>
											</div>

											<i class="fa-solid fa-chevron-down point-down"></i>
										</div>

										<div class="input-add-room-people">
											<div class="input-add add-room">
												<div class="input-add-text">
													<div class="add-text-title">Ph??ng</div>
													<div class="add-text-desc"></div>
												</div>

												<div class="input-add-btn">
													<i style="visibility: hidden;"
														class="fa-regular fa-square-minus"></i> <span
														class="input-add-number rooms">1</span> <i
														style="visibility: hidden;"
														class="fa-solid fa-square-plus"></i>
												</div>
											</div>
											<div class="input-add add-adult">
												<div class="input-add-text">
													<div class="add-text-title">Ng?????i l???n</div>
													<div class="add-text-desc">18 tu???i tr??? l??n</div>
												</div>

												<div class="input-add-btn">
													<i id="minusAdult"
														class="fa-solid fa-square-minus minusAdult"></i> <span
														id="adults" class="input-add-number adults">1</span> <i
														id="addAdult" class="fa-regular fa-square-plus addAdult"></i>
												</div>
											</div>
											<div class="input-add add-child">
												<div class="input-add-text">
													<div class="add-text-title">Tr??? em</div>
													<div class="add-text-desc">0-12 tu???i</div>
												</div>

												<div class="input-add-btn">
													<i id="minusChild"
														class="fa-regular fa-square-minus minusChild"></i> <span
														class="input-add-number children">0</span> <i
														id="addChild" class="fa-solid fa-square-plus addChild"></i>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="content-discount-wrap">
									<div class="content-discount-tag">
										<p>?????t g??i ti???t ki???m</p>
									</div>
									<div class="discount-input">
										<!-- <i class="fa-light fa-magnifying-glass"></i> -->
										<label for="discount-input-text"> <i
											class="fa-solid fa-magnifying-glass"></i>
										</label> <input id="discount-input-text" type="text"
											placeholder="Nh???p ho???c ch???n khuy???n m??i">
									</div>
								</div>

							</div>

							<div id="item-category-house"
								class="content-item-category content-item-category-house">
								<button
									class="content-item-button blue-button button-padding_12">
									Ch??? ??? qua ????m</button>
								<button
									class="content-item-button blue-outline-button button-padding_12">
									Ch??? ??? trong ng??y</button>
								<div id="house-text">
									<img height="20" width="20" alt="dayuse-icon"
										src="//cdn6.agoda.net/images/esc-web/dayuse-icon.svg">
									<p>
										<span> Ch??? ??? ri??ng </span> <span> l?? n??i ??? l??u d??i, t???
										</span> <span> 1 - 2 th??ng tr??? l??n. </span> <span> Ph?? h???p v???i
											nh???ng kh??ch h??ng c?? nhu c???u ??i du l???ch, c??ng t??c trong th???i
											gian d??i m?? mu???n ti???t ki???m chi ph??. </span>
									</p>
								</div>
								<div class="content-item-input-text">
									<i class="fa-light fa-magnifying-glass"></i> <label
										for="place-input"> <i
										class="fa-solid fa-magnifying-glass"></i>
									</label> <input id="place-input" type="text"
										placeholder="Nh???p ?????a ??i???m du l???ch ho???c t??n Kh??ch s???n">
									<div class="item-input-search-wrap">
										<div class="item-input-search-list">
											<ul>
												<li class="search-list-item">

													<div>
														<i class="fa-solid fa-location-dot"></i>
													</div>
													<div class="search-list-item-text">
														<p class="search-item-place">H??? Ch?? Minh</p>
														<p class="search-item-desc">Th??nh Ph???</p>
													</div>
												</li>
												<li class="search-list-item">
													<div>
														<i class="fa-sharp fa-solid fa-bed"></i>
													</div>
													<div class="search-list-item-text">
														<p class="search-item-place">Hotel Plaza 5 sao</p>
														<p class="search-item-desc">Kh??ch S???n</p>
													</div>
												</li>
												<li class="search-list-item">
													<div>
														<i class="fa-solid fa-location-dot"></i>
													</div>
													<div class="search-list-item-text">
														<p class="search-item-place">H??? Ch?? Minh</p>
														<p class="search-item-desc">Th??nh Ph???</p>
													</div>
												</li>
											</ul>
										</div>
									</div>
								</div>

								<div class="content-input-selection">
									<div class="content-input-date-select">
										<div class="input-date-wrap input-date-checkin">
											<i class="far fa-calendar-alt"></i> <input id="SelectDate"
												placeholder="Ng??y Nh???n ph??ng">

										</div>
										<div class="input-date-wrap input-date-checkout">
											<i class="far fa-calendar-alt"></i> <input id="SelectDate"
												placeholder="Ng??y Tr??? ph??ng">
										</div>

									</div>
									<div class="content-input-people-room">
										<div class="input-people-room-text">
											<i class="fa-solid fa-users"></i>
											<div class="input-people-text">
												<div class="input-people">
													<span class="adultNum">1</span> ng?????i l???n, <span
														class="childNum">0</span> tr??? em
												</div>

												<div class="input-room">1 ph??ng</div>
											</div>

											<i class="fa-solid fa-chevron-down point-down"></i>
										</div>

										<div class="input-add-room-people">
											<div class="input-add add-room">
												<div class="input-add-text">
													<div class="add-text-title">Ph??ng</div>
													<div class="add-text-desc"></div>
												</div>

												<div class="input-add-btn">
													<i style="visibility: hidden;"
														class="fa-regular fa-square-minus"></i> <span
														class="input-add-number rooms">1</span> <i
														style="visibility: hidden;"
														class="fa-solid fa-square-plus"></i>
												</div>
											</div>
											<div class="input-add add-adult">
												<div class="input-add-text">
													<div class="add-text-title">Ng?????i l???n</div>
													<div class="add-text-desc">18 tu???i tr??? l??n</div>
												</div>

												<div class="input-add-btn">
													<i id="minusAdult"
														class="fa-solid fa-square-minus minusAdult"></i> <span
														id="adults" class="input-add-number adults">1</span> <i
														id="addAdult" class="fa-regular fa-square-plus addAdult"></i>
												</div>
											</div>
											<div class="input-add add-child">
												<div class="input-add-text">
													<div class="add-text-title">Tr??? em</div>
													<div class="add-text-desc">0-12 tu???i</div>
												</div>

												<div class="input-add-btn">
													<i id="minusChild"
														class="fa-regular fa-square-minus minusChild"></i> <span
														class="input-add-number children">0</span> <i
														id="addChild" class="fa-solid fa-square-plus addChild"></i>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="content-discount-wrap">
									<div class="content-discount-tag">
										<p>?????t g??i ti???t ki???m</p>
									</div>
									<div class="discount-input">
										<i class="fa-light fa-magnifying-glass"></i> <label
											for="discount-input-text"> <i
											class="fa-solid fa-magnifying-glass"></i>
										</label> <input id="discount-input-text" type="text"
											placeholder="Nh???p ho???c ch???n khuy???n m??i">
									</div>
								</div>

							</div>

							<c:if test="${sessionScope.error_search!=null}">
								<div>
									<h3 class="error_search"
										style="color: red; text-align: center;">${sessionScope.error_search}</h3>
								</div>
							</c:if>

							<div class="content-item-search-button">
								<button class="search-button blue-button"
									onclick="location.href='search'" type="submit">T??M</button>
							</div>
						</form>
					</div>


				</div>
			</div>

			<c:forEach items="${listSearchHotels}" var="o">
				<div id="search_infomation">
					<div class="searched_item">
						<div class="images_of_searched_item">
							<div class="main_image">
								<img style="width: 100%;" src="${o.getImage1()}" alt="">
							</div>
							<div class="other_images">
								<img class="image_item" src="${o.getImage2()}" alt=""> <img
									class="image_item" src="${o.getImage3()}" alt=""> <img
									class="image_item" src="${o.getImage4()}" alt=""> <img
									class="image_item" src="${o.getImage5()}" alt="">
							</div>
						</div>
						<div class="remain_of_searched_item">
							<h1 class="name_hotel">${o.getName_Hotel()}</h1>
							<div id="address_checkin">
								<img id="icon_map"
									src="https://vnreview.vn/image/20/36/36/2036366.jpg" alt="">
								<h3 id="text_address_checkin">${o.getAddress()}</h3>
							</div>
							<div id="benefit">
								<h1 style="margin-left: 10px;">H?????ng c??c l???i ??ch ?????c bi???t
									d??nh cho th???i gian ??? c???a qu?? kh??ch</h1>

								<div id="list_benefit">
									<c:if test="${o.getBenefit1()!=null}">
										<div class="list_member">
											<img class="Mark"
												src="https://phongthuyxanh.vn/wp-content/uploads/d%E1%BA%A5u-t%C3%ADch.png"
												alt="">
											<p>${o.getBenefit1()}</p>
										</div>
									</c:if>
									<c:if test="${o.getBenefit2()!=null}">
										<div class="list_member">
											<img class="Mark"
												src="https://phongthuyxanh.vn/wp-content/uploads/d%E1%BA%A5u-t%C3%ADch.png"
												alt="">
											<p>${o.getBenefit2()}</p>
										</div>
									</c:if>
									<c:if test="${o.getBenefit3()!=null}">
										<div class="list_member">
											<img class="Mark"
												src="https://phongthuyxanh.vn/wp-content/uploads/d%E1%BA%A5u-t%C3%ADch.png"
												alt="">
											<p>${o.getBenefit3()}</p>
										</div>
									</c:if>
									<c:if test="${o.getBenefit4()!=null}">
										<div class="list_member">
											<img class="Mark"
												src="https://phongthuyxanh.vn/wp-content/uploads/d%E1%BA%A5u-t%C3%ADch.png"
												alt="">
											<p>${o.getBenefit4()}</p>
										</div>
									</c:if>
									<c:if test="${o.getBenefit5()!=null}">
										<div class="list_member">
											<img class="Mark"
												src="https://phongthuyxanh.vn/wp-content/uploads/d%E1%BA%A5u-t%C3%ADch.png"
												alt="">
											<p>${o.getBenefit5()}</p>
										</div>
									</c:if>
									<c:if test="${o.getBenefit6()!=null}">
										<div class="list_member">
											<img class="Mark"
												src="https://phongthuyxanh.vn/wp-content/uploads/d%E1%BA%A5u-t%C3%ADch.png"
												alt="">
											<p>${o.getBenefit6()}</p>
										</div>
									</c:if>
									<c:if test="${o.getBenefit7()!=null}">
										<div class="list_member">
											<img class="Mark"
												src="https://phongthuyxanh.vn/wp-content/uploads/d%E1%BA%A5u-t%C3%ADch.png"
												alt="">
											<p>${o.getBenefit7()}</p>
										</div>
									</c:if>
									<c:if test="${o.getBenefit8()!=null}">
										<div class="list_member">
											<img class="Mark"
												src="https://phongthuyxanh.vn/wp-content/uploads/d%E1%BA%A5u-t%C3%ADch.png"
												alt="">
											<p>${o.getBenefit8()}</p>
										</div>
									</c:if>
								</div>
							</div>
							<div class="price_n_button">
								<h2 class="price_hotel">${o.getPrice()} VN??</h2>
								<button
									onclick="location.href='room?hotelID=${o.getHotel_id()}'"
									type="submit" class="blue-button button-padding_12 btn_hotel">Ch???n
									Ph??ng</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>
    <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		const select_city = document.getElementsByClassName("city_select")
		// console.log(select_city[0])
		function city_click(e) {
			let ct = e.target;
			for (let i = 0; i < select_city.length; i++) {
				select_city[i].style.borderBottom = "1px solid #aaa"
			}
			// console.log(ct)
			ct.style.borderBottom = "2px solid #5392fa"
		}
		for (let i = 0; i < select_city.length; i++) {
			// console.log(select_city[i])
			select_city[i].addEventListener('click', city_click)
		}
		function Loadcity(Cityid) {
			$
					.ajax({
						url : "/ProjectTravelServices/city",
						type : "get",
						data : {
							cid : Cityid
						},
						success : function(responseData) {
							document.getElementById("content").innerHTML = responseData;
						}
					});
		}
		Loadcity("hcm")
	</script>

	<!-- M??? menu user -->
	<script>
		const app = document.querySelector('.app__header-user-display');
		const menuClick = document.querySelector('.app__header-user-menu');
		function user_menu_click() {
			// hideUserMenu();
			hideShowAddForm();
			hideShowSearchForm();
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

	<!-- search-category search-container-select CLICK -->
	<script>
		function hideBottomBorderSearchList() {
			document.getElementById("item-category-hotel").style.display = "none";
			document.getElementById("item-category-hotel").style.opacity = "0";
			document.getElementById("hotel-category").style.borderBottom = "none";
			document.getElementById("hotel-category").style.color = "rgb(126, 126, 126)";

			document.getElementById("item-category-house").style.display = "none";
			document.getElementById("item-category-house").style.opacity = "0";
			document.getElementById("house-category").style.borderBottom = "none";
			document.getElementById("house-category").style.color = "rgb(126, 126, 126)";

			document.getElementById("xxx0-category").style.borderBottom = "none";
			document.getElementById("xxx0-category").style.color = "rgb(126, 126, 126)";

			document.getElementById("xxx1-category").style.borderBottom = "none";
			document.getElementById("xxx1-category").style.color = "rgb(126, 126, 126)";

			document.getElementById("xxx2-category").style.borderBottom = "none";
			document.getElementById("xxx2-category").style.color = "rgb(126, 126, 126)";

			document.getElementById("xxx3-category").style.borderBottom = "none";
			document.getElementById("xxx3-category").style.color = "rgb(126, 126, 126)";
		}

		//------------------------------------------------ 
		function hotelCategoryClick() {
			hideBottomBorderSearchList();
			document.getElementById("item-category-hotel").style.display = "block";
			document.getElementById("item-category-hotel").style.opacity = "1";
			document.getElementById("hotel-category").style.borderBottom = "3px solid #5392fa";
			document.getElementById("hotel-category").style.color = "#5392fa";

		}
		document.getElementById("hotel-category").addEventListener('click',
				hotelCategoryClick);

		//------------------------------------------------ 
		function houseCategoryClick() {
			hideBottomBorderSearchList();
			document.getElementById("item-category-house").style.display = "block";
			document.getElementById("item-category-house").style.opacity = "1";
			document.getElementById("house-category").style.borderBottom = "3px solid #5392fa";
			document.getElementById("house-category").style.color = "#5392fa";
		}
		document.getElementById("house-category").addEventListener('click',
				houseCategoryClick);

		//------------------------------------------------ 
		function xxx0CategoryClick() {
			hideBottomBorderSearchList();
			document.getElementById("xxx0-category").style.borderBottom = "3px solid #5392fa";
			document.getElementById("xxx0-category").style.color = "#5392fa";
		}
		document.getElementById("xxx0-category").addEventListener('click',
				xxx0CategoryClick);

		//------------------------------------------------ 
		function xxx1CategoryClick() {
			hideBottomBorderSearchList();
			document.getElementById("xxx1-category").style.borderBottom = "3px solid #5392fa";
			document.getElementById("xxx1-category").style.color = "#5392fa";
		}
		document.getElementById("xxx1-category").addEventListener('click',
				xxx1CategoryClick);

		//------------------------------------------------ 
		function xxx2CategoryClick() {
			hideBottomBorderSearchList();
			document.getElementById("xxx2-category").style.borderBottom = "3px solid #5392fa";
			document.getElementById("xxx2-category").style.color = "#5392fa";
		}
		document.getElementById("xxx2-category").addEventListener('click',
				xxx2CategoryClick);

		//------------------------------------------------ 
		function xxx3CategoryClick() {
			hideBottomBorderSearchList();
			document.getElementById("xxx3-category").style.borderBottom = "3px solid #5392fa";
			document.getElementById('xxx3-category').style.color = "#5392fa";
		}
		document.getElementById("xxx3-category").addEventListener('click',
				xxx3CategoryClick);

		//-----------------------------------------------
	</script>

	<!-- M??? danh s??ch t??m ki???m kh??ch s???n, ?????a ch??? -->
	<script>
		const searchFormClick = document
				.querySelectorAll('.content-item-input-text');
		const ShowSearchForm = document
				.querySelectorAll('.item-input-search-wrap');
		function ShowSearchList() {
			hideUserMenu();
			hideShowAddForm();
			// hideShowDatePick();

			for (let i = 0; i < ShowSearchForm.length; i++)
				ShowSearchForm[i].classList.toggle('show');
		}
		for (let i = 0; i < ShowSearchForm.length; i++) {
			searchFormClick[i].addEventListener('click', ShowSearchList);
			ShowSearchForm[i].addEventListener('click', function() {
				event.stopPropagation();
			});
			searchFormClick[i].addEventListener('click', function() {
				event.stopPropagation();
			});
		}
	</script>
	<!-- M??? form th??m ph??ng, ng?????i -->
	<script>
		const AddFormClick = document
				.querySelectorAll('.input-people-room-text');
		const ShowAddForm = document.querySelectorAll('.input-add-room-people');
		function ShowAddRoomPeopleForm() {
			hideUserMenu();
			// hideShowAddForm();
			hideShowSearchForm();
			// hideShowDatePick();

			for (let i = 0; i < ShowAddForm.length; i++)
				ShowAddForm[i].classList.toggle('show');
		}

		for (let i = 0; i < ShowAddForm.length; i++) {
			AddFormClick[i].addEventListener('click', ShowAddRoomPeopleForm);
			ShowAddForm[i].addEventListener('click', function() {
				event.stopPropagation();
			});
			AddFormClick[i].addEventListener('click', function() {
				event.stopPropagation();
			});
		}
	</script>
	<!-- Script calendar flatpickr -->
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
	<script src="https://npmcdn.com/flatpickr/dist/l10n/vn.js"></script>
	<script>
		flatpickr("#SelectDate", {
			"locale" : "vn",
			minDate : "today",
			dateFormat : "d-m-Y",

		});
	</script>



	<!-- <script>
        const datepickClick = document.querySelectorAll('.content-input-selection')
        const calendarShow = document.querySelectorAll('.calendar-wrap')
        function ShowCalendar(){
            hideUserMenu();
            hideShowAddForm();
            hideShowSearchForm();
            for(let i = 0; i < calendarShow.length; i++)
                calendarShow[i].classList.toggle('show')
        }
        for (let i = 0; i < calendarShow.length; i++) {
            datepickClick[i].addEventListener('click', ShowCalendar);
            calendarShow[i].addEventListener('click', function () {
                event.stopPropagation();
            });
            datepickClick[i].addEventListener('click', function () {
                event.stopPropagation();
            });
        }
    </script> -->

	<script>
		document.onclick = function() {
			autoHide();
		};

		//auto hide
		function hideUserMenu() {
			menuClick.classList.remove('show');
		}
		function hideShowAddForm() {
			for (let i = 0; i < ShowAddForm.length; i++)
				ShowAddForm[i].classList.remove('show');
		}
		function hideShowSearchForm() {
			for (let i = 0; i < ShowSearchForm.length; i++)
				ShowSearchForm[i].classList.remove('show');
		}
		// function hideShowDatePick(){
		//     for (let i = 0; i < calendarShow.length; i++)
		//         calendarShow[i].classList.remove('show');
		// }
		function autoHide() {
			hideUserMenu();
			hideShowAddForm();
			hideShowSearchForm();
			// hideShowDatePick();
		}
	</script>


	<script src="<c:url value="./script/addRoomPeople.js"/>"></script>
	<script type="module" src="<c:url value="./script/countDays.js"/>"></script>
	<script type="module" src="<c:url value="./script/numbeWithComma.js"/>"></script>
	<script type="module" src="<c:url value="./script/cartHeader.js"/>"></script>

</body>
</html>