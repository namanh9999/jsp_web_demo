package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.Encryption;
import dao.CustomerDao;
import model.Customer;

/**
 * Servlet implementation class CustomerController
 */
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("logout")) {
			LogOut(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action.equals("changePass")) {
			ChangePassword(request, response);
		} else if (action.equals("changeInfor")) {
			ChangeInfor(request, response);
		} else if (action.equals("register")) {
			Register(request, response);
		} else if (action.equals("login")) {
			LogIn(request, response);
		}

	}

	private void LogOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String url = "";
			HttpSession session = request.getSession();
			session.invalidate();
			url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/index2.jsp";
			System.out.println(url);
			response.sendRedirect(url);

	}

	private void LogIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";

		HttpSession session = null;
		session = request.getSession();
		System.out.println("this is session 1  : " + session);
		String e_userName = "";
		String userName = request.getParameter("userName");
		System.out.println(userName);
		session.setAttribute("userName", userName);
		System.out.println("This is session 2 \" userName 2 \" " + session.getAttribute("userName"));
		request.setAttribute("session", session);
		String password = request.getParameter("password");
		System.out.println(password);
		password = new Encryption().toSHA1(password);

		System.out.println(password);
		CustomerDao cs = new CustomerDao();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cs.userNameCheck(userName, password) == true) {
			url = "/CustomerDirec/sellingPage.jsp";
		} else {
			url = "/index2.jsp";
			e_userName = "User name or password not match";
			request.setAttribute("e_userName", e_userName);
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private void ChangeInfor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName") + "";
		System.out.println("this is user ID in changeInfor Control" + userName);
		String url = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!userName.equals("null")) {
			String userID = CustomerDao.getInstance().selectByUserName(userName).getCustomerID();
			String password = CustomerDao.getInstance().selectByUserName(userName).getPassWord();
			String email = request.getParameter("emailAddress");
			String fullName = request.getParameter("fullName");
			String gender = request.getParameter("Gender");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String deliAddress = request.getParameter("deliAddress");
			String shipAddress = request.getParameter("shipAddress");
			String buyAddress = request.getParameter("buyAddress");
			String birth = String.valueOf(request.getParameter("birth"));
			String emailRegi = request.getParameter("emailRegister");
			boolean emailRegister = false;
			if (emailRegi.equals("on")) {
				emailRegister = true;
			}
			Customer cs = new Customer(userID, userName, password, fullName, gender, address, deliAddress, shipAddress,
					buyAddress, Date.valueOf(birth), phoneNumber, email, emailRegister);
			CustomerDao.getInstance().update(cs);

			url = "CustomerDirec/changeInfor.jsp";
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);

	}

	private void ChangePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "";
		String oldPass = request.getParameter("oldPass");
		oldPass = new Encryption().toSHA1(oldPass);
		String newPass = request.getParameter("newPass");
		String confirm = request.getParameter("confirm");

		String temp = new Encryption().toSHA1(confirm);
		String e_oldPass = "";
		String e_confirm = "";
		CustomerDao cs = new CustomerDao();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!cs.passCheck(oldPass) == true) {
			e_oldPass += "Password not match";
			request.setAttribute("e_oldPass", e_oldPass);
			url = "/CustomerDirec/changePassword.jsp";

			System.out.println("in old pass check ");
		} else {
			if (newPass.equals(confirm)) {
				if (!oldPass.equals(temp)) {
					System.out.println("in new and old passcheck  ");
					String userName = (String) session.getAttribute("userName");
					System.out.println("username on changePass ( svl ) " + userName);
					System.out.println("new pass");
					System.out.println(temp);
					System.out.println("old pass");
					System.out.println(oldPass);
					cs.updatePassword(userName, temp);
					session.invalidate();
					url = "/CustomerDirec/sellingPage.jsp";
				} else {
					e_oldPass += "New password at the same old password";
					request.setAttribute("e_oldPass", e_oldPass);
					url = "/CustomerDirec/changePassword.jsp";
				}
			} else {
				e_confirm += "Password not match";
				request.setAttribute("e_confirm", e_confirm);
				url = "/CustomerDirec/changePassword.jsp";
			}
		}
		RequestDispatcher rs = getServletContext().getRequestDispatcher(url);
		rs.forward(request, response);

	}

	private void Register(HttpServletRequest request, HttpServletResponse response)
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
			url = "/CustomerDirec/register.jsp";
		} else if (passwordMatch == false) {
			e_Repassword = "Password not match ! ";
			request.setAttribute("e_Repassword", e_Repassword);
			url = "/CustomerDirec/register.jsp";
		} else if (emailCheck != true) {
			request.setAttribute("e_email", "Email not exist or not true");
			url = "/CustomerDirec/register.jsp";
		} else {
			url = "/index2.jsp";
			boolean temp = false;
			if (emailRegister.equals("on")) {
				temp = true;
			}

			passWord = new Encryption().toSHA1(passWord);
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

}
