package controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAO;
import entity.CartDisplay;
import entity.Logins;
import entity.Users;
import momo.MomoModel;
import momo.ResultMoMo;
import utils.Constant;
import utils.Decode;

@WebServlet(urlPatterns = { "/Bills", "/bills" })
public class BillsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		DAO dao = new DAO();
		int Total = Integer.parseInt((req.getParameter("total_money_pass")));
		HttpSession session = req.getSession();

		String direct_payment_check = req.getParameter("direct_payment_check");
		if (direct_payment_check != null) {
			direct_payment_check = "1"; // Thanh toan khi nhan hang
		} else {

			direct_payment_check = "2"; // Thanh toan qua MOMO
		}
		session.setAttribute("Total", Total);
	
		// ==================================================================================================
		if (direct_payment_check != "1") {
			session.setAttribute("Type_Payment", "Thanh toán bằng Momo.");
			ObjectMapper mapper = new ObjectMapper();
			int code = (int) Math.floor(((Math.random() * 89999999) + 10000000));
			String orderId = Integer.toString(code);
			
			MomoModel jsonRequest = new MomoModel();
			jsonRequest.setPartnerCode(Constant.IDMOMO);
			jsonRequest.setOrderId(orderId);
			jsonRequest.setStoreId(orderId);
			jsonRequest.setRedirectUrl(Constant.redirectUrl);
			jsonRequest.setIpnUrl(Constant.ipnUrl);
			jsonRequest.setAmount(String.valueOf(Total));
			jsonRequest.setOrderInfo("Thanh toán dịch vụ khách sạn");
			jsonRequest.setRequestId(orderId);
			jsonRequest.setOrderType(Constant.orderType);
			jsonRequest.setRequestType(Constant.requestType);
			jsonRequest.setTransId("1");
			jsonRequest.setResultCode("200");
			jsonRequest.setMessage("");
			jsonRequest.setPayType(Constant.payType);
			jsonRequest.setResponseTime("300000");
			jsonRequest.setExtraData("");
			String decode = "accessKey=" + Constant.accessKey + "&amount=" + jsonRequest.amount + "&extraData="
					+ jsonRequest.extraData + "&ipnUrl=" + Constant.ipnUrl + "&orderId=" + orderId + "&orderInfo="
					+ jsonRequest.orderInfo + "&partnerCode=" + jsonRequest.getPartnerCode() + "&redirectUrl="
					+ Constant.redirectUrl + "&requestId=" + jsonRequest.getRequestId() + "&requestType="
					+ Constant.requestType;
			String signature = Decode.encode(Constant.serectkey, decode);
			jsonRequest.setSignature(signature);
			String json = mapper.writeValueAsString(jsonRequest);
			HttpClient client = HttpClient.newHttpClient();
			String respUrl = "";
			ResultMoMo res = new ResultMoMo();
			
			try {
				HttpRequest request = HttpRequest.newBuilder().uri(new URI(Constant.Url))
						.POST(HttpRequest.BodyPublishers.ofString(json)).headers("Content-Type", "application/json")
						.build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				res = mapper.readValue(response.body(), ResultMoMo.class);
			} catch (InterruptedException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(res.getPartnerCode());
//			System.out.println(res.getDeeplink());
//			System.out.println(res.getMessage());
//			System.out.println(res.getT());
//			System.out.println(res.getResultCode());
//			System.out.println();
//			System.out.println();
			
			if (res == null) {
				
				session.setAttribute("error_search", "Đặt phòng thất bại");
				RequestDispatcher rq = req.getRequestDispatcher("/ProjectTravelServices/home");
				rq.forward(req, resp);
			} else {
				resp.sendRedirect(res.payUrl);
			}
		}
		// ==================================================================================================
		else {
			session.setAttribute("Type_Payment", "Thanh toán khi nhận phòng.");
			DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDateTime now = LocalDateTime.now();

			String Date = dao.AddZero(Integer.toString(now.getDayOfMonth())) + "-"
					+ dao.AddZero(Integer.toString(now.getMonthValue()) + "-" + now.getYear());

			@SuppressWarnings("unchecked")
			List<CartDisplay> listCartsSelected = (List<CartDisplay>) session.getAttribute("listCartsSelected");
			int TotalRoom = 0;
			for (CartDisplay c : listCartsSelected) {
				TotalRoom = TotalRoom + c.getTotal_rooms();
			}

			Logins lg = (Logins) session.getAttribute("acc");
			Users user = dao.GetUsers(lg.getLogin_id());
			int user_Id = user.getUser_id();

			dao.Insert_Bill(Total, Date, TotalRoom, direct_payment_check, user_Id);

			int Last_Bill_Id = dao.Last_Bill_Id();

			for (CartDisplay c : listCartsSelected) {
				dao.Insert_Bill_Detail(c.getCheckIn_date().toString(), c.getCheckOut_date().toString(),
						c.getTotal_rooms(), Total, c.getAdult(), c.getChildren(), c.getRoom_id(), Last_Bill_Id,
						Integer.parseInt(direct_payment_check));
				dao.DeleteCart(c.getCart_id());
			}

			for (CartDisplay c : listCartsSelected) {
				System.out.println("===========" + c.getPrice_room());
				int avai_room = c.getAvailable_Room() - c.getTotal_rooms();
				dao.UpdateAvailableRoom(c.getRoom_id(), avai_room);
			}
			resp.sendRedirect("/ProjectTravelServices/invoice");
		}

	}

}
