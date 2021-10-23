package by.pva.springio.beans.lifecycle.beanpostprocessor;

import javax.annotation.PostConstruct;

public class CashPayment implements IPayment {

	public void executePayment() {
		System.out.println("Perform Cash Payment ");
	}

	@PostConstruct
	public void annoInitMethod() {
		System.out.println("Calling InitMethod for CashPayment");
	}

	@Override
	public void processInstance(IPayment bean) {
		System.out.println("processInstance method called with bean " + bean);
	}
}
