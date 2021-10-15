package by.pva.springio.beans.dependencies.collectionsandproperties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CollectionAndPropertiesInjectionExample {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("CollectionAndPropertiesInjectionExample.xml");
		
		MainBean mainBean = (MainBean) context.getBean("mainBean");
		// Properties
		mainBean.getAdminEmails().forEach((key, value)->System.out.println(key+": " + value));
		mainBean.getCapitals().forEach((key, value)->System.out.println(key+": " + value));
		
		// List
		mainBean.getSomeList().forEach(System.out::println);
		
		// Set
		mainBean.getSomeSet().forEach(System.out::println);
		
		// Map
		mainBean.getSomeMap().forEach((key, value)->System.out.println(key+": " + value));
		
		((ClassPathXmlApplicationContext) context).close();
	}	
	
}
