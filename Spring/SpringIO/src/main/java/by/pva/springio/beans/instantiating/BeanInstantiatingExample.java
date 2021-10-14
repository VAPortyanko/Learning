package by.pva.springio.beans.instantiating;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiatingExample {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("BeanInstantiatingExample.xml");
		
		Bean bean = (Bean) context.getBean("bean");
		System.out.println(bean + "\n");
		
		ClientService clientService = (ClientService) context.getBean("clientService");
		System.out.println(clientService + "\n");
		
		PaymentService paymentService = (PaymentService) context.getBean("paymentService");
		AccountService accountService = (AccountService) context.getBean("accountService");
		System.out.println(paymentService.doSomething());
		System.out.println(accountService.doSomething());
		
		((ClassPathXmlApplicationContext) context).close();
	}
	
}
