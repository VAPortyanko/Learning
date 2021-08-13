package com.example.web.testscl;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent event) {
		
		ServletContext sc = event.getServletContext();
		
		String dogBreed = sc.getInitParameter("breed");
		
		Dog dog = new Dog(dogBreed);
		
		sc.setAttribute("dog", dog);
		
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		// nothing to do here.
	}

}
