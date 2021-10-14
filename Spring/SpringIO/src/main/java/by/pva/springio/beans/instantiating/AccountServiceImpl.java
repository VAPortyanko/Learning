package by.pva.springio.beans.instantiating;

public class AccountServiceImpl implements AccountService {

	@Override
	public String doSomething() {
		return "The \"accountServiceImpl\" bean was instantiated by using an instance factory method!";
	}

}
