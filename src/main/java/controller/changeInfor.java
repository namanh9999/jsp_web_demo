package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import model.Customer;

/**
 * Servlet implementation class changeInfor
 */
public class changeInfor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public changeInfor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
			String userID =  CustomerDao.getInstance().selectByUserName(userName).getCustomerID();
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
			Customer cs = new Customer(userID, userName, password, fullName, gender, address, deliAddress, shipAddress, buyAddress, Date.valueOf(birth), phoneNumber, email, emailRegister);
			CustomerDao.getInstance().update(cs);

			url = "/changeInfor.jsp";
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
