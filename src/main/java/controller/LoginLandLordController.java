package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.LandLords;
import entity.Logins;

/**
 * Servlet implementation class LoginLandLordController
 */
@WebServlet("/loginlandlord")
public class LoginLandLordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginLandLordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/HTML/LandlordLogin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String email_input= request.getParameter("email-input");
		String password_email_input= request.getParameter("password-email-input");
		String phone_input = request.getParameter("phone-input");
		String password_phone_input= request.getParameter("password-phone-input");
		
		DAO dao=new DAO();
		HttpSession session = request.getSession();
		if(phone_input==null && password_phone_input==null) {
			Logins lg = dao.GetLogin(email_input, password_email_input, "email", "landlord");
			if(lg == null) {
				session.setAttribute("Login_Landlord_error", "Email hoặc mật khẩu không chính xác");
				response.sendRedirect("/ProjectTravelServices/loginlandlord");
			}
			else {
				session.setAttribute("Login_Landlord_error", null);
				LandLords landlord=dao.GetLandLords(lg.getLogin_id());
				session.setAttribute("landlordLogin", lg);
				session.setAttribute("acclandlord", landlord);
				response.sendRedirect("/ProjectTravelServices/homelandlord");
			}
		}
		else {
			Logins lg = dao.GetLogin(phone_input, password_phone_input, "sdt", "landlord");
			if(lg == null) {
				session.setAttribute("Login_Landlord_error", "Số điện thoại hoặc mật khẩu không chính xác");
				response.sendRedirect("/ProjectTravelServices/loginlandlord");
			}
			else {
				session.setAttribute("Login_Landlord_error", null);
				LandLords landlord=dao.GetLandLords(lg.getLogin_id());
				session.setAttribute("landlordLogin", lg);
				session.setAttribute("acclandlord", landlord);
				response.sendRedirect("/ProjectTravelServices/home");
			}
		}
	}

}
