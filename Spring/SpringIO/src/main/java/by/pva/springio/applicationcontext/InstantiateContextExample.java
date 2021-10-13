package by.pva.springio.applicationcontext;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class InstantiateContextExample {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("InstantiateContextExample.xml");
		
		Bean bean = (Bean) context.getBean("bean");
		System.out.println(bean);
		
		((ClassPathXmlApplicationContext) context).close();
		
		// OR
		
		GenericApplicationContext context2 = new GenericApplicationContext();
		new XmlBeanDefinitionReader(context2).loadBeanDefinitions("InstantiateContextExample.xml");
		context2.refresh();
		
		Bean bean2 = (Bean) context2.getBean("bean");
		System.out.println(bean2);
		
		context2.close();
		
		// OR ...
		
	}
	
}
