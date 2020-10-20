package synchronizers.lathces.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch_Shooting {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5);
		new Soldier("Вася", latch);
		new Soldier("Петя", latch);
		new Soldier("Гриша", latch);
		new Soldier("Егор", latch);
		new Soldier("Иван", latch);
	}
}

class Soldier extends Thread{
	private String name;
	private CountDownLatch latch;
	
	public Soldier(String name, CountDownLatch latch){
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
			System.out.println("Количество солдат готовых к стрельбе - " + (5 - latch.getCount()+1));
			latch.countDown();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Солдат " + name + " стреляет!");
	}
}
