/*
https://metanit.com/java/tutorial/8.10.php
*/
package lockInterface.conditions;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Condition_02 {
 
   public static void main(String[] args) {
   	
	   Condition_02 lc2 = new Condition_02();
   	
       Store store = lc2.new Store();
       Producer producer = lc2.new Producer(store);
       Consumer consumer = lc2.new Consumer(store);
       new Thread(producer).start();
       new Thread(consumer).start();
   }
   

   class Store{
      private int product=0;
      ReentrantLock locker;
      Condition condition;
       
      Store(){
          locker = new ReentrantLock(); 
          condition = locker.newCondition();
      }
       
      public void get() {
           
         locker.lock();
         try{
             while (product<1)
                 condition.await();
              
             product--;
             System.out.println("Consumer got one item");
             System.out.println("Stock items: " + product);
              
             condition.signalAll();
         }
         catch (InterruptedException e){
             System.out.println(e.getMessage());
         }
         finally{
             locker.unlock();
         }
      }
      public void put() {
           
          locker.lock();
          try{
             while (product>=3)
                 condition.await();
              
             product++;
             System.out.println("Producer added one item");
             System.out.println("Stock items: " + product);

             condition.signalAll();
         }
         catch (InterruptedException e){
             System.out.println(e.getMessage());
         }
         finally{
             locker.unlock();
         }
      }
   }
   
   // класс Производитель
   class Producer implements Runnable{
     
       Store store;
       Producer(Store store){
          this.store=store; 
       }
       public void run(){
           for (int i = 1; i < 6; i++) {
               store.put();
           }
       }
   }
   
   // Класс Потребитель
   class Consumer implements Runnable{
         
        Store store;
       Consumer(Store store){
          this.store=store; 
       }
       public void run(){
           for (int i = 1; i < 6; i++) {
               store.get();
           }
       }
   }
}
