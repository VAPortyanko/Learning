package by.pva.springio.beans.dependencies.injection.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConstructorDependencyInjectionExample {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("ConstructorDependencyInjectionExample.xml");
		
		MainBean mainBean = (MainBean) context.getBean("mainBean");
		
		System.out.println(mainBean.getDependency1());
		System.out.println(mainBean.getDependency2());
		
		Dependency1 dependency1 = (Dependency1) context.getBean("dependency1");
		
		System.out.println(dependency1);
		
		((ClassPathXmlApplicationContext) context).close();
	}	
	
}
