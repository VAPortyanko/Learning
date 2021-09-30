package by.pva.servletapi.session.urlrewriting;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ServletSessionUrlRewritingExample extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");

		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		out.println("Session status: ");
		out.println(session.isNew()?"new":"old");
		
		out.println("<br>");
		out.println("<br>");
		out.println("<a href=\"sessionNextPage\">Go to next page</a>");
		
		out.println("<br>");
		out.println("<a href=\"" + response.encodeURL("sessionNextPageUrlEncoded") + "\">Go to next page (URL encoded)</a>");
		
		out.println("<br>");
		out.println("<br>");
		out.println("<a href=\"" + request.getContextPath() + "\">Home page</a>");
		
	}

}