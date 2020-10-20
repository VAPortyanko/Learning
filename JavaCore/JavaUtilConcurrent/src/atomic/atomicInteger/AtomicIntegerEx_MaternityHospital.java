package atomic.atomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerEx_MaternityHospital {

	public static void main(String[] args) {
		
		birth("����", true);
		birth("�������", true);
		birth("�������", true);
		birth("������", true);
		
		birth("���", false);
		birth("����", false);
		birth("����", false);
		birth("���", false);
		birth("���", false);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("����� �������");
		}

		System.out.println("����� �������� ��������� - " + idsFromAtomic.countMan);
		System.out.println("����� �������� ������� - " + idsFromAtomic.countWoman);
	}
	
	private static void birth(String name, boolean sex){
		NewMan child = new NewMan(name, sex);
		child.start();
	};
}

class NewMan extends Thread{
	
	private String nameChild; // ��� �������.
	boolean sex = true;       // ��� �������(������� �� ���������).
	private String sexString="�������";
		
	// �����������.
	public NewMan(String name, boolean sex){
		this.nameChild = name;
		this.sex = sex;
		if (sex)
			new idsFromAtomic().addMan();
		else{
			new idsFromAtomic().addWoman();
			this.sexString = "�������";
		}
	}
	
	@Override
	public void run(){
		
		int timeOut = (int) (Math.random()*4501+500);
		
		System.out.println("��������� ����� �������....");
		try {
			sleep(timeOut);
		} catch (InterruptedException e) {
			System.out.println("����� �������");
		}
		
		int hours = Math.round(timeOut/1000);
		int minutes = Math.round((timeOut-hours)/100);
		System.out.println("�������(���) " + sexString + " " + nameChild + ". ���� ������ - " + hours + " ����(��)" + minutes + " �����.");
	 }
}

class idsFromAtomic{
	public static int countMan = 0;           // ���������� ���������� ������.
	public static int countWoman = 0;         // ���������� ���������� ������.
	
	private static final AtomicInteger sequenceMan = new AtomicInteger();
	private static final AtomicInteger sequenceWoman = new AtomicInteger();
	
	private int nextMan(){
		return sequenceMan.incrementAndGet();
	}
	
	private int nextWoman(){
		return sequenceWoman.incrementAndGet();
	}
	
	public void addMan(){
		idsFromAtomic.countMan = nextMan();
	}
	public void addWoman(){
		idsFromAtomic.countWoman = nextWoman();
	}
}
