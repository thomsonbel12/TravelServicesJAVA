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

<link rel="stylesheet" href="<c:url value="/assets/css/book.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/checkout.css"/>">
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> -->
</head>
<body>

	<div id="app" class="app__Checkout">
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
						class="rgbRed-text" href="/ProjectTravelServices/cartdetail">
							<i class="fa-solid fa-cart-shopping"> <span
								class="cart-item-number total_cart_room total_cart_room"></span>
						</i>
					</a>
						<div class="shopping_cart-items">
							<div class="cart-text">Danh s??ch c???a t??i</div>

							<div class="cart-list">

								<c:forEach items="${sessionScope.listCarts}" var="y">
									<div onclick="location.href='room?hotelID=${y.getHotel_id()}'"
										class="cart-item">
										<div class="cart-item-img">
											<img src="${y.getImage()}" alt="">
										</div>
										<div class="cart-item-detail">
											<div class="cart-item-name">${y.getType()}-
												${y.getName_hotel()}</div>
											<div class="cart-item-desc">
												<div class="cart-item-date">
													<label style="color: #333;">Ng??y nh???n ph??ng</label>
													<div class="cart-day">${y.getCheckIn_date() == null ? 'Ch??a ch???n ng??y' : ''}
														${y.getCheckIn_date()}</div>
												</div>
												<div class="cart-item-people">
													<div style="color: #333;">1 ph??ng</div>
													<div>${y.getAdult()}ng?????il???n, ${y.getChildren()} tr???
														em</div>
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
								<li onclick="location.href='/ProjectTravelServices/cartdetail'"><i class="fa-solid fa-list"></i> <!-- <a href="">????n ?????t ch??? c???a t??i</a> -->
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
			<div class="app__body-header">
				<ul class="body-header-bar">
					<li class="active"><span id="step-1" class="step">1</span> <span
						class="step-text">Th??ng tin kh??ch h??ng</span></li>

					<li class="active"><span id="step-2" class="step"> <i
							class="fa-solid fa-check"></i>
					</span> <span class="step-text">X??c nh???n thanh to??n</span></li>
				</ul>
			</div>

			<form style="width: 100%;" action="bills" method="post">
				<div class="app__body-content">
					<div class="body-left-content">
						<div class="body-left-item body-left-header">
							<i class="fa-regular fa-circle-user"></i> <span
								class="body-left-header-text">Xin Ch??o
								${sessionScope.user_book.getName_user()}<span
								class="rgbRed-text">${user_name}</span> !
							</span>
						</div>
						<input style="display: none;" id="total_money_pass" name="total_money_pass" value="">
						<input style="display: none;" id="total_room_pass" name="total_room_pass" value="">
						<div class="body-left-item user-info-check">
							<div class="user-info-header">
								<h4 style="color: #333;">Ph????ng th???c thanh to??n</h4>
								<h5>Vui l??ng ch???n 1 trong c??c ph????ng th???c thanh to??n sau !!</h5>
							</div>

						</div>
						<div class="body-left-item pay_method">
							<div class="direct_payment_wrap">
								<div class="payment_header">
									<input name="direct_payment_check" id="direct_payment_check_btn" type="checkbox"
										class="custom_button"> <label
										id="direct_payment_check" for="direct_payment_check_btn"
										class="direct_payment_check">Thanh To??n Khi Nh???n Ph??ng</label>
								</div>

								<div style="display: none;" id="direct_payment_detail"
									class="payment_detail direct_detail">
									<h5 class="fontWeight-600 semiBlack-text">Thanh To??n khi
										nh???n ph??ng :</h5>
									<ul style="list-style: disc; padding: 0 45px; font-size: 14px;">
										<li>T???ng s??? l?????ng ph??ng : <span
											class="rooms_total_show fontWeight-600 rgbRed-text">3</span></li>
										<li>T???ng s??? ti???n b???n ph???i tr??? : <span
											class="total_rooms_money fontWeight-600 rgbRed-text"></span>
											VN??
										</li>
									</ul>
									<span style="font-size: 13px; margin-top: 16px;"
										class="fontWeight-600 rgbRed-text">Khi nh???n ph??ng vui
										l??ng xu???t tr??nh c??c th??ng tin c???n thi???t nh?? <span
										style="text-decoration: underline;" class="semiBlack-text">H??a
											????n </span> <span class="semiRgbRed-text">(s??? c???p cho b???n khi
											ho??n th??nh qu?? tr??nh x??c nh???n)</span> v?? <span
										style="text-decoration: underline;" class="semiBlack-text">S???
											??i???n tho???i ???? ????ng k?? c???a b???n !</span>
									</span>
								</div>
							</div>

							<div class="momo_payment_wrap">
								<div class="payment_header">
									<input id="momo_payment_check_btn" type="checkbox"
										class="custom_button"> <label id="momo_payment_check"
										for="momo_payment_check_btn" class="momo_payment_check">Thanh
										To??n B???ng MoMo</label>
								</div>
								<div style="display: none;" id="momo_payment_detail"
									class="payment_detail">
									<h5 class="fontWeight-600 semiBlack-text">Thanh To??n B???ng
										MOMO :</h5>
									<ul style="list-style: disc; padding: 0 45px; font-size: 14px;">
										<li>S??? ??i???n tho???i li??n k???t v???i momo c???a bn l?? :</li>
										<li>T???ng s??? l?????ng ph??ng : <span
											class="rooms_total_show fontWeight-600 rgbRed-text">3</span></li>
										<li>T???ng s??? ti???n b???n ph???i tr??? : <span
											class="total_rooms_money fontWeight-600 rgbRed-text"></span>
											VN??
										</li>
									</ul>

								</div>
							</div>
						</div>

						<div class="body-left-item body-left-submit-btn">
							<div class="policy-text"
								style="font-size: 12px; width: 60%; color: #aaa;">
								Th???c hi???n b?????c ti???p theo ?????ng ngh??a v???i vi???c b???n ch???p nh???n tu??n
								theo <a href="" class="blue-text">??i???u kho???n s??? d???ng</a> v?? <a
									href="" class="blue-text">Ch??nh s??ch b???o m???t</a> c???a <span
									class="rgbRed-text">LHT C.</span>
							</div>

							<div class="body-submit">
								<button id="submit_checkout"
									class="submit_checkout padding-8 blue-button">Thanh
									to??n</button>
							</div>
						</div>
					</div>
					<div class="body-right-content">
						<div class="body-right-item right-room-summary">
							<div class="right-room-header fontWeight-600 semiBlack-text">
								<!-- <div> -->
								T??M T???T DANH S??CH PH??NG
								<!-- </div> -->
								<div class="right-room-header-num-room fontWeight-500">
									<div>
										( <span id="total_rooms"
											class="fontWeight-600 rgbRed-text total_room"></span>
										&nbsp;Ph??ng )
									</div>

									<div>
										T???ng : &nbsp;&nbsp;&nbsp; <span name="total_money"
											id="total_money" class=" rgbRed-text fontWeight-600"></span>
										&nbsp;VN??
									</div>
									
								</div>
							</div>
							<div class="right-room-list">


								<c:forEach items="${sessionScope.listCartsSelected}" var="y">
									<div class="room-content">
										<div class="room-content-header">
											<div class="room-tag">
												<i class="fa-solid fa-hotel"></i> <span
													class="room-tag-item room-tag-name fontWeight-600 black-text">KH??CH
													S???N</span> <span
													class="room-tag-item room-tag-city semiBlack-text">(H???
													CH?? MINH)</span>
											</div>

										</div>
										<div class="room-content-body">
											<div class="room-body-item room-img-name">
												<div class="room-img">
													<img src="${y.getImage()}" alt="">
												</div>

												<div class="room-name-money semiBlack-text fontWeight-600">
													<div class="room-name">${y.getType()}-
														${y.getName_hotel()}</div>
													<div class="room-money">
														<i class="fa-regular fa-money-bill-1 rgbRed-text"></i> <span
															class="money-1-day rgbRed-text">${y.getPrice_room()}</span>
														<span class="money-currency">VN??</span> ( <span
															class="rgbRed-text fontWeight-600">1</span> Ng??y )
													</div>
												</div>

											</div>

											<div class="room-body-item room-book-detail">
												<div class="room-date semiBlack-text">
													<i class="fa-solid fa-calendar blue-text"></i> <span>
														T??? &nbsp;</span> <input id="checkin-date"
														class="checkin_date rgbRed-text fontWeight-600" disabled
														value="${y.getCheckIn_date()}"> <span>&nbsp;
														?????n &nbsp;</span> <input id="checkout-date"
														class="checkout_date rgbRed-text fontWeight-600" disabled
														value="${y.getCheckOut_date()}">
													<!-- <span class="border-bar"></span> -->

													<span>&nbsp;&nbsp;&nbsp;&nbsp;T???ng s??? ng??y : &nbsp;</span>
													<span class="total_days rgbRed-text fontWeight-600"></span>
													<span>&nbsp; Ng??y</span>
												</div>

												<div class="room-people semiBlack-text">
													<i class="fa-solid fa-users blue-text"></i> <span
														class="room_num rgbRed-text fontWeight-600">1</span> <span>
														Ph??ng, </span> <span class="adult_num rgbRed-text fontWeight-600">${y.getAdult()}</span>
													<span>Ng?????i l???n, </span> <span
														class="adult_num rgbRed-text fontWeight-600">${y.getChildren()}</span>
													<span>Tr??? em.</span>
												</div>

												<div class="room-total-money">
													<i class="fa-solid fa-money-check-dollar"></i> <span
														class="semiBlack-text fontWeight-600">T???ng s??? ti???n
														: &nbsp;</span> <span
														class="room-total-money-text rgbRed-text fontWeight-600"></span>
													<span class="money-currency semiBlack-text">&nbsp;VN??</span>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>


							</div>
						</div>
						<div class="body-right-item"></div>
						<div class="body-right-item"></div>
					</div>
				</div>
			</form>
		</div>

	</div>


	<script type="module" src="<c:url value="/script/numbeWithComma.js" />"></script>
	<script type="module" src="<c:url value="/script/countDays.js" />"></script>
	<script type="module" src="<c:url value="/script/book.js" />"></script>
	<%-- 	<script src="<c:url value="/script/loadCountry.js" />"></script> --%>
	<script type="module" src="<c:url value="/script/cartHeader.js" />"></script>
	<script type="module" src="<c:url value="/script/checkout.js" />"></script>
	


	<!-- M??? menu user -->
	<script>
		const app = document.querySelector('.app__header-user-display');
		const menuClick = document.querySelector('.app__header-user-menu');
		function user_menu_click() {
			// hideUserMenu();
			// hideShowAddForm();
			// hideShowSearchForm();
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
	<script>
		document.onclick = function() {
			autoHide();
		};

		//auto hide
		function hideUserMenu() {
			menuClick.classList.remove('show');
		}
		// function hideShowAddForm() {
		//     for (let i = 0; i < ShowAddForm.length; i++)
		//         ShowAddForm[i].classList.remove('show');
		// }
		// function hideShowSearchForm() {
		//     for (let i = 0; i < ShowSearchForm.length; i++)
		//         ShowSearchForm[i].classList.remove('show');
		// }
		// function hideShowDatePick(){
		//     for (let i = 0; i < calendarShow.length; i++)
		//         calendarShow[i].classList.remove('show');
		// }
		function autoHide() {
			hideUserMenu();
			// hideShowAddForm();
			// hideShowSearchForm();
			// hideShowDatePick();
		}
	</script>
	
	<script>
		
	</script>

</body>
</html>