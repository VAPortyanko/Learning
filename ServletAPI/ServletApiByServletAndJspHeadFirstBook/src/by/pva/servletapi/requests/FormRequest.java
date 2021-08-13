package by.pva.servletapi.requests;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

@SuppressWarnings("serial")
public class FormRequest extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String c = request.getParameter("color");
		
		PrintWriter out = response.getWriter();
		out.println("Beer Selection Advice<br>");
		out.println("<br>Got beer color " + c);
		out.println("<br>");
		out.println("<a href=\"index.html\">Beer Advisor</a>");

	}
}