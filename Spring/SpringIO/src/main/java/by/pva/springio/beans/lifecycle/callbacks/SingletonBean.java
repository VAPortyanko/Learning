package by.pva.springio.beans.lifecycle.callbacks;

public class SingletonBean {

	public SingletonBean() {
		System.out.println("The SingletonBean constructor");
	}

	@SuppressWarnings("unused")
	private void init() {
		System.out.println("The SingletonBean init method");
	}

	@SuppressWarnings("unused")
	private void shutdown() {
		System.out.println("The SingletonBean shotdown method");
	}

	public void close() {
		System.out.println("The SingletonBean close method");
	}
}
