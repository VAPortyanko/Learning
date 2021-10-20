package by.pva.springio.beans.lifecycle.callbacks;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanCallbacksExample {
	
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("BeanCallbacksExample.xml");
		
		System.out.println("Receive the first silgetonBean:");
		context.getBean("singletonBean");
		System.out.println("Receive the second silgetonBean:");
		context.getBean("singletonBean");
		
		
		System.out.println("\nReceive the first prototypeBean:");
		context.getBean("prototypeBean");
		System.out.println("Receive the second prototypeBean:");
		context.getBean("prototypeBean");
		
		((ClassPathXmlApplicationContext) context).close();
	}
}
