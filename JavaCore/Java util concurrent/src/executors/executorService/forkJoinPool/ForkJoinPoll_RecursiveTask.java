// https://examples.javacodegeeks.com/core-java/util/concurrent/recursivetask/java-util-concurrent-recursivetask-example/

package executors.executorService.forkJoinPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoll_RecursiveTask {

	private static final int SIZE = 10000000; // With a small value of this variable, I got the best result in sequential calculations.

	public static void main(String[] args) {

		final int[] numbers = new int[SIZE];
		int maxNum = 0;

		// Start sequential calculation
		long st = System.currentTimeMillis();

		for (int i = 0; i < SIZE; i++) {
			numbers[i] = (int) (Math.random() * 10000);
			if (numbers[i] > maxNum) {
				maxNum = numbers[i];
			}
		}

		System.out.println("Calculated maximum number (sequential execution): " + maxNum + " -- Total time: " + (System.currentTimeMillis() - st));

		// Start parallel calculation
		long pt = System.currentTimeMillis();

		ForkJoinPool pool = new ForkJoinPool(4);
		MaxNumberCalculator fbn = new MaxNumberCalculator(numbers);
		System.out.println("Calculated maximum number (parallel execution): " + pool.invoke(fbn) + " -- Total time: " + (System.currentTimeMillis() - pt));
	}
	
	
	@SuppressWarnings("serial")
	static class MaxNumberCalculator extends RecursiveTask<Integer> {

	    public static final int THRESHOLD = 5;
	    private int[] numbers;
	    private int start;
	    private int end;

	    public MaxNumberCalculator(int[] numbers) {
	        this(numbers, 0, numbers.length);
	    }

	    public MaxNumberCalculator(int[] numbers, int start, int end) {

	        this.numbers = numbers;
	        this.start = start;
	        this.end = end;
	    }

	    @Override
	    public Integer compute() {
	        int length = end - start;
	        int max = 0;
	        
	        if (length < THRESHOLD) {
	            for (int x = start; x < end; x++){
	                    max = numbers[x];
	            }
		        return max;
	        } else {
	            int split = length / 2;
	            MaxNumberCalculator left = new MaxNumberCalculator(numbers, start, start + split);
	            left.fork();
	            MaxNumberCalculator right = new MaxNumberCalculator(numbers, start + split, end);
	            return Math.max(right.compute(), left.join());
	        }
	    }
	}
}
