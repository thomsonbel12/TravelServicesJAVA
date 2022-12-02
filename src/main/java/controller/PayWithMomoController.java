package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.CartDisplay;
import entity.Logins;
import entity.Users;

/**
 * Servlet implementation class PayWithMomoController
 */
@WebServlet("/paywithmomo")
public class PayWithMomoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayWithMomoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		DAO dao=new DAO();
		String message=req.getParameter("message");
//		System.out.println(message);
		if(!message.equals("Successful.")) {
			session.setAttribute("error_payment", "Đặt phòng thất bại");
			RequestDispatcher rq = req.getRequestDispatcher("/home");
			rq.forward(req, resp);
		}
		else {
		int Total= (int) session.getAttribute("Total");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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

		dao.Insert_Bill(Total, Date, TotalRoom, "2", user_Id);

		int Last_Bill_Id = dao.Last_Bill_Id();

		for (CartDisplay c : listCartsSelected) {
			dao.Insert_Bill_Detail(c.getCheckIn_date().toString(), c.getCheckOut_date().toString(), c.getTotal_rooms(),
					Total, c.getAdult(), c.getChildren(), c.getRoom_id(), Last_Bill_Id, 1);
			dao.DeleteCart(c.getCart_id());
		}
		
		for(CartDisplay c: listCartsSelected) {
			int avai_room= c.getAvailable_Room()-c.getTotal_rooms();
			dao.UpdateAvailableRoom(c.getRoom_id(), avai_room);
		}
		resp.sendRedirect("/ProjectTravelServices/invoice");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
