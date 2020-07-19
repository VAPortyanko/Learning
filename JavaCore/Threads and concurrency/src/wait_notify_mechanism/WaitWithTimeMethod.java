package wait_notify_mechanism;

public class WaitWithTimeMethod {
	
	public static void main(String[] args) throws InterruptedException {
		
		WaitWithTimeMethod.NewThread nt = new WaitWithTimeMethod.NewThread();
		
		Thread t1= new Thread(nt);
		t1.start();
		/* The thread t1 waiting 5 seconds and notify himself(change status from TIME_WAITING to BLOCKED 
		 * (if synchronized method was run by other thread) or to RUNNABLE(and continue work))*/
		Thread.sleep(1000);
		
		synchronized (nt) {
			while(true){
				System.out.println(t1.getState());
				Thread.sleep(1000);
			}
		}
	}
	
	private static class NewThread implements Runnable{
		@Override
		public void run() {
			synchronized (this) {
				System.out.println("The thread " + Thread.currentThread().getName() + " is started!");
				
				try {
					wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("The thread " + Thread.currentThread().getName() + " is finished!");
			}
			
		}
	}
	
	
}

