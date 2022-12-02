package entity;

import java.util.Date;
import java.util.List;

public class Bills {
    private int Bill_id;
    private int Total;
    private Date Date;
    private int Total_rooms;
    private int Payment_Method;
    private int User_id;
    private int index;
	public Bills() {
		super();
	}
	public Bills(int bill_id, int total, java.util.Date date, int total_rooms, int payment_Method, int user_id) {
		super();
		Bill_id = bill_id;
		Total = total;
		Date = date;
		Total_rooms = total_rooms;
		Payment_Method = payment_Method;
		User_id = user_id;
	}
	public int getBill_id() {
		return Bill_id;
	}
	public void setBill_id(int bill_id) {
		Bill_id = bill_id;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		Total = total;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public int getTotal_rooms() {
		return Total_rooms;
	}
	public void setTotal_rooms(int total_rooms) {
		Total_rooms = total_rooms;
	}
	public int getPayment_Method() {
		return Payment_Method;
	}
	public void setPayment_Method(int payment_Method) {
		Payment_Method = payment_Method;
	}
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	@Override
	public String toString() {
		return "Bills [Bill_id=" + Bill_id + ", Total=" + Total + ", Date=" + Date + ", Total_rooms=" + Total_rooms
				+ ", Payment_Method=" + Payment_Method + ", User_id=" + User_id + "]";
	}

}
