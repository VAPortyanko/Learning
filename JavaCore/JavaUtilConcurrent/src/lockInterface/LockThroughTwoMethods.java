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
		lock.lock(); // захватить лок можно в 1 методе, а снять в другом(в отличии от synchronized)
		System.out.println("Поток " + Thread.currentThread().getName() + " начал выполнение кода");
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void secondMethod(){
		lock.lock(); // Повторный захват лока темже потоком(просто для примера).
		try{
			for (int i=1; i<11; i++)
				System.out.println("Поток " + Thread.currentThread().getName() + " выполнил " + i +"-ю команду");
		}
		finally{
			lock.unlock(); // Каждый лок необходимо снять. 
			lock.unlock();
		}
	}	
}
