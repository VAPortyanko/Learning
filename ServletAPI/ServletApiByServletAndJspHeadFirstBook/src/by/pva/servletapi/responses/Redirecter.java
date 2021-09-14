package by.pva.servletapi.responses;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

@SuppressWarnings("serial")
public class Redirecter extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("Web-content/Responses/Redirect/SimpleRedirectPage.html");
		
	}
	
}