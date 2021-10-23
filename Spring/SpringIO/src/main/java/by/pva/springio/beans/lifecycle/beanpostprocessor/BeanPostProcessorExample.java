package by.pva.springio.beans.lifecycle.beanpostprocessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanPostProcessorExample {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("BeanPostProcessorExample.xml");

		IPayment cashBean = (IPayment) context.getBean("cashPaymentBean");
		IPayment cardBean = (IPayment) context.getBean("cardPaymentBean");

		((ClassPathXmlApplicationContext) context).close();

	}

}

// https://www.netjstech.com/2018/03/beanpostprocessor-in-spring-framework.html