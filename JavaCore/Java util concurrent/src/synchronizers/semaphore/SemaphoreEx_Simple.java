package synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreEx_Simple {
	public static void main(String[] args) {
		Semaphore sem = new Semaphore(2);
		Thread t1 = new Thread(new Task(sem)), t2 = new Thread(new Task(sem)), t3 = new Thread(new Task(sem));
		
		t1.start();
		t2.start();
		t3.start();
	}
}

class Task implements Runnable{
	Semaphore sem;
	
	public Task(Semaphore sem) {
		this.sem = sem;
	}

	@Override
	public void run() {
		try {
			sem.acquire();
			System.out.println("Thread \"" + Thread.currentThread().getName() + "\" has acquire permit!");
			Thread.sleep(2000);
			System.out.println("Thread \"" + Thread.currentThread().getName() + "\" after sleeping 2 seconds");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sem.release();
	}
}