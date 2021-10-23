package by.pva.springio.beans.lifecycle.callbacks;

import java.io.Closeable;

public class SingletonBeanImpClosable implements Closeable{

	public SingletonBeanImpClosable() {
		System.out.println("The SingletonBeanImpClosable constructor");
	}

	@SuppressWarnings("unused")
	private void init() {
		System.out.println("The SingletonBeanImpClosable init method");
	}

	@SuppressWarnings("unused")
	private void shutdown() {
		System.out.println("The SingletonBeanImpClosable shotdown method");
	}

	public void close() {                                                        // This method isn't defined as destroyMethod, but invokes! (class implements the Clossable interface)
		System.out.println("The SingletonBeanImpClosable close method");   
	}
}
