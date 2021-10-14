package by.pva.springio.beans.instantiating;

public class PaymentServiceImpl implements PaymentService {

	@Override
	public String doSomething() {
		return "The \"paymentServiceImpl\" bean was instantiated by using an instance factory method!";
	}

}
