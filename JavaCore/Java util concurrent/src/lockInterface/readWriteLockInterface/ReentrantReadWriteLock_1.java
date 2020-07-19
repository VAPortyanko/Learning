package lockInterface.readWriteLockInterface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLock_1 {
	public static void main(String[] args) {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		new RRWLClass(lock, true);
		new RRWLClass(lock, false);
		new RRWLClass(lock, true);
		new RRWLClass(lock, true);

	}
}



class RRWLClass implements Runnable{
	
	private static int RRWLClassNumber; // Число которое будем читать и писать.
	Thread t = new Thread(this);
	ReentrantReadWriteLock lock;
	boolean reader = true;
	
	RRWLClass(ReentrantReadWriteLock lock, boolean reader){
		this.lock = lock;
		this.reader = reader;
		t.start();
	}
	
	@Override
	public void run() {
		if(!reader)
			Pisaka();
		else{
			Chitaka();
		}	
	}
	
	void Chitaka(){
		Lock lockChitaka = lock.readLock();
		for(int i=0;i<5;i++){		

			lockChitaka.lock();
			try{
					System.out.println(Thread.currentThread().getName() + " begin read");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(Thread.currentThread().getName() + " value of RRWLClassNumber is " + RRWLClassNumber);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(Thread.currentThread().getName() +  " finish read");
			}finally{
				lockChitaka.unlock();
			}
		}	
	}
	
	void Pisaka(){
		Lock lockPisaka = lock.writeLock();
		
		for(int i=0;i<5;i++){			
		
			lockPisaka.lock();
			try{
				System.out.println(Thread.currentThread().getName() + " begin write");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				RRWLClassNumber++; 
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName() + " finish write");
			}finally{
				lockPisaka.unlock();
			}
		}	
	}
}