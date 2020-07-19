package lockInterface;

import java.util.concurrent.locks.ReentrantLock;

public class LockThroughTwoMethods {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		new ReentrantLockClass(lock);
		new ReentrantLockClass(lock);
		new ReentrantLockClass(lock);
		new ReentrantLockClass(lock);
		new ReentrantLockClass(lock);
	}
}

class ReentrantLockClass extends Thread{
	ReentrantLock lock;
	
	public ReentrantLockClass(ReentrantLock lock) {
		this.lock = lock;
		this.start();
	}
	
	@Override
	public void run(){
		firstMethod();		
		secondMethod();
	}
	
	private void firstMethod(){
		lock.lock(); // You can capture a lock in one method, and release it in another (as opposed to synchronized).
		System.out.println("Thread " + Thread.currentThread().getName() + " started to execute code!");
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void secondMethod(){
		lock.lock(); // Re-capture lock by the same thread (just for example).
		try{
			for (int i=4; i<11; i++)
				System.out.println("Thread " + Thread.currentThread().getName() + " has perfomed the " + i +"-th command");
		}
		finally{
			lock.unlock(); // Each lock must be unlocked. 
			lock.unlock();
		}
	}	
}
