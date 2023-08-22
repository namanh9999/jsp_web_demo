package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Categories;
import model.Product;

public class ProductDao implements DaoInterface<Product> {

	private ArrayList<Product> data = new ArrayList<>();

	public static ProductDao getInstance() {
		return new ProductDao();
	}

	@Override
	public ArrayList<Product> selectAll() {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Product";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String productID = rs.getString("productID");
				String productName = rs.getString("productName");
				String author = rs.getString("author");
				Date publishYear = rs.getDate("publishYear");
				double cost = rs.getDouble("cost");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				String type = rs.getString("categoriesID");
				Categories ct = CategoriesDao.getInstance().selectByID(type);
				String language = rs.getString("language");
				String country = rs.getString("country");
				String desription = rs.getString("description");
				String path = rs.getString("path");
				Product product = new Product(productID, productName, author, publishYear, cost, price, quantity, ct,
						language, country, desription, path);
				data.add(product);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
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
				String author = rs.getString("author");
				Date publishYear = rs.getDate("publishYear");
				double cost = rs.getDouble("cost");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				String type = rs.getString("type");
				Categories ct = CategoriesDao.getInstance().selectByName(type);
				String country = rs.getString("country");
				String language = rs.getString("language");
				String desription = rs.getString("description");
				String path = rs.getString("path");
				result = new Product(productID, productName, author, publishYear, cost, price, quantity, ct, language,
						country, desription, path);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(Product t) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into Product values (?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getProductID());
			ps.setString(2, t.getProductName());
			ps.setString(3, t.getAuthor());
			ps.setDate(4, t.getPublishYear());
			ps.setDouble(5, t.getCost());
			ps.setDouble(6, t.getPrice());
			ps.setInt(7, t.getQuantity());
			ps.setInt(8, t.getCategories().getCategoriesID());
			ps.setString(9, t.getLanguage());
			ps.setString(10, t.getCountry());
			ps.setString(11, t.getDescription());
			ps.setString(12, t.getPath());
			result = ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		///
		return result;
	}

	@Override
	public int insertAll(ArrayList<Product> list) {
		int count = 0;
		for (Product t : list) {
			this.insert(t);
			count++;
		}
		return count;
	}

	@Override
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

	@Override
	public int removeAll(ArrayList<Product> list) {
		int count = 0;
		for (Product t : list) {
			this.insert(t);
			count++;
		}
		return count;
	}

	@Override
	public int update(Product t) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "update Product set productName=?, author = ?, publishYear = ?, cost = ?, price = ?, quantity = ?, type = ?, language= ? , description = ?, path = ? where productID = ?  ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getProductName());
			ps.setString(2, t.getAuthor());
			ps.setDate(3, t.getPublishYear());
			ps.setDouble(4, t.getCost());
			ps.setDouble(5, t.getPrice());
			ps.setInt(6, t.getQuantity());
			ps.setString(7, t.getCategories().getCategoriesName());
			ps.setString(8, t.getLanguage());
			ps.setString(9, t.getCountry());
			ps.setString(10, t.getDescription());
			ps.setString(11, t.getPath());
			ps.setString(12, t.getProductID());
			result = ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String makeID() {
		String result = "";
		Random rd = new Random();
		for (int i = 1; i <= 8; i++) {
			result += rd.nextInt(0, 9);
		}
		return result;
	}

	public int updateMainVideo(String film) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String query = "select path from MainVideo";
		String sql = "update MainVideo set = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String temp = rs.getString("path");
				if (temp == null) {
					ps = conn.prepareStatement("insert into MainVideo values ( ? ) ");
					ps.setString(1, film);
				} else {
					ps = conn.prepareStatement(sql);
					ps.setString(1, film);
				}
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// to do
	public List<Product> selectNextProduct() {
		List<Product> list = new ArrayList<>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Product ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

		} catch (Exception e) {

		}
		return list;
	}

	public String selectMainVideo() {
		String temp = "";
		Connection conn = JDBCUtil.getConnection();
		String sql = "select path from MainVideo ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				temp = rs.getString("path");
				return temp;
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean checkFilmName(String name) {
		String temp = capitalizeAllFirstLetters(name);
		boolean result = false;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select productName from Product where productName = ?";
		try {
			PreparedStatement pd = conn.prepareStatement(sql);
			pd.setString(1, temp);
			ResultSet rs = pd.executeQuery();
			while (rs.next()) {
				if (!((rs.getString("productName")) == null)) {
					result = true;
					System.out.println(result);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// used to capitalize the first letter of every word
	public String capitalizeAllFirstLetters(String name) {
		char[] array = name.toCharArray();
		array[0] = Character.toUpperCase(array[0]);

		for (int i = 1; i < array.length; i++) {
			if (Character.isWhitespace(array[i - 1])) {
				array[i] = Character.toUpperCase(array[i]);
			}
		}
		return new String(array);
	}

	public ArrayList<String> getLanguage() {
		ArrayList<String> list = new ArrayList<>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "select languageName from Language";
		try {
			PreparedStatement pd = conn.prepareStatement(sql);
			ResultSet rs = pd.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("languageName"));
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<String> getCountry() {
		ArrayList<String> list = new ArrayList<>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Country";
		try {
			PreparedStatement pd = conn.prepareStatement(sql);
			ResultSet rs = pd.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("country"));
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (String value : list) {
			System.out.println(value);
		}
		return list;
	}

	public ArrayList<String> getCategories() {
		ArrayList<String> list = new ArrayList<>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "select categoriesName from Categories";
		try {
			PreparedStatement pd = conn.prepareStatement(sql);
			ResultSet rs = pd.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("categoriesName"));
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (String value : list) {
			System.out.println(value);
		}
		return list;
	}

	public ArrayList<Product> testOffset() {
		ArrayList<Product> list = new ArrayList<>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Product limit  16 ";
		try {
			PreparedStatement pd = conn.prepareStatement(sql);
			ResultSet rs = pd.executeQuery();
			while (rs.next()) {
				String productName = rs.getString("productName");
				String author = rs.getString("author");
				Date publishYear = rs.getDate("publishYear");
				double cost = rs.getDouble("cost");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				int type = rs.getInt("categoriesID");
				Categories categories = CategoriesDao.getInstance().selectByID(String.valueOf(type));
				String country = rs.getString("country");
				String language = rs.getString("language");
				String description = rs.getString("description");
				String path = rs.getString("path");

				Product pr = new Product(productName, author, publishYear, cost, price, quantity, categories, language,
						country, description, path);
				list.add(pr);
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Product value : list) {
			System.out.println(value);
		}
		for(Product product : list) {
			System.out.println(product.toString());
		}
		return list;
	}

}