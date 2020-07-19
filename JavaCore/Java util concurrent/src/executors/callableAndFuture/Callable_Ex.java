package executors.callableAndFuture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Callable_Ex implements Callable<String> {
	 
    @Override
    public String call() throws Exception {
        Thread.sleep(300);
        return Thread.currentThread().getName();
    }
     
    public static void main(String args[]){

        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<String>> list = new ArrayList<Future<String>>();

        Callable<String> callable = new Callable_Ex();
        for(int i=0; i< 100; i++){
            Future<String> future = executor.submit(callable);

            list.add(future);
        }
        for(Future<String> fut : list){
            try {
                System.out.println(new Date()+ "::" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        
        executor.shutdown();
    }
 
}
