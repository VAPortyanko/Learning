package com.example.web.session;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class SessionNextPage extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");

		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		out.println("Session status: ");
		out.println(session.isNew()?"new":"old");
		out.println("<br>");
		out.println("<br>");
		out.println("<a href=\"testSessionURLRewriting\">The session page (test session that use URL Rewriting)</a>");
		
	}

}
