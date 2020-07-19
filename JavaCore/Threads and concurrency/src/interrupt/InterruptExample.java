package interrupt;

public class InterruptExample {


	static class R extends Thread {
	
		@Override
		public void run() {
	
		    System.out.println("The thread  " + getName() + " started work.");
		    execute();

		}
		
		void execute(){
		    while (true) {
		    	synchronized (this){
		    		if (Thread.currentThread().isInterrupted()){
			   			System.out.println("The thread was interrupted!");
			   			break;
			   		} else {
				    	System.out.println("Doing work ...");
				    	try {
							sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
		    	}	
	    	}
		}
	}
	

	public static void main(String[] args) throws InterruptedException {
		
	
	    Thread th = new R();
	    th.start();
	    Thread.sleep(2000);
	    synchronized(th){
	    	th.interrupt();
	    }
	}
}
//set Interapted flag with interrupt method, check flag with isInterrupted(non static) and interrupt(static) methods.
//method interrupt droped interrupted flag.
