package by.pva.springio.beans.lifecycle.beanpostprocessor;

import javax.annotation.PostConstruct;

public class CardPayment implements IPayment {

	public void executePayment() {
		System.out.println("Perform Card Payment ");
	}

	@PostConstruct
	public void annoInitMethod() {
		System.out.println("Calling InitMethod for CardPayment");
	}

	@Override
	public void processInstance(IPayment bean) {
		System.out.println("processInstance method called with bean " + bean);
	}
}
