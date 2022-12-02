package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.Logins;

/**
 * Servlet implementation class AddtoCartController
 */
@WebServlet(urlPatterns = {"/AddtoCart", "/addtocart"} )
public class AddtoCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddtoCartController() {
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
		String DayInAddToCart = request.getParameter("DayInAddToCart");
		String DayOutAddToCart = request.getParameter("DayOutAddToCart");
		
		if(DayInAddToCart.equals(""))
			DayInAddToCart=null;
		if(DayOutAddToCart.equals(""))
			DayOutAddToCart=null;
		String totalRoom = request.getParameter("totalRoom");
		String totalAdults = request.getParameter("totalAdults");
		String totalChildren = request.getParameter("totalChildren");
		String Room_id = request.getParameter("Room_id");
		Logins lg= (Logins) session.getAttribute("acc");;
		int User_id = lg.getLogin_id();
		
		dao.Insert_Cart(DayInAddToCart, DayOutAddToCart, totalRoom, totalAdults, totalChildren, Room_id, User_id);
		
		response.sendRedirect("/ProjectTravelServices/cartdetail");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
