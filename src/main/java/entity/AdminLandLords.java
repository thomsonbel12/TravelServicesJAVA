package entity;

public class AdminLandLords {
	private int LandLord_id;
	private String Name_LandLord;
	private String Phone_Number;
	private String Avatar;
	private String Email;
	private int TotalOrder;
	private int Profit;
	public AdminLandLords() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminLandLords(int LandLord_id, String Name_LandLord, String Phone_Number, String Avatar, String Email, int TotalOrder, int Profit) {
		super();
		this.LandLord_id = LandLord_id;
		this.Name_LandLord = Name_LandLord;
		this.Phone_Number = Phone_Number;
		this.Avatar = Avatar;
		this.Email = Email;
		this.TotalOrder=TotalOrder;
		this.Profit=Profit;
	}
	public int getLandLord_id() {
		return LandLord_id;
	}
	public void setLandLord_id(int landLord_id) {
		LandLord_id = landLord_id;
	}
	public String getName_LandLord() {
		return Name_LandLord;
	}
	public void setName_LandLord(String name_LandLord) {
		Name_LandLord = name_LandLord;
	}
	public String getPhone_Number() {
		return Phone_Number;
	}
	public void setPhone_Number(String phone_Number) {
		Phone_Number = phone_Number;
	}
	public String getAvatar() {
		return Avatar;
	}
	public void setAvatar(String avatar) {
		Avatar = avatar;
	}
	public String getEmail() {
		return Email;
	}
	public int getTotalOrder() {
		return TotalOrder;
	}
	public void setTotalOrder(int totalOrder) {
		TotalOrder = totalOrder;
	}
	public int getProfit() {
		return Profit;
	}
	public void setProfit(int profit) {
		Profit = profit;
	}
	public void setEmail(String email) {
		Email = email;
	}
	@Override
	public String toString() {
		return "AdminLandLords [LandLord_id=" + LandLord_id + ", Name_LandLord=" + Name_LandLord + ", Phone_Number="
				+ Phone_Number + ", Avatar=" + Avatar + ", Email=" + Email + ", TotalOrder=" + TotalOrder + ", Profit="
				+ Profit + "]";
	}
	
	
}
