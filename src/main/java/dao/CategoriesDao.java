package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Categories;

public class CategoriesDao implements DaoInterface<Categories> {

	private ArrayList<Categories> data = new ArrayList<>();

	public static CategoriesDao getInstance() {
		return new CategoriesDao();
	}

	@Override
	public ArrayList<Categories> selectAll() {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Categories";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("categoriesName");
				int id = rs.getInt("categoriesID");
				Categories result = new Categories(id, name);
				data.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public Categories selectByName(String name) {
		Categories result = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Categories where categoriesName = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int typeID = rs.getInt("categoriesID");
				String typeName = rs.getString("categoriesName");
				result =  new Categories(typeID, typeName);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int insert(Categories t) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into Categories values(?, ? ) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getCategoriesID());
			ps.setString(2, t.getCategoriesName());
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<Categories> list) {
		int count = 0;
		for (Categories checkOnList : list) {
			this.insert(checkOnList);
			count++;
		}
		return count;
	}

	@Override
	public int remove(Categories t) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from Categories where typeID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getCategoriesID());
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

	return 0;
	}

	@Override
	public int removeAll(ArrayList<Categories> list) {
		int count = 0;
		for (Categories checkOnList : list) {
			this.remove(checkOnList);
			count++;
		}
	return count;
	}

	@Override
	public int update(Categories t) {
		this.remove(t);
		this.insert(t);
		return 1;
	}

	@Override
	public Categories selectByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
