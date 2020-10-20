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
		lock.lock(); // ��������� ��� ����� � 1 ������, � ����� � ������(� ������� �� synchronized)
		System.out.println("����� " + Thread.currentThread().getName() + " ����� ���������� ����");
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void secondMethod(){
		lock.lock(); // ��������� ������ ���� ����� �������(������ ��� �������).
		try{
			for (int i=1; i<11; i++)
				System.out.println("����� " + Thread.currentThread().getName() + " �������� " + i +"-� �������");
		}
		finally{
			lock.unlock(); // ������ ��� ���������� �����. 
			lock.unlock();
		}
	}	
}
