package controller;

import java.io.IOException;

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
		String userID = session.getAttribute("userName") + "";
		System.out.println("this is user ID in changeInfor Control");
		String url = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!session.equals(null)) {
			Customer cs = new CustomerDao().selectByUserName(userID);
			String userName = cs.getUserName();
			request.setAttribute("userName",userName);
			String emailAddress = cs.getEmail();
			request.setAttribute("email",emailAddress);
			String fullName = cs.getFullName();
			request.setAttribute("fullName",fullName);
			String gender = cs.getGender();
			request.setAttribute("Gender",gender);
			String phoneNumber = cs.getPhoneNumber();
			request.setAttribute("phoneNumber",phoneNumber );
			String address = cs.getAddress();
			request.setAttribute("address",address);
			String deliAddress = cs.getDeliAddress();
			request.setAttribute("deliAddress",deliAddress);
			String shipAddress = cs.getShipAddress();
			request.setAttribute("shipAddress",shipAddress );
			String birth = String.valueOf(cs.getBirth());
			request.setAttribute("birth", birth);
			boolean emailRegister = cs.isEmailRegister();
			request.setAttribute("emailRegister",emailRegister);
			url = "/changeInfor.jsp";
		} else {
			url = "/index2.jsp";
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
