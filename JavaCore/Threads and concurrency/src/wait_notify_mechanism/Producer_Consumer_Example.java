package wait_notify_mechanism;

public class Producer_Consumer_Example {
	  
    public static void main(String[] args) {
    	Producer_Consumer_Example wn = new Producer_Consumer_Example();
    	
        Store store = wn.new Store();
        Producer producer = wn.new Producer(store);
        Consumer consumer = wn.new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
    
    class Store{
       private int product=0;
       public synchronized void get() {
          while (product<1) {
             try {
                wait();
             }
             catch (InterruptedException e) {
             }
          }
          product--;
          System.out.println("Consumer bought one item.");
          System.out.println("There are " + product + " items in stock");
          notify();
       }
       public synchronized void put() {
           while (product>=3) {
             try {
                wait();
             }
             catch (InterruptedException e) { 
             } 
          }
          product++;
          System.out.println("Producer added one product.");
          System.out.println("There are " + product + " items in stock");
          notify();
       }
    }

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
