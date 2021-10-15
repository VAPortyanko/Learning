package by.pva.springio.beans.dependencies.innerbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InnerBeanExample {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("InnerBeanExample.xml");

		OuterBean outerBean = (OuterBean) context.getBean("outerBean");
		System.out.println(outerBean.getInnerBean().getName());		
		
		((ClassPathXmlApplicationContext) context).close();
		
	}
}
