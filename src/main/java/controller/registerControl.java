package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import model.Customer;

/**
 * Servlet implementation class resigster
 */
public class registerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public registerControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public Connection getConnection() {
		String DB = "jdbc:mysql://localhost:3306/Webpage";
		String user = "root";
		String ps = "blackblade";

		try {
			Connection conn = DriverManager.getConnection(DB, user, ps);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean useNameCheck(Connection conn, String userName) {
		boolean result = false;
		String sql = " select userName from Customer where userName = ? ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		String userName = request.getParameter("userName").trim();
		String passWord = request.getParameter("password").trim();
		String rePassWord = request.getParameter("rePassword").trim();
		String fullName = request.getParameter("fullName").trim();
		String gender = request.getParameter("Gender");
		String address = request.getParameter("address").trim();
		String deliAddress = request.getParameter("deliAddress").trim();
		String shipAddress = request.getParameter("shipAddress").trim();
		String buyAddress = request.getParameter("buyAddress").trim();
		String birth = request.getParameter("birth").trim();
		String phoneNumber = request.getParameter("phoneNumber").trim();
		String email = request.getParameter("emailAddress").trim();
		String emailRegister = request.getParameter("emailRegister");

		String e_userName = "";
		String e_Repassword = "";
		System.out.println(userName);
		System.out.println(passWord);
		System.out.println(rePassWord);
		System.out.println(fullName);
		System.out.println(gender);
		System.out.println(address);
		System.out.println(deliAddress);
		System.out.println(shipAddress);
		System.out.println(buyAddress);
		System.out.println(birth);
		System.out.println(phoneNumber);
		System.out.println(email);
		System.out.println(emailRegister);

		request.setAttribute("userName", userName);
		request.setAttribute("fullName", fullName);
		request.setAttribute("gender", gender);
		request.setAttribute("birth", birth);
		request.setAttribute("address", address);
		request.setAttribute("deliAddress", deliAddress);
		request.setAttribute("shipAddress", shipAddress);
		request.setAttribute("buyAddress", buyAddress);
		request.setAttribute("phoneNumber", phoneNumber);
		request.setAttribute("email", email);
		request.setAttribute("emailRegister", emailRegister);

		// check an email address is in a valid format in Java
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher emailMatcher = emailPattern.matcher(email);
		boolean emailCheck = emailMatcher.matches();
		boolean passwordMatch = passWord.equals(rePassWord);
/////
		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
/////

		// boolean uNCheck= this.useNameCheck(this.getConnection(), userName);

		CustomerDao cs = new CustomerDao();

		if (cs.userNameCheck(userName) == true) {
			e_userName += "User name already exist, Please choose new user name <_> ";
			request.setAttribute("e_userName", e_userName);
			url = "/register.jsp";
		} else if (passwordMatch == false) {
			e_Repassword = "Password not match ! ";
			request.setAttribute("e_Repassword", e_Repassword);
			url = "/register.jsp";
		} else if (emailCheck != true) {
			request.setAttribute("e_email", "Email not exist or not true");
			url = "/register.jsp";
		} else {
			url = "/index2.jsp";
			boolean temp = false;
			if (emailRegister.equals("on")) {
				temp = true;
			}
			Date date = Date.valueOf(birth);
			Random rd = new Random();
			String userID = (String) userName + rd.nextInt(1000, 9999);
			Customer cm = new Customer(userID, userName, passWord, fullName, gender, address, deliAddress, shipAddress,
					buyAddress, date, phoneNumber, email, temp);
			cs.insert(cm);
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
