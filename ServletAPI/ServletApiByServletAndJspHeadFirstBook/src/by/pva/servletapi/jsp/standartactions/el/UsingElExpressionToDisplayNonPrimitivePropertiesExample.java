package by.pva.servletapi.jsp.standartactions.el;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		Dog dogMother = new Dog();
		dogMother.setName("Luna");
		dogMother.setBreed("Bulldog");

		Dog dogFather = new Dog();
		dogFather.setName("Oscar");
		dogFather.setBreed("Bulldog");
		
		Map<String, Dog> parents = new HashMap<>();
		parents.put("mother", dogMother);
		parents.put("father", dogFather);
		
		dog.setParents(parents);
		
		DogOwner dogOwner = new DogOwner();
		dogOwner.setName("David");
		dogOwner.setDog(dog);
		
		Dog[] dogArray = {dog, dogMother, dogFather};
		List<Dog> dogList = Arrays.asList(dog, dogMother, dogFather);
		
		request.setAttribute("dogOwner", dogOwner);
		request.setAttribute("dogArray", dogArray);
		request.setAttribute("dogList", dogList);
		
		RequestDispatcher view = request.getRequestDispatcher("Web-content/Jsp/Standart actions/EL/UsingElExpressionToDisplayNonPrimitiveProperties.jsp");
		view.forward(request, response);
		
	}
	
}