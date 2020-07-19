// Примечание: метод sleep бросает исключение и снимает флаг прерывания, метод lockInterruptibly - только бросает исключение (Вроде как).
package lockInterface.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptibly {
	public static void main(String[] args) throws InterruptedException {
		ReentrantLock reLock = new ReentrantLock();
		 
		Thread t1 = new Thread(new Task3("Thread 1", "This is first thread!", reLock));
		Thread t2 = new Thread(new Task3("Thread 2", "This is second thread!", reLock));
		Thread t3 = new Thread(new Task3("Thread 3", "This is third thread!", reLock));
		
		t1.start();
		t2.start();
		t3.start();	
		
		Thread.sleep(5000);
		
		t2.interrupt();
	}
}

class Task3 implements Runnable{
	
	String threadName, msg;
	ReentrantLock lock;
	
	Task3(String name, String msg, ReentrantLock lock){
		this.threadName = name;
		this.msg = msg;
		this.lock = lock;
	}
	
	@Override
	public void run() {

		for(int i=0; i<10; i++){
			try {
				lock.lockInterruptibly();
				System.out.println("Thread \"" + threadName + "\": " + msg + " (step " + (i+1) + ")");
				Thread.sleep(1000);
				lock.unlock();
			} catch (InterruptedException e) {            
				System.out.println("Thread \"" + threadName + "\" was interrupted!");
				if (lock.isHeldByCurrentThread()) // Here we close the lock, since it could be obtained in the lockIntrerruptibly method, and then an exception was thrown on the Sleep method.
					lock.unlock();
				return;
			}
		}
	}
}