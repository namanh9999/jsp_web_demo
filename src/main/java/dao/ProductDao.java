package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import model.Author;
import model.EnumClass;
import model.Product;
import model.Type;

public class ProductDao implements DaoInterface<Product> {

	private ArrayList<Product> data = new ArrayList<Product>();

	public static ProductDao getInstance() {
		return new ProductDao();
	}

	public ArrayList<Product> selectAll() {
		Connection conn = JDBCUtil.getConnection();

		String sql = "select * from Product";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String productID = rs.getString("productID");
				String productName = rs.getString("productName");
				String id = rs.getString("author");
				Author author = AuthorDao.getInstance().selectByID(id);
				int publishYear = rs.getInt("publishYear");
				double cost = rs.getDouble("cost");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				String tid = rs.getString("type");
				Type type = TypeDao.getInstance().selectByID(tid);
				String lagid = rs.getString("language");
				Enum<EnumClass.Language> language = EnumClass.getLanguage(lagid);
				String desription = rs.getString("description");

				Product product = new Product(productID, productName, author, publishYear, cost, price, quantity, type,
						language, desription);
				data.add(product);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public Product selectByID(String id) {
		Product result = null;
		Connection conn = JDBCUtil.getConnection();

		String sql = "select * from Product where productID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String productID = rs.getString("productID");
				String productName = rs.getString("productName");
				String authorid = rs.getString("author");
				Author author = AuthorDao.getInstance().selectByID(authorid);
				int publishYear = rs.getInt("publishYear");
				double cost = rs.getDouble("cost");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				String tid = rs.getString("type");
				Type type = TypeDao.getInstance().selectByID(tid);
				String lagid = rs.getString("language");
				Enum<EnumClass.Language> language = EnumClass.getLanguage(lagid);
				String desription = rs.getString("description");
				result = new Product(productID, productName, author, publishYear, cost, price, quantity, type, language,
						desription);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insert(Product t) {
		int result = 0;

		////

		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into Product values (?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getProductID());
			ps.setString(2, t.getProductName());
			ps.setString(3, t.getAuthor().getAuthorID());
			ps.setInt(4, t.getPublishYear());
			ps.setDouble(5, t.getCost());
			ps.setDouble(6, t.getPrice());
			ps.setInt(7, t.getQuantity());
			ps.setString(8, t.getType().getTypeID());
			String temp = EnumClass.StringHanding(t.getLanguage().toString());
			ps.setString(9, temp);
			ps.setString(10, t.getdescription());

			result = ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		///
		return result;
	}

	public int insertAll(ArrayList<Product> list) {
		int count = 0;
		for (Product t : list) {
			this.insert(t);
			count++;
		}
		return count;
	}

	public int remove(Product t) {
		int result = 0;

		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from Product where productID = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getProductID());
			result = ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int removeAll(ArrayList<Product> list) {
		int count = 0;
		for (Product t : list) {
			this.insert(t);
			count++;
		
		}
		return count;
	}

	public int update(Product t) {
		this.remove(t);
		this.insert(t);
		return 0;
	}

}
