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
 * Servlet implementation class SignUpLandLordController
 */
@WebServlet("/signuplandlord")
public class SignUpLandLordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpLandLordController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/HTML/LandlordSignup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String avatar = "https://cdn3.iconfinder.com/data/icons/home-insurance-2/380/10-512.png";
		HttpSession session = request.getSession();
		String fullname_email = request.getParameter("fullname_email");
		String fullname_phone = request.getParameter("fullname_phone");
		String email_signup = request.getParameter("email_signup");
		String phone_signup = request.getParameter("phone_signup");
		String pass_email_signup = request.getParameter("pass_email_signup");
		String repass_email_signup = request.getParameter("repass_email_signup");
		String pass_phone_signup = request.getParameter("pass_phone_signup");
		String repass_phone_signup = request.getParameter("repass_phone_signup");

		DAO dao = new DAO();
		Logins a;
		if (phone_signup == null) {
			if (!pass_email_signup.equals(repass_email_signup)) {
				session.setAttribute("signup_landlord_error", "Mật khẩu không trùng khớp");
				request.getRequestDispatcher("/HTML/LandlordSignup.jsp").forward(request, response);
			} else {
				a = dao.GetLogin(email_signup, pass_email_signup, "email", "landlord");
				if (a != null) {
					session.setAttribute("signup_landlord_error", "Địa chỉ email đã được đăng ký trước đó!");
					request.getRequestDispatcher("/HTML/LandlordSignup.jsp").forward(request, response);
				} else {
					dao.Insert_Login(email_signup, "email", "landlord", pass_email_signup);
					Logins lg = dao.GetLogin(email_signup, pass_email_signup, "email", "landlord");
					dao.Insert_LandLord(lg.getLogin_id(), fullname_email, avatar, null, email_signup);
					session.setAttribute("signup_landlord_error", null);
					response.sendRedirect("/ProjectTravelServices/loginlandlord");
				}
			}
		} else {
			if (!pass_phone_signup.equals(repass_phone_signup)) {
				session.setAttribute("signup_landlord_error", "Mật khẩu không trùng khớp");
				request.getRequestDispatcher("/HTML/LandlordSignup.jsp").forward(request, response);
			} else {
				a = dao.GetLogin(phone_signup, pass_phone_signup, "sdt", "landlord");
				if (a != null) {
					session.setAttribute("signup_landlord_error", "Số điện thoại đã được đăng ký trước đó.");
					request.getRequestDispatcher("/HTML/LandlordSignup.jsp").forward(request, response);
				} else {
					dao.Insert_Login(phone_signup, "sdt", "landlord", pass_phone_signup);
					Logins lg = dao.GetLogin(phone_signup, pass_phone_signup, "sdt", "landlord");
					dao.Insert_LandLord(lg.getLogin_id(), fullname_phone, avatar, phone_signup, null);
					session.setAttribute("signup_landlord_error", null);
					response.sendRedirect("/ProjectTravelServices/loginlandlord");
				}
			}
		}

	}

}
