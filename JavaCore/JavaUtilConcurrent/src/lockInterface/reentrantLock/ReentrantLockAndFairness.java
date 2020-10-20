package lockInterface.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockAndFairness {
	public static void main(String[] args) {
		ReentrantLock reLock = new ReentrantLock();
		// ReentrantLock reLock = new ReentrantLock(true); - честность блокировки - гарантирует, что потоки будут получать локи в том порядке, в котором они его запрашивали,
		// но не гарантирует, что к моменту освобождения блокировки одним потоком другой запросит блокировку(Что позволит первому потоку снова захватить блокировку).
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