package lockInterface.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockAndFairness {
	public static void main(String[] args) {
		ReentrantLock reLock = new ReentrantLock();
		// ReentrantLock reLock = new ReentrantLock(true); - fairness - ensures that threads will receive locks in the order in which they requested it,
		// but does not guarantee that by the moment the lock is released by one thread, the other will request a lock (which will allow the first thread to capture the lock again).
		Thread t1 = new Thread(new Task("Thread 1", "This is first thread!", reLock));
		Thread t2 = new Thread(new Task("Thread 2", "This is second thread!", reLock));
		Thread t3 = new Thread(new Task("Thread 3", "This is third thread!", reLock));
		
		t1.start();
		t2.start();
		t3.start();
	}
}

class Task implements Runnable{
	
	String threadName, msg;
	ReentrantLock lock;
	
	Task(String name, String msg, ReentrantLock lock){
		this.threadName = name;
		this.msg = msg;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10;i++){
			lock.lock();

			try {
				System.out.println("Thread \"" + threadName + "\": " + msg + " (step " + (i+1) + ")");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}	
	}
}