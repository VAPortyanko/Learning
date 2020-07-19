package lockInterface.readWriteLockInterface;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReentrantReadWriteLockEx {

    private final Map<Integer, String> m = new TreeMap<Integer, String>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public String get(Integer key) throws InterruptedException {
        r.lock();
        System.out.println("Thread " + Thread.currentThread().getName() + " have got read lock and is waiting 0.1 secconds!");
        Thread.sleep(100);
        try { 
			System.out.println("Thread " + Thread.currentThread().getName() + " have got the last element. It is " + key);
        	return m.get(key);
        }
        finally { 
        	r.unlock();
        	System.out.println("Thread " + Thread.currentThread().getName() + " release read lock!");
        }
    }
    
    public String[] allKeys() {
        r.lock();
        try { return m.keySet().toArray(new String[m.size()]); }
        finally { r.unlock(); }
    }
    
    public String put(Integer key, String value) throws InterruptedException {
        w.lock();
        System.out.println("Thread " + Thread.currentThread().getName() + " have got write lock and is waiting 5 secconds!");
        Thread.sleep(5000);
        try { 
        	System.out.println("Thread " + Thread.currentThread().getName() + " Put " + value);
        	return m.put(key, value);
        }
        finally { 
        	System.out.println("Thread " + Thread.currentThread().getName() + " release write lock!");
        	w.unlock();
        }
    }
    
    public void clear() {
        w.lock();
        try { m.clear(); }
        finally { w.unlock(); }
    }
	    
    public int getSize(){
    	r.lock();
    	try {return m.size();}
    	finally { r.unlock(); }
    }
	    
    public void showCollection(){
    	r.lock();
    	try {System.out.println(m);}
    	finally { r.unlock(); }
    }
	    
    public static void main(String[] args) throws InterruptedException {
    	ReentrantReadWriteLockEx mainClass = new ReentrantReadWriteLockEx();
    	Putter pt1 = mainClass.new Putter(mainClass, 1);
    	    	
    	Getter gt1 = mainClass.new Getter(mainClass);
    	Getter gt2 = mainClass.new Getter(mainClass);
    	Getter gt3 = mainClass.new Getter(mainClass);
	    	
    	Thread t0 = new Thread(pt1, "\"putter 1\"");
    	t0.start();

    	Thread t1 = new Thread(gt1, "\"getter 1\"");
    	Thread t2 = new Thread(gt2, "\"getter 2\"");
    	Thread t3 = new Thread(gt3, "\"getter 3\"");

    	t1.start(); 
    	t2.start(); 
    	t3.start();
	    	
    	t0.join();
	    	
    	Thread.sleep(2000);
    	mainClass.showCollection();
	}
	    
    // Putter.
    class Putter implements Runnable{
	    
    	ReentrantReadWriteLockEx rwl;
    	int decNum;
	    	
    	public Putter(ReentrantReadWriteLockEx rwl, int decNum) {
			this.rwl = rwl;
			this.decNum = decNum;
		}
	    	
		@Override
		public void run() {
			// Every 5 seconds we put an element in the collection.
			for(int i=decNum*10-10;i<decNum*10;i++){
				try {
					rwl.put(i, Integer.toString(i));
					
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
   }
	    
    // Getter.
    class Getter implements Runnable{

    	ReentrantReadWriteLockEx rwl;
	    	
   		public Getter(ReentrantReadWriteLockEx rwl) {
    		this.rwl = rwl;
		}
	    	
		@Override
		public void run() {
			// Every 0.1 seconds we get an element from the collection.
			int sizeCollection = 0;
				
			while (sizeCollection < 10){
				try {
					sizeCollection = rwl.getSize();
					rwl.get(sizeCollection-1);
						
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
    }
 }
