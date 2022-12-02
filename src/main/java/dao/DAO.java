package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connect.DBContext;
import entity.Logins;
import entity.Rooms;
import entity.Users;
import entity.AdminLandLords;
import entity.AdminUsers;
import entity.Benefits;
import entity.Bill_DetailDisplay;
import entity.Bills;
import entity.CartDisplay;
import entity.Carts;
import entity.DisplayHotels;
import entity.DisplayHotelsBySearch;
import entity.Hotels;
import entity.Images;
import entity.LandLords;

public class DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Logins GetLogin(String User_name, String Passworld, String Type, String Role) {
		String query = "select * from Logins where [User_name]=? and [Passworld]=? and [Login_type]=? and [Role]=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, User_name);
			ps.setString(2, Passworld);
			ps.setString(3, Type);
			ps.setString(4, Role);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Logins(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public Logins GetLoginByEmail(String User_name, String Type, String Role) {
		String query = "select * from Logins where [User_name]=? and [Login_type]=? and [Role]=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, User_name);
			ps.setString(2, Type);
			ps.setString(3, Role);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Logins(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public Logins GetLoginByPhone(String User_name, String Type, String Role) {
		String query = "select * from Logins where [User_name]=? and [Login_type]=? and [Role]=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, User_name);
			ps.setString(2, Type);
			ps.setString(3, Role);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Logins(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public Users GetUsers(int User_id) {
		String query = "select * from Users where [User_id]=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, User_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Users(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getString(4), rs.getString(3));
			}
		} catch (Exception e) {

		}
		return null;
	}

	public Hotels GetHotels(int Hotel_id) {
		String query = "select * from Hotels where Hotel_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Hotel_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Hotels(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public Bills GetBillByBillId(int Bill_id) {
		String query = "select * from Bills where Bill_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Bill_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Bills(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public Carts GetCartById(int Cart_id) {
		String query = "select * from Carts where Cart_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Cart_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Carts(rs.getInt(1), rs.getDate(2),rs.getDate(3), rs.getInt(4), rs.getInt(7), rs.getInt(8));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public DisplayHotels getHotelsByHotelId(int Hotel_id) {
		String query = "select * from Hotels where Hotel_id = ?";
		String query1 = "SELECT TOP 1 * FROM [Images] WHERE [Hotel_id]=?;";
		String query2 = "SELECT MIN(Price) MIN_LUONG FROM Rooms WHERE [Hotel_id]=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Hotel_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(1));
				rs1 = ps1.executeQuery();

				ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, rs.getInt(1));
				rs2 = ps2.executeQuery();
				while (rs1.next() && rs2.next())
					return new DisplayHotels(rs.getInt(1), rs.getString(2), rs1.getString(2), rs.getString(3),
							rs.getString(4), rs2.getInt(1), rs.getInt(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public int GetSumCustomerByLandLordId(int LanLdLord_Id) {
		int Sum = 0;
		String query = "select sum(bd.Adult), sum(bd.Children)\r\n"
				+ "from LandLords ll join Hotels h on ll.LandLord_id = h.LandLord_id\r\n"
				+ "join Rooms r on h.Hotel_id = r.Hotel_id\r\n" + "join Bill_Detail bd on r.Room_id = bd.Room_id\r\n"
				+ "where ll.LandLord_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, LanLdLord_Id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return Sum = rs.getInt(1) + rs.getInt(2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Sum;
	}

	public int GetSumPriceByLandLordId(int LanLdLord_Id) {
		int Sum = 0;
		String query = "select sum(bd.Total_money)\r\n"
				+ "from LandLords ll join Hotels h on ll.LandLord_id = h.LandLord_id\r\n"
				+ "join Rooms r on h.Hotel_id = r.Hotel_id\r\n" + "join Bill_Detail bd on r.Room_id = bd.Room_id\r\n"
				+ "where ll.LandLord_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, LanLdLord_Id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return Sum = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Sum;
	}

	public int getSumBillByLandLordId(int LanLdLord_Id) {
		int Sum = 0;
		String query = "select count(b.Bill_id) from LandLords ll \r\n"
				+ "				  join Hotels h on ll.LandLord_id = h.LandLord_id  \r\n"
				+ "				  join Rooms r on h.Hotel_id = r.Hotel_id  \r\n"
				+ "				  join Bill_Detail bd on r.Room_id = bd.Room_id  \r\n"
				+ "				  join Bills b on bd.Bill_id = b.Bill_id\r\n"
				+ "				  where ll.LandLord_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, LanLdLord_Id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return Sum = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Sum;
	}

	public List<Users> get5RecentUserByLandLordId(int LanLdLord_Id) {
		List<Users> list5Users = new ArrayList<>();
		String query = "select DISTINCT Top 5 u.[User_id] , u.Name_User, u.Avatar, u.Email, u.Phone_number\r\n"
				+ "				from LandLords ll  \r\n"
				+ "				join Hotels h on ll.LandLord_id = h.LandLord_id   \r\n"
				+ "				join Rooms r on h.Hotel_id = r.Hotel_id   \r\n"
				+ "				join Bill_Detail bd on r.Room_id = bd.Room_id   \r\n"
				+ "				join Bills b on bd.Bill_id = b.Bill_id\r\n"
				+ "				join Users u on b.[User_id] = u.[User_id]\r\n"
				+ "				where ll.LandLord_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, LanLdLord_Id);
			rs = ps.executeQuery();
			while (rs.next()) {
				list5Users.add(
						new Users(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getString(4), rs.getString(3)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list5Users;
	}

	public Rooms GetRoomById(int Room_id) {
		String query = "select * from Rooms where Room_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Room_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Rooms();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void EditName(String Name, int User_id) {
		String query = "UPDATE [Users] SET Name_User =? WHERE User_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, Name);
			ps.setInt(2, User_id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void UpdateAvailableRoom(int Room_id, int Available_Room) {
		String query = "UPDATE [Rooms] SET Available_Room =? WHERE Room_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Available_Room);
			ps.setInt(2, Room_id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void EditEmail(String Email, int User_id) {
		String query = "UPDATE [Users] SET [Email] =? WHERE User_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, Email);
			ps.setInt(2, User_id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void EditPhoneNumber(String phone, int User_id) {
		String query = "UPDATE [Users] SET [Phone_number] =? WHERE User_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, phone);
			ps.setInt(2, User_id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void EditPassword(String pass, int Login_id) {
		String query = "UPDATE [Logins] SET Passworld =? WHERE [Login_id]=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, pass);
			ps.setInt(2, Login_id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void UpdateImage(String Image, int User_id) {
		String query = "UPDATE [Users] SET Avatar =? WHERE User_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, Image);
			ps.setInt(2, User_id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void UpdatePasswordByMail(String pass, String email) {
		String query = "UPDATE [Logins] SET Passworld =? WHERE [User_name]=? and [Login_type]='email' and [Role]='user'";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, pass);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void UpdateDateByCartId(String CheckIn_date, String CheckOut_day, int cart_id) {
		String query = "UPDATE Carts SET CheckIn_date =?, CheckOut_date=? WHERE Cart_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, CheckIn_date);
			ps.setString(2, CheckOut_day);
			ps.setInt(3, cart_id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void UpdatePasswordByPhone(String pass, String phone) {
		String query = "UPDATE [Logins] SET Passworld =? WHERE [User_name]=? and [Login_type]='sdt' and [Role]='user'";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, pass);
			ps.setString(2, phone);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Logins CheckLoginGoogle(String email) {
		String query = "select * from Logins where [User_name]=? and [Login_type]='google'";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Logins(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public Logins CheckLoginFacebook(String ID) {
		String query = "select * from Logins where [User_name]=? and [Login_type]='facebook'";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, ID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Logins(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public Logins Insert_Login(String user_name, String login_type, String role, String pass) {
		String query = "INSERT INTO dbo.Logins([User_name], [Login_type], [Role],[Passworld]) VALUES(?,?,?,?)";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user_name);
			ps.setString(2, login_type);
			ps.setString(3, role);
			ps.setString(4, pass);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void AddToCart(int room) {

	}

	public void Insert_User(int user_id, String Name_user, String avatar, String phone) {
		String query = "INSERT INTO dbo.Users([User_id], [Name_User],[Avatar], [Phone_number]) VALUES(?,?,?,?)";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, user_id);
			ps.setString(2, Name_user);
			ps.setString(3, avatar);
			ps.setString(4, phone);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void Insert_Bill(int Total, String Datee, int Total_rooms, String Payment_Method, int User_id) {
		String query = "insert into Bills (Total, [Date], Total_rooms, Payment_Method , [User_id])\r\n"
				+ "	values (?,?,?,?,?)";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Total);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String newDate = LocalDate.parse(Datee, formatter).format(formatter2);

			ps.setString(2, newDate);
			ps.setInt(3, Total_rooms);
			ps.setString(4, Payment_Method);
			ps.setInt(5, User_id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void Insert_Bill_Detail(String CheckIn_date, String CheckOut_date, int Total_rooms, int Total_money,
			int Adult, int Children, int Room_id, int Bill_id, int Status) {
		String query = "insert into Bill_Detail(CheckIn_date, CheckOut_date, \r\n"
				+ "				Total_rooms, Total_money,\r\n" + "				Adult, Children, Room_id, Bill_id, Status)\r\n"
				+ "	values (?,?,?,?,?,?,?,?,?)";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, CheckIn_date);
			ps.setString(2, CheckOut_date);
			ps.setInt(3, Total_rooms);
			ps.setInt(4, Total_money);
			ps.setInt(5, Adult);
			ps.setInt(6, Children);
			ps.setInt(7, Room_id);
			ps.setInt(8, Bill_id);
			ps.setInt(9, Status);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void Insert_Cart(String DayInAddToCart, String DayOutAddToCart, String totalRoom, String totalAdults,
			String totalChildren, String Room_id, int User_id) {
		String query = "insert into Carts (CheckIn_date, CheckOut_date, Total_rooms,Adult, "
				+ "Children, [User_id], Room_id) " + "values (?,?,?,?,?,?,?)";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, DayInAddToCart);
			ps.setString(2, DayOutAddToCart);
			ps.setString(3, totalRoom);
			ps.setString(4, totalAdults);
			ps.setString(5, totalChildren);
			ps.setInt(6, User_id);
			ps.setString(7, Room_id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void DeleteCart(int Cart_id) {
		String query = "DELETE from Carts where Cart_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Cart_id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void DeleteUser(int UserId) {
		String query = "DELETE from Carts where User_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, UserId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		query = "UPDATE [Bills] SET User_id =null WHERE User_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, UserId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		query = "DELETE from Users where User_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, UserId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		query = "DELETE from Logins where Login_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, UserId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void DeleteLandLord(int LandLordId) {
		String query = "SELECT Hotel_id FROM Hotels WHERE LandLord_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, LandLordId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int HotelId = rs.getInt(1);

				query = "UPDATE [Rooms] SET Available_Room=0 WHERE Hotel_id = ?";
				try {
					PreparedStatement ps1 = null;
					ps1 = conn.prepareStatement(query);
					ps1.setInt(1, HotelId);
					ps1.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}

				query = "SELECT Room_id FROM Rooms WHERE Hotel_id=?";
				try {
					PreparedStatement ps1 = null;
					ResultSet rs1 = null;
					ps1 = conn.prepareStatement(query);
					ps1.setInt(1, HotelId);
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						int RoomId = rs1.getInt(1);
						query = "DELETE from Carts where Room_id=?";
						try {
							PreparedStatement ps2 = null;
							ps2 = conn.prepareStatement(query);
							ps2.setInt(1, RoomId);
							ps2.executeUpdate();
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		query = "UPDATE [Hotels] SET LandLord_id=null WHERE LandLord_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, LandLordId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		query = "DELETE from LandLords where LandLord_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, LandLordId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		query = "DELETE from Logins where Login_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, LandLordId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<DisplayHotels> getAllHotels() {
		List<DisplayHotels> list = new ArrayList<>();
		String query = "select * from Hotels;";
		String query1 = "SELECT TOP 1 * FROM [Images] WHERE [Hotel_id]=?;";
		String query2 = "SELECT MIN(Price) MIN_LUONG FROM Rooms WHERE [Hotel_id]=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(1));
				rs1 = ps1.executeQuery();

				ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, rs.getInt(1));
				rs2 = ps2.executeQuery();
				while (rs1.next() && rs2.next())
					list.add(new DisplayHotels(rs.getInt(1), rs.getString(2), rs1.getString(2), rs.getString(3),
							rs.getString(4), rs2.getInt(1), rs.getInt(5)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

	}

	public List<Hotels> GetHotelsByLandLordId(int LandLordId) {
		List<Hotels> list = new ArrayList<>();
		String query = "SELECT * FROM [Hotels] WHERE LandLord_id=?;";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, LandLordId);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Hotels(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

	}

	public List<DisplayHotels> getAllHotelsByCity(String address) {
		List<DisplayHotels> list = new ArrayList<>();
		String City = null;
		if (address.equals("hn"))
			City = "%Hà Nội%";
		else if (address.equals("hcm"))
			City = "%Hồ Chí Minh%";
		else if (address.equals("dn"))
			City = "%Đà Nẵng%";
		else if (address.equals("vt"))
			City = "%Vũng Tàu%";
		else if (address.equals("dl"))
			City = "%Đà Lạt%";
		String query = "SELECT * FROM dbo.Hotels WHERE [Address] LIKE ? and LandLord_id is not null";
		String query1 = "SELECT TOP 1 * FROM [Images] WHERE [Hotel_id]=?;";
		String query2 = "SELECT MIN(Price) MIN_PRICE FROM Rooms WHERE [Hotel_id]=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, City);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;

				ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(1));
				rs1 = ps1.executeQuery();

				ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, rs.getInt(1));
				rs2 = ps2.executeQuery();
				while (rs1.next() && rs2.next())
					list.add(new DisplayHotels(rs.getInt(1), rs.getString(2), rs1.getString(2), rs.getString(3),
							rs.getString(4), rs2.getInt(1), rs.getInt(5)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

	}

	public List<DisplayHotelsBySearch> getAllHotelsBySearch(String Search1, String search_type) {
		List<DisplayHotelsBySearch> list = new ArrayList<>();
		String Search = "%" + Search1 + "%";
		//		query = "Select * from [Hotels] where [Address] like ?  or Name_Hotel like ? and LandLord_id is not null";
		String query = null;
		if(search_type.equals("0"))
			query="Select * from [Hotels] where [Address] like ?  or Name_Hotel like ? and LandLord_id is not null";
		if(search_type.equals("1"))
			query="Select * from [Hotels] where [Address] like ?  and LandLord_id is not null";
		if(search_type.equals("2"))
			query="Select * from [Hotels] where [Name_Hotel] like ? and LandLord_id is not null";
		String query1 = "SELECT TOP 5 * FROM [Images] WHERE [Hotel_id]=?;";
		String query2 = "SELECT MIN(Price) MIN_PRICE FROM Rooms WHERE [Hotel_id]=?";
		String query3 = "SELECT TOP 8 * FROM [Benefits] WHERE [Hotel_id]=?;";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			if(search_type.equals("0")) {
				ps.setString(1, Search);
				ps.setString(2, Search);
			}
			else
				ps.setString(1, Search);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				PreparedStatement ps3 = null;
				ResultSet rs3 = null;
				ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(1));
				rs1 = ps1.executeQuery();

				ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, rs.getInt(1));
				rs2 = ps2.executeQuery();

				ps3 = conn.prepareStatement(query3);
				ps3.setInt(1, rs.getInt(1));
				rs3 = ps3.executeQuery();

				String[] arrImg = new String[] { null, null, null, null, null };
				int i = 0;
				while (rs1.next()) {
					arrImg[i] = rs1.getString(2);
					i++;
				}
				String[] arrBene = new String[8];
				i = 0;
				while (rs3.next()) {
					arrBene[i] = rs3.getString(2);
					i++;
				}
				while (rs2.next())
					list.add(new DisplayHotelsBySearch(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs2.getInt(1), rs.getInt(5), arrImg[0], arrImg[1], arrImg[2], arrImg[3], arrImg[4],
							arrBene[0], arrBene[1], arrBene[2], arrBene[3], arrBene[4], arrBene[5], arrBene[6],
							arrBene[7]));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

	}

	public List<CartDisplay> getAllCartsByUserId(int User_id) {
		List<CartDisplay> list = new ArrayList<>();
		String query = "select * from Carts where User_id = ?";
		String query1 = "select h.Name_Hotel from Rooms r join Hotels h on r.Hotel_id = h.Hotel_id where Room_id = ?";
		String query2 = "SELECT Price, Available_Room, Adult, Children  FROM Rooms WHERE [Room_id]=?";
		String query3 = "SELECT TOP 1 * FROM [Images] WHERE [Room_id]=?;";
		String query4 = "SELECT Type, Hotel_id FROM Rooms WHERE [Room_id]=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, User_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				PreparedStatement ps3 = null;
				ResultSet rs3 = null;
				PreparedStatement ps4 = null;
				ResultSet rs4 = null;
				ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(8));
				rs1 = ps1.executeQuery();

				ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, rs.getInt(8));
				rs2 = ps2.executeQuery();

				ps3 = conn.prepareStatement(query3);
				ps3.setInt(1, rs.getInt(8));
				rs3 = ps3.executeQuery();

				ps4 = conn.prepareStatement(query4);
				ps4.setInt(1, rs.getInt(8));
				rs4 = ps4.executeQuery();
				while (rs1.next() && rs2.next() && rs3.next() && rs4.next())
					list.add(new CartDisplay(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4), rs1.getString(1),
							rs2.getInt(1), rs3.getString(2), rs4.getString(1), rs.getInt(5), rs.getInt(6), rs.getInt(7),
							rs.getInt(8), rs4.getInt(2), rs2.getInt(2), rs2.getInt(3), rs2.getInt(4), null));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

	}

	public List<AdminUsers> GetAllUsersBySearch(String Search1) {
		
		String Search = "%" + Search1 + "%";
		List<AdminUsers> list = new ArrayList<>();
		String query = "select * from Users WHERE Name_User LIKE ?";
		String query1 = "Select COUNT(*) from Bills where User_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, Search);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(1));
				rs1 = ps1.executeQuery();
				while (rs1.next()) {
					list.add(new AdminUsers(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getString(4),
							rs.getString(3), rs1.getInt(1)));
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public List<AdminLandLords> GetAllLandLordsBySearch(String Search1) {
		List<AdminLandLords> list = new ArrayList<>();
		String Search = "%" + Search1 + "%";
		String query = "Select * from LandLords WHERE Name_LandLord LIKE ?";
		String query1 = "select sum(bd.Total_money)\r\n"
				+ "from LandLords ll join Hotels h on ll.LandLord_id = h.LandLord_id\r\n"
				+ "join Rooms r on h.Hotel_id = r.Hotel_id\r\n" + "join Bill_Detail bd on r.Room_id = bd.Room_id\r\n"
				+ "where ll.LandLord_id = ?";
		String query2 = "select count(b.Bill_id) from LandLords ll \r\n"
				+ "				  join Hotels h on ll.LandLord_id = h.LandLord_id  \r\n"
				+ "				  join Rooms r on h.Hotel_id = r.Hotel_id  \r\n"
				+ "				  join Bill_Detail bd on r.Room_id = bd.Room_id  \r\n"
				+ "				  join Bills b on bd.Bill_id = b.Bill_id\r\n"
				+ "				  where ll.LandLord_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, Search);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(1));
				rs1 = ps1.executeQuery();

				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, rs.getInt(1));
				rs2 = ps2.executeQuery();
				while (rs1.next() && rs2.next()) {
					list.add(new AdminLandLords(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs2.getInt(1), rs1.getInt(1)));
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public List<Rooms> getAllRoomByHotelId(int hotel_ID) {
		List<Rooms> list = new ArrayList<>();
		String query = "select * from Rooms where Hotel_id = ? and Available_Room>0";
		String query1 = "SELECT TOP 1 * FROM [Images] WHERE [Room_id]=?;";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, hotel_ID);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;

				ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(1));
				rs1 = ps1.executeQuery();
				while (rs1.next())
					list.add(new Rooms(rs.getInt(1), rs.getString(2), rs1.getString(2), rs.getInt(3), rs.getInt(4),
							rs.getInt(5), rs.getInt(6), rs.getInt(7)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<Images> GetALlImagesByRoomId(int Room_id) {
		List<Images> list = new ArrayList<>();
		String query = "select * from Images where Hotel_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Room_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Images(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public List<Benefits> GetAllBenefitsByHotelId(int Hotel_ID) {
		List<Benefits> list = new ArrayList<>();
		String query = "select * from Benefits where Hotel_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Hotel_ID);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Benefits(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<Images> GetFiveImagesByHotelId(int Hotel_id) {
		List<Images> list = new ArrayList<>();
		String query = "select top 5 * from Images where Hotel_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Hotel_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Images(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<Bills> GetBillsByUserId(int User_id) {
		List<Bills> list = new ArrayList<>();
		String query = "select * from Bills where [User_id] = ?";

		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, User_id);
			rs = ps.executeQuery();
			int i=0;
			while (rs.next()) {
				i++;
				list.add(new Bills(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4), rs.getInt(5), User_id));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<Bill_DetailDisplay> GetBillDetailByBillId(int Bill_id) {
		List<Bill_DetailDisplay> list = new ArrayList<>();
		String query = "select * from Bill_Detail where Bill_id = ?";
		String query1 = "SELECT Price from Rooms where Room_id =?";
		String query2 = "select h.Name_Hotel from Rooms r join Hotels h on r.Hotel_id = h.Hotel_id where Room_id = ?";
		String query3 = "SELECT TOP 1 * FROM [Images] WHERE [Room_id]=?;";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, Bill_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(8));
				ResultSet rs1 = ps1.executeQuery();

				PreparedStatement ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, rs.getInt(8));
				ResultSet rs2 = ps2.executeQuery();

				PreparedStatement ps3 = conn.prepareStatement(query3);
				ps3.setInt(1, rs.getInt(8));
				ResultSet rs3 = ps3.executeQuery();

				while (rs1.next() && rs2.next() && rs3.next()) {
					list.add(new Bill_DetailDisplay(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4),
							rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs3.getString(2),
							rs1.getInt(1), rs2.getString(1), rs.getInt(10)));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public String AddZero(String number) {
		if (number.length() == 1) {
			number = "0" + number;
		}
		return number;
	}

	public int Last_Bill_Id() {
		String query = "select top 1* from Bills order by Bill_id desc";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {

		}
		return 0;
	}

	public void Insert_LandLord(int landlord_id, String Name_landlord, String avatar, String phone, String email) {
		String query = "INSERT INTO dbo.LandLords([LandLord_id],[Name_LandLord],[Avatar], [Phone_number], [Email]) VALUES(?,?,?,?,?)";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, landlord_id);
			ps.setString(2, Name_landlord);
			ps.setString(3, avatar);
			ps.setString(4, phone);
			ps.setString(5, email);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public LandLords GetLandLords(int landlord_id) {
		String query = "select * from LandLords where [LandLord_id]=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, landlord_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new LandLords(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {

		}
		return null;
	}

	public List<AdminUsers> GetAllUsers() {
		List<AdminUsers> list = new ArrayList<>();
		String query = "select * from Users";
		String query1 = "Select COUNT(*) from Bills where User_id=?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(1));
				rs1 = ps1.executeQuery();
				while (rs1.next()) {
					list.add(new AdminUsers(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getString(4),
							rs.getString(3), rs1.getInt(1)));
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<AdminLandLords> GetAllLandLords() {
		List<AdminLandLords> list = new ArrayList<>();
		String query = "Select * from LandLords";
		String query1 = "select sum(bd.Total_money)\r\n"
				+ "from LandLords ll join Hotels h on ll.LandLord_id = h.LandLord_id\r\n"
				+ "join Rooms r on h.Hotel_id = r.Hotel_id\r\n" + "join Bill_Detail bd on r.Room_id = bd.Room_id\r\n"
				+ "where ll.LandLord_id = ?";
		String query2 = "select count(b.Bill_id) from LandLords ll \r\n"
				+ "				  join Hotels h on ll.LandLord_id = h.LandLord_id  \r\n"
				+ "				  join Rooms r on h.Hotel_id = r.Hotel_id  \r\n"
				+ "				  join Bill_Detail bd on r.Room_id = bd.Room_id  \r\n"
				+ "				  join Bills b on bd.Bill_id = b.Bill_id\r\n"
				+ "				  where ll.LandLord_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, rs.getInt(1));
				rs1 = ps1.executeQuery();

				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, rs.getInt(1));
				rs2 = ps2.executeQuery();
				while (rs1.next() && rs2.next()) {
					list.add(new AdminLandLords(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs2.getInt(1), rs1.getInt(1)));
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public static void main(String[] args) {
//		DAO dao = new DAO();
//		List<Bills> listBill = dao.GetBillsByUserId(1);
//		List<Bill_DetailDisplay> listBillDetail = dao.GetBillDetailByBillId(1);
//		for (Bills o : listBill) {
//			System.out.println(o.getListBillDetail());
//		}
	}

}
