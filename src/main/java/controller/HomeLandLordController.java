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
import entity.Hotels;
import entity.Logins;
import entity.Users;

/**
 * Servlet implementation class HomeLandLordController
 */
@WebServlet("/homelandlord")
public class HomeLandLordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeLandLordController() {
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
		Logins lg= (Logins) session.getAttribute("landlordLogin");
		List<Hotels> listHotels = dao.GetHotelsByLandLordId(lg.getLogin_id());
		int SoKhachSan=0;
		for(Hotels o: listHotels) {
			SoKhachSan++;
		}
		List<Users> top5RecentUsers = dao.get5RecentUserByLandLordId(lg.getLogin_id());
		request.setAttribute("SoKhachSan", SoKhachSan);
		request.setAttribute("top5RecentUsers", top5RecentUsers);
		request.setAttribute("SoKhachHang", dao.GetSumCustomerByLandLordId(lg.getLogin_id()));
		request.setAttribute("TongSoTien", dao.GetSumPriceByLandLordId(lg.getLogin_id()));
		request.setAttribute("TongDonDat", dao.getSumBillByLandLordId(lg.getLogin_id()));
		request.getRequestDispatcher("/HTML/landlordHome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
