package com.example.web.testscl;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ListenerTester extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("Test context attributes set by listener<br>");
		out.println("<br>");
		
		Dog dog = (Dog) getServletContext().getAttribute("dog");
		
		out.println("Dog’s breed is: " + dog.getBreed());
	}

}
