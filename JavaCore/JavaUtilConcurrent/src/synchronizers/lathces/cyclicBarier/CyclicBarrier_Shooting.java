package synchronizers.lathces.cyclicBarier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier_Shooting {
	public static void main(String[] args) {
		CyclicBarrier latch = new CyclicBarrier(5);
		new Soldier2("����", latch);
		new Soldier2("����", latch);
		new Soldier2("�����", latch);
		new Soldier2("����", latch);
		new Soldier2("����", latch);
	}
}

class Soldier2 extends Thread{
	private String name;
	private CyclicBarrier latch;
	
	public Soldier2(String name, CyclicBarrier latch){
		this.name = name;
		this.latch = latch;
		this.start();
	}
	
	@Override
	public void run(){
		System.out.println("������ " + name + " ����� ���������� � ��������!");
		try {
			sleep((long) (5000*Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (latch) {
			System.out.println("������ " + name + " � �������� �����!");
			System.out.println("���������� ������ ������� � �������� - " + (latch.getNumberWaiting()+1));
			//System.out.println(latch.getParties()); ���������� ���������� ����������� ��� ����������� ������ �������(!�� �������) 
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} 
		System.out.println("������ " + name + " ��������!");

		try {
			latch.await();
		} catch (InterruptedException | BrokenBarrierException e1) {
			e1.printStackTrace();
		}
		
		// ��������� ��������.
		System.out.println("������ " + name + " ����� ���������� � ��������!");
		try {
			sleep((long) (5000*Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (latch) {
			System.out.println("������ " + name + " � �������� �����!");
			System.out.println("���������� ������ ������� � �������� - " + (latch.getNumberWaiting()+1));
			//System.out.println(latch.getParties()); ���������� ���������� ����������� ��� ����������� ������ �������(!�� �������) 
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("������ " + name + " ��������!");		
	}
}
