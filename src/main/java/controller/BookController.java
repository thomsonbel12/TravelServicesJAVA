package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.CartDisplay;
import entity.Carts;
import entity.Logins;
import entity.Users;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/book")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		DAO dao = new DAO();

		@SuppressWarnings("unchecked")
		List<CartDisplay> listCarts= (List<CartDisplay>) session.getAttribute("listCarts");
		List<CartDisplay> listCartsSelected = new ArrayList<>();
		for (CartDisplay y: listCarts) {
			String selected=request.getParameter(Integer.toString(y.getCart_id()));	
			if(selected!=null) {
				String CheckIn_Date_String=request.getParameter("CheckIn_date"+y.getCart_id());
				String CheckOut_Date_String=request.getParameter("CheckOut_date"+y.getCart_id());
				
				
				char[] char1 = CheckIn_Date_String.toCharArray();
				String CheckIn_Date=String.copyValueOf(char1, 6, 4)+"-"+String.copyValueOf(char1, 3, 2)+"-"+String.copyValueOf(char1, 0, 2);
				System.out.println(CheckIn_Date);

				char[] char2 = CheckOut_Date_String.toCharArray();
				String CheckOut_Date=String.copyValueOf(char2, 6, 4)+"-"+String.copyValueOf(char2, 3, 2)+"-"+String.copyValueOf(char2, 0, 2);
				System.out.println(CheckOut_Date);
				dao.UpdateDateByCartId(CheckIn_Date, CheckOut_Date, y.getCart_id());
				Carts cart=dao.GetCartById(y.getCart_id());
				y.setCheckIn_date(cart.getCheckIn_date());
				y.setCheckOut_date(cart.getCheckOut_date());
				
				long total_day= ((y.getCheckOut_date().getTime() - y.getCheckIn_date().getTime())/(1000 * 3600 * 24))+1;
				DecimalFormat df=new DecimalFormat("#,###");
		        String Room_total_money=df.format(y.getPrice_room()*total_day);
				
				
				listCartsSelected.add(new CartDisplay(y.getCart_id(), y.getCheckIn_date(), y.getCheckOut_date(), y.getTotal_rooms(), y.getName_hotel(), 
						y.getPrice_room(), y.getImage(), y.getType(), y.getAdult(), y.getChildren(), y.getUser_id(), y.getRoom_id(), y.getHotel_id(), y.getAvailable_Room(), y.getMax_Adult(), y.getMax_Children(),Room_total_money));
			}
		}
		session.setAttribute("listCarts", listCarts);
		Logins lg= (Logins) session.getAttribute("acc");
		Users user=dao.GetUsers(lg.getLogin_id());
//		for(CartDisplay y: listCartsSelected) {
//			System.out.println(y);
//		}
		session.setAttribute("listCartsSelected", listCartsSelected);
		session.setAttribute("user_book", user);
		request.getRequestDispatcher("HTML/book.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
