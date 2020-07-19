package synchronizers.lathces.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch_Shooting {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5);
		new Soldier("Vasia", latch);
		new Soldier("Petia", latch);
		new Soldier("Grisha", latch);
		new Soldier("Egor", latch);
		new Soldier("Ivan", latch);
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
		System.out.println("The soldier "+ name +" began preparations for the shooting!");
		try {
			sleep((long) (5000*Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (latch) {
			System.out.println("The soldier "+ name +" is ready for shooting!");
			System.out.println("Number of soldiers ready to fire: " + (5 - latch.getCount()+1));
			latch.countDown();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Soldier "+ name +" is shooting!");
	}
}
