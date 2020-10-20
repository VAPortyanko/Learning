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
        Thread.sleep(1000);
        // возвращает им€ потока, который выполн€ет callable таск
        return Thread.currentThread().getName();
    }
     
    public static void main(String args[]){
        //ѕолучаем ExecutorService утилитного класса Executors с размером gпула потоков равному 10
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //создаем список с Future, которые ассоциированы с Callable
        List<Future<String>> list = new ArrayList<Future<String>>();
        // создаем экземпл€р MyCallable
        Callable<String> callable = new Callable_Ex();
        for(int i=0; i< 100; i++){
            //сабмитим Callable таски, которые будут 
            //выполнены пулом потоков
            Future<String> future = executor.submit(callable);
            //добавл€€ Future в список, 
            //мы сможем получить результат выполнени€
            list.add(future);
        }
        for(Future<String> fut : list){
            try {
                // печатаем в консоль возвращенное значение Future
                // будет задержка в 1 секунду, потому что Future.get()
                // ждет пока таск закончит выполнение
                System.out.println(new Date()+ "::" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        
        executor.shutdown();
    }
 
}
