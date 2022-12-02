package entity;

import java.util.Date;

public class Bill_DetailDisplay {
	private int Bill_Detail_id;
	private Date CheckIn_date;
	private Date CheckOut_date;
	private int Total_rooms;
	private int Total_money;
	private int Adult;
	private int Children;
	private int Room_id;
	private int Bill_id;
	private String imdRoom;
	private int price;
	private String Name_Hotel;
	private int Status;
	public Bill_DetailDisplay() {
		super();
	}

	public Bill_DetailDisplay(int bill_Detail_id, Date checkIn_date, Date checkOut_date, int total_rooms,
			int total_money, int adult, int children, int room_id, int bill_id, String imdRoom, int price,
			String name_Hotel, int status) {
		super();
		Bill_Detail_id = bill_Detail_id;
		CheckIn_date = checkIn_date;
		CheckOut_date = checkOut_date;
		Total_rooms = total_rooms;
		Total_money = total_money;
		Adult = adult;
		Children = children;
		Room_id = room_id;
		Bill_id = bill_id;
		this.imdRoom = imdRoom;
		this.price = price;
		Name_Hotel = name_Hotel;
		Status = status;
	}

	public int getBill_Detail_id() {
		return Bill_Detail_id;
	}
	public void setBill_Detail_id(int bill_Detail_id) {
		Bill_Detail_id = bill_Detail_id;
	}
	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public Date getCheckIn_date() {
		return CheckIn_date;
	}
	public void setCheckIn_date(Date checkIn_date) {
		CheckIn_date = checkIn_date;
	}
	public Date getCheckOut_date() {
		return CheckOut_date;
	}
	public void setCheckOut_date(Date checkOut_date) {
		CheckOut_date = checkOut_date;
	}
	public int getTotal_rooms() {
		return Total_rooms;
	}
	public void setTotal_rooms(int total_rooms) {
		Total_rooms = total_rooms;
	}
	public int getTotal_money() {
		return Total_money;
	}
	public void setTotal_money(int total_money) {
		Total_money = total_money;
	}
	public int getAdult() {
		return Adult;
	}
	public void setAdult(int adult) {
		Adult = adult;
	}
	public int getChildren() {
		return Children;
	}
	public void setChildren(int children) {
		Children = children;
	}
	public int getRoom_id() {
		return Room_id;
	}
	public void setRoom_id(int room_id) {
		Room_id = room_id;
	}
	public int getBill_id() {
		return Bill_id;
	}
	public void setBill_id(int bill_id) {
		Bill_id = bill_id;
	}
	public String getImdRoom() {
		return imdRoom;
	}
	public void setImdRoom(String imdRoom) {
		this.imdRoom = imdRoom;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName_Hotel() {
		return Name_Hotel;
	}
	public void setName_Hotel(String name_Hotel) {
		Name_Hotel = name_Hotel;
	}
	@Override
	public String toString() {
		return "Bill_DetailDisplay [Bill_Detail_id=" + Bill_Detail_id + ", CheckIn_date=" + CheckIn_date
				+ ", CheckOut_date=" + CheckOut_date + ", Total_rooms=" + Total_rooms + ", Total_money=" + Total_money
				+ ", Adult=" + Adult + ", Children=" + Children + ", Room_id=" + Room_id + ", Bill_id=" + Bill_id
				+ ", imdRoom=" + imdRoom + ", price=" + price + ", Name_Hotel=" + Name_Hotel + ", Status=" + Status
				+ "]";
	}
	
	
}
