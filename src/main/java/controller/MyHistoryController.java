package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.Bills;
import entity.Logins;
import entity.Users;

/**
 * Servlet implementation class MyHistoryController
 */
@WebServlet(urlPatterns = {"/MyHistory","/myhistory"})
public class MyHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyHistoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		DAO dao=new DAO();
		HttpSession session = request.getSession();
		Logins lg= (Logins) session.getAttribute("acc");
		Users user= dao.GetUsers(lg.getLogin_id());
		int User_Id = user.getUser_id();
//		System.out.println("userId"+User_Id);
		List<Bills> ListBill = dao.GetBillsByUserId(User_Id);
//		for(Bills o:ListBill) {
//			System.out.println(o);
//		}
		request.setAttribute("ListBill", ListBill);
		request.getRequestDispatcher("/HTML/myHistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
