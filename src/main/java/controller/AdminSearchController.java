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
import entity.AdminLandLords;
import entity.AdminUsers;

/**
 * Servlet implementation class AdminSearchController
 */
@WebServlet("/adminSearch")
public class AdminSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchController() {
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
		String input=request.getParameter("search_input");
		List<AdminUsers> ListAllUsers= dao.GetAllUsersBySearch(input);
		List<AdminLandLords> ListAllLandLords=dao.GetAllLandLordsBySearch(input);
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
