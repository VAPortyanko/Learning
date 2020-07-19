package wait_notify_mechanism;

import java.lang.Thread.State;

public class NotifyAllMethod {
	
	Object lock = new Object();
	
	public static void main(String[] args) throws InterruptedException {
		NotifyAllMethod st = new NotifyAllMethod();
		
		Thread t1 = new Thread(st.new SleepingThread());
		t1.start();
		Thread t2 = new Thread(st.new SleepingThread());
		t2.start();
		Thread t3 = new Thread(st.new SleepingThread());
		t3.start();
		
		st.stopThreads(t1, t2, t3, st);
	}

	
	/* Wait for all threads to finish run */
	private void stopThreads(Thread t1, Thread t2, Thread t3, NotifyAllMethod st) throws InterruptedException{
		while(!(t1.getState().equals(State.WAITING) && t2.getState().equals(State.WAITING) && t3.getState().equals(State.WAITING))){
			Thread.sleep(3000);
		}
		/* Wake up all threads */
		synchronized (st.lock) {
			st.lock.notifyAll();
			/* or Wake up one random thread */
			// st.lock.notify();
		}
	}
	
	private class SleepingThread implements Runnable{

		@Override
		public void run() {
			synchronized (lock) {
				try {
					System.out.println("The thread " + Thread.currentThread().getName() +" is waiting!");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("The thread " + Thread.currentThread().getName() +" is complited!");
			}				
		}
	}
}

