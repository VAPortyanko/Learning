package by.pva.springio.beans.instantiating.laziness;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLazinessExample {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("BeanLazinessExample.xml");
		
		System.out.println("The application context has been initialized!");
		
		// The singletonScopedBean object already exists (It has been created during the context initialization).
		SingletonScopedBean singletonScopedBean = (SingletonScopedBean) context.getBean("singletonScopedBean");

		// The prototypeScopedBean will be created for the first time. 
		PrototypeScopedBean prototypeScopedBean = (PrototypeScopedBean) context.getBean("prototypeScopedBean");
		
		// A singleton scoped bean is created during context initialization, 
		// and a prototype scoped bean is created on first use 
		// (injection to another bean or getting it from the context).
		
		// We can change laziness of a singleton scoped bean using the "lazy-init" attribute.
		
		((ClassPathXmlApplicationContext) context).close();
	}
}
