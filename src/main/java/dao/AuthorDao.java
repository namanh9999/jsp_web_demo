package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Author;

public class AuthorDao implements DaoInterface<Author> {

	private ArrayList<Author> data = new ArrayList<Author>();

	public static AuthorDao getInstance() {
		return new AuthorDao();
	}

	public ArrayList<Author> selectAll() {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from Author;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String authorID = rs.getString("authorID");
				String fullName = rs.getString("fullName");
				Date birth = rs.getDate("birth");
				String biography = rs.getString("biography");
				Author a = new Author(authorID, fullName, birth, biography);
				data.add(a);
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public Author selectByID(String id) {

		Author result = null;
		Connection conn = JDBCUtil.getConnection();

		String sql = "select * from Author where authorID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String authorID = rs.getString("authorID");
				String fullName = rs.getString("fullName");
				Date birth = rs.getDate("birth");
				String biography = rs.getString("biography");
				result = new Author(authorID, fullName, birth, biography);
			}
			JDBCUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insert(Author t) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into Author values (?, ? , ? , ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getAuthorID());
			ps.setString(2, t.getFullName());
			ps.setDate(3, t.getBirth());
			ps.setString(4, t.getBiography());
			System.out.println(sql);
			result = ps.executeUpdate();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertAll(ArrayList<Author> list) {
		int count = 0;
		for (Author t : list) {
			count += this.insert(t);
		}
		return count;
	}

	public int remove(Author t) {
		int result = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from Author where id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getAuthorID());
			result = ps.executeUpdate();
			JDBCUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public int removeAll(ArrayList<Author> list) {
		int countFinish = 0;
		for (Author t : data) {
			countFinish += this.remove(t);
		}
		return countFinish;
	}

	public int update(Author t) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "Update Author set fullName = ? , birth = ? , biography = ?  where authorID = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getFullName());
			ps.setDate(2, t.getBirth());
			ps.setString(3, t.getBiography());
			ps.setString(4, t.getAuthorID());
			System.out.println(sql);
			ps.executeUpdate();
			JDBCUtil.closeConnection(conn);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

}
