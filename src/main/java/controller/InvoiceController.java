package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.Session;
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
 * Servlet implementation class InvoiceController
 */
@WebServlet("/invoice")
public class InvoiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceController() {
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
		int Total= (int) session.getAttribute("Total");
		LocalDateTime now = LocalDateTime.now();
		
		String Date = dao.AddZero(Integer.toString(now.getDayOfMonth())) + "/"
				+ dao.AddZero(Integer.toString(now.getMonthValue()) + "/" + now.getYear());
		
		@SuppressWarnings("unchecked")
		List<CartDisplay> listCartsSelected = (List<CartDisplay>) session.getAttribute("listCartsSelected");
		int Last_Bill_Id = dao.Last_Bill_Id();
		Logins lg = (Logins) session.getAttribute("acc");
		Users user = dao.GetUsers(lg.getLogin_id());
		DecimalFormat df=new DecimalFormat("#,###");
		String total=df.format(Total);
		request.setAttribute("OrderId", Last_Bill_Id);
		request.setAttribute("Date", Date);
		request.setAttribute("NameCustomer", user.getName_user());
		request.setAttribute("PhoneNumber", user.getPhone_number());
		request.setAttribute("Email", user.getEmail());
		request.setAttribute("listCartsSelected", listCartsSelected);
		request.setAttribute("Total", total);
//		request.setAttribute("Type_Payment", );
		request.getRequestDispatcher("/HTML/invoice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
