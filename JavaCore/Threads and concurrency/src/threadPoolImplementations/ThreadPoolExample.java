package threadPoolImplementations;

import java.util.LinkedList;

public class ThreadPoolExample {

	private final int nThreads;
	private final PoolWorker[] threads;
	private final LinkedList<Runnable> queue;

	public static void main(String[] args) {
		ThreadPoolExample tPE = new ThreadPoolExample(2);

		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName() + " counter - " + i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		tPE.execute(r);
		tPE.execute(r);
		tPE.execute(r);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		tPE.stop(); // if queue will be empty then the stop() method will not work because all PoolWorkers will be blocked (queue.wait()). This method must be redone.
	}

	public ThreadPoolExample(int nThreads) {
		this.nThreads = nThreads;
		queue = new LinkedList<Runnable>();
		threads = new PoolWorker[nThreads];

		for (int i = 0; i < nThreads; i++) {
			threads[i] = new PoolWorker();
			threads[i].start();
		}
	}

	public void execute(Runnable r) {
		synchronized (queue) {
			queue.addLast(r);
			queue.notify();
		}
	}
	
	public void stop() {
		for (int i = 0; i < nThreads; i++) {
			threads[i].stopPoolWorker();
		}
	}

	private class PoolWorker extends Thread {
		
		boolean stop = false;
		
		public void run() {
			Runnable r;

			while (!stop) {
				synchronized (queue) {
					while (queue.isEmpty()) {
						try {
							queue.wait();
						} catch (InterruptedException ignored) {
						}
					}

					r = (Runnable) queue.removeFirst();
				}

				// If we don't catch RuntimeException, the pool could leak threads.
				try {
					r.run();
				} catch (RuntimeException e) {
					// You might want to log something here
				}
			}
		}

		public void stopPoolWorker() {
			stop = true;
		}
	}
}
