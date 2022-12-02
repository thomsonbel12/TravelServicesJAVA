package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import entity.Bill_DetailDisplay;
import entity.DisplayHotels;

/**
 * Servlet implementation class BillDetailsController
 */
@WebServlet("/billdetail")
public class BillDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillDetailsController() {
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
		String Billid = request.getParameter("cid");
		DAO dao = new DAO();
		List<Bill_DetailDisplay> list = dao.GetBillDetailByBillId(Integer.parseInt(Billid));
		PrintWriter out = response.getWriter();
		for(Bill_DetailDisplay o: list) {
			long total_day= ((o.getCheckOut_date().getTime() - o.getCheckIn_date().getTime())/(1000 * 3600 * 24))+1;
			SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
			String CheckInDate = sm.format(o.getCheckIn_date());
			String CheckOutDate=sm.format(o.getCheckOut_date());
			
			DecimalFormat df=new DecimalFormat("#,###");
	        String Money_one_day = df.format(o.getPrice());
	        String Room_total_money=df.format(o.getPrice()*total_day);
	        
			out.print("<div class=\"bill_room cart-room-wrap\">\r\n"
					+ "\r\n"
					+ "										<div class=\"cart-room padding-8\">\r\n"
					+ "											<div class=\"cart-room-img\">\r\n"
					+ "												<img src="+o.getImdRoom()+" alt=\"\">\r\n"
					+ "											</div>\r\n"
					+ "											<div class=\"cart-room-content\">\r\n"
					+ "												<div style=\"max-height: 43%;\"\r\n"
					+ "													class=\"cart-room-name rgbRed-text fontWeight-700\">"+o.getName_Hotel()+"</div>\r\n"
					+ "												<div class=\"cart-room-decs\">\r\n"
					+ "													<div class=\"cart-room-date\">\r\n"
					+ "														<div style=\"width: 100%;\"\r\n"
					+ "															class=\"content-input-date-select\">\r\n"
					+ "															<div class=\"input-date-wrap input-date-checkin flatpickr\">\r\n"
					+ "																<i class=\"far fa-calendar-alt\"></i> <input disabled\r\n"
					+ "																	id=\"SelectDate\" class=\"date checkin_date\"\r\n"
					+ "																	placeholder=\"Ngày Nhận phòng\" value=\""+CheckInDate+"\">\r\n"
					+ "															</div>\r\n"
					+ "															<div\r\n"
					+ "																class=\"input-date-wrap input-date-checkout flatpickr\">\r\n"
					+ "																<i class=\"far fa-calendar-alt\"></i> <input disabled\r\n"
					+ "																	id=\"SelectDate\" class=\"date checkout_date\"\r\n"
					+ "																	placeholder=\"Ngày Trả phòng\" value=\""+CheckOutDate+"\">\r\n"
					+ "															</div>\r\n"
					+ "														</div>\r\n"
					+ "													</div>\r\n"
					+ "\r\n"
					+ "													<div class=\"cart-room-people\">\r\n"
					+ "														<div style=\"width: 100%;\"\r\n"
					+ "															class=\"content-input-people-room\">\r\n"
					+ "															<div class=\"input-people-room-text\">\r\n"
					+ "																<i class=\"fa-solid fa-users\"></i>\r\n"
					+ "																<div class=\"input-people-text\">\r\n"
					+ "																	<div class=\"input-people\">\r\n"
					+ "																		<span class=\"adultNum\">"+o.getAdult()+"</span> người lớn, <span\r\n"
					+ "																			class=\"childNum\">"+o.getChildren()+"</span> trẻ em\r\n"
					+ "																	</div>\r\n"
					+ "\r\n"
					+ "																	<div class=\"input-room\">"+o.getTotal_rooms()+" phòng</div>\r\n"
					+ "																</div>\r\n"
					+ "															</div>\r\n"
					+ "														</div>\r\n"
					+ "													</div>\r\n"
					+ "												</div>\r\n"
					+ "											</div>\r\n"
					+ "											<div class=\"cart-room-money\">\r\n"
					+ "												<div class=\"money-1day-wrap\">\r\n"
					+ "													<div class=\"cart-room-money-tag fontWeight-700 rgbRed-text\">\r\n"
					+ "														Giá Tiền ( <span class=\"rgbRed-text\"> 1 </span> Ngày )\r\n"
					+ "													</div>\r\n"
					+ "													<div\r\n"
					+ "														class=\"cart-room-money-price fontWeight-700 rgbRed-text\">\r\n"
					+ "														<div class=\"cart-room-money-text padding-8\">\r\n"
					+ "															<span class=\"money-1-day\">"+Money_one_day+"</span> <span\r\n"
					+ "																class=\"semiBlack-text\">&nbsp;VNĐ</span>\r\n"
					+ "														</div>\r\n"
					+ "													</div>\r\n"
					+ "												</div>\r\n"
					+ "\r\n"
					+ "												<div class=\"total-days-wrap\">\r\n"
					+ "													<div class=\"fontWeight-700 rgbRed-text\">Tổng số ngày</div>\r\n"
					+ "													<div class=\"fontWeight-700 rgbRed-text\">\r\n"
					+ "														<span class=\"total_days\">"+total_day+"</span> <span\r\n"
					+ "															class=\"semiBlack-text fontWeight-600\">Ngày</span>\r\n"
					+ "													</div>\r\n"
					+ "												</div>\r\n"
					+ "\r\n"
					+ "												<div class=\"total-room-money\">\r\n"
					+ "													<div class=\"fontWeight-700 rgbRed-text\">Tổng số Tiền</div>\r\n"
					+ "													<div class=\"fontWeight-700 rgbRed-text\">\r\n"
					+ "														<span class=\"room-total-money-text\">"+Room_total_money+"</span> <span\r\n"
					+ "															class=\"semiBlack-text fontWeight-600\">VND</span>\r\n"
					+ "													</div>\r\n"
					+ "												</div>\r\n"
					+ "											</div>\r\n"
					+ "										</div>\r\n"
					+ "									</div>");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
