 package by.pva.test;

public class Test_0001 {

	public static void main(String[] args) {
		Runnable r1 =  new Test_0001.SimpleThread();
		Thread t1 = new Thread(r1); 
		Thread t2 = new Thread(r1);
		Thread t3 = new Thread(r1);
		t1.start();
		t2.start();
		t3.start();
	}
	
	static class SimpleThread implements Runnable{
		
		private static String ordinarVariable = "";
		private static ThreadLocal<String> threadLocalVariable = new ThreadLocal<String>() ;
		
		@Override
		public void run() {
			ordinarVariable = Thread.currentThread().getName();
			threadLocalVariable.set(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (this) {
				System.out.println(Thread.currentThread().getName());
				System.out.println("ordinarVariable: " + ordinarVariable);
				System.out.println("threadLocalVariable: " + threadLocalVariable.get());
				System.out.println();	
			}
		}
	}
}
