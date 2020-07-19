package priority;

public class Priority {
	
	public static void main(String[] args) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		Clicker hi = new Clicker(Thread.NORM_PRIORITY + 3);
		Clicker lo = new Clicker(Thread.NORM_PRIORITY - 3);
		
		lo.start();
		hi.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Main thread is interrupted!");
		}
		
		lo.stop();
		hi.stop();
		
		try {
			hi.t.join();
			lo.t.join();
		} catch (InterruptedException e) {
			System.out.println("Main thread is interrupted!");
		}
		System.out.println("Low-priority thread  - " + lo.click);
		System.out.println("High-priority thread - " + hi.click);
	}
	
	static class Clicker implements Runnable{

		long click = 0;
		Thread t;
		private volatile boolean running = true;  // This variable can be changed by thread itself or by parent thread (It initialize in constructor and change in stop method). The variable is read in the thread in the loop.
		                                          // http://www.sql.ru/forum/1270996/volatile, http://tutorials.jenkov.com/java-concurrency/volatile.html, ...
		public Clicker(int p){
			t = new Thread(this);
			t.setPriority(p);
		}
		
		@Override
		public void run() {
			while(running){
				click++;
			}
		}
		
		public void start(){
			t.start();
		}
		
		public void stop(){
			running = false;
		}
	}
}
