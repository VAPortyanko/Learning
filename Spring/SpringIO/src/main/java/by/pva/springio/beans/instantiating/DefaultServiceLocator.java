package by.pva.springio.beans.instantiating;

public class DefaultServiceLocator {

	private static PaymentService paymentService = new PaymentServiceImpl();
	private static AccountService accountService = new AccountServiceImpl();

	public PaymentService createPaymentServiceInstance() {
		return paymentService;
	}

	public AccountService createAccountServiceInstance() {
		return accountService;
	}
}
