package by.pva.springio.configuration.annotationbasedconfiguration.autowired.optionalautowiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArrayAndListAutowiredExample {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("OptionalAutowiringExample.xml");
		MainBean bean = (MainBean) context.getBean("mainBean");
		
		System.out.println(bean.getDependency1());
		System.out.println(bean.getDependency2());
		System.out.println(bean.getDependency3());
		
		((ClassPathXmlApplicationContext) context).close();
	}
	
}
