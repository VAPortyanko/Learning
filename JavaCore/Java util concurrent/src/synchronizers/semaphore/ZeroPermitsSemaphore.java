// ����������� �������� � �������� ��������� �������� ��������� ���������� ��������� ����������. ���� ���� ��� ����� ���������� 0, �� ��� ����� ��������� ���������� release �����.
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
			System.out.println("Available permits: " + sem.availablePermits());
			sem.release();
			System.out.println("Available permits: " + sem.availablePermits());			
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Available permits: " + sem.availablePermits());
	}
	
}