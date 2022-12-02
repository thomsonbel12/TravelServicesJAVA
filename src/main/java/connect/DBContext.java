package connect;

//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.*;

public class DBContext {
	private final String serverName = "localhost";
	private final String dbName = "DuLichThoii";
	private final String portNumber = "1433";
	private final String instance = "";// LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
	private final String userID = "sa";
	private final String password = "123456";
	
	
	public Connection getConnection() throws Exception 
	{
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}

	/* Insert your other code right after this comment */
	/*
	 * Change/update information of your database connection, DO NOT change name of
	 * instance variables in this class
	 */
	public static void main(String[] args) {
		try {
			System.out.println(new DBContext().getConnection());
			System.out.println("thanh cong");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("loi");
		}
	}
}
