package test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class Atest {

	public static void main(String[] args) throws Exception {

		Runnable run1 = () -> System.out.println("Callable");
		
		Callable<Object> executor = Executors.callable(run1);
		System.out.println(executor.call());

	}
}

