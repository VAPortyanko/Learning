package by.pva.springio.beans.naming;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanNamingExample {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("BeanNamingExample.xml");
		
		Bean bean  = (Bean) context.getBean("bean");
		Bean bean1 = (Bean) context.getBean("beanAlias1");
		Bean bean2 = (Bean) context.getBean("beanAlias2");
		
		System.out.println(bean);
		System.out.println(bean1);
		System.out.println(bean2);
		
		System.out.println((bean == bean1) && (bean == bean2));
		
		((ClassPathXmlApplicationContext) context).close();
		
	}
}

/*
	In XML-based configuration metadata, you use the "id" attribute, the "name" attribute,
	or both to specify the bean identifiers. The id attribute lets you specify exactly one id.
*/