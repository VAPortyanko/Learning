package by.pva.springio.annotationbasedconfiguration.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowiredExample {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("AutowiredExample.xml");
		MainBean bean = (MainBean) context.getBean("mainBean");
		System.out.println(bean.getDependency1().getName());
		
		((ClassPathXmlApplicationContext) context).close();
	}
	
}
