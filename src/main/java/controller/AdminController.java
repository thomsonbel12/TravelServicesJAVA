package controller;

import java.io.IOException;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.AdminLandLords;
import entity.AdminUsers;
import entity.LandLords;
import entity.Users;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao=new DAO();
		HttpSession session = request.getSession();
		List<AdminUsers> ListAllUsers= dao.GetAllUsers();
		List<AdminLandLords> ListAllLandLords=dao.GetAllLandLords();
		int CountUsers=0;
		int CountLandLords=0;
		for(AdminUsers o: ListAllUsers) {
			CountUsers++;
		}
		for(AdminLandLords o: ListAllLandLords) {
			CountLandLords++;
		}
		request.setAttribute("ListAllUsers", ListAllUsers);
		request.setAttribute("ListAllLandLords", ListAllLandLords);
		request.setAttribute("CountUsers", CountUsers);
		request.setAttribute("CountLandLords", CountLandLords);
		request.getRequestDispatcher("/HTML/admin.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
