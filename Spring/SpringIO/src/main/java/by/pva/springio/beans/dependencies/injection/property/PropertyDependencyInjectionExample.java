package by.pva.springio.beans.dependencies.injection.property;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyDependencyInjectionExample {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("PropertyDependencyInjectionExample.xml");
		
		MainBean mainBean = (MainBean) context.getBean("mainBean");
		
		System.out.println(mainBean.getDependency1());
		System.out.println(mainBean.getDependency2());
		
		mainBean.getProperties().forEach((key, value) -> System.out.println(key + " =  " + value));
		
		((ClassPathXmlApplicationContext) context).close();
	}	
	
}
