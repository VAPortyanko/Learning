package by.pva.servletapi.responses;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

@SuppressWarnings("serial")
public class ServletParameterFromServletContext extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("Company email: " + getServletContext().getInitParameter("company_email"));
		out.println("<br>");
		out.println("<a href=\"index.html\">Beer Advisor</a>");
		
	}
	
}