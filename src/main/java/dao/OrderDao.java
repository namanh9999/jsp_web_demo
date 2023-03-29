package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Customer;
import model.EnumClass;
import model.EnumClass.OrderStatus;
import model.EnumClass.Payments;
import model.Order;

public class OrderDao implements DaoInterface<Order> {

	private ArrayList<Order> data = new ArrayList<Order>();

	public static OrderDao getInstance() {
		return new OrderDao();
	}

	public ArrayList<Order> selectAll() {
		Connection conn = JDBCUtil.getConnection();

		String sql = "select * from Orders ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String orderID = rs.getString("orderID");
				String id = rs.getString("customer");
				Customer customer = CustomerDao.getInstance().selectByID(id);
				String buyAddress = rs.getString("buyAddress");
				String deliAddress = rs.getString("deliAddress");
				String od1 = rs.getString("orderStatus");
				Enum<EnumClass.OrderStatus> orderStatus = EnumClass.getOrderStatus(od1);
				String od2 = rs.getString("payments");
				Enum<EnumClass.Payments> payments = EnumClass.getPayments(od2);
				String od3 = rs.getString("paymentStatus");
				Enum<EnumClass.PaymentStatus> paymentStatus = EnumClass.getPaymentStatus(od3);
				double amountPaid = rs.getDouble("amountPaid");
				double missingAmount = rs.getDouble("missingAmount");
				Date orderDate = rs.getDate("orderDate");
				Date shipDate = rs.getDate("shipDate");
				System.out.println(orderStatus);
				Order result = new Order(orderID, customer, buyAddress, deliAddress, orderStatus, payments,
						paymentStatus, amountPaid, missingAmount, orderDate, shipDate);
				data.add(result);
			}

			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public Order selectByID(String findId) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Orders ";
		Order result = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String orderID = rs.getString("orderID");
				String id = rs.getString("customer");
				Customer customer = CustomerDao.getInstance().selectByID(id);
				String buyAddress = rs.getString("buyAddress");
				String deliAddress = rs.getString("deliAddress");
				String od1 = rs.getString("orderStatus");
				Enum<EnumClass.OrderStatus> orderStatus = EnumClass.getOrderStatus(od1);
				String od2 = rs.getString("payments");
				Enum<EnumClass.Payments> payments = EnumClass.getPayments(od2);
				String od3 = rs.getString("paymentStatus");
				Enum<EnumClass.PaymentStatus> paymentStatus = EnumClass.getPaymentStatus(od3);
				double amountPaid = rs.getDouble("amountPaid");
				double missingAmount = rs.getDouble("missingAmount");
				Date orderDate = rs.getDate("orderDate");
				Date shipDate = rs.getDate("shipDate");
				System.out.println(orderStatus);
				result = new Order(orderID, customer, buyAddress, deliAddress, orderStatus, payments, paymentStatus,
						amountPaid, missingAmount, orderDate, shipDate);
				return result;
			}

			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insert(Order t) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into Orders values (?,?,?,?,?,?,?,?,?,?,?)";
		int result = 0;
		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getOrderID());
			ps.setString(2, t.getCustomer().getCustomerID());
			ps.setString(3, t.getBuyAddress());
			ps.setString(4, t.getDeliAddress());
			String value1 = Order.StringHanding(t.getOrderStatus().toString());
			ps.setString(5, value1);
			String value2 = Order.StringHanding(t.getPayments().toString());
			ps.setString(6, value2);
			String value3 = Order.StringHanding(t.getPaymentStatus().toString());
			ps.setString(7, value3);
			ps.setDouble(8, t.getAmountPaid());
			ps.setDouble(9,t.getMissingAmount());
			ps.setDate(10, t.getOrderDate());
			ps.setDate(11, t.getShipDate());
			ps.executeUpdate();
			result++;
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertAll(ArrayList<Order> list) {
		int count = 0;
		for(Order t : list){
			this.insert(t);
			count++;
		}
		return count;
	}

	public int remove(Order t) {
		int count = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = " delete  from Orders  where orderID = ? ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getOrderID());
			ps.executeUpdate();
			} catch (Exception e ) {
				e.printStackTrace();
			}
		return count;
	}


	public int removeAll(ArrayList<Order> list) {
		int count = 0;
		for (Order t : list) {
				this.remove(t);
				count++;
		}
		return count;
	}

	public int update(Order t) {
		this.remove(t);
		this.insert(t);
		
		return 1;
	}

}
