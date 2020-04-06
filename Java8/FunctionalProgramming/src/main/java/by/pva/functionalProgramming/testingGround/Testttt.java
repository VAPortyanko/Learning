package by.pva.functionalProgramming.testingGround;

import java.util.ArrayList;

public class Testttt {
	
	static ArrayList<Object> a = new ArrayList<>(1);

	public static void main(String[] args) throws Exception {
	    Thread t1 = new Thread() {
	        public void run() {
	            while (true) {

	                a.add(new Object());
	            }
	        }
	    };

	    Thread t2 = new Thread() {
	        public void run() {
	            while (true) {
	                a = new ArrayList<>(1);
	                a.add(new Object());
	                a.add(new Object());
	            }
	        }
	    };

	    t2.start();
	    System.out.println(a);
	    System.out.println(a.size());
	    Thread.sleep(1);
	    System.out.println(a);
	    System.out.println(a.size());
	    t1.start();
	}
	
}
