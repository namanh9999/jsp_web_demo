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
 * Servlet implementation class loginControl
 */
public class loginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "";

		String e_userName = "";
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println(password);
		password = new Encryption().toSHA1(password);
		String remember = request.getParameter("remember");
		CustomerDao cs = new CustomerDao();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cs.userNameCheck(userName, password) == true) {
			url = "/sellingPage.jsp";
				HttpSession session = request.getSession();
				request.setAttribute("session", session);
				System.out.println(session);
		} else {
			url = "/index2.jsp";
			e_userName = "User name or password not match";
			request.setAttribute("e_userName", e_userName);
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	
}
