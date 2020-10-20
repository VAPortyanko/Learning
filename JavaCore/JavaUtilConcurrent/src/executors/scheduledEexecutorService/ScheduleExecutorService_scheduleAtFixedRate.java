// https://tproger.ru/translations/java8-concurrency-tutorial-1/

package executors.scheduledEexecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorService_scheduleAtFixedRate {

	public static void main(String[] args) {
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

		int initialDelay = 0;
		int period = 1;
		executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
		
	}

}
