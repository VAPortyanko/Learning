package runnableImplementationExample;

public class MultiThread_Runnable {
	public static void main(String[] args) {
		SimpleThread c1 = new SimpleThread(15);
		SimpleThread c2 = new SimpleThread(10);
		Thread t1 = new Thread(c1);
		t1.start();
		t1 = new Thread(c2);
		t1.start();
		//t1.start(); // This line will throw IllegalThreadStateException (It is forbidden to call the method "start" twice, you need to create a new object)
		new Thread(c1).start();
	}
}

class SimpleThread implements Runnable{

	int simpleNumber;
	
	SimpleThread(int a){
		this.simpleNumber = a;
	}
	
	
	@Override
	public void run() {
		System.out.println("Running thread '" + Thread.currentThread().getName() +"'");
		for (int i = simpleNumber; i>0; i--){
			System.out.println("Поток '" + Thread.currentThread().getName() + "' " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Thread '" + Thread.currentThread().getName() + "' is interrupted.");
			}
		}	
		System.out.println("Thread '" + Thread.currentThread().getName() + "' was finished.");
	}
}