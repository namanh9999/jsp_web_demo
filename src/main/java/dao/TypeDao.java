package dao;

import model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TypeDao implements DaoInterface<Type> {

	private ArrayList<Type> data = new ArrayList<Type>();

	public static TypeDao getInstance() {
		return new TypeDao();
	}

	public ArrayList<Type> selectAll() {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Type";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String typeID = rs.getString("typeID");
				String typeName = rs.getString("typeName");
				Type result = new Type(typeID, typeName);
				data.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public Type selectByID(String id) {
		Type result = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Type where typeID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String typeID = rs.getString("typeID");
				String typeName = rs.getString("typeName");
				result =  new Type(typeID, typeName);
			}
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int insert(Type t) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Type where typeID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getTypeID());
			ps.setString(2, t.getTypeName());
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int insertAll(ArrayList<Type> list) {
		int count = 0;
		for (Type checkOnList : list) {
			this.insert(checkOnList);
			count++;
		}
		return count;
	}

	public int remove(Type t) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from Type where typeID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getTypeID());
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return 0;
	}

	public int removeAll(ArrayList<Type> list) {
		int count = 0;
		for (Type checkOnList : list) {
			this.remove(checkOnList);
			count++;
		}
	return count;
	}

	public int update(Type t) {
		this.remove(t);
		this.insert(t);
		return 1;
	}

}
