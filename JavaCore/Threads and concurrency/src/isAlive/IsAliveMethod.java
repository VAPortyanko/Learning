package isAlive;

public class IsAliveMethod extends Thread{
	
	public void run(){
		for (int i = 3; i>0; i--){
			System.out.println("Thread '" + Thread.currentThread().getName() +"': " + i);
			System.out.println("Thread '" + Thread.currentThread().getName() +"' is alive: " + this.isAlive());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1;
		t1 = new IsAliveMethod();
		System.out.println("Thread '" + t1.getName() +"' before start is alive: " + t1.isAlive());
		t1.start();
		Thread.sleep(1000);
		System.out.println("Thread '" + t1.getName() +"' after start is alive: " + t1.isAlive());
	}
}
