package executors.callableAndFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Future_Get_ExecutionException implements Callable<Integer> {

  private boolean throwException;

  public Future_Get_ExecutionException(boolean throwException) {
    this.throwException = throwException;
  }

  @Override
  public Integer call() throws Exception {
    if(this.throwException == true) {
      throw new Exception();
    }
    return 0;
  }
  
  public static void main(String[] args) {
	    Callable<Integer> callable = new Future_Get_ExecutionException(true);

	    ExecutorService exec = Executors.newFixedThreadPool(3);
	    Future<Integer> future = exec.submit(callable);

	    try {
	      int result = future.get();
	      System.out.println(result); 
	    } catch (Exception e) {
	      // The exception will be printed out
	      System.out.println("Exception: " + e);
	    }
	    
	    exec.shutdown();
  }
}