package entity;

public class AdminUsers {
	private int User_id;
	private String Name_user;
	private String Phone_number;
	private String Avatar;
	private String Email;
	private int TotalOrder;
	public AdminUsers() {
		super();
	}
	public AdminUsers(int User_id, String Name_user, String Avatar, String Email, String Phone_number, int TotalOrder) {
		this.User_id=User_id;
		this.Name_user=Name_user;
		this.Phone_number=Phone_number;
		this.Email=Email;
		this.Avatar=Avatar;
		this.TotalOrder=TotalOrder;
	}
	public String getAvatar() {
		return Avatar;
	}
	public String getName_user() {
		return Name_user;
	}
	public String getPhone_number() {
		return Phone_number;
	}
	public int getUser_id() {
		return User_id;
	}
	public void setAvatar(String avatar) {
		Avatar = avatar;
	}
	public void setName_user(String name_user) {
		Name_user = name_user;
	}
	public void setPhone_number(String phone_number) {
		Phone_number = phone_number;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	public int getTotalOrder() {
		return TotalOrder;
	}
	public void setTotalOrder(int totalOrder) {
		TotalOrder = totalOrder;
	}
	@Override
	public String toString() {
		return "AdminUsers [User_id=" + User_id + ", Name_user=" + Name_user + ", Phone_number=" + Phone_number
				+ ", Avatar=" + Avatar + ", Email=" + Email + ", TotalOrder=" + TotalOrder + "]";
	}
}