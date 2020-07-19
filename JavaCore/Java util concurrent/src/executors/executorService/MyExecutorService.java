package executors.executorService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyExecutorService {
	public static void main(String[] args) throws Exception{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		System.out.println("submit worker 1");
		Future<String> future1 = executor.submit(new Worker("worker1"));
		System.out.println("submit worker 2");
		Future<String> future2 = executor.submit(new Worker("worker2"));
		
		System.out.println("Result from worker 1: " + future1.get());
		System.out.println("Result from worker 2: " + future2.get());
		System.out.println("-------------------------------------------------------");
		
		System.out.println("submit workers using invokeAll()"); // Постановка в очередь сразу нескольких потоков. Метод invokeAll гарантирует завершение всех потоков по выходу из него.
		List<Future<String>> futures = executor.invokeAll(Arrays.asList(new Worker("worker1"), new Worker("worker2")));
		System.out.println("Exited involkeAll()");
		
		for(Future<String> future: futures){
			System.out.println("Result from workers: " + future.get());
		}
		
		System.out.println("-------------------------------------------------------");
		System.out.println("Complete the task, kill the created executor, otherwise jvm will not end.");
		executor.shutdown();
	}
}

class Worker implements Callable<String>{

	private final String name;
	
	public Worker(String name) {
		this.name=name;
	}
	
	@Override
	public String call() throws Exception {
		long sleepTime =(long) (Math.random()*10000L);
		System.out.println(name + " started, going to sleep for " + sleepTime);
		Thread.sleep(sleepTime);
		System.out.println(name + " finished");
		return name;
	}
	
}
