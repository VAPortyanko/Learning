package synchronizers.lathces.cyclicBarier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier_Shooting {
	public static void main(String[] args) {
		CyclicBarrier latch = new CyclicBarrier(5);
		new Soldier2("Вася", latch);
		new Soldier2("Петя", latch);
		new Soldier2("Гриша", latch);
		new Soldier2("Егор", latch);
		new Soldier2("Иван", latch);
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
		System.out.println("Солдат " + name + " начал подготовку к стрельбе!");
		try {
			sleep((long) (5000*Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (latch) {
			System.out.println("Солдат " + name + " к стрельбе готов!");
			System.out.println("Количество солдат готовых к стрельбе - " + (latch.getNumberWaiting()+1));
			//System.out.println(latch.getParties()); возвращает количество необходимое для продолжения работы потоков(!не текущее) 
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} 
		System.out.println("Солдат " + name + " стреляет!");

		try {
			latch.await();
		} catch (InterruptedException | BrokenBarrierException e1) {
			e1.printStackTrace();
		}
		
		// Повторная стрельба.
		System.out.println("Солдат " + name + " начал подготовку к стрельбе!");
		try {
			sleep((long) (5000*Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (latch) {
			System.out.println("Солдат " + name + " к стрельбе готов!");
			System.out.println("Количество солдат готовых к стрельбе - " + (latch.getNumberWaiting()+1));
			//System.out.println(latch.getParties()); возвращает количество необходимое для продолжения работы потоков(!не текущее) 
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("Солдат " + name + " стреляет!");		
	}
}
