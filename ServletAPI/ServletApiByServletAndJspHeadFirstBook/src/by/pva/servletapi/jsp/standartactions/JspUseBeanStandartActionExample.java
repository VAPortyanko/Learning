package by.pva.servletapi.jsp.standartactions;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class JspUseBeanStandartActionExample extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Person person = new Person();
		person.setName("David");
		Dog dog = new Dog();
		dog.setName("Chappy");
		dog.setBreed("Bulldog");
		
		request.setAttribute("person", person);
		request.setAttribute("dog", dog);
		
		RequestDispatcher view = request.getRequestDispatcher("Web-content/Jsp/Standart actions/JSP_UseBean.jsp");
		view.forward(request, response);
		
	}
	
}