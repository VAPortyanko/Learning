package synchronizers.lathces.countDownLatch;

import java.util.concurrent.CountDownLatch;

class PrepareStartWaitingResult {
	
	private final int N = 5;
	
	public static void main(String[] args) throws InterruptedException {
		PrepareStartWaitingResult testClass = new PrepareStartWaitingResult();
		testClass.test();
	}
	
	void test() throws InterruptedException {
		
		CountDownLatch startSignal = new CountDownLatch(1);
	    CountDownLatch doneSignal = new CountDownLatch(N);

	    for (int i = 0; i < N; ++i)                                      // create and start threads
	    	new Thread(new Worker(startSignal, doneSignal)).start();

	    System.out.println("Thread initialization");                     // don't let run yet
	    startSignal.countDown();                                         // let all threads proceed
	    System.out.println("Do something parallel with other threads.");
	    doneSignal.await();                                              // wait for all to finish
	    System.out.println("After completing tasks");
	   }


	class Worker implements Runnable {
	   private final CountDownLatch startSignal;
	   private final CountDownLatch doneSignal;
	   Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
	      this.startSignal = startSignal;
	      this.doneSignal = doneSignal;
	   }
	   public void run() {
	      try {
	        startSignal.await();
	        doWork();
	        doneSignal.countDown();
	      } catch (InterruptedException ex) {} // return;
	   }

	   void doWork() { System.out.println("Thread " + Thread.currentThread().getName() + " doing some work!"); }
	 }
}