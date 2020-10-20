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
   
//  ласс ћагазин, хран€щий произведенные товары
   class Store{
      private int product=0;
      ReentrantLock locker;
      Condition condition;
       
      Store(){
          locker = new ReentrantLock(); // создаем блокировку
          condition = locker.newCondition(); // получаем условие, св€занное с блокировкой
      }
       
      public void get() {
           
         locker.lock();
         try{
             // пока нет доступных товаров на складе, ожидаем
             while (product<1)
                 condition.await();
              
             product--;
             System.out.println("ѕокупатель купил 1 товар");
             System.out.println("“оваров на складе: " + product);
              
             // сигнализируем
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
             // пока на складе 3 товара, ждем освобождени€ места
             while (product>=3)
                 condition.await();
              
             product++;
             System.out.println("ѕроизводитель добавил 1 товар");
             System.out.println("“оваров на складе: " + product);
             // сигнализируем
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
   
   // класс ѕроизводитель
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
   
   //  ласс ѕотребитель
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
