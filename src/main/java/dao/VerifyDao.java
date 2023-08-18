package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Customer;
import model.Verify;

public class VerifyDao {

	public Verify selectByCutomerID(String ID) {

		Verify vf = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Verification where customerID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String customerID = rs.getString("customerID");
				Customer customer = CustomerDao.getInstance().selectByID(customerID);
				Date recently = rs.getDate("recently");
				Date lastTime = rs.getDate("lastTime");
				String code = rs.getString("code");
				boolean verify = rs.getBoolean("verify");
				String reason = rs.getString("reason");
				vf = new Verify(customer, recently, lastTime, code, verify, reason);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return vf;
	}

	public boolean checkVerify(String customerID) {
		boolean check = false;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select verify from Verification where customerID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customerID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				check = rs.getBoolean("verify");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public boolean insert(Verify vf) {
		boolean result = false;
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into Verification values ( ? , ? , ? , ? , ?, ? )";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vf.getCustomer().getCustomerID());
			ps.setDate(2, vf.getRecently());
			ps.setDate(3, vf.getLastTime());
			ps.setString(4, vf.getCode());
			ps.setBoolean(5, vf.isVerify());
			ps.setString(6, vf.getReason());
			int temp = ps.executeUpdate();

			if (temp == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean update(Verify vf) {

		boolean result = false;
		String customerID = vf.getCustomer().getCustomerID();
		Date recently = vf.getRecently();
		Date lastTime = vf.getLastTime();
		String code = vf.getCode();
		boolean verify = vf.isVerify();
		String reason = vf.getReason();

		Connection conn = JDBCUtil.getConnection();
		String sql = "Update Verification set  recently = ? , lastTime = ?, code = ?, verify = ?, reason = ?  where customerID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, recently);
			ps.setDate(2, lastTime);
			ps.setString(3, code);
			ps.setBoolean(4, verify);
			ps.setString(5, reason);
			ps.setString(6, customerID);
			ps.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Date getLastDate(String id) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select lastTime from Verification where customerID = ? ";
		Date date = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				date = rs.getDate("lastTime");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}
}
