package lockInterface.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockMethod {
	public static void main(String[] args) {
		ReentrantLock reLock = new ReentrantLock();
 
		Thread t1 = new Thread(new Task2("Thread 1", "This is first thread!", reLock));
		Thread t2 = new Thread(new Task2("Thread 2", "This is second thread!", reLock));
		Thread t3 = new Thread(new Task2("Thread 3", "This is third thread!", reLock));
		
		t1.start();
		t2.start();
		t3.start();
	}
}

class Task2 implements Runnable{
	
	String threadName, msg;
	ReentrantLock lock;
	
	Task2(String name, String msg, ReentrantLock lock){
		this.threadName = name;
		this.msg = msg;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10;i++){

			try {
				boolean ans = lock.tryLock(1500, TimeUnit.MILLISECONDS);
				
				if (ans){
					System.out.println("Thread \"" + threadName + "\": " + msg + " (step " + (i+1) + ")");
					Thread.sleep(1000);
					lock.unlock();}
				else{
					System.out.println("Thread \"" + threadName + "\" has not asquired lock!");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}	
	}
}