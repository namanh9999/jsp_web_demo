package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Result;

import model.Admin;

public class AdminDao implements DaoInterface<Admin> {

	ArrayList<Admin> data = new ArrayList<>();

	public static AdminDao getInstance() {
		return new AdminDao();
	}

	@Override
	public ArrayList<Admin> selectAll() {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Admin";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String adminID = rs.getString("adminID");
				String userName = rs.getString("userName");
				String fullName = rs.getString("fullName");
				Date dayOfbirth =Date.valueOf( rs.getString("birth"));
				String address = rs.getString("address");
				String password = rs.getString("password");
				String emailRegister = rs.getString("emailRegister");
				String phoneNumber = rs.getString("phoneNumber");
				String creator = rs.getString("creator");
				Date dateCreated = rs.getDate("dateCreated");
				Date lastLoginDate = rs.getDate("lastLoginDate");
				int failed = rs.getInt("failed");
				boolean valid = rs.getBoolean("valid");
				Admin ad = new Admin(adminID, userName, fullName, dayOfbirth, address, password, emailRegister,
						phoneNumber, creator, dateCreated, lastLoginDate, failed, valid);
				data.add(ad);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public Admin selectByID(String id) {
		Admin ad = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Admin where adminID =? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String adminID = rs.getString("adminID");
				String userName = rs.getString("userName");
				String fullName = rs.getString("fullName");
				Date dayOfbirth =Date.valueOf( rs.getString("birth"));
				String address = rs.getString("address");
				String password = rs.getString("password");
				String emailRegister = rs.getString("emailRegister");
				String phoneNumber = rs.getString("phoneNumber");
				String creator = rs.getString("creator");
				Date dateCreated = rs.getDate("dateCreated");
				Date lastLoginDate = rs.getDate("lastLoginDate");
				int failed = rs.getInt("failed");
				boolean valid = rs.getBoolean("valid");
				ad = new Admin(adminID, userName, fullName, dayOfbirth, address, password, emailRegister, phoneNumber,
						creator, dateCreated, lastLoginDate, failed, valid);
				data.add(ad);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ad;
	}

	public Admin selectByUserName(String userName) {
		Admin ad = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Admin where userName =? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String adminID = rs.getString("adminID");
				String fullName = rs.getString("fullName");
				Date dayOfbirth =Date.valueOf( rs.getString("birth"));
				String address = rs.getString("address");
				String password = rs.getString("password");
				String emailRegister = rs.getString("emailRegister");
				String phoneNumber = rs.getString("phoneNumber");
				String creator = rs.getString("creator");
				Date dateCreated = rs.getDate("dateCreated");
				Date lastLoginDate = rs.getDate("lastLoginDate");
				int failed = rs.getInt("failed");
				boolean valid = rs.getBoolean("valid");
				ad = new Admin(adminID, userName, fullName, dayOfbirth, address, password, emailRegister, phoneNumber,
						creator, dateCreated, lastLoginDate, failed, valid);
				data.add(ad);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ad;
	}

	@Override
	public int insert(Admin t) {
		Connection conn = JDBCUtil.getConnection();
		String sql = " insert into  Admin values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getAdminID());
			ps.setString(2, t.getUserName());
			ps.setString(3, t.getFullName());
			ps.setDate(4, t.getDayOfBirth());
			ps.setString(5, t.getAddress());
			ps.setString(6, t.getPassword());
			ps.setString(7, t.getEmailRegister());
			ps.setString(8, t.getPhoneNumber());
			ps.setString(9, t.getCreator());
			ps.setDate(10, t.getDateCreated());
			ps.setDate(11, t.getLastLoginDate());
			ps.setInt(12, 0);
			ps.setBoolean(13, true);
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<Admin> list) {
		return 0;
	}

	@Override
	public int remove(Admin t) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete form Admin where adminID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getAdminID());
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int removeAll(ArrayList<Admin> list) {
		return 0;
	}

	@Override
	public int update(Admin t) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update Admin set  userName =?,fullName = ? , dayOfBitrh = ? , addresss = ?, password = ?, emailRegister =? , phoneNumber = ?, creator = ?, date =? , failed = ? , valid = ? where adminID =? ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getFullName());
			ps.setDate(3, t.getDayOfBirth());
			ps.setString(4, t.getAddress());
			ps.setString(5, t.getPassword());
			ps.setString(6, t.getEmailRegister());
			ps.setString(7, t.getPhoneNumber());
			ps.setString(8, t.getCreator());
			ps.setDate(9, t.getDateCreated());
			ps.setDate(10, t.getLastLoginDate());
			ps.setInt(11, 0);
			ps.setString(12, t.getAdminID());
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getSuperUserMail() {
		String result = "";
		Connection conn = JDBCUtil.getConnection();
		String sql = "select email from Superuser";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString("email");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void updateLoginDateSuperUser(Date lastDate) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update Superuser set lastLogin = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, lastDate);
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int checkSuperUserAccount(String userName, String passWord) {
		int result = 0;
		String userNameCheck = "";
		String passWordCheck = "";
		Connection conn = JDBCUtil.getConnection();
		String sql = "select userName, password , email from Superuser";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userNameCheck = rs.getString("userName");
				passWordCheck = rs.getString("password");
			}
			// close connection to Database
			JDBCUtil.closeConnection(conn);
			if (userName.equals(userNameCheck)) {
				if (passWord.equals(passWordCheck)) {
					result = 1;
				}
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(
					"Database or table not found ! Check and fix on source code at checkSuperUserAction function ");

		}
		return result;
	}

	public int checkAdminAccount(String userName, String passWord) {
		int result = 1;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select userName, password , failed , valid from Admin where userName = ? ";
		String sql2 = "update Admin set failed = ?, valid = ? where userName = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String user = rs.getString("userName");
				String pass = rs.getString("password");
				int failed = rs.getInt("failed");
				boolean valid = rs.getBoolean("valid");
				if (user == null) {
					// user not ready exist
					result = 1;
				} else {
					if (valid) {
						if (pass.equals(passWord)) {
							// log in successfully
							ps = conn.prepareStatement(sql2);
							ps.setInt(1, 0);
							ps.setBoolean(2, true);
							ps.setString(3, userName);
							ps.executeUpdate();
							result = 0;
						} else {
							// checking for amount of login failed to decide lock account
							if (failed <= 4) {
								failed += 1;
								ps = conn.prepareStatement(sql2);
								ps.setInt(1, failed);
								ps.setBoolean(2, true);
								ps.setString(3, userName);
								ps.executeUpdate();
								result = 2;
							} else {
								// account invalid
								ps = conn.prepareStatement(sql2);
								ps.setInt(1, failed);
								ps.setBoolean(2, false);
								ps.setString(3, userName);
								ps.executeUpdate();
								// wrong password if failed 5 times ( lock account )
								result = 3;
							}
						}
					}
				}
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String selectSuperUser() {
		String result = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select email from Superuser";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString("email");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateLoginDate(String userName, Date date) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update Admin set  lastLoginDate =? where userName =? ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setDate(1, date);
			ps.setString(2, userName);
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Date createDate() {
		Date date;
		LocalDate now = LocalDate.now();
		date = Date.valueOf(now);
		return date;
	}
	
	public boolean userNameCheck (String userName) {
		boolean result = false;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select userName from Admin where userName = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
