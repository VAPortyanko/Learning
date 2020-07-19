package executors.callableAndFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Future_Cancel {
	public static void main(String[] args) throws Exception {

		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				for (int i = 0; i < 1000; i++) {
					System.out.println(Thread.currentThread().getName() + ": " + i);
					if (Thread.interrupted())
						return Thread.currentThread().getName() + ": Result (thread was interrupted) = " + String.valueOf(i);
				}
				return Thread.currentThread().getName() + ": Result = " + String.valueOf(999);
			}
		};

		FutureTask<String> task1 = new FutureTask<>(callable);
		FutureTask<String> task2 = new FutureTask<>(callable);
		FutureTask<String> task3 = new FutureTask<>(callable);

		Thread t1 = new Thread(task1, "T1");
		Thread t2 = new Thread(task2, "T2");
		Thread t3 = new Thread(task3, "T3");

		// If task was canceled, Thread t1 will not start!
		task1.cancel(true);
		t1.start();

		// Thread t2 will be interrupted by calling the cancel(true) method (If
		// argument of the method cancel = false, it will not be interrupted).
		t2.start();
		Thread.sleep(0, 1);
		task2.cancel(true);

		t3.start();

		// System.out.println(task1.get()); throw an CancelationException.
		// System.out.println(task2.get()); throw an CancelationException.
		System.out.println(task3.get());
		System.out.println("Task 1 canceled status: " + task1.isCancelled());
		System.out.println("Task 2 canceled status: " + task2.isCancelled());
		System.out.println("Task 3 canceled status: " + task3.isCancelled());

		// Returns true if this task completed. Completion may be due to normal
		// termination, an exception, or cancellation -- in all of these cases,
		// this method will return true.
		System.out.println("Task 1 isDone status: " + task1.isDone());
		System.out.println("Task 2 isDone status: " + task2.isDone());
		System.out.println("Task 3 isDone status: " + task3.isDone());
	}
}