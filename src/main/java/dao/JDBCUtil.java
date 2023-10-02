package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
	private static String DB = "jdbc:mysql://localhost:3306/your_database_name";
	private static String user = "root";
	private static String pass = "your_password";

	public static Connection getConnection() {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB, user, pass);
			System.out.println("Connect Successful !");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connect Failed !");
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				System.out.println(" Closed !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
