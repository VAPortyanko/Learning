// Конструктор семафора в качестве аргумента получает начальное количество доступных разрешений. Даже если оно равно изначально 0, то его можно увеличить используюя release метод.
package synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class ZeroPermitsSemaphore {

	public static void main(String[] args) {
		Semaphore sem = new Semaphore(0);
		new Thread(new Task2(sem)).start();
	}

}

class Task2 implements Runnable{
	
	Semaphore sem;
	
	public Task2(Semaphore sem) {
		this.sem = sem;
	}

	@Override
	public void run() {
		try {
			System.out.println(sem.availablePermits());
			sem.release();
			System.out.println(sem.availablePermits());			
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sem.availablePermits());
	}
	
}