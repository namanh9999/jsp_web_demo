package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.CustomerDao;
import dao.VerifyDao;
import model.Customer;
import model.Verify;
import util.EmailProcess;
import util.Encryption;
import util.findOutOS;

/**
 * Servlet implementation class CustomerController
 */
@MultipartConfig
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
	@Override
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String action = request.getParameter("action");
		System.out.println("This is action : " + action);
		if (action.equals("changePassInfor")) {
			ChangePassword(request, response);
		} else if (action.equals("changeInfor")) {
			ChangeInfor(request, response);
		} else if (action.equals("register")) {
			Register(request, response);
		} else if (action.equals("login")) {
			Runnable find_os = new findOutOS();
			find_os.run();
			LogIn(request, response);
		} else if (action.equals("saveInfor") || action.equals("changePass")) {
			checkVerifyCode(request, response, action);
		}
	}

	private void LogOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String url = "";
		HttpSession session = request.getSession();
		session.invalidate();
		url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/login.jsp";
		System.out.println(url);
		response.sendRedirect(url);

	}

	private void LogIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";

		HttpSession session = request.getSession();
		String e_userName = "";
		String userName = request.getParameter("userName");
		System.out.println(userName);
		session.setAttribute("userName", userName);
		String password = request.getParameter("password");
		password = new Encryption().toSHA1(password);
		CustomerDao cs = new CustomerDao();
		if (cs.userNameCheck(userName, password)) {
			session.setAttribute("userID", cs.selectByUserName(userName).getCustomerID());
			session.setAttribute("avatarPath", CustomerDao.getInstance().selectByUserName(userName).getAvatarPath());
			url = "/index.jsp";
		} else {
			url = "/login.jsp";
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
			String gender = request.getParameter("gender");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String deliAddress = request.getParameter("deliAddress");
			String shipAddress = request.getParameter("shipAddress");
			String buyAddress = request.getParameter("buyAddress");
			String birth = String.valueOf(request.getParameter("birth"));
			String emailRegi = request.getParameter("emailRegister");
			Part part = request.getPart("avatar");
//			String avatarPath = request.getParameter("avatar");
			boolean emailRegister = false;
			if (emailRegi.equals("on")) {
				emailRegister = true;
			}
			Customer cs = new Customer(userID, userName, password, fullName, gender, address, deliAddress, shipAddress,
					buyAddress, Date.valueOf(birth), phoneNumber, email, emailRegister);
			CustomerDao.getInstance().update(cs);
			uploadPicture(cs, part, request, userID);
			url = "/CustomerDirec/changeInfor.jsp";
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private boolean uploadPicture(Customer cm, Part part, HttpServletRequest request, String userID)
			throws IOException {
		boolean result = false;
		String folder = getServletContext().getRealPath("avatar");
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		int maxFileSize = 5 * 1024;
		int maxMemSize = 5 * 1024;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println("This is multiple part check" + isMultipart);
		String filePath = folder + "/" + fileName;
		File file = new File(filePath);
		if (isMultipart && isImage(file)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxMemSize);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(maxFileSize);
			part.write(filePath);
			CustomerDao.getInstance().avatarUpdate(cm, userID, fileName);
			result = true;
		}
		return result;
	}

	private void Register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String verifyCode = createVerifyCode();

		String url = "";
		String userName = request.getParameter("userName").trim();
		String passWord = request.getParameter("password").trim();
		String rePassWord = request.getParameter("rePassword").trim();
		String fullName = request.getParameter("fullName").trim();
		String gender = request.getParameter("gender");
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
		String e_gender = "";

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

		// boolean uNCheck= this.useNameCheck(this.getConnection(), userName);

		CustomerDao cs = new CustomerDao();

		if (cs.userNameCheck(userName)) {
			e_userName = "User name already exist, Please choose new user name <_> ";
			request.setAttribute("e_userName", e_userName);
			url = "/CustomerDirec/register.jsp";
		} else if (!passwordMatch) {
			e_Repassword = "Password not match ! ";
			request.setAttribute("e_Repassword", e_Repassword);
			url = "/CustomerDirec/register.jsp";
		} else if (!emailCheck) {
			request.setAttribute("e_email", "Email not exist or not true");
			url = "/CustomerDirec/register.jsp";
		} else if (gender == null) {
			e_gender = "Please select your gender";
			request.setAttribute("e_gender", e_gender);
			url = "/CustomerDirec/register.jsp";
		} else {
			boolean temp = false;
			if (emailRegister == null) {
				temp = false;
			} else {
				temp = true;
			}
			passWord = new Encryption().toSHA1(passWord);
			Date date = Date.valueOf(birth);
			Random rd = new Random();
			String userID = userName + rd.nextInt(1000, 9999);
			Customer cm = new Customer(userID, userName, passWord, fullName, gender, address, deliAddress, shipAddress,
					buyAddress, date, phoneNumber, email, temp);

			session.setAttribute("customerID", userID);
			session.setAttribute("CustomerInfor", cm);
			session.setAttribute("verifyCode", verifyCode);
			session.setAttribute("action", "saveInfor");
			EmailProcess emp = new EmailProcess();
			String content = createContent("register account", verifyCode);
			String subject = "Bug Company verify Code";
			emp.sendEmail(email, content, subject);
			url = "/CustomerDirec/verifyCode.jsp";
			System.out.println(url);
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private void ChangePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "";
		String confirmPass;
		String oldPass = request.getParameter("oldPass");
		oldPass = new Encryption().toSHA1(oldPass);
		String newPass = request.getParameter("newPass");
		String temp = request.getParameter("confirm");
		confirmPass = new Encryption().toSHA1(temp);
		String e_oldPass = "";
		String e_confirm = "";
		CustomerDao cs = new CustomerDao();
		if (!cs.passCheck(oldPass)) {
			System.out.println("This is oldpass check ! true");
			e_oldPass += "Password not match";
			request.setAttribute("e_oldPass", e_oldPass);
			url = "/CustomerDirec/changePassword.jsp";
		}
		if (!newPass.equals(temp)) {
			System.out.println("This is oldpass check ");
			e_confirm += "Password not match";
			request.setAttribute("e_confirm", e_confirm);
			url = "/CustomerDirec/changePassword.jsp";
		} else {
			System.out.println("This is newpass = confirm ");
			if (!oldPass.equals(temp)) {
				String userName = (String) session.getAttribute("userName");
				EmailProcess ep = new EmailProcess();
				String email = cs.selectByUserName(userName).getEmail();
				String verifyCode = createVerifyCode();
				session.setAttribute("userName", userName);
				session.setAttribute("confirmPass", confirmPass);
				session.setAttribute("verifyCode", verifyCode);
				session.setAttribute("action", "changePass");
				String contents = createContent("change password", verifyCode);
				ep.sendEmail(email, contents, "Verify code for change for password");
				System.out.println("there is Change Pass function");
				url = "/CustomerDirec/verifyCode.jsp";
			} else {
				e_oldPass += "New password at the same old password";
				request.setAttribute("e_oldPass", e_oldPass);
				url = "/CustomerDirec/changePassword.jsp";
			}
		}

		RequestDispatcher rs = getServletContext().getRequestDispatcher(url);
		rs.forward(request, response);

	}

	private void checkVerifyCode(HttpServletRequest request, HttpServletResponse response, String action)
			throws ServletException, IOException {
		String url = "";
		String e_verifyCode = "";
		HttpSession session = request.getSession();
		String codeCheck = String.valueOf(session.getAttribute("verifyCode"));
		System.out.println("Code check" + codeCheck);
		String code = request.getParameter("verifyCode");
		System.out.println("Code" + code);
		if (codeCheck.equals(code)) {
			CustomerDao cmd = new CustomerDao();
			Date lastTime = createDate();
			VerifyDao vd = new VerifyDao();
			if (action.equals("saveInfor")) {
				Customer cm = (Customer) session.getAttribute("CustomerInfor");
				System.out.println(cm.toString());
				cmd.insert(cm);
				Verify verify = new Verify(cm, lastTime, lastTime, code, true, "Register an account");
				vd.insert(verify);
				url = "/login.jsp";
				session.invalidate();
			} else if (action.equals("changePass")) {
				Customer cm = cmd.selectByID(session.getAttribute("userID") + "");
				System.out.println("There is check Verify Code function");
				System.out.println("Code" + code);
				Date recently = vd.getLastDate(String.valueOf(session.getAttribute("userID")));
				Verify verify = new Verify(cm, recently, lastTime, code, true, "Change password");
				vd.update(verify);
				String userName = session.getAttribute("userName") + "";
				String confirmPass = session.getAttribute("confirmPass") + "";
				cmd.updatePassword(userName, confirmPass);
				url = "/login.jsp";
				session.invalidate();
			}
		} else {
			e_verifyCode = "verify code not match. Re-enter or send again";
			request.setAttribute("e_verifyCode", e_verifyCode);
			url = "/CustomerDirec/vetifyCode.jsp";
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private String createVerifyCode() {
		Random rd = new Random();
		String verifyCode = "";
		for (int i = 1; i < 7; i++) {
			verifyCode += String.valueOf(rd.nextInt(0, 9));
		}
		return verifyCode;
	}

	private Date createDate() {
		Date date;
		LocalDate now = LocalDate.now();
		date = Date.valueOf(now);
		return date;
	}

	private String createContent(String content, String verifyCode) {
		String result = "<html>\n" + "<title>Bug Company</title>\n" + "<body>\n" + "    <div align=\"center\" >\n"
				+ "<h1>Bug Company</h1>\n" + "<div>This is verify code for" + content + "<br> <strong>" + verifyCode
				+ "</strong> </div>\n" + "</div>\n" + "</body>\n" + "</html>";
		return result;
	}

	private boolean isImage(File file) {
		boolean result = false;
		String mimeType = new MimetypesFileTypeMap().getContentType(file);
		if (mimeType.startsWith("image/")) {
			result = true;
		}
		return result;
	}
}