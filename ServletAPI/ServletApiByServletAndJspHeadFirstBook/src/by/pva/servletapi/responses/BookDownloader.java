package by.pva.servletapi.responses;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

@SuppressWarnings("serial")
public class BookDownloader extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=Servlets_and_JSP.pdf");
		ServletContext ctx = getServletContext();
		
		InputStream is = ctx.getResourceAsStream("WEB-INF/files/Servlets_and_JSP.pdf");
		
		int read = 0;
		byte[] bytes = new byte[1024];
		
		OutputStream os = response.getOutputStream();
		while ((read = is.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
		
		os.flush();
		os.close();
		
	}
	
}