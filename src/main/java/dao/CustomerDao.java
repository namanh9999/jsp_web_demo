package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Customer;

public class CustomerDao implements DaoInterface<Customer> {

	public static CustomerDao getInstance() {
		return new CustomerDao();
	}

	private ArrayList<Customer> data = new ArrayList<>();

	@Override
	public ArrayList<Customer> selectAll() {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Customer;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String customerID = rs.getString("customerID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String deliAddress = rs.getString("deliAddress");
				String shipAddress = rs.getString("shipAddress");
				String buyAddress = rs.getString("buyAddress");
				Date birth = rs.getDate("birth");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				boolean emailRegister = rs.getBoolean("emailRegister");
				Customer cs = new Customer(customerID, userName, passWord, fullName, gender, address, deliAddress,
						shipAddress, buyAddress, birth, phoneNumber, email, emailRegister);
				data.add(cs);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public Customer selectByID(String id) {
		Customer cs = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Customer where customerID = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String customerID = rs.getString("customerID");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String deliAddress = rs.getString("deliAddress");
				String shipAddress = rs.getString("shipAddress");
				String buyAddress = rs.getString("buyAddress");
				Date birth = rs.getDate("birth");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				boolean emailRegister = rs.getBoolean("emailRegister");
				cs = new Customer(customerID, userName, passWord, fullName, gender, address, deliAddress, shipAddress,
						buyAddress, birth, phoneNumber, email, emailRegister);
				break;
			}

			JDBCUtil.closeConnection(conn);
			return cs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cs;
	}

	public Customer selectByUserName(String userName) {
		Customer cs = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Customer where userName = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String customerID = rs.getString("customerID");
				String passWord = rs.getString("passWord");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String deliAddress = rs.getString("deliAddress");
				String shipAddress = rs.getString("shipAddress");
				String buyAddress = rs.getString("buyAddress");
				Date birth = rs.getDate("birth");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				String avatarPath = rs.getString("avatarPath");
				boolean emailRegister = rs.getBoolean("emailRegister");
				cs = new Customer(customerID, userName, passWord, fullName, gender, address, deliAddress, shipAddress,
						buyAddress, birth, phoneNumber, email, emailRegister,avatarPath);
				break;
			}

			JDBCUtil.closeConnection(conn);
			return cs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cs;
	}

	@Override
	public int insert(Customer t) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into Customer values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getCustomerID());
			ps.setString(2, t.getUserName());
			ps.setString(3, t.getPassWord());
			ps.setString(4, t.getFullName());
			ps.setString(5, t.getGender());
			ps.setString(6, t.getAddress());
			ps.setString(7, t.getDeliAddress());
			ps.setString(8, t.getShipAddress());
			ps.setString(9, t.getBuyAddress());
			ps.setDate(10, t.getBirth());
			ps.setString(11, t.getPhoneNumber());
			ps.setString(12, t.getEmail());
			ps.setBoolean(13, t.isEmailRegister());
			ps.setString(14, t.getAvatarPath());
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<Customer> list) {
		int count = 0;
		for (Customer checkOnList : list) {
			count += this.insert(checkOnList);
		}
		return count;
	}

	@Override
	public int remove(Customer t) {

		int result = 0;
		Connection conn = JDBCUtil.getConnection();

		String sql = "delete from Customer where customerID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			result = 1;
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int removeAll(ArrayList<Customer> list) {
		int count = 0;
		for (Customer checkOnList : list) {
			count += this.remove(checkOnList);
		}
		return count;
	}

	@Override
	public int update(Customer t) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "update Customer set userName = ?, passWord = ?, "
				+ "fullName = ?, gender = ?, address = ?, deliAddress = ?,"
				+ " shipAddress = ?, buyAddress = ?, birth = ?, phoneNumber = ?, "
				+ "email = ?, emailRegister = ? where customerID = ? ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getPassWord());
			ps.setString(3, t.getFullName());
			ps.setString(4, t.getGender());
			ps.setString(5, t.getAddress());
			ps.setString(6, t.getDeliAddress());
			ps.setString(7, t.getShipAddress());
			ps.setString(8, t.getBuyAddress());
			ps.setDate(9, t.getBirth());
			ps.setString(10, t.getPhoneNumber());
			ps.setString(11, t.getEmail());
			ps.setBoolean(12, t.isEmailRegister());
			ps.setString(13, t.getCustomerID());
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean userNameCheck(String userName) {
		boolean check = false;
		Connection conn = JDBCUtil.getConnection();
		String sql = " select userName from Customer where userName = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				check = true;
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public boolean userNameCheck(String userName, String password) {
		boolean check = false;
		Connection conn = JDBCUtil.getConnection();
		String sql = " select userName from Customer where userName = ? and password = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				check = true;
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public boolean passCheck(String pass) {
		boolean check = false;
		Connection conn = JDBCUtil.getConnection();
		String sql = " select passWord from Customer where passWord =?   ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pass);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("passWord").equals(pass)) {
					check = true;
				}
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public void updatePassword(String userName, String pass) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update Customer set passWord = ? where userName = ? ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pass);
			ps.setString(2, userName);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void avatarUpdate( Customer cm ,String userID, String path) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update Customer set avatarPath = ? where customerID = ?   ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, path);
			ps.setString(2, userID);
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
