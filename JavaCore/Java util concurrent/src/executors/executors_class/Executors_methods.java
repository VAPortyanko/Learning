package executors.executors_class;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Executors_methods {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ExecutorService exec1 = Executors.newCachedThreadPool();
		ExecutorService exec2 = Executors.newFixedThreadPool(5);
		ExecutorService exec3 = Executors.newSingleThreadExecutor();
		ExecutorService exec4 = Executors.newWorkStealingPool();		
		ScheduledExecutorService scExec1 = Executors.newScheduledThreadPool(5);
		ScheduledExecutorService scExec2 = Executors.newSingleThreadScheduledExecutor();
	}
}
