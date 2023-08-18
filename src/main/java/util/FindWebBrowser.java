package util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user-agent")
public class FindWebBrowser implements Runnable {
	
public void run(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String userAgent = request.getHeader("User-Agent");

		// Now you can process the userAgent string as needed
		// For example, you can log it or send it as a response to the client.
		response.setContentType("text/plain");
		response.getWriter().write("User-Agent: " + userAgent);
	
	}

@Override
public void run() {
	// TODO Auto-generated method stub
	
}
}
