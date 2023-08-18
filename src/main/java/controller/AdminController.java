package controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import dao.AdminDao;
import dao.JDBCUtil;
import model.Admin;
import util.DateProcess;
import util.EmailProcess;
import util.Encryption;

/**
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet implements Runnable {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("logout")) {
			LogOut(request, response);
		} else if (action.equals("adminManager")) {
			showAllAccount(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String action = request.getParameter("action");
		System.out.println("this is action" + action);
		if (action.equals("logout")) {
			LogOut(request, response);
		} else if (action.equals("login")) {
			LogIn(request, response);
		} else if (action.equals("verifyCode")) {
			checkVerifyCode(request, response, "");
		} else if (action.equals("verifyCodeSU")) {
			checkVerifyCode(request, response, "superuser");
		} else if (action.equals("adminManager")) {
			showAllAccount(request, response);
		} else if (action.equals("AdminAccountRegister")) {
			createNewAdminAccount(request, response);
		}
	}

	private void LogOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String url = "";
		HttpSession session = request.getSession();
		session.invalidate();
		url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/Admin/login.jsp";
		System.out.println(url);
		response.sendRedirect(url);
	}

	private void LogIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		String e_error = "";
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		HttpSession session = request.getSession();
		// get permission from admin account
		String permission = request.getParameter("permission");
		System.out.println(permission);
		// dealing with superuser account
		if (permission.equals("superUser")) {
			actionSuperUser(request, response, e_error, userName, password, session, url);

		} else if (permission.equals("administrator")) {
			actionAdministrator(request, response, e_error, userName, password, session, url);
		}

	}

	private void checkVerifyCode(HttpServletRequest request, HttpServletResponse response, String privilege)
			throws ServletException, IOException {
		String url = "";
		String e_verifyCode = "";
		System.out.println(privilege);

		HttpSession session = request.getSession();
		String codeCheck = String.valueOf(session.getAttribute("verifyCode"));
		String code = request.getParameter("verifyCode").trim();
		if (code.equals(codeCheck)) {
			sendEmailForSuperUser(AdminDao.getInstance().selectSuperUser());
			e_verifyCode = "";
			request.setAttribute("e_verifyCode", e_verifyCode);
			if (privilege.equals("superuser")) {
				session.setAttribute("privilege", "superuser");
			}
			url = "/Admin/index.jsp";
		} else {

			sendEmailForSuperUser(AdminDao.getInstance().selectSuperUser());
			e_verifyCode = "";
			request.setAttribute("e_verifyCode", e_verifyCode);
			url = "/Admin/verifyCode.jsp";
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private Date createDate() {
		Date date;
		LocalDate now = LocalDate.now();
		date = Date.valueOf(now);
		return date;
	}

	private void sendEmailForSuperUser(int status, String adminName) {
		String user = AdminDao.getInstance().selectSuperUser();
		EmailProcess ep = new EmailProcess();
		String subject = "Login warning";
		ep.sendEmail(user, createContent(status, adminName), subject);

	}

	private void sendEmailForSuperUser(String adminName) {
		String user = AdminDao.getInstance().selectSuperUser();
		EmailProcess ep = new EmailProcess();
		String subject = "Login warning";
		ep.sendEmail(user, createContent(0, adminName), subject);
	}

	private void sendVerifyCode(String email, String code) {
		EmailProcess ep = new EmailProcess();
		ep.sendEmail(email, createContent(4, code), "Verify Code");
	}

	private String createContent(int status, String adminName) {
		String result = "";
		if (status == 0) {
			result = " <html> </body style=\" background-color : red;\"> <div align=\"center\"><h1> Login warning !! </h1></div><div align=\"center\" ><div> "
					+ adminName
					+ "  : Has just logged into your web </div><div>permission : Administrator </div><div> If you don't do it , you can do some thing to protect your web </div></div></html> ";
		} else if (status == 2) {
			result = " <html> </body style=\" background-color : red;\"> <div align=\"center\"><h1> Login warning !! </h1></div><div align=\"center\" ><div> "
					+ adminName
					+ "  : </div><div>permission : Administrator </div><div>They are trying to login to your website. If you don't do it , you can do some thing to protect your web </div></div></html> ";
		} else if (status == 4) {
			result = " <html> </body style=\" background-color : red;\"> <div align=\"center\"><h1> Verify Code </h1></div><div align=\"center\" ><div> "
					+ adminName
					+ "  : </div><div>permission : Administrator </div><div>They are trying to login to your website. If you don't do it , you can do some thing to protect your web </div></div></html> ";
		} else if (status == 1) {
			result = " <html> </body style=\" background-color : red;\"> <div align=\"center\"><h1> Verify Code </h1></div><div align=\"center\" ><div> "
					+ adminName
					+ "  : </div><div>permission : Super User </div><div>They are trying to login to your website. If you don't do it , you can do some thing to protect your web </div></div></html> ";

		}
		return result;
	}

	private String createVerifyCode() {
		Random rd = new Random();
		String verifyCode = "";
		for (int i = 1; i < 7; i++) {
			verifyCode += String.valueOf(rd.nextInt(0, 9));
		}
		return verifyCode;
	}

	private void showAllAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Admin> list = AdminDao.getInstance().selectAll();
		HttpSession session = request.getSession();
		session.setAttribute("adminList", list);
		String url = "/Admin/AdminManager/AdminManager.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private void actionSuperUser(HttpServletRequest request, HttpServletResponse response, String e_error,
			String userName, String password, HttpSession session, String url) throws ServletException, IOException {
		long start = System.currentTimeMillis();
		String email = "";
		int validCheck = AdminDao.getInstance().checkSuperUserAccount(userName, password);
		if (validCheck == 1) {
			email = AdminDao.getInstance().getSuperUserMail();
			System.out.println(email);
			sendEmailForSuperUser(0, "Super User");
			AdminDao.getInstance().updateLoginDateSuperUser(createDate());

// slower than above way(0.5 s)
//			 Runnable sendEmailThread = () -> sendEmailForSuperUser(0,"Super User");
//			 Runnable updateLastLogin = () -> AdminDao.getInstance().updateLoginDateSuperUser(createDate());
//			 sendEmailThread.run();
//			 updateLastLogin.run();

			request.setAttribute("e_error", e_error);
			String code = createVerifyCode();
			System.out.println("Code check is : " + code);
			session.setAttribute("verifyCode", code);
			sendVerifyCode(email, code);
			// to do
			System.out.println(System.currentTimeMillis() - start);
			url = "/Admin/verifyCode.jsp";
		} else {
			Runnable sendEmailThread = () -> sendEmailForSuperUser(1, "Super User");
			e_error = "Username or password not match ! Please try again ";
			request.setAttribute("e_error", e_error);
			sendEmailThread.run();
			url = "/Admin/login.jsp";
		}

		// handing administrator account
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private void actionAdministrator(HttpServletRequest request, HttpServletResponse response, String e_error,
			String userName, String password, HttpSession session, String url) throws ServletException, IOException {
		String privilege = "administrator";

		// i'm checking for administrator account , see more at AdminDao File
		int checkValid = AdminDao.getInstance().checkAdminAccount(userName, password);
		System.out.println(checkValid);
		if (checkValid == 1) {
			// account not exist
			e_error = "Username or password not match";
			url = "/Admin/login.jsp";
			request.setAttribute("e_error", e_error);
		} else if (checkValid == 0) {
			// login success
			Admin ad = AdminDao.getInstance().selectByUserName(userName);
			session.setAttribute("admin", ad);
			// Update admin account information
			AdminDao.getInstance().updateLoginDate(userName, AdminDao.getInstance().createDate());
			sendEmailForSuperUser(0, userName);
			// set verifyCode and send it to administrator
			String code = createVerifyCode();
			session.setAttribute("verifyCode", code);
			sendVerifyCode(ad.getEmailRegister(), code);
			url = "/Admin/verifyCode.jsp";
			request.setAttribute("e_error", e_error);

		} else if (checkValid == 2) {
			int failed = AdminDao.getInstance().selectByUserName(userName).getFailed();
			e_error = "You have " + (5 - failed) + " times left, before this account is locked";
			// send email for superuser to warning
			sendEmailForSuperUser(2, userName);
			request.setAttribute("e_error", e_error);
			url = "/Admin/login.jsp";
		} else if (checkValid == 3) {
			e_error = "This account is locked, please contact to superuser";
			request.setAttribute("e_error", e_error);
			url = "/Admin/login.jsp";
		}
		request.setAttribute("privilege", privilege);
		request.setAttribute("e_error", e_error);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	// to do
	private void createNewAdminAccount(HttpServletRequest request, HttpServletResponse response) {
		AdminDao adminDao = new AdminDao();
		String e_email = "";
		String e_username = "";
		String e_repassword = "";
		String e_phoneNumber = "";
		String id = String.valueOf(createID());
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String temp = request.getParameter("password");
		String password = Encryption.getInstance().toSHA1(temp);
		String rePassword = request.getParameter("rePassword");
		boolean passwordMatch = password.equals(rePassword);
		String address = request.getParameter("address");
		String birth = request.getParameter("birth");

		// check email format
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher emailMatcher = emailPattern.matcher(email);
		boolean emailCheck = emailMatcher.matches();
		String phoneNumber = request.getParameter("phoneNumber");
		if (phoneNumber.length() > 10 || phoneNumber.length() < 9) {
			e_phoneNumber = "Phone Number is not correct ! Plea try again ";
			request.setAttribute("e_phoneNumber", e_phoneNumber);
		} else if (!emailCheck) {
			e_email = "Incorrect email format";
			request.setAttribute("e_email", e_email);
		} else if (!passwordMatch) {
			e_repassword = "Password not match";
			request.setAttribute("e_repassword", phoneNumber);
		} else if (!adminDao.userNameCheck(userName) == true) {
			e_username = "Username already existing";
			request.setAttribute("", phoneNumber);
		} else {
			Admin newAccount = new Admin(id, userName, fullName, Date.valueOf(birth), address, password, email,
					phoneNumber, "superuser", DateProcess.getInstance().getCurrentDay(),
					DateProcess.getInstance().getCurrentDay(), 0, true);
			AdminDao.getInstance().insert(newAccount);

			System.out.println("success");
		}

	}

	private static int createID() {
		int lastID = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select adminID from Admin  order by adminID desc limit 1";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			int id = 0;
			while (result.next()) {
				id = result.getInt("adminID");
			}
			lastID = id + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastID;
	}

	@Override
	public void run() {

	}
}
