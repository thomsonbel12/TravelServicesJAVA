package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
 * Servlet implementation class BookNowController
 */
@WebServlet(urlPatterns = {"/BookNow","/booknow"} )
public class BookNowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookNowController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String Hotel_id=request.getParameter("Hotel_id");
		Logins lg= (Logins) session.getAttribute("acc");;
		if(lg==null) {
			session.setAttribute("error_lg", "Đặt phòng thất bại");
			response.sendRedirect("room?hotelID="+Hotel_id);
//			RequestDispatcher rq = request.getRequestDispatcher("room?hotelID="+Hotel_id);
//			rq.forward(request, response);
		}
		else {
		DAO dao = new DAO();
		String CheckIn_date = request.getParameter("DayInAddToCart2");
		String CheckOut_date = request.getParameter("DayOutAddToCart2");
		String Total_rooms = request.getParameter("totalRoom2");
		String Adult = request.getParameter("totalAdults2");
		String Children = request.getParameter("totalChildren2");
		int User_id = lg.getLogin_id();
		String Room_id = request.getParameter("Room_id");
		
		dao.Insert_Cart(CheckIn_date, CheckOut_date, Total_rooms, Adult, Children, Room_id, User_id);
		List<CartDisplay> listCarts = dao.getAllCartsByUserId(User_id);
		List<CartDisplay> listCartsSelected = new ArrayList<>();
		
		listCartsSelected.add(listCarts.get(listCarts.size() - 1));
		
		
		for (CartDisplay y: listCartsSelected) {
			long total_day= ((y.getCheckOut_date().getTime() - y.getCheckIn_date().getTime())/(1000 * 3600 * 24))+1;
			DecimalFormat df=new DecimalFormat("#,###");
	        String Room_total_money=df.format(y.getPrice_room()*total_day);
	        y.setTotalPrice(Room_total_money);
		}
		
		session.setAttribute("listCartsSelected", listCartsSelected);
		Users user = dao.GetUsers(User_id);
		session.setAttribute("user_book", user);
		request.getRequestDispatcher("HTML/book.jsp").forward(request, response);
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
