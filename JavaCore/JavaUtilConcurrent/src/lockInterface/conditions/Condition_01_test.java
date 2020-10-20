package lockInterface.conditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Condition_01_test {
	final Lock lock = new ReentrantLock();
	final Condition notFull  = lock.newCondition(); 
	final Condition notEmpty = lock.newCondition(); 

	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	public static void main(String[] args) {
		Condition_01_test lc = new Condition_01_test();
		new Thread(lc.new PutThread(lc)).start();
		new Thread(lc.new TakeThread(lc)).start();
		Thread t2 = new Thread(lc.new TakeThread(lc));
		t2.setPriority(10);
		t2.start();
		
	}
	   
	public void put(Object x) throws InterruptedException {
		lock.lock();
	    try {
	    	while (count == items.length)
	    		notFull.await();
	        items[putptr] = x;
	        System.out.println("Thread " + Thread.currentThread().getName() + " put string " + x);
	        Thread.sleep(1000);
	        if (++putptr == items.length) putptr = 0;
	        ++count;
	        notEmpty.signal();
	    } finally {
	    	lock.unlock();
	    }
	}

	public Object take() throws InterruptedException {
		lock.lock();
	    try {
	    	while (count == 0)
	    		notEmpty.await();
	        Object x = items[takeptr];
	        System.out.println("Thread " + Thread.currentThread().getName() + " take string " + x);
	        Thread.sleep(1000);
	        if (++takeptr == items.length) takeptr = 0;
	        --count;
	        notFull.signal();
	        return x;
	    } finally {
	    	lock.unlock();
	    }
	}
	
	class TakeThread implements Runnable{
		
		private final Condition_01_test lc; 
		
		public TakeThread(Condition_01_test lc2) {
			this.lc = lc2; 
		}
		
		@Override
		public void run() {
			for(int i=0; i<10; i++){
				try {
					lc.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class PutThread implements Runnable{
		
		private final Condition_01_test lc; 
		
		public PutThread(Condition_01_test lc2) {
			this.lc = lc2; 
		}
		
		@Override
		public void run() {
			for(int i=0; i<20; i++){
				try {
					lc.put("New Sting");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
