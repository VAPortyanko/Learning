package synchronizers.semaphore;

import java.util.concurrent.Semaphore;
// ����� ����� ��������� ��� ������������ semaphore.ecquire � semaphore.release(��� ������ � ���� �� semaphore)
// �������� ���� ������� ��� ������� ��������� ����������.

public class Semaphore_SemaphoreMetods {
	// ������� ������ ��������.
	static Semaphore semaphore = new Semaphore(6); // �������� ������������ 6 ������(���� 1 ����� ������� ������� ��� 5 ����������).
	
	public static void main(String[] args) throws InterruptedException {
		Semaphore_SemaphoreMetods obj1 = new Semaphore_SemaphoreMetods();
		obj1.semaphoreMetods(semaphore);
	}
	
	void semaphoreMetods(Semaphore semaphore) throws InterruptedException{
		System.out.println("��������� ��������� ���������� ��� ��������. �� ������� ���������� - " + semaphore.availablePermits());
		System.out.println();
		
		System.out.println("��������� �� ������� ������������? - " + semaphore.isFair() + ".(������������ ������� - ������, ������� ��� �������� ������� ��������� � ����� �������, ����� ��� ����� �������� ��������� ��� ���� ���� � ������� ��� ����� ���������)");
		System.out.println();
		
		semaphore.acquire(); // ������ ������ ���������� �������� (����� ����� ��������� ����������)
		System.out.println("��������� 1 ���������� �������� � ������� ������ semaphore.acquire()");
		System.out.println("��������� ��������� ���������� ��� ��������. �� ������� ����������(����� ������� ������) - " + semaphore.availablePermits());
		System.out.println();
		
		semaphore.acquire(3); // ������ ����� ���� ����������.
		System.out.println("��������� 3 ���������� �������� � ������� ������ semaphore.acquire(3)");
		System.out.println("��������� ��������� ���������� ��� ��������. �� ������� ����������(����� ������� ����) - " + semaphore.availablePermits());
		System.out.println();
		
		int acquireCount = semaphore.drainPermits(); // ������ ���� ��������� ����������.
		System.out.println("��������� ��� ��������� ���������� �������� � ���������� - " + acquireCount + " � ������� ������ semaphore.drainPermits()");
		System.out.println("��������� ��������� ���������� ��� ��������. �� ������� ����������(����� ������� ���� ��������� ���������) - " + semaphore.availablePermits());
		System.out.println();
		
		int waitingCount = semaphore.getQueueLength();
		System.out.println("��������� ���������� �������(!�� ��������) ��������� ������� ����� ������� semaphore.getQueueLength()");
		System.out.println("����� ��������� �������� �� ������ �������� - " + waitingCount);
		System.out.println();
		
		System.out.println("������� ������������ �����, � ������� ��������� ���������� ��������� �������� �������. ����� �������� ������ � ���������, ������� ������� ����� �������� �������� ��� 3 ����������");
		NewThread newThreed = new NewThread(semaphore);
		newThreed.start();
		System.out.println();
		
		System.out.println("������� ��������� ��� 3 ���������� � ������ ��������� ����� ��������� �������");
		semaphore.acquire(3); // ��� �� �����, ��� ��� ��� ���������� �������� ������. ����� �� ��� ���, ���� ������������ �����, ��������, �� ��������� 1 ��� ��������� ����������(���� ���� �� �� �� �������).
		System.out.println("����������� ������!"); // ������������ ����� ��������� 4 ����������(������ ���������� + ���� ���������� ��������).
		System.out.println();
		
		
		System.out.println("��������� ��������� ���������� ��� ��������. �� ������� ���������� - " + semaphore.availablePermits());
		System.out.println("������ ���������� ����������");
		semaphore.acquire();
		System.out.println("��������� ��������� ���������� ��� ��������. �� ������� ���������� - " + semaphore.availablePermits());
		System.out.println();
		
		System.out.println("��� ��������� ���������� ���������. ������� ������� tryAcuire()");
		semaphore.tryAcquire();
		System.out.println("������� ��������� ���������� �� ������(��� ��� ��������� ���) ������������� ������� ������������");
	}
}

class NewThread extends Thread{
	Semaphore semaphore;
	
	public NewThread(Semaphore semaphore){
		this.semaphore = semaphore;
	}
	
	@Override
	public void run(){
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("(������������ ����� �1)����� ������� ����������� 1 ��� ��������� ����������, ������� ���������� � ������� �� �������� - " + semaphore.getQueueLength());
		
		System.out.println("(������������ ����� �1) ���� �� ��������� ������? - " + semaphore.hasQueuedThreads());
		semaphore.release(4); // ��� ����������� ������ � ������ main ����� ������������� �������, ���������� ���������� ����� 3 ����������, ��� ��� main ���� ������� 3 ����������.
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("(������������ ����� �1) ���� �� ��������� ������? - " + semaphore.hasQueuedThreads());
	}
}
