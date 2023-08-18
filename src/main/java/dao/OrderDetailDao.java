package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Order;
import model.OrderDetail;
import model.Product;

public class OrderDetailDao implements DaoInterface<OrderDetail> {
	private ArrayList<OrderDetail> data = new ArrayList<>();

	public OrderDetail getInstance() {
		return new OrderDetail();
	}

	@Override
	public ArrayList<OrderDetail> selectAll() {
		Connection conn = JDBCUtil.getConnection();
		String sql = "Select * from OrderDetail";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String orDetailID = rs.getString("orderDetailID");
				String id = rs.getString("orders");
				Order order = OrderDao.getInstance().selectByID(id);
				String proId = rs.getString("product");
				Product product = ProductDao.getInstance().selectByID(proId);
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				double discount = rs.getDouble("discount");
				double cost = rs.getDouble("cost");
				double tax = rs.getDouble("tax");
				double totalCost = rs.getDouble("totalCost");

				OrderDetail od = new OrderDetail(orDetailID, order, product, quantity, price, discount, cost, tax,
						totalCost);
				data.add(od);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public OrderDetail selectByID(String id) {
		OrderDetail result = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "Select * from OrderDetail where orderDetail = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String orDetailID = rs.getString("orderDetailID");
				String orid = rs.getString("orders");
				Order order = OrderDao.getInstance().selectByID(orid);
				String proId = rs.getString("product");
				Product product = ProductDao.getInstance().selectByID(proId);
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				double discount = rs.getDouble("discount");
				double cost = rs.getDouble("cost");
				double tax = rs.getDouble("tax");
				double totalCost = rs.getDouble("totalCost");
				OrderDetail od = new OrderDetail(orDetailID, order, product, quantity, price, discount, cost, tax,
						totalCost);
				data.add(od);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(OrderDetail t) {

		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into OrderDetail values (?,?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getOrDetailID());
			ps.setString(2, t.getOrder().getOrderID());
			ps.setString(3, t.getProductID().getProductID());
			ps.setInt(4, t.getQuantity());
			ps.setDouble(5, t.getPrice());
			ps.setDouble(6, t.getDiscount());
			ps.setDouble(7, t.getCost());
			ps.setDouble(8, t.getTax());
			ps.setDouble(9, t.getTotalCost());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertAll(ArrayList<OrderDetail> list) {
		int count = 0;
		for (OrderDetail t : list) {
			this.insert(t);
			count++;
		}
		return count;
	}

	@Override
	public int remove(OrderDetail t) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from  OrderDetail where orderDetailID = ? ) ";
		String sql1 = "delete from  Orders where orderID = ? ) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps.setString(1, t.getOrDetailID());
			ps1.setString(1, t.getOrder().getOrderID());
			ps1.executeUpdate();
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int removeAll(ArrayList<OrderDetail> list) {
		int count = 0;
		for (OrderDetail t : list) {
			this.remove(t);
			count++;
		}
	return count;
	}

	@Override
	public int update(OrderDetail t) {
		this.remove(t);
		this.insert(t);
	return 1;
	}

}
