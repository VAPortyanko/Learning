package com.example.web;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

@SuppressWarnings("serial")
public class ServletParameterFromServletConfig extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("email :" + getServletConfig().getInitParameter("email"));
		out.println("<br>");
		out.println("phone :" + getServletConfig().getInitParameter("phone"));
		out.println("<br>");
		out.println("<a href=\"index.html\">Beer Advisor</a>");
		
	}
	
}