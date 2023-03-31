package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.Encryption;
import dao.CustomerDao;

/**
 * Servlet implementation class changePass
 */
public class changePass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public changePass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "";
		String oldPass = request.getParameter("oldPass");
		oldPass = new Encryption().toSHA1(oldPass);
		String newPass = request.getParameter("newPass");
		String confirm = request.getParameter("confirm");

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
			url="/changePassword.jsp";
		} else {
			if (newPass.equals(confirm)) {
				HttpSession session = request.getSession();
				String userName = (String) session.getAttribute("userName");
				System.out.println("username on changePass ( svl ) "+userName);
				String temp = new Encryption().toSHA1(confirm);
				System.out.println(temp);
				cs.updatePassword(userName, temp);
				session.invalidate();
				url="/index2.jsp";
			} else {
				e_confirm += "Password not match";
				request.setAttribute("e_confirm", e_confirm);
				url="/changePassword.jsp";
			}
		}
		RequestDispatcher rs = getServletContext().getRequestDispatcher(url);
		rs.forward(request, response);
	}
}