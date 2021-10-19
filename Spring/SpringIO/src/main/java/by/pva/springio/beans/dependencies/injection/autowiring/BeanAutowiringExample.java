package by.pva.springio.beans.dependencies.injection.autowiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAutowiringExample {
	
	public static void main(String[] args) {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("BeanAutowiringExample.xml");
		
		MainBean mainBean = (MainBean) context.getBean("mainBean");
		System.out.println(mainBean.getName());
		System.out.println(mainBean.getBean1().getName());
		System.out.println(mainBean.getBean2().getName());
		System.out.println(mainBean.getBean1().getBean3().getName());
		System.out.println(mainBean.getBean2().getBean4().getName());
		
		((ClassPathXmlApplicationContext) context).close();
		
	}
	
}
