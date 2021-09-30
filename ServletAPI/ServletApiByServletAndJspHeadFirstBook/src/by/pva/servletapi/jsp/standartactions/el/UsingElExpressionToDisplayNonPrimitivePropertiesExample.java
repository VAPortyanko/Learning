package by.pva.servletapi.jsp.standartactions.el;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UsingElExpressionToDisplayNonPrimitivePropertiesExample extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Dog dog = new Dog();
		dog.setName("Chappy");
		dog.setBreed("Bulldog");
		
		DogOwner dogOwner = new DogOwner();
		dogOwner.setName("David");
		dogOwner.setDog(dog);
		
		request.setAttribute("dogOwner", dogOwner);
		
		RequestDispatcher view = request.getRequestDispatcher("Web-content/Jsp/Standart actions/EL/UsingElExpressionToDisplayNonPrimitiveProperties.jsp");
		view.forward(request, response);
		
	}
	
}