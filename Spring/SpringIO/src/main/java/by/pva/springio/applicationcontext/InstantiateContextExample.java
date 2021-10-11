package by.pva.springio.applicationcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InstantiateContextExample {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("InstantiateContextExample.xml");
		TestBean bean = (TestBean) context.getBean("bean");
		System.out.println(bean);
		((ClassPathXmlApplicationContext) context).close();
		
	}
	
}
