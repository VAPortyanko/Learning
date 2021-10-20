package by.pva.springio.beans.lifecycle.callbacks;

public class PrototypeBean {

	public PrototypeBean() {
		System.out.println("The PrototypeBean constructor");
	}

	@SuppressWarnings("unused")
	private void init() {
		System.out.println("The PrototypeBean init method");
	}

	@SuppressWarnings("unused")
	private void shutdown() {
		System.out.println("The PrototypeBean shotdown method");
	}

	public void close() {
		System.out.println("The PrototypeBean close method");
	}
}
